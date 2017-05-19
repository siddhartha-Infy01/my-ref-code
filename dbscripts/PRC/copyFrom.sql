drop procedure copyFrom;
DELIMITER $$


CREATE DEFINER=`esoko`@`%` PROCEDURE `copyFrom`(nwid VARCHAR(25),sourceuserId varchar(25),targetuserId VARCHAR(25),paramname varchar(25),userId varchar(25))
BEGIN
declare setting integer;
 if paramname = 'newsTarget' then
	 
      set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='newsAndLibrary');
	 
	   if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,application_id,target,currency_id,effective_date,bonus,incentive,record_status,created_by,created_ts)   select network_id,targetuserId,application_id,target,currency_id,now(),bonus,incentive,'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='newsAndLibrary';
		 
	  	   
	   end if;
elseif 	paramname = 'offerTarget' then

       set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Offers');
	 
	   if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,application_id,target,currency_id,effective_date,bonus,incentive,record_status,created_by,created_ts)   select network_id,targetuserId,application_id,target,currency_id,now(),bonus,incentive,'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Offers';
		  
		  end if;
elseif 	paramname = 'surveyTarget' then

       set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Survey');
	 
	   if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,template,application_id,target,currency_id,effective_date,bonus,incentive,record_status,created_by,created_ts)   select network_id,targetuserId,template,application_id,target,currency_id,now(),bonus,incentive,'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Survey';
		  
		  end if;	
elseif 	paramname = 'priceTarget' then

       set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices');
	 
	   if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,application_id,location_id,commodity_id,target,currency_id,effective_date,bonus,incentive,record_status,created_by,created_ts)   select network_id,targetuserId,application_id,location_id,commodity_id,target,currency_id,now(),bonus,incentive,'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices';
		  
		  end if;	
elseif 	paramname = 'surveyForm' then

       set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Survey');
	 
	   if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,template,application_id,currency_id,effective_date,record_status,created_by,created_ts)   select network_id,targetuserId,template,application_id,currency_id,now(),'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Survey';
		  
		  end if;		  		  
elseif paramname = 'priceCommodities' then
           set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices');
	 
	 if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,application_id,commodity_id,currency_id,effective_date,record_status,created_by,created_ts)   select network_id,targetuserId,application_id,commodity_id,currency_id,now(),'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices';
		  
		  end if;
elseif paramname = 'priceLocations' then
           set setting = (select count(*) from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices');
	 
	 if setting >= 1 then
	     
		  insert into agent_details(network_id,user_id,application_id,location_id,currency_id,effective_date,record_status,created_by,created_ts)   select network_id,targetuserId,application_id,location_id,currency_id,now(),'A',userId,now() from agent_details where user_id=sourceuserId and network_id= nwid and application_id='Prices';
		  
		  end if;		  
		  
		  
 end if;
 
 	
END

    	