-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findUploadDetailsForAlert`(pushAlertId VARCHAR(100),peopleID varchar(100))
BEGIN
declare counter int;
declare nwid varchar(100);
declare conter1 int;

DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (networkId varchar(100) primary key);
insert into _result10 select network_id from alert_source_networks where push_alert_id=pushAlertId;
set counter =0;
set counter = ( select count(*) from _result10 where networkId='ALL');
			if counter = 1 then
			
			insert ignore into _result10 (select network_id from Networks where is_private='N');
			end if;
			
if peopleID is null then


	select a.upload_ID	from price_upload_master a,alert_locations b,alert_commodities c,alert_price_type d where a.network_id in ( select * from _result10) and a.commodity=c.commodity_id and a.market=b.location_id and b.push_alert_id=pushAlertId and c.push_alert_id=pushAlertId and a.price_type=d.price_type and d.alert_id=pushAlertId and a.auth_stat='A';
else

 set nwid= (select payee_network_id from push_alert_master where push_alert_id=pushAlertId);
 set conter1 =0;
 set conter1 = ( select count(*) from people where default_network_id=nwid and people_id=peopleID);
  if conter1 = 0 then
  set nwid = ( select default_network_id from system_user where user_id=peopleID);
  end if;
 
 select a.upload_ID	from price_upload_master a,user_locations b,user_commodities c where a.network_id in ( select * from _result10) 
and a.commodity=c.commodity_id and a.market=b.location_id and b.network_id=nwid and c.network_id=nwid and b.user_id=peopleID and c.user_id=peopleID 
and a.auth_stat='A';
	

end if;			
  
END