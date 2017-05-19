package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the library_commodity database table.
 * 
 */
@Entity
@Table(name = "library_commodity")
@NamedQuery(name = "LibraryCommodity.findAll", query = "SELECT l FROM LibraryCommodity l")
public class LibraryCommodity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lib_comm_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int libCommId;

	@Column(name = "commodity_id")
	private String commodityId;

	public int getLibCommId() {
		return libCommId;
	}

	public void setLibCommId(int libCommId) {
		this.libCommId = libCommId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result + libCommId;
		result = prime * result
				+ ((libraryId == null) ? 0 : libraryId.hashCode());
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
		LibraryCommodity other = (LibraryCommodity) obj;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (libCommId != other.libCommId)
			return false;
		if (libraryId == null) {
			if (other.libraryId != null)
				return false;
		} else if (!libraryId.equals(other.libraryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LibraryCommodity [libCommId=" + libCommId + ", commodityId="
				+ commodityId + ", libraryId=" + libraryId + "]";
	}

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	@Column(name = "library_id")
	private String libraryId;

	public LibraryCommodity() {
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

}