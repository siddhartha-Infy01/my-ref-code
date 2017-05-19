package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;


/**
 * The persistent class for the people_photo database table.
 * 
 */
@Entity
@Table(name="people_photo")
@NamedQuery(name="PeoplePhoto.findAll", query="SELECT p FROM PeoplePhoto p")
public class PeoplePhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PeoplePhotoPK id;

	@Lob
	private byte[] photo;

	public PeoplePhoto() {
	}

	public PeoplePhotoPK getId() {
		return this.id;
	}

	public void setId(PeoplePhotoPK id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(photo);
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
		PeoplePhoto other = (PeoplePhoto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeoplePhoto [id=" + id + ", photo=" + Arrays.toString(photo)
				+ "]";
	}

}