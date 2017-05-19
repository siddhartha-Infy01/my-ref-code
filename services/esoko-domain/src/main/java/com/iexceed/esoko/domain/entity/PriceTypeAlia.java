package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the price_type_alias database table.
 * 
 */
@Entity
@Table(name="price_type_alias")
@NamedQuery(name="PriceTypeAlia.findAll", query="SELECT p FROM PriceTypeAlia p")
public class PriceTypeAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	private String name;

	@Column(name="price_type_id")
	private String priceTypeId;

	public PriceTypeAlia() {
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceTypeId() {
		return this.priceTypeId;
	}

	public void setPriceTypeId(String priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((priceTypeId == null) ? 0 : priceTypeId.hashCode());
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
		PriceTypeAlia other = (PriceTypeAlia) obj;
		if (aliasId == null) {
			if (other.aliasId != null)
				return false;
		} else if (!aliasId.equals(other.aliasId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priceTypeId == null) {
			if (other.priceTypeId != null)
				return false;
		} else if (!priceTypeId.equals(other.priceTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PriceTypeAlia [aliasId=" + aliasId + ", name=" + name
				+ ", priceTypeId=" + priceTypeId + "]";
	}

}