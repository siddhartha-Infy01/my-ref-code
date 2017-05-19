package com.iexceed.esoko.domain.dao;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.EsokoApp;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class EsokoAppRepo extends AbstractRepoForEntity<EsokoApp, String> {
	public static Logger log = LoggerUtils.getLogger();

	public long getSequenceNumber() {
		log.info("Inside EsokoAppRepo -> getSequenceNumber");
		Query query = entityManager
				.createNativeQuery(getSql("sequenceGenerator"));
		String sequence =  (String) query.getSingleResult();
		entityManager.close();		
		return Long.valueOf(sequence);
	}
}
