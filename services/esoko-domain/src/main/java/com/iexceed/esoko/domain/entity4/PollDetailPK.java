package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the poll_details database table.
 * 
 */
@Embeddable
public class PollDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="poll_id")
	private String pollId;

	@Column(name="ques_no")
	private String quesNo;

	public PollDetailPK() {
	}
	public String getPollId() {
		return this.pollId;
	}
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	public String getQuesNo() {
		return this.quesNo;
	}
	public void setQuesNo(String quesNo) {
		this.quesNo = quesNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PollDetailPK)) {
			return false;
		}
		PollDetailPK castOther = (PollDetailPK)other;
		return 
			this.pollId.equals(castOther.pollId)
			&& this.quesNo.equals(castOther.quesNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pollId.hashCode();
		hash = hash * prime + this.quesNo.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "PollDetailPK [pollId=" + pollId + ", quesNo=" + quesNo + "]";
	}
		
}