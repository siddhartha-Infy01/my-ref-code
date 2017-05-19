-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `priceupload`(nwid VARCHAR(100),sortBy varchar(50))
BEGIN
	DECLARE exit_loop BOOLEAN DEFAULT 0; 
	DECLARE count2 INT DEFAULT 0; 
	DECLARE count INT DEFAULT 0;
	declare counter int;
	DECLARE count4 INT DEFAULT 0;

	declare upldid1 varchar(100);
	declare comm1 varchar(100);
	declare pricetype1 varchar(100);
	declare market1 varchar(100);
	declare colected1 datetime;
	declare currency1 varchar(100);
	declare price1 decimal(22,2);
	declare measure1 varchar(100);
	declare variety1 varchar(100);
	declare weight1 decimal(22,2);
	declare weightmeasure1 varchar(100);
	declare uploadmode1 varchar(100);
	declare agent1 varchar(100);
	declare gis1 varchar(100);
	declare created1 datetime;
	declare measure2 varchar(50);
	declare currency2 varchar(50);
	declare weight2 decimal(22,2);
	declare weighmeasure2 varchar(50);
	declare price2 decimal(22,3) default null;
	declare changer decimal(22,2) default null;
	declare exchange_rate decimal(22,3);
	declare conv1_factor decimal(22,3) default 0;
	declare curr_Temp varchar(50);
	declare latitude1  decimal(22,8);
	declare latitude2 decimal(22,8);
	declare longitude1 decimal(22,8);
	declare longitude2 decimal(22,8);
	declare distance decimal (22,2) default null;
