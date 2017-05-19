select (SELECT count(*) FROM keywords where keyword=? and channel=?)
+
(select count(*) from keyword_alias where alias=? and channel=?) as sum;