package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the subscription_master database table.
 * 
 */
@Entity
@Table(name="subscription_master")
@NamedQuery(name="SubscriptionMaster.findAll", query="SELECT s FROM SubscriptionMaster s")
public class SubscriptionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionMasterPK id;

	@Column(name="subscription_level")
	private String subscriptionLevel;

	public SubscriptionMaster() {
	}

	public SubscriptionMasterPK getId() {
		return this.id;
	}

	public void setId(SubscriptionMasterPK id) {
		this.id = id;
	}

	public String getSubscriptionLevel() {
		return this.subscriptionLevel;
	}

	public void setSubscriptionLevel(String subscriptionLevel) {
		this.subscriptionLevel = subscriptionLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((subscriptionLevel == null) ? 0 : subscriptionLevel
						.hashCode());
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
		SubscriptionMaster other = (SubscriptionMaster) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subscriptionLevel == null) {
			if (other.subscriptionLevel != null)
				return false;
		} else if (!subscriptionLevel.equals(other.subscriptionLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionMaster [id=" + id + ", subscriptionLevel="
				+ subscriptionLevel + "]";
	}
	

}