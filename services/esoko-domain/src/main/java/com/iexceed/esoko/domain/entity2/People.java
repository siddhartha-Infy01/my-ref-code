package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the people database table.
 * 
 */
@Entity
@Table(name="people")
@NamedQuery(name="People.findAll", query="SELECT p FROM People p")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PeoplePK id;

	private String add1;

	private String add2;

	private String birthyear;

	private String company;

	private String country;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_ts")
	private Timestamp createdTs;

	@Column(name="currency_id")
	private String currencyId;

	private String email;

	private String fax;

	@Column(name="first_name")
	private String firstName;

	private String fixedtel;

	private String gender;

	@Column(name="is_visible")
	private String isVisible;

	@Column(name="language_id")
	private String languageId;

	@Column(name="last_name")
	private String lastName;

	@Column(name="master_flag")
	private String masterFlag;

	private String mode;

	private String modified_By;

	@Column(name="modified_ts")
	private Timestamp modifiedTs;

	private String msisdn;

	private String msisdn2;

	private String nickname;

	@Column(name="operator_id")
	private String operatorId;

	private String position;

	@Column(name="record_status")
	private String recordStatus;

	private String shortdesc;

	private String title;

	private String town;

	private String website;

	public People() {
	}

	public PeoplePK getId() {
		return this.id;
	}

	public void setId(PeoplePK id) {
		this.id = id;
	}

	public String getAdd1() {
		return this.add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return this.add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getBirthyear() {
		return this.birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFixedtel() {
		return this.fixedtel;
	}

	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMasterFlag() {
		return this.masterFlag;
	}

	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getModified_By() {
		return this.modified_By;
	}

	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}

	public Timestamp getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Timestamp modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMsisdn2() {
		return this.msisdn2;
	}

	public void setMsisdn2(String msisdn2) {
		this.msisdn2 = msisdn2;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getShortdesc() {
		return this.shortdesc;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", add1=" + add1 + ", add2=" + add2
				+ ", birthyear=" + birthyear + ", company=" + company
				+ ", country=" + country + ", createdBy=" + createdBy
				+ ", createdTs=" + createdTs + ", currencyId=" + currencyId
				+ ", email=" + email + ", fax=" + fax + ", firstName="
				+ firstName + ", fixedtel=" + fixedtel + ", gender=" + gender
				+ ", isVisible=" + isVisible + ", languageId=" + languageId
				+ ", lastName=" + lastName + ", masterFlag=" + masterFlag
				+ ", mode=" + mode + ", modified_By=" + modified_By
				+ ", modifiedTs=" + modifiedTs + ", msisdn=" + msisdn
				+ ", msisdn2=" + msisdn2 + ", nickname=" + nickname
				+ ", operatorId=" + operatorId + ", position=" + position
				+ ", recordStatus=" + recordStatus + ", shortdesc=" + shortdesc
				+ ", title=" + title + ", town=" + town + ", website="
				+ website + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((add1 == null) ? 0 : add1.hashCode());
		result = prime * result + ((add2 == null) ? 0 : add2.hashCode());
		result = prime * result
				+ ((birthyear == null) ? 0 : birthyear.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((fixedtel == null) ? 0 : fixedtel.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isVisible == null) ? 0 : isVisible.hashCode());
		result = prime * result
				+ ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((masterFlag == null) ? 0 : masterFlag.hashCode());
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((modified_By == null) ? 0 : modified_By.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result + ((msisdn2 == null) ? 0 : msisdn2.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result
				+ ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result
				+ ((recordStatus == null) ? 0 : recordStatus.hashCode());
		result = prime * result
				+ ((shortdesc == null) ? 0 : shortdesc.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		People other = (People) obj;
		if (add1 == null) {
			if (other.add1 != null)
				return false;
		} else if (!add1.equals(other.add1))
			return false;
		if (add2 == null) {
			if (other.add2 != null)
				return false;
		} else if (!add2.equals(other.add2))
			return false;
		if (birthyear == null) {
			if (other.birthyear != null)
				return false;
		} else if (!birthyear.equals(other.birthyear))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (fixedtel == null) {
			if (other.fixedtel != null)
				return false;
		} else if (!fixedtel.equals(other.fixedtel))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isVisible == null) {
			if (other.isVisible != null)
				return false;
		} else if (!isVisible.equals(other.isVisible))
			return false;
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (masterFlag == null) {
			if (other.masterFlag != null)
				return false;
		} else if (!masterFlag.equals(other.masterFlag))
			return false;
		if (mode == null) {
			if (other.mode != null)
				return false;
		} else if (!mode.equals(other.mode))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (modified_By == null) {
			if (other.modified_By != null)
				return false;
		} else if (!modified_By.equals(other.modified_By))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (msisdn2 == null) {
			if (other.msisdn2 != null)
				return false;
		} else if (!msisdn2.equals(other.msisdn2))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (recordStatus == null) {
			if (other.recordStatus != null)
				return false;
		} else if (!recordStatus.equals(other.recordStatus))
			return false;
		if (shortdesc == null) {
			if (other.shortdesc != null)
				return false;
		} else if (!shortdesc.equals(other.shortdesc))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

}