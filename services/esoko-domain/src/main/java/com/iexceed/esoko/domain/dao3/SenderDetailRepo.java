package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.SenderDetail;
import com.iexceed.esoko.domain.entity3.SenderDetailPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SenderDetailRepo extends
		AbstractRepoForEntity<SenderDetail, SenderDetailPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(SenderDetail entity, SenderDetailPK entitypk) {
		log.info("Inside SenderDetailRepo -> exists");
		return super.exists(SenderDetail.class, entitypk);
	}

	public SenderDetail merge(SenderDetail entity) {
		log.info("Inside SenderDetailRepo -> merge");
		return super.merge(entity);
	}

	public SenderDetail save(SenderDetail entity) {
		log.info("Inside SenderDetailRepo -> save");
		return super.save(entity);
	}

	public SenderDetail findOne(SenderDetail entity, SenderDetailPK entitypk) {
		log.info("Inside SenderDetailRepo -> findOne");
		return super.findOne(SenderDetail.class, entitypk);
	}

	public void delete(SenderDetail entity) {
		log.info("Inside SenderDetailRepo -> delete");
		super.delete(entity);
	}

	public List<SenderDetail> queryAllSenderId() {
		log.info("Entered query sender ids");
		Query query = entityManager.createNativeQuery(
				getSql("queryAllSenderId"), SenderDetail.class);

		List<SenderDetail> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<SenderDetail> querySenderDetail(String networkId,
			String application) {
		log.info("Inside SenderDetailRepo -> querySenderDetail");
		log.debug("NetworkId: " + networkId);
		log.debug("Appzlication: " + application);
		Query query = entityManager.createNativeQuery(
				getSql("querySenderDetail"), SenderDetail.class);
		query.setParameter(1, networkId);
		query.setParameter(2, application);
		List<SenderDetail> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

}
