-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findLocationsByParentChecked`(criteriaid VARCHAR(25),userdata char(5),networkid VARCHAR(25))
BEGIN
  -- Temporary storage
  DROP TABLE IF EXISTS _result9;
  CREATE TEMPORARY TABLE _result9 (location_id VARCHAR(25) PRIMARY KEY);

  if userdata = 'Y' then
  
  INSERT INTO _result9  (select location_id from user_locations where user_id= criteriaid and network_id=networkid);
  
  elseif userdata = 'A' then
  
  INSERT INTO _result9  (select location_id from agent_details where detail_id= criteriaid);
  
  else
  
  INSERT INTO _result9  (select location_id from network_location where network_id= criteriaid);
  
  end if;

  -- Iteration
  DROP TABLE IF EXISTS _tmploc;
  CREATE TEMPORARY TABLE _tmploc LIKE _result9;
  REPEAT
    TRUNCATE TABLE _tmploc;
    INSERT INTO _tmploc SELECT a.location_id
      FROM locations a JOIN  _result9 b ON b.location_id = a.parent_id;
    INSERT IGNORE INTO _result9 SELECT * FROM _tmploc ;
  
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
    DROP TABLE _tmploc;
	select * from locations where location_id in (select * from _result9);
	END