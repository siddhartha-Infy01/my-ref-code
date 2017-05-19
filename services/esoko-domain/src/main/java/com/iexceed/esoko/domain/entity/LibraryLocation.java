package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the library_locations database table.
 * 
 */
@Entity
@Table(name = "library_locations")
@NamedQuery(name = "LibraryLocation.findAll", query = "SELECT l FROM LibraryLocation l")
public class LibraryLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lib_loc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int libLocId;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "library_id")
	private String libraryId;

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public LibraryLocation() {
	}

	public int getLibLocId() {
		return libLocId;
	}

	public void setLibLocId(int libLocId) {
		this.libLocId = libLocId;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "LibraryLocation [libLocId=" + libLocId + ", locationId="
				+ locationId + ", libraryId=" + libraryId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + libLocId;
		result = prime * result
				+ ((libraryId == null) ? 0 : libraryId.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
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
		LibraryLocation other = (LibraryLocation) obj;
		if (libLocId != other.libLocId)
			return false;
		if (libraryId == null) {
			if (other.libraryId != null)
				return false;
		} else if (!libraryId.equals(other.libraryId))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		return true;
	}

}