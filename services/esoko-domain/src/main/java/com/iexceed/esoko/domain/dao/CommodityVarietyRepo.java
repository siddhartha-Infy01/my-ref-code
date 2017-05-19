package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommodityVariety;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommodityVarietyRepo extends
		AbstractRepoForEntity<CommodityVariety, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<CommodityVariety> queryVarietiesbyCommId(String commodityId) {
		log.info("Entered query  varities by commodity id");
		log.info("commodityId::" + commodityId);
		Query query = entityManager.createNativeQuery(
				getSql("queryVarietiesbyCommId"), CommodityVariety.class);
		query.setParameter(1, commodityId);
		List<CommodityVariety> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<CommodityVariety> queryVarietiesByParentId(String parentId) {
		log.info("queryVarietiesByParentId");
		log.info("parentId::" + parentId);
		Query query = entityManager.createNativeQuery(
				getSql("queryVarietiesByParentId"), CommodityVariety.class);
		query.setParameter(1, parentId);
		List<CommodityVariety> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}
	
	
}
