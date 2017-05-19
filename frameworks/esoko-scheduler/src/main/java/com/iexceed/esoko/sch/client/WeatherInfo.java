package com.iexceed.esoko.sch.client;

public enum WeatherInfo {
	WEATHER("weather"), TEMPERATURE("temperature"), LONGITUDE("longitude"), LATITUDE(
			"latitude"), STATION("station"), CONTENT("content"), CONDITION(
			"condition"), TOTOAGRI("totoagriculture"), SUMMMARY("summmary"), LOCATION(
			"location"), FORECASTS("forecast"), TOTAL("total"), CHANCE("chance"), PRECIPITATION(
			"precipitation"), HIGH("high"), LOW("low"), TIME("time"), LANGUAGE(
			"language"), DAYS("days");

	private final String name;

	private WeatherInfo(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
