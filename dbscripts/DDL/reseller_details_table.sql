DROP TABLE IF EXISTS `reseller_master` ;
CREATE TABLE IF NOT EXISTS `reseller_master` (
  `reseller_id` varchar(25) ,
  `company` varchar(25) ,
  `type` varchar(25) ,  
  `town` varchar(10),
  `country` varchar(25),
  `network_id` varchar(25) ,
  `owner_name` varchar(25) ,
  `msisdn1` varchar(25),
  `msisdn2` varchar(25) ,
  `address` varchar(1500) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `fixed` varchar(25) ,
  `website` varchar(25),
  `countries` varchar(250),
  `paypal` varchar(100) ,
  `bitcoin` varchar(50) ,
  `bank_details` varchar(75),
  `mobile_money` varchar(75),
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`reseller_id`)
)
ENGINE = InnoDB;



DROP TABLE IF EXISTS `reseller_network` ;
CREATE TABLE IF NOT EXISTS `reseller_network` (
  `reseller_id` varchar(25) ,
  `network_id` varchar(25) ,
  `owner_name` varchar(25) ,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`reseller_id`,`network_id`)
)
ENGINE = InnoDB;
