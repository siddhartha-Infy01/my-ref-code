-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- 
drop procedure queryAllPolls;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryAllPolls`(pollType VARCHAR(25), sortBy VARCHAR(25))
BEGIN
	
	DECLARE lCount INTEGER(11);
	DECLARE lRecipientType varchar(10);
	DECLARE lRecipientId varchar(100);
	DECLARE lPollId varchar(100);
	DECLARE lNetworkId varchar(100);
	DECLARE exit_loop BOOLEAN DEFAULT 0;
	DECLARE pollCursor CURSOR FOR select poll_id,network_id from _POLL_SUMM;
	DECLARE recipientCursor CURSOR FOR select recipient_type,recipient_id from push_alert_recipients where push_alert_id=lPollId;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;
	
	DROP TEMPORARY TABLE IF EXISTS _POLL_SUMM;
	CREATE TEMPORARY TABLE _POLL_SUMM (	
	`poll_id` VARCHAR(100),
	`network_id` VARCHAR(100),
	`poll_name` VARCHAR(100),
	`participants` INTEGER(11),
	`responses` INTEGER(11),
	`start_date` date,
	`end_date` date,
	`poll_state` CHAR(3),
	`poll_ques` LONGTEXT,
	PRIMARY KEY (`poll_id`)
 );
	
	IF pollType = 'ALL' THEN
		insert into _POLL_SUMM select poll_id,network_id,poll_name,
		0 as participants,0 as responses,start_date,end_date,poll_state,
		(select poll_ques from poll_details l 
		where poll_id=m.poll_id and ques_no='1') as pollQues
		from poll_master m;
	
	ELSEIF pollType = 'ACTIVE' THEN		
		insert into _POLL_SUMM select poll_id,network_id,poll_name,
		0 as participants,0 as responses,start_date,end_date,poll_state,
		(select poll_ques from poll_details l 
		where poll_id=m.poll_id and ques_no='1') as pollQues
		from poll_master m where poll_active_till >= now();
	
	ELSE
		insert into _POLL_SUMM select poll_id,network_id,poll_name,
		0 as participants,0 as responses,start_date,end_date,poll_state,
		(select poll_ques from poll_details l 
		where poll_id=m.poll_id and ques_no='1') as pollQues
		from poll_master m where poll_active_till < now();
	
	END IF;
	
	OPEN pollCursor;
		REPEAT
			FETCH pollCursor INTO lPollId,lNetworkId;		
			IF NOT exit_loop THEN				
				SET lCount = 0;
				SET @COUNTER = (select count(*) from push_alert_recipients where push_alert_id=lPollId);				
				IF @COUNTER > 0 THEN
					OPEN recipientCursor;
						REPEAT
							FETCH recipientCursor INTO lRecipientType,lRecipientId;							
							IF NOT exit_loop THEN								
								IF lRecipientType = 'U' THEN
									SET lCount = lCount + 1;
									UPDATE _POLL_SUMM
									SET participants = lCount
									WHERE poll_id = lPollId;
								ELSE
									SET @GROUPTYPE = (SELECT type from group_master where group_id=lRecipientId AND network_id=lNetworkId);
									SET @TEMP = 0;
									IF @GROUPTYPE = 'S' THEN										
										SET @TEMP = (SELECT COUNT(*) from user_smart_group 
													where group_id=lRecipientId AND network_id=lNetworkId 
													group by group_id);										
									ELSE
										SET @TEMP = (SELECT COUNT(*) from user_group 
													where group_id=lRecipientId AND network_id=lNetworkId 
													group by group_id);
										IF @TEMP IS NULL  THEN
											SET @TEMP = 0;
										END IF;
									END IF;
									SET lCount = lCount + @TEMP;
									UPDATE _POLL_SUMM
									SET participants = lCount
									WHERE poll_id = lPollId;
								END IF;															
							END IF;
							UNTIL exit_loop
						END REPEAT;
					CLOSE recipientCursor;
					SET exit_loop = 0;
				END IF;
			END IF;
			UNTIL exit_loop
		END REPEAT;
	CLOSE pollCursor;
	
	IF sortBy = 'Name' THEN	
		select poll_id,poll_name,participants,responses,start_date,end_date,poll_state,poll_ques 
		from _POLL_SUMM
		ORDER BY poll_name;
	ELSEIF sortBy = 'Date Created' THEN
		select poll_id,poll_name,participants,responses,start_date,end_date,poll_state,poll_ques 
		from _POLL_SUMM
		ORDER BY start_date;
	ELSE 
		select poll_id,poll_name,participants,responses,start_date,end_date,poll_state,poll_ques 
		from _POLL_SUMM
		ORDER BY end_date;
	END IF;
END