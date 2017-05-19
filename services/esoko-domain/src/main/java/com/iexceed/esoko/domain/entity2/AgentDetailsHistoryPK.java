package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the agent_details_history database table.
 * 
 */
@Embeddable
public class AgentDetailsHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detail_id")
	private int detailId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="effective_date")
	private java.util.Date effectiveDate;

	public AgentDetailsHistoryPK() {
	}
	public int getDetailId() {
		return this.detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
	}
	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AgentDetailsHistoryPK)) {
			return false;
		}
		AgentDetailsHistoryPK castOther = (AgentDetailsHistoryPK)other;
		return 
			(this.detailId == castOther.detailId)
			&& this.effectiveDate.equals(castOther.effectiveDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.detailId;
		hash = hash * prime + this.effectiveDate.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "AgentDetailsHistoryPK [detailId=" + detailId
				+ ", effectiveDate=" + effectiveDate + "]";
	}
	
	
}