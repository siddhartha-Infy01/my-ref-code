select subscription_id,subscription_type,subscription_level,param_name,param_value from subscription_details where subscription_category = 'P' and 
country_name in (select primary_location_id from Networks where network_id = ?);
