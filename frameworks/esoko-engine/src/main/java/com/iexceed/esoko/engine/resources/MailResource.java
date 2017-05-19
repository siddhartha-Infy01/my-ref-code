package com.iexceed.esoko.engine.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

@Component
public class MailResource {

	//@Resource(mappedName = "java:jboss/mail/Default")
	
	private Session mailSession;

	public boolean sendEmail(String fromAddress, List<String> toAddress,
			String Subject, String content,List<File> files) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(fromAddress);
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO,
					getToAddressArray(toAddress));
			m.setSubject(Subject);
			m.setSentDate(new java.util.Date());
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(getMessagePart(content));
			if (files != null)
				for(File file:files){
				mp.addBodyPart(getAttachment(file));
				}
			m.setContent(mp, "text/plain");
			Transport.send(m);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private Address[] getToAddressArray(List<String> toAddress)
			throws AddressException {
		List<Address> internetAddres = new ArrayList<Address>();

		for (String address : toAddress) {
			Address add = new InternetAddress(address);
			internetAddres.add(add);
		}
		Address[] addArray = new Address[internetAddres.size()];
		return internetAddres.toArray(addArray);

	}

	private MimeBodyPart getAttachment(File file) throws MessagingException {
		MimeBodyPart mbp = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(file);
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(fds.getName());
		return mbp;
	}

	private MimeBodyPart getMessagePart(String Content)
			throws MessagingException {
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setText(Content);
		return mbp;

	}

}
