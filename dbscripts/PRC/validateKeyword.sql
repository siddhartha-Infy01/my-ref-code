-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DROP PROCEDURE `validateKeyword`;

DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `validateKeyword`(id VARCHAR(45),
			nwid VARCHAR(45), keyword VARCHAR(100), channel VARCHAR(45), appName VARCHAR(45))
BEGIN
		
	DROP TEMPORARY TABLE IF EXISTS _tmp_keyword;
	CREATE TEMPORARY TABLE _tmp_keyword (  
	`id` VARCHAR(50) NOT NULL DEFAULT '',
	`keyword` VARCHAR(150) DEFAULT NULL,
	`nwid` VARCHAR(150) DEFAULT NULL,
	`channel` VARCHAR(45) DEFAULT NULL);
	
	INSERT INTO _tmp_keyword SELECT push_alert_id,alert_code,payee_network_id,''
	FROM push_alert_master where alert_code IS NOT NULL;
	
	INSERT INTO _tmp_keyword SELECT crop_tip_id,alert_code,network_id,''
	FROM crop_tip_master where alert_code IS NOT NULL;
	
	INSERT INTO _tmp_keyword SELECT poll_id,public_keyword,network_id,''
	FROM poll_master where public_keyword IS NOT NULL;
	
	INSERT INTO _tmp_keyword SELECT p.poll_id,p.keyword, (select network_id from poll_master where poll_id=p.poll_id),''
	FROM poll_keywords p;
	
	INSERT INTO _tmp_keyword SELECT p.poll_id,p.alias, (select network_id from poll_master where poll_id=p.poll_id),''
	FROM poll_alias p;
	
	INSERT INTO _tmp_keyword SELECT sms_id,sms_Code, network_id,''
	FROM sms_codes where network_id=nwid;
	
	INSERT INTO _tmp_keyword SELECT smid_id,alias_id, network_id,''
	FROM sms_code_alias where network_id=nwid;
	
	
	IF appName = 'KEYWORD' THEN
		INSERT INTO _tmp_keyword SELECT k.keyword,k.keyword, k.network_id,k.channel
		FROM keywords k where k.channel=channel;
		
		INSERT INTO _tmp_keyword SELECT k.keyword,k.alias,(SELECT l.network_id from keywords l where l.keyword = k.keyword),k.channel
		FROM keyword_alias k where k.channel=channel;
	ELSE
		INSERT INTO _tmp_keyword SELECT k.keyword,k.keyword, k.network_id,k.channel
		FROM keywords k;
		
		INSERT INTO _tmp_keyword SELECT k.keyword,k.alias, (SELECT l.network_id from keywords l where l.keyword = k.keyword),k.channel
		FROM keyword_alias k;
	END IF;
	
	SELECT * FROM _tmp_keyword t where t.keyword = keyword and t.id != id;
END