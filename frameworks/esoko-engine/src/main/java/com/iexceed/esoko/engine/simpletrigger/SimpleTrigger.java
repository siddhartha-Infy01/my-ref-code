package com.iexceed.esoko.engine.simpletrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerKey.triggerKey;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class SimpleTrigger {

	private static Logger log = LoggerUtils.getSchLogger();

	public void creatTrigger(Class<? extends Job> jobClass, String triggerId,
			Date triggerDate) {
		log.info("Inside SimpleTrigger -> creatTrigger");
		log.debug("JobClass: " + jobClass.getName());
		log.debug("TriggerId: " + triggerId);
		log.debug("TriggerDate: " + triggerDate);
		if (Utils.scheduler != null) {
			TriggerKey triggerKey = triggerKey(triggerId);
			try {
				JobDetail job = newJob(jobClass)
						.withIdentity(jobClass.getName()).storeDurably(true)
						.build();
				Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(triggerId).startAt(triggerDate)
						.forJob(job).build();
				if (Utils.scheduler.checkExists(triggerKey)) {
					log.info("Trigger already exists");
					Utils.scheduler.rescheduleJob(triggerKey, trigger);
					log.info("Rescheduled successfully");
				} else {
					Utils.scheduler.addJob(job, true);
					Utils.scheduler.scheduleJob(trigger);
					log.debug("Job scheduled successfully");
				}
			} catch (SchedulerException e) {
				log.error(Utils.getStackTrace(e));
			}
		}
	}

}
