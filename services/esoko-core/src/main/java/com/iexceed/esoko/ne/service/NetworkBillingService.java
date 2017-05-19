package com.iexceed.esoko.ne.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.acc.AccountingEngine;
import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.SmppRouteRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.System_transactionRepo;
import com.iexceed.esoko.domain.dao.Transaction_codeRepo;
import com.iexceed.esoko.domain.dao3.OperatorRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.domain.entity.Transaction_code;
import com.iexceed.esoko.domain.entity3.Operator;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.NwkFundTransferReq;
import com.iexceed.esoko.jaxb.ns.NwkFundTransferRes;
import com.iexceed.esoko.jaxb.ns.QRACCOUNTDTLSLIST;
import com.iexceed.esoko.jaxb.ns.QRSMSDTLS;
import com.iexceed.esoko.jaxb.ns.QueryAccountDtlsListRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkBillingSummaryRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkSMSRatesRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkTransactionsRes;
import com.iexceed.esoko.jaxb.ns.QueryTransactionServicesRes;
import com.iexceed.esoko.jaxb.ns.TRNDTLS;
import com.iexceed.esoko.jaxb.ns.TXNSERVICES;
import com.iexceed.esoko.jaxb.ns.USRBILDTLS;
import com.iexceed.esoko.jaxb.se.FXRTDTLS;
import com.iexceed.esoko.jaxb.se.QRFXRTDTLS;
import com.iexceed.esoko.jaxb.se.QueryfxRatesReq;
import com.iexceed.esoko.jaxb.se.QueryfxRatesRes;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.Cost;
import com.iexceed.esoko.objects.TRANSACTION_CODE;
import com.iexceed.esoko.se.service.FxRatesService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class NetworkBillingService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	System_accountRepo accountRepo;
	@Autowired
	System_transactionRepo txnRepo;
	@Autowired
	SmppRouteRepo smsRepo;
	@Autowired
	Transaction_codeRepo codeRepo;
	@Autowired
	FxRatesService fxService;
	@Autowired
	CurrencyRepo currRepo;
	@Autowired
	SystemUserRepo userRepo;
	@Autowired
	ForexRepo forexRepo;
	@Autowired
	OperatorRepo operatorRepo;
	@Autowired
	AccountingEngine accEngine;

	public NetworkBillingService() {

	}

	public QueryNwkBillingSummaryRes queryNwkSummary(String networkId,
			String userId) {
		log.info("Inside QueryNwkBillingSummaryRes -> queryNwkSummary");
		log.debug("NetworkId: " + networkId);
		log.debug("User id:" + userId);
		QueryNwkBillingSummaryRes qNwkBllnSumm = new QueryNwkBillingSummaryRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		System_account entity = accountRepo.queryAccDetails(networkId);

		if (entity != null) {
			if (userRepo.exists(SystemUser.class, userId)) {
				log.info("Record found");
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
				Currency currency = currRepo
						.findByCcyCode(user.getCurrencyId());
				if (currency != null) {
					bilDtls.setCurrSymbol(currency.getSymbol());
					bilDtls.setCurrencyName(currency.getName());
				}
				bilDtls.setAccountName(entity.getAccName());
				qNwkBllnSumm.setUSRBILDTLS(bilDtls);
				errorCode = ERROR_CODE.ES_SC_001;
			}
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkBillingService", "queryNwkBillingSummary", "",
				errorCode);
		qNwkBllnSumm.setHeader(header);
		return qNwkBllnSumm;
	}

	public QueryNwkTransactionsRes queryNwkTxn(String networkId,
			String fromDate, String toDate, String servicename, String baseCcy,
			String quoteCcy) {
		log.info("Inside QueryNwkTransactionsRes -> queryNwkTxn");
		log.debug("NetworkId: " + networkId);
		log.debug("FromDate: " + fromDate);
		log.debug("ToDate: " + toDate);
		log.debug("ServiceName: " + servicename);
		log.debug("Base currency: " + baseCcy);
		log.debug("Quote currency: " + quoteCcy);
		QueryNwkTransactionsRes qNwkTxn = new QueryNwkTransactionsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		List<System_transaction> entity = txnRepo.queryTransactions(
				Utils.getFormatedDate(fromDate), Utils.getFormatedDate(toDate),
				servicename, networkId);

		if (entity.size() != 0) {
			try {
				log.info("Record found");
				if (!baseCcy.equals(quoteCcy)) {
					convertAmnts(entity, baseCcy, quoteCcy);
				}
				if (entity.size() != 0) {
					List<TRNDTLS> txnList = new ArrayList<TRNDTLS>();
					for (System_transaction sysTxns : entity) {
						TRNDTLS txnDtls = new TRNDTLS();
						log.info("getCreatedTs->" + sysTxns.getCreatedTs());
						txnDtls.setBalance(Float.parseFloat(sysTxns
								.getBalance().toString()));
						txnDtls.setDate(Utils.getDDMMMYYFormat(sysTxns
								.getTransactionDate().toString(), 0));
						txnDtls.setServiceName(sysTxns.getTransCode());
						txnDtls.setDescription(sysTxns.getDescription());
						txnDtls.setCrDr(sysTxns.getCrDr());
						txnDtls.setLcyAmount(Float.parseFloat(sysTxns
								.getLcyAmount().toString()));
						txnList.add(txnDtls);
						txnDtls = null;
					}
					qNwkTxn.getTRNDTLS().addAll(txnList);
					errorCode = ERROR_CODE.ES_SC_001;
				} else {
					errorCode = ERROR_CODE.ES_MS_002;
				}
			} catch (Exception e) {
				errorCode = ERROR_CODE.ES_DT_001;
				log.info("Unable to map data");
				log.debug(Utils.getStackTrace(e));
			}
		} else {
			errorCode = ERROR_CODE.ES_MS_002;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkBillingService", "queryNwkTransactions", "", errorCode);
		qNwkTxn.setHeader(header);
		return qNwkTxn;
	}

	public NwkFundTransferRes nwkFundTransfer(NwkFundTransferReq req) {
		log.info("Inside NwkFundTransferRes -> nwkFundTransfer");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		NwkFundTransferRes nwkFundTransfer = new NwkFundTransferRes();
		SystemUser sysUser = userRepo.findByUserIdNwkId(req.getHeader()
				.getUserId(), req.getFNDTRFDTLS().getNetworkId());
		String encryptedPswd = Utils.hashSHA256(req.getFNDTRFDTLS()
				.getPassword(), sysUser.getUserId() + Utils.lServerToken);
		if (sysUser.getPassword().equals(encryptedPswd)) {
			Accounting accounting = new Accounting();
			accounting.setDebitAcNo(req.getFNDTRFDTLS().getFromAcc());
			accounting.setDebitCcy(req.getFNDTRFDTLS().getCcy());
			accounting.setCreditAcNo(req.getFNDTRFDTLS().getToAcc());
			accounting.setNetworkId(req.getFNDTRFDTLS().getNetworkId());
			accounting.setTransactionDate(new Date());
			Cost cost = new Cost();
			cost.setBaseCost(Double.valueOf(req.getFNDTRFDTLS().getAmount()));
			accounting.setTrnAmt(cost);
			accounting.setUserId(req.getHeader().getUserId());
			accounting.setTrnCode(TRANSACTION_CODE.FT_ACC.name());
			boolean result = accEngine.doAccounting(accounting).endsWith("");
			nwkFundTransfer.setHeader(req.getHeader());
			if (result) {
				errorCode = ERROR_CODE.FT_TR_002;
				header = (Header) HeaderFactory.getHeader(HeaderType.NS,
						"NetworkPermissionService", "queryAllNwkGroups", "",
						errorCode);
			} else {
				errorCode = ERROR_CODE.FT_TR_003;
				header = (Header) HeaderFactory.getHeader(HeaderType.NS,
						"NetworkPermissionService", "queryAllNwkGroups", "",
						errorCode);
			}
			nwkFundTransfer.setHeader(header);
		} else {
			errorCode = ERROR_CODE.FT_TR_001;
			header = (Header) HeaderFactory.getHeader(HeaderType.NS,
					"NetworkPermissionService", "queryAllNwkGroups", "",
					errorCode);
			nwkFundTransfer.setHeader(header);
		}
		return nwkFundTransfer;
	}

	public QueryNwkSMSRatesRes queryNwkSMSRates(String countryId,
			String currency) {
		log.info("Inside QueryNwkSMSRatesRes -> queryNwkSMSRates");
		log.debug("CountryId: " + countryId);
		log.debug("Currency: " + currency);
		QueryNwkSMSRatesRes qNwkSmsRates = new QueryNwkSMSRatesRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		List<Map<String, Object>> entity = (List<Map<String, Object>>) smsRepo
				.querySmsRates(countryId);

		if (entity.size() != 0) {
			log.info("Record found");
			List<QRSMSDTLS> smsList = new ArrayList<QRSMSDTLS>();
			for (Map<String, Object> map : entity) {
				QRSMSDTLS smsDtls = new QRSMSDTLS();
				Object price = map.get("price");
				if (price != null) {
					smsDtls.setAmount(Float.parseFloat(price.toString()));
				}
				Object curr = map.get("currency");
				if (curr != null) {
					Currency tmpCurr = currRepo.findByCcyCode(curr.toString());
					smsDtls.setCurrency(tmpCurr.getSymbol());
				}
				Object operatorId = map.get("operatorId");
				if (operatorId != null) {
					Operator operator = operatorRepo.findOne(Operator.class,
							operatorId.toString());
					if (operator != null) {
						smsDtls.setProviderName(operator.getName());
					}
				}
				Object premium = map.get("premium");
				if (premium != null) {
					smsDtls.setPremium(Float.parseFloat(premium.toString()));
				}
				smsDtls.setVoice(0.0f);
				smsDtls.setPremiumVoice(0.0f);
				smsList.add(smsDtls);
			}
			qNwkSmsRates.getQRSMSDTLS().addAll(smsList);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_MS_003;
			log.info("No record found");
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkBillingService", "queryNwkSMSRates", "", errorCode);
		qNwkSmsRates.setHeader(header);
		return qNwkSmsRates;
	}

	public QueryTransactionServicesRes queryTxServices() {
		log.info("Inside QueryTransactionServicesRes -> queryTxServices");
		QueryTransactionServicesRes qTxnServcies = new QueryTransactionServicesRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<Transaction_code> txnCodes = codeRepo.listAllServices();
		if (txnCodes.size() == 0) {
			log.info("No record found");
			errorCode = ERROR_CODE.ES_NR_001;
		} else {
			log.info("Record found");
			List<TXNSERVICES> txnCodeList = new ArrayList<TXNSERVICES>();
			for (Transaction_code txn : txnCodes) {
				TXNSERVICES txnCode = new TXNSERVICES();
				txnCode.setTxnService(txn.getTransaction_code());
				txnCodeList.add(txnCode);
			}
			qTxnServcies.getTXNSERVICES().addAll(txnCodeList);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkBillingService", "queryTxnServices", "", errorCode);
		qTxnServcies.setHeader(header);
		return qTxnServcies;
	}

	public void convertAmnts(List<System_transaction> entity, String baseCurr,
			String quoteCurr) throws DatatypeConfigurationException {

		QueryfxRatesReq req = new QueryfxRatesReq();

		// Converting balance
		List<QRFXRTDTLS> balList = new ArrayList<QRFXRTDTLS>();
		for (System_transaction sysTxns : entity) {
			QRFXRTDTLS fxDtls = new QRFXRTDTLS();
			fxDtls.setBaseCCy(baseCurr);
			fxDtls.setQuoteCCy(quoteCurr);
			fxDtls.setAmount(Float.parseFloat(sysTxns.getBalance().toString()));
			balList.add(fxDtls);
		}
		req.getQRFXRTDTLS().addAll(balList);
		QueryfxRatesRes res = fxService.queryfxRates(req);
		List<FXRTDTLS> convertedBal = res.getFXRTDTLS();

		List<FXRTDTLS> convertedLcyAmt = null;
		if (convertedBal.size() != 0) {
			// Converting LcyAmount
			List<QRFXRTDTLS> amntList = new ArrayList<QRFXRTDTLS>();
			for (System_transaction sysTxns : entity) {
				QRFXRTDTLS fxDtls = new QRFXRTDTLS();
				fxDtls.setBaseCCy(baseCurr);
				fxDtls.setQuoteCCy(quoteCurr);
				fxDtls.setAmount(Float.parseFloat(sysTxns.getLcyAmount()
						.toString()));
				amntList.add(fxDtls);
			}

			req = new QueryfxRatesReq();
			req.getQRFXRTDTLS().addAll(amntList);
			res = fxService.queryfxRates(req);
			convertedLcyAmt = res.getFXRTDTLS();
		}

		if (convertedLcyAmt != null) {
			for (int i = 0; i < entity.size(); i++) {
				entity.get(i).setBalance(
						Double.valueOf(Float.toString(convertedBal.get(i)
								.getConvrtAmount())));
				log.debug("Balance:: " + convertedBal.get(i).getAmount()
						+ " -> " + convertedBal.get(i).getConvrtAmount());
				entity.get(i).setLcyAmount(
						Double.parseDouble(Float.toString(convertedLcyAmt
								.get(i).getConvrtAmount())));
				log.debug("LcyAmount:: " + convertedLcyAmt.get(i).getAmount()
						+ " -> " + convertedLcyAmt.get(i).getConvrtAmount());
			}
		} else {
			log.info("No record found in Forex");
			entity.clear();
		}

	}

	public QueryAccountDtlsListRes queryAccontListDtl() {
		log.info("Inside NetworkBillingService -> queryAccontListDtl");
		QueryAccountDtlsListRes res = new QueryAccountDtlsListRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<Map<String, Object>> accountDtlList = accountRepo
				.queryAccontListDtl();
		if (accountDtlList != null) {
			if (accountDtlList.size() != 0) {
				for (Map<String, Object> map : accountDtlList) {
					QRACCOUNTDTLSLIST qrAccDtlList = new QRACCOUNTDTLSLIST();
					if (map.get("account_no") != null) {
						qrAccDtlList.setAccountNo(map.get("account_no")
								.toString());
					}
					if (map.get("acc_name") != null) {
						qrAccDtlList.setAccountName(map.get("acc_name")
								.toString());
					}
					if (map.get("user_id") != null) {
						qrAccDtlList.setUserId(map.get("user_id").toString());
					}
					if (map.get("mobile_no") != null) {
						qrAccDtlList.setMobileNo(map.get("mobile_no")
								.toString());
					}
					StringBuffer name = new StringBuffer();
					if (map.get("first_name") != null) {
						name.append(map.get("first_name").toString());
					}
					if (map.get("last_name") != null) {
						name.append(" " + map.get("last_name").toString());
					}
					qrAccDtlList.setUserName(name.toString());
					res.getQRACCOUNTDTLSLIST().add(qrAccDtlList);
				}
				errorCode = ERROR_CODE.ES_SC_001;
			} else {
				errorCode = ERROR_CODE.ES_NR_001;
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkBillingService", "queryAccontListDtl", "", errorCode);
		res.setHeader(header);
		return res;
	}
}