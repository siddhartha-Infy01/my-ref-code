package com.iexceed.esoko.domain.dao2;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.ResellerMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@SuppressWarnings("unchecked")
@Component
public class ResellerDetailsRepo extends
		AbstractRepoForEntity<ResellerMaster, String> {
	public static Logger log = LoggerUtils.getLogger();

	public ResellerMaster save(ResellerMaster entity) {
		log.info("Inside ResellerDetailsRepo -> save");
		super.save(entity);
		return entity;
	}

	public ResellerMaster findOne(ResellerMaster entity, String resellerId) {
		log.info("Inside ResellerDetailsRepo -> findOne");
		return super.findOne(ResellerMaster.class, resellerId);
	}

	public boolean exists(ResellerMaster entity, String resellerId) {
		log.info("Inside ResellerDetailsRepo -> exists");
		return super.exists(ResellerMaster.class, resellerId);

	}

	public ResellerMaster merge(ResellerMaster entity) {
		log.info("Inside ResellerDetailsRepo -> merge");
		return super.merge(entity);
	}

	public ResellerMaster getResellerDetails(String resellerId) {
		log.info("Inside ResellerNetworkRepo :: getResellerDetails");
		log.debug("resellerId::" + resellerId);
		ResellerMaster entity = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryResellerDtls"), ResellerMaster.class);
			query.setParameter(1, resellerId);
			entity = (ResellerMaster) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return entity;
	}

	public String getResellerAdmins(String networkId) {
		log.info("Inside ResellerNetworkRepo :: getResellerAdmins");
		log.debug("networkId::" + networkId);
		String resellerAdmins = null;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("getResellerAdmins"));
			query.setParameter(1, networkId);
			resellerAdmins = (String) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		log.debug("resellerAdmins::" + resellerAdmins);
		return resellerAdmins;
	}

	public List<ResellerMaster> getAllReseller(Object networkId) {
		log.info("Inside ResellerDetailsRepo :: getAllReseller");
		log.debug("resellerId::" + networkId);
		List<ResellerMaster> entity = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryAllResellerDtls"), ResellerMaster.class);
			query.setParameter(1, networkId);
			entity = query.getResultList();
		} catch (NoResultException e) {
			log.debug("No records found");
		}
		entityManager.close();
		return entity;
	}

	public ResellerMaster getResellerDetailsByNetwork(String networkId) {
		log.info("Inside ResellerNetworkRepo :: getResellerDetailsByNetwork");
		log.debug("networkId::" + networkId);
		ResellerMaster entity = null;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryResellerDtlsByNetwork"), ResellerMaster.class);
			query.setParameter(1, networkId);
			entity = (ResellerMaster) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("No records found");
		} catch(Exception e){
			log.debug("Error Occurred");
		}
		entityManager.close();
		return entity;
	}
}
