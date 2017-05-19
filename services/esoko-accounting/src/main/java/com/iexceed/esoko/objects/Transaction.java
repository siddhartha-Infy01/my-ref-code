package com.iexceed.esoko.objects;

import java.io.Serializable;

import com.iexceed.esoko.domain.entity.System_transaction;

public class Transaction implements Serializable {

	private String trnRefNo;
	private System_transaction trnEntity;
	private String status;

	public String getTrnRefNo() {
		return trnRefNo;
	}

	public void setTrnRefNo(String trnRefNo) {
		this.trnRefNo = trnRefNo;
	}

	public System_transaction getTrnEntity() {
		return trnEntity;
	}

	public void setTrnEntity(System_transaction trnEntity) {
		this.trnEntity = trnEntity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((trnEntity == null) ? 0 : trnEntity.hashCode());
		result = prime * result
				+ ((trnRefNo == null) ? 0 : trnRefNo.hashCode());
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
		Transaction other = (Transaction) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (trnEntity == null) {
			if (other.trnEntity != null)
				return false;
		} else if (!trnEntity.equals(other.trnEntity))
			return false;
		if (trnRefNo == null) {
			if (other.trnRefNo != null)
				return false;
		} else if (!trnRefNo.equals(other.trnRefNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [trnRefNo=" + trnRefNo + ", trnEntity=" + trnEntity
				+ ", status=" + status + "]";
	}
}
