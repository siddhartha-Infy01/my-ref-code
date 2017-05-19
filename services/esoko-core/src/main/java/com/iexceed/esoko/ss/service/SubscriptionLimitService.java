package com.iexceed.esoko.ss.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao2.SubscriptionLimitRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionMasterRepo;
import com.iexceed.esoko.domain.entity2.SubscriptionDetail;
import com.iexceed.esoko.domain.entity2.SubscriptionDetailPK;
import com.iexceed.esoko.domain.entity2.SubscriptionMaster;
import com.iexceed.esoko.domain.entity2.SubscriptionMasterPK;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ss.AddSubLimitsDtlsReq;
import com.iexceed.esoko.jaxb.ss.AddSubLimitsDtlsRes;
import com.iexceed.esoko.jaxb.ss.CRLIMITS;
import com.iexceed.esoko.jaxb.ss.CRLIMITSLIST;
import com.iexceed.esoko.jaxb.ss.DLLIMITSLIST;
import com.iexceed.esoko.jaxb.ss.DeleteSubLimitsDtlsReq;
import com.iexceed.esoko.jaxb.ss.DeleteSubLimitsDtlsRes;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.QRLIMITSLIST;
import com.iexceed.esoko.jaxb.ss.QRNWKLIMITDTLS;
import com.iexceed.esoko.jaxb.ss.QRYLIMITS;
import com.iexceed.esoko.jaxb.ss.QueryNetworkLimitRes;
import com.iexceed.esoko.jaxb.ss.QuerySubLimitsDtlsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

//Author:Gangadhar
@Service
public class SubscriptionLimitService {
	@Autowired
	SubscriptionLimitRepo repo;
	@Autowired
	SubscriptionMasterRepo masterRepo;

	private final String serviceName = "SubscriptionLimitService";
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;
	public static Logger log = LoggerUtils.getLogger();

