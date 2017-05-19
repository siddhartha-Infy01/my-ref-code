package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Network_permissions database table.
 * 
 */
@Embeddable
public class Network_permissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="group_id")
	private String groupId;

	public Network_permissionPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Network_permissionPK)) {
			return false;
		}
		Network_permissionPK castOther = (Network_permissionPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.groupId.equals(castOther.groupId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.groupId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Network_permissionPK [networkId=" + networkId + ", groupId="
				+ groupId + "]";
	}
	
	
}