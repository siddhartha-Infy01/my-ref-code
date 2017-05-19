package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crop_tip_master database table.
 * 
 */
@Entity
@Table(name="crop_tip_master")
@NamedQuery(name="CropTipMaster.findAll", query="SELECT c FROM CropTipMaster c")
public class CropTipMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="crop_tip_id")
	private String cropTipId;

	@Column(name="alert_code")
	private String alertCode;

	@Column(name="alert_state")
	private String alertState;

	private String category;

	@Column(name="commodity_id")
	private String commodityId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="crop_tip_name")
	private String cropTipName;

	@Column(name="currency_id")
	private String currencyId;

	@Column(name="custom_category")
	private String customCategory;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="network_id")
	private String networkId;

	@Column(name="number_of_tips")
	private int numberOfTips;

	@Column(name="payee_account")
	private String payeeAccount;

	@Column(name="payee_type")
	private String payeeType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="trigger_type")
	private String triggerType;

	public CropTipMaster() {
	}

	public String getCropTipId() {
		return this.cropTipId;
	}

	public void setCropTipId(String cropTipId) {
		this.cropTipId = cropTipId;
	}

	public String getAlertCode() {
		return this.alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertState() {
		return this.alertState;
	}

	public void setAlertState(String alertState) {
		this.alertState = alertState;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
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

	public String getCropTipName() {
		return this.cropTipName;
	}

	public void setCropTipName(String cropTipName) {
		this.cropTipName = cropTipName;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCustomCategory() {
		return this.customCategory;
	}

	public void setCustomCategory(String customCategory) {
		this.customCategory = customCategory;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public int getNumberOfTips() {
		return this.numberOfTips;
	}

	public void setNumberOfTips(int numberOfTips) {
		this.numberOfTips = numberOfTips;
	}

	public String getPayeeAccount() {
		return this.payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeType() {
		return this.payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alertCode == null) ? 0 : alertCode.hashCode());
		result = prime * result
				+ ((alertState == null) ? 0 : alertState.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((cropTipId == null) ? 0 : cropTipId.hashCode());
		result = prime * result
				+ ((cropTipName == null) ? 0 : cropTipName.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result
				+ ((customCategory == null) ? 0 : customCategory.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + numberOfTips;
		result = prime * result
				+ ((payeeAccount == null) ? 0 : payeeAccount.hashCode());
		result = prime * result
				+ ((payeeType == null) ? 0 : payeeType.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((triggerType == null) ? 0 : triggerType.hashCode());
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
		CropTipMaster other = (CropTipMaster) obj;
		if (alertCode == null) {
			if (other.alertCode != null)
				return false;
		} else if (!alertCode.equals(other.alertCode))
			return false;
		if (alertState == null) {
			if (other.alertState != null)
				return false;
		} else if (!alertState.equals(other.alertState))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
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
		if (cropTipId == null) {
			if (other.cropTipId != null)
				return false;
		} else if (!cropTipId.equals(other.cropTipId))
			return false;
		if (cropTipName == null) {
			if (other.cropTipName != null)
				return false;
		} else if (!cropTipName.equals(other.cropTipName))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (customCategory == null) {
			if (other.customCategory != null)
				return false;
		} else if (!customCategory.equals(other.customCategory))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
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
		if (numberOfTips != other.numberOfTips)
			return false;
		if (payeeAccount == null) {
			if (other.payeeAccount != null)
				return false;
		} else if (!payeeAccount.equals(other.payeeAccount))
			return false;
		if (payeeType == null) {
			if (other.payeeType != null)
				return false;
		} else if (!payeeType.equals(other.payeeType))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (triggerType == null) {
			if (other.triggerType != null)
				return false;
		} else if (!triggerType.equals(other.triggerType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CropTipMaster [cropTipId=" + cropTipId + ", alertCode="
				+ alertCode + ", alertState=" + alertState + ", category="
				+ category + ", commodityId=" + commodityId + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", cropTipName="
				+ cropTipName + ", currencyId=" + currencyId
				+ ", customCategory=" + customCategory + ", endDate=" + endDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", networkId=" + networkId + ", numberOfTips=" + numberOfTips
				+ ", payeeAccount=" + payeeAccount + ", payeeType=" + payeeType
				+ ", startDate=" + startDate + ", triggerType=" + triggerType
				+ "]";
	}		

}