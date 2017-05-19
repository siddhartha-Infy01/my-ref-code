package com.iexceed.esoko.domain.dao2;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.SubscriptionMaster;
import com.iexceed.esoko.domain.entity2.SubscriptionMasterPK;
import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SubscriptionMasterRepo extends
		AbstractRepoForEntity<SubscriptionMaster, SubscriptionMasterPK> {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	private ResourceLocatorImpl resource;

	public SubscriptionMaster save(SubscriptionMaster entity) {
		super.save(entity);
		return entity;
	}

	public SubscriptionMaster findOne(SubscriptionMaster entity,
			SubscriptionMasterPK entityPK) {
		log.info("Inside SubscriptionPriceRepo -> findOne");
		return super.findOne(SubscriptionMaster.class, entityPK);
	}

	public boolean exists(SubscriptionMaster entity,
			SubscriptionMasterPK entityPK) {
		return super.exists(SubscriptionMaster.class, entityPK);

	}

	public SubscriptionMaster merge(SubscriptionMaster entity) {
		return super.merge(entity);
	}

	public List<SubscriptionMaster> querySubPricingList() {
		log.info("Inside SubscriptionMasterRepo -> querySubPricingList");
		List<SubscriptionMaster> subPriceList = null;
		Query query = entityManager.createNativeQuery(
				getSql("querySubPricingList"), SubscriptionMaster.class);
		try {
			subPriceList = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return subPriceList;
	}

	public int findMasterRecordExist(int sub_id) {
		log.info("Inside SubscriptionMasterRepo -> querySubPricingList");
		log.debug("sub_level::" + sub_id);
		int returnCount = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("findMasterSubRecordExist"));
			query.setParameter(1, sub_id);
			returnCount = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return returnCount;
	}

	public int updateMasterRecord(String sub_level, int sub_id) {
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("updateMasterSubRecord"), SubscriptionMaster.class);
			query.setParameter(1, sub_level);
			query.setParameter(2, sub_id);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	public int getRecentSubId() {
		log.info("Inside SubscriptionMasterRepo -> getRecentSubId");
		int returnCount = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getMaxSubscribId"));
			if (query.getSingleResult() != null) {
				returnCount = Integer.parseInt(query.getSingleResult()
						.toString());
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		} catch (Exception e) {
			log.debug("No records found");
		}
		entityManager.close();
		return returnCount;
	}

	public int deleteMasterSubscriptions(String sub_id) {
		log.info("Entered Delete Master Subscriptions");
		log.debug("sub_id -> " + sub_id);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("deleteMasterSubscriptions"),
					SubscriptionMaster.class);
			query.setParameter(1, sub_id);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
}
