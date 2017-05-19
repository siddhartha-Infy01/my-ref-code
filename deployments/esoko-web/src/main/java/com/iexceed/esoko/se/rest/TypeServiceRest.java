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
import com.iexceed.esoko.jaxb.se.AddTypeReqWrap;
import com.iexceed.esoko.jaxb.se.AddTypeResWrap;
import com.iexceed.esoko.se.service.TypeService;

@Component
@Path("TypeService")
public class TypeServiceRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	TypeService typeService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createTypes")
	public AddTypeResWrap createTypes(AddTypeReqWrap req) {
		log.info("inside TypeServiceRest-->createTypes");
		AddTypeResWrap resWrap = new AddTypeResWrap();
		resWrap.setAddTypeRes(typeService.createTypes(req.getAddTypeReq()));
		return resWrap;
	}

}
