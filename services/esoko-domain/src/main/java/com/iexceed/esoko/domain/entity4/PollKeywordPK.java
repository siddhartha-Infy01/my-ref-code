package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the poll_keywords database table.
 * 
 */
@Embeddable
public class PollKeywordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="poll_id")
	private String pollId;

	private String keyword;

	@Column(name="poll_ques_no")
	private String pollQuesNo;

	public PollKeywordPK() {
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
	public String getPollQuesNo() {
		return this.pollQuesNo;
	}
	public void setPollQuesNo(String pollQuesNo) {
		this.pollQuesNo = pollQuesNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PollKeywordPK)) {
			return false;
		}
		PollKeywordPK castOther = (PollKeywordPK)other;
		return 
			this.pollId.equals(castOther.pollId)
			&& this.keyword.equals(castOther.keyword)
			&& this.pollQuesNo.equals(castOther.pollQuesNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pollId.hashCode();
		hash = hash * prime + this.keyword.hashCode();
		hash = hash * prime + this.pollQuesNo.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "PollKeywordPK [pollId=" + pollId + ", keyword=" + keyword
				+ ", pollQuesNo=" + pollQuesNo + "]";
	}
		
}