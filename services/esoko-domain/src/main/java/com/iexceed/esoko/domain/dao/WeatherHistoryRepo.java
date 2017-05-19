package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.WeatherHistory;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class WeatherHistoryRepo extends AbstractRepoForEntity<WeatherHistory, String> {
	public static Logger log = LoggerUtils.getLogger();
	
	

	
	public WeatherHistory save(WeatherHistory weatherhist) {
		super.save(weatherhist);
		return weatherhist;

	}
}
