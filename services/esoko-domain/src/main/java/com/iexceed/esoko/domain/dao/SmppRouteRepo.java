package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.SmppRoute;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SmppRouteRepo extends AbstractRepoForEntity<SmppRoute, String> {
	private static Logger log = LoggerUtils.getLogger();

	public List<Map<String, Object>> querySmsRates(String locationId) {
		log.info("Inside SmppRouteRepo -> querySmsRates");
		Query query = entityManager.createNativeQuery(getSql("querySmsRates"));
		query.setParameter(1, locationId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("operatorId", obj[i]);
					break;
				case 1:
					recordMap.put("price", obj[i]);
					break;
				case 2:
					recordMap.put("currency", obj[i]);
					break;
				case 3:
					recordMap.put("premium", obj[i]);
					break;
				}
			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public boolean exists(SmppRoute entity, String routeId) {
		log.info("Inside SmppRouteRepo -> exists");
		return super.exists(SmppRoute.class, routeId);
	}

	public SmppRoute merge(SmppRoute entity) {
		log.info("Inside SmppRouteRepo -> merge");
		return super.merge(entity);
	}

	public SmppRoute save(SmppRoute entity) {
		log.info("Inside SmppRouteRepo -> save");
		return super.save(entity);
	}

	public SmppRoute findOne(SmppRoute entity, String routeId) {
		log.info("Inside SmppRouteRepo -> findOne");
		return super.findOne(SmppRoute.class, routeId);
	}

	public void delete(SmppRoute entity) {
		log.info("Inside SmppRouteRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside SmppRouteRepo -> deleteAll");
		super.delete(entities);
	}

	public SmppRoute queryRoute(String operatorId, String gatewayId,
			String networkId, String type, String direction) {
		log.info("Inside SmppRouteRepo -> queryRoute");
		Query query = null;
		if (networkId == null) {
			query = entityManager.createNativeQuery(getSql("getSmppRoute1"),
					SmppRoute.class);
			query.setParameter(1, operatorId);
			query.setParameter(2, gatewayId);
			query.setParameter(3, type);
			query.setParameter(4, direction);
		} else {
			query = entityManager.createNativeQuery(getSql("getSmppRoute2"),
					SmppRoute.class);
			query.setParameter(1, operatorId);
			query.setParameter(2, gatewayId);
			query.setParameter(3, networkId);
			query.setParameter(4, type);
			query.setParameter(5, direction);
		}
		SmppRoute route = null;
		try {
			route = (SmppRoute) query.getSingleResult();
		} catch (NoResultException e) {
			log.error("No record found");
		}
		return route;
	}
}
