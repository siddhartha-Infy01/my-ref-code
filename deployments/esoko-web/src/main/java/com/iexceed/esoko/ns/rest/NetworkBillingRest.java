package com.iexceed.esoko.ns.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.NwkFundTransferReqWrap;
import com.iexceed.esoko.jaxb.ns.NwkFundTransferResWrap;
import com.iexceed.esoko.jaxb.ns.QueryAccountDtlsListResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkBillingSummaryResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkSMSRatesResWrap;
import com.iexceed.esoko.jaxb.ns.QueryNwkTransactionsResWrap;
import com.iexceed.esoko.jaxb.ns.QueryTransactionServicesResWrap;
import com.iexceed.esoko.ne.service.NetworkBillingService;

/*
 * @author Ankur
 */
@Path("NetworkBillingService")
@Component
public class NetworkBillingRest {

	public static Logger log = LoggerUtils.getLogger();

	public NetworkBillingRest() {

	}

	@Autowired
	public NetworkBillingService nwBillingService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkBillingSummary")
	public QueryNwkBillingSummaryResWrap queryNwkBillingSummary(
			@QueryParam("networkId") String networkId,
			@QueryParam("userId") String userId) {
		log.info("Inside QueryNwkBillingSummaryResWrap -> queryNwkBillingSummary");
		QueryNwkBillingSummaryResWrap summaryResWrap = new QueryNwkBillingSummaryResWrap();
		summaryResWrap.setQueryNwkBillingSummaryRes(nwBillingService
				.queryNwkSummary(networkId, userId));
		return summaryResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkTransactions")
	public QueryNwkTransactionsResWrap queryNwkTransactions(
			@QueryParam("networkId") String networkId,
			@QueryParam("fromDate") String fromDate,
			@QueryParam("toDate") String toDate,
			@QueryParam("servicename") String servicename,
			@QueryParam("baseCcy") String baseCcy,
			@QueryParam("quoteCcy") String quoteCcy) {
		log.info("Inside QueryNwkTransactionsResWrap -> queryNwkTransactions");
		QueryNwkTransactionsResWrap txnResWrap = new QueryNwkTransactionsResWrap();
		txnResWrap.setQueryNwkTransactionsRes(nwBillingService.queryNwkTxn(
				networkId, fromDate, toDate, servicename, baseCcy, quoteCcy));
		return txnResWrap;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("nwkFundTransfer")
	public NwkFundTransferResWrap nwkFundTransfer(NwkFundTransferReqWrap req) {
		log.info("Inside NwkFundTransferResWrap -> nwkFundTransfer");
		NwkFundTransferResWrap trnsferResWrap = new NwkFundTransferResWrap();
		trnsferResWrap.setNwkFundTransferRes(nwBillingService
				.nwkFundTransfer(req.getNwkFundTransferReq()));
		return trnsferResWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryNwkSMSRates")
	public QueryNwkSMSRatesResWrap queryNwkSMSRates(
			@QueryParam("countryId") String countryId,
			@QueryParam("currency") String currency) {
		log.info("Inside QueryNwkSMSRatesResWrap -> queryNwkSMSRates");
		QueryNwkSMSRatesResWrap qNwkSMS = new QueryNwkSMSRatesResWrap();
		qNwkSMS.setQueryNwkSMSRatesRes(nwBillingService.queryNwkSMSRates(
				countryId, currency));
		return qNwkSMS;

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryTxnServices")
	public QueryTransactionServicesResWrap queryTxnServices() {
		log.info("Inside QueryTransactionServicesResWrap -> queryTxnServices");
		QueryTransactionServicesResWrap qTxnService = new QueryTransactionServicesResWrap();
		qTxnService.setQueryTransactionServicesRes(nwBillingService
				.queryTxServices());
		return qTxnService;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAccontListDtl")
	public QueryAccountDtlsListResWrap queryAccontListDtl() {
		log.info("Inside NetworkBillingService -> queryAccontListDtl");
		QueryAccountDtlsListResWrap resWrap = new QueryAccountDtlsListResWrap();
		resWrap.setQueryAccountDtlsListRes(nwBillingService
				.queryAccontListDtl());
		return resWrap;
	}
}
