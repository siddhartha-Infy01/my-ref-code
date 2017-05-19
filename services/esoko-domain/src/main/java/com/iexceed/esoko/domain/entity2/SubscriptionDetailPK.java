package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the subscription_details database table.
 * 
 */
@Embeddable
public class SubscriptionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subscription_id")
	private int subscriptionId;

	@Column(name="param_name")
	private String paramName;

	@Column(name="subscription_type")
	private String subscriptionType;

	public SubscriptionDetailPK() {
	}
	
	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getParamName() {
		return this.paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
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
				+ ((paramName == null) ? 0 : paramName.hashCode());
		result = prime * result + subscriptionId;
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
		SubscriptionDetailPK other = (SubscriptionDetailPK) obj;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		if (subscriptionId != other.subscriptionId)
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
		return "SubscriptionDetailPK [subscriptionId=" + subscriptionId
				+ ", paramName=" + paramName + ", subscriptionType="
				+ subscriptionType + "]";
	}
}