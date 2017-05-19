package com.iexceed.esoko.objects;

public enum ACCROLE {
	D_NWK_ACC("D", "D_NWK_ACC"), C_NWK_ACC("C", "C_NWK_ACC"), D_USR_ACC("D",
			"D_USR_ACC"), C_USR_ACC("C", "C_USR_ACC"), D_NWK_NOT("D",
			"D_NWK_NOT"), C_NWK_NOT("C", "C_NWK_NOT"), D_ESK_BNK_ACC("D",
			"D_ESK_BNK_ACC"), C_ESK_BNK_ACC("C", "C_ESK_BNK_ACC"), D_RCP_ACC(
			"D", "D_RCP_ACC"), C_RSL_ACC("C", "C_RSL_ACC"), D_RSL_ACC("D",
			"D_RSL_ACC"), C_ESK_ACC("C", "C_ESK_ACC"), D_ESK_ACC("D",
			"D_ESK_ACC");

	String C_D;
	String accRole;

	ACCROLE(String C_D, String accRole) {
		this.C_D = C_D;
		this.accRole = accRole;
	}

	public String getCD() {
		return C_D;
	}

	public String getACCRole() {
		return accRole;
	}

}