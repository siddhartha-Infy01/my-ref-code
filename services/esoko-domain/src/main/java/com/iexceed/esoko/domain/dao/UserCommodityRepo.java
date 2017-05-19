package com.iexceed.esoko.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkCommodity;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class UserCommodityRepo extends
		AbstractRepoForEntity<UserCommodity, UserCommodityPK> {
	public static Logger log = LoggerUtils.getLogger();

	
	@SuppressWarnings("unchecked")
	public List<UserCommodity> queryUserCommodity(String userId,String networkId) {
		log.info("Entered query User Commodity");
		log.info("userid::" + userId);
		Query query = entityManager.createNativeQuery(getSql("findByUser"),
				UserCommodity.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);

		List<UserCommodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public UserCommodity save(UserCommodity usrcomm) {
		super.save(usrcomm);
		return usrcomm;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(Iterable enities) {
		super.delete(enities);
	}
	
	public boolean deleteUserCommodity(String userId,String networkId) {
		log.info("Entered delete User Commodity");
		log.info("userid::" + userId);
		Query query = entityManager.createNativeQuery(
				getSql("deleteCommodityByUser"), UserCommodity.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);

		query.executeUpdate();
		entityManager.close();
		return true;
	}
}
