package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the alert_locations database table.
 * 
 */
@Embeddable
public class AlertLocationPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "push_alert_id")
	private String pushAlertId;

	@Column(name = "location_id")
	private String locationId;

	public AlertLocationPK() {
	}

	public String getPushAlertId() {
		return this.pushAlertId;
	}

	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlertLocationPK)) {
			return false;
		}
		AlertLocationPK castOther = (AlertLocationPK) other;
		return this.pushAlertId.equals(castOther.pushAlertId)
				&& this.locationId.equals(castOther.locationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pushAlertId.hashCode();
		hash = hash * prime + this.locationId.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "AlertLocationPK [pushAlertId=" + pushAlertId + ", locationId="
				+ locationId + "]";
	}

}