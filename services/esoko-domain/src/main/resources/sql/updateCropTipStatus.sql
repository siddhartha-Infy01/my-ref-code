UPDATE crop_tip_master
SET alert_state = ?,
modified_by = ?,
modified_ts = ?
WHERE crop_tip_id = ?;