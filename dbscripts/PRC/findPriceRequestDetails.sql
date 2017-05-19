-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findPriceRequestDetails`(networkId VARCHAR(100),locationId varchar(100),commodityId varchar(100))
BEGIN
DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (location_id VARCHAR(25) PRIMARY KEY);
  insert into _result10(select location_id from locations where location_id= locationId);
  insert into _result10(select location_id from locations where parent_id= locationId);
     
DROP TABLE IF EXISTS _tmp10;
CREATE TEMPORARY TABLE _tmp10 LIKE _result10;

REPEAT

    TRUNCATE TABLE _tmp10;
    INSERT INTO _tmp10 SELECT a.location_id
      FROM locations a JOIN  _result10 b ON b.location_id = a.parent_id;
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
UNTIL ROW_COUNT() = 0
  END REPEAT;
 
DROP TABLE _tmp10; 
 select *	from price_upload_master where  market in (select * from _result10) and commodity=commodityId and network_id=networkId and 
 date(collected_on) > date((sysdate() - interval 14 day)) group by market,price_type;
	
		
  
END