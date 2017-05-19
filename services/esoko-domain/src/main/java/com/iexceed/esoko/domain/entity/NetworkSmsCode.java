package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the network_sms_codes database table.
 * 
 */
@Entity
@Table(name="network_sms_codes")
@NamedQuery(name="NetworkSmsCode.findAll", query="SELECT n FROM NetworkSmsCode n")
public class NetworkSmsCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NetworkSmsCodePK id;

	@Column(name="is_primary")
	private String isPrimary;

	public NetworkSmsCode() {
	}

	public NetworkSmsCodePK getId() {
		return this.id;
	}

	public void setId(NetworkSmsCodePK id) {
		this.id = id;
	}

	public String getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isPrimary == null) ? 0 : isPrimary.hashCode());
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
		NetworkSmsCode other = (NetworkSmsCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPrimary == null) {
			if (other.isPrimary != null)
				return false;
		} else if (!isPrimary.equals(other.isPrimary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NetworkSmsCode [id=" + id + ", isPrimary=" + isPrimary + "]";
	}

}