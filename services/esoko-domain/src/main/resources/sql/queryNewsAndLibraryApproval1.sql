SELECT a.upload_id,(select title from library where lib_id=a.upload_id) as headline,
a.agent_id,a.application_id,a.upload_dt,(select first_name from people where people_id=a.agent_id and default_network_id=?) as name FROM upload_master a where network_id=? and application_id in ('news','library') and auth_stat='U' order by name;

