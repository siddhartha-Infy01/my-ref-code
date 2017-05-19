SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema esoko
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `system_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `system_user` ;

CREATE TABLE IF NOT EXISTS `system_user` (
  `user_id` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(150) NULL,
  `last_name` VARCHAR(150) NULL,
  `nickname` VARCHAR(45) NULL,
  `password` VARCHAR(150) NULL,
  `gender` VARCHAR(45) NULL,
  `type` CHAR(1) NULL,
  `agent_id` VARCHAR(25) NULL,
   `website` VARCHAR(50) NULL,
  `about_me` VARCHAR(50) NULL,  
  `currency_id` VARCHAR(25) NULL,
  `UI_language` varCHAR(25) NULL,
  `language_id` varCHAR(25) NULL,
  `birth_year` VARCHAR(50) NULL,
  `msisdn` VARCHAR(25) NULL,
  `operator_id` varchar(25),
  `email` VARCHAR(150) NULL,
  `msisdn_verification` CHAR(1) NULL,
  `email_verification` CHAR(1) NULL,
  `default_network_id` VARCHAR(25) NULL,
  `is_visible` CHAR(1) NULL,
  `msisdn2` VARCHAR(25) NULL,
  `email2` VARCHAR(150) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `USER_GIS` POINT NULL,
  `country` VARCHAR(45) NULL,
  `town` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `auth_by` VARCHAR(1000) NULL,
  `auth_ts` DATETIME NULL,
  `auth_status` CHAR(1) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_NNAME` ON `system_user` (`nickname` ASC);

CREATE INDEX `ix_pRIMlOC` ON `system_user` (`primary_location_id` ASC);

CREATE INDEX `ix_nWiD` ON `system_user` (`default_network_id` ASC);


-- -----------------------------------------------------
-- Table `user_pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_pictures` ;

