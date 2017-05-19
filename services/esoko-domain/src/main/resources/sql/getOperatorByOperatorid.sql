select o.name operator,o.mcc,o.mnc,
(Select gateway_name from gateway_details where gateway_id=s.gateway_id) gateway,
o.location_id country,
s.route_id routeId,s.cost,s.margin,s.premium,s.cost_currency__id currency,s.network_id exception,
s.type channel,s.direction,y.smsc
from operators o
inner join smpp_routes s on o.operator_id = s.operator_id
inner join gateways y on y.route_id=s.route_id
where o.operator_id=?
and s.type='Kannel'
and s.direction='MT'
and s.record_status='A'
and o.record_status='A'
order by o.location_id;