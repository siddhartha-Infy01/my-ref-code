package com.iexceed.esoko.domain.dao3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.CropTipMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class CropTipMasterRepo extends
		AbstractRepoForEntity<CropTipMaster, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(CropTipMaster entity, String id) {
		log.info("Inside CropTipMasterRepo -> exists");
		return super.exists(CropTipMaster.class, id);
	}

	public CropTipMaster merge(CropTipMaster entity) {
		log.info("Inside CropTipMasterRepo -> merge");
		return super.merge(entity);
	}

	public CropTipMaster save(CropTipMaster entity) {
		log.info("Inside CropTipMasterRepo -> save");
		return super.save(entity);
	}

	public CropTipMaster findOne(CropTipMaster entity, String id) {
		log.info("Inside CropTipMasterRepo -> findOne");
		return super.findOne(CropTipMaster.class, id);
	}

	public void delete(CropTipMaster entity) {
		log.info("Inside CropTipMasterRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside CropTipMasterRepo -> deleteAll");
		super.delete(entities);
	}

	public List<Map<String, Object>> queryAllTips(String show, String sort,
			String networkId) {
		log.info("Inside CropTipMasterRepo -> queryAllTips");
		log.debug("Show: " + show);
		log.debug("Sort by: " + sort);
		log.debug("NetworkId: " + networkId);
		List<Map<String, Object>> tipList = new ArrayList<Map<String, Object>>();
		Query query = entityManager
				.createNativeQuery("{call queryAllCropTips(?,?,?)}");
		query.setParameter(1, show);
		query.setParameter(2, sort);
		query.setParameter(3, networkId);

		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("cropTipId", obj[i]);
					break;

				case 1:
					recordMap.put("cropTipName", obj[i]);
					break;

				case 2:
					recordMap.put("category", obj[i]);
					break;

				case 3:
					recordMap.put("categoryName", obj[i]);
					break;

				case 4:
					recordMap.put("commodity", obj[i]);
					break;

				case 5:
					recordMap.put("state", obj[i]);
					break;

				case 6:
					recordMap.put("type", obj[i]);
					break;

				case 7:
					recordMap.put("nuOfTips", obj[i]);
					break;

				case 8:
					recordMap.put("networkId", obj[i]);
					break;
				}
			}
			tipList.add(recordMap);
		}
		entityManager.close();
		return tipList;
	}

	public void updateCropTipStatus(String cropTipId, String modifiedBy,
			String status) {
		log.info("Inside CropTipMasterRepo -> updateCropTipStatus");
		log.debug("CropTipId: " + cropTipId);
		log.debug("Status: " + status);
		log.debug("Modified by: " + modifiedBy);
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifiedTs = df.format(dt);
		log.debug("Modified timestamp: " + modifiedTs);
		Query query = entityManager
				.createNativeQuery(getSql("updateCropTipStatus"));
		query.setParameter(1, status);
		query.setParameter(2, modifiedBy);
		query.setParameter(3, modifiedTs);
		query.setParameter(4, cropTipId);
		query.executeUpdate();
		entityManager.close();
	}

	public CropTipMaster queryCropTipByAlertCode(String alertCode) {
		log.info("Inside CropTipMasterRepo -> queryCropTipByAlertCode");
		log.debug("AlertCode: " + alertCode);
		Query query = entityManager.createNativeQuery(getSql("checkAlertCode"),
				CropTipMaster.class);
		query.setParameter(1, alertCode);
		CropTipMaster entity = null;
		try {
			entity = (CropTipMaster) query.getSingleResult();
		} catch (NoResultException e) {
			log.error("No result found");
		} finally {
			entityManager.close();
		}
		return entity;
	}
	
	public CropTipMaster queryCropTipByAlertName(String alertName) {
		log.info("Inside CropTipMasterRepo -> queryCropTipByAlertName");
		log.debug("AlertName: " + alertName);
		Query query = entityManager.createNativeQuery(getSql("checkAlertName"),
				CropTipMaster.class);
		query.setParameter(1, alertName);
		CropTipMaster entity = null;
		try {
			entity = (CropTipMaster) query.getSingleResult();
		} catch (NoResultException e) {
			log.error("No result found");
		} finally {
			entityManager.close();
		}
		return entity;
	}
	
}
