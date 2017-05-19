package com.iexceed.esoko.sch.client;

public enum FilePath {
	WEATHERPROPERTIES(
			"E:\\Esoko2\\frameworks\\esoko-scheduler\\src\\main\\resources\\weather.properties"), KEYSTOREPATH(
			"E:\\Esoko2\\frameworks\\esoko-scheduler\\src\\main\\resources\\keystore.jks"), AIRTIMEPROPERTIES(
			"E:\\Esoko2\\frameworks\\esoko-scheduler\\src\\main\\resources\\airtime.properties");

	private final String name;

	private FilePath(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
