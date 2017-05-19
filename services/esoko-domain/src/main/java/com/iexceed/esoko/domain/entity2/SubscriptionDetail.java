package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the subscription_details database table.
 * 
 */
@Entity
@Table(name="subscription_details")
@NamedQuery(name="SubscriptionDetail.findAll", query="SELECT s FROM SubscriptionDetail s")
public class SubscriptionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionDetailPK id;

	@Column(name="country_name")
	private String countryName;

	private String margin;

	@Column(name="network_name")
	private String networkName;

	@Column(name="param_value")
	private String paramValue;

	@Column(name="subscription_category")
	private String subscriptionCategory;

	@Column(name="subscription_level")
	private String subscriptionLevel;

	public SubscriptionDetail() {
	}

	public SubscriptionDetailPK getId() {
		return this.id;
	}

	public void setId(SubscriptionDetailPK id) {
		this.id = id;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getMargin() {
		return this.margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getNetworkName() {
		return this.networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getSubscriptionCategory() {
		return this.subscriptionCategory;
	}

	public void setSubscriptionCategory(String subscriptionCategory) {
		this.subscriptionCategory = subscriptionCategory;
	}

	public String getSubscriptionLevel() {
		return this.subscriptionLevel;
	}

	public void setSubscriptionLevel(String subscriptionLevel) {
		this.subscriptionLevel = subscriptionLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((margin == null) ? 0 : margin.hashCode());
		result = prime * result
				+ ((networkName == null) ? 0 : networkName.hashCode());
		result = prime * result
				+ ((paramValue == null) ? 0 : paramValue.hashCode());
		result = prime
				* result
				+ ((subscriptionCategory == null) ? 0 : subscriptionCategory
						.hashCode());
		result = prime
				* result
				+ ((subscriptionLevel == null) ? 0 : subscriptionLevel
						.hashCode());
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
		SubscriptionDetail other = (SubscriptionDetail) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (margin == null) {
			if (other.margin != null)
				return false;
		} else if (!margin.equals(other.margin))
			return false;
		if (networkName == null) {
			if (other.networkName != null)
				return false;
		} else if (!networkName.equals(other.networkName))
			return false;
		if (paramValue == null) {
			if (other.paramValue != null)
				return false;
		} else if (!paramValue.equals(other.paramValue))
			return false;
		if (subscriptionCategory == null) {
			if (other.subscriptionCategory != null)
				return false;
		} else if (!subscriptionCategory.equals(other.subscriptionCategory))
			return false;
		if (subscriptionLevel == null) {
			if (other.subscriptionLevel != null)
				return false;
		} else if (!subscriptionLevel.equals(other.subscriptionLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionDetail [id=" + id + ", countryName=" + countryName
				+ ", margin=" + margin + ", networkName=" + networkName
				+ ", paramValue=" + paramValue + ", subscriptionCategory="
				+ subscriptionCategory + ", subscriptionLevel="
				+ subscriptionLevel + "]";
	}

}