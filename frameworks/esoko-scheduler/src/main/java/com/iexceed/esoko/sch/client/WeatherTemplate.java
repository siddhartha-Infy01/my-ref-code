package com.iexceed.esoko.sch.client;

import com.iexceed.esoko.domain.dao.WeatherRepo;
import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.engine.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherTemplate {
	@Autowired
	WeatherRepo weatherRepo;

	public String createTemplate(String location, String source, Date date) {
		List<Weather> list_weathers = this.weatherRepo.queryWeatherByCrntDt(
				Utils.getDateWithoutTime(date), location, source);
		String weatherTemplate = null;
		if (list_weathers.size() > 0) {
			Weather weather = (Weather) list_weathers.get(0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(weather.getDate());
			String rain = weather.getRain();
			String[] parts = rain.split("/");
			String amount = parts[0];
			String percent = parts[1];
			if (rain.contains("0.0mm")) {
				weatherTemplate = this.weatherRepo
						.getWeatherTemplate()
						.replace("<Location>", weather.getLocationId())
						.replace(
								"<Day Of Week>",
								new SimpleDateFormat("EEEE").format(weather
										.getDate()))
						.replace("<Date of Month>", String.valueOf(cal.get(5)))
						.replace(
								"<Month>",
								new SimpleDateFormat("MMMM").format(weather
										.getDate()))
						.replace("<Summary>", weather.getSummary())
						.replace("<Min-temp>", weather.getMin_temp())
						.replace("<Max-temp>", weather.getMax_temp())
						.replace(
								"<Rain probability> chance of <Rain in mm> Rainfall",
								"");
				return weatherTemplate;
			} else {
				weatherTemplate = this.weatherRepo
						.getWeatherTemplate()
						.replace("<Location>", weather.getLocationId())
						.replace(
								"<Day Of Week>",
								new SimpleDateFormat("EEEE").format(weather
										.getDate()))
						.replace("<Date of Month>", String.valueOf(cal.get(5)))
						.replace(
								"<Month>",
								new SimpleDateFormat("MMMM").format(weather
										.getDate()))
						.replace("<Summary>", weather.getSummary())
						.replace("<Min-temp>", weather.getMin_temp())
						.replace("<Max-temp>", weather.getMax_temp())
						.replace("<Rain probability>", percent)
						.replace("<Rain in mm>", amount);
				return weatherTemplate;
			}
		}
		weatherTemplate = "No data available";
		return weatherTemplate;
	}
}
