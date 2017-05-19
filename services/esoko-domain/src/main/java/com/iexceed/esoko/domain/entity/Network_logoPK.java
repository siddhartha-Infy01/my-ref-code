package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Network_logo database table.
 * 
 */
@Embeddable
public class Network_logoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="logo_id")
	private String logoId;

	@Column(name="network_id")
	private String networkId;

	public Network_logoPK() {
	}
	public String getLogoId() {
		return this.logoId;
	}
	public void setLogoId(String logoId) {
		this.logoId = logoId;
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
		if (!(other instanceof Network_logoPK)) {
			return false;
		}
		Network_logoPK castOther = (Network_logoPK)other;
		return 
			this.logoId.equals(castOther.logoId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.logoId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Network_logoPK [logoId=" + logoId + ", networkId=" + networkId
				+ "]";
	}
}