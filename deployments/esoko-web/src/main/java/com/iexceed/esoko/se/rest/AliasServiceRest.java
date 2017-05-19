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
import com.iexceed.esoko.jaxb.se.AddAliasReqWrap;
import com.iexceed.esoko.jaxb.se.AddAliasResWrap;
import com.iexceed.esoko.se.service.AliasService;

@Component
@Path("AliasService")
public class AliasServiceRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	AliasService aliasService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createAlias")
	public AddAliasResWrap createAlias(AddAliasReqWrap reqWrap) {
		log.info("inside AliasServiceRest-->createAlias");
		AddAliasResWrap resWrap = new AddAliasResWrap();
		resWrap.setAddAliasRes(aliasService.createAlias(reqWrap
				.getAddAliasReq()));
		return resWrap;
	}
}
