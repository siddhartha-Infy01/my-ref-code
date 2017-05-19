package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_permission;
import com.iexceed.esoko.domain.entity.Network_permissionPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_permissionRepo extends
		AbstractRepoForEntity<Network_permission, Network_permissionPK> {
	public static Logger log = LoggerUtils.getLogger();

	public Network_permission queryNwkPermissions(String networkId,
			String groupId) {
		Network_permission nwpermsn;
		log.info("query network permissions by group");
		log.info("networkId::" + networkId);
		log.info("groupId::" + groupId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryNwkPermissions"), Network_permission.class);
			query.setParameter(1, networkId);
			query.setParameter(2, groupId);
			nwpermsn = (Network_permission) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return nwpermsn;

	}

	public Network_permission save(Network_permission nwpermsn) {
		super.save(nwpermsn);
		return nwpermsn;

	}

	public boolean exists(Network_permission nwPrm, Network_permissionPK nwPrmPk) {
		return super.exists(Network_permission.class, nwPrmPk);
	}

	public Network_permission merge(Network_permission nwPrm) {
		return super.merge(nwPrm);
	}
}