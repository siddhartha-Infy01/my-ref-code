package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the people database table.
 * 
 */
@Embeddable
public class PeoplePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="people_id")
	private String peopleId;

	@Column(name="default_network_id")
	private String defaultNetworkId;

	public PeoplePK() {
	}
	public String getPeopleId() {
		return this.peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getDefaultNetworkId() {
		return this.defaultNetworkId;
	}
	public void setDefaultNetworkId(String defaultNetworkId) {
		this.defaultNetworkId = defaultNetworkId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PeoplePK)) {
			return false;
		}
		PeoplePK castOther = (PeoplePK)other;
		return 
			this.peopleId.equals(castOther.peopleId)
			&& this.defaultNetworkId.equals(castOther.defaultNetworkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.peopleId.hashCode();
		hash = hash * prime + this.defaultNetworkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "PeoplePK [peopleId=" + peopleId + ", defaultNetworkId="
				+ defaultNetworkId + "]";
	}
}