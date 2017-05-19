package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the sms_controller database table.
 * 
 */
@Entity
@Table(name="sms_controller")
@NamedQuery(name="SmsController.findAll", query="SELECT s FROM SmsController s")
public class SmsController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String msisdn;

	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String timiLimit;

	public SmsController() {
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getTimiLimit() {
		return this.timiLimit;
	}

	public void setTimiLimit(String timiLimit) {
		this.timiLimit = timiLimit;
	}

	@Override
	public String toString() {
		return "SmsController [msisdn=" + msisdn + ", action=" + action
				+ ", createdTs=" + createdTs + ", timiLimit=" + timiLimit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result
				+ ((timiLimit == null) ? 0 : timiLimit.hashCode());
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
		SmsController other = (SmsController) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (timiLimit == null) {
			if (other.timiLimit != null)
				return false;
		} else if (!timiLimit.equals(other.timiLimit))
			return false;
		return true;
	}
		
}