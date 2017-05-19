package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the network_categories database table.
 * 
 */
@Embeddable
public class NetworkCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	private String category;

	public NetworkCategoryPK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetworkCategoryPK)) {
			return false;
		}
		NetworkCategoryPK castOther = (NetworkCategoryPK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.category.equals(castOther.category);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.category.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "NetworkCategoryPK [networkId=" + networkId + ", category="
				+ category + "]";
	}
}