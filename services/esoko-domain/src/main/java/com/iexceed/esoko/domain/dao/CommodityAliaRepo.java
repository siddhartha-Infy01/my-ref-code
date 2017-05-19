package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommodityAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommodityAliaRepo extends AbstractRepoForEntity<CommodityAlia, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	
	public List<CommodityAlia> queryCommoditiesaliabyCommId(String commodityId) {
		log.info("Entered query Commodities alias by commodity id");
		log.info("Commodity::" + commodityId);
		Query query = entityManager.createNativeQuery(getSql("queryCommoditiesaliabyCommId"),CommodityAlia.class);
		query.setParameter(1, commodityId);
		List<CommodityAlia> resultList=query.getResultList();
		entityManager.close();
		return resultList;
		
	}
	
	public CommodityAlia merge(CommodityAlia alias) {
		log.info("Inside CommodityAliaRepo -> merge");
		return super.merge(alias);
	}

}
