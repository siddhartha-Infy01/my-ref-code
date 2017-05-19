package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_occupations database table.
 * 
 */
@Embeddable
public class UserOccupationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;

	@Column(name="occupation_id")
	private String occupationId;

	@Column(name="network_id")
	private String networkId;

	public UserOccupationPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOccupationId() {
		return this.occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
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
		if (!(other instanceof UserOccupationPK)) {
			return false;
		}
		UserOccupationPK castOther = (UserOccupationPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.occupationId.equals(castOther.occupationId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.occupationId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
}