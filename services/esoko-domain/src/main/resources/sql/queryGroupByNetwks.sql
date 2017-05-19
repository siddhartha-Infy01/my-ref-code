(select a.group_id, a.type, (select count(*) from user_group where  
group_id=a.group_id and network_id=a.network_id) as count, a.GROUP_name  
from group_master a where a.network_id=? and type='N' group by group_id) Union
(select a.group_id, a.type, (select count(*) from user_smart_group where  
group_id=a.group_id and network_id=a.network_id) as count, a.GROUP_name  
from group_master a where a.network_id=? and type='S' group by group_id);