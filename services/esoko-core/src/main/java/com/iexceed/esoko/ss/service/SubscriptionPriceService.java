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

import com.iexceed.esoko.domain.dao2.SubscriptionMasterRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionPriceRepo;
import com.iexceed.esoko.domain.entity2.SubscriptionDetail;
import com.iexceed.esoko.domain.entity2.SubscriptionDetailPK;
import com.iexceed.esoko.domain.entity2.SubscriptionMaster;
import com.iexceed.esoko.domain.entity2.SubscriptionMasterPK;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ss.AddSubPricesDtlsReq;
import com.iexceed.esoko.jaxb.ss.AddSubPricesDtlsRes;
import com.iexceed.esoko.jaxb.ss.CRPRICES;
import com.iexceed.esoko.jaxb.ss.CRPRICESLIST;
import com.iexceed.esoko.jaxb.ss.DLPRICESLIST;
import com.iexceed.esoko.jaxb.ss.DeleteSubPricesDtlsReq;
import com.iexceed.esoko.jaxb.ss.DeleteSubPricesDtlsRes;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.QRPRICELIST;
import com.iexceed.esoko.jaxb.ss.QRYPRICES;
import com.iexceed.esoko.jaxb.ss.QuerySubPricingDtlsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

//Author:Gangadhar
@Service
public class SubscriptionPriceService {
	@Autowired
	SubscriptionPriceRepo repo;
	@Autowired
	SubscriptionMasterRepo masterRepo;
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;
	public static Logger log = LoggerUtils.getLogger();
	private final String serviceName = "SubscriptionLimitService";

