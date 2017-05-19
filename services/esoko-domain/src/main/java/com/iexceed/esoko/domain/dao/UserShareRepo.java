package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Currency;

import com.iexceed.esoko.domain.entity.UserShare;
import com.iexceed.esoko.domain.entity.UserSharePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class UserShareRepo extends
		AbstractRepoForEntity<UserShare, UserSharePK> {
	public static Logger log = LoggerUtils.getLogger();

	public UserShare save(UserShare entity) {

		super.save(entity);
		log.debug("ShareType -> " + entity.getShareType());
		return entity;

	}

	public UserShare findOne(UserShare entity, UserSharePK pkId) {

		return super.findOne(UserShare.class, pkId);
	}

	public boolean exists(UserShare entity, UserSharePK pkId) {
		return super.exists(UserShare.class, pkId);

	}

	public UserShare merge(UserShare entity) {
		return super.merge(entity);
	}

	public int delete(String fromNetwork, String toNetwork) {
		log.info("Entered query currencies by name");
		log.debug("fromNetwork -> " + fromNetwork);
		log.debug("toNetwork -> " + toNetwork);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("stopSharing"), Currency.class);
			query.setParameter(1, fromNetwork);
			query.setParameter(2, toNetwork);
			count = query.executeUpdate();
			query = entityManager.createNativeQuery(getSql("stopSharing"),
					Currency.class);
			query.setParameter(1, toNetwork);
			query.setParameter(2, fromNetwork);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	public List<Map<String, Object>> querySendingShareDtls(String networkId) {
		log.info("Inside UserShareRepo -> querySendingShareDtls");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("sendingShareDtls"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		query.setParameter(6, networkId);
		query.setParameter(7, networkId);
		query.setParameter(8, networkId);
		query.setParameter(9, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryReceivingShareDtls(String networkId) {
		log.info("Inside UserShareRepo -> queryReceivingShareDtls");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("receivingShareDtls"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public int updateShareTypeDtls(String fromNetwork, String toNetwork,
			String shareItem) {
		log.info("Entered query currencies by name");
		log.debug("fromNetwork -> " + fromNetwork);
		log.debug("toNetwork -> " + toNetwork);
		log.debug("shareType ::" + shareItem);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("approveShareItemDtls"), Currency.class);
			query.setParameter(1, shareItem);
			query.setParameter(2, fromNetwork);
			query.setParameter(3, toNetwork);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	public int suspendShareTypeDtls(String fromNetwork, String toNetwork,
			String shareItem, String flag, String userId) {
		log.info("Entered query currencies by name");
		log.debug("fromNetwork -> " + fromNetwork);
		log.debug("toNetwork -> " + toNetwork);
		log.debug("shareType ::" + shareItem);
		log.debug("flag::" + flag);
		log.debug("userId::" + userId);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call modifyDelteShareItemsDtls(?,?,?,?,?)}");
			query.setParameter(1, fromNetwork);
			query.setParameter(2, toNetwork);
			query.setParameter(3, shareItem);
			query.setParameter(4, userId);
			query.setParameter(5, flag);
			count = query.executeUpdate();
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			return 0;
		}
		entityManager.close();
		return count;
	}

	public int suspendSharings(String fromNetwork, String toNetwork,
			String userId) {
		log.info("Entered query currencies by name");
		log.debug("fromNetwork -> " + fromNetwork);
		log.debug("toNetwork -> " + toNetwork);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("suspendSharings"), Currency.class);
			query.setParameter(1, userId);
			query.setParameter(2, fromNetwork);
			query.setParameter(3, toNetwork);
			count = query.executeUpdate();
			query = entityManager.createNativeQuery(getSql("suspendSharings"),
					Currency.class);
			query.setParameter(1, userId);
			query.setParameter(2, toNetwork);
			query.setParameter(3, fromNetwork);
			count = query.executeUpdate();
		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	
	public void delete(UserShare entity) {
		log.debug("ShareType -> " + entity.getShareType());
		super.delete(entity);

	}
	public List<Map<String, Object>> queryyourRequestedShareDtls(String networkId) {
		log.info("Inside UserShareRepo -> queryyourRequestedShareDtls");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("yourRequestedShareDtls"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}
	public List<Map<String, Object>> queryyourRequestedShareDtls1(String networkId) {
		log.info("Inside UserShareRepo -> queryyourRequestedShareDtls1");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("yourRequestedShareDtls1"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}
	public List<Map<String, Object>> queryTheyRequestedShareDtls(String networkId) {
		log.info("Inside UserShareRepo -> queryTheyRequestedShareDtls");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("theyRequestedShareDtls"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}
	public List<Map<String, Object>> queryTheyRequestedShareDtls1(String networkId) {
		log.info("Inside UserShareRepo -> queryTheyRequestedShareDtls1");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("theyRequestedShareDtls1"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		List<Object[]> pplList = query.getResultList();
		log.debug("pplList -> " + pplList);
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("networId", obj[i]);
					break;
				case 1:
					recordMap.put("prices", obj[i]);
					break;
				case 2:
					recordMap.put("offers", obj[i]);
					break;
				case 3:
					recordMap.put("newsAndLibrary", obj[i]);
					break;
				case 4:
					recordMap.put("people", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}
	
public UserShare queryProfileTypeforshare(String fromnetworkId,String toNetworkId) {
		
		log.info("queryprofiletype for share");
		UserShare resultList = new UserShare();
		
		try
		{
			Query query = entityManager.createNativeQuery(
					getSql("queryProfileTypeforshare"), UserShare.class);
			query.setParameter(1, fromnetworkId);
			query.setParameter(2, toNetworkId);
			
			 resultList = (UserShare) query.getSingleResult();
		}
		 catch (NoResultException e) {
				return null;
			}
			

		
		entityManager.close();
		return resultList;

	}
}
