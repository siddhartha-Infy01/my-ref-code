SELECT * FROM esoko.survey where network_id=? and user_id=? and survey_name=? and phase=? and created_ts=(SELECT MAX(created_ts) FROM esoko.survey
   WHERE network_id=? and user_id=? and survey_name=?  and phase=?);