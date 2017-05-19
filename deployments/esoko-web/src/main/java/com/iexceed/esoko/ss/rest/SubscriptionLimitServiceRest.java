package com.iexceed.esoko.ss.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.jaxb.ss.AddSubLimitsDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.AddSubLimitsDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSubLimitsDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSubLimitsDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QueryNetworkLimitResWrap;
import com.iexceed.esoko.jaxb.ss.QuerySubLimitsDtlsResWrap;
import com.iexceed.esoko.ss.service.SubscriptionLimitService;

@Component
@Path("SubscriptionLimitService")
public class SubscriptionLimitServiceRest {
	@Autowired
	SubscriptionLimitService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySubLimitDtls")
	public QuerySubLimitsDtlsResWrap querySubPricingDtlsRes() {
		QuerySubLimitsDtlsResWrap resWrap = new QuerySubLimitsDtlsResWrap();
		resWrap.setQuerySubLimitsDtlsRes(service.querySubLimitDtlsRes());
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createSubLimitDtls")
	public AddSubLimitsDtlsResWrap createSubPricingDtlsRes(
			AddSubLimitsDtlsReqWrap req) {
		AddSubLimitsDtlsResWrap resWrap = new AddSubLimitsDtlsResWrap();
		resWrap.setAddSubLimitsDtlsRes(service.createSubLimitDtlsRes(req
				.getAddSubLimitsDtlsReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteSubLimitsDtls")
	public DeleteSubLimitsDtlsResWrap deleteSubLimitsDtls(
			DeleteSubLimitsDtlsReqWrap req) {
		DeleteSubLimitsDtlsResWrap resWrap = new DeleteSubLimitsDtlsResWrap();
		resWrap.setDeleteSubLimitsDtlsRes(service.deleteSubAllLimits(req
				.getDeleteSubLimitsDtlsReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNetworkLimit")
	public QueryNetworkLimitResWrap queryNetworkLimit(
			@QueryParam("type") String type,
			@QueryParam("countryName") String countryName,
			@QueryParam("paramName") String paramName) {
		QueryNetworkLimitResWrap resWarp = new QueryNetworkLimitResWrap();
		resWarp.setQueryNetworkLimitRes(service.queryNetworkLimits(type,
				countryName, paramName));
		return resWarp;
	}
}
