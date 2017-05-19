package com.iexceed.esoko.domain.dao3;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.domain.entity3.PushAlertRecipientPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class PushAlertRecipientRepo extends
		AbstractRepoForEntity<PushAlertRecipient, PushAlertRecipientPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PushAlertRecipient entity,
			PushAlertRecipientPK recipientPk) {
		log.info("Inside PushAlertRecipientRepo -> exists");
		return super.exists(PushAlertRecipient.class, recipientPk);
	}

	public PushAlertRecipient merge(PushAlertRecipient entity) {
		log.info("Inside PushAlertRecipientRepo -> merge");
		return super.merge(entity);
	}

	public PushAlertRecipient save(PushAlertRecipient entity) {
		log.info("Inside PushAlertRecipientRepo -> save");
		return super.save(entity);
	}

	public PushAlertRecipient findOne(PushAlertRecipient entity,
			PushAlertRecipientPK recipientPk) {
		log.info("Inside PushAlertRecipientRepo -> findOne");
		return super.findOne(PushAlertRecipient.class, recipientPk);
	}

	public void delete(PushAlertRecipient entity) {
		log.info("Inside PushAlertRecipientRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside PushAlertRecipientRepo -> deleteAll");
		super.delete(entities);
	}

	public List<PushAlertRecipient> findAllPushAlertRecipients(
			String pushAlertId) {
		log.info("Inside PushAlertRecipientRepo -> findAllPushAlertRecipients");
		log.debug("PushAlertId: " + pushAlertId);
		Query query = entityManager.createNativeQuery(
				getSql("findAllPushAlertRecp"), PushAlertRecipient.class);
		query.setParameter(1, pushAlertId);
		List<PushAlertRecipient> recipients = query.getResultList();
		entityManager.close();
		return recipients;

	}
}
