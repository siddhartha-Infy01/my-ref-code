select a.* from commodities a,network_commodities b where a.commodity_id=b.commodity_id and (b.network_id=? or b.network_id="ESOKO_NETWORK")
union
select a.* from commodities a,network_commodities b,Networks c where a.commodity_id=b.commodity_id and b.network_id=c.network_id and c.primary_location_id=(select primary_location_id from Networks where network_id=? ) and c.type="K"
union
select a.* from commodities a,network_commodities b,Networks c where a.commodity_id=b.commodity_id and b.network_id=c.network_id and c.primary_location_id=(select primary_location_id from Networks where network_id=?) and c.is_private="N"
