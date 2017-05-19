package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.PriceType;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class PriceTypeRepo extends AbstractRepoForEntity<PriceType, String> {

	public static Logger log = LoggerUtils.getLogger();

	public PriceType querypriceTypebyId(String priceTypeId) {
		log.info("Entered query price type  by priceTypeId");
		log.info("priceTypeId::" + priceTypeId);
		PriceType pricetype;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("querypriceTypebyId"), PriceType.class);
			query.setParameter(1, priceTypeId);
			pricetype = (PriceType) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return pricetype;

	}

	public List<PriceType> queryAllPriceType() {
		log.info("Entered query price type  by priceTypeId");
		List<PriceType> pricetype;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryAllPriceType"), PriceType.class);
			pricetype = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return pricetype;

	}

	public PriceType save(PriceType pricetype) {
		super.save(pricetype);
		return pricetype;
	}

	public PriceType findOne(PriceType entity, String id) {

		return super.findOne(PriceType.class, id);
	}

	public boolean exists(PriceType entity, String id) {
		return super.exists(PriceType.class, id);

	}

	public PriceType merge(PriceType entity) {
		return super.merge(entity);
	}
}
