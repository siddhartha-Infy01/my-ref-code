package com.iexceed.esoko.ms.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ms.CRUSRCMDDTLS;
import com.iexceed.esoko.jaxb.ms.SaveUserCommodityReq;
import com.iexceed.esoko.jaxb.ms.SaveUserCommodityRes;

@Service
public class UserCommoditiesService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	UserCommodityRepo cmdRepo;

	@Transactional
	public SaveUserCommodityRes saveUserCommodity(SaveUserCommodityReq req) {
		SaveUserCommodityRes userCommodityRes = new SaveUserCommodityRes();
		log.info("Inside SaveUserCommodityRes -> saveUserCommodity");
		UserCommodity entity = new UserCommodity();
		UserCommodityPK commdityPkEntity = new UserCommodityPK();
		CRUSRCMDDTLS cmdDtls = (CRUSRCMDDTLS) req.getCRUSRCMDDTLS().get(0);
		commdityPkEntity.setCommodityId(cmdDtls.getCommodityId());
		commdityPkEntity.setUserId(req.getHeader().getUserId());
		entity.setId(commdityPkEntity);
		entity.setName(cmdDtls.getCommodityName());
		cmdRepo.save(entity);
		return userCommodityRes;
	}
}
