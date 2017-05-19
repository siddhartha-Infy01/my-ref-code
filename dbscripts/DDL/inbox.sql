DROP TABLE IF EXISTS `keywords` ;
CREATE TABLE keywords (

    keyword varchar(100) NOT NULL,
    network_id varchar(100) NOT NULL,
     created_by varchar(100),
    created_ts datetime,
    modified_by varchar(100),
    modified_ts datetime,
    record_status char(1),
	join_flag char(1),
	reply_flag char(1),
	airtime_flag char(1),
	payee_account varchar(100),
	response longtext,
	airtime decimal(22,3),
	airtime_currency varchar(100),
	no_of_messages int,
	no_characters int,
	ealerts longtext,
	groups longtext,
	my_network char(1),
	 PRIMARY KEY (`keyword`,`network_id`)
) ENGINE=InnoDB;

drop table if exists `inbox_monitor`;
 create table inbox_monitor ( activity_id int auto_increment,
 service_name varchar(100),
 people_id varchar(100),
 network_id varchar(100),
 msisdn varchar(25),
 message longtext,
 error_stat varchar(25),
 created_ts datetime,
 primary key(`activity_id`)
 )ENGINE=InnoDB;
 
 drop table if exists `keyword_alias`;
 create table keyword_alias (  keyword varchar(100) NOT NULL,
    channel varchar(100) NOT NULL,
	alias varchar(100) NOT NULL,
 primary key(`keyword`,`channel`,`alias`)
 )ENGINE=InnoDB;
 
 drop table if exists `poll_master`;
 
 CREATE TABLE `poll_master` (
  `poll_id` varchar(100) NOT NULL,
  `network_id` varchar(45) NOT NULL,
  `interpret` char(1) NOT NULL DEFAULT 'N',
  `send_reminder` int(11) NOT NULL DEFAULT '0',
  `reminder` text,
  `payee_acc_no` varchar(45) NOT NULL,
  `payee_type` char(1) NOT NULL,
  `currency_id` varchar(45) NOT NULL,
  `frequency` char(1) DEFAULT NULL,
  `repeat_flag` char(1) DEFAULT NULL,
  `schedule_days` varchar(45) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `schedule_time` time NOT NULL,
  `stop_interval` int(11) NOT NULL DEFAULT '0',
  `stop_measure` varchar(45) NOT NULL,
  `poll_code` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_ts` datetime DEFAULT NULL,
  `poll_name` varchar(45) NOT NULL,
  `poll_state` varchar(45) NOT NULL DEFAULT 'N',
  `public_keyword` varchar(45) DEFAULT NULL,
  `poll_active_till` date NOT NULL,
  PRIMARY KEY (`poll_id`)

) ENGINE=InnoDB;


drop table if exists `sms_controller`;
 
 CREATE TABLE `sms_controller` (
  `msisdn` varchar(100) NOT NULL,
   `action` varchar(100) NOT NULL,
  `TimiLimit` varchar(45) NOT NULL,
  `created_ts` datetime,
  PRIMARY KEY (`msisdn`)

) ENGINE=InnoDB;

drop table if exists `poll_alias`;

CREATE TABLE `poll_alias` (
  `poll_id` varchar(45) NOT NULL,
  `keyword` varchar(45) NOT NULL,
  `alias` varchar(45) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`poll_id`,`keyword`,`alias`)
) ENGINE=InnoDB;

drop table if exists `poll_details`;

CREATE TABLE `poll_details` (
  `poll_id` varchar(45) NOT NULL,
  `ques_no` varchar(45) NOT NULL,
  `poll_ques` longtext NOT NULL,
  `no_of_char` int(11) NOT NULL,
  `no_of_msg` int(11) NOT NULL,
  `correct_go_to` varchar(45) DEFAULT NULL,
  `incorrect_go_to` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`poll_id`,`ques_no`)
) ENGINE=InnoDB;

drop table if exists `poll_keywords`;

CREATE TABLE `poll_keywords` (
  `poll_id` varchar(45) NOT NULL,
  `keyword` varchar(45) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `poll_ques_no` varchar(45) NOT NULL,
  PRIMARY KEY (`poll_id`,`keyword`,`poll_ques_no`)

) ENGINE=InnoDB;