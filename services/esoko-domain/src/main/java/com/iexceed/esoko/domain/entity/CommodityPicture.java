package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Date;

/**
 * The persistent class for the commodity_pictures database table.
 * 
 */
@Entity
@Table(name = "commodity_pictures")
@NamedQuery(name = "CommodityPicture.findAll", query = "SELECT c FROM CommodityPicture c")
public class CommodityPicture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pic_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int picId;

	@Column(name = "commodity_id")
	private String commodityId;

	@Lob
	private byte[] content;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	public CommodityPicture() {
	}

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
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

	@Override
	public String toString() {
		return "CommodityPicture [picId=" + picId + ", commodityId="
				+ commodityId + ", content=" + Arrays.toString(content)
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result + Arrays.hashCode(content);
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + picId;
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
		CommodityPicture other = (CommodityPicture) obj;
		if (commodityId == null) {
			if (other.commodityId != null)
				return false;
		} else if (!commodityId.equals(other.commodityId))
			return false;
		if (!Arrays.equals(content, other.content))
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
		if (picId != other.picId)
			return false;
		return true;
	}

}