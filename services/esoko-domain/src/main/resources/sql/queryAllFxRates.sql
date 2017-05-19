select * from  (select * from (select  base_currency_id,(select name from currencies where code=a.base_currency_id) as currency_name
,rate,date from forex a where date(date) <=? and quote_currency_id='USD'  
union 
select base_currency_id,(select name from currencies where code=a.base_currency_id) as currency_name
,rate,date from forex_history a where date(date) <=? and quote_currency_id='USD'   ) as t1
order  by date desc)  as t2 group by base_currency_id;