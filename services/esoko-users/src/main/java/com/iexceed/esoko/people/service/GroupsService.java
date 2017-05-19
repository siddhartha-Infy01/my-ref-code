package com.iexceed.esoko.people.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.NetworkUseridRepo;
import com.iexceed.esoko.domain.dao.SmartGroupRuleRepo;
import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao.UserSmartGroupRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.ProfileSharingTypeRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.domain.entity.Profile_Sharing_type;
import com.iexceed.esoko.domain.entity.SmartgroupRule;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserGroupPK;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity.UserSmartGroup;
import com.iexceed.esoko.domain.entity.UserSmartGroupPK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.people.ADDTOGRPDTL;
import com.iexceed.esoko.jaxb.people.AddToGroupReq;
import com.iexceed.esoko.jaxb.people.AddToGroupRes;
import com.iexceed.esoko.jaxb.people.CMMDTLS;
import com.iexceed.esoko.jaxb.people.CRGROUPDTL;
import com.iexceed.esoko.jaxb.people.CheckGroupNameRes;
import com.iexceed.esoko.jaxb.people.CreateGroupReq;
import com.iexceed.esoko.jaxb.people.CreateGroupRes;
import com.iexceed.esoko.jaxb.people.DELFRMGRP;
import com.iexceed.esoko.jaxb.people.DLGROUPDTL;
import com.iexceed.esoko.jaxb.people.DeleteFromGroupReq;
import com.iexceed.esoko.jaxb.people.DeleteFromGroupRes;
import com.iexceed.esoko.jaxb.people.DeleteGroupReq;
import com.iexceed.esoko.jaxb.people.DeleteGroupRes;
import com.iexceed.esoko.jaxb.people.EDGROUPDTL;
import com.iexceed.esoko.jaxb.people.EDITABLE;
import com.iexceed.esoko.jaxb.people.EditGroupReq;
import com.iexceed.esoko.jaxb.people.EditGroupRes;
import com.iexceed.esoko.jaxb.people.GetSmrtGrpCntReq;
import com.iexceed.esoko.jaxb.people.GetSmrtGrpCntRes;
import com.iexceed.esoko.jaxb.people.Header;
import com.iexceed.esoko.jaxb.people.NWKDTLS;
import com.iexceed.esoko.jaxb.people.OCCDTLS;
import com.iexceed.esoko.jaxb.people.PEOPLEDTL;
import com.iexceed.esoko.jaxb.people.QRGROUPDETAIL;
import com.iexceed.esoko.jaxb.people.QueryAllGroupsRes;
import com.iexceed.esoko.jaxb.people.RULES;
import com.iexceed.esoko.jaxb.people.SMRTGRPCNT;
import com.iexceed.esoko.jaxb.people.SMRTGRPCNTREQ;
import com.iexceed.esoko.jaxb.people.SMRTGRPDTL;
import com.iexceed.esoko.jaxb.people.ViewGroupDetailRes;
import com.iexceed.esoko.jaxb.people.ViewSmrtGrpDtlRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class GroupsService {
	private static Logger log = LoggerUtils.getLogger();	
	@Autowired
	private GroupMasterRepo grpRepo;
	@Autowired
	private UserGroupRepo usrGrpRepo;
	@Autowired
	private PeopleRepo pplRepo;
	@Autowired
	private UserCommodityRepo usrComRepo;
	@Autowired
	private NetworkUseridRepo nwkusrIdRepo;
	@Autowired
	private UserOccupationRepo usrOccpRepo;
	@Autowired
	private SmartGroupRuleRepo smrtGrpRuleRepo;
	@Autowired
	private ProfileSharingTypeRepo sharingRepo;
	@Autowired
	private UserPictureRepo userPicRepo;
	@Autowired
	private UserSmartGroupRepo usrSmrtGrpRepo;

	public QueryAllGroupsRes queryAllGroups(String networkId) {
		log.info("Insisde QueryAllGroupsRes -> queryAllGroups");
		log.debug("NetworkId: " + networkId);
		QueryAllGroupsRes groupRes = new QueryAllGroupsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (StringUtils.isNotEmpty(networkId)) {
			List<Map<String, Object>> grpDtls = grpRepo
					.queryGroupByNetwks(networkId);
			if (grpDtls.size() == 0) {
				log.info("No record found");
				errorCode = ERROR_CODE.ES_NR_001;
			} else {
				List<QRGROUPDETAIL> grpDtlLst = new ArrayList<QRGROUPDETAIL>();
				for (Map<String, Object> grpMap : grpDtls) {
					QRGROUPDETAIL grpDtl = new QRGROUPDETAIL();
					if (!(grpMap.get("groupId").toString()
							.equals("Administrator"))) {
						grpDtl.setGroupId(grpMap.get("groupId").toString());
						grpDtl.setName(grpMap.get("groupName").toString());
						if (grpMap.get("type").toString().equalsIgnoreCase("S")) {
							grpDtl.setSmartGroup("Y");
						} else {
							grpDtl.setSmartGroup("N");
						}
						grpDtl.setNoOfPeople(Integer.valueOf(grpMap
								.get("count").toString()));
						grpDtlLst.add(grpDtl);
						grpDtl = null;
					}
				}

				List<Map<String, Object>> ungrpdList = pplRepo
						.queryUngrouped(networkId);
				QRGROUPDETAIL ungrouped = new QRGROUPDETAIL();
				ungrouped.setGroupId("Ungrouped");
				ungrouped.setName("Ungrouped");
				ungrouped.setNoOfPeople(ungrpdList.size());
				ungrouped.setSmartGroup("Y");
				grpDtlLst.add(ungrouped);

				groupRes.getQRGROUPDETAIL().addAll(grpDtlLst);
				errorCode = ERROR_CODE.ES_SC_001;
			}
		} else {
			log.info("NetworkId is empty");
			errorCode = ERROR_CODE.ES_PK_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "queryAllGroups", "", errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	@Transactional
	public DeleteGroupRes deleteGroup(DeleteGroupReq req) {
		log.info("Inside DeleteGroupRes -> deleteGroup");
		DeleteGroupRes groupRes = new DeleteGroupRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<DLGROUPDTL> grpDtlLst = req.getDLGROUPDTL();
		try {			
			for (DLGROUPDTL grpDtl : grpDtlLst) {
				if (StringUtils.isNotEmpty(grpDtl.getNetworkId())
						&& StringUtils.isNotEmpty(grpDtl.getGroupId())) {
					log.debug("NetworkId: " + grpDtl.getNetworkId());
					log.debug("GroupId: " + grpDtl.getGroupId());
					GroupMasterPK grpMstPk = new GroupMasterPK();
					grpMstPk.setGroupId(grpDtl.getGroupId());
					grpMstPk.setNetworkId(grpDtl.getNetworkId());

					GroupMaster groupMaster = grpRepo.findOne(
							GroupMaster.class, grpMstPk);
					if (groupMaster == null) {
						log.info("No record found");
						// errorCode = ERROR_CODE.ES_NR_001;
					} else {
						grpRepo.delete(groupMaster);
						List<UserGroup> grpList = usrGrpRepo
								.queryUsersByNwkGrpId(grpDtl.getGroupId(),
										grpDtl.getNetworkId());
						if (grpList.size() != 0) {
							usrGrpRepo.delete(grpList);
						}

						if (groupMaster.getType().equalsIgnoreCase("S")) {							
							List<SmartgroupRule> ruleList = smrtGrpRuleRepo
									.queryAllRules(grpDtl.getGroupId(),
											grpDtl.getNetworkId());
							if (ruleList.size() != 0) {
								smrtGrpRuleRepo.delete(ruleList);
							}
							
							List<UserSmartGroup> groupList = usrSmrtGrpRepo
									.queryAllEntities(grpDtl.getGroupId(),
											grpDtl.getNetworkId());
							if (groupList.size() != 0) {
								usrSmrtGrpRepo.delete(groupList);
							}
						}

					}
					errorCode = ERROR_CODE.PPL_GR_DL;
				}
			}			
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "deleteGroup", req.getHeader().getUserId(),
				errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	@Transactional
	public EditGroupRes editGroup(EditGroupReq req) {
		log.info("Inside EditGroupRes -> editGroup");
		EditGroupRes groupRes = new EditGroupRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		EDGROUPDTL grpDtl = req.getEDGROUPDTL();
		if (StringUtils.isNotEmpty(grpDtl.getNetworkId())
				&& StringUtils.isNotEmpty(grpDtl.getGroupId())) {
			log.debug("NetworkId: " + grpDtl.getNetworkId());
			log.debug("GroupId: " + grpDtl.getGroupId());
			log.debug("UpdatedName: " + grpDtl.getUpdatedName());
			log.debug("SmartGroup: " + grpDtl.getSmartGroup());
			GroupMasterPK grpPk = new GroupMasterPK();
			grpPk.setGroupId(grpDtl.getGroupId());
			grpPk.setNetworkId(grpDtl.getNetworkId());
			GroupMaster grp = grpRepo.findOne(GroupMaster.class, grpPk);
			if (grp == null) {
				errorCode = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {
				try {
					Date dt = new Date();
					Timestamp ts = new Timestamp(dt.getTime());
					GroupMaster newRec = new GroupMaster();
					newRec.setCreatedBy(grp.getCreatedBy());
					newRec.setCreatedTs(grp.getCreatedTs());
					newRec.setModifiedBy(req.getHeader().getUserId());
					newRec.setModifiedTs(ts);
					newRec.setType(grp.getType());
					if (!(grpDtl.getGroupId().equals("Recently added") || grpDtl
							.getGroupId().equals("Administrator"))) {
						newRec.setGROUP_name(grpDtl.getUpdatedName());
						newRec.setId(grpPk);
						grpRepo.merge(newRec);
						log.debug("GroupMaster updated successfully");
					}
					if (grpDtl.getSmartGroup().equalsIgnoreCase("Y")) {
						List<SmartgroupRule> ruleList = smrtGrpRuleRepo
								.queryAllRules(grpDtl.getGroupId(),
										grpDtl.getNetworkId());
						if (ruleList.size() != 0) {
							smrtGrpRuleRepo.delete(ruleList);
						}
						// Adding other rules
						List<RULES> rulesList = grpDtl.getRULES();
						createSmartGroupRules(rulesList, grpDtl.getGroupId(),
								grpDtl.getNetworkId(), grpDtl.getMatch(),
								grpDtl.getLimit(), grpDtl.getSelectedBy());

						List<UserSmartGroup> groupList = usrSmrtGrpRepo
								.queryAllEntities(grpDtl.getGroupId(),
										grpDtl.getNetworkId());
						if (groupList.size() != 0) {
							usrSmrtGrpRepo.delete(groupList);
						}
						this.populateUserSmartGroup(grpDtl.getGroupId(),
								grpDtl.getNetworkId(), grpDtl.getNetworkId(),
								grp.getCreatedTs(),
								req.getHeader().getUserId(), ts);

					}					
					errorCode = ERROR_CODE.PPL_GR_ED;
				} catch (DomainException e) {
					errorCode = e.getCode();
					log.error(Utils.getStackTrace(e));
				}
			}

		} else {
			log.debug("Primary key is empty");
			errorCode = ERROR_CODE.ES_PK_001;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "editGroup", req.getHeader().getUserId(),
				errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	public ViewGroupDetailRes viewGroupDetail(String networkId, String groupId) {
		log.info("Inside ViewGroupDetailRes -> viewGroupDetail");
		log.debug("NetworkId: " + networkId);
		log.debug("GroupId: " + groupId);
		ViewGroupDetailRes groupRes = new ViewGroupDetailRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		boolean isSmartGrp = pplRepo.isSmartGroup(groupId, networkId);
		List<Map<String, Object>> pplLst = null;
		if (isSmartGrp) {
			pplLst = pplRepo.querySmartGroupsDetail(groupId, networkId);
		} else {
			pplLst = pplRepo.queryGroupsDetail(groupId, networkId);
		}
		if (pplLst.size() == 0) {
			log.debug("No record found");
			errorCode = ERROR_CODE.GR_NR_001;
		} else {
			List<PEOPLEDTL> pplDtlLst = new ArrayList<PEOPLEDTL>();

			for (Map<String, Object> ppl : pplLst) {
				PEOPLEDTL pplDtl = new PEOPLEDTL();
				String pplNetworkId = "";
				String peopleId = "";

				if (ppl.get("networkId") != null) {
					pplNetworkId = ppl.get("networkId").toString();
				}

				if (ppl.get("peopleId") != null) {
					peopleId = ppl.get("peopleId").toString();
				}
				pplDtl.setPeopleId(peopleId);
				if (ppl.get("editFlag") != null) {
					String editable = ppl.get("editFlag").toString();
					pplDtl.setEdit(editable);
					pplDtl.setEDITABLE(this.getSharingDetails(editable,
							pplNetworkId, networkId));
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

				UserPicture userPic = userPicRepo.queryUserPic(peopleId);
				if (userPic != null) {
					pplDtl.setProfilePic(userPic.getContent());
				}

				List<UserCommodity> usrComLst = usrComRepo.queryUserCommodity(
						peopleId, pplNetworkId);
				if (usrComLst.size() != 0) {
					List<CMMDTLS> cmDtlLst = new ArrayList<CMMDTLS>();
					for (UserCommodity usrCom : usrComLst) {
						UserCommodityPK usrComPk = usrCom.getId();
						CMMDTLS cmDtl = new CMMDTLS();
						cmDtl.setCommodity(usrComPk.getCommodityId());
						cmDtlLst.add(cmDtl);
					}
					pplDtl.getCMMDTLS().addAll(cmDtlLst);
				}

				List<NetworkUserid> nwkUsrIdLst = nwkusrIdRepo
						.queryNetworksByUser(peopleId);
				if (nwkUsrIdLst.size() != 0) {
					List<NWKDTLS> nwkDtlLst = new ArrayList<NWKDTLS>();
					for (NetworkUserid nwkUsrId : nwkUsrIdLst) {
						NWKDTLS nwkDtl = new NWKDTLS();
						NetworkUseridPK nwkUsrIdPk = nwkUsrId.getId();
						nwkDtl.setNetwork(nwkUsrIdPk.getNetworkId());
						nwkDtlLst.add(nwkDtl);
					}
					pplDtl.getNWKDTLS().addAll(nwkDtlLst);
				}

				List<UserOccupation> occUsrIdLst = usrOccpRepo
						.queryusrOccupationbyuserId(peopleId, pplNetworkId);
				if (occUsrIdLst.size() != 0) {
					List<OCCDTLS> occDtls = new ArrayList<OCCDTLS>();
					for (UserOccupation occupation : occUsrIdLst) {
						UserOccupationPK occPk = occupation.getId();
						OCCDTLS occDtl = new OCCDTLS();
						occDtl.setOccupation(occPk.getOccupationId());
						occDtls.add(occDtl);
					}
					pplDtl.getOCCDTLS().addAll(occDtls);
				}
				pplDtlLst.add(pplDtl);
			}
			groupRes.getPEOPLEDTL().addAll(pplDtlLst);
			errorCode = ERROR_CODE.ES_SC_001;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "viewGroupDetail", "", errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	@Transactional
	public CreateGroupRes createGroup(CreateGroupReq req) {
		log.info("Inside CreateGroupRes -> createGroup");
		CreateGroupRes groupRes = new CreateGroupRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		CRGROUPDTL grpDtl = req.getCRGROUPDTL();
		if (StringUtils.isNotEmpty(grpDtl.getNetworkId())
				&& StringUtils.isNotEmpty(grpDtl.getName())) {
			try {
				log.debug("NetworkId: " + grpDtl.getNetworkId());
				log.debug("name: " + grpDtl.getName());
				GroupMasterPK grpMstPK = new GroupMasterPK();
				grpMstPK.setGroupId(grpDtl.getName());
				grpMstPK.setNetworkId(grpDtl.getNetworkId());
				boolean recExist = grpRepo.exists(GroupMaster.class, grpMstPK);
				if (recExist) {
					log.debug("Record already exists");
					errorCode = ERROR_CODE.PPL_GR_001;
				} else {
					GroupMaster newGrpMst = new GroupMaster();
					newGrpMst.setCreatedBy(req.getHeader().getUserId());
					Date dt = new Date();
					newGrpMst.setCreatedTs(dt);
					newGrpMst.setGROUP_name(grpDtl.getName());
					newGrpMst.setId(grpMstPK);

					log.debug("SmartGroup: " + grpDtl.getSmartGroup());
					if (StringUtils.isNotEmpty(grpDtl.getSmartGroup())
							&& grpDtl.getSmartGroup().equalsIgnoreCase("Y")) {
						newGrpMst.setType("S");

						List<RULES> rulesList = grpDtl.getRULES();
						createSmartGroupRules(rulesList, grpDtl.getName(),
								grpDtl.getNetworkId(), grpDtl.getMatch(),
								grpDtl.getLimit(), grpDtl.getSelectedBy());

						populateUserSmartGroup(grpDtl.getName(),
								grpDtl.getNetworkId(), req.getHeader()
										.getUserId(), dt, null, null);
					} else {
						newGrpMst.setType("N");
					}
					grpRepo.save(newGrpMst);				
					errorCode = ERROR_CODE.PPL_GR_CR;
					log.debug("Data saved successfully");
				}
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}

		} else {
			errorCode = ERROR_CODE.ES_PK_001;
			log.debug("Primary key not found");
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "createGroup", req.getHeader().getUserId(),
				errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	private void populateUserSmartGroup(String groupId, String networkId,
			String createdBy, Date createdTs, String modifiedBy, Date modifiedTs) {
		List<Map<String, Object>> pplLst = pplRepo.getSmartGroupRecords(
				groupId, networkId);
		if (pplLst.size() != 0) {
			for (Map<String, Object> entity : pplLst) {
				UserSmartGroupPK smrtGrpPK = new UserSmartGroupPK();
				smrtGrpPK.setGroupId(groupId);
				smrtGrpPK.setNetworkId(networkId);
				smrtGrpPK.setUserId(entity.get("peopleId").toString());
				UserSmartGroup smrtGrp = new UserSmartGroup();
				smrtGrp.setId(smrtGrpPK);
				smrtGrp.setCreatedBy(createdBy);
				smrtGrp.setCreatedTs(createdTs);
				smrtGrp.setModifiedBy(modifiedBy);
				smrtGrp.setModifiedTs(modifiedTs);
				usrSmrtGrpRepo.save(smrtGrp);
			}
		}
	}

	@Transactional
	public AddToGroupRes addToGroup(AddToGroupReq req) {
		log.info("Inside CreateGroupRes -> createGroup");
		AddToGroupRes groupRes = new AddToGroupRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<ADDTOGRPDTL> grpDtlLst = req.getADDTOGRPDTL();
		int userCount = 0;
		int groupCount = 0;
		String tmpUsrId = "";
		String tmpGrpId = "";
		for (ADDTOGRPDTL grpDtl : grpDtlLst) {
			try {
				if (StringUtils.isNotEmpty(grpDtl.getUserId())
						&& StringUtils.isNotEmpty(grpDtl.getNetworkId())
						&& StringUtils.isNotEmpty(grpDtl.getGroupId())) {
					log.debug("UserId: " + grpDtl.getUserId());
					log.debug("NetworkId: " + grpDtl.getNetworkId());
					log.debug("GroupId: " + grpDtl.getGroupId());
					UserGroupPK usrGrpPk = new UserGroupPK();
					usrGrpPk.setGroupId(grpDtl.getGroupId());
					usrGrpPk.setNetworkId(grpDtl.getNetworkId());
					usrGrpPk.setUserId(grpDtl.getUserId());
					UserGroup usrGrp = usrGrpRepo.findOne(UserGroup.class,
							usrGrpPk);
					Date dt = new Date();
					if (usrGrp == null) {
						usrGrp = new UserGroup();
						usrGrp.setCreatedBy(req.getHeader().getUserId());
						usrGrp.setCreatedTs(dt);
						usrGrp.setId(usrGrpPk);
						usrGrpRepo.save(usrGrp);
					} else {
						usrGrp.setCreatedBy(usrGrp.getCreatedBy());
						usrGrp.setCreatedTs(usrGrp.getCreatedTs());
						usrGrp.setModifiedBy(req.getHeader().getUserId());
						usrGrp.setModifiedTs(dt);
						usrGrpRepo.merge(usrGrp);
					}
					if (!tmpUsrId.equals(grpDtl.getUserId())) {
						userCount++;
					}
					if (!tmpGrpId.equals(grpDtl.getGroupId())) {
						groupCount++;
					}
					tmpUsrId = grpDtl.getUserId();
					tmpGrpId = grpDtl.getGroupId();
					log.debug("Data saved successfully");
				}
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
				break;
			}
		}

		if (userCount == 1) {
			errorCode = ERROR_CODE.GR_SC_001;
		} else if (userCount > 1) {
			errorCode = ERROR_CODE.GR_SC_002;
		} else {
			errorCode = ERROR_CODE.ES_SV_002;
		}

		if (errorCode != ERROR_CODE.ES_SV_002) {
			HeaderFactory.setExtraParamMap("$1", userCount + "");
			HeaderFactory.setExtraParamMap("$2", groupCount + "");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "addToGroup", req.getHeader().getUserId(),
				errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	@Transactional
	public DeleteFromGroupRes deleteFromGroup(DeleteFromGroupReq req) {
		log.info("Inside DeleteFromGroupRes -> deleteFromGroup");
		DeleteFromGroupRes groupRes = new DeleteFromGroupRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<DELFRMGRP> grpDtls = req.getDELFRMGRP();
		int userIdCount = 0;
		String tmpUserId = "";
		for (DELFRMGRP grp : grpDtls) {
			try {
				log.debug("GroupId: " + grp.getGroupId());
				log.debug("NetworkId: " + grp.getNetworkId());
				log.debug("UserId: " + grp.getUserId());
				UserGroupPK usrGrpPk = new UserGroupPK();
				usrGrpPk.setGroupId(grp.getGroupId());
				usrGrpPk.setNetworkId(grp.getNetworkId());
				usrGrpPk.setUserId(grp.getUserId());
				UserGroup usrGrp = usrGrpRepo
						.findOne(UserGroup.class, usrGrpPk);
				if (usrGrp != null) {
					usrGrpRepo.delete(usrGrp);
					log.debug("Record deleted successfully");
				} else {
					log.debug("No record found");
					// errorCode = ERROR_CODE.ES_NR_001;
				}
				if (!tmpUserId.equals(grp.getUserId())) {
					userIdCount++;
				}
				tmpUserId = grp.getUserId();
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}
		}
		if (userIdCount == 1) {
			errorCode = ERROR_CODE.GR_RM_001;
		} else if (userIdCount > 1) {
			errorCode = ERROR_CODE.GR_RM_002;
		} else {
			errorCode = ERROR_CODE.DM_SV_003;
		}
		if (errorCode != ERROR_CODE.DM_SV_003) {
			HeaderFactory.setExtraParamMap("$1", userIdCount + "");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "deleteFromGroup",
				req.getHeader().getUserId(), errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	public ViewSmrtGrpDtlRes viewSmrtGrpDtl(String networkId, String groupId) {
		log.info("Inside ViewSmrtGrpDtlRes -> viewSmrtGrpDtl");
		log.debug("NetworkId: " + networkId);
		log.debug("GroupId: " + groupId);
		ViewSmrtGrpDtlRes groupRes = new ViewSmrtGrpDtlRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<SmartgroupRule> smrtRuleLst = smrtGrpRuleRepo.queryAllRules(
				groupId, networkId);
		if (smrtRuleLst.size() == 0) {
			log.debug("No record found");
			errorCode = ERROR_CODE.ES_NR_001;
		} else {
			List<RULES> smrtRuleDtl = new ArrayList<RULES>();
			for (SmartgroupRule smrtGrp : smrtRuleLst) {
				RULES rule = new RULES();
				rule.setCondition(smrtGrp.getConditions());
				rule.setType(smrtGrp.getType());
				rule.setValue(smrtGrp.getValue());
				smrtRuleDtl.add(rule);
			}
			SMRTGRPDTL smrtGrpDtl = new SMRTGRPDTL();
			smrtGrpDtl.getRULES().addAll(smrtRuleDtl);
			groupRes.setSMRTGRPDTL(smrtGrpDtl);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "viewSmrtGrpDtl", "", errorCode);
		groupRes.setHeader(header);
		return groupRes;
	}

	public GetSmrtGrpCntRes getSmrtGrpCnt(GetSmrtGrpCntReq req) {
		log.info("Inside GroupService -> getSmrtGrpCnt");
		GetSmrtGrpCntRes groupRes = new GetSmrtGrpCntRes();
		SMRTGRPCNTREQ smrtGrpReq = req.getSMRTGRPCNTREQ();
		String networkId = smrtGrpReq.getNetworkId();
		String matchValue = smrtGrpReq.getMatch();
		String selectedBy = smrtGrpReq.getSelectedBy();
		long limitValue = smrtGrpReq.getLimit();
		List<Map<String, String>> ruleList = new ArrayList<Map<String, String>>();
		for (RULES smrtGrp : smrtGrpReq.getRULES()) {
			Map<String, String> rule = new HashMap<String, String>();
			rule.put("Type", smrtGrp.getType());
			rule.put("Condition", smrtGrp.getCondition());
			rule.put("Value", smrtGrp.getValue());
			ruleList.add(rule);
		}
		long count = pplRepo.getSmartGroupCount(ruleList, networkId,
				matchValue, selectedBy, limitValue);
		SMRTGRPCNT smrtGrpRes = new SMRTGRPCNT();
		smrtGrpRes.setCount(count);
		Header header = (Header) HeaderFactory
				.getHeader(HeaderType.PEOPLE, "GroupsService",
						"GetSmartGroupCount", "", ERROR_CODE.ES_SC_001);
		groupRes.setHeader(header);
		groupRes.setSMRTGRPCNT(smrtGrpRes);
		return groupRes;
	}

	public CheckGroupNameRes checkGroupName(String groupName, String networkId) {
		log.info("Inside GroupsService -> checkGroupName");
		CheckGroupNameRes res = new CheckGroupNameRes();
		ERROR_CODE errorCode = null;
		GroupMasterPK grpPK = new GroupMasterPK();
		grpPK.setGroupId(groupName);
		grpPK.setNetworkId(networkId);
		boolean exists = grpRepo.exists(GroupMaster.class, grpPK);
		if (exists) {
			errorCode = ERROR_CODE.PPL_GR_001;
		} else {
			errorCode = ERROR_CODE.PPL_GR_002;
		}
		HeaderFactory.setExtraParamMap("$1", groupName);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.PEOPLE,
				"GroupsService", "checkGroupName", "", errorCode);
		res.setHeader(header);
		return res;
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

				editable.setCMMDTLS("Y");
				editable.setNWKDTLS("Y");
				editable.setOCCDTLS("Y");
				editable.setProfilePic("Y");
			}
		}

		return editable;
	}

	private void createSmartGroupRules(List<RULES> rulesList, String groupId,
			String networkId, String matchVal, long limit, String selectedBy) {
		if ((rulesList != null) && (rulesList.size() != 0)) {
			// Adding rule for matching
			if (!groupId.equals("Recently added")) {
				SmartgroupRule matchRule = new SmartgroupRule();
				matchRule.setGroupId(groupId);
				matchRule.setNetworkId(networkId);
				matchRule.setType("Match");
				log.debug("Match: " + matchVal);
				matchRule.setValue(matchVal);
				smrtGrpRuleRepo.save(matchRule);
			}
			for (RULES rule : rulesList) {
				log.debug("Value: " + rule.getValue());
				log.debug("Type: " + rule.getType());
				log.debug("Condition: " + rule.getCondition());
				SmartgroupRule commonRule = new SmartgroupRule();
				commonRule.setGroupId(groupId);
				commonRule.setNetworkId(networkId);
				commonRule.setType(rule.getType());
				commonRule.setConditions(rule.getCondition());
				commonRule.setValue(rule.getValue());
				smrtGrpRuleRepo.save(commonRule);
			}
		}
		log.debug("Limit: " + limit);
		if ((limit != 0) && !groupId.equals("Recently added")) {
			// Adding rule for limit of People
			SmartgroupRule limitRule = new SmartgroupRule();
			limitRule.setGroupId(groupId);
			limitRule.setNetworkId(networkId);
			limitRule.setType("Limit");
			limitRule.setValue(Long.toString(limit));
			log.debug("SelectedBy: " + selectedBy);
			limitRule.setConditions(selectedBy);
			smrtGrpRuleRepo.save(limitRule);
		}
	}

}
