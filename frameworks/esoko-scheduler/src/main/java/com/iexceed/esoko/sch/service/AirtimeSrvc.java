package com.iexceed.esoko.sch.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.client.AfricasTalkingGateway;
import com.iexceed.esoko.sch.client.FilePath;

@Component
public class AirtimeSrvc {
	public static Logger log = LoggerUtils.getLogger();

	public void fetchMsg() {

		int lastReceivedId = 0;

		// Here is a sample of how to fetch all messages using a while loop
		try {
			JSONArray results = null;
			do {
				results = initialize().fetchMessages(lastReceivedId);
				for (int i = 0; i < results.length(); ++i) {
					JSONObject result = results.getJSONObject(i);
					log.debug(result.toString());
					lastReceivedId = result.getInt("id");
				}
			} while (results.length() > 0);
		} catch (Exception e) {
			log.error("Caught an Exception: " + e.getMessage());
		}

		// NOTE: Be sure to save lastReceivedId here for next time

		// DONE!!!

	}

	public void sendMessage(String to_, String message) {
		try {
			initialize().sendMessage(to_, message);
		} catch (Exception e) {
			log.error("Caught an Exception: " + e.getMessage());
		}

	}

	public void sendMessage(String to_, String message_, String from_,
			int bulkSMSMode_) {
		try {
			initialize().sendMessage(to_, message_, from_, bulkSMSMode_);
		} catch (Exception e) {
			log.error("Caught an Exception: " + e.getMessage());
		}
	}

	public void sendMessage(String to_, String message_, String from_,
			int bulkSMSMode_, HashMap<String, String> options_) {
		try {
			initialize().sendMessage(to_, message_, from_, bulkSMSMode_,
					options_);
		} catch (Exception e) {
			log.error("Caught an Exception: " + e.getMessage());
		}
	}

	private AfricasTalkingGateway initialize() {
		Properties prop = new Properties();
		InputStream inputStream = null;
		String username = null;
		String password = null;
		try {
			inputStream = new FileInputStream(
					FilePath.AIRTIMEPROPERTIES.toString());
			// load a properties file
			prop.load(inputStream);
			// get the property value

			username = prop.getProperty("username");
			password = prop.getProperty("api_key");
		} catch (IOException e) {
			log.error(Utils.getStackTrace(e));
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(Utils.getStackTrace(e));
				}
			}
		}

		// Create a new instance of our awesome gateway class
		AfricasTalkingGateway gateway = new AfricasTalkingGateway(username,
				password);
		return gateway;

	}
}
