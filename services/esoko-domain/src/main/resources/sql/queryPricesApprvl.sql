SELECT upload_ID,commodity,price_type,market,collected_on,(select name from currencies where a.currency_id=currency_id),price,(select symbol from Measures where measure_id=a.measure),
variety,weight,(select symbol from Measures where measure_id=a.weight_measure),upload_mode,agent_id,upload_gis,created_ts FROM price_upload_master a where network_id=?