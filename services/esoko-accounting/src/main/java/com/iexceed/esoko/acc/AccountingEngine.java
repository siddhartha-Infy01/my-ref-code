package com.iexceed.esoko.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

@Transactional
@Service
public class AccountingEngine {

	@Autowired
	FundTransferAccounting ftAccounting;
	@Autowired
	PushAccounting psuhAccounting;
	@Autowired
	AlertAccounting alertAccounting;
	@Autowired
	GeneralSMSAccounting genSmsAccounting;
	@Autowired
	InboxAccounting inboxAccounting;
	@Autowired
	InvoiceAccounting invoiceAccounting;

	public String doAccounting(Accounting accounting) {
		String trnrefNo = null;
		switch (TRANSACTION_CODE.valueOf(accounting.getTrnCode())) {
		case PUSH_ACC:
			trnrefNo = psuhAccounting.doAccounting(accounting);
			break;
		case ALERT_ACC:
			trnrefNo = alertAccounting.doAccounting(accounting);
			break;
		case GENSMS_ACC:
			trnrefNo = genSmsAccounting.doAccounting(accounting);
			break;
		case INVOICE_ACC:
			trnrefNo = invoiceAccounting.doAccounting(accounting);
			break;
		case INBOX_ACC:
			trnrefNo = inboxAccounting.doAccounting(accounting);
			break;
		case FT_ACC:
			trnrefNo = ftAccounting.doAccounting(accounting);
			break;
		default:
			break;

		}
		return trnrefNo;
	}

}
