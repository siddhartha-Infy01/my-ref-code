package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CMDDETAILS;
import com.iexceed.esoko.jaxb.db.COUNTDTLS;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.QueryVariousCountsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @Ankur
 */
@Service
public class CountsService {

	public static Logger log = LoggerUtils.getLogger();

	public QueryVariousCountsRes queryVariousCounts(String userId) {
		log.info("Inside QueryVariousCountsRes -> queryVariousCounts");
		log.debug("UserId: " + userId);
		QueryVariousCountsRes countRes = new QueryVariousCountsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<COUNTDTLS> cntDtlsLst = new ArrayList<COUNTDTLS>();
		COUNTDTLS cntDtls = new COUNTDTLS();
		cntDtls.setNoOfCountryNwks(100);
		cntDtls.setNoOfPeople(10000);
		cntDtls.setNoOfPrivateNwks(50);
		cntDtls.setNoOfPublicNwks(50);
		cntDtls.setNoOfRegionalNwks(20);
		List<CMDDETAILS> comDtlsLst = new ArrayList<CMDDETAILS>();
		CMDDETAILS comDtls = new CMDDETAILS();
		comDtls.setAttribute("Local");
		comDtls.setCommodityId("Ban01");
		comDtls.setCommodityName("Banana");
		comDtls.setCount(1200);	
		comDtlsLst.add(comDtls);
		cntDtls.getCMDDETAILS().addAll(comDtlsLst);
		cntDtlsLst.add(cntDtls);
		countRes.getCOUNTDTLS().addAll(cntDtlsLst);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"CountsService", "queryVariousCounts", "", errorCode);
		countRes.setHeader(header);
		return countRes;
	}
}
