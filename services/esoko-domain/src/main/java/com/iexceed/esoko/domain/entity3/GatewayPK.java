package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gateways database table.
 * 
 */
@Embeddable
public class GatewayPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="gateway_id")
	private String gatewayId;

	@Column(name="route_id")
	private String routeId;

	private String type;

	public GatewayPK() {
	}
	public String getGatewayId() {
		return this.gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getRouteId() {
		return this.routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GatewayPK)) {
			return false;
		}
		GatewayPK castOther = (GatewayPK)other;
		return 
			this.gatewayId.equals(castOther.gatewayId)
			&& this.routeId.equals(castOther.routeId)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gatewayId.hashCode();
		hash = hash * prime + this.routeId.hashCode();
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "GatewayPK [gatewayId=" + gatewayId + ", routeId=" + routeId
				+ ", type=" + type + "]";
	}
	
}