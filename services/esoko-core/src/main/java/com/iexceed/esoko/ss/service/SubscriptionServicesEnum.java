package com.iexceed.esoko.ss.service;

public enum SubscriptionServicesEnum {
	// Price services
	MONTHLY("Monthly", "N"), MONTHLY_WHOLESALE("Monthly Wholesale", "Y"), ANNUAL_PYMT_BONUS(
			"Annual Payment BONUS (Monthly)", "N"), AGENT(
			"Agent (each additional one)", "N"), AGENT_WHOLESLE(
			"Agent Wholesale", "Y"), SURVEY_ALLOWNCE("Survey Form Allowance",
			"N"), SURVEY_APP("Survey App", "N"), SURVEY_APP_WHOLESALE(
			"Survey App Wholesale", "Y"), KLWDGE_PLUS("Knowledge +", "N"), KLWDGE_PLUS_WHOLESALE(
			"Knowledge + Wholesale", "Y"), CALL_CANTRE("Call Centre", "N"), CALL_CANTRE_WHOLESALE(
			"Call Centre Wholesale", "Y"), FREE_SMS_RGLR("Free SMS (Monthly)",
			"N"), SMS_RGLR("SMS (regular)", "N"), SMS_REGLR_WHOLESALE(
			"SMS (regular) Wholesale", "Y"), SMS_PRUM("SMS (premium)", "N"), SMS_PRUM_WHOLESALE(
			"SMS (premium) Wholesale", "Y"), VOICE_RGLR("Voice (regular)", "N"), VOICE_RGLR_WHOLESALE(
			"Voice (regular) Wholesale", "Y"), VOICE_PRUM_WHOLESALE(
			"Voice (premium) Wholesale", "Y"), VOICE_PRUM("Voice (premium)",
			"N"), FREE_CREDIT("Free Credit (for sms use only)", "N"), BASIC_SUPPORT(
			"*Basic Support (online/webinars)", "N"), PRUM_SUPPORT(
			"*Premium Support", "N"), MNGD_SERVICES("Managed Services", "N"), MIN_PUR_PERIOD(
			"Minimum purchase period", "N"),
	// Limit services
	MEMBERS("Members (Limit)"), MESSAGE_TRAFFIC("Message Traffic (in & out)"), ALERTS(
			"Alerts (i.e. recipients setup)"), AGENTS("Agents (surveys/K+)"), NETWORKS(
			"Networks"), GROUPS("Groups"), SMART_GROUPS("SmartGroups"), ADMINISTRATORS(
			"Administrators"), PUSH("Push"), INBOX("Inbox (keywords)"), PRICES(
			"Prices (marketplace)"), OFFERS("Offers (marketplace)"), UPLOAD_FORMS(
			"Upload: Forms"), UPLOAD_TRAFFIC("Upload: Traffic"), API1_REQUEST(
			"API 1 requests");

	private String serviceCode;
	private String margin;

	private SubscriptionServicesEnum(String exCode) {
		this.serviceCode = exCode;
	}

	private SubscriptionServicesEnum(String exCode, String margin) {
		this.serviceCode = exCode;
		this.margin = margin;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public String getMargin() {
		return margin;
	}
}
