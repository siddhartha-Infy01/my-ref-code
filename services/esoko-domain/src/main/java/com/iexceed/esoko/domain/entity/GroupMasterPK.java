package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the group_master database table.
 * 
 */
@Embeddable
public class GroupMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="group_id")
	private String groupId;

	@Column(name="network_id")
	private String networkId;

	public GroupMasterPK() {
	}
	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
		if (!(other instanceof GroupMasterPK)) {
			return false;
		}
		GroupMasterPK castOther = (GroupMasterPK)other;
		return 
			this.groupId.equals(castOther.groupId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "GroupMasterPK [groupId=" + groupId + ", networkId=" + networkId
				+ "]";
	}
	
	
}