package com.iexceed.esoko.sch.trg;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.job.SmartGroupPopulationJob;

/**
 * @author Debabrata SI Version 1.0
 * 
 * 
 */
public class TriggerHandler {
	private static Logger log = LoggerUtils.getSchLogger();
	private static Logger smrtLog = LoggerUtils.getSmartGroupLogger();	
	private static TriggerHandler instance;

	/**
	 * reading quartz properties and based on these properties it creates
	 * scheduler object
	 */
	private TriggerHandler() {

	}

	public void startQuartz() {
		try {
			Utils.scheduler = new StdSchedulerFactory("quartz.properties")
					.getScheduler();
			log.debug("Scheduler name" + Utils.scheduler.getSchedulerName());
			log.debug("scheduler id" + Utils.scheduler.getSchedulerInstanceId());
			Utils.scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	public static TriggerHandler getInstance() {
		if (instance == null) {
			instance = new TriggerHandler();
		}
		return instance;
	}

	public void stopQuartz() {
		try {
			if (Utils.scheduler != null) {
				Utils.scheduler.shutdown();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creating a trigger from trigger details
	 * 
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	private Trigger createTrigger(Class<? extends Job> jobClass,
			TriggerDetails triggerDetails) {

		log.info("Inside TriggerHandler -> createTrigger");
		log.debug("TriggerId: " + triggerDetails.getTriggerId());
		log.debug("Frequency: " + triggerDetails.getFrequency());
		log.debug("Repeats: " + triggerDetails.getRepeats());
		log.debug("Schedule days: " + triggerDetails.getScheduleDays());
		log.debug("Start date: " + triggerDetails.getStartDate());
		log.debug("End date: " + triggerDetails.getEndDate());
		log.debug("Start time: " + triggerDetails.getStartTime());
		Trigger trigger = null;
		if (Utils.scheduler != null && triggerDetails != null) {
			try {
				if (!Utils.scheduler.checkExists(triggerKey(triggerDetails
						.getTriggerId()))) {
					JobDetail job = newJob(jobClass)
							.withIdentity(jobClass.getName())
							.storeDurably(true).build();

					if ("recurring".equalsIgnoreCase(triggerDetails
							.getFrequency())) {
						TriggerKey triggerKey = triggerKey(triggerDetails
								.getTriggerId());

						if (triggerDetails.isFlag()) {
							try {
								trigger = newTrigger()
										.withIdentity(triggerKey)
										.startAt(triggerDetails.getStartDate())
										.withSchedule(
												cronSchedule(new CronExpression(
														CronUtils
																.createCronExpression(triggerDetails))))
										.forJob(job).build();
							} catch (ParseException e) {
								e.printStackTrace();
							}
						} else {
							trigger = newTrigger()
									.withIdentity(triggerKey)
									.startAt(triggerDetails.getStartDate())
									.withSchedule(
											cronSchedule(new CronExpression(
													CronUtils
															.createCronExpression(triggerDetails))))
									.endAt(triggerDetails.getEndDate())
									.forJob(job).build();
						}
						log.debug("Cron Expression"
								+ CronUtils
										.createCronExpression(triggerDetails));

					} else {
						trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
								.withIdentity(triggerDetails.getTriggerId())
								.startAt(triggerDetails.getStartDate())
								.forJob(job).build();
					}
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return trigger;
	}

	/**
	 * Activate the existing trigger and return TriggerDetails
	 * 
	 * @throws SchedulerException
	 * @throws InterruptedException
	 */
	public void activateTrigger(Class<? extends Job> jobClass,
			TriggerDetails triggerDetails) {
		log.info("Inside TriggerHandler -> activateTrigger");
		if (Utils.scheduler != null) {
			try {
				TriggerKey triggerKey = triggerKey(triggerDetails
						.getTriggerId());
				if (Utils.scheduler.getTriggerState(triggerKey).equals(
						Trigger.TriggerState.NONE)) {
					Trigger trigger = this.createTrigger(jobClass,
							triggerDetails);
					log.debug("scheduler state:"
							+ Utils.scheduler.getTriggerState(triggerKey));
					if (trigger != null) {
						JobDetail job = newJob(jobClass)
								.withIdentity(jobClass.getName())
								.storeDurably(true).build();
						Utils.scheduler.addJob(job, true);
						Utils.scheduler.scheduleJob(trigger);
						log.debug("Job scheduled successfully");
					}

				} else if (Utils.scheduler.getTriggerState(triggerKey).equals(
						Trigger.TriggerState.PAUSED)) {
					Utils.scheduler.resumeTrigger(triggerKey);
					log.debug("scheduler state:"
							+ Utils.scheduler.getTriggerState(triggerKey));
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	public void reactivateTrigger(TriggerDetails triggerDetails) {
		log.info("Inside TriggerHandler -> reactivateTrigger");
		if (Utils.scheduler != null) {
			try {
				TriggerKey triggerKey = triggerKey(triggerDetails
						.getTriggerId());
				if (Utils.scheduler.checkExists(triggerKey)) {
					if (Utils.scheduler.getTriggerState(triggerKey).equals(
							Trigger.TriggerState.PAUSED)) {
						Utils.scheduler.resumeTrigger(triggerKey);
						log.debug("scheduler state:"
								+ Utils.scheduler.getTriggerState(triggerKey));
					}
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deactivate the existing trigger and return TriggerDetails
	 * 
	 * @throws SchedulerException
	 * 
	 */
	public void deactivateTrigger(TriggerDetails triggerDetails) {
		log.info("Inside TriggerHandler -> deactivateTrigger");
		if (Utils.scheduler != null) {
			try {
				TriggerKey triggerKey = triggerKey(triggerDetails
						.getTriggerId());
				if (Utils.scheduler.getTriggerState(triggerKey).equals(
						Trigger.TriggerState.NORMAL)) {
					Utils.scheduler.pauseTrigger(triggerKey);
					log.debug("scheduler state:"
							+ Utils.scheduler.getTriggerState(triggerKey));
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Update existing trigger with newTrigger and return trigger details
	 * 
	 * @throws ParseException
	 * @throws SchedulerException
	 */
	public void updateTrigger(Class<? extends Job> jobClass,
			TriggerDetails triggerDetails) {
		log.info("Inside TriggerHandler -> updateTrigger");
		log.debug("TriggerId: " + triggerDetails.getTriggerId());
		log.debug("Frequency: " + triggerDetails.getFrequency());
		log.debug("Repeats: " + triggerDetails.getRepeats());
		log.debug("Schedule days: " + triggerDetails.getScheduleDays());
		log.debug("Start date: " + triggerDetails.getStartDate());
		log.debug("End date: " + triggerDetails.getEndDate());
		log.debug("Start time: " + triggerDetails.getStartTime());
		Trigger newTrigger = null;
		if (Utils.scheduler != null) {
			{
				try {
					if (Utils.scheduler.checkExists(triggerKey(triggerDetails
							.getTriggerId()))) {

						JobDetail job = newJob(jobClass)
								.withIdentity(jobClass.getName())
								.storeDurably(true).build();

						TriggerKey triggerKey = triggerKey(triggerDetails
								.getTriggerId());
						if ("recurring".equalsIgnoreCase(triggerDetails
								.getFrequency())) {
							newTrigger = newTrigger()
									.withIdentity(triggerKey)
									.startAt(triggerDetails.getStartDate())
									.withSchedule(
											cronSchedule(new CronExpression(
													CronUtils
															.createCronExpression(triggerDetails))))
									.endAt(triggerDetails.getEndDate())
									.forJob(job).build();
							Utils.scheduler.rescheduleJob(triggerKey, newTrigger);
						} else {
							newTrigger = (SimpleTrigger) TriggerBuilder
									.newTrigger()
									.withIdentity(triggerDetails.getTriggerId())
									.startAt(triggerDetails.getStartDate())
									.forJob(job).build();
							Utils.scheduler.rescheduleJob(triggerKey, newTrigger);

						}
					}
				} catch (SchedulerException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Delete existing trigger and return the trigger details.
	 * 
	 * @throws SchedulerException
	 */
	public void deleteTrigger(TriggerDetails triggerDetails) {
		log.info("Inside TriggerHandler -> deleteTrigger");
		if (Utils.scheduler != null) {
			try {
				TriggerKey triggerKey = triggerKey(triggerDetails
						.getTriggerId());
				Utils.scheduler.unscheduleJob(triggerKey);
				log.debug("scheduler state:"
						+ Utils.scheduler.getTriggerState(triggerKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	public void createSmrtGrpPltnJob() {
		try {
			if (!Utils.scheduler
					.checkExists(triggerKey("SMART_GRP_POPULATION_TRIGGER"))) {
				Trigger trigger = newTrigger()
						.withIdentity("SMART_GRP_POPULATION_TRIGGER")
						.withSchedule(
								cronSchedule(new CronExpression(
										"0 0/5 * 1/1 * ? *"))).build();
				JobDetail job = newJob(SmartGroupPopulationJob.class)
						.withIdentity("SMART_GRP_POPULATION_JOB").build();
				Utils.scheduler.scheduleJob(job, trigger);
				smrtLog.info("Trigger created for SmartGroup job.");
			} else {
				smrtLog.info("Trigger for SmartGroup job exists.");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
