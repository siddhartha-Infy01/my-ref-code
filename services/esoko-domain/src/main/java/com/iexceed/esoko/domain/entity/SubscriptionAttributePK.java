package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the subscription_attributes database table.
 * 
 */
@Embeddable
public class SubscriptionAttributePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sub_attribute_id")
	private String subAttributeId;

	@Column(name="subscription_id")
	private String subscriptionId;

	public SubscriptionAttributePK() {
	}
	public String getSubAttributeId() {
		return this.subAttributeId;
	}
	public void setSubAttributeId(String subAttributeId) {
		this.subAttributeId = subAttributeId;
	}
	public String getSubscriptionId() {
		return this.subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriptionAttributePK)) {
			return false;
		}
		SubscriptionAttributePK castOther = (SubscriptionAttributePK)other;
		return 
			this.subAttributeId.equals(castOther.subAttributeId)
			&& this.subscriptionId.equals(castOther.subscriptionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subAttributeId.hashCode();
		hash = hash * prime + this.subscriptionId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SubscriptionAttributePK [subAttributeId=" + subAttributeId
				+ ", subscriptionId=" + subscriptionId + "]";
	}
}