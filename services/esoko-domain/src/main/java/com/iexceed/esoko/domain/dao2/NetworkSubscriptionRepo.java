package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.InvoiceDetail;
import com.iexceed.esoko.domain.entity2.NetworkSubscription;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionPK;
import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class NetworkSubscriptionRepo extends
		AbstractRepoForEntity<NetworkSubscription, NetworkSubscriptionPK> {
	public static Logger log = LoggerUtils.getLogger();

	public NetworkSubscription save(NetworkSubscription entity) {
		super.save(entity);
		return entity;
	}

	public NetworkSubscription findOne(NetworkSubscription entity,
			NetworkSubscriptionPK entityPK) {
		log.info("Inside NetworkSubscription -> findOne");
		return super.findOne(NetworkSubscription.class, entityPK);
	}

	public boolean exists(NetworkSubscription entity,
			NetworkSubscriptionPK entityPK) {
		log.info("Inside NetworkSubscription -> exists");
		return super.exists(NetworkSubscription.class, entityPK);

	}

	public NetworkSubscription merge(NetworkSubscription entity) {
		log.info("Inside NetworkSubscription -> merge");
		return super.merge(entity);
	}

	public List<Map<String, Object>> queryResellerSubscription(
			String networkId, String resellerId, String filterBy) {
		log.info("Inside NetworkSubscription -> queryResellerSubscription");
		log.debug("networkId::" + networkId);
		log.debug("resellerId::" + resellerId);
		String filter = null;
		filter = filterBy.toString();
		Query query = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		ResourceLocatorImpl resource = new ResourceLocatorImpl();
		try {

			if (filterBy.toString().equalsIgnoreCase("E30")
					|| filterBy.toString().equalsIgnoreCase("E60")
					|| filterBy.toString().equalsIgnoreCase("E90")
					|| filterBy.toString().equalsIgnoreCase("E7")) {
				int val = Integer.parseInt(filter.substring(1));
				val++;
				query = entityManager
						.createNativeQuery(getSql("queryResellerSubscribers"));

				query.setParameter(1, resellerId);
				query.setParameter(2, val);
			}

			else if (filterBy.toString().equalsIgnoreCase("RU")) {
				Properties props = resource.getEsokoProperties();
				log.debug("RecentlyModifiedAgentDuration::"
						+ props.getProperty("RecentlyModifiedAgentDuration"));
				int recentlyAdded = Integer.parseInt(props
						.getProperty("RecentlyModifiedAgentDuration"));
				query = entityManager
						.createNativeQuery(getSql("queryResellerSubscribers2"));

				query.setParameter(1, resellerId);
				query.setParameter(2, recentlyAdded);

			}

			else if (filterBy.toString().equalsIgnoreCase("RA")) {
				Properties props = resource.getEsokoProperties();
				log.debug("RecentlyAddedAgentDuration::"
						+ props.getProperty("RecentlyAddedAgentDuration"));
				int recentlyAdded = Integer.parseInt(props
						.getProperty("RecentlyAddedAgentDuration"));
				query = entityManager
						.createNativeQuery(getSql("queryResellerSubscribers3"));

				query.setParameter(1, resellerId);
				query.setParameter(2, recentlyAdded);

			}

			else if (filterBy.toString().equalsIgnoreCase("E")) {

				query = entityManager
						.createNativeQuery(getSql("queryResellerSubscribers4"));

				query.setParameter(1, resellerId);

			}

			else {
				query = entityManager
						.createNativeQuery(getSql("queryResellerSubscribers1"));

				query.setParameter(1, resellerId);

			}

			List<Object[]> nwkSubscribList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : nwkSubscribList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("sub_id", obj[i]);
						break;
					case 1:
						recordMap.put("sub_type", obj[i]);
						break;
					case 2:
						recordMap.put("network_id", obj[i]);
						break;
					case 3:
						recordMap.put("network_name", obj[i]);
						break;
					case 4:
						recordMap.put("balance", obj[i]);
						break;
					case 5:
						recordMap.put("expiry", obj[i]);
						break;
					case 6:
						recordMap.put("currency", obj[i]);
						break;
					case 7:
						recordMap.put("owner_name", obj[i]);
						break;
					case 8:
						recordMap.put("expiryFlag", obj[i]);
						break;
					case 9:
						recordMap.put("balanceFlag", obj[i]);
						break;
					case 10:
						recordMap.put("alerts", obj[i]);
						break;
					case 11:
						recordMap.put("agents", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("resellerSubscriptionDtls::" + recordList);
		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> querySubscriptionInvoiceDtls(
			String networkId, String subscrib_type, String resellerId) {
		log.info("Inside NetworkSubscription -> querySubscriptionInvoiceDtls");
		log.debug("networkId::" + networkId);
		log.debug("resellerId::" + resellerId);
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryResellerSubscriberInvoices"));
			query.setParameter(1, networkId);
			query.setParameter(2, subscrib_type);
			query.setParameter(3, resellerId);
			List<Object[]> nwkSubscribList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : nwkSubscribList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("date", obj[i]);
						break;
					case 1:
						recordMap.put("number", obj[i]);
						break;
					case 2:
						recordMap.put("network_id", obj[i]);
						break;
					case 3:
						recordMap.put("network_name", obj[i]);
						break;
					case 4:
						recordMap.put("due", obj[i]);
						break;
					case 5:
						recordMap.put("amount", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("resellerSubscriptionDtls::" + recordList);
		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> querySubsriptionDetails(String networkId,
			String subscription_type) {

		log.info("querySubsriptionDetails");
		log.debug("networkid::" + networkId);
		log.debug("subscription_type::" + subscription_type);
		Query query = entityManager
				.createNativeQuery(getSql("querySubscriptionDetails"));
		query.setParameter(1, networkId);
		query.setParameter(2, networkId);
		query.setParameter(3, networkId);
		query.setParameter(4, networkId);
		query.setParameter(5, networkId);
		query.setParameter(6, networkId);
		query.setParameter(7, subscription_type);
		query.setParameter(8, networkId);
		query.setParameter(9, subscription_type);
		query.setParameter(10, networkId);
		query.setParameter(11, subscription_type);
		query.setParameter(12, "Default");
		query.setParameter(13, subscription_type);
		query.setParameter(14, "Default");
		query.setParameter(15, subscription_type);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("param_name", obj[i]);
					break;
				case 1:
					recordMap.put("param_value", obj[i]);
					break;
				case 2:
					recordMap.put("owner", obj[i]);
					break;
				case 3:
					recordMap.put("balance", obj[i]);
					break;
				case 4:
					recordMap.put("currency", obj[i]);
					break;
				case 5:
					recordMap.put("account", obj[i]);
					break;
				case 6:
					recordMap.put("network_name", obj[i]);
					break;
				case 7:
					recordMap.put("period", obj[i]);
					break;
				case 8:
					recordMap.put("casReseller", obj[i]);
					break;
				case 9:
					recordMap.put("marginReseller", obj[i]);
					break;
				case 10:
					recordMap.put("bonus", obj[i]);
					break;
				}

			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public int getRecentSubId() {
		log.info("Inside NetworkSubscriptionRepo -> getRecentSubId");
		int returnCount = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getMaxNetworkSubscripId"));
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

	public List<InvoiceDetail> queryResellerSubscriptionInvoices(
			String networkId, String subscription_type, String resellerId) {

		log.info("queryResellerSubscriptionInvoices");
		log.debug("networkid::" + networkId);
		log.debug("subscription_type::" + subscription_type);
		List<InvoiceDetail> list = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryResellerSubscriptionInvoices"),
					InvoiceDetail.class);
			query.setParameter(1, networkId);
			query.setParameter(2, subscription_type);
			query.setParameter(3, resellerId);
			list = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return list;
	}

	public List<Map<String, Object>> queryResellerSubscriberInvoices(
			String networkId, String subscripType, String resellerId) {
		log.info("Inside NetworkSubscription -> queryResellerSubscripInvoices");
		log.debug("networkId::" + networkId);
		log.debug("subscripType::" + subscripType);
		log.debug("resellerId::" + resellerId);
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryResellerSubscriberInvoices"));
			query.setParameter(1, networkId);
			query.setParameter(2, subscripType);
			query.setParameter(3, resellerId);
			List<Object[]> nwkSubscribList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : nwkSubscribList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("date", obj[i]);
						break;
					case 1:
						recordMap.put("invoice_no", obj[i]);
						break;
					case 2:
						recordMap.put("network_id", obj[i]);
						break;
					case 3:
						recordMap.put("network_name", obj[i]);
						break;
					case 4:
						recordMap.put("due", obj[i]);
						break;
					case 5:
						recordMap.put("amount", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("queryResellerSubscripInvoices::" + recordList);
		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryAllResellerInvoices(String resellerId) {
		log.info("Inside NetworkSubscription -> queryAllResellerInvoices");
		log.debug("resellerId::" + resellerId);
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryAllResellerInvoices"));
			query.setParameter(1, resellerId);
			List<Object[]> nwkSubscribList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : nwkSubscribList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("date", obj[i]);
						break;
					case 1:
						recordMap.put("invoice_no", obj[i]);
						break;
					case 2:
						recordMap.put("network_id", obj[i]);
						break;
					case 3:
						recordMap.put("network_name", obj[i]);
						break;
					case 4:
						recordMap.put("due", obj[i]);
						break;
					case 5:
						recordMap.put("amount", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("queryResellerSubscripInvoices::" + recordList);
		entityManager.close();
		return recordList;
	}

	public NetworkSubscription queryExistingOne(String resellerId,
			String networkId, String subscriberId) {
		NetworkSubscription entity = null;
		log.info("Inside NetworkSubscription -> queryExistingOne");
		log.debug("networkId::" + networkId);
		log.debug("resellerId::" + resellerId);
		log.debug("subscriptionType::" + subscriberId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("getExistingSubscriptionNwk"),
					NetworkSubscription.class);
			query.setParameter(1, subscriberId);
			query.setParameter(2, networkId);
			query.setParameter(3, resellerId);
			entity = (NetworkSubscription) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return entity;
	}
}
