package com.iexceed.esoko.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.Transaction_code;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class Transaction_codeRepo extends
		AbstractRepoForEntity<Transaction_code, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<Transaction_code> listAllServices() {
		log.info("list all services available in esoko");

		Query query = entityManager.createNativeQuery(
				getSql("listAllServices"), Transaction_code.class);

		List<Transaction_code> resultList = query.getResultList();

		entityManager.close();
		return resultList;

	}

	public Transaction_code getTrnCodeObject(String transactionCode) {
		log.info("Getting of transaction code object");
		log.debug("TransacTion code:" + transactionCode);
		Transaction_code transactionObj;
		Query query = entityManager.createNativeQuery(
				getSql("getTrnCodeObject"), Transaction_code.class);
		query.setParameter(1, transactionCode);
		transactionObj = (Transaction_code) query.getSingleResult();
		entityManager.close();
		return transactionObj;

	}
}
