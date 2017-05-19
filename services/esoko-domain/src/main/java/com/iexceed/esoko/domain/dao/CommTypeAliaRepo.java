package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommTypeAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommTypeAliaRepo extends AbstractRepoForEntity<CommTypeAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<CommTypeAlia> queryCommTypealiabyQtypeId(String qTypeId) {
		log.info("Entered query Commodities type alias by qTypeId");
		log.info("qTypeId::" + qTypeId);
		Query query = entityManager.createNativeQuery(getSql("queryCommTypealiabyQtypeId"),CommTypeAlia.class);
		query.setParameter(1, qTypeId);
		List<CommTypeAlia> resultList=query.getResultList();
		System.out.println("List:::" + resultList);
		entityManager.close();
		return resultList;
		
	}
}

