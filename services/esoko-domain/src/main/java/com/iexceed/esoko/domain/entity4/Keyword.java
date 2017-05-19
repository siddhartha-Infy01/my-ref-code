package com.iexceed.esoko.domain.entity4;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the keywords database table.
 * 
 */
@Entity
@Table(name="keywords")
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KeywordPK id;

	private BigDecimal airtime;

	@Column(name="airtime_currency")
	private String airtimeCurrency;

	@Column(name="airtime_flag")
	private String airtimeFlag;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Lob
	private String ealerts;

	@Lob
	private String groups;

	@Column(name="join_flag")
	private String joinFlag;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Column(name="my_network")
	private String myNetwork;

	@Column(name="network_id")
	private String networkId;

	@Column(name="no_characters")
	private int noCharacters;

	@Column(name="no_of_messages")
	private int noOfMessages;

	@Column(name="payee_account")
	private String payeeAccount;

	@Column(name="record_status")
	private String recordStatus;

	@Column(name="reply_flag")
	private String replyFlag;

	@Lob
	private String response;

	public Keyword() {
	}

	public KeywordPK getId() {
		return this.id;
	}

	public void setId(KeywordPK id) {
		this.id = id;
	}

	public BigDecimal getAirtime() {
		return this.airtime;
	}

	public void setAirtime(BigDecimal airtime) {
		this.airtime = airtime;
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

	public String getEalerts() {
		return this.ealerts;
	}

	public void setEalerts(String ealerts) {
		this.ealerts = ealerts;
	}

	public String getGroups() {
		return this.groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getJoinFlag() {
		return this.joinFlag;
	}

	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
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

	public String getMyNetwork() {
		return this.myNetwork;
	}

	public void setMyNetwork(String myNetwork) {
		this.myNetwork = myNetwork;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public int getNoCharacters() {
		return this.noCharacters;
	}

	public void setNoCharacters(int noCharacters) {
		this.noCharacters = noCharacters;
	}

	public int getNoOfMessages() {
		return this.noOfMessages;
	}

	public void setNoOfMessages(int noOfMessages) {
		this.noOfMessages = noOfMessages;
	}

	public String getPayeeAccount() {
		return this.payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getReplyFlag() {
		return this.replyFlag;
	}

	public void setReplyFlag(String replyFlag) {
		this.replyFlag = replyFlag;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airtime == null) ? 0 : airtime.hashCode());
		result = prime * result
				+ ((airtimeCurrency == null) ? 0 : airtimeCurrency.hashCode());
		result = prime * result
				+ ((airtimeFlag == null) ? 0 : airtimeFlag.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((ealerts == null) ? 0 : ealerts.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((joinFlag == null) ? 0 : joinFlag.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((myNetwork == null) ? 0 : myNetwork.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + noCharacters;
		result = prime * result + noOfMessages;
		result = prime * result
				+ ((payeeAccount == null) ? 0 : payeeAccount.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((replyFlag == null) ? 0 : replyFlag.hashCode());
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
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
		Keyword other = (Keyword) obj;
		if (airtime == null) {
			if (other.airtime != null)
				return false;
		} else if (!airtime.equals(other.airtime))
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
		if (ealerts == null) {
			if (other.ealerts != null)
				return false;
		} else if (!ealerts.equals(other.ealerts))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (joinFlag == null) {
			if (other.joinFlag != null)
				return false;
		} else if (!joinFlag.equals(other.joinFlag))
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
		if (myNetwork == null) {
			if (other.myNetwork != null)
				return false;
		} else if (!myNetwork.equals(other.myNetwork))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (noCharacters != other.noCharacters)
			return false;
		if (noOfMessages != other.noOfMessages)
			return false;
		if (payeeAccount == null) {
			if (other.payeeAccount != null)
				return false;
		} else if (!payeeAccount.equals(other.payeeAccount))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (replyFlag == null) {
			if (other.replyFlag != null)
				return false;
		} else if (!replyFlag.equals(other.replyFlag))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Keyword [id=" + id + ", airtime=" + airtime
				+ ", airtimeCurrency=" + airtimeCurrency + ", airtimeFlag="
				+ airtimeFlag + ", createdBy=" + createdBy + ", createdTs="
				+ createdTs + ", ealerts=" + ealerts + ", groups=" + groups
				+ ", joinFlag=" + joinFlag + ", modifiedBy=" + modifiedBy
				+ ", modifiedTs=" + modifiedTs + ", myNetwork=" + myNetwork
				+ ", networkId=" + networkId + ", noCharacters=" + noCharacters
				+ ", noOfMessages=" + noOfMessages + ", payeeAccount="
				+ payeeAccount + ", recordStatus=" + recordStatus
				+ ", replyFlag=" + replyFlag + ", response=" + response + "]";
	}		

}