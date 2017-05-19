package com.iexceed.esoko.sch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.Message_delivary_DetailRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.PushAlertRecipientRepo;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.PushAlertRecipient;
import com.iexceed.esoko.jaxb.people.PEOPLEDTL;
import com.iexceed.esoko.jaxb.people.ViewGroupDetailRes;
import com.iexceed.esoko.people.service.GroupsService;
import com.iexceed.esoko.sch.comp.MessageObjectBean;

@Service
public class SmsCallBackSrvc {

	@Autowired
	Message_delivary_DetailRepo delvRepo;
	@Autowired
	PushAlertSrvc pushAlertService;

	@Transactional
	public boolean populateMessageReponse(Map<String, String> reponse) {

		Message_delivary_Detail deliveryDetail = delvRepo
				.getMessageLogDetails(reponse.get("myId"));
		System.out.println("Message_delivary_Detail -> "
				+ deliveryDetail.toString());
		System.out.println("Status -> " + reponse.get("status"));
		deliveryDetail.setAnswer(reponse.get("answer"));
		deliveryDetail.setMsgStatus(getStatus(reponse.get("answer")).equals(
				"DELIVRD") ? "D" : "F");
		if (!deliveryDetail.getMsgStatus().equalsIgnoreCase("D"))
			passReverseAccounting(deliveryDetail);
		delvRepo.save(deliveryDetail);
		return true;
	}

	private String getStatus(String answer) {
		int statindex = answer.indexOf("stat:");
		return answer.substring(statindex + 5, statindex + 12);
	}

	private boolean passReverseAccounting(Message_delivary_Detail deliveryDetail) {
		List<MessageObjectBean> beans = pushAlertService
				.getMessageObjects(deliveryDetail.getScheduleId());
		for (MessageObjectBean bean : beans)
			bean.setReversal(true);
		pushAlertService.processMessages(beans);
		return true;
	}

}
