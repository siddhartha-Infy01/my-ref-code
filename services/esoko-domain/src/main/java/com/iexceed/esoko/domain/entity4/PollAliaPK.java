package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the poll_alias database table.
 * 
 */
@Embeddable
public class PollAliaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="poll_id")
	private String pollId;

	private String keyword;

	private String alias;

	public PollAliaPK() {
	}
	public String getPollId() {
		return this.pollId;
	}
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	public String getKeyword() {
		return this.keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAlias() {
		return this.alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PollAliaPK)) {
			return false;
		}
		PollAliaPK castOther = (PollAliaPK)other;
		return 
			this.pollId.equals(castOther.pollId)
			&& this.keyword.equals(castOther.keyword)
			&& this.alias.equals(castOther.alias);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pollId.hashCode();
		hash = hash * prime + this.keyword.hashCode();
		hash = hash * prime + this.alias.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "PollAliaPK [pollId=" + pollId + ", keyword=" + keyword
				+ ", alias=" + alias + "]";
	}	
	
}