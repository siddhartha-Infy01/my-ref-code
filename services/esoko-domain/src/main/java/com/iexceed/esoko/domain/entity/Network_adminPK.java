package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Network_admins database table.
 * 
 */
@Embeddable
public class Network_adminPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;

	@Column(name="network_id")
	private String networkId;

	public Network_adminPK() {
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
		if (!(other instanceof Network_adminPK)) {
			return false;
		}
		Network_adminPK castOther = (Network_adminPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Network_adminPK [userId=" + userId + ", networkId=" + networkId
				+ "]";
	}
}