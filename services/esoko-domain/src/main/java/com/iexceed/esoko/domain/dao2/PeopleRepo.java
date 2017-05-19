package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.dao.SmartGroupRuleRepo;
import com.iexceed.esoko.domain.entity.SmartgroupRule;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PeopleRepo extends AbstractRepoForEntity<People, PeoplePK> {
	private static Logger log = LoggerUtils.getLogger();
	private static Logger smrtGrpLog = LoggerUtils.getSmartGroupLogger();
	@Autowired
	private ResourceLocatorImpl resource;
	@Autowired
	private SmartGroupRuleRepo smrtGrpRuleRepo;

	public boolean exists(People people, PeoplePK peoplePk) {
		log.info("Inside PeopleRepo -> exists");
		return super.exists(People.class, peoplePk);
	}

	public People merge(People people) {
		log.info("Inside PeopleRepo -> merge");
		return super.merge(people);
	}

	public People save(People people) {
		log.info("Inside PeopleRepo -> save");
		return super.save(people);
	}

	public People findOne(People people, PeoplePK peoplePk) {
		log.info("Inside PeopleRepo -> findOne");
		return super.findOne(People.class, peoplePk);
	}

	public List<Map<String, Object>> queryAllPeople(String networkId,
			String locations, String commodities, String occupations) {
		log.info("Inside PeopleRepo -> queryAllPeople");
		log.debug("NetworkId: " + networkId);
		log.debug("Locations: " + locations);
		log.debug("Commodities: " + commodities);
		log.debug("Occupations: " + occupations);
		Query query = entityManager
				.createNativeQuery("{call queryAllPeople(?,?,?,?)}");
		query.setParameter(1, networkId);
		if (commodities == null) {
			query.setParameter(2, "");
		} else {
			query.setParameter(2, commodities);
		}
		if (locations == null) {
			query.setParameter(3, "");
		} else {
			query.setParameter(3, locations);
		}
		if (occupations == null) {
			query.setParameter(4, "");
		} else {
			query.setParameter(4, occupations);
		}
		return getResult(query);
	}

	public List<Map<String, Object>> searchAllPeople(String networkId) {
		log.info("Inside PeopleRepo -> searchAllPeople");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery("{call searchAllPeople(?)}");
		query.setParameter(1, networkId);
		return getResult(query);
	}

	public List<Map<String, Object>> queryAdmins(String networkId,
			String locations, String commodities, String occupations) {
		log.info("Inside PeopleRepo -> queryAdmins");
		log.debug("NetworkId: " + networkId);
		log.debug("Locations: " + locations);
		log.debug("Commodities: " + commodities);
		log.debug("Occupations: " + occupations);
		Query query = entityManager
				.createNativeQuery("{call queryAdmins(?,?,?,?)}");
		query.setParameter(1, networkId);
		if (commodities == null) {
			query.setParameter(2, "");
		} else {
			query.setParameter(2, commodities);
		}
		if (locations == null) {
			query.setParameter(3, "");
		} else {
			query.setParameter(3, locations);
		}
		if (occupations == null) {
			query.setParameter(4, "");
		} else {
			query.setParameter(4, occupations);
		}
		return getResult(query);
	}

	public List<Map<String, Object>> queryUngrouped(String networkId) {
		log.info("Inside PeopleRepo -> queryUngrouped");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager
				.createNativeQuery("{call queryUngrouped(?)}");
		query.setParameter(1, networkId);
		return getResult(query);
	}

	public List<Map<String, Object>> queryRecentlyAdded(String networkId,
			String locations, String commodities, String occupations) {
		log.info("Inside PeopleRepo -> queryRecentlyAdded");
		log.debug("NetworkId: " + networkId);
		log.debug("Locations: " + locations);
		log.debug("Commodities: " + commodities);
		log.debug("Occupations: " + occupations);
		Query query = entityManager
				.createNativeQuery("{call queryRecentlyAdded(?,?,?,?,?)}");
		List<SmartgroupRule> rule = smrtGrpRuleRepo.queryAllRules(
				"Recently added", networkId);
		String duration = rule.get(0).getValue();
		log.debug("RecentlyAddedDuration: " + duration);
		query.setParameter(1, networkId);
		query.setParameter(2, duration);
		if (commodities == null) {
			query.setParameter(3, "");
		} else {
			query.setParameter(3, commodities);
		}
		if (locations == null) {
			query.setParameter(4, "");
		} else {
			query.setParameter(4, locations);
		}
		if (occupations == null) {
			query.setParameter(5, "");
		} else {
			query.setParameter(5, occupations);
		}
		return getResult(query);
	}

	public boolean isSmartGroup(String groupId, String networkId) {
		log.info("Inside PeopleRepo -> isSmartGroup");
		log.debug("GroupId: " + groupId);
		log.debug("NetworkId: " + networkId);
		boolean status = false;
		Query query = entityManager.createNativeQuery(getSql("queryGroupType"));
		query.setParameter(1, groupId);
		query.setParameter(2, networkId);
		String type = Character.toString((Character) query.getSingleResult());
		if (type.equalsIgnoreCase("S")) {
			status = true;
		}
		entityManager.close();
		return status;
	}

	private void createRules(String type, String condition, String value,
			String networkId, List<String> userIdList) {
		SmartGroupQueries smrtGrpQrs = new SmartGroupQueries();
		String finalCondition = null;
		String finalValue = null;
		String[] values = value.split(",");
		for (String val : values) {
			if (condition.equalsIgnoreCase("ISIN")
					|| condition.equalsIgnoreCase("ISNOTIN")
					|| condition.equalsIgnoreCase("IS")
					|| condition.equalsIgnoreCase("ISNOT")) {
				List<String> elements = this.getElements(type, val);
				StringBuilder tmpString = new StringBuilder();
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						tmpString.append("'" + elements.get(i) + "'");
					} else {
						tmpString.append(",'" + elements.get(i) + "'");
					}
				}
				finalValue = tmpString.toString();
				if (condition.equalsIgnoreCase("IS")) {
					finalCondition = "ISIN";
				} else if (condition.equalsIgnoreCase("ISNOT")) {
					finalCondition = "ISNOTIN";
				} else {
					finalCondition = condition;
				}
			} else {
				finalCondition = condition;
				finalValue = value;
			}
			String tmpQuery1 = smrtGrpQrs.generateQuery(type, finalCondition,
					finalValue, networkId, 1);
			String tmpQuery2 = smrtGrpQrs.generateQuery(type, finalCondition,
					finalValue, networkId, 2);
			Query query = entityManager.createNativeQuery(tmpQuery1);
			List<String> tmpLst1 = query.getResultList();
			query = entityManager.createNativeQuery(tmpQuery2);
			entityManager.close();
			List<String> tmpLst2 = query.getResultList();
			tmpLst1.addAll(tmpLst2);
			Set<String> finalList = new HashSet<String>(tmpLst1);
			String[] finalArray = new String[finalList.size()];
			finalList.toArray(finalArray);
			if (finalArray.length != 0) {
				StringBuffer tmpBuffer = new StringBuffer();
				for (int i = 0; i < finalArray.length; i++) {
					if (i == 0) {
						String obj = finalArray[i];
						tmpBuffer.append("('" + obj + "'");
					} else {
						String obj = finalArray[i];
						tmpBuffer.append(",'" + obj + "'");
					}
				}
				tmpBuffer.append(")");
				userIdList.add(tmpBuffer.toString());
			}
		}

	}

	public List<Map<String, Object>> queryGroupsDetail(String groupId,
			String networkId) {
		log.info("Inside PeopleRepo -> queryGroupsDetail");
		log.debug("GroupId: " + groupId);
		log.debug("NetworkId: " + networkId);

		Query query = entityManager
				.createNativeQuery("{call queryPeopleInGroup(?,?)}");
		query.setParameter(1, groupId);
		query.setParameter(2, networkId);
		return getResult(query);
	}

	public List<Map<String, Object>> querySmartGroupsDetail(String groupId,
			String networkId) {
		log.info("Inside PeopleRepo -> querySmartGroupsDetail");
		log.debug("GroupId: " + groupId);
		log.debug("NetworkId: " + networkId);

		Query query = entityManager
				.createNativeQuery("{call queryPeopleInSmartGroup(?,?)}");
		query.setParameter(1, groupId);
		query.setParameter(2, networkId);
		return getResult(query);
	}

	public List<Map<String, Object>> getSmartGroupRecords(String groupId,
			String networkId) {
		smrtGrpLog.info("Inside PeopleRepo -> getSmartGroupRecords");
		smrtGrpLog.debug("GroupId: " + groupId);
		smrtGrpLog.debug("NetworkId: " + networkId);

		List<SmartgroupRule> ruleList = smrtGrpRuleRepo.queryAllRules(groupId,
				networkId);
		List<String> userIdList = new ArrayList<String>();
		String matchValue = null;
		String limitCondn = null;
		String limitValue = null;
		for (SmartgroupRule smrtGrp : ruleList) {
			if (smrtGrp.getType().equalsIgnoreCase("Match")) {
				matchValue = smrtGrp.getValue();
			} else if (smrtGrp.getType().equalsIgnoreCase("Limit")) {
				limitCondn = smrtGrp.getConditions();
				limitValue = smrtGrp.getValue();
			} else {
				createRules(smrtGrp.getType(), smrtGrp.getConditions(),
						smrtGrp.getValue(), networkId, userIdList);
			}
		}
		StringBuffer rule = new StringBuffer("");
		if ((matchValue == null) && (groupId.equals("Recently added"))) {
			if (userIdList.size() == 0) {
				rule.append("WHERE people_id IN ('')");
			} else {
				rule.append("WHERE people_id IN " + userIdList.get(0));
			}
		} else {
			String match = "";
			if (matchValue.equalsIgnoreCase("ALL")) {
				match = " AND ";
			} else {
				match = " OR ";
			}
			if (userIdList.size() == 0) {
				rule.append("WHERE people_id IN ('')");
			} else {
				rule.append("WHERE people_id IN ");
				for (int i = 0; i < userIdList.size(); i++) {
					if (i == 0) {
						rule.append(userIdList.get(i));
					} else {
						rule.append(match + "people_id IN " + userIdList.get(i));
					}
				}
			}
			if (limitCondn != null) {
				if (limitCondn.equalsIgnoreCase("R")) {
					rule.append(" LIMIT " + limitValue);
				} else if (limitCondn.equalsIgnoreCase("F")) {
					rule.append(" ORDER BY first_name LIMIT " + limitValue);
				} else if (limitCondn.equalsIgnoreCase("L")) {
					rule.append(" ORDER BY last_name LIMIT " + limitValue);
				}
			}
		}
		Query query = entityManager
				.createNativeQuery("{call createSmartGroup(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, rule.toString());
		return getResult(query);
	}

	public long getSmartGroupCount(List<Map<String, String>> ruleList,
			String networkId, String matchValue, String selectedBy,
			long limitValue) {
		log.info("Inside PeopleRepo -> queryAdmins");
		Query query = null;
		List<String> userIdList = new ArrayList<String>();

		for (Map<String, String> smrtGrp : ruleList) {
			createRules(smrtGrp.get("Type"), smrtGrp.get("Condition"),
					smrtGrp.get("Value"), networkId, userIdList);
		}
		StringBuffer rule = new StringBuffer("");
		if (matchValue != null) {
			String match = "";
			if (matchValue.equalsIgnoreCase("ALL")) {
				match = " AND ";
			} else {
				match = " OR ";
			}
			if (userIdList.size() == 0) {
				rule.append("WHERE people_id IN ('')");
			} else {
				rule.append("WHERE people_id IN ");
				for (int i = 0; i < userIdList.size(); i++) {
					if (i == 0) {
						rule.append(userIdList.get(i));
					} else {
						rule.append(match + "people_id IN " + userIdList.get(i));
					}
				}
			}
			if (selectedBy != null) {
				if (selectedBy.equalsIgnoreCase("R")) {
					rule.append(" LIMIT " + limitValue);
				} else if (selectedBy.equalsIgnoreCase("F")) {
					rule.append(" ORDER BY first_name LIMIT " + limitValue);
				} else if (selectedBy.equalsIgnoreCase("L")) {
					rule.append(" ORDER BY last_name LIMIT " + limitValue);
				}
			}
		}
		query = entityManager.createNativeQuery("{call createSmartGroup(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, rule.toString());
		List<Map<String, Object>> result = getResult(query);
		return result.size();
	}

	/*public List<People> queryPeopleByUserId(String userId) {
		log.info("Inside PeopleRepo -> queryAdmins");
		log.debug("UserId: " + userId);
		Query query = entityManager.createNativeQuery(
				getSql("queryPeopleByUsrId"), People.class);
		query.setParameter(1, userId);
		List<People> pplList = query.getResultList();
		entityManager.close();
		return pplList;
	}*/

		public People queryPeopleByUserId(String userId) {
		log.info("Inside PeopleRepo -> queryAdmins");
		log.debug("UserId: " + userId);
		Query query = entityManager.createNativeQuery(
				getSql("queryPeopleByUsrId"), People.class);
		query.setParameter(1, userId);
		People pplList = (People) query.getSingleResult();
		entityManager.close();
		return pplList;
	}

	public void delete(People ppl) {
		super.delete(ppl);
	}

	public List<Map<String, Object>> queryPplApproval(String networkId,
			String sortBy) {
		log.info("Inside PeopleRepo -> queryPplApproval");
		log.debug("networkId: " + networkId);
		Query query = null;
		if (sortBy.toString().equalsIgnoreCase("commodity")) {
			query = entityManager.createNativeQuery(getSql("queryPplApproval"));
			query.setParameter(1, networkId);
			query.setParameter(2, networkId);
			query.setParameter(3, networkId);
			query.setParameter(4, networkId);
		} else if (sortBy.toString().equalsIgnoreCase("location")) {
			query = entityManager
					.createNativeQuery(getSql("queryPplApproval1"));
			query.setParameter(1, networkId);
			query.setParameter(2, networkId);
			query.setParameter(3, networkId);
			query.setParameter(4, networkId);
		} else {
			query = entityManager
					.createNativeQuery(getSql("queryPplApproval2"));
			query.setParameter(1, networkId);
			query.setParameter(2, networkId);
			query.setParameter(3, networkId);
			query.setParameter(4, networkId);
		}
		List<Object[]> pplList = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("peopleId", obj[i]);
					break;
				case 1:
					recordMap.put("firstName", obj[i]);
					break;
				case 2:
					recordMap.put("lastName", obj[i]);
					break;
				case 3:
					recordMap.put("msisdn", obj[i]);
					break;
				case 4:
					recordMap.put("createdBy", obj[i]);
					break;
				case 5:
					recordMap.put("mode", obj[i]);
					break;
				case 6:
					recordMap.put("createdTs", obj[i]);
					break;
				case 7:
					recordMap.put("gender", obj[i]);
					break;
				case 8:
					recordMap.put("birthYear", obj[i]);
					break;
				case 9:
					recordMap.put("location", obj[i]);
					break;
				case 10:
					recordMap.put("commodities", obj[i]);
					break;
				case 11:
					recordMap.put("occupations", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public People queryMasterNetwork(String userId) {
		log.info("Inside PeopleRepo -> queryMasterNetwork");
		log.debug("userId: " + userId);
		People people;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("fetchMasterNetwork"), People.class);
			query.setParameter(1, userId);
			people = (People) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return people;
	}

	public People findByMsisdnNwkId(String msisdn, String networkId) {
		log.info("Inside PeopleRepo -> findByMsisdnNwkId");
		log.debug("MSISDN: " + msisdn);
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("findPersonByTelNwk"), People.class);
		People people = null;
		try {
			query.setParameter(1, msisdn);
			query.setParameter(2, networkId);
			people = (People) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return people;
	}
	
	public People findByMsisdnNwkId1(String msisdn, String networkId) {
		log.info("Inside PeopleRepo -> findByMsisdnNwkId");
		log.debug("MSISDN: " + msisdn);
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("findPersonByTelNwk1"), People.class);
		People people = null;
		try {
			query.setParameter(1, msisdn);
			query.setParameter(2, networkId);
			people = (People) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return people;
	}

	public People findByMsisdn(String msisdn) {
		log.info("Inside PeopleRepo -> findByMsisdn");
		log.debug("MSISDN: " + msisdn);
		Query query = entityManager.createNativeQuery(
				getSql("findPersonByTel"), People.class);
		People people = null;
		try {
			query.setParameter(1, msisdn);
			people = (People) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return people;
	}

	public People findByEmailNwkId(String email, String networkId) {
		log.info("Inside PeopleRepo -> findByEmailNwkId");
		log.debug("Email: " + email);
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("findPersonByEmailNwk"), People.class);
		People people = null;
		try {
			query.setParameter(1, email);
			query.setParameter(2, networkId);
			people = (People) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return people;
	}

	public List<String> getElements(String choice, String values) {
		smrtGrpLog.info("Inside PeopleRepo -> getElements");
		smrtGrpLog.debug("Choice: " + choice);
		smrtGrpLog.debug("Values: " + values);
		Query query = entityManager
				.createNativeQuery("{call get_elements(?,?)}");
		query.setParameter(1, choice);
		query.setParameter(2, values);
		List<String> elements = query.getResultList();
		entityManager.close();
		return elements;
	}

	public People getNameByNwkId(String agentId, String networkid) {
		People people = null;
		;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryNamesbyNetworkId"), People.class);
			query.setParameter(1, agentId);
			query.setParameter(2, networkid);
			people = (People) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return people;
	}

	public int addAsAdmin(String userNetworkid, String userid,
			String networkid, String headeruserid) {
		log.info("Entered Add as admin repo");
		log.debug("userNetworkid -> " + userNetworkid);
		log.debug("headeruserid -> " + headeruserid);
		log.debug("networkid -> " + networkid);
		log.debug("userid -> " + userid);
		int count = 0;

		try {
			Query query = entityManager
					.createNativeQuery("{call addAdminExistingUser(?,?,?,?)}");
			query.setParameter(1, userNetworkid);
			query.setParameter(2, userid);
			query.setParameter(3, networkid);
			query.setParameter(4, headeruserid);

			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	private List<Map<String, Object>> getResult(Query query) {
		List<Object[]> pplList = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : pplList) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("peopleId", obj[i]);
					break;
				case 1:
					recordMap.put("firstName", obj[i]);
					break;
				case 2:
					recordMap.put("lastName", obj[i]);
					break;
				case 3:
					recordMap.put("nickName", obj[i]);
					break;
				case 4:
					recordMap.put("gender", obj[i]);
					break;
				case 5:
					recordMap.put("town", obj[i]);
					break;
				case 6:
					recordMap.put("country", obj[i]);
					break;
				case 7:
					recordMap.put("currencyId", obj[i]);
					break;
				case 8:
					recordMap.put("languageId", obj[i]);
					break;
				case 9:
					recordMap.put("msisdn", obj[i]);
					break;
				case 10:
					recordMap.put("email", obj[i]);
					break;
				case 11:
					recordMap.put("editFlag", obj[i]);
					break;
				case 12:
					recordMap.put("networkId", obj[i]);
					break;
				case 13:
					recordMap.put("finalValue", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public int updateRcrdSts(String peopleId, String networkId) {
		int i = 0;
		log.info("Inside PeopleRepo -> updateRcrdSts");
		log.debug("peopleId: " + peopleId);
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("updateRecordStatus"), People.class);
		query.setParameter(1, peopleId);
		query.setParameter(2, networkId);
		i = query.executeUpdate();
		entityManager.close();
		return i;
	}
	
	public int keywordNWjoin(String peopleId, String networkId) {
		int i = 0;
		log.info("Inside PeopleRepo -> keywordNWjoin");
		log.debug("peopleId: " + peopleId);
		log.debug("NetworkId: " + networkId);
		try
		{
		Query query = entityManager
				.createNativeQuery("{call keywordNWjoin(?,?)}",People.class);
		query.setParameter(2, peopleId);
		query.setParameter(1, networkId);
		i = query.executeUpdate();
		}
		
		catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return i;
	}

}
