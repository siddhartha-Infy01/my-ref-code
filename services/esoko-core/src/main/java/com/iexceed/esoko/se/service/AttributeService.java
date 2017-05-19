package com.iexceed.esoko.se.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.AttributesRepo;
import com.iexceed.esoko.domain.entity.Attribute;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.AddAttributeReq;
import com.iexceed.esoko.jaxb.se.AddAttributeRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class AttributeService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	AttributesRepo attributeRepo;

	@Transactional
	public AddAttributeRes createAttribute(AddAttributeReq req) {
		log.info("Inside AttributeService-->createAttribute");
		AddAttributeRes res = new AddAttributeRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (!(StringUtils.isEmpty(req.getADDATTRIBUTE().getAttributeName()))) {
			Attribute attribute = new Attribute();
			attribute.setAttributeId(req.getADDATTRIBUTE().getAttributeName());
			attribute.setAttDesc(req.getADDATTRIBUTE().getAttributeName());
			log.debug("Attribute name:"
					+ req.getADDATTRIBUTE().getAttributeName());
			attributeRepo.save(attribute);
			errorCode = ERROR_CODE.ES_SV_001;
		} else {
			errorCode = ERROR_CODE.SE_ATTR_ER;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"Attribute Service", "createAttribute", req.getHeader()
						.getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}
}
