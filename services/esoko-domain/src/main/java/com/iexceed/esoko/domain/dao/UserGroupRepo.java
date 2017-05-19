package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserGroupPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class UserGroupRepo extends
		AbstractRepoForEntity<UserGroup, UserGroupPK> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(UserGroup userGroup, UserGroupPK userGroupPk) {
		log.info("Inside UserGroupRepo -> exists");
		return super.exists(UserGroup.class, userGroupPk);
	}

	public int queryNwisManaged(String networkId, String userId) {
		log.info("query network is managed ");
		log.info("networkid::" + networkId);
		int returnVal = 0;
		Query query = entityManager
				.createNativeQuery(getSql("queryNwisManaged"));
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		returnVal = Integer.parseInt(query.getSingleResult().toString());

		entityManager.close();
		return returnVal;

	}

	public UserGroup merge(UserGroup userGroup) {
		log.info("Inside UserGroupRepo -> merge");
		return super.merge(userGroup);
	}

	public UserGroup save(UserGroup userGroup) {
		log.info("Inside UserGroupRepo -> save");
		return super.save(userGroup);
	}

	public UserGroup findOne(UserGroup userGroup, UserGroupPK userGroupPk) {
		log.info("Inside UserGroupRepo -> findOne");
		return super.findOne(UserGroup.class, userGroupPk);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable userGroup) {
		log.info("Inside UserGroupRepo -> delete");
		super.delete(userGroup);
	}

	public void delete(UserGroup userGroup) {
		log.info("Inside UserGroupRepo -> delete");
		super.delete(userGroup);
	}

	public List<UserGroup> queryUsersByNwkGrpId(String groupId, String nwkId) {
		log.info("Inside UserGroupRepo -> queryUsersByNwkGrpId");
		log.debug("GroupId: " + groupId);
		log.debug("NetworkId: " + nwkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryUsersByNwkIdGrpId"), UserGroup.class);
		query.setParameter(1, groupId);
		query.setParameter(2, nwkId);
		List<UserGroup> grpList = query.getResultList();
		entityManager.close();
		return grpList;
	}

	public List<UserGroup> queryUsrsByUsrIdNwkId(String usrId, String nwkId) {
		log.info("Inside UserGroupRepo -> queryUsrsByUsrIdNwkId");
		log.debug("UserId: " + usrId);
		log.debug("NetworkId: " + nwkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryUsrsByUsrIdNwkId"), UserGroup.class);
		query.setParameter(1, usrId);
		query.setParameter(2, nwkId);

		List<UserGroup> grpList = new ArrayList<UserGroup>();
		try {
			grpList = query.getResultList();
		} catch (NullPointerException e) {
			Utils.getStackTrace(e);
		}
		entityManager.close();
		return grpList;
	}

	public List<UserGroup> queryUsrsByNwkId(String nwkId) {
		log.info("Inside UserGroupRepo -> queryUsrsByNwkId");
		log.debug("NetworkId: " + nwkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryUsrsByNwkId"), UserGroup.class);
		query.setParameter(1, nwkId);
		List<UserGroup> grpList = new ArrayList<UserGroup>();
		try {
			grpList = query.getResultList();
		} catch (NullPointerException e) {
			Utils.getStackTrace(e);
		}
		entityManager.close();
		return grpList;
	}
}
