SELECT * FROM inbox_monitor where network_id=?  and created_ts between ? and ? and service_name=? and channel=? order by created_ts;