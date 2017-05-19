package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the keyword_alias database table.
 * 
 */
@Entity
@Table(name="keyword_alias")
@NamedQuery(name="KeywordAlia.findAll", query="SELECT k FROM KeywordAlia k")
public class KeywordAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KeywordAliaPK id;

	public KeywordAlia() {
	}

	public KeywordAliaPK getId() {
		return this.id;
	}

	public void setId(KeywordAliaPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		KeywordAlia other = (KeywordAlia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeywordAlia [id=" + id + "]";
	}	

}