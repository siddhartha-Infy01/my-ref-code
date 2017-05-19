package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SubscriptionAttribute;
import com.iexceed.esoko.domain.entity.SubscriptionAttributePK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class SubscriptionAttributeRepo extends AbstractRepoForEntity<SubscriptionAttribute, SubscriptionAttributePK> {
	public static Logger log = LoggerUtils.getLogger();
}

