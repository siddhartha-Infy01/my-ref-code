package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.OperatorTemplate;
import com.iexceed.esoko.domain.entity3.OperatorTemplatePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class OperatorTemplateRepo extends
		AbstractRepoForEntity<OperatorTemplate, OperatorTemplatePK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(OperatorTemplate entity, OperatorTemplatePK templatePK) {
		log.info("Inside OperatorTemplateRepo -> exists");
		return super.exists(OperatorTemplate.class, templatePK);
	}

	public OperatorTemplate merge(OperatorTemplate entity) {
		log.info("Inside OperatorTemplateRepo -> merge");
		return super.merge(entity);
	}

	public OperatorTemplate save(OperatorTemplate entity) {
		log.info("Inside OperatorTemplateRepo -> save");
		return super.save(entity);
	}

	public OperatorTemplate findOne(OperatorTemplate entity,
			OperatorTemplatePK templatePK) {
		log.info("Inside OperatorTemplateRepo -> findOne");
		return super.findOne(OperatorTemplate.class, templatePK);
	}
	
	public List<OperatorTemplate> findAll(){
		return (List<OperatorTemplate>) super.findAll();
	}

	public void delete(OperatorTemplate entity) {
		log.info("Inside OperatorTemplateRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside OperatorTemplateRepo -> deleteAll");
		super.delete(entities);
	}

	public List<OperatorTemplate> getTemplate(String operatorId) {
		log.info("Inside OperatorTemplateRepo -> getTemplate");
		log.debug("OperatorId: " + operatorId);
		Query query = entityManager.createNativeQuery(
				getSql("queryOperatorTemplate"), OperatorTemplate.class);
		query.setParameter(1, operatorId);			
		List<OperatorTemplate> template = query.getResultList();
		entityManager.close();
		return template;
	}
	
	public List<OperatorTemplate> getAllTemplates() {
		log.info("Inside OperatorTemplateRepo -> getAllTemplates");		
		Query query = entityManager.createNativeQuery(
				getSql("queryAllOprTemplates"), OperatorTemplate.class);				
		List<OperatorTemplate> template = query.getResultList();
		entityManager.close();
		return template;
	}
}
