package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reseller_master database table.
 * 
 */
@Entity
@Table(name="reseller_master")
@NamedQuery(name="ResellerMaster.findAll", query="SELECT r FROM ResellerMaster r")
public class ResellerMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reseller_id")
	private String resellerId;

	private String address;

	@Column(name="bank_details")
	private String bankDetails;

	private String bitcoin;

	private String company;

	private String countries;

	private String country;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String email;

	private String fixed;

	@Column(name="mobile_money")
	private String mobileMoney;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String msisdn1;

	private String msisdn2;

	@Column(name="network_id")
	private String networkId;

	@Column(name="owner_name")
	private String ownerName;

	private String paypal;

	@Column(name="record_status")
	private String recordStatus;

	private String town;

	private String type;

	private String website;

	public ResellerMaster() {
	}

	public String getResellerId() {
		return this.resellerId;
	}

	public void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankDetails() {
		return this.bankDetails;
	}

	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}

	public String getBitcoin() {
		return this.bitcoin;
	}

	public void setBitcoin(String bitcoin) {
		this.bitcoin = bitcoin;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountries() {
		return this.countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFixed() {
		return this.fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public String getMobileMoney() {
		return this.mobileMoney;
	}

	public void setMobileMoney(String mobileMoney) {
		this.mobileMoney = mobileMoney;
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

	public String getMsisdn1() {
		return this.msisdn1;
	}

	public void setMsisdn1(String msisdn1) {
		this.msisdn1 = msisdn1;
	}

	public String getMsisdn2() {
		return this.msisdn2;
	}

	public void setMsisdn2(String msisdn2) {
		this.msisdn2 = msisdn2;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPaypal() {
		return this.paypal;
	}

	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
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

}