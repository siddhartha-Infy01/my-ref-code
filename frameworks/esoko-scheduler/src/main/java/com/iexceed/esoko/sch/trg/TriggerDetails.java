package com.iexceed.esoko.sch.trg;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Debabrata SI
 *
 */
public class TriggerDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -126948337058963497L;
	private String triggerId;
	private String frequency;
	private String repeats;
	private String scheduleDays;
	private Date startDate;
	private Date endDate;
	private LocalTime startTime;
	private boolean flag;

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}

	public String getScheduleDays() {
		return scheduleDays;
	}

	public void setScheduleDays(String scheduleDays) {
		this.scheduleDays = scheduleDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

}
