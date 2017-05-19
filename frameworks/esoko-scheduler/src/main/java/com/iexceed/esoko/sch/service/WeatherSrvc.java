package com.iexceed.esoko.sch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.LocationRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.WeatherHistoryRepo;
import com.iexceed.esoko.domain.dao.WeatherRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao3.AlertProfileBasedRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.domain.entity.WeatherHistory;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity3.AlertProfileBased;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.client.AWhereRestClient;
import com.iexceed.esoko.sch.client.TotoRestClient;
import com.iexceed.esoko.sch.client.WeatherTemplate;
import com.vividsolutions.jts.geom.Point;

@Service
public class WeatherSrvc {
	@Autowired
	WeatherRepo weatherRepo;
	@Autowired
	WeatherHistoryRepo weatherHisRepo;
	@Autowired
	AWhereRestClient awhereWeatherClient;
	@Autowired
	TotoRestClient totoWeatherClient;
	@Autowired
	LocationRepo locationRepo;
	@Autowired
	PushAlertMasterRepo pushAlertMasterRepo;
	@Autowired
	AlertProfileBasedRepo alertProfileBasedRepo;
	@Autowired
	PeopleRepo peopleRepo;
	@Autowired
	SystemUserRepo sysUserRepo;
	@Autowired
	WeatherTemplate weatherTemplate;
	public static Logger log = LoggerUtils.getLogger();

	@Transactional
	public void startPushingWeather() {
		// getting all gis locations
		List<Location> list_locations = locationRepo.queryAllGisLocations();
		Map<String, Point> points = new HashMap<String, Point>();
		for (Location location : list_locations) {
			points.put(location.getLocationId(), location.getGis());
		}

		// Getting weather for all gis locations from totoweather
		List<Weather> new_totoweathers = null;
		new_totoweathers = new ArrayList<Weather>();
		for (Map.Entry<String, Point> entry : points.entrySet()) {
			new_totoweathers.add(totoWeatherClient.getWeather("english",
					String.valueOf(entry.getValue().getCoordinate().x),
					String.valueOf(entry.getValue().getCoordinate().y), "14"));
		}

		// Getting weather for all gis locations from awhere
		/*List<Weather> new_awhereweathers = null;
		new_awhereweathers = new ArrayList<Weather>();
		for (Map.Entry<String, Point> entry : points.entrySet()) {
			new_awhereweathers.add(awhereWeatherClient.getWeather(
					String.valueOf(entry.getValue().getCoordinate().x),
					String.valueOf(entry.getValue().getCoordinate().y),
					new Date()));
		}*/

		for (Weather weather : new_totoweathers) {
			List<Weather> list_dbweathers = weatherRepo.queryWeatherByLocIdSrc(
					Utils.getDateWithoutTime(weather.getDate()),
					weather.getLocationId(), weather.getSource());
			// inserting only in weather table
			if (list_dbweathers.size() == 0) {
				weatherRepo.save(weather);
			}

			/*
			 * Deleting old date from weather and insert into weatherhistory and
			 * insert latest date into weather
			 */

			else {
				List<WeatherHistory> list_wthrHisty = new ArrayList<WeatherHistory>();
				for (Weather dbweather : list_dbweathers) {
					WeatherHistory wthrHisty = new WeatherHistory();
					wthrHisty.setMeasure_id(dbweather.getMeasureId());
					wthrHisty.setSource(dbweather.getSource());
					wthrHisty.setDate(dbweather.getDate());
					wthrHisty.setMin_temp(dbweather.getMin_temp());
					wthrHisty.setMax_temp(dbweather.getMax_temp());
					wthrHisty.setSummary(dbweather.getSummary());
					wthrHisty.setRain(dbweather.getRain());
					wthrHisty.setResponse(dbweather.getResponse());
					list_wthrHisty.add(wthrHisty);
				}
				for (WeatherHistory weatherHistory : list_wthrHisty) {
					weatherHisRepo.save(weatherHistory);
				}
				for (Weather dbweather : list_dbweathers) {
					weatherRepo.delete(dbweather);
				}
				weatherRepo.save(weather);

			}
		}

		/*for (Weather weather : new_awhereweathers) {
			List<Weather> list_dbweathers = weatherRepo.queryWeatherByLocIdSrc(
					Utils.getDateWithoutTime(weather.getDate()),
					weather.getLocationId(), weather.getSource());
			// inserting only into weather table
			if (list_dbweathers.size() == 0) {
				weatherRepo.save(weather);
			}

			
			 * Deleting old data from weather and insert into weatherhistory and
			 * insert latest data into weather
			 

			else {
				List<WeatherHistory> list_wthrHisty = new ArrayList<WeatherHistory>();
				for (Weather dbweather : list_dbweathers) {
					WeatherHistory wthrHisty = new WeatherHistory();
					wthrHisty.setMeasure_id(dbweather.getMeasureId());
					wthrHisty.setSource(dbweather.getSource());
					wthrHisty.setDate(dbweather.getDate());
					wthrHisty.setMin_temp(dbweather.getMin_temp());
					wthrHisty.setMax_temp(dbweather.getMax_temp());
					wthrHisty.setSummary(dbweather.getSummary());
					wthrHisty.setRain(dbweather.getRain());
					wthrHisty.setResponse(dbweather.getResponse());
					list_wthrHisty.add(wthrHisty);
				}
				for (Weather dbweather : list_dbweathers) {
					weatherRepo.delete(dbweather);
				}

				for (WeatherHistory weatherHistory : list_wthrHisty) {
					weatherHisRepo.save(weatherHistory);
				}
				weatherRepo.save(weather);

			}
		}*/

	}

