package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.POLLDTLS;
import com.iexceed.esoko.jaxb.db.QueryPollStatsRes;
import com.iexceed.esoko.jaxb.db.QuerySmsTrafficRes;
import com.iexceed.esoko.jaxb.db.QuerySurveyStatsRes;
import com.iexceed.esoko.jaxb.db.QueryUploadStatsRes;
import com.iexceed.esoko.jaxb.db.SURVEYDTLS;
import com.iexceed.esoko.jaxb.db.TRAFFICDTLS;
import com.iexceed.esoko.jaxb.db.UPLDSTATS;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class DashBoardUsageService {

	public static Logger log = LoggerUtils.getLogger();
	private static String serviceName = "UsageService";
	Header header = null;
	Enum<ERROR_CODE> errorCode = ERROR_CODE.DM_SV_002;

	public QuerySmsTrafficRes querySmsTraffic(String networkId, String userId) {
		log.info("Inside DashBoardUsageService -> querySmsTraffic");
		QuerySmsTrafficRes qryResp = new QuerySmsTrafficRes();
		List<TRAFFICDTLS> trfDtlsList = new ArrayList<TRAFFICDTLS>();
		TRAFFICDTLS dtls = new TRAFFICDTLS();
		TRAFFICDTLS dtls1 = new TRAFFICDTLS();
		dtls.setMonth("August");
		dtls.setNoOfSms(10);
		dtls.setType("Push");
		dtls1.setMonth("September");
		dtls1.setNoOfSms(15);
		dtls1.setType("Price Type");
		trfDtlsList.add(dtls);
		trfDtlsList.add(dtls1);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"QuerySmsTraffic", "", errorCode);
		qryResp.setHeader(header);
		qryResp.getTRAFFICDTLS().addAll(trfDtlsList);
		return qryResp;
	}

	public QueryPollStatsRes queryPollStats(String networkId, String userId) {
		log.info("Inside DashBoardUsageService -> queryPollStats");
		QueryPollStatsRes qryResp = new QueryPollStatsRes();
		List<POLLDTLS> pollsList = new ArrayList<POLLDTLS>();
		POLLDTLS dtls = new POLLDTLS();
		POLLDTLS dtls1 = new POLLDTLS();
		dtls.setDescription("Test Description");
		dtls.setNoOfNegatives(5);
		dtls.setNoOfPositives(3);
		dtls.setNoOfSuspensions(8);
		dtls.setPollId("Carrot");
		dtls1.setDescription("Test Description1");
		dtls1.setNoOfNegatives(2);
		dtls1.setNoOfPositives(3);
		dtls1.setNoOfSuspensions(5);
		dtls1.setPollId("Ricce");
		pollsList.add(dtls);
		pollsList.add(dtls1);
		qryResp.getPOLLDTLS().addAll(pollsList);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"QueryPollStats", "", errorCode);
		qryResp.setHeader(header);
		return qryResp;
	}

	public QuerySurveyStatsRes querySurveyStats(String networkId, String userId) {
		log.info("Inside DashBoardUsageService -> querySurveyStats");
		QuerySurveyStatsRes qryResp = new QuerySurveyStatsRes();
		List<SURVEYDTLS> surDtlsLIst = new ArrayList<SURVEYDTLS>();
		SURVEYDTLS dtls = new SURVEYDTLS();
		SURVEYDTLS dtls1 = new SURVEYDTLS();
		dtls.setDescription("Test Description");
		dtls.setNoOfNegatives(5);
		dtls.setNoOfPositives(3);
		dtls.setNoOfSuspensions(8);
		dtls.setSurveyId("Carrot");
		dtls1.setDescription("Test Description1");
		dtls1.setNoOfNegatives(2);
		dtls1.setNoOfPositives(3);
		dtls1.setNoOfSuspensions(5);
		dtls1.setSurveyId("Ricce");
		surDtlsLIst.add(dtls);
		surDtlsLIst.add(dtls1);
		qryResp.getSURVEYDTLS().addAll(surDtlsLIst);
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"QuerySurveyStats", "", errorCode);
		qryResp.setHeader(header);
		return qryResp;
	}

	public QueryUploadStatsRes queryUploadStats(String networkId, String userId) {
		QueryUploadStatsRes qryResp = new QueryUploadStatsRes();
		List<UPLDSTATS> upldDtlsList = new ArrayList<UPLDSTATS>();
		UPLDSTATS dtls = new UPLDSTATS();
		UPLDSTATS dtls1 = new UPLDSTATS();
		dtls.setCommodityId("Rice");
		dtls.setCommodityName("Rice");
		dtls.setMarketName("Madiwala");
		dtls.setNoOfPriceUploads(10);
		dtls.setUploadId("RICE123");
		dtls1.setCommodityId("Carrot");
		dtls1.setCommodityName("Carrot");
		dtls1.setMarketName("Madiwala");
		dtls1.setNoOfPriceUploads(50);
		dtls1.setUploadId("CARROT123");
		upldDtlsList.add(dtls);
		upldDtlsList.add(dtls1);
		qryResp.getUPLDSTATS().addAll(upldDtlsList);
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"QueryUploadStats", "", errorCode);
		qryResp.setHeader(header);
		return qryResp;
	}
}
