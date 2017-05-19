select * from locations where location_id= (select sms_id from sms_codes where sms_code=(
select sms_code from sms_codes  where sms_code=? and network_id=?
union
select sms_code from sms_code_alias where alias_id=? and network_id=?));


