package com.iexceed.esoko.ss.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SmppRouteRepo;
import com.iexceed.esoko.domain.dao3.GatewayDetailRepo;
import com.iexceed.esoko.domain.dao3.GatewayRepo;
import com.iexceed.esoko.domain.dao3.KannelDataRepo;
import com.iexceed.esoko.domain.dao3.OperatorRepo;
import com.iexceed.esoko.domain.dao3.OperatorTemplateRepo;
import com.iexceed.esoko.domain.dao3.SenderDetailRepo;
import com.iexceed.esoko.domain.dao3.UploadRateReportRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity3.Gateway;
import com.iexceed.esoko.domain.entity3.GatewayDetail;
import com.iexceed.esoko.domain.entity3.GatewayPK;
import com.iexceed.esoko.domain.entity3.KannelData;
import com.iexceed.esoko.domain.entity3.Operator;
import com.iexceed.esoko.domain.entity3.OperatorTemplate;
import com.iexceed.esoko.domain.entity3.SenderDetail;
import com.iexceed.esoko.domain.entity3.SenderDetailPK;
import com.iexceed.esoko.domain.entity3.SmppRoute;
import com.iexceed.esoko.domain.entity3.UploadRateReport;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.SystemEnums;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ss.CNTOPERATORDTLS;
import com.iexceed.esoko.jaxb.ss.CNTRYOPERATORS;
import com.iexceed.esoko.jaxb.ss.CRGATEWAY;
import com.iexceed.esoko.jaxb.ss.CROPERATORS;
import com.iexceed.esoko.jaxb.ss.CRSNDRID;
import com.iexceed.esoko.jaxb.ss.CreateGatewayReq;
import com.iexceed.esoko.jaxb.ss.CreateGatewayRes;
import com.iexceed.esoko.jaxb.ss.CreateOperatorReq;
import com.iexceed.esoko.jaxb.ss.CreateOperatorRes;
import com.iexceed.esoko.jaxb.ss.CreateSenderId;
import com.iexceed.esoko.jaxb.ss.CreateSenderIdRes;
import com.iexceed.esoko.jaxb.ss.DLSNDID;
import com.iexceed.esoko.jaxb.ss.DeleteOperatorReq;
import com.iexceed.esoko.jaxb.ss.DeleteOperatorRes;
import com.iexceed.esoko.jaxb.ss.DeleteSenderId;
import com.iexceed.esoko.jaxb.ss.DeleteSenderIdRes;
import com.iexceed.esoko.jaxb.ss.EDROUTEDTL;
import com.iexceed.esoko.jaxb.ss.EditOperatorReq;
import com.iexceed.esoko.jaxb.ss.EditOperatorRes;
import com.iexceed.esoko.jaxb.ss.GATEWAYDTLS;
import com.iexceed.esoko.jaxb.ss.GATEWAYS;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.OPERATORDTLS;
import com.iexceed.esoko.jaxb.ss.OPERATORS;
import com.iexceed.esoko.jaxb.ss.OPRTRDTLS;
import com.iexceed.esoko.jaxb.ss.QueryAllGatewaysRes;
import com.iexceed.esoko.jaxb.ss.QueryAllSmppRes;
import com.iexceed.esoko.jaxb.ss.QueryCountryDtlsRes;
import com.iexceed.esoko.jaxb.ss.QueryGatewayDtlsRes;
import com.iexceed.esoko.jaxb.ss.QueryOperatorByLocRes;
import com.iexceed.esoko.jaxb.ss.QuerySenderIdRes;
import com.iexceed.esoko.jaxb.ss.RATEDETAILS;
import com.iexceed.esoko.jaxb.ss.ROUTEDTL;
import com.iexceed.esoko.jaxb.ss.SENDERIDS;
import com.iexceed.esoko.jaxb.ss.SMPP;
import com.iexceed.esoko.jaxb.ss.SWROUTEDTL;
import com.iexceed.esoko.jaxb.ss.SwitchOperatorReq;
import com.iexceed.esoko.jaxb.ss.SwitchOperatorRes;
import com.iexceed.esoko.jaxb.ss.UPLOADRATES;
import com.iexceed.esoko.jaxb.ss.UploadRatesReq;
import com.iexceed.esoko.jaxb.ss.UploadRatesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class GatewayService {
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private OperatorRepo operatorRepo;
	@Autowired
	private OperatorTemplateRepo opTempRepo;
	@Autowired
	private EsokoAppRepo appRepo;
	@Autowired
	private SmppRouteRepo routeRepo;
	@Autowired
	private GatewayRepo gatewayRepo;
	@Autowired
	private GatewayDetailRepo gatewayDtlRepo;
	@Autowired
	private CurrencyRepo currencyRepo;
	@Autowired
	private KannelDataRepo kannelRepo;
	@Autowired
	private SenderDetailRepo sndrRepo;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private UploadRateReportRepo reportRepo;

	public QueryGatewayDtlsRes queryGatewayDetails() {
		log.info("Inside GatewayService -> queryGatewayDetails");
		QueryGatewayDtlsRes res = new QueryGatewayDtlsRes();
		Enum<ERROR_CODE> errorCode = null;
		List<Map<String, String>> gateways = gatewayRepo.getAllGateways();
		if (gateways.size() != 0) {
			List<GATEWAYDTLS> gatewayDtls = new ArrayList<GATEWAYDTLS>();
			String prevGatewayName = null;
			Map<String, Integer> gatewayIndex = new HashMap<String, Integer>();
			int gtwyIndex = 0;
			int cntryIndex = 0;
			for (Map<String, String> map1 : gateways) {
				String gatewayName = map1.get("gateway");
				String countryName = map1.get("country");
				if ((prevGatewayName == null)
						|| (!prevGatewayName.equals(gatewayName))) {
					if ((prevGatewayName != null)
							&& (!prevGatewayName.equals(gatewayName))) {
						cntryIndex = 0;
					}
					GATEWAYDTLS gtwyDtl = new GATEWAYDTLS();
					gtwyDtl.setGatewayName(gatewayName);
					if (!gatewayIndex.containsKey("gatewayName")) {
						gatewayIndex.put(gatewayName, gtwyIndex);
						gtwyIndex++;
					}
					if (!gatewayIndex.containsKey(gatewayName + countryName)) {
						gatewayIndex.put(gatewayName + countryName, cntryIndex);
						cntryIndex++;
					}
					List<OPERATORDTLS> operatorDtls = new ArrayList<OPERATORDTLS>();
					OPERATORDTLS oprDtl = new OPERATORDTLS();
					oprDtl.setCountryName(countryName);
					operatorDtls.add(oprDtl);
					List<OPERATORS> operators = new ArrayList<OPERATORS>();
					addOperator(map1, operators);
					oprDtl.getOPERATORS().addAll(operators);
					gtwyDtl.getOPERATORDTLS().addAll(operatorDtls);
					gatewayDtls.add(gtwyDtl);
				} else {
					GATEWAYDTLS gtwyDtl = gatewayDtls.get(gatewayIndex
							.get(gatewayName));
					if (gatewayIndex.containsKey(gatewayName + countryName)) {
						OPERATORDTLS oprDtl = gtwyDtl.getOPERATORDTLS().get(
								gatewayIndex.get(gatewayName + countryName));
						List<OPERATORS> operators = oprDtl.getOPERATORS();
						addOperator(map1, operators);
					} else {
						List<OPERATORDTLS> operatorDtls = new ArrayList<OPERATORDTLS>();
						OPERATORDTLS oprDtl = new OPERATORDTLS();
						oprDtl.setCountryName(countryName);
						operatorDtls.add(oprDtl);
						List<OPERATORS> operators = new ArrayList<OPERATORS>();
						addOperator(map1, operators);
						oprDtl.getOPERATORS().addAll(operators);
						gtwyDtl.getOPERATORDTLS().addAll(operatorDtls);
						gatewayIndex.put(gatewayName + countryName, cntryIndex);
						cntryIndex++;
					}
				}
				prevGatewayName = gatewayName;
			}
			if (gatewayDtls.size() != 0) {
				for (GATEWAYDTLS gtwDtls : gatewayDtls) {
					int totalOperators = 0;
					List<OPERATORDTLS> tmpList = gtwDtls.getOPERATORDTLS();
					gtwDtls.setNuOfCountries(tmpList.size() + " Countries");
					for (OPERATORDTLS oprDtl : tmpList) {
						List<OPERATORS> oprs = oprDtl.getOPERATORS();
						oprDtl.setNuOfOperators(oprs.size()
								+ " Mobile Operators");
						totalOperators += oprs.size();
					}
					gtwDtls.setNuOfOperators(totalOperators
							+ " Mobile Operators");
				}
			}
			res.getGATEWAYDTLS().addAll(gatewayDtls);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.error("No result found");
		}

		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "Query Gateway details", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryCountryDtlsRes queryCountryDetails() {
		log.info("Inside GatewayService -> queryCountryDetails");
		QueryCountryDtlsRes res = new QueryCountryDtlsRes();
		Enum<ERROR_CODE> errorCode = null;
		List<Map<String, String>> countries = gatewayRepo.getAllCountries();
		if (countries.size() == 0) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.error("No record found");
		} else {
			List<CNTOPERATORDTLS> operatorDtls = new ArrayList<CNTOPERATORDTLS>();
			Map<String, Integer> countryIndex = new HashMap<String, Integer>();
			int index = 0;
			String prevCountry = null;
			for (Map<String, String> map1 : countries) {
				String countryName = map1.get("country");
				if ((prevCountry == null) || (!prevCountry.equals(countryName))) {
					CNTOPERATORDTLS oprtrDtl = new CNTOPERATORDTLS();
					oprtrDtl.setCountryName(countryName);
					List<CNTRYOPERATORS> operators = new ArrayList<CNTRYOPERATORS>();
					addCountryOperator(map1, operators);
					oprtrDtl.getCNTRYOPERATORS().addAll(operators);
					operatorDtls.add(oprtrDtl);
					countryIndex.put(countryName, index);
					index++;
				} else {
					if (countryIndex.containsKey(countryName)) {
						CNTOPERATORDTLS oprtrDtl = operatorDtls
								.get(countryIndex.get(countryName));
						List<CNTRYOPERATORS> operators = oprtrDtl
								.getCNTRYOPERATORS();
						addCountryOperator(map1, operators);
					}
				}
				prevCountry = countryName;
			}
			if (operatorDtls.size() != 0) {
				for (CNTOPERATORDTLS details : operatorDtls) {
					List<CNTRYOPERATORS> tmpList = details.getCNTRYOPERATORS();
					details.setNuOfOperators(tmpList.size()
							+ " Mobile Operators");
				}
			}
			res.getCNTOPERATORDTLS().addAll(operatorDtls);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "Query Country details", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryAllGatewaysRes queryAllGateways() {
		log.info("Inside GatewayService -> queryAllGateways");
		QueryAllGatewaysRes res = new QueryAllGatewaysRes();
		List<GatewayDetail> gatewayLst = gatewayDtlRepo.queryAllDetails();
		Enum<ERROR_CODE> errorCode = null;
		if (gatewayLst.size() == 0) {
			errorCode = ERROR_CODE.ES_NR_001;
		} else {
			List<GATEWAYS> gateways = new ArrayList<GATEWAYS>();
			for (GatewayDetail gateway : gatewayLst) {
				GATEWAYS entity = new GATEWAYS();
				entity.setGatewayId(gateway.getGatewayId());
				entity.setGatewayName(gateway.getGatewayName());
				gateways.add(entity);
			}
			errorCode = ERROR_CODE.ES_SC_001;
			res.getGATEWAYS().addAll(gateways);
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "queryAllGateways", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CreateGatewayRes createGateway(CreateGatewayReq req) {
		log.info("Inside GatewayService -> createGateway");
		CreateGatewayRes res = new CreateGatewayRes();
		Enum<ERROR_CODE> errorCode = null;
		CRGATEWAY gateway = req.getCRGATEWAY();
		GatewayDetail gtwyDtl = gatewayDtlRepo.getGatewayDetail(gateway
				.getGatewayName());
		if (gtwyDtl == null) {
			try {
				GatewayDetail entity = new GatewayDetail();
				entity.setGatewayId(gateway.getGatewayName());
				entity.setGatewayName(gateway.getGatewayName());
				gatewayDtlRepo.save(entity);
				errorCode = ERROR_CODE.GW_CR_001;
			} catch (DomainException e) {
				log.error(Utils.getStackTrace(e));
				errorCode = e.getCode();
			}
		} else {
			errorCode = ERROR_CODE.GW_CR_002;
			log.error("Record already exists");
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "createGateway", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private void addOperator(Map<String, String> map1, List<OPERATORS> operators) {
		OPERATORS operator = new OPERATORS();
		operator.setOperatorName(map1.get("operator"));
		operator.setChannel(map1.get("channel"));
		float cost = Float.valueOf(map1.get("cost"));
		float margin = Float.valueOf(map1.get("margin"));
		float price = cost + margin;
		operator.setCost(cost);
		Currency currency = currencyRepo.findByCcyCode(map1.get("currency"));
		if (currency != null) {
			operator.setCurrency(currency.getSymbol());
			operator.setCurrencyName(currency.getName());
		}
		operator.setDirection(map1.get("direction"));
		operator.setExceptions(map1.get("exception"));
		if(map1.get("exception").equalsIgnoreCase("None")){
			operator.setExceptionName("None");
		}else{
			Network nwk = nwkRepo.findOne(Network.class, map1.get("exception"));
			operator.setExceptionName(nwk.getName());
		}
		operator.setMargin(margin);
		operator.setMcc(map1.get("mcc"));
		operator.setMnc(map1.get("mnc"));
		operator.setPrice(price);
		operator.setRouteId(map1.get("routeId"));
		operators.add(operator);
	}

	private void addCountryOperator(Map<String, String> map1,
			List<CNTRYOPERATORS> operators) {
		CNTRYOPERATORS operator = new CNTRYOPERATORS();
		operator.setOperatorName(map1.get("operator"));
		operator.setGatewayName(map1.get("gateway"));
		operator.setChannel(map1.get("channel"));
		float cost = Float.valueOf(map1.get("cost"));
		float margin = Float.valueOf(map1.get("margin"));
		float price = cost + margin;
		operator.setCost(cost);
		Currency currency = currencyRepo.findByCcyCode(map1.get("currency"));
		if (currency != null) {
			operator.setCurrency(currency.getSymbol());
			operator.setCurrencyName(currency.getName());
		}
		operator.setDirection(map1.get("direction"));
		operator.setExceptions(map1.get("exception"));
		if(map1.get("exception").equalsIgnoreCase("None")){
			operator.setExceptionName("None");
		}else{
			Network nwk = nwkRepo.findOne(Network.class, map1.get("exception"));
			operator.setExceptionName(nwk.getName());
		}		
		operator.setMargin(margin);
		operator.setMcc(map1.get("mcc"));
		operator.setMnc(map1.get("mnc"));
		operator.setPrice(price);
		operator.setRouteId(map1.get("routeId"));
		operators.add(operator);
	}

	@Transactional
	public CreateOperatorRes createOperator(CreateOperatorReq req) {
		log.info("Inside GatewayService -> createOperator");
		CreateOperatorRes res = new CreateOperatorRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		CROPERATORS crOperator = req.getCROPERATORS();
		String operatorId = crOperator.getMcc() + crOperator.getMnc();
		List<OperatorTemplate> template = opTempRepo.getTemplate(operatorId);
		SmppRoute exstingRoute = routeRepo.queryRoute(operatorId,
				crOperator.getGatewayId1(), crOperator.getNetworkId(),
				"Kannel", "MT");
		if (template.size() == 0) {
			errorCode = ERROR_CODE.OP_CR_001;
			log.error("MNC/MCC does not exists");
		} else if (exstingRoute != null) {
			errorCode = ERROR_CODE.OP_CR_007;
			log.error("Operator already exists");
		} else {
			try {
				this.addOperator(req.getHeader().getUserId(),
						crOperator.getGatewayId1(), crOperator.getGatewayId2(),
						crOperator.getOperatorCountry(), crOperator.getMcc(),
						crOperator.getMnc(), crOperator.getOperatorName(),
						operatorId);
				this.addSMPPGateway(crOperator.getCost(),
						crOperator.getCurrencyId(),
						req.getHeader().getUserId(), "MT",
						crOperator.getGatewayId1(), crOperator.getNetworkId(),
						operatorId, "Kannel", crOperator.getSmpp1(),
						crOperator.getMargin(), crOperator.getPremium());

				errorCode = ERROR_CODE.OP_CR_002;
				log.info("Operator created successfully");
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}

		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "createOperator", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private void addOperator(String userId, String gatewayId1,
			String gatewayId2, String country, String mcc, String mnc,
			String operatorName, String operatorId) {
		Operator entity = new Operator();
		entity.setCreatedBy(userId);
		entity.setCreatedTs(new Date());

		entity.setLocationId(country);
		entity.setMcc(mcc);
		entity.setMnc(mnc);
		entity.setName(operatorName);
		entity.setOperatorId(operatorId);
		entity.setRecordStatus("A");
		entity.setAirtimeSupport("N");
		Operator exstOperator = operatorRepo
				.findOne(Operator.class, operatorId);
		if (exstOperator != null) {
			entity.setFirstPrefGateway(exstOperator.getFirstPrefGateway());
			if (exstOperator.getSecondPrefGateway() == null
					&& !exstOperator.getFirstPrefGateway().equals(gatewayId1)) {
				entity.setSecondPrefGateway(gatewayId1);
			} else {
				entity.setSecondPrefGateway(exstOperator.getSecondPrefGateway());
			}
			if (exstOperator.getSecondPrefGateway() != null
					&& exstOperator.getThirdPrefGateway() == null
					&& !exstOperator.getFirstPrefGateway().equals(gatewayId1)
					&& !exstOperator.getSecondPrefGateway().equals(gatewayId1)) {
				entity.setThirdPrefGateway(gatewayId1);
			} else {
				entity.setThirdPrefGateway(exstOperator.getThirdPrefGateway());
			}
		} else {
			entity.setFirstPrefGateway(gatewayId1);
		}

		operatorRepo.merge(entity);
		log.info("Operator saved successfully");
	}

	private void addSMPPGateway(float cost, String currencyId, String userId,
			String direction, String gatewayId, String networkId,
			String operatorId, String channel, String smpp, float margin,
			float premium) {

		SmppRoute route = new SmppRoute();
		String routeId = "" + appRepo.getSequenceNumber();
		route.setCost(BigDecimal.valueOf(cost));
		route.setCostCurrencyId(currencyId);
		route.setCreatedBy(userId);
		route.setCreatedTs(new Date());
		route.setDirection(direction);
		route.setGatewayId(gatewayId);

		String lNwkId = null;
		if (StringUtils.isNotEmpty(networkId)) {
			lNwkId = networkId;
		}
		route.setMargin(BigDecimal.valueOf(margin));
		route.setNetworkId(lNwkId);
		route.setOperatorId(operatorId);
		route.setPremium(BigDecimal.valueOf(premium));
		route.setRecordStatus("A");
		route.setRouteId(routeId);
		route.setType(channel);

		routeRepo.save(route);
		log.info("Route created successfully");

		GatewayPK gatewayPk = new GatewayPK();
		route.setRouteId(routeId);
		gatewayPk.setGatewayId(gatewayId);
		gatewayPk.setRouteId(routeId);
		gatewayPk.setType(channel);
		Gateway gateway = new Gateway();
		gateway.setId(gatewayPk);
		GatewayDetail gatewayDtl = gatewayDtlRepo.findOne(GatewayDetail.class,
				gatewayId);
		if (gatewayDtl != null) {
			gateway.setName(gatewayDtl.getGatewayName());
		}
		gateway.setSmsc(smpp);
		gateway.setDataStatus("A");
		gateway.setCreatedBy(userId);
		gateway.setCreatedTs(new Date());

		gatewayRepo.merge(gateway);
		log.info("Gateway created successfully");
	}

	@Transactional
	public DeleteOperatorRes deleteOperator(DeleteOperatorReq req) {
		log.info("Inside GatewayService -> deleteOperator");
		DeleteOperatorRes res = new DeleteOperatorRes();
		Enum<ERROR_CODE> errorCode = null;
		ROUTEDTL routeDtl = req.getROUTEDTL();
		String routeId = routeDtl.getRouteId();
		SmppRoute route = routeRepo.findOne(SmppRoute.class, routeId);
		if (route == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.error("No record found");
		} else {
			try {
				GatewayPK gatewayPk = new GatewayPK();
				gatewayPk.setGatewayId(route.getGatewayId());
				gatewayPk.setRouteId(routeId);
				gatewayPk.setType(route.getType());
				Gateway gateway = gatewayRepo.findOne(Gateway.class, gatewayPk);
				if (gateway != null) {
					gatewayRepo.delete(gateway);
				}
				routeRepo.delete(route);
				log.info("Operator deleted successfully");
				errorCode = ERROR_CODE.OP_CR_003;
			} catch (DomainException e) {
				log.error(Utils.getStackTrace(e));
				errorCode = e.getCode();
			}

		}

		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "deleteOperator", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public EditOperatorRes editOperator(EditOperatorReq req) {
		log.info("Inside GatewayService -> editOperator");
		EditOperatorRes res = new EditOperatorRes();
		Enum<ERROR_CODE> errorCode = null;
		EDROUTEDTL routeDtl = req.getEDROUTEDTL();
		String routeId = routeDtl.getRouteId();
		SmppRoute route = routeRepo.findOne(SmppRoute.class, routeId);
		if (route == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.error("No record found");
		} else {
			try {
				SmppRoute newroute = new SmppRoute();
				newroute.setCost(BigDecimal.valueOf(routeDtl.getCost()));
				newroute.setCostCurrencyId(routeDtl.getCurrencyId());
				newroute.setCreatedBy(route.getCreatedBy());
				newroute.setCreatedTs(route.getCreatedTs());
				newroute.setDirection(route.getDirection());
				newroute.setGatewayId(route.getGatewayId());
				newroute.setMargin(BigDecimal.valueOf(routeDtl.getMargin()));
				newroute.setModifiedBy(req.getHeader().getUserId());
				newroute.setModifiedTs(new Date());
				if (!routeDtl.getException().equals("")) {
					newroute.setNetworkId(routeDtl.getException());
				}
				newroute.setOperatorId(route.getOperatorId());
				newroute.setPremium(BigDecimal.valueOf(routeDtl.getPremium()));
				newroute.setRecordStatus(route.getRecordStatus());
				newroute.setRouteId(routeId);
				newroute.setType(route.getType());

				GatewayPK tmpPk = new GatewayPK();
				tmpPk.setGatewayId(route.getGatewayId());
				tmpPk.setRouteId(routeId);
				tmpPk.setType(route.getType());
				Gateway exstGateway = gatewayRepo.findOne(Gateway.class, tmpPk);

				routeRepo.merge(newroute);
				log.info("Route updated successfully");

				GatewayPK extGatewayPk = exstGateway.getId();

				GatewayPK newGtwyPk = new GatewayPK();
				newGtwyPk.setGatewayId(extGatewayPk.getGatewayId());
				newGtwyPk.setRouteId(routeId);
				newGtwyPk.setType(extGatewayPk.getType());

				Gateway newGtwy = new Gateway();
				newGtwy.setCreatedBy(exstGateway.getCreatedBy());
				newGtwy.setCreatedTs(exstGateway.getCreatedTs());
				newGtwy.setId(newGtwyPk);
				newGtwy.setModifiedBy(req.getHeader().getUserId());
				newGtwy.setModifiedTs(new Date());
				GatewayDetail detail = gatewayDtlRepo.findOne(
						GatewayDetail.class, extGatewayPk.getGatewayId());
				newGtwy.setName(detail.getGatewayName());
				newGtwy.setSmsc(exstGateway.getSmsc());
				newGtwy.setCallbackUrl(exstGateway.getCallbackUrl());
				newGtwy.setCode(exstGateway.getCode());
				newGtwy.setDataStatus(exstGateway.getDataStatus());
				newGtwy.setSecret(exstGateway.getSecret());
				newGtwy.setStatusPassword(exstGateway.getStatusPassword());
				newGtwy.setStatusUrl(exstGateway.getStatusUrl());
				newGtwy.setSubmitPassword(exstGateway.getSubmitPassword());
				newGtwy.setSubmitUrl(exstGateway.getSubmitUrl());
				newGtwy.setSubmitUsername(exstGateway.getSubmitUsername());

				gatewayRepo.delete(exstGateway);
				gatewayRepo.save(newGtwy);
				log.info("Gateway updated successfully");

				errorCode = ERROR_CODE.OP_CR_004;
			} catch (DomainException e) {
				log.error(Utils.getStackTrace(e));
				errorCode = e.getCode();
			}
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "editOperator", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public SwitchOperatorRes switchOperator(SwitchOperatorReq req) {
		log.info("Inside GatewayService -> switchOperator");
		SwitchOperatorRes res = new SwitchOperatorRes();
		Enum<ERROR_CODE> errorCode = null;
		SWROUTEDTL routeDtl = req.getSWROUTEDTL();
		SmppRoute route = routeRepo.findOne(SmppRoute.class,
				routeDtl.getRouteId());

		if (route == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.error("No record found");
		} else {
			SmppRoute route1 = routeRepo.queryRoute(route.getOperatorId(),
					routeDtl.getGatewayId(), route.getNetworkId(),
					route.getType(), route.getDirection());
			if (route1 != null) {
				errorCode = ERROR_CODE.OP_CR_008;
				log.error("Route with provided gateway already exists");
			} else {
				try {
					GatewayPK extGtwyPk = new GatewayPK();
					extGtwyPk.setGatewayId(route.getGatewayId());
					extGtwyPk.setRouteId(route.getRouteId());
					extGtwyPk.setType(route.getType());
					Gateway exstGateway = gatewayRepo.findOne(Gateway.class,
							extGtwyPk);

					SmppRoute newRoute = new SmppRoute();
					newRoute.setCost(route.getCost());
					newRoute.setCostCurrencyId(route.getCostCurrencyId());
					newRoute.setCreatedBy(route.getCreatedBy());
					newRoute.setCreatedTs(route.getCreatedTs());
					newRoute.setDirection(route.getDirection());
					newRoute.setGatewayId(routeDtl.getGatewayId());
					newRoute.setMargin(route.getMargin());
					newRoute.setModifiedBy(req.getHeader().getUserId());
					newRoute.setModifiedTs(new Date());
					newRoute.setNetworkId(route.getNetworkId());
					newRoute.setOperatorId(route.getOperatorId());
					newRoute.setPremium(route.getPremium());
					newRoute.setRecordStatus(route.getRecordStatus());
					newRoute.setRouteId(routeDtl.getRouteId());
					newRoute.setType(route.getType());
					routeRepo.merge(newRoute);
					log.info("Route updated");

					GatewayPK newGtwyPk = new GatewayPK();
					newGtwyPk.setGatewayId(routeDtl.getGatewayId());
					newGtwyPk.setRouteId(extGtwyPk.getRouteId());
					newGtwyPk.setType(extGtwyPk.getType());
					Gateway newGtwy = new Gateway();
					newGtwy.setCreatedBy(exstGateway.getCreatedBy());
					newGtwy.setCreatedTs(exstGateway.getCreatedTs());
					newGtwy.setId(newGtwyPk);
					newGtwy.setModifiedBy(req.getHeader().getUserId());
					newGtwy.setModifiedTs(new Date());
					GatewayDetail detail = gatewayDtlRepo.findOne(
							GatewayDetail.class, routeDtl.getGatewayId());
					newGtwy.setName(detail.getGatewayName());
					newGtwy.setSmsc(exstGateway.getSmsc());
					newGtwy.setCallbackUrl(exstGateway.getCallbackUrl());
					newGtwy.setCode(exstGateway.getCode());
					newGtwy.setDataStatus(exstGateway.getDataStatus());
					newGtwy.setSecret(exstGateway.getSecret());
					newGtwy.setStatusPassword(exstGateway.getStatusPassword());
					newGtwy.setStatusUrl(exstGateway.getStatusUrl());
					newGtwy.setSubmitPassword(exstGateway.getSubmitPassword());
					newGtwy.setSubmitUrl(exstGateway.getSubmitUrl());
					newGtwy.setSubmitUsername(exstGateway.getSubmitUsername());
					gatewayRepo.delete(exstGateway);
					gatewayRepo.save(newGtwy);
					log.info("Gateway updated");

					errorCode = ERROR_CODE.OP_CR_005;
				} catch (DomainException e) {
					log.error(Utils.getStackTrace(e));
					errorCode = e.getCode();
				}
			}
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "switchOperator", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryAllSmppRes queryAllSmpp() {
		log.info("Inside GatewayService -> queryAllSmpp");
		QueryAllSmppRes res = new QueryAllSmppRes();
		List<KannelData> kannelList = kannelRepo.queryAllKannelData();
		List<SMPP> smppList = new ArrayList<SMPP>();
		for (KannelData kannel : kannelList) {
			SMPP smpp = new SMPP();
			smpp.setSmpp(kannel.getSmpp());
			smppList.add(smpp);
		}
		res.getSMPP().addAll(smppList);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "switchOperator", "", ERROR_CODE.ES_SC_001);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CreateSenderIdRes createSenderIdRes(CreateSenderId req) {
		CreateSenderIdRes res = new CreateSenderIdRes();
		CRSNDRID crsndrid = req.getCRSNDRID();
		SenderDetail entity = new SenderDetail();
		SenderDetailPK entitypk = new SenderDetailPK();
		SenderDetail tmpentity = new SenderDetail();
		SenderDetailPK tmpentitypk = new SenderDetailPK();
		tmpentitypk.setNetworkId(crsndrid.getNetworkId());
		tmpentitypk.setLocationId(crsndrid.getLocationId());
		if (crsndrid.getOldsenderId() != null) {
			tmpentitypk.setSenderId(crsndrid.getOldsenderId());
			tmpentity.setId(tmpentitypk);
		}
		entitypk.setNetworkId(crsndrid.getNetworkId());
		entitypk.setLocationId(crsndrid.getLocationId());
		entitypk.setSenderId(crsndrid.getSenderId());

		entity.setId(entitypk);
		entity.setCreatedBy(req.getHeader().getUserId());
		entity.setCreated_TS(Utils.getCurrentDate());
		entity.setAuthStat("A");
		entity.setSendType(crsndrid.getSendType());
		entity.setOperatorId(crsndrid.getOperatorId());

		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		if (crsndrid.getEditSaveFlag().toString().equalsIgnoreCase("E")) {
			if (sndrRepo.exists(entity, entitypk)) {
				log.info("am existing hence inside merge method");
				sndrRepo.merge(entity);

				errorCode = ERROR_CODE.SENDER_ID_ED;

			}

			else {
				log.info("i dont exist hence inside save");
				sndrRepo.save(entity);
				tmpentity = sndrRepo.findOne(tmpentity, tmpentitypk);
				sndrRepo.delete(tmpentity);

				errorCode = ERROR_CODE.SENDER_ID_ED;

			}

		} else {
			sndrRepo.save(entity);
			errorCode = ERROR_CODE.SENDER_ID_SC;

		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "Create Sender ID", "", errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeleteSenderIdRes deleteSenderId(DeleteSenderId req) {
		DeleteSenderIdRes res = new DeleteSenderIdRes();
		DLSNDID dltsndr = req.getDLSNDID();
		SenderDetail entity = new SenderDetail();
		SenderDetailPK entitypk = new SenderDetailPK();
		entitypk.setNetworkId(dltsndr.getNetworkId());
		entitypk.setLocationId(dltsndr.getLocationId());
		entitypk.setSenderId(dltsndr.getSenderId());
		entity.setId(entitypk);

		entity = sndrRepo.findOne(entity, entitypk);

		Header header = null;
		Enum<ERROR_CODE> errorCode = null;

		if (sndrRepo.exists(entity, entitypk)) {
			log.info("deleting");
			sndrRepo.delete(entity);
			errorCode = ERROR_CODE.SENDER_ID_DL_001;
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"GatewayService", "delete Sender ID", "", errorCode);
		} else

		{
			errorCode = ERROR_CODE.DM_SV_002;
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"GatewayService", "delete Sender ID", "", errorCode);
		}

		res.setHeader(header);
		return res;
	}

	public QuerySenderIdRes querySenderId() {
		QuerySenderIdRes res = new QuerySenderIdRes();
		log.info("Inside QuerySenderIdRes -> querySenderId");
		List<SenderDetail> entity = (List<SenderDetail>) sndrRepo
				.queryAllSenderId();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<SENDERIDS> sndlist = new ArrayList<SENDERIDS>();

		if (entity.size() != 0) {

			try {
				for (SenderDetail entity1 : entity) {
					SENDERIDS qrSndrs = new SENDERIDS();
					qrSndrs.setNetworkId(entity1.getId().getNetworkId());
					qrSndrs.setLocationId(entity1.getId().getLocationId());
					qrSndrs.setSenderId(entity1.getId().getSenderId());
					qrSndrs.setApps(entity1.getSendType());
					Network network = nwkRepo.queryNetworkUserInfo(entity1
							.getId().getNetworkId());
					qrSndrs.setNetworkName(network.getName());
					String applications = entity1.getSendType().toString()
							.toLowerCase();
					String[] apparr = null;
					if (applications.indexOf(",") != -1) {
						apparr = applications.split(",");
						if (apparr.length == 3) {
							String[] apparr1 = { "push", "alerts", "polls",
									"inbox" };

							List<String> sllist = new ArrayList<String>(
									Arrays.asList(apparr1));

							for (String s : apparr) {
								if (sllist.contains(s)) {
									sllist.remove(s);
								}

								qrSndrs.setSendType("All except"
										+ " "
										+ sllist.toString().replace("[", "")
												.replace("]", ""));
							}
						} else {
							qrSndrs.setSendType(entity1.getSendType());
						}
					}

					else {
						qrSndrs.setSendType(entity1.getSendType());
					}
					sndlist.add(qrSndrs);
				}
				res.getSENDERIDS().addAll(sndlist);
				errorCode = ERROR_CODE.ES_SC_001;
			} catch (DomainException e) {
				errorCode = ERROR_CODE.ES_SV_002;
				log.error(Utils.getStackTrace(e));
			}

		} else {
			errorCode = ERROR_CODE.ES_NR_001;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "Currency Service", "", errorCode);
		res.setHeader(header);
		return res;

	}

	public QueryOperatorByLocRes queryOpertorbyLoc(String locationId) {
		QueryOperatorByLocRes res = new QueryOperatorByLocRes();
		log.info("Inside QueryOperatorByLocRes -> queryOpertorbyLoc");
		List<Operator> entity = (List<Operator>) operatorRepo
				.queryOperatorByLoc(locationId);
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<OPRTRDTLS> oprlist = new ArrayList<OPRTRDTLS>();

		if (entity.size() != 0) {

			try {
				for (Operator entity1 : entity) {
					OPRTRDTLS oprdtls = new OPRTRDTLS();
					oprdtls.setOperatorId(entity1.getOperatorId());
					oprdtls.setOpeartorName(entity1.getName());

					oprlist.add(oprdtls);
				}
				res.getOPRTRDTLS().addAll(oprlist);
				errorCode = ERROR_CODE.ES_SC_001;
			} catch (DomainException e) {
				errorCode = ERROR_CODE.ES_SV_002;
				log.error(Utils.getStackTrace(e));
			}
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "Currency Service", "", errorCode);
		res.setHeader(header);
		return res;

	}

	@Transactional
	public UploadRatesRes uploadRates(UploadRatesReq req) {
		log.info("Inside GatewayRest -> uploadRates");
		UploadRatesRes res = new UploadRatesRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		UPLOADRATES uploadRates = req.getUPLOADRATES();
		String gatewayId = uploadRates.getGatewayId();
		String direction = "MT"; // uploadRates.getDirection();
		String channel = "Kannel"; // uploadRates.getChannel();
		String currencyId = uploadRates.getCurrencyId();
		String smpp = uploadRates.getSMPP();
		List<RATEDETAILS> detailList = uploadRates.getRATEDETAILS();
		if (detailList.size() != 0) {
			try {
				for (RATEDETAILS detail : detailList) {
					String mcc = detail.getMCC();
					String mnc = detail.getMNC();
					String operatorId = mcc + mnc;
					float cost = Float.valueOf(detail.getCost());
					String userId = req.getHeader().getUserId();
					String operatorCountry = detail.getOperatorCountry();
					String operatorName = detail.getOperatorName();
					List<OperatorTemplate> template = opTempRepo
							.getTemplate(operatorId);
					if (template.size() == 0) {
						log.error("MNC/MCC does not exists");
						if (direction.equalsIgnoreCase("BOTH")
								&& channel.equalsIgnoreCase("BOTH")) {
							this.uploadRateReport(SystemEnums.ESYNC.getValue(),
									userId, cost, SystemEnums.MO.getValue(),
									operatorCountry, mcc, mnc, operatorName,
									smpp, currencyId);
							this.uploadRateReport(SystemEnums.ESYNC.getValue(),
									userId, cost, SystemEnums.MT.getValue(),
									operatorCountry, mcc, mnc, operatorName,
									smpp, currencyId);
							this.uploadRateReport(
									SystemEnums.KANNEL.getValue(), userId,
									cost, SystemEnums.MO.getValue(),
									operatorCountry, mcc, mnc, operatorName,
									smpp, currencyId);
							this.uploadRateReport(
									SystemEnums.KANNEL.getValue(), userId,
									cost, SystemEnums.MT.getValue(),
									operatorCountry, mcc, mnc, operatorName,
									smpp, currencyId);
						} else if (direction.equalsIgnoreCase("BOTH")) {
							this.uploadRateReport(channel, userId, cost,
									SystemEnums.MO.getValue(), operatorCountry,
									mcc, mnc, operatorName, smpp, currencyId);
							this.uploadRateReport(channel, userId, cost,
									SystemEnums.MT.getValue(), operatorCountry,
									mcc, mnc, operatorName, smpp, currencyId);
						} else if (channel.equalsIgnoreCase("BOTH")) {
							this.uploadRateReport(SystemEnums.ESYNC.getValue(),
									userId, cost, direction, operatorCountry,
									mcc, mnc, operatorName, smpp, currencyId);
							this.uploadRateReport(
									SystemEnums.KANNEL.getValue(), userId,
									cost, direction, operatorCountry, mcc, mnc,
									operatorName, smpp, currencyId);
						} else {
							this.uploadRateReport(channel, userId, cost,
									direction, operatorCountry, mcc, mnc,
									operatorName, smpp, currencyId);
						}
					} else {
						float margin = Float.valueOf(detail.getMargin());
						float premium = Float.valueOf(detail.getPremium());
						this.addOperator(req.getHeader().getUserId(),
								gatewayId, null, operatorCountry, mcc, mnc,
								operatorName, operatorId);

						if (direction.equalsIgnoreCase("BOTH")
								&& channel.equalsIgnoreCase("BOTH")) {
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MT.getValue(), gatewayId, null,
									operatorId, SystemEnums.ESYNC.getValue(),
									smpp, margin, premium);
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MO.getValue(), gatewayId, null,
									operatorId, SystemEnums.ESYNC.getValue(),
									smpp, margin, premium);
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MT.getValue(), gatewayId, null,
									operatorId, SystemEnums.KANNEL.getValue(),
									smpp, margin, premium);
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MO.getValue(), gatewayId, null,
									operatorId, SystemEnums.KANNEL.getValue(),
									smpp, margin, premium);
						} else if (direction.equalsIgnoreCase("BOTH")) {
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MT.getValue(), gatewayId, null,
									operatorId, channel, smpp, margin, premium);
							this.addSMPPGateway(cost, currencyId, userId,
									SystemEnums.MO.getValue(), gatewayId, null,
									operatorId, channel, smpp, margin, premium);
						} else if (channel.equalsIgnoreCase("BOTH")) {
							this.addSMPPGateway(cost, currencyId, userId,
									direction, gatewayId, null, operatorId,
									SystemEnums.ESYNC.getValue(), smpp, margin,
									premium);
							this.addSMPPGateway(cost, currencyId, userId,
									direction, gatewayId, null, operatorId,
									SystemEnums.KANNEL.getValue(), smpp,
									margin, premium);
						} else {
							this.addSMPPGateway(cost, currencyId, userId,
									direction, gatewayId, null, operatorId,
									channel, smpp, margin, premium);
						}
					}
				}
				errorCode = ERROR_CODE.OP_CR_006;
				log.info("Rates uploaded successfully");
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}
		} else {
			errorCode = ERROR_CODE.ES_SV_002;
			log.info("No data to save");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"GatewayService", "uploadRates", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private void uploadRateReport(String channel, String userId, float cost,
			String direction, String operatorCountry, String mcc, String mnc,
			String operatorName, String smpp, String currencyId) {
		UploadRateReport report = new UploadRateReport();
		String reportId = "" + appRepo.getSequenceNumber();
		report.setChannel(channel);
		report.setCost(BigDecimal.valueOf(cost));
		report.setCreatedBy(userId);
		report.setCreatedTs(new Date());
		report.setCurrencyId(currencyId);
		report.setDirection(direction);
		report.setLocationId(operatorCountry);
		report.setMcc(mcc);
		report.setMnc(mnc);
		report.setOperatorName(operatorName);
		report.setReportId(reportId);
		report.setSmpp(smpp);
		reportRepo.save(report);
		log.info("Updated in upload_rate_report");
	}

}
