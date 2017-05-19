package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the network_subscription_details database table.
 * 
 */
@Entity
@Table(name = "network_subscription_details")
@NamedQuery(name = "NetworkSubscriptionDetail.findAll", query = "SELECT n FROM NetworkSubscriptionDetail n")
public class NetworkSubscriptionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NetworkSubscriptionDetailPK id;

	private double amount;

	private double casReseller;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	private Date createdTs;

	@Column(name = "subscription_id")
	private String subscriptionId;

	private String description;

	private int discount;

	private double marginReseller;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_ts")
	private Date modifiedTs;

	private int period;

	private int quantity;

	@Column(name = "record_status")
	private String recordStatus;

	@Column(name = "subscription_type")
	private String subscriptionType;

	private double vat;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public NetworkSubscriptionDetail() {
	}

	public NetworkSubscriptionDetailPK getId() {
		return this.id;
	}

	public void setId(NetworkSubscriptionDetailPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCasReseller() {
		return this.casReseller;
	}

	public void setCasReseller(double casReseller) {
		this.casReseller = casReseller;
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

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getMarginReseller() {
		return this.marginReseller;
	}

	public void setMarginReseller(double marginReseller) {
		this.marginReseller = marginReseller;
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

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public double getVat() {
		return this.vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

}