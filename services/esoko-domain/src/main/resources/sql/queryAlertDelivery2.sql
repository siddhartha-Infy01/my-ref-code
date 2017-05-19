SELECT schedule_id,(select name from push_alert_master where push_alert_id=a.schedule_id) as alert_name,created_Ts,date_Format(created_Ts,?) as time,
sum(case when msg_Status= 'D' then 1 else 0 end) as sent,
sum(case when msg_Status= 'P' then 1 else 0 end) as pending ,
sum(case when msg_Status= 'F' then 1 else 0 end) as failed ,
count(*) as total
 FROM message_delivary_Details a where message_type in ('BO','PA','W','CT') and network_id=? and created_Ts between ? and ?  group by schedule_id,date(created_ts) order by alert_name;