package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class AgentDetailRepo extends
		AbstractRepoForEntity<AgentDetail, Integer> {
	private static Logger log = LoggerUtils.getLogger();

	ResourceLocatorImpl resource = new ResourceLocatorImpl();

	public List<Map<String, Object>> queryAgents(String networkId,
			String flagType) {
		log.info("Insidew Agent Details repo :: queryAgents");
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		if ("E".equalsIgnoreCase(flagType)) {

			Properties props = resource.getEsokoProperties();
			log.debug("RecentlyModifiedAgentDuration::"
					+ props.getProperty("RecentlyModifiedAgentDuration"));
			int recentlyAdded = Integer.parseInt(props
					.getProperty("RecentlyModifiedAgentDuration"));
			Query query = entityManager
					.createNativeQuery(getSql("recentlyModifiedAgents"));
			query.setParameter(1, networkId);
			query.setParameter(2, recentlyAdded);
			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();

				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("first_name", obj[i]);
						break;
					case 1:
						recordMap.put("last_name", obj[i]);
						break;
					case 2:
						recordMap.put("town", obj[i]);
						break;
					case 3:
						recordMap.put("country", obj[i]);
						break;
					case 4:
						recordMap.put("people_id", obj[i]);
						break;

					case 5:
						recordMap.put("location_id", obj[i]);
						break;
					case 6:
						recordMap.put("commodity_id", obj[i]);
						break;
					case 7:
						recordMap.put("template", obj[i]);
						break;
					case 8:
						recordMap.put("target", obj[i]);
						break;

					case 9:
						recordMap.put("application_id", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
			entityManager.close();

		}

		else if ("N".equalsIgnoreCase(flagType)) {

			Properties props = resource.getEsokoProperties();
			log.debug("RecentlyAddedAgentDuration::"
					+ props.getProperty("RecentlyAddedAgentDuration"));
			int recentlyAdded = Integer.parseInt(props
					.getProperty("RecentlyAddedAgentDuration"));
			Query query = entityManager.createNativeQuery(getSql("newAgents"));
			query.setParameter(1, networkId);
			query.setParameter(2, recentlyAdded);
			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();

				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("first_name", obj[i]);
						break;
					case 1:
						recordMap.put("last_name", obj[i]);
						break;
					case 2:
						recordMap.put("town", obj[i]);
						break;
					case 3:
						recordMap.put("country", obj[i]);
						break;
					case 4:
						recordMap.put("people_id", obj[i]);
						break;
					case 5:
						recordMap.put("location_id", obj[i]);
						break;
					case 6:
						recordMap.put("commodity_id", obj[i]);
						break;
					case 7:
						recordMap.put("template", obj[i]);
						break;
					case 8:
						recordMap.put("target", obj[i]);
						break;

					case 9:
						recordMap.put("application_id", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
			entityManager.close();

		}

		else {
			Query query = entityManager
					.createNativeQuery(getSql("queryAgents"));
			query.setParameter(1, networkId);
			query.setParameter(2, flagType);
			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();

				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("first_name", obj[i]);
						break;
					case 1:
						recordMap.put("last_name", obj[i]);
						break;
					case 2:
						recordMap.put("town", obj[i]);
						break;
					case 3:
						recordMap.put("country", obj[i]);
						break;
					case 4:
						recordMap.put("people_id", obj[i]);
						break;
					case 5:
						recordMap.put("location_id", obj[i]);
						break;
					case 6:
						recordMap.put("commodity_id", obj[i]);
						break;
					case 7:
						recordMap.put("template", obj[i]);
						break;
					case 8:
						recordMap.put("target", obj[i]);
						break;

					case 9:
						recordMap.put("application_id", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
			entityManager.close();

		}
		return recordList;

	}

	public List<Map<String, Object>> queryAgentDetails(String networkId,
			String PeopleId) {
		log.info("Insidew Agent Details repo - agent details:queryAgentDetails");
		Query query = entityManager
				.createNativeQuery(getSql("queryAgentDetails"));
		query.setParameter(1, networkId);
		query.setParameter(2, PeopleId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("first_name", obj[i]);
					break;
				case 1:
					recordMap.put("last_name", obj[i]);
					break;
				case 2:
					recordMap.put("town", obj[i]);
					break;
				case 3:
					recordMap.put("country", obj[i]);
					break;
				case 4:
					recordMap.put("people_id", obj[i]);
					break;
				case 5:
					recordMap.put("mobile", obj[i]);
					break;
				case 6:
					recordMap.put("email", obj[i]);
					break;
				case 7:
					recordMap.put("salary", obj[i]);
					break;
				case 8:
					recordMap.put("monthlybonus", obj[i]);
					break;
				case 9:
					recordMap.put("location_id", obj[i]);
					break;
				case 10:
					recordMap.put("location_count", obj[i]);
					break;
				case 11:
					recordMap.put("commodity_id", obj[i]);
					break;
				case 12:
					recordMap.put("commodity_count", obj[i]);
					break;
				case 13:
					recordMap.put("template", obj[i]);
					break;
				case 14:
					recordMap.put("template_count", obj[i]);
					break;
				case 15:
					recordMap.put("target", obj[i]);
					break;
				case 16:
					recordMap.put("incentive", obj[i]);
					break;
				case 17:
					recordMap.put("bonus", obj[i]);
					break;
				case 18:
					recordMap.put("application_id", obj[i]);
					break;
				case 19:
					recordMap.put("detail_id", obj[i]);
					break;

				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryAgentsByApplication(String networkId) {
		log.info("Insidew Agent Details repo by application id :: queryAgentsByApplication");
		Query query = entityManager
				.createNativeQuery(getSql("queryAgentsByApplication"));
		query.setParameter(1, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("first_name", obj[i]);
					break;
				case 1:
					recordMap.put("last_name", obj[i]);
					break;
				case 2:
					recordMap.put("people_id", obj[i]);
					break;
				case 3:
					recordMap.put("location_id", obj[i]);
					break;
				case 4:
					recordMap.put("commodity_id", obj[i]);
					break;
				case 5:
					recordMap.put("template", obj[i]);
					break;
				case 6:
					recordMap.put("target", obj[i]);
					break;
				case 7:
					recordMap.put("incentive", obj[i]);
					break;
				case 8:
					recordMap.put("bonus", obj[i]);
					break;
				case 9:
					recordMap.put("application_id", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public AgentDetail save(AgentDetail agentdetail) {
		super.save(agentdetail);
		return agentdetail;

	}

	public AgentDetail findOne(AgentDetail entity, Integer id) {

		return super.findOne(AgentDetail.class, id);
	}

	public AgentDetail findAgentByDetailId(Integer id) {
		AgentDetail agent = null;
		Query query = entityManager.createNativeQuery(
				getSql("findAgentByDetailId"), AgentDetail.class);
		query.setParameter(1, id);
		try {
			agent = (AgentDetail) query.getSingleResult();
		} catch (Exception e) {
			log.error("NO result found");
		}
		return agent;
	}

	public AgentDetail findDetailId(String networkId, String userId,
			String commodityId, String locationId) {
		AgentDetail agent = null;
		Query query = entityManager.createNativeQuery(getSql("findDetailId"),
				AgentDetail.class);
		query.setParameter(1, locationId);
		query.setParameter(2, commodityId);
		query.setParameter(3, networkId);
		query.setParameter(4, userId);
		try {
			agent = (AgentDetail) query.getSingleResult();
		} catch (Exception e) {
			log.error("NO result found");
		}
		return agent;
	}

	public boolean exists(AgentDetail entity, Integer id) {
		return super.exists(AgentDetail.class, id);

	}

	public AgentDetail merge(AgentDetail entity) {
		return super.merge(entity);
	}

	public List<Map<String, Object>> queryAgentTargets(String networkId,
			String applicationId, String userId, String startDate,
			String endDate) {
		log.info("inside AgentDetailRepo -> queryRecentTargets");
		log.debug("NetworkId: " + networkId);
		log.debug("ApplicationId: " + applicationId);
		log.debug("UserId: " + userId);
		log.debug("StartDate: " + startDate);
		log.debug("EndDate: " + endDate);
		Query query = entityManager
				.createNativeQuery("{call queryAgentTargets(?,?,?,?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, applicationId);
		query.setParameter(3, userId);
		query.setParameter(4, startDate);
		query.setParameter(5, endDate);

		List<Object[]> list = query.getResultList();
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("application_id", obj[i]);
					break;
				case 1:
					recordMap.put("target", obj[i]);
					break;
				case 2:
					recordMap.put("effectiveDate", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public List<String> getApplicationList(String networkId) {
		log.info("Inside AgentDetailRepo -> getApplicationList");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("queryApplicationList"));
		query.setParameter(1, networkId);
		List<String> agentList = query.getResultList();
		return agentList;
	}

	public Map<String, Map<String, Map<String, String>>> queryAgentUploads(
			String networkId, String applicationId, String userId,
			String effectiveDates) {
		log.info("AgentDetailRepo -> queryAgentUploads");
		log.debug("NetworkId: " + networkId);
		log.debug("ApplicationId: " + applicationId);
		log.debug("UserId: " + userId);
		log.debug("EffectiveDates: " + effectiveDates);
		Query query = entityManager
				.createNativeQuery("{call queryAgentUploads(?,?,?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, applicationId);
		query.setParameter(3, userId);
		query.setParameter(4, effectiveDates);
		List<Object[]> list = query.getResultList();
		Map<String, Map<String, Map<String, String>>> records = null;
		if (list.size() != 0) {
			records = new HashMap<String, Map<String, Map<String, String>>>();
			List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();
			Map<String, String> recordMap = null;
			for (Object[] obj : list) {
				recordMap = new HashMap<String, String>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("application_id", obj[i].toString());
						break;
					case 1:
						recordMap.put("uploads", obj[i].toString());
						break;
					case 2:
						recordMap.put("bonus", obj[i].toString());
						break;
					case 3:
						recordMap.put("incentive", obj[i].toString());
						break;
					case 4:
						recordMap.put("cost", obj[i].toString());
						break;
					case 5:
						recordMap.put("effective_date", obj[i].toString());
						break;
					}
				}
				tmpList.add(recordMap);
			}

			for (Map<String, String> map : tmpList) {
				String effDate = map.get("effective_date");
				Map<String, Map<String, String>> tmp = records.get(effDate);
				if (tmp == null) {
					Map<String, Map<String, String>> tmp1 = new HashMap<String, Map<String, String>>();
					records.put(effDate, tmp1);
				}
			}

			for (Map<String, String> map : tmpList) {
				String effDate = map.get("effective_date");
				Map<String, Map<String, String>> tmp = records.get(effDate);
				if (tmp != null) {
					String applId = map.get("application_id");
					Map<String, String> tmp1 = tmp.get(applId);
					if (tmp1 == null) {
						Map<String, String> tmp3 = new HashMap<String, String>();
						tmp.put(applId, tmp3);
					}
				}
			}

			for (Map<String, String> map : tmpList) {
				String effDate = map.get("effective_date");
				Map<String, Map<String, String>> tmp = records.get(effDate);
				if (tmp != null) {
					String applId = map.get("application_id");
					Map<String, String> tmp1 = tmp.get(applId);
					if (tmp1 != null) {
						tmp1.put("uploads", map.get("uploads"));
						tmp1.put("bonus", map.get("bonus"));
						tmp1.put("incentive", map.get("incentive"));
						tmp1.put("cost", map.get("cost"));
					}
				}
			}

		}

		return records;
	}

	public int deleteSuspendAgents(String networkId, String flag,
			String agentId, String userId) {
		log.info("modify agents");
		log.debug("networkId -> " + networkId);
		log.debug("agentId -> " + agentId);
		log.debug("userId ::" + userId);
		log.debug("flag::" + flag);

		int count1 = 0;
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("modifyAgents"));
			query.setParameter(1, flag);
			query.setParameter(2, userId);
			query.setParameter(3, networkId);
			query.setParameter(4, agentId);
			count = query.executeUpdate();

			Query query1 = entityManager
					.createNativeQuery(getSql("modifyAgentsmaster"));
			query1.setParameter(1, flag);
			query1.setParameter(2, agentId);
			query1.setParameter(3, networkId);
			count1 = query1.executeUpdate();
			count = count + count1;

		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			return 0;
		}
		entityManager.close();
		return count;
	}

	public int copyAgentDetails(String networkId, String sourceuserId,
			String targetuserId, String paramname, String userId) {
		log.info("copy Agent agents");
		log.debug("networkId -> " + networkId);
		log.debug("fromUser -> " + sourceuserId);
		log.debug("targetuserId -> " + targetuserId);
		log.debug("data being copied ::" + paramname);
		log.debug("userId::" + userId);
		int count = 0;

		try {
			Query query = entityManager
					.createNativeQuery(("{call copyFrom(?,?,?,?,?)}"));
			query.setParameter(1, networkId);
			query.setParameter(2, sourceuserId);
			query.setParameter(3, targetuserId);
			query.setParameter(4, paramname);
			query.setParameter(5, userId);
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

	public int saveForMultiple(String networkId, String applicationId,
			String paramvalue, String paramname, String userId,
			String action_flag, String headerUser) {
		log.info("inside AgentDetailRepo -> queryRecentTargets");
		log.debug("NetworkId: " + networkId);
		log.debug("ApplicationId: " + applicationId);
		log.debug("paramname: " + paramname);
		log.debug("paramvalue: " + paramvalue);
		log.debug("userId: " + userId);
		log.debug("action_flag: " + action_flag);
		log.debug("headerUser: " + headerUser);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call saveForMultiple(?,?,?,?,?,?,?)}");
			query.setParameter(1, networkId);
			query.setParameter(2, applicationId);
			query.setParameter(3, paramvalue);
			query.setParameter(4, paramname);
			query.setParameter(5, userId);
			query.setParameter(6, action_flag);
			query.setParameter(7, headerUser);

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

	public AgentDetail findDetailId(String networkId, String userId) {
		AgentDetail agent = null;
		Query query = entityManager.createNativeQuery(
				getSql("findAgentDetailId"), AgentDetail.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		try {
			agent = (AgentDetail) query.getSingleResult();
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
		}
		return agent;
	}

	public List<AgentDetail> findAgentDtlsPrices(String networkId, String userId) {
		List<AgentDetail> agents = null;
		Query query = entityManager.createNativeQuery(
				getSql("findAgentDetailIdPrices"), AgentDetail.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		try {
			agents = (List<AgentDetail>) query.getResultList();
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			agents = null;
		}
		return agents;
	}

	public AgentDetail findOfferDetailId(String networkId, String userId) {
		AgentDetail agent = null;
		log.debug("NetworkId: " + networkId);

		log.debug("userId: " + userId);
		Query query = entityManager.createNativeQuery(
				getSql("findOfferDetailId"), AgentDetail.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		try {
			agent = (AgentDetail) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			agent = null;
		}
		return agent;
	}

	public List<AgentDetail> findSurveyTemplateId(String networkId,
			String userId) {
		List<AgentDetail> agentList = null;
		log.debug("NetworkId: " + networkId);
		log.debug("userId: " + userId);
		Query query = entityManager.createNativeQuery(
				getSql("findSurveyTemplateId"), AgentDetail.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		try {
			agentList = query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			agentList = null;
		}
		return agentList;
	}

}