package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.CommodityPicture;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class CommodityPictureRepo extends
		AbstractRepoForEntity<CommodityPicture, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<CommodityPicture> queryCommoditiesPicbyCommId(String commodityId) {
		log.info("Entered query Commodities pics by commodity id");
		log.info("Commodity::" + commodityId);
		Query query = entityManager.createNativeQuery(
				getSql("queryCommoditiesPicbyCommId"), CommodityPicture.class);
		query.setParameter(1, commodityId);
		List<CommodityPicture> resultList = query.getResultList();
		for (CommodityPicture commd : resultList) {

			commd.setContent(Utils.decodeImage(commd.getContent()));

		}
		entityManager.close();
		return resultList;

	}

	public CommodityPicture querySinglePicbyCommId(String commodityId) {
		try {
			log.info("Entered query Commodities pics by commodity id");
			log.info("Commodity::" + commodityId);
			Query query = entityManager.createNativeQuery(
					getSql("queryCommoditiesPicbyCommId"),
					CommodityPicture.class);
			query.setParameter(1, commodityId);
			CommodityPicture commd = (CommodityPicture) query.getSingleResult();
			commd.setContent(Utils.decodeImage(commd.getContent()));
			entityManager.close();
			return commd;
		} catch (NoResultException ne) {

		}
		return null;

	}
}
