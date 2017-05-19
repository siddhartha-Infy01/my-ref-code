select distinct subscription_id from subscription_details 
where subscription_level = ? and subscription_type = ? and param_name = ? 
and (country_name = ? or network_name = ? ) and subscription_category = ?;
