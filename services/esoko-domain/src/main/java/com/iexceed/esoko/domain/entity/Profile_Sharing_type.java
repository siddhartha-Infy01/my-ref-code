package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the profile_Sharing_types database table.
 * 
 */
@Entity
@Table(name="profile_Sharing_types")
@NamedQuery(name="Profile_Sharing_type.findAll", query="SELECT p FROM Profile_Sharing_type p")
public class Profile_Sharing_type implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Profile_Sharing_typePK id;

	@Column(name="address_flag")
	private String addressFlag;

	@Column(name="birth_year_flag")
	private String birthYearFlag;

	@Column(name="company_flag")
	private String companyFlag;

	@Column(name="currency_flag")
	private String currencyFlag;

	@Column(name="email_flag")
	private String emailFlag;

	@Column(name="gender_flag")
	private String genderFlag;

	@Column(name="language_flag")
	private String languageFlag;

	@Column(name="mobile_number_flag")
	private String mobileNumberFlag;

	@Column(name="name_flag")
	private String nameFlag;

	public Profile_Sharing_type() {
	}

	public Profile_Sharing_typePK getId() {
		return this.id;
	}

	public void setId(Profile_Sharing_typePK id) {
		this.id = id;
	}

	public String getAddressFlag() {
		return this.addressFlag;
	}

	public void setAddressFlag(String addressFlag) {
		this.addressFlag = addressFlag;
	}

	public String getBirthYearFlag() {
		return this.birthYearFlag;
	}

	public void setBirthYearFlag(String birthYearFlag) {
		this.birthYearFlag = birthYearFlag;
	}

	public String getCompanyFlag() {
		return this.companyFlag;
	}

	public void setCompanyFlag(String companyFlag) {
		this.companyFlag = companyFlag;
	}

	public String getCurrencyFlag() {
		return this.currencyFlag;
	}

	public void setCurrencyFlag(String currencyFlag) {
		this.currencyFlag = currencyFlag;
	}

	public String getEmailFlag() {
		return this.emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getGenderFlag() {
		return this.genderFlag;
	}

	public void setGenderFlag(String genderFlag) {
		this.genderFlag = genderFlag;
	}

	public String getLanguageFlag() {
		return this.languageFlag;
	}

	public void setLanguageFlag(String languageFlag) {
		this.languageFlag = languageFlag;
	}

	public String getMobileNumberFlag() {
		return this.mobileNumberFlag;
	}

	public void setMobileNumberFlag(String mobileNumberFlag) {
		this.mobileNumberFlag = mobileNumberFlag;
	}

	public String getNameFlag() {
		return this.nameFlag;
	}

	public void setNameFlag(String nameFlag) {
		this.nameFlag = nameFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressFlag == null) ? 0 : addressFlag.hashCode());
		result = prime * result
				+ ((birthYearFlag == null) ? 0 : birthYearFlag.hashCode());
		result = prime * result
				+ ((companyFlag == null) ? 0 : companyFlag.hashCode());
		result = prime * result
				+ ((currencyFlag == null) ? 0 : currencyFlag.hashCode());
		result = prime * result
				+ ((emailFlag == null) ? 0 : emailFlag.hashCode());
		result = prime * result
				+ ((genderFlag == null) ? 0 : genderFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((languageFlag == null) ? 0 : languageFlag.hashCode());
		result = prime
				* result
				+ ((mobileNumberFlag == null) ? 0 : mobileNumberFlag.hashCode());
		result = prime * result
				+ ((nameFlag == null) ? 0 : nameFlag.hashCode());
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
		Profile_Sharing_type other = (Profile_Sharing_type) obj;
		if (addressFlag == null) {
			if (other.addressFlag != null)
				return false;
		} else if (!addressFlag.equals(other.addressFlag))
			return false;
		if (birthYearFlag == null) {
			if (other.birthYearFlag != null)
				return false;
		} else if (!birthYearFlag.equals(other.birthYearFlag))
			return false;
		if (companyFlag == null) {
			if (other.companyFlag != null)
				return false;
		} else if (!companyFlag.equals(other.companyFlag))
			return false;
		if (currencyFlag == null) {
			if (other.currencyFlag != null)
				return false;
		} else if (!currencyFlag.equals(other.currencyFlag))
			return false;
		if (emailFlag == null) {
			if (other.emailFlag != null)
				return false;
		} else if (!emailFlag.equals(other.emailFlag))
			return false;
		if (genderFlag == null) {
			if (other.genderFlag != null)
				return false;
		} else if (!genderFlag.equals(other.genderFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (languageFlag == null) {
			if (other.languageFlag != null)
				return false;
		} else if (!languageFlag.equals(other.languageFlag))
			return false;
		if (mobileNumberFlag == null) {
			if (other.mobileNumberFlag != null)
				return false;
		} else if (!mobileNumberFlag.equals(other.mobileNumberFlag))
			return false;
		if (nameFlag == null) {
			if (other.nameFlag != null)
				return false;
		} else if (!nameFlag.equals(other.nameFlag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profile_Sharing_type [id=" + id + ", addressFlag="
				+ addressFlag + ", birthYearFlag=" + birthYearFlag
				+ ", companyFlag=" + companyFlag + ", currencyFlag="
				+ currencyFlag + ", emailFlag=" + emailFlag + ", genderFlag="
				+ genderFlag + ", languageFlag=" + languageFlag
				+ ", mobileNumberFlag=" + mobileNumberFlag + ", nameFlag="
				+ nameFlag + "]";
	}

}