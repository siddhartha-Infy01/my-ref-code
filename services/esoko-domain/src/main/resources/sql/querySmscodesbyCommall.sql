select b.commodity_id,(select sms_code from sms_codes where network_id=? and sms_id=b.commodity_id) as sm_code 
 from network_commodities b  where b.network_id=?; 