package com.iexceed.esoko.people.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.NetworkUseridRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao.UserLocationRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserLocationPK;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.resources.MailResource;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.people.Header;
import com.iexceed.esoko.jaxb.people.QRSMSACCS;
import com.iexceed.esoko.jaxb.people.QueryNwkAccountsRes;
import com.iexceed.esoko.jaxb.people.SENDEMAIL;
import com.iexceed.esoko.jaxb.people.SENDSMS;
import com.iexceed.esoko.jaxb.people.SendEmailReq;
import com.iexceed.esoko.jaxb.people.SendEmailRes;
import com.iexceed.esoko.jaxb.people.SendSmsReq;
import com.iexceed.esoko.jaxb.people.SendSmsRes;
import com.iexceed.esoko.jaxb.people.UNMAPDTL;
import com.iexceed.esoko.jaxb.people.UnMapFromNwkReq;
import com.iexceed.esoko.jaxb.people.UnMapFromNwkRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class ActionService {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private PeopleRepo pplRepo;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private NetworkUseridRepo nwkUsrIdRepo;
	@Autowired
	private UserGroupRepo usrGrpRepo;
	@Autowired
	private SystemUserRepo sysUsrRepo;
	@Autowired
	private UserLocationRepo usrLocRepo;
	@Autowired
	private UserCommodityRepo usrCommRepo;
	@Autowired
	private UserOccupationRepo usrOccRepo;
	@Autowired
	MailResource mailResource;

	public ActionService() {

	}

	public QueryNwkAccountsRes queryNwkAccounts(String userId) {
		log.info("Inside QueryNwkAccountsRes -> queryNwkAccounts");
		log.debug("UserId: " + userId);
		QueryNwkAccountsRes accRes = new QueryNwkAccountsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<QRSMSACCS> accDtlLst = new ArrayList<QRSMSACCS>();
		accRes.getQRSMSACCS().addAll(accDtlLst);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"ActionService", "queryNwkAccounts", userId, errorCode);
		accRes.setHeader(header);
		return accRes;
	}

	public SendSmsRes sendSms(SendSmsReq req) {
		log.info("Inside SendSmsResWrap -> sendSms");
		SendSmsRes smsRes = new SendSmsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<SENDSMS> smsLst = req.getSENDSMS();
		for (SENDSMS sms : smsLst) {
			log.debug("UserId: " + sms.getUserId());
			log.debug("NetworkId: " + sms.getNetworkId());
			log.debug("Message: " + sms.getMessage());
			log.debug("Account: " + sms.getAccount());
			log.debug("Schedule: " + sms.getSchedule());
			log.debug("Frequency: " + sms.getFrequency());
			log.debug("Repeats: " + sms.getRepeats());
			log.debug("Town: " + sms.getTown());
			log.debug("StartDate: " + sms.getStartDate());
			log.debug("EndDate: " + sms.getEndDate());
			log.debug("Time: " + sms.getTime());
			log.debug("SendSMSEmail: " + sms.getSendSMSEmail());
			log.debug("SendCopyToMe: " + sms.getSendCopyToMe());
		}
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"ActionService", "sendSms", req.getHeader().getUserId(),
				errorCode);
		smsRes.setHeader(header);
		return smsRes;
	}

	public SendEmailRes sendEmail(SendEmailReq req) {
		log.info("Inside SendEmailRes -> sendEmail");
		SendEmailRes emailRes = new SendEmailRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		String fromAddress = "admin@esoko.com";
		String subject = "Esoko Info";
		String content = "content";
		File file = new File("");
		List<File> files = new ArrayList<File>();
		files.add(file);
		List<String> toAddress = new ArrayList<String>();
		List<SENDEMAIL> emailLst = req.getSENDEMAIL();
		for (SENDEMAIL email : emailLst) {
			log.debug("UserId: " + email.getUserId());
			log.debug("NetworkId: " + email.getNetworkId());
			log.debug("Subject: " + email.getSubject());
			log.debug("Message: " + email.getMessage());
			toAddress.add(email.getUserId());
			subject = email.getSubject();
			content = email.getMessage();
		}
		mailResource.sendEmail(fromAddress, toAddress, subject, content, files);
		errorCode = ERROR_CODE.EMAIL_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"ActionService", "sendEmail", req.getHeader().getUserId(),
				errorCode);
		emailRes.setHeader(header);
		return emailRes;
	}

	@Transactional
	public UnMapFromNwkRes unMapFromNwk(UnMapFromNwkReq req) {
		log.info("Inside UnMapFromNwkRes -> unMapFromNwk");
		UnMapFromNwkRes nwkRes = new UnMapFromNwkRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<UNMAPDTL> mapDtlLst = req.getUNMAPDTL();
		int no_of_person_removed = 0;
		for (UNMAPDTL mapDtl : mapDtlLst) {
			try {
				if (StringUtils.isNotEmpty(mapDtl.getUserId())
						&& StringUtils.isNotEmpty(mapDtl.getNetworkId())) {
					log.debug("UserId: " + mapDtl.getUserId());
					log.debug("NetworkId: " + mapDtl.getNetworkId());
					PeoplePK pplPk = new PeoplePK();
					pplPk.setDefaultNetworkId(mapDtl.getNetworkId());
					pplPk.setPeopleId(mapDtl.getUserId());

					People ppl = pplRepo.findOne(People.class, pplPk);
					if (ppl == null) {
						log.debug("No record found in People table");
						// errorCode = ERROR_CODE.ES_NR_001;
					} else {
						no_of_person_removed++;
						// Deleting record from NetworkUserId table
						NetworkUseridPK nwkUsrIdPk = new NetworkUseridPK();
						nwkUsrIdPk.setNetworkId(mapDtl.getNetworkId());
						nwkUsrIdPk.setUserId(mapDtl.getUserId());
						NetworkUserid nwkUsrId = nwkUsrIdRepo.findOne(
								NetworkUserid.class, nwkUsrIdPk);
						if (nwkUsrId != null) {
							nwkUsrIdRepo.delete(nwkUsrId);
						}

						// Deleting records from UserGroup table
						List<UserGroup> usrGrpLst = usrGrpRepo
								.queryUsrsByUsrIdNwkId(mapDtl.getUserId(),
										mapDtl.getNetworkId());
						if (usrGrpLst.size() != 0) {
							usrGrpRepo.delete(usrGrpLst);
						}

						// Checking if record is a master record

						String masterRec = ppl.getMasterFlag();
						if (masterRec.equalsIgnoreCase("Y")) {

							// Updating the NetowrkId to Country Network in
							// people and system_user table

							// Querying country network from Networks table
							String country = ppl.getCountry();
							Network nwk = nwkRepo.queryCountryNwk(country);
							if (nwk == null) {
								log.debug("No record found in Network table");
								// errorCode = ERROR_CODE.ES_NR_001;
							} else {
								Date dt = new Date();
								Timestamp ts = new Timestamp(dt.getTime());

								String countryNwkId = nwk.getNetworkId();
								log.debug("CountryNetwork: " + countryNwkId);

								// Updating people table

								PeoplePK updatedRec = new PeoplePK();
								// Setting country network
								updatedRec.setDefaultNetworkId(countryNwkId);
								updatedRec.setPeopleId(mapDtl.getUserId());

								People newRec = new People();
								newRec.setId(updatedRec);
								newRec.setCountry(ppl.getCountry());
								newRec.setCreatedBy(ppl.getCreatedBy());
								newRec.setCreatedTs(ppl.getCreatedTs());
								newRec.setCurrencyId(ppl.getCurrencyId());
								newRec.setEmail(ppl.getEmail());
								newRec.setFirstName(ppl.getFirstName());
								newRec.setGender(ppl.getGender());
								newRec.setLanguageId(ppl.getLanguageId());
								newRec.setLastName(ppl.getLastName());
								newRec.setMasterFlag(ppl.getMasterFlag());
								newRec.setModified_By(req.getHeader()
										.getUserId());
								newRec.setModifiedTs(ts);
								newRec.setMsisdn(ppl.getMsisdn());
								newRec.setNickname(ppl.getNickname());
								newRec.setRecordStatus(ppl.getRecordStatus());
								newRec.setTown(ppl.getTown());

								pplRepo.save(newRec);
								pplRepo.delete(ppl);

								// Updating UserLocation table
								List<UserLocation> locList = usrLocRepo
										.queryUserLocDetails(
												mapDtl.getUserId(),
												mapDtl.getNetworkId());
								for (UserLocation location : locList) {
									UserLocationPK locationPK = location
											.getId();
									UserLocationPK newLocPK = new UserLocationPK();
									newLocPK.setLocationId(locationPK
											.getLocationId());
									newLocPK.setNetworkId(countryNwkId);
									newLocPK.setUserId(locationPK.getUserId());
									UserLocation newLocation = new UserLocation();
									newLocation.setId(newLocPK);
									newLocation.setCreatedBy(location
											.getCreatedBy());
									newLocation.setCreatedTs(new Date());
									newLocation.setModifiedBy(req.getHeader()
											.getUserId());
									newLocation.setName(location.getName());
									usrLocRepo.save(newLocation);
									usrLocRepo.delete(location);
								}

								// Updating UserCommodity table
								List<UserCommodity> comList = usrCommRepo
										.queryUserCommodity(mapDtl.getUserId(),
												mapDtl.getNetworkId());
								for (UserCommodity commodity : comList) {
									UserCommodityPK commodityPK = commodity
											.getId();
									UserCommodityPK newComPK = new UserCommodityPK();
									newComPK.setCommodityId(commodityPK
											.getCommodityId());
									newComPK.setNetworkId(countryNwkId);
									newComPK.setUserId(commodityPK.getUserId());
									UserCommodity newCommodity = new UserCommodity();
									newCommodity.setId(newComPK);
									newCommodity.setCreatedBy(commodity
											.getCreatedBy());
									newCommodity.setCreatedTs(commodity
											.getCreatedTs());
									newCommodity.setDescription(commodity
											.getDescription());
									newCommodity.setModifiedBy(req.getHeader()
											.getUserId());
									newCommodity.setModifiedTs(new Date());
									newCommodity.setName(commodity.getName());
									usrCommRepo.save(newCommodity);
									usrCommRepo.delete(commodity);
								}

								// Updating UserOccupation table
								List<UserOccupation> occList = usrOccRepo
										.queryusrOccupationbyuserId(
												mapDtl.getUserId(),
												mapDtl.getNetworkId());
								for (UserOccupation occupation : occList) {
									UserOccupationPK occupationPK = occupation
											.getId();
									UserOccupationPK newOccPK = new UserOccupationPK();
									newOccPK.setNetworkId(countryNwkId);
									newOccPK.setOccupationId(occupationPK
											.getOccupationId());
									newOccPK.setUserId(occupationPK.getUserId());
									UserOccupation newOccupation = new UserOccupation();
									newOccupation.setId(newOccPK);
									newOccupation.setCreatedBy(occupation
											.getCreatedBy());
									newOccupation.setCreatedTs(occupation
											.getCreatedTs());
									newOccupation.setModifiedBy(req.getHeader()
											.getUserId());
									newOccupation.setModifiedTs(new Date());
									newOccupation.setName(occupation.getName());
									usrOccRepo.save(newOccupation);
									usrOccRepo.delete(occupation);
								}

								// Updating system_user table
								SystemUser exstRec = sysUsrRepo.findOne(
										SystemUser.class, mapDtl.getUserId());
								if (exstRec != null) {
									SystemUser newSysUsr = new SystemUser();
									newSysUsr.setAboutMe(exstRec.getAboutMe());
									newSysUsr.setAddress(exstRec.getAddress());
									newSysUsr.setAgentId(exstRec.getAgentId());
									newSysUsr.setAuthBy(exstRec.getAuthBy());
									newSysUsr.setAuthStatus(exstRec
											.getAuthStatus());
									newSysUsr.setAuthTs(exstRec.getAuthTs());
									newSysUsr.setBirthYear(exstRec
											.getBirthYear());
									newSysUsr.setCountry(exstRec.getCountry());
									newSysUsr.setCreatedBy(exstRec
											.getCreatedBy());
									newSysUsr.setCreatedTs(exstRec
											.getCreatedTs());
									newSysUsr.setCurrencyId(exstRec
											.getCurrencyId());
									// Updated NetworkId with Country Network
									newSysUsr.setDefaultNetworkId(countryNwkId);
									newSysUsr.setEmail(exstRec.getEmail());
									newSysUsr.setEmail2(exstRec.getEmail2());
									newSysUsr.setEmailVerification(exstRec
											.getEmailVerification());
									newSysUsr.setFirstName(exstRec
											.getFirstName());
									newSysUsr.setGender(exstRec.getGender());
									newSysUsr.setIsVisible(exstRec
											.getIsVisible());
									newSysUsr.setLanguageId(exstRec
											.getLanguageId());
									newSysUsr
											.setLastName(exstRec.getLastName());
									newSysUsr.setModifiedBy(req.getHeader()
											.getUserId());
									newSysUsr.setModifiedTs(ts);
									newSysUsr.setMsisdn(exstRec.getMsisdn());
									newSysUsr.setMsisdn2(exstRec.getMsisdn2());
									newSysUsr.setMsisdnVerification(exstRec
											.getMsisdnVerification());
									newSysUsr
											.setNickname(exstRec.getNickname());
									newSysUsr
											.setPassword(exstRec.getPassword());
									newSysUsr.setRecordStatus(exstRec
											.getRecordStatus());
									newSysUsr.setTown(exstRec.getTown());
									newSysUsr.setType(exstRec.getType());
									newSysUsr.setUI_language(exstRec
											.getUI_language());
									newSysUsr.setUserGis(exstRec.getUserGis());
									newSysUsr.setUserId(exstRec.getUserId());
									newSysUsr.setWebsite(exstRec.getWebsite());

									sysUsrRepo.merge(newSysUsr);
								}

								// errorCode = ERROR_CODE.ES_UD_001;
							}
						} else {
							// Deleting record from people table
							pplRepo.delete(ppl);

							List<UserLocation> locList = usrLocRepo
									.queryUserLocDetails(mapDtl.getUserId(),
											mapDtl.getNetworkId());
							if (locList.size() != 0) {
								usrLocRepo.delete(locList);
							}

							List<UserCommodity> comList = usrCommRepo
									.queryUserCommodity(mapDtl.getUserId(),
											mapDtl.getNetworkId());
							if (comList.size() != 0) {
								usrCommRepo.delete(comList);
							}

							List<UserOccupation> occList = usrOccRepo
									.queryusrOccupationbyuserId(
											mapDtl.getUserId(),
											mapDtl.getNetworkId());
							if (occList.size() != 0) {
								usrOccRepo.delete(occList);
							}

						}
					}
				}
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}
		}
		HeaderFactory.setExtraParamMap("$1", "" + no_of_person_removed);
		if (no_of_person_removed == 1) {
			errorCode = ERROR_CODE.PPL_NWK_001;
		} else {
			errorCode = ERROR_CODE.PPL_NWK_002;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"ActionService", "unMapFromNwk", req.getHeader().getUserId(),
				errorCode);
		nwkRes.setHeader(header);
		return nwkRes;
	}
}
