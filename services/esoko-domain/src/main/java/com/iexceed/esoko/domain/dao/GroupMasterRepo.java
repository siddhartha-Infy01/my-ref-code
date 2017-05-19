package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class GroupMasterRepo extends
		AbstractRepoForEntity<GroupMaster, GroupMasterPK> {
	private static Logger log = LoggerUtils.getLogger();
	private static Logger smrtGrpLog = LoggerUtils.getSmartGroupLogger();
	Map<String, Object> recordMap = null;
	List<Map<String, Object>> recordList = null;
	@Autowired
	private PeopleRepo pplRepo;

	public List<Map<String, Object>> queryGroupByNetwks(String networkId) {
		Query query = entityManager
				.createNativeQuery(getSql("queryGroupByNetwks"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("groupId", obj[i]);
					break;
				case 1:
					recordMap.put("type", obj[i]);
					break;
				case 2:
					recordMap.put("count", obj[i]);
					break;

				case 3:
					recordMap.put("groupName", obj[i]);
					break;
				}
			}
			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryAllGroupDetails(String networkId,
			String calculateCost) {
		if(calculateCost == null){
			calculateCost = "N";
		}
		
		Query query = entityManager
				.createNativeQuery("{call queryAllGroupsDetails(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, calculateCost);

		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("groupId", obj[i]);
					break;
				case 1:
					recordMap.put("type", obj[i]);
					break;
				case 2:
					recordMap.put("count", obj[i]);
					break;

				case 3:
					recordMap.put("group_name", obj[i]);
					break;

				case 4:
					recordMap.put("people_id", obj[i]);
					break;
				case 5:
					recordMap.put("first_name", obj[i]);
					break;
				case 6:
					recordMap.put("last_name", obj[i]);
					break;
				case 7:
					recordMap.put("gender", obj[i]);
					break;

				case 8:
					recordMap.put("country", obj[i]);
					break;

				case 9:
					recordMap.put("msisdn", obj[i]);
					break;
				case 10:
					recordMap.put("email", obj[i]);
					break;

				case 11:
					recordMap.put("network_id", obj[i]);
					break;

				case 12:					
					recordMap.put("cost", obj[i]);
					break;

				case 13:
					recordMap.put("operator_id", obj[i]);
					break;

				}
			}
			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public boolean exists(GroupMaster groupMaster, GroupMasterPK groupMasterPk) {
		log.info("Inside GroupMasterRepo -> exists");
		return super.exists(GroupMaster.class, groupMasterPk);
	}

	public GroupMaster merge(GroupMaster groupMaster) {
		log.info("Inside GroupMasterRepo -> merge");
		GroupMaster entity = super.merge(groupMaster);
		entityManager.flush();
		return entity;
	}

	public GroupMaster save(GroupMaster groupMaster) {
		log.info("Inside GroupMasterRepo -> save");
		GroupMaster entity = super.save(groupMaster);
		entityManager.flush();
		return entity;
	}

	public GroupMaster findOne(GroupMaster groupMaster,
			GroupMasterPK groupMasterPk) {
		log.info("Inside GroupMasterRepo -> findOne");
		return super.findOne(GroupMaster.class, groupMasterPk);
	}

	public void delete(GroupMaster groupMaster) {
		log.info("Inside GroupMasterRepo -> delete");
		super.delete(groupMaster);
		entityManager.flush();
	}

	public List<GroupMaster> queryAllSmartGroups() {
		smrtGrpLog.info("Inside GroupMasterRepo -> queryAllSmartGroups");
		Query query = entityManager.createNativeQuery(
				getSql("queryAllSmartGroups"), GroupMaster.class);
		List<GroupMaster> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}
}
