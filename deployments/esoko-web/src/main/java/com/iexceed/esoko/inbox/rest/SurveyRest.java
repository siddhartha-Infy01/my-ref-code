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
import com.iexceed.esoko.inbox.service.SurveyService;
import com.iexceed.esoko.jaxb.inbox.CreateSurveyReqWrap;
import com.iexceed.esoko.jaxb.inbox.CreateSurveyResWrap;
import com.iexceed.esoko.jaxb.inbox.QueryAgentSurveyResWrap;
import com.iexceed.esoko.jaxb.inbox.QuerySurveyDefinitionResWrap;
import com.iexceed.esoko.jaxb.inbox.QuerySurveyResWrap;

@Component
@Path("SurveyService")
public class SurveyRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	SurveyService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySurvey")
	public QuerySurveyResWrap querySurvey(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("surveyName") String surveyName,
			@QueryParam("phase") String phase) {
		log.info("Inside SurveyRest --> queryServey");
		QuerySurveyResWrap resWrap = new QuerySurveyResWrap();
		resWrap.setQuerySurveyRes(service.querySurvey(networkId, userId,
				surveyName, phase));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySurveyForAgent")
	public QueryAgentSurveyResWrap querySurveyForAgent(
			@QueryParam("networkId") String networkId,
			@QueryParam("agentId") String userId) {
		log.info("Inside SurveyRest --> querySurveyForAgent");
		QueryAgentSurveyResWrap resWrap = new QueryAgentSurveyResWrap();
		resWrap.setQueryAgentSurveyRes(service.querySurveyForAgent(networkId,
				userId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createSurvey")
	public CreateSurveyResWrap createSurvey(CreateSurveyReqWrap reqWrap) {
		log.info("Inside SurveyRest --> createSurvey");
		CreateSurveyResWrap resWrap = new CreateSurveyResWrap();
		resWrap.setCreateSurveyRes(service.createSurvey(reqWrap
				.getCreateSurveyReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySurveyDefinition")
	public QuerySurveyDefinitionResWrap querySurveyDef(
			@QueryParam("networkId") String networkId,
			@QueryParam("surveyName") String surveyName) {
		log.info("Inside SurveyRest --> queryServey");
		QuerySurveyDefinitionResWrap resWrap = new QuerySurveyDefinitionResWrap();
		resWrap.setQuerySurveyDefinitionRes(service.querySurveyDef(networkId,
				surveyName));
		return resWrap;
	}

}
