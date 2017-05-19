package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the agent database table.
 * 
 */
@Embeddable
public class AgentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="user_id")
	private String userId;

	public AgentPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AgentPK)) {
			return false;
		}
		AgentPK castOther = (AgentPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.userId.equals(castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "AgentPK [networkId=" + networkId + ", userId=" + userId + "]";
	}
}