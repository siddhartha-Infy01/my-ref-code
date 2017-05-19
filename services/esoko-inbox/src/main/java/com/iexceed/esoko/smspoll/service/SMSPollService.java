package com.iexceed.esoko.smspoll.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertRecipientRepo;
import com.iexceed.esoko.domain.dao3.SenderDetailRepo;
import com.iexceed.esoko.domain.dao4.PollAliaRepo;
import com.iexceed.esoko.domain.dao4.PollDetailRepo;
import com.iexceed.esoko.domain.dao4.PollKeywordRepo;
import com.iexceed.esoko.domain.dao4.PollMasterRepo;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMasterPK;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.domain.entity3.PushAlertRecipientPK;
import com.iexceed.esoko.domain.entity3.SenderDetail;
import com.iexceed.esoko.domain.entity4.PollAlia;
import com.iexceed.esoko.domain.entity4.PollAliaPK;
import com.iexceed.esoko.domain.entity4.PollDetail;
import com.iexceed.esoko.domain.entity4.PollDetailPK;
import com.iexceed.esoko.domain.entity4.PollKeyword;
import com.iexceed.esoko.domain.entity4.PollKeywordPK;
import com.iexceed.esoko.domain.entity4.PollMaster;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.smspoll.ALLPOLLS;
import com.iexceed.esoko.jaxb.smspoll.CreatePollReq;
import com.iexceed.esoko.jaxb.smspoll.CreatePollRes;
import com.iexceed.esoko.jaxb.smspoll.Header;
import com.iexceed.esoko.jaxb.smspoll.KEYWORDS;
import com.iexceed.esoko.jaxb.smspoll.POLLCRDTL;
import com.iexceed.esoko.jaxb.smspoll.POLLDTLS;
import com.iexceed.esoko.jaxb.smspoll.POLLID;
import com.iexceed.esoko.jaxb.smspoll.PollActionDTL;
import com.iexceed.esoko.jaxb.smspoll.PollActionReq;
import com.iexceed.esoko.jaxb.smspoll.PollActionRes;
import com.iexceed.esoko.jaxb.smspoll.QueryAllPollsRes;
import com.iexceed.esoko.jaxb.smspoll.QueryPollRes;
import com.iexceed.esoko.jaxb.smspoll.RECIPIENTS;
import com.iexceed.esoko.jaxb.smspoll.ValidateKeywordRes;
import com.iexceed.esoko.sch.job.PushAlertJob;
import com.iexceed.esoko.sch.trg.TriggerDetails;
import com.iexceed.esoko.sch.trg.TriggerHandler;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class SMSPollService {

	private final static String ALL = "All";
	private final static String POLLS = "Polls";

	private static Logger log = LoggerUtils.getLogger();
	@Autowired
	private PollKeywordRepo keywordRepo;
	@Autowired
	private PollMasterRepo masterRepo;
	@Autowired
	private PollAliaRepo aliasRepo;
	@Autowired
	private PollDetailRepo detailRepo;
	@Autowired
	private EsokoAppRepo esokoRepo;
	@Autowired
	private PushAlertRecipientRepo recpRepo;
	@Autowired
	private SenderDetailRepo senderRepo;
	@Autowired
	private PushAlertMasterRepo alertMasterRepo;
	private String serviceName = "SMSPollService";

	@Transactional
	public CreatePollRes createPoll(CreatePollReq req) {
		log.info("Inside SMSPollService -> createPoll");
		Enum<ERROR_CODE> errorCode = null;
		CreatePollRes res = new CreatePollRes();
		POLLCRDTL pollDtl = req.getPOLLCRDTL();
		String requestType = pollDtl.getRequestType();
		String pollId = "";
		boolean proceed = true;
		if (requestType.equalsIgnoreCase("EDIT")) {
			pollId = pollDtl.getPollId();
			PollMaster exists = masterRepo.findOne(PollMaster.class, pollId);
			if (exists == null) {
				proceed = false;
				errorCode = ERROR_CODE.ES_NR_001;
				log.info("No record found");
			} else {
				if (!exists.getPollName().equals(pollDtl.getPollName())) {
					exists = null;
					exists = masterRepo.getPollWithName(pollDtl.getPollName());
					if (exists != null) {
						errorCode = ERROR_CODE.POLL_ES_001;
						log.info("Poll name already in use");
						proceed = false;
					}
				}
			}
		} else {
			PollMaster exists = masterRepo.getPollWithName(pollDtl
					.getPollName());
			if (exists != null) {
				errorCode = ERROR_CODE.POLL_ES_001;
				log.info("Poll name already in use");
				proceed = false;
			} else {
				pollId = esokoRepo.getSequenceNumber() + "";
			}
		}
		if (proceed) {
			try {
				PollMaster entity = new PollMaster();
				if (requestType.equalsIgnoreCase("EDIT")) {
					PollMaster prevEntity = masterRepo.findOne(
							PollMaster.class, pollId);
					entity.setCreatedBy(prevEntity.getCreatedBy());
					entity.setCreatedTs(prevEntity.getCreatedTs());
					entity.setModifiedBy(req.getHeader().getUserId());
					entity.setModifiedTs(new Date());
					entity.setPollState(prevEntity.getPollState());
				} else {
					entity.setCreatedBy(req.getHeader().getUserId());
					entity.setCreatedTs(new Date());
					entity.setPollState("A");
				}
				entity.setCurrencyId(pollDtl.getCurrencyId());
				entity.setInterpret(pollDtl.getInterpret());
				entity.setNetworkId(pollDtl.getNetworkId());
				entity.setPayeeAccNo(pollDtl.getPayeeAccNo());
				entity.setPayeeType(pollDtl.getPayeeType());
				entity.setPollCode(pollDtl.getPollCode());
				entity.setPollId(pollId);
				entity.setPollName(pollDtl.getPollName());
				entity.setPublicKeyword(pollDtl.getPublicKeyword());
				entity.setReminder(pollDtl.getReminderMsg());
				entity.setSendReminder(Integer.valueOf(pollDtl
						.getReminderAfter()));

				entity.setStopInterval(Integer.valueOf(pollDtl
						.getStopInterval()));
				entity.setStopMeasure(pollDtl.getStopMeasure());
				entity.setFrequency(pollDtl.getFrequency());

				TriggerDetails triggerDtls = new TriggerDetails();
				triggerDtls.setTriggerId(pollId);
				triggerDtls.setFlag(false);

				if (pollDtl.getFrequency().equalsIgnoreCase("N")) {
					entity.setRepeatFlag("");
					entity.setScheduleDays("");
					entity.setStartDate(new Date());
					entity.setEndDate(new Date());
					Date dt = new Date();
					Time startTime = new Time(dt.getTime() + (2 * 60 * 1000));
					entity.setScheduleTime(startTime);

					Date startDate = new Date(startTime.getTime());
					triggerDtls.setStartDate(startDate);
					triggerDtls.setFrequency("once");

				} else {
					entity.setStartDate(Utils.getFormatedDate4(pollDtl
							.getStartDate()));
					Date date = Utils.getFormatedDateTimeStamp1(pollDtl
							.getStartDate() + " " + pollDtl.getScheduleTime());
					Time time = new Time(date.getTime());
					entity.setScheduleTime(time);
					if (pollDtl.getFrequency().equalsIgnoreCase("O")) {
						entity.setEndDate(Utils.getFormatedDate4(pollDtl
								.getStartDate()));
						entity.setRepeatFlag("");
						entity.setScheduleDays("");

						Date dt = Utils.getFormatedDateTimeStamp1(pollDtl
								.getStartDate()
								+ " "
								+ pollDtl.getScheduleTime());
						triggerDtls.setStartDate(dt);
						triggerDtls.setFrequency("once");

					} else {
						entity.setEndDate(Utils.getFormatedDate4(pollDtl
								.getEndDate()));
						entity.setRepeatFlag(pollDtl.getRepeat());
						entity.setScheduleDays(pollDtl.getScheduleDays());

						triggerDtls.setStartTime(time.toLocalTime());
						triggerDtls.setStartDate(entity.getStartDate());
						triggerDtls.setFrequency("recurring");
						triggerDtls.setEndDate(entity.getEndDate());
						if (pollDtl.getRepeat().equalsIgnoreCase("W")) {
							triggerDtls.setRepeats("weekly");
						} else {
							triggerDtls.setRepeats("monthly");
						}
						triggerDtls.setScheduleDays(pollDtl.getScheduleDays());
					}
				}
				entity.setPollActiveTill(addInterval(entity.getEndDate(),
						entity.getStopMeasure(), entity.getStopInterval()));
				masterRepo.merge(entity);

				PushAlertMasterPK PK3 = new PushAlertMasterPK();
				PK3.setPeopleId("");
				PK3.setPushAlertId(pollId);
				PushAlertMaster entity1 = new PushAlertMaster();
				if (requestType.equalsIgnoreCase("EDIT")) {
					PushAlertMaster prevEntity = alertMasterRepo.findOne(
							PushAlertMaster.class, PK3);
					entity1.setAlertState(prevEntity.getAlertState());
					entity1.setDataStatus(prevEntity.getDataStatus());
					entity1.setCreatedBy(prevEntity.getCreatedBy());
					entity1.setCreatedTs(prevEntity.getCreatedTs());
					entity1.setModifiedBy(req.getHeader().getUserId());
					entity1.setModifiedTs(new Date());
				} else {
					entity1.setAlertState("A");
					entity1.setDataStatus("A");
					entity1.setCreatedBy(req.getHeader().getUserId());
					entity1.setCreatedTs(new Date());
				}
				entity1.setAlertCode(pollDtl.getPublicKeyword());
				entity1.setCurrencyId(pollDtl.getCurrencyId());
				entity1.setEndDate(entity.getEndDate());
				entity1.setFreqFlag(pollDtl.getFrequency());
				entity1.setId(PK3);
				if (pollDtl.getPublicKeyword() == null) {
					entity1.setIsPublic("N");
				} else {
					entity1.setIsPublic("Y");
				}
				entity1.setMessageType("PO");
				entity1.setName(pollDtl.getPollName());
				entity1.setNeverEndFlag("N");
				entity1.setNoOfMessages(Integer.valueOf(pollDtl.getPOLLDTLS()
						.get(0).getNuOfMsg()));
				entity1.setPayeeAccountNo(pollDtl.getPayeeAccNo());
				entity1.setPayeeNetworkId(pollDtl.getNetworkId());
				entity1.setPayeeType(pollDtl.getPayeeType());
				entity1.setRepeatsFlag(pollDtl.getRepeat());
				if (pollDtl.getFrequency().equalsIgnoreCase("R")) {
					entity1.setSchedule("Y");
					entity1.setScheduleDays(pollDtl.getScheduleDays());
				}
				entity1.setScheduleTime(entity.getScheduleTime());
				entity1.setSenderId(getSenderId(pollDtl.getNetworkId(), POLLS));
				entity1.setStartDate(entity.getStartDate());
				entity1.setText(pollDtl.getPOLLDTLS().get(0).getQuetion());
				alertMasterRepo.merge(entity1);

				TriggerHandler handler = TriggerHandler.getInstance();
				if (requestType.equalsIgnoreCase("SAVE")) {
					handler.activateTrigger(PushAlertJob.class, triggerDtls);
				} else {
					handler.updateTrigger(PushAlertJob.class, triggerDtls);
				}
				if (entity1.getAlertState().equalsIgnoreCase("S")) {
					handler.deactivateTrigger(triggerDtls);
				}

				List<PushAlertRecipient> pARecpList = recpRepo
						.findAllPushAlertRecipients(pollId);
				recpRepo.delete(pARecpList);
				List<RECIPIENTS> recpntList = pollDtl.getRECIPIENTS();
				for (RECIPIENTS recpnt : recpntList) {
					PushAlertRecipientPK PK = new PushAlertRecipientPK();
					String parentId = recpnt.getParentId();
					if (parentId == null) {
						PK.setParentId("");
					} else {
						PK.setParentId(parentId);
					}
					PK.setPushAlertId(pollId);
					PK.setRecipientId(recpnt.getRecipientId());
					PushAlertRecipient lEntity = new PushAlertRecipient();
					lEntity.setId(PK);
					lEntity.setRecipientType(recpnt.getRecipientType());
					recpRepo.save(lEntity);
				}

				List<PollDetail> pollDtlsList = detailRepo
						.getPollDetailsForPollId(pollId);
				detailRepo.delete(pollDtlsList);

				List<PollKeyword> pKeywordLst = keywordRepo
						.getPollKeywordsForPollId(pollId);
				keywordRepo.delete(pKeywordLst);

				List<PollAlia> pAliasLst = aliasRepo
						.getPollAliasForPollId(pollId);
				aliasRepo.delete(pAliasLst);

				List<POLLDTLS> pollDtlList = pollDtl.getPOLLDTLS();
				for (POLLDTLS detail : pollDtlList) {
					PollDetailPK PK = new PollDetailPK();
					PK.setPollId(pollId);
					PK.setQuesNo(detail.getQuesNo());
					PollDetail lEntity = new PollDetail();
					String correct = detail.getCorrectGoTo();
					if (correct == null) {
						lEntity.setCorrectGoTo("0");
					} else if (correct.equals("")) {
						lEntity.setCorrectGoTo("0");
					} else {
						lEntity.setCorrectGoTo(correct);
					}
					lEntity.setCreatedBy(req.getHeader().getUserId());
					lEntity.setCreatedTs(new Date());
					lEntity.setId(PK);
					String incorrect = detail.getInCorrectGoTo();
					if (incorrect == null) {
						lEntity.setIncorrectGoTo("0");
					} else if (incorrect.equals("")) {
						lEntity.setIncorrectGoTo("0");
					} else {
						lEntity.setIncorrectGoTo(incorrect);
					}
					lEntity.setNoOfChar(Integer.valueOf(detail.getNuOfChar()));
					lEntity.setNoOfMsg(Integer.valueOf(detail.getNuOfMsg()));
					lEntity.setPollQues(detail.getQuetion());
					detailRepo.save(lEntity);

					List<KEYWORDS> reqKeywrdLst = detail.getKEYWORDS();
					for (KEYWORDS keyword : reqKeywrdLst) {
						PollKeywordPK PK1 = new PollKeywordPK();
						PK1.setKeyword(keyword.getKeyword());
						PK1.setPollId(pollId);
						PK1.setPollQuesNo(detail.getQuesNo());
						if (!keywordRepo.exists(PollKeyword.class, PK1)) {
							PollKeyword lEntity1 = new PollKeyword();
							lEntity1.setCreatedBy(req.getHeader().getUserId());
							lEntity1.setCreatedTs(new Date());
							lEntity1.setId(PK1);
							keywordRepo.save(lEntity1);
							PK1 = null;
							lEntity1 = null;
							String[] aliasList = keyword.getAlias().split(",");
							for (String alias : aliasList) {
								PollAliaPK PK2 = new PollAliaPK();
								PK2.setAlias(alias);
								PK2.setKeyword(keyword.getKeyword());
								PK2.setPollId(pollId);
								if (!aliasRepo.exists(PollAlia.class, PK2)) {
									PollAlia lEntity2 = new PollAlia();
									lEntity2.setCreatedBy(req.getHeader()
											.getUserId());
									lEntity2.setCreatedTs(new Date());
									lEntity2.setId(PK2);
									aliasRepo.save(lEntity2);
								}
							}
						}

					}

				}

				if (requestType.equalsIgnoreCase("EDIT")) {
					errorCode = ERROR_CODE.POLL_ES_003;
					log.info("Poll edited successfully");
				} else {
					errorCode = ERROR_CODE.POLL_ES_002;
					log.info("Poll created successfully");
				}
			} catch (DomainException e) {
				errorCode = e.getCode();
				log.error(Utils.getStackTrace(e));
			}
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SMSPOLL,
				serviceName, "createPoll", req.getHeader().getUserId(),
				errorCode);
		res.setHeader(header);
		return res;
	}

	public QueryPollRes queryPoll(String pollId) {
		log.info("Inside SMSPollService -> queryPoll");
		Enum<ERROR_CODE> errorCode = null;
		QueryPollRes res = new QueryPollRes();
		PollMaster entity = masterRepo.findOne(PollMaster.class, pollId);
		if (entity == null) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		} else {
			POLLCRDTL respEntity = new POLLCRDTL();
			respEntity.setCurrencyId(entity.getCurrencyId());
			respEntity.setEndDate(convertDateToString(entity.getEndDate()));
			respEntity.setFrequency(entity.getFrequency());
			respEntity.setInterpret(entity.getInterpret());
			respEntity.setNetworkId(entity.getNetworkId());
			respEntity.setPayeeAccNo(entity.getPayeeAccNo());
			respEntity.setPayeeType(entity.getPayeeType());
			respEntity.setPollCode(entity.getPollCode());
			respEntity.setPollId(entity.getPollId());
			respEntity.setPollName(entity.getPollName());
			respEntity.setPublicKeyword(entity.getPublicKeyword());
			respEntity.setReminderAfter(entity.getSendReminder() + "");
			respEntity.setReminderMsg(entity.getReminder());
			respEntity.setRepeat(entity.getRepeatFlag());
			respEntity.setRequestType("EDIT");
			respEntity.setScheduleDays(entity.getScheduleDays());
			respEntity.setScheduleTime(convertTimeToString(entity
					.getScheduleTime()));
			respEntity.setStartDate(convertDateToString(entity.getStartDate()));
			respEntity.setStopInterval(entity.getStopInterval() + "");
			respEntity.setStopMeasure(entity.getStopMeasure());

			List<PushAlertRecipient> recpList = recpRepo
					.findAllPushAlertRecipients(pollId);
			List<RECIPIENTS> respRecpList = new ArrayList<RECIPIENTS>();
			for (PushAlertRecipient recipient : recpList) {
				RECIPIENTS recp = new RECIPIENTS();
				recp.setParentId(recipient.getId().getParentId());
				recp.setRecipientId(recipient.getId().getRecipientId());
				recp.setRecipientType(recipient.getRecipientType());
				respRecpList.add(recp);
			}
			respEntity.getRECIPIENTS().addAll(respRecpList);

			List<PollDetail> pollDtlList = detailRepo
					.getPollDetailsForPollId(pollId);
			List<POLLDTLS> respDtlLst = new ArrayList<POLLDTLS>();
			for (PollDetail detail : pollDtlList) {
				POLLDTLS respEntity2 = new POLLDTLS();
				respEntity2.setCorrectGoTo(detail.getCorrectGoTo());
				respEntity2.setInCorrectGoTo(detail.getIncorrectGoTo());
				respEntity2.setNuOfChar(detail.getNoOfChar() + "");
				respEntity2.setNuOfMsg(detail.getNoOfMsg() + "");
				respEntity2.setQuesNo(detail.getId().getQuesNo());
				respEntity2.setQuetion(detail.getPollQues());

				List<PollKeyword> keywordLst = keywordRepo
						.getPollKeywordsForQues(pollId, detail.getId()
								.getQuesNo());
				List<KEYWORDS> respKwList = new ArrayList<KEYWORDS>();
				for (PollKeyword keyword : keywordLst) {
					List<PollAlia> aliasList = aliasRepo
							.getPollAliasForKeyword(pollId, keyword.getId()
									.getKeyword());
					StringBuilder alias = new StringBuilder("");
					for (PollAlia lAlias : aliasList) {
						if (alias.toString().equals("")) {
							alias.append(lAlias.getId().getAlias());
						} else {
							alias.append("," + lAlias.getId().getAlias());
						}
					}
					KEYWORDS respEntity1 = new KEYWORDS();
					respEntity1.setKeyword(keyword.getId().getKeyword());
					respEntity1.setAlias(alias.toString());
					respKwList.add(respEntity1);
				}
				respEntity2.getKEYWORDS().addAll(respKwList);

				respDtlLst.add(respEntity2);
			}
			respEntity.getPOLLDTLS().addAll(respDtlLst);
			res.setPOLLCRDTL(respEntity);
			errorCode = ERROR_CODE.ES_SC_001;
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SMSPOLL,
				serviceName, "queryPoll", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private String convertDateToString(Date dt) {
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

	public QueryAllPollsRes queryAllPolls(String pollType, String sortBy) {
		log.info("Inside SMSPollService -> queryAllPolls");
		QueryAllPollsRes res = new QueryAllPollsRes();
		Enum<ERROR_CODE> errorCode = null;
		List<Map<String, Object>> entityList = masterRepo.queryAllPolls(
				pollType, sortBy);
		if (entityList.size() == 0) {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		} else {
			errorCode = ERROR_CODE.ES_SC_001;
			List<ALLPOLLS> respList = new ArrayList<ALLPOLLS>();
			for (Map<String, Object> entity : entityList) {
				ALLPOLLS poll = new ALLPOLLS();
				poll.setEndDate(Utils.getFormatedDate1((Date) entity
						.get("endDate")));
				poll.setParticipants(entity.get("participants").toString());
				poll.setPollId(entity.get("pollId").toString());
				poll.setPollName(entity.get("pollName").toString());
				poll.setPollQues(entity.get("pollQues").toString());
				if (entity.get("pollState").toString().equalsIgnoreCase("S")) {
					poll.setPollState("suspended");
				} else {
					poll.setPollState("");
				}
				poll.setResponses(entity.get("responses").toString());
				poll.setStartDate(Utils.getFormatedDate1((Date) entity
						.get("startDate")));
				respList.add(poll);
			}
			res.getALLPOLLS().addAll(respList);
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SMSPOLL,
				serviceName, "queryAllPolls", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private Date addInterval(Date currDate, String measure, int interval) {
		Calendar dt = Calendar.getInstance();
		dt.setTime(currDate);
		Date finalDate = null;
		if (measure.equalsIgnoreCase("Days")) {
			dt.add(Calendar.DAY_OF_WEEK, interval);
			finalDate = new Date(dt.getTimeInMillis());
		} else if (measure.equalsIgnoreCase("Months")) {
			dt.add(Calendar.MONTH, interval);
			finalDate = new Date(dt.getTimeInMillis());
		}
		return finalDate;
	}

	@Transactional
	public PollActionRes pollAction(PollActionReq req) {
		log.info("Inside SMSPollService -> pollAction");
		PollActionRes res = new PollActionRes();
		Enum<ERROR_CODE> errorCode = null;
		PollActionDTL pollDetail = req.getPollActionDTL();
		String action = pollDetail.getPollAction();
		List<POLLID> pollList = pollDetail.getPOLLID();
		TriggerHandler handler = TriggerHandler.getInstance();
		for (POLLID poll : pollList) {
			String pollId = poll.getPollId();
			TriggerDetails triggerDetails = new TriggerDetails();
			triggerDetails.setTriggerId(pollId);
			if (action.equalsIgnoreCase("activate")) {
				masterRepo.updatePollState(pollId, req.getHeader().getUserId(),
						"A");
				alertMasterRepo.updatePushAlertStatus(pollId, req.getHeader()
						.getUserId(), "A");
				handler.reactivateTrigger(triggerDetails);
			} else if (action.equalsIgnoreCase("suspend")) {
				masterRepo.updatePollState(pollId, req.getHeader().getUserId(),
						"S");
				alertMasterRepo.updatePushAlertStatus(pollId, req.getHeader()
						.getUserId(), "S");
				handler.deactivateTrigger(triggerDetails);
			} else {
				PollMaster entity1 = masterRepo.findOne(PollMaster.class,
						pollId);
				if (entity1 != null) {
					masterRepo.delete(entity1);
				}
				PushAlertMasterPK entity2PK = new PushAlertMasterPK();
				entity2PK.setPeopleId("");
				entity2PK.setPushAlertId(pollId);
				PushAlertMaster entity2 = alertMasterRepo.findOne(
						PushAlertMaster.class, entity2PK);
				if (entity2 != null) {
					alertMasterRepo.delete(entity2);
				}

				List<PollKeyword> pKeywordLst = keywordRepo
						.getPollKeywordsForPollId(pollId);
				keywordRepo.delete(pKeywordLst);
				List<PollAlia> pAliasLst = aliasRepo
						.getPollAliasForPollId(pollId);
				aliasRepo.delete(pAliasLst);
				List<PollDetail> pollDtlsList = detailRepo
						.getPollDetailsForPollId(pollId);
				detailRepo.delete(pollDtlsList);
				List<PushAlertRecipient> pARecpList = recpRepo
						.findAllPushAlertRecipients(pollId);
				recpRepo.delete(pARecpList);
				handler.deleteTrigger(triggerDetails);
			}
		}
		if (action.equalsIgnoreCase("activate")) {
			errorCode = ERROR_CODE.POLL_ES_004;
		} else if (action.equalsIgnoreCase("suspend")) {
			errorCode = ERROR_CODE.POLL_ES_005;
		} else {
			errorCode = ERROR_CODE.POLL_ES_006;
		}

		Header header = (Header) HeaderFactory.getHeader(HeaderType.SMSPOLL,
				serviceName, "pollAction", "", errorCode);
		res.setHeader(header);
		return res;
	}
	
	/**
	 * @param id optional
	 * @param networkId mandatory
	 * @param keyword mandatory
	 * @param channel mandatory if appName is 'KEYWORD'
	 * @param appName mandatory for Keyword screen, use 'KEYWORD' as appName
	 * @return
	 */
	public ValidateKeywordRes validateKeywords(String id, String networkId,
			String keyword, String channel, String appName) {
		log.info("Inside SMSPollService -> validateKeywords");
		ValidateKeywordRes res = new ValidateKeywordRes();
		Enum<ERROR_CODE> errorCode = null;
		if (keywordRepo.validatedKeyword(id, networkId, keyword, channel,
				appName)) {
			errorCode = ERROR_CODE.POLL_ES_008;
		} else {
			errorCode = ERROR_CODE.POLL_ES_007;
		}
		HeaderFactory.setExtraParamMap("$1", keyword);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SMSPOLL,
				serviceName, "validateKeywords", "", errorCode);
		res.setHeader(header);
		return res;
	}
}
