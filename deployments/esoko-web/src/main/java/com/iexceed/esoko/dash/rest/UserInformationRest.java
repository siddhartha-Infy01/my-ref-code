package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.UserInformationService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.QueryUserAccountInformationResWrap;
import com.iexceed.esoko.jaxb.db.QueryUserLimitDetailsResWrap;

/*
 * @author Ankur
 */
@Path("UserInformationService")
@Component
public class UserInformationRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	UserInformationService userInfo;

	public UserInformationRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserAccountInformation")
	public QueryUserAccountInformationResWrap queryUserAccountInformation(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryUserAccountInformationResWrap -> queryUserAccountInformation");
		QueryUserAccountInformationResWrap infoRes = new QueryUserAccountInformationResWrap();
		infoRes.setQueryUserAccountInformationRes(userInfo
				.queryUserAccountInformation(userId));
		return infoRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserLimitDetails")
	public QueryUserLimitDetailsResWrap queryUserLimitDetails(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryUserLimitDetailsResWrap -> queryUserLimitDetails");
		QueryUserLimitDetailsResWrap detRes = new QueryUserLimitDetailsResWrap();
		detRes.setQueryUserLimitDetailsRes(userInfo
				.queryUserLimitDetails(userId));
		return detRes;
	}

}
