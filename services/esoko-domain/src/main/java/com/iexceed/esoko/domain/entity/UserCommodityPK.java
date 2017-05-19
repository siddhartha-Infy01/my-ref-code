package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_commodities database table.
 * 
 */
@Embeddable
public class UserCommodityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;

	@Column(name="commodity_id")
	private String commodityId;

	@Column(name="network_id")
	private String networkId;

	public UserCommodityPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommodityId() {
		return this.commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
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
		if (!(other instanceof UserCommodityPK)) {
			return false;
		}
		UserCommodityPK castOther = (UserCommodityPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.commodityId.equals(castOther.commodityId)
			&& this.networkId.equals(castOther.networkId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.commodityId.hashCode();
		hash = hash * prime + this.networkId.hashCode();
		
		return hash;
	}
}