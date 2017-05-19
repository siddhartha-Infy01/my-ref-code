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
import com.iexceed.esoko.jaxb.ms.CreateDefaultNetworkReqWrap;
import com.iexceed.esoko.jaxb.ms.CreateDefaultNetworkResWrap;
import com.iexceed.esoko.jaxb.ms.DeleteUserFromNetworkReqWrap;
import com.iexceed.esoko.jaxb.ms.DeleteUserFromNetworkResWrap;
import com.iexceed.esoko.jaxb.ms.QueryUserNetworkDetailsResWrap;
import com.iexceed.esoko.ms.service.UserNetworkDetailsService;

@Path("UserNetworkDetailsService")
@Component
public class UserNetworkDetailsRest {

	public static Logger log = LoggerUtils.getLogger();

	public UserNetworkDetailsRest() {
	}

	@Autowired
	private UserNetworkDetailsService userNwkService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserNetworkDetails")
	public QueryUserNetworkDetailsResWrap queryUserNetworkDetails(
			@QueryParam("userId") String userId) {
		QueryUserNetworkDetailsResWrap resWrap = new QueryUserNetworkDetailsResWrap();
		resWrap.setQueryUserNetworkDetailsRes(userNwkService
				.queryUserNetworkDetails(userId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createDefaultNetwork")
	public CreateDefaultNetworkResWrap createDefaultNetwork(
			CreateDefaultNetworkReqWrap req) {
		log.info("Inside NwkFundTransferResWrap -> nwkFundTransfer");
		CreateDefaultNetworkResWrap crtNwkResWrap = new CreateDefaultNetworkResWrap();
		crtNwkResWrap.setCreateDefaultNetworkRes(userNwkService
				.createDefaultNetwork(req.getCreateDefaultNetworkReq()));
		return crtNwkResWrap;
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteUserFromNetwork")
	public DeleteUserFromNetworkResWrap deleteUserFromNetwork(
			DeleteUserFromNetworkReqWrap req) {
		log.info("Inside NwkFundTransferResWrap -> nwkFundTransfer");
		DeleteUserFromNetworkResWrap delFromResWrap = new DeleteUserFromNetworkResWrap();
		delFromResWrap.setDeleteUserFromNetworkRes(userNwkService
				.deleteUserFromNetwork(req.getDeleteUserFromNetworkReq()));
		return delFromResWrap;
	}
}