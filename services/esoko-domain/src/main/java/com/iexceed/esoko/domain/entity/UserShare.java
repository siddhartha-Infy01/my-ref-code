package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user_shares database table.
 * 
 */
@Entity
@Table(name="user_shares")
@NamedQuery(name="UserShare.findAll", query="SELECT u FROM UserShare u")
public class UserShare implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserSharePK id;

	@Column(name="auth_stat")
	private String authStat;

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

	@Column(name="people_mode")
	private String peopleMode;

	@Column(name="people_type")
	private String peopleType;

	@Column(name="share_status")
	private String shareStatus;

	@Column(name="share_type")
	private String shareType;

	public UserShare() {
	}

	public UserSharePK getId() {
		return this.id;
	}

	public void setId(UserSharePK id) {
		this.id = id;
	}

	public String getAuthStat() {
		return this.authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
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

	public String getPeopleMode() {
		return this.peopleMode;
	}

	public void setPeopleMode(String peopleMode) {
		this.peopleMode = peopleMode;
	}

	public String getPeopleType() {
		return this.peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public String getShareStatus() {
		return this.shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}

	public String getShareType() {
		return this.shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
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
				+ ((peopleMode == null) ? 0 : peopleMode.hashCode());
		result = prime * result
				+ ((peopleType == null) ? 0 : peopleType.hashCode());
		result = prime * result
				+ ((shareStatus == null) ? 0 : shareStatus.hashCode());
		result = prime * result
				+ ((shareType == null) ? 0 : shareType.hashCode());
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
		UserShare other = (UserShare) obj;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
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
		if (peopleMode == null) {
			if (other.peopleMode != null)
				return false;
		} else if (!peopleMode.equals(other.peopleMode))
			return false;
		if (peopleType == null) {
			if (other.peopleType != null)
				return false;
		} else if (!peopleType.equals(other.peopleType))
			return false;
		if (shareStatus == null) {
			if (other.shareStatus != null)
				return false;
		} else if (!shareStatus.equals(other.shareStatus))
			return false;
		if (shareType == null) {
			if (other.shareType != null)
				return false;
		} else if (!shareType.equals(other.shareType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserShare [id=" + id + ", authStat=" + authStat
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", peopleMode=" + peopleMode + ", peopleType=" + peopleType
				+ ", shareStatus=" + shareStatus + ", shareType=" + shareType
				+ "]";
	}

}