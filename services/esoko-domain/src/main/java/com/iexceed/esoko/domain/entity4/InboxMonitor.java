package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the inbox_monitor database table.
 * 
 */
@Entity
@Table(name="inbox_monitor")
@NamedQuery(name="InboxMonitor.findAll", query="SELECT i FROM InboxMonitor i")
public class InboxMonitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="activity_id")
	private int activityId;

	private String channel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="error_stat")
	private String errorStat;

	@Lob
	private String message;

	private String msisdn;

	@Column(name="network_id")
	private String networkId;

	@Column(name="people_id")
	private String peopleId;

	@Column(name="service_details")
	private String serviceDetails;

	@Column(name="service_name")
	private String serviceName;

	public InboxMonitor() {
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getErrorStat() {
		return this.errorStat;
	}

	public void setErrorStat(String errorStat) {
		this.errorStat = errorStat;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getPeopleId() {
		return this.peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getServiceDetails() {
		return this.serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((errorStat == null) ? 0 : errorStat.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((peopleId == null) ? 0 : peopleId.hashCode());
		result = prime * result
				+ ((serviceDetails == null) ? 0 : serviceDetails.hashCode());
		result = prime * result
				+ ((serviceName == null) ? 0 : serviceName.hashCode());
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
		InboxMonitor other = (InboxMonitor) obj;
		if (activityId != other.activityId)
			return false;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (errorStat == null) {
			if (other.errorStat != null)
				return false;
		} else if (!errorStat.equals(other.errorStat))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (peopleId == null) {
			if (other.peopleId != null)
				return false;
		} else if (!peopleId.equals(other.peopleId))
			return false;
		if (serviceDetails == null) {
			if (other.serviceDetails != null)
				return false;
		} else if (!serviceDetails.equals(other.serviceDetails))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InboxMonitor [activityId=" + activityId + ", channel="
				+ channel + ", createdTs=" + createdTs + ", errorStat="
				+ errorStat + ", message=" + message + ", msisdn=" + msisdn
				+ ", networkId=" + networkId + ", peopleId=" + peopleId
				+ ", serviceDetails=" + serviceDetails + ", serviceName="
				+ serviceName + "]";
	}
		
}