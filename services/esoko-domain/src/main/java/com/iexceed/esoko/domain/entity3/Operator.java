package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the operators database table.
 * 
 */
@Entity
@Table(name="operators")
@NamedQuery(name="Operator.findAll", query="SELECT o FROM Operator o")
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="operator_id")
	private String operatorId;

	@Column(name="airtime_support")
	private String airtimeSupport;

	@Column(name="business_name")
	private String businessName;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="first_pref_gateway")
	private String firstPrefGateway;

	@Column(name="location_id")
	private String locationId;

	private String mcc;

	private String mnc;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String name;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="second_pref_gateway")
	private String secondPrefGateway;

	@Column(name="third_pref_gateway")
	private String thirdPrefGateway;

	public Operator() {
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getAirtimeSupport() {
		return this.airtimeSupport;
	}

	public void setAirtimeSupport(String airtimeSupport) {
		this.airtimeSupport = airtimeSupport;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public String getFirstPrefGateway() {
		return this.firstPrefGateway;
	}

	public void setFirstPrefGateway(String firstPrefGateway) {
		this.firstPrefGateway = firstPrefGateway;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return this.mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
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

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getSecondPrefGateway() {
		return this.secondPrefGateway;
	}

	public void setSecondPrefGateway(String secondPrefGateway) {
		this.secondPrefGateway = secondPrefGateway;
	}

	public String getThirdPrefGateway() {
		return this.thirdPrefGateway;
	}

	public void setThirdPrefGateway(String thirdPrefGateway) {
		this.thirdPrefGateway = thirdPrefGateway;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airtimeSupport == null) ? 0 : airtimeSupport.hashCode());
		result = prime * result
				+ ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime
				* result
				+ ((firstPrefGateway == null) ? 0 : firstPrefGateway.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime
				* result
				+ ((secondPrefGateway == null) ? 0 : secondPrefGateway
						.hashCode());
		result = prime
				* result
				+ ((thirdPrefGateway == null) ? 0 : thirdPrefGateway.hashCode());
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
		Operator other = (Operator) obj;
		if (airtimeSupport == null) {
			if (other.airtimeSupport != null)
				return false;
		} else if (!airtimeSupport.equals(other.airtimeSupport))
			return false;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
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
		if (firstPrefGateway == null) {
			if (other.firstPrefGateway != null)
				return false;
		} else if (!firstPrefGateway.equals(other.firstPrefGateway))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc))
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
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (secondPrefGateway == null) {
			if (other.secondPrefGateway != null)
				return false;
		} else if (!secondPrefGateway.equals(other.secondPrefGateway))
			return false;
		if (thirdPrefGateway == null) {
			if (other.thirdPrefGateway != null)
				return false;
		} else if (!thirdPrefGateway.equals(other.thirdPrefGateway))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operator [operatorId=" + operatorId + ", airtimeSupport="
				+ airtimeSupport + ", businessName=" + businessName
				+ ", createdBy=" + createdBy + ", createdTs=" + createdTs
				+ ", firstPrefGateway=" + firstPrefGateway + ", locationId="
				+ locationId + ", mcc=" + mcc + ", mnc=" + mnc
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", name=" + name + ", recordStatus=" + recordStatus
				+ ", secondPrefGateway=" + secondPrefGateway
				+ ", thirdPrefGateway=" + thirdPrefGateway + "]";
	}
		
}