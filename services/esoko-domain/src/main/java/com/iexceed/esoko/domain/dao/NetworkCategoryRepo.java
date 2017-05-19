package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity.NetworkCategory;

import com.iexceed.esoko.domain.entity.NetworkCategoryPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class NetworkCategoryRepo extends
		AbstractRepoForEntity<NetworkCategory, NetworkCategoryPK> {
	public static Logger log = LoggerUtils.getLogger();

	public boolean exists(NetworkCategory nwCat, NetworkCategoryPK nwCatPk) {
		boolean exists = super.exists(NetworkCategory.class, nwCatPk);
		return exists;
	}

	public NetworkCategory merge(NetworkCategory nwCat) {
		nwCat = super.merge(nwCat);
		return nwCat;
	}

	public List<NetworkCategory> queryCategoriesbyNwId(String networkId) {
		log.info("Entered query for network categories by networkid");
		log.info("networkId::" + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("queryCategoriesbyNwId"), NetworkCategory.class);
		query.setParameter(1, networkId);

		List<NetworkCategory> resultList = query.getResultList();

		entityManager.close();
		return resultList;
	}

	public NetworkCategory save(NetworkCategory nwcategory) {
		super.save(nwcategory);
		return nwcategory;

	}

	public void delete(NetworkCategory nwcategory) {
		super.delete(nwcategory);
	}

	public void delete(List<NetworkCategory> entityLst) {
		super.delete(entityLst);
	}

}
