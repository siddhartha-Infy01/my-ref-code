package com.iexceed.esoko.sch.comp;

public interface IAccountingBridge {

	public boolean doBalanceCheck(MessageObjectBean bean);
	
	public MessageObjectBean doAccounting(MessageObjectBean bean);
}