CREATE TABLE IF NOT EXISTS `user_pictures` (
  `pic_id` int NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(100) NOT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `content` MEDIUMBLOB NULL,
  PRIMARY KEY (`pic_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_occupations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_occupations` ;

CREATE TABLE IF NOT EXISTS `user_occupations` (

  `user_id` VARCHAR(100) NOT NULL,
  `occupation_id` VARCHAR(25) NULL,
  `network_id` VARCHAR(25) NOT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `name` VARCHAR(25) NULL,
  PRIMARY KEY (`user_id`,`occupation_id`,`network_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_preferences` ;

CREATE TABLE IF NOT EXISTS `user_preferences` (
  `user_id` VARCHAR(25) NOT NULL,
  `preference_id` VARCHAR(25) NOT NULL,
  `preference_name` VARCHAR(50) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `preference_value` VARCHAR(150) NULL,
  PRIMARY KEY (`user_id`, `preference_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_locations` ;

CREATE TABLE IF NOT EXISTS `user_locations` (
   `user_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
  `name` VARCHAR(25) NULL,
  `network_id` VARCHAR(25) NOT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`location_id`, `user_id`,`network_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `network_userid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_userid` ;

CREATE TABLE IF NOT EXISTS `network_userid` (
  `user_id` VARCHAR(100) NOT NULL,
  `network_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `network_name` VARCHAR(25) NULL,
  PRIMARY KEY (`user_id`, `network_id` ))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_personalization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_personalization` ;

CREATE TABLE IF NOT EXISTS `user_personalization` (
  `user_id` VARCHAR(25) NOT NULL,
  `widget_id` VARCHAR(25) NULL,
  `x_cordinates` INT NULL,
  `y_cordinates` INT NULL,
  `widget_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`user_id`, `widget_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `USER_Notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_notifications` ;

CREATE TABLE `user_notifications` (
  `notification_id` varchar(25) NOT NULL,
  `type` char(1) DEFAULT 'U',
  `user_grp_id` varchar(25) DEFAULT NULL,
  `network_id` varchar(45) DEFAULT NULL,
  `notification_msg` varchar(150) DEFAULT NULL,
  `status` char(1) DEFAULT 'U',
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`notification_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_commodities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_commodities` ;

CREATE TABLE IF NOT EXISTS `user_commodities` (
  `user_id` VARCHAR(100) NOT NULL,
  `commodity_id` VARCHAR(25) NOT NULL,
  `name` VARCHAR(50) NULL,
  `description` VARCHAR(150) NULL,
  `network_id` VARCHAR(25) NOT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`user_id`, `commodity_id`,`network_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_group` ;

CREATE TABLE `user_group` (
  `group_id` varchar(45) NOT NULL,
  `user_id` varchar(100) NOT NULL DEFAULT '',
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `network_id` varchar(25) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`,`network_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user_smart_group` ;

create table user_smart_group like user_group;

DROP TABLE IF EXISTS `smart_group_history` ;

create table  smart_group_history like user_smart_group;

-- -----------------------------------------------------
-- Table `group_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_master`;

CREATE TABLE `group_master` (
  `group_id` varchar(25) NOT NULL,
  `GROUP_name` varchar(150) DEFAULT NULL,
  `network_id` varchar(25) NOT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `type` char(1) DEFAULT 'N',
  PRIMARY KEY (`group_id`,`network_id`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `smartgroup_rules`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `smartgroup_rules`;

CREATE TABLE `smartgroup_rules` (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(45) NOT NULL,
  `network_id` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `conditions` varchar(45) DEFAULT NULL,
  `value` varchar(1000) NOT NULL,
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table `Network_admins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Network_admins` ;

CREATE TABLE IF NOT EXISTS `Network_admins` (
  `user_id` VARCHAR(25) NOT NULL,
  `network_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`user_id`, `network_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message_delivary_Details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message_delivary_Details` ;

CREATE TABLE IF NOT EXISTS `message_delivary_Details` (
  `trn_ref_no` VARCHAR(25) NOT NULL,
  `message_id` VARCHAR(25) NOT NULL,
  `user_id` VARCHAR(200) NULL,
  `schedule_id` VARCHAR(25) NULL,
  `network_id` VARCHAR(25) NULL,
  `MSG_STATUS` CHAR(1) NULL,
  `MSG_COST` DECIMAL(22,3) NULL,
  `msisdn` VARCHAR(25) NULL,
  `route_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_TS` DATETIME NULL,
  `message_type` varchar(25),
  `description` VARCHAR(500),
  `answer` VARCHAR(500),
    PRIMARY KEY (`trn_ref_no`, `message_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_MsgSch` ON `message_delivary_Details` (`schedule_id` ASC);

CREATE INDEX `IX_NwMsg` ON `message_delivary_Details` (`network_id` ASC);


-- -----------------------------------------------------
-- Table `widget_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `widget_details` ;

CREATE TABLE IF NOT EXISTS `widget_details` (
  `widget_id` VARCHAR(25) NOT NULL,
  `description` VARCHAR(50) NULL,
  PRIMARY KEY (`widget_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_widget_settings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_widget_settings` ;

CREATE TABLE IF NOT EXISTS `user_widget_settings` (
  `user_id` VARCHAR(100) NOT NULL,
  `widget_id` VARCHAR(25) NOT NULL,
   `commodity_list` VARCHAR(1000) NULL,
  `network_list` VARCHAR(1000) NULL,
  `location_list` VARCHAR(1000) NULL,
  `currency` CHAR(3) NULL,
  `date_range` VARCHAR(255) NULL,
  `price_type` VARCHAR(25) NULL,
  `network_public` char(2) NOT NULL,
  `bids_offer` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`user_id`, `widget_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `messaging_contents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `messaging_contents` ;

CREATE TABLE IF NOT EXISTS `messaging_contents` (
  `message_id` VARCHAR(25) NOT NULL,
  `messagecontent` VARCHAR(1000) NULL,
  `type` VARCHAR(25) NULL,
  `subtype` VARCHAR(25) NULL,
  `location_id` VARCHAR(25) NULL,
  `network_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  PRIMARY KEY (`message_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_shares`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_shares` ;

CREATE TABLE IF NOT EXISTS `user_shares` (
   `from_share` VARCHAR(150) NULL,
  `to_share` VARCHAR(150) NULL,
  `share_item` VARCHAR(150) NULL,
  `share_type` CHAR(1) NULL,
  `auth_stat` CHAR(1) NULL,
  `share_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `people_type` VARCHAR(45),
   `people_mode` char(2),
  PRIMARY KEY (`from_share`,`to_share`,`share_item`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `profile_Sharing_types` (
  `profile_id` varchar(25),
  `network_id`  varchar(25),
  `name_flag` char(2),
  `birth_year_flag` char(2),
  `gender_flag` char(2),
  `mobile_number_flag` char(2),
  `address_flag` char(2),
  `company_flag` char(2),
  `language_flag` char(2),
  `email_flag` char(2),
  `currency_flag` char(2),
  PRIMARY KEY (`profile_id`,`network_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `agent` ;

CREATE TABLE IF NOT EXISTS `agent` (
  `network_id` VARCHAR(25) NOT NULL,
  `user_id` VARCHAR(50) not  NULL,
  `currency_id` VARCHAR(25) NULL,
  `bonus` Decimal(22,3) NULL,
  `salary` Decimal(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `record_status` char(2),
  PRIMARY KEY (`network_id`,`user_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `agent_details` ;

CREATE TABLE IF NOT EXISTS `agent_details` (
  `detail_id` int AUTO_INCREMENT,
  `network_id` VARCHAR(25) NOT NULL,
  `user_id` VARCHAR(1000)  not NULL,
  `application_id` varchar(25) NOT null,
  `location_id` varchar(25) null,
  `commodity_id` varchar(25) null,
  `template` varchar(25) null,
  `target` int,
  `record_status` char,
  `currency_id` VARCHAR(25) NULL,
  `effective_date` DATETIME,
  `bonus` Decimal(22,3) NULL,
  `incentive` Decimal(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`detail_id`))
ENGINE = InnoDB;



DROP TABLE IF EXISTS `agent_details_history` ;

CREATE TABLE IF NOT EXISTS `agent_details_history` (
  `detail_id` int,
  `network_id` VARCHAR(25) NOT NULL,
  `user_id` VARCHAR(1000)  not NULL,
  `application_id` varchar(25) NOT null,
  `location_id` varchar(25) null,
  `commodity_id` varchar(25) null,
  `template` varchar(25) null,
  `target` int,
  `currency_id` VARCHAR(25) NULL,
  `effective_date` DATETIME,
  `bonus` Decimal(22,3) NULL,
  `incentive` Decimal(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  PRIMARY KEY (`effective_date`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `upload_master` ;

create table IF NOT EXISTS `upload_master`(
`upload_id` int AUTO_INCREMENT,
`application_id` varchar(25),
`agent_id` varchar(25),
`detail_id` int,
`network_id` varchar(25),
`upload_dt` DATETIME,
`auth_stat` char,
`auth_by` varchar(25),
`auth_ts` DATETIME,
'modified_by' varchar(25),
'modified_ts' DATETIME,
primary key(`upload_id`)
)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `agent_upload_count` ;

CREATE TABLE `agent_upload_count` (
 `detail_id` int(11) NOT NULL,
 `effective_date` varchar(45) NOT NULL,
 `uploads` int(11) NOT NULL DEFAULT '0',
 `upload_accuracy` int(11) NOT NULL DEFAULT '0',
 PRIMARY KEY (`detail_id`,`effective_date`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_templates` ;

CREATE TABLE `survey_templates` (
 `network_id` varchar(25) NOT NULL,
 `TemplateId` varchar(45) NOT NULL,
 `description` varchar(45) NOT NULL ,
 `created_by` varchar(25) ,
 `created_TS` datetime,
`auth_stat` char(2), 
'survey_definition' longtext NULL,
 PRIMARY KEY (`network_id`,`TemplateId`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `signup_detail` ;

CREATE TABLE `signup_detail` (
  `user_id` varchar(45) NOT NULL,
  `otp` varchar(45) NOT NULL,
  `timestamp` datetime NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'P',
  `network_id` varchar(45) NOT NULL,
  `device_id` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
