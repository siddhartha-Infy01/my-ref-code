select a.* from network_commodities a, commodities b where a.commodity_id=b.commodity_id and  b.record_status='A' and a.network_id=?;