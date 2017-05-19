package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sender_details database table.
 * 
 */
@Embeddable
public class SenderDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sender_id")
	private String senderId;

	@Column(name="location_id")
	private String locationId;

	@Column(name="network_id")
	private String networkId;

	public SenderDetailPK() {
	}
	public String getSenderId() {
		return this.senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getLocationId() {
		return this.locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
		if (!(other instanceof SenderDetailPK)) {
			return false;
		}
		SenderDetailPK castOther = (SenderDetailPK)other;
		return 
			this.senderId.equals(castOther.senderId)
			&& this.locationId.equals(castOther.locationId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.senderId.hashCode();
		hash = hash * prime + this.locationId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "SenderDetailPK [senderId=" + senderId + ", locationId="
				+ locationId + ", networkId=" + networkId + "]";
	}
	
}