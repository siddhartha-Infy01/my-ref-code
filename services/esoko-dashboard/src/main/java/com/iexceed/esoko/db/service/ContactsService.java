package com.iexceed.esoko.db.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.COMMONELEMENTS;
import com.iexceed.esoko.jaxb.db.CRCONTACTDETAILS;
import com.iexceed.esoko.jaxb.db.CRCONTACTDTLSRES;
import com.iexceed.esoko.jaxb.db.CreateContactsReq;
import com.iexceed.esoko.jaxb.db.CreateContactsRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.PERSONALDETAILS;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.iexceed.esoko.users.HLRLookupService;

@Service
public class ContactsService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	SystemUserRepo sysUserRepo;

	@Autowired
	PeopleRepo peopleRepo;

	@Autowired
	EsokoAppRepo esokoAppRepo;

	@Autowired
	UserPictureRepo userPicRepo;

	@Autowired
	private HLRLookupService lookupService;

	@Transactional
	public CreateContactsRes createContacts(CreateContactsReq createContactsReq) {
		log.info("Inside ContactsService -> createContacts");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		String peopleId = null;
		boolean createRecord = true;
		CreateContactsRes createContactsRes = new CreateContactsRes();
		CRCONTACTDETAILS crContactsDetails = createContactsReq
				.getCRCONTACTDETAILS();
		COMMONELEMENTS commonElements = crContactsDetails.getCOMMONELEMENT();
		List<PERSONALDETAILS> personalDtlsList = crContactsDetails
				.getPERSONDETAILS();
		for (PERSONALDETAILS personalDtl : personalDtlsList) {
			if (StringUtils.isNotEmpty(personalDtl.getMobileNo())
					&& StringUtils.isNotEmpty(commonElements.getNetworkId())) {
				// if Email is provided
				if (StringUtils.isNotEmpty(personalDtl.getEmail())) {
					// Setting Email as PeopleId
					peopleId = personalDtl.getEmail();
					// Checking in SystemUser if any record exists with the
					// given Email
					int emailCount = sysUserRepo.emailExists(personalDtl
							.getEmail());
					if (emailCount == 0) {
						// No record found
						// Checking in People if any record exists with the
						// given Email
						People ppl = peopleRepo.findByEmailNwkId(
								personalDtl.getEmail(),
								commonElements.getNetworkId());
						if (ppl != null) {
							// Record found
							errorCode = ERROR_CODE.ES_ER_002;
							log.debug("Email already exists in People table");
						} else {
							// Checking in People if any record exists with
							// the
							// given Mobile
							ppl = peopleRepo.findByMsisdnNwkId(
									personalDtl.getMobileNo(),
									commonElements.getNetworkId());
							if (ppl != null) {
								// Record found
								errorCode = ERROR_CODE.ES_ER_003;
								log.debug("Mobile number already exists in People table");
								createRecord = false;
							}
						}
					} else {
						// Record found
						errorCode = ERROR_CODE.ES_ER_002;
						log.debug("Email already exists in SystemUser table");
						createRecord = false;
					}
				} else {
					// Setting Mobile as PeopleId
					peopleId = personalDtl.getMobileNo();
					// Checking in SystemUser if any record exists with the
					// given Mobile
					int mobileCount = sysUserRepo.mobExists(personalDtl
							.getMobileNo());
					if (mobileCount == 0) {
						// No record found
						// Checking in People if any record exists with the
						// given Mobile
						People ppl = peopleRepo.findByMsisdnNwkId(
								personalDtl.getMobileNo(),
								commonElements.getNetworkId());
						if (ppl != null) {
							// Record found
							log.debug("Mobile number already exists in People table");
							createRecord = false;
						}
					} else {
						// Record found
						errorCode = ERROR_CODE.ES_ER_003;
						log.debug("Mobile number already exists in SystemUser table");
						createRecord = false;
					}
				}
				if (createRecord) {
					// Checking in SystemUser and People table if any record
					// exists with the peopleId
					boolean sysUsrExst = sysUserRepo.exists(SystemUser.class,
							peopleId);
					PeoplePK pplPk = new PeoplePK();
					pplPk.setDefaultNetworkId(commonElements.getNetworkId());
					pplPk.setPeopleId(peopleId);
					boolean pplExst = peopleRepo.exists(People.class, pplPk);
					if (sysUsrExst || pplExst) {
						// Record found
						errorCode = ERROR_CODE.PPL_CR_FL;
						log.debug("Record already exists");
						createRecord = false;
					} else {
						// No record found
						// Creating esoko user

						String id = "CONTACTS"
								+ esokoAppRepo.getSequenceNumber();
						String operatorId = null;
						if (personalDtl.getMobileNo() != null) {
							operatorId = lookupService
									.fetchOperator(personalDtl.getMobileNo());
							log.debug("operatorId:" + operatorId);
						}
						SystemUser sysUser = new SystemUser();
						sysUser.setAboutMe("");
						sysUser.setAddress(commonElements.getLocation());
						sysUser.setAgentId("CONTACTS");
						sysUser.setAuthBy(null);
						sysUser.setAuthStatus("U");
						sysUser.setAuthTs(null);
						sysUser.setBirthYear(null);
						sysUser.setCountry(commonElements.getCountry());
						sysUser.setCreatedBy(null);
						sysUser.setCreatedTs(new Timestamp((Utils
								.getCurrentDate()).getTime()));
						sysUser.setCurrencyId(commonElements.getCurrency());
						sysUser.setDefaultNetworkId(commonElements
								.getNetworkId());
						sysUser.setEmail(personalDtl.getEmail());
						sysUser.setEmailVerification(null);
						sysUser.setEmail2(null);
						sysUser.setFirstName(personalDtl.getFirstName());
						sysUser.setGender(personalDtl.getGender());
						sysUser.setIsVisible(null);
						sysUser.setLanguageId(commonElements.getLanguage());
						sysUser.setLastName(personalDtl.getLastName());
						sysUser.setModifiedBy(null);
						sysUser.setModifiedTs(null);
						sysUser.setMsisdn(personalDtl.getMobileNo());
						sysUser.setMsisdn2(null);
						sysUser.setMsisdnVerification(null);
						sysUser.setNickname(null);
						if (operatorId != null) {
							sysUser.setOperatorId(operatorId);
						}
						sysUser.setPassword(null);
						sysUser.setRecordStatus(null);
						sysUser.setTown(commonElements.getTown());
						sysUser.setType(null);
						sysUser.setUI_language(null);
						sysUser.setUserGis(null);
						sysUser.setUserId(id);
						sysUser.setWebsite(null);
						PeoplePK peoplePK = new PeoplePK();
						peoplePK.setPeopleId(id);
						peoplePK.setDefaultNetworkId(commonElements
								.getNetworkId());
						People people = new People();
						people.setId(peoplePK);
						people.setAdd1(null);
						people.setAdd2(null);
						people.setBirthyear(null);
						people.setCompany(null);
						people.setCountry(commonElements.getCountry());
						people.setCreatedBy(null);
						people.setCreatedTs(new Timestamp((Utils
								.getCurrentDate()).getTime()));
						people.setCurrencyId(commonElements.getCurrency());
						people.setEmail(personalDtl.getEmail());
						people.setFax(null);
						people.setFirstName(personalDtl.getFirstName());
						people.setFixedtel(null);
						people.setGender(personalDtl.getGender());
						people.setIsVisible(null);
						people.setLanguageId(commonElements.getLanguage());
						people.setLastName(personalDtl.getLastName());
						people.setMasterFlag(null);
						people.setModified_By(null);
						people.setModifiedTs(null);
						people.setMsisdn(personalDtl.getMobileNo());
						people.setMsisdn2(null);
						people.setNickname(null);
						if (operatorId != null) {
							people.setOperatorId(operatorId);
						}
						people.setPosition(commonElements.getOccupations());
						people.setRecordStatus(null);
						people.setShortdesc(null);
						people.setTitle(null);
						people.setTown(commonElements.getTown());
						people.setWebsite(null);
						if ("Upload".equalsIgnoreCase(createContactsReq
								.getHeader().getOperationName())) {
							people.setMode(commonElements.getMode());
						}
						if (personalDtl.getImage() != null) {
							UserPicture userPic = new UserPicture();
							userPic.setUserId(peopleId);
							userPic.setContent(Utils.encodeImage(personalDtl
									.getImage()));
							userPic.setCreatedBy(createContactsReq.getHeader()
									.getUserId());
							userPic.setCreatedTs(Utils.getCurrentDate());
							userPicRepo.save(userPic);
						}
						CRCONTACTDTLSRES crContactDtlRes = new CRCONTACTDTLSRES();
						crContactDtlRes.setPeopleId(peopleId);
						createContactsRes.setCRCONTACTDTLSRES(crContactDtlRes);
						sysUserRepo.save(sysUser);
						peopleRepo.save(people);

						errorCode = ERROR_CODE.ES_SV_001;
					}
				}
				if ("Upload".equalsIgnoreCase(createContactsReq.getHeader()
						.getOperationName())) {
					header = (Header) HeaderFactory.getHeader(HeaderType.DB,
							"ContactsService", "Upload", "", errorCode);
				} else {
					header = (Header) HeaderFactory.getHeader(HeaderType.DB,
							"ContactsService", "createContacts", "", errorCode);
				}
				createContactsRes.setHeader(header);
			}
		}
		return createContactsRes;
	}
}