-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryAllGroupsDetails`(nwid VARCHAR(25), calCost CHAR(1))
BEGIN
		
	DECLARE loperatorId VARCHAR(25);
	DECLARE exit_loop BOOLEAN DEFAULT 0;	
	DECLARE operatorCursor CURSOR FOR select distinct operator_id from _temp_group;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;
   
 DROP TEMPORARY TABLE IF EXISTS _tmp_people_in_group;
 CREATE TEMPORARY TABLE _tmp_people_in_group (  `people_id` VARCHAR(50) NOT NULL DEFAULT '',
  `first_name` VARCHAR(150) DEFAULT NULL,
  `last_name` VARCHAR(150) DEFAULT NULL,
  `nickname` VARCHAR(45) DEFAULT NULL,
  `gender` VARCHAR(25) DEFAULT NULL,
  `town` VARCHAR(25) DEFAULT NULL,
  `country` VARCHAR(25) DEFAULT NULL,
  `currency_id` VARCHAR(20) DEFAULT NULL,
  `language_id` VARCHAR(20) DEFAULT NULL,
  `msisdn` VARCHAR(25) DEFAULT NULL,
  `email` VARCHAR(150) DEFAULT NULL,
  `edit_flag` CHAR(2) NOT NULL,
  `default_network_id` VARCHAR(25) NOT NULL DEFAULT '',
  `operator_id` VARCHAR(25) DEFAULT NULL,
 PRIMARY KEY (`people_id`));
       
INSERT INTO _tmp_people_in_group SELECT people_id,first_name,last_name,nickname,gender,town,country,currency_id,language_id,msisdn,email,'Y',default_network_id,operator_id FROM people WHERE default_network_id=nwid AND record_status='A';
					
INSERT IGNORE INTO _tmp_people_in_group SELECT people_id,first_name,last_name,nickname,gender,town,country,currency_id,language_id,msisdn,email,'N',default_network_id,operator_id FROM people WHERE default_network_id in ( SELECT from_share FROM user_shares WHERE to_share = nwid AND share_item='people' AND auth_stat = 'A');					           
	
DROP TEMPORARY TABLE IF EXISTS _temp_group;
 CREATE TEMPORARY TABLE _temp_group (  `group_id` VARCHAR(50) NOT NULL DEFAULT '',
  `type` VARCHAR(150) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `group_name` varchar(100),
  `people_id` VARCHAR(45) DEFAULT NULL,
  `first_name` VARCHAR(25) DEFAULT NULL,
  `last_name` VARCHAR(25) DEFAULT NULL,
  `gender` char(5),
  `country` VARCHAR(25) DEFAULT NULL,
  `msisdn` VARCHAR(25) DEFAULT NULL,
  `email` VARCHAR(150) DEFAULT NULL,
  `default_network_id` VARCHAR(25) NOT NULL DEFAULT '',
  `cost` decimal(22,3) DEFAULT 0.0,
  `operator_id` VARCHAR(25) DEFAULT NULL);
	
  
insert into _temp_group select a.group_id, a.type, (select count(*) from user_group where  
group_id=a.group_id and network_id=nwid) as count, a.GROUP_name,b.people_id,b.first_name,b.last_name,b.gender,b.country,b.msisdn,b.email,b.default_network_id,0,b.operator_id
from group_master a,_tmp_people_in_group b,user_group c  where a.network_id=nwid and type='N' and c.user_id=b.people_id and c.network_id=nwid and c.group_id=a.group_id and a.group_id!='Administrator' ;

   
 insert into _temp_group select a.group_id, a.type, (select count(*) from user_smart_group where  
group_id=a.group_id and network_id=nwid) as count, a.GROUP_name,b.people_id,b.first_name,b.last_name,b.gender,b.country,b.msisdn,b.email,b.default_network_id,0,b.operator_id
from group_master a,_tmp_people_in_group b,user_smart_group c  where a.network_id=nwid and type='S' and c.user_id=b.people_id and c.network_id=nwid and c.group_id=a.group_id; 

SELECT COUNT(*) INTO @LCOUNT FROM _tmp_people_in_group 
where people_id not in(select user_id from user_group where network_id=nwid);

INSERT INTO _temp_group SELECT 'Ungrouped', 'N', @LCOUNT as count, 'Ungrouped',b.people_id,b.first_name,b.last_name,b.gender,b.country,b.msisdn,b.email,b.default_network_id,0,b.operator_id
FROM _tmp_people_in_group b where people_id not in(select user_id from user_group where network_id=nwid);

insert into _temp_group (group_id,group_name,type,count,default_network_id) select group_id,group_name,type,'0',nwid
from group_master where group_id not in (select group_id from user_group where network_id = nwid) and type='N' and network_id=nwid ;

insert into _temp_group (group_id,group_name,type,count,default_network_id) select group_id,group_name,type,'0',nwid
from group_master where group_id not in (select group_id from user_smart_group where network_id = nwid) and type='S' and network_id=nwid ;

IF calCost = 'Y' THEN	
	SET exit_loop = 0;
	OPEN operatorCursor;		
		REPEAT
			FETCH operatorCursor INTO loperatorId;				
			IF NOT exit_loop THEN												
				call deriveCostForOperator(nwid,'N',loperatorId,@baseCost, @retailCost, @wholesaleCost);
				SET @COST = @baseCost + @retailCost + @wholesaleCost;
				UPDATE _temp_group SET cost = @COST WHERE operator_id = loperatorId;				
			END IF;
			UNTIL exit_loop
		END REPEAT;
	CLOSE operatorCursor;
END IF;

select * from _temp_group order by group_id;
   
END