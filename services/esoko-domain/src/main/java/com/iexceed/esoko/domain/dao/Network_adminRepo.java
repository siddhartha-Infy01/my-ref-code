package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.Network_admin;
import com.iexceed.esoko.domain.entity.Network_adminPK;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_adminRepo extends
		AbstractRepoForEntity<Network_admin, Network_adminPK> {
	public static Logger log = LoggerUtils.getLogger();

	public List<Network_admin> queryNetworksManByUsr(String userId) {
		log.info("Entered all networks managed by user");
		log.info("userId::" + userId);

		Query query = entityManager.createNativeQuery(
				getSql("queryNetworksManByUsr"), Network_admin.class);
		query.setParameter(1, userId);

		List<Network_admin> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}



	public Network_admin findOne(Network_admin entity, Network_adminPK userId) {

		return super.findOne(Network_admin.class, userId);
	}

	public boolean exists(Network_admin entity, Network_adminPK id) {
		return super.exists(Network_admin.class, id);

	}

	public void delete(Network_admin id) {
		super.delete(id);
	}
}
