drop procedure findAllLocationsForUser;
delimiter $$
CREATE PROCEDURE findAllLocationsForUser(userid VARCHAR(25),networkid VARCHAR(25))
BEGIN
  -- Temporary storage
  DROP TABLE IF EXISTS _result;
  CREATE TEMPORARY TABLE _result (nwt_id VARCHAR(25) PRIMARY KEY);

  -- Seeding
  INSERT INTO _result  (SELECT default_network_id 
      FROM system_user  where user_id = userid);

  -- Iteration
  DROP TABLE IF EXISTS _tmp;
  CREATE TEMPORARY TABLE _tmp LIKE _result;
  REPEAT
    TRUNCATE TABLE _tmp;
    INSERT INTO _tmp SELECT parent_id AS nwt_id
      FROM _result JOIN Networks  ON nwt_id = network_id;

    INSERT IGNORE INTO _result SELECT nwt_id FROM _tmp;
   
UNTIL ROW_COUNT() = 0
  END REPEAT;


  DROP TABLE _tmp;
  
   select a.* from locations a,user_locations b where a.location_id=b.location_id and b.user_id=userid and b.network_id=networkid
  union
  select a.*  from locations a,network_location b where a.location_id=b.location_id and b.network_id in (SELECT * FROM _result where nwt_id != '&&')
  union
  select a.*  from locations a,network_location b,Networks c where a.location_id=b.location_id and b.network_id=c.network_id  and c.is_private="N";


END