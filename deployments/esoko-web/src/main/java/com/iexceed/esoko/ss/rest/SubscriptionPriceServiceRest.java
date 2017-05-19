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

import com.iexceed.esoko.jaxb.ss.AddSubPricesDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.AddSubPricesDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSubPricesDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.DeleteSubPricesDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QuerySubPricingDtlsResWrap;
import com.iexceed.esoko.ss.service.SubscriptionPriceService;

@Component
@Path("SubscriptionPriceService")
public class SubscriptionPriceServiceRest {
	@Autowired
	SubscriptionPriceService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySubPricingDtls")
	public QuerySubPricingDtlsResWrap querySubPricingDtlsRes(
			@QueryParam("headerType") String headerType,
			@QueryParam("networkId") String networkId) {
		QuerySubPricingDtlsResWrap resWrap = new QuerySubPricingDtlsResWrap();
		resWrap.setQuerySubPricingDtlsRes(service.querySubPricingDtlsRes(
				headerType, networkId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createSubPricingDtls")
	public AddSubPricesDtlsResWrap createSubPricingDtlsRes(
			AddSubPricesDtlsReqWrap req) {
		AddSubPricesDtlsResWrap resWrap = new AddSubPricesDtlsResWrap();
		resWrap.setAddSubPricesDtlsRes(service.createSubPricingDtlsRes(req
				.getAddSubPricesDtlsReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteSubPricingDtls")
	public DeleteSubPricesDtlsResWrap deleteSubPricingDtls(
			DeleteSubPricesDtlsReqWrap req) {
		DeleteSubPricesDtlsResWrap resWrap = new DeleteSubPricesDtlsResWrap();
		resWrap.setDeleteSubPricesDtlsRes(service.deleteSubAllPrices(req
				.getDeleteSubPricesDtlsReq()));
		return resWrap;
	}
}
