package com.iexceed.esoko.se.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.agents.QueryAgentDtlsResWrap;
import com.iexceed.esoko.jaxb.se.CreatefxRatesHistoryReqWrap;
import com.iexceed.esoko.jaxb.se.CreatefxRatesHistoryResWrap;
import com.iexceed.esoko.jaxb.se.CreatefxRatesReqWrap;
import com.iexceed.esoko.jaxb.se.CreatefxRatesResWrap;
import com.iexceed.esoko.jaxb.se.QueryfxRatesDateResWrap;
import com.iexceed.esoko.jaxb.se.QueryfxRatesReqWrap;
import com.iexceed.esoko.jaxb.se.QueryfxRatesResWrap;
//import com.iexceed.esoko.jaxb.se.crtfxhstry.CreatefxRatesHistoryReq;
//import com.iexceed.esoko.jaxb.se.crtfxhstry.CreatefxRatesHistoryRes;
//import com.iexceed.esoko.jaxb.se.crtfxrate.CreatefxRatesReq;
//import com.iexceed.esoko.jaxb.se.crtfxrate.CreatefxRatesRes;
//import com.iexceed.esoko.jaxb.se.qryfxrate.QueryfxRatesRes;
import com.iexceed.esoko.se.service.FxRatesService;

@Path("FxRatesService")
@Component
public class FxRatesServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public FxRatesServiceRest() {
	}

	@Autowired
	private FxRatesService fxRatesService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createfxRates")
	public CreatefxRatesResWrap createfxRates(CreatefxRatesReqWrap req) {
		CreatefxRatesResWrap crtFxResWrap = new CreatefxRatesResWrap();
		crtFxResWrap.setCreatefxRatesRes(fxRatesService.createfxRates(req
				.getCreatefxRatesReq()));
		return crtFxResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryfxRates")
	public QueryfxRatesResWrap queryfxRates(QueryfxRatesReqWrap req)
			throws DatatypeConfigurationException {
		QueryfxRatesResWrap qryResWrap = new QueryfxRatesResWrap();
		qryResWrap.setQueryfxRatesRes(fxRatesService.queryfxRates(req
				.getQueryfxRatesReq()));
		return qryResWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryFxRatesDate")
	public QueryfxRatesDateResWrap queryFxRatesDate(
			@QueryParam("forexDate") String forexDate) {
		QueryfxRatesDateResWrap resWrap = new QueryfxRatesDateResWrap();
		resWrap.setQueryfxRatesDateRes(fxRatesService.queryFxRatesDate(forexDate));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createfxRatesHistory")
	public CreatefxRatesHistoryResWrap createfxRatesHistory()
			throws DatatypeConfigurationException {
		CreatefxRatesHistoryResWrap fxHistoResWrap = new CreatefxRatesHistoryResWrap();
		fxHistoResWrap.setCreatefxRatesHistoryRes(fxRatesService
				.createfxRatesHistory());
		return fxHistoResWrap;
	}

}
