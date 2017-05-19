package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the message_delivary_Details database table.
 * 
 */
@Entity
@Table(name="message_delivary_Details")
@NamedQuery(name="Message_delivary_Detail.findAll", query="SELECT m FROM Message_delivary_Detail m")
public class Message_delivary_Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Message_delivary_DetailPK id;

	private String answer;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_TS;

	private String description;

	@Lob
	private String message;

	@Column(name="message_type")
	private String messageType;

	@Column(name="MSG_COST")
	private BigDecimal msgCost;

	@Column(name="MSG_STATUS")
	private String msgStatus;

	private String msisdn;

	@Column(name="network_id")
	private String networkId;

	@Column(name="route_id")
	private String routeId;

	@Column(name="schedule_id")
	private String scheduleId;

	@Column(name="user_id")
	private String userId;

	public Message_delivary_Detail() {
	}

	public Message_delivary_DetailPK getId() {
		return this.id;
	}

	public void setId(Message_delivary_DetailPK id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated_TS() {
		return this.created_TS;
	}

	public void setCreated_TS(Date created_TS) {
		this.created_TS = created_TS;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getMsgCost() {
		return this.msgCost;
	}

	public void setMsgCost(BigDecimal msgCost) {
		this.msgCost = msgCost;
	}

	public String getMsgStatus() {
		return this.msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getRouteId() {
		return this.routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
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
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((created_TS == null) ? 0 : created_TS.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result + ((msgCost == null) ? 0 : msgCost.hashCode());
		result = prime * result
				+ ((msgStatus == null) ? 0 : msgStatus.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		result = prime * result
				+ ((scheduleId == null) ? 0 : scheduleId.hashCode());
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
		Message_delivary_Detail other = (Message_delivary_Detail) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (created_TS == null) {
			if (other.created_TS != null)
				return false;
		} else if (!created_TS.equals(other.created_TS))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageType == null) {
			if (other.messageType != null)
				return false;
		} else if (!messageType.equals(other.messageType))
			return false;
		if (msgCost == null) {
			if (other.msgCost != null)
				return false;
		} else if (!msgCost.equals(other.msgCost))
			return false;
		if (msgStatus == null) {
			if (other.msgStatus != null)
				return false;
		} else if (!msgStatus.equals(other.msgStatus))
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
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		if (scheduleId == null) {
			if (other.scheduleId != null)
				return false;
		} else if (!scheduleId.equals(other.scheduleId))
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
		return "Message_delivary_Detail [id=" + id + ", answer=" + answer
				+ ", createdBy=" + createdBy + ", created_TS=" + created_TS
				+ ", description=" + description + ", message=" + message
				+ ", messageType=" + messageType + ", msgCost=" + msgCost
				+ ", msgStatus=" + msgStatus + ", msisdn=" + msisdn
				+ ", networkId=" + networkId + ", routeId=" + routeId
				+ ", scheduleId=" + scheduleId + ", userId=" + userId + "]";
	}
	
	
}