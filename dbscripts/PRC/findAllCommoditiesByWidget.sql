drop procedure findAllCommoditiesByWidget;
delimiter $$
CREATE PROCEDURE findAllCommoditiesByWidget(usrid VARCHAR(25),widgetid VARCHAR(25))
BEGIN
declare commlist varchar(1000);

select commodity_list into commlist from user_widget_settings where user_id=usrid  and widget_id=widgetid;
SET @vars = commlist;
SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
DROP TABLE IF EXISTS _result;
CREATE TEMPORARY TABLE _result (field varchar(25) PRIMARY KEY);
TRUNCATE TABLE _result;

if @vars is not null	then 	
        SET @sql := CONCAT('INSERT INTO ', '_result', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;

		
 DROP TABLE IF EXISTS _tmp10;
  CREATE TEMPORARY TABLE _tmp10 LIKE _result;
  REPEAT
    TRUNCATE TABLE _tmp10;
    INSERT INTO _tmp10 SELECT commodity_id  AS field
      FROM _result JOIN commodities  ON field = parent_id;

    INSERT IGNORE INTO _result SELECT * FROM _tmp10;
  
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
    DROP TABLE _tmp10;
		
			select* from commodities where commodity_id in (select * from _result);
	END