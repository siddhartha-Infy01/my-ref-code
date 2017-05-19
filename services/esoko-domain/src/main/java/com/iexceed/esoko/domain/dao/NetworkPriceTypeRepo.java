package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkPriceType;
import com.iexceed.esoko.domain.entity.NetworkPriceTypePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class NetworkPriceTypeRepo extends
		AbstractRepoForEntity<NetworkPriceType, NetworkPriceTypePK> {

	private static Logger log = LoggerUtils.getLogger();

	public List<NetworkPriceType> queryPriceTypes(String networkId) {
		log.info("Inside NetworkPriceTypeRepo -> queryPriceTypes");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(getSql("queryPriceTypesByNwk"),
				NetworkPriceType.class);		
		query.setParameter(1, networkId);
		List<NetworkPriceType> resultList = query.getResultList();
		return resultList;
	}

	public boolean exists(NetworkPriceType priceType,
			NetworkPriceTypePK priceTypePk) {
		log.info("Inside NetworkPriceTypeRepo -> exists");
		return super.exists(NetworkPriceType.class, priceTypePk);
	}

	public NetworkPriceType merge(NetworkPriceType priceType) {
		log.info("Inside NetworkPriceTypeRepo -> merge");
		return super.merge(priceType);
	}

	public NetworkPriceType save(NetworkPriceType priceType) {
		log.info("Inside NetworkPriceTypeRepo -> save");
		return super.save(priceType);
	}

	public NetworkPriceType findOne(NetworkPriceType priceType,
			NetworkPriceTypePK priceTypePk) {
		log.info("Inside NetworkPriceTypeRepo -> findOne");
		return super.findOne(NetworkPriceType.class, priceTypePk);
	}

	public void delete(NetworkPriceType priceType) {
		log.info("Inside priceTypeCommodityRepo -> delete");
		super.delete(priceType);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable priceTypes) {
		log.info("Inside NetworkPriceTypeRepo -> deleteAll");
		super.delete(priceTypes);
	}

}
