package com.iexceed.esoko.approval.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.LibraryImageRepo;
import com.iexceed.esoko.domain.dao.LibraryRepo;
import com.iexceed.esoko.domain.dao.LocationRepo;
import com.iexceed.esoko.domain.dao.MarketDetailsRepo;
import com.iexceed.esoko.domain.dao.MeasureFactorRepo;
import com.iexceed.esoko.domain.dao.MeasureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao2.AgentUploadCountRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.UploadMasterRepo;
import com.iexceed.esoko.domain.dao3.AlertLocationRepo;
import com.iexceed.esoko.domain.dao3.AlertPriceTypeRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.BidsOffersMasterRepo;
import com.iexceed.esoko.domain.dao3.PriceUploadMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.Library;
import com.iexceed.esoko.domain.entity.LibraryImage;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.MarketDetail;
import com.iexceed.esoko.domain.entity.MeasureFactor;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity2.AgentUploadCount;
import com.iexceed.esoko.domain.entity2.AgentUploadCountPK;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.entity3.AlertPriceType;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.BidsOffersMaster;
import com.iexceed.esoko.domain.entity3.PriceUploadMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.approval.APPROVALSERV;
import com.iexceed.esoko.jaxb.approval.APPRVLDTLS;
import com.iexceed.esoko.jaxb.approval.BIDSANDOFFERSAPPRVL;
import com.iexceed.esoko.jaxb.approval.BIDSANDOFFERSDTLS;
import com.iexceed.esoko.jaxb.approval.BidsAndOffersApprovalReq;
import com.iexceed.esoko.jaxb.approval.BidsAndOffersApprovalRes;
import com.iexceed.esoko.jaxb.approval.DELNWSLIB;
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibReq;
import com.iexceed.esoko.jaxb.approval.DeleteNewsLibRes;
import com.iexceed.esoko.jaxb.approval.EDITPRICE;
import com.iexceed.esoko.jaxb.approval.EditPriceReq;
import com.iexceed.esoko.jaxb.approval.EditPriceRes;
import com.iexceed.esoko.jaxb.approval.Header;
import com.iexceed.esoko.jaxb.approval.LIBRARYAPPRVL;
import com.iexceed.esoko.jaxb.approval.LOCAPPROVALSERVC;
import com.iexceed.esoko.jaxb.approval.LibraryApprovalReq;
import com.iexceed.esoko.jaxb.approval.LibraryApprovalRes;
import com.iexceed.esoko.jaxb.approval.LocationApprovalReq;
import com.iexceed.esoko.jaxb.approval.LocationApprovalRes;
import com.iexceed.esoko.jaxb.approval.PEOPLEAPPROVAL;
import com.iexceed.esoko.jaxb.approval.PRICEAPPRVL;
import com.iexceed.esoko.jaxb.approval.PRICEDTLS;
import com.iexceed.esoko.jaxb.approval.PriceApprovalReq;
import com.iexceed.esoko.jaxb.approval.PriceApprovalRes;
import com.iexceed.esoko.jaxb.approval.QRBIDSOFFRSAPPRV;
import com.iexceed.esoko.jaxb.approval.QRLOCAPPRV;
import com.iexceed.esoko.jaxb.approval.QRNWIBAPPRV;
import com.iexceed.esoko.jaxb.approval.QRNWLIBBYID;
import com.iexceed.esoko.jaxb.approval.QRPPPLAPPRV;
import com.iexceed.esoko.jaxb.approval.QRPRICEAPPRV;
import com.iexceed.esoko.jaxb.approval.QueryBidsOfferApprvlRes;
import com.iexceed.esoko.jaxb.approval.QueryLocationsApprvlRes;
import com.iexceed.esoko.jaxb.approval.QueryNewsAndLibApprvlRes;
import com.iexceed.esoko.jaxb.approval.QueryNewsLibByIdRes;
import com.iexceed.esoko.jaxb.approval.QueryPeopleApprvlRes;
import com.iexceed.esoko.jaxb.approval.QueryPriceApprvlRes;
import com.iexceed.esoko.jaxb.approval.RecordStsApprovalReq;
import com.iexceed.esoko.jaxb.approval.RecordStsApprovalRes;
import com.iexceed.esoko.sch.job.PushAlertJob;
import com.iexceed.esoko.sch.trg.TriggerDetails;
import com.iexceed.esoko.sch.trg.TriggerHandler;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class ApprovalService {

	@Autowired
	private UploadMasterRepo uploadmasRepo;
	@Autowired
	private PeopleRepo pplRepo;
	@Autowired
	private LibraryImageRepo libRepo;
	@Autowired
	private MeasureRepo msreRepo;
	@Autowired
	private CurrencyRepo currRepo;
	@Autowired
	private SystemUserRepo sysRepo;
	@Autowired
	private LocationRepo locRepo;
	@Autowired
	private MarketDetailsRepo marRepo;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private LibraryRepo libraryRepo;
	@Autowired
	private AgentUploadCountRepo agntUpldCntRepo;
	@Autowired
	private BidsOffersMasterRepo bidsAndOfferRepo;
	@Autowired
	private AlertLocationRepo alrtLocRepo;
	@Autowired
	private PushAlertMasterRepo pushMastRepo;
	@Autowired
	private PriceUploadMasterRepo priceUpldMstrRepo;
	@Autowired
	AlertProfileBasedRepo prflBsdrepo;
	@Autowired
	ForexRepo forexRepo;
	@Autowired
	AlertPriceTypeRepo priceTypeRepo;
	@Autowired
	MeasureFactorRepo factorRepo;
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;

	public static Logger log = LoggerUtils.getLogger();

	public QueryNewsAndLibApprvlRes queryNewsAndLibraryApprvl(String networkId,
			String SortBy) {
		QueryNewsAndLibApprvlRes res = new QueryNewsAndLibApprvlRes();

		List<QRNWIBAPPRV> qrnwlibList = new ArrayList<QRNWIBAPPRV>();

		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) uploadmasRepo
					.queryNewsAndLibraryApproval(networkId, SortBy);
			if (list.size() != 0) {
				for (Map<String, Object> libnwsloop : list) {
					QRNWIBAPPRV qrnw = new QRNWIBAPPRV();
					qrnw.setUploadId(libnwsloop.get("upload_id").toString());
					qrnw.setApplicationId(libnwsloop.get("application_id")
							.toString());
					qrnw.setHeadline(libnwsloop.get("headline").toString());
					qrnw.setUploadDate(Utils.getDDMMMYYFormat(
							libnwsloop.get("upload_date").toString(), 0));
					qrnw.setAgentId(libnwsloop.get("agent_id").toString());

					People people = new People();
					PeoplePK peoplepk = new PeoplePK();
					peoplepk.setDefaultNetworkId(networkId);
					peoplepk.setPeopleId(libnwsloop.get("agent_id").toString());
					people.setId(peoplepk);

					People people1 = pplRepo.findOne(people, peoplepk);
					if (people1 != null) {
						if (people1.getLastName() != null) {

							qrnw.setAgentName(people1.getFirstName() + " "
									+ people1.getLastName());
						} else {
							qrnw.setAgentName(people1.getFirstName());
						}

					}
					qrnwlibList.add(qrnw);
					qrnw = null;
				}

				res.getQRNWIBAPPRV().addAll(qrnwlibList);
				header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
						"ApprovalService", "queryNewsAndLibraryApprvl", "",
						ERROR_CODE.ES_SC_001);

			}

			else {
				header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
						"ApprovalService", "queryNewsAndLibraryApprvl", "",
						ERROR_CODE.ES_NR_001);
			}

		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
					"ApprovalService", "queryNewsAndLibraryApprvl", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}

		res.setHeader(header);
		return res;

	}

	public QueryNewsLibByIdRes queryNewsLibById(String libId) {

		QueryNewsLibByIdRes res = new QueryNewsLibByIdRes();

		try {
			Library userEntity = uploadmasRepo.queryNewsLibById(libId);
			if (userEntity == null) {
				ERROR = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {
				QRNWLIBBYID qrnw = new QRNWLIBBYID();
				qrnw.setLibId(userEntity.getLibId());
				if (userEntity.getCategoryId() != null) {
					qrnw.setCategory(userEntity.getCategoryId());
				}
				if (userEntity.getTitle() != null) {
					qrnw.setTitle(userEntity.getTitle());
				}
				if (userEntity.getSummary() != null) {
					qrnw.setShortDesc(userEntity.getSummary());
				}
				if (userEntity.getStory() != null) {
					qrnw.setStory(userEntity.getStory());
				}
				if (userEntity.getSourceUrl() != null) {
					qrnw.setLink(userEntity.getSourceUrl());
				}
				if (userEntity.getAuthor() != null) {
					qrnw.setAuthor(userEntity.getAuthor());
				}

				if (userEntity.getCreatedTs() != null) {
					qrnw.setDate(Utils.getDateFormater1(userEntity
							.getCreatedTs().toString(), 0));
				}

				LibraryImage lib = null;
				lib = libRepo.queryNwrPic(libId);
				if (lib != null) {
					qrnw.setPic(lib.getContent());
				}
				res.setQRNWLIBBYID(qrnw);

				ERROR = ERROR_CODE.ES_SC_001;

			}
		} catch (NoResultException e) {
			return null;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "queryNewsLibById", "", ERROR);
		res.setHeader(header);

		return res;

	}

	@Transactional
	public DeleteNewsLibRes deletenewslib(DeleteNewsLibReq req) {
		DELNWSLIB delnews = req.getDELNWSLIB();
		DeleteNewsLibRes res = new DeleteNewsLibRes();
		int returnCount = 0;
		if (delnews != null) {

			returnCount = uploadmasRepo.delteNewsLib(delnews.getUploadId(),
					delnews.getApplicationId());

			if (returnCount > 0) {
				if (delnews.getApplicationId().toString()
						.equalsIgnoreCase("newsAndLibrary")) {
					ERROR = ERROR_CODE.NWS_LIB_DEL_SC;
				} else if (delnews.getApplicationId().toString()
						.equalsIgnoreCase("Offers")) {
					ERROR = ERROR_CODE.NWS_OFF_DEL_SC;
				} else if (delnews.getApplicationId().toString()
						.equalsIgnoreCase("Prices")) {
					ERROR = ERROR_CODE.NWS_PRC_DEL_SC;
				} else {
					ERROR = ERROR_CODE.NWS_PPL_DEL_SC;
				}
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} else {
			ERROR = ERROR_CODE.DM_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "deletenewslib", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryBidsOfferApprvlRes querybidsNwsapprv(String networkId,
			String sortBy) {

		QueryBidsOfferApprvlRes res = new QueryBidsOfferApprvlRes();
		List<QRBIDSOFFRSAPPRV> qrbidoffapr = new ArrayList<QRBIDSOFFRSAPPRV>();

		String name = null;

		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) uploadmasRepo
					.queryBidsndOffersApproval(networkId, sortBy);
			if (list.size() == 0) {
				ERROR = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {
				for (Map<String, Object> libnwsloop : list) {
					QRBIDSOFFRSAPPRV qrnw = new QRBIDSOFFRSAPPRV();
					int expire = 0;
					qrnw.setUploadId(libnwsloop.get("upload_id").toString());
					if (libnwsloop.get("commodity") != null) {
						qrnw.setCommodity(libnwsloop.get("commodity")
								.toString());
					}
					if (libnwsloop.get("createdTs") != null) {
						qrnw.setDate(Utils.getDDMMMYYFormat(
								libnwsloop.get("createdTs").toString(), 0));
					}
					if (libnwsloop.get("uploadMode") != null) {
						qrnw.setMode(libnwsloop.get("uploadMode").toString());
					}
					if (libnwsloop.get("expiryDate") != null) {
						expire = Integer.parseInt(libnwsloop.get("expiryDate")
								.toString());
					}
					if (libnwsloop.get("location") != null) {
						qrnw.setLocation(libnwsloop.get("location").toString());
					}
					if (libnwsloop.get("grade") != null) {
						qrnw.setGrade(libnwsloop.get("grade").toString());
					}
					if (libnwsloop.get("payment_details") != null) {
						qrnw.setPaymentDetails(libnwsloop
								.get("payment_details").toString());
					}
					if (libnwsloop.get("delivery_point") != null) {
						qrnw.setDeliveryPoint(libnwsloop.get("delivery_point")
								.toString());
					}
					if (libnwsloop.get("delivery_type") != null) {
						if (libnwsloop.get("delivery_type").toString()
								.equalsIgnoreCase("I")) {
							qrnw.setDeliveryBy("Immediately");
						} else {
							if (libnwsloop.get("noOfDays") != null)

							{
								Integer noofdays = null;
								noofdays = Integer.parseInt(libnwsloop.get(
										"noOfDays").toString());
								if (noofdays == 1) {
									qrnw.setDeliveryBy(libnwsloop.get(
											"noOfDays").toString()
											+ " day");
								}

								else {
									qrnw.setDeliveryBy(libnwsloop.get(
											"noOfDays").toString()
											+ " days");
								}

							}
						}
					}
					if (expire < 0) {
						qrnw.setExpiresBy("expired");
					}

					else if (expire == 1) {
						qrnw.setExpiresBy("Tommorrow");

					}

					else {
						qrnw.setExpiresBy(libnwsloop.get("expiryDate")
								.toString() + " days");
					}
					qrnw.setOwnerUserId(libnwsloop.get("offerUsedId")
							.toString());

					if (libnwsloop.get("offerUploadedBy") != null) {
						qrnw.setUploadedUserId(libnwsloop
								.get("offerUploadedBy").toString());
					}

					qrnw.setQuantity(libnwsloop.get("quantity").toString()
							+ " " + libnwsloop.get("measure").toString());

					if (libnwsloop.get("negotiable").toString()
							.equalsIgnoreCase("N")) {
						qrnw.setPrice(libnwsloop.get("price_amount").toString()
								+ " "
								+ libnwsloop.get("currency_id").toString()
								+ "/" + libnwsloop.get("amountUnit").toString());
					}

					else {
						qrnw.setPrice("Negotiable");
					}

					if (libnwsloop.get("offerUploadedBy") == null) {

						People ppl = new People();
						PeoplePK pplpk = new PeoplePK();

						pplpk.setDefaultNetworkId(networkId);
						pplpk.setPeopleId(libnwsloop.get("offerUsedId")
								.toString());
						ppl.setId(pplpk);
						People ppl1 = pplRepo.findOne(ppl, pplpk);

						if (ppl1 != null) {
							if (ppl1.getFirstName() != null) {
								if (ppl1.getLastName() != null) {
									qrnw.setUser(ppl1.getFirstName() + " "
											+ ppl1.getLastName());
								} else {
									qrnw.setUser(ppl1.getFirstName());
								}
							}
						}

						else {
							SystemUser sysusr = new SystemUser();
							sysusr.setUserId(libnwsloop.get("offerUsedId")
									.toString());
							sysusr = sysRepo.findOne(sysusr,
									libnwsloop.get("offerUsedId").toString());
							if (sysusr != null) {
								if (sysusr.getFirstName() != null) {
									if (sysusr.getLastName() != null) {
										qrnw.setUser(sysusr.getFirstName()
												+ " " + sysusr.getLastName());
									} else {
										qrnw.setUser(sysusr.getFirstName());
									}
								}
							}
						}

					}

					else {
						People ppl = new People();
						PeoplePK pplpk = new PeoplePK();

						pplpk.setDefaultNetworkId(networkId);
						pplpk.setPeopleId(libnwsloop.get("offerUploadedBy")
								.toString());
						ppl.setId(pplpk);
						People ppl1 = pplRepo.findOne(ppl, pplpk);
						if (ppl1 != null) {
							if (ppl1.getFirstName() != null) {
								if (ppl1.getLastName() != null) {
									name = ppl1.getFirstName() + " "
											+ ppl1.getLastName();
								} else {
									name = ppl1.getFirstName();
								}
							}
						}

						else {
							name = "unkwown";
						}

						People ppl2 = new People();
						PeoplePK pplpk2 = new PeoplePK();

						pplpk2.setDefaultNetworkId(networkId);
						pplpk2.setPeopleId(libnwsloop.get("offerUsedId")
								.toString());
						ppl2.setId(pplpk2);
						ppl2 = pplRepo.findOne(ppl2, pplpk2);

						if (ppl2 != null) {
							if (ppl2.getFirstName() != null) {
								if (ppl2.getLastName() != null) {
									qrnw.setUser(name + " uploaded for "
											+ ppl2.getFirstName() + " "
											+ ppl2.getLastName());
								} else {
									qrnw.setUser(name + " uploaded for "
											+ ppl2.getFirstName());
								}
							}
						}

						else {
							SystemUser sysusr1 = new SystemUser();
							sysusr1.setUserId(libnwsloop.get("offerUsedId")
									.toString());
							sysusr1 = sysRepo.findOne(sysusr1,
									libnwsloop.get("offerUsedId").toString());
							if (sysusr1 != null) {
								if (sysusr1.getFirstName() != null) {
									if (sysusr1.getLastName() != null) {
										qrnw.setUser(name + " uploaded for "
												+ sysusr1.getFirstName() + " "
												+ sysusr1.getLastName());
									} else {
										qrnw.setUser(name + " uploaded for "
												+ sysusr1.getFirstName());
									}
								}
							}
						}

					}

					qrbidoffapr.add(qrnw);
					qrnw = null;
				}

				res.getQRBIDSOFFRSAPPRV().addAll(qrbidoffapr);
				ERROR = ERROR_CODE.ES_SC_001;
			}

		} catch (NoResultException e) {
			return null;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "querybidsNwsapprv", "", ERROR);
		res.setHeader(header);

		return res;

	}

	public QueryPriceApprvlRes queryPricesApprvl(String networkId, String SortBy) {
		QueryPriceApprvlRes res = new QueryPriceApprvlRes();

		List<QRPRICEAPPRV> qrprice = new ArrayList<QRPRICEAPPRV>();

		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) uploadmasRepo
					.queryPricesApprvl(networkId, SortBy);
			if (list.size() != 0) {
				for (Map<String, Object> libnwsloop : list) {
					QRPRICEAPPRV qrnw = new QRPRICEAPPRV();
					qrnw.setUploadId(libnwsloop.get("upload_id").toString());

					if (libnwsloop.get("commodity") != null) {
						qrnw.setCommodity(libnwsloop.get("commodity")
								.toString());
					}
					if (libnwsloop.get("market") != null) {
						qrnw.setMarket(libnwsloop.get("market").toString());
					}
					if (libnwsloop.get("uploadMode") != null) {
						qrnw.setMode(libnwsloop.get("uploadMode").toString());
					}
					if (libnwsloop.get("collectedOn") != null) {
						qrnw.setCollectedOn(Utils.getDDMMMYYFormat(libnwsloop
								.get("collectedOn").toString(), 0));
					}
					if (libnwsloop.get("agentId") != null) {
						qrnw.setUserId(libnwsloop.get("agentId").toString());
					}
					if (libnwsloop.get("priceType") != null) {
						qrnw.setPriceType(libnwsloop.get("priceType")
								.toString());
					}
					if (libnwsloop.get("uploadedDate") != null) {
						qrnw.setUploadedDate(Utils.getDDMMMYYFormat(libnwsloop
								.get("uploadedDate").toString(), 0));
					}

					if (libnwsloop.get("measureId") != null) {
						qrnw.setMeasure(libnwsloop.get("measureId").toString());
					}

					if (libnwsloop.get("baseMeasureId") != null) {
						qrnw.setBaseMeasureID(libnwsloop.get("baseMeasureId")
								.toString());
					}
					if (libnwsloop.get("quoteMeasureId") != null) {
						qrnw.setQuoteMeasureId(libnwsloop.get("quoteMeasureId")
								.toString());
					}

					if (libnwsloop.get("baseMeasureName") != null) {
						qrnw.setBaseMeasureName(libnwsloop.get(
								"baseMeasureName").toString());
					}

					if (libnwsloop.get("quoteMeasureName") != null) {
						qrnw.setQuoteMeasureName(libnwsloop.get(
								"quoteMeasureName").toString());
					}

					if (libnwsloop.get("price") != null) {
						qrnw.setNewPrice(libnwsloop.get("symbol").toString()
								+ " " + libnwsloop.get("price").toString());
						qrnw.setPrice(libnwsloop.get("price").toString());
						qrnw.setCurrency(libnwsloop.get("currency_id")
								.toString());
					}
					if (libnwsloop.get("weight") != null) {
						qrnw.setWeight(libnwsloop.get("weight").toString()
								+ " "
								+ libnwsloop.get("weightMeasure").toString());
						qrnw.setConversion(libnwsloop.get("weight").toString());
						qrnw.setConversionMeasure(libnwsloop.get(
								"weightMeasure").toString());
					}

					if (libnwsloop.get("old_price") != null) {
						qrnw.setOldPrice(libnwsloop.get("old_price").toString());
					}
					if (libnwsloop.get("change") != null) {
						qrnw.setChange(libnwsloop.get("change").toString());
					}

					if (libnwsloop.get("uploadGis") != null) {
						qrnw.setGpsVariance(libnwsloop.get("uploadGis")
								.toString() + "Km");
					}
					if (libnwsloop.get("changePrice") != null) {
						qrnw.setPriceTypeComparison(libnwsloop.get(
								"changePrice1").toString());
					}
					if (libnwsloop.get("comments") != null) {
						qrnw.setComments(libnwsloop.get("comments").toString());
					}

					People people = new People();
					PeoplePK peoplepk = new PeoplePK();
					peoplepk.setDefaultNetworkId(networkId);
					if (libnwsloop.get("agentId") != null) {
						peoplepk.setPeopleId(libnwsloop.get("agentId")
								.toString());
						people.setId(peoplepk);
					}
					People people1 = null;
					people1 = pplRepo.findOne(people, peoplepk);
					if (people1 != null) {
						if (people1.getLastName() != null) {

							qrnw.setUserName(people1.getFirstName() + " "
									+ people1.getLastName());
						} else {
							qrnw.setUserName(people1.getFirstName());
						}

					}

					qrprice.add(qrnw);
					qrnw = null;
				}

				res.getQRPRICEAPPRV().addAll(qrprice);
				header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
						"ApprovalService", "queryPricesApprvl", "",
						ERROR_CODE.ES_SC_001);

			}

			else {
				header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
						"ApprovalService", "queryPricesApprvl", "",
						ERROR_CODE.ES_NR_001);
			}

		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
					"ApprovalService", "queryPricesApprvl", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}

		res.setHeader(header);

		return res;

	}

	public QueryLocationsApprvlRes queryLocationsApprv(String sortBy) {

		QueryLocationsApprvlRes res = new QueryLocationsApprvlRes();
		List<QRLOCAPPRV> qrloc = new ArrayList<QRLOCAPPRV>();

		try {
			List<Location> userEntity = locRepo.queryUnauthLocs(sortBy);
			if (userEntity == null) {
				ERROR = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {

				for (Location libnwsloop : userEntity) {

					QRLOCAPPRV qrnw1 = new QRLOCAPPRV();

					qrnw1.setLocationId(libnwsloop.getLocationId());
					if (libnwsloop.getType() != null)

					{
						qrnw1.setLocationType(libnwsloop.getType());
					}

					if (libnwsloop.getParentCountry() != null)

					{
						qrnw1.setCountry(libnwsloop.getParentCountry());
					}

					if (libnwsloop.getCreatedTs() != null)

					{
						qrnw1.setUploaddate(Utils.getDDMMMYYFormat(libnwsloop
								.getCreatedTs().toString(), 0));

					}

					if (libnwsloop.getCreatedBy() != null)

					{
						qrnw1.setRequestor(libnwsloop.getCreatedBy());
						SystemUser sysusr1 = new SystemUser();
						sysusr1.setUserId(libnwsloop.getCreatedBy());
						sysusr1 = sysRepo.findOne(sysusr1,
								libnwsloop.getCreatedBy());
						if (sysusr1 != null) {
							if (sysusr1.getFirstName() != null) {
								if (sysusr1.getLastName() != null) {
									qrnw1.setRequestorname(sysusr1
											.getFirstName()
											+ " "
											+ sysusr1.getLastName());
								} else {
									qrnw1.setRequestorname(sysusr1
											.getFirstName());
								}
							}
						}
						if (sysusr1.getDefaultNetworkId() != null) {
							qrnw1.setNetworkId(sysusr1.getDefaultNetworkId());
							Network nwk = nwkRepo.queryNetworkUserInfo(sysusr1
									.getDefaultNetworkId());
							qrnw1.setNetworkName(nwk.getName());
						}

					}
					qrnw1.setUploadmode("W");

					if (libnwsloop.getType().toString().equalsIgnoreCase("M")) {
						MarketDetail temp = marRepo.queryMarketDtls(libnwsloop
								.getLocationId());
						if (temp != null) {
							if (temp.getCommodities() != null)

							{
								qrnw1.setCommodities(temp.getCommodities());
							}

							if (temp.getDaysOfWeek() != null)

							{
								qrnw1.setMarkdetDays(temp.getDaysOfWeek());
							}

							if (temp.getLocationPic() != null)

							{
								qrnw1.setMarketPic(temp.getLocationPic());
							}
						}
					}
					qrnw1.setLongitude(Double.toString(libnwsloop.getGis()
							.getCoordinates()[0].y));
					qrnw1.setLatitude(Double.toString(libnwsloop.getGis()
							.getCoordinates()[0].x));
					if (libnwsloop.getParentId() != null
							&& libnwsloop.getParentId() != null) {
						qrnw1.setLocationTree(libnwsloop.getLocationId() + ","
								+ libnwsloop.getParentId() + ","
								+ libnwsloop.getParentCountry());
					}

					qrloc.add(qrnw1);
				}
				res.getQRLOCAPPRV().addAll(qrloc);

				ERROR = ERROR_CODE.ES_SC_001;

			}
		} catch (NoResultException e) {
			return null;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "queryLocationsApprv", "", ERROR);
		res.setHeader(header);

		return res;

	}

	@Transactional
	public LocationApprovalRes locationApproval(LocationApprovalReq req) {
		log.info("Inside locationApprovalRes -> locationApprovalRes");
		LocationApprovalRes res = new LocationApprovalRes();
		LOCAPPROVALSERVC approvalDtls = req.getLOCAPPROVALSERVC();
		ERROR = ERROR_CODE.DM_SV_002;
		try {
			int count = 0;

			count = locRepo.approveLocation(approvalDtls.getLocationId(),
					approvalDtls.getApprovalFlag());
			if (approvalDtls.getApprovalFlag().toString().equalsIgnoreCase("A")) {
				ERROR = ERROR_CODE.LOC_APPRVL_SC;
			} else {
				ERROR = ERROR_CODE.LOC_REJ_SC;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_003;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "queryLocationsApprv", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryPeopleApprvlRes queryPplApproval(String networkId, String sortBy) {
		QueryPeopleApprvlRes res = new QueryPeopleApprvlRes();
		log.info("Inside ApprovalService -> queryPplApproval");
		List<Map<String, Object>> recordList = pplRepo.queryPplApproval(
				networkId, sortBy);
		if (recordList != null) {
			if (recordList.size() == 0) {
				ERROR = ERROR_CODE.ES_NR_001;
				header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
						"ApprovalService", "queryPplApproval", "", ERROR);
				res.setHeader(header);
				return res;
			}
			for (Map<String, Object> recordMap : recordList) {
				log.debug("PeopleId" + recordMap.get("peopleId"));
				log.debug("firstName" + recordMap.get("firstName"));
				log.debug("lastName" + recordMap.get("lastName"));
				log.debug("msisdn" + recordMap.get("msisdn"));
				log.debug("createdBy" + recordMap.get("createdBy"));
				log.debug("mode" + recordMap.get("mode"));
				log.debug("createdTs" + recordMap.get("createdTs"));
				log.debug("gender" + recordMap.get("gender"));
				log.debug("birthYear" + recordMap.get("birthYear"));
				log.debug("location" + recordMap.get("location"));
				log.debug("commodities" + recordMap.get("commodities"));
				log.debug("occupations" + recordMap.get("occupations"));

				QRPPPLAPPRV qrppplapprv = new QRPPPLAPPRV();
				if (recordMap != null) {
					qrppplapprv.setPeopleId((String) recordMap.get("peopleId"));
					if ((recordMap.get("firstName")) != null) {
						StringBuffer name = new StringBuffer(
								(String) recordMap.get("firstName"));
						if (recordMap.get("lastName") != null) {
							name.append(" "
									+ (String) recordMap.get("lastName"));
						}
						qrppplapprv.setName(name.toString());
					}
					if (recordMap.get("msisdn") != null) {
						qrppplapprv.setPhone((String) recordMap.get("msisdn"));
					}
					if (recordMap.get("createdBy") != null) {
						qrppplapprv
								.setUser((String) recordMap.get("createdBy"));
						PeoplePK pk = new PeoplePK();
						pk.setPeopleId((String) recordMap.get("createdBy"));
						pk.setDefaultNetworkId(networkId);
						People people = pplRepo.findOne(People.class, pk);
						if (people != null) {
							if (people.getFirstName() != null) {
								StringBuffer name = new StringBuffer(
										people.getFirstName());
								if (people.getLastName() != null) {
									name.append(" " + people.getLastName());
								}
								qrppplapprv.setUsername(name.toString());
							}
						}

					}

					if (recordMap.get("mode") != null) {
						qrppplapprv.setMode((String) recordMap.get("mode"));
					}
					if (recordMap.get("createdTs") != null) {
						qrppplapprv.setDate(Utils.getDDMMMYYFormat(recordMap
								.get("createdTs").toString(), 0));
					}
					if (recordMap.get("gender") != null) {
						qrppplapprv.setGender((String) recordMap.get("gender"));
					}
					if (recordMap.get("birthYear") != null) {
						qrppplapprv.setBirthyear((String) recordMap
								.get("birthYear"));
					}
					if (recordMap.get("location") != null) {
						qrppplapprv.setLocation((String) recordMap
								.get("location"));
					}
					if (recordMap.get("commodities") != null) {
						qrppplapprv.setCommodity((String) recordMap
								.get("commodities"));
					}
					if (recordMap.get("occupations") != null) {
						qrppplapprv.setOccupation((String) recordMap
								.get("occupations"));
					}

					res.getQRPPPLAPPRV().add(qrppplapprv);
				}

			}
			ERROR = ERROR_CODE.ES_SC_001;

		}
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "queryPplApproval", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public RecordStsApprovalRes pplApproval(RecordStsApprovalReq req) {
		log.info("Inside ApprovalService -> pplApproval");
		RecordStsApprovalRes res = new RecordStsApprovalRes();
		APPROVALSERV approvalserv = req.getAPPROVALSERVC();
		log.debug("NetworkId:" + approvalserv.getNetworkId());
		List<PEOPLEAPPROVAL> pplApprvlList = approvalserv.getPEOPLEAPPROVAL();
		for (PEOPLEAPPROVAL pplApprval : pplApprvlList) {
			log.debug("PeopleId:" + pplApprval.getPeopleId());
			log.debug("Approval Flag:" + pplApprval.getApprovalFlag());
			if (pplApprval.getApprovalFlag().equalsIgnoreCase("R")) {
				PeoplePK pk = new PeoplePK();
				pk.setPeopleId(pplApprval.getPeopleId());
				pk.setDefaultNetworkId(approvalserv.getNetworkId());
				People people = new People();
				people.setId(pk);
				People people1 = pplRepo.findOne(people, pk);
				if (pplRepo.exists(people, pk)) {
					pplRepo.delete(people1);
				}

			} else if (pplApprval.getApprovalFlag().equalsIgnoreCase("A")) {
				PeoplePK pk = new PeoplePK();
				pk.setPeopleId(pplApprval.getPeopleId());
				pk.setDefaultNetworkId(approvalserv.getNetworkId());
				People people = new People();
				people.setId(pk);
				if (pplRepo.exists(people, pk)) {
					pplRepo.updateRcrdSts(pplApprval.getPeopleId(),
							approvalserv.getNetworkId());
				}

			}
		}
		ERROR = ERROR_CODE.PPL_APPRVL;
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "pplApproval", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public LibraryApprovalRes libraryApproval(LibraryApprovalReq req) {
		log.info("Inside ApprovalService -> libraryApproval");
		LibraryApprovalRes res = new LibraryApprovalRes();
		LIBRARYAPPRVL libApprvl = req.getLIBRARYAPPRVL();
		log.debug("NetworkId:" + libApprvl.getNetworkId());
		log.debug("User id:" + req.getHeader().getUserId());
		List<APPRVLDTLS> apprvlDtlsList = libApprvl.getAPPRVLDTLS();
		for (APPRVLDTLS apprvlDtls : apprvlDtlsList) {
			log.debug("UploadId:" + apprvlDtls.getUploadId());
			log.debug("Approval Flag:" + apprvlDtls.getApprovalFlag());
			if (apprvlDtls.getApprovalFlag().equalsIgnoreCase("R")) {
				// deleting records from
				// upload_master,library,library_files,library_images,library_commodity,library_locations
				uploadmasRepo.delteUpldMstrNewsLib(apprvlDtls.getUploadId());

			} else if (apprvlDtls.getApprovalFlag().equalsIgnoreCase("A")) {
				uploadmasRepo.updateUpldMatsr(req.getHeader().getUserId(),
						Utils.getCurrentDate(), apprvlDtls.getUploadId());
				libraryRepo.updateLibrary(req.getHeader().getUserId(),
						Utils.getCurrentDate(), apprvlDtls.getUploadId());
				Library lib = new Library();
				lib.setLibId(apprvlDtls.getUploadId());
				Library library = libraryRepo.findOne(lib,
						apprvlDtls.getUploadId());
				UploadMaster master = new UploadMaster();
				master.setUploadId(apprvlDtls.getUploadId());
				UploadMaster upldMaster = uploadmasRepo.findOne(master,
						apprvlDtls.getUploadId());
				if (String.valueOf(upldMaster.getDetailId()) != null) {
					int detailId = upldMaster.getDetailId();
					log.debug("Details id:" + detailId);

					String effectiveDt = Utils.getYearAndMonth(upldMaster
							.getUploadDt());
					log.debug("Effective Date:" + effectiveDt);

					AgentUploadCount agntUpdCnt = new AgentUploadCount();
					AgentUploadCountPK pk = new AgentUploadCountPK();
					pk.setDetailId(detailId);
					pk.setEffectiveDate(effectiveDt);
					agntUpdCnt.setId(pk);
					AgentUploadCount agntUpldCount = agntUpldCntRepo.findOne(
							agntUpdCnt, pk);
					if (agntUpldCount == null) {
						agntUpdCnt.setUploads(1);
						if (library.getModifiedBy() == null) {
							agntUpdCnt.setUploadAccuracy(1);
						}

						// inserting a record into agent_upload_count table
						agntUpldCntRepo.save(agntUpdCnt);
					} else {
						agntUpldCount
								.setUploads(agntUpldCount.getUploads() + 1);
						if (library.getModifiedBy() == null) {
							agntUpldCount.setUploadAccuracy(agntUpldCount
									.getUploadAccuracy() + 1);
						}
						// updating a record into agent_upload_count table
						agntUpldCntRepo.merge(agntUpldCount);
					}
				}
				// checking for any sharing and upload for shared network
				uploadmasRepo.newsLibSharing(apprvlDtls.getUploadId(),
						libApprvl.getNetworkId());
			}
		}
		ERROR = ERROR_CODE.LIB_APPRVL;
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "libraryApproval", "", ERROR);
		res.setHeader(header);
		return res;

	}

	@Transactional
	public BidsAndOffersApprovalRes bidsAndOffersApproval(
			BidsAndOffersApprovalReq req) {
		log.info("Inside ApprovalService -> bidsAndOffersApproval");
		BidsAndOffersApprovalRes res = new BidsAndOffersApprovalRes();
		BIDSANDOFFERSAPPRVL bidsAndOffersApproval = req
				.getBIDSANDOFFERSAPPRVL();
		log.debug("NetworkId:" + bidsAndOffersApproval.getNetworkId());
		log.debug("User id:" + req.getHeader().getUserId());
		List<BIDSANDOFFERSDTLS> bidsAndOffersDtlList = bidsAndOffersApproval
				.getBIDSANDOFFERSDTLS();
		for (BIDSANDOFFERSDTLS bidsAndOffersDtl : bidsAndOffersDtlList) {
			if (bidsAndOffersDtl.getApprovalFlag().equalsIgnoreCase("R")) {
				UploadMaster master = new UploadMaster();
				master.setUploadId(bidsAndOffersDtl.getUploadId());
				BidsOffersMaster bidsOffersMaster = new BidsOffersMaster();
				bidsOffersMaster.setUpload_ID(bidsAndOffersDtl.getUploadId());
				UploadMaster upldMaster = uploadmasRepo.findOne(master,
						bidsAndOffersDtl.getUploadId());
				// deleting a record from upload_master
				if (upldMaster != null) {
					uploadmasRepo.delete(upldMaster);
				}
				BidsOffersMaster bidsOffrMstr = bidsAndOfferRepo.findOne(
						bidsOffersMaster, bidsAndOffersDtl.getUploadId());
				// deleting a record from bids_offers_master
				if (bidsOffrMstr != null) {
					bidsAndOfferRepo.delete(bidsOffrMstr);
				}
			} else if (bidsAndOffersDtl.getApprovalFlag().equalsIgnoreCase("A")) {
				UploadMaster master = new UploadMaster();
				master.setUploadId(bidsAndOffersDtl.getUploadId());
				UploadMaster upldMaster = uploadmasRepo.findOne(master,
						bidsAndOffersDtl.getUploadId());
				upldMaster.setAuthStat("A");
				upldMaster.setAuthBy(req.getHeader().getUserId());
				upldMaster.setAuthTs(Utils.getCurrentDate());
				// updating upload_master table
				uploadmasRepo.merge(upldMaster);

				BidsOffersMaster bidsOffersMaster = new BidsOffersMaster();
				bidsOffersMaster.setUpload_ID(bidsAndOffersDtl.getUploadId());
				BidsOffersMaster bidsAndOffrs = bidsAndOfferRepo.findOne(
						bidsOffersMaster, bidsAndOffersDtl.getUploadId());
				bidsAndOffrs.setAuthStat("A");
				bidsAndOffrs.setAuthBy(req.getHeader().getUserId());
				bidsAndOffrs.setAuthTs(Utils.getCurrentDate());
				// updating bids_offers_master table
				bidsAndOfferRepo.merge(bidsAndOffrs);

				if (String.valueOf(upldMaster.getDetailId()) != null) {
					int detailId = upldMaster.getDetailId();
					log.debug("Details id:" + detailId);

					String effectiveDt = Utils.getYearAndMonth(upldMaster
							.getUploadDt());
					log.debug("Effective Date:" + effectiveDt);

					AgentUploadCount agntUpdCnt = new AgentUploadCount();
					AgentUploadCountPK pk = new AgentUploadCountPK();
					pk.setDetailId(detailId);
					pk.setEffectiveDate(effectiveDt);
					agntUpdCnt.setId(pk);
					AgentUploadCount agntUpldCount = agntUpldCntRepo.findOne(
							agntUpdCnt, pk);
					if (agntUpldCount == null) {
						agntUpdCnt.setUploads(1);
						if (upldMaster.getModifiedBy() == null) {
							agntUpdCnt.setUploadAccuracy(1);
						}

						// inserting a record into agent_upload_count table
						agntUpldCntRepo.save(agntUpdCnt);
					} else {
						agntUpldCount
								.setUploads(agntUpldCount.getUploads() + 1);
						if (upldMaster.getModifiedBy() == null) {
							agntUpldCount.setUploadAccuracy(agntUpldCount
									.getUploadAccuracy() + 1);
						}
						// updating a record into agent_upload_count table
						agntUpldCntRepo.merge(agntUpldCount);
					}
				}

				String offerUserId = bidsAndOffrs.getOfferUserid();
				People ppl = new People();
				PeoplePK pk = new PeoplePK();
				pk.setPeopleId(offerUserId);
				pk.setDefaultNetworkId(bidsAndOffersApproval.getNetworkId());
				ppl.setId(pk);
				People people = pplRepo.findOne(ppl, pk);
				List<Object> pushAlertIdlist = null;

				String template = null;
				String temp = null;
				Map<String, String> bidsValues = new HashMap<String, String>();
				bidsValues.put("Quantity", String.valueOf(bidsAndOffrs
						.getQuantity().doubleValue()));
				log.debug("Quantity:" + bidsAndOffrs.getQuantity());
				bidsValues.put("Measure", bidsAndOffrs.getMeasure());
				log.debug("Measure:" + bidsAndOffrs.getMeasure());
				bidsValues.put("Commodity Name", bidsAndOffrs.getCommodity());
				log.debug("Commodity name:" + bidsAndOffrs.getCommodity());
				bidsValues.put("Currency", bidsAndOffrs.getCurrencyId());
				log.debug("Currency:" + bidsAndOffrs.getCurrencyId());
				bidsValues.put("Price", String.valueOf(bidsAndOffrs
						.getPriceAmount().doubleValue()));
				log.debug("Price:" + bidsAndOffrs.getPriceAmount());
				bidsValues.put("Offer Belongs to",
						bidsAndOffrs.getOfferUserid());
				log.debug("Offer belongs to:" + bidsAndOffrs.getOfferUserid());
				if (people != null) {
					bidsValues.put("Phone No", people.getMsisdn());
					log.debug("Phone no:" + people.getMsisdn());
				}
				bidsValues.put("Expiry Date",
						Utils.getStringDate(bidsAndOffrs.getExpiryDate()));
				log.debug("Expiry Date:"
						+ Utils.getStringDate(bidsAndOffrs.getExpiryDate()));
				bidsValues.put("Call Centre Message-Y/N", "");
				log.debug("Call Centre Message-Y/N");
				bidsValues.put("Country Call Centre", "");
				log.debug("Country Call Centre:");
				if (bidsAndOffrs.getBidOfferFlag().equals("B")) {
					template = bidsAndOfferRepo.getBidsAlertTemplate();
					log.debug("BidsAlertTemplate without values:" + template);
					temp = Utils.getBidsAlrtTmplt(template, bidsValues,
							bidsAndOffrs.getNegotiableFlag());
					log.debug("BidsAlertTemplate with values:" + temp);
				} else if ((bidsAndOffrs.getBidOfferFlag().equals("O"))) {
					template = bidsAndOfferRepo.getOffersAlertTemplate();
					log.debug("OffersAlertTemplate without values:" + template);
					temp = Utils.getOffersAlrtTmplt(template, bidsValues,
							bidsAndOffrs.getNegotiableFlag());
					log.debug("OffersAlertTemplate with values:" + temp);
				}
				pushAlertIdlist = alrtLocRepo.findPushAlertId(
						bidsAndOffersDtl.getUploadId(),
						bidsAndOffersApproval.getNetworkId());
				if (pushAlertIdlist != null) {
					for (Object pushAlertId : pushAlertIdlist) {
						if (pushAlertId != null) {
							log.debug("Push alert id:" + pushAlertId);
							List<PushAlertMaster> pushAlrtMasterList = pushMastRepo
									.getPushAlrtMstrByPshAlrtId(pushAlertId
											.toString());
							if (pushAlrtMasterList != null) {
								for (PushAlertMaster pushAlrtMaster : pushAlrtMasterList) {
									pushAlrtMaster.setText(temp);
									if (temp != null) {
										pushAlrtMaster.setNoOfMessages(Utils
												.noOfMessages(temp));
									}
									pushAlrtMaster.setModifiedTs(Utils
											.getCurrentDate());
									// updating text column in
									// push_alert_master
									// table
									pushMastRepo.merge(pushAlrtMaster);
								}
								TriggerHandler handler = TriggerHandler
										.getInstance();
								TriggerDetails dtls = new TriggerDetails();
								Date date = Utils
										.getDateAfterTwoMins(new Date());
								dtls.setTriggerId(pushAlertId.toString());
								dtls.setFrequency("Once");
								dtls.setStartDate(date);
								handler.activateTrigger(PushAlertJob.class,
										dtls);
							}
						}
					}
				}
			}

		}

		ERROR = ERROR_CODE.BIDS_OFFRS_APPRVL;
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "bidsAndOffersApproval", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public EditPriceRes editPrice(EditPriceReq req) {
		log.info("Inside ApprovalService -> editPrice");
		EditPriceRes res = new EditPriceRes();
		EDITPRICE editPrice = req.getEDITPRICE();
		log.debug("Upload id:" + editPrice.getUploadId());
		log.debug("Price:" + editPrice.getPrice());
		log.debug("Currency:" + editPrice.getCurrency());
		log.debug("Measure:" + editPrice.getMeasure());
		log.debug("Conversion:" + editPrice.getConversion());
		log.debug("Date:" + editPrice.getDate());
		log.debug("Comments:" + editPrice.getComments());

		PriceUploadMaster master = new PriceUploadMaster();
		master.setUpload_ID(editPrice.getUploadId());
		PriceUploadMaster uploadMaster = priceUpldMstrRepo.findOne(master,
				editPrice.getUploadId());
		if (uploadMaster != null) {
			uploadMaster.setPrice(new BigDecimal(editPrice.getPrice()));
			uploadMaster.setCurrencyId(editPrice.getCurrency());
			uploadMaster.setMeasureId(editPrice.getMeasure());
			uploadMaster.setWeight(new BigDecimal(editPrice.getConversion()));
			uploadMaster.setCollectedOn(Utils.getFmtDtDDMMYYYY(editPrice
					.getDate()));
			uploadMaster.setComments(editPrice.getComments());
			uploadMaster.setModifiedBy(req.getHeader().getUserId());
			uploadMaster.setModifiedTs(Utils.getCurrentDate());
			priceUpldMstrRepo.merge(uploadMaster);

		}
		ERROR = ERROR_CODE.EDIT_PRICE;
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "editPrice", "", ERROR);

		res.setHeader(header);
		return res;
	}

	@Transactional
	public PriceApprovalRes priceApproval(PriceApprovalReq req) {
		log.info("Inside ApprovalService -> priceApproval");
		PriceApprovalRes res = new PriceApprovalRes();
		PRICEAPPRVL priceApproval = req.getPRICEAPPRVL();
		log.debug("NetworkId:" + priceApproval.getNetworkId());
		log.debug("User id:" + req.getHeader().getUserId());
		List<PRICEDTLS> priceDtlList = priceApproval.getPRICEDTLS();
		for (PRICEDTLS priceDtl : priceDtlList) {
			if (priceDtl.getApprovalFlag().equalsIgnoreCase("R")) {
				UploadMaster master = new UploadMaster();
				master.setUploadId(priceDtl.getUploadId());
				PriceUploadMaster priceUpldMaster = new PriceUploadMaster();
				priceUpldMaster.setUpload_ID(priceDtl.getUploadId());
				UploadMaster upldMaster = uploadmasRepo.findOne(master,
						priceDtl.getUploadId());
				// deleting a record from upload_master
				if (upldMaster != null) {
					uploadmasRepo.delete(upldMaster);
				}
				PriceUploadMaster priceUpldMstr = priceUpldMstrRepo.findOne(
						priceUpldMaster, priceDtl.getUploadId());
				// deleting a record from price_upload_master
				if (priceUpldMstr != null) {
					priceUpldMstrRepo.delete(priceUpldMstr);
				}
			} else if (priceDtl.getApprovalFlag().equalsIgnoreCase("A")) {
				UploadMaster upldMaster = uploadmasRepo.findOne(
						UploadMaster.class, priceDtl.getUploadId());
				if (upldMaster != null) {
					upldMaster.setAuthStat("A");
					upldMaster.setAuthBy(req.getHeader().getUserId());
					upldMaster.setAuthTs(Utils.getCurrentDate());
					// updating upload_master table
					uploadmasRepo.merge(upldMaster);
				}
				PriceUploadMaster priceUpldMstr = priceUpldMstrRepo.findOne(
						PriceUploadMaster.class, priceDtl.getUploadId());
				if (priceUpldMstr != null) {
					priceUpldMstr.setAuthStat("A");
					priceUpldMstr.setAuthBy(req.getHeader().getUserId());
					priceUpldMstr.setAuthTs(Utils.getCurrentDate());
					// updating price_upload_master
					priceUpldMstrRepo.merge(priceUpldMstr);
				}
				if (upldMaster != null) {
					if (String.valueOf(upldMaster.getDetailId()) != null) {
						int detailId = upldMaster.getDetailId();
						log.debug("Details id:" + detailId);

						String effectiveDt = Utils.getYearAndMonth(upldMaster
								.getUploadDt());
						log.debug("Effective Date:" + effectiveDt);

						AgentUploadCount agntUpdCnt = new AgentUploadCount();
						AgentUploadCountPK pk = new AgentUploadCountPK();
						pk.setDetailId(detailId);
						pk.setEffectiveDate(effectiveDt);
						agntUpdCnt.setId(pk);
						AgentUploadCount agntUpldCount = agntUpldCntRepo
								.findOne(agntUpdCnt, pk);
						if (agntUpldCount == null) {
							agntUpdCnt.setUploads(1);
							if (upldMaster.getModifiedBy() == null) {
								agntUpdCnt.setUploadAccuracy(1);
							}

							// inserting a record into agent_upload_count table
							agntUpldCntRepo.save(agntUpdCnt);
						} else {
							agntUpldCount
									.setUploads(agntUpldCount.getUploads() + 1);
							if (upldMaster.getModifiedBy() == null) {
								agntUpldCount.setUploadAccuracy(agntUpldCount
										.getUploadAccuracy() + 1);
							}
							// updating a record into agent_upload_count table
							agntUpldCntRepo.merge(agntUpldCount);
						}
					}
				}
				List<Map<String, String>> recordList = prflBsdrepo
						.priceSharingapproval(priceDtl.getUploadId(),
								priceApproval.getNetworkId());

				for (Map<String, String> map : recordList) {
					log.debug("Alert id:" + map.get("alertId"));
					log.debug("User id:" + map.get("userid"));
					List<String> uploadIdList = priceUpldMstrRepo
							.findUploadDetailsForAlert(map.get("alertId"),
									map.get("userid"));
					List<PushAlertMaster> pushAlrtMasterList = pushMastRepo
							.getPushAlrtMstrByPshAlrtId(map.get("alertId"));
					PushAlertMaster pushAlrtMaster = null;
					if (pushAlrtMasterList != null) {
						pushAlrtMaster = pushAlrtMasterList.get(0);
					}

					String quoteCurrency = null;
					String toMeasureId = null;

					if (pushAlrtMaster != null) {
						quoteCurrency = pushAlrtMaster.getCurrencyId();
						log.debug("quoteCurrency:" + quoteCurrency);
					}
					List<PriceUploadMaster> prcUpldMstrList = new ArrayList<PriceUploadMaster>();
					for (String uploadId : uploadIdList) {
						PriceUploadMaster priceUldMstr = priceUpldMstrRepo
								.findOne(PriceUploadMaster.class, uploadId);
						if (map.get("userid") == null) {
							List<AlertPriceType> type = priceTypeRepo
									.findAllAlertPriceTypes(map.get("alertId"));
							if (type != null) {
								AlertPriceType alertPriceType = type.get(0);
								toMeasureId = alertPriceType.getMeasureId();
								log.debug("toMeasureId:" + toMeasureId);
							}
						} else {
							MeasureFactor factor = factorRepo
									.queryByNwkCMdPrctDM(
											priceApproval.getNetworkId(),
											priceUpldMstr.getCommodity(),
											priceUpldMstr.getPriceType());
							if (factor != null) {
								toMeasureId = factor.getBaseMeasureId();
							}

						}

						if (priceUldMstr != null && quoteCurrency != null
								&& toMeasureId != null) {
							String baseCurrency = priceUldMstr.getCurrencyId();
							String fromMeasureId = priceUldMstr.getMeasureId();
							log.debug("fromMeasureId" + fromMeasureId);
							if (baseCurrency != quoteCurrency
									|| toMeasureId != fromMeasureId) {
								log.debug("Quote currency:" + quoteCurrency);
								Forex forex = forexRepo.queryfxRatesbyCcy(
										baseCurrency, quoteCurrency);
								MeasureFactor factor = null;
								if (toMeasureId != null) {
									factor = factorRepo.queryMeasureFactor(
											toMeasureId, fromMeasureId,
											priceUldMstr.getCommodity(),
											priceUldMstr.getNetworkId(),
											priceUldMstr.getPriceType());

								}

								if (forex != null && factor != null) {
									log.debug("Conversion Factor:"
											+ factor.getConvFactor());
									log.debug("Exchange rate:"
											+ forex.getRate());
									priceUldMstr
											.setPrice(new BigDecimal(
													(priceUldMstr.getPrice()
															.doubleValue()
															* forex.getRate()
																	.doubleValue() / factor
															.getConvFactor()
															.doubleValue()))
													.setScale(
															2,
															BigDecimal.ROUND_DOWN));
								} else if (forex != null) {
									log.info("Exchange rate:" + forex.getRate());
									priceUldMstr
											.setPrice(new BigDecimal(
													(priceUldMstr.getPrice()
															.doubleValue() * forex
															.getRate()
															.doubleValue()))
													.setScale(
															2,
															BigDecimal.ROUND_DOWN));
								}

								else if (factor != null) {
									log.debug("Conversion Factor:"
											+ factor.getConvFactor());

									priceUldMstr.setPrice(new BigDecimal(
											(priceUldMstr.getPrice()
													.doubleValue() / factor
													.getConvFactor()
													.doubleValue())).setScale(
											2, BigDecimal.ROUND_DOWN));

								}
							}
							prcUpldMstrList.add(priceUldMstr);
						}
						log.debug("Price Alert master list" + prcUpldMstrList);
						String priceAlrtTemp = null;
						if (pushAlrtMaster != null) {
							priceAlrtTemp = getPrcAlrtTemplate(prcUpldMstrList,
									quoteCurrency);
						}
						log.debug("Price Alert Template:" + priceAlrtTemp);

						if (map.get("userid") == null) {
							if (pushAlrtMaster != null) {
								pushAlrtMaster.setText(priceAlrtTemp);
								if (priceAlrtTemp != null) {
									pushAlrtMaster.setNoOfMessages(Utils
											.noOfMessages(priceAlrtTemp));
								}
								pushMastRepo.merge(pushAlrtMaster);
							}
						} else {
							List<AlertProfileBased> alrtProflBasedList = prflBsdrepo
									.queryAlertProfileBasedByUsrID(
											map.get("userid"),
											map.get("alertId"));
							if (alrtProflBasedList != null) {
								for (AlertProfileBased alert : alrtProflBasedList) {
									alert.setText(priceAlrtTemp);
									if (priceAlrtTemp != null) {
										alert.setNoOfMessages(Utils
												.noOfMessages(priceAlrtTemp));
									}
									prflBsdrepo.merge(alert);
								}
							}
						}
					}

				}
			}
		}
		ERROR = ERROR_CODE.PRICE_APPRVL;
		header = (Header) HeaderFactory.getHeader(HeaderType.APPROVAL,
				"ApprovalService", "priceApproval", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public String getPrcAlrtTemplate(List<PriceUploadMaster> masterList,
			String currency) {
		log.info("Inside ApprovalService -> getPrcAlrtTemplate");
		log.debug("Currency:" + currency);
		Multimap<String, PriceUploadMaster> templateList = ArrayListMultimap
				.create();
		for (PriceUploadMaster master : masterList) {
			templateList.put(master.getCommodity(), master);
		}

		Map<String, List<PriceUploadMaster>> map = new HashMap<String, List<PriceUploadMaster>>();
		Map<String, List<String>> comdPrcTypMap = new HashMap<String, List<String>>();
		Map<String, List<String>> comdMrktMap = new HashMap<String, List<String>>();
		Map<String, StringBuffer> commdTemp = new HashMap<String, StringBuffer>();
		List<PriceUploadMaster> list = null;
		StringBuffer template = new StringBuffer();
		for (String key : templateList.keys()) {
			Collection<PriceUploadMaster> collection = templateList.get(key);
			Iterator<PriceUploadMaster> iterator = collection.iterator();
			while (iterator.hasNext()) {
				PriceUploadMaster name = (PriceUploadMaster) iterator.next();
				if (map.containsKey(key)) {
					list.add(name);
					map.put(key, list);
				} else {
					list = new ArrayList<PriceUploadMaster>();

					list.add(name);
					map.put(key, list);
				}
			}
			for (Map.Entry<String, List<PriceUploadMaster>> entry : map
					.entrySet()) {
				Set<String> set = new HashSet<String>();
				List<PriceUploadMaster> list1 = entry.getValue();
				for (PriceUploadMaster mstr : list1) {
					set.add(mstr.getPriceType());
				}
				List<String> list2 = new ArrayList<String>();
				for (String s1 : set) {
					list2.add(s1);
				}
				comdPrcTypMap.put(entry.getKey(), list2);
			}
			for (Map.Entry<String, List<PriceUploadMaster>> entry : map
					.entrySet()) {
				Set<String> set = new HashSet<String>();
				List<PriceUploadMaster> list1 = entry.getValue();
				for (PriceUploadMaster mstr : list1) {
					set.add(mstr.getMarket());
				}
				List<String> list2 = new ArrayList<String>();
				for (String s : set) {
					list2.add(s);
				}
				comdMrktMap.put(entry.getKey(), list2);
			}
			for (Map.Entry<String, List<PriceUploadMaster>> entry : map
					.entrySet()) {
				StringBuffer buffer = new StringBuffer(entry.getKey() + " in "
						+ currency);

				for (Map.Entry<String, List<String>> entry1 : comdPrcTypMap
						.entrySet()) {
					if (entry1.getKey().equals(entry.getKey())) {
						List<String> priceTypeList = entry1.getValue();
						boolean flag = true;
						for (String priceType : priceTypeList) {
							StringBuffer baseMeasureId = null;
							List<PriceUploadMaster> upldMstrList = map
									.get(entry.getKey());
							for (PriceUploadMaster mstr : upldMstrList) {
								if (mstr.getPriceType().equals(priceType)) {
									baseMeasureId = new StringBuffer(
											mstr.getMeasureId());
								}
							}
							if (flag) {
								buffer.append("(" + priceType + " "
										+ baseMeasureId);
								flag = false;
							} else {
								buffer.append("/" + priceType + " "
										+ baseMeasureId);
							}

						}
						buffer.append("):");
					}
				}

				commdTemp.put(entry.getKey(), buffer);

			}

		}
		for (Map.Entry<String, List<PriceUploadMaster>> entry : map.entrySet()) {
			List<PriceUploadMaster> masterList1 = entry.getValue();
			Set<PriceUploadMaster> masterSet = new HashSet<PriceUploadMaster>(
					masterList1);
			String commodity = entry.getKey();
			List<String> markets = comdMrktMap.get(entry.getKey());
			List<String> priceTypes = comdPrcTypMap.get(entry.getKey());
			StringBuffer buffer = new StringBuffer();
			for (String market : markets) {
				boolean flag = true;
				for (String priceType : priceTypes) {
					int count = 0;
					for (PriceUploadMaster master : masterSet) {

						if (master.getCommodity().equals(commodity)
								&& master.getPriceType().equals(priceType)) {
							if (master.getCommodity().equals(commodity)
									&& master.getMarket().equals(market)
									&& master.getPriceType().equals(priceType)) {
								if (flag) {
									buffer.append(" " + market + "="
											+ master.getPrice());
									flag = false;
								} else {
									buffer.append("/" + master.getPrice());
								}
								count = 1;
							}
						}
					}
					if (count == 0) {
						if (flag) {
							buffer.append(" " + market + "=" + "na");

							flag = false;
						} else {
							buffer.append("/na");
						}
					}
				}
			}

			if (commdTemp.containsKey(commodity)) {
				commdTemp.get(commodity).append(buffer);
			}

		}
		for (Map.Entry<String, StringBuffer> entry : commdTemp.entrySet()) {
			template.append(entry.getValue() + "\n");
		}
		return template.toString();
	}
}