package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SmartgroupRule;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SmartGroupRuleRepo extends
		AbstractRepoForEntity<SmartgroupRule, Integer> {
	private static Logger log = LoggerUtils.getLogger();
	private static Logger smrtGrpLog = LoggerUtils.getSmartGroupLogger();

	public boolean exists(SmartgroupRule smartgroup, int ruleId) {
		log.info("Inside SmartGroupRuleRepo -> exists");
		return super.exists(SmartgroupRule.class, ruleId);
	}

	public SmartgroupRule merge(SmartgroupRule smartgroup) {
		log.info("Inside SmartGroupRuleRepo -> merge");
		SmartgroupRule entity = super.merge(smartgroup);
		entityManager.flush();
		return entity;
	}

	public SmartgroupRule save(SmartgroupRule smartgroup) {
		log.info("Inside SmartGroupRuleRepo -> save");
		SmartgroupRule entity = super.save(smartgroup);
		entityManager.flush();
		return entity;
	}

	public SmartgroupRule findOne(SmartgroupRule smartgroup, int ruleId) {
		log.info("Inside SmartGroupRuleRepo -> findOne");
		return super.findOne(SmartgroupRule.class, ruleId);
	}

	public void delete(SmartgroupRule smartgroup) {
		log.info("Inside SmartGroupRuleRepo -> delete");
		super.delete(smartgroup);
		entityManager.flush();
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable smartgroup) {
		log.info("Inside SmartGroupRuleRepo -> deleteAll");
		super.delete(smartgroup);
		entityManager.flush();
	}

	public List<SmartgroupRule> queryAllRules(String groupId, String networkId) {
		smrtGrpLog.info("Inside SmartGroupRuleRepo -> queryAllRules");
		Query query = entityManager.createNativeQuery(
				getSql("queryAllSmrtGrpRules"), SmartgroupRule.class);
		query.setParameter(1, groupId);
		query.setParameter(2, networkId);
		List<SmartgroupRule> rules = query.getResultList();
		entityManager.close();
		return rules;
	}
}
