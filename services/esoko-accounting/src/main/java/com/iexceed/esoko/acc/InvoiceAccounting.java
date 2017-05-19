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
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.objects.ACCROLE;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.COSTROLE;
import com.iexceed.esoko.objects.ROLETOHEAD;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

@Component
public class InvoiceAccounting extends AbstractAccounting {

	@Override
	public String doAccounting(Accounting accounting) {
		log.info("Entered Fund Transfer Accounting");
		log.info("Transaction Code::" + accounting.getTrnCode());
		Map<String, List<Map<ACCROLE, COSTROLE>>> INVOICE = ROLETOHEAD.INVOICE;
		System_account sys_net_acc = sysAccRepo.getNetworkAccount(accounting
				.getNetworkId());
		System_account sys_esk_acc = sysAccRepo
				.getNetworkAccount(Utils.ESOKO_NETWORK);
		System_account sys_rsl_acc = sysAccRepo.getNetworkAccount(accounting
				.getResellerAcNo());
		Forex forex = null;
		List<System_transaction> transactionLegs = new ArrayList<System_transaction>();
		String trnRefNo = sysTrnRepo.generateTrnRefNo(TRANSACTION_CODE.valueOf(
				accounting.getTrnCode()).value());
		log.info("Transaction Ref No::" + trnRefNo);
		List<Map<ACCROLE, COSTROLE>> debits = INVOICE.get(ROLETOHEAD.INVDEBIT);
		int i = 0;
		for (Map<ACCROLE, COSTROLE> map : debits) {
			for (ACCROLE accrole : map.keySet()) {

				log.debug("Network Account::" + sys_net_acc.getAccountNo());
				if (accrole.getACCRole().equals(ACCROLE.D_NWK_ACC.getACCRole())) {
					System_transaction system_transaction = new System_transaction();
					System_transactionPK netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					system_transaction.setAccountNo(accounting.getDebitAcNo());
					if (map.get(accrole).getCostRole()
							.equals(COSTROLE.COST.getCostRole())) {
						Double trnAmt = accounting.getTrnAmt().getRetailPrice()
								+ accounting.getTrnAmt().getWholesalePrice();
						if (!accounting.getDebitCcy().equals(
								accounting.getDebitForeignCcy())) {
							forex = forexRepo.queryfxRatesbyCcy(
									accounting.getDebitForeignCcy(),
									accounting.getDebitCcy());
							system_transaction.setLcyAmount(trnAmt
									* forex.getRate().doubleValue());
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							Double balance = doBalanceCheck(sys_net_acc);
							log.info("Account Balance::" + balance);
							log.info("TRansaction Amount::"
									+ accounting.getTrnAmt().getRetailPrice());
							log.info("Updated balance::" + (balance - trnAmt));
							Double finalBal = balance - trnAmt;
							sys_net_acc.setBalance(finalBal);
							sys_net_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("N");
							system_transaction.setCreatedTs(Utils
									.getCurrentDate());
							system_transaction.setExRate(forex.getRate());
							system_transaction.setFcyAmount(trnAmt);
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						} else {
							system_transaction.setLcyAmount(trnAmt);
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							Double balance = doBalanceCheck(sys_net_acc);
							log.info("Account Balance::" + balance);
							log.info("TRansaction Amount::"
									+ accounting.getTrnAmt().getRetailPrice());
							log.info("Updated balance::"
									+ (balance - (accounting.getTrnAmt()
											.getRetailPrice())));
							Double finalBal = balance
									- (accounting.getTrnAmt().getRetailPrice());
							sys_net_acc.setBalance(finalBal);
							sys_net_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("N");
							system_transaction.setCreatedTs(Utils
									.getCurrentDate());
							system_transaction.setExRate(new BigDecimal(1.0));
							system_transaction.setFcyAmount(system_transaction
									.getLcyAmount());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						}

					}
					system_transaction.setCrDr(accrole.getCD());
					log.info("Transaction Leg" + i + "::" + system_transaction);
					transactionLegs.add(system_transaction);
				}

			}
		}

		List<Map<ACCROLE, COSTROLE>> credits = INVOICE
				.get(ROLETOHEAD.INVCREDIT);

		for (Map<ACCROLE, COSTROLE> map : credits) {
			for (ACCROLE accrole : map.keySet()) {
				log.debug("User Account::" + sys_esk_acc.getAccountNo());
				log.debug("Acc ROle::" + accrole.getACCRole());

				if (accrole.getACCRole().equals(ACCROLE.C_ESK_ACC.getACCRole())) {
					System_transaction system_transaction = new System_transaction();
					System_transactionPK netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					system_transaction.setAccountNo(sys_esk_acc.getAccountNo());
					log.info("Cost Role::" + map.get(accrole).getCostRole());
					log.info("Actual Cost ROle::" + COSTROLE.WHOLESALE);

					if (map.get(accrole).getCostRole()
							.equals(COSTROLE.WHOLESALE.getCostRole())) {
						Double trnAmt = accounting.getTrnAmt()
								.getWholesalePrice();
						if (!sys_esk_acc.getAcCurrency().equals(
								accounting.getDebitForeignCcy())) {
							forex = forexRepo.queryfxRatesbyCcy(
									accounting.getDebitForeignCcy(),
									sys_esk_acc.getAcCurrency());
							system_transaction.setLcyAmount(trnAmt
									* forex.getRate().doubleValue());
							Double balance = doBalanceCheck(sys_esk_acc);
							log.info("Account Balance::" + balance);
							log.info("Transaction Amount::"
									+ accounting.getTrnAmt()
											.getWholesalePrice());
							log.info("Updated balance::"
									+ (balance + trnAmt
											* forex.getRate().doubleValue()));
							Double finalBal = balance + trnAmt
									* forex.getRate().doubleValue();
							sys_esk_acc.setBalance(finalBal);
							sys_esk_acc.setMinBalance(finalBal);
							system_transaction.setCreatedTs(Utils
									.getCurrentDate());
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("U");
							system_transaction.setLocalCcy(sys_esk_acc
									.getAcCurrency());
							system_transaction.setExRate(forex.getRate());
							system_transaction.setFcyAmount(system_transaction
									.getLcyAmount());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						} else {

							system_transaction.setLcyAmount(trnAmt);
							Double balance = doBalanceCheck(sys_esk_acc);
							log.info("Account Balance::" + balance);
							log.info("TRansaction Amount::"
									+ accounting.getTrnAmt()
											.getWholesalePrice());
							log.info("Updated balance::"
									+ (balance + accounting.getTrnAmt()
											.getWholesalePrice()));
							Double finalBal = balance
									+ accounting.getTrnAmt()
											.getWholesalePrice();
							sys_esk_acc.setBalance(finalBal);
							sys_esk_acc.setMinBalance(finalBal);
							system_transaction.setCreatedTs(Utils
									.getCurrentDate());
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("U");
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							system_transaction.setExRate(new BigDecimal(1.0));
							system_transaction.setFcyAmount(system_transaction
									.getLcyAmount());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						}
					}
					system_transaction.setCrDr(accrole.getCD());
					log.info("Transaction Leg" + i + "::" + system_transaction);
					transactionLegs.add(system_transaction);
				} else if (accrole.getACCRole().equals(
						ACCROLE.C_RSL_ACC.getACCRole())) {
					System_transaction system_transaction = new System_transaction();
					System_transactionPK netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					system_transaction.setAccountNo(sys_rsl_acc.getAccountNo());
					log.info("Cost Role::" + map.get(accrole).getCostRole());
					log.info("Actual Cost ROle::" + COSTROLE.RETAIL);

					if (map.get(accrole).getCostRole()
							.equals(COSTROLE.RETAIL.getCostRole())) {

						if (!sys_rsl_acc.getAcCurrency().equals(
								accounting.getDebitForeignCcy())) {
							forex = forexRepo.queryfxRatesbyCcy(
									accounting.getDebitForeignCcy(),
									sys_rsl_acc.getAcCurrency());

							system_transaction.setLcyAmount(accounting
									.getTrnAmt().getRetailPrice()
									* forex.getRate().doubleValue());
							Double balance = doBalanceCheck(sys_rsl_acc);
							log.info("Account Balance::" + balance);
							log.info("TRansaction Amount::"
									+ accounting.getTrnAmt().getRetailPrice());
							log.info("Updated balance::"
									+ (balance + accounting.getTrnAmt()
											.getRetailPrice())
									* forex.getRate().doubleValue());
							Double finalBal = doBalanceCheck(sys_rsl_acc)
									+ accounting.getTrnAmt().getRetailPrice()
									* forex.getRate().doubleValue();
							sys_rsl_acc.setBalance(finalBal);
							sys_rsl_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("U");
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							system_transaction.setExRate(forex.getRate());
							system_transaction.setFcyAmount(accounting
									.getTrnAmt().getRetailPrice());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());

						} else {
							system_transaction.setLcyAmount(accounting
									.getTrnAmt().getRetailPrice());
							log.info("Account Balance::"
									+ doBalanceCheck(sys_rsl_acc));
							log.info("TRansaction Amount::"
									+ accounting.getTrnAmt().getRetailPrice());
							log.info("Updated balance::"
									+ (doBalanceCheck(sys_rsl_acc) + accounting
											.getTrnAmt().getRetailPrice()));
							double finalBal = doBalanceCheck(sys_rsl_acc)
									+ accounting.getTrnAmt().getRetailPrice();
							sys_rsl_acc.setBalance(finalBal);
							sys_rsl_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("U");
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							system_transaction.setExRate(new BigDecimal(1.0));
							system_transaction.setFcyAmount(system_transaction
									.getLcyAmount());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						}
					}

					system_transaction.setCrDr(accrole.getCD());
					log.info("Transaction Leg" + i + "::" + system_transaction);
					transactionLegs.add(system_transaction);
				}

			}
		}

		sysAccRepo.updateAccountData(sys_net_acc);
		sysAccRepo.updateAccountData(sys_esk_acc);
		sysAccRepo.updateAccountData(sys_rsl_acc);
		if (formAccountingLegs(accounting, transactionLegs)) {
			return trnRefNo;
		} else {
			return "";
		}
	}
}
