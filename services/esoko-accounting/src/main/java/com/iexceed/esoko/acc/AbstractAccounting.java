/**
 * 
 */
package com.iexceed.esoko.acc;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.System_transactionRepo;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

public abstract class AbstractAccounting implements IAccounting {

	@Autowired
	System_accountRepo sysAccRepo;

	@Autowired
	System_transactionRepo sysTrnRepo;

	@Autowired
	ForexRepo forexRepo;

	public Double doBalanceCheck(System_account accountNo) {
		return accountNo.getBalance();
	}

	public boolean formAccountingLegs(Accounting accounting,
			List<System_transaction> transactions) {
		log.info("Entered Forming Accounting Legs");
		for (System_transaction sys_acc_trn : transactions) {
			sys_acc_trn
					.setDescription(accounting.getDescription() == null ? TRANSACTION_CODE
							.valueOf(accounting.getTrnCode()).description()
							: accounting.getDescription());
			sys_acc_trn.setTransCode(accounting.getTrnCode());
			sys_acc_trn.setNetworkId(accounting.getNetworkId());
			sys_acc_trn.setTransactionDate(accounting.getTransactionDate());
			sys_acc_trn.setCreatedBy("SYSTEM");
			sys_acc_trn.setCreatedTs(new Date());
		}
		return sysTrnRepo.passAccountingLegs(transactions);
	}

	public abstract String doAccounting(Accounting accounting);
}
