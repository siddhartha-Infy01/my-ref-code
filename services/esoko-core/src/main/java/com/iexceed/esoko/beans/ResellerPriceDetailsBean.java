package com.iexceed.esoko.beans;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;

public class ResellerPriceDetailsBean {
	public static Logger log = LoggerUtils.getLogger();

	private String paramName;

	private String margin;
	private String networkName;
	private String paramValue;
	private String subscriptionCategory;
	private String subscriptionLevel;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getSubscriptionCategory() {
		return subscriptionCategory;
	}

	public void setSubscriptionCategory(String subscriptionCategory) {
		this.subscriptionCategory = subscriptionCategory;
	}

	public String getSubscriptionLevel() {
		return subscriptionLevel;
	}

	public void setSubscriptionLevel(String subscriptionLevel) {
		this.subscriptionLevel = subscriptionLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paramName == null) ? 0 : paramName.hashCode());
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
		ResellerPriceDetailsBean other = (ResellerPriceDetailsBean) obj;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		if (paramName.equalsIgnoreCase(other.paramName)) {
		}
		return true;
	}

	@Override
	public String toString() {
		return "ResellerPriceDetailsBean [paramName=" + paramName + ", margin="
				+ margin + ", networkName=" + networkName + ", paramValue="
				+ paramValue + ", subscriptionCategory=" + subscriptionCategory
				+ ", subscriptionLevel=" + subscriptionLevel + "]";
	}

}
