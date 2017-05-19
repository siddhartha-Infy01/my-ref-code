package com.iexceed.esoko.objects;

import java.io.Serializable;
import java.util.Date;

public class Accounting implements Serializable {
	private static final long serialVersionUID = -7205300023874604536L;
	private String trnCode;
	private String debitAcNo;
	private String debitCcy;
	private String debitForeignCcy;
	private String debitType;
	private String creditAcNo;
	private String creditCcy;
	private String resellerAcNo;
	private Date transactionDate;
	private String networkId;
	private String userId;
	private Cost trnAmt;
    private String description;
	public String getTrnCode() {
		return trnCode;
	}
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	public String getDebitAcNo() {
		return debitAcNo;
	}
	public void setDebitAcNo(String debitAcNo) {
		this.debitAcNo = debitAcNo;
	}
	public String getDebitCcy() {
		return debitCcy;
	}
	public void setDebitCcy(String debitCcy) {
		this.debitCcy = debitCcy;
	}
	public String getDebitForeignCcy() {
		return debitForeignCcy;
	}
	public void setDebitForeignCcy(String debitForeignCcy) {
		this.debitForeignCcy = debitForeignCcy;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public String getCreditAcNo() {
		return creditAcNo;
	}
	public void setCreditAcNo(String creditAcNo) {
		this.creditAcNo = creditAcNo;
	}
	public String getCreditCcy() {
		return creditCcy;
	}
	public void setCreditCcy(String creditCcy) {
		this.creditCcy = creditCcy;
	}
	public String getResellerAcNo() {
		return resellerAcNo;
	}
	public void setResellerAcNo(String resellerAcNo) {
		this.resellerAcNo = resellerAcNo;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getNetworkId() {
		return networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Cost getTrnAmt() {
		return trnAmt;
	}
	public void setTrnAmt(Cost trnAmt) {
		this.trnAmt = trnAmt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creditAcNo == null) ? 0 : creditAcNo.hashCode());
		result = prime * result
				+ ((creditCcy == null) ? 0 : creditCcy.hashCode());
		result = prime * result
				+ ((debitAcNo == null) ? 0 : debitAcNo.hashCode());
		result = prime * result
				+ ((debitCcy == null) ? 0 : debitCcy.hashCode());
		result = prime * result
				+ ((debitForeignCcy == null) ? 0 : debitForeignCcy.hashCode());
		result = prime * result
				+ ((debitType == null) ? 0 : debitType.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((resellerAcNo == null) ? 0 : resellerAcNo.hashCode());
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((trnAmt == null) ? 0 : trnAmt.hashCode());
		result = prime * result + ((trnCode == null) ? 0 : trnCode.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Accounting other = (Accounting) obj;
		if (creditAcNo == null) {
			if (other.creditAcNo != null)
				return false;
		} else if (!creditAcNo.equals(other.creditAcNo))
			return false;
		if (creditCcy == null) {
			if (other.creditCcy != null)
				return false;
		} else if (!creditCcy.equals(other.creditCcy))
			return false;
		if (debitAcNo == null) {
			if (other.debitAcNo != null)
				return false;
		} else if (!debitAcNo.equals(other.debitAcNo))
			return false;
		if (debitCcy == null) {
			if (other.debitCcy != null)
				return false;
		} else if (!debitCcy.equals(other.debitCcy))
			return false;
		if (debitForeignCcy == null) {
			if (other.debitForeignCcy != null)
				return false;
		} else if (!debitForeignCcy.equals(other.debitForeignCcy))
			return false;
		if (debitType == null) {
			if (other.debitType != null)
				return false;
		} else if (!debitType.equals(other.debitType))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (resellerAcNo == null) {
			if (other.resellerAcNo != null)
				return false;
		} else if (!resellerAcNo.equals(other.resellerAcNo))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (trnAmt == null) {
			if (other.trnAmt != null)
				return false;
		} else if (!trnAmt.equals(other.trnAmt))
			return false;
		if (trnCode == null) {
			if (other.trnCode != null)
				return false;
		} else if (!trnCode.equals(other.trnCode))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Accounting [trnCode=" + trnCode + ", debitAcNo=" + debitAcNo
				+ ", debitCcy=" + debitCcy + ", debitForeignCcy="
				+ debitForeignCcy + ", debitType=" + debitType
				+ ", creditAcNo=" + creditAcNo + ", creditCcy=" + creditCcy
				+ ", resellerAcNo=" + resellerAcNo + ", transactionDate="
				+ transactionDate + ", networkId=" + networkId + ", userId="
				+ userId + ", trnAmt=" + trnAmt + ", description="
				+ description + "]";
	}
		   
    
}
