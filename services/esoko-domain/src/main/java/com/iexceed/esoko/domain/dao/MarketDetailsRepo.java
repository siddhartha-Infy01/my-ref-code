package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.MarketDetail;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class MarketDetailsRepo extends
		AbstractRepoForEntity<MarketDetail, String> {
	@Override
	public <S extends MarketDetail> S save(S entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}
	
	@Override
	public <S extends MarketDetail> S merge(S entity) {
		// TODO Auto-generated method stub
		return super.merge(entity);
	}
	
	public MarketDetail findOne(MarketDetail marketDtls , String locationId) {
		log.info("Inside MarketDetailsRepo -> findOne");
		return super.findOne(MarketDetail.class, locationId);
	}
	public MarketDetail queryMarketDtls(String locationId) {
		log.info("Entered Market Details ");
		log.info("locationId::" + locationId);
		MarketDetail mrkdtetails;

		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryMarketDtls"), MarketDetail.class);
			query.setParameter(1, locationId);

			mrkdtetails = (MarketDetail) query.getSingleResult();

			mrkdtetails.setLocationPic(Utils.decodeImage(mrkdtetails.getLocationPic()));
		} catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return mrkdtetails;

	}

}
