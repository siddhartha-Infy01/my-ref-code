package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the gateways database table.
 * 
 */
@Entity
@Table(name="gateways")
@NamedQuery(name="Gateway.findAll", query="SELECT g FROM Gateway g")
public class Gateway implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GatewayPK id;

	@Column(name="callback_url")
	private String callbackUrl;

	private String code;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="data_status")
	private String dataStatus;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String name;

	private String secret;

	private String smsc;

	@Column(name="status_password")
	private String statusPassword;

	@Column(name="status_url")
	private String statusUrl;

	@Column(name="submit_password")
	private String submitPassword;

	@Column(name="submit_url")
	private String submitUrl;

	@Column(name="submit_username")
	private String submitUsername;

	public Gateway() {
	}

	public GatewayPK getId() {
		return this.id;
	}

	public void setId(GatewayPK id) {
		this.id = id;
	}

	public String getCallbackUrl() {
		return this.callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
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

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSmsc() {
		return this.smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	public String getStatusPassword() {
		return this.statusPassword;
	}

	public void setStatusPassword(String statusPassword) {
		this.statusPassword = statusPassword;
	}

	public String getStatusUrl() {
		return this.statusUrl;
	}

	public void setStatusUrl(String statusUrl) {
		this.statusUrl = statusUrl;
	}

	public String getSubmitPassword() {
		return this.submitPassword;
	}

	public void setSubmitPassword(String submitPassword) {
		this.submitPassword = submitPassword;
	}

	public String getSubmitUrl() {
		return this.submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public String getSubmitUsername() {
		return this.submitUsername;
	}

	public void setSubmitUsername(String submitUsername) {
		this.submitUsername = submitUsername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((callbackUrl == null) ? 0 : callbackUrl.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((dataStatus == null) ? 0 : dataStatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		result = prime * result + ((smsc == null) ? 0 : smsc.hashCode());
		result = prime * result
				+ ((statusPassword == null) ? 0 : statusPassword.hashCode());
		result = prime * result
				+ ((statusUrl == null) ? 0 : statusUrl.hashCode());
		result = prime * result
				+ ((submitPassword == null) ? 0 : submitPassword.hashCode());
		result = prime * result
				+ ((submitUrl == null) ? 0 : submitUrl.hashCode());
		result = prime * result
				+ ((submitUsername == null) ? 0 : submitUsername.hashCode());
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
		Gateway other = (Gateway) obj;
		if (callbackUrl == null) {
			if (other.callbackUrl != null)
				return false;
		} else if (!callbackUrl.equals(other.callbackUrl))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		if (dataStatus == null) {
			if (other.dataStatus != null)
				return false;
		} else if (!dataStatus.equals(other.dataStatus))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		if (smsc == null) {
			if (other.smsc != null)
				return false;
		} else if (!smsc.equals(other.smsc))
			return false;
		if (statusPassword == null) {
			if (other.statusPassword != null)
				return false;
		} else if (!statusPassword.equals(other.statusPassword))
			return false;
		if (statusUrl == null) {
			if (other.statusUrl != null)
				return false;
		} else if (!statusUrl.equals(other.statusUrl))
			return false;
		if (submitPassword == null) {
			if (other.submitPassword != null)
				return false;
		} else if (!submitPassword.equals(other.submitPassword))
			return false;
		if (submitUrl == null) {
			if (other.submitUrl != null)
				return false;
		} else if (!submitUrl.equals(other.submitUrl))
			return false;
		if (submitUsername == null) {
			if (other.submitUsername != null)
				return false;
		} else if (!submitUsername.equals(other.submitUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gateway [id=" + id + ", callbackUrl=" + callbackUrl + ", code="
				+ code + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", dataStatus=" + dataStatus + ", modifiedBy="
				+ modifiedBy + ", modifiedTs=" + modifiedTs + ", name=" + name
				+ ", secret=" + secret + ", smsc=" + smsc + ", statusPassword="
				+ statusPassword + ", statusUrl=" + statusUrl
				+ ", submitPassword=" + submitPassword + ", submitUrl="
				+ submitUrl + ", submitUsername=" + submitUsername + "]";
	}
	
	

}