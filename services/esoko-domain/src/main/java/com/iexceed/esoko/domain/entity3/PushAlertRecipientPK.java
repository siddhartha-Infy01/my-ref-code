package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the push_alert_recipients database table.
 * 
 */
@Embeddable
public class PushAlertRecipientPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="push_alert_id")
	private String pushAlertId;

	@Column(name="recipient_id")
	private String recipientId;

	@Column(name="parent_id")
	private String parentId;

	public PushAlertRecipientPK() {
	}
	public String getPushAlertId() {
		return this.pushAlertId;
	}
	public void setPushAlertId(String pushAlertId) {
		this.pushAlertId = pushAlertId;
	}
	public String getRecipientId() {
		return this.recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PushAlertRecipientPK)) {
			return false;
		}
		PushAlertRecipientPK castOther = (PushAlertRecipientPK)other;
		return 
			this.pushAlertId.equals(castOther.pushAlertId)
			&& this.recipientId.equals(castOther.recipientId)
			&& this.parentId.equals(castOther.parentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pushAlertId.hashCode();
		hash = hash * prime + this.recipientId.hashCode();
		hash = hash * prime + this.parentId.hashCode();
		
		return hash;
	}
}