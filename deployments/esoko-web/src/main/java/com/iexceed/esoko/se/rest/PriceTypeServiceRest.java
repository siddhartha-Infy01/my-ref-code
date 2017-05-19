package com.iexceed.esoko.se.rest;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.CreatepriceTypeReqWrap;
import com.iexceed.esoko.jaxb.se.CreatepriceTypeResWrap;
import com.iexceed.esoko.jaxb.se.DeletepriceTypeReqWrap;
import com.iexceed.esoko.jaxb.se.DeletepriceTypeResWrap;
import com.iexceed.esoko.jaxb.se.QuerypriceTypeResWrap;
//import com.iexceed.esoko.jaxb.se.crtpricetype.CreatepriceTypeReq;
//import com.iexceed.esoko.jaxb.se.crtpricetype.CreatepriceTypeRes;
//import com.iexceed.esoko.jaxb.se.delpricetype.DeletepriceTypeReq;
//import com.iexceed.esoko.jaxb.se.delpricetype.DeletepriceTypeRes;
//import com.iexceed.esoko.jaxb.se.qrypricetype.QuerypriceTypeRes;
import com.iexceed.esoko.se.service.PriceTypeService;

@Path("PriceTypeService")
@Component
public class PriceTypeServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public PriceTypeServiceRest() {
	}

	@Autowired
	private PriceTypeService PriceTypeService;

	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createpriceType")
	public CreatepriceTypeResWrap createpriceType(CreatepriceTypeReqWrap req) {
		CreatepriceTypeResWrap crtPriceResWrap = new CreatepriceTypeResWrap();
		crtPriceResWrap.setCreatepriceTypeRes(PriceTypeService
				.createpriceType(req.getCreatepriceTypeReq()));
		return crtPriceResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querypriceType")
	public QuerypriceTypeResWrap querypriceType(
			@QueryParam("priceType") String priceType,
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("userData") String userData) {
		QuerypriceTypeResWrap qryPriceResWrap = new QuerypriceTypeResWrap();
		qryPriceResWrap.setQuerypriceTypeRes(PriceTypeService.querypriceType(
				priceType, networkId, userId, userData));
		return qryPriceResWrap;
	}

	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deletepriceType")
	public DeletepriceTypeResWrap deletepriceType(DeletepriceTypeReqWrap req) {
		DeletepriceTypeResWrap delPriceResWrap = new DeletepriceTypeResWrap();
		delPriceResWrap.setDeletepriceTypeRes(PriceTypeService
				.deletepriceType(req.getDeletepriceTypeReq()));
		return delPriceResWrap;
	}

}
