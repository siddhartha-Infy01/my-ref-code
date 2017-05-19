package com.iexceed.esoko.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.DashBoardUsageService;
import com.iexceed.esoko.jaxb.db.QueryPollStatsResWrap;
import com.iexceed.esoko.jaxb.db.QuerySmsTrafficResWrap;
import com.iexceed.esoko.jaxb.db.QuerySurveyStatsResWrap;
import com.iexceed.esoko.jaxb.db.QueryUploadStatsRes;
import com.iexceed.esoko.jaxb.db.QueryUploadStatsResWrap;

@Component
@Path("DashBoardUsageService")
public class DashBoardUsageRest {
	@Autowired
	DashBoardUsageService usageService;

	public DashBoardUsageRest() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySmsTraffic")
	public QuerySmsTrafficResWrap querySmsTraffic(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QuerySmsTrafficResWrap resWrap = new QuerySmsTrafficResWrap();
		resWrap.setQuerySmsTrafficRes(usageService.querySmsTraffic(networkId,
				userId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPollStats")
	public QueryPollStatsResWrap queryPollStats(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QueryPollStatsResWrap resWrap = new QueryPollStatsResWrap();
		resWrap.setQueryPollStatsRes(usageService.queryPollStats(networkId,
				userId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySurveyStats")
	public QuerySurveyStatsResWrap querySurveyStats(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QuerySurveyStatsResWrap resWrap = new QuerySurveyStatsResWrap();
		resWrap.setQuerySurveyStatsRes(usageService.querySurveyStats(networkId,
				userId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUploadStats")
	public QueryUploadStatsResWrap queryUploadStats(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QueryUploadStatsResWrap resWrap = new QueryUploadStatsResWrap();
		resWrap.setQueryUploadStatsRes(usageService.queryUploadStats(networkId,
				userId));
		return resWrap;
	}
}
