package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the message_delivary_Details database table.
 * 
 */
@Embeddable
public class Message_delivary_DetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="trn_ref_no")
	private String trnRefNo;

	@Column(name="message_id")
	private String messageId;

	public Message_delivary_DetailPK() {
	}
	public String getTrnRefNo() {
		return this.trnRefNo;
	}
	public void setTrnRefNo(String trnRefNo) {
		this.trnRefNo = trnRefNo;
	}
	public String getMessageId() {
		return this.messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Message_delivary_DetailPK)) {
			return false;
		}
		Message_delivary_DetailPK castOther = (Message_delivary_DetailPK)other;
		return 
			this.trnRefNo.equals(castOther.trnRefNo)
			&& this.messageId.equals(castOther.messageId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.trnRefNo.hashCode();
		hash = hash * prime + this.messageId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "Message_delivary_DetailPK [trnRefNo=" + trnRefNo
				+ ", messageId=" + messageId + "]";
	}	
	
}