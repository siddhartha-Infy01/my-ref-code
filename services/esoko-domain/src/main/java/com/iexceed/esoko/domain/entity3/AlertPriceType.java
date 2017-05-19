package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the alert_price_type database table.
 * 
 */
@Entity
@Table(name="alert_price_type")
@NamedQuery(name="AlertPriceType.findAll", query="SELECT a FROM AlertPriceType a")
public class AlertPriceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="alert_id")
	private String alertId;

	@Column(name="measure_id")
	private String measureId;

	@Column(name="price_type")
	private String priceType;

	public AlertPriceType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlertId() {
		return this.alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public String getMeasureId() {
		return this.measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getPriceType() {
		return this.priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertId == null) ? 0 : alertId.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((measureId == null) ? 0 : measureId.hashCode());
		result = prime * result
				+ ((priceType == null) ? 0 : priceType.hashCode());
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
		AlertPriceType other = (AlertPriceType) obj;
		if (alertId == null) {
			if (other.alertId != null)
				return false;
		} else if (!alertId.equals(other.alertId))
			return false;
		if (id != other.id)
			return false;
		if (measureId == null) {
			if (other.measureId != null)
				return false;
		} else if (!measureId.equals(other.measureId))
			return false;
		if (priceType == null) {
			if (other.priceType != null)
				return false;
		} else if (!priceType.equals(other.priceType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertPriceType [id=" + id + ", alertId=" + alertId
				+ ", measureId=" + measureId + ", priceType=" + priceType + "]";
	}
	
}