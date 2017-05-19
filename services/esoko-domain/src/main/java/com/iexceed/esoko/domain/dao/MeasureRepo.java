package com.iexceed.esoko.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Measure;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class MeasureRepo extends AbstractRepoForEntity<Measure, String> {

	public static Logger log = LoggerUtils.getLogger();
	
	public Measure queryMeasurebyMeasureId(String measureId) {
		log.info("Entered query measures by measureId");
		log.info("measureId::" + measureId);
		Measure measure;
		try
		{
		Query query = entityManager.createNativeQuery(
				getSql("queryMeasurebyMeasureId"), Measure.class);
		query.setParameter(1, measureId);
		measure = (Measure) query.getSingleResult();
	} catch (NoResultException e) {
		return null;
	}
		entityManager.close();
		return measure;

	}
	
	
	public List<Measure> queryAllSystemMeasures() {
		log.info("Entered query measures by measureId");
		List<Measure> measure;
		try
		{
		Query query = entityManager.createNativeQuery(
				getSql("queryAllSystemMeasures"), Measure.class);
		measure = query.getResultList();
	} catch (NoResultException e) {
		return null;
	}
		entityManager.close();
		return measure;

	}
	
	
	public List<Measure> queryMeasurebyType(String type) {
		log.info("Entered query measures by type");
		log.info("measureId::" + type);
		Query query = entityManager.createNativeQuery(getSql("queryMeasurebyType"), Measure.class);
		query.setParameter(1, type);
		List<Measure> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}
	
	public Measure save(Measure measure) {
		super.save(measure);
		return measure;

	}
	
	public Measure findOne(Measure entity, String id) {
		log.info("Inside MeasureRepo -> findOne");
		return super.findOne(Measure.class, id);
	}
	
}

