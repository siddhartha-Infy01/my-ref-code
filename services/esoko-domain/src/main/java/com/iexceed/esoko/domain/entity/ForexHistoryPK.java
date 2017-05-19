package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the forex_history database table.
 * 
 */
@Embeddable
public class ForexHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="forex_id")
	private String forexId;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date date;

	public ForexHistoryPK() {
	}
	public String getForexId() {
		return this.forexId;
	}
	public void setForexId(String forexId) {
		this.forexId = forexId;
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ForexHistoryPK)) {
			return false;
		}
		ForexHistoryPK castOther = (ForexHistoryPK)other;
		return 
			this.forexId.equals(castOther.forexId)
			&& this.date.equals(castOther.date);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.forexId.hashCode();
		hash = hash * prime + this.date.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "ForexHistoryPK [forexId=" + forexId + ", date=" + date + "]";
	}
}