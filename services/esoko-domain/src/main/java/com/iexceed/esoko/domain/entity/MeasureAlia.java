package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the measure_alias database table.
 * 
 */
@Entity
@Table(name="measure_alias")
@NamedQuery(name="MeasureAlia.findAll", query="SELECT m FROM MeasureAlia m")
public class MeasureAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	@Column(name="measure_id")
	private String measureId;

	@Column(name="measure_name")
	private String measureName;

	public MeasureAlia() {
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getMeasureId() {
		return this.measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result
				+ ((measureId == null) ? 0 : measureId.hashCode());
		result = prime * result
				+ ((measureName == null) ? 0 : measureName.hashCode());
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
		MeasureAlia other = (MeasureAlia) obj;
		if (aliasId == null) {
			if (other.aliasId != null)
				return false;
		} else if (!aliasId.equals(other.aliasId))
			return false;
		if (measureId == null) {
			if (other.measureId != null)
				return false;
		} else if (!measureId.equals(other.measureId))
			return false;
		if (measureName == null) {
			if (other.measureName != null)
				return false;
		} else if (!measureName.equals(other.measureName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasureAlia [aliasId=" + aliasId + ", measureId=" + measureId
				+ ", measureName=" + measureName + "]";
	}

}