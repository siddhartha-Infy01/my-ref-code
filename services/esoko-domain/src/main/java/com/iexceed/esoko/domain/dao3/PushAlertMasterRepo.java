package com.iexceed.esoko.domain.dao3;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMasterPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PushAlertMasterRepo extends
		AbstractRepoForEntity<PushAlertMaster, PushAlertMasterPK> {

	private static Logger log = LoggerUtils.getLogger();

	public List<Map<String, Object>> queryAlerts(String sortBy,
			String networkId, String filter, String alertType) {
		log.info("query alerts");
		log.info("networkId::" + networkId);
		log.info("type::" + alertType);
		log.info("filter::" + filter);
		log.info("sort option::" + sortBy);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;

		if ("W".equalsIgnoreCase(alertType)) {
			log.info("edited sort::" + sortBy);
			if ("M".equalsIgnoreCase(filter)) {
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryWheather"));
					query.setParameter(1, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryWheather3"));
					query.setParameter(1, networkId);
				}
			} else if ("O".equalsIgnoreCase(filter)) {
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryWheather1"));
					query.setParameter(1, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryWheather4"));
					query.setParameter(1, networkId);
				}
			} else {
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryWheather2"));
					query.setParameter(1, networkId);
					query.setParameter(2, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryWheather5"));
					query.setParameter(1, networkId);
					query.setParameter(2, networkId);
				}
			}

		} else {
			log.info("edited sort::" + sortBy);
			if ("M".equalsIgnoreCase(filter)) {

				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts"));
					query.setParameter(1, alertType);
					query.setParameter(2, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts3"));
					query.setParameter(1, alertType);
					query.setParameter(2, networkId);
				}
			} else if ("O".equalsIgnoreCase(filter)) {
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts1"));
					query.setParameter(1, alertType);
					query.setParameter(2, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts4"));
					query.setParameter(1, alertType);
					query.setParameter(2, networkId);
				}
			} else {
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts2"));
					query.setParameter(1, networkId);
					query.setParameter(2, alertType);
					query.setParameter(3, networkId);
				} else {
					query = entityManager
							.createNativeQuery(getSql("queryAlerts5"));
					query.setParameter(1, networkId);
					query.setParameter(2, alertType);
					query.setParameter(3, networkId);
				}
			}
		}
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("push_alert_id", obj[i]);
				} else if (i == 1) {
					recordMap.put("name", obj[i]);
				} else if (i == 2) {
					recordMap.put("recipient_type", obj[i]);
				} else if (i == 3) {
					recordMap.put("recipient_id", obj[i]);
				} else if (i == 4) {
					recordMap.put("commodity_id", obj[i]);
				} else if (i == 5) {
					recordMap.put("location_id", obj[i]);
				} else if (i == 6) {
					recordMap.put("status", obj[i]);
				} else if (i == 7) {
					recordMap.put("edit_flag", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;

	}

	public boolean exists(PushAlertMaster entity, PushAlertMasterPK id) {
		log.info("Inside PushAlertMasterRepo -> exists");
		return super.exists(PushAlertMaster.class, id);
	}

	public PushAlertMaster merge(PushAlertMaster entity) {
		log.info("Inside PushAlertMasterRepo -> merge");
		return super.merge(entity);
	}

	public PushAlertMaster save(PushAlertMaster entity) {
		log.info("Inside PushAlertMasterRepo -> save");
		return super.save(entity);
	}

	public PushAlertMaster findOne(PushAlertMaster entity, PushAlertMasterPK id) {
		log.info("Inside PushAlertMasterRepo -> findOne");
		return super.findOne(PushAlertMaster.class, id);
	}

	public void delete(PushAlertMaster entity) {
		log.info("Inside PushAlertMasterRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PushAlertMasterRepo -> deleteAll");
		super.delete(entities);
	}

	public int getAlertCount(String alertName, String alertType,
			String networkId) {
		log.info("Inside PushAlertMasterRepo -> getAlertCount");
		log.debug("AlertName: " + alertName);
		log.debug("AlertType: " + alertType);
		log.debug("NetworkId: " + networkId);
		int count = 0;
		Query query = entityManager
				.createNativeQuery(getSql("findByAlertName"));
		query.setParameter(1, alertName);
		query.setParameter(2, alertType);
		query.setParameter(3, networkId);
		BigInteger result = (BigInteger) query.getSingleResult();
		count = result.intValue();
		entityManager.close();
		return count;
	}

	public void updatePushAlertStatus(String alertId, String modifiedBy,
			String status) {
		log.info("Inside PushAlertMasterRepo -> updatePushAlertStatus");
		log.debug("AlertId: " + alertId);
		log.debug("Status: " + status);
		log.debug("Modified by: " + modifiedBy);
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifiedTs = df.format(dt);
		log.debug("Modified timestamp: " + modifiedTs);
		Query query = entityManager
				.createNativeQuery(getSql("updatePushAlertStatus"));
		query.setParameter(1, status);
		query.setParameter(2, status);
		query.setParameter(3, modifiedBy);
		query.setParameter(4, modifiedTs);
		query.setParameter(5, alertId);
		query.executeUpdate();
		entityManager.close();
	}

	public List<Map<String, Object>> queryPush(String networkId) {
		log.info("query push messages");
		log.info("networkId::" + networkId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;
		query = entityManager.createNativeQuery(getSql("queryPush"));
		query.setParameter(1, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("push_alert_id", obj[i]);
				} else if (i == 1) {
					recordMap.put("start_date", obj[i]);
				} else if (i == 2) {
					recordMap.put("end_date", obj[i]);
				} else if (i == 3) {
					recordMap.put("text", obj[i]);
				} else if (i == 4) {
					recordMap.put("recipient_type", obj[i]);
				} else if (i == 5) {
					recordMap.put("recipient_id", obj[i]);
				} else if (i == 6) {
					recordMap.put("status", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;
	}

	public PushAlertMaster getAlertBasedonTrigger(PushAlertMasterPK triggerId) {
		return super.findOne(PushAlertMaster.class, triggerId);
	}

	public List<PushAlertMaster> findAllCropTipAlerts(String cropTipId) {
		log.info("Inside PushAlertMasterRepo -> findAllCropTipAlerts");
		log.info("CropTipId: " + cropTipId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllCropTipAlerts"), PushAlertMaster.class);
		query.setParameter(1, cropTipId + "%");
		List<PushAlertMaster> alertList = query.getResultList();
		return alertList;
	}

	public List<Map<String, Object>> queryAllAlertsByNetwork(String networkId) {
		log.info("Inside PushAlertMasterRepo -> queryAllAlertsByNetwork");
		log.info("networkId: " + networkId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;
		query = entityManager
				.createNativeQuery(getSql("queryAllAlertsByNetwork"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("push_alert_id", obj[i]);
				} else if (i == 1) {
					recordMap.put("alertName", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;
	}

	public List<PushAlertMaster> getPushAlrtMstrByPshAlrtId(String pushAlertId) {
		log.info("Inside PushAlertMasterRepo -> getPushAlrtMstrByPshAlrtId");
		log.debug("pushAlertId: " + pushAlertId);
		Query query = entityManager.createNativeQuery(
				getSql("getPushAlrtMstrByPshAlrtId"), PushAlertMaster.class);
		query.setParameter(1, pushAlertId);
		List<PushAlertMaster> masterList = query.getResultList();
		entityManager.close();
		if (masterList != null) {
			return masterList;
		} else {
			return null;
		}
	}

	public List<PushAlertMaster> queryAllPushAlertMaster() {
		log.info("Inside PushAlertMasterRepo -> queryAllPushAlertMaster");
		Query query = entityManager.createNativeQuery(
				getSql("getAllPushAlertMaster"), PushAlertMaster.class);
		List<PushAlertMaster> masterList = query.getResultList();
		entityManager.close();
		if (masterList != null) {
			return masterList;
		}
		return null;

	}
}