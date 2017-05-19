package com.iexceed.esoko.domain.dao2;


import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity2.SurveyTemplate;
import com.iexceed.esoko.domain.entity2.SurveyTemplatePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;


@Component
@SuppressWarnings("unchecked")
public class SurveyTemplateRepo extends AbstractRepoForEntity<SurveyTemplate, SurveyTemplatePK> {
	private static Logger log = LoggerUtils.getLogger();
	
	public boolean exists(SurveyTemplate surveytemplate, SurveyTemplatePK surveytemplatepk) {
		log.info("Inside SurveyTemplateRepo -> exists");
		return super.exists(SurveyTemplate.class, surveytemplatepk);
	}

	public SurveyTemplate merge(SurveyTemplate surveytemplate) {
		log.info("Inside SurveyTemplateRepo -> merge");
		return super.merge(surveytemplate);
	}

	public SurveyTemplate save(SurveyTemplate surveytemplate) {
		log.info("Inside SurveyTemplateRepo -> save");
		return super.save(surveytemplate);
	}

	public SurveyTemplate findOne(SurveyTemplate surveytemplate, SurveyTemplatePK surveytemplatepk) {
		log.info("Inside SurveyTemplateRepo -> findOne");
		return super.findOne(SurveyTemplate.class, surveytemplatepk);
	}
	
	public void delete(SurveyTemplate surveytemplate) {
		log.info("Inside SurveyTemplateRepo -> delete");
		 super.delete(surveytemplate);
	}
	public List<SurveyTemplate> queryAllTemplates(String networkId) {
		log.info("Inside SurveyTemplateRepo -> queryTemplates");
		log.debug("networkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryAllTemplates"), SurveyTemplate.class);
		query.setParameter(1, networkId);
		List<SurveyTemplate> tmpltList = query.getResultList();
		entityManager.close();
		return tmpltList;
	}
}