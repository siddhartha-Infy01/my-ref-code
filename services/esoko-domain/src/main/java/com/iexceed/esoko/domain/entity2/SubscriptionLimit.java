package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the subscription_limits database table.
 * 
 */
@Entity
@Table(name="subscription_limits")
@NamedQuery(name="SubscriptionLimit.findAll", query="SELECT s FROM SubscriptionLimit s")
public class SubscriptionLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionLimitPK id;

	private int adminstrators;

	private int agents;

	private int alerts;

	@Column(name="API1_REQUEST")
	private int api1Request;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private int groups;

	private int inbox;

	private int members;

	@Column(name="message_traffic")
	private int messageTraffic;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private int networks;

	private int offers;

	private int prices;

	private int push;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="smart_groups")
	private int smartGroups;

	@Column(name="upload_forms")
	private int uploadForms;

	@Column(name="upload_traffic")
	private int uploadTraffic;

	public SubscriptionLimit() {
	}

	public SubscriptionLimitPK getId() {
		return this.id;
	}

	public void setId(SubscriptionLimitPK id) {
		this.id = id;
	}

	public int getAdminstrators() {
		return this.adminstrators;
	}

	public void setAdminstrators(int adminstrators) {
		this.adminstrators = adminstrators;
	}

	public int getAgents() {
		return this.agents;
	}

	public void setAgents(int agents) {
		this.agents = agents;
	}

	public int getAlerts() {
		return this.alerts;
	}

	public void setAlerts(int alerts) {
		this.alerts = alerts;
	}

	public int getApi1Request() {
		return this.api1Request;
	}

	public void setApi1Request(int api1Request) {
		this.api1Request = api1Request;
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

	public int getGroups() {
		return this.groups;
	}

	public void setGroups(int groups) {
		this.groups = groups;
	}

	public int getInbox() {
		return this.inbox;
	}

	public void setInbox(int inbox) {
		this.inbox = inbox;
	}

	public int getMembers() {
		return this.members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public int getMessageTraffic() {
		return this.messageTraffic;
	}

	public void setMessageTraffic(int messageTraffic) {
		this.messageTraffic = messageTraffic;
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

	public int getNetworks() {
		return this.networks;
	}

	public void setNetworks(int networks) {
		this.networks = networks;
	}

	public int getOffers() {
		return this.offers;
	}

	public void setOffers(int offers) {
		this.offers = offers;
	}

	public int getPrices() {
		return this.prices;
	}

	public void setPrices(int prices) {
		this.prices = prices;
	}

	public int getPush() {
		return this.push;
	}

	public void setPush(int push) {
		this.push = push;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public int getSmartGroups() {
		return this.smartGroups;
	}

	public void setSmartGroups(int smartGroups) {
		this.smartGroups = smartGroups;
	}

	public int getUploadForms() {
		return this.uploadForms;
	}

	public void setUploadForms(int uploadForms) {
		this.uploadForms = uploadForms;
	}

	public int getUploadTraffic() {
		return this.uploadTraffic;
	}

	public void setUploadTraffic(int uploadTraffic) {
		this.uploadTraffic = uploadTraffic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminstrators;
		result = prime * result + agents;
		result = prime * result + alerts;
		result = prime * result + api1Request;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + groups;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + inbox;
		result = prime * result + members;
		result = prime * result + messageTraffic;
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + networks;
		result = prime * result + offers;
		result = prime * result + prices;
		result = prime * result + push;
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result + smartGroups;
		result = prime * result + uploadForms;
		result = prime * result + uploadTraffic;
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
		SubscriptionLimit other = (SubscriptionLimit) obj;
		if (adminstrators != other.adminstrators)
			return false;
		if (agents != other.agents)
			return false;
		if (alerts != other.alerts)
			return false;
		if (api1Request != other.api1Request)
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
		if (groups != other.groups)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inbox != other.inbox)
			return false;
		if (members != other.members)
			return false;
		if (messageTraffic != other.messageTraffic)
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
		if (networks != other.networks)
			return false;
		if (offers != other.offers)
			return false;
		if (prices != other.prices)
			return false;
		if (push != other.push)
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (smartGroups != other.smartGroups)
			return false;
		if (uploadForms != other.uploadForms)
			return false;
		if (uploadTraffic != other.uploadTraffic)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionLimit [id=" + id + ", adminstrators="
				+ adminstrators + ", agents=" + agents + ", alerts=" + alerts
				+ ", api1Request=" + api1Request + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", groups=" + groups
				+ ", inbox=" + inbox + ", members=" + members
				+ ", messageTraffic=" + messageTraffic + ", modifiedBy="
				+ modifiedBy + ", modifiedTs=" + modifiedTs + ", networks="
				+ networks + ", offers=" + offers + ", prices=" + prices
				+ ", push=" + push + ", recordStatus=" + recordStatus
				+ ", smartGroups=" + smartGroups + ", uploadForms="
				+ uploadForms + ", uploadTraffic=" + uploadTraffic + "]";
	}

}