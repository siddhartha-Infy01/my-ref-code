package com.iexceed.esoko.se.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.jaxb.se.CreateMeasureFactorReqWrap;
import com.iexceed.esoko.jaxb.se.CreateMeasureFactorResWrap;
import com.iexceed.esoko.jaxb.se.CreateMeasureReqWrap;
import com.iexceed.esoko.jaxb.se.CreateMeasureResWrap;
import com.iexceed.esoko.jaxb.se.DeleteMeasureFactorReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteMeasureFactorResWrap;
import com.iexceed.esoko.jaxb.se.DeleteMeasureReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteMeasureResWrap;
import com.iexceed.esoko.jaxb.se.QueryMeasureFactorResWrap;
import com.iexceed.esoko.jaxb.se.QueryMeasureResWrap;
import com.iexceed.esoko.se.service.MeasureService;

@Path("MeasureService")
@Component
public class MeasureServiceRest {

	public MeasureServiceRest() {
	}

	@Autowired
	private MeasureService measureService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryMeasure")
	public QueryMeasureResWrap queryMeasure(
			@QueryParam("measureId") String measureId,
			@QueryParam("type") String type,
			@QueryParam("isSystem") String isSystem,
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("userData") String userData) {
		QueryMeasureResWrap qryMeasureRes = new QueryMeasureResWrap();
		qryMeasureRes.setQueryMeasureRes(measureService.queryMeasure(measureId,
				type, isSystem, networkId, userId, userData));
		return qryMeasureRes;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createMeasure")
	public CreateMeasureResWrap createMeasure(CreateMeasureReqWrap req) {
		CreateMeasureResWrap measureResWrap = new CreateMeasureResWrap();
		measureResWrap.setCreateMeasureRes(measureService.createMeasure(req
				.getCreateMeasureReq()));
		return measureResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteMeasure")
	public DeleteMeasureResWrap deleteMeasure(DeleteMeasureReqWrap req) {
		DeleteMeasureResWrap delMsrResWrap = new DeleteMeasureResWrap();
		delMsrResWrap.setDeleteMeasureRes(measureService.deleteMeasure(req
				.getDeleteMeasureReq()));
		return delMsrResWrap;
	}

}
