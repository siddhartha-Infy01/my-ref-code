package com.iexceed.esoko.engine.http;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class HttpClient {
	private static Logger log = LoggerUtils.getLogger();

	public static JSONObject callHLRService(String destination) {
		JSONObject responseJSON = null;
		CharSequence target = "<dest>";
		String HLR_URL = Utils.HLR_URL.replace(target, destination);
		log.debug("HLR-URL:::::" + HLR_URL);
		Client client = Client.create();
		client.setReadTimeout(1000 * Utils.readTimeOut);
		client.setConnectTimeout(1000 * Utils.connectionTimeOut);
		WebResource webResource = client.resource(HLR_URL);
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		log.debug("Status:::::" + response.getStatus());
		if (response.getStatus() == 200) {
			try {
				responseJSON = new JSONObject(response.getEntity(String.class));
				log.debug("REsponse:::::" + responseJSON.toString());
			} catch (ClientHandlerException e) {
				log.error(Utils.getStackTrace(e));			
			} catch (UniformInterfaceException e) {
				log.error(Utils.getStackTrace(e));
			} catch (JSONException e) {
				log.error(Utils.getStackTrace(e));
			}
		}
		return responseJSON;
	}

}
