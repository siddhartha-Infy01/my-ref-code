package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the push_templates database table.
 * 
 */
@Entity
@Table(name="push_templates")
@NamedQuery(name="PushTemplate.findAll", query="SELECT p FROM PushTemplate p")
public class PushTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PushTemplatePK id;

	@Column(name="auth_stat")
	private String authStat;

	private int characters;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_TS;

	@Lob
	private String message;

	private int messages;

	public PushTemplate() {
	}

	public PushTemplatePK getId() {
		return this.id;
	}

	public void setId(PushTemplatePK id) {
		this.id = id;
	}

	public String getAuthStat() {
		return this.authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public int getCharacters() {
		return this.characters;
	}

	public void setCharacters(int characters) {
		this.characters = characters;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated_TS() {
		return this.created_TS;
	}

	public void setCreated_TS(Date created_TS) {
		this.created_TS = created_TS;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessages() {
		return this.messages;
	}

	public void setMessages(int messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + characters;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((created_TS == null) ? 0 : created_TS.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messages;
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
		PushTemplate other = (PushTemplate) obj;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
		if (characters != other.characters)
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (created_TS == null) {
			if (other.created_TS != null)
				return false;
		} else if (!created_TS.equals(other.created_TS))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messages != other.messages)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PushTemplate [id=" + id + ", authStat=" + authStat
				+ ", characters=" + characters + ", createdBy=" + createdBy
				+ ", created_TS=" + created_TS + ", message=" + message
				+ ", messages=" + messages + "]";
	}

}