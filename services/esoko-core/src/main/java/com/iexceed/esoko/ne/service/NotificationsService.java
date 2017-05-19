package com.iexceed.esoko.ne.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.GetNotificationsRes;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class NotificationsService {

	public static Logger log = LoggerUtils.getLogger();

	public NotificationsService() {

	}

	public GetNotificationsRes getNotificationRes(String networkId,
			String userId) {
		log.info("Inside GetNotificationsRes -> getNotificationRes");
		log.debug("NetworkId: " + networkId);
		log.debug("UserId: " + userId);
		GetNotificationsRes getNotification = new GetNotificationsRes();
		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS, "", "",
				"", null);
		getNotification.setHeader(header);
		return getNotification;
	}
}
