package com.iexceed.esoko.dash.rest;

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

import com.iexceed.esoko.db.service.OffersService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CreateOffersReqWrap;
import com.iexceed.esoko.jaxb.db.CreateOffersResWrap;
import com.iexceed.esoko.jaxb.db.QueryOffersResWrap;

/*
 * @author Ankur
 */
@Path("OffersService")
@Component
public class OffersRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	OffersService offerService;

	public OffersRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryOffers")
	public QueryOffersResWrap queryOffers(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside QueryOffersResWrap -> queryOffers");
		QueryOffersResWrap offersRes = new QueryOffersResWrap();
		offersRes
				.setQueryOffersRes(offerService.queryOffers(networkId, userId));
		return offersRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createOffers")
	public CreateOffersResWrap createOffers(CreateOffersReqWrap req) {
		log.info("Inside CreateOffersResWrap -> createOffers");
		CreateOffersResWrap offersRes = new CreateOffersResWrap();
		offersRes.setCreateOffersRes(offerService.createOffers(req
				.getCreateOffersReq()));
		return offersRes;
	}

}
