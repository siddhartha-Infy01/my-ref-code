package com.iexceed.esoko.domain.dao4;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.PollDetail;
import com.iexceed.esoko.domain.entity4.PollDetailPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PollDetailRepo extends
		AbstractRepoForEntity<PollDetail, PollDetailPK> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PollDetail entity, PollDetailPK PollDetailPK) {
		log.info("Inside PollDetailRepo -> exists");
		return super.exists(PollDetail.class, PollDetailPK);
	}

	public PollDetail merge(PollDetail entity) {
		log.info("Inside PollDetailRepo -> merge");
		return super.merge(entity);
	}

	public PollDetail save(PollDetail entity) {
		log.info("Inside PollDetailRepo -> save");
		return super.save(entity);
	}

	public PollDetail findOne(PollDetail entity, PollDetailPK id) {
		log.info("Inside PollDetailRepo -> findOne");
		return super.findOne(PollDetail.class, id);
	}

	public void delete(PollDetail entity) {
		log.info("Inside PollDetailRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PollDetailRepo -> deleteAll");
		super.delete(entities);
	}

	public List<PollDetail> getPollDetailsForPollId(String pollId) {
		log.info("Inside PollDetailRepo -> getPollDetails");
		log.debug("PollId: " + pollId);
		Query query = entityManager.createNativeQuery(
				getSql("getPollDetailsForPollId"), PollDetail.class);
		query.setParameter(1, pollId);
		List<PollDetail> entityList = query.getResultList();
		entityManager.close();
		return entityList;
	}
}
