package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Network_Apps database table.
 * 
 */
@Embeddable
public class Network_AppPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	@Column(name="app_id")
	private String appId;

	public Network_AppPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getAppId() {
		return this.appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Network_AppPK)) {
			return false;
		}
		Network_AppPK castOther = (Network_AppPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.appId.equals(castOther.appId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.appId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "Network_AppPK [networkId=" + networkId + ", appId=" + appId
				+ "]";
	}
}