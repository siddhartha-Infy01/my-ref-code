-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure queryAgentUploads;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryAgentUploads`(nwid VARCHAR(25),appId VARCHAR(25),userId VARCHAR(25),effectiveDates VARCHAR(1000))
BEGIN		 
	
	DECLARE counter integer;
	DECLARE tmpDetailId int(11);
	DECLARE tmpEffDate VARCHAR(25);	 
	DECLARE tmpUploads int(11);
	DECLARE tmpUploadAccuracy int(11);
	DECLARE tmpUserId varchar(25);
	DECLARE tmpAppId varchar(25);
	DECLARE tmpTarget int(11);
	DECLARE tmpBonus decimal(22,3);
	DECLARE tmpIncentive decimal(22,3);
	DECLARE tmpCost decimal(22,3);
	DECLARE exit_loop BOOLEAN DEFAULT 0;	   
	DECLARE agent_upload CURSOR FOR SELECT * FROM _tmpAgentUploads;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;
	
	DROP TEMPORARY TABLE IF EXISTS _tmpAgentDetails;
	CREATE TEMPORARY TABLE _tmpAgentDetails (	 
	detail_id int(11) NOT NULL,	
    application_id varchar(25) NOT NULL,                 
    user_id varchar(25) NOT NULL);
	
	DROP TEMPORARY TABLE IF EXISTS _tmpAgentUploads;
	CREATE TEMPORARY TABLE _tmpAgentUploads (	 
	detail_id int(11) NOT NULL,	
    effective_date varchar(25) NOT NULL,                 
    uploads int(11) NOT NULL,
	upload_accuracy int(11) NOT NULL);
	
	DROP TEMPORARY TABLE IF EXISTS _tmpFinalUploads;
	CREATE TEMPORARY TABLE _tmpFinalUploads (
	detail_id int(11) NOT NULL,
	user_id varchar(25) NOT NULL,
	application_id varchar(25) NOT NULL,
	uploads int(11) NOT NULL,
	bonus decimal(22,2) DEFAULT NULL,
	incentive decimal(22,2) DEFAULT NULL,
	cost decimal(22,2) DEFAULT NULL,
	effective_date varchar(25) NOT NULL
	);
	
	IF appId = 'All Applications' and  userId = 'All Agents'  then
		TRUNCATE TABLE _tmpAgentDetails;
		INSERT INTO _tmpAgentDetails
		SELECT detail_id, application_id, user_id 
		FROM agent_details
		WHERE network_id = nwid AND record_status = 'A';
		
	ELSEIF appId = 'All Applications' then
		TRUNCATE TABLE _tmpAgentDetails;
		INSERT INTO _tmpAgentDetails
		SELECT detail_id, application_id, user_id 
		FROM agent_details
		WHERE network_id = nwid AND record_status = 'A' AND user_id = userId;			
	
	ELSEIF userId = 'All Agents' then
		TRUNCATE TABLE _tmpAgentDetails;
		INSERT INTO _tmpAgentDetails
		SELECT detail_id, application_id, user_id 
		FROM agent_details
		WHERE network_id = nwid AND record_status = 'A' AND application_id = appId;	
			
	ELSE
		TRUNCATE TABLE _tmpAgentDetails;
		INSERT INTO _tmpAgentDetails
		SELECT detail_id, application_id, user_id 
		FROM agent_details
		WHERE network_id = nwid AND record_status = 'A' AND user_id = userId AND application_id = appId;
	END IF;
	
	TRUNCATE TABLE _tmpAgentUploads;
	SET @sql  := CONCAT('INSERT INTO _tmpAgentUploads
	SELECT * FROM agent_upload_count 
	WHERE detail_id in (select detail_id from _tmpAgentDetails) and effective_date in (',effectiveDates,')');	
	PREPARE stmt from @sql;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	SET counter = (SELECT COUNT(*) FROM _tmpAgentUploads);
	IF NOT counter = 0 THEN
		SET counter = 0;
		TRUNCATE TABLE _tmpFinalUploads;
		OPEN agent_upload;
			REPEAT
			FETCH agent_upload INTO tmpDetailId, tmpEffDate, tmpUploads, tmpUploadAccuracy;
			IF NOT exit_loop THEN
				SET @finaleffdate := tmpEffDate;
				SET counter = (SELECT COUNT(*)
								FROM AgentReportsView
								WHERE detail_id = tmpDetailId
								AND CONCAT(YEAR(effective_date),'-',MONTH(effective_date)) = tmpEffDate);
				IF NOT counter > 0 THEN
					SET @tdate := CONCAT(tmpEffDate,'-1');					
					SET @effDate := (SELECT MAX(effective_date)
									FROM AgentReportsView
									WHERE detail_id = tmpDetailId
									AND effective_date < CAST(@tdate AS DATE)
									LIMIT 1);					
					SET tmpEffDate	= CONCAT(YEAR(@effDate),'-',MONTH(@effDate));												
				END IF;
				
				SELECT user_id,application_id,target ,bonus,incentive 
				INTO tmpUserId,tmpAppId,tmpTarget,tmpBonus,tmpIncentive
				FROM AgentReportsView
				WHERE detail_id = tmpDetailId
				AND CONCAT(YEAR(effective_date),'-',MONTH(effective_date)) = tmpEffDate;
				
				IF tmpUploads >= tmpTarget THEN
					SET tmpCost = ((tmpIncentive * tmpUploadAccuracy) + tmpBonus);
				ELSE
					SET tmpCost = (tmpIncentive * tmpUploadAccuracy);
				END IF;
				
				INSERT INTO _tmpFinalUploads 
				VALUES(tmpDetailId,tmpUserId,tmpAppId,tmpUploads,tmpBonus,tmpIncentive,tmpCost,@finaleffdate);
			END IF;
			UNTIL exit_loop
			END REPEAT;
		CLOSE agent_upload;
	ELSE
		INSERT INTO _tmpFinalUploads 
		VALUES(0,userId,appId,0,0,0,0,CONCAT(YEAR(NOW()),'-',MONTH(NOW())));
	END IF;
	
	/*SELECT * FROM _tmpFinalUploads;*/
	SELECT application_id,SUM(uploads) uploads,SUM(bonus) bonus,SUM(incentive) incentive,SUM(cost) cost, effective_date
	FROM _tmpFinalUploads
	GROUP BY application_id,effective_date;	
	
END