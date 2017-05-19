package com.iexceed.esoko.se.rest;

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
import com.iexceed.esoko.jaxb.se.CreateNetworkReqWrap;
import com.iexceed.esoko.jaxb.se.CreateNetworkResWrap;
import com.iexceed.esoko.jaxb.se.DeleteNetworkReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteNetworkResWrap;
import com.iexceed.esoko.jaxb.se.QueryNetworksByUserResWrap;
import com.iexceed.esoko.jaxb.se.QueryNetworksResWrap;
//import com.iexceed.esoko.jaxb.se.crtntwrk.CreateNetworkReq;
//import com.iexceed.esoko.jaxb.se.crtntwrk.CreateNetworkRes;
//import com.iexceed.esoko.jaxb.se.delntwrk.DeleteNetworkReq;
//import com.iexceed.esoko.jaxb.se.delntwrk.DeleteNetworkRes;
//import com.iexceed.esoko.jaxb.se.qryntwrk.QueryNetworksRes;
//import com.iexceed.esoko.jaxb.se.qryntwrkusr.QueryNetworksByUserRes;
import com.iexceed.esoko.se.service.NetworkService;

@Path("NetworkService")
@Component
public class NetworkServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkServiceRest() {
	}

	@Autowired
	private NetworkService networkService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNetworks")
	public QueryNetworksResWrap queryNetworks(
			@QueryParam("networkId") String networkId,
			@QueryParam("networkName") String networkName,
			@QueryParam("type") String type,
			@QueryParam("parentID") String parentId,
			@QueryParam("userId") String userId,
			@QueryParam("userData") String userData) {
		QueryNetworksResWrap qNetworksResWrap = new QueryNetworksResWrap();
		qNetworksResWrap.setQueryNetworksRes(networkService.queryNetworks(
				networkId, networkName, type, parentId, userId, userData));
		return qNetworksResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNetworksByUser")
	public QueryNetworksByUserResWrap queryNetworksByUser(
			@QueryParam("userId") String userId) {
		QueryNetworksByUserResWrap qryRespWrap = new QueryNetworksByUserResWrap();
		qryRespWrap.setQueryNetworksByUserRes(networkService
				.queryNetworksByUser(userId));
		return qryRespWrap;
	}

	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createNetwork")
	public CreateNetworkResWrap createNetwork(CreateNetworkReqWrap req) {
		CreateNetworkResWrap addNwkResWrap = new CreateNetworkResWrap();
		addNwkResWrap.setCreateNetworkRes(networkService.createNetwork(req
				.getCreateNetworkReq()));
		return addNwkResWrap;
	}

	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteNetwork")
	public DeleteNetworkResWrap deleteNetwork(DeleteNetworkReqWrap req) {
		DeleteNetworkResWrap delNwkRespWrap = new DeleteNetworkResWrap();
		delNwkRespWrap.setDeleteNetworkRes(networkService.deleteNetwork(req
				.getDeleteNetworkReq()));
		return delNwkRespWrap;
	}
}
