package com.iexceed.esoko.domain.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class WeatherRepo extends AbstractRepoForEntity<Weather, String> {

	public static Logger log = LoggerUtils.getLogger();

	public Weather queryWeatherbyLocId(String locationId) {
		log.info("Entered query Weather by locationId");
		log.info("locationId::" + locationId);
		Weather weather;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryWeatherbyLocId"), Weather.class);
			query.setParameter(1, locationId);
			weather = (Weather) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return weather;

	}

	public List<Weather> queryWeather() {
		log.info("Entered query weather");

		Query query = entityManager.createNativeQuery(getSql("queryWeather"),
				Weather.class);

		List<Weather> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public Weather save(Weather weather) {
		super.save(weather);
		return weather;

	}

	public Weather findOne(Weather entity, String id) {

		return super.findOne(Weather.class, id);
	}

	public boolean exists(Weather entity, String id) {
		return super.exists(Weather.class, id);

	}

	public Weather merge(Weather entity) {
		return super.merge(entity);
	}

	public String getWeatherTemplate() {
		return getSql("weatherTemplate");
	}

	public List<Weather> queryWeatherByCrntDt(Date date, String location_id,
			String source) {
		log.info("Entered into queryWeatherByLocIdSrc");
		log.debug("Location id:" + location_id);
		log.debug("Source:" + source);
		Query query = this.entityManager.createNativeQuery(
				getSql("queryWeatherByCrntDt"), Weather.class);
		query.setParameter(1, date);
		query.setParameter(2, location_id);
		query.setParameter(3, source);
		List<Weather> listWeathers = query.getResultList();
		this.entityManager.close();
		return listWeathers;
	}
	
	 public List<Weather> queryWeatherByLocIdSrc(Date date, String location_id, String source)
	  {
	    log.info("Entered into queryWeatherByLocIdSrc");
	    log.debug("Location id:" + location_id);
	    log.debug("Source:" + source);
	    Query query = this.entityManager.createNativeQuery(
	      getSql("queryWeatherByLocIdSource"), Weather.class);
	    query.setParameter(1, date);
	    query.setParameter(2, location_id);
	    query.setParameter(3, source);
	    List<Weather> listWeathers = query.getResultList();
	    this.entityManager.close();
	    return listWeathers;
	  }
	 
	 
}
