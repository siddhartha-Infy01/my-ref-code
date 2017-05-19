package com.iexceed.esoko.agents.rest;

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

import com.iexceed.esoko.agents.service.AgentService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.agents.AgentdetailsResWrap;
import com.iexceed.esoko.jaxb.agents.AllusersResWrap;
import com.iexceed.esoko.jaxb.agents.CreateAgentRequestResWrap;
import com.iexceed.esoko.jaxb.agents.CreateAgentRequestWrap;
import com.iexceed.esoko.jaxb.agents.CreateSurveyTemplate;
import com.iexceed.esoko.jaxb.agents.CreateSurveyTemplateResWrap;
import com.iexceed.esoko.jaxb.agents.CreateSurveyTemplateWrap;
import com.iexceed.esoko.jaxb.agents.DeleteTemplateRequestResWrap;
import com.iexceed.esoko.jaxb.agents.DeleteTemplateRequestWrap;
import com.iexceed.esoko.jaxb.agents.GenerateReportsReqWrap;
import com.iexceed.esoko.jaxb.agents.GenerateReportsResWrap;
import com.iexceed.esoko.jaxb.agents.ModifyAgentsReqWrap;
import com.iexceed.esoko.jaxb.agents.ModifyAgentsResWrap;
import com.iexceed.esoko.jaxb.agents.QueryAgentDtlsByAppsResWrap;
import com.iexceed.esoko.jaxb.agents.QueryAgentDtlsResWrap;
import com.iexceed.esoko.jaxb.agents.QueryTemplatesResWrap;
import com.iexceed.esoko.jaxb.agents.SaveForMultipleReqWrap;
import com.iexceed.esoko.jaxb.agents.SaveForMultipleResWrap;
import com.iexceed.esoko.jaxb.agents.UsrExstsResWrap;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserReqWrap;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserResWrap;
import com.iexceed.esoko.jaxb.ns.CreateShareRequestResWrap;

@Component
@Path("AgentService")
public class AgentServiceRest {
	@Autowired
	AgentService service;
	private static Logger log = LoggerUtils.getLogger();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAgentDtlsRes")
	public QueryAgentDtlsResWrap queryAgentDtlsRes(
			@QueryParam("networkId") String networkId,@QueryParam("flagType") String flagType) {
		QueryAgentDtlsResWrap resWrap = new QueryAgentDtlsResWrap();
		resWrap.setQueryAgentDtlsRes(service.queryAgentDtlsRes(networkId,flagType));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAgentDtlsByAppsRes")
	public QueryAgentDtlsByAppsResWrap queryAgentDtlsByAppsRes(
			@QueryParam("networkId") String networkId) {
		QueryAgentDtlsByAppsResWrap resWrap = new QueryAgentDtlsByAppsResWrap();
		resWrap.setQueryAgentDtlsByAppsRes(service
				.queryAgentAppsDtsRes(networkId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("generateReports")	
	public GenerateReportsResWrap generateReports(GenerateReportsReqWrap req) {
		log.info("Inside AgentServiceRest -> generateReports");
		GenerateReportsResWrap resWrap = new GenerateReportsResWrap();
		resWrap.setGenerateReportsRes(service.generateReports(req
				.getGenerateReportsReq()));
		return resWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createAgentRequest")
	public CreateAgentRequestResWrap createAgentRequest(
			CreateAgentRequestWrap req) {
		log.info("Inside CreateAgentRequestResWrap -> createAgentRequest");
		CreateAgentRequestResWrap agentResWrap = new CreateAgentRequestResWrap();
		agentResWrap.setCreateAgentRequestRes(service
				.createAgentRes(req.getCreateAgentRequest()));
		return agentResWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createNewUserAgent")
	public CreateEsokoUserResWrap createEsokoUser(CreateEsokoUserReqWrap req) {
		log.info("Inside CreateEsokoUserResWrap -> createEsokoUser");
		CreateEsokoUserResWrap userRes = new CreateEsokoUserResWrap();
		userRes.setCreateEsokoUserRes(service.createNewUserAgent(req
				.getCreateEsokoUserReq()));
		return userRes;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createSurveyTemplate")
	public CreateSurveyTemplateResWrap createSurveyTemplate(
			CreateSurveyTemplateWrap req) {
		log.info("Inside createSurveyTemplateResWrap -> createSurveyTemplate");
		CreateSurveyTemplateResWrap templateResWrap = new CreateSurveyTemplateResWrap();
		templateResWrap.setCreateSurveyTemplateRes(service
				.createSurveyTemplateRes(req.getCreateSurveyTemplate()));
		return templateResWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteSurveyTemplate")
	public DeleteTemplateRequestResWrap deleteSurveyTemplate(
			DeleteTemplateRequestWrap req) { 
		log.info("Inside deleteSurveyTemplateResWrap -> deleteSurveyTemplate");
		DeleteTemplateRequestResWrap agentResWrap = new DeleteTemplateRequestResWrap();
		agentResWrap.setDeleteTemplateRequestRes(service
				.deleteTemplateRes(req.getDeleteTemplateRequest()));
		return agentResWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("agentdetailsRes")
	public AgentdetailsResWrap agentdetailsRes(
			@QueryParam("networkId") String networkId,@QueryParam("peopleId") String peopleId) {
		AgentdetailsResWrap resWrap = new AgentdetailsResWrap();
		resWrap.setAgentdetailsRes(service
				.agentDtsRes(networkId,peopleId));
		return resWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("templateDetailsRes")
	public QueryTemplatesResWrap templateDetailsRes(
			@QueryParam("networkId") String networkId) {
		QueryTemplatesResWrap resWrap = new QueryTemplatesResWrap();
		resWrap.setQueryTemplatesRes(service
				.templateDetailsRes(networkId));
		return resWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("modifyAgentsReq")
	public ModifyAgentsResWrap modifyAgents(
			ModifyAgentsReqWrap req) {
		log.info("Inside ModifyAgentsResWrap -> modifyAgentsReq");
		ModifyAgentsResWrap agentsResWrap = new ModifyAgentsResWrap();
		agentsResWrap.setModifyAgentsRes(service.modifyAgentsDtls(req.getModifyAgentsReq()));
		return agentsResWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveMulitpleReq")
	public SaveForMultipleResWrap saveMulitple(
			SaveForMultipleReqWrap req) {
		log.info("Inside SaveForMultipleResWrap -> saveMulitple");
		SaveForMultipleResWrap agentsResWrap = new SaveForMultipleResWrap();
		agentsResWrap.setSaveForMultipleRes(service.saveForMultiple(req.getSaveForMultipleReq()));
		return agentsResWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("allusersRes")
	public AllusersResWrap queryAllusers()
			 {
		AllusersResWrap resWrap = new AllusersResWrap();
		resWrap.setAllusersRes(service.allusersRes());
		return resWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("usrExtsRes")
	public UsrExstsResWrap usrExtsRes(
			@QueryParam("networkId") String networkId,@QueryParam("peopleId") String peopleId) {
		UsrExstsResWrap resWrap = new UsrExstsResWrap();
		resWrap.setUsrExstsRes(service.usrExtsRes(networkId,peopleId));
		return resWrap;
	}
}


