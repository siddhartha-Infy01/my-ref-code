-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure searchAllPeople;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `searchAllPeople`(nwid VARCHAR(25))
BEGIN

	DECLARE countryNwk VARCHAR(25);	 
	DROP TABLE IF EXISTS _tmpshare;
	CREATE TEMPORARY TABLE _tmpshare (nwtid varchar(25));
	TRUNCATE TABLE _tmpshare;
	INSERT INTO _tmpshare SELECT from_share FROM user_shares WHERE to_share = nwid AND share_item='people' AND auth_stat = 'A';
	
	SET countryNwk = (select network_id from Networks where primary_location_id=(select primary_location_id from Networks where network_id=nwid) and type='K');		
		
	DROP TABLE IF EXISTS _tmp_people;
	CREATE TEMPORARY TABLE _tmp_people (  `people_id` VARCHAR(50) NOT NULL DEFAULT '',
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
	`network_type` VARCHAR(25) NOT NULL,
	PRIMARY KEY (`people_id`));

	TRUNCATE TABLE _tmp_people;
	
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id,'MyNetwork' FROM people p, Networks n WHERE p.default_network_id=nwid and p.default_network_id=n.network_id and n.record_status='A'AND p.record_status='A';
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id,'MyNetwork' FROM people p, Networks n WHERE p.default_network_id=n.network_id and n.record_status='A' and  p.default_network_id in ( select * from _tmpshare);    
                
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id,'CountryNetwork' FROM people p, Networks n WHERE p.default_network_id=countryNwk and p.default_network_id=n.network_id and n.record_status='A'AND p.record_status='A';
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id,'PublicNetwork' FROM people p WHERE p.default_network_id in (select network_id from Networks where is_private='N' and network_id not in (nwid,countryNwk) and network_id not in (select * from _tmpshare) and record_status='A') AND p.record_status='A' ;
	SELECT * FROM _tmp_people;	
  
END