package com.iexceed.esoko.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ROLETOHEAD {

	public final static Map<String, Map<ACCROLE, COSTROLE>> FT = new HashMap<String, Map<ACCROLE, COSTROLE>>();
	public final static Map<String, List<Map<ACCROLE, COSTROLE>>> PUSH = new HashMap<String, List<Map<ACCROLE, COSTROLE>>>();
	public final static Map<String, List<Map<ACCROLE, COSTROLE>>> ALERT = new HashMap<String, List<Map<ACCROLE, COSTROLE>>>();
	public final static Map<String, Map<ACCROLE, COSTROLE>> GENSMS = new HashMap<String, Map<ACCROLE, COSTROLE>>();
	public final static Map<String, List<Map<ACCROLE, COSTROLE>>> INVOICE = new HashMap<String, List<Map<ACCROLE, COSTROLE>>>();
	public final static Map<String, List<Map<ACCROLE, COSTROLE>>> INBOX = new HashMap<String, List<Map<ACCROLE, COSTROLE>>>();
	public final static String FTDEBIT = "Debit";
	public final static String FTCREDIT = "Credit";
	public final static String ALDEBIT = "Debit";
	public final static String ALCREDIT = "Credit";
	public final static String PUDEBIT = "Debit";
	public final static String PUCREDIT = "Credit";
	public final static String INVDEBIT = "Debit";
	public final static String INVCREDIT = "Credit";
	public final static String INBDEBIT = "Debit";
	public final static String INBCREDIT = "Credit";

	private static void loadFT() {

		for (int i = 0; i < TRANSACTION_CODE.FT_ACC.noOfDebits(); i++) {
			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:

				map.put(ACCROLE.D_NWK_ACC, COSTROLE.BASE_COST);
				FT.put(FTDEBIT, map);
				break;
			}
		}

		for (int i = 0; i < TRANSACTION_CODE.FT_ACC.noOfCredits(); i++) {
			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				map.put(ACCROLE.C_USR_ACC, COSTROLE.BASE_COST);
				FT.put(FTCREDIT, map);
				break;
			}
		}

	}

	private static void loadPUSH() {

		List<Map<ACCROLE, COSTROLE>> list = new ArrayList<Map<ACCROLE, COSTROLE>>();

		for (int i = 0; i < TRANSACTION_CODE.PUSH_ACC.noOfDebits(); i++) {
			Map<ACCROLE, COSTROLE> dmap = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				dmap.put(ACCROLE.D_NWK_ACC, COSTROLE.COST);
				list.add(dmap);
				PUSH.put(PUDEBIT, list);
				break;
			case 1:
				dmap.put(ACCROLE.D_USR_ACC, COSTROLE.COST);
				list.add(dmap);
				PUSH.put(PUDEBIT, list);
				break;
			case 2:
				dmap.put(ACCROLE.D_RCP_ACC, COSTROLE.COST);
				list.add(dmap);
				PUSH.put(PUDEBIT, list);
				break;
			}
		}

		for (int i = 0; i < TRANSACTION_CODE.PUSH_ACC.noOfCredits(); i++) {

			Map<ACCROLE, COSTROLE> cmap = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				cmap.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.BASE_COST);
				list.add(cmap);
				PUSH.put(PUCREDIT, list);
				break;

			case 1:
				cmap.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.RETAIL);
				list.add(cmap);
				PUSH.put(PUCREDIT, list);
				break;
			case 2:
				cmap.put(ACCROLE.C_RSL_ACC, COSTROLE.WHOLESALE);
				list.add(cmap);
				PUSH.put(PUCREDIT, list);
				break;
			}
		}

	}

	private static void loadALERT() {
		List<Map<ACCROLE, COSTROLE>> list = new ArrayList<Map<ACCROLE, COSTROLE>>();

		for (int i = 0; i < TRANSACTION_CODE.ALERT_ACC.noOfDebits(); i++) {
			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();

			switch (i) {
			case 0:
				map.put(ACCROLE.D_NWK_ACC, COSTROLE.COST);
				list.add(map);
				ALERT.put(ALDEBIT, list);
				break;
			case 1:
				map.put(ACCROLE.D_USR_ACC, COSTROLE.COST);
				list.add(map);
				ALERT.put(ALDEBIT, list);
				break;
			case 2:
				map.put(ACCROLE.D_RCP_ACC, COSTROLE.COST);
				list.add(map);
				ALERT.put(ALDEBIT, list);
				break;
			}
		}

		for (int i = 0; i < TRANSACTION_CODE.ALERT_ACC.noOfCredits(); i++) {

			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				map.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.BASE_COST);
				list.add(map);
				ALERT.put(ALCREDIT, list);
				break;
			case 1:
				map.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.RETAIL);
				list.add(map);
				ALERT.put(ALCREDIT, list);
				break;
			case 2:
				map.put(ACCROLE.C_RSL_ACC, COSTROLE.WHOLESALE);
				list.add(map);
				ALERT.put(ALCREDIT, list);
				break;
			}
		}

	}

	private static void loadGENSMS() {
		Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();

	}

	private static void loadINVOICE() {
		List<Map<ACCROLE, COSTROLE>> list = new ArrayList<Map<ACCROLE, COSTROLE>>();
		for (int i = 0; i < TRANSACTION_CODE.INVOICE_ACC.noOfDebits(); i++) {
			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:

				map.put(ACCROLE.D_NWK_ACC, COSTROLE.COST);
				list.add(map);
				INVOICE.put(INVDEBIT, list);
				break;
			}
		}

		for (int i = 0; i < TRANSACTION_CODE.INVOICE_ACC.noOfCredits(); i++) {
			Map<ACCROLE, COSTROLE> map = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				map.put(ACCROLE.C_ESK_ACC, COSTROLE.WHOLESALE);
				list.add(map);
				INVOICE.put(INVCREDIT, list);
				break;
			case 1:
				map.put(ACCROLE.C_RSL_ACC, COSTROLE.RETAIL);
				list.add(map);
				INVOICE.put(INVCREDIT, list);
				break;
			}
		}

	}

	private static void loadINBOX() {
		List<Map<ACCROLE, COSTROLE>> list = new ArrayList<Map<ACCROLE, COSTROLE>>();

		for (int i = 0; i < TRANSACTION_CODE.INBOX_ACC.noOfDebits(); i++) {
			Map<ACCROLE, COSTROLE> dmap = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				dmap.put(ACCROLE.D_NWK_ACC, COSTROLE.COST);
				list.add(dmap);
				PUSH.put(INBDEBIT, list);
				break;
			}
		}

		for (int i = 0; i < TRANSACTION_CODE.PUSH_ACC.noOfCredits(); i++) {

			Map<ACCROLE, COSTROLE> cmap = new HashMap<ACCROLE, COSTROLE>();
			switch (i) {
			case 0:
				cmap.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.BASE_COST);
				list.add(cmap);
				PUSH.put(INBCREDIT, list);
				break;

			case 1:
				cmap.put(ACCROLE.C_ESK_BNK_ACC, COSTROLE.RETAIL);
				list.add(cmap);
				PUSH.put(INBCREDIT, list);
				break;
			case 2:
				cmap.put(ACCROLE.C_RSL_ACC, COSTROLE.WHOLESALE);
				list.add(cmap);
				PUSH.put(INBCREDIT, list);
				break;
			}
		}

	}

	static {
		loadFT();
		loadPUSH();
		loadALERT();
		loadGENSMS();
		loadINVOICE();
		loadINBOX();
	}

}
