package com.iexceed.esoko.engine.sms;

import java.util.List;

import com.iexceed.sms.KannelProperties;
import com.javacodegeeks.kannel.api.SMSManager;

public class KannelSMSSender {
	public String SendMessage(String smsMessage, String reciepient,
			String sender, String smscid, String MsgId) {
		SMSManager smsManager = SMSManager.getInstance();
		KannelProperties kannelProperties = KannelProperties.getInstance();
		try {
			System.out.println("Sending to host "
					+ kannelProperties.getKannelHost());
			System.out.println("Sending to Port "
					+ kannelProperties.getKannelPort());
			System.out.println("Sending to User "
					+ kannelProperties.getKannelUser());
			System.out.println("Sending to pwd "
					+ kannelProperties.getKannelPassword());
			System.out.println("Sending to dlrmask "
					+ kannelProperties.getDlrMask());
			System.out.println("Sending to dlrurl "
					+ kannelProperties.getDlrUrl() + "%26myId%3D" + MsgId);
			smsManager.setMessagesSendRate(kannelProperties
					.getMsgProcessingRate());
			smsManager.setMessagesPrefetchSize(kannelProperties
					.getMsgPrefetchSize());
			return smsManager.sendSMS(kannelProperties.getKannelHost(),
					kannelProperties.getKannelPort(),
					kannelProperties.getKannelUser(),
					kannelProperties.getKannelPassword(), sender, reciepient,
					smsMessage, null, null, null, null, null,
					kannelProperties.getDlrMask(), kannelProperties.getDlrUrl()
							+ "%26myId%3D" + MsgId, null, null, smscid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Not Sent";
	}

	public String SendMessageToMultipleUsers(String smsMessage,
			List<String> reciepients, String sender, String smscid, String MsgId) {
		SMSManager smsManager = SMSManager.getInstance();
		KannelProperties kannelProperties = KannelProperties.getInstance();
		try {
			System.out.println("Sending to host "
					+ kannelProperties.getKannelHost());
			System.out.println("Sending to Port "
					+ kannelProperties.getKannelPort());
			System.out.println("Sending to User "
					+ kannelProperties.getKannelUser());
			System.out.println("Sending to pwd"
					+ kannelProperties.getKannelPassword());
			System.out.println("Sending to dlrmask "
					+ kannelProperties.getDlrMask());
			System.out.println("Sending to dlrurl "
					+ kannelProperties.getDlrUrl() + "%26myId%3D" + MsgId);

			smsManager.setMessagesSendRate(kannelProperties
					.getMsgProcessingRate());
			smsManager.setMessagesPrefetchSize(kannelProperties
					.getMsgPrefetchSize());
			return smsManager.sendBulkSMS(kannelProperties.getKannelHost(),
					kannelProperties.getKannelPort(),
					kannelProperties.getKannelUser(),
					kannelProperties.getKannelPassword(), sender, reciepients,
					smsMessage, null, null, null, null, null,
					kannelProperties.getDlrMask(), kannelProperties.getDlrUrl()
							+ "%26myId%3D" + MsgId, null, null, smscid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Not Sent";
	}
}