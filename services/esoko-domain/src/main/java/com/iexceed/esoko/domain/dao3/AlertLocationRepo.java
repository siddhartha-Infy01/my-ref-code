package com.iexceed.esoko.domain.dao3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.AlertLocation;
import com.iexceed.esoko.domain.entity3.AlertLocationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AlertLocationRepo extends
		AbstractRepoForEntity<AlertLocation, AlertLocationPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(AlertLocation alert, AlertLocationPK alertPk) {
		log.info("Inside AlertLocationRepo -> exists");
		return super.exists(AlertLocation.class, alertPk);
	}

	public AlertLocation merge(AlertLocation alert) {
		log.info("Inside AlertLocationRepo -> merge");
		return super.merge(alert);
	}

	public AlertLocation save(AlertLocation alert) {
		log.info("Inside AlertLocationRepo -> save");
		return super.save(alert);
	}

	public AlertLocation findOne(AlertLocation alert, AlertLocationPK alertPk) {
		log.info("Inside AlertLocationRepo -> findOne");
		return super.findOne(AlertLocation.class, alertPk);
	}

	public void delete(AlertLocation alert) {
		log.info("Inside AlertCommodityRepo -> delete");
		super.delete(alert);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable alerts) {
		log.info("Inside AlertLocationRepo -> deleteAll");
		super.delete(alerts);
	}

	public List<AlertLocation> findAllAlertLocations(String alertId) {
		log.info("Inside AlertLocationRepo -> findAllAlertLocations");
		log.debug("AlertId: " + alertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllAlertLocations"), AlertLocation.class);
		query.setParameter(1, alertId);
		List<AlertLocation> locations = query.getResultList();
		entityManager.close();
		return locations;
	}

	public  List<Object> findPushAlertId(String uploadId, String networkId) {
		log.info("Inside AlertLocationRepo -> findPushAlertId");
		log.debug("uploadId:" + uploadId);
		log.debug("networkId: " + networkId);
		Query query = entityManager
				.createNativeQuery("{call BidsOfferCopySharing(?,?)}");
		query.setParameter(1, uploadId);
		query.setParameter(2, networkId);
		List<Object> results = query.getResultList();
		entityManager.close();
		return results;
	}

}
