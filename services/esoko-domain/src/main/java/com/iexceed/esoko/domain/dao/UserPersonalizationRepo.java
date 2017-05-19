package com.iexceed.esoko.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity.UserPersonalization;
import com.iexceed.esoko.domain.entity.UserPersonalizationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class UserPersonalizationRepo extends AbstractRepoForEntity<UserPersonalization, UserPersonalizationPK> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<UserPersonalization> queryDashBoardSettings(String userId) {
		UserPersonalization userdshbrdsettng;
		log.info("Dashboard setting for user");
		log.debug("userData::" + userId);
		
			Query query = entityManager.createNativeQuery(getSql("queryDashBoardSettings"), UserPersonalization.class);
			query.setParameter(1, userId);
			List<UserPersonalization> resultList = query.getResultList();
			entityManager.close();
			return resultList;

	}
	
	public UserPersonalization save(UserPersonalization entity) {
		super.save(entity);
		return entity;
	}

	public UserPersonalization findOne(UserPersonalization entity, UserPersonalizationPK usrper) {
		return super.findOne(UserPersonalization.class, usrper);
	}

	public boolean exists(UserPersonalization entity, UserPersonalizationPK usrper) {
		return super.exists(UserPersonalization.class, usrper);

	}

	public UserPersonalization merge(UserPersonalization entity) {
		return super.merge(entity);
	}
}

