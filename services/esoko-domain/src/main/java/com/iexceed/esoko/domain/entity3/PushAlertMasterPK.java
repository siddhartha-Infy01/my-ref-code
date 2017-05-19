package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the push_alert_master database table.
 * 
 */
@Embeddable
public class PushAlertMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="push_alert_id")
	private String pushAlertId;

	@Column(name="people_id")
	private String peopleId;

	public PushAlertMasterPK() {
	}
	public String getPushAlertId() {
		return this.pushAlertId;
	}
	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}
	public String getPeopleId() {
		return this.peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PushAlertMasterPK)) {
			return false;
		}
		PushAlertMasterPK castOther = (PushAlertMasterPK)other;
		return 
			this.pushAlertId.equals(castOther.pushAlertId)
			&& this.peopleId.equals(castOther.peopleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pushAlertId.hashCode();
		hash = hash * prime + this.peopleId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "PushAlertMasterPK [pushAlertId=" + pushAlertId + ", peopleId="
				+ peopleId + "]";
	}
		
}