package com.iexceed.esoko.domain.dao4;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.PollAlia;
import com.iexceed.esoko.domain.entity4.PollAliaPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PollAliaRepo extends AbstractRepoForEntity<PollAlia, PollAliaPK> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PollAlia entity, PollAliaPK PollAliaPK) {
		log.info("Inside PollAliaRepo -> exists");
		return super.exists(PollAlia.class, PollAliaPK);
	}

	public PollAlia merge(PollAlia entity) {
		log.info("Inside PollAliaRepo -> merge");
		return super.merge(entity);
	}

	public PollAlia save(PollAlia entity) {
		log.info("Inside PollAliaRepo -> save");
		return super.save(entity);
	}

	public PollAlia findOne(PollAlia entity, PollAliaPK id) {
		log.info("Inside PollAliaRepo -> findOne");
		return super.findOne(PollAlia.class, id);
	}

	public void delete(PollAlia entity) {
		log.info("Inside PollAliaRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PollAliaRepo -> deleteAll");
		super.delete(entities);
	}

	public List<PollAlia> getPollAliasForPollId(String pollId) {
		log.info("Inside PollAliaRepo -> getPollAliasForPollId");
		log.debug("PollId: " + pollId);
		Query query = entityManager.createNativeQuery(
				getSql("getPollAliasForPollId"), PollAlia.class);
		query.setParameter(1, pollId);
		List<PollAlia> entityList = query.getResultList();
		entityManager.close();
		return entityList;
	}

	public List<PollAlia> getPollAliasForKeyword(String pollId, String keyword) {
		log.info("Inside PollAliaRepo -> getPollAliasForPollId");
		log.debug("PollId: " + pollId);
		log.debug("Keyword: " + keyword);
		Query query = entityManager.createNativeQuery(
				getSql("getPollAliasForKeyword"), PollAlia.class);
		query.setParameter(1, pollId);
		query.setParameter(2, keyword);
		List<PollAlia> entityList = query.getResultList();
		entityManager.close();
		return entityList;
	}
}
