package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the network_subscription_apps database table.
 * 
 */
@Embeddable
public class NetworkSubscriptionAppPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "subscription_id")
	private String subscriptionId;

	@Column(name = "subscription_type")
	private String subscriptionType;

	@Column(name = "invoice_no")
	private String invoiceNo;

	@Column(name = "param_name")
	private String paramName;

	@Column(name = "network_id")
	private String networkId;

	public NetworkSubscriptionAppPK() {
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((invoiceNo == null) ? 0 : invoiceNo.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((paramName == null) ? 0 : paramName.hashCode());
		result = prime * result
				+ ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
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
		NetworkSubscriptionAppPK other = (NetworkSubscriptionAppPK) obj;
		if (invoiceNo == null) {
			if (other.invoiceNo != null)
				return false;
		} else if (!invoiceNo.equals(other.invoiceNo))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		if (subscriptionType == null) {
			if (other.subscriptionType != null)
				return false;
		} else if (!subscriptionType.equals(other.subscriptionType))
			return false;
		return true;
	}

}