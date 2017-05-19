package com.iexceed.esoko.se.service;

//import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.LocationAliaRepo;
import com.iexceed.esoko.domain.dao.LocationRepo;
import com.iexceed.esoko.domain.dao.MarketDetailsRepo;
import com.iexceed.esoko.domain.dao.NetworkLocationRepo;
import com.iexceed.esoko.domain.dao.UserLocationRepo;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.MarketDetail;
import com.iexceed.esoko.domain.entity.NetworkLocation;
import com.iexceed.esoko.domain.entity.NetworkLocationPK;
import com.iexceed.esoko.domain.entity.UserLocation;
import com.iexceed.esoko.domain.entity.UserLocationPK;
import com.iexceed.esoko.domain.utils.GeoUtils;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.approval.EDITLOCATION;
import com.iexceed.esoko.jaxb.approval.EditLocationReq;
import com.iexceed.esoko.jaxb.approval.EditLocationRes;
import com.iexceed.esoko.jaxb.se.APLOCDTLS;
import com.iexceed.esoko.jaxb.se.ApproveLocationsReq;
import com.iexceed.esoko.jaxb.se.ApproveLocationsRes;
import com.iexceed.esoko.jaxb.se.COUNTRYDTLS;
import com.iexceed.esoko.jaxb.se.CRLOCCOMM;
import com.iexceed.esoko.jaxb.se.CRLOCDAYS;
import com.iexceed.esoko.jaxb.se.CRLOCDTLS;
import com.iexceed.esoko.jaxb.se.CRLOCPRCTYP;
import com.iexceed.esoko.jaxb.se.CRSELLOC;
import com.iexceed.esoko.jaxb.se.CreateLocationsReq;
import com.iexceed.esoko.jaxb.se.CreateLocationsRes;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedLocation;
import com.iexceed.esoko.jaxb.se.DLLOCDTLS;
import com.iexceed.esoko.jaxb.se.DeleteLocationsReq;
import com.iexceed.esoko.jaxb.se.DeleteLocationsRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.LocationMapReq;
import com.iexceed.esoko.jaxb.se.LocationMapRes;
import com.iexceed.esoko.jaxb.se.MARKETDTLS;
import com.iexceed.esoko.jaxb.se.QRLOCCOMM;
import com.iexceed.esoko.jaxb.se.QRLOCDAYS;
import com.iexceed.esoko.jaxb.se.QRLOCDTLS;
import com.iexceed.esoko.jaxb.se.QRLOCPRCTYP;
import com.iexceed.esoko.jaxb.se.QRYLOCCHLDCOUNT;
import com.iexceed.esoko.jaxb.se.QueryCountriesRes;
import com.iexceed.esoko.jaxb.se.QueryLocChildsCountRes;
import com.iexceed.esoko.jaxb.se.QueryLocationsRes;
import com.iexceed.esoko.jaxb.se.QueryMarketsRes;
import com.iexceed.esoko.jaxb.se.QueryStatesRes;
import com.iexceed.esoko.jaxb.se.QueryTownsRes;
import com.iexceed.esoko.jaxb.se.STATEDTLS;
import com.iexceed.esoko.jaxb.se.TOWNDTLS;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.vividsolutions.jts.geom.Point;

@Service
public class LocationsService {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	LocationRepo locationRepo;
	@Autowired
	LocationAliaRepo locationAliaRepo;
	@Autowired
	MarketDetailsRepo marketDetailsRepo;
	@Autowired
	NetworkLocationRepo networkLocationRepo;
	@Autowired
	UserLocationRepo usrlocationRepo;

	Map<String, String> finalReslutmap;
	private final String serviceName = "LocationService";
	Header header = null;

