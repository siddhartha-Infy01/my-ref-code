SELECT a.push_alert_id,a.name,b.recipient_type,b.recipient_id, "",town,a.data_status,if(a.payee_network_id=?,'E','N') FROM esoko.push_alert_master a,push_alert_recipients b
where a.message_type="W" and b.push_alert_id=a.push_alert_id and (a.is_public='Y' or a.payee_network_id=?) order by name,push_alert_id;