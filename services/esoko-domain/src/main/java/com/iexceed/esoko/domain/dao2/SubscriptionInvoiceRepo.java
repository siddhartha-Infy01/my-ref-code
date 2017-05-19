package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.InvoiceDetail;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SubscriptionInvoiceRepo extends
		AbstractRepoForEntity<InvoiceDetail, String> {
	public static Logger log = LoggerUtils.getLogger();

	public InvoiceDetail save(InvoiceDetail entity) {
		super.save(entity);
		return entity;
	}

	public InvoiceDetail findOne(InvoiceDetail entity, String entityPK) {
		log.info("Inside SubscriptionInvoiceRepo -> findOne");
		return super.findOne(InvoiceDetail.class, entityPK);
	}

	public boolean exists(InvoiceDetail entity, String entityPK) {
		log.info("Inside SubscriptionInvoiceRepo -> exists");
		return super.exists(InvoiceDetail.class, entityPK);

	}

	public InvoiceDetail merge(InvoiceDetail entity) {
		log.info("Inside SubscriptionInvoiceRepo -> merge");
		return super.merge(entity);
	}

	public InvoiceDetail queryInvoiceDetails(String invoiceId) {
		log.info("Inside InvoiceDetailsRepo -> querySubscriberInvoices");
		log.debug("invoiceId::" + invoiceId);
		InvoiceDetail subscribInvoice = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("querySubscriberInvoices"), InvoiceDetail.class);
			query.setParameter(1, invoiceId);
			subscribInvoice = (InvoiceDetail) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("subscribInvoicesList::" + subscribInvoice);
		return subscribInvoice;
	}

	public List<InvoiceDetail> querySubscriberInvoices(String networkId,
			String subscribType, String resellerId) {
		log.info("Inside InvoiceDetailsRepo -> querySubscriberInvoices");
		log.debug("networkId::" + networkId);
		log.debug("resellerId::" + resellerId);
		log.debug("subscribType::" + subscribType);
		List<InvoiceDetail> subscribInvoicesList = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("querySubscriberInvoices"), InvoiceDetail.class);
			query.setParameter(1, networkId);
			query.setParameter(2, subscribType);
			query.setParameter(3, resellerId);
			subscribInvoicesList = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("subscribInvoicesList::" + subscribInvoicesList);
		entityManager.close();
		return subscribInvoicesList;
	}

	public String generateInvoiceNumber() {
		log.info("Inside InvoiceDetailsRepo -> generateInvoiceNumber");
		String invoiceNumber = null;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("fetchRandomInvoiceNumber"));
			invoiceNumber = query.getSingleResult().toString();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("invoiceNumber::" + invoiceNumber);
		entityManager.close();
		return invoiceNumber;
	}

	public List<Map<String, Object>> getInvociePymtsDtls(String invoiceNumber) {
		log.info("Inside InvoiceDetailsRepo -> getInvociePymtsDtls");
		log.debug("invoiceNumber::" + invoiceNumber);
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getInvoicesPymtDtls"));
			query.setParameter(1, invoiceNumber);
			query.setParameter(2, invoiceNumber);
			List<Object[]> invoicesList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : invoicesList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("invoice_id", obj[i]);
						break;
					case 1:
						recordMap.put("created_ts", obj[i]);
						break;
					case 2:
						recordMap.put("invoice_from", obj[i]);
						break;
					case 3:
						recordMap.put("company", obj[i]);
						break;
					case 4:
						recordMap.put("town", obj[i]);
						break;
					case 5:
						recordMap.put("country", obj[i]);
						break;
					case 6:
						recordMap.put("invoice_to", obj[i]);
						break;
					case 7:
						recordMap.put("quantity", obj[i]);
						break;
					case 8:
						recordMap.put("description", obj[i]);
						break;
					case 9:
						if (obj[i].toString().equals("")) {
							recordMap.put("total", null);
						} else {
							recordMap.put("total", obj[i]);
						}
						break;
					case 10:
						recordMap.put("vat", obj[i]);
						break;
					case 11:
						recordMap.put("discount", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("invoiceNumber::" + invoiceNumber);
		entityManager.close();
		return recordList;
	}
}
