package com.iexceed.esoko.se.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CommodityMeasureRepo;
import com.iexceed.esoko.domain.dao.MeasureAliaRepo;
import com.iexceed.esoko.domain.dao.MeasureFactorRepo;
import com.iexceed.esoko.domain.dao.MeasureRepo;
import com.iexceed.esoko.domain.dao.NetworkCommodityRepo;
import com.iexceed.esoko.domain.dao.PriceTypeRepo;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.CommodityMeasure;
import com.iexceed.esoko.domain.entity.Measure;
import com.iexceed.esoko.domain.entity.MeasureAlia;
import com.iexceed.esoko.domain.entity.MeasureFactor;
import com.iexceed.esoko.domain.entity.NetworkCommodity;
import com.iexceed.esoko.domain.entity.PriceType;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.CRFCTDTLS;
import com.iexceed.esoko.jaxb.se.CRMSRFCTDTLS;
import com.iexceed.esoko.jaxb.se.CUSTMSR;
import com.iexceed.esoko.jaxb.se.CreateMeasureFactorReq;
import com.iexceed.esoko.jaxb.se.CreateMeasureFactorRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.METCMSR;
import com.iexceed.esoko.jaxb.se.PRCTYP;
import com.iexceed.esoko.jaxb.se.QRALLMSRDTLS;
import com.iexceed.esoko.jaxb.se.QRFCTDTLS;
import com.iexceed.esoko.jaxb.se.QRMSALSDTLS;
import com.iexceed.esoko.jaxb.se.QRMSRFCTDTLS;
import com.iexceed.esoko.jaxb.se.QueryMeasureCommodityRes;
import com.iexceed.esoko.jaxb.se.QueryMeasureFactorRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class MeasureFactorService {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	MeasureAliaRepo measureAliasRepo;
	@Autowired
	MeasureFactorRepo factorRepo;

	@Autowired
	MeasureRepo measureRepo;

	@Autowired
	CommodityMeasureRepo commeasureRepo;

	@Autowired
	NetworkCommodityRepo networkCommodityRepo;

	@Autowired
	PriceTypeRepo priceRepo;

	public QueryMeasureFactorRes queryMeasureFactor(String networkId,
			String locationId, String commodityId, String prcType,
			String userId, String criteriaId) {

		log.info("Inside QueryMeasureRes -> queryMeasure :: ");
		log.debug("NetworkID :: " + networkId);
		log.debug("locationId :: " + locationId);
		log.debug("commodityId :: " + commodityId);
		log.debug("userId :: " + userId);
		QueryMeasureFactorRes qryMeasureRes = new QueryMeasureFactorRes();
		List<MeasureFactor> listofNwkMsrs = null;
		String newCommodityId = null;
		String prevCommodityId = null;
		QRMSRFCTDTLS qrmsrdtls = null;
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		boolean commodityBased = false;
		List<QRMSRFCTDTLS> listqrmsrdtls = new ArrayList<QRMSRFCTDTLS>();
		List<QRMSRFCTDTLS> udflistqrmsrdtls = new ArrayList<QRMSRFCTDTLS>();
		List<Commodity> commodities = networkCommodityRepo
				.queryCommodByNwkIdCrtId(networkId, criteriaId);
		log.debug("All Network Commodities :: " + commodities.toString());
		List<String> definedCommodities = new ArrayList<String>();
		List<Measure> listMeasures = measureRepo.queryAllSystemMeasures();
		log.debug("All Maintained Measures Are :: " + listMeasures.toString());
		List<CUSTMSR> custmsr = new ArrayList<CUSTMSR>();
		List<METCMSR> sysmsr = new ArrayList<METCMSR>();
		List<PRCTYP> prctyp = new ArrayList<PRCTYP>();
		List<PriceType> listPriceTypes = priceRepo.queryAllPriceType();
		log.debug("All Maintained PriceTypes Are :: "
				+ listPriceTypes.toString());
		if (StringUtils.isNotEmpty(networkId)
				&& StringUtils.isNotEmpty(commodityId)
				&& StringUtils.isNotEmpty(prcType)) {

			listofNwkMsrs = factorRepo.queryByNwkCMdPrct1(networkId,
					commodityId, prcType);
			commodityBased = true;
		} else if (StringUtils.isNotEmpty(networkId)
				&& StringUtils.isNotEmpty(commodityId)) {
			listofNwkMsrs = factorRepo.queryByNwkandCommodity(networkId,
					commodityId);
			commodityBased = true;
		} else if (StringUtils.isNotEmpty(networkId)) {
			listofNwkMsrs = factorRepo.queryByNwk(networkId);
		}
		log.debug("Result Set for Measure Factor Query :: "
				+ listofNwkMsrs.toString());
		for (MeasureFactor measureFactor : listofNwkMsrs) {
			newCommodityId = measureFactor.getCommodityId();

			if (!newCommodityId.equals(prevCommodityId)) {
				qrmsrdtls = null;
				qrmsrdtls = new QRMSRFCTDTLS();
				qrmsrdtls.setCommodityId(measureFactor.getCommodityId());
				definedCommodities.add(measureFactor.getCommodityId());
				qrmsrdtls.setDefined("Y");
			}
			QRFCTDTLS qrfctdtls = new QRFCTDTLS();
			qrfctdtls.setPriceType(measureFactor.getPriceType());
			if (commodityBased) {
				for (Measure measure : listMeasures) {
					if (measure.getMeasureId().equals(
							measureFactor.getBaseMeasureId()))
						qrfctdtls.setFrommeasureId(measureFactor
								.getBaseMeasureId()
								+ "~"
								+ measure.getIsSystem()
								+ "~"
								+ measure.getSymbol());
					if (measure.getMeasureId().equals(
							measureFactor.getBaseMeasureId()))
						qrfctdtls.setTomeasureId(measureFactor
								.getQuoteMeasureId()
								+ "~"
								+ measure.getIsSystem()
								+ "~"
								+ measure.getSymbol());
				}
			} else {
				qrfctdtls.setFrommeasureId(measureFactor.getBaseMeasureId());
				qrfctdtls.setTomeasureId(measureFactor.getQuoteMeasureId());
			}
			if (measureFactor.getConvFactor() != null) {
				qrfctdtls.setMeasureFactor(measureFactor.getConvFactor()
						.toPlainString());
			}
			qrfctdtls
					.setEffectiveDate(measureFactor.getDate() != null ? measureFactor
							.getDate().toString() : "");
			qrfctdtls.setDefault(measureFactor.getDefaultMeasure());
			qrmsrdtls.getQRFCTDTLS().add(qrfctdtls);
			if (!newCommodityId.equals(prevCommodityId)) {
				listqrmsrdtls.add(qrmsrdtls);
			}
			prevCommodityId = newCommodityId;

		}
		if (!commodityBased) {
			for (Commodity commodity : commodities) {
				if (!definedCommodities.contains(commodity.getCommodityId())) {
					QRMSRFCTDTLS qrmsrdtls2 = new QRMSRFCTDTLS();
					qrmsrdtls2.setCommodityId(commodity.getCommodityId());
					qrmsrdtls2.setDefined("N");
					udflistqrmsrdtls.add(qrmsrdtls2);
				}
			}
			for (Measure measure : listMeasures) {
				if (measure.getIsSystem().equalsIgnoreCase("Y")) {
					CUSTMSR custmsr2 = new CUSTMSR();
					custmsr2.setMeasureId(measure.getMeasureId());
					custmsr.add(custmsr2);
				} else {
					METCMSR metcmsr = new METCMSR();
					metcmsr.setMeasureId(measure.getMeasureId());
					sysmsr.add(metcmsr);
				}
			}

			for (PriceType priceType : listPriceTypes) {
				PRCTYP prctyp2 = new PRCTYP();
				prctyp2.setPriceType(priceType.getPriceTypeId());
				prctyp.add(prctyp2);
			}
		}

		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"MeasureService", "queryMeasure", "", errorCode);
		qryMeasureRes.getQRMSRFCTDTLS().addAll(listqrmsrdtls);
		qryMeasureRes.getQRMSRFCTDTLS().addAll(udflistqrmsrdtls);
		qryMeasureRes.getCUSTMSR().addAll(custmsr);
		qryMeasureRes.getMETCMSR().addAll(sysmsr);
		qryMeasureRes.getPRCTYP().addAll(prctyp);
		qryMeasureRes.setHeader(header);
		log.info("Completed queryMeasure :: " + qryMeasureRes.toString());
		return qryMeasureRes;
	}

	public QueryMeasureCommodityRes queryAllCommodityMeasure(String locationId,
			String commodityId) {
		QueryMeasureCommodityRes measureCommodityRes = new QueryMeasureCommodityRes();
		List<CommodityMeasure> commodityMeasures = commeasureRepo
				.queryAllMeasures(commodityId, locationId);

		for (CommodityMeasure commodityMeasure : commodityMeasures) {

			QRALLMSRDTLS qrallmsrdtls = new QRALLMSRDTLS();
			qrallmsrdtls.setCommodityId(commodityMeasure.getId()
					.getCommodityId());
			qrallmsrdtls.setMeasureId(commodityMeasure.getId().getMeasureId());
			List<MeasureAlia> alias = measureAliasRepo
					.queryMeasureAliabymeasureId(commodityMeasure.getId()
							.getMeasureId());

			for (MeasureAlia alia : alias) {
				QRMSALSDTLS qrmsalsdtls = new QRMSALSDTLS();
				qrmsalsdtls.setAliasID(alia.getAliasId());
				qrmsalsdtls.setName(alia.getMeasureName());
				qrallmsrdtls.getQRALSDTLS().add(qrmsalsdtls);
			}
			measureCommodityRes.getQRALLMSRDTLS().add(qrallmsrdtls);
		}
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"MeasureService", "queryMeasure", "", errorCode);
		measureCommodityRes.setHeader(header);
		return measureCommodityRes;

	}

	@Transactional
	public CreateMeasureFactorRes createMeasureFactor(CreateMeasureFactorReq req) {
		// TODO Auto-generated method stub
		log.info("Measure Service -> createMeasure");
		List<MeasureFactor> listofNwkMsrs = null;
		MeasureFactor factor = null;
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		for (CRMSRFCTDTLS qrmsrdtls : req.getCRMSRFCTDTLS()) {
			for (CRFCTDTLS crfctdtls : qrmsrdtls.getCRFCTDTLS()) {
				log.info("Network ID ->" + qrmsrdtls.getNetworkId());
				log.info("Commodity Id ->" + qrmsrdtls.getCommodityId());
				log.info("Price Type ->" + crfctdtls.getPriceType());

				factor = factorRepo.queryByNwkCMdPrct(qrmsrdtls.getNetworkId(),
						qrmsrdtls.getCommodityId(), crfctdtls.getPriceType());

				log.info("factor ->" + factor);
				if (factor == null) {
					factor = new MeasureFactor();
					factor.setCommodityId(qrmsrdtls.getCommodityId());
					factor.setNetworkId(qrmsrdtls.getNetworkId());
					factor.setPriceType(crfctdtls.getPriceType());
					factor.setBaseMeasureId(crfctdtls.getFromMeasureId());
					factor.setQuoteMeasureId(crfctdtls.getToMeasureId());
					factor.setConvFactor(new BigDecimal(crfctdtls
							.getMeasureFactor()));
					factor.setLocationId(qrmsrdtls.getLocationId());
					factor.setDate(Utils.getFormatedDate3(crfctdtls
							.getEffectiveDate()));
					factor.setDefaultMeasure(crfctdtls.getDefault());
					factor.setCreatedBy(req.getHeader().getUserId());
					factor.setCreatedTs(Utils.getCurrentDate());
					factor.setModifiedBy(req.getHeader().getUserId());
					factor.setModifiedTs(Utils.getCurrentDate());
					factorRepo.save(factor);
				} else {
					factor.setBaseMeasureId(crfctdtls.getFromMeasureId());
					factor.setQuoteMeasureId(crfctdtls.getToMeasureId());
					if (crfctdtls.getMeasureFactor() != null
							&& !("".equals(crfctdtls.getMeasureFactor()))) {
						factor.setConvFactor(new BigDecimal(crfctdtls
								.getMeasureFactor()));
					}

					factor.setLocationId(qrmsrdtls.getLocationId());
					if (crfctdtls.getEffectiveDate() != null
							&& !("".equals(crfctdtls.getEffectiveDate()))) {
						factor.setDate(Utils.getFormatedDate3(crfctdtls
								.getEffectiveDate()));
					}
					factor.setModifiedBy(req.getHeader().getUserId());
					factor.setModifiedTs(Utils.getCurrentDate());
					factor.setDefaultMeasure(crfctdtls.getDefault());
					factorRepo.merge(factor);
				}

			}

		}
		errorCode = ERROR_CODE.ES_SV_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"MeasureService", "createMeasure", "", errorCode);

		CreateMeasureFactorRes crtmeasureRes = new CreateMeasureFactorRes();
		crtmeasureRes.setHeader(header);
		return crtmeasureRes;
	}

	public QueryMeasureFactorRes queryMeasureForCommodity(String networkId,
			String locationId, String commodityId, String userId) {

		log.info("Inside QueryMeasureRes -> queryMeasure :: ");
		log.debug("NetworkID :: " + networkId);
		log.debug("locationId :: " + locationId);
		log.debug("commodityId :: " + commodityId);
		log.debug("userId :: " + userId);
		QueryMeasureFactorRes qryMeasureRes = new QueryMeasureFactorRes();
		List<MeasureFactor> listofNwkMsrs = null;
		String newCommodityId = null;
		String prevCommodityId = null;
		QRMSRFCTDTLS qrmsrdtls = null;
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		List<QRMSRFCTDTLS> listqrmsrdtls = new ArrayList<QRMSRFCTDTLS>();
		List<NetworkCommodity> commodities = networkCommodityRepo
				.queryNetworkCommodities(networkId);
		log.debug("All Network Commodities :: " + commodities.toString());
		List<PriceType> listPriceTypes = priceRepo.queryAllPriceType();
		log.debug("All Maintained PriceTypes Are :: "
				+ listPriceTypes.toString());
		if (StringUtils.isNotEmpty(networkId)
				&& StringUtils.isNotEmpty(commodityId)) {
			listofNwkMsrs = factorRepo.queryByNwkandCommodityDefault(networkId,
					commodityId);
		}
		log.debug("Result Set for Measure Factor Query :: "
				+ listofNwkMsrs.toString());
		for (MeasureFactor measureFactor : listofNwkMsrs) {
			newCommodityId = measureFactor.getCommodityId();

			if (!newCommodityId.equals(prevCommodityId)) {
				qrmsrdtls = null;
				qrmsrdtls = new QRMSRFCTDTLS();
				qrmsrdtls.setCommodityId(measureFactor.getCommodityId());
			}
			QRFCTDTLS qrfctdtls = new QRFCTDTLS();
			qrfctdtls.setPriceType(measureFactor.getPriceType());
			qrfctdtls.setFrommeasureId(measureFactor.getBaseMeasureId());
			qrfctdtls.setMeasureFactor(measureFactor.getConvFactor()
					.toPlainString());
			qrfctdtls.setTomeasureId(measureFactor.getBaseMeasureId());
			qrfctdtls
					.setEffectiveDate(measureFactor.getDate() != null ? measureFactor
							.getDate().toString() : "");
			qrfctdtls.setDefault(measureFactor.getDefaultMeasure());
			qrmsrdtls.getQRFCTDTLS().add(qrfctdtls);
			if (!newCommodityId.equals(prevCommodityId)) {
				listqrmsrdtls.add(qrmsrdtls);
			}
			prevCommodityId = newCommodityId;

		}

		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"MeasureService", "queryMeasure", "", errorCode);
		qryMeasureRes.getQRMSRFCTDTLS().addAll(listqrmsrdtls);
		qryMeasureRes.setHeader(header);
		log.info("Completed queryMeasure :: " + qryMeasureRes.toString());
		return qryMeasureRes;
	}

}
