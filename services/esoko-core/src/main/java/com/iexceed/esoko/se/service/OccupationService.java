package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.OccupationRepo;
import com.iexceed.esoko.domain.dao.UserOccupationRepo;
import com.iexceed.esoko.domain.entity.Occupation;
import com.iexceed.esoko.domain.entity.UserOccupation;
import com.iexceed.esoko.domain.entity.UserOccupationPK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.CROCCDTLS;
import com.iexceed.esoko.jaxb.se.CRSELOCC;
import com.iexceed.esoko.jaxb.se.CreateOccupationReq;
import com.iexceed.esoko.jaxb.se.CreateOccupationRes;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedOccupation;
import com.iexceed.esoko.jaxb.se.DLOCCDTLS;
import com.iexceed.esoko.jaxb.se.DeleteOccupationReq;
import com.iexceed.esoko.jaxb.se.DeleteOccupationRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.MapOccupationReq;
import com.iexceed.esoko.jaxb.se.MapOccupationRes;
import com.iexceed.esoko.jaxb.se.QROCCDTLS;
import com.iexceed.esoko.jaxb.se.QRYOCCPCHLDCOUNT;
import com.iexceed.esoko.jaxb.se.QueryOccupChildsCountRes;
import com.iexceed.esoko.jaxb.se.QueryOccupationRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class OccupationService {

	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	OccupationRepo occupationRepo;

	@Autowired
	UserOccupationRepo userOccupationRepo;

	Enum<ERROR_CODE> ERROR = null;
	Header header = null;
	private final String serviceName = "OccupationService";

	Map<String, String> finalReslutmap;

	@Transactional
	public CreateOccupationRes createOccupation(CreateOccupationReq req) {
		CreateOccupationRes createOccRes = new CreateOccupationRes();
		Occupation occupEntity = new Occupation();
		CROCCDTLS occupReqDtls = new CROCCDTLS();
		occupReqDtls = (CROCCDTLS) req.getCROCCDTLS().get(0);
		log.info("LocationID::" + occupReqDtls.getOccupationID());
		occupEntity.setOccupationId(occupReqDtls.getOccupationID().toString());
		log.info("LocationID::" + occupReqDtls.getOccupationName());
		occupEntity.setName(occupReqDtls.getOccupationName());
		log.info("Location Type::" + occupReqDtls.getType());
		occupEntity.setType(occupReqDtls.getType());
		log.info("Location Name::" + occupReqDtls.getRank());
		occupEntity.setRank(occupReqDtls.getRank());
		log.info("Location ParentId::" + occupReqDtls.getParentId());
		occupEntity.setParentId(occupReqDtls.getParentId().toString());
		// log.info("Location ParentId::" + occupReqDtls.getNetworkId());
		// occupEntity.setNetworkId(occupReqDtls.getNetworkId());
		log.info("Location Description::" + occupReqDtls.getUserId());
		occupEntity.setCreatedBy(req.getHeader().getUserId());
		// occupEntity.setAuthStat("U");
		log.info("Locaiton RecordStatus:: A");
		occupEntity.setRecordStatus("A");
		try {
			if (!occupationRepo.exists(occupEntity,
					occupEntity.getOccupationId())) {
				occupEntity.setCreatedBy(req.getHeader().getUserId());
				occupEntity.setCreatedTs(Utils.getCurrentDate());
				occupationRepo.save(occupEntity);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				Occupation tempEntity = occupationRepo.findOne(occupEntity,
						occupEntity.getOccupationId());
				occupEntity.setCreatedBy(tempEntity.getCreatedBy());
				occupEntity.setCreatedTs(tempEntity.getCreatedTs());
				occupEntity.setModifiedBy(req.getHeader().getUserId());
				occupEntity.setModifiedTs(Utils.getCurrentDate());
				occupationRepo.merge(occupEntity);
				ERROR = ERROR_CODE.ES_UD_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create Occupation", "", ERROR);
		createOccRes.setHeader(header);
		return createOccRes;
	}

	public QueryOccupationRes queryOccupation(String occupationID, String type,
			int rank, String parentId) {

		Occupation occupEntityRes = null;
		finalReslutmap = new HashMap<String, String>();
		QueryOccupationRes qryOccRes = new QueryOccupationRes();
		if (null != parentId) {
			List<QROCCDTLS> qrOccDtlsList = queryOccupationByParentId(parentId);
			if (qrOccDtlsList.size() != 0) {
				qryOccRes.getQROCCDTLS().addAll(qrOccDtlsList);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}

		} else if (null != occupationID) {
			List<QROCCDTLS> qrOccDtls = queryOccupationByOccupationId(occupationID);
			if (qrOccDtls != null) {
				qryOccRes.getQROCCDTLS().addAll(qrOccDtls);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		}
		log.info("Inside QueryOccupationRes -> queryOccupation");
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query Occupation", "", ERROR);
		qryOccRes.setHeader(header);
		return qryOccRes;
	}

	public QueryOccupationRes queryAllOccupation(String networkId,
			String userId, String userData) {
		QROCCDTLS master = new QROCCDTLS();
		QueryOccupationRes occupationRes = new QueryOccupationRes();

		if ("Y".equalsIgnoreCase(userData)) {
			finalReslutmap = occupationRepo.queryAllOccupations(userData,
					userId, networkId);
		} else {
			finalReslutmap = new HashMap<String, String>();
		}
		List<Occupation> occupationList = occupationRepo
				.queryAllRootOccupations();

		for (Occupation occupation : occupationList) {

			log.debug("Root Occupation" + occupationList.indexOf(occupation)
					+ ":::" + occupation.getOccupationId());

			if (finalReslutmap.containsKey(occupation.getOccupationId())) {

				log.debug("Going to Build Location Hierarchy for Root"
						+ occupation.getOccupationId());

				Map<String, List<Occupation>> occupationMap = occupationRepo
						.buildOccupationHierarchy(occupation.getOccupationId());

				log.debug("Hierarchy:::" + occupationMap);

				master = queryOccupations(occupationMap, occupation);
				occupationRes.getQROCCDTLS().add(master);

			} else {

				log.debug("Going to Build Location Hierarchy for Root"
						+ occupation.getOccupationId());

				Map<String, List<Occupation>> occupationMap = occupationRepo
						.buildOccupationHierarchy(occupation.getOccupationId());

				log.debug("Hierarchy:::" + occupationMap);

				master = queryOccupations(occupationMap, occupation);
				occupationRes.getQROCCDTLS().add(master);
			}
		}
		return occupationRes;
	}

	private QROCCDTLS queryOccupations(
			Map<String, List<Occupation>> occupationMap,
			Occupation rootOccupation) {
		int size = queryOccupationByOccupationId(
				rootOccupation.getOccupationId()).size();
		QROCCDTLS master = null;
		if (size > 0) {
			master = queryOccupationByOccupationId(
					rootOccupation.getOccupationId()).get(0);

			for (String key : occupationMap.keySet()) {
				List<QROCCDTLS> childList = new ArrayList<QROCCDTLS>();
				for (Occupation occupation : occupationMap.get(key)) {
					log.debug("occupation -> " + occupation);
					QROCCDTLS qrlocdtls = queryOccupationByOccupationId(
							occupation.getOccupationId()).get(0);
					childList.add(qrlocdtls);
				}
				addChild(master, childList);
			}
		}
		return master;
	}

	private List<QROCCDTLS> queryOccupationByOccupationId(String occupationID) {
		List<Occupation> occupEntityList = null;
		occupEntityList = occupationRepo
				.queryOccupationbyoccupationId(occupationID);
		List<QROCCDTLS> qrOccDtlsList = new ArrayList<QROCCDTLS>();
		for (Occupation occupEntityRes : occupEntityList) {
			QROCCDTLS qrOccDtls = new QROCCDTLS();
			qrOccDtls.setOccupationID(occupEntityRes.getOccupationId());
			qrOccDtls.setOccupationName(occupEntityRes.getName());
			qrOccDtls.setParentId(occupEntityRes.getParentId());
			qrOccDtls.setRank(occupEntityRes.getRank());
			qrOccDtls.setType(occupEntityRes.getType());
			qrOccDtls.setChildCount(Integer.toString(occupationRepo
					.countOccuptnChilds(occupEntityRes.getOccupationId())));
			if (finalReslutmap.get(occupEntityRes.getOccupationId()) == null
					|| finalReslutmap.get(occupEntityRes.getOccupationId()) == "N") {
				qrOccDtls.setSelected("n");
			} else {
				qrOccDtls.setSelected("y");
			}
			qrOccDtlsList.add(qrOccDtls);
		}
		return qrOccDtlsList;
	}

	private List<QROCCDTLS> queryOccupationByParentId(String parentId) {
		List<Occupation> occupEntity = null;
		occupEntity = (List<Occupation>) occupationRepo
				.queryOccupationbyParentId(parentId);
		List<QROCCDTLS> qrOccDtlsList = new ArrayList<QROCCDTLS>();
		if (occupEntity.size() != 0) {
			for (Occupation occupResp : occupEntity) {
				QROCCDTLS qrOccDtls = new QROCCDTLS();
				qrOccDtls.setOccupationID(occupResp.getOccupationId());
				qrOccDtls.setOccupationName(occupResp.getName());
				qrOccDtls.setParentId(occupResp.getParentId());
				qrOccDtls.setRank(occupResp.getRank());
				qrOccDtls.setType(occupResp.getType());
				qrOccDtls.setChildCount(Integer.toString(occupationRepo
						.countOccuptnChilds(occupResp.getOccupationId())));

				if (finalReslutmap.get(occupResp.getOccupationId()) == null
						|| finalReslutmap.get(occupResp.getOccupationId()) == "N") {
					qrOccDtls.setSelected("n");
				} else {
					qrOccDtls.setSelected("y");
				}
				qrOccDtlsList.add(qrOccDtls);
				qrOccDtls = null;
			}
		} else {
		}
		return qrOccDtlsList;
	}

	@Transactional
	public DeleteOccupationRes deleteOccupation(DeleteOccupationReq req) {
		DeleteOccupationRes delOccuRes = new DeleteOccupationRes();
		DLOCCDTLS delOcupDtls = new DLOCCDTLS();
		delOcupDtls = (DLOCCDTLS) req.getDLOCCDTLS().get(0);
		log.info(" CommodityID :: " + delOcupDtls.getOccupationID());
		Occupation occupEntity = new Occupation();
		occupEntity = occupationRepo.findOne(occupEntity, delOcupDtls
				.getOccupationID().toString());
		occupEntity.setRecordStatus("D");
		try {
			if (occupEntity != null) {
				occupEntity.setModifiedBy(req.getHeader().getUserId());
				occupEntity.setModifiedTs(Utils.getCurrentDate());
				occupationRepo.merge(occupEntity);
				ERROR = ERROR_CODE.ES_DL_001;
			} else {
				ERROR = ERROR_CODE.DM_SV_003;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Delete Occupations", "", ERROR);
		delOccuRes.setHeader(header);
		return delOccuRes;
	}

	public QueryOccupChildsCountRes queryOccupChildsCount(String parentId) {
		log.info("Inside QueryOccupChildsCountRes -> queryOccupChildsCount::");
		QueryOccupChildsCountRes occupChildResp = new QueryOccupChildsCountRes();
		QRYOCCPCHLDCOUNT qryOccup = new QRYOCCPCHLDCOUNT();
		log.info("parentid -> " + parentId);
		int childCount = occupationRepo.countOccuptnChilds(parentId);
		log.info("commdityChildCount -> " + childCount);
		qryOccup.setCount(childCount);
		qryOccup.setParentId(parentId);
		occupChildResp.getQRYOCCPCHLDCOUNT().add(qryOccup);
		return occupChildResp;
	}

	private QROCCDTLS addChild(QROCCDTLS qrlocdtls, List<QROCCDTLS> newList) {
		log.debug("Size Of Response -> " + qrlocdtls.getQROCCDTLS().size());
		if (qrlocdtls.getQROCCDTLS().size() == 0) {
			log.debug("Adding the First Element List-> ");
			qrlocdtls.getQROCCDTLS().addAll(newList);
		} else {
			log.debug("Adding Next Element List-> ");
			int size = qrlocdtls.getQROCCDTLS().size();
			List<QROCCDTLS> qrlocdtls2 = qrlocdtls.getQROCCDTLS();
			while (size > 0) {
				log.debug("It has child elements,So lets browse");
				for (QROCCDTLS qrlocdtls3 : qrlocdtls2) {
					log.debug("Location::::" + qrlocdtls3.getOccupationID());
					size = qrlocdtls3.getQROCCDTLS().size();
					qrlocdtls2 = qrlocdtls3.getQROCCDTLS();
					log.debug("Size of Child" + size);
					for (QROCCDTLS qrlocdtls4 : newList) {
						log.debug("Location to be added::"
								+ qrlocdtls4.getOccupationID() + "To Parent::"
								+ qrlocdtls4.getOccupationID());
						if (qrlocdtls4.getParentId().equals(
								qrlocdtls3.getOccupationID())) {
							log.debug("Adding" + qrlocdtls4.getOccupationID()
									+ "--->TO--->"
									+ qrlocdtls3.getOccupationID());
							qrlocdtls3.getQROCCDTLS().add(qrlocdtls4);
						}

					}
				}
			}
		}

		return qrlocdtls;
	}

	public CurrentlySelectedOccupation currentlySelectedCommodities(
			String userData, String Id, String networkId) {
		CurrentlySelectedOccupation currentlySelectedOccupations = new CurrentlySelectedOccupation();
		CRSELOCC crselocc = new CRSELOCC();
		if ("Y".equalsIgnoreCase(userData)) {
			List<UserOccupation> list = userOccupationRepo
					.queryusrOccupationbyuserId(Id, networkId);
			for (UserOccupation occupation : list) {
				crselocc.getOccupationID().add(
						occupation.getId().getOccupationId());
			}

		}
		currentlySelectedOccupations.setCRSELOCC(crselocc);
		return currentlySelectedOccupations;
	}

	@Transactional
	public MapOccupationRes mapoccupation(MapOccupationReq req) {
		log.info("Inside LocationsService -> Map Locations");
		MapOccupationRes mapOccupationRes = new MapOccupationRes();
		Header header = null;
		log.debug("Occupations:::" + req.getMPOCCDTLS().getOccupationID());
		String[] occupationIds = req.getMPOCCDTLS().getOccupationID()
				.split(",");
		if (req.getMPOCCDTLS().getMapType().equalsIgnoreCase("U")) {
			userOccupationRepo.deleteUserOccupation(req.getMPOCCDTLS()
					.getMapValue(), req.getMPOCCDTLS().getNetworkId());
			for (String occupationId : occupationIds) {
				UserOccupation userOccupation = new UserOccupation();
				UserOccupationPK userOccupationPK = new UserOccupationPK();
				userOccupationPK.setOccupationId(occupationId);
				userOccupationPK.setUserId(req.getMPOCCDTLS().getMapValue());
				userOccupationPK.setNetworkId("esoko");
				userOccupation.setId(userOccupationPK);
				userOccupationRepo.save(userOccupation);
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Map Location", "Selected Occupations are successfully saved",
				ERROR_CODE.OC_SC_001);
		mapOccupationRes.setHeader(header);
		return mapOccupationRes;
	}
}
