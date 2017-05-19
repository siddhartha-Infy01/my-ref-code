package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

/**
 * The persistent class for the library_images database table.
 * 
 */
@Entity
@Table(name = "library_images")
@NamedQuery(name = "LibraryImage.findAll", query = "SELECT l FROM LibraryImage l")
public class LibraryImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pic_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int picId;

	@Lob
	private byte[] content;

	@Column(name = "library_id")
	private String libraryId;

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public LibraryImage() {
	}

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "LibraryImage [picId=" + picId + ", content="
				+ Arrays.toString(content) + ", libraryId=" + libraryId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		result = prime * result
				+ ((libraryId == null) ? 0 : libraryId.hashCode());
		result = prime * result + picId;
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
		LibraryImage other = (LibraryImage) obj;
		if (!Arrays.equals(content, other.content))
			return false;
		if (libraryId == null) {
			if (other.libraryId != null)
				return false;
		} else if (!libraryId.equals(other.libraryId))
			return false;
		if (picId != other.picId)
			return false;
		return true;
	}

}