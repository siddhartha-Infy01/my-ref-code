-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findCommoditiesByParentChecked`(criteriaid VARCHAR(25),userdata char(5),networkid VARCHAR(25))
BEGIN
  -- Temporary storage
  DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (commodity_id VARCHAR(25) PRIMARY KEY);

  if userdata = 'Y' then
  
  INSERT INTO _result10  (select commodity_id from user_commodities where user_id= criteriaid and network_id=networkid);
  
  elseif userdata = 'A' then
  
  INSERT INTO _result10  (select commodity_id from agent_details where detail_id= criteriaid);
  
  else
  
  INSERT INTO _result10  (select commodity_id from network_commodities where network_id= criteriaid);
  
  end if;

  -- Iteration
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
	select * from commodities where commodity_id in (select * from _result10);
	END