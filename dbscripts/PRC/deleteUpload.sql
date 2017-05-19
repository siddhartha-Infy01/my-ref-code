-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `deleteUpload`(uploadId VARCHAR(50),application Varchar(50))
BEGIN
  if application='newsAndLibrary' then
  
  delete from upload_master where upload_id=uploadId;
  delete from library where lib_id=uploadId;
  delete from library_commodity where library_id=uploadId;
  delete from library_files where library_id=uploadId;
  delete from library_images where library_id=uploadId;
  delete from library_locations where library_id=uploadId;

  
  
  elseif application='Offers' then 
  
  delete from upload_master where upload_id=uploadId;
  delete from bids_offers_master where upload_ID=uploadId;
  
  elseif  application='Prices' then
  
  delete from upload_master where upload_id=uploadId;
  delete from price_upload_master where upload_ID=uploadId;

 else
 delete from people where people_id=uploadId and default_network_id=application;
  
  end if;
END