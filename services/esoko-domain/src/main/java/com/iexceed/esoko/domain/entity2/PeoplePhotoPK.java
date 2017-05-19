package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the people_photo database table.
 * 
 */
@Embeddable
public class PeoplePhotoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="people_id")
	private String peopleId;

	public PeoplePhotoPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getPeopleId() {
		return this.peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PeoplePhotoPK)) {
			return false;
		}
		PeoplePhotoPK castOther = (PeoplePhotoPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.peopleId.equals(castOther.peopleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.peopleId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "PeoplePhotoPK [networkId=" + networkId + ", peopleId="
				+ peopleId + "]";
	}
}