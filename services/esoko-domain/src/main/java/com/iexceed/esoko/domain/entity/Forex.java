package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the forex database table.
 * 
 */
@Entity
@Table(name="forex")
@NamedQuery(name="Forex.findAll", query="SELECT f FROM Forex f")
public class Forex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="forex_id")
	private String forexId;

	@Column(name="base_currency_id")
	private String baseCurrencyId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="quote_currency_id")
	private String quoteCurrencyId;

	private BigDecimal rate;

	@Column(name="record_status")
	private String recordStatus;

	public Forex() {
	}

	public String getForexId() {
		return this.forexId;
	}

	public void setForexId(String forexId) {
		this.forexId = forexId;
	}

	public String getBaseCurrencyId() {
		return this.baseCurrencyId;
	}

	public void setBaseCurrencyId(String baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getQuoteCurrencyId() {
		return this.quoteCurrencyId;
	}

	public void setQuoteCurrencyId(String quoteCurrencyId) {
		this.quoteCurrencyId = quoteCurrencyId;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((baseCurrencyId == null) ? 0 : baseCurrencyId.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((forexId == null) ? 0 : forexId.hashCode());
				result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((quoteCurrencyId == null) ? 0 : quoteCurrencyId.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
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
		Forex other = (Forex) obj;
		if (baseCurrencyId == null) {
			if (other.baseCurrencyId != null)
				return false;
		} else if (!baseCurrencyId.equals(other.baseCurrencyId))
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (forexId == null) {
			if (other.forexId != null)
				return false;
		} else if (!forexId.equals(other.forexId))
			return false;
	
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (quoteCurrencyId == null) {
			if (other.quoteCurrencyId != null)
				return false;
		} else if (!quoteCurrencyId.equals(other.quoteCurrencyId))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Forex [forexId=" + forexId + ", baseCurrencyId="
				+ baseCurrencyId + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", date=" + date + ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", quoteCurrencyId=" + quoteCurrencyId + ", rate=" + rate
				+ ", recordStatus=" + recordStatus + "]";
	}

}