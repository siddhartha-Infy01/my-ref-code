package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkRepo extends AbstractRepoForEntity<Network, String> {

	public static Logger log = LoggerUtils.getLogger();

	public void delete(Network entity) {
		super.delete(entity);
	}

	public boolean exists(Network nwk, String networkId) {
		log.info("Inside NetworkRepo: exists");
		boolean exists = super.exists(Network.class, networkId);
		return exists;
	}

	public Network merge(Network nwk) {
		nwk = super.merge(nwk);
		return nwk;
	}

	public Network queryNetworkUserInfo(String networkId) {
		log.info("Entered query Network info");
		log.info("networkid::" + networkId);
		Network network;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("networkInfo"), Network.class);
			query.setParameter(1, networkId);
			network = (Network) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return network;
	}

	public Network findOne(Network network, String networkId) {
		Network nwk = super.findOne(Network.class, networkId);
		return nwk;
	}

	public List<Network> queryNetworkbyParentId(String parentId) {
		log.info("Entered query Network by parentId");
		log.info("parentId::" + parentId);
		Query query = entityManager.createNativeQuery(
				getSql("queryNetworkbyParentId"), Network.class);
		query.setParameter(1, parentId);
		List<Network> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<Network> listAllPublicNwks() {
		log.info("list all public networks");

		Query query = entityManager.createNativeQuery(
				getSql("listAllPublicNwks"), Network.class);

		List<Network> resultList = query.getResultList();

		entityManager.close();
		return resultList;

	}

	public Network save(Network nwk) {
		super.save(nwk);
		return nwk;

	}

	public Network queryCountryNwk(String location) {
		log.info("Inside NetworkRepo -> queryCountryNwk");
		log.debug("PrimaryLocation: " + location);
		Query query = entityManager.createNativeQuery(
				getSql("queryCountryNwk"), Network.class);
		query.setParameter(1, location);
		Network nwk = null;
		try {
			nwk = (Network) query.getSingleResult();
		} catch (NoResultException e) {
			log.info("No result found");
		}
		entityManager.close();
		return nwk;
	}

	public List<Network> listAllNetworks() {
		log.info("list all public networks");
		Query query = entityManager.createNativeQuery(
				getSql("fetchAllNetworks"), Network.class);
		List<Network> resultList = null;
		try {
			resultList = query.getResultList();
		} catch (NoResultException e) {
			log.info("No result found");
		}
		entityManager.close();
		return resultList;

	}

}
