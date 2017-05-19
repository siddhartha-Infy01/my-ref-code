-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure queryAgentTargets;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryAgentTargets`(nwid VARCHAR(25),appId VARCHAR(25),userId VARCHAR(25),startDate VARCHAR(25), endDate VARCHAR(25))
BEGIN
		 
	DECLARE tmpAppId VARCHAR(25);
	DECLARE tmpTarget VARCHAR(25);	 
	DECLARE tmpEffDt datetime;
	DECLARE counter integer;
	DECLARE rowcounter integer;
	DECLARE exit_loop BOOLEAN DEFAULT 0;	   
	DECLARE agent_cursor CURSOR FOR SELECT application_id,target,MIN(effectiveDate) FROM _tmpResult group by application_id;
	DECLARE appid_cursor_clause CURSOR FOR SELECT DISTINCT application_id FROM AgentReportsView WHERE network_id = nwid;	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;
	 
	SET @STARTDATE :=  CAST(startDate AS DATE);
	SET @ENDDATE := CAST(endDate AS DATE);
	    
	 
	DROP TEMPORARY TABLE IF EXISTS _tmpResult;
	CREATE TEMPORARY TABLE _tmpResult (	 	 
    application_id varchar(25) NOT NULL,         
    target int(11) DEFAULT NULL,     
    effectiveDate DATE DEFAULT NULL);
	 
	DROP TEMPORARY TABLE IF EXISTS _tmpFinalResult;
	CREATE TEMPORARY TABLE _tmpFinalResult LIKE _tmpResult;			 
	
	IF appId = 'All Applications' and  userId = 'All Agents'  then
		TRUNCATE TABLE _tmpResult;
		INSERT INTO _tmpResult
		SELECT application_id, SUM(target), effective_date 
		FROM AgentReportsView 
		WHERE network_id = nwid AND effective_date BETWEEN @STARTDATE AND @ENDDATE 
		group by application_id, CONCAT(YEAR(effective_date),'-',MONTH(effective_date)) 
		order by effective_date;
		SET rowcounter = (SELECT COUNT(*) FROM _tmpResult);
		IF rowcounter = 0 THEN
			OPEN appid_cursor_clause;
			REPEAT
			FETCH appid_cursor_clause INTO tmpAppId;
			IF NOT exit_loop THEN
				SET counter = (SELECT COUNT(*)
				FROM AgentReportsView
				WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = tmpAppId
				GROUP BY application_id,CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
				ORDER BY effective_date DESC
				LIMIT 1);				
				IF counter > 0 THEN
					INSERT INTO _tmpFinalResult
					SELECT application_id, SUM(target), @STARTDATE
					FROM AgentReportsView
					WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE
					GROUP BY application_id,CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
					ORDER BY effective_date DESC
					LIMIT 1;
				ELSE
					INSERT INTO _tmpFinalResult VALUES (tmpAppId, 0, @STARTDATE);
				END IF;
			END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE appid_cursor_clause;
		ELSE
			OPEN agent_cursor;
			REPEAT
			FETCH agent_cursor INTO tmpAppId, tmpTarget, tmpEffDt;
			IF NOT exit_loop THEN		
				IF MONTH(@STARTDATE) != MONTH(tmpEffDt) OR YEAR(@STARTDATE) != YEAR(tmpEffDt) THEN			
					SET counter = (SELECT COUNT(*) FROM AgentReportsView 
					WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE
					group by application_id, CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
					ORDER By effective_date DESC
					LIMIT 1);												
					IF counter > 0 THEN
						INSERT INTO _tmpFinalResult 
						SELECT application_id, SUM(target), @STARTDATE
						FROM AgentReportsView 
						WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE
						GROUP BY application_id, CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
						ORDER BY effective_date DESC
						LIMIT 1;				
					ELSE
						INSERT INTO _tmpFinalResult VALUES (tmpAppId, 0, @STARTDATE);
					END IF;
				END IF;
			END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE agent_cursor;
			INSERT INTO _tmpFinalResult
			SELECT distinct(application_id), 0 , @STARTDATE
			FROM AgentReportsView
			WHERE network_id = nwid AND application_id NOT IN
			(SELECT distinct(application_id)
			FROM _tmpResult);
			INSERT INTO _tmpFinalResult SELECT * FROM _tmpResult;			
		END IF;			
		
	ELSEIF appId = 'All Applications' then
		TRUNCATE TABLE _tmpResult;
		INSERT INTO _tmpResult
		SELECT application_id, target, effective_date
		FROM AgentReportsView
		WHERE network_id = nwid AND effective_date BETWEEN @STARTDATE AND @ENDDATE AND user_id = userId  
		order by effective_date;				
		SET rowcounter = (SELECT COUNT(*) FROM _tmpResult);
		IF rowcounter = 0 THEN
			OPEN appid_cursor_clause;
			REPEAT
			FETCH appid_cursor_clause INTO tmpAppId;
			IF NOT exit_loop THEN
				SET counter = (SELECT COUNT(*)
				FROM AgentReportsView
				WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = tmpAppId AND user_id = userId				
				ORDER BY effective_date DESC
				LIMIT 1);				
				IF counter > 0 THEN
					INSERT INTO _tmpFinalResult
					SELECT application_id, target, @STARTDATE
					FROM AgentReportsView
					WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE 
					AND user_id = userId					
					ORDER BY effective_date DESC
					LIMIT 1;
				ELSE
					INSERT INTO _tmpFinalResult VALUES (tmpAppId, 0, @STARTDATE);
				END IF;
			END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE appid_cursor_clause;
		ELSE
			OPEN agent_cursor;
			REPEAT
			FETCH agent_cursor INTO tmpAppId, tmpTarget, tmpEffDt;
			IF NOT exit_loop THEN		
				IF MONTH(@STARTDATE) != MONTH(tmpEffDt) OR YEAR(@STARTDATE) != YEAR(tmpEffDt) THEN			
					SET counter = (SELECT COUNT(*) FROM AgentReportsView 
					WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE 
					AND user_id = userId					
					ORDER By effective_date DESC
					LIMIT 1);												
					IF counter > 0 THEN
						INSERT INTO _tmpFinalResult 
						SELECT application_id, target, @STARTDATE
						FROM AgentReportsView 
						WHERE network_id = nwid AND application_id = tmpAppId AND effective_date < @STARTDATE 
						AND user_id = userId						
						ORDER BY effective_date DESC
						LIMIT 1;				
					ELSE
						INSERT INTO _tmpFinalResult VALUES (tmpAppId, 0, @STARTDATE);
					END IF;
				END IF;
			END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE agent_cursor;
			INSERT INTO _tmpFinalResult
			SELECT distinct(application_id), 0 , @STARTDATE
			FROM AgentReportsView
			WHERE network_id = nwid AND application_id NOT IN
			(SELECT distinct(application_id)
			FROM _tmpResult);
			INSERT INTO _tmpFinalResult SELECT * FROM _tmpResult;			
		END IF;			
	
	ELSEIF userId = 'All Agents' then
		TRUNCATE TABLE _tmpResult;
		INSERT INTO _tmpResult
		SELECT application_id, SUM(target) target, effective_date
		FROM AgentReportsView
		WHERE network_id = nwid AND application_id = appId AND effective_date BETWEEN @STARTDATE AND @ENDDATE
		group by application_id, CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
		order by effective_date;		
		SET rowcounter = (SELECT COUNT(*) FROM _tmpResult);
		IF rowcounter = 0 THEN			
			SET counter = (SELECT COUNT(*)
			FROM AgentReportsView
			WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = appId
			GROUP BY application_id,CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
			ORDER BY effective_date DESC
			LIMIT 1);				
			IF counter > 0 THEN
				INSERT INTO _tmpFinalResult
				SELECT application_id, SUM(target), @STARTDATE
				FROM AgentReportsView
				WHERE network_id = nwid AND application_id = appId AND effective_date < @STARTDATE
				GROUP BY application_id,CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
				ORDER BY effective_date DESC
			LIMIT 1;
			ELSE
				INSERT INTO _tmpFinalResult VALUES (appId, 0, @STARTDATE);
			END IF;			
		ELSE				
			SELECT MIN(effectiveDate) INTO tmpEffDt FROM _tmpResult;
			IF MONTH(@STARTDATE) != MONTH(tmpEffDt) OR YEAR(@STARTDATE) != YEAR(tmpEffDt) THEN			
				SET counter = (SELECT COUNT(*) FROM AgentReportsView 
				WHERE network_id = nwid AND application_id = appId AND effective_date < @STARTDATE
				GROUP BY CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
				ORDER BY effective_date DESC
				LIMIT 1);												
				IF counter > 0 THEN
					INSERT INTO _tmpFinalResult 
					SELECT application_id, SUM(target), @STARTDATE
					FROM AgentReportsView 
					WHERE network_id = nwid AND application_id = appId AND effective_date < @STARTDATE
					GROUP BY CONCAT(YEAR(effective_date),'-',MONTH(effective_date))
					ORDER BY effective_date DESC
					LIMIT 1;				
				ELSE
					INSERT INTO _tmpFinalResult VALUES (appId, 0, @STARTDATE);
				END IF;
			END IF;			
			INSERT INTO _tmpFinalResult SELECT * FROM _tmpResult;			
		END IF;				
	ELSE
		TRUNCATE TABLE _tmpResult;
		INSERT INTO _tmpResult
		SELECT application_id, target, effective_date
		FROM AgentReportsView
		WHERE network_id = nwid AND application_id = appId AND user_id = userId AND effective_date 
		BETWEEN @STARTDATE AND @ENDDATE 
		order by effective_date;		
		SET rowcounter = (SELECT COUNT(*) FROM _tmpResult);
		IF rowcounter = 0 THEN			
			SET counter = (SELECT COUNT(*)
			FROM AgentReportsView
			WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = appId AND user_id = userId			
			ORDER BY effective_date DESC
			LIMIT 1);				
			IF counter > 0 THEN
				INSERT INTO _tmpFinalResult
				SELECT application_id, target, @STARTDATE
				FROM AgentReportsView
				WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = appId AND user_id = userId				
				ORDER BY effective_date DESC
			LIMIT 1;
			ELSE
				INSERT INTO _tmpFinalResult VALUES (appId, 0, @STARTDATE);
			END IF;			
		ELSE				
			SELECT MIN(effectiveDate) INTO tmpEffDt FROM _tmpResult;
			IF MONTH(@STARTDATE) != MONTH(tmpEffDt) OR YEAR(@STARTDATE) != YEAR(tmpEffDt) THEN			
				SET counter = (SELECT COUNT(*) FROM AgentReportsView 
				WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = appId AND user_id = userId				
				ORDER BY effective_date DESC
				LIMIT 1);												
				IF counter > 0 THEN
					INSERT INTO _tmpFinalResult 
					SELECT application_id, target, @STARTDATE
					FROM AgentReportsView 
					WHERE network_id = nwid AND effective_date < @STARTDATE AND application_id = appId AND user_id = userId					
					ORDER BY effective_date DESC
					LIMIT 1;				
				ELSE
					INSERT INTO _tmpFinalResult VALUES (appId, 0, @STARTDATE);
				END IF;
			END IF;			
			INSERT INTO _tmpFinalResult SELECT * FROM _tmpResult;			
		END IF;				
	END IF;
	SELECT application_id,target,CONCAT(YEAR(effectiveDate),'-',MONTH(effectiveDate)) effectiveDate 
	FROM _tmpFinalResult;
	
	DROP TEMPORARY TABLE IF EXISTS _tmpFinalResult;
	DROP TEMPORARY TABLE IF EXISTS _tmpResult;	
	
END