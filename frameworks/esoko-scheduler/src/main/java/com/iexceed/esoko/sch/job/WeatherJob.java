package com.iexceed.esoko.sch.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.service.WeatherSrvc;

public class WeatherJob extends QuartzJobBean {
	private static Logger log = LoggerUtils.getSchLogger();

	private WeatherSrvc service;

	@Override
	protected void executeInternal(JobExecutionContext cxt)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("Inside WeatherJob -> executeInternal");
		if (Utils.springContext == null) {
			log.info("Spring context is null");
		} else {
			service.startPushingWeather();
		}
	}

}
