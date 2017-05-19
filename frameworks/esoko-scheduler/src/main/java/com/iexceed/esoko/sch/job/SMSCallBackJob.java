package com.iexceed.esoko.sch.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.service.PushAlertSrvc;
import com.iexceed.esoko.sch.service.SmsCallBackSrvc;

public class SMSCallBackJob extends QuartzJobBean {

	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		SmsCallBackSrvc smsCallbackService = Utils.springContext
				.getBean(SmsCallBackSrvc.class);

	}

}
