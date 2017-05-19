package com.iexceed.esoko.engine.utils;

public enum SystemEnums {
	TXNSALL("All Services"), KANNEL("Kannel"), ESYNC("Esync"), MT("MT"), MO(
			"MO"),TRN_CCY("USD");

	private String value;

	private SystemEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
