package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the upload_master database table.
 * 
 */
@Entity
@Table(name = "upload_master")
@NamedQuery(name = "UploadMaster.findAll", query = "SELECT u FROM UploadMaster u")
public class UploadMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "upload_id")
	private String uploadId;

	@Column(name = "agent_id")
	private String agentId;

	@Column(name = "application_id")
	private String applicationId;

	@Column(name = "auth_by")
	private String authBy;

	@Column(name = "auth_stat")
	private String authStat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auth_ts")
	private Date authTs;

	@Column(name = "detail_id")
	private Integer detailId;

	@Column(name = "network_id")
	private String networkId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upload_dt")
	private Date uploadDt;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_ts")
	private Date modifiedTs;

	public UploadMaster() {
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public Date getUploadDt() {
		return this.uploadDt;
	}

	public void setUploadDt(Date uploadDt) {
		this.uploadDt = uploadDt;
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
		return "UploadMaster [uploadId=" + uploadId + ", agentId=" + agentId
				+ ", applicationId=" + applicationId + ", authBy=" + authBy
				+ ", authStat=" + authStat + ", authTs=" + authTs
				+ ", detailId=" + detailId + ", networkId=" + networkId
				+ ", uploadDt=" + uploadDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((authBy == null) ? 0 : authBy.hashCode());
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result + ((authTs == null) ? 0 : authTs.hashCode());
		result = prime * result + detailId;
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((uploadDt == null) ? 0 : uploadDt.hashCode());
		result = prime * result
				+ ((uploadId == null) ? 0 : uploadId.hashCode());
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
		UploadMaster other = (UploadMaster) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
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
		if (detailId != other.detailId)
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
		if (uploadDt == null) {
			if (other.uploadDt != null)
				return false;
		} else if (!uploadDt.equals(other.uploadDt))
			return false;
		if (uploadId == null) {
			if (other.uploadId != null)
				return false;
		} else if (!uploadId.equals(other.uploadId))
			return false;
		return true;
	}

}