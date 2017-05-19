package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserLocationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class UserLocationRepo extends
		AbstractRepoForEntity<UserLocation, UserLocationPK> {
	public static Logger log = LoggerUtils.getLogger();

	
	@SuppressWarnings("unchecked")
	public List<UserLocation> queryUserLocDetails(String userId,String networkId) {
		log.info("Entered query User Location");
		log.info("userid::" + userId);
		Query query = entityManager.createNativeQuery(getSql("findByUserloc"),
				UserLocation.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);
		List<UserLocation> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public UserLocation save(UserLocation usrloc) {
		super.save(usrloc);
		return usrloc;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(Iterable enities) {
		super.delete(enities);
	}
	

	public boolean deleteUserLocation(String userId,String networkId){
		Query query = entityManager.createNativeQuery(getSql("deleteLocationByUser"),UserLocation.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);
		query.executeUpdate();
		return true;
	}
}
