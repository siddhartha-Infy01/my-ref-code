package com.iexceed.esoko.sch.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.Message_delivary_DetailRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.PushAlertRecipientRepo;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.domain.entity.Message_delivary_DetailPK;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.people.PEOPLEDTL;
import com.iexceed.esoko.jaxb.people.ViewGroupDetailRes;
import com.iexceed.esoko.people.service.GroupsService;
import com.iexceed.esoko.sch.comp.IAccountingBridge;
import com.iexceed.esoko.sch.comp.ICostBridge;
import com.iexceed.esoko.sch.comp.IPushAlertBridge;
import com.iexceed.esoko.sch.comp.ISMSBridge;
import com.iexceed.esoko.sch.comp.MessageObjectBean;

@Service
public class PushAlertSrvc {

	public static Logger log = LoggerUtils.getSchLogger();

	@Autowired
	IPushAlertBridge pushAlertBridge;

	@Autowired
	IAccountingBridge accountingBridge;

	@Autowired
	ICostBridge costBridge;

	@Autowired
	ISMSBridge smsBridge;

	@Autowired
	Message_delivary_DetailRepo delvRepo;
	@Autowired
	AlertProfileBasedRepo profileBasedRepo;
	@Autowired
	PushAlertRecipientRepo pushAlertRecRepo;
	@Autowired
	GroupsService groupService;

	@Transactional
	public void startPushingmessages(JobExecutionContext ctx) {
		log.debug("Trigger name::" + ctx.getTrigger());
		List<MessageObjectBean> beans = getMessageObjects(ctx.getTrigger()
				.getKey().getName());
		log.debug("Message Objects::" + ctx.getTrigger());
		log.debug("No Of Messages to be sent::" + beans.size());
		processMessages(beans);
		populateLog(beans);
	}

	public void populateLog(List<MessageObjectBean> beans) {
		for (MessageObjectBean bean : beans) {
			Message_delivary_Detail delivary_Detail = new Message_delivary_Detail();
			Message_delivary_DetailPK delivary_DetailPK = new Message_delivary_DetailPK();
			delivary_DetailPK.setTrnRefNo(bean.getTrnRefNo());
			delivary_DetailPK.setMessageId(bean.getMessageId());
			delivary_Detail.setId(delivary_DetailPK);
			delivary_Detail.setMessage(bean.getMessage());
			delivary_Detail.setMessageType(bean.getType());
			delivary_Detail.setMsgCost(new BigDecimal(bean.getMsgCost()
					.getBaseCost()
					+ bean.getMsgCost().getRetailPrice()
					+ bean.getMsgCost().getWholesalePrice()));
			delivary_Detail
					.setMsgStatus(String.valueOf(bean.getMessageStatus()));
			delivary_Detail.setMsisdn(bean.getMsisdn());
			delivary_Detail.setNetworkId(bean.getNetworkId());
			delivary_Detail.setScheduleId(bean.getAlertId());
			delivary_Detail.setRouteId(bean.getOperatorId());
			delivary_Detail.setUserId(bean.getUserId());
			delivary_Detail.setCreatedBy("SYSTEM");
			delivary_Detail.setCreated_TS(Utils.getCurrentDate());
			if (bean.isProfileBased()) {
				repopulateAlertProfileBased(bean.getNetworkId(),
						bean.getAlertId());
			}
			delvRepo.save(delivary_Detail);
		}

	}

	public List<MessageObjectBean> getMessageObjects(String alertId) {
		return pushAlertBridge.fetchAllScheduled(alertId);

	}

	public boolean processMessages(List<MessageObjectBean> beans) {

		for (MessageObjectBean bean : beans) {
			log.debug("Message Content:::" + bean.toString());

			if (pushAlertBridge.isSurchargeable(bean)) {
				log.debug("It is Surchargeable Message:::");
				if (!accountingBridge.doAccounting(bean).getTrnRefNo()
						.equals("")) {
					if (!bean.isReversal()) {
						if (smsBridge.sendSMS(bean)) {
							bean.setMessageStatus('S');
						} else {
							bean.setMessageStatus('F');
							bean.setDescription("Msg Failed at Kannel gateway");
						}
					} else {
						bean.setMessageStatus('F');
						bean.setDescription("Insufficient Balance");
					}
				}

			} else {
				log.debug("It is a Standard Message:::");
				if (!bean.isReversal()) {
					if (!accountingBridge.doAccounting(bean).getTrnRefNo()
							.equals("")) {
						if (smsBridge.sendSMS(bean)) {
							bean.setMessageStatus('S');
						} else {
							bean.setMessageStatus('F');
							bean.setDescription("Msg Failed at Kannel gateway");
						}
					} else {
						bean.setMessageStatus('F');
						bean.setDescription("Insufficient Balance");
					}
				}

			}
		}

		return true;

	}

	public void repopulateAlertProfileBased(String networkId, String alertId) {

		List<PushAlertRecipient> alertRecipientList = findAllPushAlertRecipients(alertId);

		if (alertRecipientList.size() > 0) {
			deleteProfileBased(alertId);
			for (PushAlertRecipient alertRecipient : findAllPushAlertRecipients(alertId)) {
				if (alertRecipient.getRecipientType().equalsIgnoreCase("G")) {
					ViewGroupDetailRes groupDtls = groupService
							.viewGroupDetail(networkId, alertRecipient.getId()
									.getRecipientId());
					List<PEOPLEDTL> listOfPeople = groupDtls.getPEOPLEDTL();
					for (PEOPLEDTL people : listOfPeople) {
						populateProfileBased(alertId, people.getPeopleId());
					}
				}
			}

		}

	}

	public List<PushAlertRecipient> findAllPushAlertRecipients(String alertid) {
		return pushAlertRecRepo.findAllPushAlertRecipients(alertid);
	}

	public void deleteProfileBased(String alertId) {
		List<AlertProfileBased> profileList = profileBasedRepo
				.findAllAlertProfiles(alertId);
		if (profileList.size() != 0) {
			profileBasedRepo.delete(profileList);
		}
	}

	private void populateProfileBased(String alertid, String peopled) {
		AlertProfileBased profileBased = new AlertProfileBased();
		profileBased.setPushAlertId(alertid);
		profileBased.setText("Data Not Available Yet");
		profileBased.setUserId(peopled);
		profileBasedRepo.save(profileBased);
	}
}
