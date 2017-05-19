-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `deriveCostForOperator`(IN nwid VARCHAR(25),IN premium char(1), IN operatorId VARCHAR(25) ,OUT baseCost decimal(22,3),OUT retailCost decimal(22,3),OUT wholesaleCost decimal(22,3))
BEGIN
		
	DECLARE marginCost decimal(22,3);
	DECLARE premiumCost decimal(22,3);
	DECLARE lnwk_id VARCHAR(25);
	DECLARE loperatorId VARCHAR(25);
	DECLARE exit_loop BOOLEAN DEFAULT 0;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = 1;
 
 DROP TEMPORARY TABLE IF EXISTS _cost_map;
 CREATE TEMPORARY TABLE _cost_map (	
	`cost_type` VARCHAR(25),
	`cost` decimal(22,3) DEFAULT 0.0,
	PRIMARY KEY (`cost_type`)
 );

	call getNetworkPrices(nwid,'REGULAR', @retail);
	INSERT INTO _cost_map values('REGULAR', @retail);
	call getNetworkPrices(nwid,'WHOLESALE', @wholesale);
	INSERT INTO _cost_map values('WHOLESALE', @wholesale);
	call getNetworkPrices(nwid,'PREMIUM', @wholesale);
	INSERT INTO _cost_map values('PREMIUM', @wholesale);
	call getNetworkPrices(nwid,'PREMIUM_WHOLESALE', @wholesale);
	INSERT INTO _cost_map values('PREMIUM_WHOLESALE', @wholesale);

	set loperatorId=operatorId;
	SET baseCost = 0;
	SET marginCost = 0;								
	SET @COUNTER1 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.first_pref_gateway
					where o.operator_id=loperatorId
					and network_id=nwid
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');				
	SET @COUNTER2 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.second_pref_gateway
					where o.operator_id=loperatorId
					and network_id=nwid
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');				
	SET @COUNTER3 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.third_pref_gateway
					where o.operator_id=loperatorId
					and network_id=nwid
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');				
	SET @COUNTER4 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.first_pref_gateway
					where o.operator_id=loperatorId								
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');				
	SET @COUNTER5 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.second_pref_gateway
					where o.operator_id=loperatorId								
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');				
	SET @COUNTER6 = (select count(*)
					from operators o
					inner join smpp_routes s on o.operator_id = s.operator_id
					and s.gateway_id = o.third_pref_gateway
					where o.operator_id=loperatorId								
					and s.type='Kannel'
					and s.direction='MT'
					and o.record_status='A');							
				
	IF @COUNTER1 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.first_pref_gateway
		where o.operator_id=loperatorId
		and network_id=nwid
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';					
				
	ELSEIF @COUNTER2 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.second_pref_gateway
		where o.operator_id=loperatorId
		and network_id=nwid
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';								
				
	ELSEIF @COUNTER3 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.third_pref_gateway
		where o.operator_id=loperatorId
		and network_id=nwid
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';
				
	ELSEIF @COUNTER4 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.first_pref_gateway
		where o.operator_id=loperatorId								
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';								
				
	ELSEIF @COUNTER5 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.second_pref_gateway
		where o.operator_id=loperatorId								
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';								
				
	ELSEIF @COUNTER6 = 1 THEN
		select s.cost,s.margin,s.premium
		INTO baseCost,marginCost,premiumCost
		from operators o
		inner join smpp_routes s on o.operator_id = s.operator_id
		and s.gateway_id = o.third_pref_gateway
		where o.operator_id=loperatorId								
		and s.type='Kannel'
		and s.direction='MT'
		and o.record_status='A';
								
	ELSE
		SET @TEMP = (select count(*)					
		from smpp_routes
		where operator_id=loperatorId
		and type='Kannel'
		and direction='MT'
		and record_status='A'
		limit 1);				
		
		IF @TEMP = 1 THEN
			select s.cost,s.margin,s.premium
			INTO baseCost,marginCost,premiumCost
			from smpp_routes s
			where s.operator_id=loperatorId
			and type='Kannel'
			and s.direction='MT'
			and s.record_status='A'
			limit 1;			
		ELSE
			SET baseCost = 0;
			SET marginCost = 0;
			SET premiumCost = 0;
		END IF;
				
	END IF;	
	if premium='Y' THEN
		SELECT cost INTO @TEMP1 FROM _cost_map WHERE cost_type = 'REGULAR';
		SELECT cost INTO @TEMP2 FROM _cost_map WHERE cost_type = 'WHOLESALE';
		SET retailCost = @TEMP1-((@TEMP1 * marginCost) /  100);
		SET wholesaleCost = @TEMP2-((@TEMP2 * marginCost) /  100);				
	ELSE		
		SELECT cost INTO @TEMP1 FROM _cost_map WHERE cost_type = 'PREMIUM';
		SELECT cost INTO @TEMP2 FROM _cost_map WHERE cost_type = 'PREMIUM_WHOLESALE';
		SET retailCost = @TEMP1-((@TEMP1 * premiumCost) /  100);
		SET wholesaleCost = @TEMP2-((@TEMP2 * premiumCost) /  100);				
	END IF;
END