package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.se.ALLCURRENCIES;
import com.iexceed.esoko.jaxb.se.CRCURRDTLS;
import com.iexceed.esoko.jaxb.se.CreatCurrenciesReq;
import com.iexceed.esoko.jaxb.se.CreatCurrenciesRes;
import com.iexceed.esoko.jaxb.se.DLCURRDTLS;
import com.iexceed.esoko.jaxb.se.DeleteCurrenciesReq;
import com.iexceed.esoko.jaxb.se.DeleteCurrenciesRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRCURBYNAME;
import com.iexceed.esoko.jaxb.se.QRCURRDTLS;
import com.iexceed.esoko.jaxb.se.QueryAllCurrencies;
import com.iexceed.esoko.jaxb.se.QueryCcByNameRes;
import com.iexceed.esoko.jaxb.se.QueryCurrenciesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class CurrencyService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	CurrencyRepo currecyRepo;
	Enum<ERROR_CODE> ERROR = null;
	Header header = null;
	private final String serviceName = "CurrencyService";

	public QueryCurrenciesRes queryCurrency(String currencyCode) {
		QueryCurrenciesRes queryCurRes = new QueryCurrenciesRes();
		log.info("Inside QueryCurrenciesRes -> queryCurrency");
		Currency currencyEntity = currecyRepo.findByCcyCode(currencyCode);
		log.info("currencyCode -> " + currencyCode);
		log.info("currencyEntity -> " + currencyEntity);
		try {
			if (currencyEntity != null) {
				QRCURRDTLS qrCurDtls = new QRCURRDTLS();
				qrCurDtls.setCurrencyCode(currencyEntity.getCode());
				qrCurDtls.setCurrencyName(currencyEntity.getName());
				qrCurDtls.setCurrencyId(currencyEntity.getCurrencyId());
				qrCurDtls.setRank(currencyEntity.getRank());
				qrCurDtls.setSymbol(currencyEntity.getSymbol());
				queryCurRes.getQRCURRDTLS().add(qrCurDtls);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Currency Service", "", ERROR_CODE.ES_SC_001);
		queryCurRes.setHeader(header);
		return queryCurRes;

	}

	@Transactional
	public CreatCurrenciesRes createCurrency(CreatCurrenciesReq req) {
		log.info("Inside CurrencyService --> Create Currency");
		CreatCurrenciesRes crtCurRes = new CreatCurrenciesRes();
		CRCURRDTLS curDtls = new CRCURRDTLS();
		curDtls = (CRCURRDTLS) req.getCRCURRDTLS().get(0);
		Currency curEntity = new Currency();
		log.info("Currency Code:: " + curDtls.getCurrencyCode());
		curEntity.setCode(curDtls.getCurrencyCode());
		log.info("Currency Id:: " + curDtls.getCurrencyId());
		curEntity.setCurrencyId(curDtls.getCurrencyId().toString());
		log.info("Currency Rank:: " + curDtls.getRank());
		curEntity.setRank(curDtls.getRank());
		log.info("Currency Symbol::" + curDtls.getSymbol());
		curEntity.setSymbol(curDtls.getSymbol());
		log.info("Currency No. of Decimals:: " + curDtls.getNoOfDecimals());
		curEntity.setNumberOfDecimals(curDtls.getNoOfDecimals());
		log.info("Currency User Id:: " + curDtls.getUserId());
		curEntity.setCreatedBy(curDtls.getUserId());
		curEntity.setRecordStatus("A");
		try {
			if (!currecyRepo.exists(curEntity, curEntity.getCode())) {
				curEntity.setCreatedBy(req.getHeader().getUserId());
				curEntity.setCreatedTs(Utils.getCurrentDate());
				currecyRepo.save(curEntity);
				ERROR = ERROR_CODE.ES_SV_001;
			} else {
				Currency tempEntity = currecyRepo.findOne(curEntity,
						curEntity.getCode());
				curEntity.setCreatedBy(tempEntity.getCreatedBy());
				curEntity.setCreatedTs(tempEntity.getCreatedTs());
				curEntity.setModifiedBy(req.getHeader().getUserId());
				curEntity.setModifiedTs(Utils.getCurrentDate());
				currecyRepo.merge(curEntity);
				ERROR = ERROR_CODE.ES_UD_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Creat Currencies", "", ERROR_CODE.ES_SC_001);
		crtCurRes.setHeader(header);
		return crtCurRes;
	}

	@Transactional
	public DeleteCurrenciesRes deleteCurrency(DeleteCurrenciesReq req) {
		DeleteCurrenciesRes delCurRes = new DeleteCurrenciesRes();
		DLCURRDTLS dlCurDtrl = new DLCURRDTLS();
		Currency curEntity = new Currency();
		dlCurDtrl = (DLCURRDTLS) req.getDLCURRDTLS().get(0);
		log.info("Currency Code:: " + dlCurDtrl.getCurrencyCode());
		curEntity.setCode(dlCurDtrl.getCurrencyCode());
		log.info("Currency Id:: " + dlCurDtrl.getCurrencyCode());
		curEntity.setCurrencyId(dlCurDtrl.getCurrencyCode());
		curEntity = currecyRepo.findOne(curEntity, curEntity.getCurrencyId());
		curEntity.setRecordStatus("D");
		try {
			if (curEntity != null) {
				curEntity.setModifiedBy(req.getHeader().getUserId());
				curEntity.setModifiedTs(Utils.getCurrentDate());
				currecyRepo.merge(curEntity);
				ERROR = ERROR_CODE.ES_DL_001;
			} else {
				ERROR = ERROR_CODE.DM_SV_003;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Delete Currencies", "", ERROR);
		delCurRes.setHeader(header);
		return delCurRes;
	}

	public QueryCcByNameRes queryCcByName(String currencyCode) {
		QueryCcByNameRes queryCurRes = new QueryCcByNameRes();
		Currency currencyEntity = currecyRepo.findccByname(currencyCode);
		QRCURBYNAME qrCurDtls = new QRCURBYNAME();
		try {
			if (currencyEntity != null) {
				qrCurDtls.setCurrencyCode(currencyEntity.getCode());
				qrCurDtls.setCurrencyId(currencyEntity.getCurrencyId());
				qrCurDtls.setRank(currencyEntity.getRank());
				qrCurDtls.setSymbol(currencyEntity.getSymbol());
				qrCurDtls.setNoOfDecimals(currencyEntity.getNumberOfDecimals());
				queryCurRes.getQRCURBYNAME().add(qrCurDtls);
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query CcByName", "", ERROR_CODE.ES_SC_001);
		queryCurRes.setHeader(header);
		return queryCurRes;

	}

	public QueryAllCurrencies queryAllCurrencies() {
		QueryAllCurrencies currency = new QueryAllCurrencies();
		List<Currency> curResp = (List<Currency>) currecyRepo
				.queryALLcurrencies();
		List<ALLCURRENCIES> curList = new ArrayList<ALLCURRENCIES>();
		if (curResp.size() != 0) {
			try {
				for (Currency curLoop : curResp) {
					ALLCURRENCIES curDtls = new ALLCURRENCIES();
					curDtls.setCurrencyCode(curLoop.getCode());
					curDtls.setCurrencyName(curLoop.getName());
					curDtls.setCurrencyId(curLoop.getCurrencyId());
					curDtls.setSymbol(curLoop.getSymbol());
					curList.add(curDtls);
				}
				currency.getALLCURRENCIES().addAll(curList);
				ERROR = ERROR_CODE.ES_SC_001;
			} catch (DomainException e) {
				ERROR = ERROR_CODE.ES_SV_002;
				log.error(Utils.getStackTrace(e));
			}

		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Creat Currencies", "", ERROR_CODE.ES_SC_001);
		currency.setHeader(header);

		return currency;
	}
}
