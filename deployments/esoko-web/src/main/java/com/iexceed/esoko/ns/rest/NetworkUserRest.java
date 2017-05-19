package com.iexceed.esoko.ns.rest;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.QueryNetworkUserInfoResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNetworkUserInfoResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNetworkUserInfoReqWrap;
import com.iexceed.esoko.ne.service.NetworkUserService;

/*
 * @author Ankur
 */
@Path("NetworkUserService")
@Component
public class NetworkUserRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkUserRest() {
	}

	@Autowired
	private NetworkUserService nwUserService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNetworkUserInfo")
	public QueryNetworkUserInfoResWrap queryNetworkUserInfo(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryNetworkUserInfoResWrap -> queryNetworkUserInfo");
		QueryNetworkUserInfoResWrap infoResWrap = new QueryNetworkUserInfoResWrap();
		infoResWrap.setQueryNetworkUserInfoRes(nwUserService
				.queryNwkUserInfo(networkId));
		return infoResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("saveNetworkUserInfo")
	public SaveNetworkUserInfoResWrap saveNetworkUserInfo(
			SaveNetworkUserInfoReqWrap infoReqWrap) {
		log.info("SaveNetworkUserInfoResWrap -> saveNetworkUserInfo");
		SaveNetworkUserInfoResWrap infoResWrap = new SaveNetworkUserInfoResWrap();
		infoResWrap.setSaveNetworkUserInfoRes(nwUserService
				.saveNwkUserInfo(infoReqWrap.getSaveNetworkUserInfoReq()));
		return infoResWrap;
	}

}
