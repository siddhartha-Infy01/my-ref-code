package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user_preferences database table.
 * 
 */
@Entity
@Table(name="user_preferences")
@NamedQuery(name="UserPreference.findAll", query="SELECT u FROM UserPreference u")
public class UserPreference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPreferencePK id;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="preference_name")
	private String preferenceName;

	@Column(name="preference_value")
	private String preferenceValue;

	public UserPreference() {
	}

	public UserPreferencePK getId() {
		return this.id;
	}

	public void setId(UserPreferencePK id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getPreferenceName() {
		return this.preferenceName;
	}

	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}

	public String getPreferenceValue() {
		return this.preferenceValue;
	}

	public void setPreferenceValue(String preferenceValue) {
		this.preferenceValue = preferenceValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((preferenceName == null) ? 0 : preferenceName.hashCode());
		result = prime * result
				+ ((preferenceValue == null) ? 0 : preferenceValue.hashCode());
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
		UserPreference other = (UserPreference) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (preferenceName == null) {
			if (other.preferenceName != null)
				return false;
		} else if (!preferenceName.equals(other.preferenceName))
			return false;
		if (preferenceValue == null) {
			if (other.preferenceValue != null)
				return false;
		} else if (!preferenceValue.equals(other.preferenceValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPreference [id=" + id + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + ", preferenceName="
				+ preferenceName + ", preferenceValue=" + preferenceValue + "]";
	}

}