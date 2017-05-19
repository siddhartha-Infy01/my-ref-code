package com.iexceed.esoko.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.SmartGroupHistory;
import com.iexceed.esoko.domain.entity.SmartGroupHistoryPK;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SmartGroupHistoryRepo extends
		AbstractRepoForEntity<SmartGroupHistory, SmartGroupHistoryPK> {

	@Autowired
	private GroupMasterRepo masterRepo;
	@Autowired
	private PeopleRepo pplRepo;

	public static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager manager;

	public SmartGroupHistory merge(SmartGroupHistory smartGroupHistory) {
		log.info("Inside SmartGroupHistoryRepo -> merge");
		return super.merge(smartGroupHistory);
	}

	public SmartGroupHistory save(SmartGroupHistory smartGroupHistory) {
		log.info("Inside SmartGroupHistoryRepo -> save");		
		return super.save(smartGroupHistory);
	}

	public SmartGroupHistory findOne(SmartGroupHistory smartGroupHistory,
			SmartGroupHistoryPK smartGroupHistoryPk) {
		log.info("Inside SmartGroupHistoryRepo -> findOne");
		return super.findOne(SmartGroupHistory.class, smartGroupHistoryPk);
	}

	public void delete(Iterable smartGroupHistory) {
		log.info("Inside SmartGroupHistoryRepo -> delete");
		super.delete(smartGroupHistory);
	}

	public void delete(SmartGroupHistory smartGroupHistory) {
		log.info("Inside SmartGroupHistoryRepo -> delete");
		super.delete(smartGroupHistory);
	}
}