	public QuerySubLimitsDtlsRes querySubLimitDtlsRes() {
		QuerySubLimitsDtlsRes res = new QuerySubLimitsDtlsRes();
		// Fetching all records from subscription limit details
		List<SubscriptionDetail> entityList = repo.querySubLimitList();
		List<QRLIMITSLIST> qrLimitsList = new ArrayList<QRLIMITSLIST>();
		String newSubId = null;
		String prevSubId = null;
		QRLIMITSLIST qrLimitsDtls = null;
		if (entityList.size() > 0) {
			for (SubscriptionDetail entity : entityList) {
				newSubId = entity.getCountryName();
				if (!newSubId.equals(prevSubId)) {
					qrLimitsDtls = null;
					qrLimitsDtls = new QRLIMITSLIST();
					qrLimitsDtls.setSubId(entity.getCountryName());
					qrLimitsDtls.setSubLevel(entity.getSubscriptionLevel());
				}
				QRYLIMITS qrLimits = new QRYLIMITS();
				try {
					qrLimits.setRowId(entity.getId().getSubscriptionId());
					qrLimits.setType(entity.getId().getSubscriptionType());
					qrLimits.setParamName(SubscriptionServicesEnum.valueOf(
							entity.getId().getParamName().toUpperCase())
							.getServiceCode());
					qrLimits.setParamValue(entity.getParamValue());
					qrLimitsDtls.getQRYLIMITS().add(qrLimits);
					if (!newSubId.equals(prevSubId)) {
						qrLimitsList.add(qrLimitsDtls);
					}
					prevSubId = newSubId;
				} catch (Exception e) {
				}

			}
			res.getQRLIMITSLIST().addAll(qrLimitsList);
			ERROR = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS, serviceName,
				"Query SubLimits DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public AddSubLimitsDtlsRes createSubLimitDtlsRes(AddSubLimitsDtlsReq req) {
		log.info("Inside AddSubLimitsDtlsRes -> createSubPricingDtlsRes");
		AddSubLimitsDtlsRes res = new AddSubLimitsDtlsRes();
		List<CRLIMITSLIST> priceList = req.getCRLIMITSLIST();
		int row_no = 0;
		if (priceList.size() != 0) {
			log.debug("priceList::" + priceList);
			row_no = masterRepo.getRecentSubId();
			for (CRLIMITSLIST crListDtls : priceList) {

				SubscriptionMaster masterEntity = new SubscriptionMaster();
				SubscriptionMasterPK masterEntityPK = new SubscriptionMasterPK();
				masterEntityPK.setSubscriptionType(crListDtls.getType());
				log.debug("SubscriptionMasterPK sub-Type-:"
						+ masterEntityPK.getSubscriptionType());
				masterEntity.setId(masterEntityPK);
				masterEntity.setSubscriptionLevel(crListDtls.getLevel());
				List<CRLIMITS> crPricesList = crListDtls.getCRLIMITS();
				if (crPricesList.size() != 0) {
					log.debug("priceList::" + crPricesList);
					if (masterRepo.findMasterRecordExist(crListDtls.getRowId()) > 0) {
						log.info("Inside merge");
						masterRepo.updateMasterRecord(
								masterEntityPK.getSubscriptionType(),
								crListDtls.getRowId());
						row_no = crListDtls.getRowId();
					} else {
						log.info("Inside save");
						masterRepo.save(masterEntity);
						row_no++;
					}
					for (CRLIMITS crPrices : crPricesList) {
						log.debug("SubLevel::" + crListDtls.getLevel());
						log.debug("SubType::" + crListDtls.getType());
						log.debug("SubscriptionType::"
								+ crPrices.getCountryName());

						if (!StringUtils.isNotEmpty(crListDtls.getLevel())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, serviceName,
									"Create SubLimits DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER1);
							res.setHeader(header);
							return res;
						}
						if (!StringUtils.isNotEmpty(crListDtls.getType())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, serviceName,
									"Create SubLimits DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER2);
							res.setHeader(header);
							return res;
						}
						if (!StringUtils.isNotEmpty(crPrices.getCountryName())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, serviceName,
									"Create SubLimits DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER3);
							res.setHeader(header);
							return res;
						} else {
							if (repo.findSubDtlsRecordExist(
									crListDtls.getType(),
									crPrices.getParamName(),
									row_no) > 0) {
								log.info("Inside record exists");
								SubscriptionDetail entity = new SubscriptionDetail();
								SubscriptionDetailPK entityPk = new SubscriptionDetailPK();
								entityPk.setParamName(crPrices.getParamName());
								entityPk.setSubscriptionType(crListDtls
										.getType());
								entity.setId(entityPk);
								entity.setCountryName(crPrices.getCountryName());
								entity.setParamValue(crPrices.getParamValue());
								entity.setNetworkName(crPrices.getNetworkName());
								entity.setSubscriptionLevel(crListDtls
										.getLevel());
								entity.setSubscriptionCategory("L");
								entity = repo.querySubPricingDtls(
										crListDtls.getType(),
										crPrices.getParamName(),
										row_no);
								if(crPrices.getMargin() != null){
								entity.setMargin(crPrices.getMargin());
								}
								if(StringUtils.isNotEmpty(crPrices.getParamValue()))
								{
								entity.setParamValue(crPrices.getParamValue());
								}
								// Updating the record
								repo.merge(entity);
								ERROR = ERROR_CODE.SS_SUBLT_UD;
							} else {
								log.info("Inside save");
								SubscriptionDetail entity = new SubscriptionDetail();
								SubscriptionDetailPK entityPk = new SubscriptionDetailPK();
								entityPk.setParamName(crPrices.getParamName());
								entityPk.setSubscriptionId(row_no);
								entityPk.setSubscriptionType(crListDtls
										.getType());
								entity.setId(entityPk);
								entity.setCountryName(crPrices.getCountryName());
								entity.setParamValue(crPrices.getParamValue());
								entity.setNetworkName(crPrices.getNetworkName());
								entity.setSubscriptionLevel(crListDtls
										.getLevel());
								entity.setMargin(crPrices.getMargin());
								entity.setSubscriptionCategory("L");
								// saving the record
								repo.save(entity);
								ERROR = ERROR_CODE.SS_SUBLT_SV;
								entity = new SubscriptionDetail();
								entityPk = new SubscriptionDetailPK();
							}
						}
					}
				}
			}
		} else {
			ERROR = ERROR_CODE.ES_SV_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS, serviceName,
				"Create SubLimits DtlsRes", "", ERROR);
		res.setHeader(header);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeleteSubLimitsDtlsRes deleteSubAllLimits(DeleteSubLimitsDtlsReq req) {
		DeleteSubLimitsDtlsRes res = new DeleteSubLimitsDtlsRes();
		DLLIMITSLIST dlPRicesList = req.getDLLIMITSLIST();
		if (dlPRicesList != null) {
			if (!StringUtils.isNotEmpty(dlPRicesList.getSubLevel())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"SubscriptionPriceService",
						"Create SubPricing DtlsRes", "",
						ERROR_CODE.SS_SUBPR_ER1);
				res.setHeader(header);
				return res;
			}
			if (!StringUtils.isNotEmpty(dlPRicesList.getSubId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Delete All SubPricing Dtls", "",
						ERROR_CODE.SS_SUBPR_ER2);
				res.setHeader(header);
				return res;
			} else {
				// Getting row numbers for deleting master records
				List rowCounts = repo.getSubscribMasterRows(
						dlPRicesList.getSubLevel(), dlPRicesList.getSubId());
				if (rowCounts.size() != 0) {
					for (int i = 0; i < rowCounts.size(); i++) {
						log.info("Insdie master delete records");
						log.info("rowCounts::" + rowCounts);
						// Deleting the master records by using row id
						masterRepo.deleteMasterSubscriptions(rowCounts.get(i)
								.toString());
					}
				}
				// Deleting subscription details
				int returnCount = repo.delteAllLimits(
						dlPRicesList.getSubLevel(), dlPRicesList.getSubId());
				if (returnCount > 0) {
					ERROR = ERROR_CODE.SS_SUBLT_DL;
				} else {
					ERROR = ERROR_CODE.ES_NR_001;
				}
			}
		} else {
			ERROR = ERROR_CODE.DM_SV_003;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.SS, serviceName,
				"Delete All SubPricing Dtls", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryNetworkLimitRes queryNetworkLimits(String type,
			String countryName, String paramName) {
		log.info("Inside QueryNetworkLimitRes -> queryNetworkLimits");
		log.debug("Type::" + type);
		log.debug("CountryName::" + countryName);
		log.debug("ParamName::" + paramName);
		QueryNetworkLimitRes res = new QueryNetworkLimitRes();
		SubscriptionDetail entity = repo.queryNetworkLimit(type, countryName,
				paramName);
		if (entity != null) {
			QRNWKLIMITDTLS dtls = new QRNWKLIMITDTLS();
			dtls.setCountryName(entity.getCountryName());
			dtls.setMargin(entity.getMargin());
			dtls.setParamName(entity.getId().getParamName());
			dtls.setParamValue(entity.getParamValue());
			dtls.setSubType(entity.getId().getSubscriptionType());
			dtls.setRowId(entity.getId().getSubscriptionId());
			res.setQRNWKLIMITDTLS(dtls);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS, serviceName,
				"Query Network Limits", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public Map<String, String> queryNetworkLimits(String networkId) {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, Object>> recordList = repo
				.queryNetworkLimitDtls(networkId);
		for (Map<String, Object> pricemap : recordList) {
			map.put(pricemap.get("param_name").toString(),
					pricemap.get("param_value").toString());
		}
		return map;
	}

}
