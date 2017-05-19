package com.iexceed.esoko.ms.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.ResellerDetailsRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity2.ResellerMaster;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ms.CRUSRDTLS;
import com.iexceed.esoko.jaxb.ms.Header;
import com.iexceed.esoko.jaxb.ms.QRUSRDTLS;
import com.iexceed.esoko.jaxb.ms.QueryUserDetailsRes;
import com.iexceed.esoko.jaxb.ms.SaveUserDetailsReq;
import com.iexceed.esoko.jaxb.ms.SaveUserDetailsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class UserDetailsService {
	public static Logger log = LoggerUtils.getLogger();
	private final String serviceName = "UserDetailsService";
	@Autowired
	SystemUserRepo userRepo;
	@Autowired
	UserOccupationRepo occupRepo;
	@Autowired
	UserPictureRepo picrepo;
	@Autowired
	PeopleRepo pplRepo;
	@Autowired
	NetworkRepo nwkRepo;
	@Autowired
	CurrencyRepo currRepo;
	@Autowired
	ResellerDetailsRepo resellerDetails;

	public QueryUserDetailsRes queryUserDetails(String userId) {
		QueryUserDetailsRes queryUserRes = new QueryUserDetailsRes();
		log.info("Inside QueryUserDetailsRes -> queryUserDetails");
		log.info("userId -> " + userId);
		SystemUser userEntity = userRepo.findOne(SystemUser.class, userId);
		Header header = null;
		Enum<ERROR_CODE> ENUM_CODE = null;
		if (userEntity == null) {
			ENUM_CODE = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		} else {
			QRUSRDTLS qrusrDtls = new QRUSRDTLS();
			if (userEntity.getLastName() != null) {
				qrusrDtls.setName(userEntity.getFirstName() + " "
						+ userEntity.getLastName());
			} else {
				qrusrDtls.setName(userEntity.getFirstName());

			}
			qrusrDtls.setGender(userEntity.getGender());
			qrusrDtls.setBirthYear(userEntity.getBirthYear());
			qrusrDtls.setCountry(userEntity.getCountry());
			qrusrDtls.setTown(userEntity.getTown());
			qrusrDtls.setPhone1(userEntity.getMsisdn());
			qrusrDtls.setPhone2(userEntity.getMsisdn2());
			qrusrDtls.setAddress(userEntity.getAddress());
			qrusrDtls.setEmail(userEntity.getEmail());
			qrusrDtls.setWebsite(userEntity.getWebsite());
			Currency currency = currRepo.findOne(Currency.class,
					userEntity.getCurrencyId());
			if (currency != null) {
				qrusrDtls.setDispCcy(currency.getName());
			}
			qrusrDtls.setLanguage(userEntity.getLanguageId());
			Network network = nwkRepo.queryNetworkUserInfo(userEntity
					.getDefaultNetworkId());
			qrusrDtls.setDefaultNwk(network.getName());
			qrusrDtls.setDefaultNwkId(network.getNetworkId());
			qrusrDtls.setDefaultNwkLoc(network.getPrimaryLocationId());
			qrusrDtls.setDefaultNwkOwner(network.getOwnerUserId());
			qrusrDtls.setPrivSettings(userEntity.getIsVisible());
			UserPicture pictureEntity = picrepo.queryUserPic(userId);
			if (pictureEntity != null) {
				qrusrDtls.setProfilePic(pictureEntity.getContent());
			}
			List<UserOccupation> occupEntity = (List<UserOccupation>) occupRepo
					.queryusrOccupationbyuserId(userId,
							userEntity.getDefaultNetworkId());
			if (occupEntity.size() != 0) {
				StringBuilder occupation = new StringBuilder();
				for (int i = 0; i < occupEntity.size(); i++) {
					if (i == 0) {
						occupation.append(occupEntity.get(i).getId()
								.getOccupationId());
					} else {
						occupation.append(","
								+ occupEntity.get(i).getId().getOccupationId());
					}
				}
				qrusrDtls.setOccupation(occupation.toString());
			}
			qrusrDtls.setResellerId(getResellerForUser(userId,
					network.getNetworkId()));
			queryUserRes.setQRUSRDTLS(qrusrDtls);
			ENUM_CODE = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.MS, serviceName,
				"Query UserDetails", "", ENUM_CODE);
		queryUserRes.setHeader(header);
		return queryUserRes;

	}

	@Transactional
	public SaveUserDetailsRes saveUserDetails(SaveUserDetailsReq req) {
		SaveUserDetailsRes saveUserRes = new SaveUserDetailsRes();
		CRUSRDTLS crUsrDtls = req.getCRUSRDTLS();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		try {
			String userId = req.getHeader().getUserId();
			SystemUser exstngRecInSysUser = userRepo.findOne(SystemUser.class,
					userId);
			PeoplePK pplPk = new PeoplePK();
			pplPk.setDefaultNetworkId(crUsrDtls.getDefaultNwk());
			pplPk.setPeopleId(userId);
			People exstngRecInPpl = pplRepo.findOne(People.class, pplPk);
			if ((exstngRecInSysUser == null) || (exstngRecInPpl == null)) {
				errorCode = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {
				boolean updateRecord = true;
				if (StringUtils.isNotEmpty(crUsrDtls.getEmail())
						&& !crUsrDtls.getEmail().equals(
								exstngRecInSysUser.getEmail())) {
					int emailCount = userRepo.emailExists(crUsrDtls.getEmail(),
							userId);
					if (emailCount == 0) {
						if (!crUsrDtls.getEmail().equals(
								exstngRecInPpl.getEmail())) {
							People tmp = pplRepo.findByEmailNwkId(
									crUsrDtls.getEmail(),
									crUsrDtls.getDefaultNwk());
							if (tmp != null) {
								errorCode = ERROR_CODE.ES_ER_002;
								log.debug("Email already exists in People table");
								updateRecord = false;
							}
						}

					} else {
						errorCode = ERROR_CODE.ES_ER_002;
						log.debug("Email already exists in SystemUser table");
						updateRecord = false;
					}
				}
				if (updateRecord
						&& StringUtils.isNotEmpty(crUsrDtls.getEmail())
						&& StringUtils.isNotEmpty(crUsrDtls.getPhone1())
						&& !crUsrDtls.getPhone1().equals(
								exstngRecInSysUser.getMsisdn())) {
					int mobileCount = userRepo.mobExists(crUsrDtls.getPhone1(),
							userId);
					if (mobileCount == 0) {
						if (!crUsrDtls.getPhone1().equals(
								exstngRecInPpl.getMsisdn())) {
							People tmp = pplRepo.findByMsisdnNwkId(
									crUsrDtls.getPhone1(),
									crUsrDtls.getDefaultNwk());
							if (tmp != null) {
								errorCode = ERROR_CODE.ES_ER_003;
								log.debug("Mobile number already exists in People table");
								updateRecord = false;
							}
						}
					} else {
						errorCode = ERROR_CODE.ES_ER_003;
						log.debug("Mobile number already exists in SystemUser table");
						updateRecord = false;
					}
				}

				if (updateRecord) {
					SystemUser sysEntity = new SystemUser();
					log.debug("UserId -> " + req.getHeader().getUserId());
					sysEntity.setUserId(userId);
					log.debug("Name -> " + crUsrDtls.getName());
					String name = null, firstName = null, lastName = null;
					name = crUsrDtls.getName();
					if (name.indexOf(" ") != -1) {
						String[] temp = name.split(" ");
						firstName = temp[0];
						lastName = temp[1];
					} else {
						firstName = name;
					}
					sysEntity.setFirstName(firstName);
					log.debug("Gender -> " + crUsrDtls.getGender());
					sysEntity.setGender(crUsrDtls.getGender());
					log.debug("BirthYear -> " + crUsrDtls.getBirthYear());
					sysEntity.setBirthYear(crUsrDtls.getBirthYear());
					log.debug("Country -> " + crUsrDtls.getCountry());
					sysEntity.setCountry(crUsrDtls.getCountry());
					log.debug("Town -> " + crUsrDtls.getTown());
					sysEntity.setTown(crUsrDtls.getTown());
					log.debug("Type -> " + exstngRecInSysUser.getType());
					sysEntity.setType(exstngRecInSysUser.getType());
					log.debug("Phone1 -> " + crUsrDtls.getPhone1());
					sysEntity.setMsisdn(crUsrDtls.getPhone1());
					log.debug("Phone2 -> " + crUsrDtls.getPhone2());
					sysEntity.setMsisdn2(crUsrDtls.getPhone2());
					log.debug("Address -> " + crUsrDtls.getAddress());
					sysEntity.setAddress(crUsrDtls.getAddress());
					log.debug("Email -> " + crUsrDtls.getEmail());
					sysEntity.setEmail(crUsrDtls.getEmail());
					log.debug("Website -> " + crUsrDtls.getWebsite());
					sysEntity.setWebsite(crUsrDtls.getWebsite());
					log.debug("Currency -> " + crUsrDtls.getDispCcy());
					sysEntity.setCurrencyId(crUsrDtls.getDispCcy());
					log.debug("LastName -> " + lastName);
					sysEntity.setLastName(lastName);
					log.debug("Language -> " + crUsrDtls.getLanguage());
					sysEntity.setLanguageId(crUsrDtls.getLanguage());
					log.debug("Network Id -> " + crUsrDtls.getDefaultNwk());
					sysEntity.setDefaultNetworkId(crUsrDtls.getDefaultNwk());
					log.debug("Private Settings -> "
							+ crUsrDtls.getPrivSettings());
					sysEntity.setIsVisible(crUsrDtls.getPrivSettings());
					sysEntity.setRecordStatus(exstngRecInSysUser
							.getRecordStatus());
					sysEntity.setModifiedBy(req.getHeader().getUserId());
					sysEntity.setModifiedTs(Utils.getCurrentDate());
					sysEntity.setCreatedBy(exstngRecInSysUser.getCreatedBy());
					sysEntity.setCreatedTs(exstngRecInSysUser.getCreatedTs());
					sysEntity.setUI_language(exstngRecInSysUser
							.getUI_language());
					sysEntity.setUserGis(exstngRecInSysUser.getUserGis());
					sysEntity.setPassword(exstngRecInSysUser.getPassword());
					sysEntity.setNickname(exstngRecInSysUser.getNickname());
					sysEntity.setAgentId(exstngRecInSysUser.getAgentId());
					sysEntity.setAboutMe(exstngRecInSysUser.getAboutMe());
					sysEntity.setMsisdnVerification(exstngRecInSysUser
							.getMsisdnVerification());
					sysEntity.setEmailVerification(exstngRecInSysUser
							.getEmailVerification());
					sysEntity.setEmail2(exstngRecInSysUser.getEmail2());
					sysEntity.setAuthBy(exstngRecInSysUser.getAuthBy());
					sysEntity.setAuthStatus(exstngRecInSysUser.getAuthStatus());
					sysEntity.setAuthTs(exstngRecInSysUser.getAuthTs());
					userRepo.merge(sysEntity);

					log.info("Populating pictures");
					UserPicture picEntity = new UserPicture();
					picEntity.setUserId(userId);
					picEntity.setContent(crUsrDtls.getProfilePic());

					if (picrepo.queryUserPic(userId) == null) {
						log.info("Inserting data pictures");
						picEntity.setCreatedBy(req.getHeader().getUserId());
						picEntity.setCreatedTs(Utils.getCurrentDate());
						picrepo.save(picEntity);
					} else {
						log.info("Merging data pictures");
						picEntity.setPicId(picrepo.queryUserPic(userId)
								.getPicId());
						UserPicture temp_entity = picrepo.findOne(picEntity,
								picEntity.getPicId());
						picEntity.setModifiedBy(req.getHeader().getUserId());
						picEntity.setModifiedTs(Utils.getCurrentDate());
						picEntity.setCreatedBy(temp_entity.getCreatedBy());
						picEntity.setCreatedTs(temp_entity.getCreatedTs());
						picrepo.merge(picEntity);
					}

					if (exstngRecInPpl.getMasterFlag().equals("Y")) {
						People people = new People();
						people.setId(pplPk);
						people.setAdd1(crUsrDtls.getAddress());
						people.setAdd2(exstngRecInPpl.getAdd2());
						people.setBirthyear(crUsrDtls.getBirthYear());
						people.setCountry(crUsrDtls.getCountry());
						people.setCreatedBy(exstngRecInPpl.getCreatedBy());
						people.setCreatedTs(exstngRecInPpl.getCreatedTs());
						people.setCurrencyId(crUsrDtls.getDispCcy());
						people.setEmail(crUsrDtls.getEmail());
						people.setFax(exstngRecInPpl.getFax());
						people.setFirstName(firstName);
						people.setFixedtel(exstngRecInPpl.getFixedtel());
						people.setGender(crUsrDtls.getGender());
						people.setIsVisible(crUsrDtls.getPrivSettings());
						people.setLanguageId(crUsrDtls.getLanguage());
						people.setLastName(lastName);
						people.setMasterFlag(exstngRecInPpl.getMasterFlag());
						people.setModified_By(userId);
						Timestamp ts = new Timestamp(Utils.getCurrentDate()
								.getTime());
						people.setModifiedTs(ts);
						people.setMsisdn(crUsrDtls.getPhone1());
						people.setMsisdn2(crUsrDtls.getPhone2());
						people.setNickname(exstngRecInPpl.getNickname());
						people.setPosition(exstngRecInPpl.getPosition());
						people.setRecordStatus(exstngRecInPpl.getRecordStatus());
						people.setShortdesc(exstngRecInPpl.getShortdesc());
						people.setTitle(exstngRecInPpl.getTitle());
						people.setTown(crUsrDtls.getTown());
						people.setWebsite(crUsrDtls.getWebsite());
						pplRepo.merge(people);
					}

					log.debug("Occupation -> " + crUsrDtls.getOccupation());
					String[] listOfOcc = crUsrDtls.getOccupation().split(",");

					List<UserOccupation> usrOccLst = occupRepo
							.queryusrOccupationbyuserId(userId,
									crUsrDtls.getDefaultNwk());
					if (usrOccLst.size() != 0) {
						occupRepo.delete(usrOccLst);
					}

					for (String occupation : listOfOcc) {
						UserOccupation userOccupation = new UserOccupation();
						UserOccupationPK userOccupationPK = new UserOccupationPK();
						userOccupationPK.setUserId(userId);
						userOccupationPK.setOccupationId(occupation);
						userOccupationPK
								.setNetworkId(crUsrDtls.getDefaultNwk());
						userOccupation.setId(userOccupationPK);
						userOccupation.setName(occupation);

						if (!occupRepo.exists(userOccupation, userOccupationPK)) {
							userOccupation.setCreatedBy(req.getHeader()
									.getUserId());
							userOccupation.setCreatedTs(Utils.getCurrentDate());
							occupRepo.save(userOccupation);
						} else {

							UserOccupation temp_entity = occupRepo.findOne(
									userOccupation, userOccupationPK);
							userOccupation.setModifiedBy(req.getHeader()
									.getUserId());
							userOccupation
									.setModifiedTs(Utils.getCurrentDate());
							userOccupation.setCreatedBy(temp_entity
									.getCreatedBy());
							userOccupation.setCreatedTs(temp_entity
									.getCreatedTs());
							occupRepo.merge(userOccupation);
						}
					}
					errorCode = ERROR_CODE.ES_MS_001;
				}
			}

		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.MS, serviceName,
				"SaveUserDetails", "", errorCode);
		saveUserRes.setHeader(header);
		return saveUserRes;

	}

	private String getResellerForUser(String userId, String networkId) {
		ResellerMaster master = resellerDetails.getResellerDetailsByNetwork(
				networkId);
		String resellerId = "";
		if(master != null){
			resellerId = resellerDetails.getResellerDetailsByNetwork(
					networkId).getResellerId();
		}		
		return resellerId != null ? resellerId : "";

	}

}
