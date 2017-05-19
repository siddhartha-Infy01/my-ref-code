package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the network_categories database table.
 * 
 */
@Entity
@Table(name="network_categories")
@NamedQuery(name="NetworkCategory.findAll", query="SELECT n FROM NetworkCategory n")
public class NetworkCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NetworkCategoryPK id;



	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="modified_ts")
	private Date modifiedTs;

	public NetworkCategory() {
	}

	public NetworkCategoryPK getId() {
		return this.id;
	}

	public void setId(NetworkCategoryPK id) {
		this.id = id;
	}


	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
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
		NetworkCategory other = (NetworkCategory) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NetworkCategory [id=" + id +  ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + "]";
	}

}