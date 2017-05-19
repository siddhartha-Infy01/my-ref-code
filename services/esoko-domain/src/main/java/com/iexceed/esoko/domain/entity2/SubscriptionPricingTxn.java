package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the subscription_pricing_txn database table.
 * 
 */
@Entity
@Table(name="subscription_pricing_txn")
@NamedQuery(name="SubscriptionPricingTxn.findAll", query="SELECT s FROM SubscriptionPricingTxn s")
public class SubscriptionPricingTxn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionPricingTxnPK id;

	private String agent;

	@Column(name="annual_payment_bonus")
	private String annualPaymentBonus;

	@Column(name="basic_support")
	private String basicSupport;

	@Column(name="call_centre")
	private String callCentre;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="free_credit")
	private String freeCredit;

	@Column(name="knowledge_plus")
	private String knowledgePlus;

	@Column(name="managed_services")
	private String managedServices;

	@Column(name="minimum_purchase_period")
	private String minimumPurchasePeriod;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String monthly;

	@Column(name="premium_support")
	private String premiumSupport;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="sms_premium")
	private String smsPremium;

	@Column(name="sms_regular")
	private String smsRegular;

	@Column(name="survey_app")
	private String surveyApp;

	@Column(name="voice_premium")
	private String voicePremium;

	@Column(name="voice_regular")
	private String voiceRegular;

	public SubscriptionPricingTxn() {
	}

	public SubscriptionPricingTxnPK getId() {
		return this.id;
	}

	public void setId(SubscriptionPricingTxnPK id) {
		this.id = id;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAnnualPaymentBonus() {
		return this.annualPaymentBonus;
	}

	public void setAnnualPaymentBonus(String annualPaymentBonus) {
		this.annualPaymentBonus = annualPaymentBonus;
	}

	public String getBasicSupport() {
		return this.basicSupport;
	}

	public void setBasicSupport(String basicSupport) {
		this.basicSupport = basicSupport;
	}

	public String getCallCentre() {
		return this.callCentre;
	}

	public void setCallCentre(String callCentre) {
		this.callCentre = callCentre;
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

	public String getFreeCredit() {
		return this.freeCredit;
	}

	public void setFreeCredit(String freeCredit) {
		this.freeCredit = freeCredit;
	}

	public String getKnowledgePlus() {
		return this.knowledgePlus;
	}

	public void setKnowledgePlus(String knowledgePlus) {
		this.knowledgePlus = knowledgePlus;
	}

	public String getManagedServices() {
		return this.managedServices;
	}

	public void setManagedServices(String managedServices) {
		this.managedServices = managedServices;
	}

	public String getMinimumPurchasePeriod() {
		return this.minimumPurchasePeriod;
	}

	public void setMinimumPurchasePeriod(String minimumPurchasePeriod) {
		this.minimumPurchasePeriod = minimumPurchasePeriod;
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

	public String getMonthly() {
		return this.monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getPremiumSupport() {
		return this.premiumSupport;
	}

	public void setPremiumSupport(String premiumSupport) {
		this.premiumSupport = premiumSupport;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getSmsPremium() {
		return this.smsPremium;
	}

	public void setSmsPremium(String smsPremium) {
		this.smsPremium = smsPremium;
	}

	public String getSmsRegular() {
		return this.smsRegular;
	}

	public void setSmsRegular(String smsRegular) {
		this.smsRegular = smsRegular;
	}

	public String getSurveyApp() {
		return this.surveyApp;
	}

	public void setSurveyApp(String surveyApp) {
		this.surveyApp = surveyApp;
	}

	public String getVoicePremium() {
		return this.voicePremium;
	}

	public void setVoicePremium(String voicePremium) {
		this.voicePremium = voicePremium;
	}

	public String getVoiceRegular() {
		return this.voiceRegular;
	}

	public void setVoiceRegular(String voiceRegular) {
		this.voiceRegular = voiceRegular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime
				* result
				+ ((annualPaymentBonus == null) ? 0 : annualPaymentBonus
						.hashCode());
		result = prime * result
				+ ((basicSupport == null) ? 0 : basicSupport.hashCode());
		result = prime * result
				+ ((callCentre == null) ? 0 : callCentre.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((freeCredit == null) ? 0 : freeCredit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((knowledgePlus == null) ? 0 : knowledgePlus.hashCode());
		result = prime * result
				+ ((managedServices == null) ? 0 : managedServices.hashCode());
		result = prime
				* result
				+ ((minimumPurchasePeriod == null) ? 0 : minimumPurchasePeriod
						.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((monthly == null) ? 0 : monthly.hashCode());
		result = prime * result
				+ ((premiumSupport == null) ? 0 : premiumSupport.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((smsPremium == null) ? 0 : smsPremium.hashCode());
		result = prime * result
				+ ((smsRegular == null) ? 0 : smsRegular.hashCode());
		result = prime * result
				+ ((surveyApp == null) ? 0 : surveyApp.hashCode());
		result = prime * result
				+ ((voicePremium == null) ? 0 : voicePremium.hashCode());
		result = prime * result
				+ ((voiceRegular == null) ? 0 : voiceRegular.hashCode());
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
		SubscriptionPricingTxn other = (SubscriptionPricingTxn) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (annualPaymentBonus == null) {
			if (other.annualPaymentBonus != null)
				return false;
		} else if (!annualPaymentBonus.equals(other.annualPaymentBonus))
			return false;
		if (basicSupport == null) {
			if (other.basicSupport != null)
				return false;
		} else if (!basicSupport.equals(other.basicSupport))
			return false;
		if (callCentre == null) {
			if (other.callCentre != null)
				return false;
		} else if (!callCentre.equals(other.callCentre))
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
		if (freeCredit == null) {
			if (other.freeCredit != null)
				return false;
		} else if (!freeCredit.equals(other.freeCredit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (knowledgePlus == null) {
			if (other.knowledgePlus != null)
				return false;
		} else if (!knowledgePlus.equals(other.knowledgePlus))
			return false;
		if (managedServices == null) {
			if (other.managedServices != null)
				return false;
		} else if (!managedServices.equals(other.managedServices))
			return false;
		if (minimumPurchasePeriod == null) {
			if (other.minimumPurchasePeriod != null)
				return false;
		} else if (!minimumPurchasePeriod.equals(other.minimumPurchasePeriod))
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
		if (monthly == null) {
			if (other.monthly != null)
				return false;
		} else if (!monthly.equals(other.monthly))
			return false;
		if (premiumSupport == null) {
			if (other.premiumSupport != null)
				return false;
		} else if (!premiumSupport.equals(other.premiumSupport))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (smsPremium == null) {
			if (other.smsPremium != null)
				return false;
		} else if (!smsPremium.equals(other.smsPremium))
			return false;
		if (smsRegular == null) {
			if (other.smsRegular != null)
				return false;
		} else if (!smsRegular.equals(other.smsRegular))
			return false;
		if (surveyApp == null) {
			if (other.surveyApp != null)
				return false;
		} else if (!surveyApp.equals(other.surveyApp))
			return false;
		if (voicePremium == null) {
			if (other.voicePremium != null)
				return false;
		} else if (!voicePremium.equals(other.voicePremium))
			return false;
		if (voiceRegular == null) {
			if (other.voiceRegular != null)
				return false;
		} else if (!voiceRegular.equals(other.voiceRegular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionPricingTxn [id=" + id + ", agent=" + agent
				+ ", annualPaymentBonus=" + annualPaymentBonus
				+ ", basicSupport=" + basicSupport + ", callCentre="
				+ callCentre + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", freeCredit=" + freeCredit + ", knowledgePlus="
				+ knowledgePlus + ", managedServices=" + managedServices
				+ ", minimumPurchasePeriod=" + minimumPurchasePeriod
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", monthly=" + monthly + ", premiumSupport=" + premiumSupport
				+ ", recordStatus=" + recordStatus + ", smsPremium="
				+ smsPremium + ", smsRegular=" + smsRegular + ", surveyApp="
				+ surveyApp + ", voicePremium=" + voicePremium
				+ ", voiceRegular=" + voiceRegular + "]";
	}

}