declare old_price varchar(50) default null;
declare measure3 varchar(50);
	declare currency3 varchar(50);
	declare weight3 decimal(22,2);
	declare weighmeasure3 varchar(50);
	declare price3 decimal(22,3) default null;
	declare changer1 decimal(22,2) default null;
	declare old_price2 varchar(50) default null;
	declare pricetype3 varchar(50);
	declare comment1 longtext;


	  declare v_comm cursor for 
				  SELECT upload_ID,commodity,price_type,market,collected_on,currency_id,price,measure_id,
	variety,weight,weight_measure,upload_mode,agent_id,asText(upload_gis),created_ts,comments  FROM price_upload_master  where network_id=nwid and auth_stat='U';

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;	


	DROP TABLE IF EXISTS _tmp_prices;
	 CREATE TEMPORARY TABLE _tmp_prices (  `upload_id` VARCHAR(50) NOT NULL DEFAULT '',
	  `commodity` VARCHAR(150) DEFAULT NULL,
	  `price_type` VARCHAR(150) DEFAULT NULL,
	  `market` VARCHAR(45) DEFAULT NULL,
	  `collected_on` DATETIME,
	  `currencies` VARCHAR(25) DEFAULT NULL,
	  `price` decimal(22,2),
	  `measure` VARCHAR(20) DEFAULT NULL,
	  `variety` VARCHAR(20) DEFAULT NULL,
	  `weight` decimal(22,2),
	  `weightmeasure` VARCHAR(150) DEFAULT NULL,
	  `upload_mode` CHAR(2) NOT NULL,
	`agent_id` VARCHAR(25) NOT NULL  ,
	`upload_gis` decimal(22,1),
	`created_ts` datetime,
	`old_price` varchar(50),
	`changee` integer,
	`changepricetype` integer,
	`changepricetype1` varchar(75),
	`comments` longtext,
	`curr_symbol` varchar(50),
	`baseMeasure` varchar(100),
	`quoteMeasure` varchar(100),
	`baseMeasureName` varchar(100),
	`quoteMeasureName` varchar(100),
	primary key (upload_id));


		  BLOCK1: BEGIN
		  open v_comm;
				  REPEAT
				   FETCH v_comm into upldid1,comm1,pricetype1,market1,colected1,currency1,price1,measure1,variety1,weight1,weightmeasure1,uploadmode1,agent1,gis1,created1,comment1;
				   if not exit_loop then 
					
					
				
						set count =(SELECT count(*) FROM price_upload_master  where  network_id=nwid and auth_stat='A' and price_type=pricetype1 and commodity=comm1 and market=market1 order by collected_on desc limit 1);
						set count4 = (SELECT count(*) FROM price_upload_master  where  network_id=nwid and auth_stat='A' and price_type!=pricetype1 and commodity=comm1 and market=market1 order by collected_on desc limit 1);

					if count >=  1 then 
				
				   select price ,measure_id ,currency_id,weight ,weight_measure into price2,measure2,currency2,weight2,weighmeasure2 from price_upload_master where  network_id=nwid and auth_stat='A' and price_type=pricetype1 and commodity=comm1 and market=market1 order by collected_on desc limit 1;
				set  curr_Temp = (select symbol from currencies where currency_id=currency1);
				   
				   
				   if price2 is not null then   
				    
					   if measure2 = measure1 then 
					     
							if currency2 = currency1 then
					             
								  set changer = ((price1 - price2)/price2)*100;
								  set old_price = CONCAT( curr_Temp," ",round(price2,2));
							  else
								  
								  select rate into exchange_rate from forex where base_currency_id =currency2 and quote_currency_id=currency1;
								 
								  set changer = (( price1 - (price2 * exchange_rate))/(price2 * exchange_rate))*100; 
								  
								  set old_price = concat(curr_Temp," ", round((price2 * exchange_rate),2));
							 end if;
					  
						 elseif weightmeasure1 is not null then  
						
							 
							   
						 if measure2 = weightmeasure1  then
						  
							if currency2 = currency1 then
							        set price2 = weight1 * price2;
									set changer = ((price1 - price2)/price2)*100;
								   set old_price = CONCAT(curr_Temp," ", round(price2,2));
							else
					  
								select rate into exchange_rate from forex where base_currency_id =currency2 and quote_currency_id=currency1;
					   set price2 = weight1 * price2;
								set changer = (( price1 - (price2 * exchange_rate))/(price2 * exchange_rate))*100; 
								set old_price = concat( curr_Temp," ",round((price2 * exchange_rate),2));
							end if;
						elseif weighmeasure2 = weightmeasure1 then
                          			if currency2 = currency1 then
							        set price2 = (price2/weight2) * weight1;
									set changer = ((price1 - price2)/price2)*100;
								   set old_price = CONCAT( curr_Temp," ",round(price2,2));
							else
					  
								select rate into exchange_rate from forex where base_currency_id =currency2 and quote_currency_id=currency1;
					   set price2 = (price2/weight2) * weight1;
								set changer = (( price1 - (price2 * exchange_rate))/(price2 * exchange_rate))*100; 
								set old_price = concat(curr_Temp," ", round((price2 * exchange_rate),2) );
							end if;			
							end if;
							elseif  weightmeasure2 is not null then  
							if measure1 = weighmeasure2  then
						  
							if currency2 = currency1 then
							        set price2 =  price2/weight2;
									set changer = ((price1 - price2)/price2)*100;
								   set old_price = CONCAT( curr_Temp," ",round(price2,2));
							else
					  set price2 =  price2/weight2;
								select rate into exchange_rate from forex where base_currency_id =currency2 and quote_currency_id=currency1;
					   
								set changer = (( price1 - (price2 * exchange_rate))/(price2 * exchange_rate))*100; 
								set old_price = concat( curr_Temp," ",round((price2 * exchange_rate),2));
							end if;
					         end if;
					   else 
					   
							SELECT conv_factor into conv1_factor FROM measure_factor where commodity_id=comm1 and network_id=nwid and default_measure='Y' and base_measure_id=measure2 and quote_measure_id=measure1 group by base_measure_id,quote_measure_id;
					   
					   
					   
					   if conv1_factor !=0 then
					   
							set price2 = price2/conv1_factor;
						 if currency2 = currency1 then
							  set changer = ((price1 - price2)/price2)*100;
							  set old_price = CONCAT( curr_Temp," ",round(price2,2));
						else
					  
						  select rate into exchange_rate from forex where base_currency_id =currency2 and quote_currency_id=currency1;
					  set price2 = price2/conv1_factor;
							set changer = (( price1 - (price2 * exchange_rate))/(price2 * exchange_rate))*100; 
							set old_price = concat(curr_Temp," ", round((price2 * exchange_rate),2));
					   end if;
					   
					  
					   end if;
					   end if;
					    end if;
						end if;
						if count4 >=  1 then 
				
				   select price ,measure_id ,currency_id,weight ,weight_measure,price_type into price3,measure3,currency3,weight3,weighmeasure3,pricetype3 from price_upload_master where  network_id=nwid and auth_stat='A' and price_type!=pricetype1 and commodity=comm1 and market=market1 order by collected_on desc limit 1;
				
				   
				   
				   if price3 is not null then   
				    
					   if measure3 = measure1 then 
					     
							if currency3 = currency1 then
					             
								  set changer1 = ((price1 - price3)/price3)*100;
								  set old_price2 = CONCAT( changer1," ",pricetype3);
							  else
								  
								  select rate into exchange_rate from forex where base_currency_id =currency3 and quote_currency_id=currency1;
								 
								  set changer1 = (( price1 - (price3 * exchange_rate))/(price3 * exchange_rate))*100; 
								  
								  set old_price2 = concat(changer1," ", pricetype3);
							 end if;
					  
						 elseif weightmeasure1 is not null then  
						
							 
							   
						 if measure3 = weightmeasure1  then
						  
							if currency3 = currency1 then
							        set price3 = weight1 * price3;
									set changer1 = ((price1 - price3)/price3)*100;
								    set old_price2 = CONCAT( changer1," ",pricetype3);
							else
					  
								select rate into exchange_rate from forex where base_currency_id =currency3 and quote_currency_id=currency1;
					   set price3 = weight1 * price3;
								set changer1 = (( price1 - (price3 * exchange_rate))/(price3 * exchange_rate))*100; 
								 set old_price2 = CONCAT( changer1," ",pricetype3);
							end if;
						elseif weighmeasure3 = weightmeasure1 then
                          			if currency3 = currency1 then
							        set price3 = (price3/weight3) * weight1;
									set changer1 = ((price1 - price3)/price3)*100;
								    set old_price2 = CONCAT( changer1," ",pricetype3);
							else
					  
								select rate into exchange_rate from forex where base_currency_id =currency3 and quote_currency_id=currency1;
					   set price2 = (price3/weight3) * weight1;
								set changer1 = (( price1 - (price3 * exchange_rate))/(price3 * exchange_rate))*100; 
								 set old_price2 = CONCAT( changer1," ",pricetype3);
							end if;			
							end if;
							elseif  weightmeasure3 is not null then  
							if measure1 = weighmeasure3 then
						  
							if currency3 = currency1 then
							        set price3 =  price3/weight3;
									set changer1 = ((price1 - price3)/price3)*100;
								    set old_price2 = CONCAT( changer1," ",pricetype3);
							else
					  set price3 =  price3/weight3;
								select rate into exchange_rate from forex where base_currency_id =currency3 and quote_currency_id=currency1;
					   
								set changer1 = (( price1 - (price3 * exchange_rate))/(price3 * exchange_rate))*100; 
								 set old_price2 = CONCAT( changer1," ",pricetype3);
							end if;
					         end if;
					   else 
					   
							SELECT conv_factor into conv1_factor FROM measure_factor where commodity_id=comm1 and network_id=nwid and default_measure='Y' and base_measure_id=measure3 and quote_measure_id=measure1 group by base_measure_id,quote_measure_id;
					   
					   
					   
					   if conv1_factor !=0 then
					   
							set price3 = price3/conv1_factor;
						 if currency3 = currency1 then
							  set changer1 = ((price1 - price3)/price3)*100;
							   set old_price2 = CONCAT( changer1," ",pricetype3);
						else
					  
						  select rate into exchange_rate from forex where base_currency_id =currencye and quote_currency_id=currency1;
					  set price3 = price3/conv1_factor;
							set changer1 = (( price1 - (price3 * exchange_rate))/(price3 * exchange_rate))*100; 
							 set old_price2 = CONCAT( changer1," ",pricetype3);
					   end if;
					   
					  
					   end if;
					   end if;
					    end if;
						end if;
						      if gis1 is not null then
							  
					   		set latitude1 = substring_index(substring(gis1, instr(gis1, "(")+1), " ", 1);	
							set longitude1 = substring_index(substring(gis1, instr(gis1, " ")+1), ")", 1);
							set count2 = ( select count(*) from locations where location_id=market1);
							if count2 = 1 then
							set gis1= null;
							set gis1 = (select asText(gis) from locations where location_id=market1);
							if gis1 is not null then
						
                 
							
							set latitude2 = substring_index(substring(gis1, instr(gis1, "(")+1), " ", 1);	
							set longitude2 = substring_index(substring(gis1, instr(gis1, " ")+1), ")", 1);
							
							set distance = (SELECT  111.1111 *  DEGREES(ACOS(COS(RADIANS(latitude1))  * COS(RADIANS(latitude2))
         * COS(RADIANS(longitude1 - longitude2))
         + SIN(RADIANS(latitude1))
         * SIN(RADIANS(latitude2))))  from dual); 
							end if;
							end if;
							
						
					
					       end if ;
			 
				  insert into _tmp_prices values(upldid1,comm1,pricetype1,market1,colected1,(select name from currencies where currency_id=currency1),price1,ifnull((select symbol from Measures where measure_id=measure1),measure1),variety1,weight1,ifnull((select symbol from Measures where measure_id=weightmeasure1),weightmeasure1),uploadmode1,agent1,ROUND(distance,1),created1,old_price,ROUND(changer),ROUND(changer1),old_price2,comment1,(select symbol from currencies where currency_id=currency1),measure1,weightmeasure1,(select measure_name from Measures where measure_id=measure1),(select measure_name from Measures where measure_id=weightmeasure1));
				  
				  
				  set count=0;
				    set changer=null;
					set price2=null;
				   set distance = null;
				  set old_price= null;
				  set changer1 = null;
				  set old_price2 = null;
				  set price3= null;
			end if;
                       
					  UNTIL exit_loop 
					  END REPEAT;
					CLOSE v_comm;
					
					if sortBy="markets" then
			
				select * from _tmp_prices order by market;
				
				 
				 elseif sortBy="pricetype" then
				 
				 select * from _tmp_prices order by price_type;
				 
				 elseif sortBy="GPS" then
				 
				 select * from _tmp_prices order by upload_gis;
				 
				 elseif sortBy="priceVar" then
				 
				 select * from _tmp_prices order by changee;
				  elseif sortBy="priceTypeVar" then
				 
				 select * from _tmp_prices order by changepricetype;
				 
				 else 
				 
				 select *,(select first_name from people where default_network_id= nwid and people_id=a.agent_id) as name from _tmp_prices a order by name;
				 
				 end if;
				 
					
		end BLOCK1;
	  END