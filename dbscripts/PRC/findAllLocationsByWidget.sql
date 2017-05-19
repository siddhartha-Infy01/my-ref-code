drop procedure findAllLocationsByWidget;
delimiter $$
CREATE PROCEDURE findAllLocationsByWidget(usrid VARCHAR(25),widgetid VARCHAR(25))
BEGIN
declare loclist varchar(1000);

select location_list into loclist from user_widget_settings where user_id=usrid  and widget_id=widgetid;
SET @vars = loclist;
SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
DROP TABLE IF EXISTS _result1;
CREATE TEMPORARY TABLE _result1 (field varchar(25) PRIMARY KEY);
TRUNCATE TABLE _result1;

if @vars is not null	then 	
        SET @sql := CONCAT('INSERT INTO ', '_result1', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;
	
		DROP TABLE IF EXISTS _tmloc;
  CREATE TEMPORARY TABLE _tmloc LIKE _result1;
  REPEAT
    TRUNCATE TABLE _tmloc;
    INSERT INTO _tmloc SELECT location_id  AS field
      FROM _result1 JOIN locations  ON field = parent_id;

    INSERT IGNORE INTO _result1 SELECT * FROM _tmloc;
  
UNTIL ROW_COUNT() = 0
  END REPEAT;
  
    DROP TABLE _tmloc;
		       
			  select * from locations where location_id in (select * from _result1);
		

END 