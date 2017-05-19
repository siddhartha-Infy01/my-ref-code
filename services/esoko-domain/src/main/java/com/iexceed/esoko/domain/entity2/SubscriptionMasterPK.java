package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subscription_master database table.
 * 
 */
@Embeddable
public class SubscriptionMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subscription_id")
	private int subscriptionId;

	@Column(name="subscription_type")
	private String subscriptionType;

	public SubscriptionMasterPK() {
	}
	public int getSubscriptionId() {
		return this.subscriptionId;
	}
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getSubscriptionType() {
		return this.subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriptionMasterPK)) {
			return false;
		}
		SubscriptionMasterPK castOther = (SubscriptionMasterPK)other;
		return 
			(this.subscriptionId == castOther.subscriptionId)
			&& this.subscriptionType.equals(castOther.subscriptionType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subscriptionId;
		hash = hash * prime + this.subscriptionType.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SubscriptionMasterPK [subscriptionId=" + subscriptionId
				+ ", subscriptionType=" + subscriptionType + "]";
	}
}