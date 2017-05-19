package com.iexceed.esoko.se.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.CreateWeatherHistoryResWrap;
import com.iexceed.esoko.jaxb.se.CreateweatherReqWrap;
import com.iexceed.esoko.jaxb.se.CreateweatherResWrap;
import com.iexceed.esoko.jaxb.se.DeleteWeatherReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteWeatherResWrap;
import com.iexceed.esoko.jaxb.se.QueryWeatherResWrap;
import com.iexceed.esoko.jaxb.se.QueryWeatherSourceResWrap;
import com.iexceed.esoko.jaxb.se.SaveWeatherReqWrap;
import com.iexceed.esoko.jaxb.se.SaveWeatherResWrap;
import com.iexceed.esoko.se.service.WeatherService;

@Path("WeatherService")
@Component
public class WeatherServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public WeatherServiceRest() {
	}

	@Autowired
	private WeatherService weatherService;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createweather")
	public CreateweatherResWrap createweather(CreateweatherReqWrap req) {
		CreateweatherResWrap resWrap = new CreateweatherResWrap();
		resWrap.setCreateweatherRes(weatherService.createweather(req
				.getCreateweatherReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryWeather")
	public QueryWeatherResWrap queryWeather(
			@QueryParam("wthrId") String wthrId,
			@QueryParam("locId") String locId, @QueryParam("date") Date date)
			throws DatatypeConfigurationException {
		QueryWeatherResWrap qryResWrap = new QueryWeatherResWrap();
		qryResWrap.setQueryWeatherRes(weatherService.queryWeather(wthrId,
				locId, date));
		return qryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("createWeatherHistory")
	public CreateWeatherHistoryResWrap createWeatherHistory() {
		CreateWeatherHistoryResWrap histResWrap = new CreateWeatherHistoryResWrap();
		histResWrap.setCreateWeatherHistoryRes(weatherService
				.createWeatherHistory());
		return histResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("saveWeather")
	public SaveWeatherResWrap saveWeather(SaveWeatherReqWrap req) {
		SaveWeatherResWrap resWrap = new SaveWeatherResWrap();
		resWrap.setSaveWeatherRes(weatherService.saveWeather(req
				.getSaveWeatherReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteWeather")
	public DeleteWeatherResWrap deleteWeather(DeleteWeatherReqWrap req) {
		DeleteWeatherResWrap resWrap = new DeleteWeatherResWrap();
		resWrap.setDeleteWeatherRes(weatherService.deleteWeather(req
				.getDeleteWeatherReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryWeatherSource")
	public QueryWeatherSourceResWrap queryWeather(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		QueryWeatherSourceResWrap resWrap = new QueryWeatherSourceResWrap();
		resWrap.setQueryWeatherSourceRes(weatherService.queryWeather(networkId,
				userId));
		return resWrap;
	}

}
