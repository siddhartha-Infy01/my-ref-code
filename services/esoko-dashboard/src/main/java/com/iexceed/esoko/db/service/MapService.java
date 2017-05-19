package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.NWKDETAILS;
import com.iexceed.esoko.jaxb.db.PEOPLEDTLS;
import com.iexceed.esoko.jaxb.db.QueryMapCoordinatesRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class MapService {
	public static Logger log = LoggerUtils.getLogger();
	private final String serviceName = "NetworkSMSCodesService";
	Enum<ERROR_CODE> ERROR = ERROR_CODE.DM_SV_002;
	Header header = null;

	public MapService() {
	}

	public QueryMapCoordinatesRes queryMapCoordinates(String networkId,
			String userId) {
		log.info("MapService -> QueryMapCoordinates");
		QueryMapCoordinatesRes qryResp = new QueryMapCoordinatesRes();
		List<NWKDETAILS> mapList = new ArrayList<NWKDETAILS>();
		List<PEOPLEDTLS> peopleList = new ArrayList<PEOPLEDTLS>();
		NWKDETAILS nwkDtls = new NWKDETAILS();
		NWKDETAILS nwkDtls1 = new NWKDETAILS();
		PEOPLEDTLS pepflDtls = new PEOPLEDTLS();
		PEOPLEDTLS pepflDtls1 = new PEOPLEDTLS();
		nwkDtls.setXCo(10);
		nwkDtls.setYCo(20);
		nwkDtls1.setXCo(10);
		nwkDtls1.setYCo(20);
		pepflDtls.setXCo(10);
		pepflDtls.setYCo(20);
		pepflDtls1.setXCo(10);
		pepflDtls1.setYCo(20);
		peopleList.add(pepflDtls);
		peopleList.add(pepflDtls1);
		nwkDtls.getPEOPLEDTLS().add(pepflDtls);
		nwkDtls.getPEOPLEDTLS().add(pepflDtls1);
		nwkDtls1.getPEOPLEDTLS().add(pepflDtls);
		nwkDtls1.getPEOPLEDTLS().add(pepflDtls1);
		mapList.add(nwkDtls);
		mapList.add(nwkDtls1);
		qryResp.getNWKDETAILS().addAll(mapList);
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"Query MapCoordinates", "", ERROR);
		qryResp.setHeader(header);
		return qryResp;
	}
}