	public QueryLocationsRes queryLocations(String locationID, String type,
			int rank, String parentId, String networkId, String userId,
			String userData) {
		log.info("Inside QueryLocationsRes -> queryLocations::");
		QueryLocationsRes locRes = new QueryLocationsRes();
		log.info("Inside QueryLocationsRes -> locationID::" + locationID);
		List<Location> locationEntitybyParent = null;
		// Location entityByLocId = null;
		if (parentId != null) {
			locationEntitybyParent = (List<Location>) locationRepo
					.queryLocationsbyParentID(parentId);
			List<QRLOCDTLS> locationList = new ArrayList<QRLOCDTLS>();
			for (Location locationQry : locationEntitybyParent) {
				QRLOCDTLS qrLocDtls = new QRLOCDTLS();
				qrLocDtls.setLocationID(locationQry.getLocationId());
				qrLocDtls.setName(locationQry.getName());
				qrLocDtls.setDescription(locationQry.getDescription());
				qrLocDtls.setType(locationQry.getType());
				qrLocDtls.setRank(locationQry.getRank());
				qrLocDtls.setParentId(locationQry.getParentId());
				locationList.add(qrLocDtls);
				qrLocDtls = null;
			}
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Location Service", "Location Service", "",
					ERROR_CODE.ES_SC_001);
			locRes.setHeader(header);
			locRes.getQRLOCDTLS().addAll(locationList);

		} else if (locationID != null) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Location Service", "Location Service", "",
					ERROR_CODE.ES_SC_001);
			locRes.setHeader(header);
			locRes.getQRLOCDTLS().addAll(queryLocation(locationID));
		} else if (userId != null && userData.equalsIgnoreCase("Y")) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Location Service", "Location Service", "",
					ERROR_CODE.ES_SC_001);
			locRes.setHeader(header);
			locRes.getQRLOCDTLS().addAll(queryMyLocation(userId, networkId));
		} else if (networkId != null && userData.equalsIgnoreCase("N")) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Location Service", "Location Service", "",
					ERROR_CODE.ES_SC_001);
			locRes.setHeader(header);
			locRes.getQRLOCDTLS().addAll(queryNetworkLocations(networkId));
		}

		return locRes;
	}

	public QueryLocationsRes queryAllLocations(String networkId, String userId,
			String widgetId, String userData) {
		log.info("Inside QueryLocationsRes -> queryLocations::");
		QueryLocationsRes locRes = new QueryLocationsRes();
		QRLOCDTLS master = new QRLOCDTLS();

		if ("Y".equalsIgnoreCase(userData)) {
			finalReslutmap = locationRepo.queryAllLocations(userData, userId,
					null, networkId);
		} else if ("Y".equalsIgnoreCase(userData) && widgetId != null) {
			finalReslutmap = locationRepo.queryAllLocations(userData, userId,
					widgetId, networkId);
		} else if ("N".equalsIgnoreCase(userData) && networkId != null) {
			finalReslutmap = locationRepo.queryAllLocations(userData,
					networkId, null, networkId);
		} else if ("N".equalsIgnoreCase(userData)
				&& userId.equalsIgnoreCase("")) {
			finalReslutmap = new HashMap<String, String>();
		} else if ("A".equalsIgnoreCase(userData) && networkId != null
				&& userId != null) {
			log.info("Building location map for network");
			finalReslutmap = locationRepo.queryAllLocations(userData, userId,
					null, networkId);
		}

		log.debug("Mapped Locations -> " + finalReslutmap);

		List<Location> locationList = locationRepo.queryAllRootLocations();

		log.debug("No of  root locations ->" + locationList.size());

		for (Location location : locationList) {

			log.debug("Root Location" + locationList.indexOf(location) + ":::"
					+ location.getLocationId());
			if (!finalReslutmap.isEmpty()) {
				if (finalReslutmap.containsKey(location.getLocationId())) {

					log.debug("Going to Build Location Hierarchy for Root"
							+ location.getLocationId());

					Map<String, List<Location>> locationMap = locationRepo
							.buildLocationHierarchy(location.getLocationId());

					log.debug("Hierarchy:::" + locationMap);

					master = queryLocations(locationMap, location);
					locRes.getQRLOCDTLS().add(master);

				}
			} else {
				log.debug("Going to Build Location Hierarchy for Root"
						+ location.getLocationId());

				Map<String, List<Location>> locationMap = locationRepo
						.buildLocationHierarchy(location.getLocationId());

				log.debug("Hierarchy:::" + locationMap);

				master = queryLocations(locationMap, location);
				locRes.getQRLOCDTLS().add(master);

			}

		}

		return locRes;
	}

	private QRLOCDTLS queryLocations(Map<String, List<Location>> locationMap,
			Location rootLocation) {
		int size = queryLocation(rootLocation.getLocationId()).size();
		QRLOCDTLS master = null;
		if (size > 0) {
			master = queryLocation(rootLocation.getLocationId()).get(0);

			for (String key : locationMap.keySet()) {
				List<QRLOCDTLS> childList = new ArrayList<QRLOCDTLS>();
				for (Location location : locationMap.get(key)) {
					log.debug("Location -> " + location);
					QRLOCDTLS qrlocdtls = queryLocation(
							location.getLocationId()).get(0);
					childList.add(qrlocdtls);
				}
				addChild(master, childList);
			}
		}
		return master;

	}

	private QRLOCDTLS addChild(QRLOCDTLS qrlocdtls, List<QRLOCDTLS> newList) {
		log.debug("Size Of Response -> " + qrlocdtls.getQRLOCDTLS().size());
		if (qrlocdtls.getQRLOCDTLS().size() == 0) {
			log.debug("Adding the First Element List-> ");
			qrlocdtls.getQRLOCDTLS().addAll(newList);
		} else {
			log.debug("Adding Next Element List-> ");
			int size = qrlocdtls.getQRLOCDTLS().size();
			List<QRLOCDTLS> qrlocdtls2 = qrlocdtls.getQRLOCDTLS();
			while (size > 0) {
				log.debug("It has child elements,So lets browse");
				for (QRLOCDTLS qrlocdtls3 : qrlocdtls2) {
					log.debug("Location::::" + qrlocdtls3.getLocationID());
					size = qrlocdtls3.getQRLOCDTLS().size();
					qrlocdtls2 = qrlocdtls3.getQRLOCDTLS();
					log.debug("Size of Child" + size);
					for (QRLOCDTLS qrlocdtls4 : newList) {
						log.debug("Location to be added::"
								+ qrlocdtls4.getLocationID() + "To Parent::"
								+ qrlocdtls4.getLocationID());
						if (qrlocdtls4.getParentId().equals(
								qrlocdtls3.getLocationID())) {
							log.debug("Adding" + qrlocdtls4.getLocationID()
									+ "--->TO--->" + qrlocdtls3.getLocationID());
							qrlocdtls3.getQRLOCDTLS().add(qrlocdtls4);
						}

					}
				}
			}
		}

		return qrlocdtls;
	}

	private List<QRLOCDTLS> queryLocation(String locationId) {

		List<Location> entityByLoc = locationRepo
				.queryLocationsbyLocId(locationId);
		MarketDetail marketDetail = marketDetailsRepo.findOne(
				MarketDetail.class, locationId);
		List<QRLOCDTLS> qrLocDtlslist = new ArrayList<QRLOCDTLS>();
		for (Location entityByLocId : entityByLoc) {
			QRLOCDTLS qrLocDtls = new QRLOCDTLS();
			qrLocDtls.setLocationID(entityByLocId.getLocationId());
			qrLocDtls.setName(entityByLocId.getName());
			qrLocDtls.setDescription(entityByLocId.getDescription());
			qrLocDtls.setType(entityByLocId.getType());
			qrLocDtls.setRank(entityByLocId.getRank());
			qrLocDtls.setParentId(entityByLocId.getParentId());
			qrLocDtls.setLongitude(Double.toString(entityByLocId.getGis()
					.getCoordinates()[0].y));
			qrLocDtls.setLatitude(Double.toString(entityByLocId.getGis()
					.getCoordinates()[0].x));
			qrLocDtls.setChildCount(Integer.toString(locationRepo
					.countChilds(entityByLocId.getLocationId())));
			if (finalReslutmap != null) {
				qrLocDtls
						.setSelected((finalReslutmap.get(entityByLocId
								.getLocationId()) == null)
								|| (finalReslutmap.get(entityByLocId
										.getLocationId()) == "N") ? "n" : "y");
			}
			if (marketDetail != null) {
				qrLocDtls
						.setLocationPic(marketDetail.getLocationPic() == null ? ""
								: marketDetail.getLocationPic().toString());
				qrLocDtls.getQRLOCDAYS().addAll(
						getDaysOfWeek(marketDetail.getDaysOfWeek()));
				qrLocDtls.getQRLOCPRCTYP().addAll(
						getPriceTypes(marketDetail.getPriceTypes()));
				qrLocDtls.getQRLOCCOMM().addAll(
						getLocationCOmmdities(marketDetail.getCommodities()));
			}
			qrLocDtlslist.add(qrLocDtls);
		}
		return qrLocDtlslist;

	}

	/*
	 * private List<QRLOCALS> queryLocationAlias(String locationId) {
	 * 
	 * List<LocationAlia> locationAlsEntity = (List<LocationAlia>)
	 * locationAliaRepo .queryLocationsAliabyLocId(locationId);
	 * 
	 * List<QRLOCALS> locationAlsList = new ArrayList<QRLOCALS>(); for
	 * (LocationAlia locationAlsQry : locationAlsEntity) { QRLOCALS qrLocAlsDtls
	 * = new QRLOCALS(); qrLocAlsDtls.setAliasID(locationAlsQry.getAliasId());
	 * qrLocAlsDtls.setName(locationAlsQry.getName());
	 * locationAlsList.add(qrLocAlsDtls); qrLocAlsDtls = null; }
	 * 
	 * return locationAlsList; }
	 */

	private List<QRLOCDAYS> getDaysOfWeek(String listofDays) {
		List<QRLOCDAYS> list = new ArrayList<QRLOCDAYS>();
		if (listofDays != null) {
			String[] days = listofDays.split(",");
			for (String day : days) {
				QRLOCDAYS qrlocdays = new QRLOCDAYS();
				qrlocdays.setDayOfWeek(day);
				list.add(qrlocdays);
			}
		}
		return list;

	}

	private List<QRLOCPRCTYP> getPriceTypes(String listofpriceTypes) {
		List<QRLOCPRCTYP> list = new ArrayList<QRLOCPRCTYP>();
		if (listofpriceTypes != null) {
			String[] pricetypes = listofpriceTypes.split(",");
			for (String pricetype : pricetypes) {
				QRLOCPRCTYP qrlocdays = new QRLOCPRCTYP();
				qrlocdays.setPriceType(pricetype);
				list.add(qrlocdays);
			}
		}
		return list;

	}

	private List<QRLOCCOMM> getLocationCOmmdities(String locationCommodities) {
		List<QRLOCCOMM> list = new ArrayList<QRLOCCOMM>();
		if (locationCommodities != null) {
			String[] commodities = locationCommodities.split(",");

			for (String commodity : commodities) {
				QRLOCCOMM qrlocdays = new QRLOCCOMM();
				qrlocdays.setCommodityId(commodity);
				list.add(qrlocdays);
			}
		}
		return list;

	}

	@Transactional
	public CreateLocationsRes createLocations(CreateLocationsReq req) {
		log.info("Inside LocationsService -> createLocations");
		CreateLocationsRes createLocationRes = new CreateLocationsRes();
		StringBuilder stringBuilder = null;
		Header header = null;
		for (CRLOCDTLS locReqDtls : req.getCRLOCDTLS()) {
			Location locationEntity = new Location();
			MarketDetail marketdetails = new MarketDetail();
			NetworkLocation location = new NetworkLocation();
			NetworkLocationPK locationPK = new NetworkLocationPK();
			locReqDtls = (CRLOCDTLS) req.getCRLOCDTLS().get(0);
			log.info("LocationID::" + locReqDtls.getLocationID());
			locationEntity.setLocationId(locReqDtls.getLocationID().toString());
			log.info("Location Name::" + locReqDtls.getName());
			locationEntity.setName(locReqDtls.getName());
			log.info("Location Description::" + locReqDtls.getDescription());
			locationEntity.setDescription(locReqDtls.getDescription());
			log.info("Location Type::" + locReqDtls.getType());
			locationEntity.setType(locReqDtls.getType());
			log.info("Location Rank::" + locReqDtls.getRank());
			locationEntity.setRank(locReqDtls.getRank());
			log.info("Location ParentId::" + locReqDtls.getParentId());
			locationEntity.setParentId(locReqDtls.getParentId().toString());
			locationEntity.setGis(GeoUtils.getGisValue(
					locReqDtls.getLongitude(), locReqDtls.getLatitude()));
			locationEntity.setRecordStatus("A");
			locationEntity.setCreatedBy(req.getHeader().getUserId());
			locationEntity.setCreatedTs(Utils.getCurrentDate());
			locationEntity.setModifiedBy(req.getHeader().getUserId());
			locationEntity.setModifiedTs(Utils.getCurrentDate());

			log.info("Locaiton RecordStatus:: A");
			if (locReqDtls.getType().equals("L")) {
				locationEntity.setAuthStat("A");
				locationEntity.setAuthBy(req.getHeader().getUserId());
				locationPK.setLocationId(locReqDtls.getLocationID());
				locationPK.setNetworkId(locReqDtls.getNetworkID());
				location.setId(locationPK);
				location.setCreatedBy(req.getHeader().getUserId());
				location.setCreatedTs(Utils.getCurrentDate());
				location.setModifiedBy(req.getHeader().getUserId());
				location.setModifiedTs(Utils.getCurrentDate());
				networkLocationRepo.save(location);
			} else {
				locationEntity.setAuthStat("U");
			}
			log.info("ParentId -> " + locReqDtls.getParentId());
			locationEntity.setParentId(locReqDtls.getParentId());
			log.info("ParentCountry -> " + locReqDtls.getParentCountry());
			locationEntity.setParentCountry(locReqDtls.getParentCountry());
			marketdetails.setLocationId(locReqDtls.getLocationID());
			for (CRLOCDAYS crlocdays : locReqDtls.getCRLOCDAYS()) {
				stringBuilder = new StringBuilder();
				stringBuilder.append(crlocdays.getDayOfWeek() + ",");
				marketdetails.setDaysOfWeek(stringBuilder.toString());
			}

			for (CRLOCCOMM crloccomm : locReqDtls.getCRLOCCOMM()) {
				stringBuilder = new StringBuilder();
				stringBuilder.append(crloccomm.getCommodityId() + ",");
				marketdetails.setCommodities(stringBuilder.toString());
			}

			for (CRLOCPRCTYP crlocprctyp : locReqDtls.getCRLOCPRCTYP()) {
				stringBuilder = new StringBuilder();
				stringBuilder.append(crlocprctyp.getPriceType() + ",");
				marketdetails.setPriceTypes(stringBuilder.toString());
			}
			if (locReqDtls.getLocationPic().getBytes() != null)
				marketdetails.setLocationPic(locReqDtls.getLocationPic()
						.getBytes());

			marketDetailsRepo.merge(marketdetails);
			locationRepo.merge(locationEntity);
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Map Location", "Location Successfully Created",
				ERROR_CODE.LC_SC_002);
		createLocationRes.setHeader(header);
		return createLocationRes;
	}

	@Transactional
	public DeleteLocationsRes deleteLocations(DeleteLocationsReq req) {
		DeleteLocationsRes locationRes = new DeleteLocationsRes();
		DLLOCDTLS delLocDtls = new DLLOCDTLS();
		delLocDtls = (DLLOCDTLS) req.getDLLOCDTLS().get(0);
		log.info(" LocationID :: " + delLocDtls.getLocationID());
		Location locationEntity = locationRepo.findOne(Location.class,
				delLocDtls.getLocationID().toString());
		locationEntity.setRecordStatus("D");
		locationRepo.save(locationEntity);
		locationRes.setHeader(req.getHeader());
		return locationRes;
	}

	@Transactional
	public ApproveLocationsRes approveLocations(ApproveLocationsReq req) {
		ApproveLocationsRes approveLocationsRes = new ApproveLocationsRes();
		Location location = new Location();
		APLOCDTLS appDtls = new APLOCDTLS();
		appDtls = (APLOCDTLS) req.getAPLOCDTLS().get(0);
		location = locationRepo.findOne(Location.class, appDtls.getLocationID()
				.toString());
		location.setLocationId(appDtls.getLocationID().toString());
		log.info(" LocationId :: " + location.getLocationId());
		location.setAuthBy(appDtls.getApproverID().toString());
		log.info(" ApproverID :: " + location.getAuthBy());
		location.setAuthTs(appDtls.getApprovalDate().toGregorianCalendar()
				.getTime());
		log.info(" Auth Timestamp :: " + location.getAuthTs());
		approveLocationsRes.setHeader(req.getHeader());
		return approveLocationsRes;
	}

	public QueryLocChildsCountRes queryLocChildsCount(String parentId) {
		log.info("Inside QueryLocChildsCountRes -> queryCmdsChildsCount::");
		log.info("parentid -> " + parentId);
		QueryLocChildsCountRes countRes = new QueryLocChildsCountRes();
		int commdityChildCount = locationRepo.countLocChilds(parentId);
		log.info("commdityChildCount -> " + commdityChildCount);
		QRYLOCCHLDCOUNT qryChldCount = new QRYLOCCHLDCOUNT();
		qryChldCount.setParentId(parentId);
		qryChldCount.setCount(commdityChildCount);
		countRes.getQRYLOCCHLDCOUNT().add(qryChldCount);
		return countRes;
	}

	public QueryCountriesRes queryCountries() {
		log.info("Inside QueryCountriesRes -> queryCountries::");
		QueryCountriesRes countriesRes = new QueryCountriesRes();
		List<Location> countries = (List<Location>) locationRepo
				.queryCountries();
		List<COUNTRYDTLS> countryList = new ArrayList<COUNTRYDTLS>();
		for (Location location : countries) {
			COUNTRYDTLS countryDtls = new COUNTRYDTLS();
			countryDtls.setLocationID(location.getLocationId());
			countryDtls.setCountryISDCode(Utils.getCountryCode(location
					.getLocationId()));
			countryList.add(countryDtls);
		}
		countriesRes.getCOUNTRYDTLS().addAll(countryList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"LocationService", "queryCountries", "", ERROR_CODE.ES_SC_001);
		countriesRes.setHeader(header);
		return countriesRes;
	}

	public QueryTownsRes queryTowns(String parentCountry) {
		log.info("Inside QueryCountriesRes -> queryTowns::");
		QueryTownsRes townsRes = new QueryTownsRes();
		List<Location> countries = (List<Location>) locationRepo
				.queryTowns(parentCountry);
		List<TOWNDTLS> countryList = new ArrayList<TOWNDTLS>();
		for (Location location : countries) {
			TOWNDTLS locationDtls = new TOWNDTLS();
			locationDtls.setLocationID(location.getLocationId());
			countryList.add(locationDtls);
		}
		townsRes.getTOWNDTLS().addAll(countryList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"LocationService", "queryTowns", "", ERROR_CODE.ES_SC_001);
		townsRes.setHeader(header);
		return townsRes;
	}

	public QueryTownsRes queryStateTowns(String state) {
		log.info("Inside QueryCountriesRes -> queryTowns::");
		QueryTownsRes townsRes = new QueryTownsRes();
		List<Location> countries = (List<Location>) locationRepo
				.queryStateTowns(state);
		List<TOWNDTLS> countryList = new ArrayList<TOWNDTLS>();
		for (Location location : countries) {
			TOWNDTLS locationDtls = new TOWNDTLS();
			locationDtls.setLocationID(location.getLocationId());
			countryList.add(locationDtls);
		}
		townsRes.getTOWNDTLS().addAll(countryList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"LocationService", "queryStateTowns", "", ERROR_CODE.ES_SC_001);
		townsRes.setHeader(header);
		return townsRes;
	}

	public QueryStatesRes queryStates(String parentCountry) {
		QueryStatesRes qryRes = new QueryStatesRes();
		List<Location> states = (List<Location>) locationRepo
				.queryStates(parentCountry);
		List<STATEDTLS> stateList = new ArrayList<STATEDTLS>();
		if (states.size() != 0) {
			for (Location location : states) {
				STATEDTLS state = new STATEDTLS();
				state.setLocationID(location.getLocationId());
				stateList.add(state);
			}
		}
		qryRes.getSTATEDTLS().addAll(stateList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"LocationService", "queryStates", "", ERROR_CODE.ES_SC_001);
		qryRes.setHeader(header);
		return qryRes;
	}

	public QueryMarketsRes queryMarkets(String parentRegion) {
		QueryMarketsRes qryRes = new QueryMarketsRes();
		List<Location> markets = (List<Location>) locationRepo
				.queryMarkets(parentRegion);
		List<MARKETDTLS> marketList = new ArrayList<MARKETDTLS>();
		if (markets.size() != 0) {
			for (Location location : markets) {
				MARKETDTLS market = new MARKETDTLS();
				market.setLocationID(location.getLocationId());
				market.setCountryID(location.getParentCountry());
				marketList.add(market);
			}
		}
		qryRes.getMARKETDTLS().addAll(marketList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"LocationService", "queryMarkets", "", ERROR_CODE.ES_SC_001);
		qryRes.setHeader(header);
		return qryRes;
	}

	public List<QRLOCDTLS> queryMyLocation(String userId, String networkId) {
		List<UserLocation> list = usrlocationRepo.queryUserLocDetails(userId,
				networkId);
		List<QRLOCDTLS> qrlocdtlses = new ArrayList<QRLOCDTLS>();
		for (UserLocation location : list) {
			qrlocdtlses.add(queryLocation(location.getId().getLocationId())
					.get(0));
		}
		return qrlocdtlses;
	}

	public List<QRLOCDTLS> queryNetworkLocations(String networkId) {
		List<NetworkLocation> list = networkLocationRepo
				.queryNetworkLocations(networkId);
		List<QRLOCDTLS> qrlocdtlses = new ArrayList<QRLOCDTLS>();
		for (NetworkLocation location : list) {
			qrlocdtlses.add(queryLocation(location.getId().getLocationId())
					.get(0));
		}
		return qrlocdtlses;
	}

	public CurrentlySelectedLocation currentlySelectedLocations(
			String userData, String Id, String networkId) {
		log.info("Inside Query Selected Locations::");
		log.info("Query Selected Locations ID::" + Id);
		log.info("Query Selected Locations userData::" + userData);
		CurrentlySelectedLocation currentlySelectedLocation = new CurrentlySelectedLocation();
		CRSELLOC crselloc = new CRSELLOC();
		if ("Y".equalsIgnoreCase(userData)) {
			log.info("Querying User Locations::");
			List<UserLocation> list = usrlocationRepo.queryUserLocDetails(Id,
					networkId);

			for (UserLocation location : list) {
				crselloc.getLocationID().add(location.getId().getLocationId());
			}
		} else {
			log.info("Querying Network Locations::");
			List<NetworkLocation> list = networkLocationRepo
					.queryNetworkLocations(Id);
			for (NetworkLocation location : list) {
				crselloc.getLocationID().add(location.getId().getLocationId());
			}
		}
		currentlySelectedLocation.setCRSELLOC(crselloc);
		log.info("Selected Locations are::::"
				+ currentlySelectedLocation.toString());
		return currentlySelectedLocation;
	}

	@Transactional
	public LocationMapRes mapLocation(LocationMapReq req) {
		log.info("Inside LocationsService -> Map Locations");
		LocationMapRes mapLocationRes = new LocationMapRes();
		Header header = null;
		log.debug("Locations:::" + req.getMPLOCDTLS().getLocationID());
		String[] locationIds = req.getMPLOCDTLS().getLocationID().split(",");
		if (req.getMPLOCDTLS().getMapType().equalsIgnoreCase("U")) {
			usrlocationRepo.deleteUserLocation(
					req.getMPLOCDTLS().getMapValue(), req.getMPLOCDTLS()
							.getNetworkId());
			for (String locationId : locationIds) {
				UserLocation userLocation = new UserLocation();
				UserLocationPK userLocationPK = new UserLocationPK();
				userLocationPK.setLocationId(locationId);
				userLocationPK.setUserId(req.getMPLOCDTLS().getMapValue());
				userLocationPK.setNetworkId(req.getMPLOCDTLS().getNetworkId());
				userLocation.setId(userLocationPK);
				usrlocationRepo.save(userLocation);
			}
		} else {
			networkLocationRepo.deleteNetworkLocation(req.getMPLOCDTLS()
					.getMapValue());
			for (String locationId : locationIds) {
				NetworkLocation networkLocation = new NetworkLocation();
				NetworkLocationPK networkLocationPK = new NetworkLocationPK();
				networkLocationPK.setLocationId(locationId);
				networkLocationPK
						.setNetworkId(req.getMPLOCDTLS().getMapValue());
				networkLocation.setId(networkLocationPK);
				networkLocationRepo.save(networkLocation);
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Map Location", "Selected Locations are successfully saved",
				ERROR_CODE.LC_SC_001);
		mapLocationRes.setHeader(header);
		return mapLocationRes;
	}

	@Transactional
	public EditLocationRes editLocation(EditLocationReq req) {
		log.info("Inside LocationsService -> editLocation");
		EDITLOCATION editLocation = req.getEDITLOCATION();
		com.iexceed.esoko.jaxb.approval.Header apprvHeader = null;
		log.debug("Location id:" + editLocation.getLocationId());
		log.debug("Type:" + editLocation.getType());
		log.debug("Location tree:" + editLocation.getLocationTree());
		log.debug("Latitude:" + editLocation.getLatitude());
		log.debug("Longitude:" + editLocation.getLongitude());
		log.debug("Market desk:" + editLocation.getMarketDesk());
		log.debug("commodities:" + editLocation.getCommodities());
		EditLocationRes res = new EditLocationRes();
		Location loc = new Location();
		Location location = locationRepo.findOne(loc,
				editLocation.getLocationId());
		if (location != null) {
			String locTree = editLocation.getLocationTree();
			String[] parts = locTree.split(",");
			String parentId = parts[1];
			String parentCountry = parts[2];
			Point gis = GeoUtils.getGisValue(editLocation.getLongitude(),
					editLocation.getLatitude());
			location.setParentId(parentId);
			location.setParentCountry(parentCountry);
			location.setGis(gis);
			locationRepo.merge(location);
			if (editLocation.getType().equalsIgnoreCase("M")) {
				MarketDetail mrkDtl = new MarketDetail();
				mrkDtl.setLocationId(editLocation.getLocationId());
				MarketDetail marketDtl = marketDetailsRepo.findOne(mrkDtl,
						editLocation.getLocationId());
				if (marketDtl != null) {
					marketDtl.setCommodities(editLocation.getCommodities());
					marketDtl.setDaysOfWeek(editLocation.getMarketDesk());
					marketDtl.setLocationPic(editLocation.getImage());
					marketDetailsRepo.merge(marketDtl);
				}
			}
			apprvHeader = (com.iexceed.esoko.jaxb.approval.Header) HeaderFactory
					.getHeader(HeaderType.APPROVAL, serviceName,
							"editLocation", "Location successfully updated",
							ERROR_CODE.LOC_UPDT);
		} else {
			apprvHeader = (com.iexceed.esoko.jaxb.approval.Header) HeaderFactory
					.getHeader(HeaderType.APPROVAL, serviceName,
							"editLocation", "Unable to update Location",
							ERROR_CODE.ES_UD_002);

		}

		res.setHeader(apprvHeader);
		return res;
	}
}
