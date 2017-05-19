package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the subscriptions database table.
 * 
 */
@Entity
@Table(name="subscriptions")
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="subscription_id")
	private String subscriptionId;

	@Column(name="base_subscription_id")
	private String baseSubscriptionId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String name;

	@Column(name="primary_network_id")
	private String primaryNetworkId;

	@Column(name="record_status")
	private String recordStatus;

	private String type;

	public Subscription() {
	}

	public String getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getBaseSubscriptionId() {
		return this.baseSubscriptionId;
	}

	public void setBaseSubscriptionId(String baseSubscriptionId) {
		this.baseSubscriptionId = baseSubscriptionId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryNetworkId() {
		return this.primaryNetworkId;
	}

	public void setPrimaryNetworkId(String primaryNetworkId) {
		this.primaryNetworkId = primaryNetworkId;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((baseSubscriptionId == null) ? 0 : baseSubscriptionId
						.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((primaryNetworkId == null) ? 0 : primaryNetworkId.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Subscription other = (Subscription) obj;
		if (baseSubscriptionId == null) {
			if (other.baseSubscriptionId != null)
				return false;
		} else if (!baseSubscriptionId.equals(other.baseSubscriptionId))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (primaryNetworkId == null) {
			if (other.primaryNetworkId != null)
				return false;
		} else if (!primaryNetworkId.equals(other.primaryNetworkId))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [subscriptionId=" + subscriptionId
				+ ", baseSubscriptionId=" + baseSubscriptionId + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", modifiedBy="
				+ modifiedBy + ", modifiedTs=" + modifiedTs + ", name=" + name
				+ ", primaryNetworkId=" + primaryNetworkId + ", recordStatus="
				+ recordStatus + ", type=" + type + "]";
	}

}