	public QuerySubPricingDtlsRes querySubPricingDtlsRes(String headerType,
			String networkId) {
		QuerySubPricingDtlsRes res = new QuerySubPricingDtlsRes();
		// Fetching all records from subscription details
		if ("SS".equalsIgnoreCase(headerType)) {
			List<QRPRICELIST> qrPriceList = this.querySubscribtionDetails();
			if (qrPriceList.size() > 0) {
				log.info("entityList::" + qrPriceList);

				res.getQRPRICELIST().addAll(qrPriceList);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		}
		if ("RS".equalsIgnoreCase(headerType)) {
			List<QRPRICELIST> qrPriceList = this
					.queryResellerSubscribtionDetails(networkId);
			if (qrPriceList.size() > 0) {
				log.info("entityList::" + qrPriceList);

				res.getQRPRICELIST().addAll(qrPriceList);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"SubscriptionPriceService", "Query SubPricing DtlsRes", "",
				ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public AddSubPricesDtlsRes createSubPricingDtlsRes(AddSubPricesDtlsReq req) {
		log.info("Inside AddSubPricesDtlsRes -> createSubPricingDtlsRes");
		AddSubPricesDtlsRes res = new AddSubPricesDtlsRes();
		List<CRPRICESLIST> priceList = req.getCRPRICESLIST();
		int row_no = masterRepo.getRecentSubId();
		if (priceList.size() != 0) {
			log.debug("priceList::" + priceList);

			for (CRPRICESLIST crListDtls : priceList) {
				SubscriptionMaster masterEntity = new SubscriptionMaster();
				SubscriptionMasterPK masterEntityPK = new SubscriptionMasterPK();
				masterEntityPK.setSubscriptionType(crListDtls.getType());
				log.debug("SubscriptionMasterPK sub-Type-:"
						+ masterEntityPK.getSubscriptionType());
				masterEntity.setId(masterEntityPK);
				masterEntity.setSubscriptionLevel(crListDtls.getLevel());
				List<CRPRICES> crPricesList = crListDtls.getCRPRICES();
				if (crPricesList.size() != 0) {
					// Checking the record existing
					if (masterRepo.findMasterRecordExist(crListDtls.getRowId()) > 0) {
						log.info("Inside merge");
						// Updating the same record with row id
						masterRepo.updateMasterRecord(
								masterEntityPK.getSubscriptionType(),
								crListDtls.getRowId());
						row_no = crListDtls.getRowId();
					} else {
						log.info("Inside save");
						masterRepo.save(masterEntity);
						row_no++;
					}
					for (CRPRICES crPrices : crPricesList) {

						if (!StringUtils.isNotEmpty(crListDtls.getLevel())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, "SubscriptionPriceService",
									"Create SubPricing DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER1);
							res.setHeader(header);
							return res;
						}
						if (!StringUtils.isNotEmpty(crListDtls.getType())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, "SubscriptionPriceService",
									"Create SubPricing DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER3);
							res.setHeader(header);
							return res;
						}
						if (!StringUtils.isNotEmpty(crPrices.getParamName())) {
							header = (Header) HeaderFactory.getHeader(
									HeaderType.SS, "SubscriptionPriceService",
									"Create SubPricing DtlsRes", "",
									ERROR_CODE.SS_SUBPR_ER2);
							res.setHeader(header);
							return res;
						} else {
							// Checking the price details exist
							if (repo.findSubDtlsRecordExist(row_no,
									crListDtls.getType(),
									crPrices.getParamName()) > 0) {
								log.info("Inside record exists");
								SubscriptionDetail entity = new SubscriptionDetail();
								entity = repo.querySubPricingDtls(row_no,
										crListDtls.getType(),
										crPrices.getParamName());

								entity.setCountryName(crPrices.getCountryName());
								entity.setParamValue(crPrices.getParamValue());
								entity.setNetworkName(crPrices.getNetworkName());
								entity.setSubscriptionLevel(crListDtls
										.getLevel());
								entity.setMargin(crPrices.getMargin());
								entity.setSubscriptionCategory("P");

								// Updating the record
								repo.merge(entity);
								ERROR = ERROR_CODE.SS_SUBPR_UD;
								entity = null;
							}
							// saving the subscription details
							else {
								if (repo.findSubDtlsRecordExist(row_no,
										crListDtls.getType(),
										crPrices.getParamName()) == 0) {
									log.info("Inside save");
									SubscriptionDetail entity = new SubscriptionDetail();
									SubscriptionDetailPK entityPk = new SubscriptionDetailPK();
									entityPk.setParamName(crPrices
											.getParamName());
									entityPk.setSubscriptionId(row_no);
									entityPk.setSubscriptionType(crListDtls
											.getType());
									entity.setId(entityPk);
									entity.setCountryName(crPrices
											.getCountryName());
									entity.setParamValue(crPrices
											.getParamValue());
									entity.setNetworkName(crPrices
											.getNetworkName());
									entity.setSubscriptionLevel(crListDtls
											.getLevel());
									entity.setMargin(crPrices.getMargin());
									entity.setSubscriptionCategory("P");
									// saving the record
									log.debug("ParamName::"
											+ entityPk.getParamName());
									log.debug("row_no::" + row_no);
									log.debug("CountryName::"
											+ crPrices.getCountryName());
									log.debug("ParamValue::"
											+ crPrices.getParamValue());
									log.debug("Type::" + crListDtls.getType());
									log.debug("Level::" + crListDtls.getLevel());
									log.debug("Margin::" + crPrices.getMargin());

									repo.save(entity);
									ERROR = ERROR_CODE.SS_SUBPR_SV;
									entity = null;
									entityPk = null;
								}
							}
						}
					}
				}
				// calling API to inserting network details for reseller
				if (crListDtls.getLevel().equalsIgnoreCase("N")) {
					//
				}
			}
		} else {
			ERROR = ERROR_CODE.ES_SV_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"SubscriptionPriceService", "Create SubPricing DtlsRes", "",
				ERROR);
		res.setHeader(header);
		return res;
	}

	// Delete all the price details
	@Transactional
	public DeleteSubPricesDtlsRes deleteSubAllPrices(DeleteSubPricesDtlsReq req) {
		DeleteSubPricesDtlsRes res = new DeleteSubPricesDtlsRes();
		DLPRICESLIST dlPRicesList = req.getDLPRICESLIST();
		if (dlPRicesList != null) {
			if (!StringUtils.isNotEmpty(dlPRicesList.getSubId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"SubscriptionPriceService",
						"Create SubPricing DtlsRes", "",
						ERROR_CODE.SS_SUBPR_ER1);
				res.setHeader(header);
				return res;
			}
			if (!StringUtils.isNotEmpty(dlPRicesList.getSubLevel())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"SubscriptionPriceService",
						"Delete All SubPricing Dtls", "",
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
				int returnCount = repo.delteAllPrices(
						dlPRicesList.getSubLevel(), dlPRicesList.getSubId());
				if (returnCount > 0) {
					ERROR = ERROR_CODE.SS_SUBPR_DL;
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

	public List<QRPRICELIST> querySubscribtionDetails() {
		log.info("Inside Subscription Price Service -> querySubscribtionDetails");
		List<SubscriptionDetail> entityList = repo.querySubPricingList();
		List<QRPRICELIST> qrPriceList = new ArrayList<QRPRICELIST>();
		String newCountry = null;
		String prevCountry = null;
		QRPRICELIST qrPriceDtls = null;
		if (entityList.size() > 0) {
			log.info("entityList::" + entityList);
			for (SubscriptionDetail entity : entityList) {
				newCountry = entity.getCountryName();
				if (!newCountry.equals(prevCountry)) {
					qrPriceDtls = null;
					qrPriceDtls = new QRPRICELIST();
					qrPriceDtls.setSubId(entity.getCountryName());
					qrPriceDtls.setSubLevel(entity.getSubscriptionLevel());
				}
				try {
					QRYPRICES qrPrices = new QRYPRICES();
					qrPrices.setType(entity.getId().getSubscriptionType());
					qrPrices.setRowId(entity.getId().getSubscriptionId());
					qrPrices.setParamName(SubscriptionServicesEnum.valueOf(
							entity.getId().getParamName().toUpperCase())
							.getServiceCode());
					qrPrices.setMargin(SubscriptionServicesEnum.valueOf(
							entity.getId().getParamName().toUpperCase())
							.getMargin());
					qrPrices.setParamValue(entity.getParamValue());
					qrPriceDtls.getQRYPRICES().add(qrPrices);
					if (!newCountry.equals(prevCountry)) {
						qrPriceList.add(qrPriceDtls);

					}
					prevCountry = newCountry;
				} catch (Exception e) {

				}
			}

		}
		return qrPriceList;
	}

	public List<QRPRICELIST> queryResellerSubscribtionDetails(String networkId) {
		log.info("Inside Subscription Price Service -> queryResellerSubscribtionDetails");
		log.debug("networkId::" + networkId);
		String newType = null;
		String prevType = null;
		List<Map<String, Object>> defaultList = repo
				.queryResellerPriceDtls(networkId);
		List<QRPRICELIST> qrPriceList = new ArrayList<QRPRICELIST>();
		QRPRICELIST qrPriceDtls = new QRPRICELIST();
		if (defaultList.size() > 0) {
			log.info("entityList::" + defaultList);
			for (Map<String, Object> map : defaultList) {
				newType = networkId;
				if (!newType.equals(prevType)) {
					qrPriceDtls = null;
					qrPriceDtls = new QRPRICELIST();
					qrPriceDtls.setSubId(networkId);
					qrPriceDtls.setSubLevel("N");
				}
				try {
					QRYPRICES qrPrices = new QRYPRICES();
					if (map.get("param_name") != null) {
						qrPrices.setParamName(SubscriptionServicesEnum.valueOf(
								map.get("param_name").toString().toUpperCase())
								.getServiceCode());
					}
					if (map.get("param_value") != null) {
						qrPrices.setParamValue(map.get("param_value")
								.toString().toUpperCase());
					}
					if (map.get("margin") != null) {
						qrPrices.setMargin(SubscriptionServicesEnum.valueOf(
								map.get("margin").toString().toUpperCase())
								.getServiceCode());
					}
					if (map.get("subscription_type") != null) {
						qrPrices.setType(map.get("subscription_type")
								.toString());

					}
					if (map.get("subscription_id") != null) {
						qrPrices.setRowId(Integer.parseInt(map.get(
								"subscription_id").toString()));

					}
					qrPriceDtls.getQRYPRICES().add(qrPrices);
					if (!newType.equals(prevType)) {
						qrPriceList.add(qrPriceDtls);

					}
					prevType = newType;
				} catch (Exception e) {

				}
			}

		}
		return qrPriceList;
	}

	public Map<String, String> queryNetworkPrices(String networkId) {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, Object>> recordList = repo
				.queryNetworkPriceDtls(networkId);
		for (Map<String, Object> pricemap : recordList) {
			map.put(pricemap.get("param_name").toString(),
					pricemap.get("param_value").toString());
		}
		return map;
	}
}
