drop table if exists `survey`;
 create table survey ( survey_id int auto_increment,
 survey_name varchar(100),
 survey_type varchar(100),
  phase varchar(100),
 network_id varchar(100),
 user_id varchar(25),
 total_score varchar(100),
 secured_score varchar(100),
 txt_qns_1 varchar(50),
 txt_fld_1 varchar(500),
 txt_qns_2 varchar(50),
 txt_fld_2 varchar(500),
 txt_qns_3 varchar(50),
 txt_fld_3 varchar(500),
 txt_qns_4 varchar(50),
 txt_fld_4 varchar(500),
 txt_qns_5 varchar(50),
 txt_fld_5 varchar(500),
 txt_qns_6 varchar(50),
 txt_fld_6 varchar(500),
 txt_qns_7 varchar(50),
 txt_fld_7 varchar(500),
 txt_qns_8 varchar(50),
 txt_fld_8 varchar(500),
 txt_qns_9 varchar(50),
 txt_fld_9 varchar(500),
 txt_qns_10 varchar(50),
 txt_fld_10 varchar(500),
 txt_qns_11 varchar(50),
 txt_fld_11 varchar(500),
 txt_qns_12 varchar(50),
 txt_fld_12 varchar(500),
 txt_qns_13 varchar(50),
 txt_fld_13 varchar(500),
 txt_qns_14 varchar(50),
 txt_fld_14 varchar(500),
 txt_qns_15 varchar(50),
 txt_fld_15 varchar(500),
 txt_qns_16 varchar(50),
 txt_fld_16 varchar(500),
 txt_qns_17 varchar(50),
 txt_fld_17 varchar(500),
 txt_qns_18 varchar(50),
 txt_fld_18 varchar(500),
 txt_qns_19 varchar(50),
 txt_fld_19 varchar(500),
 txt_qns_20 varchar(50),
 txt_fld_20 varchar(500),
 txt_qns_21 varchar(50),
 txt_fld_21 varchar(500),
 txt_qns_22 varchar(50),
 txt_fld_22 varchar(500),
 txt_qns_23 varchar(50),
 txt_fld_23 varchar(500),
 txt_qns_24 varchar(50),
 txt_fld_24 varchar(500),
 txt_qns_25 varchar(50),
 txt_fld_25 varchar(500),
 txt_qns_26 varchar(50),
 txt_fld_26 varchar(500),
 txt_qns_27 varchar(50),
 txt_fld_27 varchar(500),
 txt_qns_28 varchar(50),
 txt_fld_28 varchar(500),
 txt_qns_29 varchar(50),
 txt_fld_29 varchar(500),
 txt_qns_30 varchar(50),
 txt_fld_30 varchar(500),
 txt_qns_31 varchar(50),
 txt_fld_31 varchar(500),
 txt_qns_32 varchar(50),
 txt_fld_32 varchar(500),
 txt_qns_33 varchar(50),
 txt_fld_33 varchar(500),
 txt_qns_34 varchar(50),
 txt_fld_34 varchar(500),
 txt_qns_35 varchar(50),
 txt_fld_35 varchar(500),
 txt_qns_36 varchar(50),
 txt_fld_36 varchar(500),
 txt_qns_37 varchar(50),
 txt_fld_37 varchar(500),
 txt_qns_38 varchar(50),
 txt_fld_38 varchar(500),
 txt_qns_39 varchar(50),
 txt_fld_39 varchar(500),
 txt_qns_40 varchar(50),
 txt_fld_40 varchar(500),
 txt_qns_41 varchar(50),
 txt_fld_41 varchar(500),
 txt_qns_42 varchar(50),
 txt_fld_42 varchar(500),
 txt_qns_43 varchar(50),
 txt_fld_43 varchar(500),
 txt_qns_44 varchar(50),
 txt_fld_44 varchar(500),
 txt_qns_45 varchar(50),
 txt_fld_45 varchar(500),
 txt_qns_46 varchar(50),
 txt_fld_46 varchar(500),
 txt_qns_47 varchar(50),
 txt_fld_47 varchar(500),
 txt_qns_48 varchar(50),
 txt_fld_48 varchar(500),
 txt_qns_49 varchar(50),
 txt_fld_49 varchar(500),
 txt_qns_50 varchar(50),
 txt_fld_50 varchar(500),
 blob_fld_1 longblob,
 blob_fld_2 longblob,
 blob_fld_3 longblob,
 blob_fld_4 longblob,
 blob_fld_5 longblob,
 blob_fld_6 longblob,
 blob_fld_7 longblob,
 blob_fld_8 longblob,
 blob_fld_9 longblob,
 blob_fld_10 longblob,
 `gis_fld_1` POINT,
 `gis_fld_2` POINT,
 `gis_fld_3` POINT,
 `gis_fld_4` POINT,
 `gis_fld_5` POINT,
 record_status char(1) NULL,
 `created_by` VARCHAR(25) NULL,
  `created_ts` DATETIME NULL,
  `auth_by` VARCHAR(25) NULL,
  `auth_ts` DATETIME NULL,
  `auth_stat` CHAR(1) NULL,
 primary key(`survey_id`)
 )ENGINE=InnoDB;