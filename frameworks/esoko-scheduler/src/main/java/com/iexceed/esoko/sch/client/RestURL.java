package com.iexceed.esoko.sch.client;

public enum RestURL {
	TOTOAGRICULTURE("http://www.totoagriculture.org/weather/weather"), AWHERE(
			"https://data.awhere.com/api/weather/daily"), AFRICATAKINGGATEWAY(
			"http://api.africastalking.com/version1/airtime/send"), TXTNPAY(
			"https://www.txtnpay.net/ecommerce/XMLService.aspx");

	private final String name;

	private RestURL(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
