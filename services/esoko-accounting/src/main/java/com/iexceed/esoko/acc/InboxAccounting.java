package com.iexceed.esoko.acc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.dao2.ResellerDetailsRepo;
import com.iexceed.esoko.domain.dao2.ResellerNetworkRepo;
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
public class InboxAccounting extends AbstractAccounting {
	@Autowired
	ResellerNetworkRepo resNetRepo;
	@Autowired
	ResellerDetailsRepo resellerDetRepo;

	@Override
	public String doAccounting(Accounting accounting) {
		log.info("Entered Inbox Accounting");
		log.info("Transaction Code::" + accounting.getTrnCode());
		System_transaction system_transaction = null;
		System_transactionPK netTransactionPK = null;
		Forex forex = null;
		Map<String, List<Map<ACCROLE, COSTROLE>>> ALERT = ROLETOHEAD.INBOX;

		System_account sys_net_acc = sysAccRepo.getNetworkAccount(accounting
				.getNetworkId());

		log.info("Network Account::" + sys_net_acc.getAccountNo());

		System_account sys_user_acc = sysAccRepo.getUserAccount(accounting
				.getUserId());

		log.info("User Account::" + sys_user_acc.getAccountNo());

		System_account sys_resl_acc = sysAccRepo.getAccountDetails(accounting
				.getResellerAcNo());

		log.info("Reseller Account" + sys_resl_acc.getAccountNo());

		System_account esokoAccount = deriveEsokoAccount();

		log.info("Esoko Account" + esokoAccount.getAccountNo());

		List<System_transaction> transactionLegs = new ArrayList<System_transaction>();

		String trnRefNo = sysTrnRepo.generateTrnRefNo(TRANSACTION_CODE.valueOf(
				accounting.getTrnCode()).value());

		log.info("Transaction Ref No::" + trnRefNo);

		List<Map<ACCROLE, COSTROLE>> debits = ALERT.get(ROLETOHEAD.INBDEBIT);
		log.debug("All Debit Accroles" + debits);
		int i = 0;
		for (Map<ACCROLE, COSTROLE> map : debits) {
			for (ACCROLE accrole : map.keySet()) {
				log.debug("Looping through debit accroles");
				if (accrole.getACCRole().equals(ACCROLE.D_NWK_ACC.getACCRole())
						&& accounting.getDebitType().equals("N")) {
					log.debug("Will debit Network Account");
					system_transaction = new System_transaction();
					netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					system_transaction.setAccountNo(accounting.getDebitAcNo());
					log.info("Cost Role::" + map.get(accrole).getCostRole());
					log.info("Actual Cost ROle::" + COSTROLE.COST);

					if (map.get(accrole).getCostRole()
							.equals(COSTROLE.COST.getCostRole())) {
						Double trnAmt = accounting.getTrnAmt().getBaseCost()
								+ accounting.getTrnAmt().getRetailPrice()
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
							log.info("Transaction Amount::" + trnAmt
									* forex.getRate().doubleValue());
							log.info("Updated balance::"
									+ (balance - trnAmt
											* forex.getRate().doubleValue()));
							Double finalBal = balance - trnAmt
									* forex.getRate().doubleValue();
							if (finalBal < 0)
								return "";
							sys_net_acc.setBalance(finalBal);
							sys_net_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("N");
							system_transaction.setExRate(forex.getRate());
							system_transaction.setFcyAmount(system_transaction
									.getLcyAmount()
									* forex.getRate().doubleValue());
							system_transaction.setFcyCcy(accounting
									.getDebitForeignCcy());
						} else {
							system_transaction.setLcyAmount(trnAmt);
							system_transaction.setLocalCcy(accounting
									.getDebitCcy());
							Double balance = doBalanceCheck(sys_net_acc);
							log.info("Account Balance::" + balance);
							log.info("Transaction Amount::" + trnAmt
									* forex.getRate().doubleValue());
							log.info("Updated balance::" + (balance - trnAmt));
							Double finalBal = balance - trnAmt;
							if (finalBal < 0)
								return "";
							sys_net_acc.setBalance(finalBal);
							sys_net_acc.setMinBalance(finalBal);
							system_transaction.setBalance(finalBal);
							system_transaction.setBalance_Stat("U");
							system_transaction.setAccountType("N");
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

		List<Map<ACCROLE, COSTROLE>> credits = ALERT.get(ROLETOHEAD.INBCREDIT);

		for (Map<ACCROLE, COSTROLE> map : credits) {
			log.debug("All Credit Accroles" + credits);
			for (ACCROLE accrole : map.keySet()) {
				log.debug("Looping through credit accroles");
				log.debug("Acc ROle::" + accrole.getACCRole());
				log.info("Cost Role::" + map.get(accrole).getCostRole());
				log.info("Actual Cost ROle::" + COSTROLE.BASE_COST);
				if (map.get(accrole).getCostRole()
						.equals(COSTROLE.BASE_COST.getCostRole())) {

					system_transaction = new System_transaction();
					netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					if (!accounting.getDebitCcy().equals(
							esokoAccount.getAcCurrency())) {
						forex = forexRepo.queryfxRatesbyCcy(
								accounting.getDebitForeignCcy(),
								esokoAccount.getAcCurrency());
						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getBaseCost() * forex.getRate().doubleValue());
						system_transaction.setAccountNo(esokoAccount
								.getAccountNo());
						Double balance = doBalanceCheck(esokoAccount);
						log.info("Account Balance::" + balance);
						log.info("Transaction Amount::"
								+ accounting.getTrnAmt().getBaseCost()
								* forex.getRate().doubleValue());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getBaseCost()
										* forex.getRate().doubleValue()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getBaseCost()
								* forex.getRate().doubleValue();
						esokoAccount.setBalance(finalBal);
						esokoAccount.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
						system_transaction.setExRate(forex.getRate());
						system_transaction
								.setFcyAmount(system_transaction.getLcyAmount()
										* forex.getRate().doubleValue());
						system_transaction.setFcyCcy(accounting
								.getDebitForeignCcy());
					} else {

						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getBaseCost());
						system_transaction.setAccountNo(esokoAccount
								.getAccountNo());
						Double balance = doBalanceCheck(esokoAccount);
						log.info("Account Balance::" + balance);
						log.info("TRansaction Amount::"
								+ accounting.getTrnAmt().getBaseCost());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getBaseCost()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getBaseCost();
						esokoAccount.setBalance(finalBal);
						esokoAccount.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
						system_transaction.setExRate(new BigDecimal(1.0));
						system_transaction.setFcyAmount(system_transaction
								.getLcyAmount());
						system_transaction.setFcyCcy(accounting
								.getDebitForeignCcy());
					}
				} else if (map.get(accrole).getCostRole()
						.equals(COSTROLE.RETAIL.getCostRole())) {
					system_transaction = new System_transaction();
					netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					if (!accounting.getDebitCcy().equals(
							sys_resl_acc.getAcCurrency())) {

						forex = forexRepo.queryfxRatesbyCcy(
								accounting.getDebitForeignCcy(),
								sys_resl_acc.getAcCurrency());

						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getRetailPrice()
								* forex.getRate().doubleValue());
						system_transaction.setAccountNo(accounting
								.getResellerAcNo());
						Double balance = doBalanceCheck(sys_resl_acc);
						log.info("Account Balance::" + balance);
						log.info("Transaction Amount::"
								+ accounting.getTrnAmt().getRetailPrice());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getRetailPrice()
										* forex.getRate().doubleValue()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getRetailPrice()
								* forex.getRate().doubleValue();
						sys_resl_acc.setBalance(finalBal);
						sys_resl_acc.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
						system_transaction.setExRate(forex.getRate());
						system_transaction
								.setFcyAmount(system_transaction.getLcyAmount()
										* forex.getRate().doubleValue());
						system_transaction.setFcyCcy(accounting
								.getDebitForeignCcy());
					} else {
						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getRetailPrice());
						system_transaction.setAccountNo(accounting
								.getResellerAcNo());
						Double balance = doBalanceCheck(sys_resl_acc);
						log.info("Account Balance::" + balance);
						log.info("TRansaction Amount::"
								+ accounting.getTrnAmt().getRetailPrice());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getRetailPrice()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getRetailPrice();
						sys_resl_acc.setBalance(finalBal);
						sys_resl_acc.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
						system_transaction.setExRate(new BigDecimal(1.0));
						system_transaction.setFcyAmount(system_transaction
								.getLcyAmount());
						system_transaction.setFcyCcy(accounting
								.getDebitForeignCcy());
					}
				} else if (map.get(accrole).getCostRole()
						.equals(COSTROLE.WHOLESALE.getCostRole())) {
					system_transaction = new System_transaction();
					netTransactionPK = new System_transactionPK();
					netTransactionPK.setTransactionId(++i);
					netTransactionPK.setTransactionRefNo(trnRefNo);
					system_transaction.setId(netTransactionPK);
					system_transaction
							.setAccountNo(esokoAccount.getAccountNo());
					if (!accounting.getDebitCcy().equals(
							esokoAccount.getAcCurrency())) {
						forex = forexRepo.queryfxRatesbyCcy(
								accounting.getDebitForeignCcy(),
								esokoAccount.getAcCurrency());
						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getWholesalePrice());
						Double balance = doBalanceCheck(esokoAccount);
						log.info("Account Balance::" + balance);
						log.info("Transaction Amount::"
								+ accounting.getTrnAmt().getWholesalePrice()
								* forex.getRate().doubleValue());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getWholesalePrice()
										* forex.getRate().doubleValue()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getWholesalePrice()
								* forex.getRate().doubleValue();
						esokoAccount.setBalance(finalBal);
						esokoAccount.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
						system_transaction.setExRate(forex.getRate());
						system_transaction
								.setFcyAmount(system_transaction.getLcyAmount()
										* forex.getRate().doubleValue());
						system_transaction.setFcyCcy(accounting
								.getDebitForeignCcy());
					} else {
						system_transaction.setLcyAmount(accounting.getTrnAmt()
								.getWholesalePrice());
						Double balance = doBalanceCheck(esokoAccount);
						log.info("Account Balance::" + balance);
						log.info("TRansaction Amount::"
								+ accounting.getTrnAmt().getWholesalePrice());
						log.info("Updated balance::"
								+ (balance + accounting.getTrnAmt()
										.getWholesalePrice()));
						Double finalBal = balance
								+ accounting.getTrnAmt().getWholesalePrice();
						sys_user_acc.setBalance(finalBal);
						sys_user_acc.setMinBalance(finalBal);
						system_transaction.setBalance(finalBal);
						system_transaction.setBalance_Stat("U");
						system_transaction.setAccountType("U");
						system_transaction
								.setLocalCcy(accounting.getDebitCcy());
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

		sysAccRepo.updateAccountData(sys_net_acc);
		sysAccRepo.updateAccountData(sys_user_acc);
		sysAccRepo.updateAccountData(esokoAccount);
		sysAccRepo.updateAccountData(sys_resl_acc);

		if (formAccountingLegs(accounting, transactionLegs)) {
			return trnRefNo;
		} else {
			return "";
		}
	}

	private System_account deriveEsokoAccount() {
		System_account sys_net_acc = sysAccRepo
				.getNetworkAccount(Utils.ESOKO_NETWORK);
		return sys_net_acc;
	}
}
