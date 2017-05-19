package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subscription_limits database table.
 * 
 */
@Embeddable
public class SubscriptionLimitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subscription_type")
	private String subscriptionType;

	@Column(name="sub_level")
	private String subLevel;

	@Column(name="sub_id")
	private String subId;

	public SubscriptionLimitPK() {
	}
	public String getSubscriptionType() {
		return this.subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getSubLevel() {
		return this.subLevel;
	}
	public void setSubLevel(String subLevel) {
		this.subLevel = subLevel;
	}
	public String getSubId() {
		return this.subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriptionLimitPK)) {
			return false;
		}
		SubscriptionLimitPK castOther = (SubscriptionLimitPK)other;
		return 
			this.subscriptionType.equals(castOther.subscriptionType)
			&& this.subLevel.equals(castOther.subLevel)
			&& this.subId.equals(castOther.subId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subscriptionType.hashCode();
		hash = hash * prime + this.subLevel.hashCode();
		hash = hash * prime + this.subId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SubscriptionLimitPK [subscriptionType=" + subscriptionType
				+ ", subLevel=" + subLevel + ", subId=" + subId + "]";
	}
}