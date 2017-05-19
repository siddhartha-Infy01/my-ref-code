select * 
from profile_Sharing_types 
where network_id=? and profile_id=(select people_type 
from user_shares 
where to_share=? and from_share=? and share_item='people' and auth_stat='A');