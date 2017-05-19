package com.iexceed.esoko.ss.service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.acc.AccountingEngine;
import com.iexceed.esoko.acc.InvoiceAccounting;
import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao.UserGroupRepo;
import com.iexceed.esoko.domain.dao2.NetworkSubscriptionAppsRepo;
import com.iexceed.esoko.domain.dao2.NetworkSubscriptionDetailsRepo;
import com.iexceed.esoko.domain.dao2.NetworkSubscriptionRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.ResellerDetailsRepo;
import com.iexceed.esoko.domain.dao2.ResellerNetworkRepo;
import com.iexceed.esoko.domain.dao2.SubscriberMasterRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionInvoiceRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionMasterRepo;
import com.iexceed.esoko.domain.dao2.SubscriptionPriceRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.System_account;
import com.iexceed.esoko.domain.entity.UserGroup;
import com.iexceed.esoko.domain.entity2.InvoiceDetail;
import com.iexceed.esoko.domain.entity2.NetworkSubscription;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionApp;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionAppPK;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionDetail;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionDetailPK;
import com.iexceed.esoko.domain.entity2.NetworkSubscriptionPK;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity2.ResellerMaster;
import com.iexceed.esoko.domain.entity2.ResellerNetwork;
import com.iexceed.esoko.domain.entity2.ResellerNetworkPK;
import com.iexceed.esoko.domain.entity2.SubscriberMaster;
import com.iexceed.esoko.engine.reports.InvoiceReport;
import com.iexceed.esoko.engine.resources.MailResource;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserReq;
import com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL;
import com.iexceed.esoko.jaxb.se.CRNWKDETAILS;
import com.iexceed.esoko.jaxb.se.CreateNetworkReq;
import com.iexceed.esoko.jaxb.ss.DeleteResellerSubInvoiceRes;
import com.iexceed.esoko.jaxb.ss.EXTRA;
import com.iexceed.esoko.jaxb.ss.Header;
import com.iexceed.esoko.jaxb.ss.INVOICEAPPS;
import com.iexceed.esoko.jaxb.ss.MSGINVOICES;
import com.iexceed.esoko.jaxb.ss.MSGSUBSCRIPTION;
import com.iexceed.esoko.jaxb.ss.QRALLRSLRINVOICES;
import com.iexceed.esoko.jaxb.ss.QRINVCEPYMTDTLS;
import com.iexceed.esoko.jaxb.ss.QRRESELLERINVOICES;
import com.iexceed.esoko.jaxb.ss.QRRSLRSUBERDTLS;
import com.iexceed.esoko.jaxb.ss.QRRSLRSUBSINVOICES;
import com.iexceed.esoko.jaxb.ss.QRSUBSCRIBINVOICES;
import com.iexceed.esoko.jaxb.ss.QRYSUBSCRIBUPGRADE;
import com.iexceed.esoko.jaxb.ss.QRYSUBSCRIBUPGRADELIST;
import com.iexceed.esoko.jaxb.ss.QueryAllResellerInvoicesRes;
import com.iexceed.esoko.jaxb.ss.QueryInvoicePaymentRes;
import com.iexceed.esoko.jaxb.ss.QueryResellerSubscriberDtlsRes;
import com.iexceed.esoko.jaxb.ss.QueryResellerSubscripInvoicesRes;
import com.iexceed.esoko.jaxb.ss.QuerySubscriptionInvoicesRes;
import com.iexceed.esoko.jaxb.ss.QuerySubscriptionUpgradeRes;
import com.iexceed.esoko.jaxb.ss.ResellerSubscriptionTopupReq;
import com.iexceed.esoko.jaxb.ss.ResellerSubscriptionTopupRes;
import com.iexceed.esoko.jaxb.ss.SUBSCRIBERRGSTR;
import com.iexceed.esoko.jaxb.ss.SUBSCRIPSUSPEND;
import com.iexceed.esoko.jaxb.ss.SUBSCRIPTOPUP;
import com.iexceed.esoko.jaxb.ss.SubscriberMessagesReq;
import com.iexceed.esoko.jaxb.ss.SubscriberMessagesRes;
import com.iexceed.esoko.jaxb.ss.SubscriberRegistrationReq;
import com.iexceed.esoko.jaxb.ss.SubscriberRegistrationRes;
import com.iexceed.esoko.jaxb.ss.SubscripInvoiceUpgradeReq;
import com.iexceed.esoko.jaxb.ss.SubscripInvoiceUpgradeRes;
import com.iexceed.esoko.jaxb.ss.SubscriptionSuspendReq;
import com.iexceed.esoko.jaxb.ss.SubscriptionSuspendRes;
import com.iexceed.esoko.jaxb.ss.TOPUPINVOICES;
import com.iexceed.esoko.jaxb.ss.UPGRADEINVOICES;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.Cost;
import com.iexceed.esoko.objects.TRANSACTION_CODE;
import com.iexceed.esoko.se.service.NetworkService;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

//import com.iexceed.esoko.signup.service.UserSignUpService;

@Service
public class ResellerSubscriptionService {
	@Autowired
	NetworkSubscriptionRepo nwkSubscriptionRepo;
	@Autowired
	NetworkSubscriptionDetailsRepo nwkSubscripDtlsRepo;
	@Autowired
	SubscriberMasterRepo subscriberRepo;
	@Autowired
	SubscriptionPriceRepo repo;
	@Autowired
	SubscriptionMasterRepo masterRepo;
	@Autowired
	SubscriptionInvoiceRepo invoiceRepo;
	@Autowired
	NetworkSubscriptionAppsRepo appsRepo;
	@Autowired
	private NetworkRepo nwkRepo;
	@Autowired
	private NetworkService nwkService;
	@Autowired
	private PeopleRepo peopleRepo;
	@Autowired
	private SystemUserRepo sysUserRepo;
	@Autowired
	private UserGroupRepo userGrpRepo;
	// @Autowired
	// UserSignUpService userService;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	AccountingEngine accEngnie;
	@Autowired
	System_accountRepo sysAccRepo;
	@Autowired
	ForexRepo forexRepo;
	@Autowired
	CurrencyRepo currRepo;
	@Autowired
	InvoiceAccounting invoiceAccounting;
	@Autowired
	ResellerDetailsRepo resellerDtlRepo;
	@Autowired
	ResellerNetworkRepo resNetRepo;
	@Autowired
	ResellerDetailsRepo resellerDetRepo;
	@Autowired
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;
	public static Logger log = LoggerUtils.getLogger();

