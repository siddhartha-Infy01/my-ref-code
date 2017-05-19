package com.iexceed.esoko.ss.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.Network_adminRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao2.ResellerDetailsRepo;
import com.iexceed.esoko.domain.dao2.ResellerNetworkRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.Network_admin;
import com.iexceed.esoko.domain.entity.Network_adminPK;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserGroupPK;
import com.iexceed.esoko.domain.entity2.ResellerMaster;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.CRNWKDETAILS;
import com.iexceed.esoko.jaxb.se.CreateNetworkReq;
import com.iexceed.esoko.jaxb.ss.CRRSLRDTLS;
import com.iexceed.esoko.jaxb.ss.CreateResellerDtlsReq;
import com.iexceed.esoko.jaxb.ss.CreateResellerDtlsRes;
import com.iexceed.esoko.jaxb.ss.DLRSLRDTLS;
import com.iexceed.esoko.jaxb.ss.DeleteResellerDtlsReq;
import com.iexceed.esoko.jaxb.ss.DeleteResellerDtlsRes;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.QRALLRSLR;
import com.iexceed.esoko.jaxb.ss.QRRSLRDTLS;
import com.iexceed.esoko.jaxb.ss.QueryAllResellerRes;
import com.iexceed.esoko.jaxb.ss.QueryResellerDtlsRes;
import com.iexceed.esoko.se.service.NetworkService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class ResellerDetailsService {
	@Autowired
	ResellerDetailsRepo resellerRepo;
	@Autowired
	ResellerNetworkRepo resellerNwkRepo;
	@Autowired
	Network_adminRepo nwkAdminRepo;
	@Autowired
	NetworkRepo nwkRepo;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	NetworkService nwkService;
	@Autowired
	UserGroupRepo userRepo;
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;
	public static Logger log = LoggerUtils.getLogger();

	@Transactional
	public CreateResellerDtlsRes createResellerDtls(CreateResellerDtlsReq req) {
		CreateResellerDtlsRes res = new CreateResellerDtlsRes();
		CRRSLRDTLS resellerDtls = req.getCRRSLRDTLS();
		ResellerMaster entity = new ResellerMaster();
		String networkId = "NWK" + esokoRepo.getSequenceNumber();
		log.info("Inside ResellerDetailsService -> createResellerDtls");
		if (resellerDtls != null) {
			log.debug("ResellerId::" + resellerDtls.getResellerId());
			// if (!StringUtils.isNotEmpty(resellerDtls.getResellerId())) {
			// header = (Header) HeaderFactory.getHeader(HeaderType.SS,
			// "ResellerDetailsService", "Create Reseller Dtls Res",
			// "", ERROR_CODE.RSLR_EMPTY_ER);
			// res.setHeader(header);
			// return res;
			// }
			if (!StringUtils.isNotEmpty(resellerDtls.getNetworkId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"ResellerDetailsService", "Create Reseller Dtls Res",
						"", ERROR_CODE.ES_PK_002);
				res.setHeader(header);
				return res;
			}

			if (resellerRepo.getResellerDetailsByNetwork(resellerDtls
					.getNetworkId()) != null) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"ResellerDetailsService", "Create Reseller Dtls Res",
						"", ERROR_CODE.RSLR_EX_SC);
				res.setHeader(header);
				return res;

			}
			entity.setResellerId(generateReslllerId());
			log.debug("BankDtls::" + resellerDtls.getBankDtls());
			entity.setBankDetails(resellerDtls.getBankDtls());
			log.debug("Bit coin::" + resellerDtls.getBitcoin());
			entity.setBitcoin(resellerDtls.getBitcoin());
			log.debug("Company::" + resellerDtls.getCompany());
			entity.setCompany(resellerDtls.getCompany());
			entity.setCreatedBy(req.getHeader().getUserId());
			entity.setCreatedTs(Utils.getCurrentDate());
			log.debug("Fixed::" + resellerDtls.getFixed());
			entity.setFixed(resellerDtls.getFixed());
			log.debug("Mobile Money::" + resellerDtls.getMobileMoney());
			entity.setMobileMoney(resellerDtls.getMobileMoney());
			log.debug("Msisdn1::" + resellerDtls.getMsisdn1());
			entity.setMsisdn1(resellerDtls.getMsisdn1());
			log.debug("Msisdn2::" + resellerDtls.getMsisdn2());
			entity.setMsisdn2(resellerDtls.getMsisdn2());
			log.debug("Email::" + resellerDtls.getEmail());
			entity.setEmail(resellerDtls.getEmail());
			log.debug("Address::" + resellerDtls.getAddress());
			entity.setAddress(resellerDtls.getAddress());
			log.debug("Paypal::" + resellerDtls.getPaypal());
			entity.setPaypal(resellerDtls.getPaypal());
			log.debug("Toen::" + resellerDtls.getTown());
			entity.setTown(resellerDtls.getTown());
			log.debug("Type::" + resellerDtls.getType());
			entity.setType(resellerDtls.getType());
			log.debug("Website::" + resellerDtls.getWebsite());
			entity.setWebsite(resellerDtls.getWebsite());
			log.debug("BankDtls::" + resellerDtls.getBankDtls());
			entity.setRecordStatus("A");
			log.debug("NetworkId::" + resellerDtls.getNetworkId()
					+ resellerDtls);
			entity.setNetworkId(networkId);
			log.debug("Countries::" + resellerDtls.getCountries());
			entity.setCountries(resellerDtls.getCountries());
			entity.setCountry(resellerDtls.getCountry());
			entity.setOwnerName(Utils.ESOKO_NETWORK);
			
			Network nwkEntity = new Network();
			nwkEntity.setNetworkId(resellerDtls.getNetworkId());
			boolean nwkEntryExist = nwkRepo.exists(nwkEntity,
					resellerDtls.getNetworkId());

			if (!nwkEntryExist) {
				log.info("Inside calling network details save api"
						+ resellerDtls.toString());
				CreateNetworkReq nwkReq = new CreateNetworkReq();
				com.iexceed.esoko.jaxb.se.Header header1 = new com.iexceed.esoko.jaxb.se.Header();
				header1.setUserId(req.getHeader().getUserId());
				CRNWKDETAILS nwkDtls = new CRNWKDETAILS();
				nwkDtls.setNetworkID(networkId);
				nwkDtls.setType("G");
				nwkDtls.setNetworkOwner(resellerDtls.getResellerId());
				nwkDtls.setName(resellerDtls.getNetworkId());
				// nwkDtls.setParentid(subscribRegister.getLocation());
				log.info("Country" + resellerDtls.getCountry());
				nwkDtls.setCountry(resellerDtls.getCountry());
				nwkReq.setCRNWKDETAILS(nwkDtls);
				nwkReq.setHeader(header1);
				nwkService.createNetwork(nwkReq);
			} else {
				entity.setNetworkId(resellerDtls.getNetworkId());
			}

			// Method for saving network admin details
			this.saveNwkAdminsInfo(resellerDtls.getAdmins(), networkId, req
					.getHeader().getUserId());
			if (!resellerRepo.exists(entity, resellerDtls.getResellerId())) {
				resellerRepo.save(entity);
				ERROR = ERROR_CODE.RSLR_CR_SC;
			} else {
				ResellerMaster temp_entity = resellerRepo.findOne(entity,
						resellerDtls.getResellerId());
				entity.setModifiedBy(req.getHeader().getUserId());
				entity.setModifiedTs(Utils.getCurrentDate());
				entity.setCreatedBy(temp_entity.getCreatedBy());
				entity.setCreatedTs(temp_entity.getCreatedTs());
				resellerRepo.merge(entity);
				ERROR = ERROR_CODE.RSLR_UP_SC;
			}

		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerDetailsService", "Create Reseller Dtls", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeleteResellerDtlsRes deleteResellerDtls(DeleteResellerDtlsReq req) {
		log.info("DeleteResellerDtlsRes -> deleteResellerDtls");
		DeleteResellerDtlsRes res = new DeleteResellerDtlsRes();
		DLRSLRDTLS dlResellerDtls = req.getDLRSLRDTLS();
		ResellerMaster entity = new ResellerMaster();
		log.debug("ResellerId::" + dlResellerDtls.getResellerId());
		if (!StringUtils.isNotEmpty(dlResellerDtls.getResellerId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerDetailsService", "Delete Reseller Dtls Res", "",
					ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}
		entity.setResellerId(dlResellerDtls.getResellerId());
		// ResellerNetworkPK nwkPkEntity = new ResellerNetworkPK();
		// nwkPkEntity.setNetworkId(dlResellerDtls.getNetworkId());
		// nwkPkEntity.setResellerId(dlResellerDtls.getResellerId());
		log.debug("ResellerId::" + dlResellerDtls.getResellerId());
		entity = resellerRepo.findOne(entity, dlResellerDtls.getResellerId());
		// nwkEntity = resellerNwkRepo.findOne(nwkEntity, nwkPkEntity);
		if (entity != null) {
			entity.setRecordStatus("D");
			entity.setModifiedBy(req.getHeader().getUserId());
			entity.setModifiedTs(Utils.getCurrentDate());
			resellerRepo.merge(entity);
			ERROR = ERROR_CODE.RSLR_DL_SC;
		}
		// if (nwkEntity != null) {
		// nwkEntity.setRecordStatus("D");
		// nwkEntity.setModifiedBy(req.getHeader().getUserId());
		// nwkEntity.setModifiedTs(Utils.getCurrentDate());
		// resellerNwkRepo.merge(nwkEntity);
		// ERROR = ERROR_CODE.ES_UD_001;
		// }
		else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerDetailsService", "Delete Reseller Dtls", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryResellerDtlsRes queryResellerDtls(String resellerId,
			String networkId) {
		log.info("Inside QueryResellerDtlsRes -> queryResellerDtls");
		QueryResellerDtlsRes res = new QueryResellerDtlsRes();
		log.debug("resellerId::" + resellerId);
		log.debug("networkId::" + networkId);
		ResellerMaster entity = null;
		// if (!StringUtils.isNotEmpty(networkId)) {
		// header = (Header) HeaderFactory.getHeader(HeaderType.SS,
		// "ResellerDetailsService", "Query Reseller Dtls Res", "",
		// ERROR_CODE.ES_PK_002);
		// res.setHeader(header);
		// return res;
		// }
		if (resellerId != null) {
			entity = resellerRepo.getResellerDetails(resellerId);
		} else {
			entity = resellerRepo.getResellerDetailsByNetwork(networkId);
		}

		QRRSLRDTLS qrDtls = new QRRSLRDTLS();
		if (entity != null) {
			qrDtls.setBankDtls(entity.getBankDetails());
			qrDtls.setNetworkId(entity.getNetworkId());
			qrDtls.setBitcoin(entity.getBitcoin());
			qrDtls.setCompany(entity.getCompany());
			qrDtls.setCountries(entity.getCountries());
			qrDtls.setCountry(entity.getCountry());
			qrDtls.setFixed(entity.getFixed());
			qrDtls.setMobileMoney(entity.getMobileMoney());
			qrDtls.setMsisdn1(entity.getMsisdn1());
			qrDtls.setMsisdn2(entity.getMsisdn2());
			qrDtls.setPaypal(entity.getPaypal());
			qrDtls.setResellerId(entity.getResellerId());
			qrDtls.setTown(entity.getTown());
			qrDtls.setType(entity.getType());
			qrDtls.setWebsite(entity.getWebsite());
			qrDtls.setEmail(entity.getEmail());
			qrDtls.setAddress(entity.getAddress());
			String resellerAdmins = resellerRepo.getResellerAdmins(entity
					.getNetworkId());
			qrDtls.setAdmins(resellerAdmins);
			// if (resellerList.size() != 0) {
			// for (Network_admin reseller : resellerList) {
			// QRRSLRNWKADMINS qrNwkAdmins = new QRRSLRNWKADMINS();
			// qrNwkAdmins.setAdmin(reseller.getId().getUserId());
			// qrNwkRslrAdmins.add(qrNwkAdmins);
			// }
			// qrDtls.getQRRSLRNWKADMINS().addAll(qrNwkRslrAdmins);
			// }
			res.setQRRSLRDTLS(qrDtls);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerDetailsService", "Query Reseller Dtls", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryAllResellerRes queryAllReseller(String networkId) {
		QueryAllResellerRes resellerRes = new QueryAllResellerRes();
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerDetailsService", "Query All Reseller Dtls", "",
					ERROR_CODE.ES_PK_002);
			resellerRes.setHeader(header);
			return resellerRes;
		}
		List<ResellerMaster> resellerMasterlist = resellerRepo
				.getAllReseller(Utils.ESOKO_NETWORK);
		List<QRALLRSLR> qrAllRslrList = new ArrayList<QRALLRSLR>();
		if (resellerMasterlist.size() != 0) {
			for (ResellerMaster entity : resellerMasterlist) {
				QRALLRSLR qrAllRslr = new QRALLRSLR();
				qrAllRslr.setResellerId(entity.getResellerId());
				qrAllRslr.setTown(entity.getTown());
				qrAllRslr.setType(entity.getType());
				qrAllRslr.setCountry(entity.getCountry());
				qrAllRslr.setCompany(entity.getCompany());
				qrAllRslr.setNetworkId(entity.getNetworkId());
				qrAllRslrList.add(qrAllRslr);
			}
			resellerRes.getQRALLRSLR().addAll(qrAllRslrList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerDetailsService", "Query All Reseller Dtls", "", ERROR);
		resellerRes.setHeader(header);
		return resellerRes;
	}

	public void saveNwkAdminsInfo(String resellerAdmins, String networkId,
			String userId) {
		log.info("Inside Reseller Details Service -> saveNwkAdminsInfo");
		UserGroup userGroup = new UserGroup();
		UserGroupPK userGroupPK = new UserGroupPK();
		Network_admin nwkAdminEntity = new Network_admin();
		Network_adminPK nwkAdminPKEntity = new Network_adminPK();
		String[] resellerAdminsArr = resellerAdmins.split(",");
		for (int i = 0; i < resellerAdminsArr.length; i++) {
			log.debug("Admin::" + resellerAdminsArr[i]);
			userGroupPK.setGroupId("Administrator");
			userGroupPK.setNetworkId(networkId);
			userGroupPK.setUserId(resellerAdminsArr[i]);
			userGroup.setId(userGroupPK);
			userRepo.save(userGroup);
			if (!userRepo.exists(userGroup, userGroupPK)) {
				log.info("Inside save of network admins::");
				userGroup.setCreatedTs(Utils.getCurrentDate());
				userGroup.setCreatedBy(userId);
				userRepo.save(userGroup);
			} else {
				log.info("Inside merge of network admins::");
				UserGroup tempAdmin = userRepo.findOne(userGroup, userGroupPK);
				tempAdmin.setModifiedTs(Utils.getCurrentDate());
				tempAdmin.setModifiedBy(userId);
				userRepo.merge(tempAdmin);
			}
		}

	}

	private String generateReslllerId() {
		StringBuffer transaction = new StringBuffer();
		transaction.append("RSL" + Utils.getJulianDt()
				+ Math.round(Math.random() * 100000));
		return transaction.toString();

	}
}
