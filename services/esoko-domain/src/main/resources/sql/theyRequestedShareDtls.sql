select a.from_share,
ifnull(min((select (case 
when b.share_status='AA'  then 'GA' 
when b.share_status='AR'  then 'G' end)
from user_shares b where b.to_share=? and b.share_item ='prices' and b.from_share = a.from_share and b.auth_stat=a.auth_stat and a.share_type=b.share_type)),'N') prices,
ifnull(min((select (case 
when b.share_status='AA' then 'GA' 
when b.share_status='AR' then 'G'  end)
from user_shares b where b.to_share=? and b.share_item ='offers' and b.from_share = a.from_share and b.auth_stat=a.auth_stat and a.share_type=b.share_type)),'N') offers,
ifnull(min((select (case 
when b.share_status='AA' then 'GA' 
when b.share_status='AR' then 'G'  end)
from user_shares b where b.to_share=? and b.share_item ='newsAndLibrary' and b.from_share = a.from_share and b.auth_stat=a.auth_stat and a.share_type=b.share_type)),'N') newsAndLibrary
,ifnull(min((select (case 
when b.share_status='AA'  then 'GA' 
when b.share_status='AR'  then 'G' end)
from user_shares b where b.to_share=? and b.share_item ='people' and b.from_share = a.from_share and b.auth_stat=a.auth_stat and a.share_type=b.share_type)),'N') people

from  user_shares a where a.to_share=?  and a.share_type='S' and a.auth_stat='U' group by from_share;


