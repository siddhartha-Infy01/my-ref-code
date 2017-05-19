package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Language;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class LanguageRepo extends AbstractRepoForEntity<Language, String> {

	public static Logger log = LoggerUtils.getLogger();

	public Language queryLaguages(String code) {
		log.info("Entered query language by code");
		log.info("Commodity::" + code);
		Language language;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryLaguages"), Language.class);
			query.setParameter(1, code);
			language = (Language) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return language;

	}

	public List<Language> queryALLLanguage() {
		log.info("Entered query all currencies");

		Query query = entityManager.createNativeQuery(
				getSql("queryALLLanguage"), Language.class);
		List<Language> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public Language save(Language lang) {
		super.save(lang);
		return lang;

	}

	public Language findOne(Language entity, String languageId) {

		return super.findOne(Language.class, languageId);
	}

	public boolean exists(Language entity, String languageId) {
		return super.exists(Language.class, languageId);

	}

	public Language merge(Language entity) {
		return super.merge(entity);
	}
}
