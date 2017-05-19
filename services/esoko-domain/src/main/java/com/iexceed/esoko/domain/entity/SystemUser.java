package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.vividsolutions.jts.geom.Point;

import java.util.Date;


/**
 * The persistent class for the system_user database table.
 * 
 */
@Entity
@Table(name="system_user")
@NamedQuery(name="SystemUser.findAll", query="SELECT s FROM SystemUser s")
public class SystemUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="about_me")
	private String aboutMe;

	private String address;

	@Column(name="agent_id")
	private String agentId;

	@Column(name="auth_by")
	private String authBy;

	@Column(name="auth_status")
	private String authStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auth_ts")
	private Date authTs;

	@Column(name="birth_year")
	private String birthYear;

	private String country;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="currency_id")
	private String currencyId;

	@Column(name="default_network_id")
	private String defaultNetworkId;

	private String email;

	@Column(name="email_verification")
	private String emailVerification;

	private String email2;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="is_visible")
	private String isVisible;

	@Column(name="language_id")
	private String languageId;

	@Column(name="last_name")
	private String lastName;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	private String msisdn;

	@Column(name="msisdn_verification")
	private String msisdnVerification;

	private String msisdn2;

	private String nickname;

	@Column(name="operator_id")
	private String operatorId;

	private String password;

	@Column(name="record_status")
	private String recordStatus;

	private String town;

	private String type;

	private String UI_language;

	@Column(name="USER_GIS")
	private Point userGis;

	private String website;

	public SystemUser() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAboutMe() {
		return this.aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAuthBy() {
		return this.authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Date getAuthTs() {
		return this.authTs;
	}

	public void setAuthTs(Date authTs) {
		this.authTs = authTs;
	}

	public String getBirthYear() {
		return this.birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
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

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getDefaultNetworkId() {
		return this.defaultNetworkId;
	}

	public void setDefaultNetworkId(String defaultNetworkId) {
		this.defaultNetworkId = defaultNetworkId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailVerification() {
		return this.emailVerification;
	}

	public void setEmailVerification(String emailVerification) {
		this.emailVerification = emailVerification;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMsisdnVerification() {
		return this.msisdnVerification;
	}

	public void setMsisdnVerification(String msisdnVerification) {
		this.msisdnVerification = msisdnVerification;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUI_language() {
		return this.UI_language;
	}

	public void setUI_language(String UI_language) {
		this.UI_language = UI_language;
	}

	public Point getUserGis() {
		return this.userGis;
	}

	public void setUserGis(Point userGis) {
		this.userGis = userGis;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}