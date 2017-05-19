package com.iexceed.esoko.acc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.domain.entity.System_transactionPK;
import com.iexceed.esoko.objects.ACCROLE;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.COSTROLE;
import com.iexceed.esoko.objects.ROLETOHEAD;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

@Component
public class FundTransferAccounting extends AbstractAccounting {

	@Override
	public String doAccounting(Accounting accounting) {
		log.info("Entered Fund Transfer Accounting");
		log.info("Transaction Code::" + accounting.getTrnCode());
		Map<String, Map<ACCROLE, COSTROLE>> FT = ROLETOHEAD.FT;
		System_account sys_net_acc = null;
		System_account sys_user_acc = null;
		Forex forex = null;
		if (accounting.getDebitAcNo().equals("")) {
			sys_net_acc = sysAccRepo.getNetworkAccount(accounting
					.getNetworkId());
		} else {
			sys_net_acc = sysAccRepo.getAccountDetails(accounting
					.getDebitAcNo());
		}
		if (accounting.getCreditAcNo().equals("")) {
			sys_user_acc = sysAccRepo.getUserAccount(accounting.getUserId());
		} else {
			sys_user_acc = sysAccRepo.getAccountDetails(accounting
					.getCreditAcNo());
		}
		List<System_transaction> transactionLegs = new ArrayList<System_transaction>();
		String trnRefNo = sysTrnRepo.generateTrnRefNo(TRANSACTION_CODE.valueOf(
				accounting.getTrnCode()).value());
		log.info("Transaction Ref No::" + trnRefNo);
		Map<ACCROLE, COSTROLE> debits = FT.get(ROLETOHEAD.FTDEBIT);
		int i = 0;
		for (ACCROLE accrole : debits.keySet()) {
			System_transaction system_transaction = new System_transaction();
			System_transactionPK netTransactionPK = new System_transactionPK();
			netTransactionPK.setTransactionId(++i);
			netTransactionPK.setTransactionRefNo(trnRefNo);
			system_transaction.setId(netTransactionPK);
			log.debug("Network Account::" + sys_net_acc.getAccountNo());
			if (accrole.getACCRole().equals(ACCROLE.D_NWK_ACC.getACCRole())) {
				system_transaction.setAccountNo(sys_net_acc.getAccountNo());
			}

			log.info("Cost Role::" + debits.get(accrole).getCostRole());
			log.info("Actual Cost ROle::" + COSTROLE.BASE_COST);

			if (debits.get(accrole).getCostRole()
					.equals(COSTROLE.BASE_COST.getCostRole())) {

				if (!accounting.getDebitCcy().equals(
						sys_net_acc.getAcCurrency())) {
					forex = forexRepo.queryfxRatesbyCcy(
							accounting.getDebitForeignCcy(),
							sys_net_acc.getAcCurrency());
					system_transaction.setLcyAmount(accounting.getTrnAmt()
							.getBaseCost() * forex.getRate().doubleValue());
					system_transaction.setLocalCcy(accounting.getDebitCcy());
					Double balance = doBalanceCheck(sys_net_acc);
					log.info("Account Balance::" + balance);
					log.info("TRansaction Amount::"
							+ accounting.getTrnAmt().getBaseCost());
					log.info("Updated balance::"
							+ (balance - accounting.getTrnAmt().getBaseCost())
							* forex.getRate().doubleValue());
					Double finalBal = doBalanceCheck(sys_net_acc)
							- accounting.getTrnAmt().getBaseCost()
							* forex.getRate().doubleValue();
					sys_net_acc.setBalance(finalBal);
					sys_net_acc.setMinBalance(finalBal);
					system_transaction.setBalance(finalBal);
					system_transaction.setBalance_Stat("U");
					system_transaction.setAccountType("N");
					system_transaction.setExRate(forex.getRate());
					system_transaction.setFcyAmount(system_transaction
							.getLcyAmount() * forex.getRate().doubleValue());
					system_transaction.setFcyCcy(sys_net_acc.getAcCurrency());

				} else {
					system_transaction.setLcyAmount(accounting.getTrnAmt()
							.getBaseCost());
					system_transaction.setLocalCcy(accounting.getDebitCcy());
					log.info("Account Balance::" + doBalanceCheck(sys_net_acc));
					log.info("TRansaction Amount::"
							+ accounting.getTrnAmt().getBaseCost());
					log.info("Updated balance::"
							+ (doBalanceCheck(sys_net_acc) - accounting
									.getTrnAmt().getBaseCost()));
					double finalBal = doBalanceCheck(sys_net_acc)
							- accounting.getTrnAmt().getBaseCost();
					sys_net_acc.setBalance(finalBal);
					sys_net_acc.setMinBalance(finalBal);
					system_transaction.setBalance(finalBal);
					system_transaction.setBalance_Stat("U");
					system_transaction.setAccountType("N");
					system_transaction.setExRate(new BigDecimal(1.0));
					system_transaction.setFcyAmount(system_transaction
							.getLcyAmount());
					system_transaction.setFcyCcy(sys_net_acc.getAcCurrency());
				}

			}
			system_transaction.setCrDr(accrole.getCD());
			log.info("Transaction Leg" + i + "::" + system_transaction);
			transactionLegs.add(system_transaction);
		}

		Map<ACCROLE, COSTROLE> credits = FT.get(ROLETOHEAD.FTCREDIT);

		for (ACCROLE accrole : credits.keySet()) {
			System_transaction system_transaction = new System_transaction();
			System_transactionPK netTransactionPK = new System_transactionPK();
			netTransactionPK.setTransactionId(++i);
			netTransactionPK.setTransactionRefNo(trnRefNo);
			system_transaction.setId(netTransactionPK);
			log.debug("User Account::" + sys_user_acc.getAccountNo());
			log.debug("Acc ROle::" + accrole.getACCRole());

			if (accrole.getACCRole().equals(ACCROLE.C_USR_ACC.getACCRole())) {
				system_transaction.setAccountNo(sys_user_acc.getAccountNo());
			}

			log.info("Cost Role::" + credits.get(accrole).getCostRole());
			log.info("Actual Cost ROle::" + COSTROLE.BASE_COST);

			if (credits.get(accrole).getCostRole()
					.equals(COSTROLE.BASE_COST.getCostRole())) {
				if (!accounting.getDebitCcy().equals(
						sys_user_acc.getAcCurrency())) {
					forex = forexRepo.queryfxRatesbyCcy(
							accounting.getDebitForeignCcy(),
							sys_net_acc.getAcCurrency());
					system_transaction.setLcyAmount(accounting.getTrnAmt()
							.getBaseCost() * forex.getRate().doubleValue());
					Double balance = doBalanceCheck(sys_user_acc);
					log.info("Account Balance::" + balance);
					log.info("Transaction Amount::"
							+ accounting.getTrnAmt().getBaseCost());
					log.info("Updated balance::"
							+ (balance + accounting.getTrnAmt().getBaseCost()
									* forex.getRate().doubleValue()));
					Double finalBal = balance
							+ accounting.getTrnAmt().getBaseCost()
							* forex.getRate().doubleValue();
					sys_user_acc.setBalance(finalBal);
					sys_user_acc.setMinBalance(finalBal);
					system_transaction.setBalance(finalBal);
					system_transaction.setBalance_Stat("U");
					system_transaction.setAccountType("U");
					system_transaction.setLocalCcy(accounting.getDebitCcy());
					system_transaction.setExRate(forex.getRate());
					system_transaction.setFcyAmount(system_transaction
							.getLcyAmount() * forex.getRate().doubleValue());
					system_transaction.setFcyCcy(sys_user_acc.getAcCurrency());
				} else {
					system_transaction.setLcyAmount(accounting.getTrnAmt()
							.getBaseCost());
					log.info("Account Balance::" + doBalanceCheck(sys_user_acc));
					log.info("TRansaction Amount::"
							+ accounting.getTrnAmt().getBaseCost());
					log.info("Updated balance::"
							+ (doBalanceCheck(sys_user_acc) + accounting
									.getTrnAmt().getBaseCost()));
					double finalBal = doBalanceCheck(sys_user_acc)
							+ accounting.getTrnAmt().getBaseCost();
					sys_user_acc.setBalance(finalBal);
					sys_user_acc.setMinBalance(finalBal);
					system_transaction.setBalance(finalBal);
					system_transaction.setBalance_Stat("U");
					system_transaction.setAccountType("U");
					system_transaction.setLocalCcy(accounting.getDebitCcy());
					system_transaction.setExRate(new BigDecimal(1.0));
					system_transaction.setFcyAmount(system_transaction
							.getLcyAmount());
					system_transaction.setFcyCcy(sys_user_acc.getAcCurrency());
				}

			}

			system_transaction.setCrDr(accrole.getCD());
			log.info("Transaction Leg" + i + "::" + system_transaction);
			transactionLegs.add(system_transaction);
		}
		sysAccRepo.updateAccountData(sys_net_acc);
		sysAccRepo.updateAccountData(sys_user_acc);
		if (formAccountingLegs(accounting, transactionLegs)) {
			return trnRefNo;
		} else {
			return "";
		}
	}
}
