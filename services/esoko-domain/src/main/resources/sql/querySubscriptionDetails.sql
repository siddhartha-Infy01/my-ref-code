select param_name,param_value,(select owner_user_id from Networks where network_id =?) as "owner",
(select balance from System_accounts where owner_id = ? and type='N') as "balance",
(select ac_currency from System_accounts where owner_id = ? and type='N') as "currency",
(select account_no from System_accounts where owner_id = ? and type='N') as "account",
(select name from Networks where network_id =? and type='N') as "network_name",
(select period from network_subscription_details where network_id =? and subscription_type= ? ) as period,
(select casReseller from network_subscription_details where network_id =? and subscription_type= ?) as casReseller,
(select marginReseller from network_subscription_details where network_id =? and subscription_type= ?) as marginReseller,
(select param_value from subscription_details where subscription_category = 'P' and 
country_name = ? and subscription_type= ? and param_name ='ANNUAL_PYMT_BONUS') as "bonus"  from subscription_details
where subscription_category = 'P' and country_name = ? and subscription_type= ? and subscription_level='D';