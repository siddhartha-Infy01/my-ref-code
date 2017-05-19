package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class CurrencyRepo extends AbstractRepoForEntity<Currency, String> {

	public static Logger log = LoggerUtils.getLogger();

	public Currency findByCcyCode(String ccyCode) {
		Currency currency;
		log.info("Entered query currencies");
		log.info("Currency::" + ccyCode);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findByCcyCode"), Currency.class);
			query.setParameter(1, ccyCode);

			currency = (Currency) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return currency;

	}

	public Currency findccByname(String name) {
		log.info("Entered query currencies by name");
		log.info("name::" + name);
		Currency currency;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findccByname"), Currency.class);
			query.setParameter(1, name);
			currency = (Currency) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return currency;

	}
	
	public Currency findccByCode(String code) {
		log.info("Entered query currencies by code");
		log.info("Code::" + code);
		Currency currency;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findccByCode"), Currency.class);
			query.setParameter(1, code);
			currency = (Currency) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return currency;
	}
	
	public List<Currency> queryALLcurrencies() {
		log.info("Entered query all currencies");
		Query query = entityManager.createNativeQuery(
				getSql("queryallcurrencies"), Currency.class);
		List<Currency> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}
	
	public Currency save(Currency ccy) {
		super.save(ccy);
		return ccy;
	}

	public Currency findOne(Currency entity, String currencyId) {
		return super.findOne(Currency.class, currencyId);
	}

	public boolean exists(Currency entity, String currencyId) {
		return super.exists(Currency.class, currencyId);
	}
	
	public Currency merge(Currency entity) {
		return super.merge(entity);
	}

}
