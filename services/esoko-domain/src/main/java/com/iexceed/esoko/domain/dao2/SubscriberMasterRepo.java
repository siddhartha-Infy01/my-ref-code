package com.iexceed.esoko.domain.dao2;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.SubscriberMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SubscriberMasterRepo extends
		AbstractRepoForEntity<SubscriberMaster, String> {
	private static Logger log = LoggerUtils.getLogger();

	public SubscriberMaster save(SubscriberMaster entity) {
		log.info("Inside SubscriberMasterRepo -> Save ");
		super.save(entity);
		return entity;

	}

	public SubscriberMaster findOne(SubscriberMaster entity, String id) {
		log.info("Inside SubscriberMasterRepo -> findOne ");
		return super.findOne(SubscriberMaster.class, id);
	}

	public boolean exists(SubscriberMaster entity, String id) {
		log.info("Inside SubscriberMasterRepo -> exists ");
		return super.exists(SubscriberMaster.class, id);

	}

	public SubscriberMaster merge(SubscriberMaster entity) {
		log.info("Inside SubscriberMasterRepo -> merge");
		return super.merge(entity);
	}
}
