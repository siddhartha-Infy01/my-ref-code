select * from System_transactions where transaction_date between  ? and ? and trans_code=? and account_no=(select account_no from System_accounts where owner_id=?)  ORDER BY transaction_date DESC;
