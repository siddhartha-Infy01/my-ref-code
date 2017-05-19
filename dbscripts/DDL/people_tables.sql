
create table people(
`people_id` varchar(50),
`first_name` varchar(150),
`last_name` varchar(150),
`nickname` varchar(45),
`gender` varchar(25),
`town` varchar(25),
`country` varchar(25),
`currency_id` varchar(25),
`language_id` varchar(25),
`msisdn` varchar(25),
`operator_id` varchar(25),
`email` varchar(150),
`record_status` char(1),
`is_visible` char(1),
`created_by` varchar(25),
`created_ts` timestamp,
`modified_By` varchar(25),
`modified_ts` timestamp,
`default_network_id` varchar(25),
`master_flag` char(2),
  `msisdn2` varchar(25) DEFAULT NULL,
  `title` varchar(25) DEFAULT NULL,
  `birthyear` varchar(25) DEFAULT NULL,
  `company` varchar(55) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `shortdesc` varchar(300) DEFAULT NULL,
  `fixedtel` varchar(25) DEFAULT NULL,
  `fax` varchar(30) DEFAULT NULL,
  `add1` varchar(45) DEFAULT NULL,
  `add2` varchar(45) DEFAULT NULL,
  `mode` char(2),
  `website` varchar(50) DEFAULT NULL,
primary key(`people_id`,`default_network_id`));


ALTER TABLE people modify COLUMN add1 varchar(1000);

ALTER TABLE people modify COLUMN add2 varchar(1000);

create table network_people_privacy
(
 `network_id` varchar(25),
 `first_name_shareable` char(2),
 `last_name_shareable` char(2),
`nickname_shareable` char(2), 
`photo_shareable` char(2), 
`country_shareable` char(2), 
`town_shareable` char(2),
`gender_shareable` char(2),
`currency_id_shareable` char(2), 
`language_id_shareable` char(2), 
`msisdn_shareable` char(2),
`email_shareable` char(2), 
 primary key(`network_id`));

 create table people_photo
 (`network_id` varchar(20),
 `people_id` varchar(25),
 `photo` mediumblob,
 primary key (`network_id`,`people_id`));

 create table people_pending_auth
(`pending_id` int NOT NULL AUTO_INCREMENT,
`people_id` varchar(25),
`owner_network_id` varchar(25),
`change_network_id` varchar(25),
`profile_field_changed` varchar(250),
`profile_field_value` varchar(250),
`auth_status` char(1),
`auth_by` varchar(25),
`auth_timestamp` timestamp,
primary key(`pending_id`));









