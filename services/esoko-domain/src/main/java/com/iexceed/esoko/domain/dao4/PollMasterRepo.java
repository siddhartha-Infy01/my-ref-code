package com.iexceed.esoko.domain.dao4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.PollMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PollMasterRepo extends AbstractRepoForEntity<PollMaster, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PollMaster entity, String String) {
		log.info("Inside PollMasterRepo -> exists");
		return super.exists(PollMaster.class, String);
	}

	public PollMaster merge(PollMaster entity) {
		log.info("Inside PollMasterRepo -> merge");
		return super.merge(entity);
	}

	public PollMaster save(PollMaster entity) {
		log.info("Inside PollMasterRepo -> save");
		return super.save(entity);
	}

	public PollMaster findOne(PollMaster entity, String id) {
		log.info("Inside PollMasterRepo -> findOne");
		return super.findOne(PollMaster.class, id);
	}

	public void delete(PollMaster entity) {
		log.info("Inside PollMasterRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PollMasterRepo -> deleteAll");
		super.delete(entities);
	}

	public PollMaster getPollWithName(String pollName) {
		log.info("Inside PollMasterRepo -> getPoll");
		log.debug("PollName: " + pollName);
		Query query = entityManager.createNativeQuery(
				getSql("getPollWithName"), PollMaster.class);
		query.setParameter(1, pollName);
		PollMaster entity = null;
		try {
			entity = (PollMaster) query.getSingleResult();
		} catch (NoResultException e) {
			log.error("No record found");
		} finally {
			entityManager.close();
		}
		return entity;
	}

	public List<Map<String, Object>> queryAllPolls(String pollType, String sortBy) {
		log.info("Inside PollMasterRepo -> queryAllPolls");
		log.debug("PollType: " + pollType);
		Query query = entityManager
				.createNativeQuery("{call queryAllPolls(?,?)}");
		query.setParameter(1, pollType);
		query.setParameter(2, sortBy);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		List<Object[]> list = query.getResultList();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("pollId", obj[i]);
					break;

				case 1:
					recordMap.put("pollName", obj[i]);
					break;

				case 2:
					recordMap.put("participants", obj[i]);
					break;

				case 3:
					recordMap.put("responses", obj[i]);
					break;

				case 4:
					recordMap.put("startDate", obj[i]);
					break;

				case 5:
					recordMap.put("endDate", obj[i]);
					break;

				case 6:
					recordMap.put("pollState", obj[i]);
					break;

				case 7:
					recordMap.put("pollQues", obj[i]);
					break;
				}
			}
			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public void updatePollState(String pollId, String modifiedBy, String status) {
		log.info("Inside PollMasterRepo -> updatePollStates");
		log.debug("PollId: " + pollId);
		log.debug("ModifiedBy: " + modifiedBy);
		log.debug("Status: " + status);
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifiedTs = df.format(dt);
		log.debug("Modified timestamp: " + modifiedTs);
		Query query = entityManager
				.createNativeQuery(getSql("updatePollStates"));		
		query.setParameter(1, status);
		query.setParameter(2, modifiedBy);
		query.setParameter(3, modifiedTs);
		query.setParameter(4, pollId);
		query.executeUpdate();
		entityManager.close();		
	}
}
