package com.iexceed.esoko.sch.comp;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.objects.Cost;

public interface ICostBridge {
	public final static Logger log = LoggerUtils.getSchLogger();

	public MessageObjectBean deriveCostAndRoute(MessageObjectBean bean);

}
