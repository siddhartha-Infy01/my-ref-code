package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_logo;
import com.iexceed.esoko.domain.entity.Network_logoPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_logoRepo extends AbstractRepoForEntity<Network_logo, Network_logoPK> {
	public static Logger log = LoggerUtils.getLogger();
}

