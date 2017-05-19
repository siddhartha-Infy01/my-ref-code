package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the poll_master database table.
 * 
 */
@Entity
@Table(name="poll_master")
@NamedQuery(name="PollMaster.findAll", query="SELECT p FROM PollMaster p")
public class PollMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="poll_id")
	private String pollId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String frequency;

	private String interpret;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="network_id")
	private String networkId;

	@Column(name="payee_acc_no")
	private String payeeAccNo;

	@Column(name="payee_type")
	private String payeeType;

	@Temporal(TemporalType.DATE)
	@Column(name="poll_active_till")
	private Date pollActiveTill;

	@Column(name="poll_code")
	private String pollCode;

	@Column(name="poll_name")
	private String pollName;

	@Column(name="poll_state")
	private String pollState;

	@Column(name="public_keyword")
	private String publicKeyword;

	@Lob
	private String reminder;

	@Column(name="repeat_flag")
	private String repeatFlag;

	@Column(name="schedule_days")
	private String scheduleDays;

	@Column(name="schedule_time")
	private Time scheduleTime;

	@Column(name="send_reminder")
	private int sendReminder;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="stop_interval")
	private int stopInterval;

	@Column(name="stop_measure")
	private String stopMeasure;

	public PollMaster() {
	}

	public String getPollId() {
		return this.pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getInterpret() {
		return this.interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getPayeeAccNo() {
		return this.payeeAccNo;
	}

	public void setPayeeAccNo(String payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}

	public String getPayeeType() {
		return this.payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public Date getPollActiveTill() {
		return this.pollActiveTill;
	}

	public void setPollActiveTill(Date pollActiveTill) {
		this.pollActiveTill = pollActiveTill;
	}

	public String getPollCode() {
		return this.pollCode;
	}

	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}

	public String getPollName() {
		return this.pollName;
	}

	public void setPollName(String pollName) {
		this.pollName = pollName;
	}

	public String getPollState() {
		return this.pollState;
	}

	public void setPollState(String pollState) {
		this.pollState = pollState;
	}

	public String getPublicKeyword() {
		return this.publicKeyword;
	}

	public void setPublicKeyword(String publicKeyword) {
		this.publicKeyword = publicKeyword;
	}

	public String getReminder() {
		return this.reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getRepeatFlag() {
		return this.repeatFlag;
	}

	public void setRepeatFlag(String repeatFlag) {
		this.repeatFlag = repeatFlag;
	}

	public String getScheduleDays() {
		return this.scheduleDays;
	}

	public void setScheduleDays(String scheduleDays) {
		this.scheduleDays = scheduleDays;
	}

	public Time getScheduleTime() {
		return this.scheduleTime;
	}

	public void setScheduleTime(Time scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public int getSendReminder() {
		return this.sendReminder;
	}

	public void setSendReminder(int sendReminder) {
		this.sendReminder = sendReminder;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStopInterval() {
		return this.stopInterval;
	}

	public void setStopInterval(int stopInterval) {
		this.stopInterval = stopInterval;
	}

	public String getStopMeasure() {
		return this.stopMeasure;
	}

	public void setStopMeasure(String stopMeasure) {
		this.stopMeasure = stopMeasure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result
				+ ((interpret == null) ? 0 : interpret.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((payeeAccNo == null) ? 0 : payeeAccNo.hashCode());
		result = prime * result
				+ ((payeeType == null) ? 0 : payeeType.hashCode());
		result = prime * result
				+ ((pollActiveTill == null) ? 0 : pollActiveTill.hashCode());
		result = prime * result
				+ ((pollCode == null) ? 0 : pollCode.hashCode());
		result = prime * result + ((pollId == null) ? 0 : pollId.hashCode());
		result = prime * result
				+ ((pollName == null) ? 0 : pollName.hashCode());
		result = prime * result
				+ ((pollState == null) ? 0 : pollState.hashCode());
		result = prime * result
				+ ((publicKeyword == null) ? 0 : publicKeyword.hashCode());
		result = prime * result
				+ ((reminder == null) ? 0 : reminder.hashCode());
		result = prime * result
				+ ((repeatFlag == null) ? 0 : repeatFlag.hashCode());
		result = prime * result
				+ ((scheduleDays == null) ? 0 : scheduleDays.hashCode());
		result = prime * result
				+ ((scheduleTime == null) ? 0 : scheduleTime.hashCode());
		result = prime * result + sendReminder;
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + stopInterval;
		result = prime * result
				+ ((stopMeasure == null) ? 0 : stopMeasure.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PollMaster other = (PollMaster) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (interpret == null) {
			if (other.interpret != null)
				return false;
		} else if (!interpret.equals(other.interpret))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (payeeAccNo == null) {
			if (other.payeeAccNo != null)
				return false;
		} else if (!payeeAccNo.equals(other.payeeAccNo))
			return false;
		if (payeeType == null) {
			if (other.payeeType != null)
				return false;
		} else if (!payeeType.equals(other.payeeType))
			return false;
		if (pollActiveTill == null) {
			if (other.pollActiveTill != null)
				return false;
		} else if (!pollActiveTill.equals(other.pollActiveTill))
			return false;
		if (pollCode == null) {
			if (other.pollCode != null)
				return false;
		} else if (!pollCode.equals(other.pollCode))
			return false;
		if (pollId == null) {
			if (other.pollId != null)
				return false;
		} else if (!pollId.equals(other.pollId))
			return false;
		if (pollName == null) {
			if (other.pollName != null)
				return false;
		} else if (!pollName.equals(other.pollName))
			return false;
		if (pollState == null) {
			if (other.pollState != null)
				return false;
		} else if (!pollState.equals(other.pollState))
			return false;
		if (publicKeyword == null) {
			if (other.publicKeyword != null)
				return false;
		} else if (!publicKeyword.equals(other.publicKeyword))
			return false;
		if (reminder == null) {
			if (other.reminder != null)
				return false;
		} else if (!reminder.equals(other.reminder))
			return false;
		if (repeatFlag == null) {
			if (other.repeatFlag != null)
				return false;
		} else if (!repeatFlag.equals(other.repeatFlag))
			return false;
		if (scheduleDays == null) {
			if (other.scheduleDays != null)
				return false;
		} else if (!scheduleDays.equals(other.scheduleDays))
			return false;
		if (scheduleTime == null) {
			if (other.scheduleTime != null)
				return false;
		} else if (!scheduleTime.equals(other.scheduleTime))
			return false;
		if (sendReminder != other.sendReminder)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (stopInterval != other.stopInterval)
			return false;
		if (stopMeasure == null) {
			if (other.stopMeasure != null)
				return false;
		} else if (!stopMeasure.equals(other.stopMeasure))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PollMaster [pollId=" + pollId + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", currencyId=" + currencyId
				+ ", endDate=" + endDate + ", frequency=" + frequency
				+ ", interpret=" + interpret + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + ", networkId=" + networkId
				+ ", payeeAccNo=" + payeeAccNo + ", payeeType=" + payeeType
				+ ", pollActiveTill=" + pollActiveTill + ", pollCode="
				+ pollCode + ", pollName=" + pollName + ", pollState="
				+ pollState + ", publicKeyword=" + publicKeyword
				+ ", reminder=" + reminder + ", repeatFlag=" + repeatFlag
				+ ", scheduleDays=" + scheduleDays + ", scheduleTime="
				+ scheduleTime + ", sendReminder=" + sendReminder
				+ ", startDate=" + startDate + ", stopInterval=" + stopInterval
				+ ", stopMeasure=" + stopMeasure + "]";
	}	

}