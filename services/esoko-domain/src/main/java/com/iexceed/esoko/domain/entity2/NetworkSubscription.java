package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the network_subscription database table.
 * 
 */
@Entity
@Table(name = "network_subscription")
@NamedQuery(name = "NetworkSubscription.findAll", query = "SELECT n FROM NetworkSubscription n")
public class NetworkSubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NetworkSubscriptionPK id;

	private double balance;

	@Column(name = "subscription_type")
	private String subscriptionType;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	private String currency;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	private String message;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_ts")
	private Date modifiedTs;

	@Column(name = "network_name")
	private String networkName;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "prevent_logins")
	private String preventLogins;

	@Column(name = "record_status")
	private String recordStatus;

	@Column(name = "reseller_id")
	private String resellerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "suspend_debits")
	private String suspendDebits;

	public NetworkSubscription() {
	}

	public NetworkSubscriptionPK getId() {
		return this.id;
	}

	public void setId(NetworkSubscriptionPK id) {
		this.id = id;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getNetworkName() {
		return this.networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPreventLogins() {
		return this.preventLogins;
	}

	public void setPreventLogins(String preventLogins) {
		this.preventLogins = preventLogins;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSuspendDebits() {
		return this.suspendDebits;
	}

	public void setSuspendDebits(String suspendDebits) {
		this.suspendDebits = suspendDebits;
	}

	@Override
	public String toString() {
		return "NetworkSubscription [id=" + id + ", balance=" + balance
				+ ", subscriptionType=" + subscriptionType + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", currency="
				+ currency + ", endDate=" + endDate + ", message=" + message
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", networkName=" + networkName + ", ownerName=" + ownerName
				+ ", preventLogins=" + preventLogins + ", recordStatus="
				+ recordStatus + ", resellerId=" + resellerId + ", startDate="
				+ startDate + ", suspendDebits=" + suspendDebits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkName == null) ? 0 : networkName.hashCode());
		result = prime * result
				+ ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result
				+ ((preventLogins == null) ? 0 : preventLogins.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((resellerId == null) ? 0 : resellerId.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime
				* result
				+ ((subscriptionType == null) ? 0 : subscriptionType.hashCode());
		result = prime * result
				+ ((suspendDebits == null) ? 0 : suspendDebits.hashCode());
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
		NetworkSubscription other = (NetworkSubscription) obj;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
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
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
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
		if (networkName == null) {
			if (other.networkName != null)
				return false;
		} else if (!networkName.equals(other.networkName))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (preventLogins == null) {
			if (other.preventLogins != null)
				return false;
		} else if (!preventLogins.equals(other.preventLogins))
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
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (subscriptionType == null) {
			if (other.subscriptionType != null)
				return false;
		} else if (!subscriptionType.equals(other.subscriptionType))
			return false;
		if (suspendDebits == null) {
			if (other.suspendDebits != null)
				return false;
		} else if (!suspendDebits.equals(other.suspendDebits))
			return false;
		return true;
	}

}