package com.iexceed.esoko.ms.rest;


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
import com.iexceed.esoko.jaxb.ms.QueryUserDetailsResWrap;
import com.iexceed.esoko.jaxb.ms.SaveUserDetailsReqWrap;
import com.iexceed.esoko.jaxb.ms.SaveUserDetailsResWrap;
import com.iexceed.esoko.ms.service.UserDetailsService;

@Path("UserDetailsService")
@Component
public class UserDtlsServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public UserDtlsServiceRest() {
	}

	@Autowired
	private UserDetailsService userDtlsService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserDetails")
	public QueryUserDetailsResWrap queryCommodities(
			@QueryParam("userId") String userId) {
		QueryUserDetailsResWrap resWrap = new QueryUserDetailsResWrap();
		resWrap.setQueryUserDetailsRes(userDtlsService.queryUserDetails(userId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("saveUserDetails")
	public SaveUserDetailsResWrap saveUserDetails(SaveUserDetailsReqWrap req) {
		SaveUserDetailsResWrap userDtlsResWrap = new SaveUserDetailsResWrap();
		userDtlsResWrap.setSaveUserDetailsRes(userDtlsService
				.saveUserDetails(req.getSaveUserDetailsReq()));
		return userDtlsResWrap;
	}

}