package com.iexceed.esoko.domain.dao2;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionApp;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionAppPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class NetworkSubscriptionAppsRepo extends
		AbstractRepoForEntity<NetworkSubscriptionApp, NetworkSubscriptionAppPK> {
	public static Logger log = LoggerUtils.getLogger();

	public NetworkSubscriptionApp save(NetworkSubscriptionApp entity) {
		log.info("Inside NetworkSubscriptionAppRepo -> save");
		super.save(entity);
		return entity;
	}

	public NetworkSubscriptionApp findOne(NetworkSubscriptionApp entity,
			NetworkSubscriptionAppPK entityPK) {
		log.info("Inside NetworkSubscriptionAppRepo -> findOne");
		return super.findOne(NetworkSubscriptionApp.class, entityPK);
	}

	public boolean exists(NetworkSubscriptionApp entity,
			NetworkSubscriptionAppPK entityPK) {
		log.info("Inside NetworkSubscriptionAppRepo -> exists");
		return super.exists(NetworkSubscriptionApp.class, entityPK);

	}

	public NetworkSubscriptionApp merge(NetworkSubscriptionApp entity) {
		log.info("Inside NetworkSubscriptionAppRepo -> merge");
		return super.merge(entity);
	}

	public List<NetworkSubscriptionApp> getExistingInvoice(String subscriptionId) {
		List<NetworkSubscriptionApp> invocieNoList = null;
		Query query = null;
		log.info("Inside NetworkSubscriptionAppRepo -> getExistingInvoice");
		try {
			query = entityManager.createNativeQuery(
					getSql("getExistingInvocieNo"),
					NetworkSubscriptionApp.class);
			query.setParameter(1, subscriptionId);
			invocieNoList = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("invocieNoList::" + invocieNoList);
		return invocieNoList;
	}

	public List<NetworkSubscriptionApp> getNwkSubAppByNwkIdType(
			String networkId, String type) {
		List<NetworkSubscriptionApp> nwkSubAppList = null;
		Query query = null;
		log.info("Inside NetworkSubscriptionAppRepo -> getNwkSubAppByNwkIdType");
		try {
			query = entityManager.createNativeQuery(
					getSql("getNwkSubAppByNwkIdType"),
					NetworkSubscriptionApp.class);
			query.setParameter(1, networkId);
			query.setParameter(2, type);
			nwkSubAppList = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		return nwkSubAppList;
	}

	public void deleteByNwkIdAndType(String networkId, String type) {
		log.info("Inside NetworkSubscriptionAppRepo -> getNwkSubAppByNwkIdType");
		log.debug("NetworkId:" + networkId);
		log.debug("Type:" + type);
		Query query = null;
		try {
			query = entityManager.createNativeQuery(
					getSql("deleteNwkSubAppByNwkIdType"),
					NetworkSubscriptionApp.class);
			query.setParameter(1, networkId);
			query.setParameter(2, type);
			int i=query.executeUpdate();
			log.info("No of records deleted:"+i);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
