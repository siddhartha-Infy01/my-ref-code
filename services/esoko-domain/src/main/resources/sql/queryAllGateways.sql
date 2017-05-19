select o.name operator,o.mcc,o.mnc,g.gateway_name gateway,o.location_id country,
s.route_id routeId,s.cost,s.cost_currency__id currency,s.network_id exception,
s.type channel,s.direction,s.margin
From operators o
inner join smpp_routes s on o.operator_id = s.operator_id
inner join gateway_details g on s.gateway_id = g.gateway_id
where s.record_status='A'
and o.record_status='A'
order by g.gateway_name, o.location_id;