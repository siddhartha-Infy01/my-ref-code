package com.iexceed.esoko.domain.dao4;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.InboxMonitor;
import com.iexceed.esoko.engine.utils.LoggerUtils;


@Component
@SuppressWarnings("unchecked")
public class InboxMonitorRepo extends
		AbstractRepoForEntity<InboxMonitor, Integer> {
	private static Logger log = LoggerUtils.getLogger();
	
	
	public List<InboxMonitor> queryActivity(Date fromdate,
			Date todate, String service, String subservice,String networkId,String sortBy) {
		
		log.info("query inbox activity");
		log.info("fromdate::" + fromdate);
		log.info("todate::" + todate);
		log.info("servicename::" + service);
		log.info("subservice::" + subservice);
		log.info("networkId::" + networkId);
		Query query = null;
		
		if ("all".equalsIgnoreCase(service) && "all".equalsIgnoreCase(subservice)) {
			if(sortBy.toString().equalsIgnoreCase("name"))
			{
			query = entityManager
					.createNativeQuery(getSql("queryAllinboxActivity"),InboxMonitor.class);
			query.setParameter(1, networkId);
			query.setParameter(2, fromdate);
			query.setParameter(3, todate);
			}
			else
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivity4"),InboxMonitor.class);
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
			}
		}
		

		
		else
		{
			if("all".equalsIgnoreCase(subservice))
			{  	if(sortBy.toString().equalsIgnoreCase("name"))
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivity2"),InboxMonitor.class);
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, service);
			}
			else
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivity5"),InboxMonitor.class);
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, service);
			}
			}
			
			else if("all".equalsIgnoreCase(service))
			{
				if(sortBy.toString().equalsIgnoreCase("name"))
				{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivity1"),InboxMonitor.class);
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, subservice);
				}
				else
				{
					query = entityManager
							.createNativeQuery(getSql("queryAllinboxActivity6"),InboxMonitor.class);
					query.setParameter(1, networkId);
					query.setParameter(2, fromdate);
					query.setParameter(3, todate);
					query.setParameter(4, subservice);
				}
			}
			
			else
				
			{
				if(sortBy.toString().equalsIgnoreCase("name"))
				{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivity3"),InboxMonitor.class);
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, service);
				query.setParameter(5, subservice);
				}
				else
				{
					query = entityManager
							.createNativeQuery(getSql("queryAllinboxActivity7"),InboxMonitor.class);
					query.setParameter(1, networkId);
					query.setParameter(2, fromdate);
					query.setParameter(3, todate);
					query.setParameter(4, service);
					query.setParameter(5, subservice);
				}
				
			}
		}
		
		List<InboxMonitor> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}
	
	

	public List<Map<String, Object>> queryActivitygraph(Date fromdate,
			Date todate, String service, String subservice,String networkId) {
		
		log.info("query inbox activity");
		log.info("fromdate::" + fromdate);
		log.info("todate::" + todate);
		log.info("servicename::" + service);
		log.info("subservice::" + subservice);
		log.info("networkId::" + networkId);
		Query query = null;
		
		if ("all".equalsIgnoreCase(service) && "all".equalsIgnoreCase(subservice)) {
			query = entityManager
					.createNativeQuery(getSql("queryAllinboxActivitygraph"));
			query.setParameter(1, networkId);
			query.setParameter(2, fromdate);
			query.setParameter(3, todate);
		}
		

		
		else
		{
			if("all".equalsIgnoreCase(subservice))
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivitygraph2"));
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, service);
			}
			
			else if("all".equalsIgnoreCase(service))
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivitygraph1"));
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, subservice);
			}
			
			else
				
			{
				query = entityManager
						.createNativeQuery(getSql("queryAllinboxActivitygraph3"));
				query.setParameter(1, networkId);
				query.setParameter(2, fromdate);
				query.setParameter(3, todate);
				query.setParameter(4, service);
				query.setParameter(5, subservice);
				
			}
		}
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		for (Object[] obj : list) {
			log.debug("mapList::" + list);
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("date", obj[i]);
				}  else if (i == 1) {
					recordMap.put("no_sms", obj[i]);
				} else if (i == 2) {
					recordMap.put("type", obj[i]);
				} 			}
			resultList.add(recordMap);
		}

		entityManager.close();
		return resultList;
	}

	
	public InboxMonitor save(InboxMonitor entity) {
		super.save(entity);
		return entity;

	}

	public InboxMonitor findOne(InboxMonitor entity, Integer id) {

		return super.findOne(InboxMonitor.class, id);
	}
     
	public boolean exists(InboxMonitor entity, Integer id) {
		return super.exists(InboxMonitor.class, id);

	}

	public InboxMonitor merge(InboxMonitor entity) {
		return super.merge(entity);
	}
	
	public void delete(InboxMonitor entity) {
		log.info("Inside InboxMonitorRepo -> delete");
		super.delete(entity);
	}

}
