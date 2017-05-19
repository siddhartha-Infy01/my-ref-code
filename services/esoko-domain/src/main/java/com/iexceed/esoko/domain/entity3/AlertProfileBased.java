package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the alert_profile_based database table.
 * 
 */
@Entity
@Table(name = "alert_profile_based")
@NamedQuery(name = "AlertProfileBased.findAll", query = "SELECT a FROM AlertProfileBased a")
public class AlertProfileBased implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

	@Column(name = "push_alert_id")
	private String pushAlertId;

	@Column(name = "no_of_messages")
	private int noOfMessages;

	@Lob
	private String text;

	@Column(name = "user_id")
	private String userId;

	public AlertProfileBased() {
	}

	public int getProfileId() {
		return this.profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getPushAlertId() {
		return this.pushAlertId;
	}

	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNoOfMessages() {
		return noOfMessages;
	}

	public void setNoOfMessages(int noOfMessages) {
		this.noOfMessages = noOfMessages;
	}

	@Override
	public String toString() {
		return "AlertProfileBased [profileId=" + profileId + ", pushAlertId="
				+ pushAlertId + ", noOfMessages=" + noOfMessages + ", text="
				+ text + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noOfMessages;
		result = prime * result + profileId;
		result = prime * result
				+ ((pushAlertId == null) ? 0 : pushAlertId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		AlertProfileBased other = (AlertProfileBased) obj;
		if (noOfMessages != other.noOfMessages)
			return false;
		if (profileId != other.profileId)
			return false;
		if (pushAlertId == null) {
			if (other.pushAlertId != null)
				return false;
		} else if (!pushAlertId.equals(other.pushAlertId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}