DROP TABLE IF EXISTS `push_alert_master` ;

CREATE TABLE `push_alert_master` (
  `push_alert_id` varchar(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `payee_type` varchar(25) DEFAULT NULL,
  `payee_network_id` varchar(25) DEFAULT NULL,
  `data_status` char(3) DEFAULT NULL,
  `currency_id` varchar(25) DEFAULT NULL,
  `alert_state` char(3) DEFAULT NULL,
  `is_public` char(3) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(100) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `payee_account_no` varchar(25) DEFAULT NULL,
  `language` varchar(25) DEFAULT NULL,
  `bid_offer` char(2) DEFAULT NULL,
  `no_of_messages` int(11) DEFAULT NULL,
  `text` longtext,
  `freq_flag` char(3) DEFAULT NULL,
  `repeats_flag` char(3) DEFAULT NULL,
  `country` varchar(25) DEFAULT NULL,
  `country_state` varchar(25) DEFAULT NULL,
  `town` varchar(25) DEFAULT NULL,
  `never_end_flag` char(3) DEFAULT NULL,
  `wheather_recepient` char(3) DEFAULT NULL,
  `price_recepient` char(3) DEFAULT NULL,
  `schedule_days` varchar(25) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `schedule_time` time DEFAULT NULL,
  `message_type` char(3) DEFAULT NULL,
  `airtime_flag` char(3) DEFAULT NULL,
  `schedule` char(3) DEFAULT NULL,
  `senderId` varchar(45) DEFAULT NULL,
  `alert_code` varchar(50),
  `send_copy` char(2),
  `airtime_amount` decimal(22,2),
  `airtime_currency` varchar(25),
  `send_email` char(2),
  `people_id` varchar(45) NOT NULL,
  PRIMARY KEY (`push_alert_id`,`people_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `alert_profile_based` ;

CREATE TABLE IF NOT EXISTS `alert_profile_based` (
  `profile_id` int auto_increment,
  `push_alert_id` VARCHAR(20) NOT NULL,
  `text` longtext,
  `user_id` varchar(45),
    PRIMARY KEY (`profile_id`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `alert_source_networks` ;

CREATE TABLE IF NOT EXISTS `alert_source_networks` (
  `push_alert_id` VARCHAR(20) NOT NULL,
  `network_id` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`push_alert_id`,`network_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `alert_locations` ;

CREATE TABLE IF NOT EXISTS `alert_locations` (
  `push_alert_id` VARCHAR(20) NOT NULL,
  `location_id` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`push_alert_id`,`location_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `alert_commodities` ;

CREATE TABLE IF NOT EXISTS `alert_commodities` (
  `push_alert_id` VARCHAR(20) NOT NULL,
  `commodity_id` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`push_alert_id`,`commodity_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `push_alert_recipients` ;

CREATE TABLE IF NOT EXISTS `push_alert_recipients` (
  `push_alert_id` VARCHAR(20) NOT NULL,
  `recipient_type` char(1) NOT NULL,
  `recipient_id` varchar(45) NOT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`push_alert_id`,`recipient_id`,`parent_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `operator_templates` ;
CREATE TABLE `operator_templates` (
  `template_id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_id` varchar(45) NOT NULL,
  `template` varchar(45) NOT NULL,
  `template_prefix` varchar(45) DEFAULT NULL,
  `template_length` int(11) DEFAULT NULL,
  PRIMARY KEY (`template_id`,`operator_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `operators` ;
CREATE TABLE IF NOT EXISTS `operators` (
  `operator_id` varchar(25) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `mcc` varchar(45) DEFAULT NULL,
  `mnc` varchar(45) DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `airtime_support` char(1) DEFAULT 'N',
  `first_pref_gateway` varchar(25) DEFAULT NULL,
  `second_pref_gateway` varchar(25) DEFAULT NULL,
  `third_pref_gateway` varchar(25) DEFAULT NULL,
  `location_id` varchar(25) DEFAULT NULL,
  `business_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`operator_id`))
ENGINE = InnoDB;




drop table if exists `gateways`;

CREATE TABLE IF NOT EXISTS `gateways` (
  `gateway_id` VARCHAR(20) NOT NULL,
  `route_id` varchar(25),
  `name` varchar(45) ,
  `smsc` varchar(45),
  `submit_url` varchar(45),
  `submit_username` varchar(45),
   `submit_password` varchar(45),
   `status_url` varchar(45),
   `status_password` varchar(45),
   `type` varchar(20),
      `callback_url` varchar(45),
   `data_status` char(1),
   `created_by` VARCHAR(25),
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `code` varchar(45) ,
  `secret` varchar(45),
  PRIMARY KEY (`gateway_id`,`route_id`,`type`))
ENGINE = InnoDB;

drop table if exists `gateway_details`;

CREATE TABLE `gateway_details` (
  `gateway_id` varchar(30) NOT NULL,
  `gateway_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB;

drop table if exists `smpp_routes`;

CREATE TABLE `smpp_routes` (
  `route_id` varchar(25) NOT NULL,
  `operator_id` varchar(45) DEFAULT NULL,
  `gateway_id` varchar(45) DEFAULT NULL,
  `cost` decimal(22,3) DEFAULT NULL,
  `cost_currency__id` varchar(45) DEFAULT NULL,
  `record_status` char(1) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `network_id` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `direction` varchar(45) DEFAULT NULL,
  `margin` decimal(22,3) DEFAULT NULL,
  `premium` decimal(22,3) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB;

drop table if exists `kannel_data`;

CREATE TABLE IF NOT EXISTS `kannel_data` (
  `smpp` VARCHAR(20) NOT NULL,
  `smsc` varchar(25) not null,
  `ipaddress` varchar(45) ,
  `port_no` varchar(45),
  
  PRIMARY KEY (`smpp`))
ENGINE = InnoDB;

drop table if exists `alert_price_type`;

CREATE TABLE `alert_price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alert_id` varchar(45) DEFAULT NULL,
  `price_type` varchar(100) DEFAULT NULL,
  `measure_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `push_templates` ;

CREATE TABLE `push_templates` (
 `network_id` varchar(25) NOT NULL,
 `TemplateId` varchar(45) NOT NULL,
 `message` longtext ,
 `created_by` varchar(25) ,
 `created_TS` datetime,
`auth_stat` char(2), 
`characters` int,
`messages` int,
 PRIMARY KEY (`network_id`,`TemplateId`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `sender_details` ;

CREATE TABLE `sender_details` (
 `sender_id` varchar(25) NOT NULL,
 `location_id` varchar(45) NOT NULL,
 `network_id` varchar(25) NOT NULL,
 `send_type` varchar(10) NOT NULL,
 `operator_id` varchar(10) NOT NULL,
 `created_by` varchar(25) ,
 `created_TS` datetime,
 `auth_stat` char(2), 
 PRIMARY KEY (`sender_id`,`location_id`,`network_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `upload_rate_report` ;

CREATE TABLE `upload_rate_report` (
  `operator_name` varchar(45) DEFAULT NULL,
  `mcc` varchar(45) DEFAULT NULL,
  `mnc` varchar(45) DEFAULT NULL,
  `location_id` varchar(45) DEFAULT NULL,
  `cost` decimal(22,3) DEFAULT NULL,
  `channel` varchar(45) DEFAULT NULL,
  `direction` varchar(45) DEFAULT NULL,
  `smpp` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `report_id` varchar(45) NOT NULL,
  `currency_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB


DROP TABLE IF EXISTS `bids_offers_master` ;

CREATE TABLE `bids_offers_master` (
  `upload_ID` varchar(45) ,
  `bid_offer_flag` CHAR(2) ,
  `commodity` varchar(45),
  `variety` varchar(45) ,
  `type` VARCHAR(45),
  `origin` varchar(45) ,
  `quantity` DECIMAL(22,3),
  `measure` varchar(45),
  `price_amount`DECIMAL(22,3),
  `currency_id` VARCHAR(25),
  `amount_unit` varchar(45) ,
  `negotiable_flag` CHAR(2),
  `expiry_date` DATETIME,
  `offer_owner` VARCHAR(25),
  `offer_uploaded_by` VARCHAR(25),
  `offer_userid` VARCHAR(50),
  `payement_mode` VARCHAR(50),
  `delivery_point` VARCHAR(50),
  `delivery_type` CHAR(2),
  `no_of_days` INTEGER,
  `documents` longtext,
  `notes` longtext,
  `grade` varchar(50),
  `upload_mode` char(2),
  `network_id` varchar(50),
  `auth_stat` char(2),
  `auth_by` varchar(50),
  `auth_ts` datetime,
  `created_by` varchar(50),
  `created_ts` datetime,
  `location` VARCHAR(50) NULL,
  PRIMARY KEY (`upload_ID`)
) ENGINE=InnoDB

DROP TABLE IF EXISTS `price_upload_master` ;

CREATE TABLE `price_upload_master` (
  `upload_ID` varchar(45) ,
  `commodity` varchar(45),
  `price_type` varchar(45) ,
  `market` VARCHAR(45),
  `collected_on` DATETIME ,
   `currency_id` VARCHAR(25),
  `price` DECIMAL(22,3),
  `measure_id`  VARCHAR(25),
   `variety` VARCHAR(25),
  `weight` DECIMAL(22,3),
  `weight_measure` VARCHAR(50),
    `variety_comment` longtext,
  `comments` longtext,
    `network_id` varchar(50),
  `auth_stat` char(2),
  `auth_by` varchar(50),
  `auth_ts` datetime,
  `created_by` varchar(50),
  `created_ts` datetime,
  `upload_mode` char(2),
  `agent_id` varchar(50),
  `upload_gis` POINT,
  `modified_by` varchar(45),
  `modified_ts` datetime,  
  PRIMARY KEY (`upload_ID`)
) ENGINE=InnoDB

CREATE INDEX `IX_price_upload_master` ON `price_upload_master` (`network_id`,`price_type`,`auth_stat`,`market`,`commodity` ASC);

DROP TABLE IF EXISTS `crop_tip_master` ;

CREATE TABLE IF NOT EXISTS `crop_tip_master` (
  `crop_tip_id` VARCHAR(100) NOT NULL,
  `crop_tip_name` varchar(100) not null,
  `network_id` varchar(100),
  `category` VARCHAR(100) NULL,
  `custom_category` VARCHAR(100) NULL,
  `trigger_type` VARCHAR(100) NULL,
  `payee_account` VARCHAR(100) NULL,
  `alert_code` varchar(100) NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `created_by` VARCHAR(100) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(100) NULL,
  `modified_ts` VARCHAR(25) NULL,
  `alert_state` char(2) ,
  `number_of_tips` int(11) default null,
  `commodity_id` varchar(100) default null,
  `currency_id` varchar(45) DEFAULT NULL,
  `payee_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`crop_tip_id` ))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `crop_tip_details` ;

CREATE TABLE IF NOT EXISTS `crop_tip_details` (
  `crop_tip_id` VARCHAR(100) NOT NULL,
  `tip_no` VARCHAR(100) NOT NULL,
  `no_of_messages` int(11) DEFAULT NULL,
  `text` longtext,
  `no_of_characters` int(11) DEFAULT NULL,
  `transmission_date` datetime DEFAULT NULL,
  `interval_period` int(11) default null,
  `interval_measure` VARCHAR(100) NULL,
    `created_by` VARCHAR(100) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(100) NULL,
  `modified_ts` DATETIME NULL,
  `transmission_time` time DEFAULT NULL,
  PRIMARY KEY (`crop_tip_id` ,`tip_no`))
ENGINE = InnoDB;


