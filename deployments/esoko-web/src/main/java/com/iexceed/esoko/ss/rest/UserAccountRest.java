package com.iexceed.esoko.ss.rest;

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
import com.iexceed.esoko.jaxb.ss.EditUserAccountReqWrap;
import com.iexceed.esoko.jaxb.ss.EditUserAccountResWrap;
import com.iexceed.esoko.jaxb.ss.QueryUserAccountResWrap;
import com.iexceed.esoko.ss.service.UserAccountService;

@Component
@Path("UserAccountService")
public class UserAccountRest {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private UserAccountService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserAccount")
	public QueryUserAccountResWrap queryUserAccount(
			@QueryParam("userId") String userId,
			@QueryParam("networkId") String networkId) {
		log.info("Inside UserAccountRest -> queryUserAccount");
		QueryUserAccountResWrap resWrap = new QueryUserAccountResWrap();
		resWrap.setQueryUserAccountRes(service.queryUserAccount(userId,
				networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllUserAccounts")
	public QueryUserAccountResWrap queryAllUserAccounts(
			@QueryParam("userId") String userId,
			@QueryParam("networkId") String networkId) {
		log.info("Inside UserAccountRest -> queryAllUserAccounts");
		QueryUserAccountResWrap resWrap = new QueryUserAccountResWrap();
		resWrap.setQueryUserAccountRes(service.queryAllUserAccounts(userId,
				networkId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editUserAccount")
	public EditUserAccountResWrap editUserAccount(EditUserAccountReqWrap reqWrap) {
		log.info("Inside UserAccountRest -> editUserAccount");
		EditUserAccountResWrap resWrap = new EditUserAccountResWrap();
		resWrap.setEditUserAccountRes(service.editUserAccount(reqWrap
				.getEditUserAccountReq()));
		return resWrap;
	}
}
