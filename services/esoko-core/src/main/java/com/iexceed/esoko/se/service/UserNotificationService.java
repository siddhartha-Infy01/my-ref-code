package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.beans.NotificationBean;
import com.iexceed.esoko.domain.dao.USER_NotificationRepo;
import com.iexceed.esoko.domain.entity.UserNotification;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Service
public class UserNotificationService {

	@Autowired
	USER_NotificationRepo notificationRepo;
	
	public static Logger log = LoggerUtils.getLogger();

	@Transactional
	public boolean populateNotifications(List<NotificationBean> notifications) {
		for (NotificationBean bean : notifications) {
			UserNotification userNotification = new UserNotification();
			userNotification.setNotificationId(generateNotificationId());
			userNotification.setNetworkId(bean.getNetworkId());
			userNotification.setNotificationMsg(bean.getMessage());
			userNotification.setStatus("U");
			userNotification.setType(bean.getType());
			userNotification.setUserGrpId(bean.getUserOrGroup());
			userNotification.setCreatedBy("SYSTEM");
			userNotification.setCreatedTs(Utils.getCurrentDate());
			userNotification.setModifiedBy("SYSTEM");
			userNotification.setModifiedTs(Utils.getCurrentDate());
			notificationRepo.save(userNotification);
		}
		return true;
	}

	@Transactional
	public boolean markAsRead(List<String> notificationIds) {

		for (String notifId : notificationIds) {
			UserNotification userNotification = new UserNotification();
			userNotification.setNotificationId(notifId);
			userNotification=notificationRepo.findOne(userNotification, notifId);
			userNotification.setStatus("R");
			notificationRepo.save(userNotification);
		}
		return true;
	}

	public List<NotificationBean> queryNotifications(String networkId,
			String userID) {
		log.debug("Entered Query All Notifications");
		List<NotificationBean> notifications = new ArrayList<NotificationBean>();
		List<UserNotification> list=notificationRepo.getAllNotifications(networkId, userID);
		log.debug("Results From DB"+list);
		log.debug("Results List Size"+list.size());
		for(UserNotification notification:list){
			NotificationBean bean=new NotificationBean();
			bean.setMessage(notification.getNotificationMsg());
			bean.setNetworkId(notification.getNetworkId());
			bean.setType(notification.getType());
			bean.setUserOrGroup(notification.getUserGrpId());
			bean.setNotifId(notification.getNotificationId());
			notifications.add(bean);
		}
		return notifications;
	}

	private String generateNotificationId() {
		StringBuffer transaction = new StringBuffer();
		transaction.append("NOTF" + Utils.getJulianDt()
				+ Math.round(Math.random() * 100000));
		return transaction.toString();

	}
}
