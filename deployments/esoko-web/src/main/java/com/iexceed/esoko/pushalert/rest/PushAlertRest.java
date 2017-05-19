package com.iexceed.esoko.pushalert.rest;

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
import com.iexceed.esoko.jaxb.pushalert.ActionReqWrap;
import com.iexceed.esoko.jaxb.pushalert.ActionResWrap;
import com.iexceed.esoko.jaxb.pushalert.CheckAlertNameResWrap;
import com.iexceed.esoko.jaxb.pushalert.CreateCropTipReqWrap;
import com.iexceed.esoko.jaxb.pushalert.CreateCropTipResWrap;
import com.iexceed.esoko.jaxb.pushalert.CreatePushAlertReqWrap;
import com.iexceed.esoko.jaxb.pushalert.CreatePushAlertResWrap;
import com.iexceed.esoko.jaxb.pushalert.CreatePushTemplateResWrap;
import com.iexceed.esoko.jaxb.pushalert.CreatePushTemplateWrap;
import com.iexceed.esoko.jaxb.pushalert.CropActionReqWrap;
import com.iexceed.esoko.jaxb.pushalert.CropActionResWrap;
import com.iexceed.esoko.jaxb.pushalert.DeletePushTemplateRequestResWrap;
import com.iexceed.esoko.jaxb.pushalert.DeletePushTemplateRequestWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertDeliveryResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertsResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryCropAlertResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryCropTipsResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryPeopleByPhoneResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryPushAlertDlvrybyIdResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryPushDeliveryResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryPushResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryPushTemplatesResWrap;
import com.iexceed.esoko.jaxb.pushalert.QueryRecipientsResWrap;
import com.iexceed.esoko.pushalert.service.PushAlertService;

@Component
@Path("PushAlertService")
public class PushAlertRest {
	private static Logger log = LoggerUtils.getLogger();

