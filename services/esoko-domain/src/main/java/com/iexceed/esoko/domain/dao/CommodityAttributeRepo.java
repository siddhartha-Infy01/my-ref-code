package com.iexceed.esoko.domain.dao;

import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommodityAttribute;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class CommodityAttributeRepo extends AbstractRepoForEntity<CommodityAttribute, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	public List<CommodityAttribute> queryAttributebyCommId(String commodityId) {
		log.info("Entered query commodity attributes by commodity id");
		log.info("commodityId::" + commodityId);
		Query query = entityManager.createNativeQuery(getSql("queryAttributebyCommId"), CommodityAttribute.class);
		query.setParameter(1, commodityId);
			
List<CommodityAttribute> resultList=query.getResultList();
entityManager.close();
		return resultList;
	}
}

