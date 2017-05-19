package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the commodity_attributes database table.
 * 
 */
@Entity
@Table(name="commodity_attributes")
@NamedQuery(name="CommodityAttribute.findAll", query="SELECT c FROM CommodityAttribute c")
public class CommodityAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int attributeId;

	@Column(name="att_name")
	private String attName;

	@Column(name="commodity_id")
	private String commodityId;

	public CommodityAttribute() {
	}

	

	public int getAttributeId() {
		return attributeId;
	}



	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}



	public String getAttName() {
		return this.attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}



	@Override
	public String toString() {
		return "CommodityAttribute [attributeId=" + attributeId + ", attName="
				+ attName + ", commodityId=" + commodityId + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attName == null) ? 0 : attName.hashCode());
		result = prime * result + attributeId;
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
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
		CommodityAttribute other = (CommodityAttribute) obj;
		if (attName == null) {
			if (other.attName != null)
				return false;
		} else if (!attName.equals(other.attName))
			return false;
		if (attributeId != other.attributeId)
			return false;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		return true;
	}



}