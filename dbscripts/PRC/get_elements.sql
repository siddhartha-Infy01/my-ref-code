-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure get_elements;
delimiter $$

CREATE PROCEDURE `esoko`.`get_elements` (choice VARCHAR(25), elements VARCHAR(255))
BEGIN
		SET @vars = elements;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultTable;
        CREATE TEMPORARY TABLE _resultTable (field varchar(25) primary key);
        TRUNCATE TABLE _resultTable;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultTable', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;

	    DROP TABLE IF EXISTS _tmpTable;
        CREATE TEMPORARY TABLE _tmpTable LIKE _resultTable;
		IF choice = 'Commodity' THEN
			REPEAT
			TRUNCATE TABLE _tmpTable;
			INSERT INTO _tmpTable SELECT a.commodity_id
			FROM commodities a JOIN  _resultTable b ON b.field = a.parent_id;
			INSERT IGNORE INTO _resultTable SELECT * FROM _tmpTable ;
			UNTIL ROW_COUNT() = 0
			END REPEAT;
		
		ELSEIF choice = 'Location' THEN			
			REPEAT
			TRUNCATE TABLE _tmpTable;
			INSERT INTO _tmpTable SELECT a.location_id
			FROM locations a JOIN  _resultTable b ON b.field = a.parent_id;
			INSERT IGNORE INTO _resultTable SELECT * FROM _tmpTable ;
			UNTIL ROW_COUNT() = 0
			END REPEAT;
			
		ELSEIF choice = 'Occupation' THEN
			REPEAT
			TRUNCATE TABLE _tmpTable;
			INSERT INTO _tmpTable SELECT a.occupation_id
			FROM occupations a JOIN  _resultTable b ON b.field = a.parent_id;
			INSERT IGNORE INTO _resultTable SELECT * FROM _tmpTable ;
			UNTIL ROW_COUNT() = 0
			END REPEAT;
		END IF;
				
	SELECT * FROM _resultTable;
END