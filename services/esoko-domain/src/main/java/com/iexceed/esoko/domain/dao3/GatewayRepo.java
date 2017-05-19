package com.iexceed.esoko.domain.dao3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.Gateway;
import com.iexceed.esoko.domain.entity3.GatewayPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class GatewayRepo extends AbstractRepoForEntity<Gateway, GatewayPK> {

	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(Gateway entity, GatewayPK gatewayPk) {
		log.info("Inside GatewayRepo -> exists");
		return super.exists(Gateway.class, gatewayPk);
	}

	public Gateway merge(Gateway entity) {
		log.info("Inside GatewayRepo -> merge");
		return super.merge(entity);
	}

	public Gateway save(Gateway entity) {
		log.info("Inside GatewayRepo -> save");
		return super.save(entity);
	}

	public Gateway findOne(Gateway entity, GatewayPK gatewayPk) {
		log.info("Inside GatewayRepo -> findOne");
		return super.findOne(Gateway.class, gatewayPk);
	}

	public void delete(Gateway entity) {
		log.info("Inside GatewayRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside GatewayRepo -> deleteAll");
		super.delete(entities);
	}

	public List<Map<String, String>> getAllGateways() {
		log.info("Inside GatewayRepo -> getAllGateways");		
		Query query = entityManager
				.createNativeQuery(getSql("queryAllGateways"));				
		return this.getResult(query);
	}
	
	public List<Map<String, String>> getAllCountries() {
		log.info("Inside GatewayRepo -> getAllCountries");		
		Query query = entityManager
				.createNativeQuery(getSql("queryAllCountries"));		
		return this.getResult(query);
	}
	
	private List<Map<String, String>> getResult(Query query){
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<Object[]> entityList = query.getResultList();
		for (Object[] obj : entityList) {
			Map<String, String> recordMap = new HashMap<String, String>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("operator", obj[i].toString());
					break;
				case 1:
					recordMap.put("mcc", obj[i].toString());
					break;
				case 2:
					recordMap.put("mnc", obj[i].toString());
					break;
				case 3:
					recordMap.put("gateway", obj[i].toString());
					break;
				case 4:
					recordMap.put("country", obj[i].toString());
					break;
				case 5:
					recordMap.put("routeId", obj[i].toString());
					break;
				case 6:
					recordMap.put("cost", obj[i].toString());
					break;
				case 7:
					recordMap.put("currency", obj[i].toString());
					break;
				case 8:
					if(obj[i] == null){
						recordMap.put("exception", "None");
					}else{
						recordMap.put("exception", obj[i].toString());
					}					
					break;
				case 9:
					recordMap.put("channel", obj[i].toString());
					break;
				case 10:
					recordMap.put("direction", obj[i].toString());
					break;
				case 11:
					recordMap.put("margin", obj[i].toString());
					break;
				}
			}
			resultList.add(recordMap);
		}
		return resultList;
	}
	
}
