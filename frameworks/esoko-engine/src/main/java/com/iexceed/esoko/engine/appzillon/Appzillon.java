package com.iexceed.esoko.engine.appzillon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.iexceed.esoko.engine.utils.HashXor;
import com.iexceed.esoko.engine.utils.JdbcConnector;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Appzillon {
	private static Logger log = LoggerUtils.getLogger();
	private static String lRequestKey = null;
	private static String lSessionId = null;
	private String lDeviceId = null;
	private boolean proceed;

	public Appzillon() {

	}

	public Appzillon(String lDeviceId) {
		log.info("Inside Appzillon construtor");
		this.lDeviceId = lDeviceId;
		lRequestKey = "000NEW";
		lSessionId = "";
		try {
			JSONObject requestJSON = getRequestJSON(Utils.lLoginInterfaceId);
			requestJSON.put("appzillonBody", getLoginRequestJSON());
			ClientResponse response = callAppzillonServer(requestJSON
					.toString());
			if (response.getStatus() == 200) {
				JSONObject responseJSON = new JSONObject(
						response.getEntity(String.class));
				if (getResponseStatus(responseJSON).equals("success")) {
					String canProceed = responseJSON
							.getJSONObject("appzillonBody")
							.getJSONObject("loginResponse")
							.getString("canProceed");
					if (canProceed.equals("true")) {
						proceed = true;
					} else {
						proceed = false;
						log.error("Appzillon: canProceed is false");
					}
				} else {
					proceed = false;
					log.error("Appzillon: Header status is faliure");
				}
			} else {
				proceed = false;
				log.error("Appzillon: Response Code for login: "
						+ response.getStatus());
			}
		} catch (Exception e) {
			proceed = false;
			log.error("Appzillon: " + Utils.getStackTrace(e));
		}
	}

	public List<String> getAppzillonUsers() {
		log.info("Inside Appzillon -> getAppzillonUsers");
		List<String> appzillonUserList = null;
		if (proceed) {
			try {
				appzillonUserList = new ArrayList<String>();
				JSONObject requestJSON = getRequestJSON(Utils.lGetUserInterfaceId);
				requestJSON.put("appzillonBody", getAppzillonUsersQueryJSON());
				ClientResponse response = callAppzillonServer(requestJSON
						.toString());
				if (response.getStatus() == 200) {
					JSONObject responseJSON = new JSONObject(
							response.getEntity(String.class));
					if (getResponseStatus(responseJSON).equals("success")) {
						JSONArray appzillonUsersArray = responseJSON
								.getJSONObject("appzillonBody").getJSONArray(
										"appzillonGetUserResponse");
						if ((appzillonUsersArray != null)
								&& (appzillonUsersArray.length() != 0)) {
							for (int i = 0; i < appzillonUsersArray.length(); i++) {
								JSONObject appzillonUser = appzillonUsersArray
										.getJSONObject(i);
								appzillonUserList.add(appzillonUser
										.getString("userId"));
							}
						}
					} else {
						log.error("Appzillon -> getAppzillonUsers: Header status is faliure");
					}
				} else {
					log.error("Appzillon -> getAppzillonUsers: Response code : "
							+ response.getStatus());
				}
			} catch (Exception e) {
				log.error("Appzillon -> getAppzillonUsers: "
						+ Utils.getStackTrace(e));
			}
		}

		return appzillonUserList;
	}

	public boolean createAppzillonUser(String lUserId, String lUserName,
			String lEmail, String lPhoneNumber, String lTown, String lCountry,
			String lPassword) {
		log.info("Inside Appzillon -> createAppzillonUser");
		boolean userCreated = false;
		try {

			if (proceed) {
				JSONObject requestJSON = getRequestJSON(Utils.lCreateUserInterfaceId);
				requestJSON.put(
						"appzillonBody",
						createAppzillonUserJSON(lUserId, lUserName, lEmail,
								lPhoneNumber, lTown, lCountry, lPassword));
				ClientResponse response = callAppzillonServer(requestJSON
						.toString());
				if (response.getStatus() == 200) {
					JSONObject responseJSON = new JSONObject(
							response.getEntity(String.class));
					if (getResponseStatus(responseJSON).equals("success")) {
						String statusString = responseJSON.getJSONObject(
								"appzillonBody").getString(
								"appzillonCreateUserResponse");
						JSONObject statusObj = new JSONObject(statusString);
						String status = statusObj.getString("status");
						if (status.equals("success")) {
							userCreated = true;
						} else {
							log.error("Appzillon -> createEsokoUser: Response status is faliure");
						}
					} else {
						log.error("Appzillon -> createEsokoUser: Header status is faliure");
					}
				} else {
					log.error("Appzillon -> createEsokoUser: Response code : "
							+ response.getStatus());
				}
			}

		} catch (Exception e) {
			log.error("Appzillon -> createEsokoUser: " + Utils.getStackTrace(e));
		}
		return userCreated;
	}

	public void deleteAppzillonUser(List<String> appzillonUsers) {
		log.info("Inside Appzillon -> deleteAppzillonUser");
		if (proceed) {
			try {
				JSONObject requestJSON = getRequestJSON(Utils.lDeleteUserInterfaceId);
				requestJSON.put("appzillonBody",
						deleteAppzillonUserJSON(appzillonUsers));
				ClientResponse response = callAppzillonServer(requestJSON
						.toString());
				if (response.getStatus() == 200) {
					JSONObject responseJSON = new JSONObject(
							response.getEntity(String.class));
					if (getResponseStatus(responseJSON).equals("success")) {
						String status = responseJSON
								.getJSONObject("appzillonBody")
								.getJSONObject("appzillonDeleteUserResponse")
								.getString("status");
						if (status.equals("success")) {
							log.error("Appzillon -> deleteAppzillonUser: User deleted successfully");
						} else {
							log.error("Appzillon -> deleteAppzillonUser: Response status is faliure");
						}
					} else {
						log.error("Appzillon -> deleteAppzillonUser: Header status is faliure");
					}
				} else {
					log.error("Appzillon -> deleteAppzillonUser: Response code : "
							+ response.getStatus());
				}
			} catch (Exception e) {
				log.error("Appzillon -> deleteAppzillonUser: "
						+ Utils.getStackTrace(e));
			}
		}
	}

	private String getResponseStatus(JSONObject responseJSON)
			throws JSONException {
		log.info("Inside Appzillon -> getResponseStatus");
		updateSessionVairables(responseJSON);
		String lStatus = responseJSON.getJSONObject("appzillonHeader")
				.getString("status");
		log.debug("Response Status: " + lStatus);
		return lStatus;
	}

	private void updateSessionVairables(JSONObject responseJSON)
			throws JSONException {
		lRequestKey = responseJSON.getJSONObject("appzillonHeader").getString(
				"requestKey");
		lSessionId = responseJSON.getJSONObject("appzillonHeader").getString(
				"sessionId");
	}

	private ClientResponse callAppzillonServer(String requestJSONString) {
		Client client = Client.create();
		client.setReadTimeout(1000 * Utils.readTimeOut);
		client.setConnectTimeout(1000 * Utils.connectionTimeOut);
		WebResource webResource = client.resource(Utils.lServerURL);
		ClientResponse response = webResource.accept("application/json").post(
				ClientResponse.class, requestJSONString);

		return response;
	}

	private JSONObject getRequestJSON(String lInterfaceId) throws JSONException {
		log.info("Inside Appzillon -> getRequestJSON");
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("appId", Utils.lAdminAppId);
		headerMap.put("sessionId", lSessionId);
		headerMap.put("deviceId", this.lDeviceId);
		headerMap.put("requestKey", lRequestKey);
		headerMap.put("userId", Utils.lUserID);
		headerMap.put("screenId", "");
		headerMap.put("status", "success");
		headerMap.put("interfaceId", lInterfaceId);
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("appzillonHeader", headerMap);
		return requestJSON;
	}

	private JSONObject getLoginRequestJSON() throws JSONException {
		log.info("Inside Appzillon -> getLoginRequestJSON");
		Date d = new Date();
		DateFormat df = new SimpleDateFormat(Utils.lSystemDateFormat);
		String lSysDate = df.format(d);
		// Utility.hashSHA256(lPin, lUserID + ServerToken);//Use this function
		// to generate Hashed PIN
		String pinOtp = HashXor.hashValue(this.lDeviceId, this.lDeviceId, "",
				Utils.lUserID, Utils.lHashedPin, lSysDate);
		Map<String, String> loginRequestMap = new HashMap<String, String>();
		loginRequestMap.put("requestKey", lRequestKey);
		loginRequestMap.put("hashKey1", this.lDeviceId);
		loginRequestMap.put("hashKey2", this.lDeviceId);
		loginRequestMap.put("deviceId", this.lDeviceId);
		loginRequestMap.put("userId", Utils.lUserID);
		loginRequestMap.put("pin", pinOtp);
		loginRequestMap.put("interfaceId", Utils.lLoginInterfaceId);
		loginRequestMap.put("sysDate", lSysDate);
		loginRequestMap.put("appId", Utils.lAdminAppId);
		JSONObject loginRequest = new JSONObject();
		loginRequest.put("loginRequest", loginRequestMap);
		return loginRequest;
	}

	private JSONObject getAppzillonUsersQueryJSON() throws JSONException {
		log.info("Inside Appzillon -> getAppzillonUsersQueryJSON");
		Map<String, String> appzillonUsersRequestMap = new HashMap<String, String>();
		appzillonUsersRequestMap.put("appId", Utils.lEsokoAppId);
		appzillonUsersRequestMap.put("userId", "");
		appzillonUsersRequestMap.put("userName", "");

		JSONObject appzillonUsersRequest = new JSONObject();
		appzillonUsersRequest.put("appzillonGetUserRequest",
				appzillonUsersRequestMap);
		return appzillonUsersRequest;
	}

	private JSONObject createAppzillonUserJSON(String lUserId,
			String lUserName, String lEmail, String lPhoneNumber, String lTown,
			String lCountry, String lPassword) throws JSONException {
		log.info("Inside Appzillon -> createAppzillonUserJSON");
		JSONObject deviceId = new JSONObject();
		deviceId.put("deviceId", this.lDeviceId);
		JSONArray deviceIdArray = new JSONArray();
		deviceIdArray.put(deviceId);

		JSONObject userStatusObject = new JSONObject();
		userStatusObject.put("loginTime", "");
		userStatusObject.put("loginStatus", "N");
		userStatusObject.put("userLocked", "N");

		Map<String, Object> createAppzillonUserMap = new HashMap<String, Object>();
		createAppzillonUserMap.put("appId", Utils.lEsokoAppId);
		createAppzillonUserMap.put("userId", lUserId);
		createAppzillonUserMap.put("userName", lUserName);
		createAppzillonUserMap.put("email1", lEmail);
		createAppzillonUserMap.put("phone1", lPhoneNumber);
		createAppzillonUserMap.put("addr1", lTown);
		createAppzillonUserMap.put("addr2", lCountry);
		createAppzillonUserMap.put("addr3", "");
		createAppzillonUserMap.put("addr4", "");
		createAppzillonUserMap.put("email2", "");
		createAppzillonUserMap.put("phone2", "");
		createAppzillonUserMap.put("password", lPassword);
		createAppzillonUserMap.put("extIdentifier", "");
		createAppzillonUserMap.put("language", "en");
		createAppzillonUserMap.put("Devices", deviceIdArray);
		createAppzillonUserMap.put("userStatus", userStatusObject);

		JSONObject cretaeAppzillonUserRequest = new JSONObject();
		cretaeAppzillonUserRequest.put("appzillonCreateUserRequest",
				createAppzillonUserMap);

		return cretaeAppzillonUserRequest;
	}

	private static JSONObject deleteAppzillonUserJSON(
			List<String> appzillonUsers) throws JSONException {
		log.info("Inside Appzillon -> deleteAppzillonUserJSON");
		JSONArray lUserList = new JSONArray();
		for (String lUserId : appzillonUsers) {
			JSONObject deleteUserObj = new JSONObject();
			deleteUserObj.put("userId", lUserId);
			deleteUserObj.put("appId", Utils.lEsokoAppId);
			lUserList.put(deleteUserObj);
		}
		JSONObject deleteAppzillonUsersRequest = new JSONObject();
		deleteAppzillonUsersRequest
				.put("appzillonDeleteUserRequest", lUserList);
		return deleteAppzillonUsersRequest;
	}

	public void updateUserAccount(String userId, String activeState) {
		log.debug("Inside Appzillon -> updateUserAccount");

		JdbcConnector connector = new JdbcConnector();
		Connection connection = connector.getJdbcConnection("AppzillonDS");
		if (connection != null) {
			PreparedStatement stmt = null;
			try {
				stmt = connection
						.prepareStatement("UPDATE tb_smtp_user_login SET USER_ACTIVE=? WHERE USER_ID=?");
				if (stmt == null) {
					log.info("Unable to create prepared statement");
				} else {
					stmt.setString(1, activeState);
					stmt.setString(2, userId);
					stmt.executeUpdate();
					log.info("Record active state set to: " + activeState);
				}
			} catch (SQLException e) {
				log.error(Utils.getStackTrace(e));
			} finally {

				try {
					if (stmt != null) {
						stmt.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.error(Utils.getStackTrace(e));
				}

			}
		}
	}

}
