package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.ACCDTLS;
import com.iexceed.esoko.jaxb.db.HISTORY;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.LMTDTLS;
import com.iexceed.esoko.jaxb.db.QueryUserAccountInformationRes;
import com.iexceed.esoko.jaxb.db.QueryUserLimitDetailsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class UserInformationService {
	public static Logger log = LoggerUtils.getLogger();

	public QueryUserAccountInformationRes queryUserAccountInformation(
			String userId) {
		log.info("Inside QueryUserAccountInformationRes -> queryUserAccountInformation");
		log.debug("UserId: " + userId);
		QueryUserAccountInformationRes infoRes = new QueryUserAccountInformationRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		List<ACCDTLS> accDtlsLst = new ArrayList<ACCDTLS>();
		ACCDTLS accDtls = new ACCDTLS();
		accDtls.setBalance(12000.00f);
		accDtls.setExpiry("26 Sep 2015");
		accDtls.setSubscriptionType("Monthly");

		List<HISTORY> historyLst = new ArrayList<HISTORY>();
		HISTORY history = new HISTORY();
		history.setBalance(12000.00f);
		history.setCredit(6000.00f);
		history.setDate("26 Sep 2014");
		history.setDebit(6000.00f);
		history.setDescription("Installments paid");
		history.setService("All");
		historyLst.add(history);
		accDtls.getHISTORY().addAll(historyLst);
		accDtlsLst.add(accDtls);
		infoRes.getACCDTLS().addAll(accDtlsLst);
		errorCode = ERROR_CODE.ES_SC_001;
		
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"UserInformationService", "queryUserAccountInformation", "",
				errorCode);
		
		infoRes.setHeader(header);
		return infoRes;
	}

	public QueryUserLimitDetailsRes queryUserLimitDetails(String userId) {
		log.info("Inside QueryUserLimitDetailsRes -> queryUserLimitDetails");
		log.debug("UserId: " + userId);
		QueryUserLimitDetailsRes detRes = new QueryUserLimitDetailsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		
		List<LMTDTLS> lmtDtlsLst = new ArrayList<LMTDTLS>();
		LMTDTLS lmtDtls = new LMTDTLS();
		lmtDtls.setLimit(12000.00f);
		lmtDtls.setUsedLimit(6000.00f);
		lmtDtlsLst.add(lmtDtls);
		detRes.getLMTDTLS().addAll(lmtDtlsLst);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"UserInformationService", "queryUserLimitDetails", "",
				errorCode);
		detRes.setHeader(header);
		return detRes;
	}
}
