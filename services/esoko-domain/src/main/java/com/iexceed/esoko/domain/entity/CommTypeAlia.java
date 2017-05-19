package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the comm_type_alias database table.
 * 
 */
@Entity
@Table(name="comm_type_alias")
@NamedQuery(name="CommTypeAlia.findAll", query="SELECT c FROM CommTypeAlia c")
public class CommTypeAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	private String name;

	@Column(name="q_type_id")
	private String qTypeId;

	public CommTypeAlia() {
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
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((qTypeId == null) ? 0 : qTypeId.hashCode());
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
		CommTypeAlia other = (CommTypeAlia) obj;
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
		if (qTypeId == null) {
			if (other.qTypeId != null)
				return false;
		} else if (!qTypeId.equals(other.qTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommTypeAlia [aliasId=" + aliasId + ", name=" + name
				+ ", qTypeId=" + qTypeId + "]";
	}

}