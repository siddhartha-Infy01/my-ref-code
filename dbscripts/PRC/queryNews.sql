-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
drop procedure queryNews;
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `queryNews`(nwid VARCHAR(25),usrid VARCHAR(25))
BEGIN
DECLARE  setting varchar(1000);
declare nwtlist varchar(1000);
declare commlist varchar(1000);
declare loclist varchar (1000);
declare vars varchar(1000);


declare public_flag char(5);
set setting=(select count(*) from user_widget_settings where user_id=usrid  and widget_id="NEWS");
 DROP TABLE IF EXISTS _tmp1;
 CREATE TEMPORARY TABLE _tmp1 (nwtid varchar(25));
 TRUNCATE TABLE _tmp1;
 INSERT INTO _tmp1 select network_id from library where network_id=nwid union select network_id  from Networks where is_private='N';
                       

if setting=1 then

        select commodity_list,network_list,location_list,network_public into commlist,nwtlist,loclist,public_flag from user_widget_settings where user_id=usrid  and widget_id="NEWS";
		SET @vars = commlist;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result4;
        CREATE TEMPORARY TABLE _result4 (field varchar(25));
        TRUNCATE TABLE _result4;
        
		if @vars is not null	then 
        SET @sql := CONCAT('INSERT INTO ', '_result4', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
        end if;            
			
		SET @vars1 = nwtlist;
		SET @vars1 := CONCAT("('", REPLACE(@vars1, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result5;
        CREATE TEMPORARY TABLE _result5 (field varchar(25));
        TRUNCATE TABLE _result5;
        
       	if @vars1 is not null then 
        SET @sql := CONCAT('INSERT INTO ', '_result1', ' VALUES ', @vars1);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;
		
		
		SET @vars2 = loclist;
		SET @vars2 := CONCAT("('", REPLACE(@vars2, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result6;
        CREATE TEMPORARY TABLE _result6 (field varchar(25));
        TRUNCATE TABLE _result6;
		
			if @vars2 is not null then 
        SET @sql := CONCAT('INSERT INTO ', '_result2', ' VALUES ', @vars2);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
	    end if;
        
    
	    

               If commlist is  null and nwtlist is null and loclist is null then
			 
			            if public_flag = 'Y' then
			                							
						    select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id in (select * from _tmp1)  and a.auth_stat='A' and a.type='N' order by created_ts desc limit 3 ;
				       			 
		                    
						  else 
						 
						    select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id =nwid  and a.auth_stat='A' and a.type='N' order by created_ts desc limit 3 ;
							
						   end if;
             elseif commlist is not null and nwtlist is  null and loclist is null then
			      
				        if public_flag = 'Y' then
						  
						  select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d  where a.network_id in (select * from _tmp1)  and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result4) order by created_ts desc limit 3 ; 
			              
                        else
						
						   select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d  where a.network_id=nwid and  a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result4) order by created_ts desc limit 3 ; 
				 
				         end if;
             elseif commlist is not null and nwtlist is not null and loclist is null then  
			              
						  
				        if public_flag = 'Y' then
						
						  INSERT IGNORE INTO _tmp1 SELECT field FROM _result5;
						  
						  select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d  where a.network_id in (select * from _tmp1) and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result4) order by created_ts desc limit 3 ; 
						  
						else
						
						  select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d  where a.network_id in (select * from _result5) and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result4) order by created_ts desc limit 3 ; 
				 
				         end if;
						
               elseif commlist is not null and nwtlist is not null and loclist is not null then
			   
			            if public_flag = 'Y' then
						INSERT IGNORE INTO _tmp1 SELECT field FROM _result5;
						select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d,library_locations e  where a.network_id in (select * from _tmp1)  and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result4) and e.location_id in (select * from _result6) order by created_ts desc limit 3 ;
						
						else
						
						select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d,library_locations e  where a.network_id in (select * from _result5) and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result4) and e.location_id in (select * from _result6) order by created_ts desc limit 3 ;
						
                        end if;
             elseif commlist is null and nwtlist is not null and loclist is null then
                      
					 if public_flag = 'Y' then
					 INSERT IGNORE INTO _tmp1 SELECT field FROM _result5;
					 
					 select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id in (select * from _tmp1) and a.auth_stat='A' and a.type='N'  order by created_ts desc limit 3 ;
					  
					  else
					  
					  select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id in (select * from _result5)  and a.auth_stat='A' and a.type='N'  order by created_ts desc limit 3 ;
					  
					  end if;
					  
             elseif commlist is null and nwtlist is not null and loclist is not null then
			 
				       if public_flag = 'Y' then
					   INSERT IGNORE INTO _tmp1 SELECT field FROM _result5;
					   select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_locations d where a.network_id in (select * from _tmp1)  and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.location_id in (select * from _result6)  order by created_ts desc limit 3 ; 
					   
					   else
					   
					   select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_locations d where a.network_id in (select * from _result5) and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.location_id in (select * from _result6)  order by created_ts desc limit 3 ; 
					   
					   end if;
             elseif commlist is null and nwtlist is null and loclist is not null then
			 
                  if public_flag = 'Y' then
					   
					   select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_locations d where a.network_id in (select * from _tmp1)  and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.location_id in (select * from _result6)  order by created_ts desc limit 3 ; 
					   
					   else
					   
					   select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_locations d where a.network_id=nwtlist  and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id  and d.location_id in (select * from _result6) order by created_ts desc limit 3 ; 
					   
					   end if;
				 
             elseif commlist is not null and nwtlist is null and loclist is not null then
              
			  
			          if public_flag = 'Y' then
						
						select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d,library_locations e  where a.network_id in (select * from _tmp1) and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result4) and e.location_id in (select * from _result6) order by created_ts desc limit 3 ;
						
						else
						
						select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a,library_commodity d,library_locations e  where a.network_id =nwid and a.auth_stat='A' and a.type='N' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result4) and e.location_id in (select * from _result6) order by created_ts desc limit 3 ;
						
                        end if;
			  
 
		else
		
select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id=nwid  and a.auth_stat='A' and a.type='N'  order by created_ts desc limit 3 ;
end if;



else
select a.title,a.story,a.published_ts,a.source_url,(select content from library_images where library_id=a.lib_id) as image from library a where a.network_id=nwid and  a.auth_stat='A' and a.type='N'  order by created_ts desc limit 3 ; 
end if;
  
END