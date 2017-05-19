package com.iexceed.esoko.sch.comp;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.sms.KannelSMSSender;

@Component
public class SMSBridge implements ISMSBridge {

	public boolean sendSMS(MessageObjectBean bean) {
		KannelSMSSender sender = new KannelSMSSender();
		sender.SendMessage(bean.getMessage(), bean.getMsisdn(),
				bean.getSenderId(), bean.getSmsc(), bean.getMessageId());
		return true;
	}

	public static void main(String[] args) {
		new SMSBridge().sendSMS(null);
	}

}
