package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class CommodityRepo extends AbstractRepoForEntity<Commodity, String> {
	@Autowired
	CommodityVarietyRepo varietyRepo;
	@Autowired
	NetworkCommodityRepo networkCommodityRepo;

	public static Logger log = LoggerUtils.getLogger();
	
	public Commodity findCommodityFromCode(String networkId,String Code) {
		log.info("inside findLocationFromCode");
		log.info("networkId::" + networkId);
		log.info("Code::" + Code);
		Commodity commo;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findCommodityFromCode"), Commodity.class);
					
			query.setParameter(2, networkId);
			query.setParameter(1, Code);
			query.setParameter(4, networkId);
			query.setParameter(3, Code);
			
			commo = (Commodity) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		catch (RuntimeException e) {
			log.error(Utils.getStackTrace(e));
			return null;
		}
		entityManager.close();
		return commo;

	}
	
	

	public List<Commodity> queryCommoditiesbyCommId(String commodityId) {
		List<Commodity> commodity;
		log.info("Entered query Commodities by commodity id");
		log.info("Commodity::" + commodityId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findByCommodityId"), Commodity.class);
			query.setParameter(1, "%" + commodityId + "%");
			System.out.println("Executing Query" + getSql("findByCommodityId"));
			commodity = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return commodity;

	}
	
	public Commodity findOne(Commodity location , String commodityId) {
		log.info("Inside commodityRepo -> findOne");
		return super.findOne(Commodity.class, commodityId);
	}

	public List<Commodity> queryCommoditiesbyParentID(String parentId) {
		Query query = entityManager.createNativeQuery(getSql("findByParentID"),
				Commodity.class);
		query.setParameter(1, parentId);
		List<Commodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public Commodity save(Commodity comm) {
		super.save(comm);
		return comm;

	}

	public int countChilds(String parentId) {

		List<Commodity> resultList = queryCommoditiesbyParentID(parentId);
		int countList = resultList.size();
		int countIterative = countList;
		int varitiesCount = 0;
		List variteisList = new ArrayList();
		while (countList > 0) {
			for (Commodity commodity : resultList) {

				countList = 0;
				varitiesCount = 0;

				countList = queryCommoditiesbyParentID(
						commodity.getCommodityId()).size();
				resultList = queryCommoditiesbyParentID(commodity
						.getCommodityId());
				variteisList = varietyRepo.queryVarietiesbyCommId(commodity
						.getCommodityId());
				if (variteisList != null) {
					varitiesCount = variteisList.size();
				}

				countIterative = countIterative + countList + varitiesCount;

			}
		}
		return countIterative;

	}

	public Map<String, String> queryAllCommodities(String userData,
			String queryCriteria, String widgetId, String networkId) {
		log.info("Entered query all commodities");
		log.debug("userData::" + userData);
		log.debug("queryCriteria::" + queryCriteria);
		Map<String, String> columnMap = null;
		Query query = null;
		if ("Y".equalsIgnoreCase(userData)) {
			log.info("Querying User Data::");
			query = entityManager.createNativeQuery(
					"{call findAllCommoditiesForUser(?,?)}", Commodity.class);
			query.setParameter(1, queryCriteria);
			query.setParameter(2, networkId);
			List<Commodity> resultList = query.getResultList();
			columnMap = new HashMap<String, String>();
			if (widgetId != null) {
				Query widgetQuery = entityManager.createNativeQuery(
						"{call findAllCommoditiesByWidget(?,?)}",
						Commodity.class);
				widgetQuery.setParameter(1, queryCriteria);
				widgetQuery.setParameter(2, widgetId);
				List<Commodity> widgetResultList = widgetQuery.getResultList();
				for (Commodity obj : resultList) {
					log.debug("Creating mapped object::" + obj);

					log.debug("Key::" + obj.getCommodityId());

					columnMap.put(obj.getCommodityId(), "N");
				}
				for (Commodity commodity : widgetResultList) {

					columnMap.put(commodity.getCommodityId(), "Y");
				}
				log.debug("columnMap::" + columnMap);
			} else {

				Query queryParentChecked = entityManager.createNativeQuery(
						"{call findCommoditiesByParentChecked(?,?,?)}",
						Commodity.class);
				queryParentChecked.setParameter(1, queryCriteria);
				queryParentChecked.setParameter(2, userData);
				queryParentChecked.setParameter(3, networkId);
				List<Commodity> userDataResultList = queryParentChecked
						.getResultList();
				for (Commodity obj : resultList) {
					log.debug("Creating mapped object::" + obj);

					log.debug("Key::" + obj.getCommodityId());

					columnMap.put(obj.getCommodityId(), "N");
				}
				for (Commodity commodity : userDataResultList) {

					columnMap.put(commodity.getCommodityId(), "Y");
				}
				log.debug("columnMap::" + columnMap);

			}

		} else if ("A".equalsIgnoreCase(userData)) {
			log.info("Querying User Data::");
			query = entityManager.createNativeQuery(
					"{call findAllCommoditiesForUser(?,?)}", Commodity.class);
			query.setParameter(1, queryCriteria);
			query.setParameter(2, networkId);
			List<Commodity> resultList = query.getResultList();
			columnMap = new HashMap<String, String>();

			Query queryParentChecked = entityManager.createNativeQuery(
					"{call findCommoditiesByParentChecked(?,?,?)}",
					Commodity.class);
			queryParentChecked.setParameter(1, queryCriteria);
			queryParentChecked.setParameter(2, userData);
			queryParentChecked.setParameter(3, networkId);
			List<Commodity> userDataResultList = queryParentChecked
					.getResultList();
			for (Commodity obj : resultList) {
				log.debug("Creating mapped object::" + obj);

				log.debug("Key::" + obj.getCommodityId());

				columnMap.put(obj.getCommodityId(), "N");
			}
			for (Commodity commodity : userDataResultList) {

				columnMap.put(commodity.getCommodityId(), "Y");
			}
			log.debug("columnMap::" + columnMap);

		} else {
			log.info("Querying Network Data::");
			query = entityManager.createNativeQuery(
					"{call findAllCommoditiesByNetwork(?)}", Commodity.class);
			query.setParameter(1, queryCriteria);
			List<Commodity> resultList = query.getResultList();
			columnMap = new HashMap<String, String>();
			for (Commodity obj : resultList) {
				log.debug("Creating mapped object::" + obj);
				log.debug("Commodity ID::" + obj.getCommodityId());

				log.debug("Creating mapped object::" + obj);

				log.debug("Key::" + obj.getCommodityId());
				columnMap.put(obj.getCommodityId(), "N");

			}

			Query queryParentChecked = entityManager.createNativeQuery(
					"{call findCommoditiesByParentChecked(?,?,?)}",
					Commodity.class);
			queryParentChecked.setParameter(1, queryCriteria);
			queryParentChecked.setParameter(2, userData);
			queryParentChecked.setParameter(3, networkId);
			List<Commodity> userDataResultList = queryParentChecked
					.getResultList();
			for (Commodity commodity : userDataResultList) {

				columnMap.put(commodity.getCommodityId(), "Y");
			}
			log.debug("columnMap::" + columnMap);

		}

		entityManager.close();
		return columnMap;
	}

	public List<Commodity> queryAllRootCommodities() {

		log.info("Entered query Commodities by parent id");
		Query query = entityManager.createNativeQuery(
				getSql("findAllRootCommodities"), Commodity.class);
		List<Commodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public boolean hasChild(String commodityId) {
		log.info("Entered query Commodities by parent id");
		Query query = entityManager.createNativeQuery(
				getSql("hasChildCommodity"), Commodity.class);
		List<Commodity> resultList = query.getResultList();
		if (resultList.size() == 0) {
			return false;
		}
		entityManager.close();
		return true;
	}

	public Map<String, List<Commodity>> buildCommodityHierarchy(String parentId) {

		Map<String, List<Commodity>> commodityMap = new LinkedHashMap<String, List<Commodity>>();
		List<Commodity> resultList = queryCommoditiesbyParentID(parentId);
		commodityMap.put(parentId, resultList);
		int countList = resultList.size();
		while (countList > 0) {
			for (Commodity commodity : resultList) {

				resultList = queryCommoditiesbyParentID(commodity
						.getCommodityId());
				commodityMap.put(commodity.getCommodityId(), resultList);
				countList = resultList.size();
			}
		}

		return commodityMap;

	}

	public List<Commodity> findAllUnAuthCommodities() {
		log.info("inside findAllUnAuthCommodities");
		Query query = entityManager.createNativeQuery(
				getSql("findAllUnAuthCommodities"), Commodity.class);
		List<Commodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	
	public List<Commodity> queryCommodities(String detailId) {

		log.info("inside queryCommodities");
		log.debug("detailId::" + detailId);
		Query query = entityManager.createNativeQuery(
				"{call findCommoditiesByParentChecked(?,?,?)}",Commodity.class);
		query.setParameter(1, detailId);
		query.setParameter(2, "A");
		query.setParameter(3, "");
		List<Commodity> resultList = query.getResultList();
		log.debug("CommoditiesList:"+resultList);
		entityManager.close();
		return resultList;

	}
	

	public List<Commodity> queryChildCommdsByParentId(String parentId) {
		log.info("inside queryChildCommodByParentId");
		log.debug("parentId::" + parentId);
		Query query = entityManager.createNativeQuery(getSql("queryChildCommdsByParentId"),
				Commodity.class);
		query.setParameter(1, parentId);
		List<Commodity> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}
	
	public List<Commodity> findAllVarietiesFromRoot(String parentId) {
		log.info("inside findAllVarietiesFromRoot");
		log.debug("parentId::" + parentId);
		Query query = entityManager.createNativeQuery(
				"{call findAllVarietiesFromRoot(?)}", Commodity.class);
		query.setParameter(1, parentId);
		List<Commodity> listOfCommodities = query.getResultList();
		entityManager.close();
		return listOfCommodities;
	}

}
