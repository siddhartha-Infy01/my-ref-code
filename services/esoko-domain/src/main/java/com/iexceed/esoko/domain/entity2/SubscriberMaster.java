package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the subscriber_master database table.
 * 
 */
@Entity
@Table(name="subscriber_master")
@NamedQuery(name="SubscriberMaster.findAll", query="SELECT s FROM SubscriberMaster s")
public class SubscriberMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="subscriber_id")
	private String subscriberId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	private String email;

	private String gender;

	private String location;

	private String mobile;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="network_id")
	private String networkId;

	private String occupations;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="reseller_id")
	private String resellerId;

	@Column(name="subscription_type")
	private String subscriptionType;

	public SubscriberMaster() {
	}

	public String getSubscriberId() {
		return this.subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
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

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getOccupations() {
		return this.occupations;
	}

	public void setOccupations(String occupations) {
		this.occupations = occupations;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getResellerId() {
		return this.resellerId;
	}

	public void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}

	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((occupations == null) ? 0 : occupations.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((resellerId == null) ? 0 : resellerId.hashCode());
		result = prime * result
				+ ((subscriberId == null) ? 0 : subscriberId.hashCode());
		result = prime
				* result
				+ ((subscriptionType == null) ? 0 : subscriptionType.hashCode());
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
		SubscriberMaster other = (SubscriberMaster) obj;
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
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
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
		if (occupations == null) {
			if (other.occupations != null)
				return false;
		} else if (!occupations.equals(other.occupations))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (resellerId == null) {
			if (other.resellerId != null)
				return false;
		} else if (!resellerId.equals(other.resellerId))
			return false;
		if (subscriberId == null) {
			if (other.subscriberId != null)
				return false;
		} else if (!subscriberId.equals(other.subscriberId))
			return false;
		if (subscriptionType == null) {
			if (other.subscriptionType != null)
				return false;
		} else if (!subscriptionType.equals(other.subscriptionType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriberMaster [subscriberId=" + subscriberId
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", dob=" + dob + ", email=" + email + ", gender=" + gender
				+ ", location=" + location + ", mobile=" + mobile
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", networkId=" + networkId + ", occupations=" + occupations
				+ ", recordStatus=" + recordStatus + ", resellerId="
				+ resellerId + ", subscriptionType=" + subscriptionType + "]";
	}

}