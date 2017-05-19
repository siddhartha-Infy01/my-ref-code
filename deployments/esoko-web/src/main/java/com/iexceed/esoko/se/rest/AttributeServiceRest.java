package com.iexceed.esoko.se.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.AddAttributeReqWrap;
import com.iexceed.esoko.jaxb.se.AddAttributeResWrap;
import com.iexceed.esoko.se.service.AttributeService;

@Component
@Path("AttributeService")
public class AttributeServiceRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	AttributeService attributeService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createAttribute")
	public AddAttributeResWrap createAttribute(AddAttributeReqWrap reqWrap) {
		log.info("inside AttributeServiceRest-->createAttribute");
		AddAttributeResWrap resWrap = new AddAttributeResWrap();
		resWrap.setAddAttributeRes(attributeService.createAttribute(reqWrap
				.getAddAttributeReq()));
		return resWrap;
	}
}
