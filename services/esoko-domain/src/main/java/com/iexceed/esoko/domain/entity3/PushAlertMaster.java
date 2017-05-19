package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the push_alert_master database table.
 * 
 */
@Entity
@Table(name="push_alert_master")
@NamedQuery(name="PushAlertMaster.findAll", query="SELECT p FROM PushAlertMaster p")
public class PushAlertMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PushAlertMasterPK id;

	@Column(name="airtime_amount")
	private BigDecimal airtimeAmount;

	@Column(name="airtime_currency")
	private String airtimeCurrency;

	@Column(name="airtime_flag")
	private String airtimeFlag;

	@Column(name="alert_code")
	private String alertCode;

	@Column(name="alert_state")
	private String alertState;

	@Column(name="bid_offer")
	private String bidOffer;

	private String country;

	@Column(name="country_state")
	private String countryState;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	@Column(name="data_status")
	private String dataStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="freq_flag")
	private String freqFlag;

	@Column(name="is_public")
	private String isPublic;

	private String language;

	@Column(name="message_type")
	private String messageType;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String name;

	@Column(name="never_end_flag")
	private String neverEndFlag;

	@Column(name="no_of_messages")
	private int noOfMessages;

	@Column(name="payee_account_no")
	private String payeeAccountNo;

	@Column(name="payee_network_id")
	private String payeeNetworkId;

	@Column(name="payee_type")
	private String payeeType;

	@Column(name="price_recepient")
	private String priceRecepient;

	@Column(name="repeats_flag")
	private String repeatsFlag;

	private String schedule;

	@Column(name="schedule_days")
	private String scheduleDays;

	@Column(name="schedule_time")
	private Time scheduleTime;

	@Column(name="send_copy")
	private String sendCopy;

	@Column(name="send_email")
	private String sendEmail;

	private String senderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	@Lob
	private String text;

	private String town;

	@Column(name="wheather_recepient")
	private String wheatherRecepient;

	public PushAlertMaster() {
	}

	public PushAlertMasterPK getId() {
		return this.id;
	}

	public void setId(PushAlertMasterPK id) {
		this.id = id;
	}

	public BigDecimal getAirtimeAmount() {
		return this.airtimeAmount;
	}

	public void setAirtimeAmount(BigDecimal airtimeAmount) {
		this.airtimeAmount = airtimeAmount;
	}

	public String getAirtimeCurrency() {
		return this.airtimeCurrency;
	}

	public void setAirtimeCurrency(String airtimeCurrency) {
		this.airtimeCurrency = airtimeCurrency;
	}

	public String getAirtimeFlag() {
		return this.airtimeFlag;
	}

	public void setAirtimeFlag(String airtimeFlag) {
		this.airtimeFlag = airtimeFlag;
	}

	public String getAlertCode() {
		return this.alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertState() {
		return this.alertState;
	}

	public void setAlertState(String alertState) {
		this.alertState = alertState;
	}

	public String getBidOffer() {
		return this.bidOffer;
	}

	public void setBidOffer(String bidOffer) {
		this.bidOffer = bidOffer;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryState() {
		return this.countryState;
	}

	public void setCountryState(String countryState) {
		this.countryState = countryState;
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

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFreqFlag() {
		return this.freqFlag;
	}

	public void setFreqFlag(String freqFlag) {
		this.freqFlag = freqFlag;
	}

	public String getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
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

	public String getNeverEndFlag() {
		return this.neverEndFlag;
	}

	public void setNeverEndFlag(String neverEndFlag) {
		this.neverEndFlag = neverEndFlag;
	}

	public int getNoOfMessages() {
		return this.noOfMessages;
	}

	public void setNoOfMessages(int noOfMessages) {
		this.noOfMessages = noOfMessages;
	}

	public String getPayeeAccountNo() {
		return this.payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getPayeeNetworkId() {
		return this.payeeNetworkId;
	}

	public void setPayeeNetworkId(String payeeNetworkId) {
		this.payeeNetworkId = payeeNetworkId;
	}

	public String getPayeeType() {
		return this.payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public String getPriceRecepient() {
		return this.priceRecepient;
	}

	public void setPriceRecepient(String priceRecepient) {
		this.priceRecepient = priceRecepient;
	}

	public String getRepeatsFlag() {
		return this.repeatsFlag;
	}

	public void setRepeatsFlag(String repeatsFlag) {
		this.repeatsFlag = repeatsFlag;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getScheduleDays() {
		return this.scheduleDays;
	}

	public void setScheduleDays(String scheduleDays) {
		this.scheduleDays = scheduleDays;
	}

	public Time getScheduleTime() {
		return this.scheduleTime;
	}

	public void setScheduleTime(Time scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getSendCopy() {
		return this.sendCopy;
	}

	public void setSendCopy(String sendCopy) {
		this.sendCopy = sendCopy;
	}

	public String getSendEmail() {
		return this.sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getSenderId() {
		return this.senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getWheatherRecepient() {
		return this.wheatherRecepient;
	}

	public void setWheatherRecepient(String wheatherRecepient) {
		this.wheatherRecepient = wheatherRecepient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airtimeAmount == null) ? 0 : airtimeAmount.hashCode());
		result = prime * result
				+ ((airtimeCurrency == null) ? 0 : airtimeCurrency.hashCode());
		result = prime * result
				+ ((airtimeFlag == null) ? 0 : airtimeFlag.hashCode());
		result = prime * result
				+ ((alertCode == null) ? 0 : alertCode.hashCode());
		result = prime * result
				+ ((alertState == null) ? 0 : alertState.hashCode());
		result = prime * result
				+ ((bidOffer == null) ? 0 : bidOffer.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((countryState == null) ? 0 : countryState.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result
				+ ((dataStatus == null) ? 0 : dataStatus.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((freqFlag == null) ? 0 : freqFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((neverEndFlag == null) ? 0 : neverEndFlag.hashCode());
		result = prime * result + noOfMessages;
		result = prime * result
				+ ((payeeAccountNo == null) ? 0 : payeeAccountNo.hashCode());
		result = prime * result
				+ ((payeeNetworkId == null) ? 0 : payeeNetworkId.hashCode());
		result = prime * result
				+ ((payeeType == null) ? 0 : payeeType.hashCode());
		result = prime * result
				+ ((priceRecepient == null) ? 0 : priceRecepient.hashCode());
		result = prime * result
				+ ((repeatsFlag == null) ? 0 : repeatsFlag.hashCode());
		result = prime * result
				+ ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result
				+ ((scheduleDays == null) ? 0 : scheduleDays.hashCode());
		result = prime * result
				+ ((scheduleTime == null) ? 0 : scheduleTime.hashCode());
		result = prime * result
				+ ((sendCopy == null) ? 0 : sendCopy.hashCode());
		result = prime * result
				+ ((sendEmail == null) ? 0 : sendEmail.hashCode());
		result = prime * result
				+ ((senderId == null) ? 0 : senderId.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		result = prime
				* result
				+ ((wheatherRecepient == null) ? 0 : wheatherRecepient
						.hashCode());
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
		PushAlertMaster other = (PushAlertMaster) obj;
		if (airtimeAmount == null) {
			if (other.airtimeAmount != null)
				return false;
		} else if (!airtimeAmount.equals(other.airtimeAmount))
			return false;
		if (airtimeCurrency == null) {
			if (other.airtimeCurrency != null)
				return false;
		} else if (!airtimeCurrency.equals(other.airtimeCurrency))
			return false;
		if (airtimeFlag == null) {
			if (other.airtimeFlag != null)
				return false;
		} else if (!airtimeFlag.equals(other.airtimeFlag))
			return false;
		if (alertCode == null) {
			if (other.alertCode != null)
				return false;
		} else if (!alertCode.equals(other.alertCode))
			return false;
		if (alertState == null) {
			if (other.alertState != null)
				return false;
		} else if (!alertState.equals(other.alertState))
			return false;
		if (bidOffer == null) {
			if (other.bidOffer != null)
				return false;
		} else if (!bidOffer.equals(other.bidOffer))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (countryState == null) {
			if (other.countryState != null)
				return false;
		} else if (!countryState.equals(other.countryState))
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
		if (dataStatus == null) {
			if (other.dataStatus != null)
				return false;
		} else if (!dataStatus.equals(other.dataStatus))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (freqFlag == null) {
			if (other.freqFlag != null)
				return false;
		} else if (!freqFlag.equals(other.freqFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!isPublic.equals(other.isPublic))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (messageType == null) {
			if (other.messageType != null)
				return false;
		} else if (!messageType.equals(other.messageType))
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
		if (neverEndFlag == null) {
			if (other.neverEndFlag != null)
				return false;
		} else if (!neverEndFlag.equals(other.neverEndFlag))
			return false;
		if (noOfMessages != other.noOfMessages)
			return false;
		if (payeeAccountNo == null) {
			if (other.payeeAccountNo != null)
				return false;
		} else if (!payeeAccountNo.equals(other.payeeAccountNo))
			return false;
		if (payeeNetworkId == null) {
			if (other.payeeNetworkId != null)
				return false;
		} else if (!payeeNetworkId.equals(other.payeeNetworkId))
			return false;
		if (payeeType == null) {
			if (other.payeeType != null)
				return false;
		} else if (!payeeType.equals(other.payeeType))
			return false;
		if (priceRecepient == null) {
			if (other.priceRecepient != null)
				return false;
		} else if (!priceRecepient.equals(other.priceRecepient))
			return false;
		if (repeatsFlag == null) {
			if (other.repeatsFlag != null)
				return false;
		} else if (!repeatsFlag.equals(other.repeatsFlag))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (scheduleDays == null) {
			if (other.scheduleDays != null)
				return false;
		} else if (!scheduleDays.equals(other.scheduleDays))
			return false;
		if (scheduleTime == null) {
			if (other.scheduleTime != null)
				return false;
		} else if (!scheduleTime.equals(other.scheduleTime))
			return false;
		if (sendCopy == null) {
			if (other.sendCopy != null)
				return false;
		} else if (!sendCopy.equals(other.sendCopy))
			return false;
		if (sendEmail == null) {
			if (other.sendEmail != null)
				return false;
		} else if (!sendEmail.equals(other.sendEmail))
			return false;
		if (senderId == null) {
			if (other.senderId != null)
				return false;
		} else if (!senderId.equals(other.senderId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		if (wheatherRecepient == null) {
			if (other.wheatherRecepient != null)
				return false;
		} else if (!wheatherRecepient.equals(other.wheatherRecepient))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PushAlertMaster [id=" + id + ", airtimeAmount=" + airtimeAmount
				+ ", airtimeCurrency=" + airtimeCurrency + ", airtimeFlag="
				+ airtimeFlag + ", alertCode=" + alertCode + ", alertState="
				+ alertState + ", bidOffer=" + bidOffer + ", country="
				+ country + ", countryState=" + countryState + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", currencyId="
				+ currencyId + ", dataStatus=" + dataStatus + ", endDate="
				+ endDate + ", freqFlag=" + freqFlag + ", isPublic=" + isPublic
				+ ", language=" + language + ", messageType=" + messageType
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", name=" + name + ", neverEndFlag=" + neverEndFlag
				+ ", noOfMessages=" + noOfMessages + ", payeeAccountNo="
				+ payeeAccountNo + ", payeeNetworkId=" + payeeNetworkId
				+ ", payeeType=" + payeeType + ", priceRecepient="
				+ priceRecepient + ", repeatsFlag=" + repeatsFlag
				+ ", schedule=" + schedule + ", scheduleDays=" + scheduleDays
				+ ", scheduleTime=" + scheduleTime + ", sendCopy=" + sendCopy
				+ ", sendEmail=" + sendEmail + ", senderId=" + senderId
				+ ", startDate=" + startDate + ", text=" + text + ", town="
				+ town + ", wheatherRecepient=" + wheatherRecepient + "]";
	}
		
}