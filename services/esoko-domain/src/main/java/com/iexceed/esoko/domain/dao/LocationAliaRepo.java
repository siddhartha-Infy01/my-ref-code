package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity.LocationAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class LocationAliaRepo extends AbstractRepoForEntity<LocationAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<LocationAlia> queryLocationsAliabyLocId(String locationId) {
		log.info("Entered query locations alias by location id");
		log.info("location::" + locationId);
		Query query = entityManager.createNativeQuery(getSql("queryLocationsAliabyLocId"),LocationAlia.class);
		query.setParameter(1, locationId);
		List<LocationAlia> resultList=query.getResultList();
		System.out.println("List:::" + resultList);
		entityManager.close();
		return resultList;
		
	}
}

