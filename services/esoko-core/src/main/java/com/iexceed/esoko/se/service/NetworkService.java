package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.Network_permissionRepo;
import com.iexceed.esoko.domain.dao.Network_privacyRepo;
import com.iexceed.esoko.domain.dao.SmartGroupRuleRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.Network_permission;
import com.iexceed.esoko.domain.entity.Network_permissionPK;
import com.iexceed.esoko.domain.entity.Network_privacy;
import com.iexceed.esoko.domain.entity.SmartgroupRule;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.CRNWKDETAILS;
import com.iexceed.esoko.jaxb.se.CreateNetworkReq;
import com.iexceed.esoko.jaxb.se.CreateNetworkRes;
import com.iexceed.esoko.jaxb.se.DLNWKDETAILS;
import com.iexceed.esoko.jaxb.se.DeleteNetworkReq;
import com.iexceed.esoko.jaxb.se.DeleteNetworkRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRNWKDETAILS;
import com.iexceed.esoko.jaxb.se.QRNWKDETAILSUSR;
import com.iexceed.esoko.jaxb.se.QueryNetworksByUserRes;
import com.iexceed.esoko.jaxb.se.QueryNetworksRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class NetworkService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	NetworkRepo networkRepo;
	@Autowired
	GroupMasterRepo grpMstRepo;
	@Autowired
	Network_privacyRepo nwkPrvRepo;
	@Autowired
	SmartGroupRuleRepo smrtGrpRepo;
	@Autowired
	System_accountRepo sysAccRepo;
	@Autowired
	EsokoAppRepo esokoRepo;
	@Autowired
	Network_permissionRepo permissionRepo;
	
	Enum<ERROR_CODE> ERROR = null;
	Header header = null;
	private final String serviceName = "NetworkService";

	public QueryNetworksRes queryNetworks(String networkId, String networkName,
			String type, String parentId, String userId, String userData) {
		QueryNetworksRes queryNwkRes = new QueryNetworksRes();
		List<QRNWKDETAILS> qrNwkDtlsList = new ArrayList<QRNWKDETAILS>();
		if (null != parentId) {
			List<Network> nwkEntity = (List<Network>) networkRepo
					.queryNetworkbyParentId(parentId);

			if (nwkEntity.size() != 0) {
				for (Network nwkResp : nwkEntity) {
					QRNWKDETAILS qrNwkDtls = new QRNWKDETAILS();
					qrNwkDtls.setNetworkID(nwkResp.getNetworkId());
					qrNwkDtls.setName(nwkResp.getName());
					qrNwkDtls.setType(nwkResp.getType());
					qrNwkDtls.setDescription(nwkResp.getDescription());
					qrNwkDtls.setParentid(nwkResp.getParentId());
					qrNwkDtlsList.add(qrNwkDtls);
				}
				queryNwkRes.getQRNWKDETAILS().addAll(qrNwkDtlsList);
			} else if (null != networkId) {
				Network entity = networkRepo.queryNetworkUserInfo(networkId);
				if (entity != null) {
					QRNWKDETAILS qrNwkDtls = new QRNWKDETAILS();
					qrNwkDtls.setNetworkID(entity.getNetworkId());
					qrNwkDtls.setName(entity.getName());
					qrNwkDtls.setType(entity.getType());
					qrNwkDtls.setDescription(entity.getDescription());
					qrNwkDtls.setParentid(entity.getParentId());
					qrNwkDtlsList.add(qrNwkDtls);
				}
			}

			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			List<Network> listNetwork = (List<Network>) networkRepo
					.listAllPublicNwks();
			for (Network entity : listNetwork) {
				QRNWKDETAILS qrNwkDtls = new QRNWKDETAILS();
				qrNwkDtls.setNetworkID(entity.getNetworkId());
				qrNwkDtls.setName(entity.getName());
				qrNwkDtls.setType(entity.getType());
				qrNwkDtls.setDescription(entity.getDescription());
				qrNwkDtls.setParentid(entity.getParentId());
				qrNwkDtlsList.add(qrNwkDtls);
			}
			queryNwkRes.getQRNWKDETAILS().addAll(qrNwkDtlsList);
			ERROR = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query Networks", "", ERROR);
		queryNwkRes.setHeader(header);
		return queryNwkRes;
	}

	public QueryNetworksByUserRes queryNetworksByUser(String userId) {
		QueryNetworksByUserRes queryNwkByUserRes = new QueryNetworksByUserRes();
		List<Network> nwkEntity = (List<Network>) networkRepo
				.queryNetworkUserInfo(userId);
		List<QRNWKDETAILSUSR> qrNwkUsrDtlsList = new ArrayList<QRNWKDETAILSUSR>();
		try {
			if (nwkEntity.size() != 0) {
				for (Network nwkResp : nwkEntity) {
					QRNWKDETAILSUSR qrNwkDtls = new QRNWKDETAILSUSR();
					qrNwkDtls.setNetworkID(nwkResp.getNetworkId());
					qrNwkDtls.setName(nwkResp.getName());
					qrNwkUsrDtlsList.add(qrNwkDtls);
					qrNwkDtls = null;
				}
				queryNwkByUserRes.getQRNWKDETAILSUSR().addAll(qrNwkUsrDtlsList);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query Networks", "", ERROR);
		return queryNwkByUserRes;
	}

	@Transactional
	public CreateNetworkRes createNetwork(CreateNetworkReq req) {
		log.info("Inside NetworkService -> createNetwork");
		CreateNetworkRes createNetworkRes = new CreateNetworkRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		try {
			if (StringUtils.isNotEmpty(req.getCRNWKDETAILS().getNetworkID())) {
				boolean recExists = networkRepo.exists(Network.class, req
						.getCRNWKDETAILS().getNetworkID());
				boolean recExstInNwkPrv = nwkPrvRepo.exists(
						Network_privacy.class, req.getCRNWKDETAILS()
								.getNetworkID());
				if (recExists || recExstInNwkPrv) {
					log.info("Record already exists");
					errorCode = ERROR_CODE.ES_ER_001;
				} else {
					Network networkEntity = new Network();
					CRNWKDETAILS nwkReqDtls = req.getCRNWKDETAILS();
					log.debug("NetworID:" + nwkReqDtls.getNetworkID());
					networkEntity.setNetworkId(nwkReqDtls.getNetworkID()
							.toString());
					log.debug("Type::" + nwkReqDtls.getType());
					networkEntity.setType(nwkReqDtls.getType());
					Network network = networkRepo.queryCountryNwk(nwkReqDtls
							.getCountry());

					String parentNwkId = null;
					if (network == null) {
						parentNwkId = Utils.ESOKO_NETWORK;
					} else {
						parentNwkId = network.getNetworkId();
					}
					log.debug("ParentId:" + parentNwkId);
					networkEntity.setParentId(parentNwkId);
					log.debug("Name:" + nwkReqDtls.getName());
					networkEntity.setName(nwkReqDtls.getName());
					log.debug("Description:" + nwkReqDtls.getDescription());
					networkEntity.setDescription(nwkReqDtls.getDescription());
					log.debug("RecordStatus: A");
					networkEntity.setRecordStatus("A");
					log.debug("AuthStat: A");
					networkEntity.setAuthStat("U");
					log.debug("OwnerUserId: " + nwkReqDtls.getNetworkOwner());
					networkEntity.setOwnerUserId(nwkReqDtls.getNetworkOwner());
					log.debug("IsPrivate: Y");
					networkEntity.setIsPrivate("Y");
					log.debug("PrimaryLocationId: " + nwkReqDtls.getCountry());
					networkEntity.setPrimaryLocationId(nwkReqDtls.getCountry());
					log.debug("Website: " + nwkReqDtls.getWebsite());
					networkEntity.setWebsite(nwkReqDtls.getWebsite());
					log.debug("CreatedBy: " + req.getHeader().getUserId());
					networkEntity.setCreatedBy(req.getHeader().getUserId());
					Date timeStamp = new Date();
					log.debug("CreatedTs: " + timeStamp);
					networkEntity.setCreatedTs(timeStamp);
					log.debug("IsVisible: 1");
					networkEntity.setIsVisible("1");
					networkRepo.save(networkEntity);

					// Creating network privacy table
					Network_privacy nwkPrivacy = new Network_privacy();
					nwkPrivacy.setContacts("M");
					nwkPrivacy.setCreatedBy(req.getHeader().getUserId());
					nwkPrivacy.setCreatedTs(timeStamp);
					nwkPrivacy.setNetworkId(nwkReqDtls.getNetworkID()
							.toString());
					nwkPrivacy.setNewsLib("M");
					nwkPrivacy.setOffers("M");
					nwkPrivacy.setPrices("M");
					nwkPrvRepo.save(nwkPrivacy);										

					// Creating System Accounts table
					System_account sysAcc = new System_account();
					long accNumber = esokoRepo.getSequenceNumber();
					sysAcc.setAccName("N" + accNumber);
					log.debug("Account Name: " + "N" + accNumber);
					sysAcc.setAccountNo("N" + accNumber);
					log.debug("Account Number: " + "N" + accNumber);
					sysAcc.setAcCurrency("USD");
					log.debug("Currency: USD");
					sysAcc.setBalance(0.00);
					log.debug("Balance: 0");
					sysAcc.setCreatedBy(req.getHeader().getUserId());
					sysAcc.setCreatedTs(new Date());
					sysAcc.setOwnerId(nwkReqDtls.getNetworkID());
					log.debug("OwnerId: " + nwkReqDtls.getNetworkID());
					sysAcc.setRecordStatus("A");
					log.debug("RecordStatus: A");
					sysAcc.setType("N");
					log.debug("Type: N");
					sysAccRepo.save(sysAcc);
					

					// Creating Notional System Accounts table
				/*	System_account notAcc = new System_account();
					long notaccNumber = esokoRepo.getSequenceNumber();
					notAcc.setAccName("N" + notaccNumber);
					log.debug("Account Name: " + "N" + notaccNumber);
					notAcc.setAccountNo("N" + notaccNumber);
					log.debug("Account Number: " + "N" + notaccNumber);
					notAcc.setAcCurrency("USD");
					log.debug("Currency: USD");
					notAcc.setBalance(0.00);
					log.debug("Balance: 0");
					notAcc.setCreatedBy(req.getHeader().getUserId());
					notAcc.setCreatedTs(new Date());
					notAcc.setOwnerId(nwkReqDtls.getNetworkID());
					log.debug("OwnerId: " + nwkReqDtls.getNetworkID());
					notAcc.setRecordStatus("A");
					log.debug("RecordStatus: A");
					notAcc.setType("G");
					log.debug("Type: G");
					sysAccRepo.save(notAcc); */
					
					// Creating permissions for Administrator in Network Permission
					Network_permission entity = new Network_permission();
					Network_permissionPK entityPK = new Network_permissionPK();
					entityPK.setGroupId("Administrator");
					entityPK.setNetworkId(nwkReqDtls.getNetworkID());
					entity.setId(entityPK);
					entity.setAgents("Y");
					entity.setAlerts("Y");
					entity.setApprove("Y");
					entity.setBilling("Y");
					entity.setConfigurations("Y");
					entity.setCreatedBy(req.getHeader().getUserId());
					entity.setCreatedTs(new Date());
					entity.setInbox("Y");
					entity.setMarketplace("Y");
					entity.setPeople("Y");
					entity.setPermissions("Y");
					entity.setPush("Y");
					entity.setReports("Y");
					entity.setSharing("Y");
					entity.setSmspolls("Y");
					entity.setUpload("Y");
					permissionRepo.save(entity);
					
					// Creating entry in GroupMaster table
					GroupMasterPK grpMstPk = new GroupMasterPK();
					grpMstPk.setGroupId("Administrator");
					grpMstPk.setNetworkId(nwkReqDtls.getNetworkID());
					boolean exists = grpMstRepo.exists(GroupMaster.class,
							grpMstPk);
					if (!exists) {
						GroupMaster grpMst = new GroupMaster();
						grpMst.setCreatedBy(req.getHeader().getUserId());
						grpMst.setCreatedTs(timeStamp);
						grpMst.setGROUP_name("Administrator");
						grpMst.setId(grpMstPk);
						grpMst.setType("N");
						grpMstRepo.save(grpMst);
						log.debug("GroupMaster: Row added for Administrator group");
					}

					GroupMasterPK grpMstPk2 = new GroupMasterPK();
					grpMstPk2.setGroupId("Recently added");
					grpMstPk2.setNetworkId(nwkReqDtls.getNetworkID());
					exists = grpMstRepo.exists(GroupMaster.class, grpMstPk2);
					if (!exists) {
						GroupMaster grpMst = new GroupMaster();
						grpMst.setCreatedBy(req.getHeader().getUserId());
						grpMst.setCreatedTs(timeStamp);
						grpMst.setGROUP_name("Recently added");
						grpMst.setId(grpMstPk2);
						grpMst.setType("S");
						grpMstRepo.save(grpMst);
						log.debug("GroupMaster: Row added for RecentlyAdded group");
					}

					List<SmartgroupRule> smrtGrpLst = smrtGrpRepo
							.queryAllRules("Recently added",
									nwkReqDtls.getNetworkID());
					if (smrtGrpLst.size() == 0) {
						SmartgroupRule rule = new SmartgroupRule();
						rule.setGroupId("Recently added");
						rule.setNetworkId(nwkReqDtls.getNetworkID());
						rule.setType("People");
						rule.setConditions("ADDED");
						rule.setValue("90");
						smrtGrpRepo.save(rule);
						log.debug("SmartGroupRule: Row added for RecentlyAdded group");
					}

					errorCode = ERROR_CODE.ES_SV_001;
					log.info("Record saved successfully");
				}
			} else {
				log.debug("Primary key is blank");
				errorCode = ERROR_CODE.ES_PK_001;
			}
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"NetworkService", "createNetwork", req.getHeader().getUserId(),
				errorCode);
		createNetworkRes.setHeader(header);
		return createNetworkRes;
	}

	@Transactional
	public DeleteNetworkRes deleteNetwork(DeleteNetworkReq req) {
		DeleteNetworkRes delNwkRes = new DeleteNetworkRes();
		DLNWKDETAILS delNwkDtls = new DLNWKDETAILS();
		delNwkDtls = (DLNWKDETAILS) req.getDLNWKDETAILS().get(0);
		log.info(" NetworkID :: " + delNwkDtls.getNetworkID());
		Network networkEntity = new Network();
		networkEntity = networkRepo.findOne(networkEntity,
				delNwkDtls.getNetworkID());
		networkEntity.setRecordStatus("D");
		try {
			if (networkEntity != null) {
				networkEntity.setModifiedBy(req.getHeader().getUserId());
				networkEntity.setModifiedTs(Utils.getCurrentDate());
				networkRepo.merge(networkEntity);
				ERROR = ERROR_CODE.ES_DL_001;
			} else {
				ERROR = ERROR_CODE.ES_SV_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Delete Network", "", ERROR);
		delNwkRes.setHeader(header);
		return delNwkRes;
	}

}
