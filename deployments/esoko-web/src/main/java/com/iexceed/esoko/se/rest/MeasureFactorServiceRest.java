package com.iexceed.esoko.se.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.jaxb.se.CreateMeasureFactorReqWrap;
import com.iexceed.esoko.jaxb.se.CreateMeasureFactorResWrap;
import com.iexceed.esoko.jaxb.se.CreateMeasureResWrap;
import com.iexceed.esoko.jaxb.se.QueryMeasureCommodityResWrap;
import com.iexceed.esoko.jaxb.se.QueryMeasureFactorResWrap;
import com.iexceed.esoko.se.service.MeasureFactorService;

@Path("MeasureFactorService")
@Component
public class MeasureFactorServiceRest {

	public MeasureFactorServiceRest() {
	}

	@Autowired
	private MeasureFactorService measureService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMeasureFactors")
	public QueryMeasureFactorResWrap queryMeasureFactors(
			@QueryParam("networkId") String networkId,
			@QueryParam("locationId") String locationId,
			@QueryParam("commodityId") String commodityId,
			@QueryParam("priceType") String priceType,
			@QueryParam("userId") String userId,@QueryParam("criteriaId") String criteriaId) {
		QueryMeasureFactorResWrap qryMeasureRes = new QueryMeasureFactorResWrap();
		qryMeasureRes.setQueryMeasureFactorRes(measureService
				.queryMeasureFactor(networkId, locationId, commodityId,
						priceType, userId,criteriaId));
		return qryMeasureRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMeasureFactorForCommodity")
	public QueryMeasureFactorResWrap queryMeasureFactorForCommodity(
			@QueryParam("networkId") String networkId,
			@QueryParam("locationId") String locationId,
			@QueryParam("commodityId") String commodityId,
			@QueryParam("userId") String userId) {
		QueryMeasureFactorResWrap qryMeasureRes = new QueryMeasureFactorResWrap();
		qryMeasureRes.setQueryMeasureFactorRes(measureService
				.queryMeasureForCommodity(networkId, locationId, commodityId,
						userId));
		return qryMeasureRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCommodityMeasureFactors")
	public QueryMeasureCommodityResWrap queryCommodityMeasureFactors(

	@QueryParam("locationId") String locationId,
			@QueryParam("commodityId") String commodityId) {
		QueryMeasureCommodityResWrap qryMeasureRes = new QueryMeasureCommodityResWrap();
		qryMeasureRes.setQueryMeasureCommodityRes(measureService
				.queryAllCommodityMeasure(locationId, commodityId));
		return qryMeasureRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createMeasureFactor")
	public CreateMeasureFactorResWrap createMeasureFactors(
			CreateMeasureFactorReqWrap req) {

		CreateMeasureFactorResWrap measureResWrap = new CreateMeasureFactorResWrap();
		measureResWrap.setCreateMeasureFactorRes(measureService
				.createMeasureFactor(req.getCreateMeasureFactorReq()));
		return measureResWrap;
	}

}
