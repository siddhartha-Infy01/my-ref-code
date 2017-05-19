DROP TABLE IF EXISTS `library` ;

CREATE TABLE IF NOT EXISTS `library` (
  `lib_id` int NOT NULL AUTO_INCREMENT,
  `network_id` VARCHAR(45) NOT NULL,
  `title` VARCHAR(25)  NOT NULL,
  `summary` LONGTEXT  NOT NULL,
  `story` LONGTEXT NOT NULL,
  `source_url` MEDIUMTEXT NULL,
  `published_ts` DATETIME NULL,
  `type` char(5) not null,
   `category_id` varchar(25),
  `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `auth_by` VARCHAR(25) NULL,
  `auth_ts` DATETIME NULL,
  `auth_stat` CHAR(1) NULL,
  `author` varchar(25) null,
  `modified_by` VARCHAR(25) NULL,
  `modified_ts` DATETIME NULL,
   PRIMARY KEY (`lib_id`))
ENGINE = InnoDB;

CREATE INDEX `IX_NetworkId` ON `library` (`network_id` ASC);

DROP TABLE IF EXISTS `library_images` ;

CREATE TABLE `library_images` (
    `pic_id` int NOT NULL AUTO_INCREMENT ,
    `library_id` int NOT NULL,
    `content` longblob  NULL,
  PRIMARY KEY (`pic_id`)
);

DROP TABLE IF EXISTS `library_files` ;

CREATE TABLE `library_files` (
    `file_id` int NOT NULL AUTO_INCREMENT ,
    `library_id` int NOT NULL ,
    `file_content` longblob  NULL,
  `filename` varchar(25),
  `filetype` varchar(20),
  PRIMARY KEY (`file_id`)
);


DROP TABLE IF EXISTS `library_locations` ;

CREATE TABLE `library_locations` (
    `lib_loc_id` int NOT NULL AUTO_INCREMENT ,
    `library_id` int NOT NULL ,
    `location_id` varchar(25) not NULL,
  PRIMARY KEY (`lib_loc_id`)
);


DROP TABLE IF EXISTS `library_commodity` ;

CREATE TABLE `library_commodity` (
    `lib_comm_id` int NOT NULL AUTO_INCREMENT ,
    `library_id` int NOT NULL ,
    `commodity_id` varchar(25) NOT NULL,
  PRIMARY KEY (`lib_comm_id`)
);

DROP TABLE IF EXISTS `lib_categories` ;

CREATE TABLE `lib_categories` (
    `category_id` varchar(25) not NULL,
  `type` char(5) not null,
    `STATUS` CHAR(5) NOT NULL,
    `NAME` varchar(25)  NULL,
  PRIMARY KEY (`category_id`)
);



