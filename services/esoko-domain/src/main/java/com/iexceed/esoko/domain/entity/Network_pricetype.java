package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the Network_pricetypes database table.
 * 
 */
@Entity
@Table(name="Network_pricetypes")
@NamedQuery(name="Network_pricetype.findAll", query="SELECT n FROM Network_pricetype n")
public class Network_pricetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="network_id")
	private String networkId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="farm_gate")
	private String farmGate;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="off_lorry")
	private String offLorry;

	private String producer;

	private String retail;

	private String wholesale;

	public Network_pricetype() {
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
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

	public String getFarmGate() {
		return this.farmGate;
	}

	public void setFarmGate(String farmGate) {
		this.farmGate = farmGate;
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

	public String getOffLorry() {
		return this.offLorry;
	}

	public void setOffLorry(String offLorry) {
		this.offLorry = offLorry;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getRetail() {
		return this.retail;
	}

	public void setRetail(String retail) {
		this.retail = retail;
	}

	public String getWholesale() {
		return this.wholesale;
	}

	public void setWholesale(String wholesale) {
		this.wholesale = wholesale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((farmGate == null) ? 0 : farmGate.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((offLorry == null) ? 0 : offLorry.hashCode());
		result = prime * result
				+ ((producer == null) ? 0 : producer.hashCode());
		result = prime * result + ((retail == null) ? 0 : retail.hashCode());
		result = prime * result
				+ ((wholesale == null) ? 0 : wholesale.hashCode());
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
		Network_pricetype other = (Network_pricetype) obj;
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
		if (farmGate == null) {
			if (other.farmGate != null)
				return false;
		} else if (!farmGate.equals(other.farmGate))
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
		if (offLorry == null) {
			if (other.offLorry != null)
				return false;
		} else if (!offLorry.equals(other.offLorry))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		if (retail == null) {
			if (other.retail != null)
				return false;
		} else if (!retail.equals(other.retail))
			return false;
		if (wholesale == null) {
			if (other.wholesale != null)
				return false;
		} else if (!wholesale.equals(other.wholesale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Network_pricetype [networkId=" + networkId + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", farmGate="
				+ farmGate + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", offLorry=" + offLorry + ", producer="
				+ producer + ", retail=" + retail + ", wholesale=" + wholesale
				+ "]";
	}

}