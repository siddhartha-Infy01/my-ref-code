select case when DATEDIFF(now(),invoice.paid_date) = 0 then 'Today' 
when DATEDIFF(now(),invoice.paid_date) = -1 then 'Yesterday' else  DATE_FORMAT(invoice.paid_date,'%D %b`%y') end  as "date",
invoice.invoice_no as "number",details.network_id as "network_id",
(select network.name from Networks network where network.network_id = details.network_id) as "networkName",
if((invoice.invoice_date + interval invoice.dso day) > current_date,DATE_FORMAT((invoice.invoice_date + interval invoice.dso day),'%D %b`%y' ),DATEDIFF(current_Date,(invoice.invoice_date + interval invoice.dso day)))  as "due" , invoice.invoice_amount as "amount" from invoice_details invoice,network_subscription_details details where details.invoice_no = invoice.invoice_no 
and details.network_id = ? and details.subscription_type = ? and details.reseller_id = ? and invoice.paid_status='U';
