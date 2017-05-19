package com.iexceed.esoko.sch.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.System_transactionRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.AlertSourceNetworkRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertRecipientRepo;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.AlertSourceNetwork;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMasterPK;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class PushAlertBridge implements IPushAlertBridge {

	@Autowired
	PushAlertMasterRepo masterRepo;

	@Autowired
	AlertProfileBasedRepo alertProfileBased;

	@Autowired
	PushAlertRecipientRepo pushAlertRecepient;

	@Autowired
	GroupMasterRepo groupMasterRepo;

	@Autowired
	PeopleRepo peopleRepo;

	@Autowired
	System_transactionRepo system_transactionRepo;

	@Autowired
	ICostBridge costBridge;

	@Autowired
	AlertSourceNetworkRepo alertSourceNetwrok;

	public List<MessageObjectBean> fetchAllScheduled(String triggerId) {
		log.debug("Fetching all the scheduled messages");
		return resolveRecipients(triggerId);
	}

	public boolean isSurchargeable(MessageObjectBean bean) {
		if (getContributingNetworks(bean.getAlertId()).size() > 1
				&& getContributingNetworks(bean.getAlertId()).contains(
						bean.getNetworkId())) {
			bean.setSurchargeable(true);
			bean.setDescription(getContributingNetworks(bean.getAlertId())
					.toString());
			return true;
		} else {
			bean.setSurchargeable(false);
			return false;
		}

	}

	public List<AlertSourceNetwork> getContributingNetworks(String alertId) {
		return alertSourceNetwrok.findAllAlertNetworks(alertId);
	}

	private List<MessageObjectBean> resolveRecipients(String triggerId) {
		log.debug("Started Resolving Messages");
		PushAlertMasterPK pk = new PushAlertMasterPK();
		pk.setPeopleId("");
		pk.setPushAlertId(triggerId);
		PushAlertMaster alertMaster = masterRepo.getAlertBasedonTrigger(pk);
		log.debug("Push Alert Master Records" + alertMaster.toString());
		List<MessageObjectBean> beanList = null;

		if (!isProfileBased(alertMaster)) {
			log.debug("It is not a profile based alert");
			beanList = new ArrayList<MessageObjectBean>();
			List<PushAlertRecipient> alertRecipients = pushAlertRecepient
					.findAllPushAlertRecipients(triggerId);
			log.debug("Fetched all alert recipients::" + alertRecipients);
			String networkId = alertMaster.getPayeeNetworkId();
			log.debug("Network ID::" + networkId);
			if (alertMaster.getMessageType().equalsIgnoreCase("CT")
					&& alertMaster.getId().getPeopleId() != null) {
				People people = (People) peopleRepo
						.queryPeopleByUserId(alertMaster.getId().getPeopleId());
				log.debug("Creating Message object for::"
						+ people.getFirstName() + people.getLastName());
				MessageObjectBean bean = new MessageObjectBean();
				bean.setAlertId(triggerId);
				bean.setMessageId(generateMessageId());
				bean.setMessageStatus('U');
				bean.setMessage(alertMaster.getText());
				bean.setMsisdn(people.getMsisdn());
				bean.setOperatorId(people.getOperatorId());
				bean.setNetworkId(networkId);
				bean.setFcy(alertMaster.getCurrencyId());
				bean.setNoOfMsgs(alertMaster.getNoOfMessages());
				bean.setPayeeAcNo(alertMaster.getPayeeAccountNo());
				bean.setPayeeType(alertMaster.getPayeeType());
				bean.setType(alertMaster.getMessageType());
				bean.setSenderId(alertMaster.getSenderId());
				bean.setUserId(people.getId().getPeopleId());
				bean.setProfileBased(false);
				costBridge.deriveCostAndRoute(bean);
				log.debug("Composed Bean::" + bean);
				beanList.add(bean);
			} else {
				for (PushAlertRecipient alertRecipient : alertRecipients) {

					log.debug("Recipient Details::" + alertRecipient.toString());
					if (alertRecipient.getRecipientType().equals("G")) {

						log.debug("Recipient Type is Group::"
								+ alertRecipient.getId().getRecipientId());
						log.debug("Get All Prople Under the group::"
								+ alertRecipient.getId().getRecipientId());
						boolean isSmartGrp = peopleRepo.isSmartGroup(
								alertRecipient.getId().getRecipientId(),
								networkId);
						log.debug(alertRecipient.getId().getRecipientId()
								+ "Is a smart Group!!!");
						List<Map<String, Object>> pplLst = null;
						if (isSmartGrp) {
							pplLst = peopleRepo.querySmartGroupsDetail(
									alertRecipient.getId().getRecipientId(),
									networkId);
						} else {
							pplLst = peopleRepo.queryGroupsDetail(
									alertRecipient.getId().getRecipientId(),
									networkId);
						}
						log.debug("Details of people under the group"
								+ pplLst.toString());
						for (Map<String, Object> people : pplLst) {
							log.debug("Creating Message object for::"
									+ people.get("firstName ")
									+ people.get("lastName"));
							MessageObjectBean bean = new MessageObjectBean();
							bean.setAlertId(triggerId);
							bean.setMessageId(generateMessageId());
							bean.setMessageStatus('U');
							bean.setMessage(alertMaster.getText());
							bean.setMsisdn(people.get("msisdn").toString());
							bean.setOperatorId(people.get("finalValue")
									.toString());
							bean.setNetworkId(networkId);
							bean.setFcy(alertMaster.getCurrencyId());
							bean.setNoOfMsgs(alertMaster.getNoOfMessages());
							bean.setPayeeAcNo(alertMaster.getPayeeAccountNo());
							bean.setPayeeType(alertMaster.getPayeeType());
							bean.setType(alertMaster.getMessageType());
							bean.setUserId(people.get("peopleId").toString());
							bean.setSenderId(alertMaster.getSenderId());
							bean.setProfileBased(false);
							costBridge.deriveCostAndRoute(bean);
							log.debug("Composed Bean::" + bean);
							beanList.add(bean);
						}

					} else {
						log.debug("Recipient Type is Individual::"
								+ alertRecipient.getId().getRecipientId());
						log.debug("Get People Details::"
								+ alertRecipient.getId().getRecipientId());
						People people = (People) peopleRepo
								.queryPeopleByUserId(alertRecipient.getId()
										.getRecipientId());
						log.debug("Creating Message object for::"
								+ people.getFirstName() + people.getLastName());
						MessageObjectBean bean = new MessageObjectBean();
						bean.setAlertId(triggerId);
						bean.setMessageId(generateMessageId());
						bean.setMessageStatus('U');
						bean.setMessage(alertMaster.getText());
						bean.setMsisdn(people.getMsisdn());
						bean.setOperatorId(people.getOperatorId());
						bean.setNetworkId(networkId);
						bean.setFcy(alertMaster.getCurrencyId());
						bean.setNoOfMsgs(alertMaster.getNoOfMessages());
						bean.setPayeeAcNo(alertMaster.getPayeeAccountNo());
						bean.setPayeeType(alertMaster.getPayeeType());
						bean.setType(alertMaster.getMessageType());
						bean.setUserId(people.getId().getPeopleId());
						bean.setSenderId(alertMaster.getSenderId());
						bean.setProfileBased(false);
						costBridge.deriveCostAndRoute(bean);
						log.debug("Composed Bean::" + bean);
						beanList.add(bean);

					}

				}
			}

		} else {
			log.debug("It is a profile based alert");
			List<AlertProfileBased> alertProfileBaseds = alertProfileBased
					.findAllAlertProfiles(triggerId);

			for (AlertProfileBased alertProfileBased : alertProfileBaseds) {
				log.debug("Recipient Type is Individual::"
						+ alertProfileBased.getUserId());
				log.debug("Get People Details::"
						+ alertProfileBased.getUserId());
				People people = (People) peopleRepo
						.queryPeopleByUserId(alertProfileBased.getUserId());
				MessageObjectBean bean = new MessageObjectBean();
				bean.setAlertId(triggerId);
				bean.setMessageId(generateMessageId());
				bean.setMessageStatus('U');
				bean.setMessage(alertProfileBased.getText());
				bean.setMsisdn(people.getMsisdn());
				bean.setOperatorId(people.getOperatorId());
				bean.setNetworkId(alertMaster.getPayeeNetworkId());
				bean.setFcy(alertMaster.getCurrencyId());
				bean.setNoOfMsgs(alertMaster.getNoOfMessages());
				bean.setPayeeAcNo(alertMaster.getPayeeAccountNo());
				bean.setPayeeType(alertMaster.getPayeeType());
				bean.setType(alertMaster.getMessageType());
				bean.setUserId(people.getId().getPeopleId());
				bean.setSenderId(alertMaster.getSenderId());
				bean.setProfileBased(true);
				costBridge.deriveCostAndRoute(bean);
				log.debug("Composed Bean::" + bean);
				beanList.add(bean);
			}

		}
		return beanList;
	}

	private boolean isProfileBased(PushAlertMaster alertMaster) {
		log.debug("Check if it is Alert or Profile");
		if ((alertMaster.getWheatherRecepient() != null)
				&& (alertMaster.getWheatherRecepient().equals("P") || alertMaster
						.getPriceRecepient().equals("P"))) {
			log.debug("Profile it is");
			return true;

		} else if ((alertMaster.getWheatherRecepient() != null)
				&& (alertMaster.getWheatherRecepient().equals("A") || alertMaster
						.getPriceRecepient().equals("A"))) {
			log.debug("Alert It is");
			return false;

		} else {
			log.debug("Alert It is");
			return false;
		}
	}

	private String generateMessageId() {
		StringBuffer transaction = new StringBuffer();
		transaction.append("MSG" + Utils.getJulianDt()
				+ Math.round(Math.random() * 100000));
		return transaction.toString();

	}

}
