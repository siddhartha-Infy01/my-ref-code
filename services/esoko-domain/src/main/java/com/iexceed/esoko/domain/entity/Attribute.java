package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the attributes database table.
 * 
 */
@Entity
@Table(name="attributes")
@NamedQuery(name="Attribute.findAll", query="SELECT a FROM Attribute a")
public class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private String attributeId;

	@Column(name="att_desc")
	private String attDesc;

	public Attribute() {
	}

	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttDesc() {
		return this.attDesc;
	}

	public void setAttDesc(String attDesc) {
		this.attDesc = attDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attDesc == null) ? 0 : attDesc.hashCode());
		result = prime * result
				+ ((attributeId == null) ? 0 : attributeId.hashCode());
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
		Attribute other = (Attribute) obj;
		if (attDesc == null) {
			if (other.attDesc != null)
				return false;
		} else if (!attDesc.equals(other.attDesc))
			return false;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attribute [attributeId=" + attributeId + ", attDesc=" + attDesc
				+ "]";
	}

}