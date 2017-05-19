package com.iexceed.esoko.ms.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ms.QuerySMSRatesResWrap;
import com.iexceed.esoko.jaxb.ms.QueryUserBillingSummaryResWrap;
import com.iexceed.esoko.jaxb.ms.QueryUserTransactionsResWrap;
import com.iexceed.esoko.ms.service.UserBillingService;

@Path("UserBillingService")
@Component
public class UserBillingRest {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	UserBillingService billService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserBillingSummary")
	public QueryUserBillingSummaryResWrap queryUserBillingSummary(
			@QueryParam("userId") String userId) {
		QueryUserBillingSummaryResWrap billResWrap = new QueryUserBillingSummaryResWrap();
		billResWrap.setQueryUserBillingSummaryRes(billService
				.queryUserBillingSummary(userId));
		return billResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryUserTransactions")
	public QueryUserTransactionsResWrap queryUserTransactions(
			@QueryParam("userId") String userId,
			@QueryParam("fromDate") String fromDate,
			@QueryParam("toDate") String toDate,
			@QueryParam("servicename") String servicename,
			@QueryParam("baseCcy") String baseCcy,
			@QueryParam("quoteCcy") String quoteCcy) {
		QueryUserTransactionsResWrap billResWrap = new QueryUserTransactionsResWrap();
		billResWrap.setQueryUserTransactionsRes(billService
				.queryUserTransactions(userId, fromDate, toDate, servicename, baseCcy, quoteCcy));
		return billResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySMSRates")
	public QuerySMSRatesResWrap querySMSRates(
			@QueryParam("countryId") String countryId,
			@QueryParam("Currency") String currency) {
		QuerySMSRatesResWrap smsResWrap = new QuerySMSRatesResWrap();
		smsResWrap.setQuerySMSRatesRes(billService.querySMSRates(countryId,
				currency));
		return smsResWrap;
	}
}
