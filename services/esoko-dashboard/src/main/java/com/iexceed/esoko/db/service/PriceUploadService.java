package com.iexceed.esoko.db.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CommodityRepo;
import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.UploadMasterRepo;
import com.iexceed.esoko.domain.dao3.PriceUploadMasterRepo;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.entity3.PriceUploadMaster;
import com.iexceed.esoko.domain.utils.GeoUtils;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.CHILDCOMMD;
import com.iexceed.esoko.jaxb.db.COMMODITIES;
import com.iexceed.esoko.jaxb.db.CRPRICEDETAILS;
import com.iexceed.esoko.jaxb.db.Commodity;
import com.iexceed.esoko.jaxb.db.CreatePricesReq;
import com.iexceed.esoko.jaxb.db.CreatePricesRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.PARENTCOMMD;
import com.iexceed.esoko.jaxb.db.QRCHILDCOMMODITIES;
import com.iexceed.esoko.jaxb.db.QRCOMMODITIESDETAILS;
import com.iexceed.esoko.jaxb.db.QRMARKETDETAILS;
import com.iexceed.esoko.jaxb.db.QueryChildCommoditiesRes;
import com.iexceed.esoko.jaxb.db.QueryCommoditiesRes;
import com.iexceed.esoko.jaxb.db.QueryMarketsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class PriceUploadService {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	AgentDetailRepo agentDtlsRepo;

	@Autowired
	PriceUploadMasterRepo priceUploadMasterRepo;

	@Autowired
	EsokoAppRepo esokoAppRepo;

	@Autowired
	UploadMasterRepo uploadMasterRepo;

	@Autowired
	CommodityRepo commdRepo;

	public QueryMarketsRes querymarkets(String nwkId, String userId) {
		log.info("Inside PriceService -> queryMarkets");
		Header header = null;
		Enum<ERROR_CODE> errorCode = ERROR_CODE.ES_NR_001;
		QueryMarketsRes queryMarketsRes = new QueryMarketsRes();
		List<AgentDetail> agentDtls = agentDtlsRepo.findAgentDtlsPrices(nwkId,
				userId);

		if (agentDtls != null) {
			for (AgentDetail agentDtl : agentDtls) {
				String[] markets = agentDtl.getLocationId().split(",");
				for (String market : markets) {
					QRMARKETDETAILS qrMarketDtls = new QRMARKETDETAILS();
					qrMarketDtls.setDetailId(String.valueOf(agentDtl
							.getDetailId()));
					qrMarketDtls.setMarket(market);
					queryMarketsRes.getQRMARKETDETAILS().add(qrMarketDtls);
					errorCode = ERROR_CODE.ES_SC_001;
				}
			}
		} else {
			errorCode = ERROR_CODE.ES_NR_001;

		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"PriceService", "queryMarket", "", errorCode);
		queryMarketsRes.setHeader(header);
		return queryMarketsRes;
	}

	public QueryCommoditiesRes queryCommodities(String detailId) {
		log.info("Inside PriceService -> queryCommodities");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		QueryCommoditiesRes queryCommoditiesRes = new QueryCommoditiesRes();
		QRCOMMODITIESDETAILS qrCommoditiesDtls = new QRCOMMODITIESDETAILS();

		List<com.iexceed.esoko.domain.entity.Commodity> commoditiesList = null;
		commoditiesList = commdRepo.queryCommodities(detailId);
		int counter = 0;
		if (commoditiesList != null) {
			for (com.iexceed.esoko.domain.entity.Commodity commodity : commoditiesList) {

				if (commodity.getType().toString().equalsIgnoreCase("C")) {
					counter++;
					COMMODITIES commd = new COMMODITIES();
					commd.setCommodity(commodity.getCommodityId());
					qrCommoditiesDtls.getCOMMODITIES().add(commd);
				}

			}
			if (counter == 0) {
				errorCode = ERROR_CODE.ES_NR_001;
			}

			else {
				qrCommoditiesDtls.setDetailId(detailId);
				errorCode = ERROR_CODE.ES_SC_001;
			}
		} else {
			errorCode = ERROR_CODE.ES_NR_001;

		}
		queryCommoditiesRes.setQRCOMMODITIESDETAILS(qrCommoditiesDtls);
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"PriceService", "queryCommodities", "", errorCode);
		queryCommoditiesRes.setHeader(header);
		return queryCommoditiesRes;
	}

	public QueryChildCommoditiesRes queryChildCommodities(String detailId) {
		log.info("Inside PriceService -> queryChildCommodities");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		QueryChildCommoditiesRes queryCommoditiesRes = new QueryChildCommoditiesRes();
		QRCHILDCOMMODITIES qrChldCommodsDtls = new QRCHILDCOMMODITIES();
		AgentDetail agentDetail = null;
		agentDetail = agentDtlsRepo.findAgentByDetailId(Integer
				.parseInt(detailId));
		if (agentDetail != null) {
			String[] commodities = agentDetail.getCommodityId().split(",");
			for (String commodity : commodities) {
				PARENTCOMMD commd = new PARENTCOMMD();
				commd.setParentCommodityName(commodity);
				log.debug("Parent commodity:" + commodity);
				List<com.iexceed.esoko.domain.entity.Commodity> childCommdsList = commdRepo
						.queryChildCommdsByParentId(commd
								.getParentCommodityName());
				if (childCommdsList != null) {
					for (com.iexceed.esoko.domain.entity.Commodity childCommod : childCommdsList) {
						CHILDCOMMD childCommd = new CHILDCOMMD();
						childCommd.setChildCommodityName(childCommod
								.getCommodityId());
						log.debug("Child commpdity name:"
								+ childCommod.getCommodityId());
						commd.getCHILDCOMMD().add(childCommd);
					}

				}
				qrChldCommodsDtls.getPARENTCOMMD().add(commd);
			}
			qrChldCommodsDtls.setDetailId(detailId);
			errorCode = ERROR_CODE.ES_SC_001;

		} else {
			errorCode = ERROR_CODE.ES_NR_001;

		}
		queryCommoditiesRes.setQRCHILDCOMMODITIES(qrChldCommodsDtls);
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"PriceService", "queryChildCommodities", "", errorCode);
		queryCommoditiesRes.setHeader(header);
		return queryCommoditiesRes;
	}

	@Transactional
	public CreatePricesRes createPrices(CreatePricesReq createPricesReq) {
		log.info("Inside PriceService -> createPrices");
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		CreatePricesRes createPricesRes = new CreatePricesRes();
		CRPRICEDETAILS crPriceDetails = createPricesReq.getCRPRICEDETAILS();
		try {
			List<Commodity> commoditiesList = crPriceDetails.getCommodities();
			for (Commodity commodity : commoditiesList) {
				String id = "PRICES" + esokoAppRepo.getSequenceNumber();
				UploadMaster uploadMaster = new UploadMaster();
				uploadMaster.setUploadId(id);
				uploadMaster.setAgentId(crPriceDetails.getAgentId());
				uploadMaster.setApplicationId("Prices");
				if (crPriceDetails.getDetailId() != null
						&& !("".equals(crPriceDetails.getDetailId()))) {
					uploadMaster.setDetailId(Integer.parseInt(crPriceDetails
							.getDetailId()));
				}
				uploadMaster.setAuthBy("");
				uploadMaster.setAuthStat("U");
				uploadMaster.setAuthTs(null);
				uploadMaster.setNetworkId(crPriceDetails.getNetworkId());
				uploadMaster.setUploadDt(Utils.getCurrentDate());
				uploadMasterRepo.save(uploadMaster);

				PriceUploadMaster master = new PriceUploadMaster();
				master.setUpload_ID(id);
				master.setCommodity(commodity.getCommodityId());
				master.setPriceType(commodity.getPriceType());
				master.setMarket(crPriceDetails.getMarket());
				master.setCollectedOn(Utils.getFormatedDate2(crPriceDetails
						.getCollectdDate()));
				master.setCurrencyId(crPriceDetails.getCurrencyId());
				master.setPrice(new BigDecimal(commodity.getPrice()));
				master.setMeasureId(commodity.getMeasureId());
				master.setVariety(commodity.getVariety());
				if (commodity.getWeight() != 0) {
					master.setWeight(new BigDecimal(commodity.getWeight()));
				}
				if (commodity.getWeightMeasure() != null) {
					master.setWeightMeasure(commodity.getWeightMeasure());
				}
				if (commodity.getVarietyComment() != null) {
					master.setVarietyComment(commodity.getVarietyComment());
				}
				if (crPriceDetails.getComments() != null) {
					master.setComments(crPriceDetails.getComments());
				}
				master.setNetworkId(crPriceDetails.getNetworkId());
				master.setAuthStat("U");
				master.setAuthBy("");
				master.setAuthTs(null);

				if (createPricesReq.getHeader().getUserId() != null) {

					master.setCreatedBy(createPricesReq.getHeader().getUserId());
				}
				master.setCreatedTs(Utils.getCurrentDate());
				master.setUploadMode(crPriceDetails.getUploadMode());
				master.setAgentId(crPriceDetails.getAgentId());
				if (!("".equals(crPriceDetails.getLongitude()))
						&& crPriceDetails.getLongitude() != null
						&& !("".equals(crPriceDetails.getLatitude()))
						&& crPriceDetails.getLatitude() != null) {
					master.setUploadGis(GeoUtils.getGisValue(
							crPriceDetails.getLongitude(),
							crPriceDetails.getLatitude()));
				}
				priceUploadMasterRepo.save(master);
			}
			errorCode = ERROR_CODE.PRC_SC;
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = ERROR_CODE.ES_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"PriceService", "createPrices", "", errorCode);
		createPricesRes.setHeader(header);

		return createPricesRes;
	}
}
