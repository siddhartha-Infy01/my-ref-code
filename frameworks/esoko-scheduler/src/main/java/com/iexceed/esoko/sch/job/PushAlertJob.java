package com.iexceed.esoko.sch.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.service.PushAlertSrvc;

public class PushAlertJob extends QuartzJobBean {

	//@Autowired
	//private PushAlertSrvc alertService;

	private int timeout;
	private static Logger log = LoggerUtils.getSchLogger();

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		log.debug("PushAlertJob -> Excecuting JOB");
		
		PushAlertSrvc alertService = Utils.springContext
				.getBean(PushAlertSrvc.class);
		alertService.startPushingmessages(ctx);
	}

}
