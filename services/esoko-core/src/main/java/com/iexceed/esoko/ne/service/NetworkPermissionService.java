package com.iexceed.esoko.ne.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.Network_permissionRepo;
import com.iexceed.esoko.domain.entity.Network_permission;
import com.iexceed.esoko.domain.entity.Network_permissionPK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.CRAPPS;
import com.iexceed.esoko.jaxb.ns.CRNETWORKS;
import com.iexceed.esoko.jaxb.ns.CRPRMSDTLS;
import com.iexceed.esoko.jaxb.ns.GRPDTLS;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.QRNETWORKS;
import com.iexceed.esoko.jaxb.ns.QRPRMSDTLS;
import com.iexceed.esoko.jaxb.ns.QueryAllNwkGroupsRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkPermissionsRes;
import com.iexceed.esoko.jaxb.ns.SaveNwkPermissionsReq;
import com.iexceed.esoko.jaxb.ns.SaveNwkPermissionsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class NetworkPermissionService {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkPermissionService() {

	}

	@Autowired
	GroupMasterRepo groupMaster;

	@Autowired
	Network_permissionRepo nwkPrmRepo;

	public QueryAllNwkGroupsRes queryAllNwkGroups(String networkId) {
		log.info("Inside QueryAllNwkGroupsRes -> queryAllNwkGroups");
		log.debug("NetworkId: " + networkId);
		QueryAllNwkGroupsRes qAllNwkGroup = new QueryAllNwkGroupsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		List<Map<String, Object>> nwkGroupLst = groupMaster
				.queryGroupByNetwks(networkId);
		if (nwkGroupLst.size() != 0) {
			log.info("Record found");
			List<GRPDTLS> grpDtlsLst = new ArrayList<GRPDTLS>();
			for (Map<String, Object> map : nwkGroupLst) {
				GRPDTLS grpDtls = new GRPDTLS();
				Object grpId = map.get("groupId");
				if (grpId != null) {
					grpDtls.setGroupId(grpId.toString());
				}
				Object objCnt = map.get("count");
				if(objCnt != null) {	
					grpDtls.setCount(objCnt.toString());
				}
				Object objName = map.get("groupName");
				if(objName != null){
					grpDtls.setGroupName(objName.toString());
				}
				grpDtlsLst.add(grpDtls);
			}
			errorCode = ERROR_CODE.ES_SC_001;
			qAllNwkGroup.getGRPDTLS().addAll(grpDtlsLst);
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPermissionService", "queryAllNwkGroups", "", errorCode);
		qAllNwkGroup.setHeader(header);
		return qAllNwkGroup;
	}

	public QueryNwkPermissionsRes queryNwkPermission(String groupId,
			String networkId) {
		log.info("Inside QueryNwkPermissionsRes-> queryNwkPermission");
		log.debug("GroupId: " + groupId);
		log.debug("NetworkId: " + networkId);
		QueryNwkPermissionsRes qNwkPermission = new QueryNwkPermissionsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		Network_permissionPK permPk = new Network_permissionPK();
		permPk.setGroupId(groupId);
		permPk.setNetworkId(networkId);

		boolean recExist = nwkPrmRepo.exists(Network_permission.class, permPk);

		if (recExist) {
			log.info("Record found");
			Network_permission nwkPerm = nwkPrmRepo.queryNwkPermissions(
					networkId, groupId);
			QRPRMSDTLS qrPrmDtls = new QRPRMSDTLS();
			QRNETWORKS qrNtwks = new QRNETWORKS();
			CRAPPS crApps = new CRAPPS();

			qrPrmDtls.setGroupId(groupId);
			qrPrmDtls.setNetworkId(networkId);
			qNwkPermission.setQRPRMSDTLS(qrPrmDtls);

			qrNtwks.setBilling(nwkPerm.getBilling());
			qrNtwks.setConfigurations(nwkPerm.getConfigurations());
			qrNtwks.setPermissions(nwkPerm.getPermissions());
			qrNtwks.setSharing(nwkPerm.getSharing());
			qNwkPermission.setQRNETWORKS(qrNtwks);

			crApps.setAgents(nwkPerm.getAgents());
			crApps.setAlerts(nwkPerm.getAlerts());
			crApps.setApprove(nwkPerm.getApprove());
			crApps.setInbox(nwkPerm.getInbox());
			crApps.setMarketplace(nwkPerm.getMarketplace());
			crApps.setPeople(nwkPerm.getPeople());
			crApps.setPush(nwkPerm.getPush());
			crApps.setSmspolls(nwkPerm.getSmspolls());
			crApps.setUpload(nwkPerm.getUpload());
			crApps.setReports(nwkPerm.getReports());
			qNwkPermission.setCRAPPS(crApps);

			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPermissionService", "queryNwkPermissions", "",
				errorCode);
		qNwkPermission.setHeader(header);
		return qNwkPermission;
	}

	@Transactional
	public SaveNwkPermissionsRes saveNwkPermissions(SaveNwkPermissionsReq req) {
		log.info("Inside SaveNwkPermissionsRes -> saveNwkPermissions");
		SaveNwkPermissionsRes svNwkPermission = new SaveNwkPermissionsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		Date timeStamp = new Date();

		try {
			CRPRMSDTLS prmDtls = req.getCRPRMSDTLS();
			if (StringUtils.isNotEmpty(prmDtls.getGroupId())
					&& StringUtils.isNotEmpty(prmDtls.getNetworkId())) {
				Network_permission nwkPerm = new Network_permission();
				CRNETWORKS nwks = req.getCRNETWORKS();
				CRAPPS apps = req.getCRAPPS();

				Network_permissionPK permPk = new Network_permissionPK();
				permPk.setGroupId(prmDtls.getGroupId());
				log.debug("GroupId: " + prmDtls.getGroupId());
				permPk.setNetworkId(prmDtls.getNetworkId());
				log.debug("NetworkId: " + prmDtls.getNetworkId());
				nwkPerm.setId(permPk);

				nwkPerm.setBilling(nwks.getBilling());
				log.debug("Billing: " + nwks.getBilling());
				nwkPerm.setConfigurations(nwks.getConfigurations());
				log.debug("Configurations: " + nwks.getConfigurations());
				nwkPerm.setPermissions(nwks.getPermissions());
				log.debug("Permissions: " + nwks.getPermissions());
				nwkPerm.setSharing(nwks.getSharing());
				log.debug("Sharing: " + nwks.getSharing());

				nwkPerm.setAgents(apps.getAgents());
				log.debug("Agents: " + apps.getAgents());
				nwkPerm.setAlerts(apps.getAlerts());
				log.debug("Alerts: " + apps.getAlerts());
				nwkPerm.setApprove(apps.getApprove());
				log.debug("Approve: " + apps.getApprove());
				nwkPerm.setInbox(apps.getInbox());
				log.debug("Inbox: " + apps.getInbox());
				nwkPerm.setMarketplace(apps.getMarketplace());
				log.debug("Marketplace: " + apps.getMarketplace());
				nwkPerm.setPeople(apps.getPeople());
				log.debug("People: " + apps.getPeople());
				nwkPerm.setPush(apps.getPush());
				log.debug("Push: " + apps.getPush());
				nwkPerm.setSmspolls(apps.getSmspolls());
				log.debug("Smspolls: " + apps.getSmspolls());
				nwkPerm.setUpload(apps.getUpload());
				log.debug("Upload: " + apps.getUpload());
				nwkPerm.setReports(apps.getReports());
				log.debug("Reports: " + apps.getReports());

				boolean recExist = nwkPrmRepo.exists(Network_permission.class,
						permPk);
				if (recExist) {
					log.info("Merging record");
					Network_permission prevRec = nwkPrmRepo
							.queryNwkPermissions(permPk.getNetworkId(),
									permPk.getGroupId());
					nwkPerm.setCreatedBy(prevRec.getCreatedBy());
					log.debug("CreatedBy: " + prevRec.getCreatedBy());
					nwkPerm.setCreatedTs(prevRec.getCreatedTs());
					log.debug("CreatedTs: " + prevRec.getCreatedTs());
					nwkPerm.setModifiedBy(req.getHeader().getUserId());
					log.debug("ModifiedBy: " + req.getHeader().getUserId());
					nwkPerm.setModifiedTs(timeStamp);
					log.debug("ModifiedTs: " + timeStamp);
					nwkPrmRepo.merge(nwkPerm);
					errorCode = ERROR_CODE.NW_PERMS_SV;
					HeaderFactory.setExtraParamMap("$1",
							prmDtls.getGroupId());
				} else {
					log.info("Saving record");
					nwkPerm.setCreatedBy(req.getHeader().getUserId());
					log.debug("CreatedBy: " + req.getHeader().getUserId());
					nwkPerm.setCreatedTs(timeStamp);
					log.debug("CreatedTs: " + timeStamp);
					nwkPrmRepo.save(nwkPerm);
					errorCode = ERROR_CODE.NW_PERMS_SV;
					HeaderFactory.setExtraParamMap("$1",
							prmDtls.getGroupId());
				}

			} else {
				log.debug("Primary key is blank");
				errorCode = ERROR_CODE.ES_PK_001;
			}
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory
				.getHeader(HeaderType.NS, "NetworkPermissionService",
						"saveNwkPermissions", "", errorCode);
		svNwkPermission.setHeader(header);
		return svNwkPermission;
	}
}
