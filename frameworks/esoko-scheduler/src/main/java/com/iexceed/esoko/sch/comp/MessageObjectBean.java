package com.iexceed.esoko.sch.comp;

import com.iexceed.esoko.objects.Cost;

public class MessageObjectBean {

	private String messageId;
	private String trnRefNo;
	private String alertId;
	private String type;
	private boolean isProfileBased;
	private boolean isSurchargeable;
	private boolean isReversal = false;
	private String userId;
	private String networkId;
	private char messageStatus;
	private String message;
	private Cost msgCost;
	private String lcy;
	private String fcy;
	private String msisdn;
	private String smsc;
	private String routeId;
	private String senderId;
	private String operatorId;
	private String payeeType;
	private Integer noOfMsgs;
	private String payeeAcNo;
	private String description;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTrnRefNo() {
		return trnRefNo;
	}

	public void setTrnRefNo(String trnRefNo) {
		this.trnRefNo = trnRefNo;
	}

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isProfileBased() {
		return isProfileBased;
	}

	public void setProfileBased(boolean isProfileBased) {
		this.isProfileBased = isProfileBased;
	}

	public boolean isSurchargeable() {
		return isSurchargeable;
	}

	public void setSurchargeable(boolean isSurchargeable) {
		this.isSurchargeable = isSurchargeable;
	}

	public boolean isReversal() {
		return isReversal;
	}

	public void setReversal(boolean isReversal) {
		this.isReversal = isReversal;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public char getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(char messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Cost getMsgCost() {
		return msgCost;
	}

	public void setMsgCost(Cost msgCost) {
		this.msgCost = msgCost;
	}

	public String getLcy() {
		return lcy;
	}

	public void setLcy(String lcy) {
		this.lcy = lcy;
	}

	public String getFcy() {
		return fcy;
	}

	public void setFcy(String fcy) {
		this.fcy = fcy;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSmsc() {
		return smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPayeeType() {
		return payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public Integer getNoOfMsgs() {
		return noOfMsgs;
	}

	public void setNoOfMsgs(Integer noOfMsgs) {
		this.noOfMsgs = noOfMsgs;
	}

	public String getPayeeAcNo() {
		return payeeAcNo;
	}

	public void setPayeeAcNo(String payeeAcNo) {
		this.payeeAcNo = payeeAcNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertId == null) ? 0 : alertId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fcy == null) ? 0 : fcy.hashCode());
		result = prime * result + (isProfileBased ? 1231 : 1237);
		result = prime * result + (isReversal ? 1231 : 1237);
		result = prime * result + (isSurchargeable ? 1231 : 1237);
		result = prime * result + ((lcy == null) ? 0 : lcy.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((messageId == null) ? 0 : messageId.hashCode());
		result = prime * result + messageStatus;
		result = prime * result + ((msgCost == null) ? 0 : msgCost.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((noOfMsgs == null) ? 0 : noOfMsgs.hashCode());
		result = prime * result
				+ ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result
				+ ((payeeAcNo == null) ? 0 : payeeAcNo.hashCode());
		result = prime * result
				+ ((payeeType == null) ? 0 : payeeType.hashCode());
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		result = prime * result
				+ ((senderId == null) ? 0 : senderId.hashCode());
		result = prime * result + ((smsc == null) ? 0 : smsc.hashCode());
		result = prime * result
				+ ((trnRefNo == null) ? 0 : trnRefNo.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MessageObjectBean other = (MessageObjectBean) obj;
		if (alertId == null) {
			if (other.alertId != null)
				return false;
		} else if (!alertId.equals(other.alertId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fcy == null) {
			if (other.fcy != null)
				return false;
		} else if (!fcy.equals(other.fcy))
			return false;
		if (isProfileBased != other.isProfileBased)
			return false;
		if (isReversal != other.isReversal)
			return false;
		if (isSurchargeable != other.isSurchargeable)
			return false;
		if (lcy == null) {
			if (other.lcy != null)
				return false;
		} else if (!lcy.equals(other.lcy))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (messageStatus != other.messageStatus)
			return false;
		if (msgCost == null) {
			if (other.msgCost != null)
				return false;
		} else if (!msgCost.equals(other.msgCost))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (noOfMsgs == null) {
			if (other.noOfMsgs != null)
				return false;
		} else if (!noOfMsgs.equals(other.noOfMsgs))
			return false;
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (payeeAcNo == null) {
			if (other.payeeAcNo != null)
				return false;
		} else if (!payeeAcNo.equals(other.payeeAcNo))
			return false;
		if (payeeType == null) {
			if (other.payeeType != null)
				return false;
		} else if (!payeeType.equals(other.payeeType))
			return false;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		if (senderId == null) {
			if (other.senderId != null)
				return false;
		} else if (!senderId.equals(other.senderId))
			return false;
		if (smsc == null) {
			if (other.smsc != null)
				return false;
		} else if (!smsc.equals(other.smsc))
			return false;
		if (trnRefNo == null) {
			if (other.trnRefNo != null)
				return false;
		} else if (!trnRefNo.equals(other.trnRefNo))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "MessageObjectBean [messageId=" + messageId + ", trnRefNo="
				+ trnRefNo + ", alertId=" + alertId + ", type=" + type
				+ ", isProfileBased=" + isProfileBased + ", isSurchargeable="
				+ isSurchargeable + ", isReversal=" + isReversal + ", userId="
				+ userId + ", networkId=" + networkId + ", messageStatus="
				+ messageStatus + ", message=" + message + ", msgCost="
				+ msgCost + ", lcy=" + lcy + ", fcy=" + fcy + ", msisdn="
				+ msisdn + ", smsc=" + smsc + ", routeId=" + routeId
				+ ", senderId=" + senderId + ", operatorId=" + operatorId
				+ ", payeeType=" + payeeType + ", noOfMsgs=" + noOfMsgs
				+ ", payeeAcNo=" + payeeAcNo + ", description=" + description
				+ "]";
	}

}