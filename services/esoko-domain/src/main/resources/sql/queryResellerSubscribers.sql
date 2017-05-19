select nwk_sub.subscription_id as "sub_id",nwk_sub.subscription_type as "sub_type",nwk_sub.network_id as "network_id",
(select network.name from Networks network where network.network_id = nwk_sub.network_id) as "network_name",nwk_sub.balance as
 "balance",DATE_FORMAT(nwk_sub.end_date,'%b %D `%y') as "expiry",nwk_sub.currency as "currency",nwk_sub.owner_name as "owner_name",
case when DATEDIFF(nwk_sub.end_date,nwk_sub.start_date) >1 then 'G' else 'R' end as "expiryFlag",
case when nwk_sub.balance >50 then 'G' else 'R' end as "balanceFlag" ,
(select nwk_app.param_value from network_subscription_apps nwk_app where nwk_sub.subscription_id = nwk_app.subscription_id and nwk_app.subscription_type = nwk_sub.subscription_type and nwk_app.param_name = 'ALERTS') as "alerts",
(select nwk_app.param_value from network_subscription_apps nwk_app where nwk_sub.subscription_id = nwk_app.subscription_id and nwk_app.subscription_type = nwk_sub.subscription_type and nwk_app.param_name = 'AGENTS') as "agents" 
from network_subscription nwk_sub where nwk_sub.reseller_id = ? and nwk_sub.record_status = 'A' and nwk_sub.end_date between date((sysdate() - interval 1 day)) and date((sysdate() + interval ? day)) ;
