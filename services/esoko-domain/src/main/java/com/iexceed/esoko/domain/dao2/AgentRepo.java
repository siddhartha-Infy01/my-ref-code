package com.iexceed.esoko.domain.dao2;





import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.Agent;
import com.iexceed.esoko.domain.entity2.AgentPK;


import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AgentRepo extends
		AbstractRepoForEntity<Agent, AgentPK> {
	
	public static Logger log = LoggerUtils.getLogger();	
	
	public Agent save(Agent entity) {
		super.save(entity);
		return entity;
	}

	public Agent findOne(Agent entity,
			AgentPK entityPK) {
		log.info("Inside Agent -> findOne");
		return super.findOne(Agent.class, entityPK);
	}

	public boolean exists(Agent entity, AgentPK entityPK) {
		return super.exists(Agent.class, entityPK);

	}

	public Agent merge(Agent entity) {
		return super.merge(entity);
	}

}
