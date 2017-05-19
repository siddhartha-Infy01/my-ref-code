package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the commodity_variety database table.
 * 
 */
@Entity
@Table(name="commodity_variety")
@NamedQuery(name="CommodityVariety.findAll", query="SELECT c FROM CommodityVariety c")
public class CommodityVariety implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="variety_id")
	private int varietyId;

	@Column(name="attribute_id")
	private String attributeId;

	@Column(name="commodity_id")
	private String commodityId;

	@Column(name="q_type_id")
	private String qTypeId;

	public CommodityVariety() {
	}

	public int getVarietyId() {
		return this.varietyId;
	}

	public void setVarietyId(int varietyId) {
		this.varietyId = varietyId;
	}

	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getQTypeId() {
		return this.qTypeId;
	}

	public void setQTypeId(String qTypeId) {
		this.qTypeId = qTypeId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result + ((qTypeId == null) ? 0 : qTypeId.hashCode());
		result = prime * result + varietyId;
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
		CommodityVariety other = (CommodityVariety) obj;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (qTypeId == null) {
			if (other.qTypeId != null)
				return false;
		} else if (!qTypeId.equals(other.qTypeId))
			return false;
		if (varietyId != other.varietyId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommodityVariety [varietyId=" + varietyId + ", attributeId="
				+ attributeId + ", commodityId=" + commodityId + ", qTypeId="
				+ qTypeId + "]";
	}

}