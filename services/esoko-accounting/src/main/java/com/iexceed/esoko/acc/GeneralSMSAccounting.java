package com.iexceed.esoko.acc;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.objects.Accounting;

@Component
public class GeneralSMSAccounting extends AbstractAccounting {

	@Override
	public String doAccounting(Accounting accounting) {
		return "";
	}

}
