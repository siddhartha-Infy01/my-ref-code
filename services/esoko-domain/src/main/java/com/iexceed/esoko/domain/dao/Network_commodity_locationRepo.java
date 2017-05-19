package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_commodity_location;
import com.iexceed.esoko.domain.entity.Network_commodity_locationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_commodity_locationRepo extends AbstractRepoForEntity<Network_commodity_location, Network_commodity_locationPK> {
	public static Logger log = LoggerUtils.getLogger();
}

