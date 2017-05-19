package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the profile_Sharing_types database table.
 * 
 */
@Embeddable
public class Profile_Sharing_typePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="profile_id")
	private String profileId;

	@Column(name="network_id")
	private String networkId;

	public Profile_Sharing_typePK() {
	}
	public String getProfileId() {
		return this.profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
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
		if (!(other instanceof Profile_Sharing_typePK)) {
			return false;
		}
		Profile_Sharing_typePK castOther = (Profile_Sharing_typePK)other;
		return 
			this.profileId.equals(castOther.profileId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.profileId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Profile_Sharing_typePK [profileId=" + profileId
				+ ", networkId=" + networkId + "]";
	}
}