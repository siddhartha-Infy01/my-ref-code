package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subscription_networks database table.
 * 
 */
@Embeddable
public class SubscriptionNetworkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subscription_id")
	private String subscriptionId;

	@Column(name="network_id")
	private String networkId;

	public SubscriptionNetworkPK() {
	}
	public String getSubscriptionId() {
		return this.subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
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
		if (!(other instanceof SubscriptionNetworkPK)) {
			return false;
		}
		SubscriptionNetworkPK castOther = (SubscriptionNetworkPK)other;
		return 
			this.subscriptionId.equals(castOther.subscriptionId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subscriptionId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SubscriptionNetworkPK [subscriptionId=" + subscriptionId
				+ ", networkId=" + networkId + "]";
	}
}