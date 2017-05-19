package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SmsCodeAlia;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SmsCodeAliaRepo extends AbstractRepoForEntity<SmsCodeAlia, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<SmsCodeAlia> queryAllSMSAlias(String networkId) {
		log.info("Inside SmsCodeAliaRepo -> queryAllSMSCodes");
		log.debug("NetworkId: " + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllSMSAlias"), SmsCodeAlia.class);
		query.setParameter(1, networkId);
		List<SmsCodeAlia> aliasList = query.getResultList();
		entityManager.close();
		return aliasList;
	}

	public List<SmsCodeAlia> querySmscodeAlia(String smc_code, String networkId) {
		log.info("Entered query sms code alias");
		log.info("smc_code::" + smc_code);
		log.info("smc_code::" + networkId);
		Query query = entityManager.createNativeQuery(
				getSql("querySmscodeAlia"), SmsCodeAlia.class);
		query.setParameter(1, smc_code);
		query.setParameter(2, networkId);
		List<SmsCodeAlia> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public SmsCodeAlia save(SmsCodeAlia entity) {
		super.save(entity);
		return entity;
	}

	public SmsCodeAlia findOne(SmsCodeAlia entity, String aliasId) {
		return super.findOne(SmsCodeAlia.class, aliasId);
	}

	public boolean exists(SmsCodeAlia entity, String aliasId) {
		return super.exists(SmsCodeAlia.class, aliasId);

	}

	public SmsCodeAlia merge(SmsCodeAlia entity) {
		return super.merge(entity);
	}

}