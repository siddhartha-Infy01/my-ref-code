DROP TABLE IF EXISTS `subscription_master` ;
CREATE TABLE `subscription_master` (
  `subscription_id` int auto_increment,
  `subscription_type` varchar(25) NOT NULL DEFAULT '',
  `subscription_level` varchar(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`subscription_id`,`subscription_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1$$

;

DROP TABLE IF EXISTS `subscription_details` ;
CREATE TABLE IF NOT EXISTS `subscription_details` (
 `subscription_id` int null,
 `subscription_type` varchar(25),
 `subscription_level` varchar(1) NOT NULL DEFAULT '',
 `subscription_category` char(1),
 `param_name` varchar(25),
 `param_value` varchar(25),
 `country_name` varchar(25),
 `network_name` varchar(25),
 `margin` varchar(25),
  PRIMARY KEY (`subscription_id`,`param_name`,`subscription_type`))
ENGINE = InnoDB;