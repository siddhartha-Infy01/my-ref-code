package com.iexceed.esoko.domain.dao2;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.NetworkSubscription;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionDetail;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionDetailPK;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class NetworkSubscriptionDetailsRepo
		extends
		AbstractRepoForEntity<NetworkSubscriptionDetail, NetworkSubscriptionDetailPK> {
	public static Logger log = LoggerUtils.getLogger();

	public NetworkSubscriptionDetail save(NetworkSubscriptionDetail entity) {
		super.save(entity);
		return entity;
	}

	public NetworkSubscriptionDetail findOne(NetworkSubscriptionDetail entity,
			NetworkSubscriptionDetailPK entityPK) {
		log.info("Inside NetworkSubscriptionDetailsRepo -> findOne");
		return super.findOne(NetworkSubscriptionDetail.class, entityPK);
	}

	public boolean exists(NetworkSubscriptionDetail entity,
			NetworkSubscriptionDetailPK entityPK) {
		log.info("Inside NetworkSubscriptionDetailsRepo -> exists");
		return super.exists(NetworkSubscriptionDetail.class, entityPK);

	}

	public NetworkSubscriptionDetail merge(NetworkSubscriptionDetail entity) {
		log.info("Inside NetworkSubscriptionDetailsRepo -> merge");
		return super.merge(entity);
	}

	public NetworkSubscriptionDetail getSubscriberDetails(String subscriberId) {
		log.info("Inside getSubscriberDetails");
		NetworkSubscriptionDetail entity = null;
		log.debug("subscriberId::" + subscriberId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("getSubscriberDetails"),
					NetworkSubscriptionDetail.class);
			query.setParameter(1, subscriberId);
			entity = (NetworkSubscriptionDetail) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return entity;
	}
}
