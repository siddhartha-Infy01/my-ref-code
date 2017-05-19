select distinct subscription_id from subscription_details where subscription_level = ? and (country_name = ? or network_name = ?) and subscription_category = ?;
