drop procedure findAllOccupationsForUser;
DELIMITER $$
CREATE PROCEDURE `findAllOccupationsForUser`(userid VARCHAR(25),networkid VARCHAR(25))
BEGIN
  DROP TABLE IF EXISTS _result9;
  CREATE TEMPORARY TABLE _result9 (occupation_id VARCHAR(25) PRIMARY KEY);

  INSERT INTO _result9  (select occupation_id from user_occupations where user_id= userid and network_id=networkid);


  DROP TABLE IF EXISTS _tmploc;
  CREATE TEMPORARY TABLE _tmploc LIKE _result9;
  REPEAT
    TRUNCATE TABLE _tmploc;
    INSERT INTO _tmploc SELECT a.occupation_id
      FROM occupations a JOIN  _result9 b ON b.occupation_id = a.parent_id;
    INSERT IGNORE INTO _result9 SELECT * FROM _tmploc ;
  
   UNTIL ROW_COUNT() = 0
 END REPEAT;
    DROP TABLE _tmploc;
	select * from occupations where occupation_id in (select * from _result9);
END