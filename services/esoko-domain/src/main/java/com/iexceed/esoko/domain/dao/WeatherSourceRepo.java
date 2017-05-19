package com.iexceed.esoko.domain.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.WeatherSource;
import com.iexceed.esoko.domain.entity.WeatherSourcePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Repository
public class WeatherSourceRepo extends
		AbstractRepoForEntity<WeatherSource, WeatherSourcePK> {
	public static Logger log = LoggerUtils.getLogger();

	public WeatherSource save(WeatherSource weatherSource) {
		super.save(weatherSource);
		return weatherSource;

	}

	public WeatherSource findOne(WeatherSource source, WeatherSourcePK pk) {

		return super.findOne(WeatherSource.class, pk);
	}

	public boolean exists(WeatherSource entity, WeatherSourcePK pk) {
		return super.exists(WeatherSource.class, pk);

	}

	public WeatherSource merge(WeatherSource entity) {
		return super.merge(entity);
	}

	public void delete(WeatherSource weatherSource) {
		super.delete(weatherSource);
	}

	public List<WeatherSource> queryWeatherSrcByNwkIdUserID(String networkId, String userId) {
		log.info("Inside WeatherSourceRepo -> queryWeatherSrcByNwkIdUserID");
		log.debug("NetworkId: " + networkId);
		log.debug("userId: " + userId);
		List<WeatherSource> weatherSrcList = null;
		Query query = entityManager.createNativeQuery(getSql("queryWeatherSrcByNwkIdSrc"),
				WeatherSource.class);
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		weatherSrcList = query.getResultList();
		return weatherSrcList;
	}
}
