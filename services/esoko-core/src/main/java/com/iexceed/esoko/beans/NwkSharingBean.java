package com.iexceed.esoko.beans;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;

public class NwkSharingBean {
	public static Logger log = LoggerUtils.getLogger();
	private String networkId = null;
	private String people = null;
	private String offers = null;
	private String newsAndLibrary = null;
	private String prices = null;

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public String getNewsAndLibrary() {
		return newsAndLibrary;
	}

	public void setNewsAndLibrary(String newsAndLibrary) {
		this.newsAndLibrary = newsAndLibrary;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.info("Inside bean");
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final NwkSharingBean other = (NwkSharingBean) obj;

		if (!networkId.equals(other.networkId)) {
			return false;
		}

		if (this.networkId.equalsIgnoreCase(other.networkId)) {
			other.setPeople(checkFlags(other.people, this.people));
			other.setNewsAndLibrary(checkFlags(other.newsAndLibrary,
					this.newsAndLibrary));
			other.setOffers(checkFlags(other.offers, this.offers));
			other.setPrices(checkFlags(other.prices, this.prices));
		}

		return true;
	}

	@Override
	public String toString() {
		return "NwkSharingBean [networkId=" + networkId + ", people=" + people
				+ ", offers=" + offers + ", newsAndLibrary=" + newsAndLibrary
				+ ", prices=" + prices + "]";
	}

	public static String checkFlags(String flag1, String flag2) {
		String returnVal = null;
		if (flag1.equalsIgnoreCase("O") && flag2.equalsIgnoreCase("G")) {
			returnVal = "B";// Both
		} else if (flag1.equalsIgnoreCase("O") && flag2.equalsIgnoreCase("GA")) {
			returnVal = "A";// Sending +Approve
		} else if (flag1.equalsIgnoreCase("O") && flag2.equalsIgnoreCase("N")) {
			returnVal = "O";// Only Sending
		} else if (flag1.equalsIgnoreCase("O") && flag2.equalsIgnoreCase("S")) {
			returnVal = "C";// Sending + Receiving with suspend
		}
		if (flag1.equalsIgnoreCase("N") && flag2.equalsIgnoreCase("G")) {
			returnVal = "G";// Receiving
		} else if (flag1.equalsIgnoreCase("N") && flag2.equalsIgnoreCase("GA")) {
			returnVal = "D";// Receiving with Approve
		} else if (flag1.equalsIgnoreCase("N") && flag2.equalsIgnoreCase("N")) {
			returnVal = "N";// None
		} else if (flag1.equalsIgnoreCase("N") && flag2.equalsIgnoreCase("S")) {
			returnVal = "S";// Receiving with suspend
		}
		if (flag1.equalsIgnoreCase("S1") && flag2.equalsIgnoreCase("G")) {
			returnVal = "F";// Sending with Suspend + Receive
		} else if (flag1.equalsIgnoreCase("S1") && flag2.equalsIgnoreCase("GA")) {
			returnVal = "J";// Sending with Suspend + Receiving with Approve
		} else if (flag1.equalsIgnoreCase("S1") && flag2.equalsIgnoreCase("N")) {
			returnVal = "S1";// Sending with Suspend
		} else if (flag1.equalsIgnoreCase("S1") && flag2.equalsIgnoreCase("S")) {
			returnVal = "I";// Sending with Suspend + Receiving with Suspend
		}
		return returnVal;
	}
}
