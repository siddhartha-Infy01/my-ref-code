package com.iexceed.esoko.objects;

import java.util.ArrayList;
import java.util.List;

public enum TRANSACTION_CODE {
	PUSH_ACC("PUAC", 3, 3, "Push Service"), ALERT_ACC("ALAC", 3, 3,
			"Alert Service"), GENSMS_ACC("GEAC", 1, 1, "SMS Service"), FT_ACC(
			"FTAC", 1, 1, "Fund Transfer"), INVOICE_ACC("INAC", 1, 2,
			"Invoice Accounting"), INBOX_ACC("IBAC", 1, 3, "Inbox and Polls");

	String trnCode;
	String description;
	Integer debits;
	Integer credits;

	TRANSACTION_CODE(String trnCode, Integer debits, Integer credits,
			String description) {
		this.trnCode = trnCode;
		this.debits = debits;
		this.credits = credits;
		this.description = description;
	}

	public String value() {
		return trnCode;
	}

	public Integer noOfDebits() {
		return debits;
	}

	public Integer noOfCredits() {
		return credits;
	}

	public String description() {
		return description;
	}
}

enum ROLE_TO_HEAD {

	PUSH_ACC_ROLE(null, null, null), ALERT_ACC_ROLE(null, null, null), GENSMS_ACC_ROLE(
			null, null, null), FT_ACC_ROLE(null, null, null), INVOICE_ACC_ROLE(
			null, null, null), INBOX_ACC_ROLE(null, null, null);
	List<String> debitRoles = new ArrayList<String>();
	List<String> creditRoles = new ArrayList<String>();
	List<String> costRoles = new ArrayList<String>();

	private ROLE_TO_HEAD(List<String> debitRoles, List<String> creditRoles,
			List<String> costRoles) {
		this.debitRoles = debitRoles;
		this.creditRoles = creditRoles;
		this.costRoles = costRoles;
	}

}
