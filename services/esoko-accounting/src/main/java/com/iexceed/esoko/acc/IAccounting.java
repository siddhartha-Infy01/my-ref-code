package com.iexceed.esoko.acc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.objects.Accounting;

public interface IAccounting {
	
	public static Logger log = LoggerUtils.getSchLogger();

	Double doBalanceCheck(System_account accountNo);

	boolean formAccountingLegs(Accounting accounting,
			List<System_transaction> transactions);

	String doAccounting(Accounting accounting);

}
