select concat(cast(current_date as unsigned),lpad(floor(10000*rand()),5,'0')) from dual;