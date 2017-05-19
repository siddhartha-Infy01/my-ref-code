select profile_id as "Profile",name_flag as "Name",birth_year_flag as "birth_year",gender_flag as "gender",mobile_number_flag as "mobile",address_flag as "address",company_flag as "company"
,language_flag as "language",email_flag as "email",currency_flag as "currency"
 FROM esoko.profile_Sharing_types where network_id = ?;