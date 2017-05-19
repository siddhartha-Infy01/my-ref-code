-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `PriceSharingApproval`(uploadId VARCHAR(100),networkId Varchar(50))
BEGIN
declare id varchar(100);
declare v_network varchar(100);
declare comm varchar(100);
declare town varchar(100);
DECLARE exit_loop BOOLEAN DEFAULT 0; 
declare typer varchar(100);
declare v_sharestatus varchar(20);
declare pr_type varchar(100);
declare counter int;
declare v_share cursor for 
 select * from _result11 ;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;

DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (to_share VARCHAR(200) PRIMARY KEY,share_status char(2));
 INSERT INTO _result10  ( select to_share,share_status from user_shares where from_share=networkId and share_item='prices' and share_status='AA' and 
 to_share != networkId and auth_stat='A');
 
 DROP TABLE IF EXISTS _result12;
   CREATE TEMPORARY TABLE _result12 (alert_id VARCHAR(200) PRIMARY KEY,people_id varchar(200));
 
 
 DROP TABLE IF EXISTS _tmp10;
  CREATE TEMPORARY TABLE _tmp10 LIKE _result10;
  REPEAT
    TRUNCATE TABLE _tmp10;
    INSERT  INTO _tmp10 SELECT a.to_share,a.share_status
      FROM user_shares a JOIN  _result10 b ON a.from_share = b.to_share and a.share_item='prices' and a.share_status='AA' and a.to_share != networkId  and a.auth_stat='A';
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
	
	UNTIL ROW_COUNT() = 0
  END REPEAT;
  


 
DROP TABLE IF EXISTS _result11;
  CREATE TEMPORARY TABLE _result11 (to_share VARCHAR(500) PRIMARY KEY,share_status char(2));
  INSERT INTO _result11(select * from _result10);

   INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share in (select to_share from _result10) and share_item='prices' and share_status='AR' and  to_share != networkId  and auth_stat='A'); 
      INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share=networkId and share_item='prices' and share_status='AR' and  to_share != networkId  and auth_stat='A');
 
 select commodity,market,price_type into comm,town,pr_type from price_upload_master where upload_ID=uploadId;
    
	
	OPEN v_share;
			REPEAT
			FETCH v_share INTO v_network,v_sharestatus;
			IF NOT exit_loop THEN	
			set id = (select concat("PRICE",cast(current_date as unsigned),lpad(floor(10000*rand()),5,'0')) from dual);
			 insert into upload_master select id,application_id,null,null,v_network,now(),if(v_sharestatus='AA','A','U'),"System",now(),null,null from upload_master where upload_id=uploadId;
			 
		
insert into price_upload_master select id,commodity,price_type,market,collected_on,currency_id,price,measure_id,variety,weight,weight_measure,
variety_comment,comments,v_network,if(v_sharestatus='AA','A','U'),null,null,created_by,created_ts,upload_mode,null,upload_gis,null,null from price_upload_master
where upload_ID=uploadId;

 END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE v_share;
			insert ignore into _result10 values(networkId,'AA');
			set counter =0;
			set counter = ( select count(*) from Networks where is_private='N' and network_id in (select to_share from _result10));
			if counter = 0 then
		
		insert into _result12 (
SELECT b.push_alert_id,null FROM alert_commodities a,alert_locations b,alert_price_type e
where a.commodity_id=comm and b.location_id=town and e.price_type=pr_type and e.alert_id=b.push_alert_id and a.push_alert_id=
b.push_alert_id  and b.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='PA' and d.push_alert_id=c.push_alert_id and d.alert_state='A'));

 insert ignore into _result12 (SELECT a.push_alert_id,a.user_id FROM alert_profile_based a,user_commodities b,user_locations c,push_alert_master d where a.user_id=b.user_id and a.user_id=c.user_id and b.commodity_id=comm and c.location_id=town and d.payee_network_id=c.network_id and d.payee_network_id=b.network_id and a.push_alert_id=d.push_alert_id  and a.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='PA' and d.push_alert_id=c.push_alert_id and d.alert_state='A')); 

else

insert ignore into _result10 values('ALL','AA');
insert into _result12 (SELECT b.push_alert_id,null FROM alert_commodities a,alert_locations b,alert_price_type e
where a.commodity_id=comm and b.location_id=town and e.price_type=pr_type and e.alert_id=b.push_alert_id and a.push_alert_id=
b.push_alert_id  and b.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='PA' and d.push_alert_id=c.push_alert_id and d.alert_state='A'));

insert ignore into _result12 (SELECT a.push_alert_id,a.user_id FROM alert_profile_based a,user_commodities b,user_locations c,push_alert_master d where a.user_id=b.user_id and a.user_id=c.user_id and b.commodity_id=comm and c.location_id=town and d.payee_network_id=c.network_id and d.payee_network_id=b.network_id and a.push_alert_id=d.push_alert_id  and a.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='PA' and d.push_alert_id=c.push_alert_id and d.alert_state='A')); 

select * from _result12;
end if;
END