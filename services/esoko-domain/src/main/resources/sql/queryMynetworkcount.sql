select a.network_id,count(*),n.description from network_userid a,Networks n  where a.network_id  
in (select network_id from network_userid where user_id=?) and a.network_id=n.network_id group by a.network_id;