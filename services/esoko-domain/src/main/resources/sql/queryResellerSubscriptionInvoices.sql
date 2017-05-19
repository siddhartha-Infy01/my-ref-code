select * from invoice_details invoice,network_subscription_details dtls where invoice.invoice_no = dtls.invoice_no and dtls.network_id =?
and dtls.subscription_type = ?  and dtls.reseller_id =? and invoice.paid_status = 'U' and invoice.created_ts > now();
