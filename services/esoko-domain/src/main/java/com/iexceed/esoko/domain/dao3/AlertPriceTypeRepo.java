package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.AlertPriceType;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AlertPriceTypeRepo extends
		AbstractRepoForEntity<AlertPriceType, Integer> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(AlertPriceType alert, Integer id) {
		log.info("Inside AlertPriceTypeRepo -> exists");
		return super.exists(AlertPriceType.class, id);
	}

	public AlertPriceType merge(AlertPriceType alert) {
		log.info("Inside AlertPriceTypeRepo -> merge");
		return super.merge(alert);
	}

	public AlertPriceType save(AlertPriceType alert) {
		log.info("Inside AlertPriceTypeRepo -> save");
		return super.save(alert);
	}

	public AlertPriceType findOne(AlertPriceType alert, Integer id) {
		log.info("Inside AlertPriceTypeRepo -> findOne");
		return super.findOne(AlertPriceType.class, id);
	}

	public void delete(AlertPriceType alert) {
		log.info("Inside AlertPriceTypeRepo -> delete");
		super.delete(alert);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable alerts) {
		log.info("Inside AlertPriceTypeRepo -> deleteAll");
		super.delete(alerts);
	}

	public List<AlertPriceType> findAllAlertPriceTypes(String alertId) {
		log.info("Inside AlertPriceTypeRepo -> findAllAlertPriceTypes");
		log.debug("AlertId: " + alertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllAlertPriceTypes"), AlertPriceType.class);
		query.setParameter(1, alertId);
		List<AlertPriceType> priceTypes = query.getResultList();
		entityManager.close();
		return priceTypes;
	}
}
