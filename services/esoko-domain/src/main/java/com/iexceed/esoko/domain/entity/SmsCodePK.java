package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sms_codes database table.
 * 
 */
@Embeddable
public class SmsCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="sms_id")
	private String smsId;

	@Column(name="network_id")
	private String networkId;

	public SmsCodePK() {
	}
	public String getSmsId() {
		return this.smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
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
		if (!(other instanceof SmsCodePK)) {
			return false;
		}
		SmsCodePK castOther = (SmsCodePK)other;
		return 
			this.smsId.equals(castOther.smsId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.smsId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "SmsCodePK [smsId=" + smsId + ", networkId=" + networkId + "]";
	}
}