package com.iexceed.esoko.people.rest;

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
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibReqWrap;
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibResWrap;
import com.iexceed.esoko.jaxb.people.AddUserAsAdminReqWrap;
import com.iexceed.esoko.jaxb.people.AddUserAsAdminResWrap;
import com.iexceed.esoko.jaxb.people.CheckEmailResWrap;
import com.iexceed.esoko.jaxb.people.CheckMobileResWrap;
import com.iexceed.esoko.jaxb.people.CopyUsersToPeopleReqWrap;
import com.iexceed.esoko.jaxb.people.CopyUsersToPeopleResWrap;
import com.iexceed.esoko.jaxb.people.CreatePeopleReqWrap;
import com.iexceed.esoko.jaxb.people.CreatePeopleResWrap;
import com.iexceed.esoko.jaxb.people.EditPeopleReqWrap;
import com.iexceed.esoko.jaxb.people.EditPeopleResWrap;
import com.iexceed.esoko.jaxb.people.QueryAllPeopleResWrap;
import com.iexceed.esoko.jaxb.people.QueryPersonResWrap;
import com.iexceed.esoko.jaxb.people.QueryUsersListResWrap;
import com.iexceed.esoko.jaxb.people.SearchAllPeopleResWrap;
import com.iexceed.esoko.people.service.PeopleService;

@Path("PeopleService")
@Component
public class PeopleServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public PeopleServiceRest() {
	}

	@Autowired
	private PeopleService peopleService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createPeople")
	public CreatePeopleResWrap createPeople(CreatePeopleReqWrap req) {
		log.info("Inside PeopleServiceRest -> createPeople");
		CreatePeopleResWrap commditiesResWrap = new CreatePeopleResWrap();
		commditiesResWrap.setCreatePeopleRes(peopleService.createPeople(req
				.getCreatePeopleReq()));
		return commditiesResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllPeople")
	public QueryAllPeopleResWrap queryAllPeople(
			@QueryParam("operation") String operation,
			@QueryParam("networkId") String networkId,
			@QueryParam("locations") String locations,
			@QueryParam("commodities") String commodities,
			@QueryParam("occupations") String occupations) {
		log.info("Inside PeopleServiceRest -> queryAllPeople");
		QueryAllPeopleResWrap resWrap = new QueryAllPeopleResWrap();
		resWrap.setQueryAllPeopleRes(peopleService.queryAllPeople(operation,
				networkId, locations, commodities, occupations));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("searchAllPeople")
	public SearchAllPeopleResWrap searchAllPeople(
			@QueryParam("networkId") String networkId) {
		log.info("Inside PeopleServiceRest -> queryAllPeople");
		SearchAllPeopleResWrap resWrap = new SearchAllPeopleResWrap();
		resWrap.setSearchAllPeopleRes(peopleService.searchAllPeople(networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUsersList")
	public QueryUsersListResWrap queryUsersList(
			@QueryParam("networkId") String networkId) {
		log.info("Inside PeopleServiceRest -> queryUsersList");
		QueryUsersListResWrap resWrap = new QueryUsersListResWrap();
		resWrap.setQueryUsersListRes(peopleService.queryUsersList(networkId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("copyUsersToPeople")
	public CopyUsersToPeopleResWrap copyUsersToPeople(
			CopyUsersToPeopleReqWrap req) {
		log.info("Inside PeopleServiceRest -> copyUsersToPeople");
		CopyUsersToPeopleResWrap commditiesResWrap = new CopyUsersToPeopleResWrap();
		commditiesResWrap.setCopyUsersToPeopleRes(peopleService
				.copyUsersToPeople(req.getCopyUsersToPeopleReq()));
		return commditiesResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryPerson")
	public QueryPersonResWrap queryPerson(@QueryParam("userId") String userId,
			@QueryParam("networkId") String networkId) {
		log.info("Inside PeopleServiceRest -> queryPerson");
		QueryPersonResWrap resWrap = new QueryPersonResWrap();
		resWrap.setQueryPersonRes(peopleService.queryPerson(userId, networkId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("editPeople")
	public EditPeopleResWrap editPeople(EditPeopleReqWrap req) {
		log.info("Inside PeopleServiceRest -> editPeople");
		EditPeopleResWrap resWrap = new EditPeopleResWrap();
		resWrap.setEditPeopleRes(peopleService.editPeople(req
				.getEditPeopleReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("checkEmail")
	public CheckEmailResWrap checkEmail(@QueryParam("emailId") String emailId,
			@QueryParam("networkId") String networkId) {
		log.info("Inside PeopleServiceRest -> checkEmail");
		CheckEmailResWrap resWrap = new CheckEmailResWrap();
		resWrap.setCheckEmailRes(peopleService.checkEmail(emailId, networkId));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("checkMobile")
	public CheckMobileResWrap checkMobile(@QueryParam("mobile") String mobile,
			@QueryParam("networkId") String networkId,
			@QueryParam("countryName") String countryName) {
		log.info("Inside PeopleServiceRest -> checkMobile");
		CheckMobileResWrap resWrap = new CheckMobileResWrap();
		resWrap.setCheckMobileRes(peopleService.checkMobile(mobile, networkId,
				countryName));
		return resWrap;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("addAdmin")
	public AddUserAsAdminResWrap addAdmin(
			AddUserAsAdminReqWrap req) {
		log.info("Inside AddUserAsAdminResWrap -> addAdmin");
		AddUserAsAdminResWrap reswrap = new AddUserAsAdminResWrap();
		reswrap.setAddUserAsAdminRes(peopleService.addAdmin(req.getAddUserAsAdminReq()));
		return reswrap;
	}
}
