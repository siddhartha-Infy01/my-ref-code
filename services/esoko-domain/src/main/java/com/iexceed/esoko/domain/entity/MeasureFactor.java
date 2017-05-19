package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the measure_factor database table.
 * 
 */
@Entity
@Table(name = "measure_factor")
@NamedQuery(name = "MeasureFactor.findAll", query = "SELECT m FROM MeasureFactor m")
public class MeasureFactor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measure_conv_id")
	private int measureConvId;

	@Column(name = "base_measure_id")
	private String baseMeasureId;

	@Column(name = "commodity_id")
	private String commodityId;

	@Column(name = "conv_factor")
	private BigDecimal convFactor;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "default_measure")
	private String defaultMeasure;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_ts")
	private Date modifiedTs;

	@Column(name = "network_id")
	private String networkId;

	@Column(name = "price_type")
	private String priceType;

	@Column(name = "quote_measure_id")
	private String quoteMeasureId;

	public MeasureFactor() {
	}

	public int getMeasureConvId() {
		return this.measureConvId;
	}

	public void setMeasureConvId(int measureConvId) {
		this.measureConvId = measureConvId;
	}

	public String getBaseMeasureId() {
		return this.baseMeasureId;
	}

	public void setBaseMeasureId(String baseMeasureId) {
		this.baseMeasureId = baseMeasureId;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public BigDecimal getConvFactor() {
		return this.convFactor;
	}

	public void setConvFactor(BigDecimal convFactor) {
		this.convFactor = convFactor;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDefaultMeasure() {
		return this.defaultMeasure;
	}

	public void setDefaultMeasure(String defaultMeasure) {
		this.defaultMeasure = defaultMeasure;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getPriceType() {
		return this.priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getQuoteMeasureId() {
		return this.quoteMeasureId;
	}

	public void setQuoteMeasureId(String quoteMeasureId) {
		this.quoteMeasureId = quoteMeasureId;
	}

	@Override
	public String toString() {
		return "MeasureFactor [measureConvId=" + measureConvId
				+ ", baseMeasureId=" + baseMeasureId + ", commodityId="
				+ commodityId + ", convFactor=" + convFactor + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", date=" + date
				+ ", defaultMeasure=" + defaultMeasure + ", locationId="
				+ locationId + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", networkId=" + networkId + ", priceType="
				+ priceType + ", quoteMeasureId=" + quoteMeasureId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((baseMeasureId == null) ? 0 : baseMeasureId.hashCode());
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result
				+ ((convFactor == null) ? 0 : convFactor.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((defaultMeasure == null) ? 0 : defaultMeasure.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + measureConvId;
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((priceType == null) ? 0 : priceType.hashCode());
		result = prime * result
				+ ((quoteMeasureId == null) ? 0 : quoteMeasureId.hashCode());
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
		MeasureFactor other = (MeasureFactor) obj;
		if (baseMeasureId == null) {
			if (other.baseMeasureId != null)
				return false;
		} else if (!baseMeasureId.equals(other.baseMeasureId))
			return false;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (convFactor == null) {
			if (other.convFactor != null)
				return false;
		} else if (!convFactor.equals(other.convFactor))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (defaultMeasure == null) {
			if (other.defaultMeasure != null)
				return false;
		} else if (!defaultMeasure.equals(other.defaultMeasure))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (measureConvId != other.measureConvId)
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (priceType == null) {
			if (other.priceType != null)
				return false;
		} else if (!priceType.equals(other.priceType))
			return false;
		if (quoteMeasureId == null) {
			if (other.quoteMeasureId != null)
				return false;
		} else if (!quoteMeasureId.equals(other.quoteMeasureId))
			return false;
		return true;
	}

}