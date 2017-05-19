package com.iexceed.esoko.domain.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.System_transaction;
import com.iexceed.esoko.domain.entity.System_transactionPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.SystemEnums;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class System_transactionRepo extends
		AbstractRepoForEntity<System_transaction, System_transactionPK> {
	public static Logger log = LoggerUtils.getLogger();

	public List<System_transaction> queryTransactions(Date fromdate,
			Date todate, String service, String userid) {
		log.info("query system transactions");
		log.info("fromdate::" + fromdate);
		log.info("todate::" + todate);
		log.info("servicename::" + service);

		Query query = null;
		if (SystemEnums.TXNSALL.getValue().equalsIgnoreCase(service)) {
			query = entityManager.createNativeQuery(
					getSql("queryTransactionsall"), System_transaction.class);
			query.setParameter(1, fromdate);
			query.setParameter(2, todate);
			query.setParameter(3, userid);

		} else {
			query = entityManager.createNativeQuery(
					getSql("queryTransactions"), System_transaction.class);
			query.setParameter(1, fromdate);
			query.setParameter(2, todate);
			query.setParameter(3, service);
			query.setParameter(4, userid);
		}
		List<System_transaction> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public String generateTrnRefNo(String trnCode) {
		StringBuffer transaction = new StringBuffer();
		transaction.append("TRN" + trnCode + Utils.getJulianDt()
				+ Math.round(Math.random() * 100000));
		return transaction.toString();

	}

	

	public boolean passAccountingLegs(List<System_transaction> list) {
		for (System_transaction system_transaction : list)
			super.save(system_transaction);
		return true;
	}
}
