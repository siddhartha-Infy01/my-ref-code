package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the user_personalization database table.
 * 
 */
@Entity
@Table(name = "user_personalization")
@NamedQuery(name = "UserPersonalization.findAll", query = "SELECT u FROM UserPersonalization u")
public class UserPersonalization implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPersonalizationPK id;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_ts")
	private Date modifiedTs;

	@Column(name = "widget_status")
	private String widgetStatus;

	@Column(name = "x_cordinates")
	private int xCordinates;

	@Column(name = "y_cordinates")
	private int yCordinates;

	public UserPersonalization() {
	}

	public UserPersonalizationPK getId() {
		return this.id;
	}

	public void setId(UserPersonalizationPK id) {
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

	public String getWidgetStatus() {
		return this.widgetStatus;
	}

	public void setWidgetStatus(String widgetStatus) {
		this.widgetStatus = widgetStatus;
	}

	public int getXCordinates() {
		return this.xCordinates;
	}

	public void setXCordinates(int xCordinates) {
		this.xCordinates = xCordinates;
	}

	public int getYCordinates() {
		return this.yCordinates;
	}

	public void setYCordinates(int yCordinates) {
		this.yCordinates = yCordinates;
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
				+ ((widgetStatus == null) ? 0 : widgetStatus.hashCode());
		result = prime * result + xCordinates;
		result = prime * result + yCordinates;
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
		UserPersonalization other = (UserPersonalization) obj;
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
		if (widgetStatus == null) {
			if (other.widgetStatus != null)
				return false;
		} else if (!widgetStatus.equals(other.widgetStatus))
			return false;
		if (xCordinates != other.xCordinates)
			return false;
		if (yCordinates != other.yCordinates)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPersonalization [id=" + id + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + ", widgetStatus="
				+ widgetStatus + ", xCordinates=" + xCordinates
				+ ", yCordinates=" + yCordinates + "]";
	}
	
	
}