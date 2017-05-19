SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema esoko
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `esoko` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `esoko` ;




-- -----------------------------------------------------
-- Table `Transaction_codes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Transaction_codes` ;

CREATE TABLE IF NOT EXISTS `Transaction_codes` (
  `Transaction_code` VARCHAR(20) NOT NULL,
  `description` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`Transaction_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `occupations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `occupations` ;

CREATE TABLE IF NOT EXISTS `occupations` (
  `occupation_id` VARCHAR(25) NOT NULL,
  `type` VARCHAR(25) NULL,
  `rank` INT NULL,
  `parent_id` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
   `auth_by` VARCHAR(25) NULL,
  `auth_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`occupation_id`))
ENGINE = InnoDB;

CREATE INDEX `ix_ocuparent` ON `occupations` (`parent_id` ASC);

CREATE INDEX `IX_OccRank` ON `occupations` (`rank` ASC);


-- -----------------------------------------------------
-- Table `forex_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forex_history` ;

CREATE TABLE IF NOT EXISTS `forex_history` (
  `forex_id` VARCHAR(25) NOT NULL,
  `date` DATETIME NOT NULL,
  `base_currency_id` CHAR(3) NULL,
  `quote_currency_id` CHAR(3) NULL,
  `rate` DECIMAL(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `modified_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`forex_id`, `date`))
ENGINE = InnoDB;

CREATE INDEX `IX_HBCur` ON `forex_history` (`base_currency_id` ASC);

CREATE INDEX `IX_HQcur` ON `forex_history` (`quote_currency_id` ASC);


-- -----------------------------------------------------
-- Table `currencies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `currencies` ;

CREATE TABLE IF NOT EXISTS `currencies` (
  `currency_id` CHAR(3) NOT NULL,
  `code` CHAR(3) NULL,
  `symbol` VARCHAR(6) NULL,
  `number_of_decimals` INT NULL,
  `rank` INT NULL,
  `record_status` CHAR(1) NULL,
  `name` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `modified_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`currency_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_CCode` ON `currencies` (`code` ASC);

CREATE INDEX `IX_CRank` ON `currencies` (`rank` ASC);


-- -----------------------------------------------------
-- Table `locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locations` ;

