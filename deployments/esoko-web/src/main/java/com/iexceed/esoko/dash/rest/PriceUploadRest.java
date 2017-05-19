package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.PriceUploadService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CreatePricesReqWrap;
import com.iexceed.esoko.jaxb.db.CreatePricesResWrap;
import com.iexceed.esoko.jaxb.db.QueryChildCommoditiesResWrap;
import com.iexceed.esoko.jaxb.db.QueryCommoditiesResWrap;
import com.iexceed.esoko.jaxb.db.QueryMarketsResWrap;

@Path("PriceService")
@Component
public class PriceUploadRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	PriceUploadService priceService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMarkets")
	public QueryMarketsResWrap queryMarkets(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside PriceRest -> queryMarkets");
		QueryMarketsResWrap marketRes = new QueryMarketsResWrap();
		marketRes.setQueryMarketsRes(priceService.querymarkets(networkId,
				userId));
		return marketRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCommodities")
	public QueryCommoditiesResWrap queryCommodities(
			@QueryParam("detailId") String detailId) {
		log.info("Inside PriceRest -> queryCommodities");
		QueryCommoditiesResWrap queryCommoditiesResWrap = new QueryCommoditiesResWrap();
		queryCommoditiesResWrap.setQueryCommoditiesRes(priceService
				.queryCommodities(detailId));
		return queryCommoditiesResWrap;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryChildCommodities")
	public QueryChildCommoditiesResWrap queryChildCommodities(
			@QueryParam("detailId") String detailId) {
		log.info("Inside PriceRest -> queryChildCommodities");
		QueryChildCommoditiesResWrap queryChildCommodResWrap = new QueryChildCommoditiesResWrap();
		queryChildCommodResWrap.setQueryChildCommoditiesRes(priceService
				.queryChildCommodities(detailId));
		return queryChildCommodResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createPrices")
	public CreatePricesResWrap createPrices(
			CreatePricesReqWrap createPricesReqWrap) {
		log.info("Inside PriceRest -> createPrices");
		CreatePricesResWrap createPricesResWrap = new CreatePricesResWrap();
		createPricesResWrap.setCreatePricesRes(priceService
				.createPrices(createPricesReqWrap.getCreatePricesReq()));
		return createPricesResWrap;
	}
}
