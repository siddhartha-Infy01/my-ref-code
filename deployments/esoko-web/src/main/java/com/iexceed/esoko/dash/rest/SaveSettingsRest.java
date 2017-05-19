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

import com.iexceed.esoko.db.service.SaveSettingsService;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.QueryDashBoardSettingsResWrap;
import com.iexceed.esoko.jaxb.db.QueryWidgetSettingsResWrap;
import com.iexceed.esoko.jaxb.db.SaveDashBoardSettingsReqWrap;
import com.iexceed.esoko.jaxb.db.SaveDashBoardSettingsResWrap;
import com.iexceed.esoko.jaxb.db.SavewidgetSettingsReqWrap;
import com.iexceed.esoko.jaxb.db.SavewidgetSettingsResWrap;

/*
 * @author Ankur
 */
@Path("SaveSettingsService")
@Component
public class SaveSettingsRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	SaveSettingsService settingsService;

	public SaveSettingsRest() {

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("saveDashBoardSettings")
	public SaveDashBoardSettingsResWrap saveDashBoardSettings(
			SaveDashBoardSettingsReqWrap req) {
		SaveDashBoardSettingsResWrap settingsRes = new SaveDashBoardSettingsResWrap();
		settingsRes.setSaveDashBoardSettingsRes(settingsService
				.saveDashBoardSettings(req.getSaveDashBoardSettingsReq()));
		return settingsRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryDashBoardSettings")
	public QueryDashBoardSettingsResWrap queryDashBoardSettings(
			@QueryParam("userId") String userId) {
		log.info("Inside QueryDashBoardSettingsResWrap -> queryDBSettings");
		QueryDashBoardSettingsResWrap settingsRes = new QueryDashBoardSettingsResWrap();
		settingsRes.setQueryDashBoardSettingsRes(settingsService
				.queryDBSettings(userId));
		return settingsRes;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("savewidgetSettings")
	public SavewidgetSettingsResWrap savewidgetSettings(
			SavewidgetSettingsReqWrap req) {
		log.info("Inside SavewidgetSettingsResWrap -> savewidgetSettings");
		SavewidgetSettingsResWrap settingsRes = new SavewidgetSettingsResWrap();
		settingsRes.setSavewidgetSettingsRes(settingsService
				.savewidgetSettings(req.getSavewidgetSettingsReq()));
		return settingsRes;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryWidgetSettings")
	public QueryWidgetSettingsResWrap queryWidgetSettings(
			@QueryParam("userId") String userId,
			@QueryParam("widgetId") String widgetId) {
		log.info("Inside QueryWidgetSettingsResWrap -> queryWidgetSettings");
		QueryWidgetSettingsResWrap settingsRes = new QueryWidgetSettingsResWrap();
		settingsRes.setQueryWidgetSettingsRes(settingsService
				.queryWidgetSettings(userId, widgetId));
		return settingsRes;
	}

}
