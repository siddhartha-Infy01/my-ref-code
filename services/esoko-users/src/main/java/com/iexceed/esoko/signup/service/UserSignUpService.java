package com.iexceed.esoko.signup.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.Message_delivary_DetailRepo;
import com.iexceed.esoko.domain.dao.NetworkCategoryRepo;
import com.iexceed.esoko.domain.dao.NetworkPictureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.NetworkUseridRepo;
import com.iexceed.esoko.domain.dao.Network_privacyRepo;
import com.iexceed.esoko.domain.dao.SignupDetailRepo;
import com.iexceed.esoko.domain.dao.SmartGroupRuleRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao.UserLocationRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.domain.entity.Message_delivary_DetailPK;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkCategory;
import com.iexceed.esoko.domain.entity.NetworkPicture;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.domain.entity.Network_privacy;
import com.iexceed.esoko.domain.entity.SignupDetail;
import com.iexceed.esoko.domain.entity.SmartgroupRule;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserGroupPK;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.appzillon.Appzillon;
import com.iexceed.esoko.engine.simpletrigger.SignupJob;
import com.iexceed.esoko.engine.simpletrigger.SimpleTrigger;
import com.iexceed.esoko.engine.sms.KannelSMSSender;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserReq;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserRes;
import com.iexceed.esoko.jaxb.login.crtusr.USERDETAILRES;
import com.iexceed.esoko.jaxb.login.crtusr.VALIDATEUSER;
import com.iexceed.esoko.jaxb.login.crtusr.ValidateEsokoUserReq;
import com.iexceed.esoko.jaxb.login.crtusr.ValidateEsokoUserRes;
import com.iexceed.esoko.jaxb.login.usrquery.QueryEsokoUserRes;
import com.iexceed.esoko.jaxb.login.usrquery.USERDETAIL;
import com.iexceed.esoko.jaxb.se.CRNWKDETAILS;
import com.iexceed.esoko.jaxb.se.CreateNetworkReq;
import com.iexceed.esoko.jaxb.se.CreateNetworkRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.se.service.NetworkService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.iexceed.esoko.users.HLRLookupService;

/*
 * @author Ankur
 */
@Service
public class UserSignUpService {
	private static Logger log = LoggerUtils.getLogger();

	@Autowired
	private SystemUserRepo sysUserRepo;
	@Autowired
	private UserOccupationRepo userOccRepo;
	@Autowired
	private UserPictureRepo userPicRepo;
	@Autowired
	private UserCommodityRepo userCommRepo;
	@Autowired
	private UserLocationRepo userLocRepo;
	@Autowired
	private NetworkUseridRepo nwUsrIdRepo;
	@Autowired
	private PeopleRepo pplRepo;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private Network_privacyRepo nwkPrivacyRepo;
	@Autowired
	private NetworkService nwkService;
	@Autowired
	private NetworkPictureRepo nwkPicRepo;
	@Autowired
	private GroupMasterRepo grpMasterRepo;
	@Autowired
	private SmartGroupRuleRepo smrtGrpRulRepo;
	@Autowired
	private NetworkCategoryRepo nwkCatRepo;
	@Autowired
	private System_accountRepo sysAccRepo;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	private HLRLookupService lookupService;
	@Autowired
	private UserGroupRepo usrGrpRepo;
	@Autowired
	private SignupDetailRepo signupRepo;
	@Autowired
	private SimpleTrigger trigger;
	@Autowired
	private Message_delivary_DetailRepo msgDelDtls;

