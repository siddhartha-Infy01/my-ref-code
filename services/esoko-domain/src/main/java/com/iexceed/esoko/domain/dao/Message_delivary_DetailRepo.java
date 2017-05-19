package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.domain.entity.Message_delivary_DetailPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Message_delivary_DetailRepo
		extends
		AbstractRepoForEntity<Message_delivary_Detail, Message_delivary_DetailPK> {
	public static Logger log = LoggerUtils.getLogger();

	public List<Map<String, Object>> queryPushDelivery(Date fromdate,
			Date todate, String networkId) {
		log.info("query push delivery reports");
		log.info("networkId::" + networkId);
		log.info("todate::" + todate);
		log.info("fromdate::" + fromdate);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;
		query = entityManager.createNativeQuery(getSql("queryPushDelivery"));
		query.setParameter(1, networkId);
		query.setParameter(2, fromdate);
		query.setParameter(3, todate);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("schedule_id", obj[i]);
				} else if (i == 1) {
					recordMap.put("date", obj[i]);
				} else if (i == 2) {
					recordMap.put("message", obj[i]);
				} else if (i == 3) {
					recordMap.put("sent", obj[i]);
				} else if (i == 4) {
					recordMap.put("pending", obj[i]);
				} else if (i == 5) {
					recordMap.put("failed", obj[i]);
				} else if (i == 6) {
					recordMap.put("total", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;
	}

	public List<Map<String, Object>> queryAlertDelivery(Date fromdate,
			Date todate, String networkId, String sortBy, String messageType) {
		log.info("query push delivery reports");
		log.info("networkId::" + networkId);
		log.info("todate::" + todate);
		log.info("fromdate::" + fromdate);
		log.info("sortBy::" + sortBy);
		log.info("messageType::" + messageType);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;
		if (messageType.equalsIgnoreCase("A")) {
			if (sortBy.equalsIgnoreCase("date")) {
				query = entityManager
						.createNativeQuery(getSql("queryAlertDelivery"));
				query.setParameter(1, "%h:%i %p");
				query.setParameter(2, networkId);
				query.setParameter(3, fromdate);
				query.setParameter(4, todate);

			}

			else {
				query = entityManager
						.createNativeQuery(getSql("queryAlertDelivery2"));
				query.setParameter(1, "%h:%i %p");
				query.setParameter(2, networkId);
				query.setParameter(3, fromdate);
				query.setParameter(4, todate);
			}
		}

		else

		{
			if (sortBy.equalsIgnoreCase("date")) {
				query = entityManager
						.createNativeQuery(getSql("queryAlertDelivery1"));
				query.setParameter(1, "%h:%i %p");
				query.setParameter(2, messageType);
				query.setParameter(3, networkId);
				query.setParameter(4, fromdate);
				query.setParameter(5, todate);
			} else {
				query = entityManager
						.createNativeQuery(getSql("queryAlertDelivery3"));
				query.setParameter(1, "%h:%i %p");
				query.setParameter(2, messageType);
				query.setParameter(3, networkId);
				query.setParameter(4, fromdate);
				query.setParameter(5, todate);

			}

		}
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("schedule_id", obj[i]);
				} else if (i == 1) {
					recordMap.put("alert_name", obj[i]);
				} else if (i == 2) {
					recordMap.put("date", obj[i]);
				} else if (i == 3) {
					recordMap.put("time", obj[i]);
				} else if (i == 4) {
					recordMap.put("sent", obj[i]);
				} else if (i == 5) {
					recordMap.put("pending", obj[i]);
				} else if (i == 6) {
					recordMap.put("failed", obj[i]);
				} else if (i == 7) {
					recordMap.put("total", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;
	}

	public List<Message_delivary_Detail> queryPushAlertDlvrybyId(
			String pushAlertId, Date createdate) {

		log.info("query push and alert delivery details by id");

		Query query = entityManager.createNativeQuery(
				getSql("queryPushAlertDlvrybyId"),
				Message_delivary_Detail.class);
		query.setParameter(1, pushAlertId);
		query.setParameter(2, createdate);

		List<Message_delivary_Detail> resultList = query.getResultList();

		entityManager.close();
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public Message_delivary_Detail save(Message_delivary_Detail entity) {
		return super.save(entity);
	}

	public Message_delivary_Detail getMessageLogDetails(String messageId) {
		Query query = null;
		query = entityManager.createNativeQuery(getSql("queryMessageLogDetails"),
				Message_delivary_Detail.class);
		query.setParameter(1, messageId);
		return (Message_delivary_Detail) query.getSingleResult();
	}
}
