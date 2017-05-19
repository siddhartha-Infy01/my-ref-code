
package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_pricetype;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_pricetypeRepo extends AbstractRepoForEntity<Network_pricetype, String> {
	public static Logger log = LoggerUtils.getLogger();
	

	public List<Network_pricetype> queryNetworkPriceTyps(String networkId) {
		log.info("query network price types");
		log.info("networkId::" + networkId);

		Query query = entityManager.createNativeQuery(
				getSql("queryNetworkPriceTyps"), Network_pricetype.class);
		query.setParameter(1, networkId);

		List<Network_pricetype> resultList = query.getResultList();
		
		
		entityManager.close();
		return resultList;
	}

	public Network_pricetype save(Network_pricetype entity) {
		super.save(entity);
		return entity;

	}

	public Network_pricetype findOne(Network_pricetype entity , String nwkId) {
		return super.findOne(Network_pricetype.class , nwkId);
	}

	public boolean exists(Network_pricetype entity, String id) {
		return super.exists(Network_pricetype.class, id);

	}
	
	public  Network_pricetype merge(Network_pricetype entity) {
		return super.merge(entity);
	}
}

