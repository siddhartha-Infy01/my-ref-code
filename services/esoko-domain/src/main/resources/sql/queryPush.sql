SELECT a.push_alert_id,a.start_date,a.end_date,a.text,b.recipient_type,b.recipient_id,a.data_Status
FROM esoko.push_alert_master a,push_alert_recipients b where a.message_type='P' and b.push_alert_id=a.push_alert_id 
and a.payee_network_id=? and a.schedule='Y';