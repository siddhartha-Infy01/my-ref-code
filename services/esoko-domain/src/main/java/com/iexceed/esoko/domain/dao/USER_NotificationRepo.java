package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserNotification;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class USER_NotificationRepo extends
		AbstractRepoForEntity<UserNotification, String> {
	public static Logger log = LoggerUtils.getLogger();

	@Override
	public UserNotification save(UserNotification entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

	public UserNotification findOne(UserNotification entity, String pkId) {
		return super.findOne(UserNotification.class, pkId);
	}

	public List<UserNotification> getAllNotifications(String networkId,
			String userOrgrp) {
		log.info("Entered query Network by parentId");
		log.info("networkId::" + networkId);
		log.info("userOrgrp::" + userOrgrp);
		Query query = entityManager.createNativeQuery(
				getSql("queryAllNotifications"), UserNotification.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userOrgrp);
		List<UserNotification> resultList = query.getResultList();
		entityManager.close();
		log.info("Result::" + resultList.toString());
		return resultList;
	}
}
