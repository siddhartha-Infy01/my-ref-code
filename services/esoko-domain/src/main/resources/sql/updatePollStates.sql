update poll_master
set poll_state = ?,modified_by=?,modified_ts=?
where poll_id = ?;