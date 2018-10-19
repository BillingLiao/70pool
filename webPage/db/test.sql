/*
	插入所有云算力产品收益
*/
INSERT INTO t_income(user_id,income_type,electricity_fees,theoretical_income,pure_income,settlement_income,return_cycle,create_time)
SELECT o.user_id AS user_id,o.order_type AS income_type,
	c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1) AS electricity_fees,
	(SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1) AS theoretical_income,
	(SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)*o.amount AS pure_income,
	 CASE WHEN ((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*o.amount <= 0 THEN 0
	 ELSE ((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*o.amount END AS settlement_income,
	CASE WHEN o.actual_receipts/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)/(((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*o.amount) <= 0 THEN 0
	ELSE o.actual_receipts/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)/(((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*o.amount) END AS return_cycle,
	CURRENT_DATE AS create_time
FROM t_order o
LEFT JOIN t_cloud_product c ON c.product_id = o.product_id
WHERE o.order_type = 2 AND o.order_status = 2 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)>=2;

/*
	插入所有理财产品收益
*/
INSERT INTO t_income(user_id,income_type,money,create_time)
SELECT o.user_id AS user_id,o.order_type AS income_type,
	o.actual_receipts*f.reward_rate/360/100 AS money,
	CURRENT_DATE AS create_time
FROM t_order o
LEFT JOIN t_financial_product f ON f.product_id = o.product_id
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)>=2 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)<f.cycle+2;

/*
	查询理财产品今日到期所有订单
*/
SELECT o.*
FROM
t_order o
LEFT JOIN t_financial_product f ON f.product_id = o.product_id
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time) = f.cycle+1;

/*
	理财产品到期后更改状态为已完成
*/
UPDATE t_order o LEFT JOIN t_financial_product f ON f.product_id = o.product_id
SET o.order_status = 5,o.`completion_time` = CURRENT_DATE
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time) = f.cycle+1;

/*
	理财产品到期更改状态后插入订单记录表
*/
INSERT INTO t_order_record(order_id,order_status,create_time)
SELECT o.order_id,5, NOW() FROM
t_order o LEFT JOIN t_financial_product f ON f.product_id = o.product_id
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time) = f.cycle+1;

/*
	云算力产品昨日收益余额
*/
SELECT *
FROM
t_income
 WHERE income_type = 2 AND TO_DAYS(CURRENT_DATE) = TO_DAYS(create_time)
 
 
 /*
	统计用户云算力产品昨日收益总额
 */
SELECT SUM(settlement_income)
FROM
t_income
WHERE income_type = 2 AND TO_DAYS(CURRENT_DATE) = TO_DAYS(create_time) AND user_id = 2

 /*
	统计用户云算力产品收益总额
 */
SELECT SUM(settlement_income)
FROM
t_income
WHERE income_type = 2  AND user_id = 2

 /*
	统计理财产品昨日收益总额
 */
SELECT SUM(money)
FROM
t_income
WHERE income_type = 3 AND TO_DAYS(CURRENT_DATE) = TO_DAYS(create_time) AND user_id = 2

/*
	统计用户持有算力（订单状态为已付款的）
*/
SELECT SUM(amount) FROM t_order WHERE order_type = 2 AND order_status = 2 AND user_id = 2

/*
	查询用户订单记录
*/
SELECT odr.*,o.order_no,o.order_type,o.amount,o.actual_receipts,p.product_name
FROM t_order_record odr
LEFT JOIN t_order o ON o.order_id = odr.order_id
LEFT JOIN t_product p ON p.product_id = o.product_id
WHERE o.user_id = 2 AND ((odr.order_status = 0  AND (TO_DAYS(CURRENT_DATE) - TO_DAYS(odr.create_time))<3) OR odr.order_status != 0)
ORDER BY odr.create_time DESC
/*
	查询提现订单记录
*/
SELECT pr.*,pf.forward_no,pf.forward_type,pf.btc,pf.btc_true,pf.cny
FROM t_present_record pr
LEFT JOIN t_put_forward pf ON pf.put_forward_id = pr.put_forward_id
WHERE pf.user_id = 2
ORDER BY pr.create_time DESC
/*
	查询出公告，加管理员姓名
*/
SELECT t.*,s.user_name FROM t_totices t
LEFT JOIN t_sys_user s ON s.user_id = t.publish_user

