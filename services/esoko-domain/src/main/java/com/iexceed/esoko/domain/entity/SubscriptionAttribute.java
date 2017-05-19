package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the subscription_attributes database table.
 * 
 */
@Entity
@Table(name="subscription_attributes")
@NamedQuery(name="SubscriptionAttribute.findAll", query="SELECT s FROM SubscriptionAttribute s")
public class SubscriptionAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionAttributePK id;

	@Column(name="attr_max_value")
	private int attrMaxValue;

	@Column(name="attr_min_value")
	private int attrMinValue;

	@Column(name="attr_name")
	private String attrName;

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

	@Column(name="record_status")
	private String recordStatus;

	public SubscriptionAttribute() {
	}

	public SubscriptionAttributePK getId() {
		return this.id;
	}

	public void setId(SubscriptionAttributePK id) {
		this.id = id;
	}

	public int getAttrMaxValue() {
		return this.attrMaxValue;
	}

	public void setAttrMaxValue(int attrMaxValue) {
		this.attrMaxValue = attrMaxValue;
	}

	public int getAttrMinValue() {
		return this.attrMinValue;
	}

	public void setAttrMinValue(int attrMinValue) {
		this.attrMinValue = attrMinValue;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
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

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attrMaxValue;
		result = prime * result + attrMinValue;
		result = prime * result
				+ ((attrName == null) ? 0 : attrName.hashCode());
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
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
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
		SubscriptionAttribute other = (SubscriptionAttribute) obj;
		if (attrMaxValue != other.attrMaxValue)
			return false;
		if (attrMinValue != other.attrMinValue)
			return false;
		if (attrName == null) {
			if (other.attrName != null)
				return false;
		} else if (!attrName.equals(other.attrName))
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
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionAttribute [id=" + id + ", attrMaxValue="
				+ attrMaxValue + ", attrMinValue=" + attrMinValue
				+ ", attrName=" + attrName + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + ", recordStatus="
				+ recordStatus + "]";
	}

}