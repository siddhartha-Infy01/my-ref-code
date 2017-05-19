package com.iexceed.esoko.domain.dao2;




import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;

import com.iexceed.esoko.domain.entity2.AgentDetailsHistory;
import com.iexceed.esoko.domain.entity2.AgentDetailsHistoryPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class AgentDetailsHistoryRepo extends
		AbstractRepoForEntity<AgentDetailsHistory, AgentDetailsHistoryPK> {
	public static Logger log = LoggerUtils.getLogger();
	
	public AgentDetailsHistory save(AgentDetailsHistory agentDetailHist) {
		log.info("Inside AgentDetailsHistoryRepo -> save");
		return super.save(agentDetailHist);
	}
	
}
