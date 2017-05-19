package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the network_commodities database table.
 * 
 */
@Embeddable
public class NetworkCommodityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="commodity_id")
	private String commodityId;

	public NetworkCommodityPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getCommodityId() {
		return this.commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetworkCommodityPK)) {
			return false;
		}
		NetworkCommodityPK castOther = (NetworkCommodityPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.commodityId.equals(castOther.commodityId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.commodityId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "NetworkCommodityPK [networkId=" + networkId + ", commodityId="
				+ commodityId + "]";
	}
}