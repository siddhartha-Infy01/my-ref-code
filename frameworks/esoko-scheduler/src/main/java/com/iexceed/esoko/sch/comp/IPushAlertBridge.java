package com.iexceed.esoko.sch.comp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iexceed.esoko.domain.entity3.AlertSourceNetwork;
import com.iexceed.esoko.engine.utils.LoggerUtils;

public interface IPushAlertBridge {
	public final static Logger log = LoggerUtils.getSchLogger();

	public List<MessageObjectBean> fetchAllScheduled(String alertId);

	public boolean isSurchargeable(
			MessageObjectBean bean);

	public List<AlertSourceNetwork> getContributingNetworks(
			String alertId);

}
