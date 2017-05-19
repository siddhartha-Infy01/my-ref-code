package com.iexceed.esoko.ss.service;

import java.util.Map;

import com.iexceed.esoko.engine.utils.Utils;

public class ValidateLimitApi {

	private String networkid;
	SubscriptionLimitService limitService;
	Map<String, String> limitsMap;

	public ValidateLimitApi(String networkid) {
		this.networkid = networkid;
		limitService = Utils.springContext
				.getBean(SubscriptionLimitService.class);
		limitsMap = limitService.queryNetworkLimits(this.networkid);
	}

	public int getNoMembers() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.MEMBERS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getMessageTraffic() {
		String strinVal = limitsMap
				.get(SubscriptionServicesEnum.MESSAGE_TRAFFIC);
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getAlertAtPoint() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.ALERTS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfAgents() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.AGENTS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfNetworks() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.NETWORKS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfGroups() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.GROUPS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfSmartGroups() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.SMART_GROUPS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}
	}

	public int getNoOfAdminstartors() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.ADMINISTRATORS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfPush() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.PUSH
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfInbox() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.INBOX
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}
	}

	public int getNoOfPriceUploads() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.PRICES
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getNoOfOfferUploads() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.OFFERS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}
	}

	public int getNoOfUploadForms() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.UPLOAD_FORMS
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}

	public int getUploadTraffic() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.UPLOAD_TRAFFIC
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}
	}

	public int getApiRequests() {
		String strinVal = limitsMap.get(SubscriptionServicesEnum.API1_REQUEST
				.getServiceCode());
		if (strinVal.equalsIgnoreCase("unlimited")) {
			return -1;
		} else {
			return Integer.valueOf(strinVal);
		}

	}
}
