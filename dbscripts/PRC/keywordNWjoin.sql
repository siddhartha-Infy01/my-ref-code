-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `keywordNWjoin`(networkId varchar(100),userId varchar(100))
BEGIN

declare counter int;
set counter =0;
set counter = (select count(*) from people where default_network_id=networkId and people_id=userId);
if counter =0 then
insert into people (select people_id,first_name,last_name,nickname,gender,town,country,currency_id,language_id,msisdn,operator_id,email,'A',created_by,created_ts,
modified_by,modified_ts,networkId,'N',is_visible,msisdn2,title,birthyear,company,position,shortdesc,fixedtel,fax,add1,add2,website,mode from people where people_id=userId limit 1; 
end if;



	END