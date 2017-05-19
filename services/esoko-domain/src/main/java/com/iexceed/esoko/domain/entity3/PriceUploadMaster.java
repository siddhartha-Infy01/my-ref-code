package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the price_upload_master database table.
 * 
 */
@Entity
@Table(name="price_upload_master")
@NamedQuery(name="PriceUploadMaster.findAll", query="SELECT p FROM PriceUploadMaster p")
public class PriceUploadMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String upload_ID;

	@Column(name="agent_id")
	private String agentId;

	@Column(name="auth_by")
	private String authBy;

	@Column(name="auth_stat")
	private String authStat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auth_ts")
	private Date authTs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="collected_on")
	private Date collectedOn;

	@Lob
	private String comments;

	private String commodity;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	private String market;

	@Column(name="measure_id")
	private String measureId;

	@Column(name="network_id")
	private String networkId;

	private BigDecimal price;

	@Column(name="price_type")
	private String priceType;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;
	
	@Column(name = "upload_gis",columnDefinition="Point")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point uploadGis;

	@Column(name="upload_mode")
	private String uploadMode;

	private String variety;

	@Lob
	@Column(name="variety_comment")
	private String varietyComment;

	private BigDecimal weight;

	@Column(name="weight_measure")
	private String weightMeasure;

	public PriceUploadMaster() {
	}

	public String getUpload_ID() {
		return this.upload_ID;
	}

	public void setUpload_ID(String upload_ID) {
		this.upload_ID = upload_ID;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public Date getCollectedOn() {
		return this.collectedOn;
	}

	public void setCollectedOn(Date collectedOn) {
		this.collectedOn = collectedOn;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getMarket() {
		return this.market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getMeasureId() {
		return this.measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPriceType() {
		return this.priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Point getUploadGis() {
		return this.uploadGis;
	}

	public void setUploadGis(Point uploadGis) {
		this.uploadGis = uploadGis;
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

	public String getVarietyComment() {
		return this.varietyComment;
	}

	public void setVarietyComment(String varietyComment) {
		this.varietyComment = varietyComment;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getWeightMeasure() {
		return this.weightMeasure;
	}

	public void setWeightMeasure(String weightMeasure) {
		this.weightMeasure = weightMeasure;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	@Override
	public String toString() {
		return "PriceUploadMaster [upload_ID=" + upload_ID + ", agentId="
				+ agentId + ", authBy=" + authBy + ", authStat=" + authStat
				+ ", authTs=" + authTs + ", collectedOn=" + collectedOn
				+ ", comments=" + comments + ", commodity=" + commodity
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", currencyId=" + currencyId + ", market=" + market
				+ ", measureId=" + measureId + ", networkId=" + networkId
				+ ", price=" + price + ", priceType=" + priceType
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", uploadGis=" + uploadGis + ", uploadMode=" + uploadMode
				+ ", variety=" + variety + ", varietyComment=" + varietyComment
				+ ", weight=" + weight + ", weightMeasure=" + weightMeasure
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + ((authTs == null) ? 0 : authTs.hashCode());
		result = prime * result
				+ ((collectedOn == null) ? 0 : collectedOn.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result
				+ ((measureId == null) ? 0 : measureId.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((priceType == null) ? 0 : priceType.hashCode());
		result = prime * result
				+ ((uploadGis == null) ? 0 : uploadGis.hashCode());
		result = prime * result
				+ ((uploadMode == null) ? 0 : uploadMode.hashCode());
		result = prime * result
				+ ((upload_ID == null) ? 0 : upload_ID.hashCode());
		result = prime * result + ((variety == null) ? 0 : variety.hashCode());
		result = prime * result
				+ ((varietyComment == null) ? 0 : varietyComment.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		result = prime * result
				+ ((weightMeasure == null) ? 0 : weightMeasure.hashCode());
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
		PriceUploadMaster other = (PriceUploadMaster) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
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
		if (collectedOn == null) {
			if (other.collectedOn != null)
				return false;
		} else if (!collectedOn.equals(other.collectedOn))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
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
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (measureId == null) {
			if (other.measureId != null)
				return false;
		} else if (!measureId.equals(other.measureId))
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceType == null) {
			if (other.priceType != null)
				return false;
		} else if (!priceType.equals(other.priceType))
			return false;
		if (uploadGis == null) {
			if (other.uploadGis != null)
				return false;
		} else if (!uploadGis.equals(other.uploadGis))
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
		if (varietyComment == null) {
			if (other.varietyComment != null)
				return false;
		} else if (!varietyComment.equals(other.varietyComment))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		if (weightMeasure == null) {
			if (other.weightMeasure != null)
				return false;
		} else if (!weightMeasure.equals(other.weightMeasure))
			return false;
		return true;
	}

	
}