drop procedure if exists queryRecentlyAdded;

DELIMITER $$
CREATE  PROCEDURE `queryRecentlyAdded`(nwid VARCHAR(25),time_param int,commodityids VARCHAR(25),locationids VARCHAR(25),occupationids VARCHAR(25))
BEGIN


     DROP TABLE IF EXISTS _tmpshare;
	 CREATE TEMPORARY TABLE _tmpshare (nwtid varchar(25));
	 TRUNCATE TABLE _tmpshare;
     INSERT INTO _tmpshare SELECT from_share FROM user_shares WHERE to_share = nwid AND share_item='people' AND auth_stat = 'A';

       SET @vars = commodityids;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultComm;
        CREATE TEMPORARY TABLE _resultComm (field varchar(25) primary key);
        TRUNCATE TABLE _resultComm;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultComm', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;

	    DROP TABLE IF EXISTS _resultComm1;
        CREATE TEMPORARY TABLE _resultComm1 LIKE _resultComm;
		REPEAT
        TRUNCATE TABLE _resultComm1;
        INSERT INTO _resultComm1 SELECT a.commodity_id
        FROM commodities a JOIN  _resultComm b ON b.field = a.parent_id;
        INSERT IGNORE INTO _resultComm SELECT * FROM _resultComm1 ;
        UNTIL ROW_COUNT() = 0
        END REPEAT;

        SET @vars = locationids;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultLoc;
        CREATE TEMPORARY TABLE _resultLoc (field varchar(25) primary key);
        TRUNCATE TABLE _resultLoc;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultLoc', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;

		DROP TABLE IF EXISTS _resultLoc1;
        CREATE TEMPORARY TABLE _resultLoc1 LIKE _resultLoc;
		REPEAT
        TRUNCATE TABLE _resultLoc1;
        INSERT INTO _resultLoc1 SELECT a.location_id
        FROM locations a JOIN  _resultLoc b ON b.field = a.parent_id;
        INSERT IGNORE INTO _resultLoc SELECT * FROM _resultLoc1 ;
        UNTIL ROW_COUNT() = 0
        END REPEAT;

		SET @vars = occupationids;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _resultOcc;
        CREATE TEMPORARY TABLE _resultOcc (field varchar(25) primary key);
        TRUNCATE TABLE _resultOcc;
        
        SET @sql := CONCAT('INSERT INTO ', '_resultOcc', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;

        DROP TABLE IF EXISTS _resultOcc1;
        CREATE TEMPORARY TABLE _resultOcc1 LIKE _resultLoc;
		REPEAT
        TRUNCATE TABLE _resultOcc1;
        INSERT INTO _resultOcc1 SELECT a.occupation_id
        FROM occupations a JOIN  _resultOcc b ON b.field = a.parent_id;
        INSERT IGNORE INTO _resultOcc SELECT * FROM _resultOcc1 ;
        UNTIL ROW_COUNT() = 0
        END REPEAT;


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
	PRIMARY KEY (`people_id`));

 DROP TABLE IF EXISTS _tmp_people1;
 CREATE TEMPORARY TABLE _tmp_people1 (  `people_id` VARCHAR(50) NOT NULL DEFAULT '',
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

 DROP TABLE IF EXISTS _tmp_people2;
 CREATE TEMPORARY TABLE _tmp_people2 (  `people_id` VARCHAR(50) NOT NULL DEFAULT '',
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

   TRUNCATE TABLE _tmp_people;
   TRUNCATE TABLE _tmp_people1;
   TRUNCATE TABLE _tmp_people2;
       
    if commodityids !='' and  locationids !='' and occupationids !='' then
	
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        		
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        	
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    
	        	
    SELECT * FROM _tmp_people a,_tmp_people1 b,_tmp_people2 c where a.people_id=b.people_id and b.people_id=c.people_id and c.people_id=a.people_id;
	
	elseif commodityids !='' and locationids !='' and occupationids='' then
	
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	       		
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        
	
	SELECT * FROM _tmp_people1 a,_tmp_people2 b where a.people_id=b.people_id;
	
	elseif locationids !='' and occupationids !='' and commodityids='' then

	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    
	        	
	SELECT * FROM _tmp_people1 a,_tmp_people2 b where a.people_id=b.people_id;
	
	elseif occupationids !='' and commodityids !='' and locationids='' then
	
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
	
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	       
	INSERT IGNORE INTO _tmp_people2 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        
	
	SELECT * FROM _tmp_people1 a,_tmp_people2 b where a.people_id=b.people_id;
	
	elseif commodityids !='' then
	
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_commodities u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and u.commodity_id in (select * from _resultComm) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    	        
	
	select * from _tmp_people1;
	
	elseif locationids !='' then
	
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_locations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.location_id in (select * from _resultLoc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    
	        
	select * from _tmp_people1;
	
	elseif occupationids !='' then
	
	INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=nwid and p.people_id=u.user_id and p.default_network_id=n.network_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) AND p.record_status='A'and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;
    INSERT IGNORE INTO _tmp_people1 SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n,user_occupations u WHERE p.default_network_id=n.network_id and p.people_id=u.user_id and n.record_status='A' and  u.occupation_id in (select * from _resultOcc) and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day)) and p.default_network_id=u.network_id;    
	        	
	select * from _tmp_people1;

    else 

	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'Y',p.default_network_id FROM people p, Networks n WHERE p.default_network_id=nwid and p.default_network_id=n.network_id and n.record_status='A'AND p.record_status='A' and p.created_ts > date((sysdate() - interval time_param day));
	INSERT IGNORE INTO _tmp_people SELECT p.people_id,p.first_name,p.last_name,p.nickname,p.gender,p.town,p.country,p.currency_id,p.language_id,p.msisdn,p.email,'N',p.default_network_id FROM people p, Networks n WHERE p.default_network_id=n.network_id and n.record_status='A' and  p.default_network_id in ( select * from _tmpshare) and p.created_ts > date((sysdate() - interval time_param day));    
                
	SELECT * FROM _tmp_people;

    end if;
  
END