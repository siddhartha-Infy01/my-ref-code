package com.iexceed.esoko.domain.dao;

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
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.NetworkLocation;
import com.iexceed.esoko.domain.entity.NetworkLocationPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class LocationRepo extends AbstractRepoForEntity<Location, String> {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	NetworkLocationRepo networkLocationRepo;

	

	public Location findOne(Location location , String locationId) {
		log.info("Inside LocationRepo -> findOne");
		return super.findOne(Location.class, locationId);
	}
	public List<Location> queryLocationsbyLocId(String locationId) {
		log.info("Entered query locations by location id");
		log.info("location::" + locationId);
		List<Location> location;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryLocationsbyLocId"), Location.class);
			query.setParameter(1, "%" + locationId + "%");
			location = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();
		return location;

	}
	
	public Location findLocationFromCode(String networkId,String Code) {
		log.info("inside findLocationFromCode");
		log.info("networkId::" + networkId);
		log.info("Code::" + Code);
		Location location;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("findLocationFromCode"), Location.class);
					
			query.setParameter(2, networkId);
			query.setParameter(1, Code);
			query.setParameter(4, networkId);
			query.setParameter(3, Code);
			
			location = (Location) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		catch (RuntimeException e) {
			log.error(Utils.getStackTrace(e));
			return null;
		}
		entityManager.close();
		return location;

	}
	

	public List<Location> queryLocationsbyParentID(String parentId) {
		log.info("Entered query locations by parent id");
		log.info("ParentID::" + parentId);
		Query query = entityManager.createNativeQuery(
				getSql("queryLocationsbyParentID"), Location.class);
		query.setParameter(1, parentId);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public Map<String, String> queryAllLocations(String userData,
			String queryCriteria, String widgetId, String networkId) {
		log.info("Entered query all Locations");
		log.debug("userData::" + userData);
		log.debug("queryCriteria::" + queryCriteria);
		Map<String, String> columnMap = null;
		List<Location> resultList = null;
		Query query = null;
		if ("Y".equalsIgnoreCase(userData)) {
			log.info("Querying User Data::");
			query = entityManager.createNativeQuery(
					"{call findAllLocationsForUser(?,?)}", Location.class);
			query.setParameter(1, queryCriteria);
			query.setParameter(2, networkId);
			resultList = query.getResultList();
			columnMap = new HashMap<String, String>();
			if (widgetId != null) {
				Query widgetQuery = entityManager.createNativeQuery(
						"{call findAllLocationsByWidget(?,?)}", Location.class);

				widgetQuery.setParameter(1, queryCriteria);
				widgetQuery.setParameter(2, widgetId);
				List<Location> widgetResultList = widgetQuery.getResultList();
				for (Location obj : resultList) {
					log.debug("Creating mapped object::" + obj);

					log.debug("Key::" + obj.getLocationId());

					columnMap.put(obj.getLocationId(),
							widgetResultList.contains(obj) ? "Y" : "N");
				}
			} else {

				Query queryParentChecked = entityManager.createNativeQuery(
						"{call findLocationsByParentChecked(?,?,?)}",
						Location.class);
				queryParentChecked.setParameter(1, queryCriteria);
				queryParentChecked.setParameter(2, userData);
				queryParentChecked.setParameter(3, networkId);
				List<Location> userDataResultList = queryParentChecked
						.getResultList();
				for (Location obj : resultList) {
					log.debug("Creating mapped object::" + obj);

					log.debug("Key::" + obj.getLocationId());

					columnMap.put(obj.getLocationId(), "N");
				}
				for (Location location : userDataResultList) {

					columnMap.put(location.getLocationId(), "Y");
				}
				log.debug("columnMap::" + columnMap);

			}

		} else if ("A".equalsIgnoreCase(userData)) {
			log.info("Querying User Data::");
			query = entityManager.createNativeQuery(
					"{call findAllLocationsForUser(?,?)}", Location.class);
			query.setParameter(1, queryCriteria);
			query.setParameter(2, networkId);
			resultList = query.getResultList();
			columnMap = new HashMap<String, String>();

			Query queryParentChecked = entityManager.createNativeQuery(
					"{call findLocationsByParentChecked(?,?,?)}",
					Location.class);
			queryParentChecked.setParameter(1, queryCriteria);
			queryParentChecked.setParameter(2, userData);
			queryParentChecked.setParameter(3, networkId);
			List<Location> userDataResultList = queryParentChecked
					.getResultList();
			for (Location obj : resultList) {
				log.debug("Creating mapped object::" + obj);

				log.debug("Key::" + obj.getLocationId());

				columnMap.put(obj.getLocationId(), "N");
			}
			for (Location location : userDataResultList) {

				columnMap.put(location.getLocationId(), "Y");
			}
			log.debug("columnMap::" + columnMap);

		} else {
			log.info("Querying Network Data::");
			query = entityManager.createNativeQuery(
					"{call findAllLocationsByNetwork(?)}", Location.class);
			query.setParameter(1, queryCriteria);
			resultList = query.getResultList();
			columnMap = new HashMap<String, String>();
			for (Location obj : resultList) {
				log.debug("Creating mapped object::" + obj);
				NetworkLocation networkLocation = new NetworkLocation();
				NetworkLocationPK networkLocationPK = new NetworkLocationPK();
				networkLocationPK.setLocationId(obj.getLocationId());
				networkLocationPK.setNetworkId(queryCriteria);
				networkLocation.setId(networkLocationPK);
				log.debug("Key::" + obj.getLocationId());
				log.debug("Value::"
						+ (networkLocationRepo.exists(networkLocation,
								networkLocationPK) ? "Y" : "N"));

				columnMap.put(obj.getLocationId(), networkLocationRepo.exists(
						networkLocation, networkLocationPK) ? "Y" : "N");
			}

		}

		entityManager.close();
		return columnMap;
	}

	public List<Location> queryAllRootLocations() {
		log.info("Entered query Commodities by parent id");
		Query query = entityManager.createNativeQuery(
				getSql("findAllRootLocations"), Location.class);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public List<Location> queryCountries() {
		log.info("all countries");
		Query query = entityManager.createNativeQuery(getSql("queryCountries"),
				Location.class);
		List<Location> resultList = query.getResultList();
		entityManager.close();

		return resultList;
	}

	public List<Location> queryTowns(String parentCountry) {
		log.info("Entered query towns by parent country");
		log.info("ParentID::" + parentCountry);
		Query query = entityManager.createNativeQuery(getSql("queryTowns"),
				Location.class);
		query.setParameter(1, parentCountry);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<Location> queryStateTowns(String state) {
		log.info("Entered query stateTowns by parent country");
		log.info("ParentID::" + state);
		Query query = entityManager.createNativeQuery(
				getSql("queryStateTowns"), Location.class);
		query.setParameter(1, state);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<Location> queryStates(String parentCountry) {
		log.info("Inside LocationRepo - > queryStates");
		log.info("ParentID::" + parentCountry);
		Query query = entityManager.createNativeQuery(getSql("queryStates"),
				Location.class);
		query.setParameter(1, parentCountry);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public List<Location> queryMarkets(String parentRegion) {
		log.info("Inside LocationRepo - > queryMarkets");
		log.info("ParentID::" + parentRegion);
		Query query = null;
		if (parentRegion.equals("")) {
			query = entityManager.createNativeQuery(getSql("queryAllMarkets"),
					Location.class);
		} else {
			query = entityManager.createNativeQuery(getSql("queryMarkets"),
					Location.class);
			query.setParameter(1, parentRegion);
		}
		
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;
	}

	public Location save(Location loc) {
		super.save(loc);
		return loc;

	}

	public Location merge(Location loc) {
		super.merge(loc);
		return loc;

	}

	public int countLocChilds(String parentId) {
		log.info("Entered query Location count by parent id");
		log.info("ParentID::" + parentId);
		List<Location> resultList = queryLocationsbyParentID(parentId);
		int countList = resultList.size();
		int countIterative = countList;

		while (countList > 0) {
			for (Location location : resultList) {
				countList = 0;
				countList = queryLocationsbyParentID(location.getLocationId())
						.size();
				resultList = queryLocationsbyParentID(location.getLocationId());
				countIterative = countIterative + countList;

			}
		}

		return countIterative;

	}

	public Map<String, List<Location>> buildLocationHierarchy(String parentId) {

		Map<String, List<Location>> locationMap = new LinkedHashMap<String, List<Location>>();
		List<Location> resultList = queryLocationsbyParentID(parentId);
		locationMap.put(parentId, resultList);
		int countList = resultList.size();
		while (countList > 0) {
			for (Location location : resultList) {

				resultList = queryLocationsbyParentID(location.getLocationId());
				locationMap.put(location.getLocationId(), resultList);
				countList = resultList.size();
			}
		}

		return locationMap;

	}

	public int countChilds(String parentId) {

		List<Location> resultList = queryLocationsbyParentID(parentId);
		int countList = resultList.size();
		int countIterative = countList;
		while (countList > 0) {
			for (Location location : resultList) {

				countList = 0;

				countList = queryLocationsbyParentID(location.getLocationId())
						.size();
				resultList = queryLocationsbyParentID(location.getLocationId());

				countIterative = countIterative + countList;

			}
		}
		return countIterative;

	}

	public List<Location> queryAllGisLocations() {
		log.info("Entered queryAllGisLocations");
		Query query = entityManager.createNativeQuery(
				getSql("findAllGisLocations"), Location.class);
		List<Location> resultList = query.getResultList();
		entityManager.close();
		return resultList;

	}

	public List<Location> queryUnauthLocs(String sortBy) {
		log.info("Inside LocationRepo - > queryUnauthLocs");

	Query query = null;
		if(sortBy.toString().equalsIgnoreCase("name"))
		{
		 query = entityManager.createNativeQuery(getSql("queryUnauthLocs"),
				Location.class);
		}
		
		else 
		{
			 query = entityManager.createNativeQuery(getSql("queryUnauthLocs1"),
						Location.class);
		}
	



		List<Location> resultList = query.getResultList();
		entityManager.close();
		
		return resultList;
	}
	
	public int approveLocation(String locationId, String approveFlag) {
		log.info("Inside location repo for approval");
		log.debug("locationId -> " + locationId);
		log.debug("approveFlag -> " + approveFlag);
		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call approveLocation(?,?)}");
			query.setParameter(1, locationId);
			query.setParameter(2, approveFlag);
			count = query.executeUpdate();
			

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
}
