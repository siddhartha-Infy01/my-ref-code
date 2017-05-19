package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_privacy;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_privacyRepo extends
		AbstractRepoForEntity<Network_privacy, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<Network_privacy> queryNetworkPriv(String networkId) {
		log.info("Privacy setting for network");
		log.info("networkId::" + networkId);

		Query query = entityManager.createNativeQuery(
				getSql("queryNetworkPriv"), Network_privacy.class);
		query.setParameter(1, networkId);

		List<Network_privacy> resultList = query.getResultList();

		entityManager.close();
		return resultList;
	}

	public Network_privacy save(Network_privacy nwpriv) {
		super.save(nwpriv);
		return nwpriv;

	}

	public boolean exists(Network_privacy nwk, String networkId) {
		boolean exists = super.exists(Network_privacy.class, networkId);
		return exists;
	}

	public Network_privacy merge(Network_privacy nwk) {

		nwk = super.merge(nwk);

		return nwk;
	}

	public Network_privacy findOne(Network_privacy network, String networkId) {
		Network_privacy nwk = super.findOne(Network_privacy.class, networkId);
		return nwk;
	}
	
	public void delete(Network_privacy entity){
		super.delete(entity);
	}

}
