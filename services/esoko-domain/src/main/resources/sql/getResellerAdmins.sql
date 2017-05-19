select GROUP_CONCAT(DISTINCT concat(first_name,' ',last_name)) from system_user system,user_group nwk 
where nwk.network_id =system.default_network_id and system.user_id = nwk.user_id and nwk.network_id = ?;
