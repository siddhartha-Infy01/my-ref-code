SELECT * FROM inbox_monitor where network_id=? and created_ts between ? and ? order by created_ts;