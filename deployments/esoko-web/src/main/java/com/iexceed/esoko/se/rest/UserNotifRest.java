package com.iexceed.esoko.se.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.beans.NotificationBean;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.ALLNOTIF;
import com.iexceed.esoko.jaxb.se.QueryAllNotif;
import com.iexceed.esoko.jaxb.se.QueryAllNotifWrap;
import com.iexceed.esoko.se.service.UserNotificationService;

@Component
@Path("NotifService")
public class UserNotifRest {
	
	public static Logger log = LoggerUtils.getLogger();
	
	@Autowired
	UserNotificationService notifService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNotifs")
	public QueryAllNotifWrap createAlias(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.debug("Entered Query All Notifications Rest");
		QueryAllNotifWrap wrap = new QueryAllNotifWrap();
		QueryAllNotif notif = new QueryAllNotif();
		List<NotificationBean> notifications = notifService.queryNotifications(
				networkId, userId);
		log.debug("List Of Notifications:::"+notifications);
		log.debug("List Of Notifications Size"+notifications.size());
		for (NotificationBean notification : notifications) {
			ALLNOTIF allnotif = new ALLNOTIF();
			allnotif.setNotifId(notification.getNotifId());
			allnotif.setType(notification.getType());
			allnotif.setNotifMsg(notification.getMessage());
			allnotif.setUsrOrGrp(notification.getUserOrGroup());
			notif.getALLNOTIF().add(allnotif);
		}
		wrap.setQueryAllNotif(notif);
		return wrap;
	}

}