	@Transactional(timeout = 30)
	public CreateEsokoUserRes createEsokoUser(CreateEsokoUserReq req) {
		log.info("Inside UserSignUpService -> createEsokoUser");
		CreateEsokoUserRes userRes = new CreateEsokoUserRes();
		Date timeStamp = new Date();
		com.iexceed.esoko.jaxb.login.crtusr.Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		String lDeviceId = req.getUSERDETAIL().get(0).getDeviceId();
		Appzillon appzillonUser = new Appzillon(lDeviceId);
		List<String> appzillonUserList = appzillonUser.getAppzillonUsers();
		if (appzillonUserList != null) {
			List<String> createdUserList = new ArrayList<String>();
			List<com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL> usrDtlList = req
					.getUSERDETAIL();
			for (com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL usrDtl : usrDtlList) {
				if (StringUtils.isNotEmpty(usrDtl.getEmail())
						|| StringUtils.isNotEmpty(usrDtl.getPhone())) {

					String userId = null;
					if (StringUtils.isNotEmpty(usrDtl.getEmail())) {
						userId = usrDtl.getEmail();
					} else {
						userId = usrDtl.getPhone();
					}

					log.debug("UserId: " + userId);
					try {
						boolean recExistsInAppzillon = appzillonUserList
								.contains(userId);
						boolean recExistInSysUsr = sysUserRepo.exists(
								SystemUser.class, userId);

						PeoplePK pplPk = new PeoplePK();
						pplPk.setDefaultNetworkId(usrDtl.getNetworkId());
						pplPk.setPeopleId(userId);
						boolean recExistInPpl = pplRepo.exists(People.class,
								pplPk);

						if (recExistInSysUsr || recExistInPpl
								|| recExistsInAppzillon) {
							log.info("Record already exists");
							errorCode = ERROR_CODE.ES_ER_001;
						} else {
							int emailCount = 0;
							People ppl1 = new People();
							ppl1 = null;
							if (StringUtils.isNotEmpty(usrDtl.getEmail())) {
								emailCount = sysUserRepo.emailExists(userId);
								ppl1 = pplRepo.findByEmailNwkId(userId,
										usrDtl.getNetworkId());
							}
							int phoneCount = 0;
							People ppl2 = new People();
							ppl2 = null;
							if (StringUtils.isNotEmpty(usrDtl.getPhone())) {

								ppl2 = pplRepo.findByMsisdnNwkId(
										usrDtl.getPhone(),
										usrDtl.getNetworkId());

								phoneCount = sysUserRepo.mobExists(usrDtl
										.getPhone());
							}
							if ((emailCount > 0) || (ppl1 != null)) {
								log.info("Email already exists");
								errorCode = ERROR_CODE.ES_ER_002;
							} else if ((phoneCount > 0) || (ppl2 != null)) {
								log.info("Phone number already exists");
								errorCode = ERROR_CODE.ES_ER_003;
							} else {

								StringBuilder userName = new StringBuilder();
								userName.append(usrDtl.getFirstName());
								if (StringUtils
										.isNotEmpty(usrDtl.getLastName())) {
									userName.append(" " + usrDtl.getLastName());
								}
								boolean nwkCrtnStatus = true;
								String nwkId = null;
								if (StringUtils.isEmpty(usrDtl.getNetworkId())) {
									nwkId = "NWK"
											+ esokoRepo.getSequenceNumber();
									pplPk.setDefaultNetworkId(nwkId);
									String type = createNetwork(nwkId,
											req.getHeader(), usrDtl,
											userName.toString(), userId);
									log.debug("Creating Network");
									if (!type.equals("S")) {
										nwkCrtnStatus = false;
									} else {
										log.debug("Created successfully");
									}
								} else {
									nwkId = usrDtl.getNetworkId().toString();
								}
								if (nwkCrtnStatus) {
									String operatorId = null;
									NetworkUserid nwkUserId = new NetworkUserid();
									NetworkUseridPK nwkUserIdPk = new NetworkUseridPK();

									nwkUserIdPk.setUserId(userId);
									nwkUserIdPk.setNetworkId(nwkId);
									nwkUserId.setId(nwkUserIdPk);
									nwkUserId.setCreatedBy(req.getHeader()
											.getUserId());
									nwkUserId.setCreatedTs(timeStamp);
									nwkUserId.setNetworkName(userName
											.toString());
									nwUsrIdRepo.save(nwkUserId);
									log.debug("Data inserted in NetworkUserid");

									SystemUser sysUser = new SystemUser();
									sysUser.setUserId(userId);
									sysUser.setDefaultNetworkId(nwkId);
									log.debug("FirstName: "
											+ usrDtl.getFirstName());
									if (StringUtils.isNotEmpty(usrDtl
											.getFirstName())) {
										sysUser.setFirstName(usrDtl
												.getFirstName());
									}
									log.debug("LastName: "
											+ usrDtl.getLastName());
									if (StringUtils.isNotEmpty(usrDtl
											.getLastName())) {
										sysUser.setLastName(usrDtl
												.getLastName());
									}
									// log.debug("Password: " +
									// req.getUSERDETAIL().getPassword());
									if (StringUtils.isNotEmpty(usrDtl
											.getPassword())) {										
										String encryptedPswd = Utils
												.hashSHA256(
														usrDtl.getPassword(),
														userId
																+ Utils.lServerToken);
										sysUser.setPassword(encryptedPswd);
									}
									log.debug("Gender: " + usrDtl.getGender());
									if (StringUtils.isNotEmpty(usrDtl
											.getGender())) {
										sysUser.setGender(usrDtl.getGender());
									}
									log.debug("Email: " + usrDtl.getEmail());
									if (StringUtils.isNotEmpty(usrDtl
											.getEmail())) {
										sysUser.setEmail(usrDtl.getEmail());
									}
									log.debug("Phone: " + usrDtl.getPhone());
									if (StringUtils.isNotEmpty(usrDtl
											.getPhone())) {
										sysUser.setMsisdn(usrDtl.getPhone());
										operatorId = lookupService
												.fetchOperator(usrDtl
														.getPhone());
										if (!operatorId.equals("")) {
											sysUser.setOperatorId(operatorId);
											log.debug("OperatorId: "
													+ operatorId);
										}

									}
									log.debug("Language: "
											+ usrDtl.getLanguage());
									if (StringUtils.isNotEmpty(usrDtl
											.getLanguage())) {
										sysUser.setLanguageId(usrDtl
												.getLanguage());
									}
									log.debug("Country: " + usrDtl.getCountry());
									if (StringUtils.isNotEmpty(usrDtl
											.getCountry())) {
										sysUser.setCountry(usrDtl.getCountry());
									}
									log.debug("Town: " + usrDtl.getTown());
									if (StringUtils
											.isNotEmpty(usrDtl.getTown())) {
										sysUser.setTown(usrDtl.getTown());
									}
									log.debug("YearOfBirth: "
											+ usrDtl.getYearOfBirth());
									if (StringUtils.isNotEmpty(usrDtl
											.getYearOfBirth())) {
										sysUser.setBirthYear(usrDtl
												.getYearOfBirth());
									}
									log.debug("Type: U");
									sysUser.setType("U");
									log.debug("AuthStatus: U");
									sysUser.setAuthStatus("U");
									log.debug("CurrencyId: USD");
									sysUser.setCurrencyId("USD");
									log.debug("IsVisible: Y");
									sysUser.setIsVisible("Y");
									log.debug("RecordStatus: A");
									sysUser.setRecordStatus("A");
									log.debug("Email Verification: N");
									sysUser.setEmailVerification("N");
									log.debug("Mobile Verification");
									sysUser.setMsisdnVerification("N");
									log.debug("CreateBy: "
											+ req.getHeader().getUserId());
									sysUser.setCreatedBy(req.getHeader()
											.getUserId());
									log.debug("CreatedTs: " + timeStamp);
									sysUser.setCreatedTs(timeStamp);
									sysUserRepo.save(sysUser);
									log.debug("Esoko user created successfully");

									createPeopleProfile(usrDtl, pplPk, nwkId,
											req.getHeader(), operatorId);
									log.debug("People profile created successfully");

									if (StringUtils.isNotEmpty(usrDtl
											.getOccupationId())) {
										log.debug("Occupations: "
												+ usrDtl.getOccupationId());
										String[] occList = usrDtl
												.getOccupationId().split(",");
										for (String occupation : occList) {
											UserOccupationPK userOccPK = new UserOccupationPK();
											userOccPK.setUserId(userId);
											userOccPK
													.setOccupationId(occupation);
											userOccPK.setNetworkId(nwkId);
											UserOccupation userOcc = new UserOccupation();
											userOcc.setId(userOccPK);
											userOcc.setName(occupation);
											userOcc.setCreatedBy(req
													.getHeader().getUserId());
											userOcc.setCreatedTs(timeStamp);
											userOccRepo.save(userOcc);
											log.debug("Data inserted in UserOccupation");
										}
									}

									System_account sysAcc = new System_account();
									long accNumber = esokoRepo
											.getSequenceNumber();
									sysAcc.setAccName("U" + accNumber);
									sysAcc.setAccountNo("U" + accNumber);
									sysAcc.setAcCurrency("USD");
									sysAcc.setBalance(0.00);
									sysAcc.setCreatedBy(req.getHeader()
											.getUserId());
									sysAcc.setCreatedTs(new Date());
									sysAcc.setOwnerId(userId);
									sysAcc.setRecordStatus("A");
									sysAcc.setType("U");
									sysAccRepo.save(sysAcc);
									log.debug("Data inserted in SystemAccount");

									UserGroupPK usrGrpPk = new UserGroupPK();
									usrGrpPk.setGroupId("Administrator");
									usrGrpPk.setNetworkId(nwkId);
									usrGrpPk.setUserId(userId);
									UserGroup usrGrp = usrGrpRepo.findOne(
											UserGroup.class, usrGrpPk);
									if (usrGrp == null) {
										usrGrp = new UserGroup();
										usrGrp.setCreatedBy(req.getHeader()
												.getUserId());
										Date dt = new Date();
										usrGrp.setCreatedTs(dt);
										usrGrp.setId(usrGrpPk);
										usrGrpRepo.save(usrGrp);
									}

									boolean status = appzillonUser
											.createAppzillonUser(userId,
													userName.toString(),
													usrDtl.getEmail(),
													usrDtl.getPhone(),
													usrDtl.getTown(),
													usrDtl.getCountry(),
													usrDtl.getPassword());
									createdUserList.add(userId);
									if (status) {
										log.debug("Appzillon user created successfully");
										errorCode = ERROR_CODE.ES_SV_001;
										if (usrDtl.getUserType()
												.equalsIgnoreCase("EsokoUser")) {
											log.debug("UserType: "
													+ usrDtl.getUserType());
											appzillonUser.updateUserAccount(
													userId, "N");
											USERDETAILRES usrDtlRes = new USERDETAILRES();
											usrDtlRes.setUserId(userId);
											userRes.setUSERDETAILRES(usrDtlRes);
											updateSignUpDetails(userId, nwkId,
													lDeviceId,
													usrDtl.getPhone());
										}
									} else {
										appzillonUserList.clear();
										appzillonUserList = appzillonUser
												.getAppzillonUsers();
										if (appzillonUserList.contains(userId)) {
											log.debug("Appzillon user created successfully");
											errorCode = ERROR_CODE.ES_SV_001;
											if (usrDtl.getUserType()
													.equalsIgnoreCase(
															"EsokoUser")) {
												log.debug("USerType: "
														+ usrDtl.getUserType());
												appzillonUser
														.updateUserAccount(
																userId, "N");
												USERDETAILRES usrDtlRes = new USERDETAILRES();
												usrDtlRes.setUserId(userId);
												userRes.setUSERDETAILRES(usrDtlRes);
												updateSignUpDetails(userId,
														nwkId, lDeviceId,
														usrDtl.getPhone());
											}
										} else {
											deleteUserRecords(createdUserList,
													nwkId);
											log.debug("Error in creating Appzillon user");
											errorCode = ERROR_CODE.ES_SV_002;
										}
									}

								} else {
									log.debug("Unable to create Network for Esoko User");
									errorCode = ERROR_CODE.ES_SV_002;
								}
							}
						}
					} catch (Exception e) {
						errorCode = ERROR_CODE.ES_SV_002;
						log.error(Utils.getStackTrace(e));
						if (createdUserList.size() > 0) {
							appzillonUser.deleteAppzillonUser(createdUserList);
						}
					}
				} else {
					log.debug("Primary key is blank");
					errorCode = ERROR_CODE.ES_PK_001;
				}
			}
		} else {
			log.debug("Unable to get list of Appzillon users");
			errorCode = ERROR_CODE.ES_SV_002;
		}
		header = (com.iexceed.esoko.jaxb.login.crtusr.Header) HeaderFactory
				.getHeader(HeaderType.CRTUSR, "UserSignUpService",
						"createEsokoUser", req.getHeader().getUserId(),
						errorCode);
		userRes.setHeader(header);
		return userRes;
	}

