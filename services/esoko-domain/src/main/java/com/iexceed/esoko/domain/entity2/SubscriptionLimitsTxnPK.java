package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subscription_limits_txn database table.
 * 
 */
@Embeddable
public class SubscriptionLimitsTxnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subscription_type")
	private String subscriptionType;

	@Column(name="network_id")
	private String networkId;

	public SubscriptionLimitsTxnPK() {
	}
	public String getSubscriptionType() {
		return this.subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriptionLimitsTxnPK)) {
			return false;
		}
		SubscriptionLimitsTxnPK castOther = (SubscriptionLimitsTxnPK)other;
		return 
			this.subscriptionType.equals(castOther.subscriptionType)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subscriptionType.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SubscriptionLimitsTxnPK [subscriptionType=" + subscriptionType
				+ ", networkId=" + networkId + "]";
	}
}