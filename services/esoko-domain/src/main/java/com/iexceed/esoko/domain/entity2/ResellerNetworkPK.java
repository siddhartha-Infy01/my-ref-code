package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the reseller_network database table.
 * 
 */
@Embeddable
public class ResellerNetworkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="reseller_id")
	private String resellerId;

	@Column(name="network_id")
	private String networkId;

	public ResellerNetworkPK() {
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
		ResellerNetworkPK other = (ResellerNetworkPK) obj;
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
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((resellerId == null) ? 0 : resellerId.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "ResellerNetworkPK [resellerId=" + resellerId + ", networkId="
				+ networkId + "]";
	}
}