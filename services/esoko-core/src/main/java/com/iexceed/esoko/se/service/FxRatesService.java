package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.ForexHistoryRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.ForexHistory;
import com.iexceed.esoko.domain.entity.ForexHistoryPK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ms.TRNDTLS;
import com.iexceed.esoko.jaxb.se.CRFXRTDTLS;
import com.iexceed.esoko.jaxb.se.CreatefxRatesHistoryRes;
import com.iexceed.esoko.jaxb.se.CreatefxRatesReq;
import com.iexceed.esoko.jaxb.se.CreatefxRatesRes;
import com.iexceed.esoko.jaxb.se.FXRTDTLS;
import com.iexceed.esoko.jaxb.se.FXRTSDATES;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRFXRTDTLS;
import com.iexceed.esoko.jaxb.se.QueryfxRatesDateRes;
import com.iexceed.esoko.jaxb.se.QueryfxRatesReq;
import com.iexceed.esoko.jaxb.se.QueryfxRatesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class FxRatesService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	ForexRepo frxRepo;
	@Autowired
	ForexHistoryRepo forexHistRepo;
	@Autowired
	CurrencyRepo currRepo;
	Enum<ERROR_CODE> ERROR = null;
	Header header = null;
	private final String serviceName = "FxRatesService";

	@Transactional
	public CreatefxRatesRes createfxRates(CreatefxRatesReq req) {
		CreatefxRatesRes fxRatesRes = new CreatefxRatesRes();
		Forex forexEntity = new Forex();
		CRFXRTDTLS crfxrdtls = new CRFXRTDTLS();
		crfxrdtls = (CRFXRTDTLS) req.getCRFXRTDTLS().get(0);
		log.info("ForexId::" + crfxrdtls.getForexId());
		forexEntity.setForexId(crfxrdtls.getForexId().toString());
		log.info("Date::" + crfxrdtls.getDate());
		if (crfxrdtls.getDate() != null) {
			forexEntity.setDate(crfxrdtls.getDate().toGregorianCalendar()
					.getTime());
		}
		log.info("BaseCCy::" + crfxrdtls.getBaseCCy());
		forexEntity.setBaseCurrencyId(crfxrdtls.getBaseCCy());
		log.info("QuoteCCy::" + crfxrdtls.getQuoteCCy());
		forexEntity.setQuoteCurrencyId(crfxrdtls.getQuoteCCy().toString());
		log.info("Rate::" + crfxrdtls.getRate());
		forexEntity.setRate(crfxrdtls.getRate());
		log.info("UserId::" + crfxrdtls.getUserId());
		forexEntity.setCreatedBy(crfxrdtls.getUserId());
		forexEntity.setRecordStatus("A");
		log.debug("ForexId -> " + forexEntity.getForexId());
		try {
			if (!frxRepo.exists(forexEntity, forexEntity.getForexId())) {
				forexEntity.setCreatedBy(req.getHeader().getUserId());
				forexEntity.setCreatedTs(Utils.getCurrentDate());
				frxRepo.save(forexEntity);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				Forex tempEntity = frxRepo.findOne(forexEntity,
						forexEntity.getForexId());
				forexEntity.setCreatedBy(tempEntity.getCreatedBy());
				forexEntity.setCreatedTs(tempEntity.getCreatedTs());
				forexEntity.setModifiedBy(req.getHeader().getUserId());
				forexEntity.setModifiedTs(Utils.getCurrentDate());
				frxRepo.merge(forexEntity);
				ERROR = ERROR_CODE.ES_UD_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create fxRates", "", ERROR_CODE.ES_SC_001);
		fxRatesRes.setHeader(header);
		return fxRatesRes;
	}

	public QueryfxRatesRes queryfxRates(QueryfxRatesReq req)
			throws DatatypeConfigurationException {
		QueryfxRatesRes fxRatesRes = new QueryfxRatesRes();
		Header header = null;
		log.info("Inside QueryfxRatesRes -> queryfxRates");
		QRFXRTDTLS qrFxDtls = req.getQRFXRTDTLS().get(0);
		Forex forexEntity = frxRepo.queryfxRatesbyCcy(qrFxDtls.getBaseCCy(),
				qrFxDtls.getQuoteCCy());
		log.debug("Forex List::" + forexEntity);
		if (forexEntity != null) {
			log.debug("fxDtlsList -> " + forexEntity.getRate());
			List<FXRTDTLS> fxDtlsList = this.convertAmount(fxRatesRes,
					req.getQRFXRTDTLS(),
					Float.parseFloat(forexEntity.getRate().toString()));
			log.debug("fxDtlsList -> "
					+ Float.parseFloat(forexEntity.getRate().toString()));

			fxRatesRes.getFXRTDTLS().addAll(fxDtlsList);
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Forex Rates Service", "Query fxRates by Ccy", "",
					ERROR_CODE.ES_SC_001);
		} else {
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"Forex Rates Service", "Query fxRates by Ccy", "",
					ERROR_CODE.ES_NR_001);
		}
		fxRatesRes.setHeader(header);
		return fxRatesRes;

	}
	
	public QueryfxRatesDateRes queryFxRatesDate(String forexDate)
			 {
		QueryfxRatesDateRes fxRatesRes = new QueryfxRatesDateRes();
		Header header = null;
		
		
		log.info("Inside QueryfxRatesDateRes -> QueryfxRatesDateRes");
		log.debug("forexDate ->" + Utils.getFormatedDate(forexDate));
		List<Map<String, Object>> list = (List<Map<String, Object>>) frxRepo.queryAllFxRates(Utils.getFormatedDate(forexDate));
		List<FXRTSDATES> fxrates = new ArrayList<FXRTSDATES>();
		
		if (list.size() != 0)
		{
			for (Map<String, Object> fxloop : list) {
				FXRTSDATES fxdates = new FXRTSDATES();
				
				if(fxloop.get("Date") != null)
				{
					fxdates.setDate(Utils.getDDMMMYYFormat(fxloop
							.get("Date").toString(), 1));
				}
				
				fxdates.setLocalCcy(fxloop.get("currency_name").toString());
				fxdates.setLocalCcyCode(fxloop.get("base_currency_id").toString());
				fxdates.setUsdRates(fxloop.get("rate").toString());
				fxrates.add(fxdates);
				fxdates = null;				
			}
			
			fxRatesRes.getFXRTSDATES().addAll(fxrates);
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"FxRatesService", "queryallFxrates", "",
					ERROR_CODE.FX_SC_001);
			
		}
		
		else {
			header = (Header) HeaderFactory.getHeader(HeaderType.SE,
					"FxRatesService", "queryallFxrates", "",
					ERROR_CODE.FX_NR_001);
		}
		
		
		fxRatesRes.setHeader(header);		
		return fxRatesRes;

	}

	@Transactional
	public CreatefxRatesHistoryRes createfxRatesHistory()
			throws DatatypeConfigurationException {
		CreatefxRatesHistoryRes fxHistryRes = new CreatefxRatesHistoryRes();
		log.info("Inside CreatefxRatesHistoryRes -> createfxRatesHistory");
		List<Forex> forexEntity = frxRepo.queryForex();
		ForexHistory frxHistentity = new ForexHistory();
		ForexHistoryPK fxHisPKEntity = new ForexHistoryPK();
		log.info("Forex List::" + forexEntity);
		try {
			if (forexEntity.size() != 0) {
				for (Forex forexResp : forexEntity) {
					log.debug("Date -> " + forexResp.getCreatedTs());
					log.debug("ForexId -> " + forexResp.getForexId());
					fxHisPKEntity.setForexId(forexResp.getForexId());
					fxHisPKEntity.setDate(forexResp.getCreatedTs());
					frxHistentity.setId(fxHisPKEntity);
					frxHistentity.setBaseCurrencyId(forexResp
							.getBaseCurrencyId());
					frxHistentity.setQuoteCurrencyId(forexResp
							.getQuoteCurrencyId());
					frxHistentity.setRate(forexResp.getRate());
					frxHistentity.setCreatedBy(forexResp.getCreatedBy());
					frxHistentity.setCreatedTs(forexResp.getCreatedTs());
					frxHistentity.setModifiedBy(forexResp.getModifiedBy());
					frxHistentity.setModifiedTs(forexResp.getModifiedTs());
					forexHistRepo.save(frxHistentity);
					frxRepo.delete(forexEntity);
					// forexList.add(qrFrDtls);
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
				"Creat fxRates", "", ERROR_CODE.ES_SC_001);
		fxHistryRes.setHeader(header);
		return fxHistryRes;
	}

	public List<FXRTDTLS> convertAmount(QueryfxRatesRes res,
			List<QRFXRTDTLS> qrFxDtls, Float rate) {
		List<FXRTDTLS> fxDtlsList = new ArrayList<FXRTDTLS>();
		Float convertAmount;
		log.debug("rate -> " + rate);
		log.info("Inside FxRatesService -> convertAmount");
		try {
			if (qrFxDtls.size() != 0) {
				for (QRFXRTDTLS qrFxLoop : qrFxDtls) {
					FXRTDTLS fxDtls = new FXRTDTLS();
					fxDtls.setBaseCCy(qrFxLoop.getBaseCCy());
					fxDtls.setQuoteCCy(qrFxLoop.getQuoteCCy());
					Currency currency = currRepo.findByCcyCode(qrFxLoop
							.getQuoteCCy());
					fxDtls.setQuoteCCySymbol(currency.getSymbol());
					fxDtls.setAmount(qrFxLoop.getAmount());
					convertAmount = qrFxLoop.getAmount() * rate;
					convertAmount = Utils.roundDoubleVal(
							Double.valueOf(convertAmount), 3);
					fxDtls.setRate(rate);
					fxDtls.setConvrtAmount(convertAmount);
					fxDtlsList.add(fxDtls);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return fxDtlsList;
	}
}
