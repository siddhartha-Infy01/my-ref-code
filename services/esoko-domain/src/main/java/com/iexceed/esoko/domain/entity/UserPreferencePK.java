package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_preferences database table.
 * 
 */
@Embeddable
public class UserPreferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;

	@Column(name="preference_id")
	private String preferenceId;

	public UserPreferencePK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPreferenceId() {
		return this.preferenceId;
	}
	public void setPreferenceId(String preferenceId) {
		this.preferenceId = preferenceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserPreferencePK)) {
			return false;
		}
		UserPreferencePK castOther = (UserPreferencePK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.preferenceId.equals(castOther.preferenceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.preferenceId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "UserPreferencePK [userId=" + userId + ", preferenceId="
				+ preferenceId + "]";
	}
}