	private void sendOTP(String OTP, String phoneNo, String userId,
			Date expiryDate, String nwkId) {
		KannelSMSSender sender = new KannelSMSSender();
		log.debug("Sending OTP to " + phoneNo + " through SMS center: "
				+ Utils.smsCenter + " using SenderId: " + Utils.senderId);

		String message = Utils.OTPMessage;
		message = message.replace("$1", OTP);
		message = message.replace("$2", expiryDate.toString());
		sender.SendMessage(message, phoneNo, Utils.senderId, Utils.smsCenter,
				userId);
		log.info("Message sent: " + message);
		try {
			String messageId = generateMessageId();
			log.info("Logging message delivery details with messageId: "
					+ messageId);
			Message_delivary_DetailPK PK = new Message_delivary_DetailPK();
			PK.setMessageId(messageId);
			PK.setTrnRefNo(messageId.replace("MSG", "TRN"));
			Message_delivary_Detail msgDtl = new Message_delivary_Detail();
			msgDtl.setCreated_TS(new Date());
			msgDtl.setId(PK);
			msgDtl.setMessageType("SIGNUP");
			msgDtl.setMessage(message);
			msgDtl.setMsisdn(phoneNo);
			msgDtl.setMsgStatus("U");
			msgDtl.setNetworkId(nwkId);
			msgDtl.setUserId(userId);
			msgDelDtls.save(msgDtl);
		} catch (DomainException e) {
			log.error(Utils.getStackTrace(e));
		}

	}

