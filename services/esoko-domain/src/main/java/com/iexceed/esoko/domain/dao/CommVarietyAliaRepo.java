package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommVarietyAlia;

import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommVarietyAliaRepo extends AbstractRepoForEntity<CommVarietyAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<CommVarietyAlia> queryCommvaritiesaliabyVarId(String varietyId) {
		log.info("Entered query Commodities variety alias by varietyId");
		log.info("varietyId::" + varietyId);
		Query query = entityManager.createNativeQuery(getSql("queryCommvaritiesaliabyVarId"),CommVarietyAlia.class);
		query.setParameter(1, varietyId);
		List<CommVarietyAlia> resultList=query.getResultList();
		System.out.println("List:::" + resultList);
		entityManager.close();
		return resultList;
		
	}
}

