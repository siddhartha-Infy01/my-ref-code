package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the network_location database table.
 * 
 */
@Embeddable
public class NetworkLocationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="location_id")
	private String locationId;

	public NetworkLocationPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetworkLocationPK)) {
			return false;
		}
		NetworkLocationPK castOther = (NetworkLocationPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.locationId.equals(castOther.locationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.locationId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "NetworkLocationPK [networkId=" + networkId + ", locationId="
				+ locationId + "]";
	}
}