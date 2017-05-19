package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_locations database table.
 * 
 */
@Embeddable
public class UserLocationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="location_id")
	private String locationId;

	@Column(name="user_id")
	private String userId;

	@Column(name="network_id")
	private String networkId;

	public UserLocationPK() {
	}
	public String getLocationId() {
		return this.locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserLocationPK)) {
			return false;
		}
		UserLocationPK castOther = (UserLocationPK)other;
		return 
			this.locationId.equals(castOther.locationId)
			&& this.userId.equals(castOther.userId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.locationId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
}