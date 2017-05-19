package com.iexceed.esoko.domain.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Occupation;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class OccupationRepo extends AbstractRepoForEntity<Occupation, String> {

	public static Logger log = LoggerUtils.getLogger();

	public List<Occupation> queryOccupationbyoccupationId(String occupationId) {
		log.info("Entered query occupations  by occupationid");
		log.info("occupationId::" + occupationId);
		List<Occupation> occupation;
		try {

			Query query = entityManager.createNativeQuery(
					getSql("queryOccupationbyoccupationId"), Occupation.class);
			query.setParameter(1, "%"+occupationId+"%");

			occupation = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return occupation;
	}

	public List<Occupation> queryOccupationbyParentId(String parentId) {
		log.info("Entered query occupations  by parentId");
		log.info("parentId::" + parentId);

		Query query = entityManager.createNativeQuery(
				getSql("queryOccupationbyParentId"), Occupation.class);
		query.setParameter(1, parentId);

		List<Occupation> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public int countOccuptnChilds(String parentId) {
		log.info("Entered query occupations child count by parent id");
		log.info("ParentID::" + parentId);
		List<Occupation> resultList = queryOccupationbyParentId(parentId);
		int countList = resultList.size();
		int countIterative = countList;

		while (countList > 0) {
			for (Occupation occupation : resultList) {
				countList = 0;
				countList = queryOccupationbyParentId(
						occupation.getOccupationId()).size();
				resultList = queryOccupationbyParentId(occupation
						.getOccupationId());
				countIterative = countIterative + countList;

			}
		}

		return countIterative;

	}

	public Occupation save(Occupation occptn) {
		super.save(occptn);
		return occptn;

	}

	public Occupation findOne(Occupation entity, String occupId) {

		return super.findOne(Occupation.class, occupId);
	}

	public boolean exists(Occupation entity, String occupId) {
		return super.exists(Occupation.class, occupId);

	}

	public Occupation merge(Occupation entity) {
		return super.merge(entity);
	}

	public Map<String, String> queryAllOccupations(String userData,
			String queryCriteria, String networkId) {
		log.info("Entered query all Occupations");
		log.debug("userData::" + userData);
		log.debug("queryCriteria::" + queryCriteria);
		Map<String, String> columnMap = null;
		Query query = null;
		if ("Y".equalsIgnoreCase(userData)) {
			log.info("Querying User Data::");
			query = entityManager.createNativeQuery(
					"{call findAllOccupationsForUser(?,?)}", Occupation.class);
			query.setParameter(1, queryCriteria);
			query.setParameter(2, networkId);
			List<Occupation> resultList = query.getResultList();
			columnMap = new HashMap<String, String>();
			for (Occupation obj : resultList) {
				log.debug("Creating mapped object::" + obj);
				columnMap.put(obj.getOccupationId(), "Y");
				log.debug("Key::" + obj.getOccupationId());

			}
		}

		entityManager.close();
		return columnMap;
	}

	public List<Occupation> queryAllRootOccupations() {

		log.info("Entered query Occupation by parent id");
		Query query = entityManager.createNativeQuery(
				getSql("findAllRootOccupations"), Occupation.class);
		List<Occupation> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public Map<String, List<Occupation>> buildOccupationHierarchy(
			String parentId) {

		Map<String, List<Occupation>> occupationMap = new LinkedHashMap<String, List<Occupation>>();
		List<Occupation> resultList = queryOccupationbyParentId(parentId);
		occupationMap.put(parentId, resultList);
		int countList = resultList.size();
		while (countList > 0) {
			for (Occupation occupation : resultList) {

				resultList = queryOccupationbyParentId(occupation
						.getOccupationId());
				occupationMap.put(occupation.getOccupationId(), resultList);
				countList = resultList.size();
			}
		}

		return occupationMap;

	}
}
