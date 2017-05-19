package com.iexceed.esoko.ss.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ss.EDITUSERACC;
import com.iexceed.esoko.jaxb.ss.EditUserAccountReq;
import com.iexceed.esoko.jaxb.ss.EditUserAccountRes;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.QRUSERACCNT;
import com.iexceed.esoko.jaxb.ss.QueryUserAccountRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class UserAccountService {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private System_accountRepo sysAccRepo;
	@Autowired
	CurrencyRepo currRepo;

	public QueryUserAccountRes queryUserAccount(String userId, String networkId) {
		log.info("Inside UserAccountService -> queryUserAccount");
		log.debug("UserId: " + userId);
		log.debug("NetworkId: " + networkId);
		QueryUserAccountRes res = new QueryUserAccountRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(networkId)) {
			System_account userAcc = sysAccRepo.queryAccDetails(userId);
			System_account nwkAcc = sysAccRepo.queryAccDetails(networkId);
			if ((userAcc == null) && (nwkAcc == null)) {
				log.info("No record found");
				errorCode = ERROR_CODE.ES_NR_001;
			} else {
				List<QRUSERACCNT> accnList = new ArrayList<QRUSERACCNT>();
				if (userAcc != null) {
					log.info("Setting User accout details");
					QRUSERACCNT accnt = new QRUSERACCNT();
					log.debug("Account name: " + userAcc.getAccName());
					accnt.setAccountName(userAcc.getAccName());
					log.debug("Account number: " + userAcc.getAccountNo());
					accnt.setAccountNo(userAcc.getAccountNo());
					if (userAcc.getBalance() != null) {
						log.debug("Account balance: "
								+ userAcc.getBalance().toString());
						accnt.setBalance(userAcc.getBalance().toString());
					}
					log.debug("CurrencyId: " + userAcc.getAcCurrency());
					accnt.setCurrecyId(userAcc.getAcCurrency());
					Currency currency = currRepo.findByCcyCode(userAcc
							.getAcCurrency());
					log.debug("Currency Symbol: " + currency.getSymbol());
					accnt.setCurrecySymbol(currency.getSymbol());
					if (userAcc.getMinBalance() != null) {
						log.debug("Minimum balance: "
								+ userAcc.getMinBalance().toString());
						accnt.setMinBalance(userAcc.getMinBalance().toString());
					}
					log.debug("Account type: " + userAcc.getType());
					accnt.setType(userAcc.getType());
					accnList.add(accnt);
				}
				if (nwkAcc != null) {
					log.info("Setting Network accout details");
					QRUSERACCNT accnt = new QRUSERACCNT();
					log.debug("Account name: " + nwkAcc.getAccName());
					accnt.setAccountName(nwkAcc.getAccName());
					log.debug("Account number: " + nwkAcc.getAccountNo());
					accnt.setAccountNo(nwkAcc.getAccountNo());
					if (nwkAcc.getBalance() != null) {
						log.debug("Account balance: "
								+ nwkAcc.getBalance().toString());
						accnt.setBalance(nwkAcc.getBalance().toString());
					}
					log.debug("CurrencyId: " + nwkAcc.getAcCurrency());
					accnt.setCurrecyId(nwkAcc.getAcCurrency());
					Currency currency = currRepo.findByCcyCode(nwkAcc
							.getAcCurrency());
					log.debug("Currency Symbol: " + currency.getSymbol());
					accnt.setCurrecySymbol(currency.getSymbol());
					if (nwkAcc.getMinBalance() != null) {
						log.debug("Minimum balance: "
								+ nwkAcc.getMinBalance().toString());
						accnt.setMinBalance(nwkAcc.getMinBalance().toString());
					}
					log.debug("Account type: " + nwkAcc.getType());
					accnt.setType(nwkAcc.getType());
					accnList.add(accnt);
				}
				res.getQRUSERACCNT().addAll(accnList);
				errorCode = ERROR_CODE.ES_SC_001;
			}
		} else {
			log.info("UserId or NetworkId cannot be null");
			errorCode = ERROR_CODE.ES_PK_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"UserAccountService", "Query User Account", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryUserAccountRes queryAllUserAccounts(String userId,
			String networkId) {
		log.info("Inside UserAccountService -> queryAllUserAccounts");
		log.debug("UserId: " + userId);
		log.debug("NetworkId: " + networkId);
		QueryUserAccountRes res = new QueryUserAccountRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(networkId)) {
			List<System_account> userAccList = sysAccRepo.queryAllAccDetails(
					userId, networkId);
			if (userAccList.size() == 0) {
				log.info("No record found");
				errorCode = ERROR_CODE.ES_NR_001;
			} else {
				List<QRUSERACCNT> accnList = new ArrayList<QRUSERACCNT>();
				for (System_account userAcc : userAccList) {
					QRUSERACCNT accnt = new QRUSERACCNT();
					log.debug("Account name: " + userAcc.getAccName());
					accnt.setAccountName(userAcc.getAccName());
					log.debug("Account number: " + userAcc.getAccountNo());
					accnt.setAccountNo(userAcc.getAccountNo());
					if (userAcc.getBalance() != null) {
						log.debug("Account balance: "
								+ userAcc.getBalance().toString());
						accnt.setBalance(userAcc.getBalance().toString());
					}
					log.debug("CurrencyId: " + userAcc.getAcCurrency());
					accnt.setCurrecyId(userAcc.getAcCurrency());
					Currency currency = currRepo.findByCcyCode(userAcc
							.getAcCurrency());
					log.debug("Currency Symbol: " + currency.getSymbol());
					accnt.setCurrecySymbol(currency.getSymbol());
					if (userAcc.getMinBalance() != null) {
						log.debug("Minimum balance: "
								+ userAcc.getMinBalance().toString());
						accnt.setMinBalance(userAcc.getMinBalance().toString());
					}
					log.debug("Account type: " + userAcc.getType());
					accnt.setType(userAcc.getType());
					accnList.add(accnt);
				}
				res.getQRUSERACCNT().addAll(accnList);
				errorCode = ERROR_CODE.ES_SC_001;
			}
		} else {
			log.info("UserId or NetworkId cannot be null");
			errorCode = ERROR_CODE.ES_PK_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"UserAccountService", "Query User Account", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public EditUserAccountRes editUserAccount(EditUserAccountReq req) {
		log.info("Inside UserAccountService -> editUserAccount");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		EditUserAccountRes res = new EditUserAccountRes();
		EDITUSERACC edtUserAcc = req.getEDITUSERACC();
		if (StringUtils.isNotEmpty(edtUserAcc.getOwnerId())) {
			System_account oldRecord = sysAccRepo.queryAccDetails(edtUserAcc
					.getOwnerId());
			if (oldRecord == null) {
				log.info("No record found");
				errorCode = ERROR_CODE.ES_NR_001;
			} else {
				System_account newRecord = new System_account();
				if (StringUtils.isNotEmpty(edtUserAcc.getAccName())) {
					log.debug("Account Name: " + edtUserAcc.getAccName());
					newRecord.setAccName(edtUserAcc.getAccName());
				} else {
					newRecord.setAccName(oldRecord.getAccName());
				}
				if (StringUtils.isNotEmpty(edtUserAcc.getCurrencyId())) {
					log.debug("Account Currency: " + edtUserAcc.getCurrencyId());
					newRecord.setAcCurrency(edtUserAcc.getCurrencyId());
				} else {
					newRecord.setAcCurrency(oldRecord.getAcCurrency());
				}
				if (StringUtils.isNotEmpty(edtUserAcc.getBalance())) {
					log.debug("Account Balance: " + edtUserAcc.getBalance());
					newRecord.setBalance(Double
							.valueOf(edtUserAcc.getBalance()));
				} else {
					newRecord.setBalance(oldRecord.getBalance());
				}
				if (StringUtils.isNotEmpty(edtUserAcc.getMinBalance())) {
					log.debug("Account MinBalance: "
							+ edtUserAcc.getMinBalance());
					newRecord.setMinBalance(Double
							.valueOf(edtUserAcc.getMinBalance()));
				} else {
					newRecord.setMinBalance(oldRecord.getMinBalance());
				}
				newRecord.setAccountNo(oldRecord.getAccountNo());
				newRecord.setCreatedBy(oldRecord.getCreatedBy());
				newRecord.setCreatedTs(oldRecord.getCreatedTs());
				newRecord.setModifiedBy(req.getHeader().getUserId());
				newRecord.setModifiedTs(new Date());
				newRecord.setOwnerId(oldRecord.getOwnerId());
				newRecord.setRecordStatus(oldRecord.getRecordStatus());
				newRecord.setType(oldRecord.getType());
				sysAccRepo.merge(newRecord);
				errorCode = ERROR_CODE.ES_SC_001;
				log.info("User account updated successfully");

			}
		} else {
			log.info("OwnerId cannot be null");
			errorCode = ERROR_CODE.ES_PK_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"UserAccountService", "Edit User Account", req.getHeader()
						.getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}
}
