package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the System_transactions database table.
 * 
 */
@Embeddable
public class System_transactionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="transaction_id")
	private int transactionId;

	@Column(name="transaction_ref_no")
	private String transactionRefNo;

	public System_transactionPK() {
	}
	public int getTransactionId() {
		return this.transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionRefNo() {
		return this.transactionRefNo;
	}
	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof System_transactionPK)) {
			return false;
		}
		System_transactionPK castOther = (System_transactionPK)other;
		return 
			(this.transactionId == castOther.transactionId)
			&& this.transactionRefNo.equals(castOther.transactionRefNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.transactionId;
		hash = hash * prime + this.transactionRefNo.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "System_transactionPK [transactionId=" + transactionId
				+ ", transactionRefNo=" + transactionRefNo + "]";
	}
}