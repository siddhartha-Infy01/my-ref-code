package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

/**
 * The persistent class for the library_files database table.
 * 
 */
@Entity
@Table(name = "library_files")
@NamedQuery(name = "LibraryFile.findAll", query = "SELECT l FROM LibraryFile l")
public class LibraryFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "file_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;

	@Column(name = "library_id")
	private String libraryId;

	@Lob
	@Column(name = "file_content")
	private byte[] fileContent;

	private String filename;

	private String filetype;

	public LibraryFile() {
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	@Override
	public String toString() {
		return "LibraryFile [fileId=" + fileId + ", libraryId=" + libraryId
				+ ", fileContent=" + Arrays.toString(fileContent)
				+ ", filename=" + filename + ", filetype=" + filetype + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fileContent);
		result = prime * result + fileId;
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result
				+ ((filetype == null) ? 0 : filetype.hashCode());
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
		LibraryFile other = (LibraryFile) obj;
		if (!Arrays.equals(fileContent, other.fileContent))
			return false;
		if (fileId != other.fileId)
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (filetype == null) {
			if (other.filetype != null)
				return false;
		} else if (!filetype.equals(other.filetype))
			return false;
		if (libraryId == null) {
			if (other.libraryId != null)
				return false;
		} else if (!libraryId.equals(other.libraryId))
			return false;
		return true;
	}

}