package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.LanguageRepo;
import com.iexceed.esoko.domain.entity.Language;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.ALLLANGUAGES;
import com.iexceed.esoko.jaxb.se.CRLANGDTLS;
import com.iexceed.esoko.jaxb.se.CreatelanguagesReq;
import com.iexceed.esoko.jaxb.se.CreatelanguagesRes;
import com.iexceed.esoko.jaxb.se.DLLANGDTLS;
import com.iexceed.esoko.jaxb.se.DeletelanguagesReq;
import com.iexceed.esoko.jaxb.se.DeletelanguagesRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRLANGDTLS;
import com.iexceed.esoko.jaxb.se.QueryAllLanguages;
import com.iexceed.esoko.jaxb.se.QuerylanguagesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class LaguagesService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	LanguageRepo langRepo;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.DM_SV_002;
	Header header = null;
	private final String serviceName = "LaguagesService";

	public QuerylanguagesRes queryLaguages(String languageCode,
			String networkId, String userId, String userData) {
		log.info("Inside Language -> queryLaguages ");
		QuerylanguagesRes qryLangRes = new QuerylanguagesRes();
		Language langEntity = langRepo.queryLaguages(languageCode);
		try {
			if (langEntity != null) {
				QRLANGDTLS qrLangDtls = new QRLANGDTLS();
				qrLangDtls.setLanguageCode(langEntity.getCode());
				qrLangDtls.setLanguageId(langEntity.getLanguageId());
				
				
				qrLangDtls.setRank(langEntity.getRank());
				
				qrLangDtls.setName(langEntity.getName());
				qryLangRes.getQRLANGDTLS().add(qrLangDtls);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query languages", "", ERROR);
		qryLangRes.setHeader(header);
		return qryLangRes;
	}

	@Transactional
	public CreatelanguagesRes createlanguages(CreatelanguagesReq req) {
		log.info("Inside Language -> createlanguages ");
		CreatelanguagesRes crtLangRes = new CreatelanguagesRes();
		CRLANGDTLS langDtls = req.getCRLANGDTLS().get(0);
		Language langEntity = new Language();
		log.info("Language Id::" + langDtls.getLanguageId());
		langEntity.setLanguageId(langDtls.getLanguageId());
		log.info("Language Code::" + langDtls.getLanguageCode());
		langEntity.setCode(langDtls.getLanguageCode());
		log.info("Language Rank::" + langDtls.getRank());
		langEntity.setRank(langDtls.getRank());
		log.info("Language Name::" + langDtls.getName());
		langEntity.setName(langDtls.getName());
		langEntity.setRecordStatus("A");
		try {
			if (!langRepo.exists(langEntity, langDtls.getLanguageId())) {
				langEntity.setCreatedBy(req.getHeader().getUserId());
				langEntity.setCreatedTs(Utils.getCurrentDate());
				langRepo.save(langEntity);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				Language tempEntity = langRepo.findOne(langEntity,
						langDtls.getLanguageId());
				langEntity.setCreatedBy(tempEntity.getCreatedBy());
				langEntity.setCreatedTs(tempEntity.getCreatedTs());
				langEntity.setModifiedBy(req.getHeader().getUserId());
				langEntity.setModifiedTs(Utils.getCurrentDate());
				langRepo.merge(langEntity);
				ERROR = ERROR_CODE.ES_UD_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create Languages", "", ERROR);
		crtLangRes.setHeader(header);
		return crtLangRes;
	}

	@Transactional
	public DeletelanguagesRes deletelanguages(DeletelanguagesReq req) {
		log.info("Inside Language -> deletelanguages ");
		DeletelanguagesRes delLangRes = new DeletelanguagesRes();
		Language entity = new Language();
		DLLANGDTLS langDtls = req.getDLLANGDTLS().get(0);
		langDtls = (DLLANGDTLS) req.getDLLANGDTLS().get(0);
		log.info("Language Code::" + langDtls.getLanguageCode());
		entity = langRepo.findOne(entity, langDtls.getLanguageCode());
		try {
			if (entity != null) {
				entity.setRecordStatus("D");
				entity.setModifiedBy(req.getHeader().getUserId());
				entity.setModifiedTs(Utils.getCurrentDate());
				langRepo.save(entity);
				ERROR = ERROR_CODE.ES_DL_001;
			} else {
				ERROR = ERROR_CODE.DM_SV_003;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create Languages", "", ERROR);
		delLangRes.setHeader(header);
		return delLangRes;
	}

	public QueryAllLanguages queryAllLanguages() {
		QueryAllLanguages langResp = new QueryAllLanguages();
		List<Language> language = (List<Language>) langRepo.queryALLLanguage();
		List langList = new ArrayList();
		for (Language langLoop : language) {
			ALLLANGUAGES langDtls = new ALLLANGUAGES();
			langDtls.setLanguageCode(langLoop.getLanguageId());
			langDtls.setLanguageId(langLoop.getName());
			langList.add(langDtls);
		}
		langResp.getALLLANGUAGES().addAll(langList);
		return langResp;
	}
}
