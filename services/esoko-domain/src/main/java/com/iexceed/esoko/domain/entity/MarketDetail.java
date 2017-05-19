package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;


/**
 * The persistent class for the market_details database table.
 * 
 */
@Entity
@Table(name="market_details")
@NamedQuery(name="MarketDetail.findAll", query="SELECT m FROM MarketDetail m")
public class MarketDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="location_id")
	private String locationId;

	private String commodities;

	@Column(name="days_of_week")
	private String daysOfWeek;

	@Lob
	@Column(name="location_pic")
	private byte[] locationPic;

	@Column(name="price_types")
	private String priceTypes;

	public MarketDetail() {
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getCommodities() {
		return this.commodities;
	}

	public void setCommodities(String commodities) {
		this.commodities = commodities;
	}

	public String getDaysOfWeek() {
		return this.daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public byte[] getLocationPic() {
		return this.locationPic;
	}

	public void setLocationPic(byte[] locationPic) {
		this.locationPic = locationPic;
	}

	public String getPriceTypes() {
		return this.priceTypes;
	}

	public void setPriceTypes(String priceTypes) {
		this.priceTypes = priceTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commodities == null) ? 0 : commodities.hashCode());
		result = prime * result
				+ ((daysOfWeek == null) ? 0 : daysOfWeek.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + Arrays.hashCode(locationPic);
		result = prime * result
				+ ((priceTypes == null) ? 0 : priceTypes.hashCode());
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
		MarketDetail other = (MarketDetail) obj;
		if (commodities == null) {
			if (other.commodities != null)
				return false;
		} else if (!commodities.equals(other.commodities))
			return false;
		if (daysOfWeek == null) {
			if (other.daysOfWeek != null)
				return false;
		} else if (!daysOfWeek.equals(other.daysOfWeek))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (!Arrays.equals(locationPic, other.locationPic))
			return false;
		if (priceTypes == null) {
			if (other.priceTypes != null)
				return false;
		} else if (!priceTypes.equals(other.priceTypes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarketDetail [locationId=" + locationId + ", commodities="
				+ commodities + ", daysOfWeek=" + daysOfWeek + ", locationPic="
				+ Arrays.toString(locationPic) + ", priceTypes=" + priceTypes
				+ "]";
	}

}