	public QueryResellerSubscriberDtlsRes queryResellerSubscripDtls(
			String networkId, String resellerId, String filterBy) {
		log.info("Inside QueryResellerSubscribDtlsRes :: queryResellerSubscripDtls");
		log.debug("networkId::" + networkId);
		log.debug("resellerId::" + resellerId);
		QueryResellerSubscriberDtlsRes res = new QueryResellerSubscriberDtlsRes();
		if (!StringUtils.isNotEmpty(resellerId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService",
					"Query Reseller Subscriber Dtls", "",
					ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService",
					"Query Reseller Subscriber Dtls", "", ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		List<Map<String, Object>> nwkSubscriptionList = nwkSubscriptionRepo
				.queryResellerSubscription(networkId, resellerId, filterBy);
		List<QRRSLRSUBERDTLS> qrRslrList = new ArrayList<QRRSLRSUBERDTLS>();
		if (nwkSubscriptionList.size() != 0) {
			for (Map<String, Object> entity : nwkSubscriptionList) {
				QRRSLRSUBERDTLS qrSlrSubDtls = new QRRSLRSUBERDTLS();
				if (entity.get("sub_id") != null) {
					qrSlrSubDtls.setRowId(entity.get("sub_id").toString());
				}
				if (entity.get("sub_type") != null) {
					qrSlrSubDtls.setSubType(entity.get("sub_type").toString());
				}
				if (entity.get("network_id") != null) {
					qrSlrSubDtls.setNetworkId(entity.get("network_id")
							.toString());
				}
				if (entity.get("network_name") != null) {
					qrSlrSubDtls.setNetworkName(entity.get("network_name")
							.toString());
				}
				if (entity.get("expiryFlag") != null) {
					qrSlrSubDtls.setExpiryFlag(entity.get("expiryFlag")
							.toString());
				}
				if (entity.get("expiry") != null) {
					qrSlrSubDtls.setExpiry(entity.get("expiry").toString());
				}
				if (entity.get("currency") != null) {
					qrSlrSubDtls.setCurrency(entity.get("currency").toString());
				}
				if (entity.get("balanceFlag") != null) {
					qrSlrSubDtls.setBalanceFlag(entity.get("balanceFlag")
							.toString());
				}
				if (entity.get("balance") != null) {
					BigDecimal balance = new BigDecimal(
							(Double) entity.get("balance"));
					qrSlrSubDtls.setBalance("$"
							+ balance.setScale(2, BigDecimal.ROUND_DOWN)
									.toString());
				}
				/*
				 * if (entity.get("alerts") != null) {
				 * qrSlrSubDtls.setAlerts("1200/3000"); } if
				 * (entity.get("agents") != null) {
				 * qrSlrSubDtls.setAgents("5/12"); }
				 */
				qrSlrSubDtls.setAlerts("1200/3000");
				qrSlrSubDtls.setAgents("5/12");
				if (entity.get("owner_name") != null) {
					qrSlrSubDtls.setOwnerName(entity.get("owner_name")
							.toString());
				}
				List<QRRESELLERINVOICES> qrInvoicesList = this
						.queryResellerInvoices(networkId, entity
								.get("sub_type").toString(), resellerId);
				if (qrInvoicesList.size() != 0) {
					qrSlrSubDtls.getQRRESELLERINVOICES().addAll(qrInvoicesList);
				}
				qrRslrList.add(qrSlrSubDtls);
			}
			res.getQRRSLRSUBERDTLS().addAll(qrRslrList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerSubscriptionService", "Query Reseller SubscripDtls",
				"", ERROR);
		res.setHeader(header);
		return res;
	}

	public QuerySubscriptionInvoicesRes querySubscriptionInvoices(
			String networkId, String suscrib_type, String resellerId) {
		log.info("Inside QuerySubscriptionInvoicesRes :: querySubscriptionInvoices");
		log.debug("networkId::" + networkId);
		log.debug("suscrib_type::" + suscrib_type);
		log.debug("resellerId::" + resellerId);
		QuerySubscriptionInvoicesRes res = new QuerySubscriptionInvoicesRes();

		if (!StringUtils.isNotEmpty(resellerId)) {
			header = (Header) HeaderFactory
					.getHeader(HeaderType.SS, "ResellerSubscriptionService",
							"Query Subscription Invoices", "",
							ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService",
					"Query Subscription Invoices", "", ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(suscrib_type)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService",
					"Query Subscription Invoices", "", ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}
		List<QRSUBSCRIBINVOICES> qrInvoicesList = new ArrayList<QRSUBSCRIBINVOICES>();
		List<Map<String, Object>> nwkInvoicesList = nwkSubscriptionRepo
				.querySubscriptionInvoiceDtls(networkId, suscrib_type,
						resellerId);
		if (nwkInvoicesList.size() != 0) {
			for (Map<String, Object> entity : nwkInvoicesList) {
				QRSUBSCRIBINVOICES invoices = new QRSUBSCRIBINVOICES();
				invoices.setAmount(entity.get("amount").toString());
				invoices.setDate(entity.get("date").toString());
				invoices.setDue(entity.get("due").toString());
				invoices.setInvoiceNo(entity.get("number").toString());
				invoices.setNetworkId(entity.get("network_id").toString());
				invoices.setNetworkName(entity.get("network_name").toString());
				qrInvoicesList.add(invoices);
			}
			res.getQRSUBSCRIBINVOICES().addAll(qrInvoicesList);
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerSubscriptionService", "Query Reseller SubscripDtls",
				"", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public SubscriptionSuspendRes subscriptionSuspend(SubscriptionSuspendReq req) {
		log.info("Inside SubscriptionSuspendRes :: subscriptionSuspend");
		SubscriptionSuspendRes res = new SubscriptionSuspendRes();
		SUBSCRIPSUSPEND suspendDtls = req.getSUBSCRIPSUSPEND();
		NetworkSubscription entity = new NetworkSubscription();
		NetworkSubscriptionPK entityPk = new NetworkSubscriptionPK();
		log.debug("networkId::" + suspendDtls.getNetworkId());
		log.debug("subscription id::" + suspendDtls.getSubscriptionId());
		log.debug("message::" + suspendDtls.getMessage());
		log.debug("prevent logs::" + suspendDtls.getPreventLogs());
		log.debug("suspend debits::" + suspendDtls.getSuspendDebits());
		if (suspendDtls.getSubscriptionId() == 0) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}
		if (!StringUtils.isNotEmpty(suspendDtls.getNetworkId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(suspendDtls.getSubscriptionType())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}
		entityPk.setNetworkId(suspendDtls.getNetworkId());
		entityPk.setSubscriptionId("SUB" + esokoRepo.getSequenceNumber());

		entity.setId(entityPk);
		entity = nwkSubscriptionRepo.findOne(entity, entityPk);
		if (entity != null) {
			entity.setSubscriptionType(suspendDtls.getSubscriptionType());
			entity.setMessage(suspendDtls.getMessage());
			entity.setPreventLogins(suspendDtls.getPreventLogs());
			entity.setSuspendDebits(suspendDtls.getSuspendDebits());
			entity.setRecordStatus("S");
			entity.setModifiedBy(req.getHeader().getUserId());
			entity.setModifiedTs(Utils.getCurrentDate());
			nwkSubscriptionRepo.merge(entity);
			ERROR = ERROR_CODE.SUB_SUSPEND_SC;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerSubscriptionService", "Reseller Subscription Suspend",
				"", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public SubscriberRegistrationRes subscriberRegistration(
			SubscriberRegistrationReq req) {
		SubscriberRegistrationRes res = new SubscriberRegistrationRes();
		CreateEsokoUserReq userReq = new CreateEsokoUserReq();
		SUBSCRIBERRGSTR subscribRegister = req.getSUBSCRIBERRGSTR();
		Network nwkEntity = new Network();
		String networkId = "NWK" + esokoRepo.getSequenceNumber();
		List<USERDETAIL> usrDtlList = new ArrayList<USERDETAIL>();
		USERDETAIL userDtls = new USERDETAIL();
		String sub_id = null;
		log.info("Inside SubscriberRegistrationRes -> subscriptionMessage");
		if (!StringUtils.isNotEmpty(req.getSUBSCRIBERRGSTR().getName())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscriber Registration",
					"", ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}
		if (!StringUtils.isNotEmpty(req.getSUBSCRIBERRGSTR().getEmail())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscriber Registration",
					"", ERROR_CODE.USR_EMAIL_ERR);
			res.setHeader(header);
			return res;
		}
		if (subscribRegister != null) {
			sub_id = "SUB" + esokoRepo.getSequenceNumber();
			log.debug("Dob::" + subscribRegister.getDob());
			SubscriberMaster entity = new SubscriberMaster();
			ResellerNetwork resellerNetwork = new ResellerNetwork();
			ResellerNetworkPK resellerNetworkPk = new ResellerNetworkPK();
			NetworkSubscription networkSubscription = new NetworkSubscription();
			NetworkSubscriptionPK networkSubscriptionPK = new NetworkSubscriptionPK();
			entity.setCreatedBy(req.getHeader().getUserId());
			entity.setCreatedTs(Utils.getCurrentDate());
			entity.setDob(Utils.getFormatedDate3(subscribRegister.getDob()));
			log.debug("Email::" + subscribRegister.getEmail());
			entity.setEmail(subscribRegister.getEmail());
			log.debug("Gender::" + subscribRegister.getGender());
			entity.setGender(subscribRegister.getGender());
			log.debug("Location::" + subscribRegister.getLocation());
			entity.setLocation(subscribRegister.getLocation());
			log.debug("Mobile::" + subscribRegister.getMobile());
			entity.setMobile(subscribRegister.getMobile());
			log.debug("NetworkId::" + networkId);
			entity.setNetworkId(networkId);
			log.debug("Occupations::" + subscribRegister.getOccupation());
			entity.setOccupations(subscribRegister.getOccupation());
			log.debug("Reseller Id::" + subscribRegister.getResellerId());
			entity.setResellerId(subscribRegister.getResellerId());
			log.debug("Name::" + subscribRegister.getName());
			entity.setSubscriberId(sub_id);
			log.debug("Subscription Type::"
					+ subscribRegister.getSubscribType());
			entity.setSubscriptionType(subscribRegister.getSubscribType());
			entity.setRecordStatus("A");
			ResellerMaster resellerMaster = resellerDtlRepo.findOne(
					ResellerMaster.class, subscribRegister.getResellerId());
			if (resellerMaster != null) {
				resellerNetwork.setOwnerName(resellerMaster.getOwnerName());
			}
			resellerNetwork.setCreatedBy(req.getHeader().getUserId());
			resellerNetwork.setCreatedTs(Utils.getCurrentDate());
			resellerNetwork.setRecordStatus("A");
			networkSubscriptionPK.setSubscriptionId(sub_id);
			networkSubscriptionPK.setNetworkId(networkId);
			networkSubscription.setId(networkSubscriptionPK);
			networkSubscription.setSubscriptionType(subscribRegister
					.getSubscribType());
			networkSubscription.setResellerId(subscribRegister.getResellerId());
			networkSubscription.setNetworkName(subscribRegister.getName());
			if (resellerMaster != null) {
				networkSubscription.setOwnerName(resellerMaster.getOwnerName());
			}
			networkSubscription.setCurrency("USD");
			networkSubscription.setPreventLogins("N");
			networkSubscription.setSuspendDebits("N");
			networkSubscription.setRecordStatus("A");
			networkSubscription.setCreatedBy(req.getHeader().getUserId());
			networkSubscription.setCreatedTs(Utils.getCurrentDate());
			String invoiceNumber = invoiceRepo.generateInvoiceNumber();
			InvoiceDetail invoiceEntity = new InvoiceDetail();
			invoiceEntity.setInvoiceNo(invoiceNumber);
			invoiceEntity.setInvoiceAmount(BigDecimal.ZERO);
			// invoiceEntity.setDueAmount(new BigDecimal());
			invoiceEntity.setCreatedBy(req.getHeader().getUserId());
			invoiceEntity.setCreatedTs(Utils.getCurrentDate());
			invoiceEntity.setCurrency("USD");
			invoiceEntity.setInvoiceDate(Utils.getCurrentDate());
			invoiceEntity.setInvoiceFrom(subscribRegister.getResellerId());
			invoiceEntity.setInvoiceTo(subscribRegister.getNetworkId());
			invoiceEntity.setPaid_Status("I");

			NetworkSubscriptionDetail nwkSubscriptionDtlsEntity = new NetworkSubscriptionDetail();
			NetworkSubscriptionDetailPK nwkSubscriptionDtlsPk = new NetworkSubscriptionDetailPK();
			log.debug("invoiceNumber::" + invoiceNumber);
			nwkSubscriptionDtlsPk.setInvoiceId(invoiceNumber);
			log.debug("NetworkId::" + networkId);

			nwkSubscriptionDtlsPk.setNetworkId(networkId);
			nwkSubscriptionDtlsPk.setResellerId(subscribRegister
					.getResellerId());
			nwkSubscriptionDtlsEntity.setSubscriptionId(sub_id);
			nwkSubscriptionDtlsEntity.setId(nwkSubscriptionDtlsPk);
			nwkSubscriptionDtlsEntity.setSubscriptionType(subscribRegister
					.getSubscribType());
			nwkSubscriptionDtlsEntity.setCreatedBy(req.getHeader().getUserId());
			nwkSubscriptionDtlsEntity.setCreatedTs(Utils.getCurrentDate());
			nwkSubscriptionDtlsEntity.setRecordStatus("A");

			// Calling Create User API
			com.iexceed.esoko.jaxb.login.crtusr.Header userHeader = new com.iexceed.esoko.jaxb.login.crtusr.Header();
			userDtls.setCountry(subscribRegister.getLocation());
			userDtls.setEmail(subscribRegister.getEmail());
			userDtls.setGender(subscribRegister.getGender());
			userDtls.setNetworkId(subscribRegister.getNetworkId());
			userDtls.setOccupationId(subscribRegister.getOccupation());
			userDtls.setPhone(subscribRegister.getMobile());
			userDtls.setYearOfBirth(subscribRegister.getDob());
			usrDtlList.add(userDtls);
			userHeader.setUserId(req.getHeader().getUserId());
			userReq.getUSERDETAIL().addAll(usrDtlList);
			userReq.setHeader(userHeader);
			// userService.createEsokoUser(userReq);
			// Calling create netwrok API
			nwkEntity.setNetworkId(subscribRegister.getNetworkId());
			boolean nwkEntryExist = nwkRepo.exists(nwkEntity,
					subscribRegister.getNetworkId());
			if (!nwkEntryExist) {
				log.info("Inside calling network details save api");
				CreateNetworkReq nwkReq = new CreateNetworkReq();
				com.iexceed.esoko.jaxb.se.Header header1 = new com.iexceed.esoko.jaxb.se.Header();
				header1.setUserId(req.getHeader().getUserId());
				CRNWKDETAILS nwkDtls = new CRNWKDETAILS();
				nwkDtls.setNetworkID(networkId);
				nwkDtls.setType("G");
				nwkDtls.setNetworkOwner(subscribRegister.getNetworkId());
				nwkDtls.setName(subscribRegister.getName());
				// nwkDtls.setParentid(subscribRegister.getLocation());
				nwkDtls.setCountry(subscribRegister.getLocation());
				nwkReq.setCRNWKDETAILS(nwkDtls);
				nwkReq.setHeader(header1);
				nwkService.createNetwork(nwkReq);
			} else {
				entity.setNetworkId(subscribRegister.getNetworkId());
				networkSubscriptionPK.setNetworkId(subscribRegister
						.getNetworkId());
				nwkSubscriptionDtlsPk.setNetworkId(subscribRegister
						.getNetworkId());
			}

			if (!subscriberRepo.exists(entity, subscribRegister.getName())) {
				subscriberRepo.save(entity);
				invoiceRepo.save(invoiceEntity);
				nwkSubscriptionRepo.save(networkSubscription);
				nwkSubscripDtlsRepo.save(nwkSubscriptionDtlsEntity);
				ERROR = ERROR_CODE.SUBR_CR_SC;
			} else {
				SubscriberMaster temp_entity = subscriberRepo.findOne(entity,
						entity.getSubscriberId());
				entity.setCreatedBy(temp_entity.getCreatedBy());
				entity.setCreatedTs(temp_entity.getCreatedTs());
				entity.setModifiedBy(req.getHeader().getUserId());
				entity.setModifiedTs(Utils.getCurrentDate());
				subscriberRepo.merge(entity);
				invoiceRepo.merge(invoiceEntity);
				nwkSubscriptionRepo.merge(networkSubscription);
				nwkSubscripDtlsRepo.merge(nwkSubscriptionDtlsEntity);
				ERROR = ERROR_CODE.SUBR_UD_SC;
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerSubscriptionService",
				"Reseller Subscriber Registration", "", ERROR);
		header.setExtraParams(sub_id);
		res.setHeader(header);
		return res;
	}

	public QuerySubscriptionUpgradeRes querySubscriptionUpgrade(
			String networkId, String type, String currencyId) {
		QuerySubscriptionUpgradeRes res = new QuerySubscriptionUpgradeRes();
		String currencySymbol = null;
		log.info("Inside QuerySubscriptionUpgradeRes -> querySubscriptionUpgrade");
		log.debug("networkId::" + networkId);
		log.debug("subscrib_type::" + type);
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(type)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}
		List<NetworkSubscriptionApp> nwkSubAppList = appsRepo
				.getNwkSubAppByNwkIdType(networkId, type);
		List<Map<String, Object>> defaultList = nwkSubscriptionRepo
				.querySubsriptionDetails(networkId, type);
		Forex forex = null;
		Currency currency = null;
		if (!("USD".equalsIgnoreCase(currencyId))) {
			forex = forexRepo.queryfxRatesbyCcy("USD", currencyId);
			if (forex != null) {
				currency = currRepo.findOne(Currency.class,
						forex.getQuoteCurrencyId());
				currencySymbol = currency.getSymbol();
			} else {
				currencySymbol = "$";
			}

		} else {
			currencySymbol = "$";
		}
		// QRYSUBSCRIBUPGRADELIST qrySubscripUpgradeList = new
		// ArrayList<QRYSUBSCRIBUPGRADELIST>();
		String newNetwork = null;
		String prevNetwork = null;
		int months = 0;
		QRYSUBSCRIBUPGRADELIST qrPriceDtls = new QRYSUBSCRIBUPGRADELIST();

		if (defaultList.size() > 0) {
			// for (int i = 0; i < periods.length; i++) {
			log.debug("entityList::" + defaultList);
			// log.debug("periods::" + periods[i]);

			for (Map<String, Object> map : defaultList) {
				newNetwork = networkId;
				if (!newNetwork.equals(prevNetwork)) {
					qrPriceDtls = null;
					qrPriceDtls = new QRYSUBSCRIBUPGRADELIST();
					qrPriceDtls.setNetworkId(newNetwork);
				}

				qrPriceDtls.setCurrencySymbol(currencySymbol);

				if (map.get("owner") != null) {
					qrPriceDtls.setOwnerId(map.get("owner").toString());
				}
				if (map.get("balance") != null) {
					qrPriceDtls.setBalance(map.get("balance").toString());
				}
				if (map.get("currency") != null) {
					qrPriceDtls.setCurrency(map.get("currency").toString());
				}
				if (map.get("account") != null) {
					qrPriceDtls.setAccountNo(map.get("account").toString());
				}
				if (map.get("network_name") != null) {
					qrPriceDtls.setNetworkName(map.get("network_name")
							.toString());
				}
				if (map.get("period") != null) {
					qrPriceDtls.setPeriod(Integer.valueOf(map.get("period")
							.toString()));
				} else {
					qrPriceDtls.setPeriod(3);
				}
				if (map.get("casReseller") != null) {
					qrPriceDtls.setCostReseller(Double.valueOf(map.get(
							"casReseller").toString()));
				}
				if (map.get("marginReseller") != null) {
					qrPriceDtls.setMarginEarned(Double.valueOf(map.get(
							"marginReseller").toString()));
				}

				if (map.get("bonus") != null) {
					log.debug("ANNUAL_PYMT_BONUS::"
							+ map.get("bonus").toString());
					if ("".equals((Utils.stripNonDigits(map.get("bonus")
							.toString())))) {
						months = 12;
					} else {
						months = 12 - Integer.parseInt(Utils.stripNonDigits(map
								.get("bonus").toString()));
					}
				}
				try {
					QRYSUBSCRIBUPGRADE qrPrices = new QRYSUBSCRIBUPGRADE();
					if (map.get("param_name") != null) {
						qrPrices.setParamName(SubscriptionServicesEnum.valueOf(
								map.get("param_name").toString().toUpperCase())
								.getServiceCode());
					}

					if (map.get("param_value") != null && forex != null
							&& currency != null) {
						String str3 = Utils.stripNonDigits(map.get(
								"param_value").toString());
						if (StringUtils.isNotEmpty(str3)) {
							double value_3 = Double.parseDouble(str3) * 3
									* forex.getRate().doubleValue();
							double value_6 = Double.parseDouble(str3) * 6
									* forex.getRate().doubleValue();
							double value_9 = Double.parseDouble(str3) * 9
									* forex.getRate().doubleValue();
							double value_year = Double.parseDouble(str3)
									* months * forex.getRate().doubleValue();
							Locale locale = new Locale("us", "US");
							NumberFormat numberFormat = NumberFormat
									.getInstance(locale);
							qrPrices.setPeriod3(currencySymbol
									+ numberFormat.format(value_3));
							qrPrices.setPeriod6(currencySymbol
									+ numberFormat.format(value_6));
							qrPrices.setPeriod9(currencySymbol
									+ numberFormat.format(value_9));
							qrPrices.setPeriodYear(currencySymbol
									+ numberFormat.format(value_year));
							log.debug("value_3::" + currencySymbol + value_3);
							log.debug("value_6::" + currencySymbol + value_6);
							log.debug("value_9::" + currencySymbol + value_9);
							log.debug("value_year::" + currencySymbol
									+ value_year);
						} else {
							qrPrices.setPeriod3(currencySymbol + "0");
							qrPrices.setPeriod6(currencySymbol + "0");
							qrPrices.setPeriod9(currencySymbol + "0");
							qrPrices.setPeriodYear(currencySymbol + "0");
						}
					} else if (map.get("param_value") != null) {
						String str3 = Utils.stripNonDigits(map.get(
								"param_value").toString());
						if (StringUtils.isNotEmpty(str3)) {
							double value_3 = Double.parseDouble(str3) * 3;
							double value_6 = Double.parseDouble(str3) * 6;
							double value_9 = Double.parseDouble(str3) * 9;
							double value_year = Double.parseDouble(str3)
									* months;
							Locale locale = new Locale("us", "US");
							NumberFormat numberFormat = NumberFormat
									.getInstance(locale);
							qrPrices.setPeriod3(currencySymbol
									+ numberFormat.format(value_3));
							qrPrices.setPeriod6(currencySymbol
									+ numberFormat.format(value_6));
							qrPrices.setPeriod9(currencySymbol
									+ numberFormat.format(value_9));
							qrPrices.setPeriodYear(currencySymbol
									+ numberFormat.format(value_year));
							log.debug("value_3::" + currencySymbol + value_3);
							log.debug("value_6::" + currencySymbol + value_6);
							log.debug("value_9::" + currencySymbol + value_9);
							log.debug("value_year::" + currencySymbol
									+ value_year);
						} else {
							qrPrices.setPeriod3(currencySymbol + "0");
							qrPrices.setPeriod6(currencySymbol + "0");
							qrPrices.setPeriod9(currencySymbol + "0");
							qrPrices.setPeriodYear(currencySymbol + "0");
						}
					}
					// }

					qrPriceDtls.getQRYSUBSCRIBUPGRADE().add(qrPrices);

					// if (!newNetwork.equals(prevNetwork)) {
					// qrySubscripUpgradeList.add(qrPriceDtls);
					// }
					prevNetwork = newNetwork;
				} catch (Exception e) {

				}
				// }
				// qrPriceDtls = new QRYSUBSCRIBUPGRADELIST();
				// periods[periods.length - 1] = months;
			}
			for (NetworkSubscriptionApp nwkSubApp : nwkSubAppList) {
				if (!(nwkSubApp.getId().getParamName()
						.equalsIgnoreCase("Discount"))
						&& !(nwkSubApp.getId().getParamName()
								.equalsIgnoreCase("VAT tax"))) {
					EXTRA extra = new EXTRA();
					extra.setParamName(nwkSubApp.getId().getParamName());
					log.debug("Param name:" + nwkSubApp.getId().getParamName());
					extra.setParamValue(nwkSubApp.getParamValue());
					log.debug("Param value:" + nwkSubApp.getParamValue());
					qrPriceDtls.getEXTRA().add(extra);
				}

			}
			for (NetworkSubscriptionApp nwkSubApp : nwkSubAppList) {
				if (nwkSubApp.getId().getParamName()
						.equalsIgnoreCase("Discount")) {
					EXTRA extra = new EXTRA();
					extra.setParamName(nwkSubApp.getId().getParamName());
					log.debug("Param name:" + nwkSubApp.getId().getParamName());
					extra.setParamValue(nwkSubApp.getParamValue());
					log.debug("Param value:" + nwkSubApp.getParamValue());
					qrPriceDtls.getEXTRA().add(extra);
				}

			}
			for (NetworkSubscriptionApp nwkSubApp : nwkSubAppList) {
				if (nwkSubApp.getId().getParamName()
						.equalsIgnoreCase("VAT tax")) {
					EXTRA extra = new EXTRA();
					extra.setParamName(nwkSubApp.getId().getParamName());
					log.debug("Param name:" + nwkSubApp.getId().getParamName());
					extra.setParamValue(nwkSubApp.getParamValue());
					log.debug("Param value:" + nwkSubApp.getParamValue());
					qrPriceDtls.getEXTRA().add(extra);
				}

			}
			log.debug("qrySubscripUpgradeList::" + qrPriceDtls);
			res.setQRYSUBSCRIBUPGRADELIST(qrPriceDtls);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"QueryResellerPrices", "Query Reseller Price DtlsRes", "",
				ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public SubscripInvoiceUpgradeRes upgradeSubscriptionInvoices(
			SubscripInvoiceUpgradeReq req) {
		log.info("Inside SubscripInvoiceUpgradeRes -> upgradeSubscriptionInvoices");
		SubscripInvoiceUpgradeRes res = new SubscripInvoiceUpgradeRes();
		UPGRADEINVOICES invoiceDtls = req.getUPGRADEINVOICES();
		List<INVOICEAPPS> invocieAppsList = req.getUPGRADEINVOICES()
				.getINVOICEAPPS();
		System_account sys_net_acc = null;
		String operationName = req.getHeader().getOperationName();
		log.info("Operation Name ->" + operationName);
		boolean accountingReqd = false;
		boolean offerCredit = false;
		double smsBalance = 0;
		double voiceBalance = 0;

		if (operationName.equalsIgnoreCase("InvoiceOnly")) {
			accountingReqd = false;
		} else if (operationName.equalsIgnoreCase("Upgrade")) {
			accountingReqd = true;
		} else if (operationName.equalsIgnoreCase("UpgradeCredit")) {
			accountingReqd = true;
			offerCredit = true;
		}

		log.info("Accounting Reqd ->" + accountingReqd);
		log.info("Offer Credit ->" + offerCredit);

		if (!StringUtils.isNotEmpty(invoiceDtls.getNetworkId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(invoiceDtls.getSubscriptionType())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(invoiceDtls.getResellerId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}

		log.info("Subscriber ID -> " + invoiceDtls.getSubscriberName());
		log.info("Reseller ID -> " + invoiceDtls.getResellerId());
		log.info("Network ID -> " + invoiceDtls.getNetworkId());

		if (accountingReqd) {
			log.info("Accounting Required:::" + accountingReqd);
			log.info("Network Id:::" + invoiceDtls.getNetworkId());
			ResellerMaster resellerMaster = resellerDtlRepo.findOne(
					ResellerMaster.class, invoiceDtls.getResellerId());
			sys_net_acc = sysAccRepo.getNetworkAccount(invoiceDtls
					.getNetworkId());
			if (sys_net_acc.getBalance() < (Double.parseDouble(invoiceDtls
					.getResellerCost())
					+ Double.parseDouble(invoiceDtls.getMarginEarned())
					+ smsBalance + voiceBalance)
					&& !offerCredit) {
				HeaderFactory.setExtraParamMap("$1",
						String.valueOf((Double.parseDouble(invoiceDtls
								.getNetworkBalance()) - sys_net_acc
								.getBalance())));
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						"ResellerSubscriptionService", "Subscription Suspend",
						"", ERROR_CODE.SUBR_OF_CR);
				res.setHeader(header);
				return res;
			} else if (sys_net_acc != null && resellerMaster != null) {
				log.info("Network Account Balance:::"
						+ sys_net_acc.getBalance());
				log.info("Invoice Amount:::" + invoiceDtls.getTotal());
				log.info("Offer Credit:::" + offerCredit);
				if (sys_net_acc.getBalance() >= (Double.parseDouble(invoiceDtls
						.getResellerCost())
						+ Double.parseDouble(invoiceDtls.getMarginEarned())
						+ smsBalance + voiceBalance)
						&& !offerCredit) {
					Accounting accounting = new Accounting();
					accounting.setDebitAcNo(sys_net_acc.getAccountNo());
					accounting.setDebitCcy(sys_net_acc.getAcCurrency());
					accounting.setDebitForeignCcy(invoiceDtls.getCurrency());
					accounting.setNetworkId(invoiceDtls.getNetworkId());
					accounting.setResellerAcNo(sysAccRepo.getNetworkAccount(
							resellerMaster.getNetworkId()).getAccountNo());
					accounting.setTransactionDate(Utils.getCurrentDate());
					accounting.setUserId(req.getHeader().getUserId());
					accounting.setTrnCode(TRANSACTION_CODE.INVOICE_ACC.name());
					Cost cost = new Cost();
					/*
					 * cost.setBaseCost((Double.parseDouble(invoiceDtls
					 * .getResellerCost()) +
					 * Double.parseDouble(invoiceDtls.getMarginEarned()) +
					 * smsBalance + voiceBalance));
					 */
					cost.setBaseCost(0);
					Double retailCost = Double.parseDouble(invoiceDtls
							.getResellerCost());
					cost.setRetailPrice(retailCost);
					cost.setWholesalePrice(Double.parseDouble(invoiceDtls
							.getMarginEarned()));
					accounting.setTrnAmt(cost);
					invoiceAccounting.doAccounting(accounting);

					header = (Header) HeaderFactory.getHeader(HeaderType.SS,
							"ResellerSubscriptionService",
							"Upgrade Subscription Invoices", "",
							ERROR_CODE.SUB_UPGRD_INVCE);

				} else if (sys_net_acc.getBalance() < (Double
						.parseDouble(invoiceDtls.getResellerCost())
						+ Double.parseDouble(invoiceDtls.getMarginEarned())
						+ smsBalance + voiceBalance)
						&& offerCredit) {
					System_account sys_rsl_acc = sysAccRepo
							.getNetworkAccount(resellerMaster.getNetworkId());
					if (sys_rsl_acc.getBalance() < (Double
							.parseDouble(invoiceDtls.getResellerCost())
							+ Double.parseDouble(invoiceDtls.getMarginEarned())
							+ smsBalance + voiceBalance)) {
						header = (Header) HeaderFactory.getHeader(
								HeaderType.SS, "ResellerSubscriptionService",
								"Upgrade Subscription Invoices", "",
								ERROR_CODE.SUB_UPGRD_INV_ER);
						res.setHeader(header);
						return res;
					} else {
						Accounting accounting = new Accounting();
						accounting.setDebitAcNo(sys_rsl_acc.getAccountNo());
						accounting.setDebitCcy(sys_rsl_acc.getAcCurrency());
						accounting
								.setDebitForeignCcy(invoiceDtls.getCurrency());
						accounting.setNetworkId(resellerMaster.getNetworkId());
						accounting.setResellerAcNo(sys_rsl_acc.getAccountNo());
						accounting.setTransactionDate(Utils.getCurrentDate());
						accounting.setUserId(req.getHeader().getUserId());
						accounting.setTrnCode(TRANSACTION_CODE.INVOICE_ACC
								.name());
						Cost cost = new Cost();
						cost.setBaseCost(0);
						cost.setRetailPrice(Double.parseDouble(invoiceDtls
								.getResellerCost())
								+ Double.parseDouble(invoiceDtls
										.getMarginEarned()));
						cost.setWholesalePrice(Double.parseDouble(invoiceDtls
								.getResellerCost()));
						accounting.setTrnAmt(cost);
						invoiceAccounting.doAccounting(accounting);
						header = (Header) HeaderFactory.getHeader(
								HeaderType.SS, "ResellerSubscriptionService",
								"Upgrade Subscription Invoices", "",
								ERROR_CODE.SUB_UPGRD_INVCE);

					}

				}

			}
		} else {
			ERROR = ERROR_CODE.SUB_UPGRD_INVCE;
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"SubscripInvoiceUpgradeRes",
					"Upgrade Subscription Invoices", "", ERROR);
		}
		NetworkSubscription nwkSubscriptionEntity = nwkSubscriptionRepo
				.queryExistingOne(invoiceDtls.getResellerId(),
						invoiceDtls.getNetworkId(),
						invoiceDtls.getSubscriberName());

		NetworkSubscriptionDetail nwkSubscriptionEntityDtls = nwkSubscripDtlsRepo
				.getSubscriberDetails(invoiceDtls.getSubscriberName());

		if (subscriberRepo.exists(SubscriberMaster.class,
				invoiceDtls.getSubscriberName())) {
			SubscriberMaster subMaster = subscriberRepo.findOne(
					SubscriberMaster.class, invoiceDtls.getSubscriberName());
			subMaster.setSubscriberId(invoiceDtls.getSubscriberName());
			subMaster.setSubscriptionType(invoiceDtls.getSubscriptionType());
			subMaster.setResellerId(invoiceDtls.getResellerId());
			subscriberRepo.merge(subMaster);
		} else {
			SubscriberMaster subMaster = new SubscriberMaster();
			subMaster.setSubscriberId(invoiceDtls.getSubscriberName());
			subMaster.setSubscriptionType(invoiceDtls.getSubscriptionType());
			subMaster.setResellerId(invoiceDtls.getResellerId());
			subMaster.setNetworkId(invoiceDtls.getNetworkId());
			subMaster.setCreatedBy(req.getHeader().getUserId());
			subMaster.setCreatedTs(Utils.getCurrentDate());
			subscriberRepo.save(subMaster);
		}
		if (nwkSubscriptionEntity != null && nwkSubscriptionEntityDtls != null) {
			InvoiceDetail invoiceDetail = invoiceRepo
					.queryInvoiceDetails(nwkSubscriptionEntityDtls.getId()
							.getInvoiceId());

			String invoiceNumber = invoiceDetail.getInvoiceNo();
			log.debug("SubscriptionType::" + invoiceDtls.getSubscriptionType());
			log.debug("balance::" + invoiceDtls.getNetworkBalance());
			nwkSubscriptionEntity.setBalance(nwkSubscriptionEntity.getBalance()
					+ Double.parseDouble(invoiceDtls.getTotal()));
			nwkSubscriptionEntity.setSubscriptionType(invoiceDtls
					.getSubscriptionType());
			nwkSubscriptionEntity.setCreatedBy(req.getHeader().getUserId());
			nwkSubscriptionEntity.setCreatedTs(Utils.getCurrentDate());
			log.debug("ExpiryDate::" + invoiceDtls.getExpiryDate());
			nwkSubscriptionEntity.setEndDate(Utils.getFormatedDate3(invoiceDtls
					.getExpiryDate()));
			nwkSubscriptionEntity.setRecordStatus("A");
			log.debug("Reseller Id::" + invoiceDtls.getResellerId());
			nwkSubscriptionEntity.setResellerId(invoiceDtls.getResellerId());
			log.debug("StartDate::" + invoiceDtls.getStartDate());
			nwkSubscriptionEntity.setStartDate(Utils
					.getFormatedDate3(invoiceDtls.getStartDate()));
			log.debug("Currency::" + invoiceDtls.getCurrency());
			nwkSubscriptionEntity.setCurrency(invoiceDtls.getCurrency());
			log.debug("OwnerName::" + invoiceDtls.getOwnerName());
			nwkSubscriptionEntity.setOwnerName(invoiceDtls.getOwnerName());
			nwkSubscriptionEntity.setModifiedBy(req.getHeader().getUserId());
			nwkSubscriptionEntity.setModifiedTs(Utils.getCurrentDate());

			NetworkSubscriptionDetailPK nwkSubscriptionDtlsPk = nwkSubscriptionEntityDtls
					.getId();

			log.debug("invoiceNumber::" + invoiceNumber);
			nwkSubscriptionDtlsPk.setInvoiceId(invoiceNumber);
			log.debug("NetworkId::" + invoiceDtls.getNetworkId());
			nwkSubscriptionDtlsPk.setNetworkId(invoiceDtls.getNetworkId());
			nwkSubscriptionDtlsPk.setResellerId(invoiceDtls.getResellerId());
			nwkSubscriptionEntityDtls.setId(nwkSubscriptionDtlsPk);
			nwkSubscriptionEntityDtls.setSubscriptionType(invoiceDtls
					.getSubscriptionType());
			log.debug("Amount"
					+ Double.parseDouble(invoiceDtls.getNetworkBalance()));
			nwkSubscriptionEntityDtls.setAmount(Double.parseDouble(invoiceDtls
					.getNetworkBalance()));
			nwkSubscriptionEntityDtls.setCasReseller(Double
					.parseDouble(invoiceDtls.getResellerCost()));
			nwkSubscriptionEntityDtls.setCreatedBy(req.getHeader().getUserId());
			nwkSubscriptionEntityDtls.setCreatedTs(Utils.getCurrentDate());
			log.debug("Discount::" + invoiceDtls.getDiscount());
			nwkSubscriptionEntityDtls.setDiscount(Integer.parseInt(invoiceDtls
					.getDiscount()));
			log.debug("Margin Earned::" + invoiceDtls.getMarginEarned());
			nwkSubscriptionEntityDtls.setMarginReseller(Double
					.parseDouble(invoiceDtls.getMarginEarned()));
			log.debug("Period::" + invoiceDtls.getPeriod());
			nwkSubscriptionEntityDtls.setPeriod(invoiceDtls.getPeriod());
			nwkSubscriptionEntityDtls.setRecordStatus("A");
			nwkSubscriptionEntityDtls.setVat(Double.parseDouble(invoiceDtls
					.getVat()));
			nwkSubscriptionEntityDtls.setDescription(invoiceDtls
					.getSubscriptionType()
					+ "Subscription From : "
					+ invoiceDtls.getStartDate()
					+ " to "
					+ invoiceDtls.getExpiryDate());

			invoiceDetail.setInvoiceNo(invoiceNumber);
			invoiceDetail.setInvoiceAmount(BigDecimal.valueOf(Double
					.parseDouble(invoiceDtls.getTotal())));
			if (offerCredit) {
				invoiceDetail.setDueAmount(new BigDecimal(Double
						.parseDouble(invoiceDtls.getNetworkBalance())
						- sys_net_acc.getBalance()));
			} else {
				invoiceDetail.setDueAmount(BigDecimal.ZERO);
			}
			invoiceDetail.setCreatedBy(req.getHeader().getUserId());
			invoiceDetail.setCreatedTs(Utils.getCurrentDate());
			invoiceDetail.setCurrency(invoiceDtls.getCurrency());
			invoiceDetail.setInvoiceDate(Utils.getCurrentDate());
			invoiceDetail.setInvoiceFrom(invoiceDtls.getResellerId());
			invoiceDetail.setInvoiceTo(invoiceDtls.getNetworkId());
			invoiceDetail.setDso(invoiceDtls.getDso());
			invoiceDetail.setPaid_Status("I");

			if (invocieAppsList.size() != 0) {
				log.debug("invocieAppsList::" + invocieAppsList);
				appsRepo.deleteByNwkIdAndType(invoiceDtls.getNetworkId(),
						invoiceDtls.getSubscriptionType());
				for (INVOICEAPPS invoiceAppDtls : invocieAppsList) {
					// Saving subscription apps details
					NetworkSubscriptionApp nwkAppEntity = new NetworkSubscriptionApp();
					NetworkSubscriptionAppPK nwkAppEntiAppPK = new NetworkSubscriptionAppPK();
					nwkAppEntiAppPK.setInvoiceNo(invoiceNumber);
					nwkAppEntiAppPK.setNetworkId(invoiceDtls.getNetworkId());
					nwkAppEntiAppPK.setParamName(invoiceAppDtls.getParamName());
					nwkAppEntiAppPK.setSubscriptionId(nwkSubscriptionEntity
							.getId().getSubscriptionId());
					nwkAppEntiAppPK.setSubscriptionType(invoiceDtls
							.getSubscriptionType());
					nwkAppEntity.setId(nwkAppEntiAppPK);
					if (!("".equals(invoiceAppDtls.getParamValue()))) {
						nwkAppEntity.setParamValue(invoiceAppDtls
								.getParamValue());
					}
					nwkAppEntity.setPeriod(invoiceDtls.getPeriod() + "");
					// SubscriptionServicesEnum
					nwkAppEntity.setDescription(invoiceDtls
							.getSubscriptionType()
							+ " "
							+ invoiceAppDtls.getParamName().toUpperCase()
							+ " (" + invoiceDtls.getPeriod() + "Months )");
					log.debug("Description::" + nwkAppEntity.getDescription());
					if (invoiceAppDtls.getParamName().equalsIgnoreCase("SMS")) {
						smsBalance = Double.parseDouble(invoiceAppDtls
								.getParamValue());
					}
					if (invoiceAppDtls.getParamName().equalsIgnoreCase("VOICE")) {
						voiceBalance = Double.parseDouble(invoiceAppDtls
								.getParamValue());
					}
					appsRepo.save(nwkAppEntity);

				}
			}
			invoiceDetail.setInvoiceAmount(BigDecimal.valueOf(Double
					.parseDouble(invoiceDtls.getResellerCost())
					+ Double.parseDouble(invoiceDtls.getMarginEarned())
					+ smsBalance + voiceBalance));
			nwkSubscripDtlsRepo.merge(nwkSubscriptionEntityDtls);
			nwkSubscriptionRepo.merge(nwkSubscriptionEntity);
			invoiceRepo.merge(invoiceDetail);
		} else {

		}
		res.setHeader(header);
		return res;
	}

	public List<QRRESELLERINVOICES> queryResellerInvoices(String networkId,
			String subscriptionType, String resellerId) {
		List<QRRESELLERINVOICES> qryInvoice = new ArrayList<QRRESELLERINVOICES>();
		List<InvoiceDetail> invoiceList = nwkSubscriptionRepo
				.queryResellerSubscriptionInvoices(networkId, subscriptionType,
						resellerId);
		if (invoiceList.size() != 0) {
			log.info("Inside queryResellerInvoices::" + invoiceList);
			for (InvoiceDetail entity : invoiceList) {
				QRRESELLERINVOICES invoices = new QRRESELLERINVOICES();
				invoices.setInvoiceNo(entity.getInvoiceNo());
				invoices.setAmount(entity.getInvoiceAmount().toString());
				qryInvoice.add(invoices);
			}
		}
		return qryInvoice;
	}

	@Transactional
	public ResellerSubscriptionTopupRes resellerSubscriptionTopup(
			ResellerSubscriptionTopupReq req) {
		log.info("Inside ResellerSubscriptionTopupRes -> resellerSubscriptionTopup");
		ResellerSubscriptionTopupRes res = new ResellerSubscriptionTopupRes();
		SUBSCRIPTOPUP topUpDtls = req.getSUBSCRIPTOPUP();
		log.debug("Network Id::" + topUpDtls.getNetworkId());
		log.debug("Row Id::" + topUpDtls.getRowId());
		log.debug("Subscription Type::" + topUpDtls.getSubscriptionType());
		if (!StringUtils.isNotEmpty(topUpDtls.getNetworkId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		/*
		 * if (topUpDtls.getRowId() == 0) { header = (Header)
		 * HeaderFactory.getHeader(HeaderType.SS, "ResellerSubscriptionService",
		 * "Subscription Suspend", "", ERROR_CODE.ES_PK_002);
		 * res.setHeader(header); return res; }
		 */

		if (!StringUtils.isNotEmpty(topUpDtls.getSubscriptionType())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService", "Subscription Suspend", "",
					ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}

		double topupAmount = 0;
		if (topUpDtls.getTopupAmount() != null) {
			topupAmount = Double.parseDouble(topUpDtls.getTopupAmount());
		}
		NetworkSubscription nwkSubscripEntity = new NetworkSubscription();
		NetworkSubscriptionPK nwkPkEntity = new NetworkSubscriptionPK();
		nwkPkEntity.setNetworkId(topUpDtls.getNetworkId());
		nwkPkEntity.setSubscriptionId(topUpDtls.getRowId());
		nwkSubscripEntity.setId(nwkPkEntity);
		nwkSubscripEntity.setSubscriptionType(topUpDtls.getSubscriptionType());
		NetworkSubscription tempNwkEntity = nwkSubscriptionRepo.findOne(
				nwkSubscripEntity, nwkPkEntity);
		List<TOPUPINVOICES> invoiceLIst = req.getSUBSCRIPTOPUP()
				.getTOPUPINVOICES();
		if (invoiceLIst.size() != 0) {
			for (TOPUPINVOICES invoices : invoiceLIst) {
				if (!StringUtils.isNotEmpty(invoices.getInvoiceNo())) {
					header = (Header) HeaderFactory.getHeader(HeaderType.SS,
							"ResellerSubscriptionService",
							"Subscription Topup", "", ERROR_CODE.INVOICE_NO_ER);
					res.setHeader(header);
					return res;
				}
				InvoiceDetail entity = new InvoiceDetail();
				entity.setInvoiceNo(invoices.getInvoiceNo());
				InvoiceDetail tempEntity = invoiceRepo.findOne(entity,
						invoices.getInvoiceNo());
				entity.setCreatedBy(tempEntity.getCreatedBy());
				entity.setCreatedTs(tempEntity.getCreatedTs());
				entity.setCurrency(tempEntity.getCurrency());
				entity.setDso(tempEntity.getDso());
				entity.setInvImage(tempEntity.getInvImage());
				entity.setInvoiceFrom(tempEntity.getInvoiceFrom());
				entity.setInvoiceTo(tempEntity.getInvoiceTo());
				entity.setPaid_Date(Utils.getCurrentDate());
				entity.setPaid_Status("P");
				entity.setInvoiceDate(tempEntity.getInvoiceDate());
				entity.setInvoiceAmount(tempEntity.getInvoiceAmount());
				if (entity.getInvoiceAmount() != null) {
					topupAmount = topupAmount
							- Double.parseDouble(entity.getInvoiceAmount()
									.toString());
				}
				invoiceRepo.merge(entity);

			}
		}
		if (tempNwkEntity != null) {
			log.debug("topupAmount::" + topupAmount);
			if (topupAmount > 0) {
				nwkSubscripEntity.setBalance(topupAmount
						+ tempNwkEntity.getBalance());
			}
			long diff = tempNwkEntity.getEndDate().getTime()
					- tempNwkEntity.getStartDate().getTime();
			int diffInDays = (int) ((diff) / (1000 * 60 * 60 * 24));
			Calendar cal = Calendar.getInstance();
			log.debug("DiffInDays :: " + diffInDays);
			cal.add(Calendar.DATE, diffInDays);
			nwkSubscripEntity.setStartDate(Utils.getCurrentDate());
			nwkSubscripEntity.setEndDate(new Timestamp(cal.getTimeInMillis()));
			nwkSubscripEntity.setResellerId(tempNwkEntity.getResellerId());
			nwkSubscripEntity.setRecordStatus(tempNwkEntity.getRecordStatus());
			nwkSubscripEntity.setCreatedBy(tempNwkEntity.getCreatedBy());
			nwkSubscripEntity.setCreatedTs(tempNwkEntity.getCreatedTs());
			nwkSubscripEntity.setModifiedBy(req.getHeader().getUserId());
			nwkSubscripEntity.setModifiedTs(Utils.getCurrentDate());
			nwkSubscripEntity.setOwnerName(tempNwkEntity.getOwnerName());
			nwkSubscripEntity
					.setPreventLogins(tempNwkEntity.getPreventLogins());
			nwkSubscripEntity
					.setSuspendDebits(tempNwkEntity.getSuspendDebits());
			nwkSubscripEntity.setCurrency(tempNwkEntity.getCurrency());
			nwkSubscriptionRepo.merge(nwkSubscripEntity);
		}
		ERROR = ERROR_CODE.SUB_UPGRD_INVCE;
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"SubscripInvoiceUpgradeRes", "Reseller Subscription Topup", "",
				ERROR);
		res.setHeader(header);
		return res;

	}

	public QueryResellerSubscripInvoicesRes queryresellerSubscripInvoices(
			String networkId, String subscrib_type, String reseller_id) {
		log.info("Inside QueryResellerSubscripInvoicesRes -> queryresellerSubscripInvoices");
		log.debug("networkId::" + networkId);
		log.debug("subscrib_type::" + subscrib_type);
		log.debug("reseller_id::" + reseller_id);
		QueryResellerSubscripInvoicesRes res = new QueryResellerSubscripInvoicesRes();
		List<Map<String, Object>> invoicesList = nwkSubscriptionRepo
				.queryResellerSubscriberInvoices(networkId, subscrib_type,
						reseller_id);
		List<QRRSLRSUBSINVOICES> qrRslrList = new ArrayList<QRRSLRSUBSINVOICES>();
		if (invoicesList.size() != 0) {
			for (Map<String, Object> map : invoicesList) {
				QRRSLRSUBSINVOICES rslrInvoicesDtls = new QRRSLRSUBSINVOICES();
				if (map.get("date") != null) {
					rslrInvoicesDtls.setDate(map.get("date").toString());
				}
				if (map.get("invoice_no") != null) {
					rslrInvoicesDtls.setInvoiceNo(map.get("invoice_no")
							.toString());
				}
				if (map.get("network_id") != null) {
					rslrInvoicesDtls.setNetworkId(map.get("network_id")
							.toString());
				}
				if (map.get("network_name") != null) {
					rslrInvoicesDtls.setNetworkName(map.get("network_name")
							.toString());
				}
				if (map.get("due") != null) {
					rslrInvoicesDtls.setDue(map.get("due").toString());
				}
				if (map.get("amount") != null) {
					rslrInvoicesDtls.setAmount(map.get("amount").toString());
				}
				qrRslrList.add(rslrInvoicesDtls);
			}
			res.getQRRSLRSUBSINVOICES().addAll(qrRslrList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"QueryResellerSubscripInvoicesRes",
				"Query Reseller Subscriber Invoices", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryAllResellerInvoicesRes queryAllResellerInvoices(
			String reseller_id) {
		log.info("Inside QueryAllResellerInvoicesRes -> queryAllResellerInvoices");
		log.debug("reseller_id::" + reseller_id);
		QueryAllResellerInvoicesRes res = new QueryAllResellerInvoicesRes();
		List<Map<String, Object>> invoicesList = nwkSubscriptionRepo
				.queryAllResellerInvoices(reseller_id);
		List<QRALLRSLRINVOICES> qrRslrList = new ArrayList<QRALLRSLRINVOICES>();
		if (invoicesList.size() != 0) {
			for (Map<String, Object> map : invoicesList) {
				QRALLRSLRINVOICES rslrInvoicesDtls = new QRALLRSLRINVOICES();
				if (map.get("date") != null) {
					rslrInvoicesDtls.setDate(map.get("date").toString());
				}
				if (map.get("invoice_no") != null) {
					rslrInvoicesDtls.setInvoiceNo(map.get("invoice_no")
							.toString());
				}
				if (map.get("network_id") != null) {
					rslrInvoicesDtls.setNetworkId(map.get("network_id")
							.toString());
				}
				if (map.get("network_name") != null) {
					rslrInvoicesDtls.setNetworkName(map.get("network_name")
							.toString());
				}
				if (map.get("due") != null) {
					rslrInvoicesDtls.setDue(map.get("due").toString());
				}
				if (map.get("amount") != null) {
					rslrInvoicesDtls.setAmount(map.get("amount").toString());
				}
				qrRslrList.add(rslrInvoicesDtls);
			}
			res.getQRALLRSLRINVOICES().addAll(qrRslrList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"QueryResellerSubscripInvoicesRes",
				"Query All Reseller Invoices", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public QueryInvoicePaymentRes generateInvoicePDF(String invoiceId) {
		log.info("Inside QueryAllResellerInvoicesRes -> generateInvoicePDF");
		log.debug("invoiceId::" + invoiceId);
		QueryInvoicePaymentRes res = new QueryInvoicePaymentRes();
		InvoiceReport invoiceReport = new InvoiceReport();
		List<Map<String, Object>> invoiceList = invoiceRepo
				.getInvociePymtsDtls(invoiceId);
		if (invoiceList.size() != 0) {
			InvoiceDetail invoiceEntiy = new InvoiceDetail();
			invoiceEntiy.setInvoiceNo(invoiceId);
			// calling api for downloading invoice PDF
			byte[] bytes = invoiceReport.generatePDF(invoiceList);
			log.debug("Image" + bytes);
			if (bytes != null) {
				if (bytes.length != 0) {
					invoiceEntiy = invoiceRepo.findOne(invoiceEntiy, invoiceId);
					if (invoiceEntiy != null) {
						invoiceEntiy.setInvImage(bytes);
						invoiceEntiy.setModifiedTs(Utils.getCurrentDate());
						invoiceRepo.merge(invoiceEntiy);
					}
					QRINVCEPYMTDTLS pdfDtls = new QRINVCEPYMTDTLS();
					pdfDtls.setInvoiceFile(bytes);
					res.setQRINVCEPYMTDTLS(pdfDtls);
				}
			}
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"QueryInvoicePaymentRes", "Generate Invoice PDF", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public SubscriberMessagesRes subscriberMessages(SubscriberMessagesReq req) {
		log.info("Inside SubscriberMessagesRes -> subscriberMessages");
		SubscriberMessagesRes res = new SubscriberMessagesRes();
		NetworkSubscription entity = new NetworkSubscription();
		NetworkSubscriptionPK entityPk = new NetworkSubscriptionPK();
		MSGSUBSCRIPTION msgSubscription = req.getMSGSUBSCRIPTION();
		List<MSGINVOICES> msgInvoicesList = req.getMSGSUBSCRIPTION()
				.getMSGINVOICES();
		log.debug("NetworkId::" + msgSubscription.getNetworkId());
		log.debug("SubscriptionId::" + msgSubscription.getSubscriptionId());
		log.debug("SubscriptionType::" + msgSubscription.getSubscriptionType());

		if (!StringUtils.isNotEmpty(msgSubscription.getNetworkId())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"SubscriberMessagesRes", "Subscriber Messages", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}

		if (!StringUtils.isNotEmpty(msgSubscription.getSubscriptionType())) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"SubscriberMessagesRes", "Subscriber Messages", "",
					ERROR_CODE.SS_SUBPR_ER3);
			res.setHeader(header);
			return res;
		}
		if (msgSubscription.getSubscriptionId() == null) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"SubscriberMessagesRes", "Subscriber Messages", "",
					ERROR_CODE.RSLR_EMPTY_ER);
			res.setHeader(header);
			return res;
		}
		entityPk.setNetworkId(msgSubscription.getNetworkId());
		entityPk.setSubscriptionId(msgSubscription.getSubscriptionId());
		entity.setId(entityPk);
		entity.setSubscriptionType(msgSubscription.getSubscriptionType());
		entity = nwkSubscriptionRepo.findOne(entity, entityPk);
		if (entity != null) {
			entity.setMessage(msgSubscription.getMessage());
			entity.setModifiedBy(req.getHeader().getUserId());
			entity.setModifiedTs(Utils.getCurrentDate());
			nwkSubscriptionRepo.merge(entity);

			if (msgInvoicesList.size() != 0) {
				if (msgSubscription.getEmail().equalsIgnoreCase("Y")) {

					if (msgSubscription.getSendTo().equalsIgnoreCase("O")) {
						SubscriberMaster subscriberMaster = subscriberRepo
								.findOne(new SubscriberMaster(),
										msgSubscription.getSubscriptionId());
						List<String> sendTo = new ArrayList<String>();
						sendTo.add(subscriberMaster.getEmail());
						log.debug("From:" + msgSubscription.getEmail());
						log.debug("SendTo:" + sendTo);
						MailResource mailResource = new MailResource();
						if (msgSubscription.getAttachment().equalsIgnoreCase(
								"Y")) {
							List<File> files = new ArrayList<File>();
							byte[] invoiceFile = null;
							for (MSGINVOICES invoices : msgInvoicesList) {
								invoiceFile = this
										.generateInvoicePDF(
												invoices.getInvoiceId())
										.getQRINVCEPYMTDTLS().getInvoiceFile();
								files.add(Utils.convertByteToFile(invoiceFile));
							}
							mailResource.sendEmail(msgSubscription.getEmail(),
									sendTo, null, msgSubscription.getMessage(),
									files);
						} else {
							mailResource.sendEmail(msgSubscription.getEmail(),
									sendTo, null, msgSubscription.getMessage(),
									null);

						}

					} else if (msgSubscription.getSendTo()
							.equalsIgnoreCase("A")) {
						List<UserGroup> usrGrpList = userGrpRepo
								.queryUsrsByNwkId(msgSubscription
										.getNetworkId());
						List<String> emailList = new ArrayList<String>();
						for (UserGroup usrGrp : usrGrpList) {
							SystemUser sysUser = new SystemUser();
							sysUser.setUserId(usrGrp.getId().getGroupId());
							SystemUser systemUser = sysUserRepo.findOne(
									sysUser, usrGrp.getId().getGroupId());
							PeoplePK peoplePk = new PeoplePK();
							peoplePk.setPeopleId(systemUser.getEmail());
							peoplePk.setDefaultNetworkId(sysUserRepo.findOne(
									sysUser, usrGrp.getId().getGroupId())
									.getDefaultNetworkId());
							People people = new People();
							people.setId(peoplePk);
							emailList
									.add((peopleRepo.findOne(people, peoplePk))
											.getEmail());
						}

						log.debug("From:" + msgSubscription.getEmail());
						log.debug("Send To:" + emailList);
						if (msgSubscription.getAttachment().equalsIgnoreCase(
								"Y")) {
							MailResource mailResource = new MailResource();
							List<File> files = new ArrayList<File>();
							byte[] invoiceFile = null;
							for (MSGINVOICES invoices : msgInvoicesList) {
								invoiceFile = this
										.generateInvoicePDF(
												invoices.getInvoiceId())
										.getQRINVCEPYMTDTLS().getInvoiceFile();
								files.add(Utils.convertByteToFile(invoiceFile));
							}
							mailResource.sendEmail(msgSubscription.getEmail(),
									emailList, null,
									msgSubscription.getMessage(), files);
						} else {
							MailResource mailResource = new MailResource();
							mailResource.sendEmail(msgSubscription.getEmail(),
									emailList, null,
									msgSubscription.getMessage(), null);
						}

					}

				}
			}
			ERROR = ERROR_CODE.ES_SV_001;

		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"SubscriberMessagesRes", "Subscriber Messages", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeleteResellerSubInvoiceRes deleteSubscriptionInvoices(
			String invoiceId) {
		log.info("Inside ResellerSubscriptionService :: deleteSubscriptionInvoices");
		log.debug("Invoice id::" + invoiceId);
		DeleteResellerSubInvoiceRes res = new DeleteResellerSubInvoiceRes();

		if (StringUtils.isEmpty(invoiceId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					"ResellerSubscriptionService",
					"deleteSubscriptionInvoices", "", ERROR_CODE.INVOICE_NO_ER);
			res.setHeader(header);
			return res;
		}
		InvoiceDetail invoiceDtl = invoiceRepo.queryInvoiceDetails(invoiceId);
		if (invoiceDtl != null) {
			invoiceDtl.setPaid_Status("D");
			invoiceRepo.merge(invoiceDtl);
			ERROR = ERROR_CODE.SS_SUBIV_DL;

		} else {
			ERROR = ERROR_CODE.INVOICE_NO_ER_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SS,
				"ResellerSubscriptionService", "deleteSubscriptionInvoices",
				"", ERROR);
		res.setHeader(header);
		return res;
	}
}
