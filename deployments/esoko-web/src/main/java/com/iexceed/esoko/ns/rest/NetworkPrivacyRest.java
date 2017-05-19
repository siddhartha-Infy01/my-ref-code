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
import com.iexceed.esoko.jaxb.ns.QueryNwkPrivacySettingsResWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPrivacySettingsReqWrap;
import com.iexceed.esoko.jaxb.ns.SaveNwkPrivacySettingsResWrap;
import com.iexceed.esoko.ne.service.NetworkPrivacyService;

/*
 * @author Ankur
 */
@Path("NetworkPrivacyService")
@Component
public class NetworkPrivacyRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkPrivacyRest() {

	}

	@Autowired
	NetworkPrivacyService nwPrivacyService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveNwkPrivacySettings")
	public SaveNwkPrivacySettingsResWrap saveNwkPrivacySettings(
			SaveNwkPrivacySettingsReqWrap req) {
		log.info("Inside SaveNwkPrivacySettingsResWrap -> saveNwkPrivacySettings");
		SaveNwkPrivacySettingsResWrap privacyResWrap = new SaveNwkPrivacySettingsResWrap();
		privacyResWrap.setSaveNwkPrivacySettingsRes(nwPrivacyService
				.saveNwkPrivacySettings(req.getSaveNwkPrivacySettingsReq()));
		return privacyResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkPrivacySettings")
	public QueryNwkPrivacySettingsResWrap queryNwkPrivacySettings(
			@QueryParam("networkId") String networkId) {
		log.info("Inside QueryNwkPrivacySettingsResWrap -> queryNwkPrivacySettings");
		QueryNwkPrivacySettingsResWrap settingsResWrap = new QueryNwkPrivacySettingsResWrap();
		settingsResWrap.setQueryNwkPrivacySettingsRes(nwPrivacyService
				.queryNwPrivacySettings(networkId));
		return settingsResWrap;
	}
}
