package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.KannelData;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class KannelDataRepo extends AbstractRepoForEntity<KannelData, String> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(KannelData data, String id) {
		log.info("Inside KannelDataRepo -> exists");
		return super.exists(KannelData.class, id);
	}

	public KannelData merge(KannelData data) {
		log.info("Inside KannelDataRepo -> merge");
		return super.merge(data);
	}

	public KannelData save(KannelData data) {
		log.info("Inside KannelDataRepo -> save");
		return super.save(data);
	}

	public KannelData findOne(KannelData data, String id) {
		log.info("Inside KannelDataRepo -> findOne");
		return super.findOne(KannelData.class, id);
	}

	public void delete(KannelData data) {
		log.info("Inside KannelDataRepo -> delete");
		super.delete(data);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable data) {
		log.info("Inside KannelDataRepo -> deleteAll");
		super.delete(data);
	}

	public List<KannelData> queryAllKannelData() {
		log.info("Inside KannelDataRepo -> queryAllKannelData");
		Query query = entityManager.createNativeQuery(
				getSql("queryAllKannels"), KannelData.class);
		List<KannelData> list = query.getResultList();
		return list;

	}
}
