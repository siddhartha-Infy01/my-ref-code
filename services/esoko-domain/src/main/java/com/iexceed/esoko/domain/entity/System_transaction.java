package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the System_transactions database table.
 * 
 */
@Entity
@Table(name="System_transactions")
@NamedQuery(name="System_transaction.findAll", query="SELECT s FROM System_transaction s")
public class System_transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private System_transactionPK id;

	@Column(name="account_no")
	private String accountNo;

	@Column(name="account_type")
	private String accountType;

	private Double balance;

	private String balance_Stat;

	@Column(name="cr_dr")
	private String crDr;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String description;

	@Column(name="ex_rate")
	private BigDecimal exRate;

	@Column(name="fcy_amount")
	private Double fcyAmount;

	@Column(name="fcy_ccy")
	private String fcyCcy;

	@Column(name="lcy_amount")
	private Double lcyAmount;

	@Column(name="local_ccy")
	private String localCcy;

	@Column(name="network_id")
	private String networkId;

	@Column(name="trans_code")
	private String transCode;

	@Column(name="transaction_date")
	private Date transactionDate;

	public System_transaction() {
	}

	public System_transactionPK getId() {
		return this.id;
	}

	public void setId(System_transactionPK id) {
		this.id = id;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getBalance_Stat() {
		return this.balance_Stat;
	}

	public void setBalance_Stat(String balance_Stat) {
		this.balance_Stat = balance_Stat;
	}

	public String getCrDr() {
		return this.crDr;
	}

	public void setCrDr(String crDr) {
		this.crDr = crDr;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getExRate() {
		return this.exRate;
	}

	public void setExRate(BigDecimal exRate) {
		this.exRate = exRate;
	}

	public Double getFcyAmount() {
		return this.fcyAmount;
	}

	public void setFcyAmount(Double d) {
		this.fcyAmount = d;
	}

	public String getFcyCcy() {
		return this.fcyCcy;
	}

	public void setFcyCcy(String fcyCcy) {
		this.fcyCcy = fcyCcy;
	}

	public Double getLcyAmount() {
		return this.lcyAmount;
	}

	public void setLcyAmount(Double d) {
		this.lcyAmount = d;
	}

	public String getLocalCcy() {
		return this.localCcy;
	}

	public void setLocalCcy(String localCcy) {
		this.localCcy = localCcy;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result
				+ ((balance_Stat == null) ? 0 : balance_Stat.hashCode());
		result = prime * result + ((crDr == null) ? 0 : crDr.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((exRate == null) ? 0 : exRate.hashCode());
		result = prime * result
				+ ((fcyAmount == null) ? 0 : fcyAmount.hashCode());
		result = prime * result + ((fcyCcy == null) ? 0 : fcyCcy.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lcyAmount == null) ? 0 : lcyAmount.hashCode());
		result = prime * result
				+ ((localCcy == null) ? 0 : localCcy.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((transCode == null) ? 0 : transCode.hashCode());
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
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
		System_transaction other = (System_transaction) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (balance_Stat == null) {
			if (other.balance_Stat != null)
				return false;
		} else if (!balance_Stat.equals(other.balance_Stat))
			return false;
		if (crDr == null) {
			if (other.crDr != null)
				return false;
		} else if (!crDr.equals(other.crDr))
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (exRate == null) {
			if (other.exRate != null)
				return false;
		} else if (!exRate.equals(other.exRate))
			return false;
		if (fcyAmount == null) {
			if (other.fcyAmount != null)
				return false;
		} else if (!fcyAmount.equals(other.fcyAmount))
			return false;
		if (fcyCcy == null) {
			if (other.fcyCcy != null)
				return false;
		} else if (!fcyCcy.equals(other.fcyCcy))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lcyAmount == null) {
			if (other.lcyAmount != null)
				return false;
		} else if (!lcyAmount.equals(other.lcyAmount))
			return false;
		if (localCcy == null) {
			if (other.localCcy != null)
				return false;
		} else if (!localCcy.equals(other.localCcy))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (transCode == null) {
			if (other.transCode != null)
				return false;
		} else if (!transCode.equals(other.transCode))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "System_transaction [id=" + id + ", accountNo=" + accountNo
				+ ", accountType=" + accountType + ", balance=" + balance
				+ ", balance_Stat=" + balance_Stat + ", crDr=" + crDr
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", description=" + description + ", exRate=" + exRate
				+ ", fcyAmount=" + fcyAmount + ", fcyCcy=" + fcyCcy
				+ ", lcyAmount=" + lcyAmount + ", localCcy=" + localCcy
				+ ", networkId=" + networkId + ", transCode=" + transCode
				+ ", transactionDate=" + transactionDate + "]";
	}

}