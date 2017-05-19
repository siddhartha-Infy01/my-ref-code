package com.iexceed.esoko.ns.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.GetNotificationsResWrap;
import com.iexceed.esoko.ne.service.NotificationsService;

/*
 * @author Ankur
 */
@Path("NotificationsService")
@Component
public class NotificationsRest {

	public static Logger log = LoggerUtils.getLogger();

	public NotificationsRest() {

	}

	@Autowired
	public NotificationsService notificationService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("getNotifications")
	public GetNotificationsResWrap getNotifications(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside GetNotificationsResWrap -> getNotifications");
		GetNotificationsResWrap notifyResWrap = new GetNotificationsResWrap();
		notifyResWrap.setGetNotificationsRes(notificationService
				.getNotificationRes(networkId, userId));
		return notifyResWrap;
	}
}
