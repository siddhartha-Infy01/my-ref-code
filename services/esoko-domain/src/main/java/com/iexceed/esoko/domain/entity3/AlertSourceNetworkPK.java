package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the alert_source_networks database table.
 * 
 */
@Embeddable
public class AlertSourceNetworkPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "push_alert_id")
	private String pushAlertId;

	@Column(name = "network_id")
	private String networkId;

	public AlertSourceNetworkPK() {
	}

	public String getPushAlertId() {
		return this.pushAlertId;
	}

	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlertSourceNetworkPK)) {
			return false;
		}
		AlertSourceNetworkPK castOther = (AlertSourceNetworkPK) other;
		return this.pushAlertId.equals(castOther.pushAlertId)
				&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pushAlertId.hashCode();
		hash = hash * prime + this.networkId.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "AlertSourceNetworkPK [pushAlertId=" + pushAlertId
				+ ", networkId=" + networkId + "]";
	}

}