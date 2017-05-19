package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Profile_Sharing_type;
import com.iexceed.esoko.domain.entity.Profile_Sharing_typePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class Profile_Sharing_typeRepo extends
		AbstractRepoForEntity<Profile_Sharing_type, Profile_Sharing_typePK> {
	public static Logger log = LoggerUtils.getLogger();

	public boolean exists(Profile_Sharing_type profileShare,
			Profile_Sharing_typePK profileSharePk) {
		log.info("Inside Profile_Sharing_typeRepo -> exists");
		return super.exists(Profile_Sharing_type.class, profileSharePk);
	}

	public Profile_Sharing_type merge(Profile_Sharing_type profileShare) {
		log.info("Inside Profile_Sharing_typeRepo -> merge");
		return super.merge(profileShare);
	}

	public Profile_Sharing_type save(Profile_Sharing_type profileShare) {
		log.info("Inside Profile_Sharing_typeRepo -> save");
		return super.save(profileShare);
	}

	public Profile_Sharing_type findOne(Profile_Sharing_type profileShare,
			Profile_Sharing_typePK profileSharePk) {
		log.info("Inside Profile_Sharing_typeRepo -> findOne");
		return super.findOne(Profile_Sharing_type.class, profileSharePk);
	}

	public void delete(Profile_Sharing_type profileShare) {
		log.info("Inside Profile_Sharing_typeRepo -> delete");
		super.delete(profileShare);
	}

	public List<Profile_Sharing_type> querySharingProfile(String networkId) {
		log.info("Entered query Sharing Profile");
		List<Profile_Sharing_type> resultList = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("fetchNwkProfiles"), Profile_Sharing_type.class);
			query.setParameter(1, networkId);
			resultList = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return resultList;

	}

	public List<Map<String, Object>> queryProfileDetails(String networkId) {
		log.info("Inside Profile_Sharing_type -> queryProfileDetails");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("queryProfileDetails"));
		query.setParameter(1, networkId);
		List<Object[]> profileList = query.getResultList();
		log.debug("pplList -> " + profileList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : profileList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("Profile", obj[i]);
					break;
				case 1:
					recordMap.put("Name", obj[i]);
					break;
				case 2:
					recordMap.put("birth_year", obj[i]);
					break;
				case 3:
					recordMap.put("gender", obj[i]);
					break;
				case 4:
					recordMap.put("mobile", obj[i]);
					break;
				case 5:
					recordMap.put("address", obj[i]);
					break;
				case 6:
					recordMap.put("company", obj[i]);
					break;
				case 7:
					recordMap.put("language", obj[i]);
					break;
				case 8:
					recordMap.put("email", obj[i]);
					break;
				case 9:
					recordMap.put("currency", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public int approveSharingProfileDtls(String fromNetwork, String toNetwork,
			String shareItem, String approveFlag, String peopleShareType,
			String peopleMode, String userId) {
		int returnCount = 0;
		log.info("Inside Profile_Sharing_type -> approveSharingProfileDtls");
		log.debug("NetworkId: " + fromNetwork);
		Query query = entityManager
				.createNativeQuery("{call approveShare(?,?,?,?,?,?,?)}");
		query.setParameter(1, fromNetwork);
		query.setParameter(2, toNetwork);
		query.setParameter(3, shareItem);
		query.setParameter(4, approveFlag);
		query.setParameter(5, peopleShareType);
		query.setParameter(6, peopleMode);
		query.setParameter(7, userId);
		returnCount = query.executeUpdate();
		entityManager.close();
		return returnCount;
	}
	
	public List<Map<String, Object>> queryNwkPeopleCounts(String networkId) {
		log.info("Inside Profile_Sharing_type -> queryNwkPeopleCounts");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("queryNwkPeopleCounts"));
		query.setParameter(1, networkId);
		List<Object[]> nwkDtls = query.getResultList();
		log.debug("pplList -> " + nwkDtls);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : nwkDtls) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("count", obj[i]);
					break;
				case 1:
					recordMap.put("network", obj[i]);
					break;
				case 2:
					recordMap.put("location", obj[i]);
					break;
				case 3:
					recordMap.put("image", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}
}
