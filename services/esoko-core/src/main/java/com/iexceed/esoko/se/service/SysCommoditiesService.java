package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.AttributesRepo;
import com.iexceed.esoko.domain.dao.CommodityAliaRepo;
import com.iexceed.esoko.domain.dao.CommodityAttributeRepo;
import com.iexceed.esoko.domain.dao.CommodityPictureRepo;
import com.iexceed.esoko.domain.dao.CommodityRepo;
import com.iexceed.esoko.domain.dao.CommodityTypeRepo;
import com.iexceed.esoko.domain.dao.CommodityVarietyRepo;
import com.iexceed.esoko.domain.dao.MeasureRepo;
import com.iexceed.esoko.domain.dao.TypesRepo;
import com.iexceed.esoko.domain.entity.Attribute;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.CommodityAlia;
import com.iexceed.esoko.domain.entity.CommodityAttribute;
import com.iexceed.esoko.domain.entity.CommodityPicture;
import com.iexceed.esoko.domain.entity.CommodityType;
import com.iexceed.esoko.domain.entity.CommodityVariety;
import com.iexceed.esoko.domain.entity.Measure;
import com.iexceed.esoko.domain.entity.Type;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.COMATTRIBS;
import com.iexceed.esoko.jaxb.se.COMFAMILY;
import com.iexceed.esoko.jaxb.se.COMGRP;
import com.iexceed.esoko.jaxb.se.COMMEASURES;
import com.iexceed.esoko.jaxb.se.COMMEASURESDTLS;
import com.iexceed.esoko.jaxb.se.COMSUBGROUP;
import com.iexceed.esoko.jaxb.se.COMTYPES;
import com.iexceed.esoko.jaxb.se.COMVARIETY;
import com.iexceed.esoko.jaxb.se.CRALSDTLS;
import com.iexceed.esoko.jaxb.se.CRCOMMATTDTLS;
import com.iexceed.esoko.jaxb.se.CRCOMMDTLS;
import com.iexceed.esoko.jaxb.se.CreateCommoditiesReq;
import com.iexceed.esoko.jaxb.se.CreateCommoditiesRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRSYSCOMMDTLS;
import com.iexceed.esoko.jaxb.se.QuerySysCommoditiesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class SysCommoditiesService {
	public static Logger log = LoggerUtils.getLogger();
	private final String serviceName = "SysCommoditiesService";
	@Autowired
	CommodityRepo commoditiesRepo;
	@Autowired
	CommodityAliaRepo commoditiesAliasRepo;
	@Autowired
	CommodityPictureRepo cmdPicRepo;
	@Autowired
	CommodityAttributeRepo cmdAttribRepo;
	@Autowired
	CommodityTypeRepo cmdTyperepo;
	@Autowired
	CommodityVarietyRepo commodityVarityRepo;
	@Autowired
	TypesRepo typeRepo;
	@Autowired
	AttributesRepo attribRepo;
	@Autowired
	MeasureRepo measurerepo;

	public QuerySysCommoditiesRes querySystemCommodity(String parentID,
			String type) {
		log.info("Entered Query Sys Commodities");
		log.info("parentID:::" + parentID);
		log.info("Type::" + type);
		List<COMGRP> group;
		List<COMSUBGROUP> subgroup;
		List<COMFAMILY> family;
		List<QRSYSCOMMDTLS> commodity;
		List<COMVARIETY> variety;
		QuerySysCommoditiesRes commoditiesRes = new QuerySysCommoditiesRes();
		if (type.equals("S")) {
			group = getGroup(parentID);
			subgroup = getSubGroup(parentID);
			family = getFamily(parentID);
			commodity = getCommodity(parentID);
			variety = getVariety(parentID);
			if (group.size() > 0)
				commoditiesRes.getCOMGRP().addAll(group);
			if (subgroup.size() > 0)
				commoditiesRes.getCOMSUBGROUP().addAll(subgroup);
			if (family.size() > 0)
				commoditiesRes.getCOMFAMILY().addAll(family);
			if (commodity.size() > 0)
				commoditiesRes.getQRSYSCOMMDTLS().addAll(commodity);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		} else if (type.equals("G")) {
			subgroup = getSubGroup(parentID);
			family = getFamily(parentID);
			commodity = getCommodity(parentID);
			variety = getVariety(parentID);
			if (subgroup.size() > 0)
				commoditiesRes.getCOMSUBGROUP().addAll(subgroup);
			if (family.size() > 0)
				commoditiesRes.getCOMFAMILY().addAll(family);
			if (commodity.size() > 0)
				commoditiesRes.getQRSYSCOMMDTLS().addAll(commodity);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		} else if (type.equals("U")) {
			family = getFamily(parentID);
			commodity = getCommodity(parentID);
			variety = getVariety(parentID);
			if (family.size() > 0)
				commoditiesRes.getCOMFAMILY().addAll(family);
			if (commodity.size() > 0)
				commoditiesRes.getQRSYSCOMMDTLS().addAll(commodity);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		} else if (type.equals("F")) {
			commodity = getCommodity(parentID);
			variety = getVariety(parentID);
			if (commodity.size() > 0)
				commoditiesRes.getQRSYSCOMMDTLS().addAll(commodity);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		} else if (type.equals("C")) {
			variety = getVariety(parentID);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		} else if (type.equals("T")) {
			variety = getVariety(parentID);
			if (variety.size() > 0)
				commoditiesRes.getCOMVARIETY().addAll(variety);
		}
		return commoditiesRes;
	}

	private List<COMGRP> getGroup(String parentID) {
		log.info("Entered Query Groups");
		List<Commodity> commodities = commoditiesRepo.queryAllRootCommodities();
		List<COMGRP> comgrplist = new ArrayList<COMGRP>();
		log.info("Root Commodities Size::" + commodities.size());
		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("G")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				COMGRP comgrp = new COMGRP();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel("G");
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				comgrplist.add(comgrp);
			}
		}

		log.info("Response From Groups Query is::");
		return comgrplist;
	}

	private List<COMSUBGROUP> getSubGroup(String parentID) {
		log.info("Entered Query Sub-Groups");
		List<Commodity> commodities = commoditiesRepo
				.queryCommoditiesbyParentID(parentID);
		List<COMSUBGROUP> comgrplist = new ArrayList<COMSUBGROUP>();
		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("U")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				COMSUBGROUP comgrp = new COMSUBGROUP();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel(commdity.getType());
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				comgrplist.add(comgrp);
			}
		}
		return comgrplist;
	}

	private List<COMFAMILY> getFamily(String parentID) {
		log.info("Entered Query Families");
		List<Commodity> commodities = commoditiesRepo
				.queryCommoditiesbyParentID(parentID);
		List<COMFAMILY> comgrplist = new ArrayList<COMFAMILY>();

		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("F")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				COMFAMILY comgrp = new COMFAMILY();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel(commdity.getType());
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				comgrplist.add(comgrp);
			}
		}
		return comgrplist;
	}

	private List<QRSYSCOMMDTLS> getCommodity(String parentID) {
		log.info("Entered Query Commodities");
		List<Commodity> commodities = commoditiesRepo
				.queryCommoditiesbyParentID(parentID);
		List<QRSYSCOMMDTLS> comgrplist = new ArrayList<QRSYSCOMMDTLS>();
		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("C")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				QRSYSCOMMDTLS comgrp = new QRSYSCOMMDTLS();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel(commdity.getType());
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				comgrp.getCOMMEASURES().addAll(getMeasures());
				comgrp.getCOMTYPES().addAll(
						getTypesForCommodity(commdity.getCommodityId()));
				comgrp.getCOMATTRIBS().addAll(
						getAttibutesForCommodity(commdity.getCommodityId()));
				comgrplist.add(comgrp);
			}
		}
		return comgrplist;
	}

	private List<COMVARIETY> getVariety(String parentID) {
		log.info("Entered Query Varities");
		List<Commodity> commodities = commoditiesRepo
				.queryCommoditiesbyParentID(parentID);
		List<COMVARIETY> comgrplist = new ArrayList<COMVARIETY>();
		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("V")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				COMVARIETY comgrp = new COMVARIETY();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel(commdity.getType());
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				CommodityPicture commodityPicture = cmdPicRepo
						.querySinglePicbyCommId(commdity.getCommodityId());
				comgrp.setPicture(commodityPicture == null ? null
						: commodityPicture.getContent());
				comgrp.getCOMTYPES().addAll(
						getTypesForCommodity(commdity.getCommodityId()));
				comgrp.getCOMMEASURES().addAll(getMeasures());
				comgrp.getCOMATTRIBS().addAll(
						getAttibutesForCommodity(commdity.getCommodityId()));
				comgrplist.add(comgrp);
			}

		}
		return comgrplist;
	}

	private List<COMVARIETY> getqualifiedVariety(String parentID) {
		log.info("Entered Query Qualified Varities");
		List<Commodity> commodities = commoditiesRepo
				.queryCommoditiesbyParentID(parentID);
		List<COMVARIETY> comgrplist = new ArrayList<COMVARIETY>();
		for (Commodity commdity : commodities) {
			if (commdity.getType().equalsIgnoreCase("V")) {
				Integer childCount = commoditiesRepo.countChilds(commdity
						.getCommodityId());
				log.info("Child Count" + childCount);
				COMVARIETY comgrp = new COMVARIETY();
				comgrp.setName(commdity.getName()
						+ (childCount > 0 ? "(" + childCount + ")" : ""));
				comgrp.setLevel(commdity.getType());
				comgrp.setParent(parentID);
				comgrp.setRank(commdity.getRank().toString());
				comgrp.setStatus(commdity.getRecordStatus());
				comgrp.setPicture(cmdPicRepo.querySinglePicbyCommId(
						commdity.getCommodityId()).getContent());
				comgrp.getCOMMEASURES().addAll(getMeasures());
				comgrp.getCOMATTRIBS().addAll(
						getAttibutesForCommodity(commdity.getCommodityId()));
				comgrplist.add(comgrp);
			}

		}
		return comgrplist;
	}

	private List<COMTYPES> getTypesForCommodity(String commodityId) {
		List<COMTYPES> listValues = new ArrayList<COMTYPES>();
		List<CommodityType> commoditytypes = cmdTyperepo
				.queryTypebyCommId(commodityId);
		for (CommodityType type : commoditytypes) {
			COMTYPES comtypes = new COMTYPES();
			comtypes.setType(String.valueOf(type.getqTypeId()));
			listValues.add(comtypes);
		}
		return listValues;

	}

	private List<COMATTRIBS> getAttibutesForCommodity(String commodityId) {
		List<COMATTRIBS> listValues = new ArrayList<COMATTRIBS>();
		List<CommodityAttribute> commoditytypes = cmdAttribRepo
				.queryAttributebyCommId(commodityId);
		for (CommodityAttribute type : commoditytypes) {
			COMATTRIBS comattribs = new COMATTRIBS();
			comattribs.setName(String.valueOf(type.getAttributeId()));
			comattribs.setSelected("Y");
			listValues.add(comattribs);
		}
		return listValues;

	}

	private List<COMMEASURES> getMeasures() {
		List<COMMEASURES> commeasures = new ArrayList<COMMEASURES>();
		commeasures.add(getMeasuresForPriceType("O"));
		commeasures.add(getMeasuresForPriceType("W"));
		return commeasures;
	}

	private COMMEASURES getMeasuresForPriceType(String priceType) {
		COMMEASURES commeasures = new COMMEASURES();
		commeasures.setPriceType(priceType);
		List<Measure> list = measurerepo.queryAllSystemMeasures();
		for (Measure measure : list) {
			COMMEASURESDTLS measureDtls = new COMMEASURESDTLS();
			measureDtls.setMeasureId(measure.getMeasureId());
			commeasures.getCOMMEASURESDTLS().add(measureDtls);
		}
		return commeasures;
	}

	@Transactional
	public CreateCommoditiesRes createCommodities(CreateCommoditiesReq req) {
		log.info("Inside CommoditiesService -> createCommodities");
		CreateCommoditiesRes createCommoditiesRes = new CreateCommoditiesRes();
		for (CRCOMMDTLS crcommdtls : req.getCRCOMMDTLS()) {
			if (!crcommdtls.getStatus().equals("D")) {
				saveCommodities(crcommdtls, req.getHeader().getUserId());
				log.info("Saved Commodities");
				saveCommoditiesVariety(crcommdtls.getCommodityID(),
						crcommdtls.getType(), crcommdtls.getCRCOMMATTDTLS());
				log.info("Saved Variety");
				saveCommoditiesTypes(crcommdtls.getQType());
				log.info("Saved Types");
				saveCommoditiesAlias(crcommdtls.getCommodityID(),
						crcommdtls.getCRALSDTLS());
				log.info("Saved Aliases");
				saveCommoditiesPic(crcommdtls.getPicture(),
						crcommdtls.getCommodityID(), req.getHeader()
								.getUserId());
				log.info("Saved Picture");
				saveCommoditiesAttribs(crcommdtls.getCRCOMMATTDTLS());
				log.info("Saved Attributes");
				saveCommodityMeasure("O", crcommdtls.getOffLorry(),
						crcommdtls.getCommodityAlias());
				saveCommodityMeasure("W", crcommdtls.getWholesale(),
						crcommdtls.getCommodityAlias());
				log.info("Saved pricetypes");
				Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
						serviceName, "CommodityService", "createCommodity",
						ERROR_CODE.CM_SC_002);
				createCommoditiesRes.setHeader(header);
			} else {
				deleteCommodities(crcommdtls.getCommodityID(), "D");
				Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
						serviceName, "CommodityService", "createCommodity",
						ERROR_CODE.ES_DL_001);
				createCommoditiesRes.setHeader(header);
			}
		}

		return createCommoditiesRes;
	}

	private boolean saveCommodities(CRCOMMDTLS cmdtls, String userId) {
		Commodity commodity = new Commodity();
		log.info(" CommodityID :: " + cmdtls.getCommodityID());
		commodity.setCommodityId(cmdtls.getCommodityID().toString());
		log.info("Commodity Name:: " + cmdtls.getName());
		commodity.setName(cmdtls.getName());
		log.info("Commodity Description:: " + cmdtls.getDescription());
		commodity.setDescription(cmdtls.getDescription());
		log.info("Global Location:: " + cmdtls.getGlobalLocation());
		commodity.setGlobal_location(cmdtls.getGlobalLocation());
		log.info("Commodity Type:: " + cmdtls.getType());
		commodity.setType(cmdtls.getType());
		log.info("Created date:: " + Utils.getCurrentDate());
		commodity.setCreatedTs(Utils.getCurrentDate());
		log.info("Created by:: " + userId);
		commodity.setCreatedBy(userId);
		;
		log.info("Commodity Rank:: " + cmdtls.getRank());
		commodity.setRank(cmdtls.getRank());
		log.info("Commodity AuthStat:: U");
		commodity.setAuthStat("U");
		log.info("Commodity RecordStatus:: A");
		commodity.setRecordStatus("A");
		commoditiesRepo.merge(commodity);
		return true;
	}

	private boolean saveCommoditiesAlias(String commodityId,
			List<CRALSDTLS> crAlsDtls) {
		try {
			if (crAlsDtls.size() > 0) {
				for (CRALSDTLS cralsdtls2 : crAlsDtls) {
					CommodityAlia commodityAlia = new CommodityAlia();
					commodityAlia.setCommodityId(commodityId);
					commodityAlia
							.setAliasId(cralsdtls2.getAliasID() == null ? commodityId
									: cralsdtls2.getAliasID());
					commodityAlia.setName(cralsdtls2.getAliasID());
					commoditiesAliasRepo.merge(commodityAlia);
				}
			}
		} catch (DomainException domainException) {
			return true;
		}
		return true;
	}

	private boolean saveCommoditiesVariety(String commodityId, String type,
			List<CRCOMMATTDTLS> varities) {

		for (CRCOMMATTDTLS varity : varities) {
			CommodityVariety commodityVariety = new CommodityVariety();
			commodityVariety.setCommodityId(commodityId);
			commodityVariety.setQTypeId(type);
			commodityVariety.setAttributeId(varity.getAttributeID());
			commodityVarityRepo.merge(commodityVariety);
		}

		return true;
	}

	private boolean saveCommoditiesPic(byte[] content, String commodityId,
			String userId) {
		CommodityPicture commodityPicture = new CommodityPicture();
		commodityPicture.setCommodityId(commodityId);
		commodityPicture.setCreatedBy(userId);
		commodityPicture.setCreatedTs(Utils.getCurrentDate());
		commodityPicture.setContent(Utils.encodeImage(content));
		cmdPicRepo.merge(commodityPicture);
		return true;
	}

	private boolean saveCommoditiesTypes(String type) {
		if (type != null) {
			Type type2 = new Type();
			type2.setTypeId(type);
			type2.setDescription(type);
			typeRepo.merge(type2);
		}
		return true;
	}

	private boolean saveCommoditiesAttribs(List<CRCOMMATTDTLS> attributes) {
		for (CRCOMMATTDTLS attribute : attributes) {
			Attribute entity = new Attribute();
			entity.setAttributeId(attribute.getAttributeID());
			entity.setAttDesc(attribute.getAttributeID());
			attribRepo.merge(entity);
		}
		return true;
	}

	private boolean saveCommodityMeasure(String priceType, String MeasureId,
			String commodiyId) {
		return true;

	}

	@Transactional
	public boolean deleteCommodities(String commodityId, String status) {
		log.info("Inside CommoditiesService -> deleteCommodities");
		Commodity commodity = commoditiesRepo.findOne(Commodity.class,
				commodityId);
		commodity.setRecordStatus("D");
		commoditiesRepo.save(commodity);
		return true;
	}
}
