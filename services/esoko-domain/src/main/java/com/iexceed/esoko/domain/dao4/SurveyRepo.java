package com.iexceed.esoko.domain.dao4;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.Survey;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Repository
@SuppressWarnings("unchecked")
public class SurveyRepo extends AbstractRepoForEntity<Survey, Integer> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(Survey entity, int id) {
		log.info("Inside SurveyRepo -> exists");
		return super.exists(Survey.class, id);
	}

	public Survey merge(Survey entity) {
		log.info("Inside SurveyRepo -> merge");
		return super.merge(entity);
	}

	public Survey save(Survey entity) {
		log.info("Inside SurveyRepo -> save");
		return super.save(entity);
	}

	public Survey findOne(Survey entity, int id) {
		log.info("Inside SurveyRepo -> findOne");
		return super.findOne(Survey.class, id);
	}

	public void delete(Survey entity) {
		log.info("Inside SurveyRepo -> delete");
		super.delete(entity);
	}

	public Survey queryByNwkIdUserId(String networkId, String userId,
			String surveyName, String phase) {
		log.info("Inside SurveyRepo -> queryByNwkIdUserId");
		log.debug("networkId: " + networkId);
		log.debug("UserId: " + userId);
		log.debug("surveyName: " + surveyName);
		log.debug("phase: " + phase);
		Survey survey = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("querySurveyByNwkIdUserId"), Survey.class);
			query.setParameter(1, networkId);
			query.setParameter(2, userId);
			query.setParameter(3, surveyName);
			query.setParameter(4, phase);
			query.setParameter(5, networkId);
			query.setParameter(6, userId);
			query.setParameter(7, surveyName);
			query.setParameter(8, phase);
			survey = (Survey) query.getSingleResult();
		} catch (NoResultException e) {
			log.error(e.getMessage());
		}
		entityManager.close();
		return survey;
	}

	
}
