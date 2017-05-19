package com.iexceed.esoko.context;

import java.util.Properties;

import org.springframework.web.context.ContextLoaderListener;

import com.iexceed.esoko.engine.resources.ResourceLocatorImpl;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.trg.TriggerHandler;

public class EsokoContextStartup extends ContextLoaderListener {

	public void contextInitialized(javax.servlet.ServletContextEvent event) {
		ResourceLocatorImpl resource = new ResourceLocatorImpl();
		Properties esokoProperties = resource.getEsokoProperties();
		Utils.lSystemDateFormat = esokoProperties
				.getProperty("AppzillonDateFormat");
		Utils.lServerURL = esokoProperties.getProperty("AppzillonServerURL");
		Utils.lUserID = esokoProperties.getProperty("AppzillonUserID");
		Utils.lAdminAppId = esokoProperties.getProperty("AdminAppId");
		Utils.lEsokoAppId = esokoProperties.getProperty("EsokoAppId");
		Utils.lLoginInterfaceId = esokoProperties
				.getProperty("LoginInterfaceId");
		Utils.lGetUserInterfaceId = esokoProperties
				.getProperty("GetUserInterfaceId");
		Utils.lCreateUserInterfaceId = esokoProperties
				.getProperty("CreateUserInterfaceId");
		Utils.lDeleteUserInterfaceId = esokoProperties
				.getProperty("DeleteUserInterfaceId");
		Utils.lHashedPin = esokoProperties.getProperty("AppzillonPin");
		Utils.connectionTimeOut = Integer.valueOf((String) esokoProperties
				.get("AppzillonConnectionTimeOut"));
		Utils.readTimeOut = Integer.valueOf((String) esokoProperties
				.get("AppzillonReadTimeOut"));
		Utils.HLR_URL = esokoProperties.getProperty("hlrurl");
		Utils.smsCenter = esokoProperties.getProperty("smsCenter");
		Utils.senderId = esokoProperties.getProperty("senderId");
		Utils.OTPMessage = esokoProperties.getProperty("OTPMessage");
		Utils.OTPExpiryMins = Integer.valueOf(esokoProperties
				.getProperty("OTPExpiryMins"));
		Utils.lServerToken = esokoProperties.getProperty("OTPMessage");
		esokoProperties = null;
		resource = null;

		super.contextInitialized(event);
	};

	public void contextDestroyed(javax.servlet.ServletContextEvent event) {
		Utils.lSystemDateFormat = null;
		Utils.lServerURL = null;
		Utils.lUserID = null;
		Utils.lAdminAppId = null;
		Utils.lEsokoAppId = null;
		Utils.lLoginInterfaceId = null;
		Utils.lGetUserInterfaceId = null;
		Utils.lCreateUserInterfaceId = null;
		Utils.lDeleteUserInterfaceId = null;
		Utils.lHashedPin = null;
		Utils.smsCenter = null;
		Utils.senderId = null;		
		Utils.OTPMessage = null;
		Utils.lServerToken = null;
		TriggerHandler triggerHandler = TriggerHandler.getInstance();
		triggerHandler.stopQuartz();
		super.contextDestroyed(event);
	};

}
