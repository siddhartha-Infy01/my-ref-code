package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the keywords database table.
 * 
 */
@Embeddable
public class KeywordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String keyword;

	private String channel;

	public KeywordPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KeywordPK)) {
			return false;
		}
		KeywordPK castOther = (KeywordPK)other;
		return 
			this.keyword.equals(castOther.keyword)
			&& this.channel.equals(castOther.channel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.keyword.hashCode();
		hash = hash * prime + this.channel.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "KeywordPK [keyword=" + keyword + ", channel=" + channel + "]";
	}
		
}