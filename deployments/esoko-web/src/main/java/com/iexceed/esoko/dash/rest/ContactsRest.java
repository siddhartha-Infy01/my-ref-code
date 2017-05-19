package com.iexceed.esoko.dash.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.db.service.ContactsService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.CreateContactsReqWrap;
import com.iexceed.esoko.jaxb.db.CreateContactsResWrap;

@Path("ContactsService")
@Component
public class ContactsRest {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	ContactsService contactsService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createContacts")
	public CreateContactsResWrap createContacts(
			CreateContactsReqWrap createContactsReqWrap) {
		log.info("Inside ContactsRest-->createContacts");
		CreateContactsResWrap createContactsResWrap = new CreateContactsResWrap();
		createContactsResWrap.setCreateContactsRes(contactsService
				.createContacts(createContactsReqWrap.getCreateContactsReq()));
		return createContactsResWrap;
	}

}
