-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `approveShare`(fromnwid VARCHAR(25),tonwid VARCHAR(25),shareitem varchar(25),approvalflag varchar(25),ppl_Share_type varchar(25),ppl_mode varchar(25),userid varchar(25))
BEGIN
declare temp_ppl_mode char(2);
declare temp_name char(2);
declare temp_br_yr char(2);
declare temp_gender char(2);
declare temp_mob_no char(2);
declare temp_address char(2);
declare temp_lan char(2);
declare temp_email char(2);
declare temp_company char(2);
declare temp_curr char(2);


if shareitem='people' then
		if approvalflag='R' then
			
			delete from user_shares where from_share=fromnwid and to_share=tonwid and share_item='people'; 
		
		else
		
		     if ppl_Share_type is null then
			 
			 select people_mode into temp_ppl_mode from user_shares where from_share=fromnwid and to_share=tonwid and share_item='people';
			          if temp_ppl_mode='R' then
					  
					  update user_shares set auth_stat='A',modified_by=userid,modified_ts=now() where from_share=fromnwid and to_share=tonwid and share_item='people';
			          
					  else
					  
                      
					  
					  select name_flag,birth_year_flag,gender_flag,mobile_number_flag,address_flag,language_flag,email_flag,company_flag,currency_flag into temp_name,temp_br_yr,temp_gender,temp_mob_no,temp_address,temp_lan,temp_email,temp_company,temp_curr from 
					  profile_Sharing_types where profile_id=(select people_type from user_shares where from_share=fromnwid and to_share=tonwid and share_item='people') and network_id=fromnwid;
					  
					  SET @sql := CONCAT('select people_id');
					  
					  if temp_name='Y' then
					  
					  SET @sql := CONCAT(@sql, ',first_name,last_name');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null');
					  
					  end if;
					   if temp_gender='Y' then
					  
					  SET @sql := CONCAT(@sql, ',gender');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;

					  if temp_br_yr='Y' then
					  
					  
					  SET @sql := CONCAT(@sql, ',birthyear');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  
					  end if;
					 					  
					  if temp_mob_no='Y' then
					  
					  SET @sql := CONCAT(@sql, ',msisdn,fax,fixedtel');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null,null');
					  end if;
					  if temp_address='Y' then
					  
					  SET @sql := CONCAT(@sql, ',town,country,add1');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null,null');
					  end if;
					  if temp_lan='Y' then
					 
					  SET @sql := CONCAT(@sql, ',language_id');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					   if temp_email='Y' then
					  
					  SET @sql := CONCAT(@sql, ',email');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					  if temp_company='Y' then
					  
					  SET @sql := CONCAT(@sql, ',company,position');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null');
					  end if;
					  if temp_curr='Y' then
					  
					  SET @sql := CONCAT(@sql, ',currency_id');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					  			  
					  
					  SET @sql := CONCAT(@sql,',"N","',userid,'",now(),"A"',',"',tonwid,'"', ' from people where default_network_id=','"',fromnwid,'"');
					 
					  SET @sql :=CONCAT('INSERT ignore INTO people(people_id,first_name,last_name,gender,birthyear,msisdn,fax,fixedtel,town,country,add1,language_id,email,company,position,currency_id,master_flag,created_by,created_ts,record_status,default_network_id) ',@sql);
					   PREPARE stmt FROM @sql;
					  
	                   EXECUTE stmt;
	                   DEALLOCATE PREPARE stmt;
					  		  
					  
					  
					  
					  
					  delete from user_shares where from_share=fromnwid and to_share=tonwid and share_item='people';
                      end if;					  
			 else
			 
			          if ppl_mode='R' then
					  
					   update user_shares set auth_stat='A',modified_by=userid,people_type=ppl_Share_type,modified_ts=now() where from_share=fromnwid and to_share=tonwid and share_item='people';
			 
			          else
					  
					  
					  select name_flag,birth_year_flag,gender_flag,mobile_number_flag,address_flag,language_flag,email_flag,company_flag,currency_flag into temp_name,temp_br_yr,temp_gender,temp_mob_no,temp_address,temp_lan,temp_email,temp_company,temp_curr from 
					  profile_Sharing_types where profile_id=ppl_Share_type and network_id=fromnwid;
					  
					  SET @sql := CONCAT('select people_id');
					  
					  if temp_name='Y' then
					  
					  SET @sql := CONCAT(@sql, ',first_name,last_name');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null');
					  
					  end if;
					   if temp_gender='Y' then
					  
					  SET @sql := CONCAT(@sql, ',gender');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;

					  if temp_br_yr='Y' then
					  
					  
					  SET @sql := CONCAT(@sql, ',birthyear');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  
					  end if;
					 					  
					  if temp_mob_no='Y' then
					  
					  SET @sql := CONCAT(@sql, ',msisdn,fax,fixedtel');
					  
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null,null');
					  end if;
					  if temp_address='Y' then
					  
					  SET @sql := CONCAT(@sql, ',town,country,add1');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null,null');
					  end if;
					  if temp_lan='Y' then
					 
					  SET @sql := CONCAT(@sql, ',language_id');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					   if temp_email='Y' then
					  
					  SET @sql := CONCAT(@sql, ',email');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					  if temp_company='Y' then
					  
					  SET @sql := CONCAT(@sql, ',company,position');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null,null');
					  end if;
					  if temp_curr='Y' then
					  
					  SET @sql := CONCAT(@sql, ',currency_id');
					  else 
					  
					  SET @sql := CONCAT(@sql, ',null');
					  end if;
					  			  
					  
					  SET @sql := CONCAT(@sql,',"N","',userid,'",now(),"A"',',"',tonwid,'"', ' from people where default_network_id=','"',fromnwid,'"');
					 
					  SET @sql :=CONCAT('INSERT ignore INTO people(people_id,first_name,last_name,gender,birthyear,msisdn,fax,fixedtel,town,country,add1,language_id,email,company,position,currency_id,master_flag,created_by,created_ts,record_status,default_network_id) ',@sql);
					   PREPARE stmt FROM @sql;
					  
	                   EXECUTE stmt;
	                   DEALLOCATE PREPARE stmt;
			           
					   
			           delete from user_shares where from_share=fromnwid and to_share=tonwid and share_item='people';
		
			
			          end if;
		    end if;
		 end if;
		
else
        if approvalflag='R' then
		
		delete from user_shares where from_share=fromnwid and to_share=tonwid and share_item=shareitem;
		
		else
		
		update user_shares set auth_stat='A',modified_by=userid,modified_ts=now() where from_share=fromnwid and to_share=tonwid and share_item=shareitem;

		
        end if;
		
end if;		

  
END