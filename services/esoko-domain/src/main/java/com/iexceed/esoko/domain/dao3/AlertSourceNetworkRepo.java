package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.AlertSourceNetwork;
import com.iexceed.esoko.domain.entity3.AlertSourceNetworkPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AlertSourceNetworkRepo extends
		AbstractRepoForEntity<AlertSourceNetwork, AlertSourceNetworkPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(AlertSourceNetwork alert, AlertSourceNetworkPK alertPk) {
		log.info("Inside AlertSourceNetworkRepo -> exists");
		return super.exists(AlertSourceNetwork.class, alertPk);
	}

	public AlertSourceNetwork merge(AlertSourceNetwork alert) {
		log.info("Inside AlertSourceNetworkRepo -> merge");
		return super.merge(alert);
	}

	public AlertSourceNetwork save(AlertSourceNetwork alert) {
		log.info("Inside AlertSourceNetworkRepo -> save");
		return super.save(alert);
	}

	public AlertSourceNetwork findOne(AlertSourceNetwork alert,
			AlertSourceNetworkPK alertPk) {
		log.info("Inside AlertSourceNetworkRepo -> findOne");
		return super.findOne(AlertSourceNetwork.class, alertPk);
	}

	public void delete(AlertSourceNetwork alert) {
		log.info("Inside AlertSourceNetworkRepo -> delete");
		super.delete(alert);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable alerts) {
		log.info("Inside AlertSourceNetworkRepo -> deleteAll");
		super.delete(alerts);
	}
	
	public List<AlertSourceNetwork> findAllAlertNetworks(String alertId) {
		log.info("Inside AlertSourceNetworkRepo -> findAllAlertNetworks");
		log.debug("AlertId: " + alertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllAlertNetworks"), AlertSourceNetwork.class);
		query.setParameter(1, alertId);
		List<AlertSourceNetwork> networks = query.getResultList();
		entityManager.close();
		return networks;
	}
}
