package com.iexceed.esoko.smspoll.rest;

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
import com.iexceed.esoko.jaxb.smspoll.CreatePollReqWrap;
import com.iexceed.esoko.jaxb.smspoll.CreatePollResWrap;
import com.iexceed.esoko.jaxb.smspoll.PollActionReqWrap;
import com.iexceed.esoko.jaxb.smspoll.PollActionResWrap;
import com.iexceed.esoko.jaxb.smspoll.QueryAllPollsResWrap;
import com.iexceed.esoko.jaxb.smspoll.QueryPollResWrap;
import com.iexceed.esoko.jaxb.smspoll.ValidateKeywordResWrap;
import com.iexceed.esoko.smspoll.service.SMSPollService;

@Component
@Path("SMSPollService")
public class SMSPollRest {
	private static Logger log = LoggerUtils.getLogger();

	@Autowired
	private SMSPollService service;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createPoll")
	public CreatePollResWrap createPoll(CreatePollReqWrap reqWrap) {
		log.info("Inside SMSPollRest -> createPoll");
		CreatePollResWrap resWrap = new CreatePollResWrap();
		resWrap.setCreatePollRes(service.createPoll(reqWrap.getCreatePollReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPoll")
	public QueryPollResWrap queryPoll(@QueryParam("pollId") String pollId) {
		log.info("Inside SMSPollRest -> queryPoll");
		QueryPollResWrap resWrap = new QueryPollResWrap();
		resWrap.setQueryPollRes(service.queryPoll(pollId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllPolls")
	public QueryAllPollsResWrap queryAllPolls(
			@QueryParam("pollType") String pollType,
			@QueryParam("sortBy") String sortBy) {
		log.info("Inside SMSPollRest -> queryAllPolls");
		QueryAllPollsResWrap resWrap = new QueryAllPollsResWrap();
		resWrap.setQueryAllPollsRes(service.queryAllPolls(pollType, sortBy));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("pollAction")
	public PollActionResWrap pollAction(PollActionReqWrap reqWrap) {
		log.info("Inside SMSPollRest -> pollAction");
		PollActionResWrap resWrap = new PollActionResWrap();
		resWrap.setPollActionRes(service.pollAction(reqWrap.getPollActionReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("validateKeywords")
	public ValidateKeywordResWrap validateKeywords(@QueryParam("id") String id,
			@QueryParam("networkId") String networkId,
			@QueryParam("keyword") String keyword,
			@QueryParam("channel") String channel,
			@QueryParam("appName") String appName) {
		log.info("Inside SMSPollRest -> validateKeywords");
		ValidateKeywordResWrap resWrap = new ValidateKeywordResWrap();
		resWrap.setValidateKeywordRes(service.validateKeywords(id, networkId,
				keyword, channel, appName));
		return resWrap;
	}
}
