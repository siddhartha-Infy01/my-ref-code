package com.iexceed.esoko.pushalert.service;

import static org.quartz.TriggerKey.triggerKey;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.LanguageRepo;
import com.iexceed.esoko.domain.dao.Message_delivary_DetailRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.System_accountRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao3.AlertCommodityRepo;
import com.iexceed.esoko.domain.dao3.AlertLocationRepo;
import com.iexceed.esoko.domain.dao3.AlertPriceTypeRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.AlertSourceNetworkRepo;
import com.iexceed.esoko.domain.dao3.CropTipDetailRepo;
import com.iexceed.esoko.domain.dao3.CropTipMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertRecipientRepo;
import com.iexceed.esoko.domain.dao3.PushTemplateRepo;
import com.iexceed.esoko.domain.dao3.SenderDetailRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.domain.entity.Language;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity3.AlertCommodity;
import com.iexceed.esoko.domain.entity3.AlertCommodityPK;
import com.iexceed.esoko.domain.entity3.AlertLocation;
import com.iexceed.esoko.domain.entity3.AlertLocationPK;
import com.iexceed.esoko.domain.entity3.AlertPriceType;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.AlertSourceNetwork;
import com.iexceed.esoko.domain.entity3.AlertSourceNetworkPK;
import com.iexceed.esoko.domain.entity3.CropTipDetail;
import com.iexceed.esoko.domain.entity3.CropTipDetailPK;
import com.iexceed.esoko.domain.entity3.CropTipMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMasterPK;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.domain.entity3.PushAlertRecipientPK;
import com.iexceed.esoko.domain.entity3.PushTemplate;
import com.iexceed.esoko.domain.entity3.PushTemplatePK;
import com.iexceed.esoko.domain.entity3.SenderDetail;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.people.PEOPLEDTL;
import com.iexceed.esoko.jaxb.people.ViewGroupDetailRes;
import com.iexceed.esoko.jaxb.pushalert.ACTIONDTL;
import com.iexceed.esoko.jaxb.pushalert.ALERTDTLS;
import com.iexceed.esoko.jaxb.pushalert.ALRTDLVRYRPT;
import com.iexceed.esoko.jaxb.pushalert.ActionReq;
import com.iexceed.esoko.jaxb.pushalert.ActionRes;
import com.iexceed.esoko.jaxb.pushalert.CROPTIP;
import com.iexceed.esoko.jaxb.pushalert.CRPSHTMPLT;
import com.iexceed.esoko.jaxb.pushalert.CheckAlertNameRes;
import com.iexceed.esoko.jaxb.pushalert.CreateCropTipReq;
import com.iexceed.esoko.jaxb.pushalert.CreateCropTipRes;
import com.iexceed.esoko.jaxb.pushalert.CreatePushAlertReq;
import com.iexceed.esoko.jaxb.pushalert.CreatePushAlertRes;
import com.iexceed.esoko.jaxb.pushalert.CreatePushTemplate;
import com.iexceed.esoko.jaxb.pushalert.CreatePushTemplateRes;
import com.iexceed.esoko.jaxb.pushalert.CropActionReq;
import com.iexceed.esoko.jaxb.pushalert.CropActionRes;
import com.iexceed.esoko.jaxb.pushalert.DLPSHTEMPS;
import com.iexceed.esoko.jaxb.pushalert.DeletePushTemplateRequest;
import com.iexceed.esoko.jaxb.pushalert.DeletePushTemplateRequestRes;
import com.iexceed.esoko.jaxb.pushalert.GROUPDTLS;
import com.iexceed.esoko.jaxb.pushalert.Header;
import com.iexceed.esoko.jaxb.pushalert.PEOPLEDTLS;
import com.iexceed.esoko.jaxb.pushalert.PRICETYPEDTL;
import com.iexceed.esoko.jaxb.pushalert.PSHALRTDTLSID;
import com.iexceed.esoko.jaxb.pushalert.PSHDLVRYRPT;
import com.iexceed.esoko.jaxb.pushalert.PUSHALERTDTLS;
import com.iexceed.esoko.jaxb.pushalert.PUSHALERTID;
import com.iexceed.esoko.jaxb.pushalert.QRALERTS;
import com.iexceed.esoko.jaxb.pushalert.QRPUSH;
import com.iexceed.esoko.jaxb.pushalert.QRPUSHTEMPLATES;
import com.iexceed.esoko.jaxb.pushalert.QRTIPS;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertDeliveryRes;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertRes;
import com.iexceed.esoko.jaxb.pushalert.QueryAlertsRes;
import com.iexceed.esoko.jaxb.pushalert.QueryCropAlertRes;
import com.iexceed.esoko.jaxb.pushalert.QueryCropTipsRes;
import com.iexceed.esoko.jaxb.pushalert.QueryPeopleByPhoneRes;
import com.iexceed.esoko.jaxb.pushalert.QueryPushAlertDlvrybyIdRes;
import com.iexceed.esoko.jaxb.pushalert.QueryPushDeliveryRes;
import com.iexceed.esoko.jaxb.pushalert.QueryPushRes;
import com.iexceed.esoko.jaxb.pushalert.QueryPushTemplatesRes;
import com.iexceed.esoko.jaxb.pushalert.QueryRecipientsRes;
import com.iexceed.esoko.jaxb.pushalert.RECIPIENTS;
import com.iexceed.esoko.jaxb.pushalert.TIPDETAILS;
import com.iexceed.esoko.jaxb.pushalert.USERDTLS;
import com.iexceed.esoko.people.service.GroupsService;
import com.iexceed.esoko.sch.comp.CostBridge;
import com.iexceed.esoko.sch.job.PushAlertJob;
import com.iexceed.esoko.sch.trg.TriggerDetails;
import com.iexceed.esoko.sch.trg.TriggerHandler;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class PushAlertService {

	private final static String ALERTS = "Alerts";
	private final static String SERIES = "Series";
	private final static String ALL = "All";
	private final String serviceName = "PushAlertService";
	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private PushAlertMasterRepo masterRepo;
	@Autowired
	private AlertCommodityRepo commodityRepo;
	@Autowired
	private AlertLocationRepo locationRepo;
	@Autowired
	private AlertPriceTypeRepo priceTypeRepo;
	@Autowired
	private AlertProfileBasedRepo profileBasedRepo;
	@Autowired
	private AlertSourceNetworkRepo sourceNwkRepo;
	@Autowired
	private PushAlertRecipientRepo recipientRepo;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	private GroupMasterRepo groupRepo;
	@Autowired
	private GroupsService groupService;
	@Autowired
	private PeopleRepo pplRepo;
	@Autowired
	private PushTemplateRepo pshRepo;
	@Autowired
	private GroupMasterRepo grpRepo;
	@Autowired
	private Message_delivary_DetailRepo msgRepo;
	@Autowired
	private SystemUserRepo sysRepo;
	@Autowired
	private LanguageRepo langRepo;
	@Autowired
	private CropTipMasterRepo tipMasterRepo;
	@Autowired
	private CropTipDetailRepo tipDetailRepo;
	@Autowired
	private SenderDetailRepo senderRepo;
	@Autowired
	private CostBridge costBridge;
	private Header header = null;
	private Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;

	@Autowired
	private System_accountRepo accRepo;

	@Transactional
	public CreatePushAlertRes createPushAlert(CreatePushAlertReq req) {
		log.info("Inside PushAlertRest -> createPushAlert");
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		String messageType = details.getMessageType();
		log.info("Alert type:" + messageType);
		Enum<ERROR_CODE> errorCode = null;
		String alertId = null;
		try {
			if (StringUtils.isNotEmpty(details.getRequestType())) {
				if (details.getRequestType().equalsIgnoreCase("save")) {
					int alertExists = masterRepo.getAlertCount(
							details.getAlertName(), details.getMessageType(),
							details.getNetworkId());
					if (alertExists != 0) {
						log.info("Alert name already exists");
						errorCode = ERROR_CODE.PUSH_ALERT_ER_003;
						return this.createResponse(req, errorCode);
					} else {
						alertId = Long.toString(esokoRepo.getSequenceNumber());
					}

				} else if (details.getRequestType().equalsIgnoreCase("edit")) {
					if (StringUtils.isNotEmpty(details.getAlertId())) {
						alertId = details.getAlertId();
						PushAlertMasterPK pk = new PushAlertMasterPK();
						pk.setPeopleId("");
						pk.setPushAlertId(alertId);
						PushAlertMaster tempRec = masterRepo.findOne(
								PushAlertMaster.class, pk);
						int nameExists = masterRepo.getAlertCount(
								details.getAlertName(), messageType,
								details.getNetworkId());

						if (tempRec == null) {
							errorCode = ERROR_CODE.ES_NR_001;
							log.debug("No record found for AlertId");
							return this.createResponse(req, errorCode);
						} else if (!(details.getAlertName().equals(tempRec
								.getName())) && (nameExists > 0)) {
							errorCode = ERROR_CODE.PUSH_ALERT_ER_003;
							log.debug("Alert name already in use");
							return this.createResponse(req, errorCode);
						}
					} else {
						errorCode = ERROR_CODE.ES_PK_001;
						log.debug("Alert Id cannot be empty or null");
						return this.createResponse(req, errorCode);
					}
				}
				// Creating push_alert_master entity
				PushAlertMaster masterEntity = new PushAlertMaster();
				masterEntity.setMessageType(details.getMessageType());
				PushAlertMasterPK pk = new PushAlertMasterPK();
				pk.setPeopleId("");
				pk.setPushAlertId(alertId);
				masterEntity.setId(pk);
				if (details.getRequestType().equalsIgnoreCase("save")) {
					masterEntity.setAlertState("A");
					masterEntity.setDataStatus("A");
				} else if (details.getRequestType().equalsIgnoreCase("edit")) {
					PushAlertMaster tempRec = masterRepo.findOne(
							PushAlertMaster.class, pk);
					masterEntity.setAlertState(tempRec.getAlertState());
					masterEntity.setDataStatus(tempRec.getDataStatus());
				}

				masterEntity.setPayeeAccountNo(details.getPayeeAccNo());
				masterEntity.setPayeeType(details.getPayeeType());
				masterEntity.setPayeeNetworkId(details.getNetworkId());
				TriggerDetails triggerDtls = new TriggerDetails();
				if (!details.getMessageType().equalsIgnoreCase("P")) {
					masterEntity.setSenderId(getSenderId(
							details.getNetworkId(), ALERTS));
					masterEntity.setIsPublic(details.getIsAlertPublic());
					if (details.getIsAlertPublic().equalsIgnoreCase("Y")) {
						masterEntity.setAlertCode(details.getAlertCode());
					}
					masterEntity.setStartDate(Utils.getFormatedDate(details
							.getStartDate()));
					triggerDtls.setStartDate(Utils.getFormatedDate(details
							.getStartDate()));
					if ((details.getNeverEnds() == null)
							|| (details.getNeverEnds().equalsIgnoreCase("N"))) {
						masterEntity.setEndDate(Utils.getFormatedDate(details
								.getEndDate()));

						triggerDtls.setEndDate(Utils.getFormatedDate(details
								.getEndDate()));
					}

				}
				// Saving data in push_alert_recipients
				if (details.getRequestType().equalsIgnoreCase("edit")) {
					this.deleteAllAlertRecipients(alertId);
				}
				List<RECIPIENTS> recipientList = details.getRECIPIENTS();
				if (recipientList.size() != 0) {
					for (RECIPIENTS recp : recipientList) {
						String parentId = null;
						if (recp.getParentId() == null) {
							parentId = "";
						} else {
							parentId = recp.getParentId();
						}
						saveAlertRecipients(alertId, recp.getRecipientId(),
								recp.getRecipientType(), parentId);
					}
				}
				if (details.getMessageType().equalsIgnoreCase("PA")) {
					this.updatePriceAlert(req, masterEntity, alertId,
							triggerDtls);
					if (details.getRequestType().equalsIgnoreCase("save")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_001;
					} else if (details.getRequestType()
							.equalsIgnoreCase("edit")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_007;
					}

				} else if (details.getMessageType().equalsIgnoreCase("BO")) {
					this.updateBidsOfferAlert(req, masterEntity, alertId,
							triggerDtls);
					if (details.getRequestType().equalsIgnoreCase("save")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_001;
					} else if (details.getRequestType()
							.equalsIgnoreCase("edit")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_007;
					}

				} else if (details.getMessageType().equalsIgnoreCase("W")) {
					this.updateWeatherAlert(req, masterEntity, alertId,
							triggerDtls);
					if (details.getRequestType().equalsIgnoreCase("save")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_001;
					} else if (details.getRequestType()
							.equalsIgnoreCase("edit")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_007;
					}

				} else if (details.getMessageType().equalsIgnoreCase("P")) {
					this.updatePushAlert(req, masterEntity, alertId);
					if (details.getSchedule().equalsIgnoreCase("Y")) {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_003;
					} else {
						errorCode = ERROR_CODE.PUSH_ALERT_SC_002;
					}
				}
			} else {
				errorCode = ERROR_CODE.ES_PK_001;
				log.debug("requestType node not found");
			}
		} catch (DomainException e) {
			log.error(Utils.getStackTrace(e));
			if (messageType.equalsIgnoreCase("P")) {
				errorCode = ERROR_CODE.PUSH_ALERT_ER_002;
			} else {
				errorCode = ERROR_CODE.PUSH_ALERT_ER_001;
			}

		} catch (Exception e) {
			log.error(Utils.getStackTrace(e));
			errorCode = ERROR_CODE.ES_SV_002;
		}
		return this.createResponse(req, errorCode);
	}

	@Transactional
	public ActionRes action(ActionReq req) {
		log.info("Inside PushAlertService -> action");
		ActionRes res = new ActionRes();
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		try {
			ACTIONDTL detail = req.getACTIONDTL();
			String actionType = detail.getAction();
			List<PUSHALERTID> idList = detail.getPUSHALERTID();
			TriggerHandler handler = TriggerHandler.getInstance();

			for (PUSHALERTID idDetail : idList) {
				PushAlertMasterPK pk = new PushAlertMasterPK();
				pk.setPeopleId("");
				pk.setPushAlertId(idDetail.getAlertId());
				PushAlertMaster entity = masterRepo.findOne(
						PushAlertMaster.class, pk);
				if (entity == null) {
					errorCode = ERROR_CODE.ES_NR_001;
					throw new DomainException();
				} else {
					TriggerDetails triggerDetails = new TriggerDetails();
					triggerDetails.setTriggerId(idDetail.getAlertId());

					if (actionType.equalsIgnoreCase("D")) {
						masterRepo.delete(entity);
						this.deleteAllAlertRecipients(idDetail.getAlertId());
						this.deleteAllAlertCommodities(idDetail.getAlertId());
						this.deleteAllAlertMarkets(idDetail.getAlertId());
						this.deleteAllPriceTypes(idDetail.getAlertId());
						this.deleteAllSourceNwks(idDetail.getAlertId());
						this.deleteAllAlertProfiles(idDetail.getAlertId());
						handler.deleteTrigger(triggerDetails);

					} else {
						masterRepo
								.updatePushAlertStatus(idDetail.getAlertId(),
										req.getHeader().getUserId(),
										detail.getAction());
						if (detail.getAction().equalsIgnoreCase("A")) {
							handler.reactivateTrigger(triggerDetails);
						} else if (detail.getAction().equalsIgnoreCase("S")) {
							handler.deactivateTrigger(triggerDetails);
						}
					}
				}
			}
			if (actionType.equalsIgnoreCase("A")) {
				errorCode = ERROR_CODE.PUSH_ALERT_SC_004;
			} else if (actionType.equalsIgnoreCase("S")) {
				errorCode = ERROR_CODE.PUSH_ALERT_SC_005;
			} else if (actionType.equalsIgnoreCase("D")) {
				errorCode = ERROR_CODE.PUSH_ALERT_SC_006;
			}

		} catch (DomainException e) {
			if (errorCode == null) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			} else {
				log.debug("No record found");
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "Action Push/Alert", req.getHeader()
						.getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryAlertRes queryAlert(String alertId) {
		log.info("Inside PushAlertService -> queryAlert");
		log.debug("AlertId: " + alertId);
		QueryAlertRes res = new QueryAlertRes();
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		PushAlertMasterPK pk = new PushAlertMasterPK();
		pk.setPeopleId("");
		pk.setPushAlertId(alertId);
		PushAlertMaster entity = masterRepo.findOne(PushAlertMaster.class, pk);
		if (entity == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		} else {
			errorCode = ERROR_CODE.ES_SC_001;
			ALERTDTLS details = new ALERTDTLS();
			details.setAlertName(entity.getName());
			String messageType = entity.getMessageType();
			if (messageType.equalsIgnoreCase("PA")) {
				details.setCountry(entity.getCountry());
				details.setAlertType(entity.getPriceRecepient());
				if (entity.getPriceRecepient().equalsIgnoreCase("A")) {
					details.setCommodity(alertCommodityList(alertId));
				}
				details.setDataSource(alertSourceNwkList(alertId));
				details.setMarkets(alertLocationList(alertId));
				List<AlertPriceType> tempList = priceTypeRepo
						.findAllAlertPriceTypes(alertId);
				if (tempList.size() != 0) {
					List<PRICETYPEDTL> priceDtl = new ArrayList<PRICETYPEDTL>();
					for (AlertPriceType priceType : tempList) {
						PRICETYPEDTL priceTypeDtl = new PRICETYPEDTL();
						priceTypeDtl.setMeasureId(priceType.getMeasureId());
						priceTypeDtl.setPriceType(priceType.getPriceType());
						priceDtl.add(priceTypeDtl);
					}
					details.getPRICETYPEDTL().addAll(priceDtl);
				}
				details.setCurrency(entity.getCurrencyId());
				details.setDeliverOn(entity.getScheduleDays());
				details.setTime(convertTimeToString(entity.getScheduleTime()));
			} else if (messageType.equalsIgnoreCase("W")) {
				details.setAlertType(entity.getWheatherRecepient());
				if (entity.getWheatherRecepient().equalsIgnoreCase("A")) {
					details.setCountry(entity.getCountry());
					details.setState(entity.getCountryState());
					details.setTown(entity.getTown());
				}
				Language language = langRepo.findOne(Language.class,
						entity.getLanguage());
				if (language != null) {
					details.setLanguage(language.getName());
				}
				details.setDeliverOn(entity.getScheduleDays());
				details.setTime(convertTimeToString(entity.getScheduleTime()));
			} else if (messageType.equalsIgnoreCase("BO")) {
				String bidsOffers = entity.getBidOffer();
				if (bidsOffers.equalsIgnoreCase("B")) {
					details.setBidsBids("Y");
					details.setBidsOffers("N");
				} else if (bidsOffers.equalsIgnoreCase("O")) {
					details.setBidsOffers("Y");
					details.setBidsBids("N");
				} else if (bidsOffers.equalsIgnoreCase("BO")) {
					details.setBidsBids("Y");
					details.setBidsOffers("Y");
				}
				details.setDataSource(alertSourceNwkList(alertId));
				details.setCommodity(alertCommodityList(alertId));
				details.setMarkets(alertLocationList(alertId));
				details.setCurrency(entity.getCurrencyId());
			}

			List<PushAlertRecipient> tempList = recipientRepo
					.findAllPushAlertRecipients(alertId);
			if (tempList.size() != 0) {
				List<RECIPIENTS> recpList = new ArrayList<RECIPIENTS>();
				for (PushAlertRecipient recp : tempList) {
					RECIPIENTS recipient = new RECIPIENTS();
					PushAlertRecipientPK recpPk = recp.getId();
					recipient.setRecipientId(recpPk.getRecipientId());
					recipient.setRecipientType(recp.getRecipientType());
					recipient.setParentId(recpPk.getParentId());
					recpList.add(recipient);
				}
				details.getRECIPIENTS().addAll(recpList);
			}

			details.setPayeeAccNo(entity.getPayeeAccountNo());
			details.setPayeeAccName("");
			details.setPayeeAccBal("");
			details.setPayeeType(entity.getPayeeType());
			details.setIsAlertPublic(entity.getIsPublic());
			if ((entity.getIsPublic() != null)
					&& (entity.getIsPublic().equalsIgnoreCase("Y"))) {
				details.setAlertCode(entity.getAlertCode());
			}
			details.setStartDate(convertDateToString(entity.getStartDate()));
			if ((entity.getNeverEndFlag() == null)
					|| (entity.getNeverEndFlag().equalsIgnoreCase("N"))) {
				details.setEndDate(convertDateToString(entity.getEndDate()));
			}
			details.setNeverEnds(entity.getNeverEndFlag());

			res.setALERTDTLS(details);
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "Query Alert", "", errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryRecipientsRes queryRecipients(String networkId,
			String calculateCost) {
		QueryRecipientsRes res = new QueryRecipientsRes();
		Enum<ERROR_CODE> errorCode = null;
		Header header = null;
		String newGroupId = null;
		String prevGroupId = null;
		GROUPDTLS groupdtls = null;
		List<GROUPDTLS> grpList = new ArrayList<GROUPDTLS>();
		if (StringUtils.isNotEmpty(networkId)) {
			List<Map<String, Object>> grpDtls = grpRepo.queryAllGroupDetails(
					networkId, calculateCost);

			if (grpDtls.size() == 0) {
				log.info("No record found");
				errorCode = ERROR_CODE.ES_NR_001;
			} else {

				for (Map<String, Object> map : grpDtls) {

					if (map.get("groupId") != null) {
						newGroupId = map.get("groupId").toString();
					}

					if (!newGroupId.equals(prevGroupId)) {
						groupdtls = new GROUPDTLS();

						groupdtls.setGroupId(map.get("groupId").toString());
						if (map.get("group_name") != null) {
							groupdtls.setGroupName(map.get("group_name")
									.toString());
						}
						if (map.get("type") != null) {
							groupdtls.setType("G");
						}
						if (map.get("count") != null) {
							groupdtls.setCount(map.get("count").toString());
						}
					}

					USERDTLS usrdtls = new USERDTLS();
					if (map.get("people_id") != null) {
						usrdtls.setUserId(map.get("people_id").toString());
						usrdtls.setType("U");
						usrdtls.setCost(Float.valueOf(map.get("cost")
								.toString()));
					}

					if (map.get("last_name") != null) {
						usrdtls.setUserName(map.get("first_name").toString()
								+ " " + map.get("last_name").toString());
					}

					else if (map.get("first_name") != null) {
						usrdtls.setUserName(map.get("first_name").toString());
					}

					if (map.get("country") != null) {
						usrdtls.setCountry(map.get("country").toString());
					}
					if (map.get("msisdn") != null) {
						usrdtls.setMobile(map.get("msisdn").toString());
					} else {
						usrdtls.setMobile("No mobile phone number found. This person 'CANNOT' receive any text messages");
					}

					if (map.get("people_id") != null) {
						groupdtls.getUSERDTLS().add(usrdtls);
					}

					if (!newGroupId.equals(prevGroupId)) {
						grpList.add(groupdtls);
					}

					prevGroupId = newGroupId;

				}
				/*
				 * List<Map<String, Object>> ungrpdList = pplRepo
				 * .queryUngrouped(networkId); GROUPDTLS grpDtl = new
				 * GROUPDTLS(); String groupId = "Ungrouped";
				 * grpDtl.setGroupId(groupId); grpDtl.setGroupName("Ungrouped");
				 * grpDtl.setCount("" + ungrpdList.size()); grpDtl.setType("G");
				 * if (ungrpdList.size() != 0) { List<USERDTLS> userList = new
				 * ArrayList<USERDTLS>(); createUserDetail(userList,
				 * ungrpdList); grpDtl.getUSERDTLS().addAll(userList); }
				 * grpList.add(grpDtl);
				 */

				res.getGROUPDTLS().addAll(grpList);
				errorCode = ERROR_CODE.ES_SC_001;
			}

		}

		else {
			log.info("NetworkId is empty");
			errorCode = ERROR_CODE.ES_PK_002;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "queryRecipients", "", errorCode);
		res.setHeader(header);

		return res;
	}

	public CheckAlertNameRes checkAlertName(String requestType, String alertId,
			String alertName, String alertType, String networkId) {
		CheckAlertNameRes res = new CheckAlertNameRes();
		int nameExists = masterRepo.getAlertCount(alertName, alertType,
				networkId);
		if (requestType.equalsIgnoreCase("save")) {
			if (nameExists == 0) {
				ERROR = ERROR_CODE.ES_SC_001;
				log.debug("Alert name can be used");
			} else {
				ERROR = ERROR_CODE.PUSH_ALERT_ER_003;
				log.debug("Alert name already in use");
			}
		} else if (requestType.equalsIgnoreCase("edit")) {
			PushAlertMasterPK pk = new PushAlertMasterPK();
			pk.setPeopleId("");
			pk.setPushAlertId(alertId);
			PushAlertMaster tempRec = masterRepo.findOne(PushAlertMaster.class,
					pk);
			if (tempRec == null) {
				ERROR = ERROR_CODE.ES_NR_001;
				log.debug("No record found for AlertId");
			} else if (!(alertName.equals(tempRec.getName()))
					&& (nameExists > 0)) {
				ERROR = ERROR_CODE.PUSH_ALERT_ER_003;
				log.debug("Alert name already in use");
			} else {
				ERROR = ERROR_CODE.ES_SC_001;
				log.debug("Alert name can be used");
			}
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "checkAlertName", "", ERROR);
		res.setHeader(header);
		return res;
	}

	/*
	 * private void createUserDetail(List<USERDTLS> userList, List<Map<String,
	 * Object>> pplLst) { for (Map<String, Object> usrMap : pplLst) { USERDTLS
	 * usrDtl = new USERDTLS(); if (usrMap.get("country") != null) {
	 * usrDtl.setCountry(usrMap.get("country").toString()); } if
	 * (usrMap.get("msisdn") == null) { usrDtl.setMobile(
	 * "No mobile phone number found. This person 'CANNOT' receive any text messages"
	 * ); } else { usrDtl.setMobile(usrMap.get("msisdn").toString()); }
	 * usrDtl.setType("U"); if (usrMap.get("peopleId") != null) {
	 * usrDtl.setUserId(usrMap.get("peopleId").toString());
	 * usrDtl.setCost(0.0f); }
	 * 
	 * StringBuilder name = new StringBuilder();
	 * 
	 * if (usrMap.get("firstName") != null) {
	 * name.append(usrMap.get("firstName").toString()); } if
	 * (usrMap.get("lastName") != null) { name.append(" " +
	 * usrMap.get("lastName").toString()); }
	 * usrDtl.setUserName(name.toString()); userList.add(usrDtl); } }
	 */

	private String alertCommodityList(String alertId) {
		String result = null;
		List<AlertCommodity> tempList1 = commodityRepo
				.findAllAlertCommodities(alertId);
		if (tempList1.size() != 0) {
			StringBuilder commList = new StringBuilder();
			int i = 0;
			for (AlertCommodity comm : tempList1) {
				if (i == 0) {
					AlertCommodityPK comPK = comm.getId();
					commList.append(comPK.getCommodityId());
				} else {
					AlertCommodityPK comPK = comm.getId();
					commList.append("," + comPK.getCommodityId());
				}
				i++;
			}
			result = commList.toString();
		}
		return result;
	}

	private String alertLocationList(String alertId) {
		String result = null;
		List<AlertLocation> tempList3 = locationRepo
				.findAllAlertLocations(alertId);
		if (tempList3.size() != 0) {
			StringBuilder locList = new StringBuilder();
			int i = 0;
			for (AlertLocation loc : tempList3) {
				if (i == 0) {
					AlertLocationPK locPK = loc.getId();
					locList.append(locPK.getLocationId());
				} else {
					AlertLocationPK locPK = loc.getId();
					locList.append("," + locPK.getLocationId());
				}
				i++;
			}
			result = locList.toString();
		}
		return result;
	}

	private String alertSourceNwkList(String alertId) {
		String result = null;
		List<AlertSourceNetwork> tempList2 = sourceNwkRepo
				.findAllAlertNetworks(alertId);
		if (tempList2.size() != 0) {
			StringBuilder srcList = new StringBuilder();
			int i = 0;
			for (AlertSourceNetwork src : tempList2) {
				if (i == 0) {
					AlertSourceNetworkPK srcPK = src.getId();
					srcList.append(srcPK.getNetworkId());
				} else {
					AlertSourceNetworkPK srcPK = src.getId();
					srcList.append("," + srcPK.getNetworkId());
				}
				i++;
			}
			result = srcList.toString();
		}
		return result;
	}

	private String convertDateToString(Date dt) {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		String date = df.format(dt);
		return date;
	}

	private String convertDateToString1(Date dt) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = df.format(dt);
		return date;
	}

	private String convertTimeToString(Time time) {
		Date dt = new Date(time.getTime());
		DateFormat df = new SimpleDateFormat("h:mm a");
		String date = df.format(dt);
		return date;
	}

	private CreatePushAlertRes createResponse(CreatePushAlertReq req,
			Enum<ERROR_CODE> errorCode) {
		CreatePushAlertRes res = new CreatePushAlertRes();
		Header header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "Creating Push/Alert", req.getHeader()
						.getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}

	private void updatePriceAlert(CreatePushAlertReq req,
			PushAlertMaster entity, String alertId, TriggerDetails triggerDtls)
			throws DomainException, SchedulerException, ParseException {
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		// Saving data in push_alert_master
		entity.setName(details.getAlertName());
		entity.setCountry(details.getCountry());
		entity.setTown("NA");
		entity.setCountryState("NA");
		entity.setPriceRecepient(details.getAlertType());
		entity.setWheatherRecepient("NA");
		entity.setBidOffer("NA");
		entity.setLanguage("NA");
		entity.setNoOfMessages(0);
		entity.setText("No data available");
		entity.setCurrencyId(details.getCurrency());
		entity.setSchedule("Y");
		entity.setFreqFlag("R");
		entity.setRepeatsFlag("W");
		entity.setScheduleDays(details.getDeliverOn());
		entity.setNeverEndFlag(details.getNeverEnds());
		Date dt = Utils.getFormatedDateTimeStamp(details.getStartDate() + " "
				+ details.getTime());
		Time startTime = new Time(dt.getTime());
		entity.setScheduleTime(startTime);
		entity.setAirtimeFlag("NA");
		this.updatePushAlertMaster(entity, req, alertId);

		if (details.getRequestType().equalsIgnoreCase("edit")) {
			this.deleteAllAlertCommodities(alertId);
			this.deleteAllAlertMarkets(alertId);
			this.deleteAllPriceTypes(alertId);
			this.deleteAllSourceNwks(alertId);
			this.deleteAllAlertProfiles(alertId);
		}
		// Saving data in alert_commodities
		if (details.getAlertType().equalsIgnoreCase("P")) {
			this.saveAlertCommodity(alertId, "Recipient");

			// Saving data in alert_profile_based
			this.saveAlertProfiles(details, alertId);
		} else {
			String[] commodityArray = details.getCommodity().split(",");
			for (String commodityId : commodityArray) {
				saveAlertCommodity(alertId, commodityId);
			}
			// Saving data in alert_locations
			saveAlertMarkets(alertId, details.getMarkets().split(","));
		}

		// Saving data in alert_price_type
		List<PRICETYPEDTL> priceList = details.getPRICETYPEDTL();
		if (priceList.size() != 0) {
			for (PRICETYPEDTL priceDtls : priceList) {
				saveAlertPriceType(alertId, priceDtls.getMeasureId(),
						priceDtls.getPriceType());
			}
		}
		// Saving data in alert_source_networks
		saveAlertSourceNwk(alertId, details.getDataSource().split(","));

		// Creating Trigger
		triggerDtls.setTriggerId(alertId);
		triggerDtls.setFrequency("recurring");
		triggerDtls.setRepeats("weekly");
		if (details.getNeverEnds().equalsIgnoreCase("Y")) {
			triggerDtls.setFlag(true);
		} else {
			triggerDtls.setFlag(false);
		}
		triggerDtls.setScheduleDays(details.getDeliverOn());
		LocalTime lclTime = startTime.toLocalTime();
		triggerDtls.setStartTime(lclTime);

		TriggerHandler handler = TriggerHandler.getInstance();

		if (details.getRequestType().equalsIgnoreCase("save")) {
			handler.activateTrigger(PushAlertJob.class, triggerDtls);
		} else if (details.getRequestType().equalsIgnoreCase("edit")) {
			handler.updateTrigger(PushAlertJob.class, triggerDtls);
		}

		if (entity.getAlertState().equalsIgnoreCase("S")) {
			handler.deactivateTrigger(triggerDtls);
		}
	}

	private void updateBidsOfferAlert(CreatePushAlertReq req,
			PushAlertMaster entity, String alertId, TriggerDetails tirgerDtls)
			throws DomainException {
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		// Saving data in push_alert_master
		entity.setName(details.getAlertName());
		entity.setCountry("NA");
		entity.setCountryState("NA");
		entity.setTown("NA");
		entity.setPriceRecepient("NA");
		entity.setWheatherRecepient("NA");
		entity.setBidOffer(details.getBidsOffers());
		entity.setLanguage("NA");
		entity.setNoOfMessages(0);
		entity.setText("No data available");
		entity.setCurrencyId(details.getCurrency());
		entity.setSchedule("Y");
		entity.setFreqFlag("R");
		entity.setRepeatsFlag("W");
		entity.setScheduleDays("NA");
		entity.setNeverEndFlag(details.getNeverEnds());
		entity.setAirtimeFlag("NA");
		this.updatePushAlertMaster(entity, req, alertId);
		if (details.getRequestType().equalsIgnoreCase("edit")) {
			this.deleteAllAlertCommodities(alertId);
			this.deleteAllAlertMarkets(alertId);
			this.deleteAllSourceNwks(alertId);
		}
		// Saving data in alert_commodities
		String[] commodityArray = details.getCommodity().split(",");
		for (String commodityId : commodityArray) {
			saveAlertCommodity(alertId, commodityId);
		}
		// Saving data in alert_locations
		saveAlertMarkets(alertId, details.getMarkets().split(","));

		// Saving data in alert_source_networks
		saveAlertSourceNwk(alertId, details.getDataSource().split(","));

	}

	private void updateWeatherAlert(CreatePushAlertReq req,
			PushAlertMaster entity, String alertId, TriggerDetails triggerDtls)
			throws DomainException, SchedulerException, ParseException {
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		if (details.getRequestType().equalsIgnoreCase("edit")) {
			this.deleteAllAlertProfiles(alertId);
		}

		// Saving data in push_alert_master
		entity.setName(details.getAlertName());
		entity.setPriceRecepient("NA");
		entity.setWheatherRecepient(details.getAlertType());
		if (details.getAlertType().equalsIgnoreCase("A")) {
			entity.setCountry(details.getCountry());
			entity.setCountryState(details.getState());
			entity.setTown(details.getTown());
		} else if (details.getAlertType().equalsIgnoreCase("P")) {
			entity.setCountry("NA");
			entity.setCountryState("NA");
			entity.setTown("Recipient");
			// Saving data in alert_profile_based
			this.saveAlertProfiles(details, alertId);
		}
		entity.setBidOffer("NA");
		entity.setLanguage(details.getLanguage());
		entity.setNoOfMessages(0);
		entity.setText("No data available");
		entity.setCurrencyId("NA");
		entity.setSchedule("Y");
		entity.setFreqFlag("R");
		entity.setRepeatsFlag("W");
		entity.setScheduleDays(details.getDeliverOn());
		entity.setNeverEndFlag(details.getNeverEnds());
		Date dt = Utils.getFormatedDateTimeStamp(details.getStartDate() + " "
				+ details.getTime());
		Time startTime = new Time(dt.getTime());
		entity.setScheduleTime(startTime);
		entity.setAirtimeFlag("NA");
		this.updatePushAlertMaster(entity, req, alertId);

		// Creating Trigger
		triggerDtls.setTriggerId(alertId);
		triggerDtls.setFrequency("recurring");
		triggerDtls.setRepeats("weekly");
		if (details.getNeverEnds().equalsIgnoreCase("Y")) {
			triggerDtls.setFlag(true);
		} else {
			triggerDtls.setFlag(false);
		}
		triggerDtls.setScheduleDays(details.getDeliverOn());
		LocalTime lclTime = startTime.toLocalTime();
		triggerDtls.setStartTime(lclTime);

		TriggerHandler handler = TriggerHandler.getInstance();

		if (details.getRequestType().equalsIgnoreCase("save")) {
			handler.activateTrigger(PushAlertJob.class, triggerDtls);
		} else if (details.getRequestType().equalsIgnoreCase("edit")) {
			handler.updateTrigger(PushAlertJob.class, triggerDtls);
		}
		if (entity.getAlertState().equalsIgnoreCase("S")) {
			handler.deactivateTrigger(triggerDtls);
		}
	}

	private void updatePushAlert(CreatePushAlertReq req,
			PushAlertMaster entity, String alertId) throws DomainException,
			SchedulerException, ParseException {
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		entity.setName("NA");
		entity.setCountry("NA");
		entity.setCountryState("NA");
		entity.setTown("NA");
		entity.setPriceRecepient("NA");
		entity.setWheatherRecepient("NA");
		entity.setBidOffer("NA");
		entity.setLanguage("NA");
		entity.setNoOfMessages(details.getNoOfMsgs().intValue());
		entity.setText(details.getMessage());
		entity.setCurrencyId("NA");
		entity.setIsPublic("NA");
		entity.setSchedule(details.getSchedule());
		entity.setSenderId(details.getSenderId());
		entity.setAirtimeFlag(details.getAirTime());
		entity.setAirtimeAmount(BigDecimal.valueOf(details.getAirTimeAmount()));
		entity.setAirtimeCurrency(details.getAirTimeCurrency());
		entity.setSendCopy(details.getSendCopyToMe());
		entity.setSendEmail(details.getSendAsSmsEmail());
		Time startTime = null;
		if (details.getSchedule().equalsIgnoreCase("Y")) {
			entity.setStartDate(Utils.getFormatedDate(details.getStartDate()));
			entity.setFreqFlag(details.getFrequency());
			Date dt = Utils.getFormatedDateTimeStamp(details.getStartDate()
					+ " " + details.getTime());
			startTime = new Time(dt.getTime());
			entity.setScheduleTime(startTime);
			if (details.getFrequency().equalsIgnoreCase("O")) {
				entity.setRepeatsFlag("NA");
				entity.setScheduleDays("NA");
				entity.setEndDate(Utils.getFormatedDate(details.getStartDate()));
			} else {
				entity.setRepeatsFlag(details.getRepeat());
				entity.setScheduleDays(details.getDeliverOn());
				entity.setEndDate(Utils.getFormatedDate(details.getEndDate()));
			}
		} else {
			entity.setStartDate(new Date());
			entity.setEndDate(new Date());
			entity.setFreqFlag("O");
			entity.setRepeatsFlag("NA");
			entity.setScheduleDays("NA");
			Date dt = new Date();
			startTime = new Time(dt.getTime() + (2 * 60 * 1000));
			entity.setScheduleTime(startTime);

			// / code to be removed ///
			/*
			 * List<RECIPIENTS> recipientList = details.getRECIPIENTS(); if
			 * (recipientList.size() != 0) { for (RECIPIENTS recp :
			 * recipientList) { if (recp.getRecipientType().equals("U")) {
			 * sendSMS(recp.getRecipientId(), details.getNetworkId(),
			 * details.getMessage(), alertId); } else if
			 * (recp.getRecipientType().equals("G")) { boolean isSmartGrp =
			 * pplRepo.isSmartGroup( recp.getRecipientId(),
			 * details.getNetworkId()); List<Map<String, Object>> pplLst = null;
			 * if (isSmartGrp) { pplLst = pplRepo.querySmartGroupsDetail(
			 * recp.getRecipientId(), details.getNetworkId()); } else { pplLst =
			 * pplRepo.queryGroupsDetail( recp.getRecipientId(),
			 * details.getNetworkId()); }
			 * 
			 * for (Map<String, Object> ppl : pplLst) {
			 * sendSMS(ppl.get("peopleId").toString(),
			 * ppl.get("networkId").toString(), details.getMessage(), alertId);
			 * } } } }
			 */
			// / code to be removed ///
		}

		entity.setNeverEndFlag("NA");
		entity.setAirtimeFlag(details.getAirTime());
		this.updatePushAlertMaster(entity, req, alertId);

		// Creating trigger
		TriggerDetails triggerDtls = new TriggerDetails();
		triggerDtls.setTriggerId(alertId);

		triggerDtls.setFlag(false);
		if (details.getSchedule().equalsIgnoreCase("Y")) {
			if (entity.getFreqFlag().equalsIgnoreCase("O")) {
				Date dt = Utils.getFormatedDateTimeStamp(details.getStartDate()
						+ " " + details.getTime());
				triggerDtls.setStartDate(dt);
				triggerDtls.setFrequency("once");
			} else {
				triggerDtls.setStartTime(startTime.toLocalTime());
				triggerDtls.setStartDate(Utils.getFormatedDate(details
						.getStartDate()));
				triggerDtls.setFrequency("recurring");
				triggerDtls.setEndDate(Utils.getFormatedDate(details
						.getEndDate()));
				if (details.getRepeat().equalsIgnoreCase("W")) {
					triggerDtls.setRepeats("weekly");
				} else {
					triggerDtls.setRepeats("monthly");
				}
				triggerDtls.setScheduleDays(details.getDeliverOn());
			}
		} else {
			Date startDate = new Date(entity.getScheduleTime().getTime());
			triggerDtls.setStartDate(startDate);
			triggerDtls.setFrequency("once");
		}

		TriggerHandler handler = TriggerHandler.getInstance();

		if (details.getRequestType().equalsIgnoreCase("save")) {
			handler.activateTrigger(PushAlertJob.class, triggerDtls);
		} else if (details.getRequestType().equalsIgnoreCase("edit")) {
			handler.updateTrigger(PushAlertJob.class, triggerDtls);
		}

		if (entity.getAlertState().equalsIgnoreCase("S")) {
			handler.deactivateTrigger(triggerDtls);
		}
	}

	/*
	 * private void sendSMS(String userId, String nwkId, String message, String
	 * alertId) { log.debug("UserId: " + userId); PeoplePK pplPk = new
	 * PeoplePK(); pplPk.setDefaultNetworkId(nwkId); pplPk.setPeopleId(userId);
	 * People user = pplRepo.findOne(People.class, pplPk); if (user != null) {
	 * log.debug("Message sent to: " + user.getMsisdn()); KannelSMSSender sender
	 * = new KannelSMSSender(); sender.SendMessage(message, user.getMsisdn(),
	 * "Esoko", "infobip_gw_smsc", alertId); } }
	 */

	private void updatePushAlertMaster(PushAlertMaster entity,
			CreatePushAlertReq req, String alertId) throws DomainException {
		PUSHALERTDTLS details = req.getPUSHALERTDTLS();
		if (details.getRequestType().equalsIgnoreCase("save")) {
			entity.setCreatedBy(req.getHeader().getUserId());
			entity.setCreatedTs(new Date());
			masterRepo.save(entity);
		} else {
			PushAlertMasterPK pk = new PushAlertMasterPK();
			pk.setPeopleId("");
			pk.setPushAlertId(alertId);
			PushAlertMaster prevEntity = masterRepo.findOne(
					PushAlertMaster.class, pk);
			if (prevEntity != null) {
				entity.setCreatedBy(prevEntity.getCreatedBy());
				entity.setCreatedTs(prevEntity.getCreatedTs());
			}
			entity.setModifiedBy(req.getHeader().getUserId());
			entity.setModifiedTs(new Date());
			masterRepo.merge(entity);
		}
	}

	private void deleteAllAlertRecipients(String alertId)
			throws DomainException {
		List<PushAlertRecipient> pARecipients = recipientRepo
				.findAllPushAlertRecipients(alertId);
		if (pARecipients.size() != 0) {
			recipientRepo.delete(pARecipients);
		}
	}

	private void saveAlertRecipients(String alertId, String recipientId,
			String recipientType, String parentId) throws DomainException {
		PushAlertRecipient pARecipient = new PushAlertRecipient();
		PushAlertRecipientPK recipientPK = new PushAlertRecipientPK();
		recipientPK.setPushAlertId(alertId);
		recipientPK.setRecipientId(recipientId);
		recipientPK.setParentId(parentId);
		pARecipient.setId(recipientPK);
		pARecipient.setRecipientType(recipientType);
		/*
		 * if (StringUtils.isNotEmpty(parentId)) {
		 * pARecipient.setParentId(parentId); }
		 */
		recipientRepo.save(pARecipient);
	}

	private void deleteAllAlertCommodities(String alertId)
			throws DomainException {
		List<AlertCommodity> alertComms = commodityRepo
				.findAllAlertCommodities(alertId);
		if (alertComms.size() != 0) {
			commodityRepo.delete(alertComms);
		}
	}

	private void saveAlertCommodity(String alertId, String commodityId)
			throws DomainException {
		AlertCommodity commodity = new AlertCommodity();
		AlertCommodityPK commodityPK = new AlertCommodityPK();
		commodityPK.setPushAlertId(alertId);
		commodityPK.setCommodityId(commodityId);
		commodity.setId(commodityPK);
		commodityRepo.save(commodity);
	}

	private void deleteAllAlertProfiles(String alertId) throws DomainException {
		List<AlertProfileBased> profileList = profileBasedRepo
				.findAllAlertProfiles(alertId);
		if (profileList.size() != 0) {
			profileBasedRepo.delete(profileList);
		}
	}

	private void saveAlertProfiles(PUSHALERTDTLS details, String alertId)
			throws DomainException {
		List<RECIPIENTS> recipientList = details.getRECIPIENTS();
		if (recipientList.size() != 0) {
			for (RECIPIENTS recp : recipientList) {
				if (recp.getRecipientType().equalsIgnoreCase("U")) {
					saveProfiles(alertId, recp.getRecipientId(),
							details.getMessage());
				} else {
					ViewGroupDetailRes groupDtls = groupService
							.viewGroupDetail(details.getNetworkId(),
									recp.getRecipientId());
					if (groupDtls.getHeader().getType().equalsIgnoreCase("S")) {
						List<PEOPLEDTL> peopleDtl = groupDtls.getPEOPLEDTL();
						if (peopleDtl.size() != 0) {
							for (PEOPLEDTL people : peopleDtl) {
								saveProfiles(alertId, people.getPeopleId(),
										details.getMessage());
							}
						}
					}
				}
			}
		}
	}

	private void saveProfiles(String alertId, String userId, String text)
			throws DomainException {
		AlertProfileBased profile = new AlertProfileBased();
		profile.setPushAlertId(alertId);
		profile.setUserId(userId);
		profile.setText(text);
		profileBasedRepo.save(profile);
	}

	private void deleteAllAlertMarkets(String alertId) throws DomainException {
		List<AlertLocation> alertLocs = locationRepo
				.findAllAlertLocations(alertId);
		if (alertLocs.size() != 0) {
			locationRepo.delete(alertLocs);
		}

	}

	private void saveAlertMarkets(String alertId, String[] locations)
			throws DomainException {
		for (String locationId : locations) {
			AlertLocation location = new AlertLocation();
			AlertLocationPK locationPK = new AlertLocationPK();
			locationPK.setPushAlertId(alertId);
			locationPK.setLocationId(locationId);
			location.setId(locationPK);
			locationRepo.save(location);
		}
	}

	private void deleteAllPriceTypes(String alertId) throws DomainException {
		List<AlertPriceType> priceTypeLst = priceTypeRepo
				.findAllAlertPriceTypes(alertId);
		if (priceTypeLst.size() != 0) {
			priceTypeRepo.delete(priceTypeLst);
		}
	}

	private void saveAlertPriceType(String alertId, String measureId,
			String type) throws DomainException {
		AlertPriceType priceType = new AlertPriceType();
		priceType.setAlertId(alertId);
		priceType.setMeasureId(measureId);
		priceType.setPriceType(type);
		priceTypeRepo.save(priceType);
	}

	private void deleteAllSourceNwks(String alertId) throws DomainException {
		List<AlertSourceNetwork> nwkList = sourceNwkRepo
				.findAllAlertNetworks(alertId);
		if (nwkList.size() != 0) {
			sourceNwkRepo.delete(nwkList);
		}
	}

	private void saveAlertSourceNwk(String alertId, String[] networks)
			throws DomainException {
		for (String network : networks) {
			AlertSourceNetwork sourceNwk = new AlertSourceNetwork();
			AlertSourceNetworkPK sourceNwkPK = new AlertSourceNetworkPK();
			sourceNwkPK.setPushAlertId(alertId);
			sourceNwkPK.setNetworkId(network);
			sourceNwk.setId(sourceNwkPK);
			sourceNwkRepo.save(sourceNwk);
		}
	}

	public QueryPushRes queryPushres(String networkId) {

		QueryPushRes qrypush = new QueryPushRes();
		Header header = null;

		log.info("Inside QueryAlertsRes -> QueryPushRes");
		log.debug("networkId ->" + networkId);
		QRPUSH qrpsh = new QRPUSH();
		String newAlertId = null;
		String PrevAlertId = null;
		StringBuilder recpNameLis = new StringBuilder();
		StringBuilder recpNameLis1 = new StringBuilder();
		int i = 0;
		int j = 0;
		List<Map<String, Object>> list = (List<Map<String, Object>>) masterRepo
				.queryPush(networkId);
		log.debug("mapList::" + list);
		List<QRPUSH> qrPushs = new ArrayList<QRPUSH>();

		if (list.size() != 0) {
			for (Map<String, Object> alertloop : list) {

				if (alertloop.get("push_alert_id") != null) {
					newAlertId = alertloop.get("push_alert_id").toString();

				}

				if (!newAlertId.equals(PrevAlertId)) {
					qrpsh = new QRPUSH();
					recpNameLis = new StringBuilder();
					recpNameLis1 = new StringBuilder();
					i = 0;
					j = 0;
					if (alertloop.get("push_alert_id") != null) {
						qrpsh.setAlertId(alertloop.get("push_alert_id")
								.toString());
					}

					if (alertloop.get("status") != null) {
						qrpsh.setStatus(alertloop.get("status").toString());
					}

					if (alertloop.get("start_date") != null) {
						qrpsh.setStartDate(Utils.getDDMMMYYFormat(alertloop
								.get("start_date").toString(), 0));
					}

					if (alertloop.get("end_date") != null) {
						qrpsh.setEndDate(Utils.getDDMMMYYFormat(
								alertloop.get("end_date").toString(), 0));
					}

					if (alertloop.get("text") != null) {
						qrpsh.setText(alertloop.get("text").toString());
					}

				}

				if (alertloop.get("recipient_type").toString()
						.equalsIgnoreCase("G")) {
					if (alertloop.get("recipient_id") != null) {

						GroupMaster group = new GroupMaster();
						GroupMasterPK grouppk = new GroupMasterPK();
						grouppk.setGroupId(alertloop.get("recipient_id")
								.toString());
						grouppk.setNetworkId(networkId);
						group.setId(grouppk);

						GroupMaster group1 = groupRepo.findOne(group, grouppk);

						if (group1 != null) {
							if (i == 0) {
								recpNameLis.append(group1.getGROUP_name());
							} else {
								recpNameLis
										.append("," + group1.getGROUP_name());
							}
						}
						qrpsh.setGroups(recpNameLis.toString());

					}
					i++;

				}

				if (alertloop.get("recipient_type").toString()
						.equalsIgnoreCase("U")) {
					if (alertloop.get("recipient_id") != null) {

						People people = new People();
						PeoplePK peoplepk = new PeoplePK();
						peoplepk.setDefaultNetworkId(networkId);
						peoplepk.setPeopleId(alertloop.get("recipient_id")
								.toString());
						people.setId(peoplepk);

						People people1 = pplRepo.findOne(people, peoplepk);

						if (pplRepo.exists(people1, peoplepk)) {

							if (people1.getLastName() != null) {
								if (j == 0) {
									recpNameLis1.append(people1.getFirstName()
											+ " " + people1.getLastName());
								} else {
									recpNameLis1.append(","
											+ people1.getFirstName() + " "
											+ people1.getLastName());
								}

							}

							else {
								if (j == 0) {
									recpNameLis1.append(people1.getFirstName());
								} else {
									recpNameLis1.append(","
											+ people1.getFirstName());
								}
							}

						} else {
							SystemUser sysuser1 = sysRepo.findOne(
									SystemUser.class,
									alertloop.get("recipient_id").toString());

							if (sysuser1 != null) {

								if (sysuser1.getLastName() != null) {
									if (j == 0) {
										recpNameLis1.append(sysuser1
												.getFirstName()
												+ " "
												+ sysuser1.getLastName());
									} else {
										recpNameLis1.append(","
												+ sysuser1.getFirstName() + " "
												+ sysuser1.getLastName());
									}

								}

								else {
									if (j == 0) {
										recpNameLis1.append(sysuser1
												.getFirstName());
									} else {
										recpNameLis1.append(","
												+ sysuser1.getFirstName());
									}
								}

							}

						}

						qrpsh.setUsers(recpNameLis1.toString());

					}
					j++;
				}

				if (i == 0) {
					if (j == 1) {
						qrpsh.setReceiver((j + " Recipient").toString());
					}

					else {
						qrpsh.setReceiver((j + " Recipients").toString());
					}

				}

				else if (j == 0) {
					if (i == 1) {
						qrpsh.setReceiver((i + " Group").toString());
					}

					else {
						qrpsh.setReceiver((i + " Groups").toString());
					}

				}

				else {
					if (j == 1 & i == 1) {
						qrpsh.setReceiver((i + " Group," + j + " Recipient")
								.toString());
					}

					else if (j == 1) {
						qrpsh.setReceiver((i + " Groups," + j + " Recipient")
								.toString());
					}

					else if (i == 1)

					{
						qrpsh.setReceiver((i + " Group," + j + " Recipients")
								.toString());
					}

					else {
						qrpsh.setReceiver((i + " Groups," + j + " Recipients")
								.toString());
					}
				}

				log.debug("alertloop::" + qrpsh);
				if (!newAlertId.equals(PrevAlertId)) {
					qrPushs.add(qrpsh);
				}

				PrevAlertId = newAlertId;

			}

			qrypush.getQRPUSH().addAll(qrPushs);
			ERROR = ERROR_CODE.ES_SC_001;

		}

		else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				serviceName, "Query push DtlsRes", "", ERROR);
		qrypush.setHeader(header);
		return qrypush;
	}

	public QueryAlertsRes queryAlertres(String sortBy, String networkId,
			String filter, String alertType) {
		QueryAlertsRes qryalert = new QueryAlertsRes();
		Header header = null;

		log.info("Inside QueryAlertsRes -> QueryAlertsRes");
		log.debug("sortBy ->" + sortBy);
		log.debug("networkId ->" + networkId);
		log.debug("filter ->" + filter);
		log.debug("alertType ->" + alertType);
		QRALERTS qralrt = new QRALERTS();
		String newAlertId = null;
		String PrevAlertId = null;
		StringBuilder recpNameLis = new StringBuilder();
		StringBuilder recpNameLis1 = new StringBuilder();
		int i = 0;
		int j = 0;
		List<Map<String, Object>> list = (List<Map<String, Object>>) masterRepo
				.queryAlerts(sortBy, networkId, filter, alertType);
		log.debug("mapList::" + list);
		List<QRALERTS> qrAlerts = new ArrayList<QRALERTS>();

		if (list.size() != 0) {
			for (Map<String, Object> alertloop : list) {

				if (alertloop.get("push_alert_id") != null) {
					newAlertId = alertloop.get("push_alert_id").toString();

				}

				if (!newAlertId.equals(PrevAlertId)) {
					qralrt = new QRALERTS();
					recpNameLis = new StringBuilder();
					recpNameLis1 = new StringBuilder();
					i = 0;
					j = 0;
					if (alertloop.get("push_alert_id") != null) {
						qralrt.setAlertId(alertloop.get("push_alert_id")
								.toString());
					}

					if (alertloop.get("name") != null) {
						qralrt.setAlertName(alertloop.get("name").toString());
					}

					if (alertloop.get("commodity_id") != null ? alertloop
							.get("commodity_id").toString().equals("Recipient")
							: false)

					{
						qralrt.setCommodities("Profile Based");
						qralrt.setCommodities1("Profile Based");
						qralrt.setLocations("Profile Based");
						qralrt.setLocations1("Profile Based");
					}

					else if (alertloop.get("location_id") != null ? alertloop
							.get("location_id").toString().equals("Recipient")
							: false)

					{
						qralrt.setLocations("User's primary location");
						qralrt.setLocations1("User's primary location");
					}

					else {

						if (alertloop.get("commodity_id") != null) {
							String commodities = alertloop.get("commodity_id")
									.toString();
							String[] commditiesArr = null;
							if (commodities.indexOf(",") != -1) {
								commditiesArr = commodities.split(",");
								qralrt.setCommodities(commditiesArr.length
										+ " Commodities");
							} else {
								qralrt.setCommodities(alertloop.get(
										"commodity_id").toString());
							}

							qralrt.setCommodities1(alertloop
									.get("commodity_id").toString());

						}

						if (alertloop.get("location_id") != null) {
							String locations = alertloop.get("location_id")
									.toString();
							String[] locationsArr = null;
							if (locations.indexOf(",") != -1) {
								locationsArr = locations.split(",");
								qralrt.setLocations(locationsArr.length
										+ " Markets");
							}

							else {
								qralrt.setLocations(alertloop
										.get("location_id").toString());
							}

							qralrt.setLocations1(alertloop.get("location_id")
									.toString());
						}

					}

					if (alertloop.get("status") != null) {
						qralrt.setStatus(alertloop.get("status").toString());
					}
					if (alertloop.get("edit_flag") != null) {
						qralrt.setEditFlag(alertloop.get("edit_flag")
								.toString());
					}

				}

				if (alertloop.get("recipient_type").toString()
						.equalsIgnoreCase("G")) {
					if (alertloop.get("recipient_id") != null) {

						GroupMaster group = new GroupMaster();
						GroupMasterPK grouppk = new GroupMasterPK();
						grouppk.setGroupId(alertloop.get("recipient_id")
								.toString());
						grouppk.setNetworkId(networkId);
						group.setId(grouppk);

						GroupMaster group1 = groupRepo.findOne(group, grouppk);

						if (group1 != null) {
							if (i == 0) {
								recpNameLis.append(group1.getGROUP_name());
							} else {
								recpNameLis
										.append("," + group1.getGROUP_name());
							}

							qralrt.setGroups(recpNameLis.toString());
						}
					}
					i++;
				}

				if (alertloop.get("recipient_type").toString()
						.equalsIgnoreCase("U")) {
					if (alertloop.get("recipient_id") != null) {

						People people = new People();
						PeoplePK peoplepk = new PeoplePK();
						peoplepk.setDefaultNetworkId(networkId);
						peoplepk.setPeopleId(alertloop.get("recipient_id")
								.toString());
						people.setId(peoplepk);

						People people1 = pplRepo.findOne(people, peoplepk);

						if (pplRepo.exists(people1, peoplepk)) {

							if (people1.getLastName() != null) {
								if (j == 0) {
									recpNameLis1.append(people1.getFirstName()
											+ " " + people1.getLastName());
								} else {
									recpNameLis1.append(","
											+ people1.getFirstName() + " "
											+ people1.getLastName());
								}

							}

							else {
								if (j == 0) {
									recpNameLis1.append(people1.getFirstName());
								} else {
									recpNameLis1.append(","
											+ people1.getFirstName());
								}
							}

						}

						else {

							SystemUser sysuser1 = sysRepo.findOne(
									SystemUser.class,
									alertloop.get("recipient_id").toString());

							if (sysuser1.getLastName() != null) {
								if (j == 0) {
									recpNameLis1.append(sysuser1.getFirstName()
											+ " " + sysuser1.getLastName());
								} else {
									recpNameLis1.append(","
											+ sysuser1.getFirstName() + " "
											+ sysuser1.getLastName());
								}

							}

							else {
								if (j == 0) {
									recpNameLis1
											.append(sysuser1.getFirstName());
								} else {
									recpNameLis1.append(","
											+ sysuser1.getFirstName());
								}
							}

						}

						qralrt.setUsers(recpNameLis1.toString());

					}
					j++;
				}

				if (i == 0) {
					if (j == 1) {
						qralrt.setReceiver((j + " Recipient").toString());
					}

					else {
						qralrt.setReceiver((j + " Recipients").toString());
					}

				}

				else if (j == 0) {
					if (i == 1) {
						qralrt.setReceiver((i + " Group").toString());
					}

					else {
						qralrt.setReceiver((i + " Groups").toString());
					}

				}

				else {
					if (j == 1 & i == 1) {
						qralrt.setReceiver((i + " Group," + j + " Recipient")
								.toString());
					}

					else if (j == 1) {
						qralrt.setReceiver((i + " Groups," + j + " Recipient")
								.toString());
					}

					else if (i == 1)

					{
						qralrt.setReceiver((i + " Group," + j + " Recipients")
								.toString());
					}

					else {
						qralrt.setReceiver((i + " Groups," + j + " Recipients")
								.toString());
					}
				}

				log.debug("alertloop::" + qralrt);
				if (!newAlertId.equals(PrevAlertId)) {
					qrAlerts.add(qralrt);
				}

				PrevAlertId = newAlertId;

			}
			qryalert.getQRALERTS().addAll(qrAlerts);
			ERROR = ERROR_CODE.ES_SC_001;
		}

		else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				serviceName, "Query Agent DtlsRes", "", ERROR);
		qryalert.setHeader(header);
		return qryalert;
	}

	@Transactional
	public CreatePushTemplateRes createPushTemplateRes(CreatePushTemplate req) {
		CreatePushTemplateRes res = new CreatePushTemplateRes();
		CRPSHTMPLT crvPshTmpt = req.getCRPSHTMPLT();
		PushTemplate entity = new PushTemplate();
		PushTemplatePK entitypk = new PushTemplatePK();
		PushTemplate tmpentity = new PushTemplate();
		PushTemplatePK tmpentitypk = new PushTemplatePK();
		tmpentitypk.setNetworkId(crvPshTmpt.getNetworkId());
		if (crvPshTmpt.getOldTemplateId() != null) {
			tmpentitypk.setTemplateId(crvPshTmpt.getOldTemplateId());
			tmpentity.setId(tmpentitypk);
		}
		entitypk.setNetworkId(crvPshTmpt.getNetworkId());
		entitypk.setTemplateId(crvPshTmpt.getTemplateId());
		entity.setId(entitypk);
		entity.setCreatedBy(req.getHeader().getUserId());
		entity.setCreated_TS(Utils.getCurrentDate());
		entity.setAuthStat("A");
		entity.setMessage(crvPshTmpt.getMessage());
		entity.setMessages(Integer.parseInt(crvPshTmpt.getNoofmessages()));
		entity.setCharacters(Integer.parseInt(crvPshTmpt.getCharacters()));

		if (crvPshTmpt.getEditFlag().toString().equalsIgnoreCase("E")) {
			if (pshRepo.exists(entity, entitypk)) {
				log.info("am existing hence inside merge method");
				pshRepo.merge(entity);
				ERROR = ERROR_CODE.PUSH_TEMPLATE_ED;
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						serviceName, "Create push templates", "", ERROR);
			} else {
				pshRepo.save(entity);
				tmpentity = pshRepo.findOne(tmpentity, tmpentitypk);
				pshRepo.delete(tmpentity);
				ERROR = ERROR_CODE.PUSH_TEMPLATE_ED;
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						serviceName, "Create push templates", "", ERROR);
			}
		} else {
			log.info("i dont exist hence inside save");
			pshRepo.save(entity);

			ERROR = ERROR_CODE.PUSH_TEMPLATE_SC;
			header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
					serviceName, "Create push templates", "", ERROR);
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				serviceName, "Create psh templates", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeletePushTemplateRequestRes deleteTemplateRes(
			DeletePushTemplateRequest req) {
		DeletePushTemplateRequestRes res = new DeletePushTemplateRequestRes();
		List<DLPSHTEMPS> dltTemplt = req.getDLPSHTEMPS();
		for (DLPSHTEMPS dlpshtmp : dltTemplt) {
			PushTemplate entity = new PushTemplate();
			PushTemplatePK entitypk = new PushTemplatePK();
			entitypk.setNetworkId(dlpshtmp.getNetworkId());
			entitypk.setTemplateId(dlpshtmp.getTemplateId());
			entity.setId(entitypk);

			entity = pshRepo.findOne(entity, entitypk);

			if (pshRepo.exists(entity, entitypk)) {
				log.info("deleting");
				pshRepo.delete(entity);
				ERROR = ERROR_CODE.PS_TMP_DL_001;
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						serviceName, "delete push templates", "", ERROR);
			} else

			{
				ERROR = ERROR_CODE.DM_SV_002;
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						serviceName, "delete push templates", "", ERROR);
			}
		}

		res.setHeader(header);
		return res;
	}

	public QueryPushTemplatesRes templateDetailsRes(String networkId,
			String sortBy) {

		log.info("Inside QueryPushTemplatesRes -> templateDetailsRes");
		log.debug("NetworkId: " + networkId);

		QueryPushTemplatesRes res = new QueryPushTemplatesRes();
		List<PushTemplate> pushTmpt = pshRepo.queryAllPushTemplates(networkId,
				sortBy);
		List<QRPUSHTEMPLATES> qrtempList = new ArrayList<QRPUSHTEMPLATES>();

		if (pushTmpt.size() != 0) {
			log.info("Record found");
			for (PushTemplate srvytem : pushTmpt) {
				QRPUSHTEMPLATES qrTemplates = new QRPUSHTEMPLATES();

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				try {
					Date date = formatter.parse(srvytem.getCreated_TS()
							.toString());
					log.debug("Date: " + date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				qrTemplates.setCreatedTs(Utils.getDateFormater(srvytem
						.getCreated_TS().toString(), 0));
				qrTemplates.setMessage(srvytem.getMessage());
				qrTemplates.setTemplateId(srvytem.getId().getTemplateId());
				qrTemplates.setNetworkId(srvytem.getId().getNetworkId());
				qrTemplates.setCharacters(Integer.toString(srvytem
						.getCharacters()));
				qrTemplates.setNoOfMessages(Integer.toString(srvytem
						.getMessages()));
				qrtempList.add(qrTemplates);
				qrTemplates = null;

			}
			res.getQRPUSHTEMPLATES().addAll(qrtempList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				serviceName, "Query templates DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;

	}

	public QueryPushDeliveryRes queryPushDelivery(String networkId,
			String fromDate, String toDate) {
		QueryPushDeliveryRes res = new QueryPushDeliveryRes();
		Header header = null;
		try {
			log.info("Inside QueryPushDeliveryRes -> queryPushDelivery");
			log.debug("networkId -> " + networkId);
			log.debug("fromDate -> " + fromDate);
			log.debug("fromDate -> " + toDate);
			List<Map<String, Object>> entity = (List<Map<String, Object>>) msgRepo
					.queryPushDelivery(Utils.getFormatedDate(fromDate),
							Utils.getFormatedDate(toDate), networkId);
			List<PSHDLVRYRPT> dlvryList = new ArrayList<PSHDLVRYRPT>();
			log.info("entity -> " + entity);

			if (entity.size() != 0) {
				for (Map<String, Object> dlvryloop : entity) {
					PSHDLVRYRPT dlvryrpt = new PSHDLVRYRPT();

					dlvryrpt.setScheduleId(dlvryloop.get("schedule_id")
							.toString());
					dlvryrpt.setMessage(dlvryloop.get("message").toString());
					dlvryrpt.setSent(dlvryloop.get("sent").toString());
					dlvryrpt.setPending(dlvryloop.get("pending").toString());
					dlvryrpt.setFailed(dlvryloop.get("failed").toString());
					dlvryrpt.setTotal(dlvryloop.get("total").toString());
					dlvryrpt.setDate(Utils.getDDMMMYYFormat(
							dlvryloop.get("date").toString(), 0));

					dlvryList.add(dlvryrpt);
					dlvryrpt = null;
				}
				res.getPSHDLVRYRPT().addAll(dlvryList);
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryPushDelivery", "",
						ERROR_CODE.ES_SC_001);
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryPushDelivery", "",
						ERROR_CODE.ES_NR_001);
			}
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
					"PushAlertService", "queryPushDelivery", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}
		res.setHeader(header);
		return res;
	}

	public QueryAlertDeliveryRes queryAlertDelivery(String networkId,
			String fromDate, String toDate, String sortBy, String messageType) {
		QueryAlertDeliveryRes res = new QueryAlertDeliveryRes();
		Header header = null;
		try {
			log.info("Inside QueryAlertDeliveryRes -> queryAlertDelivery");
			log.debug("networkId -> " + networkId);
			log.debug("fromDate -> " + fromDate);
			log.debug("fromDate -> " + toDate);
			log.debug("sortBy -> " + sortBy);
			log.debug("messageType -> " + messageType);
			List<Map<String, Object>> entity = (List<Map<String, Object>>) msgRepo
					.queryAlertDelivery(Utils.getFormatedDate(fromDate),
							Utils.getFormatedDate(toDate), networkId, sortBy,
							messageType);
			List<ALRTDLVRYRPT> dlvryList = new ArrayList<ALRTDLVRYRPT>();
			log.info("entity -> " + entity);

			if (entity.size() != 0) {
				for (Map<String, Object> dlvryloop : entity) {
					ALRTDLVRYRPT dlvryrpt = new ALRTDLVRYRPT();

					dlvryrpt.setScheduleId(dlvryloop.get("schedule_id")
							.toString());
					dlvryrpt.setAlertName(dlvryloop.get("alert_name")
							.toString());
					dlvryrpt.setTime(dlvryloop.get("time").toString());
					dlvryrpt.setSent(dlvryloop.get("sent").toString());
					dlvryrpt.setPending(dlvryloop.get("pending").toString());
					dlvryrpt.setFailed(dlvryloop.get("failed").toString());
					dlvryrpt.setTotal(dlvryloop.get("total").toString());
					dlvryrpt.setDate(Utils.getDDMMMYYFormat(
							dlvryloop.get("date").toString(), 0));

					dlvryList.add(dlvryrpt);
					dlvryrpt = null;
				}
				res.getALRTDLVRYRPT().addAll(dlvryList);
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryAlertDelivery", "",
						ERROR_CODE.ES_SC_001);
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryAlertDelivery", "",
						ERROR_CODE.PUSH_ALR_NR);
			}
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
					"PushAlertService", "queryAlertDelivery", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}
		res.setHeader(header);
		return res;
	}

	public QueryPushAlertDlvrybyIdRes queryAlertPshDlvry(String alrtId,
			String createDate) {
		QueryPushAlertDlvrybyIdRes res = new QueryPushAlertDlvrybyIdRes();
		Header header = null;
		try {
			log.info("Inside QueryPushAlertDlvrybyIdRes -> queryAlertPshDlvry");
			log.debug("alrtId -> " + alrtId);
			log.debug("createDate -> " + createDate);

			List<Message_delivary_Detail> entity = (List<Message_delivary_Detail>) msgRepo
					.queryPushAlertDlvrybyId(alrtId,
							Utils.getFormatedDate1(createDate));
			List<PSHALRTDTLSID> dlvryList = new ArrayList<PSHALRTDTLSID>();
			log.info("entity -> " + entity);

			if (entity.size() != 0) {
				for (Message_delivary_Detail dlvryloop : entity) {
					PSHALRTDTLSID dlvryrpt = new PSHALRTDTLSID();

					dlvryrpt.setUserid(dlvryloop.getUserId());
					dlvryrpt.setStatus(dlvryloop.getMsgStatus());
					People ppl = new People();
					PeoplePK pplpk = new PeoplePK();

					pplpk.setDefaultNetworkId(dlvryloop.getNetworkId());
					pplpk.setPeopleId(dlvryloop.getUserId());
					ppl.setId(pplpk);
					People ppl1 = pplRepo.findOne(ppl, pplpk);

					if (ppl1 != null) {
						if (ppl1.getFirstName() != null) {
							if (ppl1.getLastName() != null) {
								dlvryrpt.setUsername(ppl1.getFirstName() + " "
										+ ppl1.getLastName());
							} else {
								dlvryrpt.setUsername(ppl1.getFirstName());
							}
						}
					}

					else {
						SystemUser sysusr = new SystemUser();
						sysusr.setUserId(dlvryloop.getUserId());
						sysusr = sysRepo.findOne(sysusr, dlvryloop.getUserId());
						if (sysusr != null) {
							if (sysusr.getFirstName() != null) {
								if (sysusr.getLastName() != null) {
									dlvryrpt.setUsername(sysusr.getFirstName()
											+ " " + sysusr.getLastName());
								} else {
									dlvryrpt.setUsername(sysusr.getFirstName());
								}
							}
						}
					}

					dlvryList.add(dlvryrpt);
					dlvryrpt = null;
				}
				res.getPSHALRTDTLSID().addAll(dlvryList);
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryAlertDelivery", "",
						ERROR_CODE.ES_SC_001);
			} else {
				header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
						"PushAlertService", "queryAlertDelivery", "",
						ERROR_CODE.ES_NR_001);
			}
		} catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
					"PushAlertService", "queryAlertDelivery", "",
					ERROR_CODE.DM_SV_002);
			Utils.getStackTrace(e);
		}
		res.setHeader(header);
		return res;
	}

	public QueryPeopleByPhoneRes queryPeopleByPhone(String phoneNumbers) {
		log.info("Inside PushAlertService -> queryPeople");
		QueryPeopleByPhoneRes res = new QueryPeopleByPhoneRes();
		String[] numberArray = phoneNumbers.split(",");
		List<PEOPLEDTLS> pplList = new ArrayList<PEOPLEDTLS>();
		StringBuilder unavailableNumber = new StringBuilder("");
		for (int i = 0; i < numberArray.length; i++) {
			People people = pplRepo.findByMsisdn(numberArray[i]);
			if (people != null) {
				PEOPLEDTLS pplDtl = new PEOPLEDTLS();
				StringBuilder name = new StringBuilder("");
				if (people.getFirstName() != null) {
					name.append(people.getFirstName());
				}
				if (people.getLastName() != null) {
					name.append(" " + people.getLastName());
				}
				pplDtl.setCountry(people.getCountry());
				pplDtl.setMobile(people.getMsisdn());
				pplDtl.setType("U");
				pplDtl.setUserId(people.getId().getPeopleId());
				pplDtl.setUserName(name.toString());
				pplList.add(pplDtl);
			} else {
				if (unavailableNumber.toString().equals("")) {
					unavailableNumber.append(numberArray[i]);
				} else {
					unavailableNumber.append("," + numberArray[i]);
				}
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "queryPeopleByPhone", "",
				ERROR_CODE.ES_SC_001);
		res.getPEOPLEDTLS().addAll(pplList);
		res.setUNAVLBLNOS(unavailableNumber.toString());
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CreateCropTipRes createCropTip(CreateCropTipReq req) {
		log.info("Inside PushAlertService -> createCropTip");
		CreateCropTipRes res = new CreateCropTipRes();
		CROPTIP cropTip = req.getCROPTIP();
		List<RECIPIENTS> recipients = cropTip.getRECIPIENTS();
		List<TIPDETAILS> tipDetails = cropTip.getTIPDETAILS();
		String cropTipId = null;
		String requestType = cropTip.getRequestType();
		String triggerType = cropTip.getTriggerType();
		String userId = req.getHeader().getUserId();
		boolean proceed = true;
		if (requestType.equalsIgnoreCase("edit")) {
			cropTipId = cropTip.getCropTipId();
			CropTipMaster entity = tipMasterRepo.findOne(CropTipMaster.class,
					cropTipId);
			if (entity == null) {
				proceed = false;
				ERROR = ERROR_CODE.ES_NR_001;
			} else {
				triggerType = entity.getTriggerType();
				if ((!cropTip.getTipName().equals(entity.getCropTipName()))
						&& (tipMasterRepo.queryCropTipByAlertName(cropTip
								.getTipName()) != null)) {
					proceed = false;
					ERROR = ERROR_CODE.PUSH_ALERT_ER_005;
				}
				if ((triggerType.equalsIgnoreCase("SMS"))
						&& !(cropTip.getTipCode().equals(entity.getAlertCode()))) {
					if (tipMasterRepo.queryCropTipByAlertCode(cropTip
							.getTipCode()) != null) {
						proceed = false;
						ERROR = ERROR_CODE.PUSH_ALERT_ER_004;
					}
				}
			}
		} else {
			if (tipMasterRepo.queryCropTipByAlertName(cropTip.getTipName()) != null) {
				proceed = false;
				ERROR = ERROR_CODE.PUSH_ALERT_ER_005;
			}
			if ((triggerType.equalsIgnoreCase("SMS"))
					&& (tipMasterRepo.queryCropTipByAlertCode(cropTip
							.getTipCode()) != null)) {
				proceed = false;
				ERROR = ERROR_CODE.PUSH_ALERT_ER_004;
			}
		}
		if (proceed) {
			try {
				if (requestType.equalsIgnoreCase("save")) {
					cropTipId = esokoRepo.getSequenceNumber() + "";
				}
				// Updating crop_tip_master
				CropTipMaster entity2 = new CropTipMaster();
				if (requestType.equalsIgnoreCase("edit")) {
					CropTipMaster exstngEntity2 = tipMasterRepo.findOne(
							CropTipMaster.class, cropTipId);
					entity2.setAlertState(exstngEntity2.getAlertState());
					entity2.setCreatedBy(exstngEntity2.getCreatedBy());
					entity2.setCreatedTs(exstngEntity2.getCreatedTs());
					entity2.setModifiedBy(userId);
					entity2.setModifiedTs(new Date());
				} else {
					entity2.setAlertState("A");
					entity2.setCreatedBy(userId);
					entity2.setCreatedTs(new Date());
				}
				if (cropTip.getTriggerType().equalsIgnoreCase("SMS")) {
					entity2.setAlertCode(cropTip.getTipCode());
				}
				entity2.setCategory(cropTip.getTipCategory());
				entity2.setCommodityId(cropTip.getCommodity());
				entity2.setCropTipId(cropTipId);
				entity2.setCropTipName(cropTip.getTipName());

				if (cropTip.getTipCategory().equalsIgnoreCase("O")) {
					entity2.setCustomCategory(cropTip.getCustomCategory());
				}
				entity2.setCurrencyId(cropTip.getCurrencyId());
				entity2.setEndDate(Utils.getFormatedDate4(cropTip.getEndDate()));
				entity2.setNetworkId(cropTip.getNetworkId());
				entity2.setNumberOfTips(tipDetails.size());
				entity2.setPayeeAccount(cropTip.getPayeeAccNo());
				entity2.setPayeeType(cropTip.getPayeeType());
				entity2.setStartDate(Utils.getFormatedDate4(cropTip
						.getStartDate()));
				entity2.setTriggerType(cropTip.getTriggerType());
				tipMasterRepo.merge(entity2);

				// Updating crop_tip_details and push_alert_master
				List<CropTipDetail> tipList = tipDetailRepo
						.findAllCropDetails(cropTipId);
				tipDetailRepo.delete(tipList);

				List<PushAlertMaster> alertList = masterRepo
						.findAllCropTipAlerts(cropTipId);
				masterRepo.delete(alertList);

				Set<String> peopleList = new HashSet<String>();
				if (triggerType.equalsIgnoreCase("AUTO")
						&& requestType.equalsIgnoreCase("SAVE")) {
					peopleList.add("");
				} else {
					for (PushAlertMaster master : alertList) {
						peopleList.add(master.getId().getPeopleId());
					}
				}

				log.debug("PeopleList: " + peopleList);

				TriggerHandler handler = TriggerHandler.getInstance();
				for (PushAlertMaster entity : alertList) {
					TriggerDetails triggerDetails = new TriggerDetails();
					triggerDetails
							.setTriggerId(entity.getId().getPushAlertId());
					handler.deleteTrigger(triggerDetails);
				}

				if (triggerType.equalsIgnoreCase("AUTO")) {
					// Updating push_alert_recipients
					this.deleteAllAlertRecipients(cropTipId);
					for (RECIPIENTS recp : recipients) {
						String parentId = null;
						if (recp.getParentId() == null) {
							parentId = "";
						} else {
							parentId = recp.getParentId();
						}
						saveAlertRecipients(cropTipId, recp.getRecipientId(),
								recp.getRecipientType(), parentId);
					}
				}

				Date prevDate = null;
				for (TIPDETAILS detail : tipDetails) {
					int tipNumber = detail.getTipNumber().intValue();
					log.debug("Tip Number: " + tipNumber);
					log.debug("Tip interval: " + detail.getTipInterval());
					log.debug("Tip Measure: " + detail.getTipMeasure());
					String lCropTipId = cropTipId + tipNumber;
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Time transmissionTime = null;
					Date transmissionDate = Utils.getFormatedDateTimeStamp1(df
							.format(new Date()) + " " + detail.getTipTime());
					if (triggerType.equalsIgnoreCase("AUTO")) {
						transmissionDate = Utils
								.getFormatedDateTimeStamp1(detail.getTipDate()
										+ " " + detail.getTipTime());
					} else if (triggerType.equalsIgnoreCase("SMS")) {
						if (tipNumber == 1) {
							transmissionDate = Utils
									.getFormatedDateTimeStamp1(df
											.format(new Date())
											+ " "
											+ detail.getTipTime());
							prevDate = transmissionDate;
						} else {
							transmissionDate = this.addInterval(prevDate,
									detail.getTipMeasure(), detail
											.getTipInterval().intValue());
							prevDate = transmissionDate;
						}
					}
					transmissionTime = new Time(transmissionDate.getTime());

					for (String peopleId : peopleList) {
						// String peopleId = master.getId().getPeopleId();
						Date startDate = Utils
								.getFormatedDateTimeStamp1(cropTip
										.getStartDate()
										+ " "
										+ detail.getTipTime());
						Date endDate = Utils.getFormatedDateTimeStamp1(cropTip
								.getEndDate() + " " + detail.getTipTime());

						// Updating push_alert_master
						PushAlertMaster entity1 = new PushAlertMaster();
						PushAlertMasterPK pk1 = new PushAlertMasterPK();
						pk1.setPeopleId(peopleId);
						pk1.setPushAlertId(lCropTipId);
						entity1.setAlertState(entity2.getAlertState());
						entity1.setCreatedBy(entity2.getCreatedBy());
						entity1.setCreatedTs(entity2.getCreatedTs());
						entity1.setModifiedBy(entity2.getModifiedBy());
						entity1.setModifiedTs(entity2.getModifiedTs());
						entity1.setDataStatus(entity2.getAlertState());
						entity1.setId(pk1);
						if (cropTip.getTriggerType().equalsIgnoreCase("AUTO")) {
							entity1.setIsPublic("N");
						} else {
							entity1.setIsPublic("Y");
							entity1.setAlertCode(cropTip.getTipCode());
						}
						entity1.setCurrencyId(cropTip.getCurrencyId());
						entity1.setEndDate(endDate);
						entity1.setFreqFlag("O");
						entity1.setMessageType("CT");
						entity1.setName(cropTip.getTipName());
						entity1.setNoOfMessages(detail.getNuOfMsg().intValue());
						entity1.setPayeeAccountNo(cropTip.getPayeeAccNo());
						entity1.setPayeeNetworkId(cropTip.getNetworkId());
						entity1.setPayeeType(cropTip.getPayeeType());
						entity1.setScheduleTime(transmissionTime);
						entity1.setSenderId(getSenderId(cropTip.getNetworkId(),
								SERIES));
						entity1.setStartDate(startDate);
						entity1.setText(detail.getTipContent());
						masterRepo.merge(entity1);
						log.debug("StartDate: " + startDate);
						log.debug("EndDate: " + endDate);
						log.debug("Transmission Date for Tip " + tipNumber
								+ ": " + transmissionDate);
						// Creating trigger
						Date currDate = new Date();

						if (transmissionDate.equals(currDate)
								|| transmissionDate.after(currDate)) {
							if (isInRange(startDate, endDate, transmissionDate)) {
								log.info("Creating trigger");
								TriggerDetails triggerDtls = new TriggerDetails();
								triggerDtls.setTriggerId(lCropTipId + peopleId);
								triggerDtls.setFlag(false);
								triggerDtls.setStartDate(transmissionDate);
								triggerDtls.setFrequency("once");
								handler.activateTrigger(PushAlertJob.class,
										triggerDtls);
								if (entity2.getAlertState().equalsIgnoreCase(
										"S")) {
									handler.deactivateTrigger(triggerDtls);
								}
							} else {
								log.info("Deleting trigger because transmission date is out of start and end date range");
								deleteTigger(lCropTipId + peopleId);
							}
						} else {
							log.info("Deleting trigger because transmission date is out of start and end date range");
							deleteTigger(lCropTipId + peopleId);
						}
					}

					// Updating crop_tip_details
					CropTipDetailPK pk = new CropTipDetailPK();
					pk.setCropTipId(cropTipId);
					pk.setTipNo("" + tipNumber);
					CropTipDetail entity = new CropTipDetail();
					entity.setCreatedBy(userId);
					entity.setCreatedTs(new Date());
					entity.setId(pk);
					if (tipNumber != 1) {
						entity.setIntervalMeasure(detail.getTipMeasure());
						entity.setIntervalPeriod(detail.getTipInterval()
								.intValue());
					}
					entity.setNoOfCharacters(detail.getNuOfChar().intValue());
					entity.setNoOfMessages(detail.getNuOfMsg().intValue());
					entity.setText(detail.getTipContent());
					if (cropTip.getTriggerType().equalsIgnoreCase("AUTO")) {
						entity.setTransmissionDate(transmissionDate);
					}
					entity.setTransmissionTime(transmissionTime);
					tipDetailRepo.save(entity);
				}

				if (requestType.equalsIgnoreCase("edit")) {
					ERROR = ERROR_CODE.PUSH_ALERT_SC_009;
					log.debug("Series updated successfully");
				} else {
					ERROR = ERROR_CODE.PUSH_ALERT_SC_008;
					log.debug("Series created successfully");
				}

			} catch (DomainException e) {
				ERROR = e.getCode();
				log.error(Utils.getStackTrace(e));
			}
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "createCropTip", "", ERROR);
		res.setHeader(header);
		return res;
	}

	private Date addInterval(Date currDate, String measure, int interval) {
		Calendar dt = Calendar.getInstance();
		dt.setTime(currDate);
		Date finalDate = null;
		if (measure.equalsIgnoreCase("Minutes")) {
			dt.add(Calendar.MINUTE, interval);
			finalDate = new Date(dt.getTimeInMillis());
		} else if (measure.equalsIgnoreCase("Hours")) {
			dt.add(Calendar.HOUR_OF_DAY, interval);
			finalDate = new Date(dt.getTimeInMillis());
		} else if (measure.equalsIgnoreCase("Days")) {
			dt.add(Calendar.DAY_OF_WEEK, interval);
			finalDate = new Date(dt.getTimeInMillis());
		} else if (measure.equalsIgnoreCase("Weeks")) {
			dt.add(Calendar.WEEK_OF_MONTH, interval);
			finalDate = new Date(dt.getTimeInMillis());
		} else if (measure.equalsIgnoreCase("Month")) {
			dt.add(Calendar.MONTH, interval);
			finalDate = new Date(dt.getTimeInMillis());
		}
		return finalDate;
	}

	public QueryCropTipsRes queryCropTips(String show, String sort,
			String networkId) {
		log.info("Inside PushAlertService -> queryCropTips");
		QueryCropTipsRes res = new QueryCropTipsRes();
		List<Map<String, Object>> tipList = tipMasterRepo.queryAllTips(show,
				sort, networkId);
		if (tipList.size() != 0) {
			List<QRTIPS> resultList = new ArrayList<QRTIPS>();
			for (Map<String, Object> map : tipList) {
				QRTIPS tip = new QRTIPS();
				tip.setAlertName(map.get("cropTipName").toString());
				String category = map.get("category").toString();
				if (category.equalsIgnoreCase("A")) {
					tip.setCategory("Agric");
				} else if (category.equalsIgnoreCase("N")) {
					tip.setCategory("Nutrition");
				} else if (category.equalsIgnoreCase("H")) {
					tip.setCategory("Health");
				} else if (category.equalsIgnoreCase("O")) {
					tip.setCategory(map.get("categoryName").toString());
				}
				if (map.get("commodity") != null) {
					tip.setCommodity(map.get("commodity").toString());
				}
				tip.setCropTipId(map.get("cropTipId").toString());
				tip.setPeopleId("");
				String state = map.get("state").toString();
				if (state.equalsIgnoreCase("A")) {
					tip.setStatus("Active");
				} else if (state.equalsIgnoreCase("S")) {
					tip.setStatus("Suspended");
				}
				int groupCount = 0;
				int userCount = 0;
				StringBuilder recpDetail = new StringBuilder();
				if (map.get("type").toString().equalsIgnoreCase("AUTO")) {
					List<PushAlertRecipient> recpList = recipientRepo
							.findAllPushAlertRecipients(map.get("cropTipId")
									.toString());
					for (PushAlertRecipient recipient : recpList) {
						if (recipient.getRecipientType().equalsIgnoreCase("G")) {
							groupCount++;
						} else {
							userCount++;
						}
						String name = this.getUserName(recipient.getId()
								.getRecipientId(), map.get("networkId")
								.toString(), recipient.getRecipientType());
						if (recpDetail.toString().equals("")) {
							recpDetail.append(name);
						} else {
							recpDetail.append(", " + name);
						}
					}
				} else {
					List<PushAlertMaster> alertList = masterRepo
							.findAllCropTipAlerts(map.get("cropTipId")
									.toString());
					int nuOfTips = Integer.parseInt(map.get("nuOfTips")
							.toString());
					userCount = alertList.size() / nuOfTips;

					Set<String> nameSet = new HashSet<String>();
					for (PushAlertMaster alert : alertList) {
						nameSet.add(this.getUserName(alert.getId()
								.getPeopleId(), "", "U"));
					}
					for (String name : nameSet) {
						if (recpDetail.toString().equals("")) {
							recpDetail.append(name);
						} else {
							recpDetail.append(", " + name);
						}
					}
				}
				StringBuilder recpCount = new StringBuilder();
				if (groupCount > 0) {
					if (groupCount == 1) {
						recpCount.append(groupCount + " Group");
					} else {
						recpCount.append(groupCount + " Groups");
					}
				}
				if (userCount > 0) {
					if (groupCount > 0) {
						recpCount.append(", ");
					}
					if (userCount == 1) {
						recpCount.append(userCount + " Recipient");
					} else {
						recpCount.append(userCount + " Recipients");
					}
				}
				if (recpCount.toString().equals("")) {
					recpCount.append("No recipients are registered");
				}
				tip.setRecipientCount(recpCount.toString());
				tip.setRecipientDetail(recpDetail.toString());
				resultList.add(tip);
			}
			res.getQRTIPS().addAll(resultList);
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "createCropTip", "", ERROR);
		res.setHeader(header);
		return res;
	}

	private String getUserName(String userId, String networkId, String type) {
		StringBuilder name = new StringBuilder();
		if (type.equalsIgnoreCase("U")) {
			SystemUser user = sysRepo.findOne(SystemUser.class, userId);
			name.append(user.getFirstName());
			if (StringUtils.isNotEmpty(user.getLastName())) {
				name.append(" " + user.getLastName());
			}
		} else {
			GroupMasterPK groupMasterPk = new GroupMasterPK();
			groupMasterPk.setGroupId(userId);
			groupMasterPk.setNetworkId(networkId);
			GroupMaster entity = groupRepo.findOne(GroupMaster.class,
					groupMasterPk);
			name.append(entity.getGROUP_name());
		}

		return name.toString();
	}

	public QueryCropAlertRes queryCropAlert(String cropTipId, String operation) {
		log.info("Inside PushAlertRest -> queryCropAlert");
		log.debug("CropTipId: " + cropTipId);
		log.debug("Operation: " + operation);
		QueryCropAlertRes res = new QueryCropAlertRes();
		CropTipMaster entity = tipMasterRepo.findOne(CropTipMaster.class,
				cropTipId);
		if (entity == null) {
			ERROR = ERROR_CODE.ES_NR_001;
			log.info("No crop tip found");
		} else {
			CROPTIP tip = new CROPTIP();
			tip.setCommodity(entity.getCommodityId());
			tip.setCropTipId(cropTipId);
			tip.setCurrencyId(entity.getCurrencyId());
			tip.setCustomCategory(entity.getCustomCategory());
			tip.setEndDate(convertDateToString1(entity.getEndDate()));
			tip.setNetworkId(entity.getNetworkId());
			tip.setPayeeAccNo(entity.getPayeeAccount());
			tip.setPayeeType(entity.getPayeeType());
			tip.setPeopleId("");
			if (operation.equalsIgnoreCase("EDIT")) {
				tip.setRequestType("edit");
			} else {
				tip.setRequestType("save");
			}
			tip.setStartDate(convertDateToString1(entity.getStartDate()));
			tip.setTipCategory(entity.getCategory());
			tip.setTipCode(entity.getAlertCode());
			tip.setTipName(entity.getCropTipName());
			tip.setTriggerType(entity.getTriggerType());

			if (entity.getTriggerType().equalsIgnoreCase("AUTO")) {
				List<PushAlertRecipient> recpList = recipientRepo
						.findAllPushAlertRecipients(cropTipId);
				List<RECIPIENTS> recpEntityList = new ArrayList<RECIPIENTS>();
				for (PushAlertRecipient recp : recpList) {
					RECIPIENTS lEntity = new RECIPIENTS();
					lEntity.setParentId(recp.getId().getParentId());
					lEntity.setRecipientId(recp.getId().getRecipientId());
					lEntity.setRecipientType(recp.getRecipientType());
					recpEntityList.add(lEntity);
				}
				tip.getRECIPIENTS().addAll(recpEntityList);
			}

			List<CropTipDetail> tipList = tipDetailRepo
					.findAllCropDetails(cropTipId);
			List<TIPDETAILS> tipDetailList = new ArrayList<TIPDETAILS>();
			for (CropTipDetail tipDetail : tipList) {
				TIPDETAILS lEntity = new TIPDETAILS();
				lEntity.setNuOfChar(BigInteger.valueOf(tipDetail
						.getNoOfCharacters()));
				lEntity.setNuOfMsg(BigInteger.valueOf(tipDetail
						.getNoOfMessages()));
				lEntity.setTipContent(tipDetail.getText());
				if (tipDetail.getTransmissionDate() != null) {
					lEntity.setTipDate(convertDateToString1(tipDetail
							.getTransmissionDate()));
				}
				lEntity.setTipInterval(BigInteger.valueOf(tipDetail
						.getIntervalPeriod()));
				lEntity.setTipMeasure(tipDetail.getIntervalMeasure());
				lEntity.setTipNumber(BigInteger.valueOf(Long.valueOf(tipDetail
						.getId().getTipNo())));
				lEntity.setTipTime(convertTimeToString(tipDetail
						.getTransmissionTime()));
				tipDetailList.add(lEntity);
			}
			tip.getTIPDETAILS().addAll(tipDetailList);
			res.setCROPTIP(tip);
			ERROR = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "queryCropAlert", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CropActionRes cropAction(CropActionReq req) {
		log.info("Inside PushAlertService -> cropAction");
		CropActionRes res = new CropActionRes();
		ACTIONDTL actionDtl = req.getACTIONDTL();
		String action = actionDtl.getAction();
		String userId = req.getHeader().getUserId();
		List<PUSHALERTID> alertList = actionDtl.getPUSHALERTID();
		try {
			for (PUSHALERTID alertId : alertList) {
				String cropTipId = alertId.getAlertId();
				CropTipMaster tip = tipMasterRepo.findOne(CropTipMaster.class,
						cropTipId);
				String triggerType = tip.getTriggerType();
				List<PushAlertMaster> cropTipList = null;
				List<CropTipDetail> tipDetail = null;
				if (action.equalsIgnoreCase("A")) {
					tipMasterRepo.updateCropTipStatus(cropTipId, userId, "A");
				} else if (action.equalsIgnoreCase("S")) {
					tipMasterRepo.updateCropTipStatus(cropTipId, userId, "S");
				} else if (action.equalsIgnoreCase("D")) {
					// Deleting crop_tip_master
					tipMasterRepo.delete(tip);
					// Deleting crop_tip_details
					List<CropTipDetail> tipList = tipDetailRepo
							.findAllCropDetails(cropTipId);
					tipDetailRepo.delete(tipList);
					tipDetail = tipList;
					// Deleting push_alert_master
					List<PushAlertMaster> lAlertList = masterRepo
							.findAllCropTipAlerts(cropTipId);
					masterRepo.delete(lAlertList);
					cropTipList = lAlertList;
					// Deleting push_alert_recipients
					this.deleteAllAlertRecipients(cropTipId);
					// Deleting trigger
				}
				if (tipDetail == null) {
					tipDetail = tipDetailRepo.findAllCropDetails(cropTipId);
				}

				TriggerHandler handler = TriggerHandler.getInstance();
				TriggerDetails triggerDetails = new TriggerDetails();
				if (cropTipList == null) {
					cropTipList = masterRepo.findAllCropTipAlerts(cropTipId);
				}

				Set<String> peopleList = new HashSet<String>();
				for (PushAlertMaster alertMaster : cropTipList) {
					peopleList.add(alertMaster.getId().getPeopleId());
				}
				for (String peopleId : peopleList) {
					Date transmissionDate = null;
					for (int i = 1; i <= tipDetail.size(); i++) {
						String triggerId = cropTipId + i + peopleId;
						triggerDetails.setTriggerId(triggerId);
						if (action.equalsIgnoreCase("A")) {
							masterRepo.updatePushAlertStatus(cropTipId + i,
									userId, "A");
							transmissionDate = recalibrateTriggers(triggerType,
									cropTipId, userId, transmissionDate,
									i + "", peopleId);
						} else if (action.equalsIgnoreCase("S")) {
							masterRepo.updatePushAlertStatus(cropTipId + i,
									userId, "S");
							handler.deactivateTrigger(triggerDetails);
						} else if (action.equalsIgnoreCase("D")) {
							handler.deleteTrigger(triggerDetails);
						}
					}
				}
			}
			if (action.equalsIgnoreCase("A")) {
				ERROR = ERROR_CODE.PUSH_ALERT_SC_004;
			} else if (action.equalsIgnoreCase("S")) {
				ERROR = ERROR_CODE.PUSH_ALERT_SC_005;
			} else if (action.equalsIgnoreCase("D")) {
				ERROR = ERROR_CODE.PUSH_ALERT_SC_006;
			}
		} catch (DomainException e) {
			log.error(Utils.getStackTrace(e));
			ERROR = e.getCode();
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.PUSHALERT,
				"PushAlertService", "cropAction", "", ERROR);
		res.setHeader(header);
		return res;
	}

	private Date recalibrateTriggers(String triggerType, String cropTipId,
			String modifiedBy, Date transmissionDate, String tipNumber,
			String peopleId) {
		String triggerId = cropTipId + tipNumber + peopleId;
		log.info("Inside PushAlertService -> recalibrateTriggers");
		log.debug("triggerType: " + triggerType);
		log.debug("triggerId: " + triggerId);
		log.debug("transmissionDate: " + transmissionDate);
		TriggerKey triggerKey = triggerKey(triggerId);
		try {
			if ((Utils.scheduler != null)
					&& (Utils.scheduler.checkExists(triggerKey))) {
				log.info("Trigger found for " + triggerId);
				CropTipMaster tipMaster = tipMasterRepo.findOne(
						CropTipMaster.class, cropTipId);
				Date startDate = tipMaster.getStartDate();
				log.debug("StartDate: " + startDate);
				Date endDate = tipMaster.getEndDate();
				log.debug("EndDate: " + endDate);

				CropTipDetailPK id = new CropTipDetailPK();
				id.setCropTipId(cropTipId);
				id.setTipNo(tipNumber);
				CropTipDetail tip = tipDetailRepo.findOne(CropTipDetail.class,
						id);
				Time transmissionTime = tip.getTransmissionTime();
				if (transmissionDate == null) {
					Date lDate = null;
					if (triggerType.equalsIgnoreCase("AUTO")) {
						lDate = tip.getTransmissionDate();
					} else {
						lDate = new Date();
					}
					Date todayDate = new Date();
					if (lDate.equals(todayDate) || lDate.before(todayDate)) {
						Date tempDate = addInterval(todayDate, "Days", 1);
						transmissionDate = Utils
								.getFormatedDateTimeStamp1(convertDateToString1(tempDate)
										+ " "
										+ convertTimeToString(transmissionTime));
						log.debug("Transmission Date: " + transmissionDate);
						if (isInRange(startDate, endDate, transmissionDate)) {
							updateTrigger(cropTipId, tipNumber, modifiedBy,
									transmissionDate, peopleId, triggerType);
						} else {
							log.info("Deleting trigger because transmission date is out of start and end date range");
							deleteTigger(triggerId);
						}
					} else {
						TriggerHandler handler = TriggerHandler.getInstance();
						TriggerDetails triggerDetails = new TriggerDetails();
						triggerDetails.setTriggerId(triggerId);
						handler.reactivateTrigger(triggerDetails);
					}
				} else {
					transmissionDate = addInterval(transmissionDate,
							tip.getIntervalMeasure(), tip.getIntervalPeriod());
					log.debug("Transmission Date: " + transmissionDate);
					if (isInRange(startDate, endDate, transmissionDate)) {
						updateTrigger(cropTipId, tipNumber, modifiedBy,
								transmissionDate, peopleId, triggerType);
					} else {
						log.info("Deleting trigger because transmission date is out of start and end date range");
						deleteTigger(triggerId);
					}
				}
			} else {
				log.info("No trigger found for " + triggerId);
			}
		} catch (SchedulerException e) {
			log.error(Utils.getStackTrace(e));
		}
		return transmissionDate;
	}

	private void updateTrigger(String cropTipId, String tipNumber,
			String modifiedBy, Date transmissionDate, String peopleId,
			String triggerType) {
		String triggerId = cropTipId + tipNumber + peopleId;
		TriggerHandler handler = TriggerHandler.getInstance();
		if (triggerType.equalsIgnoreCase(peopleId)) {
			tipDetailRepo.updateCropTip(cropTipId, tipNumber, transmissionDate,
					modifiedBy);
		}
		TriggerDetails triggerDetails = new TriggerDetails();
		triggerDetails.setTriggerId(triggerId);
		handler.deleteTrigger(triggerDetails);
		triggerDetails.setFlag(false);
		triggerDetails.setStartDate(transmissionDate);
		triggerDetails.setFrequency("once");
		handler.activateTrigger(PushAlertJob.class, triggerDetails);
	}

	private boolean isInRange(Date startDate, Date endDate,
			Date transmissionDate) {
		boolean result = false;
		if ((startDate.before(transmissionDate) || startDate
				.equals(transmissionDate))
				&& (endDate.after(transmissionDate) || endDate
						.after(transmissionDate))) {
			result = true;
		}
		return result;
	}

	private void deleteTigger(String triggerId) {
		TriggerHandler handler = TriggerHandler.getInstance();
		TriggerDetails triggerDetails = new TriggerDetails();
		triggerDetails.setTriggerId(triggerId);
		handler.deleteTrigger(triggerDetails);
	}

	public Enum<ERROR_CODE> registerPeople(String tipCode, String peopleId,
			String createdBy) {
		log.info("Inside PushAlertService -> registerPeople");
		log.info("TipCode: " + tipCode);
		log.info("PeopleId: " + peopleId);

		CropTipMaster tipMaster = tipMasterRepo
				.queryCropTipByAlertCode(tipCode);
		if (tipMaster == null) {
			log.debug("No tip found");
			ERROR = ERROR_CODE.ES_NR_001;
		} else {
			try {
				String tipId = tipMaster.getCropTipId();
				List<CropTipDetail> tipDetails = tipDetailRepo
						.findAllCropDetails(tipId);
				int i = 1;
				Date transmissionDate = null;
				TriggerHandler handler = TriggerHandler.getInstance();
				for (CropTipDetail tip : tipDetails) {
					PushAlertMasterPK id = new PushAlertMasterPK();
					id.setPeopleId(peopleId);
					id.setPushAlertId(tipId + i);
					PushAlertMaster entity = new PushAlertMaster();
					entity.setAlertCode(tipId);
					entity.setAlertState(tipMaster.getAlertState());
					entity.setCreatedBy(createdBy);
					entity.setCreatedTs(new Date());
					entity.setCurrencyId(tipMaster.getCurrencyId());
					entity.setDataStatus(tipMaster.getAlertState());
					entity.setEndDate(tipMaster.getEndDate());
					entity.setFreqFlag("O");
					entity.setId(id);
					entity.setIsPublic("Y");
					entity.setMessageType("CT");
					entity.setName(tipMaster.getCropTipName());
					entity.setNoOfMessages(tip.getNoOfMessages());
					entity.setPayeeAccountNo(tipMaster.getPayeeAccount());
					entity.setPayeeNetworkId(tipMaster.getNetworkId());
					entity.setPayeeType(tipMaster.getPayeeType());
					entity.setScheduleTime(tip.getTransmissionTime());
					entity.setStartDate(tipMaster.getStartDate());
					entity.setText(tip.getText());
					masterRepo.save(entity);
					log.debug("Record added into push_alert_master with peopleId: "
							+ peopleId + " and tipId: " + tipId + i);

					if (i == 1) {
						Date tempDate = addInterval(new Date(), "Days", 1);
						transmissionDate = Utils
								.getFormatedDateTimeStamp1(convertDateToString1(tempDate)
										+ " "
										+ convertTimeToString(tip
												.getTransmissionTime()));
						log.debug("Transmission Date: " + transmissionDate);
					} else {
						transmissionDate = addInterval(transmissionDate,
								tip.getIntervalMeasure(),
								tip.getIntervalPeriod());
						log.debug("Transmission Date: " + transmissionDate);
					}
					TriggerDetails triggerDetails = new TriggerDetails();
					triggerDetails.setTriggerId(tipId + i + peopleId);
					handler.deleteTrigger(triggerDetails);
					triggerDetails.setFlag(false);
					triggerDetails.setStartDate(transmissionDate);
					triggerDetails.setFrequency("once");
					handler.activateTrigger(PushAlertJob.class, triggerDetails);
					if (tipMaster.getAlertState().equalsIgnoreCase("S")) {
						handler.deactivateTrigger(triggerDetails);
					}
					log.debug("Trigger created with triggerId: " + tipId + i
							+ peopleId);
					i++;
				}
			} catch (DomainException e) {
				ERROR = e.getCode();
			}
			ERROR = ERROR_CODE.ES_SC_001;
		}
		return ERROR;
	}

	private String getSenderId(String networkId, String application) {
		log.info("Inside PushAlertService -> getSenderId");
		log.debug("NetworkId: " + networkId);
		log.debug("Application: " + application);
		String senderId = null;
		List<SenderDetail> senderList = senderRepo.querySenderDetail(networkId,
				application);
		if (senderList.size() > 1 || senderList.size() == 0) {
			senderList.clear();
			senderList = senderRepo.querySenderDetail(networkId, ALL);
			if (senderList.size() > 0) {
				log.info("Found senderId for " + ALL);
				SenderDetail detail = senderList.get(0);
				senderId = detail.getId().getSenderId();
			} else {
				log.info("No senderId found, using Esoko");
				senderId = Utils.senderId;
			}
		} else {
			log.info("Found senderId for " + application);
			SenderDetail detail = senderList.get(0);
			senderId = detail.getId().getSenderId();
		}
		return senderId;
	}
}