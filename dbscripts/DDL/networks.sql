SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema esoko
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `Network_commodity_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Network_commodity_location` ;

CREATE TABLE IF NOT EXISTS `Network_commodity_location` (
  `network_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
  `commodity_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(100) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(100) NULL,
  `modified_ts` VARCHAR(25) NULL,
  PRIMARY KEY (`network_id`, `location_id`,`commodity_id` ))
ENGINE = InnoDB;

CREATE INDEX `IX_NWLoc` ON `Network_commodity_location` (`location_id` ASC);

CREATE INDEX `IX_NWCom` ON `Network_commodity_location` (`commodity_id` ASC);

DROP TABLE IF EXISTS `network_pictures` ;

CREATE TABLE IF NOT EXISTS `network_pictures` (
  `pic_id` INT NOT NULL AUTO_INCREMENT,
  `network_id` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `created_by` VARCHAR(100) NULL,
  `modified_by` VARCHAR(100) NULL,
  `modified_ts` VARCHAR(25) NULL,
  `content` MEDIUMBLOB NULL,
  PRIMARY KEY (`pic_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_nwdPic` ON `network_pictures` (`network_id` ASC);

DROP TABLE IF EXISTS `network_categories` ;

CREATE TABLE network_categories (

    `network_id` VARCHAR(25) NOT NULL,
     `category` VARCHAR(25) NOT NULL,
	  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
    PRIMARY KEY (`network_id`,`category`)
);


-- -----------------------------------------------------
-- Table `subscription_networks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subscription_networks` ;

CREATE TABLE IF NOT EXISTS `subscription_networks` (
  `subscription_id` VARCHAR(25) NOT NULL,
  `network_id` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`subscription_id`, `network_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_SubsNw` ON `subscription_networks` (`network_id` ASC);


-- -----------------------------------------------------
-- Table `subscriptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subscriptions` ;

CREATE TABLE IF NOT EXISTS `subscriptions` (
  `subscription_id` VARCHAR(25) NOT NULL,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `base_subscription_id` VARCHAR(25) NULL,
  `primary_network_id` VARCHAR(25) NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`subscription_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_BSubs` ON `subscriptions` (`base_subscription_id` ASC);

CREATE INDEX `IX_PrimNw` ON `subscriptions` (`primary_network_id` ASC);


-- -----------------------------------------------------
-- Table `subscription_attributes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subscription_attributes` ;

CREATE TABLE IF NOT EXISTS `subscription_attributes` (
  `sub_attribute_id` VARCHAR(25) NOT NULL,
  `subscription_id` VARCHAR(25) NOT NULL,
  `attr_name` VARCHAR(45) NULL,
  `attr_min_value` INT NULL,
  `attr_max_value` INT NULL,
  `record_status` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`sub_attribute_id`, `subscription_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_SubsName` ON `subscription_attributes` (`attr_name` ASC);


-- -----------------------------------------------------
-- Table `Network_logo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Network_logo` ;

CREATE TABLE IF NOT EXISTS `Network_logo` (
  `logo_id` VARCHAR(25) NOT NULL,
  `content` MEDIUMBLOB NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `network_id` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`logo_id`, `network_id`))
ENGINE = InnoDB;





-- -----------------------------------------------------
-- Table `network_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_location` ;

CREATE TABLE IF NOT EXISTS `network_location` (
  `network_id` VARCHAR(25) NOT NULL,
  `location_id` VARCHAR(25) NULL,
  `created_by` VARCHAR(45) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`network_id`, `location_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `System_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `System_accounts` ;

CREATE TABLE IF NOT EXISTS `System_accounts` (
  `account_no` VARCHAR(25) NOT NULL,
  `owner_id` VARCHAR(45) NULL,
  `type` CHAR(1) NULL,
  `balance` DECIMAL(22,3) NULL,
   `ac_currency` CHAR(3) NULL,
  `min_balance` DECIMAL(22,3) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `record_status` CHAR(1) NULL,
  `acc_name` VARCHAR(45) NULL,
  PRIMARY KEY (`account_no`))
ENGINE = InnoDB;

CREATE INDEX `IX_ACOwn` ON `System_accounts` (`owner_id` ASC);

CREATE INDEX `IX_AcType` ON `System_accounts` (`type` ASC);


-- -----------------------------------------------------
-- Table `Networks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Networks` ;

CREATE TABLE IF NOT EXISTS `Networks` (
  `network_id` VARCHAR(25) NOT NULL,
  `type` CHAR(1)  NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `owner_user_id` VARCHAR(100)  NOT NULL,
  `parent_id` VARCHAR(25) NULL,
  `primary_location_id` VARCHAR(25) NOT NULL,
  `logo_id` VARCHAR(25) NULL,
  `is_private` CHAR(1) NOT NULL,
  `is_sms_whitelisted` CHAR(1) NULL,
  `record_status` CHAR(1) NULL,
  `website` VARCHAR(50) NULL,
  `created_by` VARCHAR(100) NULL,
  `created_ts` DATETIME NULL,
    `auth_by` VARCHAR(100) NULL,
  `auth_ts` DATETIME NULL,
  `modified_by` VARCHAR(100) NULL,
  `modified_ts` DATETIME NULL,
  `is_visible` CHAR(1) NULL,
  `short_name` VARCHAR(50) NULL,
  `nw_gis` POINT NULL,
  `email` VARCHAR(25)  null,
  `auth_stat` CHAR(1) NULL,
  PRIMARY KEY (`network_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_NwOwn` ON `Networks` (`owner_user_id` ASC);

CREATE INDEX `IX_NwParent` ON `Networks` (`parent_id` ASC);

CREATE INDEX `IX_NwPrivate` ON `Networks` (`is_private` ASC);

CREATE INDEX `IX_IsWlist` ON `Networks` (`is_sms_whitelisted` ASC);

CREATE INDEX `IX_IsVisible` ON `Networks` (`is_visible` ASC);


-- -----------------------------------------------------
-- Table `Network_Apps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Network_Apps` ;

CREATE TABLE IF NOT EXISTS `Network_Apps` (
  `network_priv_id` VARCHAR(25) NULL,
  `network_id` VARCHAR(25) NOT NULL,
  `app_id` VARCHAR(25) NOT NULL,
  `is_private` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`network_id`, `app_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Network_privacy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Network_privacy` ;

CREATE TABLE IF NOT EXISTS `Network_privacy` (
    `network_id` VARCHAR(25) NOT NULL,
    `contacts` char(5),
	`offers` char(5) NULL,
	`prices` char(5) NULL,
   `news_lib` char(5) NULL,	
   `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`network_id` ))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `Network_pricetypes` ;

CREATE TABLE IF NOT EXISTS `Network_pricetypes` (
  `network_id` VARCHAR(25) NOT NULL,
  `farm_gate` Char(5)  NULL,
  `off_lorry` Char(5)  NULL,
  `retail` Char(5)  NULL,
  `wholesale` Char(5)  NULL,
  `producer` char(5) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`network_id` ))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Delete above table `Network_pricetypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_price_types` ;

CREATE TABLE `network_price_types` (
  `network_id` varchar(45) NOT NULL,
  `pricetype_id` varchar(45) NOT NULL,
  `selected_value` char(1) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`network_id`,`pricetype_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `Network_permissions` ;

CREATE TABLE IF NOT EXISTS `Network_permissions` (
  `network_id` VARCHAR(25) NOT NULL,
  `group_id` VARCHAR(25)  NULL,
  `billing` Char(5)  NULL,
  `configurations` Char(5)  NULL,
  `permissions` Char(5)  NULL,
  `sharing` char(5) NULL,
    `agents` Char(5)  NULL,
  `alerts` Char(5)  NULL,
  `approve` Char(5)  NULL,
  `inbox` char(5) NULL,
    `marketplace` Char(5)  NULL,
  `people` Char(5)  NULL,
  `push` char(5) NULL,
  `smspolls` Char(5)  NULL,
  `upload` char(5) NULL,
  `reports` char(5) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`network_id`,`group_id` ))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `network_commodities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_commodities` ;

CREATE TABLE IF NOT EXISTS `network_commodities` (
  `network_id` VARCHAR(25) NOT NULL,
  `commodity_id` VARCHAR(25) NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `rank` INT NULL,
  PRIMARY KEY (`network_id`, `commodity_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `network_sms_codes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_sms_codes` ;

CREATE TABLE IF NOT EXISTS `network_sms_codes` (
  `network_id` VARCHAR(25) NOT NULL,
  `sms_id` VARCHAR(25) NULL,
  `is_primary` CHAR(1) NULL,
  PRIMARY KEY (`network_id`,`sms_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `network_profiles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `network_profiles` ;

CREATE TABLE IF NOT EXISTS `network_profiles` (
  `network_user_id` VARCHAR(25) NOT NULL,
  `network_id` VARCHAR(25) NULL,
  `user_id` VARCHAR(25) NULL,
  `type` CHAR(1) NULL,
  `msisdn` VARCHAR(25) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  `record_status` CHAR(1) NULL,
  `email` VARCHAR(75) NULL,
  PRIMARY KEY (`network_user_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_NwUser` ON `network_profiles` (`network_user_id` ASC);


-- -----------------------------------------------------
-- Table `System_invoices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `System_invoices` ;

CREATE TABLE IF NOT EXISTS `System_invoices` (
  `invoice_no` VARCHAR(25) NOT NULL,
  `invoice_date` DATETIME NULL,
  `invoice_amount` DECIMAL(22,3) NULL,
  `currency` CHAR(3) NULL,
  `inv_image` MEDIUMBLOB NULL,
  `invoice_to` VARCHAR(255) NULL,
  `invoice_from` VARCHAR(255) NULL,
  `paid_Status` CHAR(1) NULL,
  `paid_Date` DATETIME NULL,
  `dso` INT NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
  PRIMARY KEY (`invoice_no`))
ENGINE = InnoDB;

CREATE INDEX `IX_InvFrm` ON `System_invoices` (`invoice_to` ASC);

CREATE INDEX `IX_InvTo` ON `System_invoices` (`invoice_to` ASC);


-- -----------------------------------------------------
-- Table `System_transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `System_transactions` ;

CREATE TABLE IF NOT EXISTS `System_transactions` (
  `transaction_ref_no` varchar(25) NULL,
  `transaction_id` INT NOT NULL,
  `trans_code` VARCHAR(25) NULL,
  `account_no` VARCHAR(25) NULL,
  `transaction_date` DATETIME NULL,
  `cr_dr` CHAR(1) NULL,
  `lcy_amount` DECIMAL(22,3) NULL,
  `fcy_amount` DECIMAL(22,3) NULL,
  `balance` DECIMAL(22,3) NULL,
  `ex_rate` DECIMAL(22,3) NULL,
  `local_ccy` CHAR(3) NULL,
  `fcy_ccy` CHAR(3) NULL,
  `network_id` VARCHAR(25) NULL,
  `description` varchar(25) NULL,
  `account_type` CHAR(1) NULL,
  `balance_Stat` CHAR(1) NULL,
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  PRIMARY KEY (`transaction_id`, `transaction_ref_no`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
