package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the sender_details database table.
 * 
 */
@Entity
@Table(name="sender_details")
@NamedQuery(name="SenderDetail.findAll", query="SELECT s FROM SenderDetail s")
public class SenderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SenderDetailPK id;

	@Column(name="auth_stat")
	private String authStat;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_TS;

	@Column(name="operator_id")
	private String operatorId;

	@Column(name="send_type")
	private String sendType;

	public SenderDetail() {
	}

	public SenderDetailPK getId() {
		return this.id;
	}

	public void setId(SenderDetailPK id) {
		this.id = id;
	}

	public String getAuthStat() {
		return this.authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated_TS() {
		return this.created_TS;
	}

	public void setCreated_TS(Date created_TS) {
		this.created_TS = created_TS;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getSendType() {
		return this.sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((created_TS == null) ? 0 : created_TS.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result
				+ ((sendType == null) ? 0 : sendType.hashCode());
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
		SenderDetail other = (SenderDetail) obj;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (created_TS == null) {
			if (other.created_TS != null)
				return false;
		} else if (!created_TS.equals(other.created_TS))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (sendType == null) {
			if (other.sendType != null)
				return false;
		} else if (!sendType.equals(other.sendType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SenderDetail [id=" + id + ", authStat=" + authStat
				+ ", createdBy=" + createdBy + ", created_TS=" + created_TS
				+ ", operatorId=" + operatorId + ", sendType=" + sendType + "]";
	}
	
	

}