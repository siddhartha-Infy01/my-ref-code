package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the network_subscription_details database table.
 * 
 */
@Embeddable
public class NetworkSubscriptionDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="invoice_no")
	private String invoiceId;

	@Column(name="reseller_id")
	private String resellerId;

	@Column(name="network_id")
	private String networkId;

	public NetworkSubscriptionDetailPK() {
	}
	public String getInvoiceId() {
		return this.invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getResellerId() {
		return this.resellerId;
	}
	public void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetworkSubscriptionDetailPK other = (NetworkSubscriptionDetailPK) obj;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (resellerId == null) {
			if (other.resellerId != null)
				return false;
		} else if (!resellerId.equals(other.resellerId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((invoiceId == null) ? 0 : invoiceId.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((resellerId == null) ? 0 : resellerId.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "NetworkSubscriptionDetailPK [invoiceId=" + invoiceId
				+ ", resellerId=" + resellerId + ", networkId=" + networkId
				+ "]";
	}
}