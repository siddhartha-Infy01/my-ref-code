-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryCommMeasure`(networkId varchar(25),commodityId VARCHAR(300),priceTypes Varchar(50))
BEGIN
SET @vars = commodityId;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultComm;
        CREATE TEMPORARY TABLE _resultComm (field varchar(25) primary key);
        TRUNCATE TABLE _resultComm;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultComm', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
 SET @vars = priceTypes;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultLoc;
        CREATE TEMPORARY TABLE _resultLoc (field varchar(25) primary key);
        TRUNCATE TABLE _resultLoc;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultLoc', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;

select a.* from measure_factor a where a.network_id=networkId  and a.commodity_id in (select * from _resultComm) and a.price_type in (select * from _resultLoc) order by a.commodity_id;		
		
END