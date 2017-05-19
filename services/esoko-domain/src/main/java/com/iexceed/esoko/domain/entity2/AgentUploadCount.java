package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the agent_upload_count database table.
 * 
 */
@Entity
@Table(name = "agent_upload_count")
@NamedQuery(name = "AgentUploadCount.findAll", query = "SELECT a FROM AgentUploadCount a")
public class AgentUploadCount implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AgentUploadCountPK id;

	private int uploads;

	@Column(name = "upload_accuracy")
	private int uploadAccuracy;

	public AgentUploadCount() {
	}

	public AgentUploadCountPK getId() {
		return this.id;
	}

	public void setId(AgentUploadCountPK id) {
		this.id = id;
	}

	public int getUploads() {
		return this.uploads;
	}

	public void setUploads(int uploads) {
		this.uploads = uploads;
	}

	public int getUploadAccuracy() {
		return uploadAccuracy;
	}

	public void setUploadAccuracy(int uploadAccuracy) {
		this.uploadAccuracy = uploadAccuracy;
	}

	@Override
	public String toString() {
		return "AgentUploadCount [id=" + id + ", uploads=" + uploads
				+ ", uploadAccuracy=" + uploadAccuracy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + uploadAccuracy;
		result = prime * result + uploads;
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
		AgentUploadCount other = (AgentUploadCount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uploadAccuracy != other.uploadAccuracy)
			return false;
		if (uploads != other.uploads)
			return false;
		return true;
	}

}