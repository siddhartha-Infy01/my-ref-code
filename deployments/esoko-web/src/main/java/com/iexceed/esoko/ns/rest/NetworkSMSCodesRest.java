package com.iexceed.esoko.ns.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.CreateSMSCodesReqWrap;
import com.iexceed.esoko.jaxb.ns.CreateSMSCodesResWrap;
import com.iexceed.esoko.jaxb.ns.QueryCommListResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkSmsCodesResWrap;
import com.iexceed.esoko.ne.service.NetworkSMSCodesService;

/*
 * @author Ankur
 */
@Path("NetworkSMSCodesService")
@Component
public class NetworkSMSCodesRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkSMSCodesRest() {

	}

	@Autowired
	private NetworkSMSCodesService nwSMSCodesService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkSmsCodes")
	public QueryNwkSmsCodesResWrap queryNwkSmsCodes(
			@QueryParam("type") String type,
			@QueryParam("networkId") String networkId,
			@QueryParam("subType") String subType) {
		log.info("Inside QueryNwkSmsCodesResWrap -> queryNwkSmsCodes");
		QueryNwkSmsCodesResWrap smsCodeResWrap = new QueryNwkSmsCodesResWrap();
		smsCodeResWrap.setQueryNwkSmsCodesRes(nwSMSCodesService
				.qNetworkSmsCodes(type, networkId, subType));
		return smsCodeResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createSMSCodes")
	public CreateSMSCodesResWrap createSMSCodes(CreateSMSCodesReqWrap req) {
		log.info("Inside CreateSMSCodesResWrap -> createSMSCodes");
		CreateSMSCodesResWrap smsCodeResWrap = new CreateSMSCodesResWrap();
		smsCodeResWrap.setCreateSMSCodesRes(nwSMSCodesService
				.createSMSCodes(req.getCreateSMSCodesReq()));
		return smsCodeResWrap;
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCommList")
	public QueryCommListResWrap queryCommList(
			@QueryParam("networkId") String networkId) {
		QueryCommListResWrap resWrap = new QueryCommListResWrap();
		resWrap.setQueryCommListRes(nwSMSCodesService.queryCommList(networkId));
		return resWrap;
	}
}
