drop procedure querylibrary;
delimiter $$
CREATE PROCEDURE querylibrary(nwid VARCHAR(25),usrid VARCHAR(25))


BEGIN
DECLARE  setting varchar(1000);
declare nwtlist varchar(1000);
declare commlist varchar(1000);
declare loclist varchar (1000);
declare vars varchar(1000);


declare public_flag char(5);
set setting=(select count(*) from user_widget_settings where user_id=usrid  and widget_id="LIBRARY");
 DROP TABLE IF EXISTS _tmp;
 CREATE TEMPORARY TABLE _tmp (nwtid varchar(25));
 TRUNCATE TABLE _tmp;
 INSERT INTO _tmp select network_id from library where network_id=nwid union select network_id  from Networks where is_private='N';
                        

if setting=1 then

        select commodity_list,network_list,location_list,network_public into commlist,nwtlist,loclist,public_flag from user_widget_settings where user_id=usrid  and widget_id="LIBRARY";
		SET @vars = commlist;
		SET @vars := CONCAT("('", REPLACE(@vars, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result;
        CREATE TEMPORARY TABLE _result (field varchar(25));
        TRUNCATE TABLE _result;
        
		if @vars is not null	then 	
        SET @sql := CONCAT('INSERT INTO ', '_result', ' VALUES ', @vars);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;
		
		SET @vars1 = nwtlist;
		SET @vars1 := CONCAT("('", REPLACE(@vars1, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result1;
        CREATE TEMPORARY TABLE _result1 (field varchar(25));
        TRUNCATE TABLE _result1;
        
		if @vars1 is not null then 
        SET @sql := CONCAT('INSERT INTO ', '_result1', ' VALUES ', @vars1);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
		end if;
		
		
		SET @vars2 = loclist;
		SET @vars2 := CONCAT("('", REPLACE(@vars2, ",", "'),('"), "')");
        DROP TABLE IF EXISTS _result2;
        CREATE TEMPORARY TABLE _result2 (field varchar(25));
        TRUNCATE TABLE _result2;
        
		if @vars2 is not null then 
        SET @sql := CONCAT('INSERT INTO ', '_result2', ' VALUES ', @vars2);
	    PREPARE stmt FROM @sql;
	    EXECUTE stmt;
	    DEALLOCATE PREPARE stmt;
	    end if;

               If commlist is  null and nwtlist is null and loclist is null then
			 
			            if public_flag = 'Y' then
			                							
						    select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a,library_files c where a.network_id in (select * from _tmp)    and a.lib_id=c.library_id   and a.auth_stat='A' and a.type='L' order by created_ts desc limit 3 ;
				       			 
		                    
						  else 
						 
						    select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c where a.network_id =nwid   and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' order by created_ts desc limit 3 ;
							
						   end if;
             elseif commlist is not null and nwtlist is  null and loclist is null then
			      
				        if public_flag = 'Y' then
						  
						  select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d  where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result) order by created_ts desc limit 3 ; 
			              
                        else
						
						   select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d  where a.network_id=nwid  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result) order by created_ts desc limit 3 ; 
				 
				         end if;
             elseif commlist is not null and nwtlist is not null and loclist is null then  
			              
						  
				        if public_flag = 'Y' then
						
						  INSERT IGNORE INTO _tmp SELECT field FROM _result1;
						  
						  select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d  where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result) order by created_ts desc limit 3 ; 
						  
						else
						
						  select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d  where a.network_id in (select * from _result1)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.commodity_id in (select * from _result) order by created_ts desc limit 3 ; 
				 
				         end if;
						
               elseif commlist is not null and nwtlist is not null and loclist is not null then
			   
			            if public_flag = 'Y' then
						INSERT IGNORE INTO _tmp SELECT field FROM _result1;
						select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d,library_locations e  where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result) and e.location_id in (select * from _result2) order by created_ts desc limit 3 ;
						
						else
						
						select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d,library_locations e  where a.network_id in (select * from _result1)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result) and e.location_id in (select * from _result2) order by created_ts desc limit 3 ;
						
                        end if;
             elseif commlist is null and nwtlist is not null and loclist is null then
                      
					 if public_flag = 'Y' then
					 INSERT IGNORE INTO _tmp SELECT field FROM _result1;
					 
					 select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L'  order by created_ts desc limit 3 ;
					  
					  else
					  
					  select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c where a.network_id in (select * from _result1)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L'  order by created_ts desc limit 3 ;
					  
					  end if;
					  
             elseif commlist is null and nwtlist is not null and loclist is not null then
			 
				       if public_flag = 'Y' then
					   INSERT IGNORE INTO _tmp SELECT field FROM _result1;
					   select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_locations d where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.location_id in (select * from _result2)  order by created_ts desc limit 3 ; 
					   
					   else
					   
					   select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_locations d where a.network_id in (select * from _result1)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.location_id in (select * from _result2)  order by created_ts desc limit 3 ; 
					   
					   end if;
             elseif commlist is null and nwtlist is null and loclist is not null then
			 
                  if public_flag = 'Y' then
					   
					   select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_locations d where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.location_id in (select * from _result2)  order by created_ts desc limit 3 ; 
					   
					   else
					   
					   select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_locations d where a.network_id=nwid  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id  and d.location_id in (select * from _result2) order by created_ts desc limit 3 ; 
					   
					   end if;
				 
             elseif commlist is not null and nwtlist is null and loclist is not null then
              
			  
			          if public_flag = 'Y' then
						
						select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d,library_locations e  where a.network_id in (select * from _tmp)  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result) and e.location_id in (select * from _result2) order by created_ts desc limit 3 ;
						
						else
						
						select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c,library_commodity d,library_locations e  where a.network_id =nwid  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L' and a.lib_id=d.library_id and a.lib_id=e.library_id and d.commodity_id in (select * from _result) and e.location_id in (select * from _result2) order by created_ts desc limit 3 ;
						
                        end if;
			  
 
		else
		
select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c where a.network_id=nwid  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L'  order by created_ts desc limit 3 ;
end if;



else
select a.title,a.story,a.published_ts,a.source_url,c.filename,c.filetype,c.file_content,(select content from library_images where library_id=a.lib_id) as image from library a, library_files c where a.network_id=nwid  and a.lib_id=c.library_id and a.auth_stat='A' and a.type='L'  order by created_ts desc limit 3 ; 
end if;
  
END