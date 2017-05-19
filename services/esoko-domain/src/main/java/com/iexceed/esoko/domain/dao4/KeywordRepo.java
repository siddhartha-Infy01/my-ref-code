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
import com.iexceed.esoko.domain.entity4.Keyword;
import com.iexceed.esoko.domain.entity4.KeywordPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class KeywordRepo extends
		AbstractRepoForEntity<Keyword, KeywordPK> {
	
	private static Logger log = LoggerUtils.getLogger();
	
	public boolean exists(Keyword entity, KeywordPK id) {
		log.info("Inside KeywordRepo -> exists");
		return super.exists(Keyword.class, id);
	}

	public Keyword merge(Keyword entity) {
		log.info("Inside KeywordRepo -> merge");
		return super.merge(entity);
	}

	public Keyword save(Keyword entity) {
		log.info("Inside KeywordRepo -> save");
		return super.save(entity);
	}

	public Keyword findOne(Keyword entity, KeywordPK id) {
		log.info("Inside KeywordRepo -> findOne");
		return super.findOne(Keyword.class, id);
	}

	public void delete(Keyword entity) {
		log.info("Inside KeywordRepo -> delete");
		super.delete(entity);
	}
 
	public int keywordactn(String keyword,String channel,String flag,String userId)
	{
		log.debug("keyword ::" + keyword);
		log.debug("channel::" + channel);
		log.debug("userID" + userId);
		log.debug("approvalFlag ::" + flag);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("keywordActions"));
			query.setParameter(1, flag);
			query.setParameter(2,userId);
			query.setParameter(4, keyword);
			query.setParameter(3, channel);
			
					count = query.executeUpdate();

				} 
		catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			return 0;
		}
		entityManager.close();
		return count;
		
	}
	
	public int inputKeywordCheck(String channel,String keyword) {
		log.info("Inside KeywordAliaRepo -> inputKeywordCheck");
		log.debug("keyword -> " + keyword);
		log.debug("channel -> " + channel);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery(getSql("inputkeywordCheck"));
			query.setParameter(1, keyword);
			query.setParameter(2,channel);
			query.setParameter(4, channel);
			query.setParameter(3, keyword);
			
			count = Integer.parseInt(query.getSingleResult().toString());

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	public List<Map<String, Object>> queryKeywords(String networkId,String sortBy) {
		log.info("query keywords");
		log.info("networkId::" + networkId);
		log.info("sort option::" + sortBy);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Query query = null;

			
				if ("date".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryKeywordBydate"));
						query.setParameter(1, networkId);
				} else if("keyword".equalsIgnoreCase(sortBy)) {
					query = entityManager
							.createNativeQuery(getSql("queryKeywordByname"));
								query.setParameter(1, networkId);
				}
				
				else
				{
					query = entityManager
							.createNativeQuery(getSql("queryKeywordBychannel"));
								query.setParameter(1, networkId);
				}
					
			 
		
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("keyword", obj[i]);
				}  else if (i == 1) {
					recordMap.put("networkd_id", obj[i]);
				} else if (i == 2) {
					recordMap.put("join_flag", obj[i]);
				} else if (i == 3) {
					recordMap.put("reply_flag", obj[i]);
				} else if (i == 4) {
					recordMap.put("airtime_flag", obj[i]);
				} else if (i == 5) {
					recordMap.put("payee_account", obj[i]);
				} else if (i == 6) {
					recordMap.put("response", obj[i]);
				}
				else if (i == 7) {
					recordMap.put("airtime", obj[i]);
				}
				else if (i == 8) {
					recordMap.put("airtime_currency", obj[i]);
				}
				else if (i == 9) {
					recordMap.put("noOfMsgs", obj[i]);
				}
				else if (i == 10) {
					recordMap.put("noOfCharcts", obj[i]);
				}
				else if (i == 11) {
					recordMap.put("alert_id", obj[i]);
				}
				else if (i == 12) {
					recordMap.put("group_id", obj[i]);
				}
				else if (i == 13) {
					recordMap.put("my_network", obj[i]);
				}
				else if (i == 14) {
					recordMap.put("count", obj[i]);
				}
				else if (i == 15) {
					recordMap.put("group_name", obj[i]);
				}
				else if (i == 16) {
					recordMap.put("alert_name", obj[i]);
				}
				else if (i == 17) {
					recordMap.put("recordStatus", obj[i]);
				}
				else if (i == 18) {
					recordMap.put("networkName", obj[i]);
				}
				else if (i == 19) {
					recordMap.put("channel", obj[i]);
				}
				else if (i == 20) {
					recordMap.put("alias", obj[i]);
				}
			}
			resultList.add(recordMap);
		}
		entityManager.close();
		return resultList;

	}

}
