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
import com.iexceed.esoko.jaxb.se.ApproveCommoditesReqWrap;
import com.iexceed.esoko.jaxb.se.ApproveCommoditesResWrap;
import com.iexceed.esoko.jaxb.se.CreateCommoditiesReqWrap;
import com.iexceed.esoko.jaxb.se.CreateCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedCommoditiesWrap;
import com.iexceed.esoko.jaxb.se.DeleteCommoditiesReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteCommoditiesRes;
import com.iexceed.esoko.jaxb.se.MapCommoditiesReqWrap;
import com.iexceed.esoko.jaxb.se.MapCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.QueryAllUnAuthCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.QueryCommdsByNwkIdCrtIdResWrap;
import com.iexceed.esoko.jaxb.se.QueryCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.QuerySysCommoditiesResWrap;
import com.iexceed.esoko.jaxb.se.QueryTypeandAttribsResWrap;
import com.iexceed.esoko.jaxb.se.QueryVarietiesResWrap;
import com.iexceed.esoko.se.service.CommoditiesService;
import com.iexceed.esoko.se.service.SysCommoditiesService;

@Path("CommoditiesService")
@Component
public class CommoditiesServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public CommoditiesServiceRest() {
	}

	@Autowired
	private CommoditiesService commoditiesService;

	@Autowired
	private SysCommoditiesService sysCommoditiesService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCommodities")
	public QueryCommoditiesResWrap queryCommodities(
			@QueryParam("networkId") String pNetworkId,
			@QueryParam("commodityId") String pCommodityId,
			@QueryParam("userId") String pUserId,
			@QueryParam("widgetId") String pWidgetId,
			@QueryParam("userData") String pUserData) {
		QueryCommoditiesResWrap resWrap = new QueryCommoditiesResWrap();

		resWrap.setQueryCommoditiesRes(commoditiesService.queryCommodityities(
				pNetworkId, pUserId, pWidgetId, pUserData, pCommodityId));

		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllCommodities")
	public QueryCommoditiesResWrap queryAllCommodities(
			@QueryParam("networkId") String pNetworkId,
			@QueryParam("commodityId") String pCommodityId,
			@QueryParam("userId") String pUserId,
			@QueryParam("widgetId") String pWidgetId,
			@QueryParam("userData") String pUserData) {
		QueryCommoditiesResWrap resWrap = new QueryCommoditiesResWrap();

		resWrap.setQueryCommoditiesRes(commoditiesService.queryAllCommodities(
				pNetworkId, pUserId, pWidgetId, pUserData));

		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllSysCommodities")
	public QuerySysCommoditiesResWrap queryAllSysCommodities(
			@QueryParam("parentId") String pparentId,
			@QueryParam("type") String ptype) {
		QuerySysCommoditiesResWrap resWrap = new QuerySysCommoditiesResWrap();
		resWrap.setQuerySysCommoditiesRes(sysCommoditiesService
				.querySystemCommodity(pparentId, ptype));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryTypesandAttribs")
	public QueryTypeandAttribsResWrap queryAllTypesAndAttributes() {
		QueryTypeandAttribsResWrap resWrap = new QueryTypeandAttribsResWrap();
		resWrap.setQueryTypeandAttribsRes(commoditiesService
				.queryTypeandAttribs());
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryVarieties")
	public QueryVarietiesResWrap queryVarieties(
			@QueryParam("commodityID") String commodityID,
			@QueryParam("qTypeId") int qTypeId,
			@QueryParam("attribId") int attribId) {
		QueryVarietiesResWrap resWrap = new QueryVarietiesResWrap();
		resWrap.setQueryVarietiesRes(commoditiesService.queryVarieties(
				commodityID, qTypeId, attribId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createCommodities")
	public CreateCommoditiesResWrap createCommodities(
			CreateCommoditiesReqWrap req) {
		CreateCommoditiesResWrap commditiesResWrap = new CreateCommoditiesResWrap();
		commditiesResWrap.setCreateCommoditiesRes(sysCommoditiesService
				.createCommodities(req.getCreateCommoditiesReq()));
		return commditiesResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteCommodities")
	public DeleteCommoditiesRes deleteCommodities(DeleteCommoditiesReqWrap req) {

		return commoditiesService.deleteCommodities(req
				.getDeleteCommoditiesReq());
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("approveCommodites")
	public ApproveCommoditesResWrap approveCommodites(
			ApproveCommoditesReqWrap req) {
		ApproveCommoditesResWrap approveResWrap = new ApproveCommoditesResWrap();
		approveResWrap.setApproveCommoditesRes(commoditiesService
				.approveCommodites(req.getApproveCommoditesReq()));
		return approveResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("mapCommodites")
	public MapCommoditiesResWrap mapCommodites(MapCommoditiesReqWrap req) {
		MapCommoditiesResWrap mapResWrap = new MapCommoditiesResWrap();
		mapResWrap.setMapCommoditiesRes(commoditiesService.mapCommodites(req
				.getMapCommoditiesReq()));
		return mapResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("currentlySelectedCommodities")
	public CurrentlySelectedCommoditiesWrap currentlySelectedCommodities(
			@QueryParam("userData") String userData,
			@QueryParam("Id") String userId,
			@QueryParam("networkId") String networkId) {
		log.debug("User Data::::" + userData);
		log.debug("Id::::" + userId);
		CurrentlySelectedCommoditiesWrap currentlySelectedCommoditiesWrap = new CurrentlySelectedCommoditiesWrap();
		currentlySelectedCommoditiesWrap
				.setCurrentlySelectedCommodities(commoditiesService
						.currentlySelectedCommodities(userData, userId,
								networkId));
		return currentlySelectedCommoditiesWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("findAllUnAuthCommodities")
	public QueryAllUnAuthCommoditiesResWrap findAllUnAuthCommodities() {
		QueryAllUnAuthCommoditiesResWrap resWrap = new QueryAllUnAuthCommoditiesResWrap();
		resWrap.setQueryAllUnAuthCommoditiesRes(commoditiesService
				.findAllUnAuthCommodities());
		return resWrap;
	}

	
}