	private String generateMessageId() {
		StringBuffer transaction = new StringBuffer();
		transaction.append("MSG" + Utils.getJulianDt()
				+ Math.round(Math.random() * 100000));
		return transaction.toString();

	}

	private void updateSignUpDetails(String userId, String nwkId,
			String deviceId, String phoneNo) {
		SignupDetail entity = new SignupDetail();
		String OTP = Utils.getRandomString(6);
		log.debug("OTP: " + OTP);
		entity.setOtp(OTP);
		entity.setStatus("P");
		Date currTimeStamp = new Date();
		entity.setDeviceId(deviceId);
		entity.setTimestamp(currTimeStamp);
		entity.setUserId(userId);
		entity.setNetworkId(nwkId);
		signupRepo.save(entity);
		log.debug("SignupDetail populated");
		Calendar dt = Calendar.getInstance();
		dt.setTime(currTimeStamp);
		dt.add(Calendar.MINUTE, Utils.OTPExpiryMins);
		Date finalDate = new Date(dt.getTimeInMillis());
		log.debug("User created at: " + currTimeStamp
				+ " and will be expired at: " + finalDate
				+ ", If it is not verified");

		trigger.creatTrigger(SignupJob.class, userId, finalDate);
		log.debug("SignupDetail trigger created");
		log.debug("Sending OTP");
		sendOTP(OTP, phoneNo, userId, finalDate, nwkId);

	}

