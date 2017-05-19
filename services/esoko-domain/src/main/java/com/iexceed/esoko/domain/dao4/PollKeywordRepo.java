package com.iexceed.esoko.domain.dao4;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.PollKeyword;
import com.iexceed.esoko.domain.entity4.PollKeywordPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PollKeywordRepo extends
		AbstractRepoForEntity<PollKeyword, PollKeywordPK> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PollKeyword entity, PollKeywordPK PollKeywordPK) {
		log.info("Inside PollKeywordRepo -> exists");
		return super.exists(PollKeyword.class, PollKeywordPK);
	}

	public PollKeyword merge(PollKeyword entity) {
		log.info("Inside PollKeywordRepo -> merge");
		return super.merge(entity);
	}

	public PollKeyword save(PollKeyword entity) {
		log.info("Inside PollKeywordRepo -> save");
		return super.save(entity);
	}

	public PollKeyword findOne(PollKeyword entity, PollKeywordPK id) {
		log.info("Inside PollKeywordRepo -> findOne");
		return super.findOne(PollKeyword.class, id);
	}

	public void delete(PollKeyword entity) {
		log.info("Inside PollKeywordRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PollKeywordRepo -> deleteAll");
		super.delete(entities);
	}

	public List<PollKeyword> getPollKeywordsForPollId(String pollId) {
		log.info("Inside PollKeywordRepo -> getPollKeywordsForPollId");
		log.debug("PollId: " + pollId);
		Query query = entityManager.createNativeQuery(
				getSql("getPollKeywordsForPollId"), PollKeyword.class);
		query.setParameter(1, pollId);
		List<PollKeyword> entityList = query.getResultList();
		entityManager.close();
		return entityList;
	}

	public List<PollKeyword> getPollKeywordsForQues(String pollId, String quesNo) {
		log.info("Inside PollKeywordRepo -> getPollKeywordsForPollId");
		log.debug("PollId: " + pollId);
		Query query = entityManager.createNativeQuery(
				getSql("getPollKeywordsForQues"), PollKeyword.class);
		query.setParameter(1, pollId);
		query.setParameter(2, quesNo);
		List<PollKeyword> entityList = query.getResultList();
		entityManager.close();
		return entityList;
	}

	public boolean validatedKeyword(String id, String networkId,
			String keyword, String channel, String appName) {
		boolean status = false;
		log.info("Inside PollKeywordRepo -> validatedKeyword");
		log.debug("Id: " + id);
		log.debug("NetworkId: " + networkId);
		log.debug("Keyword: " + keyword);
		log.debug("Channel: " + channel);
		log.debug("AppName: " + appName);
		Query query = entityManager
				.createNativeQuery("{call validateKeyword(?,?,?,?,?)}");
		if (id == null) {
			query.setParameter(1, "");
		} else {
			query.setParameter(1, id);
		}
		if (networkId == null) {
			query.setParameter(2, "");
		} else {
			query.setParameter(2, networkId);
		}
		if (keyword == null) {
			query.setParameter(3, "");
		} else {
			query.setParameter(3, keyword);
		}
		if (channel == null) {
			query.setParameter(4, "");
		} else {
			query.setParameter(4, channel);
		}
		if (appName == null) {
			query.setParameter(5, "");
		} else {
			query.setParameter(5, appName.toUpperCase());
		}
		List<Object[]> list = query.getResultList();
		if (list.size() == 0) {
			status = true;
		}
		return status;
	}
}
