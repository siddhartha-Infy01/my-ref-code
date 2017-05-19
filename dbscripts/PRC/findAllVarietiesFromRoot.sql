-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findAllVarietiesFromRoot`(parentid VARCHAR(25))
BEGIN
  

	 -- Temporary storage
  DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (commodity_id VARCHAR(25) PRIMARY KEY);
  insert into _result10(parentid);
     
DROP TABLE IF EXISTS _tmp10;
CREATE TEMPORARY TABLE _tmp10 LIKE _result10;  
REPEAT

    TRUNCATE TABLE _tmp10;
    INSERT INTO _tmp10 SELECT a.commodity_id
      FROM commodities a JOIN  _result10 b ON b.commodity_id = a.parent_id;
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
UNTIL ROW_COUNT() = 0
  END REPEAT;
 
DROP TABLE _tmp10;
 select * from commodities where commodity_id in  (select * from _result10) and type='C';
  
  
	END