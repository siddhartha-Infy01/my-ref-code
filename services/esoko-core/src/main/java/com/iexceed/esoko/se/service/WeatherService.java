package com.iexceed.esoko.se.service;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.WeatherHistoryRepo;
import com.iexceed.esoko.domain.dao.WeatherRepo;
import com.iexceed.esoko.domain.dao.WeatherSourceRepo;
import com.iexceed.esoko.domain.entity.Weather;
import com.iexceed.esoko.domain.entity.WeatherHistory;
import com.iexceed.esoko.domain.entity.WeatherSource;
import com.iexceed.esoko.domain.entity.WeatherSourcePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.CRWTHRDTLS;
import com.iexceed.esoko.jaxb.se.CreateWeatherHistoryRes;
import com.iexceed.esoko.jaxb.se.CreateweatherReq;
import com.iexceed.esoko.jaxb.se.CreateweatherRes;
import com.iexceed.esoko.jaxb.se.DLTWEATHER;
import com.iexceed.esoko.jaxb.se.DeleteWeatherReq;
import com.iexceed.esoko.jaxb.se.DeleteWeatherRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRWEATHERSOURCE;
import com.iexceed.esoko.jaxb.se.QRWTHRDTLS;
import com.iexceed.esoko.jaxb.se.QueryWeatherRes;
import com.iexceed.esoko.jaxb.se.QueryWeatherSourceRes;
import com.iexceed.esoko.jaxb.se.SVWEATHER;
import com.iexceed.esoko.jaxb.se.SaveWeatherReq;
import com.iexceed.esoko.jaxb.se.SaveWeatherRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class WeatherService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	WeatherRepo weatherRepo;
	@Autowired
	WeatherHistoryRepo wthrHistrepo;
	@Autowired
	WeatherSourceRepo sourceRepo;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_SV_002;
	Header header = null;
	private final String serviceName = "WeatherService";

	@Transactional
	public CreateweatherRes createweather(CreateweatherReq req) {
		log.info("Inside WeatherService -> createweather");
		CreateweatherRes createWeatherRes = new CreateweatherRes();
		Weather weather = new Weather();
		CRWTHRDTLS weatherDtls = (CRWTHRDTLS) req.getCRWTHRDTLS().get(0);
		log.info(" Weather ID :: " + weatherDtls.getWthrId());
		weather.setWeatherId(weatherDtls.getWthrId().toString());
		log.info("Location Id:: " + weatherDtls.getLocId());
		weather.setLocationId(weatherDtls.getLocId().toString());
		log.info("Weather Date:: " + weatherDtls.getDate());
		weather.setDate(weatherDtls.getDate().toGregorianCalendar().getTime());
		log.info("Temperatoure:: " + weatherDtls.getTemp());
		// weather.setTemp(weatherDtls.getTemp());
		log.info("Weather Summary:: " + weatherDtls.getSummary());
		weather.setSummary(weatherDtls.getSummary());
		log.info("Rain:: " + weatherDtls.getRain());
		weather.setRain(weatherDtls.getRain());
		try {
			if (!weatherRepo.exists(weather, weather.getWeatherId())) {
				weatherRepo.save(weather);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				weatherRepo.merge(weather);
				ERROR = ERROR_CODE.ES_UD_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create Weather", "", ERROR);
		createWeatherRes.setHeader(header);
		return createWeatherRes;
	}

	public QueryWeatherRes queryWeather(String wthrId, String locId, Date date)
			throws DatatypeConfigurationException {
		log.info("Inside WeatherService -> queryWeather");
		QueryWeatherRes waetherRes = new QueryWeatherRes();
		log.info("Weather Id:: " + wthrId);
		log.info("Weather location Id:: " + locId);
		log.info("Weather date:: " + date);
		Weather wthrEntity = weatherRepo.queryWeatherbyLocId(locId);
		if (wthrEntity != null) {
			QRWTHRDTLS qrWthrDtls = new QRWTHRDTLS();
			qrWthrDtls.setWthrId(wthrEntity.getWeatherId());
			// qrWthrDtls.setTemp(wthrEntity.getTemp());
			qrWthrDtls.setSummary(wthrEntity.getSummary());
			qrWthrDtls.setRain(wthrEntity.getRain());
			qrWthrDtls.setLocId(wthrEntity.getLocationId());
			qrWthrDtls.setDate(Utils.convertDateToXMLGregorian(wthrEntity
					.getDate().toString()));
			waetherRes.getQRWTHRDTLS().add(qrWthrDtls);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query Weather", "", ERROR);
		waetherRes.setHeader(header);
		return waetherRes;
	}

	@Transactional(readOnly = true)
	public CreateWeatherHistoryRes createWeatherHistory() {
		// TODO Auto-generated method stub
		CreateWeatherHistoryRes wthHistoRes = new CreateWeatherHistoryRes();
		log.info("Inside WeatherService -> createWeatherHistory");
		List<Weather> wthrEntity = weatherRepo.queryWeather();
		WeatherHistory wthHisto = new WeatherHistory();
		try {
			if (wthrEntity != null) {
				for (Weather wthResp : wthrEntity) {
					log.debug("WeatherId -> " + wthResp.getWeatherId());
					wthHisto.setWeatherId(wthResp.getWeatherId());
					wthHisto.setMin_temp(wthResp.getMin_temp());
					wthHisto.setMax_temp(wthResp.getMax_temp());
					wthHisto.setSummary(wthResp.getSummary());
					wthHisto.setRain(wthResp.getRain());
					wthHisto.setLocationId(wthResp.getLocationId());
					wthHisto.setDate(wthResp.getDate());
					wthrHistrepo.save(wthHisto);
					weatherRepo.delete(wthResp);
				}
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create WeatherHistory", "", ERROR_CODE.ES_SC_001);
		wthHistoRes.setHeader(header);
		return wthHistoRes;
	}

	@Transactional
	public SaveWeatherRes saveWeather(SaveWeatherReq req) {
		log.info("Inside WeatherService -> saveWeather");
		SaveWeatherRes createWeatherRes = new SaveWeatherRes();
		SVWEATHER weatherDtls = req.getSVWEATHER();
		log.info(" location :: " + weatherDtls.getLocation());
		log.info("Network id:: " + weatherDtls.getNetworkId());
		log.info("Source:: " + weatherDtls.getResource());
		log.info("UserId:: " + req.getHeader().getUserId());

		try {
			WeatherSourcePK pk = new WeatherSourcePK();
			pk.setNetworkId(weatherDtls.getNetworkId());
			pk.setSource(weatherDtls.getResource());
			WeatherSource weatherSource = null;
			if (!sourceRepo.exists(WeatherSource.class, pk)) {
				weatherSource = new WeatherSource();
				weatherSource.setPk(pk);
				if (req.getHeader() != null) {
					weatherSource.setUserId(req.getHeader().getUserId());
				}
				weatherSource.setLocation(weatherDtls.getLocation());
				weatherSource.setCreatedBy(req.getHeader().getUserId());
				weatherSource.setCreatedTs(Utils.getCurrentDate());
				weatherSource.setAuthStat("U");
				sourceRepo.save(weatherSource);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				weatherSource = sourceRepo.findOne(WeatherSource.class, pk);
				weatherSource.setLocation(weatherDtls.getLocation());
				sourceRepo.merge(weatherSource);
				ERROR = ERROR_CODE.ES_UD_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"saveWeather", "", ERROR);
		createWeatherRes.setHeader(header);
		return createWeatherRes;
	}

	@Transactional
	public DeleteWeatherRes deleteWeather(DeleteWeatherReq req) {
		log.info("Inside WeatherService -> deleteWeather");
		DeleteWeatherRes createWeatherRes = new DeleteWeatherRes();
		DLTWEATHER weatherDtls = req.getDLTWEATHER();
		log.info("Network id:: " + weatherDtls.getNetworkId());
		log.info("Source:: " + weatherDtls.getSource());
		try {
			WeatherSourcePK pk = new WeatherSourcePK();
			pk.setNetworkId(weatherDtls.getNetworkId());
			pk.setSource(weatherDtls.getSource());
			WeatherSource source = new WeatherSource();
			source.setPk(pk);
			WeatherSource weatherSource = sourceRepo.findOne(source, pk);
			sourceRepo.delete(weatherSource);
			ERROR = ERROR_CODE.ES_DL_001;
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_003;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"deleteWeather", "", ERROR);
		createWeatherRes.setHeader(header);
		return createWeatherRes;
	}

	public QueryWeatherSourceRes queryWeather(String networKId, String userId) {
		log.info("Inside WeatherService -> queryWeather");
		QueryWeatherSourceRes queryWeatherRes = new QueryWeatherSourceRes();
		log.info("Network id:: " + networKId);
		log.info("userId:: " + userId);
		try {
			WeatherSourcePK pk = new WeatherSourcePK();
			pk.setNetworkId(networKId);
			pk.setSource(userId);
			List<WeatherSource> weatherSourceList = sourceRepo
					.queryWeatherSrcByNwkIdUserID(networKId, userId);
			if (weatherSourceList != null) {
				if (weatherSourceList.size() > 0) {
					for (WeatherSource weatherSource : weatherSourceList) {
						QRWEATHERSOURCE qrweather = new QRWEATHERSOURCE();
						qrweather.setLocation(weatherSource.getLocation());
						qrweather.setNetworkId(weatherSource.getPk()
								.getNetworkId());
						qrweather.setSource(weatherSource.getPk().getSource());
						queryWeatherRes.getQRWEATHERSOURCE().add(qrweather);
					}
					ERROR = ERROR_CODE.ES_SC_001;
				} else {
					ERROR = ERROR_CODE.ES_NR_001;
				}

			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_NR_001;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"queryWeather", "", ERROR);
		queryWeatherRes.setHeader(header);
		return queryWeatherRes;
	}

}
