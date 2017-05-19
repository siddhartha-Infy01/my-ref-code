-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `approveNewsCopySharing`(uploadId VARCHAR(100),networkId Varchar(50))
BEGIN
declare id varchar(100);
declare v_network varchar(100);
declare counter int;
DECLARE exit_loop BOOLEAN DEFAULT 0; 
declare typer varchar(100);
declare v_sharestatus varchar(20);
declare v_share cursor for 
 select * from _result11 ;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;

DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (to_share VARCHAR(500) PRIMARY KEY,share_status char(2));
 INSERT INTO _result10  ( select to_share,share_status from user_shares where from_share=networkId and share_item='newsAndLibrary' and share_status='AA' and 
 to_share != networkId and auth_stat='A');
 
 DROP TABLE IF EXISTS _tmp10;
  CREATE TEMPORARY TABLE _tmp10 LIKE _result10;
  REPEAT
    TRUNCATE TABLE _tmp10;
    INSERT  INTO _tmp10 SELECT a.to_share,a.share_status
      FROM user_shares a JOIN  _result10 b ON a.from_share = b.to_share and a.share_item='newsAndLibrary' and a.share_status='AA' and a.to_share != networkId and a.auth_stat='A';
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
	
	UNTIL ROW_COUNT() = 0
  END REPEAT;
  


 
DROP TABLE IF EXISTS _result11;
  CREATE TEMPORARY TABLE _result11 (to_share VARCHAR(500) PRIMARY KEY,share_status char(2));
  INSERT INTO _result11(select * from _result10);
   INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share in (select to_share from _result10) and share_item='newsAndLibrary' and share_status='AR' and  to_share != networkId and auth_stat='A');

 INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share=networkId and share_item='newsAndLibrary' and share_status='AR' and  to_share != networkId and auth_stat='A');
	
	OPEN v_share;
			REPEAT
			FETCH v_share INTO v_network,v_sharestatus;
			IF NOT exit_loop THEN	
	set typer = ( select a.type from 	 library a where lib_id=uploadId);
	if typer = 'N' then
 set id = (select concat("NWS",cast(current_date as unsigned),lpad(floor(10000*rand()),5,'0')) from dual);
 else
  set id = (select concat("LIB",cast(current_date as unsigned),lpad(floor(10000*rand()),5,'0')) from dual);
  end if;
 insert into upload_master select id,application_id,null,null,v_network,now(),if(v_sharestatus='AA','A','U'),"System",now(),null,null from upload_master where upload_id=uploadId;
 insert into library select id,v_network,a.title,a.summary,a.story,a.source_url,a.published_ts,a.type,a.category_id,a.created_by,now(),a.auth_by,a.auth_ts,if(v_sharestatus='AA','A','U'),a.author,null,null from library a where lib_id=uploadId;
 set counter =0;
 set counter = (select count(*) from library_commodity where library_id=uploadId);
 if counter > 0 then 
 insert into library_commodity(library_id,commodity_id) select id,commodity_id from library_commodity where library_id=uploadId;
 end if;
  set counter =0;
 set counter = (select count(*) from library_locations where library_id=uploadId);
 if counter > 0 then 
 insert into library_locations(library_id,location_id) select id,location_id from library_locations where library_id=uploadId;
 end if;
   set counter =0;
 set counter = (select count(*) from library_files where library_id=uploadId);
 if counter > 0 then 
 insert into library_files(library_id,file_content,filename,filetype) select id,file_content,filename,filetype from library_files where library_id=uploadId;
 end if;
    set counter =0;
 set counter = (select count(*) from library_images where library_id=uploadId);
 if counter > 0 then 
 insert into library_images(library_id,content) select id,content from library_images where library_id=uploadId;
 end if;
 
 
 END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE v_share;
		
 
END