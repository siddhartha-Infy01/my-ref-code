package com.iexceed.esoko.ss.rest;

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
import com.iexceed.esoko.jaxb.ss.CreateGatewayReqWrap;
import com.iexceed.esoko.jaxb.ss.CreateGatewayResWrap;
import com.iexceed.esoko.jaxb.ss.CreateOperatorReqWrap;
import com.iexceed.esoko.jaxb.ss.CreateOperatorResWrap;
import com.iexceed.esoko.jaxb.ss.CreateSenderIdResWrap;
import com.iexceed.esoko.jaxb.ss.CreateSenderIdWrap;
import com.iexceed.esoko.jaxb.ss.DeleteOperatorReqWrap;
import com.iexceed.esoko.jaxb.ss.DeleteOperatorResWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSenderIdResWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSenderIdWrap;
import com.iexceed.esoko.jaxb.ss.EditOperatorReqWrap;
import com.iexceed.esoko.jaxb.ss.EditOperatorResWrap;
import com.iexceed.esoko.jaxb.ss.QueryAllGatewaysResWrap;
import com.iexceed.esoko.jaxb.ss.QueryAllSmppResWrap;
import com.iexceed.esoko.jaxb.ss.QueryCountryDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QueryGatewayDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QueryOperatorByLocResWrap;
import com.iexceed.esoko.jaxb.ss.QuerySenderIdResWrap;
import com.iexceed.esoko.jaxb.ss.SwitchOperatorReqWrap;
import com.iexceed.esoko.jaxb.ss.SwitchOperatorResWrap;
import com.iexceed.esoko.jaxb.ss.UploadRatesReqWrap;
import com.iexceed.esoko.jaxb.ss.UploadRatesResWrap;
import com.iexceed.esoko.ss.service.GatewayService;

@Component
@Path("GatewayService")
public class GatewayRest {
	private static Logger log = LoggerUtils.getLogger();

	@Autowired
	private GatewayService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryGatewayDetails")
	public QueryGatewayDtlsResWrap queryGatewayDetails() {
		log.info("Inside GatewayRest -> queryGatewayDetails");
		QueryGatewayDtlsResWrap resWrap = new QueryGatewayDtlsResWrap();
		resWrap.setQueryGatewayDtlsRes(service.queryGatewayDetails());
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCountryDetails")
	public QueryCountryDtlsResWrap queryCountryDetails() {
		log.info("Inside GatewayRest -> queryCountryDetails");
		QueryCountryDtlsResWrap resWrap = new QueryCountryDtlsResWrap();
		resWrap.setQueryCountryDtlsRes(service.queryCountryDetails());
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllGateways")
	public QueryAllGatewaysResWrap queryAllGateways() {
		log.info("Inside GatewayRest -> queryAllGateways");
		QueryAllGatewaysResWrap resWrap = new QueryAllGatewaysResWrap();
		resWrap.setQueryAllGatewaysRes(service.queryAllGateways());
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createOperator")
	public CreateOperatorResWrap createOperator(CreateOperatorReqWrap req) {
		log.info("Inside GatewayRest -> createOperator");
		CreateOperatorResWrap resWrap = new CreateOperatorResWrap();
		resWrap.setCreateOperatorRes(service.createOperator(req
				.getCreateOperatorReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createGateway")
	public CreateGatewayResWrap createGateway(CreateGatewayReqWrap req) {
		log.info("Inside GatewayRest -> createGateway");
		CreateGatewayResWrap resWrap = new CreateGatewayResWrap();
		resWrap.setCreateGatewayRes(service.createGateway(req
				.getCreateGatewayReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteOperator")
	public DeleteOperatorResWrap deleteOperator(DeleteOperatorReqWrap req) {
		log.info("Inside GatewayRest -> deleteOperator");
		DeleteOperatorResWrap resWrap = new DeleteOperatorResWrap();
		resWrap.setDeleteOperatorRes(service.deleteOperator(req
				.getDeleteOperatorReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteSenderId")
	public DeleteSenderIdResWrap deleteSenderId(DeleteSenderIdWrap req) {
		log.info("Inside GatewayRest -> deleteSenderId");
		DeleteSenderIdResWrap resWrap = new DeleteSenderIdResWrap();
		resWrap.setDeleteSenderIdRes(service.deleteSenderId(req
				.getDeleteSenderId()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editOperator")
	public EditOperatorResWrap editOperator(EditOperatorReqWrap req) {
		log.info("Inside GatewayRest -> deleteOperator");
		EditOperatorResWrap resWrap = new EditOperatorResWrap();
		resWrap.setEditOperatorRes(service.editOperator(req
				.getEditOperatorReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("switchOperator")
	public SwitchOperatorResWrap switchOperator(SwitchOperatorReqWrap req) {
		log.info("Inside GatewayRest -> switchOperator");
		SwitchOperatorResWrap resWrap = new SwitchOperatorResWrap();
		resWrap.setSwitchOperatorRes(service.switchOperator(req
				.getSwitchOperatorReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllSmpp")
	public QueryAllSmppResWrap queryAllSmpp() {
		log.info("Inside GatewayRest -> queryAllSmpp");
		QueryAllSmppResWrap resWrap = new QueryAllSmppResWrap();
		resWrap.setQueryAllSmppRes(service.queryAllSmpp());
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createSenderId")
	public CreateSenderIdResWrap createSenderId(CreateSenderIdWrap req) {
		log.info("Inside GatewayRest -> createSenderId");
		CreateSenderIdResWrap resWrap = new CreateSenderIdResWrap();
		resWrap.setCreateSenderIdRes(service.createSenderIdRes(req
				.getCreateSenderId()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllSenderId")
	public QuerySenderIdResWrap queryAllSenderId() {
		log.info("Inside GatewayRest -> queryAllSenderId");
		QuerySenderIdResWrap resWrap = new QuerySenderIdResWrap();
		resWrap.setQuerySenderIdRes(service.querySenderId());
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryOpertorsByLoc")
	public QueryOperatorByLocResWrap queryOpertorsByLoc(
			@QueryParam("locationId") String locationId) {
		log.info("Inside GatewayRest -> queryOpertorsByLoc");
		QueryOperatorByLocResWrap resWrap = new QueryOperatorByLocResWrap();
		resWrap.setQueryOperatorByLocRes(service.queryOpertorbyLoc(locationId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("uploadRates")
	public UploadRatesResWrap uploadRates(UploadRatesReqWrap req) {
		log.info("Inside GatewayRest -> uploadRates");
		UploadRatesResWrap resWrap = new UploadRatesResWrap();
		resWrap.setUploadRatesRes(service.uploadRates(req.getUploadRatesReq()));
		return resWrap;
	}
}
