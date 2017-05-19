package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SmsCode;
import com.iexceed.esoko.domain.entity.SmsCodePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SmsCodeRepo extends AbstractRepoForEntity<SmsCode, SmsCodePK> {

	public static Logger log = LoggerUtils.getLogger();
	
	public List<SmsCode> queryAllSMSCodes(String networkId){
		log.info("Inside SmsCodeRepo -> queryAllSMSCodes");
		log.debug("NetworkId: "+networkId);
		Query query = entityManager.createNativeQuery(getSql("findAllSMSCodeByNwk"), SmsCode.class);
		query.setParameter(1, networkId);		
		List<SmsCode> smsList = query.getResultList();
		entityManager.close();
		return smsList;
	}
	
	public List<Map<String, Object>> querySmscodesbyLocType(String networkId,
			String type) {
		log.info("Entered query sms codes by towns/markerts");
		log.info("networkId::" + networkId);
		log.info("type::" + type);
		Query query = entityManager
				.createNativeQuery("{call findSmsCodesbyLoc(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, type);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("location_id", obj[i]);

				} else {
					recordMap.put("sms_code", obj[i]);
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public int querySmscodeExist(String networkId, String smsCode) {
		int count = 0;
		log.debug("check wheather sms code exist in network");
		log.debug("network_id:" + networkId);
		log.debug("network_id:" + smsCode);
		Query query = entityManager
				.createNativeQuery(getSql("querySmscodeExist"));
		query.setParameter(1, networkId);
		query.setParameter(2, smsCode);
		count = Integer.parseInt(query.getSingleResult().toString());

		return count;
	}

	public List<String> queryCommDroplist(String networkId) {
		log.info("dropdownlist for commodity sms codes");
		log.info("networkId::" + networkId);
		Query query = entityManager
				.createNativeQuery(getSql("queryCommDroplist"));
		query.setParameter(1, networkId);
		List<String> list = query.getResultList();
		List<String> recordList = new ArrayList<String>();
		for (String obj : list) {
			recordList.add(obj);
		}
		entityManager.close();

		return recordList;
	}

	public List<Map<String, Object>> querySmscodesbyroot(String networkId,
			String parentId) {
		log.info("Entered query sms codes by pa");
		log.info("networkId::" + networkId);
		log.info("type::" + parentId);
		Query query = entityManager
				.createNativeQuery("{call findSmsCodes(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, parentId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("commodity_id", obj[i]);

				} else {
					recordMap.put("sms_code", obj[i]);
				}

			}

			recordList.add(recordMap);
		}
		entityManager.close();

		return recordList;
	}

	public List<Map<String, Object>> querySmscodesbyComm(String networkId,
			String type, String subtype) {
		log.info("query sms codes for commodities");
		log.info("networkId::" + networkId);
		log.info("type::" + type);
		log.info("subtype::" + subtype);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;
		if ("All".equalsIgnoreCase(subtype)) {

			query = entityManager.createNativeQuery("{call findSmsCodes(?,?)}");
			query.setParameter(1, networkId);
			query.setParameter(2, "");
			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();

				for (int i = 0; i < obj.length; i++) {
					if (i == 0) {
						recordMap.put("commodity_id", obj[i]);

					} else {
						recordMap.put("sms_code", obj[i]);
					}
				}
				resultList.add(recordMap);
			}
			entityManager.close();

		} else {

			query = entityManager.createNativeQuery("{call findSmsCodes(?,?)}");
			query.setParameter(1, networkId);
			query.setParameter(2, subtype);
			List<Object[]> list = query.getResultList();
			Map<String, Object> recordMap = null;

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();

				for (int i = 0; i < obj.length; i++) {
					if (i == 0) {
						recordMap.put("commodity_id", obj[i]);

					} else {
						recordMap.put("sms_code", obj[i]);
					}
				}
				resultList.add(recordMap);
			}

			entityManager.close();
		}
		return resultList;

	}

	public SmsCode save(SmsCode entity) {
		super.save(entity);
		return entity;
	}

	public SmsCode findOne(SmsCode entity, SmsCodePK smsCode) {
		return super.findOne(SmsCode.class, smsCode);
	}

	public boolean exists(SmsCode entity, SmsCodePK smsCode) {
		return super.exists(SmsCode.class, smsCode);

	}

	public SmsCode merge(SmsCode entity) {
		return super.merge(entity);
	}
}
