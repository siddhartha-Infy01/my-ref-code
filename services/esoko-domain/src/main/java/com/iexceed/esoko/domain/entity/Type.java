package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t")
@Table(name = "type")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_id")
	private String typeId;

	private String description;

	public Type() {
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
		Type other = (Type) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", description=" + description + "]";
	}

}