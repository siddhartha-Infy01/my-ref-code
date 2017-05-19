package com.iexceed.esoko.sch.comp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.acc.AccountingEngine;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao2.ResellerDetailsRepo;
import com.iexceed.esoko.domain.dao2.ResellerNetworkRepo;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity2.ResellerMaster;
import com.iexceed.esoko.domain.entity2.ResellerNetwork;
import com.iexceed.esoko.engine.utils.SystemEnums;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.Cost;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

@Component
public class AccountingBridge implements IAccountingBridge {

	@Autowired
	AccountingEngine accountingEngine;
	@Autowired
	ResellerNetworkRepo resNetRepo;
	@Autowired
	ResellerDetailsRepo resellerDetRepo;
	@Autowired
	System_accountRepo sysAccRepo;
	
	public boolean doBalanceCheck(MessageObjectBean bean) {
		return true;
	}

	public MessageObjectBean doAccounting(MessageObjectBean bean) {
		Accounting accounting = new Accounting();
		accounting.setDebitAcNo(bean.getPayeeAcNo());
		accounting.setDebitCcy(bean.getLcy());
		accounting.setDebitForeignCcy(bean.getFcy());
		accounting.setDebitType(bean.getPayeeType());
		accounting.setDescription(bean.getDescription());
		accounting.setNetworkId(bean.getNetworkId());
		accounting.setTrnAmt(bean.getMsgCost());
		accounting.setTransactionDate(new Date());
		accounting.setResellerAcNo(deriveResellerAcc(bean.getNetworkId()));
		if (bean.getType().equals("P")) {
			accounting.setTrnCode(TRANSACTION_CODE.PUSH_ACC.name());
		} else {
			accounting.setTrnCode(TRANSACTION_CODE.ALERT_ACC.name());
		}
		accounting.setUserId(bean.getUserId());
		bean.setTrnRefNo(accountingEngine.doAccounting(accounting));
		return bean;
	}
	
	private String deriveResellerAcc(String networkId) {
		ResellerNetwork resNetwork = resNetRepo
				.getResellerForNetwork(networkId);
		ResellerMaster master = resellerDetRepo.findOne(new ResellerMaster(),
				resNetwork.getId().getResellerId());
		System_account sys_net_acc = sysAccRepo.getNetworkAccount(master
				.getNetworkId());
		return sys_net_acc.getAccountNo();
	}

}
