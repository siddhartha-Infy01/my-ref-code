-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `BidsOfferCopySharing`(uploadId VARCHAR(100),networkId Varchar(50))
BEGIN
declare id varchar(100);
declare v_network varchar(100);
declare comm varchar(100);
declare town varchar(100);
DECLARE exit_loop BOOLEAN DEFAULT 0; 
declare typer varchar(100);
declare v_sharestatus varchar(20);
declare alert_type char(2);
declare counter int;
declare v_share cursor for 
 select * from _result11 ;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;

DROP TABLE IF EXISTS _result10;
  CREATE TEMPORARY TABLE _result10 (to_share VARCHAR(500) PRIMARY KEY,share_status char(2));
 INSERT INTO _result10  ( select to_share,share_status from user_shares where from_share=networkId and share_item='offers' and share_status='AA' and 
 to_share != networkId and auth_stat='A');
 
 DROP TABLE IF EXISTS _tmp10;
  CREATE TEMPORARY TABLE _tmp10 LIKE _result10;
  REPEAT
    TRUNCATE TABLE _tmp10;
    INSERT  INTO _tmp10 SELECT a.to_share,a.share_status
      FROM user_shares a JOIN  _result10 b ON a.from_share = b.to_share and a.share_item='offers' and a.share_status='AA' and a.to_share != networkId  and a.auth_stat='A';
    INSERT IGNORE INTO _result10 SELECT * FROM _tmp10 ;
	
	UNTIL ROW_COUNT() = 0
  END REPEAT;
  


 
DROP TABLE IF EXISTS _result11;
  CREATE TEMPORARY TABLE _result11 (to_share VARCHAR(500) PRIMARY KEY,share_status char(2));
  INSERT INTO _result11(select * from _result10);

   INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share in (select to_share from _result10) and share_item='offers' and share_status='AR' and  to_share != networkId  and auth_stat='A'); 
      INSERT ignore INTO _result11  ( select to_share,share_status from user_shares where from_share=networkId and share_item='offers' and share_status='AR' and  to_share != networkId  and auth_stat='A');
 
 select commodity,location,bid_offer_flag into comm,town,alert_type from bids_offers_master where upload_ID=uploadId;
    
	
	OPEN v_share;
			REPEAT
			FETCH v_share INTO v_network,v_sharestatus;
			IF NOT exit_loop THEN	
			set id = (select concat("OFFER",cast(current_date as unsigned),lpad(floor(10000*rand()),5,'0')) from dual);
			 insert into upload_master select id,application_id,null,null,v_network,now(),if(v_sharestatus='AA','A','U'),"System",now(),null,null from upload_master where upload_id=uploadId;
			 
			insert into bids_offers_master select id,bid_offer_flag,commodity,variety,type,origin,quantity,measure,price_amount,currency_id,amount_unit,negotiable_flag,expiry_date,offer_owner,offer_uploaded_by,offer_userid,payement_mode,delivery_point,
delivery_type,no_of_days,documents,notes,grade,upload_mode,v_network,if(v_sharestatus='AA','A','U'),null,null,created_by,
created_ts,location from bids_offers_master where upload_ID=uploadId;

 END IF;
			UNTIL exit_loop
			END REPEAT;
			CLOSE v_share;
			insert ignore into _result10 values(networkId,'AA');
			set counter =0;
			set counter = ( select count(*) from Networks where is_private='N' and network_id in (select to_share from _result10));
			if counter = 0 then
		
SELECT b.push_alert_id FROM alert_commodities a,alert_locations b
where a.commodity_id=comm and b.location_id=town and a.push_alert_id=
b.push_alert_id  and b.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='BO'and d.bid_offer=alert_type and d.push_alert_id=c.push_alert_id);

else

insert ignore into _result10 values('ALL','AA');
 SELECT b.push_alert_id FROM alert_commodities a,alert_locations b
where a.commodity_id=comm and b.location_id=town and a.push_alert_id=
b.push_alert_id  and b.push_alert_id in (select d.push_alert_id from alert_source_networks c,push_alert_master d where c.network_id in (select to_share from _result10) and d.message_type='BO'and d.bid_offer=alert_type and d.push_alert_id=c.push_alert_id);
end if;

END