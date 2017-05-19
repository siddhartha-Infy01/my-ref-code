package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.AttributesRepo;
import com.iexceed.esoko.domain.dao.CommVarietyAliaRepo;
import com.iexceed.esoko.domain.dao.CommodityAliaRepo;
import com.iexceed.esoko.domain.dao.CommodityAttributeRepo;
import com.iexceed.esoko.domain.dao.CommodityPictureRepo;
import com.iexceed.esoko.domain.dao.CommodityRepo;
import com.iexceed.esoko.domain.dao.CommodityTypeRepo;
import com.iexceed.esoko.domain.dao.CommodityVarietyRepo;
import com.iexceed.esoko.domain.dao.NetworkCommodityRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.TypesRepo;
import com.iexceed.esoko.domain.dao.UserCommodityRepo;
import com.iexceed.esoko.domain.entity.Attribute;
import com.iexceed.esoko.domain.entity.CommVarietyAlia;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.CommodityAlia;
import com.iexceed.esoko.domain.entity.CommodityAttribute;
import com.iexceed.esoko.domain.entity.CommodityPicture;
import com.iexceed.esoko.domain.entity.CommodityType;
import com.iexceed.esoko.domain.entity.CommodityVariety;
import com.iexceed.esoko.domain.entity.NetworkCommodity;
import com.iexceed.esoko.domain.entity.NetworkCommodityPK;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.Type;
import com.iexceed.esoko.domain.entity.UserCommodity;
import com.iexceed.esoko.domain.entity.UserCommodityPK;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.ALIAS;
import com.iexceed.esoko.jaxb.se.ALSDTLS;
import com.iexceed.esoko.jaxb.se.APPDTLS;
import com.iexceed.esoko.jaxb.se.ATTDTLS;
import com.iexceed.esoko.jaxb.se.ATTRIBUTES;
import com.iexceed.esoko.jaxb.se.ApproveCommoditesReq;
import com.iexceed.esoko.jaxb.se.ApproveCommoditesRes;
import com.iexceed.esoko.jaxb.se.CMDATTRIBS;
import com.iexceed.esoko.jaxb.se.CMDPICS;
import com.iexceed.esoko.jaxb.se.CMDTYPE;
import com.iexceed.esoko.jaxb.se.COMMDATTRIBUTE;
import com.iexceed.esoko.jaxb.se.COMMODTYPE;
import com.iexceed.esoko.jaxb.se.COMMODVARIETY;
import com.iexceed.esoko.jaxb.se.CRSELCOM;
import com.iexceed.esoko.jaxb.se.CurrentlySelectedCommodities;
import com.iexceed.esoko.jaxb.se.DLCOMMDTLS;
import com.iexceed.esoko.jaxb.se.DeleteCommoditiesReq;
import com.iexceed.esoko.jaxb.se.DeleteCommoditiesRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.MapCommoditiesReq;
import com.iexceed.esoko.jaxb.se.MapCommoditiesRes;
import com.iexceed.esoko.jaxb.se.QRCOMMDTLS;
import com.iexceed.esoko.jaxb.se.QRUNAUTHCOMMDDTLS;
import com.iexceed.esoko.jaxb.se.QueryAllUnAuthCommoditiesRes;
import com.iexceed.esoko.jaxb.se.QueryCommoditiesRes;
import com.iexceed.esoko.jaxb.se.QueryTypeandAttribsRes;
import com.iexceed.esoko.jaxb.se.QueryVarietiesRes;
import com.iexceed.esoko.jaxb.se.TYPDTLS;
import com.iexceed.esoko.jaxb.se.TYPE;
import com.iexceed.esoko.jaxb.se.VARALS;
import com.iexceed.esoko.jaxb.se.VARDTLS;
import com.iexceed.esoko.jaxb.se.VARIETY;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class CommoditiesService {
	public static Logger log = LoggerUtils.getLogger();

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
	CommodityAttributeRepo cmdAttrbRepo;
	@Autowired
	CommodityVarietyRepo commodityVarityRepo;
	@Autowired
	CommVarietyAliaRepo cmdVarAlsRepo;
	@Autowired
	TypesRepo typeRepo;
	@Autowired
	AttributesRepo attribRepo;
	@Autowired
	UserCommodityRepo userCommRepo;
	@Autowired
	NetworkCommodityRepo networkCommRepo;
	@Autowired
	SystemUserRepo sysUserRepo;

	Map<String, String> finalReslutmap;

	private final String serviceName = "CommodityService";

	public QueryCommoditiesRes queryCommodityities(String networkId,
			String userId, String widgetId, String userData, String commodityId) {
		QueryCommoditiesRes commoditiesRes = new QueryCommoditiesRes();
		finalReslutmap = new HashMap<String, String>();
		if (commodityId != null) {
			List<Commodity> commodity = commoditiesRepo
					.queryCommoditiesbyCommId(commodityId);
			for (Commodity com : commodity) {
				commoditiesRes.getQRCOMMDTLS().add(buildCommodityData(com));
			}
		}
		return commoditiesRes;
	}

	public QueryCommoditiesRes queryAllCommodities(String networkId,
			String userId, String widgetId, String userData) {
		log.info("Inside Commdities Service -> queryAllCommodities");
		log.info("networkId ->" + networkId);
		log.info("userId -> " + userId);
		log.info("widgetId ->" + widgetId);
		log.info("userData ->" + userData);

		QueryCommoditiesRes commoditiesRes = new QueryCommoditiesRes();
		QRCOMMDTLS master = new QRCOMMDTLS();

		if ("Y".equalsIgnoreCase(userData) && widgetId == null
				&& userId != null) {
			log.info("Building commodity map for user");
			finalReslutmap = commoditiesRepo.queryAllCommodities(userData,
					userId, null, networkId);
		} else if ("Y".equalsIgnoreCase(userData) && widgetId != null
				&& userId != null) {
			log.info("Building commodity map for widget");
			finalReslutmap = commoditiesRepo.queryAllCommodities(userData,
					userId, widgetId, networkId);
		} else if ("N".equalsIgnoreCase(userData) && networkId != null) {
			log.info("Building commodity map for network");
			finalReslutmap = commoditiesRepo.queryAllCommodities(userData,
					networkId, null, networkId);
		} else if ("A".equalsIgnoreCase(userData) && networkId != null
				&& userId != null) {
			log.info("Building commodity map for network");
			finalReslutmap = commoditiesRepo.queryAllCommodities(userData,
					userId, null, networkId);
		} else if ("N".equalsIgnoreCase(userData)
				&& userId.equalsIgnoreCase("")) {
			finalReslutmap = new HashMap<String, String>();
		}

		log.debug("Mapped Commodities -> " + finalReslutmap);

		List<Commodity> commodities = commoditiesRepo.queryAllRootCommodities();

		log.debug("No of  root commodities ->" + commodities.size());

		for (Commodity commodity : commodities) {

			log.debug("Root Commodity" + commodities.indexOf(commodity) + ":::"
					+ commodity.getCommodityId());
			if (!finalReslutmap.isEmpty()) {
				if (finalReslutmap.containsKey(commodity.getCommodityId())) {

					log.debug("Going to Build Commodity Hierarchy for Root"
							+ commodity.getCommodityId());

					Map<String, List<Commodity>> commodityMap = commoditiesRepo
							.buildCommodityHierarchy(commodity.getCommodityId());

					log.debug("Hierarchy:::" + commodityMap);

					master = buildAllCommoditiesdata(commodityMap, commodity);
					commoditiesRes.getQRCOMMDTLS().add(master);

				}
			} else {
				log.debug("Going to Build Commodity Hierarchy for Root"
						+ commodity.getCommodityId());

				Map<String, List<Commodity>> commodityMap = commoditiesRepo
						.buildCommodityHierarchy(commodity.getCommodityId());

				log.debug("Hierarchy:::" + commodityMap);

				master = buildAllCommoditiesdata(commodityMap, commodity);
				commoditiesRes.getQRCOMMDTLS().add(master);

			}

		}
		return commoditiesRes;

	}

	private QRCOMMDTLS buildAllCommoditiesdata(
			Map<String, List<Commodity>> commodityMap, Commodity rootCommodity) {
		QRCOMMDTLS master = buildCommodityData(rootCommodity);

		for (String key : commodityMap.keySet()) {
			List<QRCOMMDTLS> childList = new ArrayList<QRCOMMDTLS>();
			for (Commodity commodity : commodityMap.get(key)) {
				log.debug("Commodity -> " + commodity);
				for (CommodityVariety commodityVariety : commodityVarityRepo
						.queryVarietiesbyCommId(commodity.getCommodityId())) {
					QRCOMMDTLS qrcommdtls = buildCommodityData(commodity);
					qrcommdtls.setAttribute(commodityVariety.getAttributeId());
					qrcommdtls.setType(commodityVariety.getQTypeId());
					childList.add(qrcommdtls);
				}
			}
			addChild(master, childList);
		}
		return master;

	}

	private QRCOMMDTLS buildCommodityData(Commodity commodity) {
		QRCOMMDTLS qrcommdtls = new QRCOMMDTLS();
		qrcommdtls.setCommodityID(commodity.getCommodityId());
		qrcommdtls.setName(commodity.getName());
		qrcommdtls.setChildCount(Integer.toString(commoditiesRepo
				.countChilds(commodity.getCommodityId())));

		if (finalReslutmap.get(commodity.getCommodityId()) == null
				|| finalReslutmap.get(commodity.getCommodityId()) == "N") {
			qrcommdtls.setSelected("n");
		} else {
			qrcommdtls.setSelected("y");
		}
		qrcommdtls.setDescription(commodity.getDescription());
		qrcommdtls.setParentId(commodity.getParentId());
		qrcommdtls.getALSDTLS().addAll(
				queryCommodityAlias(commodity.getCommodityId()));
		qrcommdtls.getCMDPICS().addAll(
				queryCommodityPicture(commodity.getCommodityId()));

		return qrcommdtls;

	}

	private List<ALSDTLS> queryCommodityAlias(String commodityId) {
		List<CommodityAlia> qryCmdAlsEntity = null;
		qryCmdAlsEntity = (List<CommodityAlia>) commoditiesAliasRepo
				.queryCommoditiesaliabyCommId(commodityId);
		List<ALSDTLS> queryCommoditiesList = new ArrayList<ALSDTLS>();
		for (CommodityAlia qryCmdAlias : qryCmdAlsEntity) {
			ALSDTLS alsDtls = new ALSDTLS();
			alsDtls.setAliasID(String.valueOf(qryCmdAlias.getAliasId()));
			alsDtls.setName(qryCmdAlias.getName());
			queryCommoditiesList.add(alsDtls);
		}
		return queryCommoditiesList;
	}

	private List<CMDPICS> queryCommodityPicture(String commodityId) {
		List<CommodityPicture> qryCmdPicEntity = null;
		qryCmdPicEntity = (List<CommodityPicture>) cmdPicRepo
				.queryCommoditiesPicbyCommId(commodityId);
		List<CMDPICS> queryCommoditiesPicList = new ArrayList<CMDPICS>();
		for (CommodityPicture qrycmdPic : qryCmdPicEntity) {
			CMDPICS cmdPics = new CMDPICS();
			cmdPics.setCmdPic(qrycmdPic.getContent());
			queryCommoditiesPicList.add(cmdPics);
		}
		return queryCommoditiesPicList;
	}

	private List<CMDTYPE> queryCommodityType(String commodityId) {
		List<CommodityType> qryCmdTypeEntity = null;
		qryCmdTypeEntity = (List<CommodityType>) cmdTyperepo
				.queryTypebyCommId(commodityId);
		List<CMDTYPE> queryCommoditiesTyepList = new ArrayList<CMDTYPE>();
		for (CommodityType qryCmdType : qryCmdTypeEntity) {
			CMDTYPE cmdType = new CMDTYPE();
			cmdType.setQTypeId(String.valueOf(qryCmdType.getqTypeId()));
			cmdType.setName(qryCmdType.getName());
			cmdType.setType(qryCmdType.getType());
			queryCommoditiesTyepList.add(cmdType);
		}
		return queryCommoditiesTyepList;
	}

	private List<CMDATTRIBS> queryCommodityAttribs(String commodityId) {
		List<CommodityAttribute> qryCmdAttrib = null;
		qryCmdAttrib = (List<CommodityAttribute>) cmdAttrbRepo
				.queryAttributebyCommId(commodityId);
		List<CMDATTRIBS> queryCommoditiesAttribList = new ArrayList<CMDATTRIBS>();
		for (CommodityAttribute qryCmdAttr : qryCmdAttrib) {
			CMDATTRIBS cmdAttrib = new CMDATTRIBS();
			cmdAttrib.setAttribtId(String.valueOf(qryCmdAttr.getAttributeId()));
			cmdAttrib.setName(qryCmdAttr.getAttName());
			queryCommoditiesAttribList.add(cmdAttrib);
		}
		return queryCommoditiesAttribList;
	}

	private QRCOMMDTLS addChild(QRCOMMDTLS qrcommdtls, List<QRCOMMDTLS> newList) {
		log.debug("Size Of Response -> " + qrcommdtls.getQRCOMMDTLS().size());
		if (qrcommdtls.getQRCOMMDTLS().size() == 0) {
			log.debug("Adding the First Element List-> ");
			qrcommdtls.getQRCOMMDTLS().addAll(newList);
		} else {
			log.debug("Adding Next Element List-> ");
			int size = qrcommdtls.getQRCOMMDTLS().size();
			List<QRCOMMDTLS> qrcommdtls2 = qrcommdtls.getQRCOMMDTLS();
			while (size > 0) {
				log.debug("It has child elements,So lets browse");
				for (QRCOMMDTLS qrcommdtls3 : qrcommdtls2) {
					log.debug("Commodity::::" + qrcommdtls3.getCommodityID());
					size = qrcommdtls3.getQRCOMMDTLS().size();
					qrcommdtls2 = qrcommdtls3.getQRCOMMDTLS();
					log.debug("Size of Child" + size);
					for (QRCOMMDTLS qrcommdtls4 : newList) {
						log.debug("Commodity to be added::"
								+ qrcommdtls4.getCommodityID() + "To Parent::"
								+ qrcommdtls3.getCommodityID());
						if (qrcommdtls4.getParentId().equals(
								qrcommdtls3.getCommodityID())) {
							log.debug("Adding" + qrcommdtls4.getCommodityID()
									+ "--->TO--->"
									+ qrcommdtls3.getCommodityID());
							qrcommdtls3.getQRCOMMDTLS().add(qrcommdtls4);
						}

					}
				}
			}
		}

		return qrcommdtls;
	}

	public QueryTypeandAttribsRes queryTypeandAttribs() {
		QueryTypeandAttribsRes attribsRes = new QueryTypeandAttribsRes();
		List<Type> allTypes = typeRepo.queryAllTypes();
		List<Attribute> allAttributes = attribRepo.queryAllAttributes();
		for (Type type : allTypes) {
			TYPDTLS typedtls = new TYPDTLS();
			typedtls.setDesc(type.getDescription());
			typedtls.setTypeid(type.getTypeId());
			attribsRes.getTYPDTLS().add(typedtls);
		}

		for (Attribute attributes : allAttributes) {
			ATTDTLS attdtls = new ATTDTLS();
			attdtls.setAttrDesc(attributes.getAttDesc());
			attdtls.setAttrId(attributes.getAttributeId());
			attribsRes.getATTDTLS().add(attdtls);
		}
		return attribsRes;
	}

	public QueryVarietiesRes queryVarieties(String commodityID, int qTypeId,
			int attribId) {
		log.info("Inside Commdities Service -> queryVarieties");
		QueryVarietiesRes varietiesRes = new QueryVarietiesRes();
		List<CommodityVariety> qryResponseEntity = (List<CommodityVariety>) commodityVarityRepo
				.queryVarietiesbyCommId(commodityID);
		List<CommVarietyAlia> qryCmdVarAlsEntity = (List<CommVarietyAlia>) cmdVarAlsRepo
				.queryCommvaritiesaliabyVarId(commodityID);
		List qryList = new ArrayList();
		for (CommodityVariety qryVriteisRes : qryResponseEntity) {
			VARDTLS varDtls = new VARDTLS();
			varDtls.setAttribId(qryVriteisRes.getCommodityId());
			varDtls.setQTypeId(qryVriteisRes.getQTypeId());
			varDtls.setAttribId(qryVriteisRes.getAttributeId());
			qryList.add(varDtls);
		}
		varietiesRes.getVARDTLS().addAll(qryList);
		for (CommVarietyAlia qryVriteisAlsRes : qryCmdVarAlsEntity) {
			VARALS varAls = new VARALS();
			varAls.setAliasID(qryCmdVarAlsEntity.get(0).getAttibuteId());
			varAls.setName(qryCmdVarAlsEntity.get(0).getName());
			qryList.add(varAls);
		}
		varietiesRes.getVARALS().addAll(qryList);
		return varietiesRes;
	}

	@Transactional
	public DeleteCommoditiesRes deleteCommodities(DeleteCommoditiesReq req) {
		log.info("Inside CommoditiesService -> deleteCommodities");
		Header header = null;
		DeleteCommoditiesRes deleteCommodtsRes = new DeleteCommoditiesRes();
		DLCOMMDTLS cmdtls = new DLCOMMDTLS();
		cmdtls = (DLCOMMDTLS) req.getDLCOMMDTLS().get(0);
		log.info(" CommodityID :: " + cmdtls.getCommodityID());
		Commodity commodity = commoditiesRepo.findOne(Commodity.class, cmdtls
				.getCommodityID().toString());
		commodity.setRecordStatus("D");
		commoditiesRepo.save(commodity);
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"deleteCommodities", "", ERROR_CODE.CM_DEL);
		deleteCommodtsRes.setHeader(req.getHeader());
		deleteCommodtsRes.setHeader(header);
		return deleteCommodtsRes;
	}

	@Transactional
	public ApproveCommoditesRes approveCommodites(ApproveCommoditesReq req) {
		Header header = null;
		ApproveCommoditesRes approveCommditiesRes = new ApproveCommoditesRes();
		Commodity commodity = new Commodity();
		APPDTLS appDtls = new APPDTLS();
		appDtls = (APPDTLS) req.getAPPDTLS();
		List<ALIAS> aliases = appDtls.getALIAS();
		List<ATTRIBUTES> attributes = appDtls.getATTRIBUTES();
		List<TYPE> types = appDtls.getTYPE();
		List<VARIETY> varieties = appDtls.getVARIETY();
		commodity = commoditiesRepo.findOne(Commodity.class,
				appDtls.getCommodityName());
		commodity.setCommodityId(appDtls.getCommodityName());
		log.info(" CommodityID :: " + commodity.getCommodityId());
		commodity.setAuthBy(req.getHeader().getUserId());
		log.info(" Approved by :: " + req.getHeader().getUserId());
		commodity.setAuthTs(Utils.getCurrentDate());
		log.info(" Auth Timestamp :: " + Utils.getCurrentDate());
		commodity.setAuthStat("A");
		log.info(" Auth status :: " + "A");
		commodity.setGlobal_location(appDtls.getMajorMarket());
		log.info(" Global Location :: " + appDtls.getMajorMarket());
		commodity.setDescription(appDtls.getDescription());
		log.info(" Description :: " + appDtls.getDescription());
		CommodityPicture commodityPic = new CommodityPicture();
		commodityPic = cmdPicRepo.querySinglePicbyCommId(appDtls
				.getCommodityName());
		commodityPic.setContent(appDtls.getPicture());
		commodityPic.setCreatedBy(req.getHeader().getUserId());
		log.info(" Approved by :: " + req.getHeader().getUserId());
		commodityPic.setCreatedTs(Utils.getCurrentDate());
		log.info(" Auth Timestamp :: " + Utils.getCurrentDate());
		for (ALIAS alias : aliases) {
			CommodityAlia commdAlias = new CommodityAlia();
			commdAlias.setAliasId(appDtls.getCommodityName());
			commdAlias.setCommodityId(appDtls.getCommodityName());
			commdAlias.setName(alias.getAliasName());
			commoditiesAliasRepo.merge(commdAlias);
		}
		for (ATTRIBUTES attribute : attributes) {
			CommodityAttribute commdAttribute = new CommodityAttribute();
			commdAttribute.setCommodityId(appDtls.getCommodityName());
			commdAttribute.setAttName(attribute.getAttributeName());
			cmdAttrbRepo.merge(commdAttribute);
		}
		for (TYPE type : types) {
			CommodityType commodityType = new CommodityType();
			commodityType.setqTypeId(appDtls.getCommodityName());
			commodityType.setCommodityId(appDtls.getCommodityName());
			commodityType.setName(type.getTypeName());
			commodityType.setCreatedBy(req.getHeader().getUserId());
			commodityType.setCreatedTs(Utils.getCurrentDate());
			cmdTyperepo.merge(commodityType);
		}
		for (VARIETY variety : varieties) {
			Commodity commod = new Commodity();
			commod.setCommodityId(variety.getVarietyName());
			commod.setParentId(appDtls.getCommodityName());
			commod.setType("C");
			commod.setGlobal_location(appDtls.getMajorMarket());
			commod.setDescription(appDtls.getDescription());
			commod.setCreatedBy(req.getHeader().getUserId());
			commod.setCreatedTs(Utils.getCurrentDate());
			commod.setAuthStat("U");
			commod.setRecordStatus("U");
			commoditiesRepo.merge(commod);
		}

		commoditiesRepo.merge(commodity);
		log.info("Updated Commodities");
		cmdPicRepo.merge(commodityPic);
		log.info("Updated Commodities picture");
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"approveCommodites", "", ERROR_CODE.CM_APPRVL);
		approveCommditiesRes.setHeader(header);
		return approveCommditiesRes;
	}

	@Transactional
	public MapCommoditiesRes mapCommodites(MapCommoditiesReq req) {

		MapCommoditiesRes mapCommditiesRes = new MapCommoditiesRes();
		Header header = null;
		System.out.println("Commodities:::"
				+ req.getMPCOMMDTLS().getCommodityID());
		String[] commodityIds = req.getMPCOMMDTLS().getCommodityID().split(",");
		if (req.getMPCOMMDTLS().getMapType().equalsIgnoreCase("U")) {
			userCommRepo.deleteUserCommodity(req.getMPCOMMDTLS().getMapValue(),
					req.getMPCOMMDTLS().getNetworkId());
			for (String commodityId : commodityIds) {
				UserCommodity userCommodity = new UserCommodity();
				UserCommodityPK userCommodityPK = new UserCommodityPK();
				userCommodityPK.setCommodityId(commodityId);
				userCommodityPK.setUserId(req.getMPCOMMDTLS().getMapValue());
				userCommodityPK
						.setNetworkId(req.getMPCOMMDTLS().getNetworkId());
				userCommodity.setId(userCommodityPK);
				userCommodity.setName(req.getMPCOMMDTLS().getName());
				userCommodity.setCreatedBy(req.getHeader().getUserId());
				userCommodity.setCreatedTs(Utils.getCurrentDate());
				userCommodity.setModifiedBy(req.getHeader().getUserId());
				userCommodity.setModifiedTs(Utils.getCurrentDate());
				userCommRepo.save(userCommodity);
			}
		} else {
			networkCommRepo.deleteNetworkCommodity(req.getMPCOMMDTLS()
					.getMapValue());
			for (String commodityId : commodityIds) {
				NetworkCommodity networkCommodity = new NetworkCommodity();
				NetworkCommodityPK networkCommodityPK = new NetworkCommodityPK();
				networkCommodityPK.setCommodityId(commodityId);
				networkCommodityPK.setNetworkId(req.getMPCOMMDTLS()
						.getMapValue());
				networkCommodity.setId(networkCommodityPK);
				networkCommodity.setName(req.getMPCOMMDTLS().getName());
				networkCommodity.setDescription(req.getMPCOMMDTLS()
						.getDescription());
				networkCommodity.setCreatedBy(req.getHeader().getUserId());
				networkCommodity.setCreatedTs(Utils.getCurrentDate());
				networkCommodity.setModifiedBy(req.getHeader().getUserId());
				networkCommodity.setModifiedTs(Utils.getCurrentDate());
				networkCommRepo.save(networkCommodity);
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Map Commodities",
				"Selected Commodities are successfully saved",
				ERROR_CODE.CM_SC_001);
		mapCommditiesRes.setHeader(header);
		return mapCommditiesRes;
	}

	public CurrentlySelectedCommodities currentlySelectedCommodities(
			String userData, String Id, String networkId) {
		CurrentlySelectedCommodities currentlySelectedCommodities = new CurrentlySelectedCommodities();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		CRSELCOM crselcom = new CRSELCOM();
		if ("Y".equalsIgnoreCase(userData)) {
			List<UserCommodity> commodities = userCommRepo.queryUserCommodity(
					Id, networkId);

			for (UserCommodity commodity : commodities) {
				crselcom.getCommodityID().add(
						commodity.getId().getCommodityId());

			}
		} else {
			List<NetworkCommodity> commodities = networkCommRepo
					.queryNetworkCommodities(Id);
			for (NetworkCommodity commodity : commodities) {
				crselcom.getCommodityID().add(
						commodity.getId().getCommodityId());
			}
		}
		currentlySelectedCommodities.setCRSELCOM(crselcom);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"CommoditiesService", "currentlySelectedCommodities", "",
				errorCode);
		currentlySelectedCommodities.setHeader(header);
		return currentlySelectedCommodities;
	}

	public QueryAllUnAuthCommoditiesRes findAllUnAuthCommodities() {
		log.info("Inside CommoditiesService -> findAllUnAuthCommodities");
		QueryAllUnAuthCommoditiesRes res = new QueryAllUnAuthCommoditiesRes();
		List<Commodity> unAuthCommodityList = commoditiesRepo
				.findAllUnAuthCommodities();

		List<QRUNAUTHCOMMDDTLS> qrUnAuthCommDtlslIst = new ArrayList<QRUNAUTHCOMMDDTLS>();
		if (unAuthCommodityList != null) {
			for (Commodity commodity : unAuthCommodityList) {
				CommodityPicture CommdPicture = cmdPicRepo
						.querySinglePicbyCommId(commodity.getCommodityId());
				List<CommodityType> commdTypeList = cmdTyperepo
						.queryTypebyCommId(commodity.getCommodityId());
				List<CommodityAttribute> commdAttrList = cmdAttrbRepo
						.queryAttributebyCommId(commodity.getCommodityId());
				List<CommodityVariety> commdVarList = commodityVarityRepo
						.queryVarietiesbyCommId(commodity.getCommodityId());
				QRUNAUTHCOMMDDTLS qrUnAuthCommDtls = new QRUNAUTHCOMMDDTLS();
				qrUnAuthCommDtls.setCommodity(commodity.getCommodityId());
				qrUnAuthCommDtls.setLocalName(commodity.getName());
				log.debug("commodity id:" + commodity.getCommodityId());
				if (commodity.getCreatedTs() != null) {
					qrUnAuthCommDtls.setDate(commodity.getCreatedTs()
							.toString());
					log.debug("created date:"
							+ commodity.getCreatedTs().toString());
				}
				if (commodity.getCreatedBy() != null) {
					SystemUser sysUser = sysUserRepo.findOne(SystemUser.class,
							commodity.getCreatedBy());
					StringBuffer name = null;
					if (sysUser != null) {
						if (sysUser.getLastName() != null) {
							name = new StringBuffer(sysUser.getFirstName()
									+ " " + sysUser.getLastName());
						} else {
							name = new StringBuffer(sysUser.getFirstName());
						}
						if (sysUser.getCountry() != null) {
							qrUnAuthCommDtls.setCountry(sysUser.getCountry());
						}
					}

					qrUnAuthCommDtls.setRequestor(name.toString());
					log.debug("Requestor:" + name);
				}
				qrUnAuthCommDtls.setMarket(commodity.getGlobal_location());
				log.debug("Market:" + commodity.getGlobal_location());
				qrUnAuthCommDtls.setDescription(commodity.getDescription());
				log.debug("Description:" + commodity.getDescription());
				if (CommdPicture != null) {
					qrUnAuthCommDtls.setPicture(CommdPicture.getContent());
				}
				if (commdTypeList != null && commdTypeList.size() > 0) {
					for (CommodityType cmdType : commdTypeList) {
						COMMODTYPE type = new COMMODTYPE();
						type.setTypeName(cmdType.getName());
						qrUnAuthCommDtls.getCOMMODTYPE().add(type);
					}
				}
				if (commdAttrList != null && commdAttrList.size() > 0) {
					for (CommodityAttribute cmdAttr : commdAttrList) {
						COMMDATTRIBUTE attribute = new COMMDATTRIBUTE();
						attribute.setAttributeName(cmdAttr.getAttName());
						qrUnAuthCommDtls.getCOMMDATTRIBUTE().add(attribute);
					}
				}
				if (commdVarList != null && commdVarList.size() > 0) {
					for (CommodityVariety cmdVar : commdVarList) {
						COMMODVARIETY variety = new COMMODVARIETY();
						StringBuffer varName = new StringBuffer(
								cmdVar.getCommodityId());
						if (cmdVar.getQTypeId() != null) {
							varName.append(" " + cmdVar.getQTypeId());
						}
						if (cmdVar.getAttributeId() != null) {
							varName.append(" " + cmdVar.getAttributeId());
						}
						variety.setVarietyName(varName.toString());
						qrUnAuthCommDtls.getCOMMODVARIETY().add(variety);
					}
				}
				qrUnAuthCommDtlslIst.add(qrUnAuthCommDtls);
			}

			for (QRUNAUTHCOMMDDTLS qrUnAuthCommDtl : qrUnAuthCommDtlslIst) {
				res.getQRUNAUTHCOMMDDTLS().add(qrUnAuthCommDtl);
			}
		}

		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory
				.getHeader(HeaderType.SE, "CommoditiesService",
						"findAllUnAuthCommodities", "", errorCode);
		res.setHeader(header);
		return res;
	}

}
