package com.iexceed.esoko.domain.dao3;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.entity3.BidsOffersMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")

public class BidsOffersMasterRepo extends
		AbstractRepoForEntity<BidsOffersMaster, String> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(BidsOffersMaster entity, String id) {
		log.info("Inside BidsOffersMasterRepo -> exists");
		return super.exists(BidsOffersMaster.class, id);
	}

	public BidsOffersMaster merge(BidsOffersMaster entity) {
		log.info("Inside BidsOffersMasterRepo -> merge");
		return super.merge(entity);
	}

	public BidsOffersMaster save(BidsOffersMaster entity) {
		log.info("Inside BidsOffersMasterRepo -> save");
		return super.save(entity);
	}

	

	public BidsOffersMaster findOne(BidsOffersMaster people, String uploadId) {
		log.info("Inside BidsOffersMasterRepo -> findOne");
		return super.findOne(BidsOffersMaster.class, uploadId);
	}	
	
	public void delete(BidsOffersMaster entity) {
		log.info("Inside BidsOffersMasterRepo -> delete");
		super.delete(entity);
	}

	public String getBidsAlertTemplate() {
		log.info("Inside BidsOffersMasterRepo -> getBidsAlertTemplate");
		return getSql("bidsAlertTemplate");
	}

	public String getOffersAlertTemplate() {
		log.info("Inside BidsOffersMasterRepo -> getOffersAlertTemplate");
		return getSql("offerAlertTemplate");
	}
	
	public List<BidsOffersMaster> bidsofferbydistance(String messageType,String commodityId,String locationId,double latitude,double longitude) {
		log.info("Inside BidsOfferMasterRepo -> bidsofferbydistance");
		log.debug("messageType -> " + messageType);
		log.debug("commodityId -> " + commodityId);
		log.debug("locationId -> " + locationId);
		log.debug("latitude -> " + latitude);
		log.debug("longitude -> " + longitude);
		List<BidsOffersMaster> result;
	
		try {
			Query query = entityManager
					.createNativeQuery("{call findBidsByDistance(?,?,?,?,?)}",BidsOffersMaster.class);
			query.setParameter(1, messageType);
			query.setParameter(2, commodityId);
			query.setParameter(3, locationId);
			query.setParameter(4, latitude);
			query.setParameter(5, longitude);
		 result = query.getResultList();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return result;
	}

}