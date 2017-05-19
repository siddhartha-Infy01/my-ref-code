package com.iexceed.esoko.ne.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.Network_privacyRepo;
import com.iexceed.esoko.domain.entity.Network_privacy;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.CRNWKPRVDTLS;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.QRNWKPRVDTLS;
import com.iexceed.esoko.jaxb.ns.QueryNwkPrivacySettingsRes;
import com.iexceed.esoko.jaxb.ns.SaveNwkPrivacySettingsReq;
import com.iexceed.esoko.jaxb.ns.SaveNwkPrivacySettingsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Ankur
 */
@Service
public class NetworkPrivacyService {

	public static Logger log = LoggerUtils.getLogger();
	Enum<ERROR_CODE> errorCode = ERROR_CODE.DM_SV_002;

	public NetworkPrivacyService() {

	}

	@Autowired
	Network_privacyRepo nwPrivacyRepo;

	@Transactional
	public SaveNwkPrivacySettingsRes saveNwkPrivacySettings(
			SaveNwkPrivacySettingsReq req) {
		log.info("Inside SaveNwkPrivacySettingsRes -> saveNwkPrivacySettings");
		SaveNwkPrivacySettingsRes nwkPrvSettings = new SaveNwkPrivacySettingsRes();
		Date timeStamp = new Date();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		try {
			CRNWKPRVDTLS nwPrivacyDtl = req.getCRNWKPRVDTLS();
			if (StringUtils.isNotEmpty(nwPrivacyDtl.getNetworkId())) {
				Network_privacy nwkPrivacy = new Network_privacy();
				nwkPrivacy.setNetworkId(nwPrivacyDtl.getNetworkId());
				log.debug("NetworkId: " + nwPrivacyDtl.getNetworkId());
				nwkPrivacy.setContacts(nwPrivacyDtl.getContacts());
				log.debug("Contacts: " + nwPrivacyDtl.getContacts());
				nwkPrivacy.setOffers(nwPrivacyDtl.getOffers());
				log.debug("Offers: " + nwPrivacyDtl.getOffers());
				nwkPrivacy.setPrices(nwPrivacyDtl.getPrices());
				log.debug("Prices: " + nwPrivacyDtl.getPrices());
				nwkPrivacy.setNewsLib(nwPrivacyDtl.getNewsLibrary());
				log.debug("NewLibrary: " + nwPrivacyDtl.getNewsLibrary());

				boolean dataExist = nwPrivacyRepo.exists(Network_privacy.class,
						nwPrivacyDtl.getNetworkId());

				if (dataExist) {
					log.info("Merging record");
					Network_privacy prevRec = nwPrivacyRepo.findOne(
							Network_privacy.class, nwPrivacyDtl.getNetworkId());
					nwkPrivacy.setCreatedBy(prevRec.getCreatedBy());
					log.debug("Created by: " + prevRec.getCreatedBy());
					nwkPrivacy.setCreatedTs(prevRec.getCreatedTs());
					log.debug("Created Timestamp: " + prevRec.getCreatedTs());
					nwkPrivacy.setModifiedBy(req.getHeader().getUserId());
					log.debug("Modified by: " + req.getHeader().getUserId());
					nwkPrivacy.setModifiedTs(timeStamp);
					log.debug("Modified Timestamp: " + timeStamp);
					nwPrivacyRepo.merge(nwkPrivacy);
					errorCode = ERROR_CODE.NW_PRV_SV;
				} else {
					log.info("Saving record");
					nwkPrivacy.setCreatedBy(req.getHeader().getUserId());
					log.debug("Createdby: " + req.getHeader().getUserId());
					nwkPrivacy.setCreatedTs(timeStamp);
					log.debug("Created Timestamp: " + timeStamp);
					nwPrivacyRepo.save(nwkPrivacy);
					errorCode = ERROR_CODE.NW_PRV_SV;
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
				"NetworkPrivacyService", "saveNwkPrivacySettings", req
						.getHeader().getUserId(), errorCode);
		nwkPrvSettings.setHeader(header);
		return nwkPrvSettings;
	}

	public QueryNwkPrivacySettingsRes queryNwPrivacySettings(String networkId) {
		log.info("Inside QueryNwkPrivacySettingsRes -> queryNwPrivacySettings");
		log.debug("NetworkId: " + networkId);
		QueryNwkPrivacySettingsRes qNwkPrivacySettings = new QueryNwkPrivacySettingsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		boolean dataExist = nwPrivacyRepo.exists(Network_privacy.class,
				networkId);
		if (dataExist) {
			log.info("Record found");
			Network_privacy extngRec = nwPrivacyRepo.findOne(
					Network_privacy.class, networkId);
			QRNWKPRVDTLS qNwkPrvDtl = new QRNWKPRVDTLS();
			qNwkPrvDtl.setContacts(extngRec.getContacts());
			qNwkPrvDtl.setOffers(extngRec.getOffers());
			qNwkPrvDtl.setPrices(extngRec.getPrices());
			qNwkPrvDtl.setNewsLibrary(extngRec.getNewsLib());
			qNwkPrivacySettings.setQRNWKPRVDTLS(qNwkPrvDtl);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPrivacyService", "queryNwkPrivacySettings", "",
				errorCode);
		qNwkPrivacySettings.setHeader(header);
		return qNwkPrivacySettings;
	}
}
