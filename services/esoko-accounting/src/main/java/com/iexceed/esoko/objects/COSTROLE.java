package com.iexceed.esoko.objects;

public enum COSTROLE {
	BASE_COST("BASE_COST"), WHOLESALE("WHOLESALE"), RETAIL("RETAIL"),COST("COST");
	String costRole;

	COSTROLE(String costRole) {
		this.costRole = costRole;

	}
	
	public String getCostRole(){
		return costRole;
	}

}