	private void deleteUserRecords(List<String> createdUserList,
			String networkId) {
		log.info("Inside UserSignUpService -> deleteUserRecords");
		for (String userId : createdUserList) {
			Network nwk = nwkRepo.findOne(Network.class, networkId);
			if (nwk != null) {
				nwkRepo.delete(nwk);
				log.debug("Data in Network deleted");
			}

			Network_privacy nwkPrivacy = nwkPrivacyRepo.findOne(
					Network_privacy.class, networkId);
			if (nwkPrivacy != null) {
				nwkPrivacyRepo.delete(nwkPrivacy);
				log.debug("Data in Network_privacy deleted");
			}

			NetworkUseridPK nwkUserIdPk = new NetworkUseridPK();
			nwkUserIdPk.setUserId(userId);
			nwkUserIdPk.setNetworkId(networkId);
			NetworkUserid nwkUserId = nwUsrIdRepo.findOne(NetworkUserid.class,
					nwkUserIdPk);
			if (nwkUserId != null) {
				nwUsrIdRepo.delete(nwkUserId);
				log.debug("Data in NetworkUserId deleted");
			}

			List<Map<String, Object>> grpMasterList = grpMasterRepo
					.queryGroupByNetwks(networkId);
			for (Map<String, Object> lMap : grpMasterList) {
				GroupMasterPK grpMasterPk = new GroupMasterPK();
				String groupId = lMap.get("groupId").toString();
				grpMasterPk.setGroupId(groupId);
				grpMasterPk.setNetworkId(networkId);
				GroupMaster grpMaster = grpMasterRepo.findOne(
						GroupMaster.class, grpMasterPk);
				if (grpMaster != null) {
					grpMasterRepo.delete(grpMaster);
					log.debug("Data in GroupMaster deleted");
				}

				List<SmartgroupRule> ruleList = smrtGrpRulRepo.queryAllRules(
						groupId, networkId);
				if (ruleList != null) {
					smrtGrpRulRepo.delete(ruleList);
					log.debug("Data in SmartgroupRule deleted");
				}

			}
			List<UserOccupation> usrOccList = userOccRepo
					.queryusrOccupationbyuserId(userId, networkId);
			if (usrOccList != null) {
				userOccRepo.delete(usrOccList);
				log.debug("Data in UserOccupaiton deleted");
			}

			SystemUser sysUser = sysUserRepo.findOne(SystemUser.class, userId);
			if (sysUser != null) {
				sysUserRepo.delete(sysUser);
				log.debug("Data in SystemUser deleted");
			}

			PeoplePK pplPk = new PeoplePK();
			pplPk.setDefaultNetworkId(networkId);
			pplPk.setPeopleId(userId);
			People ppl = pplRepo.findOne(People.class, pplPk);
			if (ppl != null) {
				pplRepo.delete(ppl);
				log.debug("Data in People deleted");
			}

			System_account userAcc = sysAccRepo.queryAccDetails(userId);
			if (userAcc != null) {
				sysAccRepo.delete(userAcc);
				log.debug("Data in SystemAccount deleted");
			}

			System_account nwkAcc = sysAccRepo.queryAccDetails(networkId);
			if (nwkAcc != null) {
				sysAccRepo.delete(nwkAcc);
				log.debug("Data in SystemAccount deleted");
			}

			SignupDetail signupDetail = signupRepo.findOne(SignupDetail.class,
					userId);
			if (signupDetail != null) {
				signupRepo.delete(signupDetail);
				log.debug("Data in SignupDetail deleted");
			}
		}
	}

