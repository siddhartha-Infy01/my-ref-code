package com.iexceed.esoko.se.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CommodityAliaRepo;
import com.iexceed.esoko.domain.entity.CommodityAlia;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.AddAliasReq;
import com.iexceed.esoko.jaxb.se.AddAliasRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class AliasService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	CommodityAliaRepo aliasRepo;

	@Transactional
	public AddAliasRes createAlias(AddAliasReq req) {
		log.info("Inside AliasService-->createAlias");
		AddAliasRes res = new AddAliasRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (!(StringUtils.isEmpty(req.getADDALIAS().getAliasName()))) {
			CommodityAlia alias = new CommodityAlia();
			alias.setAliasId(req.getADDALIAS().getAliasName());
			alias.setName(req.getADDALIAS().getAliasName());
			alias.setCommodityId(req.getADDALIAS().getCommodityName());
			aliasRepo.save(alias);
			errorCode = ERROR_CODE.ES_SV_001;
		} else {
			errorCode = ERROR_CODE.SE_ALIAS_ER;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"AliasService", "createAlias", req.getHeader().getUserId(),
				errorCode);
		res.setHeader(header);
		return res;
	}
}
