package com.iexceed.esoko.ns.rest;

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
import com.iexceed.esoko.jaxb.ns.QueryNetworkCommoditiesResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNetworkCommoditiesReqWrap;
import com.iexceed.esoko.jaxb.ns.SaveNetworkCommoditiesResWrap;
import com.iexceed.esoko.ne.service.NetworkCommoditiesService;

@Component
/*
 * @author Ankur
 */
@Path("NetworkCommoditiesService")
public class NetworkCommoditiesRest {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	NetworkCommoditiesService service;

	public NetworkCommoditiesRest() {
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("queryNetworkUserInfo")
	public SaveNetworkCommoditiesResWrap saveNetworkCommodities(
			SaveNetworkCommoditiesReqWrap commoditiesReqWrap) {

		SaveNetworkCommoditiesResWrap infoResWrap = new SaveNetworkCommoditiesResWrap();

		return infoResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("queryNetworkVaritiesInfo")
	public QueryNetworkCommoditiesResWrap queryNetworkVaritiesInfo(
			@QueryParam("networkId") String nwkId) {

		QueryNetworkCommoditiesResWrap infoResWrap = new QueryNetworkCommoditiesResWrap();
		infoResWrap.setQueryNetworkCommoditiesRes(service
				.queryNetworkCommodities(nwkId));
		return infoResWrap;
	}

}
