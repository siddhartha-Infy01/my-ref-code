package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the forex_history database table.
 * 
 */
@Entity
@Table(name="forex_history")
@NamedQuery(name="ForexHistory.findAll", query="SELECT f FROM ForexHistory f")
public class ForexHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ForexHistoryPK id;

	@Column(name="base_currency_id")
	private String baseCurrencyId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="quote_currency_id")
	private String quoteCurrencyId;

	private BigDecimal rate;

	public ForexHistory() {
	}

	public ForexHistoryPK getId() {
		return this.id;
	}

	public void setId(ForexHistoryPK id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((quoteCurrencyId == null) ? 0 : quoteCurrencyId.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		ForexHistory other = (ForexHistory) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}

	@Override
	public String toString() {
		return "ForexHistory [id=" + id + ", baseCurrencyId=" + baseCurrencyId
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", quoteCurrencyId=" + quoteCurrencyId + ", rate=" + rate
				+ "]";
	}

}