package com.iexceed.esoko.domain.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkUseridRepo extends
		AbstractRepoForEntity<NetworkUserid, NetworkUseridPK> {
	public static Logger log = LoggerUtils.getLogger();

	public int countUserInNwk(String networkId) {
		log.info("Entered NetworkUseridRepo -> countUserInNwk");
		log.info("NetworkId: " + networkId);
		int count = 0;
		Query query = entityManager
				.createNativeQuery(getSql("queryUserCountInNwk"));
		query.setParameter(1, networkId);
		BigInteger entity = (BigInteger) query.getSingleResult();
		count = entity.intValue();
		entityManager.close();
		return count;
	}

	public List<NetworkUserid> queryNetworksByUser(String userId) {
		log.info("Entered query networks by users");
		log.info("userId::" + userId);

		Query query = entityManager.createNativeQuery(
				getSql("findByNetworkId"), NetworkUserid.class);
		query.setParameter(1, userId);

		List<NetworkUserid> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<Map<String, Object>> queryMynetworkcount(String userId) {

		Query query = entityManager
				.createNativeQuery(getSql("queryMynetworkcount"));
		query.setParameter(1, userId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("networkId", obj[i]);

				} else if (i == 1) {
					recordMap.put("count", obj[i]);
				} else {
					recordMap.put("description", obj[i]);
				}

			}
			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public NetworkUserid findOne(NetworkUserid entity, NetworkUseridPK id) {

		return super.findOne(NetworkUserid.class, id);
	}

	public boolean exists(NetworkUserid entity, NetworkUseridPK id) {
		return super.exists(NetworkUserid.class, id);

	}

	public void delete(NetworkUserid id) {
		super.delete(id);
	}

}
