select * from smpp_routes
where operator_id=? and gateway_id=? and network_id=?
and type=? and direction=? and record_status='A';