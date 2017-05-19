-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- 
drop procedure pollMonitor;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `pollMonitor`(pollId VARCHAR(45), responseType VARCHAR(45), startDate DATE, endDate DATE, 
	networkId  VARCHAR(45))
BEGIN
	
	DROP TEMPORARY TABLE IF EXISTS `_monitor`;
	
	CREATE TEMPORARY TABLE _monitor (
	`activity_id` int(11) NOT NULL AUTO_INCREMENT,
	`poll_name` varchar(100) DEFAULT NULL,
	`people_id` varchar(100) DEFAULT NULL,	
	`msisdn` varchar(25) DEFAULT NULL,
	`message` longtext,
	`error_stat` varchar(25) DEFAULT NULL,
	`created_ts` datetime DEFAULT NULL,	
	PRIMARY KEY (`activity_id`));
	
	IF pollId = 'All Polls' AND responseType = 'All Responses' THEN
	
	INSERT INTO _monitor SELECT m.activity_id, (SELECT poll_name from poll_master p WHERE p.poll_id = m.service_details),
		(select CONCAT(p.first_name,' ',IFNULL(p.last_name,'')) FROM people p WHERE p.people_id = m.people_id AND p.default_network_id = networkId), m.msisdn, m.message, m.error_stat, m.created_ts FROM inbox_monitor m WHERE m.service_name = 'polls' AND 
		m.network_id = networkId AND m.created_ts between startDate AND endDate;
	
	END IF;
	
	SELECT * FROM _monitor;
END