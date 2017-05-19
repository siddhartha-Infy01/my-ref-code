drop procedure `queryResellerPrices`;
DELIMITER $$
CREATE DEFINER=`esoko`@`%` PROCEDURE `queryResellerPrices`(nwid VARCHAR(25))
BEGIN

DECLARE v_from_share varchar (1000);
DECLARE v_share_type varchar(1000);
DECLARE exit_loop BOOLEAN DEFAULT 0; 
DECLARE count INT DEFAULT 0;

 DECLARE share_cursor CURSOR FOR
    select subscription_id,subscription_type,param_name,param_value,margin 
    from subscription_details where subscription_level = 'D' 
    and subscription_category = 'P';
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;

 DROP TABLE IF EXISTS _tmp_reseller;
 CREATE TEMPORARY TABLE _tmp_reseller (  
`subscription_id` varchar(50) NOT NULL DEFAULT '',
  `subscription_type` varchar(150) DEFAULT NULL,
  `param_name` varchar(45) DEFAULT NULL,
  `param_value` varchar(25) DEFAULT NULL,
  `margin` varchar(25) DEFAULT NULL);
 TRUNCATE TABLE _tmp_reseller;
  
	INSERT IGNORE INTO _tmp_reseller select subscription_id,subscription_type,param_name,param_value,margin 
    from subscription_details where subscription_level = 'D' 
    and subscription_category = 'P';    
	set SQL_SAFE_UPDATES = 0;

update _tmp_reseller a,subscription_details b set a.param_value = (select param_value from  subscription_details 
where param_name = a.param_name and country_name = (select primary_location_id from Networks where network_id = nwid)
and subscription_type = a.subscription_type) 
where a.param_name = b.param_name and a.subscription_type=b.subscription_type and b.country_name = (select primary_location_id from Networks where network_id = nwid)
;

update _tmp_reseller a,subscription_details b set a.param_value = (select param_value from  subscription_details 
where param_name = a.param_name and country_name = nwid
and subscription_type = a.subscription_type) 
where a.param_name = b.param_name and a.subscription_type=b.subscription_type and b.country_name = nwid
;
  		select * from _tmp_reseller order by subscription_id;  
END$$
DELIMITER ;
