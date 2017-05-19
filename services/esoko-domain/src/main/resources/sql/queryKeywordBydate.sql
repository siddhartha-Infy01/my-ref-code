SELECT a.keyword,a.network_id,a.join_flag,a.reply_flag,a.airtime_flag,a.payee_account,a.response,a.airtime,a.airtime_currency,a.no_of_messages,a.no_characters,a.ealerts,a.groups,a.my_network,(select count(*) from inbox_monitor where message=a.keyword) as count,(SELECT group_concat(b.group_name)
FROM keywords d
INNER JOIN group_master b ON FIND_IN_SET(b.group_id, d.groups) > 0
WHERE b.network_id=a.network_id and a.keyword=d.keyword) as group_name,
(SELECT group_concat(b.name)
FROM keywords d
INNER JOIN push_alert_master b ON FIND_IN_SET(b.push_alert_id, d.ealerts) > 0
WHERE b.payee_network_id=a.network_id and a.keyword=d.keyword) as alert_name,a.record_status,(select name from Networks where network_id=a.network_id) as netwrkId,a.channel,(select group_concat(e.alias) from keyword_alias e where e.keyword=a.keyword and
e.channel=a.channel) from    keywords a where network_id=? order by a.created_ts;