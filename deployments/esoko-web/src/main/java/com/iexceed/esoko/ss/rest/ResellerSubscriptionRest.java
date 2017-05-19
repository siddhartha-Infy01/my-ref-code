package com.iexceed.esoko.ss.rest;

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
import com.iexceed.esoko.jaxb.ss.DeleteResellerInvoiceReqWrap;
import com.iexceed.esoko.jaxb.ss.DeleteResellerSubInvoiceResWrap;
import com.iexceed.esoko.jaxb.ss.QueryAllResellerInvoicesResWrap;
import com.iexceed.esoko.jaxb.ss.QueryInvoicePaymentResWrap;
import com.iexceed.esoko.jaxb.ss.QueryResellerSubscriberDtlsResWrap;
import com.iexceed.esoko.jaxb.ss.QueryResellerSubscripInvoicesResWrap;
import com.iexceed.esoko.jaxb.ss.QuerySubscriptionInvoicesResWrap;
import com.iexceed.esoko.jaxb.ss.QuerySubscriptionUpgradeResWrap;
import com.iexceed.esoko.jaxb.ss.ResellerSubscriptionTopupReqWrap;
import com.iexceed.esoko.jaxb.ss.ResellerSubscriptionTopupResWrap;
import com.iexceed.esoko.jaxb.ss.SubscriberMessagesReqWrap;
import com.iexceed.esoko.jaxb.ss.SubscriberMessagesResWrap;
import com.iexceed.esoko.jaxb.ss.SubscriberRegistrationReqWrap;
import com.iexceed.esoko.jaxb.ss.SubscriberRegistrationResWrap;
import com.iexceed.esoko.jaxb.ss.SubscripInvoiceUpgradeReqWrap;
import com.iexceed.esoko.jaxb.ss.SubscripInvoiceUpgradeResWrap;
import com.iexceed.esoko.jaxb.ss.SubscriptionSuspendReqWrap;
import com.iexceed.esoko.jaxb.ss.SubscriptionSuspendResWrap;
import com.iexceed.esoko.ss.service.ResellerSubscriptionService;

