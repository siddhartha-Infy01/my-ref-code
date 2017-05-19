package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the gateway_details database table.
 * 
 */
@Entity
@Table(name="gateway_details")
@NamedQuery(name="GatewayDetail.findAll", query="SELECT g FROM GatewayDetail g")
public class GatewayDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gateway_id")
	private String gatewayId;

	@Column(name="gateway_name")
	private String gatewayName;

	public GatewayDetail() {
	}

	public String getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getGatewayName() {
		return this.gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gatewayId == null) ? 0 : gatewayId.hashCode());
		result = prime * result
				+ ((gatewayName == null) ? 0 : gatewayName.hashCode());
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
		GatewayDetail other = (GatewayDetail) obj;
		if (gatewayId == null) {
			if (other.gatewayId != null)
				return false;
		} else if (!gatewayId.equals(other.gatewayId))
			return false;
		if (gatewayName == null) {
			if (other.gatewayName != null)
				return false;
		} else if (!gatewayName.equals(other.gatewayName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GatewayDetail [gatewayId=" + gatewayId + ", gatewayName="
				+ gatewayName + "]";
	}
	
	
}