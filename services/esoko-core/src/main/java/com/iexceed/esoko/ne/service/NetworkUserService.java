package com.iexceed.esoko.ne.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.NetworkCategoryRepo;
import com.iexceed.esoko.domain.dao.NetworkPictureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkCategory;
import com.iexceed.esoko.domain.entity.NetworkCategoryPK;
import com.iexceed.esoko.domain.entity.NetworkPicture;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.CRNWKUSERDTLS;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.QRNWKUSERDTLS;
import com.iexceed.esoko.jaxb.ns.QueryNetworkUserInfoRes;
import com.iexceed.esoko.jaxb.ns.SaveNetworkUserInfoReq;
import com.iexceed.esoko.jaxb.ns.SaveNetworkUserInfoRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class NetworkUserService {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkUserService() {

	}

	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private NetworkPictureRepo nwkPicRepo;
	@Autowired
	private NetworkCategoryRepo nwkCatRepo;
	@Autowired
	private SystemUserRepo sysUserRepo;

	public QueryNetworkUserInfoRes queryNwkUserInfo(String networkID) {
		log.info("Inside QueryNetworkUserInfoRes -> queryNwkUserInfo");
		log.info("NetworkId: " + networkID);
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		QueryNetworkUserInfoRes qNwkUserInfoRes = new QueryNetworkUserInfoRes();

		Network nwk = null;
		NetworkPicture pic = null;
		List<NetworkCategory> nwkCatLst = null;

		// Checking if record exist in Networks table
		boolean nwkEntryExist = nwkRepo.exists(Network.class, networkID);

		if (nwkEntryExist) {
			log.info("Record found");
			QRNWKUSERDTLS nwkUserDtls = new QRNWKUSERDTLS();
			// Querying NetworkRepo
			nwk = nwkRepo.queryNetworkUserInfo(networkID);
			nwkUserDtls.setDescription(nwk.getDescription());
			nwkUserDtls.setEmail(nwk.getEmail());
			nwkUserDtls.setName(nwk.getName());

			SystemUser sysUser = sysUserRepo.findOne(SystemUser.class,
					nwk.getOwnerUserId());
			if (sysUser != null) {
				StringBuilder name = new StringBuilder();
				if (StringUtils.isNotEmpty(sysUser.getFirstName())) {
					name.append(sysUser.getFirstName());
				}
				if (StringUtils.isNotEmpty(sysUser.getLastName())) {
					name.append(" " + sysUser.getLastName());
				}
				nwkUserDtls.setNetworkOwner(name.toString());
			}

			nwkUserDtls.setUrl(nwk.getWebsite());
			nwkUserDtls.setMainOffice(nwk.getPrimaryLocationId());
			nwkUserDtls.setNetworkId(nwk.getNetworkId());

			pic = nwkPicRepo.queryNwrPic(networkID);
			if (pic != null) {
				nwkUserDtls.setProfilePic(pic.getContent());
			} else {
				log.debug("No picture found");
			}
			StringBuilder catString = null;
			nwkCatLst = nwkCatRepo.queryCategoriesbyNwId(networkID);
			if (nwkCatLst.size() != 0) {
				// Converting Category list to a String and appending ,
				// after each
				// Category
				catString = new StringBuilder();
				for (int i = 0; i < nwkCatLst.size() - 1; i++) {
					NetworkCategoryPK nwCatPk = nwkCatLst.get(i).getId();
					catString.append(nwCatPk.getCategory() + ",");
				}
				NetworkCategoryPK nwCatPk = nwkCatLst.get(nwkCatLst.size() - 1)
						.getId();
				catString.append(nwCatPk.getCategory());
				nwkUserDtls.setCategories(catString.toString());
			} else {
				log.debug("No category found");
			}

			qNwkUserInfoRes.setQRNWKUSERDTLS(nwkUserDtls);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkUserService", "queryNetworkUserInfo", "", errorCode);
		qNwkUserInfoRes.setHeader(header);
		return qNwkUserInfoRes;
	}

	@Transactional
	public SaveNetworkUserInfoRes saveNwkUserInfo(SaveNetworkUserInfoReq req) {
		log.info("Inside SaveNetworkUserInfoRes -> saveNwkUserInfo");
		SaveNetworkUserInfoRes svNwkUserInfo = new SaveNetworkUserInfoRes();
		Date timeStamp = new Date();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		try {

			CRNWKUSERDTLS usrDtl = req.getCRNWKUSERDTLS();

			if (StringUtils.isNotEmpty(usrDtl.getNetworkId())) {
				Network nwk = new Network();
				nwk.setNetworkId(usrDtl.getNetworkId());
				log.debug("NetworkId: " + usrDtl.getNetworkId());
				nwk.setName(usrDtl.getName());
				log.debug("Name: " + usrDtl.getName());
				nwk.setWebsite(usrDtl.getUrl());
				log.debug("URL: " + usrDtl.getUrl());
				//nwk.setOwnerUserId(usrDtl.getNetworkOwner());
				//log.debug("NetworkOwner: " + usrDtl.getNetworkOwner());
				nwk.setEmail(usrDtl.getEmail());
				log.debug("Email: " + usrDtl.getEmail());
				nwk.setDescription(usrDtl.getDescription());
				log.debug("Description: " + usrDtl.getDescription());
				nwk.setPrimaryLocationId(usrDtl.getMainOffice());
				log.debug("MainOffice: " + usrDtl.getMainOffice());
				log.debug("nwk.getNetworkId::" + nwk.getNetworkId());
				// Checking if record exist in Networks table
				boolean nwkEntryExist = nwkRepo.exists(Network.class,
						usrDtl.getNetworkId());
				log.debug("NetworkId: " + usrDtl.getNetworkId());
				if (nwkEntryExist) {
					log.info("Merging record");
					Network preRec = nwkRepo.findOne(Network.class,
							usrDtl.getNetworkId());
					nwk.setCreatedBy(preRec.getCreatedBy());
					log.debug("CreatedBy: " + preRec.getCreatedBy());
					nwk.setCreatedTs(preRec.getCreatedTs());
					log.debug("CreatedBy: " + preRec.getCreatedTs());
					nwk.setModifiedBy(req.getHeader().getUserId());
					log.debug("ModifiedBy: " + req.getHeader().getUserId());
					nwk.setModifiedTs(timeStamp);
					log.debug("ModifiedTS: " + timeStamp);
					nwk.setOwnerUserId(preRec.getOwnerUserId());
					nwk.setType(preRec.getType());
					nwk.setParentId(preRec.getParentId());
					nwk.setLogoId(preRec.getLogoId());
					nwk.setIsPrivate(preRec.getIsPrivate());
					nwk.setIsSmsWhitelisted(preRec.getIsSmsWhitelisted());
					nwk.setRecordStatus(preRec.getRecordStatus());
					nwk.setAuthBy(preRec.getAuthBy());
					nwk.setAuthTs(preRec.getAuthTs());
					nwk.setIsVisible(preRec.getIsVisible());
					nwk.setShortName(preRec.getShortName());
					nwk.setNwGis(preRec.getNwGis());
					nwkRepo.merge(nwk);

					NetworkPicture pic = new NetworkPicture();
					pic.setNetworkId(usrDtl.getNetworkId());
					pic.setContent(usrDtl.getProfilePic());

					NetworkPicture picture = nwkPicRepo.queryNwrPic(usrDtl
							.getNetworkId());
					if (picture == null) {
						log.info("Saving Network picture");
						pic.setCreatedTs(timeStamp);
						log.debug("CreatedTS: " + timeStamp);
						pic.setCreatedBy(req.getHeader().getUserId());
						nwkPicRepo.save(pic);
					} else {
						log.info("Updating Network picture");
						pic.setCreatedTs(picture.getCreatedTs());
						log.debug("CreatedTS: " + picture.getCreatedTs());
						pic.setCreatedBy(picture.getCreatedBy());
						log.debug("CreatedBy: " + picture.getCreatedBy());
						pic.setPicId(picture.getPicId());
						nwkPicRepo.merge(pic);
					}

					String categories = usrDtl.getCategories();
					String[] catArr = categories.split(",");

					List<NetworkCategory> nwkCatList = nwkCatRepo
							.queryCategoriesbyNwId(usrDtl.getNetworkId());
					if (nwkCatList.size() != 0) {
						nwkCatRepo.delete(nwkCatList);
					}

					for (String str : catArr) {
						NetworkCategoryPK nwCatgPk = new NetworkCategoryPK();
						nwCatgPk.setNetworkId(usrDtl.getNetworkId());
						nwCatgPk.setCategory(str);
						NetworkCategory nwkCat = new NetworkCategory();
						nwkCat.setId(nwCatgPk);
						nwkCat.setModifiedBy(req.getHeader().getUserId());
						nwkCat.setModifiedTs(timeStamp);

						log.debug("Category: " + str);
						nwkCatRepo.save(nwkCat);
					}

					errorCode = ERROR_CODE.NW_DT_SV;

				} else {
					log.info("No record found");
					errorCode = ERROR_CODE.ES_NR_001;
				}

			} else {
				log.debug("Primary key is blank");
				errorCode = ERROR_CODE.ES_PK_001;
			}
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkUserService", "saveNetworkUserInfo", req.getHeader()
						.getUserId(), errorCode);
		svNwkUserInfo.setHeader(header);
		return svNwkUserInfo;
	}
}
