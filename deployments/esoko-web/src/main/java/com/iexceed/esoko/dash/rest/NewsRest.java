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

import com.iexceed.esoko.db.service.NewsService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CreateNewsReqWrap;
import com.iexceed.esoko.jaxb.db.CreateNewsResWrap;
import com.iexceed.esoko.jaxb.db.QueryNewsResWrap;

/*
 * @author Ankur
 */
@Path("NewsService")
@Component
public class NewsRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	private NewsService newsService;

	public NewsRest() {

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNews")
	public QueryNewsResWrap queryNews(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside QueryNewsResWrap -> queryNews");
		QueryNewsResWrap queryNews = new QueryNewsResWrap();
		queryNews.setQueryNewsRes(newsService.queryNews(networkId, userId));
		return queryNews;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createNews")
	public CreateNewsResWrap createLibrary(CreateNewsReqWrap req) {
		CreateNewsResWrap resWrap = new CreateNewsResWrap();
		resWrap.setCreateNewsRes(newsService.createNews(req
				.getCreateNewsReq()));
		return resWrap;
	}
}
