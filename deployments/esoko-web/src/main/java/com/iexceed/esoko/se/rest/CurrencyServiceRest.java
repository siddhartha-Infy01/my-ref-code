package com.iexceed.esoko.se.rest;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.CreatCurrenciesReqWrap;
import com.iexceed.esoko.jaxb.se.CreatCurrenciesResWrap;
import com.iexceed.esoko.jaxb.se.DeleteCurrenciesReqWrap;
import com.iexceed.esoko.jaxb.se.DeleteCurrenciesResWrap;
import com.iexceed.esoko.jaxb.se.QueryAllCurrenciesWrap;
import com.iexceed.esoko.jaxb.se.QueryCcByNameResWrap;
import com.iexceed.esoko.jaxb.se.QueryCurrenciesResWrap;
//import com.iexceed.esoko.jaxb.se.crtccy.CreatCurrenciesReq;
//import com.iexceed.esoko.jaxb.se.crtccy.CreatCurrenciesRes;
//import com.iexceed.esoko.jaxb.se.delccy.DeleteCurrenciesReq;
//import com.iexceed.esoko.jaxb.se.delccy.DeleteCurrenciesRes;
//import com.iexceed.esoko.jaxb.se.qryccy.QueryCurrenciesRes;
import com.iexceed.esoko.se.service.CurrencyService;

@Path("CurrencyService")
@Component
public class CurrencyServiceRest {

	public static Logger log = LoggerUtils.getLogger();

	public CurrencyServiceRest() {
	}

	@Autowired
	private CurrencyService currencyService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCurrencies")
	public QueryCurrenciesResWrap queryCurrencies(
			@QueryParam("currencyCode") String currencyCode) {
		QueryCurrenciesResWrap curResWrap = new QueryCurrenciesResWrap();
		curResWrap.setQueryCurrenciesRes(currencyService
				.queryCurrency(currencyCode));
		return curResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("createCurrencies")
	public CreatCurrenciesResWrap createCurrencies(CreatCurrenciesReqWrap req) {
		CreatCurrenciesResWrap crtCurResWrap = new CreatCurrenciesResWrap();
		crtCurResWrap.setCreatCurrenciesRes(currencyService.createCurrency(req
				.getCreatCurrenciesReq()));
		return crtCurResWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteCurrencies")
	public DeleteCurrenciesResWrap deleteCurrencies(DeleteCurrenciesReqWrap req) {
		DeleteCurrenciesResWrap delCurResWrap = new DeleteCurrenciesResWrap();
		delCurResWrap.setDeleteCurrenciesRes(currencyService.deleteCurrency(req
				.getDeleteCurrenciesReq()));
		return delCurResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryCcByName")
	public QueryCcByNameResWrap queryCcByName(
			@QueryParam("currencyName") String currencyName) {
		QueryCcByNameResWrap curResWrap = new QueryCcByNameResWrap();
		curResWrap.setQueryCcByNameRes(currencyService
				.queryCcByName(currencyName));
		return curResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllCurrencies")
	public QueryAllCurrenciesWrap queryAllCurrencies() {
		QueryAllCurrenciesWrap curResWrap = new QueryAllCurrenciesWrap();
		curResWrap.setQueryAllCurrencies(currencyService.queryAllCurrencies());
		return curResWrap;
	}
}
