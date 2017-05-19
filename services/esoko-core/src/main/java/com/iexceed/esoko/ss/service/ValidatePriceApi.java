package com.iexceed.esoko.ss.service;

import java.util.Map;

import com.iexceed.esoko.engine.utils.Utils;

public class ValidatePriceApi {

	private String networkid;
	SubscriptionPriceService priceService;
	Map<String, String> priceMap;

	public ValidatePriceApi(String networkid) {
		this.networkid = networkid;
		priceService = Utils.springContext
				.getBean(SubscriptionPriceService.class);
		priceMap = priceService.queryNetworkPrices(this.networkid);
	}

	public int getSMSRegular() {
		String strinVal = priceMap.get(SubscriptionServicesEnum.SMS_RGLR
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getSMSRegularWholesale() {
		String strinVal = priceMap
				.get(SubscriptionServicesEnum.SMS_REGLR_WHOLESALE
						.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getSMSPremium() {
		String strinVal = priceMap.get(SubscriptionServicesEnum.SMS_PRUM
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getSMSPremiumWholesale() {
		String strinVal = priceMap
				.get(SubscriptionServicesEnum.SMS_PRUM_WHOLESALE
						.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

}