	private String createNetwork(String nwkId,
			com.iexceed.esoko.jaxb.login.crtusr.Header requetHeader,
			com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL usrDtl,
			String networkName, String nwkOwnerUserId) {
		log.info("Inside UserSignUpService -> createNetwork");
		com.iexceed.esoko.jaxb.se.Header nwkHeader = new Header();
		nwkHeader.setErrorCode(requetHeader.getErrorCode());
		nwkHeader.setErrorDescription(requetHeader.getErrorDescription());
		nwkHeader.setExtraParams(requetHeader.getExtraParams());
		nwkHeader.setOperationName(requetHeader.getOperationName());
		nwkHeader.setServiceName(requetHeader.getServiceName());
		nwkHeader.setType(requetHeader.getType());
		nwkHeader.setUserId(requetHeader.getUserId());

		CRNWKDETAILS nwkDtls = new CRNWKDETAILS();
		nwkDtls.setCountry(usrDtl.getCountry());
		nwkDtls.setName(networkName);
		nwkDtls.setNetworkID(nwkId);
		nwkDtls.setType("P");
		nwkDtls.setNetworkOwner(nwkOwnerUserId);

		CreateNetworkReq netowrkReq = new CreateNetworkReq();
		netowrkReq.setHeader(nwkHeader);
		netowrkReq.setCRNWKDETAILS(nwkDtls);

		CreateNetworkRes nwkRes = nwkService.createNetwork(netowrkReq);
		String type = nwkRes.getHeader().getType();
		return type;
	}

