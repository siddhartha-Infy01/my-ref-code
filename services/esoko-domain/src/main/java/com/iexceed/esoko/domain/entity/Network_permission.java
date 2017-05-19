package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the Network_permissions database table.
 * 
 */
@Entity
@Table(name="Network_permissions")
@NamedQuery(name="Network_permission.findAll", query="SELECT n FROM Network_permission n")
public class Network_permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Network_permissionPK id;

	private String agents;

	private String alerts;

	private String approve;

	private String billing;

	private String configurations;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String inbox;

	private String marketplace;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String people;

	private String permissions;

	private String push;

	private String reports;

	private String sharing;

	private String smspolls;

	private String upload;

	public Network_permission() {
	}

	public Network_permissionPK getId() {
		return this.id;
	}

	public void setId(Network_permissionPK id) {
		this.id = id;
	}

	public String getAgents() {
		return this.agents;
	}

	public void setAgents(String agents) {
		this.agents = agents;
	}

	public String getAlerts() {
		return this.alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
	}

	public String getApprove() {
		return this.approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getBilling() {
		return this.billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public String getConfigurations() {
		return this.configurations;
	}

	public void setConfigurations(String configurations) {
		this.configurations = configurations;
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

	public String getInbox() {
		return this.inbox;
	}

	public void setInbox(String inbox) {
		this.inbox = inbox;
	}

	public String getMarketplace() {
		return this.marketplace;
	}

	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
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

	public String getPeople() {
		return this.people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPush() {
		return this.push;
	}

	public void setPush(String push) {
		this.push = push;
	}

	public String getReports() {
		return this.reports;
	}

	public void setReports(String reports) {
		this.reports = reports;
	}

	public String getSharing() {
		return this.sharing;
	}

	public void setSharing(String sharing) {
		this.sharing = sharing;
	}

	public String getSmspolls() {
		return this.smspolls;
	}

	public void setSmspolls(String smspolls) {
		this.smspolls = smspolls;
	}

	public String getUpload() {
		return this.upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agents == null) ? 0 : agents.hashCode());
		result = prime * result + ((alerts == null) ? 0 : alerts.hashCode());
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result + ((billing == null) ? 0 : billing.hashCode());
		result = prime * result
				+ ((configurations == null) ? 0 : configurations.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inbox == null) ? 0 : inbox.hashCode());
		result = prime * result
				+ ((marketplace == null) ? 0 : marketplace.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((people == null) ? 0 : people.hashCode());
		result = prime * result
				+ ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + ((push == null) ? 0 : push.hashCode());
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
		result = prime * result + ((sharing == null) ? 0 : sharing.hashCode());
		result = prime * result
				+ ((smspolls == null) ? 0 : smspolls.hashCode());
		result = prime * result + ((upload == null) ? 0 : upload.hashCode());
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
		Network_permission other = (Network_permission) obj;
		if (agents == null) {
			if (other.agents != null)
				return false;
		} else if (!agents.equals(other.agents))
			return false;
		if (alerts == null) {
			if (other.alerts != null)
				return false;
		} else if (!alerts.equals(other.alerts))
			return false;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (billing == null) {
			if (other.billing != null)
				return false;
		} else if (!billing.equals(other.billing))
			return false;
		if (configurations == null) {
			if (other.configurations != null)
				return false;
		} else if (!configurations.equals(other.configurations))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inbox == null) {
			if (other.inbox != null)
				return false;
		} else if (!inbox.equals(other.inbox))
			return false;
		if (marketplace == null) {
			if (other.marketplace != null)
				return false;
		} else if (!marketplace.equals(other.marketplace))
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
		if (people == null) {
			if (other.people != null)
				return false;
		} else if (!people.equals(other.people))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		if (push == null) {
			if (other.push != null)
				return false;
		} else if (!push.equals(other.push))
			return false;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
			return false;
		if (sharing == null) {
			if (other.sharing != null)
				return false;
		} else if (!sharing.equals(other.sharing))
			return false;
		if (smspolls == null) {
			if (other.smspolls != null)
				return false;
		} else if (!smspolls.equals(other.smspolls))
			return false;
		if (upload == null) {
			if (other.upload != null)
				return false;
		} else if (!upload.equals(other.upload))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Network_permission [id=" + id + ", agents=" + agents
				+ ", alerts=" + alerts + ", approve=" + approve + ", billing="
				+ billing + ", configurations=" + configurations
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", inbox=" + inbox + ", marketplace=" + marketplace
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", people=" + people + ", permissions=" + permissions
				+ ", push=" + push + ", reports=" + reports + ", sharing="
				+ sharing + ", smspolls=" + smspolls + ", upload=" + upload
				+ "]";
	}
	
	
}