@Component
@Path("ResellerSubscriptionService")
public class ResellerSubscriptionRest {
	@Autowired
	ResellerSubscriptionService resellerService;
	public static Logger log = LoggerUtils.getLogger();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryResellerSubscribers")
	public QueryResellerSubscriberDtlsResWrap queryResellerSubscription(
			@QueryParam("networkId") String networkId,
			@QueryParam("resellerId") String resellerId,
			@QueryParam("filterBy") String filterBy) {
		QueryResellerSubscriberDtlsResWrap resWrap = new QueryResellerSubscriberDtlsResWrap();
		resWrap.setQueryResellerSubscriberDtlsRes(resellerService
				.queryResellerSubscripDtls(networkId, resellerId, filterBy));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySubscriptionInvoices")
	public QuerySubscriptionInvoicesResWrap querySubscriptionInvoices(
			@QueryParam("networkId") String networkId,
			@QueryParam("suscrib_type") String suscrib_type,
			@QueryParam("resellerId") String resellerId) {
		QuerySubscriptionInvoicesResWrap resWrap = new QuerySubscriptionInvoicesResWrap();
		resWrap.setQuerySubscriptionInvoicesRes(resellerService
				.querySubscriptionInvoices(networkId, suscrib_type, resellerId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("subscriptionSuspend")
	public SubscriptionSuspendResWrap subscriptionSuspend(
			SubscriptionSuspendReqWrap reqWrap) {
		SubscriptionSuspendResWrap resWrap = new SubscriptionSuspendResWrap();
		resWrap.setSubscriptionSuspendRes(resellerService
				.subscriptionSuspend(reqWrap.getSubscriptionSuspendReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("subscriberRegistration")
	public SubscriberRegistrationResWrap subscriberRegistration(
			SubscriberRegistrationReqWrap reqWrap) {
		SubscriberRegistrationResWrap resWrap = new SubscriberRegistrationResWrap();
		resWrap.setSubscriberRegistrationRes(resellerService
				.subscriberRegistration(reqWrap.getSubscriberRegistrationReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("querySubscriptionUpgrade")
	public QuerySubscriptionUpgradeResWrap querySubscriptionUpgrade(
			@QueryParam("networkId") String networkId,
			@QueryParam("type") String type,
			@QueryParam("currencyId") String currencyId) {
		QuerySubscriptionUpgradeResWrap resWrap = new QuerySubscriptionUpgradeResWrap();
		resWrap.setQuerySubscriptionUpgradeRes(resellerService
				.querySubscriptionUpgrade(networkId, type, currencyId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("upgradeSubscriptionInvoices")
	public SubscripInvoiceUpgradeResWrap upgradeSubscriptionInvoices(
			SubscripInvoiceUpgradeReqWrap reqWrap) {
		SubscripInvoiceUpgradeResWrap resWrap = new SubscripInvoiceUpgradeResWrap();
		resWrap.setSubscripInvoiceUpgradeRes(resellerService
				.upgradeSubscriptionInvoices(reqWrap
						.getSubscripInvoiceUpgradeReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("resellerSubscriptionTopup")
	public ResellerSubscriptionTopupResWrap resellerSubscriptionTopup(
			ResellerSubscriptionTopupReqWrap reqWrap) {
		ResellerSubscriptionTopupResWrap resWrap = new ResellerSubscriptionTopupResWrap();
		resWrap.setResellerSubscriptionTopupRes(resellerService
				.resellerSubscriptionTopup(reqWrap
						.getResellerSubscriptionTopupReq()));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryresellerSubscripInvoices")
	public QueryResellerSubscripInvoicesResWrap queryresellerSubscripInvoices(
			@QueryParam("networkId") String networkId,
			@QueryParam("suscrib_type") String subscrib_type,
			@QueryParam("resellerId") String reseller_id) {
		QueryResellerSubscripInvoicesResWrap resWrap = new QueryResellerSubscripInvoicesResWrap();
		resWrap.setQueryResellerSubscripInvoicesRes(resellerService
				.queryresellerSubscripInvoices(networkId, subscrib_type,
						reseller_id));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("queryAllResellerInvoices")
	public QueryAllResellerInvoicesResWrap queryAllResellerInvoices(
			@QueryParam("resellerId") String reseller_id) {
		QueryAllResellerInvoicesResWrap resWrap = new QueryAllResellerInvoicesResWrap();
		resWrap.setQueryAllResellerInvoicesRes(resellerService
				.queryAllResellerInvoices(reseller_id));
		return resWrap;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("generateInvoicePDF")
	public QueryInvoicePaymentResWrap generateInvoicePDF(
			@QueryParam("invoiceId") String invoiceId) {
		QueryInvoicePaymentResWrap resWrap = new QueryInvoicePaymentResWrap();
		resWrap.setQueryInvoicePaymentRes(resellerService
				.generateInvoicePDF(invoiceId));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("subscriberMessages")
	public SubscriberMessagesResWrap subscriberMessages(
			SubscriberMessagesReqWrap reqWrap) {
		SubscriberMessagesResWrap resWrap = new SubscriberMessagesResWrap();
		resWrap.setSubscriberMessagesRes(resellerService
				.subscriberMessages(reqWrap.getSubscriberMessagesReq()));
		return resWrap;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("deleteSubscriptionInvoices")
	public DeleteResellerSubInvoiceResWrap deleteSubscriptionInvoices(
			DeleteResellerInvoiceReqWrap reqWrap) {
		DeleteResellerSubInvoiceResWrap resWrap = new DeleteResellerSubInvoiceResWrap();
		resWrap.setDeleteResellerSubInvoiceRes(resellerService
				.deleteSubscriptionInvoices(reqWrap.getDeleteResellerInvoiceReq()));
		return resWrap;
	}
}
