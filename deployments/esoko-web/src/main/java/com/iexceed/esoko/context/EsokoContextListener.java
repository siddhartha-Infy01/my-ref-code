package com.iexceed.esoko.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.trg.TriggerHandler;

public class EsokoContextListener implements ServletContextListener {

	private static Logger log = LoggerUtils.getLogger();

	public void contextInitialized(ServletContextEvent contextEvent) {
		log.info("Inside EsokoContextListener -> contextInitialized");
		ServletContext ctx = contextEvent.getServletContext();
		Utils.springContext = WebApplicationContextUtils
				.getWebApplicationContext(ctx);	
		log.info("WebApplicationContext intialized");
		TriggerHandler triggerHandler = TriggerHandler.getInstance();
		triggerHandler.startQuartz();
		triggerHandler.createSmrtGrpPltnJob();
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

}
