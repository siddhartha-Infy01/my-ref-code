select to_share,
max(case when share_item='prices' and auth_stat = 'A' and from_share= ? then 'O' when share_item='prices' and from_share=? and auth_stat = 'S' then 'S1' else 'N' end) prices,
max(case when share_item='offers' and auth_stat = 'A' and from_share= ? then 'O' when share_item='offers' and from_share=? and auth_stat = 'S' then 'S1' else 'N' end) offers,
max(case when share_item='newsAndLibrary' and auth_stat = 'A' and from_share= ? then 'O' when share_item='newsAndLibrary' and from_share=? and auth_stat = 'S' then 'S1' else 'N' end) newsAndLibrary,
max(case when share_item='people' and auth_stat = 'A' and from_share= ? then 'O' when share_item='people' and from_share=? and auth_stat = 'S' then 'S1' else 'N' end) people
from user_shares where from_share=? and auth_stat!='U' group by to_share;
