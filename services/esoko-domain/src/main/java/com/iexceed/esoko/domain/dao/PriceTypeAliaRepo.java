package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.PriceTypeAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class PriceTypeAliaRepo extends
		AbstractRepoForEntity<PriceTypeAlia, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<PriceTypeAlia> querypriceTypealiabyId(String priceTypeId) {
		log.info("Entered query price type alia by priceTypeId");
		log.info("priceTypeId::" + priceTypeId);
		List<PriceTypeAlia> pricetypealia;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("querypriceTypealiabyId"), PriceTypeAlia.class);
			query.setParameter(1, priceTypeId);
			pricetypealia = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return pricetypealia;
	}

	public PriceTypeAlia save(PriceTypeAlia pricetype) {
		super.save(pricetype);
		return pricetype;
	}

	public PriceTypeAlia findOne(PriceTypeAlia entity, String id) {

		return super.findOne(PriceTypeAlia.class, id);
	}

	public boolean exists(PriceTypeAlia entity, String id) {
		return super.exists(PriceTypeAlia.class, id);

	}

	public PriceTypeAlia merge(PriceTypeAlia entity) {
		return super.merge(entity);
	}
}
