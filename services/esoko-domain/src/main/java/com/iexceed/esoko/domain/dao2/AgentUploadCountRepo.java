package com.iexceed.esoko.domain.dao2;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.AgentUploadCount;
import com.iexceed.esoko.domain.entity2.AgentUploadCountPK;

@Component
@SuppressWarnings("unchecked")
public class AgentUploadCountRepo extends
		AbstractRepoForEntity<AgentUploadCount, AgentUploadCountPK> {

	public boolean exists(AgentUploadCount agentUpload,
			AgentUploadCountPK agentUploadPk) {
		log.info("Inside AgentUploadCountRepo -> exists");
		return super.exists(AgentUploadCount.class, agentUploadPk);
	}

	public AgentUploadCount merge(AgentUploadCount agentUpload) {
		log.info("Inside AgentUploadCountRepo -> merge");
		return super.merge(agentUpload);
	}

	public AgentUploadCount save(AgentUploadCount agentUpload) {
		log.info("Inside AgentUploadCountRepo -> save");
		return super.save(agentUpload);
	}

	public AgentUploadCount findOne(AgentUploadCount agentUpload,
			AgentUploadCountPK agentUploadPk) {
		log.info("Inside AgentUploadCountRepo -> findOne");
		return super.findOne(AgentUploadCount.class, agentUploadPk);
	}

	
	
}
