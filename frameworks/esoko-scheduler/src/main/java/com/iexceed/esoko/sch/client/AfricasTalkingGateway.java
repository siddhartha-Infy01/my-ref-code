package com.iexceed.esoko.sch.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class AfricasTalkingGateway {
	private String _username;
	private String _apiKey;

	public AfricasTalkingGateway(String username_, String apiKey_) {
		_username = username_;
		_apiKey = apiKey_;
	}

	public JSONArray sendMessage(String to_, String message_) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", _username);
		data.put("to", to_);
		data.put("message", message_);

		JSONObject response = sendPOSTRequest(data,
				RestURL.AFRICATAKINGGATEWAY.toString());
		JSONObject smsMessageData = response.getJSONObject("SMSMessageData");
		JSONArray recipients = smsMessageData.getJSONArray("Recipients");
		if (recipients.length() == 0) {
			throw new Exception(smsMessageData.getString("Message"));
		}
		return recipients;
	}

	public JSONArray sendMessage(String to_, String message_, String from_,
			int bulkSMSMode_) throws Exception {
		/*
		 * The optional from_ parameter should be populated with the value of a
		 * shortcode or alphanumeric that is registered with us The optional
		 * will be used by the Mobile Service Provider to determine who gets
		 * billed for a message sent using a Mobile-Terminated ShortCode. The
		 * default value is 1 (which means that you, the sender, gets charged).
		 * This parameter will be ignored for messages sent using alphanumerics
		 * or Mobile-Originated shortcodes.
		 */

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", _username);
		data.put("to", to_);
		data.put("message", message_);

		if (from_.length() > 0)
			data.put("from", from_);

		data.put("bulkSMSMode", Integer.toString(bulkSMSMode_));

		JSONObject response = sendPOSTRequest(data,
				RestURL.AFRICATAKINGGATEWAY.toString());
		JSONObject smsMessageData = response.getJSONObject("SMSMessageData");
		JSONArray recipients = smsMessageData.getJSONArray("Recipients");
		if (recipients.length() == 0) {
			throw new Exception(smsMessageData.getString("Message"));
		}
		return recipients;
	}

	public JSONArray sendMessage(String to_, String message_, String from_,
			int bulkSMSMode_, HashMap<String, String> options_)
			throws Exception {
		/*
		 * The optional from_ parameter should be populated with the value of a
		 * shortcode or alphanumeric that is registered with us The optional
		 * will be used by the Mobile Service Provider to determine who gets
		 * billed for a message sent using a Mobile-Terminated ShortCode. The
		 * default value is 1 (which means that you, the sender, gets charged).
		 * This parameter will be ignored for messages sent using alphanumerics
		 * or Mobile-Originated shortcodes. Other options can be passed into the
		 * options_ map. These are: - keyword : Specify which subscription
		 * product to use to send messages for premium rated short codes -
		 * linkId : Specified when responding to an on-demand content request on
		 * a premium rated short code
		 */

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", _username);
		data.put("to", to_);
		data.put("message", message_);

		if (from_.length() > 0)
			data.put("from", from_);

		data.put("bulkSMSMode", Integer.toString(bulkSMSMode_));

		if (options_.containsKey("keyword"))
			data.put("keyword", options_.get("keyword"));
		if (options_.containsKey("linkId"))
			data.put("linkId", options_.get("linkId"));

		JSONObject response = sendPOSTRequest(data,
				RestURL.AFRICATAKINGGATEWAY.toString());
		JSONObject smsMessageData = response.getJSONObject("SMSMessageData");
		JSONArray recipients = smsMessageData.getJSONArray("Recipients");
		if (recipients.length() == 0) {
			throw new Exception(smsMessageData.getString("Message"));
		}
		return recipients;
	}

	public JSONArray fetchMessages(int lastReceivedId_) throws Exception {
		try {

			StringBuilder sb = new StringBuilder();
			sb.append(lastReceivedId_);

			String urlString = RestURL.AFRICATAKINGGATEWAY.toString() + "?";
			urlString += URLEncoder.encode("username", "UTF-8") + "="
					+ URLEncoder.encode(_username, "UTF-8");
			urlString += "&" + URLEncoder.encode("lastReceivedId", "UTF-8")
					+ "=" + URLEncoder.encode(sb.toString(), "UTF-8");

			URL url = new URL(urlString);

			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("apikey", _apiKey);

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			int responseCode = httpConn.getResponseCode();

			BufferedReader reader;
			if (responseCode == 200) {
				reader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream()));

				String inputLine = reader.readLine();
				JSONObject response = new JSONObject(inputLine);
				JSONObject smsMessageData = response
						.getJSONObject("SMSMessageData");

				reader.close();

				return smsMessageData.getJSONArray("Messages");

			} else {
				reader = new BufferedReader(new InputStreamReader(
						httpConn.getErrorStream()));

				String inputLine = reader.readLine();
				JSONObject response = new JSONObject(inputLine);
				JSONObject smsMessageData = response
						.getJSONObject("SMSMessageData");

				reader.close();

				throw new Exception(smsMessageData.getString("Message"));

			}

		} catch (Exception e) {
			throw e;
		}
	}

	private JSONObject sendPOSTRequest(HashMap<String, String> dataMap_,
			String urlString_) throws Exception {
		try {
			String data = new String();

			Iterator it = dataMap_.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				data += URLEncoder.encode(pairs.getKey().toString(), "UTF-8");
				data += "="
						+ URLEncoder.encode(pairs.getValue().toString(),
								"UTF-8");
				if (it.hasNext())
					data += "&";
			}

			URL url = new URL(urlString_);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("apikey", _apiKey);

			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream());
			writer.write(data);
			writer.flush();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			int responseCode = httpConn.getResponseCode();

			BufferedReader reader;
			if (responseCode == 201) {
				reader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(
						httpConn.getErrorStream()));
			}

			String inputLine = reader.readLine();
			reader.close();

			return new JSONObject(inputLine);

		} catch (Exception e) {

			throw e;

		}
	}
}