	@Transactional
	public void pushWeatherTemp() {
		List<PushAlertMaster> pushAlertMasters = this.pushAlertMasterRepo
				.queryAllPushAlertMaster();
		for (PushAlertMaster pushAlertMaster : pushAlertMasters) {
			if (pushAlertMaster.getWheatherRecepient().equals("A")) {
				String town = pushAlertMaster.getTown();
				if (town != null) {
					String template = this.weatherTemplate.createTemplate(town,
							"toto", new Date());
					if (template == null) {
						pushAlertMaster.setText("No data available");
					} else {
						pushAlertMaster.setText(template);
					}
					this.pushAlertMasterRepo.merge(pushAlertMaster);
				}
			} else {
				String pushAlertId = pushAlertMaster.getId().getPushAlertId();

				List<AlertProfileBased> list_alertPrflBasd = this.alertProfileBasedRepo
						.findAllAlertProfiles(pushAlertId);
				for (AlertProfileBased alertProfileBased : list_alertPrflBasd) {
					String userId = alertProfileBased.getUserId();
					String nwkId = pushAlertMaster.getPayeeNetworkId();
					People people = this.peopleRepo.getNameByNwkId(userId,
							nwkId);
					if (people != null) {
						String town = people.getTown();
						if (town != null) {
							String template = this.weatherTemplate
									.createTemplate(town, "toto", new Date());
							if (template == null) {
								pushAlertMaster.setText("No data available");
							} else {
								alertProfileBased.setText(template);
							}
							this.alertProfileBasedRepo.merge(alertProfileBased);
						}
					} else {
						SystemUser sysUser = this.sysUserRepo
								.findByUserIdNwkId(userId, nwkId);
						if (sysUser != null) {
							String town = sysUser.getTown();
							if (town != null) {
								String template = this.weatherTemplate
										.createTemplate(town, "toto",
												new Date());
								if (template == null) {
									pushAlertMaster
											.setText("No data available");
								} else {
									alertProfileBased.setText(template);
								}
								this.alertProfileBasedRepo
										.merge(alertProfileBased);
							}
						}
					}
				}
			}
		}
	}
}
