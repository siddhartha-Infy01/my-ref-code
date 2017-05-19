select to_share,
max(case when share_item='prices' and from_share= ? then 'O'else 'N' end) prices,
max(case when share_item='offers' and from_share= ? then 'O' else 'N' end) offers,
max(case when share_item='newsAndLibrary' and from_share= ? then 'O' else 'N' end) newsAndLibrary,
max(case when share_item='people' and from_share= ? then 'O' else 'N' end) people
from user_shares where from_share=?  and auth_stat='U'  and share_type='S' group by to_share;
