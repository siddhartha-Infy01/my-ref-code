-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findBidsByDistance`(messagetype varchar(100),commmodityId varchar(100),locationId varchar(100),latitude double,longitude double)
BEGIN
Declare counter int default 0;
  DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (commodity_id VARCHAR(25) PRIMARY KEY);
  insert into _result10(select commodity_id from commodities where commodity_id= commmodityId);
     
DROP TABLE IF EXISTS _tmp10;
CREATE TEMPORARY TABLE _tmp10 LIKE _result10;

REPEAT

    TRUNCATE TABLE _tmp10;
    INSERT INTO _tmp10 SELECT a.commodity_id
      FROM commodities a JOIN  _result10 b ON b.commodity_id = a.parent_id;
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
  
    DROP TABLE IF EXISTS _result9;
  CREATE TEMPORARY TABLE _result9 (location_id VARCHAR(25) PRIMARY KEY,distancekm double);
  insert into _result9(select location_id,null from locations where location_id= locationId);
     
DROP TABLE IF EXISTS _tmp10;
CREATE TEMPORARY TABLE _tmp10 LIKE _result9;

REPEAT

    TRUNCATE TABLE _tmp10;
    INSERT INTO _tmp10 SELECT a.location_id,null
      FROM locations a JOIN  _result9 b ON b.location_id = a.parent_id;
    INSERT IGNORE INTO _result9 SELECT * FROM _tmp10 ;
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
 set counter = (select count(*) from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A' and date(expiry_date) > date(now()));

 if counter = 0 then
 
truncate table _result9;  
insert ignore into _result9 (select location_id, slc_distance (longitude, latitude, y(gis), x(gis)) as DistKM
from locations where 
MBRContains(envelope(linestring(point((latitude+(10/111)), (longitude+(10/111))), point((latitude-(10/111)), (longitude-(10/111))))), gis) 
order by DistKM);
   set counter = (select count(*) from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A' and date(expiry_date) > date(now()));
 if counter = 0 then
 insert ignore into _result9 (select location_id, slc_distance (longitude, latitude, y(gis), x(gis)) as DistKM
from locations where 
MBRContains(envelope(linestring(point((latitude+(30/111)), (longitude+(30/111))), point((latitude-(30/111)), (longitude-(30/111))))), gis) 
order by DistKM);
   set counter = (select count(*) from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A'and date(expiry_date) > date(now()));
 
  if counter = 0 then
 insert ignore into _result9 (select location_id, slc_distance (longitude, latitude, y(gis), x(gis)) as DistKM
from locations where 
MBRContains(envelope(linestring(point((latitude+(50/111)), (longitude+(50/111))), point((latitude-(50/111)), (longitude-(50/111))))), gis) 
order by DistKM);
   set counter = (select count(*) from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A' and date(expiry_date) > date(now()));
 
   if counter = 0 then
 insert ignore into _result9 (select location_id, slc_distance (longitude, latitude, y(gis), x(gis)) as DistKM
from locations where 
MBRContains(envelope(linestring(point((latitude+(2500/111)), (longitude+(2500/111))), point((latitude-(2500/111)), (longitude-(2500/111))))), gis) 
order by DistKM);
   set counter = (select count(*) from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A'and date(expiry_date) > date(now()));
 end if;
 end if;
 end if;
  end if;
  
  select * from bids_offers_master where location in (select location_id from _result9) and commodity in (select * from _result10)  and bid_offer_flag=messagetype
 and auth_stat='A' and date(expiry_date) > date(now()) limit 2;
	END