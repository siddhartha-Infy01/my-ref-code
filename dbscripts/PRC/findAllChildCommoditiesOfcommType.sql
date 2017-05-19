-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `findAllChildCommoditiesOfcommType`(networkid VARCHAR(100),criteriaId varchar(100))
BEGIN
  -- Temporary storage
  DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (commodity_id VARCHAR(500) PRIMARY KEY);
  

 if criteriaId='MY' then
  INSERT INTO _result10  (select commodity_id from network_commodities where network_id=networkid);
  else
  
  	 DROP TABLE IF EXISTS _result;
  CREATE TEMPORARY TABLE _result (nwt_id VARCHAR(25) PRIMARY KEY);

  -- Seeding
  INSERT INTO _result  (SELECT parent_id 
      FROM Networks  where network_id = networkid);
  
    -- Iteration
  DROP TABLE IF EXISTS _tmp;
  CREATE TEMPORARY TABLE _tmp LIKE _result;
  REPEAT
    TRUNCATE TABLE _tmp;
    INSERT INTO _tmp SELECT parent_id AS nwt_id
      FROM _result JOIN Networks  ON nwt_id = network_id;

    INSERT IGNORE INTO _result SELECT nwt_id FROM _tmp;
   
UNTIL ROW_COUNT() = 0
  END REPEAT;
  insert into _result10 (select a.commodity_id from commodities a,network_commodities b where a.commodity_id=b.commodity_id and b.network_id=networkid);
  
  insert ignore into _result10(  select a.commodity_id from commodities a,network_commodities b where a.commodity_id=b.commodity_id and b.network_id in (SELECT * FROM _result where nwt_id != '&&'));
  insert ignore into _result10( select a.commodity_id from commodities a,network_commodities b,Networks c where a.commodity_id=b.commodity_id and b.network_id=c.network_id  and c.is_private="N");
	
	
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
	select * from commodities where commodity_id in (select * from _result10) and type='C';
	
	





 

	END