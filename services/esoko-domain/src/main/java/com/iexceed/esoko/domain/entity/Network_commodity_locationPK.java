package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Network_commodity_location database table.
 * 
 */
@Embeddable
public class Network_commodity_locationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="location_id")
	private String locationId;

	@Column(name="commodity_id")
	private String commodityId;

	public Network_commodity_locationPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getLocationId() {
		return this.locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
		if (!(other instanceof Network_commodity_locationPK)) {
			return false;
		}
		Network_commodity_locationPK castOther = (Network_commodity_locationPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.locationId.equals(castOther.locationId)
			&& this.commodityId.equals(castOther.commodityId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.locationId.hashCode();
		hash = hash * prime + this.commodityId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Network_commodity_locationPK [networkId=" + networkId
				+ ", locationId=" + locationId + ", commodityId=" + commodityId
				+ "]";
	}
}