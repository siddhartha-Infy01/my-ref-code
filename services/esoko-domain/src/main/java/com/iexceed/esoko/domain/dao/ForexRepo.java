package com.iexceed.esoko.domain.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class ForexRepo extends AbstractRepoForEntity<Forex, String> {

	public static Logger log = LoggerUtils.getLogger();

	public Forex queryfxRatesbyCcy(String baseCurrencyId, String quoteCurrencyId) {
		log.info("Entered query forex rates by base ccy and quote ccy");
		log.info("baseCurrencyId::" + baseCurrencyId);
		log.info("quoteCurrencyId::" + quoteCurrencyId);
		Forex forex;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryfxRatesbyCcy"), Forex.class);
			query.setParameter(1, baseCurrencyId);
			query.setParameter(2, quoteCurrencyId);
			forex = (Forex) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return forex;

	}

	public List<Forex> queryForex() {
		log.info("Entered query forex rates");

		Query query = entityManager.createNativeQuery(getSql("queryForex"),
				Forex.class);

		List<Forex> resultList = query.getResultList();
		System.out.println("List:::" + resultList);
		entityManager.close();
		return resultList;
	}
	
	
	public List<Map<String, Object>> queryAllFxRates(Date  forexDate) {

		Query query = entityManager.createNativeQuery(getSql("queryAllFxRates"));
		query.setParameter(1, forexDate);
		query.setParameter(2, forexDate);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("base_currency_id", obj[i]);

				} 
				else if(i == 1)
				{
					recordMap.put("currency_name", obj[i]);
				
				}
				else if (i == 2) {
					recordMap.put("rate", obj[i]);
				}
				
				else {
					recordMap.put("Date", obj[i]);
				}

			}
			
			recordList.add(recordMap);
		}
		
		

		entityManager.close();
		return recordList;
	}

	public Forex save(Forex forex) {
		super.save(forex);
		return forex;

	}

	public Forex findOne(Forex entity, String id) {

		return super.findOne(Forex.class, id);
	}

	public boolean exists(Forex entity, String id) {
		return super.exists(Forex.class, id);

	}

	public Forex merge(Forex entity) {
		return super.merge(entity);
	}

}
