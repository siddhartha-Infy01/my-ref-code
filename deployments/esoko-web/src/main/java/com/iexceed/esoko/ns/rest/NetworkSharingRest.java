package com.iexceed.esoko.ns.rest;

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
import com.iexceed.esoko.jaxb.ns.ApproveSharingRequest;
import com.iexceed.esoko.jaxb.ns.ApproveSharingRequestResWrap;
import com.iexceed.esoko.jaxb.ns.ApproveSharingRequestWrap;
import com.iexceed.esoko.jaxb.ns.CreateShareRequestResWrap;
import com.iexceed.esoko.jaxb.ns.CreateShareRequestWrap;
import com.iexceed.esoko.jaxb.ns.EditProfileShareReqWrap;
import com.iexceed.esoko.jaxb.ns.EditProfileShareResWrap;
import com.iexceed.esoko.jaxb.ns.ModifyShareDtlsReqWrap;
import com.iexceed.esoko.jaxb.ns.ModifyShareDtlsResWrap;
import com.iexceed.esoko.jaxb.ns.QueryAllNwksResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkCountDtlsResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkSharingDataResWrap;
import com.iexceed.esoko.jaxb.ns.QueryPeopleProfilesResWrap;
import com.iexceed.esoko.jaxb.ns.QueryProfileDetailsResWrap;
import com.iexceed.esoko.jaxb.ns.QuerySharingProfileResWrap;
import com.iexceed.esoko.jaxb.ns.SharingRequestsDataResWrap;
import com.iexceed.esoko.jaxb.ns.StopSharingReqWrap;
import com.iexceed.esoko.jaxb.ns.StopSharingResWrap;
import com.iexceed.esoko.ne.service.NetworkSharingService;

/*
 * @author Sagar
 */
@Path("NetworkSharingSevice")
@Component
public class NetworkSharingRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkSharingRest() {

	}

	@Autowired
	public NetworkSharingService nwSharingService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createShareRequest")
	public CreateShareRequestResWrap createShareRequest(
			CreateShareRequestWrap req) {
		log.info("Inside CreateShareRequestResWrap -> createShareRequest");
		CreateShareRequestResWrap sharingResWrap = new CreateShareRequestResWrap();
		sharingResWrap.setCreateShareRequestRes(nwSharingService
				.createShareRes(req.getCreateShareRequest()));
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkSharingData")
	public QueryNwkSharingDataResWrap queryNwkSharingData(
			@QueryParam("networkId") String networkId,
			@QueryParam("shareId") String shareId) {
		log.info("Inside QueryNwkSharingDataResWrap -> queryNwkSharingData");
		QueryNwkSharingDataResWrap dataResWrap = new QueryNwkSharingDataResWrap();
		dataResWrap.setQueryNwkSharingDataRes(nwSharingService
				.queryNwkSharingData(networkId));
		return dataResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("stopSharingReq")
	public StopSharingResWrap stopSharing(StopSharingReqWrap req) {
		log.info("Inside StopSharingResWrap -> stopSharing");
		StopSharingResWrap sharingResWrap = new StopSharingResWrap();
		sharingResWrap.setStopSharingRes(nwSharingService.stopSharingRes(req
				.getStopSharingReq()));
		return sharingResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("modifyShareDtlsReq")
	public ModifyShareDtlsResWrap modifySharingTypeDtls(
			ModifyShareDtlsReqWrap req) {
		log.info("Inside ModifyShareDtlsResWrap -> modifyShareDtlsReq");
		ModifyShareDtlsResWrap sharingResWrap = new ModifyShareDtlsResWrap();
		sharingResWrap.setModifyShareDtlsRes(nwSharingService
				.modifySharingTypeDtls(req.getModifyShareDtlsReq()));
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("sharingRequestsDtls")
	public SharingRequestsDataResWrap sharingRequestsDtls(
			@QueryParam("networkId") String networkId) {
		log.info("Inside SharingRequestsDataResWrap -> sharingRequestsDtls");
		SharingRequestsDataResWrap sharingResWrap = new SharingRequestsDataResWrap();
		sharingResWrap.setSharingRequestsDataRes(nwSharingService
				.sharingRequestsDtls(networkId));
		return sharingResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("editProfileShare")
	public EditProfileShareResWrap editProfileShare(EditProfileShareReqWrap req) {
		log.info("Inside EditProfileShareResWrap -> editProfileShare");
		EditProfileShareResWrap sharingResWrap = new EditProfileShareResWrap();
		sharingResWrap.setEditProfileShareRes(nwSharingService
				.editProfileShare(req.getEditProfileShareReq()));
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPeopleProfiles")
	public QueryPeopleProfilesResWrap queryPeopleProfiles(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryPeopleProfilesResWrap -> querySharingProfile");
		QueryPeopleProfilesResWrap sharingResWrap = new QueryPeopleProfilesResWrap();
		sharingResWrap.setQueryPeopleProfilesRes(nwSharingService
				.queryPeopleProfiles(networkId));
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllNwks")
	public QueryAllNwksResWrap queryAllNetworks() {
		log.info("Inside QueryAllNwksResWrap -> queryAllNetworks");
		QueryAllNwksResWrap sharingResWrap = new QueryAllNwksResWrap();
		sharingResWrap.setQueryAllNwksRes(nwSharingService.queryAllNetworks());
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryProfileDetails")
	public QueryProfileDetailsResWrap queryProfileDetails(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryProfileDetailsResWrap -> queryProfileDetails");
		QueryProfileDetailsResWrap sharingResWrap = new QueryProfileDetailsResWrap();
		sharingResWrap.setQueryProfileDetailsRes(nwSharingService
				.queryProfileDetails(networkId));
		return sharingResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("approveSharingRequest")
	public ApproveSharingRequestResWrap approveSharingRequest(
			ApproveSharingRequestWrap req) {
		log.info("Inside ApproveSharingRequestResWrap -> approveSharingRequest");
		ApproveSharingRequestResWrap sharingResWrap = new ApproveSharingRequestResWrap();
		sharingResWrap.setApproveSharingRequestRes(nwSharingService
				.approveSharingRequest(req.getApproveSharingRequest()));
		return sharingResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkCountDtls")
	public QueryNwkCountDtlsResWrap queryNwkCountDtlsRes(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryNwkCountDtlsResWrap -> queryNwkCountDtlsRes");
		QueryNwkCountDtlsResWrap sharingResWrap = new QueryNwkCountDtlsResWrap();
		sharingResWrap.setQueryNwkCountDtlsRes(nwSharingService
				.queryNwkCountDtlsRes(networkId));
		return sharingResWrap;
	}
}
