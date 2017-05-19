
select invoice.invoice_no as "invoice_id",DATE_FORMAT(invoice.created_ts,'%d %M %Y') as "created_ts",reseller.owner_name as "invoice_from",
reseller.company as "company",reseller.town as "town",reseller.countries as "country",invoice.invoice_to as "invoice_to"
,nwkDtls.quantity as "quantity",
nwkDtls.description as "description",nwkDtls.amount as "total",nwkDtls.vat as "vat",nwkDtls.discount as "discount" from invoice_details invoice,
reseller_master reseller,network_subscription_details nwkDtls where invoice.invoice_no = nwkDtls.invoice_no and invoice.invoice_from = reseller.reseller_id
and invoice.invoice_no = ? and invoice.paid_Status<>'D'
union 
select null,null,null,null,null,null,null,'1',description as "description",param_value as "total",null ,null from network_subscription_apps where invoice_no=? order by invoice_id desc;