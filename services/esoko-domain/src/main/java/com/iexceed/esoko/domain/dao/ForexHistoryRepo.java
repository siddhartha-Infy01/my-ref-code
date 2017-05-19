package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.ForexHistory;
import com.iexceed.esoko.domain.entity.ForexHistoryPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class ForexHistoryRepo extends AbstractRepoForEntity<ForexHistory, ForexHistoryPK> {
	public static Logger log = LoggerUtils.getLogger();
	
	
	public ForexHistory save(ForexHistory forexhist) {
		super.save(forexhist);
		return forexhist;

	}
}

