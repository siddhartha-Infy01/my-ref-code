package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class UserOccupationRepo extends
		AbstractRepoForEntity<UserOccupation, UserOccupationPK> {
	public static Logger log = LoggerUtils.getLogger();

	@SuppressWarnings("unchecked")
	public List<UserOccupation> queryusrOccupationbyuserId(String userId,String networkId) {
		log.info("Entered query UserOccupation by  userid");
		log.info("userid::" + userId);
		Query query = entityManager.createNativeQuery(
				getSql("queryusrOccupationbyuserId"), UserOccupation.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);
		List<UserOccupation> resultList = query.getResultList();
		System.out.println(resultList);
		entityManager.close();
		return resultList;
	}


	@SuppressWarnings("unchecked")
	public UserOccupation save(UserOccupation usroccptn) {
		super.save(usroccptn);
		return usroccptn;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(Iterable entities) {
		super.delete(entities);
	}

	public UserOccupation findOne(UserOccupation entity,
			UserOccupationPK userOccId) {
		return super.findOne(UserOccupation.class, userOccId);
	}

	public boolean exists(UserOccupation entity, UserOccupationPK id) {
		return super.exists(UserOccupation.class, id);

	}

	public UserOccupation merge(UserOccupation entity) {
		return super.merge(entity);
	}
	

	public boolean deleteUserOccupation(String userId,String netwrokId){
		Query query = entityManager.createNativeQuery(getSql("deleteOccupationByUser"),UserOccupation.class);
		query.setParameter(1, userId);
		query.setParameter(2, netwrokId);
		query.executeUpdate();
		return true;
	}

}
