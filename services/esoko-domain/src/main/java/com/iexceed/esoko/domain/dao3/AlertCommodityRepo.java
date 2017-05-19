package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.AlertCommodity;
import com.iexceed.esoko.domain.entity3.AlertCommodityPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AlertCommodityRepo extends
		AbstractRepoForEntity<AlertCommodity, AlertCommodityPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(AlertCommodity alert, AlertCommodityPK alertPk) {
		log.info("Inside AlertCommodityRepo -> exists");
		return super.exists(AlertCommodity.class, alertPk);
	}

	public AlertCommodity merge(AlertCommodity alert) {
		log.info("Inside AlertCommodityRepo -> merge");
		return super.merge(alert);
	}

	public AlertCommodity save(AlertCommodity alert) {
		log.info("Inside AlertCommodityRepo -> save");
		return super.save(alert);
	}

	public AlertCommodity findOne(AlertCommodity alert, AlertCommodityPK alertPk) {
		log.info("Inside AlertCommodityRepo -> findOne");
		return super.findOne(AlertCommodity.class, alertPk);
	}

	public void delete(AlertCommodity alert) {
		log.info("Inside AlertCommodityRepo -> delete");
		super.delete(alert);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable alerts) {
		log.info("Inside AlertCommodityRepo -> deleteAll");
		super.delete(alerts);
	}

	public List<AlertCommodity> findAllAlertCommodities(String alertId) {
		log.info("Inside AlertCommodityRepo -> findAllAlertCommodities");
		log.debug("AlertId: " + alertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllAlertCommodities"), AlertCommodity.class);
		query.setParameter(1, alertId);
		List<AlertCommodity> commodities = query.getResultList();
		entityManager.close();
		return commodities;
	}
}
