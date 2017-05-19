package com.iexceed.esoko.se.rest;

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

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.CreatelanguagesReqWrap;
import com.iexceed.esoko.jaxb.se.CreatelanguagesResWrap;
import com.iexceed.esoko.jaxb.se.DeletelanguagesReqWrap;
import com.iexceed.esoko.jaxb.se.DeletelanguagesResWrap;
import com.iexceed.esoko.jaxb.se.QueryAllLanguagesWrap;
import com.iexceed.esoko.jaxb.se.QuerylanguagesResWrap;
//import com.iexceed.esoko.jaxb.se.crtlang.CreatelanguagesReq;
//import com.iexceed.esoko.jaxb.se.crtlang.CreatelanguagesRes;
//import com.iexceed.esoko.jaxb.se.dellang.DeletelanguagesReq;
//import com.iexceed.esoko.jaxb.se.dellang.DeletelanguagesRes;
//import com.iexceed.esoko.jaxb.se.qrylang.QuerylanguagesRes;
import com.iexceed.esoko.se.service.LaguagesService;

@Path("LaguagesService")
@Component
public class LaguagesServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public LaguagesServiceRest() {
	}

	@Autowired
	private LaguagesService laguagesService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createlanguages")
	public CreatelanguagesResWrap createlanguages(CreatelanguagesReqWrap req) {
		CreatelanguagesResWrap crtLangResWrap = new CreatelanguagesResWrap();
		crtLangResWrap.setCreatelanguagesRes(laguagesService
				.createlanguages(req.getCreatelanguagesReq()));
		return crtLangResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLaguages")
	public QuerylanguagesResWrap queryLaguages(
			@QueryParam("languageCode") String languageCode,
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId,
			@QueryParam("userData") String userData) {
		QuerylanguagesResWrap qryLangResWrap = new QuerylanguagesResWrap();
		qryLangResWrap.setQuerylanguagesRes(laguagesService.queryLaguages(
				languageCode, networkId, userId, userData));
		return qryLangResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deletelanguages")
	public DeletelanguagesResWrap deletelanguages(DeletelanguagesReqWrap req) {
		DeletelanguagesResWrap delLangResWrap = new DeletelanguagesResWrap();
		delLangResWrap.setDeletelanguagesRes(laguagesService
				.deletelanguages(req.getDeletelanguagesReq()));
		return delLangResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllLanguages")
	public QueryAllLanguagesWrap queryAllCurrencies() {
		QueryAllLanguagesWrap curResWrap = new QueryAllLanguagesWrap();
		curResWrap.setQueryAllLanguages(laguagesService.queryAllLanguages());
		return curResWrap;
	}
}
