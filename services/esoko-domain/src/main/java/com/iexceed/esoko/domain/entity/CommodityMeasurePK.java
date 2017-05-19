package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the commodity_measures database table.
 * 
 */
@Embeddable
public class CommodityMeasurePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="measure_id")
	private String measureId;

	@Column(name="commodity_id")
	private String commodityId;

	@Column(name="location_id")
	private String locationId;

	public CommodityMeasurePK() {
	}
	public String getMeasureId() {
		return this.measureId;
	}
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	public String getCommodityId() {
		return this.commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getLocationId() {
		return this.locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommodityMeasurePK)) {
			return false;
		}
		CommodityMeasurePK castOther = (CommodityMeasurePK)other;
		return 
			this.measureId.equals(castOther.measureId)
			&& this.commodityId.equals(castOther.commodityId)
			&& this.locationId.equals(castOther.locationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.measureId.hashCode();
		hash = hash * prime + this.commodityId.hashCode();
		hash = hash * prime + this.locationId.hashCode();
		
		return hash;
	}
}