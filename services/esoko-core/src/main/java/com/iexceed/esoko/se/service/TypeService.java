package com.iexceed.esoko.se.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.TypesRepo;
import com.iexceed.esoko.domain.entity.Type;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.AddTypeReq;
import com.iexceed.esoko.jaxb.se.AddTypeRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class TypeService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	TypesRepo typeRepo;

	@Transactional
	public AddTypeRes createTypes(AddTypeReq req) {
		log.info("Inside TypeService-->createTypes");
		AddTypeRes res = new AddTypeRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (!(StringUtils.isEmpty(req.getADDTYPE().getTypeName()))) {
			Type type = new Type();
			type.setTypeId(req.getADDTYPE().getTypeName());
			type.setDescription(req.getADDTYPE().getTypeName());
			log.debug("Type name:" + req.getADDTYPE().getTypeName());
			typeRepo.save(type);
			errorCode = ERROR_CODE.ES_SV_001;
		} else {
			errorCode = ERROR_CODE.SE_TYPE_ER;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"Type Service", "Create Type", req.getHeader().getUserId(),
				errorCode);
		res.setHeader(header);
		return res;

	}
}
