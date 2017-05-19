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

import com.iexceed.esoko.jaxb.ss.CreateResellerDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.CreateResellerDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.DeleteResellerDtlsReqWrap;
import com.iexceed.esoko.jaxb.ss.DeleteResellerDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QueryAllResellerResWrap;
import com.iexceed.esoko.jaxb.ss.QueryResellerDtlsResWrap;
import com.iexceed.esoko.ss.service.ResellerDetailsService;

@Component
@Path("ResellerDetailsService")
public class ResellerDetailsRest {
	@Autowired
	ResellerDetailsService resellerService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createResellerDtls")
	public CreateResellerDtlsResWrap createResellerDtls(
			CreateResellerDtlsReqWrap reqWrap) {
		CreateResellerDtlsResWrap resWrap = new CreateResellerDtlsResWrap();
		resWrap.setCreateResellerDtlsRes(resellerService
				.createResellerDtls(reqWrap.getCreateResellerDtlsReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteResellerDtls")
	public DeleteResellerDtlsResWrap deleteResellerDtls(
			DeleteResellerDtlsReqWrap reqWrap) {
		DeleteResellerDtlsResWrap resWrap = new DeleteResellerDtlsResWrap();
		resWrap.setDeleteResellerDtlsRes(resellerService
				.deleteResellerDtls(reqWrap.getDeleteResellerDtlsReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryResellerDtls")
	public QueryResellerDtlsResWrap queryResellerDtls(
			@QueryParam("resellerId") String resellerId,
			@QueryParam("networkId") String networkId) {
		QueryResellerDtlsResWrap resWrap = new QueryResellerDtlsResWrap();
		resWrap.setQueryResellerDtlsRes(resellerService
				.queryResellerDtls(resellerId,networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllReseller")
	public QueryAllResellerResWrap queryAllReseller(
			@QueryParam("networkId") String networkId) {
		QueryAllResellerResWrap resWrap = new QueryAllResellerResWrap();
		resWrap.setQueryAllResellerRes(resellerService
				.queryAllReseller(networkId));
		return resWrap;
	}
}
