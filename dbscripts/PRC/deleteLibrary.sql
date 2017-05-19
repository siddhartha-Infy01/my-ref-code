-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`esoko`@`%` PROCEDURE `deleteLibrary`(libId VARCHAR(50))
BEGIN
 
  
  delete from upload_master where upload_id=libId;
  delete from library where lib_id=libId;
  delete from library_commodity where library_id=libId;
  delete from library_files where library_id=libId;
  delete from library_images where library_id=libId;
  delete from library_locations where library_id=libId;

  
  
  
END