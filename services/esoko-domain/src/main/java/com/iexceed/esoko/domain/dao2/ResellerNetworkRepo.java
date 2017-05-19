package com.iexceed.esoko.domain.dao2;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.ResellerNetwork;
import com.iexceed.esoko.domain.entity2.ResellerNetworkPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@SuppressWarnings("unchecked")
@Component
public class ResellerNetworkRepo extends
		AbstractRepoForEntity<ResellerNetwork, ResellerNetworkPK> {
	public static Logger log = LoggerUtils.getLogger();

	public ResellerNetwork save(ResellerNetwork entity) {
		log.info("Inside ResellerNetworkRepo -> save");
		super.save(entity);
		return entity;
	}

	public ResellerNetwork findOne(ResellerNetwork entity, ResellerNetworkPK id) {
		log.info("Inside ResellerNetworkRepo -> findOne");
		return super.findOne(ResellerNetwork.class, id);
	}

	public boolean exists(ResellerNetwork entity, ResellerNetworkPK id) {
		log.info("Inside ResellerNetworkRepo -> exists");
		return super.exists(ResellerNetwork.class, id);

	}

	public ResellerNetwork merge(ResellerNetwork entity) {
		log.info("Inside ResellerNetworkRepo -> merge");
		return super.merge(entity);
	}

	public String getNetworkOwner(String networkId) {
		log.info("Inside ResellerNetworkRepo :: getNetworkOwner");
		String ownerName = null;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getNetworkOwnerName"));
			query.setParameter(1, networkId);
			ownerName = query.getSingleResult().toString();
			log.debug("ownerName::" + ownerName);
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return ownerName;
	}

	public ResellerNetwork getResellerForNetwork(String networkId) {
		log.info("Inside ResellerNetworkRepo :: getReseller");
		ResellerNetwork reseller = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("getResellerForNetwork"), ResellerNetwork.class);
			query.setParameter(1, networkId);
			reseller = (ResellerNetwork) query.getSingleResult();
			log.debug("reseller::" + reseller);
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return reseller;
	}
}
