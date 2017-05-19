package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.MeasureFactor;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class MeasureFactorRepo extends
		AbstractRepoForEntity<MeasureFactor, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public MeasureFactor queryMeasureFactor(String baseMeasureId,
			String quoteMeasureId,String commodity,String networkId,String priceType) {
		log.info("Entered query measure factor by base measure and quote measure");
		log.info("baseMeasureId::" + baseMeasureId);
		log.info("quoteMeasureId::" + quoteMeasureId);
		MeasureFactor measurefctr;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryMeasureFactor"), MeasureFactor.class);
			query.setParameter(1, baseMeasureId);
			query.setParameter(2, quoteMeasureId);
			query.setParameter(3, commodity);
			query.setParameter(4, networkId);
			query.setParameter(5, priceType);
			
			measurefctr = (MeasureFactor) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return measurefctr;

	}

	public MeasureFactor queryByNwkCMdPrct(String networkId,
			String commodityId, String priceType) {

		log.info("Inside Measure Factor Repo");
		log.info("Network ID ->" + networkId);
		log.info("Commodity Id ->" + commodityId);
		log.info("Price Type ->" + priceType);
		MeasureFactor measureFactor = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryByNwkCmdPrct"), MeasureFactor.class);

			query.setParameter(1, networkId);
			query.setParameter(2, commodityId);
			query.setParameter(3, priceType);

			measureFactor = (MeasureFactor) query.getSingleResult();
			log.info("Result Set ->" + measureFactor);
		} catch (NoResultException e) {
			log.info("NoResultException ->" + measureFactor);
			return null;
		} catch (RuntimeException e) {
			log.info("RuntimeException ->" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return measureFactor;

	}

	public MeasureFactor queryByNwkCMdPrctDM(String networkId,
			String commodityId, String priceType) {

		log.info("Inside Measure Factor Repo-->queryByNwkCMdPrctDM");
		log.info("Network ID ->" + networkId);
		log.info("Commodity Id ->" + commodityId);
		log.info("Price Type ->" + priceType);
		MeasureFactor measureFactor = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryByNwkCmdPrctDM"), MeasureFactor.class);

			query.setParameter(1, networkId);
			query.setParameter(2, commodityId);
			query.setParameter(3, priceType);

			measureFactor = (MeasureFactor) query.getSingleResult();
			log.info("Result Set ->" + measureFactor);
		} catch (NoResultException e) {
			log.info("NoResultException ->" + measureFactor);
			return null;
		} catch (RuntimeException e) {
			log.info("RuntimeException ->" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return measureFactor;

	}

	public List<MeasureFactor> queryByNwkCMdPrct1(String networkId,
			String commodityId, String priceType) {

		log.info("Inside Measure Factor Repo");
		log.info("Network ID ->" + networkId);
		log.info("Commodity Id ->" + commodityId);
		log.info("Price Type ->" + priceType);
		MeasureFactor measureFactor = null;
		List<MeasureFactor> listofNwkMsrs = null;
		try {
			/*
			 * Query query = entityManager.createNativeQuery(
			 * getSql("queryByNwkCmdPrct"), MeasureFactor.class);
			 */
			Query query = entityManager.createNativeQuery(
					"{call queryCommMeasure(?,?,?)}", MeasureFactor.class);
			query.setParameter(1, networkId);
			query.setParameter(2, commodityId);
			query.setParameter(3, priceType);

			listofNwkMsrs = query.getResultList();
			log.info("Result Set ->" + measureFactor);
		} catch (NoResultException e) {
			log.info("NoResultException ->" + measureFactor);
			return null;
		} catch (RuntimeException e) {
			log.info("RuntimeException ->" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return listofNwkMsrs;

	}

	public List<MeasureFactor> queryByNwkandCommodity(String networkId,
			String commodityId) {
		List<MeasureFactor> listofNwkMsrs = null;

		Query query = entityManager.createNativeQuery(
				getSql("queryMsrsByNwkandCommodity"), MeasureFactor.class);
		query.setParameter(1, networkId);
		query.setParameter(2, "%" + commodityId + "%");
		listofNwkMsrs = query.getResultList();
		return listofNwkMsrs;

	}

	public List<MeasureFactor> queryByNwkandCommodityDefault(String networkId,
			String commodityId) {
		List<MeasureFactor> listofNwkMsrs = null;

		Query query = entityManager.createNativeQuery(
				getSql("queryMsrsByNwkandCommodityDflt"), MeasureFactor.class);
		query.setParameter(1, networkId);
		query.setParameter(2, commodityId);
		listofNwkMsrs = query.getResultList();
		return listofNwkMsrs;

	}

	public List<MeasureFactor> queryByNwk(String networkId) {

		List<MeasureFactor> listofNwkMsrs = null;

		Query query = entityManager.createNativeQuery(getSql("queryMsrsByNwk"),MeasureFactor.class);
		query.setParameter(1, networkId);
		listofNwkMsrs = query.getResultList();
		return listofNwkMsrs;

	}

	public MeasureFactor save(MeasureFactor measurefctr) {
		super.save(measurefctr);
		return measurefctr;

	}

	public MeasureFactor merge(MeasureFactor nwk) {
		nwk = super.merge(nwk);
		return nwk;
	}
	
	
}
