package com.iexceed.esoko.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.SmppRouteRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.System_transactionRepo;
import com.iexceed.esoko.domain.dao3.OperatorRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.domain.entity3.Operator;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ms.Header;
import com.iexceed.esoko.jaxb.ms.QuerySMSRatesRes;
import com.iexceed.esoko.jaxb.ms.QueryUserBillingSummaryRes;
import com.iexceed.esoko.jaxb.ms.QueryUserTransactionsRes;
import com.iexceed.esoko.jaxb.ms.SMSDTLS;
import com.iexceed.esoko.jaxb.ms.TRNDTLS;
import com.iexceed.esoko.jaxb.ms.USRBILDTLS;
import com.iexceed.esoko.jaxb.ms.UserFundTransferReq;
import com.iexceed.esoko.jaxb.ms.UserFundTransferRes;
import com.iexceed.esoko.ne.service.NetworkBillingService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class UserBillingService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	System_accountRepo accountRepo;
	@Autowired
	System_transactionRepo txnRepo;
	@Autowired
	SmppRouteRepo smsRepo;
	@Autowired
	NetworkBillingService nwBillingService;
	@Autowired
	CurrencyRepo currRepo;
	@Autowired
	SystemUserRepo userRepo;
	@Autowired
	ForexRepo forexRepo;
	@Autowired
	OperatorRepo operatorRepo;
	
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;

	public QueryUserBillingSummaryRes queryUserBillingSummary(String userId) {
		QueryUserBillingSummaryRes summaryResp = new QueryUserBillingSummaryRes();

		log.info("Inside QueryUserBillingSummaryRes -> queryUserBillingSummary");
		log.debug("UserId->" + userId);
		System_account entity = accountRepo.queryAccDetails(userId);
		try {
			if (entity == null) {
				ERROR = ERROR_CODE.ES_NR_001;
			} else {
				if (userRepo.exists(SystemUser.class, userId)) {
					SystemUser user = userRepo.queryUserDetails(userId);
					String actualCurrency = user.getCurrencyId();
					USRBILDTLS bilDtls = new USRBILDTLS();
					String baseCurrency = entity.getAcCurrency();

					bilDtls.setAccountNo(entity.getAccountNo());
					if (actualCurrency.equals(baseCurrency)) {
						bilDtls.setBalance(entity.getBalance().floatValue());
					} else {
						Forex forex = forexRepo.queryfxRatesbyCcy(baseCurrency,
								actualCurrency);
						bilDtls.setBalance(entity.getBalance().floatValue()
								* forex.getRate().floatValue());
					}
					bilDtls.setCurrency(actualCurrency);
					Currency currency = currRepo.findByCcyCode(user
							.getCurrencyId());
					if (currency != null) {
						bilDtls.setCurrSymbol(currency.getSymbol());
						bilDtls.setCurrencyName(currency.getName());
					}
					bilDtls.setAccountName(entity.getAccName());
					summaryResp.setUSRBILDTLS(bilDtls);
					ERROR = ERROR_CODE.ES_SC_001;
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_002;

			Utils.getStackTrace(e);
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.MS,
				"UserBillingService", "queryUserBillingSummary", "", ERROR);
		summaryResp.setHeader(header);
		return summaryResp;
	}

	public QueryUserTransactionsRes queryUserTransactions(String userId,
			String fromDate, String toDate, String servicename, String baseCcy,
			String quoteCcy) {
		QueryUserTransactionsRes txnResp = new QueryUserTransactionsRes();
		try {
			log.info("Inside QueryUserTransactionsRes -> queryUserTransactions");
			log.debug("fromDate ->" + Utils.getFormatedDate(fromDate));
			log.debug("toDate ->" + Utils.getFormatedDate(toDate));
			log.debug("servicename -> " + servicename);
			log.debug("userId -> " + userId);
			log.debug("Base currency: " + baseCcy);
			log.debug("Quote currency: " + quoteCcy);
			List<System_transaction> entity = (List<System_transaction>) txnRepo
					.queryTransactions(Utils.getFormatedDate(fromDate),
							Utils.getFormatedDate(toDate), servicename, userId);
			List<TRNDTLS> txnList = new ArrayList<TRNDTLS>();
			log.info("entity -> " + entity);

			if (entity.size() != 0) {
				if (!baseCcy.equals(quoteCcy)) {
					nwBillingService.convertAmnts(entity, baseCcy, quoteCcy);
				}
				if (entity.size() != 0) {
					for (System_transaction txnLoop : entity) {
						TRNDTLS txnDtls = new TRNDTLS();
						txnDtls.setBalance(Float.parseFloat(txnLoop
								.getBalance().toString()));
						if (null != txnLoop.getTransactionDate()) {
							txnDtls.setDate(Utils.getDDMMMYYFormat(txnLoop
									.getTransactionDate().toString(), 0));
						}
						log.debug("Date -> " + txnDtls.getDate());
						txnDtls.setServiceName(txnLoop.getTransCode());
						txnDtls.setDescription(txnLoop.getDescription());
						txnDtls.setCrDr(txnLoop.getCrDr());
						txnDtls.setLcyAmount(Float.parseFloat(txnLoop
								.getLcyAmount().toString()));
						txnList.add(txnDtls);
						txnDtls = null;
					}
					txnResp.getTRNDTLS().addAll(txnList);
					header = (Header) HeaderFactory.getHeader(HeaderType.MS,
							"UserBillingService", "QueryUserTransactions", "",
							ERROR_CODE.ES_SC_001);
				} else {
					header = (Header) HeaderFactory.getHeader(HeaderType.MS,
							"UserBillingService", "QueryUserTransactions", "",
							ERROR_CODE.ES_MS_002);
				}
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.MS,
						"UserBillingService", "QueryUserTransactions", "",
						ERROR_CODE.ES_MS_002);
			}
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"UserBillingService", "QueryUserTransactions", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		} catch (DatatypeConfigurationException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"UserBillingService", "QueryUserTransactions", "",
					ERROR_CODE.ES_DT_001);
		}
		txnResp.setHeader(header);
		return txnResp;
	}

	public QuerySMSRatesRes querySMSRates(String countryId, String currency) {
		QuerySMSRatesRes smsResp = new QuerySMSRatesRes();
		Header header = null;
		try {
			log.info("Inside QuerySMSRatesRes -> querySMSRates");
			log.debug("countryId -> " + countryId);
			log.debug("currency -> " + currency);
			List<Map<String, Object>> entity = (List<Map<String, Object>>) smsRepo
					.querySmsRates(countryId);
			List<SMSDTLS> smsList = new ArrayList<SMSDTLS>();
			log.info("entity -> " + entity);

			if (entity.size() != 0) {
				for (Map<String, Object> smsLoop : entity) {
					SMSDTLS smsDtls = new SMSDTLS();
					log.info("getCreatedTs->"
							+ smsLoop.get("operatorId").toString());
					
					Object operatorId = smsLoop.get("operatorId");
					if (operatorId != null) {
						Operator operator = operatorRepo.findOne(Operator.class,
								operatorId.toString());
						if(operator != null){
							smsDtls.setProviderName(operator.getName());
						}					
					}
					
					smsDtls.setAmount(Float.parseFloat(smsLoop.get("price")
							.toString()));
					Currency curr = currRepo.findByCcyCode(smsLoop.get(
							"currency").toString());
					smsDtls.setCurrency(curr.getSymbol());
					smsList.add(smsDtls);
					smsDtls = null;
				}
				smsResp.getSMSDTLS().addAll(smsList);
				header = (Header) HeaderFactory.getHeader(HeaderType.MS,
						"QuerySMSRatesRes", "querySMSRates", "",
						ERROR_CODE.ES_SC_001);
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.MS,
						"QuerySMSRatesRes", "querySMSRates", "",
						ERROR_CODE.ES_MS_003);
			}
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"QuerySMSRatesRes", "querySMSRates", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}
		smsResp.setHeader(header);
		return smsResp;
	}

	public UserFundTransferRes userFundTransfer(UserFundTransferReq req) {
		UserFundTransferRes trfsRes = new UserFundTransferRes();
		// FNDTRFDTLS fndsDtls = req.getFNDTRFDTLS().get(0);

		return trfsRes;
	}
}
