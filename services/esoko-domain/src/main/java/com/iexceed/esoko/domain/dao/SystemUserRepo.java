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
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class SystemUserRepo extends AbstractRepoForEntity<SystemUser, String> {
	public static Logger log = LoggerUtils.getLogger();

	public SystemUser queryUserDetails(String userId) {
		log.info("Entered query user");
		log.info("user::" + userId);
		SystemUser sysuser;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findByUserId"), SystemUser.class);
			query.setParameter(1, userId);

			sysuser = (SystemUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return sysuser;

	}

	public List<Map<String, Object>> queryAllUsers() {
		log.info("Entered query all users");
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryAllUsers"));

			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("user_id", obj[i]);
						break;
					case 1:
						recordMap.put("first_name", obj[i]);
						break;
					case 2:
						recordMap.put("last_name", obj[i]);
						break;
					case 3:
						recordMap.put("email", obj[i]);
						break;
					case 4:
						recordMap.put("msisdn", obj[i]);
						break;
					case 5:
						recordMap.put("town", obj[i]);
						break;
					case 6:
						recordMap.put("country", obj[i]);
						break;
					case 7:
						recordMap.put("networkId", obj[i]);
						break;	

					}
				}
				recordList.add(recordMap);
			}
			entityManager.close();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return recordList;

	}

	public int mobExists(String phone, String userId) {
		log.info("Entered query user");
		log.info("user::" + phone);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(getSql("mobExists"));
			query.setParameter(1, phone);
			query.setParameter(2, userId);
			count = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return count;
		}
		entityManager.close();
		return count;

	}

	public SystemUser queryUserIdbyEmail(String email) {
		log.info("Entered query user");
		log.info("user::" + email);
		SystemUser sysuser;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryUserIdbyEmail"));
			query.setParameter(1, email);
			sysuser = (SystemUser) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return sysuser;

	}

	public SystemUser queryUserIdbyPhone(String phone) {
		log.info("Entered query user");
		log.info("user::" + phone);
		SystemUser sysuser;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryUserIdbyPhone"),SystemUser.class);
			query.setParameter(1, phone);
			sysuser = (SystemUser) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();

		return sysuser;

	}

	public int mobExists(String phone) {
		log.info("Entered query user");
		log.info("user::" + phone);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("mobileExistsNewUser"));
			query.setParameter(1, phone);
			count = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return count;
		}
		entityManager.close();
		return count;

	}

	public int emailExists(String email, String userId) {
		log.info("Entered query user");
		log.info("user::" + email);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("emailExists"));
			query.setParameter(1, email);
			query.setParameter(2, userId);
			count = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return count;
		}
		entityManager.close();
		return count;

	}

	public int emailExists(String email) {
		log.info("Entered query user");
		log.info("user::" + email);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("emailExistsNewUser"));
			query.setParameter(1, email);
			count = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return count;
		}
		entityManager.close();
		return count;

	}

	@SuppressWarnings("unchecked")
	public SystemUser save(SystemUser user) {
		super.save(user);
		return user;

	}

	public SystemUser findOne(SystemUser entity, String userId) {

		return super.findOne(SystemUser.class, userId);
	}

	public boolean exists(SystemUser entity, String id) {
		return super.exists(SystemUser.class, id);

	}

	@SuppressWarnings("unchecked")
	public SystemUser merge(SystemUser entity) {
		return super.merge(entity);
	}

	public void delete(SystemUser entity) {
		log.info("Inside SystemUserRepo -> delete");
		super.delete(entity);
	}

	public SystemUser findByUserIdNwkId(String userId, String nwkId) {
		log.info("Entered findByUserIdNwkId");
		log.info("userId::" + userId);
		log.info("nwkId::" + nwkId);
		SystemUser sysuser = null;
		try {
			Query query = this.entityManager.createNativeQuery(
					getSql("findByUserIdNwkId"), SystemUser.class);
			query.setParameter(1, userId);
			query.setParameter(2, nwkId);
			sysuser = (SystemUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		this.entityManager.close();
		return sysuser;
	}

	public SystemUser findTownByUserId(String userId) {
		log.info("Entered findTownByUserId");
		log.info("userId::" + userId);
		SystemUser sysuser = null;
		try {
			Query query = this.entityManager.createNativeQuery(
					getSql("findTownByUserId"), SystemUser.class);
			query.setParameter(1, userId);
			sysuser = (SystemUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		this.entityManager.close();
		return sysuser;

	}
}
