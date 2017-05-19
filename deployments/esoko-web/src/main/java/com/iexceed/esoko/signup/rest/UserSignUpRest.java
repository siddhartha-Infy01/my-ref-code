package com.iexceed.esoko.signup.rest;

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
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserReqWrap;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserResWrap;
import com.iexceed.esoko.jaxb.login.crtusr.ValidateEsokoUserReqWrap;
import com.iexceed.esoko.jaxb.login.crtusr.ValidateEsokoUserResWrap;
import com.iexceed.esoko.jaxb.login.usrquery.QueryEsokoUserResWrap;
import com.iexceed.esoko.signup.service.UserSignUpService;

/*
 * @author Ankur
 */
@Path("UserSignUpService")
@Component
public class UserSignUpRest {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	public UserSignUpService signUp;

	public UserSignUpRest() {

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createEsokoUser")
	public CreateEsokoUserResWrap createEsokoUser(CreateEsokoUserReqWrap req) {
		log.info("Inside CreateEsokoUserResWrap -> createEsokoUser");
		CreateEsokoUserResWrap userRes = new CreateEsokoUserResWrap();
		userRes.setCreateEsokoUserRes(signUp.createEsokoUser(req
				.getCreateEsokoUserReq()));
		return userRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryEsokoUser")
	public QueryEsokoUserResWrap queryEsokoUser(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryEsokoUserResWrap -> queryEsokoUser");
		QueryEsokoUserResWrap queryUser = new QueryEsokoUserResWrap();
		queryUser.setQueryEsokoUserRes(signUp.queryEsokoUser(userId));
		return queryUser;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("validateEsokoUser")
	public ValidateEsokoUserResWrap validateEsokoUser(
			ValidateEsokoUserReqWrap req) {
		log.info("Inside UserSignUpRest -> createEsokoUser");
		ValidateEsokoUserResWrap userRes = new ValidateEsokoUserResWrap();
		userRes.setValidateEsokoUserRes(signUp.validateEsokoUser(req
				.getValidateEsokoUserReq()));
		return userRes;
	}
}