/*
	查询用户 理财产品名称,赎回日期，持有金额跟累计收益
*/
SELECT p.product_name,(fp.cycle+1+TO_DAYS(o.payment_time)-TO_DAYS(CURRENT_DATE)) AS redemption_day,o.actual_receipts AS money,(maturity_income/cycle*(TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time))) AS cumulative_income FROM t_order o
LEFT JOIN t_product p ON p.product_id = o.product_id
LEFT JOIN t_financial_product fp ON fp.product_id = p.product_id
WHERE o.order_type = 3 AND o.order_status = 2 AND o.user_id = 2

/*
	查询出冻结理财收益
*/
SELECT SUM(o.actual_receipts+o.maturity_income) AS frozen_income
FROM
t_order o
LEFT JOIN t_financial_product f ON f.product_id = o.product_id
WHERE o.order_type = 3 AND o.order_status = 2 AND o.user_id = 2

/*
	查询用户云算力所有收益记录（同一天相加）
*/
SELECT income_id,user_id,SUM(settlement_income) AS settlement_income,DATE_SUB(create_time,INTERVAL 1 DAY) AS create_time
FROM t_income 
WHERE income_type = 2 AND user_id = 2
GROUP BY create_time
ORDER BY create_time

/*
	查询用户理财所有收益记录
*/
SELECT * FROM t_income 
WHERE income_type = 3 AND user_id = 2

/*
	查找用户btc提现中金额
*/
SELECT SUM(btc_true) FROM t_put_forward
WHERE forward_type = 0 AND forward_status = 0 AND user_id = 2;

/*
	查找用户cny提现中金额
*/
SELECT SUM(cny) FROM t_put_forward
WHERE forward_type = 1 AND forward_status = 0 AND user_id = 2;

/*
	查找cloudproduct产品信息
*/
SELECT
cloud.*,product.*,c.category_name
FROM
t_cloud_product cloud
LEFT JOIN t_product product ON cloud.product_id = product.product_id
LEFT JOIN t_category c ON c.category_id = product.category_id
WHERE loud.product_id = 2 AND cloud.del_flag = 0


LEFT JOIN t_cloud_product c ON c.product_id = o.product_id
WHERE o.order_status = 2;


SELECT c.electricity_fees*c.power_waste*24/c.rated/cp.btc_cny AS e
FROM t_cloud_product c,t_currency_price cp
ORDER BY cp.price_id DESC LIMIT 0,1;

SELECT btc_cny, FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1;
SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1;

SELECT TO_DAYS('2018-09-04')-TO_DAYS(CURRENT_DATE)>=1
SELECT TO_DAYS(CURRENT_DATE)-TO_DAYS(SELECT DATE_FORMAT(create_time,'%y-%m-%d') FROM t_order WHERE order_id =2) >=2
SELECT TO_DAYS(SELECT DATE_FORMAT(create_time,'%y-%m-%d') FROM t_order WHERE order_id =2)

WHERE datediff（day，getdate（），生产日期）<30

SELECT * form t_order WHERE TO_DAYS('2018-09-04')-TO_DAYS(CURRENT_DATE)>=1

SELECT * FROM t_income
WHERE income_type = 3 AND TO_DAYS(create_time) = TO_DAYS(CURRENT_DATE)

SELECT u.*, i.* FROM t_income i  LEFT JOIN t_user u ON u.user_id = i.user_id WHERE i.income_type = 2 AND TO_DAYS(i.create_time) = TO_DAYS(CURRENT_DATE)
SELECT DATE_ADD(CURDATE(), INTERVAL -1 DAY)

UPDATE t_user u SET u.cny = u.cny + (SELECT SUM(settlement_income) FROM t_income i WHERE u.user_id =  i.user_id AND i.income_type = 3 AND TO_DAYS(CURRENT_DATE) - TO_DAYS(i.create_time) = 1);

SELECT u.user_id,u.cny, i.`settlement_income` FROM t_user u,t_income i WHERE u.user_id =  i.user_id AND i.income_type = 3 AND TO_DAYS(CURRENT_DATE) - TO_DAYS(i.create_time) = 1