	@Autowired
	private PushAlertService service;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createPushAlert")
	public CreatePushAlertResWrap createPushAlert(CreatePushAlertReqWrap reqWrap) {
		log.info("Inside PushAlertRest -> createPushAlert");
		CreatePushAlertResWrap resWrap = new CreatePushAlertResWrap();
		resWrap.setCreatePushAlertRes(service.createPushAlert(reqWrap
				.getCreatePushAlertReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("action")
	public ActionResWrap action(ActionReqWrap reqWrap) {
		log.info("Inside PushAlertRest -> action");
		ActionResWrap resWrap = new ActionResWrap();
		resWrap.setActionRes(service.action(reqWrap.getActionReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAlertres")
	public QueryAlertsResWrap queryAlertres(
			@QueryParam("sortBy") String sortBy,
			@QueryParam("networkId") String networkId,
			@QueryParam("filter") String filter,
			@QueryParam("alertType") String alertType) {
		log.info("Inside PushAlertRest -> queryAlertres");
		QueryAlertsResWrap resWrap = new QueryAlertsResWrap();
		resWrap.setQueryAlertsRes(service.queryAlertres(sortBy, networkId,
				filter, alertType));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAlert")
	public QueryAlertResWrap queryAlert(@QueryParam("alertId") String alertId) {
		log.info("Inside PushAlertRest -> queryAlert");
		QueryAlertResWrap resWrap = new QueryAlertResWrap();
		resWrap.setQueryAlertRes(service.queryAlert(alertId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPush")
	public QueryPushResWrap quryPush(@QueryParam("networkId") String networkId) {
		log.info("Inside PushAlertRest -> quryPush");
		QueryPushResWrap resWrap = new QueryPushResWrap();
		resWrap.setQueryPushRes(service.queryPushres(networkId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createPushTemplate")
	public CreatePushTemplateResWrap createPushTemplate(
			CreatePushTemplateWrap req) {
		log.info("Inside PushAlertRest -> CreatePushTemplate");
		CreatePushTemplateResWrap templateResWrap = new CreatePushTemplateResWrap();
		templateResWrap.setCreatePushTemplateRes(service
				.createPushTemplateRes(req.getCreatePushTemplate()));
		return templateResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deletePushTemplate")
	public DeletePushTemplateRequestResWrap deletePushTemplate(
			DeletePushTemplateRequestWrap req) {
		log.info("Inside PushAlertRest -> DeletePushTemplate");
		DeletePushTemplateRequestResWrap agentResWrap = new DeletePushTemplateRequestResWrap();
		agentResWrap.setDeletePushTemplateRequestRes(service
				.deleteTemplateRes(req.getDeletePushTemplateRequest()));
		return agentResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("templateDetailsRes")
	public QueryPushTemplatesResWrap templateDetailsRes(
			@QueryParam("networkId") String networkId,
			@QueryParam("sortBy") String sortBy) {
		log.info("Inside PushAlertRest -> templateDetailsRes");
		QueryPushTemplatesResWrap resWrap = new QueryPushTemplatesResWrap();
		resWrap.setQueryPushTemplatesRes(service.templateDetailsRes(networkId,
				sortBy));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryRecipients")
	public QueryRecipientsResWrap queryRecipients(
			@QueryParam("networkId") String networkId,
			@QueryParam("calculateCost") String calculateCost) {
		log.info("Inside PushAlertRest -> queryRecipients");
		QueryRecipientsResWrap resWrap = new QueryRecipientsResWrap();
		resWrap.setQueryRecipientsRes(service.queryRecipients(networkId,
				calculateCost));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("checkAlertName")
	public CheckAlertNameResWrap checkAlertName(
			@QueryParam("requestType") String requestType,
			@QueryParam("alertId") String alertId,
			@QueryParam("alertName") String alertName,
			@QueryParam("alertType") String alertType,
			@QueryParam("networkId") String networkId) {
		log.info("Inside PushAlertRest -> checkAlertName");
		CheckAlertNameResWrap resWrap = new CheckAlertNameResWrap();
		resWrap.setCheckAlertNameRes(service.checkAlertName(requestType,
				alertId, alertName, alertType, networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPushDelivery")
	public QueryPushDeliveryResWrap queryPushDelivery(
			@QueryParam("networkId") String networkId,
			@QueryParam("fromDate") String fromDate,
			@QueryParam("toDate") String toDate) {
		log.info("Inside PushAlertRest -> queryPushDelivery");
		QueryPushDeliveryResWrap resWrap = new QueryPushDeliveryResWrap();
		resWrap.setQueryPushDeliveryRes(service.queryPushDelivery(networkId,
				fromDate, toDate));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAlertDelivery")
	public QueryAlertDeliveryResWrap queryAlertDelivery(
			@QueryParam("networkId") String networkId,
			@QueryParam("fromDate") String fromDate,
			@QueryParam("toDate") String toDate,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("messageType") String messageType) {
		log.info("Inside PushAlertRest -> queryAlertDelivery");
		QueryAlertDeliveryResWrap resWrap = new QueryAlertDeliveryResWrap();
		resWrap.setQueryAlertDeliveryRes(service.queryAlertDelivery(networkId,
				fromDate, toDate, sortBy, messageType));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAlertPshDlvry")
	public QueryPushAlertDlvrybyIdResWrap queryAlertDelivery(
			@QueryParam("alrtId") String alrtId,
			@QueryParam("createDate") String createDate) {
		log.info("Inside PushAlertRest -> queryAlertPshDlvry");
		QueryPushAlertDlvrybyIdResWrap resWrap = new QueryPushAlertDlvrybyIdResWrap();
		resWrap.setQueryPushAlertDlvrybyIdRes(service.queryAlertPshDlvry(
				alrtId, createDate));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPeopleByPhone")
	public QueryPeopleByPhoneResWrap queryPeopleByPhone(
			@QueryParam("phoneNumbers") String phoneNumbers) {
		log.info("Inside PushAlertRest -> queryPeople");
		QueryPeopleByPhoneResWrap resWrap = new QueryPeopleByPhoneResWrap();
		resWrap.setQueryPeopleByPhoneRes(service
				.queryPeopleByPhone(phoneNumbers));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createCropTip")
	public CreateCropTipResWrap createCropTip(CreateCropTipReqWrap reqWrap) {
		log.info("Inside PushAlertRest -> createCropTip");
		CreateCropTipResWrap resWrap = new CreateCropTipResWrap();
		resWrap.setCreateCropTipRes(service.createCropTip(reqWrap
				.getCreateCropTipReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCropTips")
	public QueryCropTipsResWrap queryCropTips(@QueryParam("show") String show,
			@QueryParam("sort") String sort,
			@QueryParam("networkId") String networkId) {
		log.info("Inside PushAlertRest -> queryCropTips");
		QueryCropTipsResWrap resWrap = new QueryCropTipsResWrap();
		resWrap.setQueryCropTipsRes(service
				.queryCropTips(show, sort, networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCropAlert")
	public QueryCropAlertResWrap queryCropAlert(
			@QueryParam("cropTipId") String cropTipId,
			@QueryParam("operation") String operation) {
		log.info("Inside PushAlertRest -> queryCropAlert");
		QueryCropAlertResWrap resWrap = new QueryCropAlertResWrap();
		resWrap.setQueryCropAlertRes(service.queryCropAlert(cropTipId,
				operation));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("cropAction")
	public CropActionResWrap cropAction(CropActionReqWrap reqWrap) {
		log.info("Inside PushAlertRest -> cropAction");
		CropActionResWrap resWrap = new CropActionResWrap();
		resWrap.setCropActionRes(service.cropAction(reqWrap.getCropActionReq()));
		return resWrap;
	}
}
