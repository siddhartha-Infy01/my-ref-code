package com.iexceed.esoko.people.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.NetworkUseridRepo;
import com.iexceed.esoko.domain.dao.Network_privacyRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.dao.UserLocationRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.ProfileSharingTypeRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.domain.entity.Network_privacy;
import com.iexceed.esoko.domain.entity.Profile_Sharing_type;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserLocationPK;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity3.OperatorTemplate;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ms.QRUSRNWKDTLS;
import com.iexceed.esoko.jaxb.ms.QueryUserNetworkDetailsRes;
import com.iexceed.esoko.jaxb.people.ADDADMINUSR;
import com.iexceed.esoko.jaxb.people.AddUserAsAdminReq;
import com.iexceed.esoko.jaxb.people.AddUserAsAdminRes;
import com.iexceed.esoko.jaxb.people.CMMDTLS;
import com.iexceed.esoko.jaxb.people.COMCOMMD;
import com.iexceed.esoko.jaxb.people.COMLOC;
import com.iexceed.esoko.jaxb.people.COMOCCUP;
import com.iexceed.esoko.jaxb.people.CRPEOPLEDTLS;
import com.iexceed.esoko.jaxb.people.CRPEOPLEDTLSRES;
import com.iexceed.esoko.jaxb.people.CheckEmailRes;
import com.iexceed.esoko.jaxb.people.CheckMobileRes;
import com.iexceed.esoko.jaxb.people.CopyUsersToPeopleReq;
import com.iexceed.esoko.jaxb.people.CopyUsersToPeopleRes;
import com.iexceed.esoko.jaxb.people.CreatePeopleReq;
import com.iexceed.esoko.jaxb.people.CreatePeopleRes;
import com.iexceed.esoko.jaxb.people.EDITABLE;
import com.iexceed.esoko.jaxb.people.EDITPPLDTLS;
import com.iexceed.esoko.jaxb.people.EditPeopleReq;
import com.iexceed.esoko.jaxb.people.EditPeopleRes;
import com.iexceed.esoko.jaxb.people.Header;
import com.iexceed.esoko.jaxb.people.LOCDTLS;
import com.iexceed.esoko.jaxb.people.NWKDTLS;
import com.iexceed.esoko.jaxb.people.OCCDTLS;
import com.iexceed.esoko.jaxb.people.PERSONDTL;
import com.iexceed.esoko.jaxb.people.QRALLPEOPLEDTLS;
import com.iexceed.esoko.jaxb.people.QRUSERLISTS;
import com.iexceed.esoko.jaxb.people.QueryAllPeopleRes;
import com.iexceed.esoko.jaxb.people.QueryPersonRes;
import com.iexceed.esoko.jaxb.people.QueryUsersListRes;
import com.iexceed.esoko.jaxb.people.SEARCHPPLDTL;
import com.iexceed.esoko.jaxb.people.SearchAllPeopleRes;
import com.iexceed.esoko.jaxb.ss.QRUSERACCNT;
import com.iexceed.esoko.jaxb.ss.QueryUserAccountRes;
import com.iexceed.esoko.ms.service.UserNetworkDetailsService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.iexceed.esoko.ss.service.UserAccountService;
import com.iexceed.esoko.users.HLRLookupService;

/*
 * @author Gangadhar
 */
@Service
public class PeopleService {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private PeopleRepo peopleRepo;
	@Autowired
	private SystemUserRepo sysUserRepo;
	@Autowired
	private UserCommodityRepo cmdRepo;
	@Autowired
	private UserOccupationRepo occpRepo;
	@Autowired
	private UserLocationRepo locRepo;
	@Autowired
	private NetworkUseridRepo nwkusrIdRepo;
	@Autowired
	private Network_privacyRepo nwkPrivacyRepo;
	@Autowired
	private ProfileSharingTypeRepo sharingRepo;
	@Autowired
	private UserPictureRepo userPicRepo;
	@Autowired
	private System_accountRepo sysAccRepo;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	private UserNetworkDetailsService usrNwkDtls;
	@Autowired
	private UserAccountService usrAccnt;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private HLRLookupService lookupService;

	private final String serviceName = "PeopleService";

