package com.iexceed.esoko.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkCommodity;
import com.iexceed.esoko.domain.entity.NetworkCommodityPK;
import com.iexceed.esoko.domain.entity.NetworkLocation;
import com.iexceed.esoko.domain.entity.NetworkLocationPK;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkLocationRepo extends
		AbstractRepoForEntity<NetworkLocation, NetworkLocationPK> {
	public static Logger log = LoggerUtils.getLogger();

	public List<NetworkLocation> queryNetworkLocations(String networkId) {
		log.info("Entered query locatins by networkid");
		log.info("networkId::" + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryNetworkLocations"), NetworkLocation.class);
		query.setParameter(1, networkId);
		List<NetworkLocation> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public NetworkLocation save(NetworkLocation nwloc) {
		super.save(nwloc);
		return nwloc;
	}

	public boolean exists(NetworkLocation entity, NetworkLocationPK id) {
		return super.exists(NetworkLocation.class, id);

	}
	
	public boolean deleteNetworkLocation(String networkId){
		Query query = entityManager.createNativeQuery(getSql("deleteLocationByNetwork"),UserLocation.class);
		query.setParameter(1, networkId);
		query.executeUpdate();
		return true;
	}
}
