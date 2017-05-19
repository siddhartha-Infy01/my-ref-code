package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity.CommodityType;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommodityTypeRepo extends
		AbstractRepoForEntity<CommodityType, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<CommodityType> queryTypebyCommId(String commodityId) {
		log.info("Entered query commodity types by commodity id");
		log.info("commodityId::" + commodityId);
		Query query = entityManager.createNativeQuery(
				getSql("queryTypebyCommId"), CommodityType.class);
		query.setParameter(1, commodityId);

		List<CommodityType> resultList = query.getResultList();
		
		entityManager.close();
		return resultList;
	}
}
