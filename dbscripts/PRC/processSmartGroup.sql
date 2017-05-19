drop procedure `processSmartGroup`;

DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `processSmartGroup`()
BEGIN
	delete from user_smart_group;
	insert into user_smart_group select * from smart_group_history;
	delete from smart_group_history;	
END