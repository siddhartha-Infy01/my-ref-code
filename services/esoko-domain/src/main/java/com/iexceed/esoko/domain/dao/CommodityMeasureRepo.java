package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.CommodityMeasure;
import com.iexceed.esoko.domain.entity.CommodityMeasurePK;

@Component
public class CommodityMeasureRepo extends
		AbstractRepoForEntity<CommodityMeasure, CommodityMeasurePK> {

	public List<CommodityMeasure> queryAllMeasures(String commodityId,
			String locationId) {

		List<CommodityMeasure> commodityMeasures = new ArrayList<CommodityMeasure>();

		Query query = entityManager
				.createNativeQuery(getSql("queryAllMeasuresByCommodityId"),
						CommodityMeasure.class);
		query.setParameter(1, commodityId);
		query.setParameter(2, locationId);
		commodityMeasures = query.getResultList();
		return commodityMeasures;

	}

	@Override
	public CommodityMeasure merge(CommodityMeasure entity) {
		return super.merge(entity);
	}

	@Override
	public CommodityMeasure save(CommodityMeasure entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

}
