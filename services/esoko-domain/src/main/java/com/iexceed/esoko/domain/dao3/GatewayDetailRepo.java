package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.GatewayDetail;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class GatewayDetailRepo extends
		AbstractRepoForEntity<GatewayDetail, String> {
	private static Logger log = LoggerUtils.getLogger();

	public List<GatewayDetail> findAll() {
		log.info("Inside GatewayDetailRepo -> findAll");
		return (List<GatewayDetail>) super.findAll();
	}

	public GatewayDetail findOne(GatewayDetail entity, String gatewayId) {
		log.info("Inside GatewayDetailRepo -> findOne");
		return super.findOne(GatewayDetail.class, gatewayId);
	}

	public GatewayDetail save(GatewayDetail entity) {
		log.info("Inside GatewayDetailRepo -> save");
		return super.save(entity);
	}

	public GatewayDetail getGatewayDetail(String gatewayName) {
		log.info("Inside GatewayDetailRepo -> getGatewayDetail");
		Query query = entityManager.createNativeQuery(
				getSql("queryGatewayDetail"), GatewayDetail.class);
		query.setParameter(1, gatewayName);
		GatewayDetail entity = null;
		try {
			entity = (GatewayDetail) query.getSingleResult();
		} catch (NoResultException e) {
			log.error("No result found");
		}
		entityManager.close();
		return entity;
	}
	
	public List<GatewayDetail> queryAllDetails(){
		log.info("Inside GatewayDetailRepo -> queryAllDetails");
		Query query = entityManager.createNativeQuery(
				getSql("queryAllGatewayDtl"), GatewayDetail.class);
		List<GatewayDetail> entityList = query.getResultList();
		return entityList;
		
	}
}
