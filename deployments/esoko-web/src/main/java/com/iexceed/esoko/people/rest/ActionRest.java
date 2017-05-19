package com.iexceed.esoko.people.rest;

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
import com.iexceed.esoko.jaxb.people.QueryNwkAccountsResWrap;
import com.iexceed.esoko.jaxb.people.SendEmailReqWrap;
import com.iexceed.esoko.jaxb.people.SendEmailResWrap;
import com.iexceed.esoko.jaxb.people.SendSmsReqWrap;
import com.iexceed.esoko.jaxb.people.SendSmsResWrap;
import com.iexceed.esoko.jaxb.people.UnMapFromNwkReqWrap;
import com.iexceed.esoko.jaxb.people.UnMapFromNwkResWrap;
import com.iexceed.esoko.people.service.ActionService;

/*
 * @author Ankur
 */
@Path("ActionService")
@Component
public class ActionRest {
	public ActionRest() {

	}

	@Autowired
	private ActionService action;
	private static Logger log = LoggerUtils.getLogger();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkAccounts")
	public QueryNwkAccountsResWrap queryNwkAccounts(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryNwkAccountsResWrap -> queryNwkAccounts");
		QueryNwkAccountsResWrap accRes = new QueryNwkAccountsResWrap();
		accRes.setQueryNwkAccountsRes(action.queryNwkAccounts(userId));
		return accRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("sendSms")
	public SendSmsResWrap sendSms(SendSmsReqWrap req) {
		log.info("Inside SendSmsResWrap -> sendSms");
		SendSmsResWrap smsRes = new SendSmsResWrap();
		smsRes.setSendSmsRes(action.sendSms(req.getSendSmsReq()));
		return smsRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("sendEmail")
	public SendEmailResWrap sendEmail(SendEmailReqWrap req) {
		log.info("Inside SendEmailResWrap -> sendEmail");
		SendEmailResWrap emailRes = new SendEmailResWrap();
		emailRes.setSendEmailRes(action.sendEmail(req.getSendEmailReq()));
		return emailRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("unMapFromNwk")
	public UnMapFromNwkResWrap unMapFromNwk(UnMapFromNwkReqWrap req) {
		log.info("Inside UnMapFromNwkResWrap -> unMapFromNwk");
		UnMapFromNwkResWrap nwkRes = new UnMapFromNwkResWrap();
		nwkRes.setUnMapFromNwkRes(action.unMapFromNwk(req.getUnMapFromNwkReq()));
		return nwkRes;
	}
}
