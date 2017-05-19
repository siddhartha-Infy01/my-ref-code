package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the poll_details database table.
 * 
 */
@Entity
@Table(name="poll_details")
@NamedQuery(name="PollDetail.findAll", query="SELECT p FROM PollDetail p")
public class PollDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PollDetailPK id;

	@Column(name="correct_go_to")
	private String correctGoTo;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="incorrect_go_to")
	private String incorrectGoTo;

	@Column(name="no_of_char")
	private int noOfChar;

	@Column(name="no_of_msg")
	private int noOfMsg;

	@Lob
	@Column(name="poll_ques")
	private String pollQues;

	public PollDetail() {
	}

	public PollDetailPK getId() {
		return this.id;
	}

	public void setId(PollDetailPK id) {
		this.id = id;
	}

	public String getCorrectGoTo() {
		return this.correctGoTo;
	}

	public void setCorrectGoTo(String correctGoTo) {
		this.correctGoTo = correctGoTo;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getIncorrectGoTo() {
		return this.incorrectGoTo;
	}

	public void setIncorrectGoTo(String incorrectGoTo) {
		this.incorrectGoTo = incorrectGoTo;
	}

	public int getNoOfChar() {
		return this.noOfChar;
	}

	public void setNoOfChar(int noOfChar) {
		this.noOfChar = noOfChar;
	}

	public int getNoOfMsg() {
		return this.noOfMsg;
	}

	public void setNoOfMsg(int noOfMsg) {
		this.noOfMsg = noOfMsg;
	}

	public String getPollQues() {
		return this.pollQues;
	}

	public void setPollQues(String pollQues) {
		this.pollQues = pollQues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((correctGoTo == null) ? 0 : correctGoTo.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((incorrectGoTo == null) ? 0 : incorrectGoTo.hashCode());
		result = prime * result + noOfChar;
		result = prime * result + noOfMsg;
		result = prime * result
				+ ((pollQues == null) ? 0 : pollQues.hashCode());
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
		PollDetail other = (PollDetail) obj;
		if (correctGoTo == null) {
			if (other.correctGoTo != null)
				return false;
		} else if (!correctGoTo.equals(other.correctGoTo))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (incorrectGoTo == null) {
			if (other.incorrectGoTo != null)
				return false;
		} else if (!incorrectGoTo.equals(other.incorrectGoTo))
			return false;
		if (noOfChar != other.noOfChar)
			return false;
		if (noOfMsg != other.noOfMsg)
			return false;
		if (pollQues == null) {
			if (other.pollQues != null)
				return false;
		} else if (!pollQues.equals(other.pollQues))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PollDetail [id=" + id + ", correctGoTo=" + correctGoTo
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", incorrectGoTo=" + incorrectGoTo + ", noOfChar=" + noOfChar
				+ ", noOfMsg=" + noOfMsg + ", pollQues=" + pollQues + "]";
	}
		
}