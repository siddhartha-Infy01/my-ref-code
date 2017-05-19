package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the push_alert_recipients database table.
 * 
 */
@Entity
@Table(name="push_alert_recipients")
@NamedQuery(name="PushAlertRecipient.findAll", query="SELECT p FROM PushAlertRecipient p")
public class PushAlertRecipient implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PushAlertRecipientPK id;

	@Column(name="recipient_type")
	private String recipientType;

	public PushAlertRecipient() {
	}

	public PushAlertRecipientPK getId() {
		return this.id;
	}

	public void setId(PushAlertRecipientPK id) {
		this.id = id;
	}

	public String getRecipientType() {
		return this.recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

}