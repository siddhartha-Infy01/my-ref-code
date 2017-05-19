-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure queryUngrouped;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryUngrouped`(nwid VARCHAR(25))
BEGIN


     DROP TABLE IF EXISTS _tmpshare;
	 CREATE TEMPORARY TABLE _tmpshare (nwtid varchar(25));
	 TRUNCATE TABLE _tmpshare;
     INSERT INTO _tmpshare SELECT from_share FROM user_shares WHERE to_share = nwid AND share_item='people' AND auth_stat = 'A';


 DROP TABLE IF EXISTS _tmp_people_ungroup;
 CREATE TEMPORARY TABLE _tmp_people_ungroup (  `people_id` VARCHAR(50) NOT NULL DEFAULT '',
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
 PRIMARY KEY (`people_id`));
 TRUNCATE TABLE _tmp_people_ungroup;
       
	INSERT INTO _tmp_people_ungroup SELECT people_id,first_name,last_name,nickname,gender,town,country,currency_id,language_id,msisdn,email,'Y',default_network_id FROM people WHERE default_network_id=nwid AND record_status='A';
	
	INSERT IGNORE INTO _tmp_people_ungroup SELECT people_id,first_name,last_name,nickname,gender,town,country,currency_id,language_id,msisdn,email,'N',default_network_id FROM people WHERE default_network_id in ( select * from _tmpshare);    			               
         
	SELECT * FROM _tmp_people_ungroup where people_id not in(select user_id from user_group where network_id=nwid); 
	
	drop table _tmp_people_ungroup;
	drop table _tmpshare;
   
END