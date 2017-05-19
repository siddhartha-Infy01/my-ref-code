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
import com.iexceed.esoko.jaxb.approval.EditLocationReqWrap;
import com.iexceed.esoko.jaxb.approval.EditLocationResWrap;
import com.iexceed.esoko.jaxb.se.ApproveLocationsReqWrap;
import com.iexceed.esoko.jaxb.se.ApproveLocationsResWrap;
import com.iexceed.esoko.jaxb.se.CreateLocationsReqWrap;
import com.iexceed.esoko.jaxb.se.CreateLocationsResWrap;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedLocationWrap;
import com.iexceed.esoko.jaxb.se.DeleteLocationsReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteLocationsResWrap;
import com.iexceed.esoko.jaxb.se.LocationMapReqWrap;
import com.iexceed.esoko.jaxb.se.LocationMapResWrap;
import com.iexceed.esoko.jaxb.se.QueryCountriesResWrap;
import com.iexceed.esoko.jaxb.se.QueryLocChildsCountResWrap;
import com.iexceed.esoko.jaxb.se.QueryLocationsResWrap;
import com.iexceed.esoko.jaxb.se.QueryMarketsResWrap;
import com.iexceed.esoko.jaxb.se.QueryStatesResWrap;
import com.iexceed.esoko.jaxb.se.QueryTownsResWrap;
import com.iexceed.esoko.se.service.LocationsService;

@Path("LocationsService")
@Component
public class LocationsServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public LocationsServiceRest() {

	}

	@Autowired
	private LocationsService locationsService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLocations")
	public QueryLocationsResWrap queryLocations(
			@QueryParam("locationID") String locationID,
			@QueryParam("type") String type, @QueryParam("rank") int rank,
			@QueryParam("parentId") String parentId,
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("userData") String userData) {
		QueryLocationsResWrap locationResWrap = new QueryLocationsResWrap();
		locationResWrap.setQueryLocationsRes(locationsService.queryLocations(
				locationID, type, rank, parentId, networkId, userId, userData));
		return locationResWrap;

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllLocations")
	public QueryLocationsResWrap queryAllLocations(

	@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("widgetId") String widgetId,
			@QueryParam("userData") String userData) {
		QueryLocationsResWrap locationResWrap = new QueryLocationsResWrap();
		locationResWrap.setQueryLocationsRes(locationsService
				.queryAllLocations(networkId, userId, widgetId, userData));
		return locationResWrap;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createLocations")
	public CreateLocationsResWrap createLocations(CreateLocationsReqWrap req) {
		CreateLocationsResWrap locatinWrapResp = new CreateLocationsResWrap();
		locatinWrapResp.setCreateLocationsRes(locationsService
				.createLocations(req.getCreateLocationsReq()));
		return locatinWrapResp;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteLocations")
	public DeleteLocationsResWrap deleteLocations(DeleteLocationsReqWrap req) {
		DeleteLocationsResWrap locatinWrapResp = new DeleteLocationsResWrap();
		locatinWrapResp.setDeleteLocationsRes(locationsService
				.deleteLocations(req.getDeleteLocationsReq()));
		return locatinWrapResp;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("approveLocations")
	public ApproveLocationsResWrap approveLocations(ApproveLocationsReqWrap req) {
		ApproveLocationsResWrap locationResWrap = new ApproveLocationsResWrap();

		locationResWrap.setApproveLocationsRes(locationsService
				.approveLocations(req.getApproveLocationsReq()));
		return locationResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLocChildsCount")
	public QueryLocChildsCountResWrap queryLocChildsCount(
			@QueryParam("parentId") String parentId) {
		QueryLocChildsCountResWrap qryResWrap = new QueryLocChildsCountResWrap();
		qryResWrap.setQueryLocChildsCountRes(locationsService
				.queryLocChildsCount(parentId));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCountries")
	public QueryCountriesResWrap queryCountries() {
		QueryCountriesResWrap qryResWrap = new QueryCountriesResWrap();
		qryResWrap.setQueryCountriesRes(locationsService.queryCountries());
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryTowns")
	public QueryTownsResWrap queryTowns(
			@QueryParam("parentCountry") String parentCountry) {
		QueryTownsResWrap qryResWrap = new QueryTownsResWrap();
		qryResWrap.setQueryTownsRes(locationsService.queryTowns(parentCountry));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryStateTowns")
	public QueryTownsResWrap queryStateTowns(@QueryParam("state") String state) {
		QueryTownsResWrap qryResWrap = new QueryTownsResWrap();
		qryResWrap.setQueryTownsRes(locationsService.queryStateTowns(state));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryStates")
	public QueryStatesResWrap queryStates(
			@QueryParam("parentCountry") String parentCountry) {
		QueryStatesResWrap qryResWrap = new QueryStatesResWrap();
		qryResWrap.setQueryStatesRes(locationsService
				.queryStates(parentCountry));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMarkets")
	public QueryMarketsResWrap queryMarkets(
			@QueryParam("parentRegion") String parentRegion) {
		QueryMarketsResWrap qryResWrap = new QueryMarketsResWrap();
		qryResWrap.setQueryMarketsRes(locationsService
				.queryMarkets(parentRegion));
		return qryResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("mapLocation")
	public LocationMapResWrap mapLocation(LocationMapReqWrap req) {
		LocationMapResWrap qryResWrap = new LocationMapResWrap();
		qryResWrap.setLocationMapRes(locationsService.mapLocation(req
				.getLocationMapReq()));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("currentlySelectedLocations")
	public CurrentlySelectedLocationWrap currentlySelectedLocations(

	@QueryParam("userData") String userData, @QueryParam("Id") String Id,
			@QueryParam("networkId") String networkId) {
		System.out.println("User Data::::" + userData);
		CurrentlySelectedLocationWrap currentlySelectedLocationWrap = new CurrentlySelectedLocationWrap();
		currentlySelectedLocationWrap
				.setCurrentlySelectedLocation(locationsService
						.currentlySelectedLocations(userData, Id, networkId));
		return currentlySelectedLocationWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editLocation")
	public EditLocationResWrap editLocation(EditLocationReqWrap reqWrap) {
		EditLocationResWrap resWrap = new EditLocationResWrap();
		resWrap.setEditLocationRes(locationsService.editLocation(reqWrap
				.getEditLocationReq()));
		return resWrap;
	}
}
