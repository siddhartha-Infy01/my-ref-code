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
import com.iexceed.esoko.jaxb.ns.NwkPriceTypeSaveReq;
import com.iexceed.esoko.jaxb.ns.NwkPriceTypeSaveReqWrap;
import com.iexceed.esoko.jaxb.ns.NwkPriceTypeSaveResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNetworkPriceTypesResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkPriceTypesResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPriceTypesReqWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPriceTypesResWrap;
import com.iexceed.esoko.ne.service.NetworkPriceTypesService;

/*
 * @author Ankur
 */
@Path("NetworkPriceTypesService")
@Component
public class NetworkPriceTypesRest {
	public static Logger log = LoggerUtils.getLogger();

	public NetworkPriceTypesRest() {

	}

	@Autowired
	NetworkPriceTypesService nwPriceTypesService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveNwkPriceTypes")
	public SaveNwkPriceTypesResWrap saveNwkPriceTypes(
			SaveNwkPriceTypesReqWrap req) {
		log.info("Inside NetworkPriceTypesRest -> saveNwkPriceTypes");
		SaveNwkPriceTypesResWrap saveResWrap = new SaveNwkPriceTypesResWrap();
		saveResWrap.setSaveNwkPriceTypesRes(nwPriceTypesService
				.saveNwkPriceType(req.getSaveNwkPriceTypesReq()));
		return saveResWrap;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveNetworkPriceTypes")
	public NwkPriceTypeSaveResWrap saveNetworkPriceTypes(NwkPriceTypeSaveReqWrap req) {
		log.info("Inside NetworkPriceTypesRest -> saveNwkPriceTypes");
		NwkPriceTypeSaveResWrap saveResWrap = new NwkPriceTypeSaveResWrap();
		saveResWrap.setNwkPriceTypeSaveRes(nwPriceTypesService
				.saveNetworkPriceTypes(req.getNwkPriceTypeSaveReq()));
		return saveResWrap;
	}

	// Delete this method
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkPriceTypes")
	public QueryNwkPriceTypesResWrap queryNwkPriceTypes(
			@QueryParam("networkId") String networkId) {
		log.info("Inside NetworkPriceTypesRest -> queryNwkPriceTypes");
		QueryNwkPriceTypesResWrap queryResWrap = new QueryNwkPriceTypesResWrap();
		queryResWrap.setQueryNwkPriceTypesRes(nwPriceTypesService
				.queryNwkPriceTypes(networkId));
		return queryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNetworkPriceTypes")
	public QueryNetworkPriceTypesResWrap queryNetworkPriceTypes(
			@QueryParam("networkId") String networkId) {
		log.info("Inside NetworkPriceTypesRest -> queryNetworkPriceTypes");
		QueryNetworkPriceTypesResWrap queryResWrap = new QueryNetworkPriceTypesResWrap();
		queryResWrap.setQueryNetworkPriceTypesRes(nwPriceTypesService
				.queryNetworkPriceTypes(networkId));
		return queryResWrap;
	}
}
