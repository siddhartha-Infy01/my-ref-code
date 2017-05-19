package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the agent_upload_count database table.
 * 
 */
@Embeddable
public class AgentUploadCountPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="detail_id")
	private int detailId;

	@Column(name="effective_date")
	private String effectiveDate;

	public AgentUploadCountPK() {
	}
	public int getDetailId() {
		return this.detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public String getEffectiveDate() {
		return this.effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AgentUploadCountPK)) {
			return false;
		}
		AgentUploadCountPK castOther = (AgentUploadCountPK)other;
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
		return "AgentUploadCountPK [detailId=" + detailId + ", effectiveDate="
				+ effectiveDate + "]";
	}
	
	
}