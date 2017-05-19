package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the crop_tip_details database table.
 * 
 */
@Entity
@Table(name="crop_tip_details")
@NamedQuery(name="CropTipDetail.findAll", query="SELECT c FROM CropTipDetail c")
public class CropTipDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CropTipDetailPK id;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="interval_measure")
	private String intervalMeasure;

	@Column(name="interval_period")
	private int intervalPeriod;

	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="no_of_characters")
	private int noOfCharacters;

	@Column(name="no_of_messages")
	private int noOfMessages;

	@Lob
	private String text;

	@Temporal(TemporalType.DATE)
	@Column(name="transmission_date")
	private Date transmissionDate;

	@Column(name="transmission_time")
	private Time transmissionTime;

	public CropTipDetail() {
	}

	public CropTipDetailPK getId() {
		return this.id;
	}

	public void setId(CropTipDetailPK id) {
		this.id = id;
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

	public String getIntervalMeasure() {
		return this.intervalMeasure;
	}

	public void setIntervalMeasure(String intervalMeasure) {
		this.intervalMeasure = intervalMeasure;
	}

	public int getIntervalPeriod() {
		return this.intervalPeriod;
	}

	public void setIntervalPeriod(int intervalPeriod) {
		this.intervalPeriod = intervalPeriod;
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

	public int getNoOfCharacters() {
		return this.noOfCharacters;
	}

	public void setNoOfCharacters(int noOfCharacters) {
		this.noOfCharacters = noOfCharacters;
	}

	public int getNoOfMessages() {
		return this.noOfMessages;
	}

	public void setNoOfMessages(int noOfMessages) {
		this.noOfMessages = noOfMessages;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTransmissionDate() {
		return this.transmissionDate;
	}

	public void setTransmissionDate(Date transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	public Time getTransmissionTime() {
		return this.transmissionTime;
	}

	public void setTransmissionTime(Time transmissionTime) {
		this.transmissionTime = transmissionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intervalMeasure == null) ? 0 : intervalMeasure.hashCode());
		result = prime * result + intervalPeriod;
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + noOfCharacters;
		result = prime * result + noOfMessages;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime
				* result
				+ ((transmissionDate == null) ? 0 : transmissionDate.hashCode());
		result = prime
				* result
				+ ((transmissionTime == null) ? 0 : transmissionTime.hashCode());
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
		CropTipDetail other = (CropTipDetail) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intervalMeasure == null) {
			if (other.intervalMeasure != null)
				return false;
		} else if (!intervalMeasure.equals(other.intervalMeasure))
			return false;
		if (intervalPeriod != other.intervalPeriod)
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
		if (noOfCharacters != other.noOfCharacters)
			return false;
		if (noOfMessages != other.noOfMessages)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (transmissionDate == null) {
			if (other.transmissionDate != null)
				return false;
		} else if (!transmissionDate.equals(other.transmissionDate))
			return false;
		if (transmissionTime == null) {
			if (other.transmissionTime != null)
				return false;
		} else if (!transmissionTime.equals(other.transmissionTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CropTipDetail [id=" + id + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", intervalMeasure="
				+ intervalMeasure + ", intervalPeriod=" + intervalPeriod
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", noOfCharacters=" + noOfCharacters + ", noOfMessages="
				+ noOfMessages + ", text=" + text + ", transmissionDate="
				+ transmissionDate + ", transmissionTime=" + transmissionTime
				+ "]";
	}
	
	
}