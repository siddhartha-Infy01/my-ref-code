package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the agent_details_history database table.
 * 
 */
@Entity
@Table(name="agent_details_history")
@NamedQuery(name="AgentDetailsHistory.findAll", query="SELECT a FROM AgentDetailsHistory a")
public class AgentDetailsHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AgentDetailsHistoryPK id;

	@Column(name="application_id")
	private String applicationId;

	private BigDecimal bonus;

	@Column(name="commodity_id")
	private String commodityId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	private BigDecimal incentive;

	@Column(name="location_id")
	private String locationId;

	@Column(name="network_id")
	private String networkId;

	private int target;

	private String template;

	@Column(name="user_id")
	private String userId;

	public AgentDetailsHistory() {
	}

	public AgentDetailsHistoryPK getId() {
		return this.id;
	}

	public void setId(AgentDetailsHistoryPK id) {
		this.id = id;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public BigDecimal getBonus() {
		return this.bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
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

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public BigDecimal getIncentive() {
		return this.incentive;
	}

	public void setIncentive(BigDecimal incentive) {
		this.incentive = incentive;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public int getTarget() {
		return this.target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		result = prime * result
				+ ((commodityId == null) ? 0 : commodityId.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((incentive == null) ? 0 : incentive.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + target;
		result = prime * result
				+ ((template == null) ? 0 : template.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AgentDetailsHistory other = (AgentDetailsHistory) obj;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
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
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (incentive == null) {
			if (other.incentive != null)
				return false;
		} else if (!incentive.equals(other.incentive))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (target != other.target)
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentDetailsHistory [id=" + id + ", applicationId="
				+ applicationId + ", bonus=" + bonus + ", commodityId="
				+ commodityId + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", currencyId=" + currencyId + ", incentive="
				+ incentive + ", locationId=" + locationId + ", networkId="
				+ networkId + ", target=" + target + ", template=" + template
				+ ", userId=" + userId + "]";
	}
	
	
}