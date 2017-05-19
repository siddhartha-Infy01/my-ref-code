SELECT push_alert_id as id,name as name FROM push_alert_master where payee_network_id=? and message_type not in ('P','CT')

union

SELECT crop_tip_id as id,crop_tip_name as name FROM crop_tip_master where network_id=?;