package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Network_App;
import com.iexceed.esoko.domain.entity.Network_AppPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Network_AppRepo extends AbstractRepoForEntity<Network_App, Network_AppPK> {
	public static Logger log = LoggerUtils.getLogger();
}

