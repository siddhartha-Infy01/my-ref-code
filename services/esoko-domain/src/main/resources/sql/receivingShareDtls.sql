select a.from_share,
ifnull(min((select (case 
when b.share_status='AA' and b.auth_stat = 'A' then 'GA' 
when b.share_status='AR' and b.auth_stat = 'A' then 'G' 
when b.auth_stat = 'S' then 'S' else 'N' end)
from user_shares b where b.to_share=? and b.share_item ='prices' and b.from_share = a.from_share)),'N') prices,
ifnull(min((select (case 
when b.share_status='AA' and b.auth_stat = 'A' then 'GA' 
when b.share_status='AR' and b.auth_stat = 'A' then 'G' 
when b.auth_stat = 'S' then 'S' else 'N' end)
from user_shares b where b.to_share=? and b.share_item ='offers' and b.from_share = a.from_share)),'N') offers,
ifnull(min((select (case 
when b.share_status='AA' and b.auth_stat = 'A' then 'GA' 
when b.share_status='AR' and b.auth_stat = 'A' then 'G' 
when b.auth_stat = 'S' then 'S' else 'N' end)
from user_shares b where b.to_share=? and b.share_item ='newsAndLibrary' and b.from_share = a.from_share)),'N') newsAndLibrary,
ifnull(min((select (case 
when b.share_status='AA' and b.auth_stat = 'A' then 'GA' 
when b.share_status='AR' and b.auth_stat = 'A' then 'G' 
when b.auth_stat = 'S' then 'S' else 'N' end)
from user_shares b where b.to_share=? and b.share_item ='people' and b.from_share = a.from_share)),'N') people
from  user_shares a where a.to_share=? and auth_stat!='U' group by from_share;