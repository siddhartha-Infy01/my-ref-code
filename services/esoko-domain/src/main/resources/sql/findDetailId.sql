SELECT * FROM esoko.agent_details where find_in_set(?,location_id)  and  find_in_set(?,commodity_id)and network_id=?
and user_id=? and application_id='Prices' limit 1;