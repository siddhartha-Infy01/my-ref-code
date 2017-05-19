package com.iexceed.esoko.inbox.rest;

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
import com.iexceed.esoko.inbox.service.InboxService;
import com.iexceed.esoko.jaxb.inbox.CreateKeywordReqWrap;
import com.iexceed.esoko.jaxb.inbox.CreateKeywordResWrap;
import com.iexceed.esoko.jaxb.inbox.DeleteInboxActivityReqWrap;
import com.iexceed.esoko.jaxb.inbox.DeleteInboxActivityResWrap;
import com.iexceed.esoko.jaxb.inbox.InboxActivityMonitorRes;
import com.iexceed.esoko.jaxb.inbox.InboxActivityMonitorResWrap;
import com.iexceed.esoko.jaxb.inbox.InboxFacilityReqWrap;
import com.iexceed.esoko.jaxb.inbox.InboxFacilityResWrap;
import com.iexceed.esoko.jaxb.inbox.KeywordActionsReqWrap;
import com.iexceed.esoko.jaxb.inbox.KeywordActionsResWrap;
import com.iexceed.esoko.jaxb.inbox.QueryAllAlertsByNwkWrap;
import com.iexceed.esoko.jaxb.inbox.QueryKeywordsResWrap;


@Component
@Path("InboxService")
public class InboxRest {

	
	@Autowired
	InboxService service;
	private static Logger log = LoggerUtils.getLogger();
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createKeyword")
	public CreateKeywordResWrap createKeyword(CreateKeywordReqWrap req) {
		log.info("Inside CreateKeywordResWrap -> createKeyword");
		CreateKeywordResWrap reswrap = new CreateKeywordResWrap();
		reswrap.setCreateKeywordRes(service.crKeyword(req.getCreateKeywordReq()));
		return reswrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("keywordAction")
	public KeywordActionsResWrap keywordAction(KeywordActionsReqWrap req) {
		log.info("Inside CreateKeywordResWrap -> keywordAction");
		KeywordActionsResWrap reswrap = new KeywordActionsResWrap();
		reswrap.setKeywordActionsRes(service.keywrdaction(req.getKeywordActionsReq()));
		return reswrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("inboxfacility")
	public InboxFacilityResWrap inboxfacility(InboxFacilityReqWrap req) {
		log.info("Inside InboxFacilityResWrap -> inboxfacility");
		InboxFacilityResWrap reswrap = new InboxFacilityResWrap();
		reswrap.setInboxFacilityRes(service.inboxfacility(req.getInboxFacilityReq()));
		return reswrap;
	}
	
	
		
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteInboxActivity")
	public DeleteInboxActivityResWrap deleteInboxActivity(DeleteInboxActivityReqWrap req) {
		log.info("Inside CreateKeywordResWrap -> deleteInboxActivity");
		DeleteInboxActivityResWrap reswrap = new DeleteInboxActivityResWrap();
		reswrap.setDeleteInboxActivityRes(service.deleteInboxActivity(req.getDeleteInboxActivityReq()));
		return reswrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryKeywords")
	public QueryKeywordsResWrap queryKeywords(
			@QueryParam("networkId") String networkId,@QueryParam("sortBy") String sortBy) {
		QueryKeywordsResWrap resWrap = new QueryKeywordsResWrap();
		resWrap.setQueryKeywordsRes(service.queryKeywords(networkId, sortBy));
		return resWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("inboxActivity")
	public InboxActivityMonitorResWrap inboxActivity(
			@QueryParam("networkId") String networkId,@QueryParam("todate") String todate,@QueryParam("serviceId") String serviceId,
			@QueryParam("subservice") String subservice,@QueryParam("fromdate") String fromdate,@QueryParam("sortBy") String sortBy) {
		InboxActivityMonitorResWrap resWrap = new InboxActivityMonitorResWrap();
		resWrap.setInboxActivityMonitorRes(service.inboxActivity(fromdate, todate, serviceId, subservice, networkId,sortBy));
		return resWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllAlerts")
	public QueryAllAlertsByNwkWrap queryAllAlerts(
			@QueryParam("networkId") String networkId) {
		QueryAllAlertsByNwkWrap resWrap = new QueryAllAlertsByNwkWrap();
		resWrap.setQueryAllAlertsByNwk(service.queryAllAlertsBynwk(networkId));
		return resWrap;
	}
}