	@Transactional
	public CreatePeopleRes createPeople(CreatePeopleReq req) {
		CreatePeopleRes res = new CreatePeopleRes();
		log.info("Inside People Service -> Create People");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		String peopleId = null;
		try {
			boolean createRecord = true;
			List<CRPEOPLEDTLS> crPplDtlsList = req.getCRPEOPLEDTLS();
			List<CRPEOPLEDTLSRES> crPplRespList = new ArrayList<CRPEOPLEDTLSRES>();
			for (CRPEOPLEDTLS crPplDtls : crPplDtlsList) {
				if (StringUtils.isNotEmpty(crPplDtls.getMob1())
						&& StringUtils.isNotEmpty(crPplDtls.getNetworkId())) {
					// if Email is provided
					if (StringUtils.isNotEmpty(crPplDtls.getEmail())) {
						// Setting Email as PeopleId
						peopleId = crPplDtls.getEmail();
						// Checking in SystemUser if any record exists with the
						// given Email
						int emailCount = sysUserRepo.emailExists(crPplDtls
								.getEmail());
						if (emailCount == 0) {
							// No record found
							// Checking in People if any record exists with the
							// given Email
							People ppl = peopleRepo.findByEmailNwkId(
									crPplDtls.getEmail(),
									crPplDtls.getNetworkId());
							if (ppl != null) {
								// Record found
								errorCode = ERROR_CODE.ES_ER_002;
								log.debug("Email already exists in People table");
								createRecord = false;
							} else {
								// Checking in People if any record exists with
								// the
								// given Mobile
								ppl = peopleRepo.findByMsisdnNwkId(
										crPplDtls.getMob1(),
										crPplDtls.getNetworkId());
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
						peopleId = crPplDtls.getMob1();
						// Checking in SystemUser if any record exists with the
						// given Mobile
						int mobileCount = sysUserRepo.mobExists(crPplDtls
								.getMob1());
						if (mobileCount == 0) {
							// No record found
							// Checking in People if any record exists with the
							// given Mobile
							People ppl = peopleRepo.findByMsisdnNwkId(
									crPplDtls.getMob1(),
									crPplDtls.getNetworkId());
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
						boolean sysUsrExst = sysUserRepo.exists(
								SystemUser.class, peopleId);
						PeoplePK pplPk = new PeoplePK();
						pplPk.setDefaultNetworkId(crPplDtls.getNetworkId());
						pplPk.setPeopleId(peopleId);
						boolean pplExst = peopleRepo
								.exists(People.class, pplPk);
						if (sysUsrExst || pplExst) {
							// Record found
							errorCode = ERROR_CODE.PPL_CR_FL;
							log.debug("Record already exists");
							createRecord = false;
						} else {
							// No record found
							// Creating esoko user
							CRPEOPLEDTLSRES pplRes = new CRPEOPLEDTLSRES();
							pplRes.setPeopleId(peopleId);
							crPplRespList.add(pplRes);

							String operatorId = createEsokoUser(crPplDtls,
									peopleId, req.getHeader().getUserId());

							// Creating people
							People people = new People();
							people.setId(pplPk);
							log.debug("Title: " + crPplDtls.getTitle());
							people.setTitle(crPplDtls.getTitle());
							log.debug("FirstName: " + crPplDtls.getFirstName());
							people.setFirstName(crPplDtls.getFirstName());
							log.debug("LastName: " + crPplDtls.getLastName());
							people.setLastName(crPplDtls.getLastName());
							log.debug("Gender: " + crPplDtls.getGender());
							people.setGender(crPplDtls.getGender());
							log.debug("Email: " + crPplDtls.getEmail());
							people.setEmail(crPplDtls.getEmail());
							log.debug("Mobile1: " + crPplDtls.getMob1());
							people.setMsisdn(crPplDtls.getMob1());
							log.debug("Mobile2: " + crPplDtls.getMob2());
							people.setMsisdn2(crPplDtls.getMob2());
							if (!operatorId.equals("")) {
								people.setOperatorId(operatorId);
								log.debug("OperatorId: " + operatorId);
							}
							if (crPplDtls.getLanguage() != null
									&& StringUtils.isNotEmpty(crPplDtls
											.getLanguage())) {
								log.debug("Language: "
										+ crPplDtls.getLanguage());
								people.setLanguageId(crPplDtls.getLanguage());
							} else {
								log.debug("Language: "
										+ crPplDtls.getCommonLanguage());
								people.setLanguageId(crPplDtls
										.getCommonLanguage());
							}
							log.debug("Country: " + crPplDtls.getCountry());
							people.setCountry(crPplDtls.getCountry());
							if (crPplDtls.getTown() != null
									&& StringUtils.isNotEmpty(crPplDtls
											.getTown())) {
								log.debug("Town: " + crPplDtls.getTown());
								people.setTown(crPplDtls.getTown());
							} else {
								log.debug("Town: " + crPplDtls.getCommonTown());
								people.setTown(crPplDtls.getCommonTown());
							}
							log.debug("MasterFlag: Y");
							people.setMasterFlag("Y");
							log.debug("CurrencyId: " + crPplDtls.getCurrency());
							people.setCurrencyId(crPplDtls.getCurrency());
							log.debug("RecordStatus: A");
							people.setRecordStatus("A");
							String isVisible = this.isVisible(crPplDtls
									.getNetworkId());
							log.debug("IsVisible: " + isVisible);
							people.setIsVisible(isVisible);
							log.debug("BirthYear: " + crPplDtls.getBirthYear());
							people.setBirthyear(crPplDtls.getBirthYear());
							log.debug("Company: " + crPplDtls.getCompany());
							people.setCompany(crPplDtls.getCompany());
							log.debug("Position: " + crPplDtls.getPosition());
							people.setPosition(crPplDtls.getPosition());
							log.debug("ShortDescription: "
									+ crPplDtls.getDescription());
							people.setShortdesc(crPplDtls.getDescription());
							log.debug("FixedTel: " + crPplDtls.getFixedPhone());
							people.setFixedtel(crPplDtls.getFixedPhone());
							log.debug("Fax: " + crPplDtls.getFax());
							people.setFax(crPplDtls.getFax());
							log.debug("Address1: " + crPplDtls.getAddress1());
							people.setAdd1(crPplDtls.getAddress1());
							log.debug("Address2: " + crPplDtls.getAddress2());
							people.setAdd2(crPplDtls.getAddress2());
							log.debug("Website: " + crPplDtls.getWebsite());
							people.setWebsite(crPplDtls.getWebsite());
							log.debug("CreateBy: "
									+ req.getHeader().getUserId());
							people.setCreatedBy(req.getHeader().getUserId());
							log.debug("CreatedTs: "
									+ new Timestamp(Utils.getCurrentDate()
											.getTime()));
							people.setCreatedTs(new Timestamp(Utils
									.getCurrentDate().getTime()));
							if ("Upload".equalsIgnoreCase(req.getHeader()
									.getOperationName())) {
								people.setMode(crPplDtls.getMode());
							}
							peopleRepo.save(people);
							if (crPplDtls.getImage() != null) {
								updatePicture(peopleId, req.getHeader()
										.getUserId(), crPplDtls.getImage());
							}
							if (crPplDtls.getCMMDTLS().size() != 0) {
								this.updateCommodities(peopleId, crPplDtls
										.getCMMDTLS(), req.getHeader()
										.getUserId(), crPplDtls.getNetworkId());
							} else {
								this.updateCommonCommodities(peopleId,
										crPplDtls.getCOMCOMMD(), req
												.getHeader().getUserId(),
										crPplDtls.getNetworkId());
							}
							if (crPplDtls.getLOCDTLS().size() != 0) {
								this.updateLocations(peopleId, crPplDtls
										.getLOCDTLS(), req.getHeader()
										.getUserId(), crPplDtls.getNetworkId());
							} else {
								this.updateCommonLocations(peopleId, crPplDtls
										.getCOMLOC(), req.getHeader()
										.getUserId(), crPplDtls.getNetworkId());
							}
							if (crPplDtls.getOCCDTLS().size() != 0) {
								this.updateOccupations(peopleId, crPplDtls
										.getOCCDTLS(), req.getHeader()
										.getUserId(), crPplDtls.getNetworkId());
							} else {
								this.updateCommonOccupations(peopleId,
										crPplDtls.getCOMOCCUP(), req
												.getHeader().getUserId(),
										crPplDtls.getNetworkId());
							}

							NetworkUseridPK nwkUsrIdPk = new NetworkUseridPK();
							nwkUsrIdPk.setNetworkId(crPplDtls.getNetworkId());
							nwkUsrIdPk.setUserId(peopleId);
							NetworkUserid nwkUsrId = nwkusrIdRepo.findOne(
									NetworkUserid.class, nwkUsrIdPk);
							if (nwkUsrId == null) {
								nwkUsrId = new NetworkUserid();
								nwkUsrId.setCreatedBy(req.getHeader()
										.getUserId());
								nwkUsrId.setCreatedTs(new Date());
								nwkUsrId.setId(nwkUsrIdPk);
								nwkUsrId.setNetworkName(crPplDtls
										.getNetworkId());
								nwkusrIdRepo.save(nwkUsrId);
							}

							System_account sysAcc = new System_account();
							long accNumber = esokoRepo.getSequenceNumber();
							sysAcc.setAccName("U" + accNumber);
							sysAcc.setAccountNo("U" + accNumber);
							sysAcc.setAcCurrency("USD");
							sysAcc.setBalance(0.00);
							sysAcc.setCreatedBy(req.getHeader().getUserId());
							sysAcc.setCreatedTs(new Date());
							sysAcc.setOwnerId(peopleId);
							sysAcc.setRecordStatus("A");
							sysAcc.setType("U");
							sysAccRepo.save(sysAcc);
							log.debug("Data inserted in SystemAccount");
							errorCode = ERROR_CODE.PPL_CR_SC;

							Network nwk = new Network();
							nwk.setNetworkId(crPplDtls.getNetworkId());
							Network nwk1 = nwkRepo.findOne(nwk,
									crPplDtls.getNetworkId());
							HeaderFactory.setExtraParamMap(
									"$1",
									crPplDtls.getFirstName() + " "
											+ crPplDtls.getLastName());
							HeaderFactory
									.setExtraParamMap("$2", nwk1.getName());
						}
					}
				} else {
					errorCode = ERROR_CODE.ES_PK_002;
					log.debug("NetworkId cannot be empty");
				}
			}
			res.getCRPEOPLEDTLSRES().addAll(crPplRespList);
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		if ("Upload".equalsIgnoreCase(req.getHeader().getOperationName())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
					"People Service", "Upload", req.getHeader().getUserId(),
					errorCode);
		} else {
			header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
					"People Service", "Create People", req.getHeader()
							.getUserId(), errorCode);
		}
		res.setHeader(header);
		return res;
	}

	public QueryAllPeopleRes queryAllPeople(String operation, String networkId,
			String locations, String commodities, String occupations) {
		log.info("Inside People Service -> queryAllPeople");
		QueryAllPeopleRes res = new QueryAllPeopleRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		log.debug("networkId->" + networkId);
		List<Map<String, Object>> pplLst = null;
		if (operation.equals("A")) {
			pplLst = peopleRepo.queryAdmins(networkId, locations, commodities,
					occupations);
		} else if (operation.equals("P")) {
			pplLst = peopleRepo.queryAllPeople(networkId, locations,
					commodities, occupations);
		} else if (operation.equals("R")) {
			pplLst = peopleRepo.queryRecentlyAdded(networkId, locations,
					commodities, occupations);
		} else if (operation.equals("U")) {
			pplLst = peopleRepo.queryUngrouped(networkId);
		}

		if (pplLst.size() == 0) {
			if ((locations != null) || (commodities != null)
					|| (occupations != null)) {
				errorCode = ERROR_CODE.PPL_NR_002;
			} else {
				errorCode = ERROR_CODE.PPL_NR_001;
			}
			log.debug("No record found");
		} else {
			List<QRALLPEOPLEDTLS> pplDtlLst = new ArrayList<QRALLPEOPLEDTLS>();
			for (Map<String, Object> ppl : pplLst) {
				this.setPeopleList(networkId, ppl, pplDtlLst);
			}
			res.getQRALLPEOPLEDTLS().addAll(pplDtlLst);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "Query All People Details", "", errorCode);
		res.setHeader(header);

		return res;
	}

	private void setPeopleList(String networkId, Map<String, Object> ppl,
			List<QRALLPEOPLEDTLS> pplDtlLst) {
		QRALLPEOPLEDTLS pplDtl = new QRALLPEOPLEDTLS();
		String pplNetworkId = "";
		String peopleId = "";

		if (ppl.get("networkId") != null) {
			pplNetworkId = ppl.get("networkId").toString();
		}
		if (ppl.get("peopleId") != null) {
			peopleId = ppl.get("peopleId").toString();

		}
		log.debug("peopleId:" + peopleId);
		pplDtl.setPeopleId(peopleId);

		if (ppl.get("editFlag") != null) {
			String editable = ppl.get("editFlag").toString();
			pplDtl.setEdit(editable);
			pplDtl.setEDITABLE(this.getSharingDetails(editable, pplNetworkId,
					networkId));
		}

		if (ppl.get("country") != null) {
			pplDtl.setCountry(ppl.get("country").toString());
		}
		if (ppl.get("email") != null) {
			pplDtl.setEmail(ppl.get("email").toString());
		}
		if (ppl.get("firstName") != null) {
			pplDtl.setFirstName(ppl.get("firstName").toString());
		}
		if (ppl.get("lastName") != null) {
			pplDtl.setLastName(ppl.get("lastName").toString());
		}
		if (ppl.get("gender") != null) {
			pplDtl.setGender(ppl.get("gender").toString());
		}
		if (ppl.get("msisdn") != null) {
			pplDtl.setMob1(ppl.get("msisdn").toString());
		}
		if (ppl.get("town") != null) {
			pplDtl.setTown(ppl.get("town").toString());
		}
		if (ppl.get("networkId") != null) {
			pplDtl.setOwnerNetwork(ppl.get("networkId").toString());
		}

		UserPicture usrPic = userPicRepo.queryUserPic(peopleId);
		if (usrPic != null) {
			pplDtl.setProfilePic(usrPic.getContent());
		}

		List<CMMDTLS> usrComLst = this.queryCommodities(peopleId, pplNetworkId);
		log.debug("usrComLst ->" + usrComLst);
		if (usrComLst.size() != 0) {
			pplDtl.getCMMDTLS().addAll(usrComLst);
		}

		List<NWKDTLS> nwkUsrIdLst = this.queryNwkDtls(peopleId);
		log.debug("nwkUsrIdLst ->" + nwkUsrIdLst);
		if (nwkUsrIdLst.size() != 0) {
			pplDtl.getNWKDTLS().addAll(nwkUsrIdLst);
		}

		List<OCCDTLS> occDtls = this.queryOccDtls(peopleId, pplNetworkId);
		if (occDtls.size() != 0) {
			pplDtl.getOCCDTLS().addAll(occDtls);
		}
		pplDtlLst.add(pplDtl);
	}

	public SearchAllPeopleRes searchAllPeople(String networkId) {
		log.debug("Inside PeopleService -> searchAllPeople");
		log.debug("NetworkId: " + networkId);
		SearchAllPeopleRes res = new SearchAllPeopleRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<Map<String, Object>> pplLst = peopleRepo
				.searchAllPeople(networkId);
		if (pplLst.size() == 0) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.debug("No records found");
		} else {
			List<SEARCHPPLDTL> srchPPLList = new ArrayList<SEARCHPPLDTL>();
			SEARCHPPLDTL detail1 = new SEARCHPPLDTL();
			SEARCHPPLDTL detail2 = new SEARCHPPLDTL();
			SEARCHPPLDTL detail3 = new SEARCHPPLDTL();

			List<QRALLPEOPLEDTLS> pplDtlLst1 = new ArrayList<QRALLPEOPLEDTLS>();
			List<QRALLPEOPLEDTLS> pplDtlLst2 = new ArrayList<QRALLPEOPLEDTLS>();
			List<QRALLPEOPLEDTLS> pplDtlLst3 = new ArrayList<QRALLPEOPLEDTLS>();
			for (Map<String, Object> ppl : pplLst) {
				String networkType = ppl.get("finalValue").toString();
				if (networkType.equals("MyNetwork")) {
					this.setPeopleList(networkId, ppl, pplDtlLst1);
				} else if (networkType.equals("CountryNetwork")) {
					this.setPeopleList(networkId, ppl, pplDtlLst2);
				} else if (networkType.equals("PublicNetwork")) {
					this.setPeopleList(networkId, ppl, pplDtlLst3);
				}
			}

			detail1.getQRALLPEOPLEDTLS().addAll(pplDtlLst1);
			detail2.getQRALLPEOPLEDTLS().addAll(pplDtlLst2);
			detail3.getQRALLPEOPLEDTLS().addAll(pplDtlLst3);

			srchPPLList.add(detail1);
			srchPPLList.add(detail2);
			srchPPLList.add(detail3);
			res.getSEARCHPPLDTL().addAll(srchPPLList);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "Search All People Details", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryUsersListRes queryUsersList(String networkId) {
		QueryUsersListRes res = new QueryUsersListRes();
		QRUSERLISTS allUserDtls = new QRUSERLISTS();
		List<QRUSERLISTS> qryUserList = new ArrayList<QRUSERLISTS>();
		qryUserList.add(allUserDtls);
		res.getQRUSERLISTS().addAll(qryUserList);
		return res;
	}

	@Transactional
	public CopyUsersToPeopleRes copyUsersToPeople(CopyUsersToPeopleReq req) {
		CopyUsersToPeopleRes resp = new CopyUsersToPeopleRes();
		return resp;
	}

	public QueryPersonRes queryPerson(String userId, String networkId) {
		log.info("Inside PeopleService -> queryPerson");
		log.debug("UserId: " + userId);
		log.debug("NetworkId: " + networkId);
		QueryPersonRes resWrap = new QueryPersonRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		PeoplePK pplPk = new PeoplePK();
		pplPk.setDefaultNetworkId(networkId);
		pplPk.setPeopleId(userId);
		People ppl = peopleRepo.findOne(People.class, pplPk);
		if (ppl == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No result found");
		} else {
			PERSONDTL prsnDtl = new PERSONDTL();
			prsnDtl.setPeopleId(userId);
			prsnDtl.setAddress1(ppl.getAdd1());
			prsnDtl.setAddress2(ppl.getAdd2());
			prsnDtl.setBirthYear(ppl.getBirthyear());
			prsnDtl.setCompany(ppl.getCompany());
			prsnDtl.setCountry(ppl.getCountry());
			prsnDtl.setCurrency(ppl.getCurrencyId());
			prsnDtl.setDescription(ppl.getShortdesc());
			prsnDtl.setEmail(ppl.getEmail());
			prsnDtl.setFax(ppl.getFax());
			prsnDtl.setFirstName(ppl.getFirstName());
			prsnDtl.setFixedPhone(ppl.getFixedtel());
			prsnDtl.setGender(ppl.getGender());
			prsnDtl.setLanguage(ppl.getLanguageId());
			prsnDtl.setLastName(ppl.getLastName());
			prsnDtl.setMainOffice(ppl.getCompany());
			prsnDtl.setMob1(ppl.getMsisdn());
			prsnDtl.setMob2(ppl.getMsisdn2());
			prsnDtl.setPosition(ppl.getPosition());
			prsnDtl.setPrivacy(ppl.getIsVisible());
			prsnDtl.setTitle(ppl.getTitle());
			prsnDtl.setTown(ppl.getTown());
			prsnDtl.setWebsite(ppl.getWebsite());
			prsnDtl.setSubscription("Free");
			prsnDtl.setPassword("XXXXXXXX");
			prsnDtl.setWeather("No details found");
			prsnDtl.setOffers("0 Offers matching");
			prsnDtl.setPrices("0 prices");

			QueryUserAccountRes accRes = usrAccnt.queryUserAccount(userId,
					networkId);
			if (accRes.getHeader().getType().equals("S")) {
				List<QRUSERACCNT> accnList = accRes.getQRUSERACCNT();
				for (QRUSERACCNT usrAcc : accnList) {
					if (usrAcc.getType().equals("U")) {
						float bal = Float.valueOf(usrAcc.getBalance());
						prsnDtl.setBalance(Float.toString(bal));
					}
				}
			}

			SystemUser sysUsr = sysUserRepo.findOne(SystemUser.class, userId);
			if (sysUsr != null) {
				String nwkId = sysUsr.getDefaultNetworkId();
				Network network = nwkRepo.findOne(Network.class, nwkId);
				prsnDtl.setDefaultNetwork(network.getName());
			}

			prsnDtl.getCMMDTLS().addAll(
					this.queryCommodities(userId, networkId));
			prsnDtl.getLOCDTLS().addAll(this.queryLocDtls(userId, networkId));
			prsnDtl.getOCCDTLS().addAll(this.queryOccDtls(userId, networkId));

			QueryUserNetworkDetailsRes nwkDtlRes = usrNwkDtls
					.queryUserNetworkDetails(userId);
			if (nwkDtlRes.getHeader().getType().equals("S")) {
				List<QRUSRNWKDTLS> nwkDtlList = nwkDtlRes.getQRUSRNWKDTLS();
				List<NWKDTLS> nwkDtls = new ArrayList<NWKDTLS>();
				for (QRUSRNWKDTLS nwk : nwkDtlList) {
					NWKDTLS dtl = new NWKDTLS();
					dtl.setNetwork(nwk.getNetworkName());
					nwkDtls.add(dtl);
				}
				prsnDtl.getNWKDTLS().addAll(nwkDtls);
			}
			resWrap.setPERSONDTL(prsnDtl);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "queryPerson", "", errorCode);
		resWrap.setHeader(header);

		return resWrap;
	}

	@Transactional
	public EditPeopleRes editPeople(EditPeopleReq req) {
		log.info("Inside PeopleService -> editPeople");
		EditPeopleRes res = new EditPeopleRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		EDITPPLDTLS edtPplDtls = req.getEDITPPLDTLS();
		if (StringUtils.isNotEmpty(edtPplDtls.getFirstName())
				&& StringUtils.isNotEmpty(edtPplDtls.getMob1())
				&& StringUtils.isNotEmpty(edtPplDtls.getNetworkId())) {
			try {
				boolean updateRecords = true;
				PeoplePK extngId = new PeoplePK();
				extngId.setDefaultNetworkId(edtPplDtls.getNetworkId());
				extngId.setPeopleId(edtPplDtls.getPeopleId());
				People extngRec = peopleRepo.findOne(People.class, extngId);
				if (extngRec == null) {
					errorCode = ERROR_CODE.ES_NR_001;
					log.info("No record found");
				} else {
					if (extngRec.getMasterFlag().equalsIgnoreCase("Y")) {
						int isMobExst = sysUserRepo.mobExists(
								edtPplDtls.getMob1(), edtPplDtls.getPeopleId());
						if (isMobExst != 0) {
							log.debug("Mobile number already exists in SystemUser table");
							errorCode = ERROR_CODE.ES_ER_003;
							updateRecords = false;
						} else {
							int isEmailExst = sysUserRepo.emailExists(
									edtPplDtls.getEmail(),
									edtPplDtls.getPeopleId());
							if (isEmailExst != 0) {
								log.debug("Email already exists in SystemUser table");
								errorCode = ERROR_CODE.ES_ER_002;
								updateRecords = false;
							}

						}
					}

					if (updateRecords) {
						if (!extngRec.getMsisdn().equals(edtPplDtls.getMob1())) {
							People temp = peopleRepo.findByMsisdnNwkId(
									edtPplDtls.getMob1(),
									edtPplDtls.getNetworkId());
							if (temp != null) {
								log.debug("Mobile number already exists in People table");
								errorCode = ERROR_CODE.ES_ER_003;
								updateRecords = false;
							}
						}

						if (updateRecords
								&& StringUtils
										.isNotEmpty(edtPplDtls.getEmail())
								&& (extngRec.getEmail() != null)) {
							if (!extngRec.getEmail().equals(
									edtPplDtls.getEmail())) {
								People temp = peopleRepo.findByEmailNwkId(
										edtPplDtls.getEmail(),
										edtPplDtls.getNetworkId());
								if (temp != null) {
									log.debug("Email already exists in People table");
									errorCode = ERROR_CODE.ES_ER_002;
									updateRecords = false;
								}
							}
						}

						if (updateRecords) {
							People newPplRec = this.getNewPeopleRecord(req,
									extngRec);
							peopleRepo.merge(newPplRec);

							if (extngRec.getMasterFlag().equalsIgnoreCase("Y")) {
								SystemUser sysUsr = sysUserRepo.findOne(
										SystemUser.class,
										edtPplDtls.getPeopleId());
								SystemUser newRec = this.getNewSysUsrRecord(
										req, sysUsr);
								sysUserRepo.merge(newRec);
							}
							this.deleteCommodities(edtPplDtls.getPeopleId(),
									edtPplDtls.getNetworkId());
							this.deleteLocations(edtPplDtls.getPeopleId(),
									edtPplDtls.getNetworkId());
							this.deleteOccupations(edtPplDtls.getPeopleId(),
									edtPplDtls.getNetworkId());

							this.updateCommodities(edtPplDtls.getPeopleId(),
									edtPplDtls.getCMMDTLS(), req.getHeader()
											.getUserId(), edtPplDtls
											.getNetworkId());
							this.updateLocations(edtPplDtls.getPeopleId(),
									edtPplDtls.getLOCDTLS(), req.getHeader()
											.getUserId(), edtPplDtls
											.getNetworkId());
							this.updateOccupations(edtPplDtls.getPeopleId(),
									edtPplDtls.getOCCDTLS(), req.getHeader()
											.getUserId(), edtPplDtls
											.getNetworkId());
							errorCode = ERROR_CODE.PPL_UD_SC;
						}
					}
				}

			} catch (DomainException e) {
				log.debug(Utils.getStackTrace(e));
				errorCode = e.getCode();
			}

		} else {
			log.debug("Primary keys are empty");
			errorCode = ERROR_CODE.ES_PK_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "editPeople", req.getHeader().getUserId(),
				errorCode);
		res.setHeader(header);
		return res;
	}

	public CheckEmailRes checkEmail(String emailId, String networkId) {
		log.info("Inside PeopleService -> checkEmail");
		CheckEmailRes res = new CheckEmailRes();
		Enum<ERROR_CODE> errorCode = null;
		int emailCount = sysUserRepo.emailExists(emailId);
		if (emailCount == 0) {
			People ppl = peopleRepo.findByEmailNwkId(emailId, networkId);
			if (ppl == null) {
				errorCode = ERROR_CODE.ES_SC_001;
			} else {
				errorCode = ERROR_CODE.ES_ER_002;
			}
		} else {
			errorCode = ERROR_CODE.ES_ER_002;
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "checkEmail", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CheckMobileRes checkMobile(String mobile, String networkId,
			String countryName) {
		log.info("Inside PeopleService -> checkMobile");
		CheckMobileRes res = new CheckMobileRes();
		Enum<ERROR_CODE> errorCode = null;
		boolean isValid = Utils.isPhoneNumberValid(mobile, countryName);
		if (isValid) {
			int mobileCount = sysUserRepo.mobExists(mobile);
			if (mobileCount == 0) {
				People ppl = peopleRepo.findByMsisdnNwkId(mobile, networkId);
				if (ppl == null) {
					OperatorTemplate template = lookupService
							.fetchOperatorTemplate(mobile);
					if (template == null) {
						errorCode = ERROR_CODE.ES_ER_004;
					} else {
						errorCode = ERROR_CODE.ES_SC_001;
					}
				} else {
					errorCode = ERROR_CODE.ES_ER_003;
				}
			} else {
				errorCode = ERROR_CODE.ES_ER_003;
			}
		} else {
			errorCode = ERROR_CODE.ES_ER_004;
		}

		Header header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "checkMobile", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public String createEsokoUser(CRPEOPLEDTLS crPplDtls, String userId,
			String createdBy) throws DomainException {
		log.info("Inside PeopleSerivce -> createEsokoUser");
		String operatorId = null;
		Date timeStamp = new Date();
		SystemUser sysUser = new SystemUser();
		sysUser.setUserId(userId);
		log.debug("FirstName: " + crPplDtls.getFirstName());
		sysUser.setFirstName(crPplDtls.getFirstName());
		log.debug("LastName: " + crPplDtls.getLastName());
		sysUser.setLastName(crPplDtls.getLastName());
		log.debug("Gender: " + crPplDtls.getGender());
		sysUser.setGender(crPplDtls.getGender());
		log.debug("Email: " + crPplDtls.getEmail());
		sysUser.setEmail(crPplDtls.getEmail());
		log.debug("Phone: " + crPplDtls.getMob1());
		sysUser.setMsisdn(crPplDtls.getMob1());
		operatorId = lookupService.fetchOperator(crPplDtls.getMob1());
		if (!operatorId.equals("")) {
			sysUser.setOperatorId(operatorId);
			log.debug("OperatorId: " + operatorId);
		}
		log.debug("Language: " + crPplDtls.getLanguage());
		sysUser.setLanguageId(crPplDtls.getLanguage());
		log.debug("Country: " + crPplDtls.getCountry());
		sysUser.setCountry(crPplDtls.getCountry());
		log.debug("Town: " + crPplDtls.getTown());
		sysUser.setTown(crPplDtls.getTown());
		log.debug("YearOfBirth: " + crPplDtls.getBirthYear());
		sysUser.setBirthYear(crPplDtls.getBirthYear());
		log.debug("Type: U");
		sysUser.setType("U");
		log.debug("AuthStatus: U");
		sysUser.setAuthStatus("U");
		log.debug("CurrencyId: " + crPplDtls.getCurrency());
		sysUser.setCurrencyId(crPplDtls.getCurrency());
		log.debug("RecordStatus: A");
		sysUser.setRecordStatus("A");
		log.debug("CreateBy: " + createdBy);
		sysUser.setCreatedBy(createdBy);
		log.debug("CreatedTs: " + timeStamp);
		sysUser.setCreatedTs(timeStamp);
		String isVisible = this.isVisible(crPplDtls.getNetworkId());
		log.debug("IsVisible: " + isVisible);
		sysUser.setIsVisible(isVisible);
		log.debug("Website: " + crPplDtls.getWebsite());
		sysUser.setWebsite(crPplDtls.getWebsite());
		log.debug("AboutMe: " + crPplDtls.getDescription());
		sysUser.setAboutMe(crPplDtls.getDescription());
		log.debug("Phone2: " + crPplDtls.getMob2());
		sysUser.setMsisdn2(crPplDtls.getMob2());
		log.debug(crPplDtls.getAddress1());
		sysUser.setAddress(crPplDtls.getAddress1());
		log.debug("NetworkId: " + crPplDtls.getNetworkId());
		sysUser.setDefaultNetworkId(crPplDtls.getNetworkId());
		sysUserRepo.save(sysUser);
		return operatorId;
	}

	public List<NWKDTLS> queryNwkDtls(String userId) {
		List<NetworkUserid> nwkUsrIdLst = nwkusrIdRepo
				.queryNetworksByUser(userId);
		List<NWKDTLS> nwkDtlLst = new ArrayList<NWKDTLS>();
		log.debug("userId -> " + userId);
		log.debug("nwkUsrIdLst -> " + nwkUsrIdLst);
		if (nwkUsrIdLst.size() != 0) {
			for (NetworkUserid nwkUsrId : nwkUsrIdLst) {
				NWKDTLS nwkDtl = new NWKDTLS();
				NetworkUseridPK nwkUsrIdPk = nwkUsrId.getId();
				nwkDtl.setNetwork(nwkUsrIdPk.getNetworkId());
				nwkDtlLst.add(nwkDtl);
			}
		}
		return nwkDtlLst;
	}

	public List<CMMDTLS> queryCommodities(String userId, String networkId) {
		List<CMMDTLS> cmDtlLst = new ArrayList<CMMDTLS>();
		List<UserCommodity> usrComLst = cmdRepo.queryUserCommodity(userId,
				networkId);
		log.debug("PeopleId -> " + userId);
		log.debug("usrComLst ->" + usrComLst);
		if (usrComLst.size() != 0) {
			for (UserCommodity usrCom : usrComLst) {
				UserCommodityPK usrComPk = usrCom.getId();
				CMMDTLS cmDtl = new CMMDTLS();
				cmDtl.setCommodity(usrComPk.getCommodityId());
				cmDtlLst.add(cmDtl);
			}
		}
		return cmDtlLst;
	}

	public List<OCCDTLS> queryOccDtls(String userId, String networkId) {
		log.debug("UserId: " + userId);
		List<UserOccupation> occUsrIdLst = occpRepo.queryusrOccupationbyuserId(
				userId, networkId);
		List<OCCDTLS> occDtls = new ArrayList<OCCDTLS>();
		if (occUsrIdLst.size() != 0) {
			for (UserOccupation occupation : occUsrIdLst) {
				UserOccupationPK occPk = occupation.getId();
				OCCDTLS occDtl = new OCCDTLS();
				occDtl.setOccupation(occPk.getOccupationId());
				occDtls.add(occDtl);
			}
		}
		return occDtls;
	}

	public List<LOCDTLS> queryLocDtls(String userId, String networkId) {
		log.debug("UserId: " + userId);
		List<UserLocation> usrLocLst = locRepo.queryUserLocDetails(userId,
				networkId);
		List<LOCDTLS> locDtls = new ArrayList<LOCDTLS>();
		if (usrLocLst.size() != 0) {
			for (UserLocation location : usrLocLst) {
				UserLocationPK locPk = location.getId();
				LOCDTLS locDtl = new LOCDTLS();
				locDtl.setLocation(locPk.getLocationId());
				locDtls.add(locDtl);
			}
		}
		return locDtls;
	}

	public void updateCommodities(String userId, List<CMMDTLS> commDtlLst,
			String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateCommodities");
		if (commDtlLst.size() != 0) {
			for (CMMDTLS commd : commDtlLst) {
				UserCommodityPK usrComdPk = new UserCommodityPK();
				usrComdPk.setCommodityId(commd.getCommodity());
				usrComdPk.setUserId(userId);
				usrComdPk.setNetworkId(networkId);
				UserCommodity usrComd = new UserCommodity();
				usrComd.setCreatedBy(createdBy);
				usrComd.setCreatedTs(new Date());
				usrComd.setId(usrComdPk);
				usrComd.setName(commd.getCommodity());
				cmdRepo.merge(usrComd);
			}
		}
	}

	public void updatePicture(String userId, String createdBy, byte[] content) {
		log.info("Inside PeopleService -> updatePicture");
		UserPicture userPicture = new UserPicture();
		userPicture.setUserId(userId);
		userPicture.setContent(Utils.encodeImage(content));
		userPicture.setCreatedBy(createdBy);
		userPicture.setCreatedTs(Utils.getCurrentDate());
		userPicRepo.save(userPicture);

	}

	public void updateCommonCommodities(String userId,
			List<COMCOMMD> commDtlLst, String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateCommodities");
		if (commDtlLst.size() != 0) {
			for (COMCOMMD commd : commDtlLst) {
				UserCommodityPK usrComdPk = new UserCommodityPK();
				usrComdPk.setCommodityId(commd.getCommonCommodity());
				usrComdPk.setUserId(userId);
				usrComdPk.setNetworkId(networkId);
				UserCommodity usrComd = new UserCommodity();
				usrComd.setCreatedBy(createdBy);
				usrComd.setCreatedTs(new Date());
				usrComd.setId(usrComdPk);
				usrComd.setName(commd.getCommonCommodity());
				cmdRepo.merge(usrComd);
			}
		}
	}

	public void updateLocations(String userId, List<LOCDTLS> locDtlLst,
			String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateLocations");
		if (locDtlLst.size() != 0) {
			for (LOCDTLS loc : locDtlLst) {
				UserLocationPK usrLocPk = new UserLocationPK();
				usrLocPk.setLocationId(loc.getLocation());
				usrLocPk.setUserId(userId);
				usrLocPk.setNetworkId(networkId);
				UserLocation usrLoc = new UserLocation();
				usrLoc.setCreatedBy(createdBy);
				usrLoc.setCreatedTs(new Date());
				usrLoc.setId(usrLocPk);
				usrLoc.setName(loc.getLocation());
				locRepo.merge(usrLoc);
			}
		}
	}

	public void updateCommonLocations(String userId, List<COMLOC> locDtlLst,
			String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateLocations");
		if (locDtlLst.size() != 0) {
			for (COMLOC loc : locDtlLst) {
				UserLocationPK usrLocPk = new UserLocationPK();
				usrLocPk.setLocationId(loc.getCommonLocation());
				usrLocPk.setUserId(userId);
				usrLocPk.setNetworkId(networkId);
				UserLocation usrLoc = new UserLocation();
				usrLoc.setCreatedBy(createdBy);
				usrLoc.setCreatedTs(new Date());
				usrLoc.setId(usrLocPk);
				usrLoc.setName(loc.getCommonLocation());
				locRepo.merge(usrLoc);
			}
		}
	}

	public void updateOccupations(String userId, List<OCCDTLS> occDtlLst,
			String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateOccupations");
		if (occDtlLst.size() != 0) {
			for (OCCDTLS occ : occDtlLst) {
				UserOccupationPK usrOccPk = new UserOccupationPK();
				usrOccPk.setOccupationId(occ.getOccupation());
				usrOccPk.setUserId(userId);
				usrOccPk.setNetworkId(networkId);
				UserOccupation usrOcc = new UserOccupation();
				usrOcc.setCreatedBy(createdBy);
				usrOcc.setCreatedTs(new Date());
				usrOcc.setId(usrOccPk);
				usrOcc.setName(occ.getOccupation());
				occpRepo.merge(usrOcc);
			}
		}
	}

	public void updateCommonOccupations(String userId,
			List<COMOCCUP> occDtlLst, String createdBy, String networkId) {
		log.info("Inside PeopleService -> updateOccupations");
		if (occDtlLst.size() != 0) {
			for (COMOCCUP occ : occDtlLst) {
				UserOccupationPK usrOccPk = new UserOccupationPK();
				usrOccPk.setOccupationId(occ.getCommonOccupation());
				usrOccPk.setUserId(userId);
				usrOccPk.setNetworkId(networkId);
				UserOccupation usrOcc = new UserOccupation();
				usrOcc.setCreatedBy(createdBy);
				usrOcc.setCreatedTs(new Date());
				usrOcc.setId(usrOccPk);
				usrOcc.setName(occ.getCommonOccupation());
				occpRepo.merge(usrOcc);
			}
		}
	}

	public void deleteCommodities(String userId, String networkId) {
		List<UserCommodity> usrComLst = cmdRepo.queryUserCommodity(userId,
				networkId);
		if (usrComLst.size() != 0) {
			cmdRepo.delete(usrComLst);
		}
	}

	public void deleteLocations(String userId, String networkId) {
		List<UserLocation> usrLocLst = locRepo.queryUserLocDetails(userId,
				networkId);
		if (usrLocLst.size() != 0) {
			locRepo.delete(usrLocLst);
		}
	}

	public void deleteOccupations(String userId, String networkId) {
		List<UserOccupation> usrLocLst = occpRepo.queryusrOccupationbyuserId(
				userId, networkId);
		if (usrLocLst.size() != 0) {
			occpRepo.delete(usrLocLst);
		}
	}

	private String isVisible(String networkId) {
		String isVisible = null;
		Network_privacy nwkPrv = nwkPrivacyRepo.findOne(Network_privacy.class,
				networkId);
		if (nwkPrv == null) {
			isVisible = "N";
		} else {
			String contStatus = nwkPrv.getContacts();
			if (contStatus.equalsIgnoreCase("E")) {
				isVisible = "Y";
			} else {
				isVisible = "N";
			}
		}
		return isVisible;
	}

	private People getNewPeopleRecord(EditPeopleReq req, People ppl) {
		log.debug("Inside PeopleService -> getNewPeopleRecord");
		EDITPPLDTLS edtPplDtls = req.getEDITPPLDTLS();
		People newRec = new People();
		newRec.setAdd1(edtPplDtls.getAddress1());
		newRec.setAdd2(edtPplDtls.getAddress2());
		newRec.setBirthyear(edtPplDtls.getBirthYear());
		newRec.setCompany(edtPplDtls.getCompany());
		newRec.setCountry(edtPplDtls.getCountry());
		newRec.setCreatedBy(ppl.getCreatedBy());
		newRec.setCreatedTs(ppl.getCreatedTs());
		newRec.setCurrencyId(edtPplDtls.getCurrency());
		newRec.setEmail(edtPplDtls.getEmail());
		newRec.setFax(edtPplDtls.getFax());
		newRec.setFirstName(edtPplDtls.getFirstName());
		newRec.setFixedtel(edtPplDtls.getFixedPhone());
		newRec.setGender(edtPplDtls.getGender());
		PeoplePK newPplPk = new PeoplePK();
		newPplPk.setDefaultNetworkId(edtPplDtls.getNetworkId());
		newPplPk.setPeopleId(edtPplDtls.getPeopleId());
		newRec.setId(newPplPk);
		newRec.setIsVisible(ppl.getIsVisible());
		newRec.setLanguageId(edtPplDtls.getLanguage());
		newRec.setLastName(edtPplDtls.getLastName());
		newRec.setMasterFlag(ppl.getMasterFlag());
		newRec.setModified_By(req.getHeader().getUserId());
		Date dt = new Date();
		Timestamp modifiedTs = new Timestamp(dt.getTime());
		newRec.setModifiedTs(modifiedTs);
		newRec.setMsisdn(edtPplDtls.getMob1());
		newRec.setMsisdn2(edtPplDtls.getMob2());
		newRec.setNickname(ppl.getNickname());
		newRec.setPosition(edtPplDtls.getPosition());
		newRec.setRecordStatus(ppl.getRecordStatus());
		newRec.setShortdesc(edtPplDtls.getDescription());
		newRec.setTitle(edtPplDtls.getTitle());
		newRec.setTown(edtPplDtls.getTown());
		newRec.setWebsite(edtPplDtls.getWebsite());
		if (ppl.getMsisdn().equals(edtPplDtls.getMob1())) {
			newRec.setOperatorId(ppl.getOperatorId());
		} else {
			String operatorId = lookupService.fetchOperator(edtPplDtls
					.getMob1());
			if (!operatorId.equals("")) {
				newRec.setOperatorId(operatorId);
				log.debug("OperatorId: " + operatorId);
			}
		}
		return newRec;
	}

	private SystemUser getNewSysUsrRecord(EditPeopleReq req, SystemUser sysUser) {
		log.debug("Inside PeopleService -> getNewSysUsrRecord");
		EDITPPLDTLS edtPplDtls = req.getEDITPPLDTLS();
		SystemUser newRec = new SystemUser();
		newRec.setAboutMe(edtPplDtls.getDescription());
		newRec.setAddress(edtPplDtls.getAddress1());
		newRec.setAgentId(sysUser.getAgentId());
		newRec.setAuthBy(sysUser.getAuthBy());
		newRec.setAuthStatus(sysUser.getAuthStatus());
		newRec.setAuthTs(sysUser.getAuthTs());
		newRec.setBirthYear(edtPplDtls.getBirthYear());
		newRec.setCountry(edtPplDtls.getCountry());
		newRec.setCreatedBy(sysUser.getCreatedBy());
		newRec.setCreatedTs(sysUser.getCreatedTs());
		newRec.setCurrencyId(edtPplDtls.getCurrency());
		newRec.setDefaultNetworkId(edtPplDtls.getNetworkId());
		newRec.setEmail(edtPplDtls.getEmail());
		newRec.setEmail2(sysUser.getEmail2());
		newRec.setEmailVerification(sysUser.getEmailVerification());
		newRec.setFirstName(edtPplDtls.getFirstName());
		newRec.setGender(edtPplDtls.getGender());
		newRec.setIsVisible(sysUser.getIsVisible());
		newRec.setLanguageId(edtPplDtls.getLanguage());
		newRec.setLastName(edtPplDtls.getLastName());
		newRec.setModifiedBy(req.getHeader().getUserId());
		newRec.setModifiedTs(new Date());
		newRec.setMsisdn(edtPplDtls.getMob1());
		newRec.setMsisdn2(edtPplDtls.getMob2());
		newRec.setMsisdnVerification(sysUser.getMsisdnVerification());
		newRec.setNickname(sysUser.getNickname());
		newRec.setPassword(sysUser.getPassword());
		newRec.setRecordStatus(sysUser.getRecordStatus());
		newRec.setTown(edtPplDtls.getTown());
		newRec.setType(sysUser.getType());
		newRec.setUI_language(sysUser.getUI_language());
		newRec.setUserGis(sysUser.getUserGis());
		newRec.setUserId(edtPplDtls.getPeopleId());
		newRec.setWebsite(edtPplDtls.getWebsite());
		if (sysUser.getMsisdn().equals(edtPplDtls.getMob1())) {
			newRec.setOperatorId(sysUser.getOperatorId());
		} else {
			String operatorId = lookupService.fetchOperator(edtPplDtls
					.getMob1());
			if (!operatorId.equals("")) {
				newRec.setOperatorId(operatorId);
				log.debug("OperatorId: " + operatorId);
			}
		}
		return newRec;
	}

	private EDITABLE getAllEditable(String value) {
		EDITABLE allEditable = new EDITABLE();
		allEditable.setCMMDTLS(value);
		allEditable.setCountry(value);
		allEditable.setEmail(value);
		allEditable.setFirstName(value);
		allEditable.setLastName(value);
		allEditable.setMob1(value);
		allEditable.setMob2(value);
		allEditable.setNWKDTLS(value);
		allEditable.setOCCDTLS(value);
		allEditable.setTown(value);
		allEditable.setGender(value);
		allEditable.setProfilePic(value);
		return allEditable;
	}

	private EDITABLE getSharingDetails(String type, String fromShare,
			String toShare) {
		EDITABLE editable = null;
		if (type.equals("Y")) {
			editable = getAllEditable("Y");
		} else {
			Profile_Sharing_type share = sharingRepo.querySharedEditableFields(
					fromShare, toShare);
			if (share == null) {
				editable = getAllEditable("N");
			} else {
				editable = new EDITABLE();
				if (share.getAddressFlag().equals("Y")) {
					editable.setCountry("Y");
					editable.setTown("Y");
				} else {
					editable.setCountry("N");
					editable.setTown("N");
				}

				if (share.getEmailFlag().equals("Y")) {
					editable.setEmail("Y");
				} else {
					editable.setEmail("N");
				}

				if (share.getMobileNumberFlag().equals("Y")) {
					editable.setMob1("Y");
					editable.setMob2("Y");
				} else {
					editable.setMob1("N");
					editable.setMob2("N");
				}

				if (share.getNameFlag().equals("Y")) {
					editable.setFirstName("Y");
					editable.setLastName("Y");
				} else {
					editable.setFirstName("N");
					editable.setLastName("N");
				}

				if (share.getGenderFlag().equals("Y")) {
					editable.setGender("Y");
				} else {
					editable.setGender("N");
				}

				editable.setCMMDTLS("Y");
				editable.setNWKDTLS("Y");
				editable.setOCCDTLS("Y");
				editable.setProfilePic("Y");
			}
		}

		return editable;
	}

	@Transactional
	public AddUserAsAdminRes addAdmin(AddUserAsAdminReq req) {
		ADDADMINUSR adduser = req.getADDADMINUSR();
		AddUserAsAdminRes res = new AddUserAsAdminRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		int returnCount = 0;
		if (adduser != null) {

			returnCount = peopleRepo.addAsAdmin(adduser.getUserNetworkid(),
					adduser.getUserid(), adduser.getNetworkid(), req
							.getHeader().getUserId());

			if (returnCount > 0) {

				errorCode = ERROR_CODE.PPL_ADMIN_ADD_SC;
				SystemUser entity1 = new SystemUser();
				entity1.setUserId(adduser.getUserid());
				SystemUser entity = sysUserRepo.findOne(entity1,
						adduser.getUserid());
				if (entity != null) {
					HeaderFactory.setExtraParamMap("$1", entity.getFirstName());
				}

			} else {
				errorCode = ERROR_CODE.ES_NR_001;
			}
		} else {
			errorCode = ERROR_CODE.DM_SV_002;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				serviceName, "addAdmin", "", errorCode);
		res.setHeader(header);
		return res;
	}
}
