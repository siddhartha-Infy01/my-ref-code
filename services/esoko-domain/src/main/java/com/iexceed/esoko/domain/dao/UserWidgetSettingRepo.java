package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserWidgetSetting;
import com.iexceed.esoko.domain.entity.UserWidgetSettingPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class UserWidgetSettingRepo extends AbstractRepoForEntity<UserWidgetSetting, UserWidgetSettingPK> {
	public static Logger log = LoggerUtils.getLogger();
	
	public UserWidgetSetting queryWidgetSettings(String userId,String widgetId) {
		UserWidgetSetting userdshbrdsettng;
		log.info("widget setting for user");
		log.debug("userData::" + userId);
		log.debug("widgetId::" + widgetId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryWidgetSettings"), UserWidgetSetting.class);
			query.setParameter(1, userId);
			query.setParameter(2, widgetId);
			userdshbrdsettng = (UserWidgetSetting) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return userdshbrdsettng;

	}

	public UserWidgetSetting save(UserWidgetSetting entity) {
		super.save(entity);
		return entity;
	}

	public UserWidgetSetting findOne(UserWidgetSetting entity, UserWidgetSettingPK usrwidstg) {
		return super.findOne(UserWidgetSetting.class, usrwidstg);
	}

	public boolean exists(UserWidgetSetting entity, UserWidgetSettingPK usrwidstg) {
		return super.exists(UserWidgetSetting.class, usrwidstg);

	}

	public UserWidgetSetting merge(UserWidgetSetting entity) {
		return super.merge(entity);
	}
}

