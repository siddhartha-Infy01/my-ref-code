package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the comm_attribute_alias database table.
 * 
 */
@Entity
@Table(name="comm_attribute_alias")
@NamedQuery(name="CommAttributeAlia.findAll", query="SELECT c FROM CommAttributeAlia c")
public class CommAttributeAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	@Column(name="attribute_id")
	private String attributeId;

	private String name;

	public CommAttributeAlia() {
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result
				+ ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CommAttributeAlia other = (CommAttributeAlia) obj;
		if (aliasId == null) {
			if (other.aliasId != null)
				return false;
		} else if (!aliasId.equals(other.aliasId))
			return false;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommAttributeAlia [aliasId=" + aliasId + ", attributeId="
				+ attributeId + ", name=" + name + "]";
	}
	

}