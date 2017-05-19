package com.iexceed.esoko.domain.dao4;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity4.KeywordAlia;
import com.iexceed.esoko.domain.entity4.KeywordAliaPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class KeywordAliaRepo extends
		AbstractRepoForEntity<KeywordAlia, KeywordAliaPK> {
	
	private static Logger log = LoggerUtils.getLogger();
	
	public boolean exists(KeywordAlia entity, KeywordAliaPK id) {
		log.info("Inside KeywordAliaRepo -> exists");
		return super.exists(KeywordAlia.class, id);
	}

	public KeywordAlia merge(KeywordAlia entity) {
		log.info("Inside KeywordAliaRepo -> merge");
		return super.merge(entity);
	}

	public KeywordAlia save(KeywordAlia entity) {
		log.info("Inside KeywordAliaRepo -> save");
		return super.save(entity);
	}

	public KeywordAlia findOne(KeywordAlia entity, KeywordAliaPK id) {
		log.info("Inside KeywordAliaRepo -> findOne");
		return super.findOne(KeywordAlia.class, id);
	}

	public void delete(KeywordAlia entity) {
		log.info("Inside KeywordAliaRepo -> delete");
		super.delete(entity);
	}
	
	public KeywordAlia findKeyword(String aliasId,String Channel)
	{
		KeywordAlia entity;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findKeyword"),
					KeywordAlia.class);
			query.setParameter(1, aliasId);
			query.setParameter(2, Channel);
			entity = (KeywordAlia) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		
		return entity;
	}
	
	public int aliasCheck(String aliasId,String channel,String keyword) {
		log.info("Inside KeywordAliaRepo -> aliasCheck");
		log.debug("aliasId -> " + aliasId);
		log.debug("channel -> " + channel);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call aliasCheck(?,?,?)}");
			query.setParameter(1, aliasId);
			query.setParameter(2, channel);
			query.setParameter(3, keyword);
			
			query.executeUpdate();
			
			count = Integer.parseInt(query.getSingleResult().toString());

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	

	
	public int delAlias(String channel,String keyword) {
		log.info("Inside KeywordAliaRepo -> aliasCheck");
		log.debug("keyword -> " + keyword);
		log.debug("channel -> " + channel);

		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(getSql("aliasCheck1"));
			
			query.setParameter(1, keyword);
			query.setParameter(2, channel);
			
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	
	public int keywordCheck(String channel,String keyword) {
		log.info("Inside KeywordAliaRepo -> aliasCheck");
		log.debug("keyword -> " + keyword);
		log.debug("channel -> " + channel);

		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(getSql("aliasCheck"));
			
			query.setParameter(1, keyword);
			query.setParameter(2, channel);
			
			count = Integer.parseInt(query.getSingleResult().toString());

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	
	
}
 