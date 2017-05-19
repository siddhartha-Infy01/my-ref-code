package com.iexceed.esoko.dash.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.LibraryService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CreateLibraryReqWrap;
import com.iexceed.esoko.jaxb.db.CreateLibraryResWrap;
import com.iexceed.esoko.jaxb.db.EditNewsAndLibraryReqWrap;
import com.iexceed.esoko.jaxb.db.EditNewsAndLibraryResWrap;
import com.iexceed.esoko.jaxb.db.QueryLibraryResWrap;

@Path("LibraryService")
@Component
public class LibraryRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	LibraryService service;

	public LibraryRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLibrary")
	public QueryLibraryResWrap queryLibrary(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QueryLibraryResWrap resWrap = new QueryLibraryResWrap();
		resWrap.setQueryLibraryRes(service.queryLibrary(networkId, userId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createLibrary")
	public CreateLibraryResWrap createLibrary(CreateLibraryReqWrap req) {
		log.info("Inside LibraryRest --> createLibrary");
		CreateLibraryResWrap resWrap = new CreateLibraryResWrap();
		resWrap.setCreateLibraryRes(service.createLibrary(req
				.getCreateLibraryReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("editLibrary")
	public EditNewsAndLibraryResWrap editLibrary(
			EditNewsAndLibraryReqWrap reqWrap) {
		log.info("Inside LibraryRest --> editLibrary");
		EditNewsAndLibraryResWrap resWrap = new EditNewsAndLibraryResWrap();
		resWrap.setEditNewsAndLibraryRes(service.editLibrary(reqWrap
				.getEditNewsAndLibraryReq()));
		return resWrap;
	}
}
