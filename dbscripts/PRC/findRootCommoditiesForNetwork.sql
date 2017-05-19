-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findRootCommoditiesForNetwork`(networkid VARCHAR(25))
BEGIN
  -- Temporary storage
  DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (commodity_id VARCHAR(25) PRIMARY KEY);


  
  INSERT INTO _result10  (select commodity_id from network_commodities where network_id= networkid);
  
 

  -- Iteration
  DROP TABLE IF EXISTS _tmp10;
  CREATE TEMPORARY TABLE _tmp10 LIKE _result10;
  REPEAT
    TRUNCATE TABLE _tmp10;
    INSERT IGNORE INTO _tmp10 SELECT ifnull(a.parent_id,-1)
      FROM commodities a JOIN  _result10 b ON b.commodity_id = a.commodity_id;
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
  
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
    DROP TABLE _tmp10;
	select * from commodities where commodity_id in (select * from _result10) AND PARENT_ID IS  NULL;
	END