package com.iexceed.esoko.domain.dao2;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.PeoplePendingAuth;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class PeoplePendingAuthRepo extends
		AbstractRepoForEntity<PeoplePendingAuth, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PeoplePendingAuth pplPndAuth, String pendingId) {
		log.info("Inside PeoplePendingAuthRepo -> exists");
		return super.exists(PeoplePendingAuth.class, pendingId);
	}

	@SuppressWarnings("unchecked")
	public PeoplePendingAuth merge(PeoplePendingAuth pplPndAuth) {
		log.info("Inside PeoplePendingAuthRepo -> merge");
		return super.merge(pplPndAuth);
	}

	@SuppressWarnings("unchecked")
	public PeoplePendingAuth save(PeoplePendingAuth pplPndAuth) {
		log.info("Inside PeoplePendingAuthRepo -> save");
		return super.save(pplPndAuth);
	}

	public PeoplePendingAuth findOne(PeoplePendingAuth pplPndAuth,
			String pendingId) {
		log.info("Inside PeoplePendingAuthRepo -> findOne");
		return super.findOne(PeoplePendingAuth.class, pendingId);
	}
}
