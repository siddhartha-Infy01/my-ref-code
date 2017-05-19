DROP TABLE IF EXISTS `network_subscription` ;
CREATE TABLE `network_subscription` (
  `subscription_id` varchar(25),
  `subscription_type` varchar(25) NOT NULL DEFAULT '',
  `network_id` varchar(25) NOT NULL DEFAULT '',
  `network_name` varchar(50),
  `start_date` datetime,
  `end_date` datetime,
  `balance` double,
  `currency` varchar(10),
  `prevent_logins` char(1) DEFAULT NULL,
  `suspend_debits` char(1) DEFAULT NULL,
  `owner_name` varchar(25),
  `reseller_id` varchar(25),
  `message` varchar(250),
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`subscription_id`,`network_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `network_subscription_details` ;
CREATE TABLE IF NOT EXISTS `network_subscription_details` (
`subscription_id` varchar(25),
 `reseller_id` varchar(25),
 `network_id` varchar(25) NOT NULL DEFAULT '',
 `subscription_type` varchar(25) NOT NULL DEFAULT '',
 `invoice_no` varchar(25) ,
 `amount` double,
 `discount` integer,
 `period` integer,
 `vat` double,
 `quantity` integer,
 `casReseller` double,
 `marginReseller` double,
 `description` varchar(250) DEFAULT NULL,
 `created_by` varchar(25) DEFAULT NULL,
 `created_ts` datetime DEFAULT NULL,
 `modified_by` varchar(25) DEFAULT NULL,
 `modified_ts` datetime DEFAULT NULL,
 `record_status` char(1) DEFAULT NULL,
 PRIMARY KEY (`invoice_no`,`reseller_id`,`network_id`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `network_subscription_apps` ;
CREATE TABLE IF NOT EXISTS `network_subscription_apps` (
 `subscription_id` varchar(25),
 `subscription_type` varchar(25),
 `invoice_no` varchar(25) ,
 `network_id` varchar(25) NOT NULL DEFAULT '',
 `period` varchar(25),
 `param_name` varchar(45),
 `param_value` varchar(25),
 `description` varchar(250),
 PRIMARY KEY (`subscription_id`,`subscription_type`,`invoice_no`,`param_name`,`network_id`)
)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `invoice_details` ;
CREATE TABLE IF NOT EXISTS `invoice_details` (
 `invoice_no` varchar(25) NOT NULL,
  `invoice_date` datetime DEFAULT NULL,
  `invoice_amount` decimal(22,3) DEFAULT NULL,
  `due_amount` decimal(22,3) DEFAULT NULL,
  `currency` char(3) DEFAULT NULL,
  `inv_image` blob,
  `invoice_to` varchar(255) DEFAULT NULL,
  `invoice_from` varchar(255) DEFAULT NULL,
  `paid_Status` char(1) DEFAULT NULL,
  `paid_Date` datetime DEFAULT NULL,
  `dso` int(11) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`invoice_no`)
) 
ENGINE=InnoDB ;


DROP TABLE IF EXISTS `subscriber_master` ;
CREATE TABLE `subscriber_master` (
  `subscriber_id` varchar(25) NOT NULL DEFAULT '',
  `email` varchar(75) ,
  `mobile` varchar(25),
  `gender` char(1) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `occupations` varchar(25),
  `location` varchar(25) DEFAULT NULL,
  `network_id` varchar(25) DEFAULT NULL,
  `subscription_type` varchar(25) DEFAULT NULL,
  `reseller_id` varchar(25) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`subscriber_id`)
) ENGINE=InnoDB;
