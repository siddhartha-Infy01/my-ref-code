package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the network_sms_codes database table.
 * 
 */
@Embeddable
public class NetworkSmsCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="sms_id")
	private String smsId;

	public NetworkSmsCodePK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getSmsId() {
		return this.smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetworkSmsCodePK)) {
			return false;
		}
		NetworkSmsCodePK castOther = (NetworkSmsCodePK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.smsId.equals(castOther.smsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.smsId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "NetworkSmsCodePK [networkId=" + networkId + ", smsId=" + smsId
				+ "]";
	}
}