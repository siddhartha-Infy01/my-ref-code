package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the sms_code_alias database table.
 * 
 */
@Entity
@Table(name="sms_code_alias")
@NamedQuery(name="SmsCodeAlia.findAll", query="SELECT s FROM SmsCodeAlia s")
public class SmsCodeAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="alias_id")
	private String aliasId;

	@Column(name="network_id")
	private String networkId;

	@Column(name="smid_id")
	private String smidId;

	@Column(name="sms_code")
	private String smsCode;

	public SmsCodeAlia() {
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getSmidId() {
		return this.smidId;
	}

	public void setSmidId(String smidId) {
		this.smidId = smidId;
	}

	public String getSmsCode() {
		return this.smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasId == null) ? 0 : aliasId.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + ((smidId == null) ? 0 : smidId.hashCode());
		result = prime * result + ((smsCode == null) ? 0 : smsCode.hashCode());
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
		SmsCodeAlia other = (SmsCodeAlia) obj;
		if (aliasId == null) {
			if (other.aliasId != null)
				return false;
		} else if (!aliasId.equals(other.aliasId))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (smidId == null) {
			if (other.smidId != null)
				return false;
		} else if (!smidId.equals(other.smidId))
			return false;
		if (smsCode == null) {
			if (other.smsCode != null)
				return false;
		} else if (!smsCode.equals(other.smsCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SmsCodeAlia [aliasId=" + aliasId + ", networkId=" + networkId
				+ ", smidId=" + smidId + ", smsCode=" + smsCode + "]";
	}

}