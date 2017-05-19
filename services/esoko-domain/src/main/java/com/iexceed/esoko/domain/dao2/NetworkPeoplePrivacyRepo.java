package com.iexceed.esoko.domain.dao2;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.NetworkPeoplePrivacy;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkPeoplePrivacyRepo extends
		AbstractRepoForEntity<NetworkPeoplePrivacy, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(NetworkPeoplePrivacy nwkPpl, String nwkId) {
		log.info("Inside NetworkPeoplePrivacyRepo -> exists");
		return super.exists(NetworkPeoplePrivacy.class, nwkId);
	}

	@SuppressWarnings("unchecked")
	public NetworkPeoplePrivacy merge(NetworkPeoplePrivacy nwkPpl) {
		log.info("Inside NetworkPeoplePrivacyRepo -> merge");
		return super.merge(nwkPpl);
	}

	@SuppressWarnings("unchecked")
	public NetworkPeoplePrivacy save(NetworkPeoplePrivacy nwkPpl) {
		log.info("Inside NetworkPeoplePrivacyRepo -> save");
		return super.save(nwkPpl);
	}

	public NetworkPeoplePrivacy findOne(NetworkPeoplePrivacy nwkPpl,
			String nwkId) {
		log.info("Inside NetworkPeoplePrivacyRepo -> findOne");
		return super.findOne(NetworkPeoplePrivacy.class, nwkId);
	}
}
