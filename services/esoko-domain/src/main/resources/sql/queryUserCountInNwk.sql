select count(*) from network_userid a, Networks b
where a.network_id = b.network_id and b.record_status = 'A'
and a.network_id = ?;