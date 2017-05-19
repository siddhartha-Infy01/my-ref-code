package com.iexceed.esoko.ms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.NetworkPictureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.NetworkUseridRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkPicture;
import com.iexceed.esoko.domain.entity.NetworkUserid;
import com.iexceed.esoko.domain.entity.NetworkUseridPK;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity.UserGroupPK;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ms.CRTDFTNWK;
import com.iexceed.esoko.jaxb.ms.CreateDefaultNetworkReq;
import com.iexceed.esoko.jaxb.ms.CreateDefaultNetworkRes;
import com.iexceed.esoko.jaxb.ms.DLTDFTNWK;
import com.iexceed.esoko.jaxb.ms.DeleteUserFromNetworkReq;
import com.iexceed.esoko.jaxb.ms.DeleteUserFromNetworkRes;
import com.iexceed.esoko.jaxb.ms.Header;
import com.iexceed.esoko.jaxb.ms.QRUSRNWKDTLS;
import com.iexceed.esoko.jaxb.ms.QueryUserNetworkDetailsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class UserNetworkDetailsService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	NetworkUseridRepo userRepo;
	@Autowired
	NetworkPictureRepo picRepo;
	@Autowired
	UserGroupRepo nwkAdminRepo;
	@Autowired
	SystemUserRepo systemUserrepo;
	@Autowired
	NetworkUseridRepo useridRepo;
	@Autowired
	PeopleRepo peopleRepo;
	@Autowired
	NetworkRepo nwkRepo;

	public QueryUserNetworkDetailsRes queryUserNetworkDetails(String userId) {
		QueryUserNetworkDetailsRes queryUserRes = new QueryUserNetworkDetailsRes();
		int count = 0;
		Header header = null;
		String networkId = "";
		Enum<ERROR_CODE> ERROR = null;
		log.info("Inside QueryUserNetworkDetailsRes -> queryUserNetworkDetails");
		log.debug("userId -> " + userId);
		try {
			List<Map<String, Object>> userMapList = userRepo
					.queryMynetworkcount(userId);
			NetworkPicture picEntity = new NetworkPicture();
			log.debug("User Dtls -> " + userMapList);
			if (userMapList.size() != 0) {
				List<QRUSRNWKDTLS> userList = new ArrayList<QRUSRNWKDTLS>();
				for (Map<String, Object> map : userMapList) {
					QRUSRNWKDTLS qrUsrDtls = new QRUSRNWKDTLS();
					networkId = map.get("networkId").toString();
					log.debug("NetworkId -> " + networkId);						
					qrUsrDtls.setNetworkId(networkId);
					Network network = nwkRepo.queryNetworkUserInfo(networkId);
					if(network != null){
						qrUsrDtls.setNetworkName(network.getName());
					}
					if (null != map.get("description")) {
						qrUsrDtls.setNetworkDesc(map.get("description")
								.toString());						
					}
					qrUsrDtls.setNoOfPeople(Integer.parseInt(map.get("count")
							.toString()));
					picEntity = picRepo.queryNwrPic(networkId);
					count = nwkAdminRepo.queryNwisManaged(networkId, userId);
					if (count == 0) {
						qrUsrDtls.setIsManaged("N");
					} else {
						qrUsrDtls.setIsManaged("Y");
					}
					if (picEntity != null) {
						qrUsrDtls.setProfilePic(picEntity.getContent());
					}
					userList.add(qrUsrDtls);
				}
				queryUserRes.getQRUSRNWKDTLS().addAll(userList);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.MS,
				"UserNetworkDetailsService", "QueryUserNetworkDetails", "",
				ERROR);
		queryUserRes.setHeader(header);
		return queryUserRes;

	}

	@Transactional
	public CreateDefaultNetworkRes createDefaultNetwork(
			CreateDefaultNetworkReq req) {
		CreateDefaultNetworkRes nwkRes = new CreateDefaultNetworkRes();
		Header header = null;
		log.info("Inside CreateDefaultNetworkRes -> createDefaultNetwork");
		CRTDFTNWK crtDftNwk = req.getCRTDFTNWK().get(0);
		log.debug("UserId _-> " + crtDftNwk.getUserId());
		try {
			SystemUser entity = new SystemUser();
			PeoplePK peoplePk = new PeoplePK();
			People people = new People();
			entity = systemUserrepo.findOne(entity, crtDftNwk.getUserId());
			if (entity != null) {
				log.debug("UserId -> " + crtDftNwk.getUserId());

				entity.setDefaultNetworkId(crtDftNwk.getNetworkId());
				entity.setModifiedBy(req.getHeader().getUserId());
				entity.setModifiedTs(Utils.getCurrentDate());
				systemUserrepo.merge(entity);
				People tempPeople = peopleRepo.queryMasterNetwork(crtDftNwk
						.getUserId());
				if (null != tempPeople) {
					tempPeople.setMasterFlag("N");
					peopleRepo.merge(tempPeople);
				}
				peoplePk.setDefaultNetworkId(crtDftNwk.getNetworkId());
				peoplePk.setPeopleId(crtDftNwk.getUserId());
				people.setId(peoplePk);
				people = peopleRepo.findOne(people, peoplePk);
				log.debug("FirstName::" + entity.getFirstName());
				log.debug("Email::" + entity.getEmail());
				if (null != people) {
					peopleRepo.delete(people);

				}
				if (entity != null) {
					log.debug("Country::" + entity.getCountry());
					people = new People();
					peoplePk = new PeoplePK();
					peoplePk.setDefaultNetworkId(crtDftNwk.getNetworkId());
					peoplePk.setPeopleId(crtDftNwk.getUserId());
					people.setId(peoplePk);
					people.setCountry(entity.getCountry());
					people.setCurrencyId(entity.getCurrencyId());
					people.setEmail(entity.getEmail());
					people.setFirstName(entity.getFirstName());
					people.setGender(entity.getGender());
					people.setIsVisible(entity.getIsVisible());
					people.setLanguageId(entity.getLanguageId());
					people.setLastName(entity.getLastName());
					people.setMasterFlag("Y");
					people.setMsisdn(entity.getMsisdn());
					people.setTown(entity.getTown());
					people.setCreatedTs(Timestamp.valueOf(Utils
							.getCurrentDate().toString()));
					people.setCreatedBy(req.getHeader().getUserId());
					people.setRecordStatus(entity.getRecordStatus());
					peopleRepo.save(people);
				}
				header = (Header) HeaderFactory.getHeader(HeaderType.MS,
						"UserNetworkDetailsService", "CreateDefaultNetwork",
						"", ERROR_CODE.USR_DFNW_001);
				Network entity1 = new Network();
				entity1 = nwkRepo.findOne(entity1,peoplePk.getDefaultNetworkId());
				HeaderFactory.setExtraParamMap("$1",
						entity1.getName());
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.MS,
						"UserNetworkDetailsService", "CreateDefaultNetwork",
						"", ERROR_CODE.DM_SV_001);
				
			}

		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"UserNetworkDetailsService", "CreateDefaultNetwork", "",
					ERROR_CODE.DM_SV_002);
		}
		nwkRes.setHeader(header);
		return nwkRes;
	}

	@Transactional
	public DeleteUserFromNetworkRes deleteUserFromNetwork(
			DeleteUserFromNetworkReq req) {
		DeleteUserFromNetworkRes nwkRes = new DeleteUserFromNetworkRes();
		Header header = null;
		log.info("Inside DeleteUserFromNetworkRes -> deleteUserFromNetwork");
		DLTDFTNWK crtDftNwk = req.getDLTDFTNWK().get(0);
		log.debug("UserId _-> " + crtDftNwk.getUserId());
		try {
			NetworkUserid entity = new NetworkUserid();
			NetworkUseridPK pkentity = new NetworkUseridPK();
			pkentity.setUserId(crtDftNwk.getUserId());
			pkentity.setNetworkId(crtDftNwk.getNetworkId());
			UserGroup userEntity = new UserGroup();
			UserGroupPK userPkEntity = new UserGroupPK();
			userPkEntity.setUserId(crtDftNwk.getUserId());
			userPkEntity.setNetworkId(crtDftNwk.getNetworkId());
			userPkEntity.setGroupId("Administrator");
			entity.setId(pkentity);
			entity.setNetworkName(pkentity.getNetworkId());
			userEntity.setId(userPkEntity);
			entity = useridRepo.findOne(entity, pkentity);
			userEntity = nwkAdminRepo.findOne(userEntity, userPkEntity);
			if (entity != null) {
				useridRepo.delete(entity);
			}
			if (userEntity != null) {
				nwkAdminRepo.delete(userEntity);
			}
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"UserNetworkDetailsService", "DeleteUserFromNetwork", "",
					ERROR_CODE.USR_DL_001);
			Network entity1 = new Network();
			entity1 = nwkRepo.findOne(entity1,userPkEntity.getNetworkId());
			HeaderFactory.setExtraParamMap("$1",
					entity1.getName());
			 
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.MS,
					"DeleteUserFromNetwork", "CreateDefaultNetwork", "",
					ERROR_CODE.DM_SV_001);
		}
		nwkRes.setHeader(header);
		return nwkRes;
	}
}
