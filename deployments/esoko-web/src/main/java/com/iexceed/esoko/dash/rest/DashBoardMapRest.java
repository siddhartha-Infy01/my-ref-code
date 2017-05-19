package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iexceed.esoko.db.service.MapService;
import com.iexceed.esoko.jaxb.db.QueryMapCoordinatesResWrap;

@Path("MapService")
@Controller
public class DashBoardMapRest {

	@Autowired
	MapService serivce;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMapCoordinates")
	public QueryMapCoordinatesResWrap queryMapCoordinates(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QueryMapCoordinatesResWrap reswarp = new QueryMapCoordinatesResWrap();
		reswarp.setQueryMapCoordinatesRes(serivce.queryMapCoordinates(
				networkId, userId));
		return reswarp;
	}
}
