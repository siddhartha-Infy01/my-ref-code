-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `approveLocation`(locationId VARCHAR(100),approvalFlag Varchar(50))
BEGIN
  if approvalFlag='R' then
  
  delete from locations where location_id=locationId;
  delete from market_details where location_id=locationId;
 
  
  else
  
  update locations set auth_stat='A' where location_id=locationId;
 
  
  end if;
END