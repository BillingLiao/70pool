/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - cloudcomputingpower
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloudcomputingpower` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cloudcomputingpower`;

/*Table structure for table `t_bank` */

DROP TABLE IF EXISTS `t_bank`;

CREATE TABLE `t_bank` (
  `bank_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户银行编号',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `bank_type` VARCHAR(50) DEFAULT NULL COMMENT '银行类型',
  `bank_name` VARCHAR(50) DEFAULT NULL COMMENT '银行名称',
  `opening_bank` VARCHAR(50) DEFAULT NULL COMMENT '开户行',
  `card_number` VARCHAR(50) DEFAULT NULL COMMENT '银行卡号',
  `true_name` VARCHAR(20) DEFAULT NULL COMMENT '姓名',
  `create_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`bank_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='银行表';


/*Data for the table `t_bank` */

/*Table structure for table `t_btc_addr` */

DROP TABLE IF EXISTS `t_btc_addr`;

CREATE TABLE `t_btc_addr` (
  `btc_addr_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '比特币地址id',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `addr` VARCHAR(100) DEFAULT NULL COMMENT '比特币地址',
  `create_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`btc_addr_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='比特币地址';


/*Data for the table `t_btc_addr` */

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '产品类别编号',
  `category_name` VARCHAR(50) NOT NULL COMMENT '产品类别名称',
  `category_code` VARCHAR(50) NOT NULL COMMENT '产品编码',
  PRIMARY KEY (`category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品类别表';

/*Data for the table `t_category` */

INSERT INTO `t_category` VALUES ('1', '矿机产品', 'minerProduct');
INSERT INTO `t_category` VALUES ('2', '云算力产品', 'cloudProduct');
INSERT INTO `t_category` VALUES ('3', '理财产品', 'financialProduct');

/*Table structure for table `t_income` */

DROP TABLE IF EXISTS `t_income`;

CREATE TABLE `t_income` (
  `income_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '收益编号',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `income_type` INT(11) NOT NULL COMMENT '收益类别',
  `theoretical_income` DECIMAL(25,10) DEFAULT NULL COMMENT '每T理论收益值(BTC)',
  `electricity_fees` DECIMAL(25,10) DEFAULT NULL COMMENT '电费(BTC)',
  `pure_income` DECIMAL(25,10) DEFAULT NULL COMMENT '理论日收益(BTC)',
  `settlement_income` DECIMAL(25,10) DEFAULT NULL COMMENT '纯收益(BTC)',
  `return_cycle` DECIMAL(15,3) DEFAULT NULL COMMENT '回本周期(天)',
  `money` DECIMAL(15,3) DEFAULT NULL COMMENT '理财日收益',
  `create_time` DATE DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`income_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品收益表';

/*Data for the table `t_income` */

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `menu_id` INT(11) NOT NULL AUTO_INCREMENT,
  `parent_id` INT(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '菜单名称',
  `url` VARCHAR(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` VARCHAR(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` INT(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` VARCHAR(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` INT(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', NULL, NULL, '0', 'fa fa-cog', '0');
INSERT INTO `t_menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', NULL, '1', 'fa fa-user', '2');
INSERT INTO `t_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', NULL, '1', 'fa fa-user-secret', '2');
INSERT INTO `t_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', NULL, '1', 'fa fa-th-list', '3');
INSERT INTO `t_menu` VALUES ('5', '2', '查看', NULL, 'sys:user:list,sys:user:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('6', '2', '新增', NULL, 'sys:user:save,sys:role:select', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('7', '2', '修改', NULL, 'sys:user:update,sys:role:select', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('8', '2', '删除', NULL, 'sys:user:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('9', '3', '查看', NULL, 'sys:role:list,sys:role:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('10', '3', '新增', NULL, 'sys:role:save,sys:menu:perms', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('11', '3', '修改', NULL, 'sys:role:update,sys:menu:perms', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('12', '3', '删除', NULL, 'sys:role:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('13', '4', '查看', NULL, 'sys:menu:list,sys:menu:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('14', '4', '新增', NULL, 'sys:menu:save,sys:menu:select', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('15', '4', '修改', NULL, 'sys:menu:update,sys:menu:select', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('16', '4', '删除', NULL, 'sys:menu:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('17', '0', '产品管理', NULL, NULL, '0', 'fa fa-shopping-basket', '0');
INSERT INTO `t_menu` VALUES ('18', '17', '矿机产品管理', 'modules/product/minerProduct.html', NULL, '1', NULL, '1');
INSERT INTO `t_menu` VALUES ('19', '17', '云算力产品管理', 'modules/product/cloudProduct.html', NULL, '1', NULL, '2');
INSERT INTO `t_menu` VALUES ('20', '17', '理财产品管理', 'modules/product/financialProduct.html', NULL, '1', NULL, '3');
INSERT INTO `t_menu` VALUES ('21', '0', '订单管理', NULL, NULL, '0', 'fa fa-bars', '3');
INSERT INTO `t_menu` VALUES ('22', '21', '订单列表', 'modules/order/orderList.html', NULL, '1', NULL, '0');
INSERT INTO `t_menu` VALUES ('23', '18', '查看', NULL, 'product:miner:list,product:miner:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('24', '18', '新增', NULL, 'product:miner:save', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('25', '18', '修改', NULL, 'product:miner:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('26', '18', '删除', NULL, 'product:miner:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('27', '19', '查看', NULL, 'product:cloud:list,product:cloud:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('28', '19', '新增', NULL, 'product:cloud:save', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('29', '19', '修改', NULL, 'product:cloud:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('30', '19', '删除', NULL, 'product:cloud:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('31', '20', '查看', NULL, 'product:financial:list,product:financial:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('32', '20', '新增', NULL, 'product:financial:save', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('33', '20', '修改', NULL, 'product:financial:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('34', '20', '删除', NULL, 'product:financial:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('35', '22', '查看', NULL, 'order:list,order:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES (36,22,'更新状态',NULL,'order:status:update',2,NULL,0);
INSERT INTO `t_menu` VALUES (37,0,'更新数据管理',NULL,NULL,0,'fa fa-pencil',4);
INSERT INTO `t_menu` VALUES (38,37,'预估收益变动','modules/updateData/tstimate.html',NULL,1,'fa fa-credit-card-alt',1);
INSERT INTO `t_menu` VALUES (39,38,'查看',NULL,'tstimate:list,tstimate:info',2,NULL,0);
INSERT INTO `t_menu` VALUES (40,38,'新增',NULL,'tstimate:save',2,NULL,0);
INSERT INTO `t_menu` VALUES (41,38,'修改',NULL,'tstimate:update',2,NULL,0);
INSERT INTO `t_menu` VALUES (42,38,'删除',NULL,'tstimate:delete',2,NULL,0);
INSERT INTO `t_menu` VALUES (43,37,'比特币价格变动','modules/updateData/currencyPrice.html',NULL,1,'fa fa-credit-card-alt',1);
INSERT INTO `t_menu` VALUES (44,43,'查看',NULL,'currencyPrice:list,currencyPrice:info',2,NULL,0);
INSERT INTO `t_menu` VALUES (45,43,'新增',NULL,'currencyPrice:save',2,NULL,0);
INSERT INTO `t_menu` VALUES (46,43,'修改',NULL,'currencyPrice:update',2,NULL,0);
INSERT INTO `t_menu` VALUES (47,43,'删除',NULL,'currencyPrice:delete',2,NULL,0);
INSERT INTO `t_menu` VALUES ('48', '0', '提现管理', NULL, NULL, '0', 'fa fa-bars', '3');
INSERT INTO `t_menu` VALUES ('49', '48', '提现记录', 'modules/putForward/putForwardList.html', NULL, '1', NULL, '0');
INSERT INTO `t_menu` VALUES ('50', '49', '查看', NULL, 'putForward:list,putForward:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('51', '49', '更新状态', NULL, 'putForward:status:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('52', '0', '公告管理', NULL, NULL, '0', 'fa fa-bars', '3');
INSERT INTO `t_menu` VALUES ('53', '52', '公告列表', 'modules/totices/toticesList.html', NULL, '1', NULL, '0');
INSERT INTO `t_menu` VALUES ('54', '53', '查看', NULL, 'totices:list,totices:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('55', '53', '新增', NULL, 'totices:save', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('56', '53', '修改', NULL, 'totices:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('57', '53', '删除', NULL, 'totices:delete', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('58', '0', '用户管理', NULL, NULL, '0', 'fa fa-bars', '3');
INSERT INTO `t_menu` VALUES ('59', '58', '用户列表', 'modules/user/user.html', NULL, '1', NULL, '0');
INSERT INTO `t_menu` VALUES ('60', '59', '查看', NULL, 'user:user:list,user:user:info', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('61', '59', '新增', NULL, 'user:user:save', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('62', '59', '修改', NULL, 'user:user:update', '2', NULL, '0');
INSERT INTO `t_menu` VALUES ('63', '59', '删除', NULL, 'user:user:delete', '2', NULL, '0');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_no` VARCHAR(50) NOT NULL COMMENT '订单单号',
  `product_id` INT(11) NOT NULL COMMENT '产品编号',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户编号',
  `order_type` INT(11) NOT NULL  COMMENT '订单类型 1:矿机 2:运算力产品 3:理财产品',
  `order_status` INT(11) NOT NULL  COMMENT '订单状态 0:待支付 1:待支付关闭 2:已付款，待发货 ,3:订单关闭,4:待收货 5:已完成订单',
  `amount` DECIMAL(15,2) DEFAULT NULL COMMENT '购买数量',
  `actual_receipts` DECIMAL(15,3) DEFAULT NULL COMMENT '实收款',
  `maturity_income` DECIMAL(15,3) DEFAULT NULL COMMENT '到期收益',
  `memo` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `create_time` DATE DEFAULT NULL COMMENT '订单提交时间',
  `payment_time` DATE DEFAULT NULL COMMENT '支付时间',
  `delivery_time` DATETIME DEFAULT NULL COMMENT '发货时间',
  `receiving_time` DATETIME DEFAULT NULL COMMENT '收货时间',
  `completion_time` DATE DEFAULT NULL COMMENT '完成时间',
  `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
  PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

/*Data for the table `t_order` */

INSERT INTO `t_order` VALUES ('1', 'LC20103', '1', '2','3','2','1','5000','21', '理财产品', '2018-06-27', '2018-09-02', NULL, NULL,NULL, '0');

DROP TABLE IF EXISTS `t_order_record`;

CREATE TABLE `t_order_record` (
  `order_record_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` INT(11) NOT NULL COMMENT '订单id',
  `order_status` INT(11) NOT NULL  COMMENT '订单状态 0:处理中 1:待支付关闭 2:付款成功  3:交易失败 4:已发货 5:已完成订单',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_record_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

/*Data for the table `t_put_forward` */

INSERT  INTO `t_order_record` VALUES (1,'1','0','2018-06-27 15:23:13');
INSERT  INTO `t_order_record` VALUES (2,'1','2','2018-09-02 15:23:13');



/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `category_id` INT(11) NOT NULL COMMENT '产品类别编号',
  `product_name` VARCHAR(100) NOT NULL COMMENT '产品名称',
  `pic_img` VARCHAR(255) DEFAULT NULL COMMENT '展示图片',
  `introduction` TEXT DEFAULT NULL COMMENT '产品介绍',
  `show_in_shelve` TINYINT(1) DEFAULT '1' COMMENT '是否上架：1=上架/0=下架',
  `create_user` INT(11) DEFAULT NULL COMMENT '创建者',
  `create_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新者',
  `update_at` DATETIME DEFAULT NULL COMMENT '更新时间',
  `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
  PRIMARY KEY (`product_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

/*Data for the table `t_product` */

INSERT INTO `t_product` VALUES ('1', '3', '理财产品1号', NULL,'这是理财,dfkjkj1dfdfad1', '1', NULL,'2018-06-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('2', '3', '理财产品2号', NULL,'这当时的理财啊,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('3', '1', '矿机产品1号',NULL, '1. 扫描玛雅矿机官方微信号二维码可获取最新资讯消息。2. 本批矿机价格已包含电源价格。3. 款项支付时间：确认订单60分钟内支付。4. 预期发货时间：2018年6月，物流运费到付。5. 已付货款项将不予退还，矿机无法出货情况除外。6. 本批次矿机最小订单量为1台，仅支持发货不支持托管。7. 玛雅保留此次预售的最终解释权。', '1', NULL,'2018-06-12 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('4', '1', '矿机产品2号', NULL,'这款打造的,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('5', '2', '云算力产品1号',NULL,'这优惠,dfkjkj1dfdfad1', '1', NULL,'2018-06-11 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('6', '2', '云算力产品2号',NULL, '这价格,dfkjkj1dfdfad1', '1', NULL,'2018-08-11 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('7', '3', '理财产品3号', NULL,'这是理财,dfkjkj1dfdfad1', '1', NULL,'2018-06-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('8', '3', '理财产品4号', NULL,'这当时的理财啊,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('9', '3', '理财产品5号', NULL,'这是理财,dfkjkj1dfdfad1', '1', NULL,'2018-06-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('10', '3', '理财产品6号', NULL,'这当时的理财啊,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');

/*Table structure for table `t_product_cloud` */

DROP TABLE IF EXISTS t_cloud_product;

CREATE TABLE t_cloud_product
(
   cloud_id             INT(11) NOT NULL AUTO_INCREMENT COMMENT '云算力明细id',
   product_id           INT(11) NOT NULL COMMENT '产品id',
   sell_out          	DECIMAL(15,2) DEFAULT '0.00' COMMENT '售出算力',
   stock                DECIMAL(15,2) NOT NULL COMMENT '剩余算力',
   electricity_fees     DECIMAL(15,2) DEFAULT NULL COMMENT '电费',
   retail_price		DECIMAL(15,3) DEFAULT NULL COMMENT '售价',
   price                DECIMAL(15,3) NOT NULL COMMENT '单价',
   sale_time            VARCHAR(50) DEFAULT NULL COMMENT '本期售卖时间',
   model                VARCHAR(50) NOT NULL COMMENT '机型',
   rated                DECIMAL(15,2) NOT NULL COMMENT '额定算力/T',
   power_waste		DECIMAL(15,3) NOT NULL COMMENT '功耗',
   remark               VARCHAR(200) DEFAULT NULL COMMENT '参考收益',
   start_step           INT(11) DEFAULT '0' COMMENT '起投单位',
   `power`              VARCHAR(50) COMMENT '电源',
   management_expense   DECIMAL(15,3) COMMENT '管理费',
   contract_cycle       VARCHAR(100) DEFAULT NULL COMMENT '合同周期',
   `sort` 		INT(11) DEFAULT '0' COMMENT '排序',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (cloud_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='云算力明细表';

/*Data for the table `t_cloud_product` */

INSERT INTO `t_cloud_product` VALUES ('1', '5', '1000', '300', '0.4', '570','400', '9月20号-10月10号', 'A9', '14','1.34','100','1','test','500','一年签','1','0');
INSERT INTO `t_cloud_product` VALUES ('2', '6', '1200', '500', '0.35', '630','570', '9月10号-10月1号', '蚂蚁M9', '13.5','1.35','200','1','test','600', '永久','2','0');

/*Table structure for table `t_financial_product` */

DROP TABLE IF EXISTS t_financial_product;

CREATE TABLE t_financial_product
(
   financial_id         INT(11) NOT NULL AUTO_INCREMENT COMMENT '理财明细id',
   product_id       	INT(11) NOT NULL COMMENT '产品id',
   threshold_amount     DECIMAL(15,3) DEFAULT NULL COMMENT '起投金额',
   step_term            DECIMAL(15,3) DEFAULT NULL COMMENT '投资步长',
   lock_amount          INT DEFAULT NULL COMMENT '锁定期',
   reward_rate          DECIMAL(15,3) NOT NULL COMMENT '年化收益率',
   cycle          	DECIMAL(15,3) NOT NULL COMMENT '投资周期',
   `sort` 		INT(11) DEFAULT '0' COMMENT '排序',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (financial_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='理财明细表';

/*Data for the table `t_financial_product */

INSERT INTO `t_financial_product` VALUES ('1', '1', '10000.0', '1000', '0', '5','30','1','0');
INSERT INTO `t_financial_product` VALUES ('2', '2', '20000.0', '2000', '0', '7', '90','2','0');
INSERT INTO `t_financial_product` VALUES ('3', '7', '10000.0', '1000', '0', '5','30', '3','0');
INSERT INTO `t_financial_product` VALUES ('4', '8', '20000.0', '2000', '0', '6', '30','4','0');
INSERT INTO `t_financial_product` VALUES ('5', '9', '10000.0', '1000', '0', '7','30', '5','0');
INSERT INTO `t_financial_product` VALUES ('6', '10', '20000.0', '2000', '0', '8', '30','6','0');


/*Table structure for table `t_miner_product` */

DROP TABLE IF EXISTS t_miner_product;

CREATE TABLE t_miner_product
(
   miner_id         	INT(11) NOT NULL AUTO_INCREMENT COMMENT '矿机明细id',
   product_id     	INT(11) NOT NULL COMMENT '产品id',
   price                DECIMAL(15,3) NOT NULL COMMENT '单价',
   stock 		DECIMAL(13,2) NOT NULL COMMENT '额定算力',
   model                VARCHAR(50) NOT NULL COMMENT '机型',
   remark               VARCHAR(200) DEFAULT NULL COMMENT '参考收益',
   `power`              VARCHAR(50) DEFAULT NULL COMMENT '电源',
   electricity_fees     DECIMAL(15,3) DEFAULT NULL COMMENT '电费',
   management_expense   DECIMAL(15,3) DEFAULT NULL COMMENT '管理费',
   `sort` 		INT(11) DEFAULT '0' COMMENT '排序',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (miner_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='矿机明细表';

/*Data for the table `t_miner_product */
INSERT INTO `t_miner_product` VALUES ('1', '3', '12000.0', '13.5', '蚂蚁M9', '这样哪有', NULL, NULL, NULL, '1','0');
INSERT INTO `t_miner_product` VALUES ('2', '4', '23000.0', '14', 'A9', '很火', NULL, NULL, NULL,'2', '0');

/*Table structure for table `t_proxy` */

DROP TABLE IF EXISTS `t_proxy`;

CREATE TABLE `t_proxy` (
  `proxy_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '代理编号',
  `user_id` INT(11) NOT NULL COMMENT '代理人编号',
  `son_id` INT(11) DEFAULT NULL COMMENT '子代理人编号',
  `level` INT(11) DEFAULT '0' COMMENT '代理级别',
  PRIMARY KEY (`proxy_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='代理表';

/*Data for the table `t_proxy` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `remark` VARCHAR(100) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `t_role` */

INSERT  INTO `t_role`(`role_id`,`role_name`,`remark`,`create_time`) VALUES (1,'用户',NULL,'2018-06-07 16:29:40'),(2,'数据权限测试','22','2018-07-10 17:49:54'),(3,'数据权限测试','22','2018-07-10 17:50:13'),(4,'admin','admin','2018-07-11 21:55:58');

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` INT(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `t_role_menu` */

INSERT  INTO `t_role_menu`(`id`,`role_id`,`menu_id`) VALUES (1,4,1),(2,4,2),(3,4,3),(4,4,4);

/*Table structure for table `t_totices` */

DROP TABLE IF EXISTS `t_totices`;

CREATE TABLE `t_totices` (
  `totices_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `totices_title` VARCHAR(40) NOT NULL COMMENT '标题',
  `content` TEXT NOT NULL COMMENT '内容',
  `publish_date` DATETIME DEFAULT NULL COMMENT '发布日期',
  `update_date` DATETIME DEFAULT NULL COMMENT '修改日期',
  `publish_user` INT(11) DEFAULT NULL COMMENT '发布人',
  `update_user` INT(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`totices_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='滚动公告表';


/*Data for the table `t_totices` */

INSERT  INTO `t_totices` VALUES (1,'BAT区块链往事：李彦宏、马云、马化腾逐鹿区块链 谁能走到终盘？​​','<h2 style="text-align: left;">BAT区块链往事：李彦宏、马云、马化腾逐鹿区块链 谁能走到终盘？<span style="font-weight: normal;"></span></h2><p><span style="font-size: x-small;">百家号08-2910:08&nbsp;&nbsp;</span><br></p><p><br></p><p>“世界总是在迫人选择，是活下去，还是被淘汰。”</p><p>出品／一号财经</p><p>文／皮爷、子雨</p><p>插画师／轩轩</p><p>▼</p><p>生存还是死亡，这个问题终究还是有了最新的注解。</p><p>出海，关停，监管，交易清理……从业者们如履薄冰，风声鹤唳。</p><p>帝都，临安，鹏城，曾是引领传统互联网变革的潮头。如今，他们三线相交，成为区块链世界中一股别样的势力。</p><p>他们是监管最有力的执行者，也是区块链技术平台的收割者，没人知道他们何时异军突起，亦如没人知晓他们何时入局。</p><p>壹</p><p>大梦谁先觉，平生我自知。草堂春落落，窗外日迟迟。</p><div><img src="http://t12.baidu.com/it/u=1054705706,1538190110&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=964&amp;s=80B46C33095B70C84EF184DA0000A0B3"></div><p>15年夏天，百度区块链技术研发团队正式宣告成立。</p><p>这一年，是百度成立的十周年，其网络营销收入达640.37亿元，占全部收入的96.92%。Web时代谁有流量，谁就是大爷，两年前的百度还是独孤求败，转到移动互联网时代，就成了无爪的凤凰，缺少像腾讯社交和阿里电商这样的应用场景，AI和区块链成为唯技术至上的百度最后的救命稻草。</p><p>无独有偶，腾讯这一年也面临着巨大的“麻烦”——微信提现收费。</p><p>手握QQ、微信两大社交流量入口，微信支付用3年的时间走完了支付宝10年才走完的路，顺利完成支付场景的狙击。</p><p>争天下易，理世道难。</p><p>面对8亿用户微信支付产生的近3亿的银行手续费用，腾讯被迫开始寻找更高效，性价比更高的金融渠道来降低成本，反哺用户。</p><p>16年5月31日，腾讯在它的大本营——深圳，参与并建立了金融区块链合作联盟，旗下微众银行成为腾讯布局区块链的发轫者，推出了基于腾讯云的联盟链云服务（Baas），同时推出基于区块链技术的银行间联合贷款清算平台。</p><p>腾讯与阿里两个一样做着流量生意的大鱼，在传统互联网领域打得不可开交，这边淘宝上不允许贴QQ号和QQ邮箱，那边微信就封了淘宝的URL。</p><p>可在布局区块链的时候，AT两大宿敌终于不再“狭路相逢”，一个深耕金融，一个致力商品生态，成为两条不相交的平行线。</p><p>16年7月，基于区块链技术的支付宝爱心捐赠平台“听障儿童重获新声”上线，10名听障儿童最终筹集到19.8万元。</p><p>至此，阿里在区块链领域的第一款应用实现落地。</p><p>贰</p><p>人布局，链做棋，几生几灭难出局。</p><p>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br></p>','2018-08-30 10:06:01',NULL,1,NULL);
INSERT  INTO `t_totices` VALUES (2,'科比：绝不会复出，想证明退役运动员可以在更多领域发展​​','<div><img src="https://c2.hoopchina.com.cn/uploads/star/event/images/180830/73015aa8a79fba929955653db615fa3330ab6784.jpg" alt="科比：绝不会复出，想证明退役运动员可以在更多领域发展"></div><div><p><span style="font-size: medium;">虎扑8月30日讯&nbsp;前湖人球员科比-布莱恩特近日接受了采访，对于自己退役后的生活以及与复出相关的流言也发表了看法。</span></p><p><span style="font-size: medium;">科比表示自己绝对不会复出，就是想向外界证明运动员退役后还可以做到更多事情。</span></p><p><span style="font-size: medium;">“我复出打球的几率为零，没有任何机会，已经结束了，就是这样。”科比说道。</span></p><p><span style="font-size: medium;">科比强调自己甚至没有过这种想法。</span></p><p><span style="font-size: medium;">“从没有，事情就是这样，对我们运动员来说转型会很困难。当我写《Dear Basketball》的时候，这真的是很私人的东西。去寻找接下来要做的事情，这才是真正的挑战。寻找那些你完全热爱做的事情，就像爱你最初的激情一样。这对我们来说是个挑战，我认为对于运动员来说不幸的地方就是，我们被认为只能做好一件事。因此当我退役的时候，所有人都在说‘他的竞争心太强了，不会知道如何处理自己的问题，未来他还是会回来打球得。’因此我会把这看作是一个挑战，就是他们认为我是只属于单一空间的家伙，我所知道的就只有运球、投篮、打比赛或者在那种水平中竞争。因此我把这看作是个人的挑战，我肯定不会再复出到比赛中了。我来到这里就是想向世人展示我们可以做到更多，建立自己的生意，赢得奥斯卡奖杯，赢得艾美奖，还有安妮奖，这些事情就是像外界展示，之后的运动员还可以做到跟多，‘不，不，比这些还要多的事情。’因此我绝不会复出了，想都不会想。”</span></p></div>','2018-08-30 10:14:26',NULL,1,NULL);
/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '用户姓名',
  `telphone` VARCHAR(20) NOT NULL COMMENT '手机号码',
  `password` VARCHAR(100) NOT NULL COMMENT '登陆密码',
  `salt` VARCHAR(20) NOT NULL COMMENT '盐',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `alipay` VARCHAR(50) DEFAULT NULL COMMENT '支付宝账号',
  `btc` DECIMAL(25,10) DEFAULT '0' COMMENT '云算力余额(比特币)',
  `cny` DECIMAL(15,3) DEFAULT '0' COMMENT '理财余额(人民币)',
  `register_time` DATETIME DEFAULT NULL COMMENT '注册时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `invitation_code` VARCHAR(100) DEFAULT NULL COMMENT '邀请码',
  `status` TINYINT(4) DEFAULT '1' COMMENT '状态  0：冻结   1：正常',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `t_user` */

INSERT  INTO `t_user` VALUES (1,'李定韬','17620895280','f651b74e526731fd6ea45c995783343f', 'l9rewDxy6Nw4rYdytb2p','root@cnadmart.com','136000001','0','0','2016-11-11 11:11:11','2017-03-24 15:23:13','1342f',1);
INSERT  INTO `t_user` VALUES (2,'Billing','15012920449','f651b74e526731fd6ea45c995783343f', 'l9rewDxy6Nw4rYdytb2p','root@cnadmart.com','136000001','0','0','2016-11-11 11:11:11','2017-03-24 15:23:13','134df',1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `salt` VARCHAR(20) NOT NULL COMMENT '盐',
  `mobile` VARCHAR(100) DEFAULT NULL COMMENT '手机号',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `status` TINYINT(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `t_user` */

INSERT  INTO `t_sys_user` VALUES (1,'admin','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e','15012015410','root@cnadmart.com','1','2016-11-11 11:11:11');
INSERT  INTO `t_sys_user` VALUES (2,'Billing','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e','15012920449','root@cnadmart.com','1','2016-11-11 17:49:54');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) DEFAULT NULL COMMENT '角色id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

/*Data for the table `t_user_role` */

INSERT  INTO `t_user_role`(`id`,`role_id`,`user_id`) VALUES (1,4,2);


/*Table structure for table `t_tstimate` */

DROP TABLE IF EXISTS `t_tstimate`;

CREATE TABLE `t_tstimate` (
  `tstimate_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `tstimate` DECIMAL(25,10) NOT NULL COMMENT '预估收益',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tstimate_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='预估收益表';

/*Data for the table `t_tstimate` */

INSERT  INTO `t_tstimate` VALUES (1,'0.00003936','2017-03-24 15:23:13');


/*Table structure for table `t_currency_price` */

DROP TABLE IF EXISTS `t_currency_price`;

CREATE TABLE `t_currency_price` (
  `price_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `btc_cny` DECIMAL(25,10) DEFAULT NULL COMMENT '比特币CNY价格',
  `btc_usd` DECIMAL(25,10) DEFAULT NULL COMMENT '比特币USD价格',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`price_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='比特币价格';

/*Data for the table `t_currency_price` */

INSERT  INTO `t_currency_price` VALUES (1,'44223',NULL,'2017-03-24 15:23:13');


/*Table structure for table `t_put_forward` */

DROP TABLE IF EXISTS `t_put_forward`;

CREATE TABLE `t_put_forward` (
  `put_forward_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `forward_no` VARCHAR(50) NOT NULL COMMENT '提现单号',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `bank_id` INT(11) DEFAULT NULL COMMENT '用户银行编号',
  `btc_addr_id` INT(11) DEFAULT NULL COMMENT '比特币地址id',
  `forward_type` INT(11) NOT NULL COMMENT '提现类型 0:比特币钱包地址 1:银行卡',
  `forward_status` INT(11) NOT NULL COMMENT '提现状态 0:已提交 1:提现关闭 2:提现失败  3:提现成功',
  `btc` DECIMAL(25,10) DEFAULT NULL COMMENT '云算力提现(比特币)',
  `btc_true` DECIMAL(25,10) DEFAULT NULL COMMENT '扣除矿工费(比特币)',
  `cny` DECIMAL(15,3) DEFAULT NULL COMMENT '理财提现(人民币)',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `completion_time` DATETIME DEFAULT NULL COMMENT '提现时间',
  PRIMARY KEY (`put_forward_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='提现表';

/*Data for the table `t_put_forward` */

INSERT  INTO `t_put_forward` VALUES (1,'ysl5454544','2','1',NULL,'1','3','0','0','100','2017-03-24 15:23:13','2018-03-24 15:23:13');
INSERT  INTO `t_put_forward` VALUES (2,'ysl5454547','2','1',NULL,'1','0','0','0','500','2017-03-24 15:23:13',NULL);

DROP TABLE IF EXISTS `t_present_record`;

CREATE TABLE `t_present_record` (
  `present_record_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `put_forward_id` INT(11) NOT NULL COMMENT '提现id',
  `present_status` INT(11) DEFAULT NULL COMMENT '提现状态 0:处理中 1:提现关闭 2:提现失败  3:提现成功',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`present_record_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='提现记录表';

/*Data for the table `t_put_forward` */

INSERT  INTO `t_present_record` VALUES (1,'1','0','2017-03-24 15:23:13');
INSERT  INTO `t_present_record` VALUES (2,'1','3','2018-03-24 15:23:13');
INSERT  INTO `t_present_record` VALUES (3,'2','0','2017-03-24 15:23:13');


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
