package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Subscription;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class SubscriptionRepo extends AbstractRepoForEntity<Subscription, String> {
	public static Logger log = LoggerUtils.getLogger();
}
