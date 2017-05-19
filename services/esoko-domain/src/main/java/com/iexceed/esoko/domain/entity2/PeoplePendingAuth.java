package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the people_pending_auth database table.
 * 
 */
@Entity
@Table(name="people_pending_auth")
@NamedQuery(name="PeoplePendingAuth.findAll", query="SELECT p FROM PeoplePendingAuth p")
public class PeoplePendingAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pending_id")
	private int pendingId;

	@Column(name="auth_by")
	private String authBy;

	@Column(name="auth_status")
	private String authStatus;

	@Column(name="auth_timestamp")
	private Timestamp authTimestamp;

	@Column(name="change_network_id")
	private String changeNetworkId;

	@Column(name="owner_network_id")
	private String ownerNetworkId;

	@Column(name="people_id")
	private String peopleId;

	@Column(name="profile_field_changed")
	private String profileFieldChanged;

	@Column(name="profile_field_value")
	private String profileFieldValue;

	public PeoplePendingAuth() {
	}

	public int getPendingId() {
		return this.pendingId;
	}

	public void setPendingId(int pendingId) {
		this.pendingId = pendingId;
	}

	public String getAuthBy() {
		return this.authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Timestamp getAuthTimestamp() {
		return this.authTimestamp;
	}

	public void setAuthTimestamp(Timestamp authTimestamp) {
		this.authTimestamp = authTimestamp;
	}

	public String getChangeNetworkId() {
		return this.changeNetworkId;
	}

	public void setChangeNetworkId(String changeNetworkId) {
		this.changeNetworkId = changeNetworkId;
	}

	public String getOwnerNetworkId() {
		return this.ownerNetworkId;
	}

	public void setOwnerNetworkId(String ownerNetworkId) {
		this.ownerNetworkId = ownerNetworkId;
	}

	public String getPeopleId() {
		return this.peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getProfileFieldChanged() {
		return this.profileFieldChanged;
	}

	public void setProfileFieldChanged(String profileFieldChanged) {
		this.profileFieldChanged = profileFieldChanged;
	}

	public String getProfileFieldValue() {
		return this.profileFieldValue;
	}

	public void setProfileFieldValue(String profileFieldValue) {
		this.profileFieldValue = profileFieldValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStatus == null) ? 0 : authStatus.hashCode());
		result = prime * result
				+ ((authTimestamp == null) ? 0 : authTimestamp.hashCode());
		result = prime * result
				+ ((changeNetworkId == null) ? 0 : changeNetworkId.hashCode());
		result = prime * result
				+ ((ownerNetworkId == null) ? 0 : ownerNetworkId.hashCode());
		result = prime * result + pendingId;
		result = prime * result
				+ ((peopleId == null) ? 0 : peopleId.hashCode());
		result = prime
				* result
				+ ((profileFieldChanged == null) ? 0 : profileFieldChanged
						.hashCode());
		result = prime
				* result
				+ ((profileFieldValue == null) ? 0 : profileFieldValue
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeoplePendingAuth other = (PeoplePendingAuth) obj;
		if (authBy == null) {
			if (other.authBy != null)
				return false;
		} else if (!authBy.equals(other.authBy))
			return false;
		if (authStatus == null) {
			if (other.authStatus != null)
				return false;
		} else if (!authStatus.equals(other.authStatus))
			return false;
		if (authTimestamp == null) {
			if (other.authTimestamp != null)
				return false;
		} else if (!authTimestamp.equals(other.authTimestamp))
			return false;
		if (changeNetworkId == null) {
			if (other.changeNetworkId != null)
				return false;
		} else if (!changeNetworkId.equals(other.changeNetworkId))
			return false;
		if (ownerNetworkId == null) {
			if (other.ownerNetworkId != null)
				return false;
		} else if (!ownerNetworkId.equals(other.ownerNetworkId))
			return false;
		if (pendingId != other.pendingId)
			return false;
		if (peopleId == null) {
			if (other.peopleId != null)
				return false;
		} else if (!peopleId.equals(other.peopleId))
			return false;
		if (profileFieldChanged == null) {
			if (other.profileFieldChanged != null)
				return false;
		} else if (!profileFieldChanged.equals(other.profileFieldChanged))
			return false;
		if (profileFieldValue == null) {
			if (other.profileFieldValue != null)
				return false;
		} else if (!profileFieldValue.equals(other.profileFieldValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeoplePendingAuth [pendingId=" + pendingId + ", authBy="
				+ authBy + ", authStatus=" + authStatus + ", authTimestamp="
				+ authTimestamp + ", changeNetworkId=" + changeNetworkId
				+ ", ownerNetworkId=" + ownerNetworkId + ", peopleId="
				+ peopleId + ", profileFieldChanged=" + profileFieldChanged
				+ ", profileFieldValue=" + profileFieldValue + "]";
	}

}