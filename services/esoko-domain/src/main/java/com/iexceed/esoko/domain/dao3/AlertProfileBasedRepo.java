package com.iexceed.esoko.domain.dao3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AlertProfileBasedRepo extends
		AbstractRepoForEntity<AlertProfileBased, Integer> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(AlertProfileBased alert, Integer id) {
		log.info("Inside AlertProfileBasedRepo -> exists");
		return super.exists(AlertProfileBased.class, id);
	}

	public AlertProfileBased merge(AlertProfileBased alert) {
		log.info("Inside AlertProfileBasedRepo -> merge");
		return super.merge(alert);
	}

	public AlertProfileBased save(AlertProfileBased alert) {
		log.info("Inside AlertProfileBasedRepo -> save");
		return super.save(alert);
	}

	public AlertProfileBased findOne(AlertProfileBased alert, Integer id) {
		log.info("Inside AlertProfileBasedRepo -> findOne");
		return super.findOne(AlertProfileBased.class, id);
	}

	public void delete(AlertProfileBased alert) {
		log.info("Inside AlertProfileBasedRepo -> delete");
		super.delete(alert);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable alerts) {
		log.info("Inside AlertProfileBasedRepo -> deleteAll");
		super.delete(alerts);
	}

	public List<AlertProfileBased> findAllAlertProfiles(String alertId) {
		log.info("Inside AlertProfileBasedRepo -> findAllAlertProfiles");
		log.debug("AlertId: " + alertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllAlertProfiles"), AlertProfileBased.class);
		query.setParameter(1, alertId);
		List<AlertProfileBased> profiles = query.getResultList();
		entityManager.close();
		return profiles;
	}

	public List<Map<String, String>> priceSharingapproval(String uploadId,
			String networkId) {
		log.info("Inside AlertProfileBasedRepo -> priceSharingapproval");
		log.debug("uploadId: " + uploadId);
		log.debug("networkId: " + networkId);
		Query query = entityManager
				.createNativeQuery("{call priceSharingapproval(?,?)}");
		query.setParameter(1, uploadId);
		query.setParameter(2, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, String> recordMap = null;
		List<Map<String, String>> recordList = new ArrayList<Map<String, String>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, String>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("alertId", obj[i].toString());
				} else {
					recordMap.put("userid", obj[i].toString());
				}
			}
			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public List<AlertProfileBased> queryAlertProfileBasedByUsrID(String userId,String alertId) {
		log.info("Inside AlertProfileBasedRepo -> queryAlertProfileBased");
		log.debug("userId: " + userId);
		log.debug("alertId: " + alertId);
		List<AlertProfileBased> alertProfileBasedList;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("getAlertProfileBasedByUsrId"),
					AlertProfileBased.class);
			query.setParameter(1, userId);
			query.setParameter(2, alertId);
			alertProfileBasedList = query.getResultList();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return alertProfileBasedList;
	}
}
