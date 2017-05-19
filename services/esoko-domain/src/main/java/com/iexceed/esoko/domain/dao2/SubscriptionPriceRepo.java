package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.SubscriptionDetail;
import com.iexceed.esoko.domain.entity2.SubscriptionDetailPK;
import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SubscriptionPriceRepo extends
		AbstractRepoForEntity<SubscriptionDetail, SubscriptionDetailPK> {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	private ResourceLocatorImpl resource;

	public SubscriptionDetail save(SubscriptionDetail entity) {
		super.save(entity);
		return entity;
	}

	public SubscriptionDetail findOne(SubscriptionDetail entity,
			SubscriptionDetailPK entityPK) {
		log.info("Inside SubscriptionPriceRepo -> findOne");
		return super.findOne(SubscriptionDetail.class, entityPK);
	}

	public boolean exists(SubscriptionDetail entity,
			SubscriptionDetailPK entityPK) {
		return super.exists(SubscriptionDetail.class, entityPK);

	}

	public SubscriptionDetail merge(SubscriptionDetail entity) {
		return super.merge(entity);
	}

	public List<SubscriptionDetail> querySubPricingList() {
		log.info("Inside SubscriptionDetailRepo -> querySubPricingList");
		List<SubscriptionDetail> subPriceList = null;
		Query query = entityManager.createNativeQuery(
				getSql("querySubPricingList"), SubscriptionDetail.class);
		try {
			subPriceList = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return subPriceList;
	}

	public int delteAllPrices(String sub_level, String sub_id) {
		log.info("Entered Delte AllPrices");
		log.debug("sub_level -> " + sub_level);
		log.debug("sub_id -> " + sub_id);
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("deleteAllPrices"), SubscriptionDetail.class);
			query.setParameter(1, sub_level);
			query.setParameter(2, sub_id);
			query.setParameter(3, sub_id);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	public int findSubDtlsRecordExist(int row_no, String sub_type,
			String pramName, String countryName, String networkName) {
		log.info("Inside SubscriptionPriceRepo -> findSubDtlsRecordExist");
		log.debug("sub_type::" + sub_type);
		log.debug("pramName::" + pramName);
		log.debug("countryName::" + countryName);
		log.debug("networkName::" + networkName);
		int returnCount = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("findSubDtlsRecordExist"));
			query.setParameter(1, row_no);
			query.setParameter(2, sub_type);
			query.setParameter(3, pramName);
			query.setParameter(4, countryName);
			query.setParameter(5, networkName);
			query.setParameter(6, "P");
			returnCount = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return returnCount;
	}

	public int findSubDtlsRecordExist(int row_no, String sub_type,
			String pramName) {
		log.info("Inside SubscriptionPriceRepo -> findSubDtlsRecordExist");
		log.debug("sub_type::" + sub_type);
		log.debug("pramName::" + pramName);
		log.debug("row_no::" + row_no);
		int returnCount = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("findSubDtlsRecordExistByPK"));
			query.setParameter(1, row_no);
			query.setParameter(2, sub_type);
			query.setParameter(3, pramName);
			returnCount = Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return returnCount;
	}

	public SubscriptionDetail querySubPricingDtls(int row_no, String type,
			String pramName) {
		log.info("Inside SubscriptionDetailRepo -> querySubPricingList");
		log.debug("sub_type::" + type);
		log.debug("pramName::" + pramName);
		SubscriptionDetail subPriceList = null;
		Query query = entityManager.createNativeQuery(
				getSql("querySubPricingDtls"), SubscriptionDetail.class);
		query.setParameter(1, row_no);
		query.setParameter(2, type);
		query.setParameter(3, pramName);
		try {
			subPriceList = (SubscriptionDetail) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return subPriceList;
	}

	public List getSubscribMasterRows(String sub_level, String sub_id) {
		log.info("Inside SubscriptionPriceRepo -> findSubDtlsRecordExist");
		log.debug("sub_level::" + sub_level);
		log.debug("sub_id::" + sub_id);
		List returnCount = null;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getSubscribMasterRows"));
			query.setParameter(1, sub_level);
			query.setParameter(2, sub_id);
			query.setParameter(3, sub_id);
			query.setParameter(4, "P");
			returnCount = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		log.debug("returnCount::" + returnCount);
		return returnCount;
	}

	public List<Map<String, Object>> queryResellerPriceDtls(String networkId) {

		log.info("Query Reseller PriceDtls");
		log.debug("networkid::" + networkId);
		Query query = entityManager
				.createNativeQuery("{call queryResellerPrices(?)}");
		query.setParameter(1, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("subscription_id", obj[i]);
					break;
				case 1:
					recordMap.put("subscription_type", obj[i]);
					break;
				case 2:
					recordMap.put("param_name", obj[i]);
					break;
				case 3:
					recordMap.put("param_value", obj[i]);
					break;
				case 4:
					recordMap.put("margin", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryNetworkPriceDtls(String networkId) {

		log.info("Query Reseller PriceDtls");
		log.debug("networkid::" + networkId);
		Query query = entityManager
				.createNativeQuery("{call queryNetworkPrices(?)}");
		query.setParameter(1, networkId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("subscription_id", obj[i]);
					break;
				case 1:
					recordMap.put("subscription_type", obj[i]);
					break;
				case 2:
					recordMap.put("param_name", obj[i]);
					break;
				case 3:
					recordMap.put("param_value", obj[i]);
					break;
				case 4:
					recordMap.put("margin", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}
}
