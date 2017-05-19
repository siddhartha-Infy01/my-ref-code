select * from smpp_routes
where operator_id=? and gateway_id=? and network_id is null
and type=? and direction=? and record_status='A';