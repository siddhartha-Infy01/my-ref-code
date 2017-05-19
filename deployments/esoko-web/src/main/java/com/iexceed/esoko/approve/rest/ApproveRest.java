package com.iexceed.esoko.approve.rest;

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

import com.iexceed.esoko.approval.service.ApprovalService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.approval.BidsAndOffersApprovalReqWrap;
import com.iexceed.esoko.jaxb.approval.BidsAndOffersApprovalResWrap;
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibReqWrap;
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibResWrap;
import com.iexceed.esoko.jaxb.approval.EditPriceReqWrap;
import com.iexceed.esoko.jaxb.approval.EditPriceResWrap;
import com.iexceed.esoko.jaxb.approval.LibraryApprovalReqWrap;
import com.iexceed.esoko.jaxb.approval.LibraryApprovalResWrap;
import com.iexceed.esoko.jaxb.approval.LocationApprovalReqWrap;
import com.iexceed.esoko.jaxb.approval.LocationApprovalResWrap;
import com.iexceed.esoko.jaxb.approval.PriceApprovalReqWrap;
import com.iexceed.esoko.jaxb.approval.PriceApprovalResWrap;
import com.iexceed.esoko.jaxb.approval.QueryBidsOfferApprvlResWrap;
import com.iexceed.esoko.jaxb.approval.QueryLocationsApprvlResWrap;
import com.iexceed.esoko.jaxb.approval.QueryNewsAndLibApprvlResWrap;
import com.iexceed.esoko.jaxb.approval.QueryNewsLibByIdResWrap;
import com.iexceed.esoko.jaxb.approval.QueryPeopleApprvlResWrap;
import com.iexceed.esoko.jaxb.approval.QueryPriceApprvlResWrap;
import com.iexceed.esoko.jaxb.approval.RecordStsApprovalReqWrap;
import com.iexceed.esoko.jaxb.approval.RecordStsApprovalResWrap;

@Component
@Path("ApprovalService")
public class ApproveRest {

	@Autowired
	ApprovalService service;
	private static Logger log = LoggerUtils.getLogger();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNewsAndLibraryApprvl")
	public QueryNewsAndLibApprvlResWrap queryNewsAndLibraryApprvl(
			@QueryParam("networkId") String networkId,
			@QueryParam("SortBy") String SortBy) {
		QueryNewsAndLibApprvlResWrap resWrap = new QueryNewsAndLibApprvlResWrap();
		resWrap.setQueryNewsAndLibApprvlRes(service.queryNewsAndLibraryApprvl(
				networkId, SortBy));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNewsLibById")
	public QueryNewsLibByIdResWrap queryNewsLibById(
			@QueryParam("libId") String libId) {
		QueryNewsLibByIdResWrap resWrap = new QueryNewsLibByIdResWrap();
		resWrap.setQueryNewsLibByIdRes(service.queryNewsLibById(libId));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("deleteUpload")
	public DeleteNewsLibResWrap deleteNewsLib(DeleteNewsLibReqWrap req) {
		log.info("Inside DeleteNewsLibResWrap -> modifyAgentsReq");
		DeleteNewsLibResWrap reswrap = new DeleteNewsLibResWrap();
		reswrap.setDeleteNewsLibRes(service.deletenewslib(req
				.getDeleteNewsLibReq()));
		return reswrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querybidsNwsapprv")
	public QueryBidsOfferApprvlResWrap querybidsNwsapprv(
			@QueryParam("networkId") String networkId,
			@QueryParam("sortBy") String sortBy) {
		QueryBidsOfferApprvlResWrap resWrap = new QueryBidsOfferApprvlResWrap();
		resWrap.setQueryBidsOfferApprvlRes(service.querybidsNwsapprv(networkId,
				sortBy));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPricesApprvl")
	public QueryPriceApprvlResWrap queryPricesApprvl(
			@QueryParam("networkId") String networkId,
			@QueryParam("sortBy") String sortBy) {
		QueryPriceApprvlResWrap resWrap = new QueryPriceApprvlResWrap();
		resWrap.setQueryPriceApprvlRes(service.queryPricesApprvl(networkId,
				sortBy));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryLocationsApprv")
	public QueryLocationsApprvlResWrap queryLocationsApprv(
			@QueryParam("sortBy") String sortBy) {
		QueryLocationsApprvlResWrap resWrap = new QueryLocationsApprvlResWrap();
		resWrap.setQueryLocationsApprvlRes(service.queryLocationsApprv(sortBy));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("approveLocation")
	public LocationApprovalResWrap approveLocation(LocationApprovalReqWrap req) {
		log.info("Inside LocationApprovalResWrap -> approveLocation");
		LocationApprovalResWrap reswrap = new LocationApprovalResWrap();
		reswrap.setLocationApprovalRes(service.locationApproval(req
				.getLocationApprovalReq()));
		return reswrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPplApproval")
	public QueryPeopleApprvlResWrap queryPplApproval(
			@QueryParam("networkId") String networkId,@QueryParam("sortBy") String sortBy) {
		log.info("Inside ApproveRest -> queryPplApproval");
		QueryPeopleApprvlResWrap resWrap = new QueryPeopleApprvlResWrap();
		resWrap.setQueryPeopleApprvlRes(service.queryPplApproval(networkId,sortBy));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("pplApproval")
	public RecordStsApprovalResWrap pplApproval(RecordStsApprovalReqWrap reqWrap) {
		log.info("Inside ApproveRest -> pplApproval");
		RecordStsApprovalResWrap resWrap = new RecordStsApprovalResWrap();
		resWrap.setRecordStsApprovalRes(service.pplApproval(reqWrap
				.getRecordStsApprovalReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("libraryApproval")
	public LibraryApprovalResWrap libraryApproval(LibraryApprovalReqWrap reqWrap) {
		log.info("Inside ApproveRest -> libraryApproval");
		LibraryApprovalResWrap resWrap = new LibraryApprovalResWrap();
		resWrap.setLibraryApprovalRes(service.libraryApproval(reqWrap
				.getLibraryApprovalReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("bidsAndOffersApproval")
	public BidsAndOffersApprovalResWrap bidsAndOffersApproval(
			BidsAndOffersApprovalReqWrap reqWrap) {
		log.info("Inside ApproveRest -> bidsAndOffersApproval");
		BidsAndOffersApprovalResWrap resWrap = new BidsAndOffersApprovalResWrap();
		resWrap.setBidsAndOffersApprovalRes(service
				.bidsAndOffersApproval(reqWrap.getBidsAndOffersApprovalReq()));
		return resWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editPrice")
	public EditPriceResWrap editPrice(EditPriceReqWrap reqWrap) {
		log.info("Inside ApproveRest -> editPrice");
		EditPriceResWrap resWrap = new EditPriceResWrap();
		resWrap.setEditPriceRes(service.editPrice(reqWrap.getEditPriceReq()));
		return resWrap;
	}
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("priceApproval")
	public PriceApprovalResWrap priceApproval(PriceApprovalReqWrap reqWrap) {
		log.info("Inside ApproveRest -> priceApproval");
		PriceApprovalResWrap resWrap = new PriceApprovalResWrap();
		resWrap.setPriceApprovalRes(service.priceApproval(reqWrap.getPriceApprovalReq()));
		return resWrap;
	}
}
