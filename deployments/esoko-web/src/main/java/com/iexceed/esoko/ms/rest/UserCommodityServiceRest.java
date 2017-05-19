package com.iexceed.esoko.ms.rest;


import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.ms.service.UserCommoditiesService;

@Path("UserCommoditiesService")
@Component
public class UserCommodityServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public UserCommodityServiceRest() {
	}

	@Autowired
	private UserCommoditiesService userDtlsService;
//
//	@GET
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Path("queryUserDetails")
//	public QueryUserDetailsResWrap queryCommodities(
//			@QueryParam("userId") String userId) {
//		QueryUserDetailsResWrap resWrap = new QueryUserDetailsResWrap();
//		resWrap.setQueryUserDetailsRes(userDtlsService.queryUserDetails(userId));
//		return resWrap;
//	}
//
//	@POST
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Path("saveUserDetails")
//	public SaveUserDetailsResWrap saveUserDetails(SaveUserDetailsReqWrap req) {
//		SaveUserDetailsResWrap userDtlsResWrap = new SaveUserDetailsResWrap();
//		userDtlsResWrap.setSaveUserDetailsRes(userDtlsService
//				.saveUserDetails(req.getSaveUserDetailsReq()));
//		return userDtlsResWrap;
//	}

}