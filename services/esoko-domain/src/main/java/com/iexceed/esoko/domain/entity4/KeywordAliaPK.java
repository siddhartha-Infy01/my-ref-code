package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the keyword_alias database table.
 * 
 */
@Embeddable
public class KeywordAliaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String keyword;

	@Column(name="channel")
	private String channel;

	private String alias;

	public KeywordAliaPK() {
	}
	public String getKeyword() {
		return this.keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getChannel() {
		return this.channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
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
		if (!(other instanceof KeywordAliaPK)) {
			return false;
		}
		KeywordAliaPK castOther = (KeywordAliaPK)other;
		return 
			this.keyword.equals(castOther.keyword)
			&& this.channel.equals(castOther.channel)
			&& this.alias.equals(castOther.alias);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.keyword.hashCode();
		hash = hash * prime + this.channel.hashCode();
		hash = hash * prime + this.alias.hashCode();
		
		return hash;
	}
}