CREATE TABLE IF NOT EXISTS `locations` (
  `location_id` VARCHAR(25) NOT NULL,
  `type` VARCHAR(45) NULL,
  `rank` INT NULL,
  `parent_id` VARCHAR(25) NULL,
  `gis` POINT NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `auth_by` VARCHAR(25) NULL,
  `auth_ts` DATETIME NULL,
  `auth_stat` CHAR(1) NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `currency` VARCHAR(45) NULL,
  `parent_country` VARCHAR(45) NULL,
  PRIMARY KEY (`location_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_LocParent` ON `locations` (`parent_id` ASC);

CREATE INDEX `IX_LRank` ON `locations` (`rank` ASC);
-- -----------------------------------------------------
-- Table `locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_details` ;

CREATE TABLE `market_details` (
  `location_id` VARCHAR(25) NOT NULL,
  `days_of_week` VARCHAR(100) NULL,
  `price_types` VARCHAR(100) NULL,
  `commodities` VARCHAR(100) NULL,
  `location_pic` MEDIUMBLOB NULL,
  PRIMARY KEY (`location_id`));

-- -----------------------------------------------------
-- Table `forex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forex` ;

CREATE TABLE IF NOT EXISTS `forex` (
  `forex_id` VARCHAR(25) NOT NULL,
  `date` DATETIME NULL,
  `base_currency_id` CHAR(3) NULL,
  `quote_currency_id` CHAR(3) NULL,
  `rate` DECIMAL(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `modified_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  `record_status` CHAR(1) NULL,
  PRIMARY KEY (`forex_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_BCur` ON `forex` (`base_currency_id` ASC);

CREATE INDEX `IX_Qcur` ON `forex` (`quote_currency_id` ASC);


-- -----------------------------------------------------
-- Table `location_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location_alias` ;

CREATE TABLE IF NOT EXISTS `location_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
      `name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_locationAl` ON `location_alias` (`location_id` ASC);


-- -----------------------------------------------------
-- Table `Measures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Measures` ;

CREATE TABLE IF NOT EXISTS `Measures` (
  `measure_id` VARCHAR(25) NOT NULL,
  `symbol` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `type` VARCHAR(25) NULL,
  `is_system` CHAR(1) NULL,
  `measure_name` VARCHAR(25) NULL,
  `description` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `modified_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`measure_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `measure_factor`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `measure_factor` ;

CREATE TABLE IF NOT EXISTS `measure_factor` (
  `measure_conv_id` int NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `location_id` varchar(25),
  `commodity_id` varchar(25),
  `network_id` varchar(25),
  `price_type` varchar(25),
  `base_measure_id` VARCHAR(25) NULL,
  `quote_measure_id` VARCHAR(25) NULL,
  `conv_factor` DECIMAL(22,3) NULL,
  `conv_factor` DECIMAL(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  `default_measure` CHAR(1) NULL DEFAULT 'N',
  PRIMARY KEY (`measure_conv_id`,`commodity_id`,`network_id`,`price_type`))
ENGINE = InnoDB;

CREATE INDEX `IX_BMes` ON `measure_factor` (`base_measure_id` ASC);

CREATE INDEX `IX_QMes` ON `measure_factor` (`quote_measure_id` ASC);
-- -----------------------------------------------------
-- Table `measure_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `measure_alias` ;

CREATE TABLE IF NOT EXISTS `measure_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `measure_id` VARCHAR(25) NULL,
  `measure_name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_Mid` ON `measure_alias` (`measure_id` ASC);
-- -----------------------------------------------------
-- Table `commodity_measures`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `commodity_measures` ;

CREATE TABLE IF NOT EXISTS `commodity_measures` (
  `measure_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `location_id` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `modified_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`measure_id`,`commodity_id`,`location_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `weather`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `weather` ;

CREATE TABLE IF NOT EXISTS `weather` (
  `weather_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
  `measure_id` VARCHAR(25) NULL,
  `source` VARCHAR(25) NULL,
  `date` DATETIME NULL,
  `min_temp` VARCHAR(25) NULL,
  `max_temp` VARCHAR(25) NULL,
  `summary` VARCHAR(150) NULL,
  `rain` VARCHAR(150) NULL,
  `response` longtext NULL,
  PRIMARY KEY (`weather_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_WLoc` ON `weather` (`location_id` ASC);


-- -----------------------------------------------------
-- Table `weather_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `weather_history` ;

CREATE TABLE IF NOT EXISTS `weather_history` (
   `weather_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
  `measure_id` VARCHAR(25) NULL,
  `source` VARCHAR(25) NULL,
  `date` DATETIME NULL,
  `min_temp` VARCHAR(25) NULL,
  `max_temp` VARCHAR(25) NULL,
  `summary` VARCHAR(150) NULL,
  `rain` VARCHAR(150) NULL,
  `response` longtext NULL,
  PRIMARY KEY (`weather_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_WLoc` ON `weather_history` (`location_id` ASC);

-- -----------------------------------------------------
-- Table `Weather_source`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Weather_source` ;

CREATE TABLE IF NOT EXISTS `Weather_source` (
  network_id VARCHAR(20) NOT NULL,
  source VARCHAR(45) NOT NULL,
  user_id VARCHAR(45) NOT NULL,
  location VARCHAR(45) NULL,
  created_by VARCHAR(25) NULL,
  created_ts DATETIME NULL,
  auth_by VARCHAR(25) NULL,
  auth_ts DATETIME NULL,
  auth_stat CHAR(1) NULL,
  modified_by VARCHAR(25) NULL,
  modified_ts DATETIME NULL,
  PRIMARY KEY (network_id,source))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `commodities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodities` ;

CREATE TABLE IF NOT EXISTS `commodities` (
  `commodity_id` VARCHAR(25) NOT NULL,
  `type` VARCHAR(45) NULL,
  `rank` INT NULL,
  `parent_id` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `auth_by` VARCHAR(25) NULL,
  `auth_ts` DATETIME NULL,
  `auth_stat` CHAR(1) NULL,
  `hscode` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `global_location` VARCHAR(45) NULL,
  PRIMARY KEY (`commodity_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_ComParent` ON `commodities` (`parent_id` ASC);

CREATE INDEX `IX_Hscode` ON `commodities` (`hscode` ASC);


-- -----------------------------------------------------
-- Table `commodity_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodity_alias` ;

CREATE TABLE IF NOT EXISTS `commodity_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_AlComm` ON `commodity_alias` (`commodity_id` ASC);


-- -----------------------------------------------------
-- Table `commodity_pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodity_pictures` ;

CREATE TABLE IF NOT EXISTS `commodity_pictures` (
  `pic_id` INT NOT NULL AUTO_INCREMENT,
  `commodity_id` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `created_by` VARCHAR(25) NULL,
  `content` MEDIUMBLOB NULL,
  PRIMARY KEY (`pic_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_ComidPic` ON `commodity_pictures` (`commodity_id` ASC);


-- -----------------------------------------------------
-- Table `commodity_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodity_type` ;

CREATE TABLE IF NOT EXISTS `commodity_type` (
  `q_type_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `record_status` CHAR(1) NULL,
  PRIMARY KEY (`q_type_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_Comm` ON `commodity_type` (`commodity_id` ASC);

-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE TABLE IF NOT EXISTS `type` (
  `type_id` VARCHAR(25) NOT NULL,
  `description` VARCHAR(100),
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `commodity_variety`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodity_variety` ;

CREATE TABLE IF NOT EXISTS `commodity_variety` (
  `commodity_id` VARCHAR(25) NOT NULL,
  `q_type_id` VARCHAR(25) NULL,
  `attribute_id` VARCHAR(25) NULL,
  `variety_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`variety_id`))
ENGINE = InnoDB;
CREATE unique INDEX `IX_Comm_variety` ON `commodity_variety` (`commodity_id` ASC, `q_type_id` ASC, `attribute_id` ASC);


-- -----------------------------------------------------
-- Table `comm_variety_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comm_variety_alias` ;

CREATE TABLE IF NOT EXISTS `comm_variety_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `attibute_id` VARCHAR(25) NULL,
  `q_type_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  `variety_id` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_CommVar` ON `comm_variety_alias` (`commodity_id` ASC);


-- -----------------------------------------------------
-- Table `commodity_attributes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commodity_attributes` ;

CREATE TABLE IF NOT EXISTS `commodity_attributes` (
  `attribute_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `att_name` VARCHAR(45) NULL,
  PRIMARY KEY (`attribute_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_VComm` ON `commodity_attributes` (`commodity_id` ASC);

-- -----------------------------------------------------
-- Table `attributes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `attributes` ;

CREATE TABLE IF NOT EXISTS `attributes` (
  `attribute_id` VARCHAR(25) NOT NULL,
  `att_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`attribute_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comm_attribute_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comm_attribute_alias` ;

CREATE TABLE IF NOT EXISTS `comm_attribute_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `attribute_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_AttrAl` ON `comm_attribute_alias` (`attribute_id` ASC);


-- -----------------------------------------------------
-- Table `comm_type_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comm_type_alias` ;

CREATE TABLE IF NOT EXISTS `comm_type_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `q_type_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_Qtype` ON `comm_type_alias` (`q_type_id` ASC);


-- -----------------------------------------------------
-- Table `price_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `price_types` ;

CREATE TABLE IF NOT EXISTS `price_types` (
  `price_type_id` VARCHAR(25) NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `record_status` CHAR(1) NULL,
  PRIMARY KEY (`price_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `price_type_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `price_type_alias` ;

CREATE TABLE IF NOT EXISTS `price_type_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `price_type_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_PriceId` ON `price_type_alias` (`price_type_id` ASC);


-- -----------------------------------------------------
-- Table `esoko_apps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `esoko_apps` ;

CREATE TABLE IF NOT EXISTS `esoko_apps` (
  `app_id` VARCHAR(25) NOT NULL,
  `name` VARCHAR(45) NULL,
  `record_status` CHAR(1) NULL,
  `rank` INT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`app_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_AppRank` ON `esoko_apps` (`rank` ASC);


-- -----------------------------------------------------
-- Table `sms_codes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms_codes` ;

CREATE TABLE IF NOT EXISTS `sms_codes` (
  `sms_id` VARCHAR(25) NOT NULL,
  `network_id` VARCHAR(45) NOT NULL,
  `sms_Code` VARCHAR(45) NULL ,
  `type` char(5) not null,
    `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`sms_id`,`network_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sms_code_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sms_code_alias` ;

CREATE TABLE IF NOT EXISTS `sms_code_alias` (
  `alias_id` VARCHAR(25) NOT NULL,
  `network_id` varchar(25) not null,
  `smid_id` VARCHAR(25) NULL,
  `sms_code` VARCHAR(45) NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `languages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `languages` ;

CREATE TABLE IF NOT EXISTS `languages` (
  `language_id` VARCHAR(25) NOT NULL,
  `code` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  `rank` INT NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`language_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_LCode` ON `languages` (`code` ASC);

DROP TABLE IF EXISTS `operators` ;

CREATE TABLE IF NOT EXISTS `operators` (
  `operator_id` VARCHAR(25) NOT NULL,
  `name` VARCHAR(45) NULL,
  `mcc` VARCHAR(45) NULL,
  `mnc` VARCHAR(45) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `airtime_support` boolean,
  `first_pref_gateway` VARCHAR(25),
  `second_pref_gateway` VARCHAR(25),
  `third_pref_gateway` VARCHAR(25),
  `location_id` varchar(25),
  `business_name` varchar(25),
  PRIMARY KEY (`operator_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_operator_loc` ON `operators` (`location_id` ASC);

DROP TABLE IF EXISTS `smpp_routes` ;

CREATE TABLE IF NOT EXISTS `smpp_routes` (
  `route_id` VARCHAR(25) NOT NULL,
  `operator_id` VARCHAR(45) NULL,
  `gateway_id` VARCHAR(45) NULL,
  `cost` DECIMAL(22,3) NULL,
  `price` DECIMAL(22,3) NULL,
  `cost_currency__id` VARCHAR(45) NULL,
  `price_currency__id` VARCHAR(45) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
    PRIMARY KEY (`route_id`))
ENGINE = InnoDB;






SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
