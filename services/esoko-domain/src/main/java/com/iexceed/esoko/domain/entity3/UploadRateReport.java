package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the upload_rate_report database table.
 * 
 */
@Entity
@Table(name="upload_rate_report")
@NamedQuery(name="UploadRateReport.findAll", query="SELECT u FROM UploadRateReport u")
public class UploadRateReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="report_id")
	private String reportId;

	private String channel;

	private BigDecimal cost;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	private String direction;

	@Column(name="location_id")
	private String locationId;

	private String mcc;

	private String mnc;

	@Column(name="operator_name")
	private String operatorName;

	private String smpp;

	public UploadRateReport() {
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
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

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return this.mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getSmpp() {
		return this.smpp;
	}

	public void setSmpp(String smpp) {
		this.smpp = smpp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		result = prime * result
				+ ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime * result
				+ ((reportId == null) ? 0 : reportId.hashCode());
		result = prime * result + ((smpp == null) ? 0 : smpp.hashCode());
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
		UploadRateReport other = (UploadRateReport) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
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
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc))
			return false;
		if (operatorName == null) {
			if (other.operatorName != null)
				return false;
		} else if (!operatorName.equals(other.operatorName))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		if (smpp == null) {
			if (other.smpp != null)
				return false;
		} else if (!smpp.equals(other.smpp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UploadRateReport [reportId=" + reportId + ", channel="
				+ channel + ", cost=" + cost + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", currencyId=" + currencyId
				+ ", direction=" + direction + ", locationId=" + locationId
				+ ", mcc=" + mcc + ", mnc=" + mnc + ", operatorName="
				+ operatorName + ", smpp=" + smpp + "]";
	}
		
}