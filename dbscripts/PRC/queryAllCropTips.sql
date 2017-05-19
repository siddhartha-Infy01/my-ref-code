-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure queryAllCropTips;
DELIMITER $$

CREATE PROCEDURE `esoko`.`queryAllCropTips` (lshow VARCHAR(25), sort VARCHAR(25), networkId VARCHAR(25))
BEGIN

	SET @query = 'select crop_tip_id,crop_tip_name,category,
				custom_category,commodity_id,alert_state,trigger_type,
				number_of_tips,network_id from crop_tip_master ';
	
	SET @orderBy = null;
	
	IF sort = 'NAME' THEN
		SET @orderBy = ' order by crop_tip_name';
		
	ELSEIF sort = 'TYPE' THEN
		SET @orderBy = ' order by category';
		
	ELSEIF sort = 'COMMODITY' THEN
		SET @orderBy = 'order by commodity_id';
		
	ELSEIF sort = 'STATUS' THEN
		SET @orderBy = ' order by alert_state';
		
	END IF;
	
	IF lshow = 'ALL' THEN
		SET @sql := CONCAT(@query, @orderBy);

	ELSEIF lshow = 'MY' THEN
		SET @sql := CONCAT(@query, 'WHERE network_id=',"'",networkId,"'", @orderBy);
		
	ELSEIF lshow = 'OTHER' THEN
		SET @sql := CONCAT(@query, 'WHERE network_id !=',"'",networkId,"'",' AND trigger_type="SMS"', @orderBy);				
	
	END IF;
	
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
END