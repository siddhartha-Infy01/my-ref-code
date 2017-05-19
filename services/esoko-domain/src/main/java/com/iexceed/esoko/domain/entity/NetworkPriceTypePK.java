package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the network_price_types database table.
 * 
 */
@Embeddable
public class NetworkPriceTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="pricetype_id")
	private String pricetypeId;

	public NetworkPriceTypePK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getPricetypeId() {
		return this.pricetypeId;
	}
	public void setPricetypeId(String pricetypeId) {
		this.pricetypeId = pricetypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetworkPriceTypePK)) {
			return false;
		}
		NetworkPriceTypePK castOther = (NetworkPriceTypePK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.pricetypeId.equals(castOther.pricetypeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.pricetypeId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "NetworkPriceTypePK [networkId=" + networkId + ", pricetypeId="
				+ pricetypeId + "]";
	}
		
}