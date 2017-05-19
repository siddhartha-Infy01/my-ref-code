-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findSmsCodeLocCom`(nwid VARCHAR(100),code varchar(100),type char(1))
BEGIN
  Declare counter varchar(100);
  set counter = null;
set counter = (select sms_code from sms_codes  where sms_code=code and network_id=nwid);
if counter is null then
set counter = (select sms_code from sms_code_alias where alias_id=code and network_id=nwid);
end if;
if counter is not null then
if type='C' then
select * from locations where location_id=(select sms_id from sms_codes where sms_code=counter and network_id=nwid);
else
select * from commodities where commodity_id=(select sms_id from sms_codes where sms_code=counter and network_id=nwid);
end if;
end if;
	
	END