package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user_widget_settings database table.
 * 
 */
@Entity
@Table(name="user_widget_settings")
@NamedQuery(name="UserWidgetSetting.findAll", query="SELECT u FROM UserWidgetSetting u")
public class UserWidgetSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserWidgetSettingPK id;

	@Column(name="bids_offer")
	private String bidsOffer;

	@Column(name="commodity_list")
	private String commodityList;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String currency;

	@Column(name="date_range")
	private String dateRange;

	@Column(name="location_list")
	private String locationList;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="network_list")
	private String networkList;

	@Column(name="network_public")
	private String networkPublic;

	@Column(name="price_type")
	private String priceType;

	public UserWidgetSetting() {
	}

	public UserWidgetSettingPK getId() {
		return this.id;
	}

	public void setId(UserWidgetSettingPK id) {
		this.id = id;
	}

	public String getBidsOffer() {
		return this.bidsOffer;
	}

	public void setBidsOffer(String bidsOffer) {
		this.bidsOffer = bidsOffer;
	}

	public String getCommodityList() {
		return this.commodityList;
	}

	public void setCommodityList(String commodityList) {
		this.commodityList = commodityList;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateRange() {
		return this.dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getLocationList() {
		return this.locationList;
	}

	public void setLocationList(String locationList) {
		this.locationList = locationList;
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

	public String getNetworkList() {
		return this.networkList;
	}

	public void setNetworkList(String networkList) {
		this.networkList = networkList;
	}

	public String getNetworkPublic() {
		return this.networkPublic;
	}

	public void setNetworkPublic(String networkPublic) {
		this.networkPublic = networkPublic;
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
		result = prime * result
				+ ((bidsOffer == null) ? 0 : bidsOffer.hashCode());
		result = prime * result
				+ ((commodityList == null) ? 0 : commodityList.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result
				+ ((dateRange == null) ? 0 : dateRange.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((locationList == null) ? 0 : locationList.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkList == null) ? 0 : networkList.hashCode());
		result = prime * result
				+ ((networkPublic == null) ? 0 : networkPublic.hashCode());
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
		UserWidgetSetting other = (UserWidgetSetting) obj;
		if (bidsOffer == null) {
			if (other.bidsOffer != null)
				return false;
		} else if (!bidsOffer.equals(other.bidsOffer))
			return false;
		if (commodityList == null) {
			if (other.commodityList != null)
				return false;
		} else if (!commodityList.equals(other.commodityList))
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
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (dateRange == null) {
			if (other.dateRange != null)
				return false;
		} else if (!dateRange.equals(other.dateRange))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locationList == null) {
			if (other.locationList != null)
				return false;
		} else if (!locationList.equals(other.locationList))
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
		if (networkList == null) {
			if (other.networkList != null)
				return false;
		} else if (!networkList.equals(other.networkList))
			return false;
		if (networkPublic == null) {
			if (other.networkPublic != null)
				return false;
		} else if (!networkPublic.equals(other.networkPublic))
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
		return "UserWidgetSetting [id=" + id + ", bidsOffer=" + bidsOffer
				+ ", commodityList=" + commodityList + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", currency="
				+ currency + ", dateRange=" + dateRange + ", locationList="
				+ locationList + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", networkList=" + networkList
				+ ", networkPublic=" + networkPublic + ", priceType="
				+ priceType + "]";
	}

}