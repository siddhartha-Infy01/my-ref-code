package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.vividsolutions.jts.geom.Point;

import java.util.Date;


/**
 * The persistent class for the Networks database table.
 * 
 */
@Entity
@Table(name="Networks")
@NamedQuery(name="Network.findAll", query="SELECT n FROM Network n")
public class Network implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="network_id")
	private String networkId;

	@Column(name="auth_by")
	private String authBy;

	@Column(name="auth_stat")
	private String authStat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auth_ts")
	private Date authTs;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String description;

	private String email;

	@Column(name="is_private")
	private String isPrivate;

	@Column(name="is_sms_whitelisted")
	private String isSmsWhitelisted;

	@Column(name="is_visible")
	private String isVisible;

	@Column(name="logo_id")
	private String logoId;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String name;

	@Column(name="nw_gis")
	private Point nwGis;

	@Column(name="owner_user_id")
	private String ownerUserId;

	@Column(name="parent_id")
	private String parentId;

	@Column(name="primary_location_id")
	private String primaryLocationId;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="short_name")
	private String shortName;

	private String type;

	private String website;

	public Network() {
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getIsSmsWhitelisted() {
		return this.isSmsWhitelisted;
	}

	public void setIsSmsWhitelisted(String isSmsWhitelisted) {
		this.isSmsWhitelisted = isSmsWhitelisted;
	}

	public String getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	public String getLogoId() {
		return this.logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getNwGis() {
		return this.nwGis;
	}

	public void setNwGis(Point nwGis) {
		this.nwGis = nwGis;
	}

	public String getOwnerUserId() {
		return this.ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPrimaryLocationId() {
		return this.primaryLocationId;
	}

	public void setPrimaryLocationId(String primaryLocationId) {
		this.primaryLocationId = primaryLocationId;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + ((authTs == null) ? 0 : authTs.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((isPrivate == null) ? 0 : isPrivate.hashCode());
		result = prime
				* result
				+ ((isSmsWhitelisted == null) ? 0 : isSmsWhitelisted.hashCode());
		result = prime * result
				+ ((isVisible == null) ? 0 : isVisible.hashCode());
		result = prime * result + ((logoId == null) ? 0 : logoId.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + ((nwGis == null) ? 0 : nwGis.hashCode());
		result = prime * result
				+ ((ownerUserId == null) ? 0 : ownerUserId.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime
				* result
				+ ((primaryLocationId == null) ? 0 : primaryLocationId
						.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Network other = (Network) obj;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (isPrivate == null) {
			if (other.isPrivate != null)
				return false;
		} else if (!isPrivate.equals(other.isPrivate))
			return false;
		if (isSmsWhitelisted == null) {
			if (other.isSmsWhitelisted != null)
				return false;
		} else if (!isSmsWhitelisted.equals(other.isSmsWhitelisted))
			return false;
		if (isVisible == null) {
			if (other.isVisible != null)
				return false;
		} else if (!isVisible.equals(other.isVisible))
			return false;
		if (logoId == null) {
			if (other.logoId != null)
				return false;
		} else if (!logoId.equals(other.logoId))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (nwGis == null) {
			if (other.nwGis != null)
				return false;
		} else if (!nwGis.equals(other.nwGis))
			return false;
		if (ownerUserId == null) {
			if (other.ownerUserId != null)
				return false;
		} else if (!ownerUserId.equals(other.ownerUserId))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (primaryLocationId == null) {
			if (other.primaryLocationId != null)
				return false;
		} else if (!primaryLocationId.equals(other.primaryLocationId))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Network [networkId=" + networkId + ", authBy=" + authBy
				+ ", authStat=" + authStat + ", authTs=" + authTs
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", description=" + description + ", email=" + email
				+ ", isPrivate=" + isPrivate + ", isSmsWhitelisted="
				+ isSmsWhitelisted + ", isVisible=" + isVisible + ", logoId="
				+ logoId + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", name=" + name + ", nwGis=" + nwGis
				+ ", ownerUserId=" + ownerUserId + ", parentId=" + parentId
				+ ", primaryLocationId=" + primaryLocationId
				+ ", recordStatus=" + recordStatus + ", shortName=" + shortName
				+ ", type=" + type + ", website=" + website + "]";
	}
	
	

}