package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommAttributeAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommAttributeAliaRepo extends AbstractRepoForEntity<CommAttributeAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<CommAttributeAlia> queryCommAtrraliabyAttrId(String attributeId) {
		log.info("Entered query Commodities atrribute alias by atrribute id");
		log.info("attributeId::" + attributeId);
		
		
		Query query = entityManager.createNativeQuery(getSql("queryCommAtrraliabyAttrId"),CommAttributeAlia.class);
		
		query.setParameter(1, attributeId);
		List<CommAttributeAlia> resultList=query.getResultList();
	    
		
		entityManager.close();
		return resultList;
		
	}
}

