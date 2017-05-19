package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.NetworkCommodity;
import com.iexceed.esoko.domain.entity.NetworkCommodityPK;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkCommodityRepo extends
		AbstractRepoForEntity<NetworkCommodity, NetworkCommodityPK> {

	public static Logger log = LoggerUtils.getLogger();

	public List<NetworkCommodity> queryNetworkCommodities(String networkId) {
		log.info("Entered query  commodities by network id");
		log.info("networkid::" + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryNetworkCommodities"), NetworkCommodity.class);
		query.setParameter(1, networkId);

		List<NetworkCommodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public NetworkCommodity save(NetworkCommodity nwkusr) {
		super.save(nwkusr);
		return nwkusr;
	}

	public boolean exists(NetworkCommodity entity, NetworkCommodityPK id) {
		return super.exists(NetworkCommodity.class, id);

	}

	public boolean deleteNetworkCommodity(String networkId) {
		log.info("Entered delete network Commodity");
		log.info("networkId::" + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("deleteCommodityByNetwork"), UserCommodity.class);
		query.setParameter(1, networkId);
		int noofRows = query.executeUpdate();
		log.debug("No Of Rows Deleted::" + noofRows);
		entityManager.close();
		return true;
	}

	public List<Commodity> queryGroupsByNwkId(String networkId) {
		log.info("inside queryGroupsByNwkId");
		log.debug("networkid::" + networkId);
		Query query = entityManager
				.createNativeQuery("{call findRootCommoditiesForNetwork(?)}",Commodity.class);
		query.setParameter(1, networkId);
		List<Commodity> listOfCommodities = query.getResultList();
		entityManager.close();
		return listOfCommodities;
	}

	
	public List<Commodity> queryCommodByNwkIdCrtId(String networkId,String criteriaId) {
		log.info("inside queryCommodByNwkIdCrtId");
		log.debug("networkid::" + networkId);
		log.debug("criteriaId::" + criteriaId);
		Query query = entityManager
				.createNativeQuery("{call findAllChildCommoditiesOfcommType(?,?)}",Commodity.class);
		query.setParameter(1, networkId);
		query.setParameter(2, criteriaId);
		List<Commodity> listOfCommodities = query.getResultList();
		entityManager.close();
		return listOfCommodities;
	}

}
