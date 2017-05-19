package com.iexceed.esoko.sch.trg;

import java.time.LocalTime;
import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;

/**
 * @author Debabrata SI Version 1.0
 *
 */
public class CronUtils {
	private static Logger log = LoggerUtils.getLogger();

	public static String createCronExpression(TriggerDetails triggerDetails) {
		log.info("CronUtils-->createCronExpression");
		StringBuffer expression = null;
		expression = new StringBuffer();
		if (triggerDetails != null) {
			if (triggerDetails.getRepeats() != null) {
				String repeats = triggerDetails.getRepeats();
				String scheduleDays = null;
				LocalTime startTime = triggerDetails.getStartTime();
				int hour = startTime.getHour();
				int minute = startTime.getMinute();
				int second = startTime.getSecond();
				if (repeats != null) {
					if (repeats.equalsIgnoreCase("Weekly")) {
						scheduleDays = triggerDetails.getScheduleDays();
						expression
								.append(String.valueOf(second) + " "
										+ String.valueOf(minute) + " "
										+ String.valueOf(hour) + " ? * "
										+ scheduleDays);
					} else if (repeats.equalsIgnoreCase("Monthly")) {

						scheduleDays = triggerDetails.getScheduleDays();
						if (scheduleDays != null) {
							if (!scheduleDays.contains(",")) {
								expression.append(String.valueOf(second) + " "
										+ String.valueOf(minute) + " "
										+ String.valueOf(hour) + " "
										+ scheduleDays + " */1 ?");
							} else {
								String[] scheduleDay = triggerDetails
										.getScheduleDays().split(",");
								if (!scheduleDay[0].equalsIgnoreCase("L")) {
									expression.append(String.valueOf(second)
											+ " " + String.valueOf(minute)
											+ " " + String.valueOf(hour)
											+ "  ? */1 " + scheduleDay[1] + "#"
											+ scheduleDay[0]);
								} else {
									expression
											.append(String.valueOf(second)
													+ " "
													+ String.valueOf(minute)
													+ " "
													+ String.valueOf(hour)
													+ "  ? */1 "
													+ scheduleDay[1] + "L");
								}
							}

						}
					}

				}
			}

		}
		log.debug("Cron Expression:" + expression.toString());
		return expression.toString();
	}
}
