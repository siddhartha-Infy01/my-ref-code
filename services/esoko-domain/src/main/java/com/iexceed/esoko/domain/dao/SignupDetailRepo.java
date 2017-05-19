package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SignupDetail;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class SignupDetailRepo extends
		AbstractRepoForEntity<SignupDetail, String> {
	private static Logger log = LoggerUtils.getLogger();

	@SuppressWarnings("unchecked")
	public SignupDetail save(SignupDetail user) {
		log.info("Inside SignupDetailRepo -> save");
		return super.save(user);
	}
	
	@SuppressWarnings("unchecked")
	public SignupDetail merge(SignupDetail user) {
		log.info("Inside SignupDetailRepo -> merge");
		return super.merge(user);
	}
	
	public SignupDetail findOne(SignupDetail entity, String userId) {
		log.info("Inside SignupDetailRepo -> findOne");
		return super.findOne(SignupDetail.class, userId);
	}

	public boolean exists(SignupDetail entity, String id) {
		log.info("Inside SignupDetailRepo -> exists");
		return super.exists(SignupDetail.class, id);
	}
	
	public void delete(SignupDetail entity) {
		log.info("Inside SignupDetailRepo -> delete");
		super.delete(entity);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(Iterable entities) {
		log.info("Inside PushAlertMasterRepo -> deleteAll");
		super.delete(entities);
	}
}
