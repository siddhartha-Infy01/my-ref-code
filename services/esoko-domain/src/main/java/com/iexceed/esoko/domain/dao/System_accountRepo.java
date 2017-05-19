package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class System_accountRepo extends
		AbstractRepoForEntity<System_account, String> {
	public static Logger log = LoggerUtils.getLogger();

	public boolean exists(System_account sysAcc, String ownerId) {
		log.info("Inside System_accountRepo -> exists");
		return super.exists(System_account.class, ownerId);
	}

	public System_account merge(System_account sysAcc) {
		log.info("Inside System_accountRepo -> merge");
		return super.merge(sysAcc);
	}

	public System_account findOne(System_account sysAcc, String ownerId) {
		log.info("Inside System_accountRepo -> findOne");
		return super.findOne(System_account.class, ownerId);
	}

	public System_account queryAccDetails(String ownerId) {
		log.info("Entered query for user account details");
		log.info("ownerID::" + ownerId);
		System_account sysacc;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("QueryAccDetails"), System_account.class);
			query.setParameter(1, ownerId);

			sysacc = (System_account) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return sysacc;
	}

	public List<System_account> queryAllAccDetails(String userId,
			String networkId) {
		log.info("Entered query for user and network account details");
		log.info("UserID::" + userId);
		log.info("NetworkId::" + userId);
		List<System_account> sysacc = new ArrayList<System_account>();
		Query query = entityManager.createNativeQuery(
				getSql("QueryAllAccDtls"), System_account.class);
		query.setParameter(1, userId);
		query.setParameter(2, networkId);
		sysacc = query.getResultList();
		entityManager.close();
		return sysacc;
	}

	public System_account getNetworkAccount(String networkId) {
		System_account sysacc;
		Query query = entityManager.createNativeQuery(getSql("getAccountNo"),
				System_account.class);
		query.setParameter(1, networkId);
		query.setParameter(2, "N");
		sysacc = (System_account) query.getSingleResult();
		entityManager.close();
		return sysacc;

	}

	public System_account getEsokoBankAccount() {
		System_account sysacc;
		Query query = entityManager.createNativeQuery(getSql("getAccountNo"),
				System_account.class);
		query.setParameter(1, Utils.ESOKO_NETWORK);
		query.setParameter(2, "B");
		sysacc = (System_account) query.getSingleResult();
		entityManager.close();
		return sysacc;

	}

	public System_account getUserAccount(String userId) {
		System_account sysacc;
		Query query = entityManager.createNativeQuery(getSql("getAccountNo"),
				System_account.class);
		query.setParameter(1, userId);
		query.setParameter(2, "U");
		sysacc = (System_account) query.getSingleResult();
		entityManager.close();
		return sysacc;
	}

	public System_account getNotionalAccount(String networkId) {
		System_account sysacc;
		Query query = entityManager.createNativeQuery(getSql("getAccountNo"),
				System_account.class);
		query.setParameter(1, networkId);
		query.setParameter(2, "G");
		sysacc = (System_account) query.getSingleResult();
		entityManager.close();
		return sysacc;
	}

	public System_account getVendorAccount(String vendorId) {
		return null;
	}

	public boolean saveAccount(System_account accountDetails) {
		super.save(accountDetails);
		return true;
	}

	public boolean updateAccountData(System_account accountDetails) {
		super.merge(accountDetails);
		return true;
	}

	public List<Map<String, Object>> queryAccontListDtl() {
		log.info("Inside System_accountRepo -> queryAccontListDtl");
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		try {
			Query query = entityManager
					.createNativeQuery(getSql("queryAccontListDtl"));
			List<Object[]> accountDtlList = query.getResultList();
			Map<String, Object> recordMap = null;
			for (Object[] obj : accountDtlList) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("account_no", obj[i]);
						break;
					case 1:
						recordMap.put("acc_name", obj[i]);
						break;
					case 2:
						recordMap.put("user_id", obj[i]);
						break;
					case 3:
						recordMap.put("first_name", obj[i]);
						break;
					case 4:
						recordMap.put("last_name", obj[i]);
						break;
					case 5:
						recordMap.put("mobile_no", obj[i]);
						break;
					}

				}

				recordList.add(recordMap);
			}
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		log.debug("queryAccontListDtl::" + recordList);
		entityManager.close();
		return recordList;
	}

	public System_account getAccountDetails(String accountNo) {
		log.info("Inside System_accountRepo -> getAccountDetails");
		log.debug("Account no:" + accountNo);
		System_account sysacc;
		Query query = entityManager.createNativeQuery(
				getSql("getAccountDetails"), System_account.class);
		query.setParameter(1, accountNo);
		sysacc = (System_account) query.getSingleResult();
		entityManager.close();
		return sysacc;
	}
}
