package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the network_people_privacy database table.
 * 
 */
@Entity
@Table(name="network_people_privacy")
@NamedQuery(name="NetworkPeoplePrivacy.findAll", query="SELECT n FROM NetworkPeoplePrivacy n")
public class NetworkPeoplePrivacy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="network_id")
	private String networkId;

	@Column(name="country_shareable")
	private String countryShareable;

	@Column(name="currency_id_shareable")
	private String currencyIdShareable;

	@Column(name="email_shareable")
	private String emailShareable;

	@Column(name="first_name_shareable")
	private String firstNameShareable;

	@Column(name="gender_shareable")
	private String genderShareable;

	@Column(name="language_id_shareable")
	private String languageIdShareable;

	@Column(name="last_name_shareable")
	private String lastNameShareable;

	@Column(name="msisdn_shareable")
	private String msisdnShareable;

	@Column(name="nickname_shareable")
	private String nicknameShareable;

	@Column(name="photo_shareable")
	private String photoShareable;

	@Column(name="town_shareable")
	private String townShareable;

	public NetworkPeoplePrivacy() {
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getCountryShareable() {
		return this.countryShareable;
	}

	public void setCountryShareable(String countryShareable) {
		this.countryShareable = countryShareable;
	}

	public String getCurrencyIdShareable() {
		return this.currencyIdShareable;
	}

	public void setCurrencyIdShareable(String currencyIdShareable) {
		this.currencyIdShareable = currencyIdShareable;
	}

	public String getEmailShareable() {
		return this.emailShareable;
	}

	public void setEmailShareable(String emailShareable) {
		this.emailShareable = emailShareable;
	}

	public String getFirstNameShareable() {
		return this.firstNameShareable;
	}

	public void setFirstNameShareable(String firstNameShareable) {
		this.firstNameShareable = firstNameShareable;
	}

	public String getGenderShareable() {
		return this.genderShareable;
	}

	public void setGenderShareable(String genderShareable) {
		this.genderShareable = genderShareable;
	}

	public String getLanguageIdShareable() {
		return this.languageIdShareable;
	}

	public void setLanguageIdShareable(String languageIdShareable) {
		this.languageIdShareable = languageIdShareable;
	}

	public String getLastNameShareable() {
		return this.lastNameShareable;
	}

	public void setLastNameShareable(String lastNameShareable) {
		this.lastNameShareable = lastNameShareable;
	}

	public String getMsisdnShareable() {
		return this.msisdnShareable;
	}

	public void setMsisdnShareable(String msisdnShareable) {
		this.msisdnShareable = msisdnShareable;
	}

	public String getNicknameShareable() {
		return this.nicknameShareable;
	}

	public void setNicknameShareable(String nicknameShareable) {
		this.nicknameShareable = nicknameShareable;
	}

	public String getPhotoShareable() {
		return this.photoShareable;
	}

	public void setPhotoShareable(String photoShareable) {
		this.photoShareable = photoShareable;
	}

	public String getTownShareable() {
		return this.townShareable;
	}

	public void setTownShareable(String townShareable) {
		this.townShareable = townShareable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((countryShareable == null) ? 0 : countryShareable.hashCode());
		result = prime
				* result
				+ ((currencyIdShareable == null) ? 0 : currencyIdShareable
						.hashCode());
		result = prime * result
				+ ((emailShareable == null) ? 0 : emailShareable.hashCode());
		result = prime
				* result
				+ ((firstNameShareable == null) ? 0 : firstNameShareable
						.hashCode());
		result = prime * result
				+ ((genderShareable == null) ? 0 : genderShareable.hashCode());
		result = prime
				* result
				+ ((languageIdShareable == null) ? 0 : languageIdShareable
						.hashCode());
		result = prime
				* result
				+ ((lastNameShareable == null) ? 0 : lastNameShareable
						.hashCode());
		result = prime * result
				+ ((msisdnShareable == null) ? 0 : msisdnShareable.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime
				* result
				+ ((nicknameShareable == null) ? 0 : nicknameShareable
						.hashCode());
		result = prime * result
				+ ((photoShareable == null) ? 0 : photoShareable.hashCode());
		result = prime * result
				+ ((townShareable == null) ? 0 : townShareable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetworkPeoplePrivacy other = (NetworkPeoplePrivacy) obj;
		if (countryShareable == null) {
			if (other.countryShareable != null)
				return false;
		} else if (!countryShareable.equals(other.countryShareable))
			return false;
		if (currencyIdShareable == null) {
			if (other.currencyIdShareable != null)
				return false;
		} else if (!currencyIdShareable.equals(other.currencyIdShareable))
			return false;
		if (emailShareable == null) {
			if (other.emailShareable != null)
				return false;
		} else if (!emailShareable.equals(other.emailShareable))
			return false;
		if (firstNameShareable == null) {
			if (other.firstNameShareable != null)
				return false;
		} else if (!firstNameShareable.equals(other.firstNameShareable))
			return false;
		if (genderShareable == null) {
			if (other.genderShareable != null)
				return false;
		} else if (!genderShareable.equals(other.genderShareable))
			return false;
		if (languageIdShareable == null) {
			if (other.languageIdShareable != null)
				return false;
		} else if (!languageIdShareable.equals(other.languageIdShareable))
			return false;
		if (lastNameShareable == null) {
			if (other.lastNameShareable != null)
				return false;
		} else if (!lastNameShareable.equals(other.lastNameShareable))
			return false;
		if (msisdnShareable == null) {
			if (other.msisdnShareable != null)
				return false;
		} else if (!msisdnShareable.equals(other.msisdnShareable))
			return false;
		if (networkId == null) {
			if (other.networkId != null)
				return false;
		} else if (!networkId.equals(other.networkId))
			return false;
		if (nicknameShareable == null) {
			if (other.nicknameShareable != null)
				return false;
		} else if (!nicknameShareable.equals(other.nicknameShareable))
			return false;
		if (photoShareable == null) {
			if (other.photoShareable != null)
				return false;
		} else if (!photoShareable.equals(other.photoShareable))
			return false;
		if (townShareable == null) {
			if (other.townShareable != null)
				return false;
		} else if (!townShareable.equals(other.townShareable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NetworkPeoplePrivacy [networkId=" + networkId
				+ ", countryShareable=" + countryShareable
				+ ", currencyIdShareable=" + currencyIdShareable
				+ ", emailShareable=" + emailShareable
				+ ", firstNameShareable=" + firstNameShareable
				+ ", genderShareable=" + genderShareable
				+ ", languageIdShareable=" + languageIdShareable
				+ ", lastNameShareable=" + lastNameShareable
				+ ", msisdnShareable=" + msisdnShareable
				+ ", nicknameShareable=" + nicknameShareable
				+ ", photoShareable=" + photoShareable + ", townShareable="
				+ townShareable + "]";
	}

}