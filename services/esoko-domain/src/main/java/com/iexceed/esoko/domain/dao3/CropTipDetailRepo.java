package com.iexceed.esoko.domain.dao3;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.CropTipDetail;
import com.iexceed.esoko.domain.entity3.CropTipDetailPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class CropTipDetailRepo extends
		AbstractRepoForEntity<CropTipDetail, CropTipDetailPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(CropTipDetail entity, CropTipDetailPK id) {
		log.info("Inside CropTipDetailRepo -> exists");
		return super.exists(CropTipDetail.class, id);
	}

	public CropTipDetail merge(CropTipDetail entity) {
		log.info("Inside CropTipDetailRepo -> merge");
		return super.merge(entity);
	}

	public CropTipDetail save(CropTipDetail entity) {
		log.info("Inside CropTipDetailRepo -> save");
		return super.save(entity);
	}

	public CropTipDetail findOne(CropTipDetail entity, CropTipDetailPK id) {
		log.info("Inside CropTipDetailRepo -> findOne");
		return super.findOne(CropTipDetail.class, id);
	}

	public void delete(CropTipDetail entity) {
		log.info("Inside CropTipDetailRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside CropTipDetailRepo -> deleteAll");
		super.delete(entities);
	}

	public List<CropTipDetail> findAllCropDetails(String tipId) {
		log.info("Inside CropTipDetailRepo -> findAllCropDetails");
		log.debug("TipId: " + tipId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllCropDetails"), CropTipDetail.class);
		query.setParameter(1, tipId);
		List<CropTipDetail> detailList = query.getResultList();
		entityManager.close();
		return detailList;
	}

	public void updateCropTip(String tipId, String tipNumber,
			Date transmissionDate, String modifiedBy) {
		log.info("Inside CropTipDetailRepo -> updateCropTip");
		log.debug("TipId: " + tipId);
		log.debug("TipNumber: " + tipNumber);
		log.debug("TransmissionDate: " + transmissionDate);
		log.debug("ModifiedBy: " + modifiedBy);
		Query query = entityManager.createNativeQuery(getSql("updateCropTip"));
		query.setParameter(1, transmissionDate);
		query.setParameter(2, modifiedBy);
		query.setParameter(3, new Date());
		query.setParameter(4, tipId);
		query.setParameter(5, tipNumber);
		query.executeUpdate();
		entityManager.close();
	}

}
