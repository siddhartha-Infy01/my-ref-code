select a.first_name,a.last_name,a.people_id,b.location_id,b.commodity_id,
b.template,b.target,b.incentive,b.bonus,b.application_id from people a,agent_details b where b.network_id=?
and  b.network_id=a.default_network_id and b.user_id=a.people_id  and b.record_status='A' order by application_id,first_name;