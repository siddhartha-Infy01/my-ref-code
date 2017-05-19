select b.commodity_id from  network_commodities b, commodities c
 where b.network_id=? and b.commodity_id=c.commodity_id and c.parent_id is null;