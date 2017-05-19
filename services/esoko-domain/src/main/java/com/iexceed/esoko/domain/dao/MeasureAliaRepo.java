package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity.MeasureAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class MeasureAliaRepo extends AbstractRepoForEntity<MeasureAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<MeasureAlia> queryMeasureAliabymeasureId(String measureId) {
		log.info("Entered query measures alias by measureId");
		log.info("measureId::" + measureId);
		Query query = entityManager.createNativeQuery(getSql("queryMeasureAliabymeasureId"), MeasureAlia.class);
		query.setParameter(1, measureId);
		List<MeasureAlia> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}
}


