-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `aliasCheck`(alias1 varchar(200),channel1 varchar(50),keyword1 VARCHAR(50))
BEGIN
  -- Temporary storage

declare counter int;

  SET @vars = alias1;
			 
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result;
        CREATE TEMPORARY TABLE _result (field varchar(50) primary key);
        TRUNCATE TABLE _result;
        
		if @vars is not null	then 	
        SET @sql := CONCAT('INSERT IGNORE INTO ', '_result', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;
  
  set counter =0;
select count(*)  into counter from  keywords where keyword in (select field from _result) and channel=channel1;

if counter = 0 then
set counter =0;

select count(1)   into counter from keyword_alias  where  channel=channel1 and keyword!=keyword1 and alias in (select * from _result );

end if;
  
  if counter=0 then
  delete from keyword_alias where keyword=keyword1 and channel=channel1;
  insert IGNORE into keyword_alias (select keyword1,channel1,field from _result);
  
  end if;
  
  select counter from dual;
	END