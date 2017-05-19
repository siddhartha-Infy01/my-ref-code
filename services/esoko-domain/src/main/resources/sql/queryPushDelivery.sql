SELECT schedule_id,created_Ts,message,
sum(case when msg_Status= 'D' then 1 else 0 end) as sent,
sum(case when msg_Status= 'P' then 1 else 0 end) as pending ,
sum(case when msg_Status= 'F' then 1 else 0 end) as failed ,
count(*) as total

 FROM esoko.message_delivary_Details where message_type='P' and network_id=? and created_Ts between ? and DATE_ADD(?, INTERVAL 1 DAY)  group by schedule_id,date(created_ts);