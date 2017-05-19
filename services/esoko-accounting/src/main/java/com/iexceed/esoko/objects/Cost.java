package com.iexceed.esoko.objects;

import java.io.Serializable;

public class Cost implements Serializable {
	private double baseCost;
	private double wholesalePrice;
	private double retailPrice;

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Override
	public String toString() {
		return "Cost [baseCost=" + baseCost + ", wholesalePrice="
				+ wholesalePrice + ", retailPrice=" + retailPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(retailPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wholesalePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Cost other = (Cost) obj;
		if (Double.doubleToLongBits(baseCost) != Double
				.doubleToLongBits(other.baseCost))
			return false;
		if (Double.doubleToLongBits(retailPrice) != Double
				.doubleToLongBits(other.retailPrice))
			return false;
		if (Double.doubleToLongBits(wholesalePrice) != Double
				.doubleToLongBits(other.wholesalePrice))
			return false;
		return true;
	}

}
