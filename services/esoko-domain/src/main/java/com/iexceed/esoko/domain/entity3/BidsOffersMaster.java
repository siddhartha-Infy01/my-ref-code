package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the bids_offers_master database table.
 * 
 */
@Entity
@Table(name = "bids_offers_master")
@NamedQuery(name = "BidsOffersMaster.findAll", query = "SELECT b FROM BidsOffersMaster b")
public class BidsOffersMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "upload_id")
	private String upload_ID;

	@Column(name = "amount_unit")
	private String amountUnit;

	@Column(name = "auth_by")
	private String authBy;

	@Column(name = "auth_stat")
	private String authStat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auth_ts")
	private Date authTs;

	@Column(name = "bid_offer_flag")
	private String bidOfferFlag;

	private String commodity;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	@Column(name = "currency_id")
	private String currencyId;

	@Column(name = "delivery_point")
	private String deliveryPoint;

	@Column(name = "delivery_type")
	private String deliveryType;

	@Lob
	private String documents;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_date")
	private Date expiryDate;

	private String grade;

	private String measure;

	@Column(name = "negotiable_flag")
	private String negotiableFlag;

	@Column(name = "network_id")
	private String networkId;

	@Column(name = "no_of_days")
	private Integer noOfDays;

	@Lob
	private String notes;

	@Column(name = "offer_owner")
	private String offerOwner;

	@Column(name = "offer_uploaded_by")
	private String offerUploadedBy;

	@Column(name = "offer_userid")
	private String offerUserid;

	private String origin;

	@Column(name = "payement_mode")
	private String payementMode;

	@Column(name = "price_amount")
	private BigDecimal priceAmount;

	private BigDecimal quantity;

	private String type;

	@Column(name = "upload_mode")
	private String uploadMode;

	private String variety;

	private String location;

	public BidsOffersMaster() {
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUpload_ID() {
		return this.upload_ID;
	}

	public void setUpload_ID(String upload_ID) {
		this.upload_ID = upload_ID;
	}

	public String getAmountUnit() {
		return this.amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getAuthBy() {
		return this.authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public String getAuthStat() {
		return this.authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public Date getAuthTs() {
		return this.authTs;
	}

	public void setAuthTs(Date authTs) {
		this.authTs = authTs;
	}

	public String getBidOfferFlag() {
		return this.bidOfferFlag;
	}

	public void setBidOfferFlag(String bidOfferFlag) {
		this.bidOfferFlag = bidOfferFlag;
	}

	public String getCommodity() {
		return this.commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
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

	public String getDeliveryPoint() {
		return this.deliveryPoint;
	}

	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDocuments() {
		return this.documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getNegotiableFlag() {
		return this.negotiableFlag;
	}

	public void setNegotiableFlag(String negotiableFlag) {
		this.negotiableFlag = negotiableFlag;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public Integer getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOfferOwner() {
		return this.offerOwner;
	}

	public void setOfferOwner(String offerOwner) {
		this.offerOwner = offerOwner;
	}

	public String getOfferUploadedBy() {
		return this.offerUploadedBy;
	}

	public void setOfferUploadedBy(String offerUploadedBy) {
		this.offerUploadedBy = offerUploadedBy;
	}

	public String getOfferUserid() {
		return this.offerUserid;
	}

	public void setOfferUserid(String offerUserid) {
		this.offerUserid = offerUserid;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPayementMode() {
		return this.payementMode;
	}

	public void setPayementMode(String payementMode) {
		this.payementMode = payementMode;
	}

	public BigDecimal getPriceAmount() {
		return this.priceAmount;
	}

	public void setPriceAmount(BigDecimal priceAmount) {
		this.priceAmount = priceAmount;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploadMode() {
		return this.uploadMode;
	}

	public void setUploadMode(String uploadMode) {
		this.uploadMode = uploadMode;
	}

	public String getVariety() {
		return this.variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "BidsOffersMaster [upload_ID=" + upload_ID + ", amountUnit="
				+ amountUnit + ", authBy=" + authBy + ", authStat=" + authStat
				+ ", authTs=" + authTs + ", bidOfferFlag=" + bidOfferFlag
				+ ", commodity=" + commodity + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", currencyId=" + currencyId
				+ ", deliveryPoint=" + deliveryPoint + ", deliveryType="
				+ deliveryType + ", documents=" + documents + ", expiryDate="
				+ expiryDate + ", grade=" + grade + ", measure=" + measure
				+ ", negotiableFlag=" + negotiableFlag + ", networkId="
				+ networkId + ", noOfDays=" + noOfDays + ", notes=" + notes
				+ ", offerOwner=" + offerOwner + ", offerUploadedBy="
				+ offerUploadedBy + ", offerUserid=" + offerUserid
				+ ", origin=" + origin + ", payementMode=" + payementMode
				+ ", priceAmount=" + priceAmount + ", quantity=" + quantity
				+ ", type=" + type + ", uploadMode=" + uploadMode
				+ ", variety=" + variety + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((amountUnit == null) ? 0 : amountUnit.hashCode());
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + ((authTs == null) ? 0 : authTs.hashCode());
		result = prime * result
				+ ((bidOfferFlag == null) ? 0 : bidOfferFlag.hashCode());
		result = prime * result
				+ ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result
				+ ((deliveryPoint == null) ? 0 : deliveryPoint.hashCode());
		result = prime * result
				+ ((deliveryType == null) ? 0 : deliveryType.hashCode());
		result = prime * result
				+ ((documents == null) ? 0 : documents.hashCode());
		result = prime * result
				+ ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((measure == null) ? 0 : measure.hashCode());
		result = prime * result
				+ ((negotiableFlag == null) ? 0 : negotiableFlag.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + noOfDays;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((offerOwner == null) ? 0 : offerOwner.hashCode());
		result = prime * result
				+ ((offerUploadedBy == null) ? 0 : offerUploadedBy.hashCode());
		result = prime * result
				+ ((offerUserid == null) ? 0 : offerUserid.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((payementMode == null) ? 0 : payementMode.hashCode());
		result = prime * result
				+ ((priceAmount == null) ? 0 : priceAmount.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((uploadMode == null) ? 0 : uploadMode.hashCode());
		result = prime * result
				+ ((upload_ID == null) ? 0 : upload_ID.hashCode());
		result = prime * result + ((variety == null) ? 0 : variety.hashCode());
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
		BidsOffersMaster other = (BidsOffersMaster) obj;
		if (amountUnit == null) {
			if (other.amountUnit != null)
				return false;
		} else if (!amountUnit.equals(other.amountUnit))
			return false;
		if (authBy == null) {
			if (other.authBy != null)
				return false;
		} else if (!authBy.equals(other.authBy))
			return false;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
		if (authTs == null) {
			if (other.authTs != null)
				return false;
		} else if (!authTs.equals(other.authTs))
			return false;
		if (bidOfferFlag == null) {
			if (other.bidOfferFlag != null)
				return false;
		} else if (!bidOfferFlag.equals(other.bidOfferFlag))
			return false;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
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
		if (deliveryPoint == null) {
			if (other.deliveryPoint != null)
				return false;
		} else if (!deliveryPoint.equals(other.deliveryPoint))
			return false;
		if (deliveryType == null) {
			if (other.deliveryType != null)
				return false;
		} else if (!deliveryType.equals(other.deliveryType))
			return false;
		if (documents == null) {
			if (other.documents != null)
				return false;
		} else if (!documents.equals(other.documents))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (measure == null) {
			if (other.measure != null)
				return false;
		} else if (!measure.equals(other.measure))
			return false;
		if (negotiableFlag == null) {
			if (other.negotiableFlag != null)
				return false;
		} else if (!negotiableFlag.equals(other.negotiableFlag))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (noOfDays != other.noOfDays)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (offerOwner == null) {
			if (other.offerOwner != null)
				return false;
		} else if (!offerOwner.equals(other.offerOwner))
			return false;
		if (offerUploadedBy == null) {
			if (other.offerUploadedBy != null)
				return false;
		} else if (!offerUploadedBy.equals(other.offerUploadedBy))
			return false;
		if (offerUserid == null) {
			if (other.offerUserid != null)
				return false;
		} else if (!offerUserid.equals(other.offerUserid))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (payementMode == null) {
			if (other.payementMode != null)
				return false;
		} else if (!payementMode.equals(other.payementMode))
			return false;
		if (priceAmount == null) {
			if (other.priceAmount != null)
				return false;
		} else if (!priceAmount.equals(other.priceAmount))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uploadMode == null) {
			if (other.uploadMode != null)
				return false;
		} else if (!uploadMode.equals(other.uploadMode))
			return false;
		if (upload_ID == null) {
			if (other.upload_ID != null)
				return false;
		} else if (!upload_ID.equals(other.upload_ID))
			return false;
		if (variety == null) {
			if (other.variety != null)
				return false;
		} else if (!variety.equals(other.variety))
			return false;
		return true;
	}

}