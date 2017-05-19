package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SubscriptionNetwork;
import com.iexceed.esoko.domain.entity.SubscriptionNetworkPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class SubscriptionNetworkRepo extends AbstractRepoForEntity<SubscriptionNetwork, SubscriptionNetworkPK> {
	public static Logger log = LoggerUtils.getLogger();
}

