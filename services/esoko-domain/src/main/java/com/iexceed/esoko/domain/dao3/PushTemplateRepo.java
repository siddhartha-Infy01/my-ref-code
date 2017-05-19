
package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity3.PushTemplate;
import com.iexceed.esoko.domain.entity3.PushTemplatePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PushTemplateRepo extends
		AbstractRepoForEntity<PushTemplate, PushTemplatePK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PushTemplate pshtem, PushTemplatePK pshtempk) {
		log.info("Inside PushTemplateRepo -> exists");
		return super.exists(PushTemplate.class, pshtempk);
	}

	public PushTemplate merge(PushTemplate pshtemp) {
		log.info("Inside PushTemplateRepo -> merge");
		return super.merge(pshtemp);
	}

	public PushTemplate save(PushTemplate pshtemp) {
		log.info("Inside PushTemplateRepo -> save");
		return super.save(pshtemp);
	}

	public PushTemplate findOne(PushTemplate pshtemp, PushTemplatePK pshtempk) {
		log.info("Inside PushTemplateRepo -> findOne");
		return super.findOne(PushTemplate.class, pshtempk);
	}

	public void delete(PushTemplate pshtemp) {
		log.info("Inside PushTemplateRepo -> delete");
		super.delete(pshtemp);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable pshtemp) {
		log.info("Inside PushTemplateRepo -> deleteAll");
		super.delete(pshtemp);
	}
	
	public List<PushTemplate> queryAllPushTemplates(String networkId,String sortBy) {
		log.info("Inside PushTemplateRepo -> queryAllPushTemplates");
		log.debug("networkId: " + networkId);
		Query query = null;
		if ("name".equalsIgnoreCase(sortBy))
		{
		 query = entityManager.createNativeQuery(
				getSql("queryAllPushTemplates"), PushTemplate.class);
		query.setParameter(1, networkId);
				
		}
		else
		{
			query = entityManager.createNativeQuery(
					getSql("queryAllPushTemplates1"), PushTemplate.class);
			query.setParameter(1, networkId);
		}
			List<PushTemplate> tmpltList = query.getResultList();
		entityManager.close();
		return tmpltList;
	}


}

