package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the comm_variety_alias database table.
 * 
 */
@Entity
@Table(name="comm_variety_alias")
@NamedQuery(name="CommVarietyAlia.findAll", query="SELECT c FROM CommVarietyAlia c")
public class CommVarietyAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	@Column(name="attibute_id")
	private String attibuteId;

	@Column(name="commodity_id")
	private String commodityId;

	private String name;

	@Column(name="q_type_id")
	private String qTypeId;

	@Column(name="variety_id")
	private String varietyId;

	public CommVarietyAlia() {
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getAttibuteId() {
		return this.attibuteId;
	}

	public void setAttibuteId(String attibuteId) {
		this.attibuteId = attibuteId;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQTypeId() {
		return this.qTypeId;
	}

	public void setQTypeId(String qTypeId) {
		this.qTypeId = qTypeId;
	}

	public String getVarietyId() {
		return this.varietyId;
	}

	public void setVarietyId(String varietyId) {
		this.varietyId = varietyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result
				+ ((attibuteId == null) ? 0 : attibuteId.hashCode());
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((qTypeId == null) ? 0 : qTypeId.hashCode());
		result = prime * result
				+ ((varietyId == null) ? 0 : varietyId.hashCode());
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
		CommVarietyAlia other = (CommVarietyAlia) obj;
		if (aliasId == null) {
			if (other.aliasId != null)
				return false;
		} else if (!aliasId.equals(other.aliasId))
			return false;
		if (attibuteId == null) {
			if (other.attibuteId != null)
				return false;
		} else if (!attibuteId.equals(other.attibuteId))
			return false;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (qTypeId == null) {
			if (other.qTypeId != null)
				return false;
		} else if (!qTypeId.equals(other.qTypeId))
			return false;
		if (varietyId == null) {
			if (other.varietyId != null)
				return false;
		} else if (!varietyId.equals(other.varietyId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommVarietyAlia [aliasId=" + aliasId + ", attibuteId="
				+ attibuteId + ", commodityId=" + commodityId + ", name="
				+ name + ", qTypeId=" + qTypeId + ", varietyId=" + varietyId
				+ "]";
	}

}