package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.PriceUploadMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@SuppressWarnings("unchecked")
@Repository
public class PriceUploadMasterRepo extends
		AbstractRepoForEntity<PriceUploadMaster, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PriceUploadMaster entity, String id) {
		log.info("Inside PriceUploadMasterRepo -> exists");
		return super.exists(PriceUploadMaster.class, id);
	}

	public PriceUploadMaster merge(PriceUploadMaster entity) {
		log.info("Inside PriceUploadMasterRepo -> merge");
		return super.merge(entity);
	}

	public PriceUploadMaster save(PriceUploadMaster entity) {
		log.info("Inside PriceUploadMasterRepo -> save");
		super.save(entity);
		return entity;
	}

	public PriceUploadMaster findOne(PriceUploadMaster entity, String id) {
		log.info("Inside PriceUploadMasterRepo -> findOne");
		return super.findOne(PriceUploadMaster.class, id);
	}

	public void delete(PriceUploadMaster entity) {
		log.info("Inside PriceUploadMasterRepo -> delete");
		super.delete(entity);
	}
	public List<String> findUploadDetailsForAlert(String pushAlertId, String peopleId) {
		log.info("Inside PriceUploadMasterRepo -> findUploadDetailsForAlert");
		log.debug("pushAlertId: " + pushAlertId);
		log.debug("Values: " + peopleId);
		Query query = entityManager
				.createNativeQuery("{call findUploadDetailsForAlert(?,?)}");
		query.setParameter(1, pushAlertId);
		query.setParameter(2, peopleId);
		List<String> elements = query.getResultList();
		entityManager.close();
		return elements;
	}
	
	public List<PriceUploadMaster> findPriceRequestDetails(String networkId, String locationId, String commodityId) {
		log.info("Inside PriceUploadMasterRepo -> findPriceRequestDetails");
		log.debug("networkId: " + networkId);
		log.debug("locationId: " + locationId);
		log.debug("commodityId: " + commodityId);
		List<PriceUploadMaster> elements;
		try {
		Query query = entityManager
				.createNativeQuery("{call findPriceRequestDetails(?,?,?)}",PriceUploadMaster.class);
		query.setParameter(1, networkId);
		query.setParameter(2, locationId);
		query.setParameter(3, commodityId);
		 elements = query.getResultList();
		}
		catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return elements;
	}
}