	public QueryEsokoUserRes queryEsokoUser(String userId) {
		log.info("Inside UserSignUpService -> queryEsokoUser");
		log.debug("UserId: " + userId);
		QueryEsokoUserRes queryUser = new QueryEsokoUserRes();
		com.iexceed.esoko.jaxb.login.usrquery.Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		boolean recExist = sysUserRepo.exists(SystemUser.class, userId);
		if (recExist) {
			int totalProfileFields = 19;
			int fieldsAdded = 0;
			USERDETAIL userDtl = new USERDETAIL();
			SystemUser sysUser = sysUserRepo.findOne(SystemUser.class, userId);

			if (StringUtils.isNotEmpty(sysUser.getCountry())) {
				userDtl.setCountry(sysUser.getCountry());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getEmail())) {
				userDtl.setEmail(sysUser.getEmail());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getFirstName())) {
				userDtl.setFirstName(sysUser.getFirstName());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getGender())) {
				userDtl.setGender(sysUser.getGender());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getLastName())) {
				userDtl.setLastName(sysUser.getLastName());
				fieldsAdded++;
			}
			userDtl.setNetworkId(sysUser.getDefaultNetworkId());

			if (StringUtils.isNotEmpty(sysUser.getMsisdn())) {
				userDtl.setPhone(sysUser.getMsisdn());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getTown())) {
				userDtl.setTown(sysUser.getTown());
				fieldsAdded++;
			}
			userDtl.setUserID(sysUser.getUserId());

			if (StringUtils.isNotEmpty(sysUser.getBirthYear())) {
				userDtl.setYearOfBirth(sysUser.getBirthYear());
				fieldsAdded++;
			}

			if (StringUtils.isNotEmpty(sysUser.getWebsite())) {
				fieldsAdded++;
			}

			userDtl.setCurrencyId(sysUser.getCurrencyId());

			String nwkId = sysUser.getDefaultNetworkId();

			UserPicture userPic = userPicRepo.queryUserPic(userId);
			if (userPic != null) {
				userDtl.setProfilePic(userPic.getContent());
				userDtl.setUserPicStatus("Y");
				fieldsAdded++;
			} else {
				userDtl.setUserPicStatus("N");
			}
			userDtl.setEmailValidateStatus(sysUser.getEmailVerification());
			userDtl.setMobileValidateStatus(sysUser.getMsisdnVerification());
			if (StringUtils.isEmpty(sysUser.getGender())
					|| StringUtils.isEmpty(sysUser.getBirthYear())) {
				userDtl.setAgeGenderStatus("N");
			} else {
				userDtl.setAgeGenderStatus("Y");
			}
			Network network = nwkRepo.findOne(Network.class, nwkId);
			if (network != null) {
				String nwkName = network.getName();
				userDtl.setNetworkName(nwkName);
				StringBuilder userName = new StringBuilder();
				userName.append(sysUser.getFirstName());
				if (StringUtils.isNotEmpty(sysUser.getLastName())) {
					userName.append(" " + sysUser.getLastName());
				}
				if (nwkName.equals(userName.toString())) {
					userDtl.setNetworkNameStatus("N");
				} else {
					userDtl.setNetworkNameStatus("Y");
					fieldsAdded++;
				}
				if (StringUtils.isNotEmpty(network.getWebsite())) {
					fieldsAdded++;
				}
				if (StringUtils.isNotEmpty(network.getOwnerUserId())) {
					fieldsAdded++;
				}
				if (StringUtils.isNotEmpty(network.getPrimaryLocationId())) {
					fieldsAdded++;
				}
				if (StringUtils.isNotEmpty(network.getEmail())) {
					fieldsAdded++;
				}
				if (StringUtils.isNotEmpty(network.getDescription())) {
					fieldsAdded++;
				}
			}

			NetworkPicture nwkPic = nwkPicRepo.queryNwrPic(nwkId);
			if (nwkPic == null) {
				userDtl.setNetworkPicStatus("N");
			} else {
				userDtl.setNetworkPicStatus("Y");
				fieldsAdded++;
			}

			List<NetworkCategory> nwkCatLst = nwkCatRepo
					.queryCategoriesbyNwId(nwkId);
			if (nwkCatLst.size() != 0) {
				fieldsAdded++;
			}

			int count = nwUsrIdRepo.countUserInNwk(nwkId);
			if (count == 0) {
				userDtl.setMemberStatus("N");
			} else {
				userDtl.setMemberStatus("Y");
			}

			List<UserOccupation> userOccRec = userOccRepo
					.queryusrOccupationbyuserId(userId, nwkId);
			if (userOccRec.size() != 0) {
				fieldsAdded++;
				StringBuilder occupations = new StringBuilder();
				for (int i = 0; i < userOccRec.size(); i++) {
					if (i == 0) {
						occupations.append(userOccRec.get(i).getName());
					} else {
						occupations.append("," + userOccRec.get(i).getName());
					}
				}
				userDtl.setOccupations(occupations.toString());
			}
			List<UserCommodity> userComRec = userCommRepo.queryUserCommodity(
					userId, nwkId);
			if (userComRec.size() != 0) {
				StringBuilder commodities = new StringBuilder();
				for (int i = 0; i < userComRec.size(); i++) {
					if (i == 0) {
						commodities.append(userComRec.get(i).getName());
					} else {
						commodities.append("," + userComRec.get(i).getName());
					}
				}
				userDtl.setCommodities(commodities.toString());
			}
			List<UserLocation> userLocRec = userLocRepo.queryUserLocDetails(
					userId, nwkId);
			if (userLocRec.size() != 0) {
				StringBuilder markets = new StringBuilder();
				for (int i = 0; i < userLocRec.size(); i++) {
					if (i == 0) {
						markets.append(userLocRec.get(i).getName());
					} else {
						markets.append("," + userLocRec.get(i).getName());
					}
				}
				userDtl.setMarkets(markets.toString());
			}

			int profileCompeltion = (int) (((float) fieldsAdded / totalProfileFields) * 100);
			userDtl.setProfileCompletion(profileCompeltion + "%");
			queryUser.setUSERDETAIL(userDtl);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			log.debug("No record found");
			errorCode = ERROR_CODE.ES_NR_001;
		}
		header = (com.iexceed.esoko.jaxb.login.usrquery.Header) HeaderFactory
				.getHeader(HeaderType.USRQUERY, "UserSignUpService",
						"queryEsokoUser", "", errorCode);
		queryUser.setHeader(header);
		return queryUser;
	}

	private void createPeopleProfile(
			com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL usrDtl,
			PeoplePK peoplePk, String networkId,
			com.iexceed.esoko.jaxb.login.crtusr.Header header, String operatorId) {
		log.info("Inside UserSignUpService -> createPeopleProfile");
		log.debug("NetworkId: " + networkId);
		try {
			People ppl = new People();
			ppl.setId(peoplePk);
			if (StringUtils.isNotEmpty(usrDtl.getCountry())) {
				ppl.setCountry(usrDtl.getCountry());
			}
			ppl.setCreatedBy(header.getUserId());
			ppl.setCreatedTs(new Timestamp(Utils.getCurrentDate().getTime()));
			ppl.setEmail(usrDtl.getEmail());
			if (StringUtils.isNotEmpty(usrDtl.getFirstName())) {
				ppl.setFirstName(usrDtl.getFirstName());
			}
			if (StringUtils.isNotEmpty(usrDtl.getGender())) {
				ppl.setGender(usrDtl.getGender());
			}
			if (StringUtils.isNotEmpty(usrDtl.getLanguage())) {
				ppl.setLanguageId(usrDtl.getLanguage());
			}
			if (StringUtils.isNotEmpty(usrDtl.getLastName())) {
				ppl.setLastName(usrDtl.getLastName());
			}
			ppl.setMasterFlag("Y");
			if (StringUtils.isNotEmpty(usrDtl.getPhone())) {
				ppl.setMsisdn(usrDtl.getPhone());
			}
			ppl.setRecordStatus("A");
			if (StringUtils.isNotEmpty(usrDtl.getTown())) {
				ppl.setTown(usrDtl.getTown());
			}
			ppl.setIsVisible("Y");
			if (StringUtils.isNotEmpty(usrDtl.getYearOfBirth())) {
				ppl.setBirthyear(usrDtl.getYearOfBirth());
			}
			ppl.setCurrencyId("USD");
			if (!operatorId.equals("")) {
				ppl.setOperatorId(operatorId);
			}
			pplRepo.save(ppl);
		} catch (DomainException e) {
			DomainException domainException = new DomainException();
			domainException.setCode(ERROR_CODE.DM_SV_001);
			domainException.setMessage("Cannot save data to database");
			throw domainException;
		}
	}

	@Transactional
	public ValidateEsokoUserRes validateEsokoUser(ValidateEsokoUserReq req) {
		log.info("Inside UserSignUpService -> createEsokoUser");
		ValidateEsokoUserRes res = new ValidateEsokoUserRes();
		Enum<ERROR_CODE> errorCode = null;
		VALIDATEUSER userDtl = req.getVALIDATEUSER();
		String userId = userDtl.getUserId();
		String OTP = userDtl.getOTP();
		SignupDetail detail = signupRepo.findOne(SignupDetail.class, userId);
		if (detail == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		} else {
			if (OTP.equals(detail.getOtp())) {
				errorCode = ERROR_CODE.SIGNUP_SC_001;
				log.info("User validated successfully");
				Appzillon appzillon = new Appzillon();
				appzillon.updateUserAccount(userId, "Y");
				SignupDetail signUp = signupRepo.findOne(SignupDetail.class,
						userId);
				signupRepo.delete(signUp);
			} else {
				List<String> userList = new ArrayList<String>();
				userList.add(userId);
				this.deleteUserRecords(userList, detail.getNetworkId());
				Appzillon appzillon = new Appzillon(detail.getDeviceId());
				appzillon.deleteAppzillonUser(userList);
				errorCode = ERROR_CODE.SIGNUP_ER_001;
				log.info("OTP is invalid");
			}
		}
		com.iexceed.esoko.jaxb.login.crtusr.Header header = (com.iexceed.esoko.jaxb.login.crtusr.Header) HeaderFactory
				.getHeader(HeaderType.CRTUSR, "UserSignUpService",
						"createEsokoUser", req.getHeader().getUserId(),
						errorCode);
		res.setHeader(header);
		return res;
	}
}
