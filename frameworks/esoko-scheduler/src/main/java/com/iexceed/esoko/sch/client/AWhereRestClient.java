package com.iexceed.esoko.sch.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

@Component
public class AWhereRestClient {
	public static Logger log = LoggerUtils.getLogger();

	public Weather getWeather(String latitude, String longitude, Date date) {
		Weather weather = new Weather();
		/*
		 * Client client; //WebTarget target; Response response; Properties prop
		 * = new Properties(); InputStream inputStream = null; String username =
		 * null; String password = null; try { inputStream = new
		 * FileInputStream( FilePath.WEATHERPROPERTIES.toString()); // load a
		 * properties file prop.load(inputStream); // get the property value
		 * username = prop.getProperty("username"); password =
		 * prop.getProperty("password"); } catch (IOException ex) {
		 * ex.printStackTrace(); } finally { if (inputStream != null) { try {
		 * inputStream.close(); } catch (IOException e) { e.printStackTrace(); }
		 * } } String authString = username + ":" + password; byte[]
		 * authEncBytes = Base64.encodeBase64(authString.getBytes()); String
		 * authStringEnc = new String(authEncBytes); SslConfigurator sslConfig =
		 * SslConfigurator.newInstance()
		 * .trustStoreFile(FilePath.KEYSTOREPATH.toString())
		 * .trustStorePassword("8lz0p5350k0").trustStoreType("JKS")
		 * .securityProtocol("SSL");
		 * 
		 * SSLContext sslContext = sslConfig.createSSLContext();
		 * 
		 * client =Client.create().sslContext(sslContext).build(); Calendar
		 * calendar = Calendar.getInstance(); calendar.setTime(date);
		 * 
		 * String jsonRequestValue = "[{\"lat\":" + latitude + ",\"lon\":" +
		 * longitude + ",\"start\":'" + calendar.get(Calendar.DATE) + "-" +
		 * (calendar.get(Calendar.MONTH)) + "-" + calendar.get(Calendar.YEAR) +
		 * "'}]"; String encodejsonReqVal = null; try { encodejsonReqVal =
		 * URLEncoder.encode(jsonRequestValue, "UTF-8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); } target =
		 * client.target(RestURL.AWHERE.toString()).queryParam( "json_request",
		 * encodejsonReqVal); response = target.request()
		 * .header("Content-Type", "application/json;charset=UTF-8")
		 * .header("Authorization", "Basic " + authStringEnc)
		 * .accept("application/json").get();
		 * 
		 * try { if (response.getStatus() == 200) { String json_weather =
		 * ((String) response.getEntity()); JSONObject weatherObj = null; ; try
		 * { weatherObj = new JSONObject(json_weather.trim() .substring(1,
		 * json_weather.length() - 3) .concat("]}")); } catch (JSONException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 * JSONArray daily_weather = null; ; try { daily_weather = weatherObj
		 * .getJSONArray(WeatherInfo.DAILY_WEATHER.toString()); } catch
		 * (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } JSONObject curnt_daily_weather = null; try {
		 * curnt_daily_weather = daily_weather.getJSONObject(0); } catch
		 * (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } String weather_date = null; try { weather_date
		 * = curnt_daily_weather .getString(WeatherInfo.DATE.toString()); }
		 * catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } String max_temp = null; try { max_temp =
		 * curnt_daily_weather .getString(WeatherInfo.MAX_TEMP.toString()); }
		 * catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } String min_temp = null; try { min_temp =
		 * curnt_daily_weather .getString(WeatherInfo.MIN_TEMP.toString()); }
		 * catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } String prec = null; try { prec =
		 * curnt_daily_weather.getString(WeatherInfo.PRECIP .toString()); }
		 * catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } weather = new Weather();
		 * weather.setLocationId(null); weather.setSource("awhere");
		 * weather.setDate(Utils.getFmtDate(weather_date));
		 * weather.setMin_temp(min_temp); weather.setMax_temp(max_temp);
		 * //weather.setSummary(""); weather.setRain(prec);
		 * weather.setResponse(daily_weather.toString()); } } finally {
		 * client.close(); }
		 */
		Properties prop = new Properties();
		InputStream inputStream = null;
		String username = null;
		String password = null;
		try {
			inputStream = new FileInputStream(
					FilePath.WEATHERPROPERTIES.toString());
			// load a properties file
			prop.load(inputStream);
			// get the property value
			username = prop.getProperty("username");
			password = prop.getProperty("password");
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
		String authString = username + ":" + password;
		byte[] authEncBytes = authString.getBytes();
		String authStringEnc = new String(Base64.getEncoder().encode(
				authEncBytes));
		SSLContext ctx = null;
		try {
			KeyStore trustStore;
			trustStore = KeyStore.getInstance("JKS");
			trustStore.load(
					new FileInputStream(FilePath.KEYSTOREPATH.toString()),
					password.toCharArray());
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			tmf.init(trustStore);
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, tmf.getTrustManagers(), null);
		} catch (NoSuchAlgorithmException e) {
			log.error(Utils.getStackTrace(e));
		} catch (KeyStoreException e) {
			log.error(Utils.getStackTrace(e));
		} catch (CertificateException e) {
			log.error(Utils.getStackTrace(e));
		} catch (FileNotFoundException e) {
			log.error(Utils.getStackTrace(e));
		} catch (IOException e) {
			log.error(Utils.getStackTrace(e));
		} catch (KeyManagementException e) {
			log.error(Utils.getStackTrace(e));
		}
		ClientConfig config = new DefaultClientConfig();
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
				new HTTPSProperties(null, ctx));

		WebResource service = Client.create(config).resource(
				RestURL.AWHERE.toString());
		service.addFilter(new HTTPBasicAuthFilter(username, password));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		String jsonRequestValue = "[{\"lat\":" + latitude + ",\"lon\":"
				+ longitude + ",\"start\":'" + calendar.get(Calendar.DATE)
				+ "-" + (calendar.get(Calendar.MONTH)) + "-"
				+ calendar.get(Calendar.YEAR) + "'}]";
		String encodejsonReqVal = null;
		try {
			encodejsonReqVal = URLEncoder.encode(jsonRequestValue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(Utils.getStackTrace(e));
		}
		String response = service.queryParam("json_request", encodejsonReqVal)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(response);
		JSONObject weatherJson = new JSONObject(response.substring(1,
				response.length() - 1));
		String location = weatherJson.getString("location-name");

		JSONArray daily_weatherJson = weatherJson.getJSONArray("daily-weather");
		JSONObject todayWeather = daily_weatherJson.getJSONObject(0);
		String dt = todayWeather.getString("date");
		String prec = todayWeather.getString("prec");
		String min_Temp = todayWeather.getString("min-temp");
		String max_Temp = todayWeather.getString("max-temp");
		weather.setRain(prec);
		weather.setMin_temp(min_Temp);
		weather.setMax_temp(max_Temp);
		weather.setDate(Utils.getFmtDate(dt));
		weather.setResponse(daily_weatherJson.toString());
		return weather;
	}
}
