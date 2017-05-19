package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.CountsService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.QueryVariousCountsResWrap;

/*
 * @author Ankur
 */
@Path("CountsService")
@Component
public class CountsRest {

	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	CountsService cntServie;

	public CountsRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryVariousCounts")
	public QueryVariousCountsResWrap queryVariousCounts(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryVariousCountsResWrap -> queryVariousCounts");
		QueryVariousCountsResWrap countRes = new QueryVariousCountsResWrap();
		countRes.setQueryVariousCountsRes(cntServie.queryVariousCounts(userId));
		return countRes;
	}
}
