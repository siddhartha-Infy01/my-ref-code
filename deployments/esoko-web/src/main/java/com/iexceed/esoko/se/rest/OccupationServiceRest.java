package com.iexceed.esoko.se.rest;

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
import com.iexceed.esoko.jaxb.se.CreateOccupationReqWrap;
import com.iexceed.esoko.jaxb.se.CreateOccupationResWrap;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedOccupationWrap;
import com.iexceed.esoko.jaxb.se.DeleteOccupationReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteOccupationResWrap;
import com.iexceed.esoko.jaxb.se.MapCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.MapOccupationReqWrap;
import com.iexceed.esoko.jaxb.se.MapOccupationResWrap;
import com.iexceed.esoko.jaxb.se.QueryOccupChildsCountResWrap;
import com.iexceed.esoko.jaxb.se.QueryOccupationResWrap;
//import com.iexceed.esoko.jaxb.se.crtoccuptn.CreateOccupationReq;
//import com.iexceed.esoko.jaxb.se.crtoccuptn.CreateOccupationRes;
//import com.iexceed.esoko.jaxb.se.deloccuptn.DeleteOccupationReq;
//import com.iexceed.esoko.jaxb.se.deloccuptn.DeleteOccupationRes;
//import com.iexceed.esoko.jaxb.se.qryoccuptn.QueryOccupationRes;
import com.iexceed.esoko.se.service.OccupationService;

@Path("OccupationService")
@Component
public class OccupationServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public OccupationServiceRest() {
	}

	@Autowired
	private OccupationService occupationService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createOccupation")
	public CreateOccupationResWrap createOccupation(CreateOccupationReqWrap req) {
		CreateOccupationResWrap crtResWrap = new CreateOccupationResWrap();
		crtResWrap.setCreateOccupationRes(occupationService
				.createOccupation(req.getCreateOccupationReq()));
		return crtResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryOccupation")
	public QueryOccupationResWrap queryOccupation(
			@QueryParam("occupationID") String occupationID,
			@QueryParam("type") String type, @QueryParam("rank") int rank,
			@QueryParam("parentId") String parentId) {
		QueryOccupationResWrap qryOccuResWrap = new QueryOccupationResWrap();
		qryOccuResWrap.setQueryOccupationRes(occupationService.queryOccupation(
				occupationID, type, rank, parentId));
		return qryOccuResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllOccupations")
	public QueryOccupationResWrap queryAllOccupations(
			@QueryParam("networkId") String pNetworkId,
			@QueryParam("commodityId") String pCommodityId,
			@QueryParam("userId") String pUserId,
			@QueryParam("widgetId") String pWidgetId,
			@QueryParam("userData") String pUserData) {
		QueryOccupationResWrap qryOccuResWrap = new QueryOccupationResWrap();
		qryOccuResWrap.setQueryOccupationRes(occupationService
				.queryAllOccupation(pNetworkId, pUserId, pUserData));
		return qryOccuResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteOccupation")
	public DeleteOccupationResWrap deleteOccupation(DeleteOccupationReqWrap req) {
		DeleteOccupationResWrap delOccuResWrap = new DeleteOccupationResWrap();
		delOccuResWrap.setDeleteOccupationRes(occupationService
				.deleteOccupation(req.getDeleteOccupationReq()));
		return delOccuResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryOccupChildsCount")
	public QueryOccupChildsCountResWrap queryOccupChildsCount(
			@QueryParam("parentId") String parentId) {
		QueryOccupChildsCountResWrap qryResWrap = new QueryOccupChildsCountResWrap();
		qryResWrap.setQueryOccupChildsCountRes(occupationService
				.queryOccupChildsCount(parentId));
		return qryResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("mapOccupations")
	public MapOccupationResWrap mapOccupations(MapOccupationReqWrap req) {
		MapOccupationResWrap mapResWrap = new MapOccupationResWrap();
		mapResWrap.setMapOccupationRes(occupationService.mapoccupation(req
				.getMapOccupationReq()));
		return mapResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("currentlySelectedOccupations")
	public CurrentlySelectedOccupationWrap currentlySelectedOccupations(
			@QueryParam("userData") String userData, @QueryParam("Id") String Id,@QueryParam("networkId") String networkId) {
		System.out.println("User Data::::" + userData);
		System.out.println("Id::::" + Id);
		CurrentlySelectedOccupationWrap currentlySelectedOccupationsWrap = new CurrentlySelectedOccupationWrap();
		currentlySelectedOccupationsWrap
				.setCurrentlySelectedOccupation(occupationService
						.currentlySelectedCommodities(userData, Id,networkId));
		return currentlySelectedOccupationsWrap;
	}
}
