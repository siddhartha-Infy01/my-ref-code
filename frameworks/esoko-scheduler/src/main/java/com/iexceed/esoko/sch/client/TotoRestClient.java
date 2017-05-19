package com.iexceed.esoko.sch.client;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

@Component
public class TotoRestClient {
	public static Logger log = LoggerUtils.getLogger();

	public Weather getWeather(String language, String latitude,
			String longitude, String days) {
		log.info("Inside TotoRestClient-->getWeather");
		Weather weatherInfo = null;
		JSONObject responseJSON = null;
		Client client = Client.create();
		client.setReadTimeout(1000 * Utils.readTimeOut);
		client.setConnectTimeout(1000 * Utils.connectionTimeOut);
		WebResource webResource = client.resource(RestURL.TOTOAGRICULTURE
				.toString());
		ClientResponse response = webResource
				.queryParam(WeatherInfo.LANGUAGE.toString(), language)
				.queryParam(WeatherInfo.LATITUDE.toString(), latitude)
				.queryParam(WeatherInfo.LONGITUDE.toString(), longitude)
				.queryParam(WeatherInfo.DAYS.toString(), days)
				.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() == 200) {
			try {
				responseJSON = XML.toJSONObject(response
						.getEntity(String.class));
				JSONObject weather = new JSONObject(responseJSON.get(
						WeatherInfo.WEATHER.toString()).toString());
				String station = weather.getString(WeatherInfo.STATION
						.toString());
				log.debug("Station:" + station);
				JSONArray forecasts = weather
						.getJSONArray(WeatherInfo.FORECASTS.toString());
				JSONObject forecast = new JSONObject(forecasts.get(0)
						.toString());
				JSONObject condition = new JSONObject(forecast.get(
						WeatherInfo.CONDITION.toString()).toString());
				JSONObject precipitation = new JSONObject(forecast.get(
						WeatherInfo.PRECIPITATION.toString()).toString());
				String rainInMm = precipitation.getString(WeatherInfo.TOTAL
						.toString());
				log.debug("rainInMm:" + rainInMm);
				String rainInPerc = precipitation.getString(WeatherInfo.CHANCE
						.toString());
				log.debug("rainInPerc:" + rainInPerc);
				String summmary = condition.getString(WeatherInfo.CONTENT
						.toString());
				log.debug("summmary:" + summmary);
				JSONObject temperature = new JSONObject(forecast.get(
						WeatherInfo.TEMPERATURE.toString()).toString());
				String maxTemp = temperature.getString(WeatherInfo.HIGH
						.toString());
				log.debug("maxTemp:" + maxTemp);
				String minTemp = temperature.getString(WeatherInfo.LOW
						.toString());
				log.debug("minTemp:" + minTemp);
				String time = forecast.get(WeatherInfo.TIME.toString())
						.toString();
				log.debug("time:" + time);
				weatherInfo = new Weather();
				weatherInfo.setLocationId(station);
				weatherInfo.setMeasureId(null);
				weatherInfo.setSource(WeatherInfo.TOTOAGRI.toString());
				weatherInfo.setDate(Utils.getFormatedDateTimeStamp5(time));
				weatherInfo.setMin_temp(minTemp);
				weatherInfo.setMax_temp(maxTemp);
				weatherInfo.setSummary(summmary);
				weatherInfo.setRain(rainInMm + "/" + rainInPerc);
				weatherInfo.setResponse(responseJSON.toString());

			} catch (ClientHandlerException e) {
				log.error(Utils.getStackTrace(e));
			} catch (UniformInterfaceException e) {
				log.error(Utils.getStackTrace(e));
			} catch (JSONException e) {
				log.error(Utils.getStackTrace(e));
			}
		}

		return weatherInfo;
	}

}
