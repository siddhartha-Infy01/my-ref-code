package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.LatestPriceService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.QueryLatestPriceResWrap;

/*
 * @author Ankur
 */
@Path("LatestPriceService")
@Component
public class LatestPriceRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	LatestPriceService latestPrice;

	public LatestPriceRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLatestPrice")
	public QueryLatestPriceResWrap queryLatestPrice(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside QueryLatestPriceResWrap -> queryLatestPrice");
		QueryLatestPriceResWrap qLtPrice = new QueryLatestPriceResWrap();
		qLtPrice.setQueryLatestPriceRes(latestPrice.queryLatestPrice(networkId,
				userId));
		return qLtPrice;
	}
}
