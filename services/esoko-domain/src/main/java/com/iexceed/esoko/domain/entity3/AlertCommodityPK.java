package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the alert_commodities database table.
 * 
 */
@Embeddable
public class AlertCommodityPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "push_alert_id")
	private String pushAlertId;

	@Column(name = "commodity_id")
	private String commodityId;

	public AlertCommodityPK() {
	}

	public String getPushAlertId() {
		return this.pushAlertId;
	}

	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertCommodityPK other = (AlertCommodityPK) obj;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (pushAlertId == null) {
			if (other.pushAlertId != null)
				return false;
		} else if (!pushAlertId.equals(other.pushAlertId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result
				+ ((pushAlertId == null) ? 0 : pushAlertId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AlertCommodityPK [pushAlertId=" + pushAlertId
				+ ", commodityId=" + commodityId + "]";
	}

}