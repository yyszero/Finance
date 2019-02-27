/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50639
Source Host           : localhost:8179
Source Database       : finance

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-05-25 14:50:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `uid` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `salt` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `is_system` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique_username` (`username`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('781610bdf8ed40af940a111a173fc95e', 'ruzhang', '0cca2322d53e3eacc737455a1321f502', '1', 'd7889460379f19b3803b2f100a4f39ae', '0', '2018-05-20 22:10:28', '2018-05-20 22:10:28', '0');
INSERT INTO `admin` VALUES ('aa260f1a8ff04a22b72a25f041810261', 'test', 'b78da634519303dabfd3d3f0bb065d93', '1', '462c58512befe2af3a5564af3a1fcd53', '0', '2018-05-20 17:21:44', '2018-05-20 17:21:44', '0');
INSERT INTO `admin` VALUES ('ad313d38fe9447ce863fe8584743a010', 'admin', 'c5941c5f3bc693a75e6e863bd2c55ce3', '1', '1ab6d62faa91ae7deec76d6f13ef1600', '1', '2018-05-06 11:16:51', '2018-05-11 13:59:25', null);
INSERT INTO `admin` VALUES ('d76d427377494951a905b3d162086647', 'fuhe', '1225a68f4756a84f398701957090f238', '1', 'a5aa76885e96d47c63e9f4b6d39c41d5', '0', '2018-05-20 21:59:39', '2018-05-20 21:59:39', '0');
INSERT INTO `admin` VALUES ('e312241ff4324cc9a319d25b409b63a5', 'pingzheng', '4f2c2b10cf649224885a8a190a0ae1e6', '1', 'fa35234acded868915c0aeccc4c3b863', '0', '2018-05-20 22:10:15', '2018-05-20 22:10:15', '0');

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `admin_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`),
  KEY `admin_role_foreign` (`role_id`) USING BTREE,
  CONSTRAINT `fk_ref_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`uid`),
  CONSTRAINT `fk_ref_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('781610bdf8ed40af940a111a173fc95e', '2a19e3df7a5641d09db7bafeda7a3871');
INSERT INTO `admin_role` VALUES ('e312241ff4324cc9a319d25b409b63a5', '3006a6d5cafe4a9c806445d389f1933f');
INSERT INTO `admin_role` VALUES ('d76d427377494951a905b3d162086647', '908ccf7a07e44f328093b78bef6a4faf');
INSERT INTO `admin_role` VALUES ('aa260f1a8ff04a22b72a25f041810261', 'cbe8356d64a8433cb5dad5c7fccf8dce');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookkeeping_time` date DEFAULT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `income` double DEFAULT NULL,
  `pay` double DEFAULT NULL,
  `over` double DEFAULT NULL,
  `certificate` varchar(100) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '2018-05-20', '工资', '10000', '200', '100000', null, '22222');
INSERT INTO `book` VALUES ('2', '2018-05-20', '工资', '233', '333', '444', null, '');

-- ----------------------------
-- Table structure for `fin_account_certificate`
-- ----------------------------
DROP TABLE IF EXISTS `fin_account_certificate`;
CREATE TABLE `fin_account_certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(100) DEFAULT NULL,
  `account_code` varchar(100) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(100) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(100) DEFAULT NULL,
  `overage` double DEFAULT NULL,
  `over_real` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='科目凭证表';

-- ----------------------------
-- Records of fin_account_certificate
-- ----------------------------
INSERT INTO `fin_account_certificate` VALUES ('2', '库存现金', '1001', null, '2018-05-25 14:29:00', null, '2018-05-25 14:29:00', '流动资产', '10000', '14000');
INSERT INTO `fin_account_certificate` VALUES ('3', '银行存款', '1002', null, '2018-05-25 14:21:35', null, '2018-05-25 14:21:35', '流动资产', '10000', '24000');
INSERT INTO `fin_account_certificate` VALUES ('4', '存放中央银行款项', '1003', null, '2018-05-25 14:37:56', null, '2018-05-25 14:37:56', '流动资产', '12000', '7000');
INSERT INTO `fin_account_certificate` VALUES ('5', '存放同业', '1011', null, '2018-05-25 14:21:45', null, '2018-05-25 14:21:45', '流动资产', '30000', '50000');
INSERT INTO `fin_account_certificate` VALUES ('6', '结算备付金', '1021', null, '2018-05-25 13:56:02', null, '2018-05-25 13:56:02', '流动资产', '20000', '20000');

-- ----------------------------
-- Table structure for `fin_certificate_base`
-- ----------------------------
DROP TABLE IF EXISTS `fin_certificate_base`;
CREATE TABLE `fin_certificate_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookkeeping_time` date DEFAULT NULL COMMENT '记账日期',
  `cashier` varchar(200) DEFAULT NULL COMMENT '出纳',
  `review` varchar(50) DEFAULT NULL COMMENT '审核',
  `orders` varchar(50) DEFAULT NULL COMMENT '制单',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(50) DEFAULT NULL COMMENT '凭证类型',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='凭证主表';

-- ----------------------------
-- Records of fin_certificate_base
-- ----------------------------
INSERT INTO `fin_certificate_base` VALUES ('13', '2018-05-25', null, 'admin', 'admin', '2018-05-25 14:00:28', 'admin', null, '2018-05-25 14:00:28', 's', '1', '1');
INSERT INTO `fin_certificate_base` VALUES ('14', '2018-05-25', null, 'admin', 'admin', '2018-05-25 14:21:24', 'admin', null, '2018-05-25 14:21:24', 'f', '1', '1');
INSERT INTO `fin_certificate_base` VALUES ('15', '2018-05-25', null, 'admin', 'admin', '2018-05-25 14:29:00', 'admin', null, '2018-05-25 14:29:00', 's', '2', '1');
INSERT INTO `fin_certificate_base` VALUES ('16', '2018-05-25', null, 'fuhe', 'pingzheng', '2018-05-25 14:37:56', 'pingzheng', null, '2018-05-25 14:37:56', 'z', '1', '1');

-- ----------------------------
-- Table structure for `fin_certificate_item`
-- ----------------------------
DROP TABLE IF EXISTS `fin_certificate_item`;
CREATE TABLE `fin_certificate_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_id` int(11) NOT NULL COMMENT '凭证主表id',
  `summary` varchar(200) NOT NULL COMMENT '摘要',
  `debit_account` varchar(100) DEFAULT NULL COMMENT '借方科目',
  `debit_account_code` varchar(100) DEFAULT NULL COMMENT '科目代码',
  `loan_account` varchar(100) DEFAULT NULL COMMENT '贷方科目',
  `loan_account_code` varchar(100) DEFAULT NULL COMMENT '贷方科目代码',
  `debit_amount` double unsigned DEFAULT '0' COMMENT '借方金额',
  `loan_amount` double unsigned DEFAULT '0' COMMENT '贷方金额',
  `over` double DEFAULT NULL COMMENT '剩余',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fin_certificate_item
-- ----------------------------
INSERT INTO `fin_certificate_item` VALUES ('23', '13', '1111', '库存现金', '1001', null, null, '2000', '0', '12000');
INSERT INTO `fin_certificate_item` VALUES ('24', '13', '222', '库存现金', '1001', null, null, '0', '3000', '9000');
INSERT INTO `fin_certificate_item` VALUES ('25', '13', '3333', '银行存款', '1002', null, null, '4000', '0', '14000');
INSERT INTO `fin_certificate_item` VALUES ('26', '14', '测试', '库存现金', '1001', null, null, '2000', '0', '13000');
INSERT INTO `fin_certificate_item` VALUES ('27', '14', '存款', '银行存款', '1002', null, null, '5000', '0', '24000');
INSERT INTO `fin_certificate_item` VALUES ('28', '14', '3333', '存放同业', '1011', null, null, '10000', '0', '50000');
INSERT INTO `fin_certificate_item` VALUES ('29', '15', '测试', '库存现金', '1001', null, null, '1000', '0', '14000');
INSERT INTO `fin_certificate_item` VALUES ('30', '16', '测试', '存放中央银行款项', '1003', null, null, '0', '5000', '7000');

-- ----------------------------
-- Table structure for `fin_evidence_type`
-- ----------------------------
DROP TABLE IF EXISTS `fin_evidence_type`;
CREATE TABLE `fin_evidence_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) DEFAULT NULL,
  `type_code` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='凭证类型';

-- ----------------------------
-- Records of fin_evidence_type
-- ----------------------------
INSERT INTO `fin_evidence_type` VALUES ('1', '收', 's', '2018-05-22 22:14:43', null, '2018-05-22 22:14:43', null);
INSERT INTO `fin_evidence_type` VALUES ('2', '付', 'f', '2018-05-22 22:14:44', null, '2018-05-22 22:14:44', null);
INSERT INTO `fin_evidence_type` VALUES ('3', '转', 'z', '2018-05-22 22:14:46', null, '2018-05-22 22:14:46', null);

-- ----------------------------
-- Table structure for `fin_sett_method`
-- ----------------------------
DROP TABLE IF EXISTS `fin_sett_method`;
CREATE TABLE `fin_sett_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sett_method_name` varchar(100) DEFAULT NULL,
  `sett_method_code` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='结算方式';

-- ----------------------------
-- Records of fin_sett_method
-- ----------------------------
INSERT INTO `fin_sett_method` VALUES ('1', '现金结算', 'xj', null, null, null, null);
INSERT INTO `fin_sett_method` VALUES ('2', '转帐结算', 'zz', null, null, null, null);
INSERT INTO `fin_sett_method` VALUES ('3', '支票', 'zp', null, null, null, null);
INSERT INTO `fin_sett_method` VALUES ('4', '银行本票', 'bp', null, null, null, null);
INSERT INTO `fin_sett_method` VALUES ('5', '汇付结算', 'hf', null, null, null, null);
INSERT INTO `fin_sett_method` VALUES ('6', '汇付结算', 'hf', null, null, null, null);

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` varchar(32) NOT NULL,
  `log_user` varchar(32) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `log_ip` varchar(15) DEFAULT NULL,
  `log_action` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('00a5fa54ca324e5e9e07b14bac0c82c4', 'admin', '2018-05-20 12:59:47', '127.0.0.1', '');
INSERT INTO `log` VALUES ('027c11ba56ca4ee08598c379964fb542', 'admin', '2018-05-25 14:15:19', '127.0.0.1', '');
INSERT INTO `log` VALUES ('06d1ea7e29d94ae39f24ee001a288d65', 'fuhe', '2018-05-25 14:35:07', '127.0.0.1', '');
INSERT INTO `log` VALUES ('09cceec3ab0f417c8e6c0e4c672452a1', 'admin', '2018-05-25 13:59:27', '127.0.0.1', '');
INSERT INTO `log` VALUES ('0d2dee7e9d62484490d441ab0bf9f4c4', 'admin', '2018-05-17 14:23:02', '127.0.0.1', '');
INSERT INTO `log` VALUES ('0ed571c768bb4f4aa29115b95b60ee99', 'admin', '2018-05-17 14:45:56', '127.0.0.1', '');
INSERT INTO `log` VALUES ('124ed23fe8c244309371144d2788603a', 'admin', '2018-05-20 21:56:39', '127.0.0.1', '');
INSERT INTO `log` VALUES ('14e822929536445d8b6185df77ae213f', 'admin', '2018-05-20 11:29:06', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1655a18ca11042e8ba912f3a5db79cb4', 'admin', '2018-05-17 14:03:50', '127.0.0.1', '');
INSERT INTO `log` VALUES ('185c08e93b444cdab7089e6d2b1adce3', 'admin', '2018-05-20 10:45:26', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1871e07b11684803b73a14bda17e47d8', 'admin', '2018-05-20 16:15:47', '127.0.0.1', '');
INSERT INTO `log` VALUES ('18a51e2e513142ddb4be50db766b7d71', 'admin', '2018-05-19 00:35:56', '127.0.0.1', '');
INSERT INTO `log` VALUES ('199df47e932945b2aec25887779fad33', 'admin', '2018-05-20 15:45:04', '127.0.0.1', '');
INSERT INTO `log` VALUES ('19f3e5eace084282a53e5dd0ddf74ff6', 'admin', '2018-05-20 01:06:47', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1e147e71a1494a8290e0a2653a6087d5', 'admin', '2018-05-20 01:11:39', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1e1fa8ddbcde47ffa3b15ceafc3a684c', 'admin', '2018-05-25 14:41:54', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1e6194f5ccab4c9f8815b9055895ba4f', 'admin', '2018-05-17 14:03:04', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1f629d53a45d42be9367e40a4aceb7e5', 'admin', '2018-05-22 23:41:59', '127.0.0.1', '');
INSERT INTO `log` VALUES ('1fd0b98e3cf64500bd9d085bf2472bdf', 'admin', '2018-05-17 14:06:37', '127.0.0.1', '');
INSERT INTO `log` VALUES ('21a309030da941d9a2c858c0a533bcbf', 'admin', '2018-05-25 14:48:54', '127.0.0.1', '');
INSERT INTO `log` VALUES ('256def9c7a3b440c9f8873f7169732a1', 'admin', '2018-05-20 11:40:06', '127.0.0.1', '');
INSERT INTO `log` VALUES ('29554c73efec49418f7b4dd8e125d605', 'admin', '2018-05-20 10:59:13', '127.0.0.1', '');
INSERT INTO `log` VALUES ('2faadbe6c96047c0803a278b06d0e4e8', 'admin', '2018-05-20 22:26:08', '127.0.0.1', '');
INSERT INTO `log` VALUES ('319a3d87f5c849ecae915c54e9306a6b', 'admin', '2018-05-20 21:57:19', '127.0.0.1', '');
INSERT INTO `log` VALUES ('31dcc82144c54a3d9b3a40db0b90eed7', 'admin', '2018-05-19 23:53:51', '127.0.0.1', '');
INSERT INTO `log` VALUES ('34d33908b8184768a6a28eb147d1e676', 'admin', '2018-05-20 22:18:08', '127.0.0.1', '');
INSERT INTO `log` VALUES ('3609666864ad4b5d9439991974cdee31', 'fuhe', '2018-05-20 22:22:23', '127.0.0.1', '');
INSERT INTO `log` VALUES ('3703aaa7a25b447785f6a29cc6c6a405', 'fuhe', '2018-05-25 14:30:42', '127.0.0.1', '');
INSERT INTO `log` VALUES ('372d5a41e2b64d0d8e61d32975d22401', 'admin', '2018-05-25 14:44:02', '127.0.0.1', '');
INSERT INTO `log` VALUES ('396dbb12fbd94762a2f7428a3f355b49', 'admin', '2018-05-22 21:15:34', '127.0.0.1', '');
INSERT INTO `log` VALUES ('3f5790a0ceab4480a2fffb1dfbc083c0', 'admin', '2018-05-17 14:43:38', '127.0.0.1', '');
INSERT INTO `log` VALUES ('452db3758bf14474ba05f114f7b8bf12', 'ruzhang', '2018-05-20 22:12:03', '127.0.0.1', '');
INSERT INTO `log` VALUES ('466acefaea844ec6944504245c494f90', 'admin', '2018-05-17 13:13:31', '127.0.0.1', '');
INSERT INTO `log` VALUES ('4683f291a8e84a63a459454edd096989', 'admin', '2018-05-22 23:33:29', '127.0.0.1', '');
INSERT INTO `log` VALUES ('46b0ed1bb8ee488eaf69927566c75f65', 'admin', '2018-05-17 12:40:27', '127.0.0.1', '');
INSERT INTO `log` VALUES ('474e39b3a56049cfb9b6cd313a4b1afd', 'admin', '2018-05-17 16:17:32', '127.0.0.1', '');
INSERT INTO `log` VALUES ('47997406a4fd4e60883bd182374ece4f', 'admin', '2018-05-20 21:57:12', '127.0.0.1', '');
INSERT INTO `log` VALUES ('4831dcd8c0a14292aab18c96e6473e22', 'fuhe', '2018-05-25 14:33:44', '127.0.0.1', '');
INSERT INTO `log` VALUES ('4b6c8e2a31e8498cb451c29649d14588', 'admin', '2018-05-17 14:39:25', '127.0.0.1', '');
INSERT INTO `log` VALUES ('4e27fac93c724de493ec83372c966e35', 'fuhe', '2018-05-25 14:32:38', '127.0.0.1', '');
INSERT INTO `log` VALUES ('4ee42125b6994d4e98e91337780a9e98', 'admin', '2018-05-17 17:16:33', '127.0.0.1', '');
INSERT INTO `log` VALUES ('50612eb3464e48a9b65638939cf9f0b3', 'admin', '2018-05-20 22:03:57', '127.0.0.1', '');
INSERT INTO `log` VALUES ('511c9dc446624740b3ddf9f9c47c9404', 'admin', '2018-05-15 19:27:06', '127.0.0.1', '');
INSERT INTO `log` VALUES ('53d0d8232d4a4c6793fd28ab115743aa', 'admin', '2018-05-20 10:58:08', '127.0.0.1', '');
INSERT INTO `log` VALUES ('561edbae2c024ad8b50dd85de9bec4fb', 'admin', '2018-05-22 21:23:58', '127.0.0.1', '');
INSERT INTO `log` VALUES ('63e2fbe5e7994b21be10eced4de0671d', 'admin', '2018-05-25 14:31:05', '127.0.0.1', '');
INSERT INTO `log` VALUES ('648ef68bbde144cbb2c7c30840845091', 'admin', '2018-05-20 20:49:44', '127.0.0.1', '');
INSERT INTO `log` VALUES ('65e317c8053a4ece8f27ec9db79252df', 'admin', '2018-05-17 18:14:03', '127.0.0.1', '');
INSERT INTO `log` VALUES ('66dabfa1851446e59e811deae7aa53b8', 'admin', '2018-05-20 12:44:24', '127.0.0.1', '');
INSERT INTO `log` VALUES ('67c81d974d10428ba7d0aa919324407f', 'admin', '2018-05-20 22:11:16', '127.0.0.1', '');
INSERT INTO `log` VALUES ('68900e25f31342c7b5cff50a2aab1099', 'admin', '2018-05-20 14:00:10', '127.0.0.1', '');
INSERT INTO `log` VALUES ('698086713cc2450986fe4744c765dd3e', 'admin', '2018-05-17 11:56:02', '127.0.0.1', '');
INSERT INTO `log` VALUES ('6c9920f0a08d494e9aa70e38d282e1b0', 'admin', '2018-05-17 17:58:12', '127.0.0.1', '');
INSERT INTO `log` VALUES ('6cbcf2aef7fd4249b9319a1ef09060fd', 'admin', '2018-05-15 19:58:57', '127.0.0.1', '');
INSERT INTO `log` VALUES ('6d4a28e7d3594d0e805270689acb5e9d', 'admin', '2018-05-25 13:55:23', '127.0.0.1', '');
INSERT INTO `log` VALUES ('6e9686ccbc7e450ba7d1f671f789ffba', 'admin', '2018-05-17 14:37:11', '127.0.0.1', '');
INSERT INTO `log` VALUES ('6f3d1001d2b04dda81c8db75f325ce42', 'admin', '2018-05-17 14:57:49', '127.0.0.1', '');
INSERT INTO `log` VALUES ('77e2ac40e32241acb2e315a04b53dfc7', 'admin', '2018-05-25 14:30:17', '127.0.0.1', '');
INSERT INTO `log` VALUES ('78202fa578a54f759037765018bb1542', 'admin', '2018-05-22 23:41:48', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7aafa68af2a24e449ad0663279c380e3', 'admin', '2018-05-25 14:35:17', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7aca56c4be3b4080b423c231f95dc649', 'admin', '2018-05-20 12:52:22', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7c4381aab2d348a89e22c507ddc647a7', 'admin', '2018-05-25 14:33:19', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7dfc94da4a2146739be79528ef44a9eb', 'admin', '2018-05-17 14:56:14', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7e0fd29c8ae443b7a0afa015fd7b9cb2', 'ruzhang', '2018-05-20 22:11:31', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7ef25cc5dfd347b49bd245f0d87097e8', 'admin', '2018-05-17 16:08:49', '127.0.0.1', '');
INSERT INTO `log` VALUES ('7f28e1da34b747ec88d2d7a4030e371a', 'admin', '2018-05-15 20:03:44', '127.0.0.1', '');
INSERT INTO `log` VALUES ('816dfe2213884de3ae06999e03d649c1', 'fuhe', '2018-05-25 14:36:18', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8628ea097a5146dcb6307a153f8090f3', 'admin', '2018-05-20 12:39:57', '127.0.0.1', '');
INSERT INTO `log` VALUES ('86ecc50900de441da3ae5142e72ff6b9', 'admin', '2018-05-20 17:20:45', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8942a12700ec4dd2a23b50a97f6201ea', 'pingzheng', '2018-05-20 22:18:29', '127.0.0.1', '');
INSERT INTO `log` VALUES ('894e1674a7d94ae8b5e6aa95d16c3052', 'pingzheng', '2018-05-25 14:37:10', '127.0.0.1', '');
INSERT INTO `log` VALUES ('89502ec0ffaf41fa8a7c922187c48595', 'admin', '2018-05-23 21:52:12', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8c7e7a39d8824ea1a547f748174ef64a', 'admin', '2018-05-22 22:24:10', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8dad60afd0f94f658d66385b01916a2b', 'pingzheng', '2018-05-20 22:10:47', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8dc45de1bb4545978603a6100257ae77', 'admin', '2018-05-20 12:54:25', '127.0.0.1', '');
INSERT INTO `log` VALUES ('8f883163c6824085aa249e08edd47afc', 'admin', '2018-05-17 12:04:04', '127.0.0.1', '');
INSERT INTO `log` VALUES ('90172b39893142128dce4bfee0c471ec', 'admin', '2018-05-23 21:34:39', '127.0.0.1', '');
INSERT INTO `log` VALUES ('90ddefa6a3f64650bb7e5aea9a98b9f0', 'admin', '2018-05-22 22:54:41', '127.0.0.1', '');
INSERT INTO `log` VALUES ('949c8a9733964dc5ac74ffe441831eec', 'admin', '2018-05-17 14:07:33', '127.0.0.1', '');
INSERT INTO `log` VALUES ('9a07eda3fb854f3091b912e253174980', 'admin', '2018-05-17 13:44:00', '127.0.0.1', '');
INSERT INTO `log` VALUES ('9f8e3a683f64434581dda2e44baf4c1b', 'zhuren', '2018-05-17 16:09:44', '127.0.0.1', '');
INSERT INTO `log` VALUES ('9f8ffa1f85444e59a5a8f8948c5f6843', 'admin', '2018-05-25 14:10:43', '127.0.0.1', '');
INSERT INTO `log` VALUES ('a189b63b47e14e808ba8da5546d94e21', 'admin', '2018-05-20 17:30:11', '127.0.0.1', '');
INSERT INTO `log` VALUES ('a439371594ee44dfaad507a7e0ca7a39', 'admin', '2018-05-17 14:35:10', '127.0.0.1', '');
INSERT INTO `log` VALUES ('a456c2cf551d44d68b85e758ebf743a1', 'pingzheng', '2018-05-20 22:20:52', '127.0.0.1', '');
INSERT INTO `log` VALUES ('a8498133799e414cb893230fe12842a2', 'admin', '2018-05-22 22:14:15', '127.0.0.1', '');
INSERT INTO `log` VALUES ('a98aa22b70ca4dc9b1ee9e0af931a488', 'admin', '2018-05-20 15:38:36', '127.0.0.1', '');
INSERT INTO `log` VALUES ('ab7a5a93b8e34e83a1e1b4ec979a039e', 'admin', '2018-05-17 17:34:48', '127.0.0.1', '');
INSERT INTO `log` VALUES ('ad34585aa1cf4d4593e819ee248509e5', 'admin', '2018-05-17 14:18:52', '127.0.0.1', '');
INSERT INTO `log` VALUES ('aebed0960b47473b8205718103d24efa', 'admin', '2018-05-20 09:46:02', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b012a2349deb4edfab8348f138d1ffd8', 'admin', '2018-05-25 14:34:27', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b01f9f4cd7f0462884376461843e4076', 'admin', '2018-05-17 12:03:23', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b26429b293e44a26a80048be67d4d821', 'admin', '2018-05-17 14:59:09', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b7a3570c4bd641b09fec5067ef5cc031', 'fuhe', '2018-05-25 14:36:47', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b94b1f5933f346feae5e771b80f11f5d', 'admin', '2018-05-22 23:07:32', '127.0.0.1', '');
INSERT INTO `log` VALUES ('b9e2adb686984bc9a6b3d4257e9f61fe', 'admin', '2018-05-19 00:44:40', '127.0.0.1', '');
INSERT INTO `log` VALUES ('bd95d778b27e446a8db18a71f83b0592', 'admin', '2018-05-17 16:06:04', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c0287412812b41f38d9f191c145581c8', 'admin', '2018-05-15 19:34:03', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c18997a8033b4f7990577d8331b67312', 'admin', '2018-05-20 20:10:55', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c24b3fcb967649339788cf4053d9ac9a', 'admin', '2018-05-15 19:34:23', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c749df96340f4dd7b3cb78759f082764', 'admin', '2018-05-17 16:07:01', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c7c3e8ebaf30431eae7d984ddca491f9', 'admin', '2018-05-20 21:06:55', '127.0.0.1', '');
INSERT INTO `log` VALUES ('c8bce77e64ec4ba9b55929218e6f971c', 'zhuren', '2018-05-17 16:08:09', '127.0.0.1', '');
INSERT INTO `log` VALUES ('cc877fe3c7a5492a83e9815e4ef71765', 'admin', '2018-05-17 14:47:22', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d1f83dfc8e0640f4a11faf5114edbbca', 'admin', '2018-05-20 01:01:10', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d2a040f16fe34f0e943f0ccb8ed17395', 'admin', '2018-05-20 01:08:49', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d5d4ab8f2df844df8f2d762250834eb0', 'admin', '2018-05-20 15:49:23', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d730f30d466643fea0e085572c92b0ea', 'admin', '2018-05-17 12:30:15', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d7d4bab143134dfb8ba7691370be42f5', 'pingzheng', '2018-05-20 22:21:09', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d8058383931543209cda810506a1d823', 'admin', '2018-05-25 14:25:46', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d8605a30915f44c086b195aa3bd8aab6', 'admin', '2018-05-25 14:27:48', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d8fe45449e3342f58b19d14566f50a09', 'admin', '2018-05-20 13:19:29', '127.0.0.1', '');
INSERT INTO `log` VALUES ('d9c663a101b84ff9bf0fa027144b0755', 'fuhe', '2018-05-20 21:59:51', '127.0.0.1', '');
INSERT INTO `log` VALUES ('de8ec86bd6664cf1ab3e8d5dd010a175', 'admin', '2018-05-17 18:28:45', '127.0.0.1', '');
INSERT INTO `log` VALUES ('df0cf087708f47a68a570764b49ed0b5', 'admin', '2018-05-17 14:38:18', '127.0.0.1', '');
INSERT INTO `log` VALUES ('dfa903bc709442b3acec9d598b149127', 'fuhe', '2018-05-20 22:20:26', '127.0.0.1', '');
INSERT INTO `log` VALUES ('e190f27c9a5a4dddba6ef674c90d8d87', 'admin', '2018-05-20 16:08:32', '127.0.0.1', '');
INSERT INTO `log` VALUES ('e2ea3f69b5d7468bafca7bbad824dc1b', 'admin', '2018-05-17 14:41:15', '127.0.0.1', '');
INSERT INTO `log` VALUES ('e3222c058fcd46dabc6f63040b997a0c', 'admin', '2018-05-20 21:44:41', '127.0.0.1', '');
INSERT INTO `log` VALUES ('e5b5027a2a4745fcb5987e81ffbbb968', 'admin', '2018-05-17 14:28:25', '127.0.0.1', '');
INSERT INTO `log` VALUES ('e748ca0dfcfc483ca4e23889bd47e96b', 'admin', '2018-05-25 14:43:03', '127.0.0.1', '');
INSERT INTO `log` VALUES ('ecf8dfd948884b9fb1de5d194d5e2310', 'admin', '2018-05-19 22:01:46', '127.0.0.1', '');
INSERT INTO `log` VALUES ('f40671524ad2439481db8c1b0590bb55', 'admin', '2018-05-22 22:53:17', '127.0.0.1', '');
INSERT INTO `log` VALUES ('f4a2da98fdc040f198371c42d0c2b5b8', 'admin', '2018-05-22 23:16:27', '127.0.0.1', '');
INSERT INTO `log` VALUES ('fcc31f8730834986b9ef38bd914cf7e2', 'admin', '2018-05-25 14:38:15', '127.0.0.1', '');
INSERT INTO `log` VALUES ('fd1d2db4ffa84471b9dc1f6ce7f26582', 'admin', '2018-05-17 12:34:49', '127.0.0.1', '');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `uid` varchar(32) NOT NULL,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`uid`,`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('3eb011e6638b4184aef77c3b095883df', 'flyshy', 'b2e61c4d4362ac061ad3bfe115b7b700', '6cfa0ce808a2ff68e61d248af75243a7', '1', '2017-03-14 09:44:35', '2017-03-14 09:44:35');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `menu_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_type` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '资源类型，菜单或都按钮(menu,button)',
  `menu_url` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `parent_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `child_num` int(10) NOT NULL DEFAULT '0',
  `listorder` int(10) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('00dc5c51e4824f49a30013385f680b0c', '日志管理', 'auth', '/console/log/index', 'log:index', 'e5f52fe2115e46229c60803e478d2e9a', null, '0', '1', '2017-01-06 14:11:23', '2017-05-08 14:55:21');
INSERT INTO `menu` VALUES ('0519372651f9424c9fbc6d83cebe31ac', '科目余额', 'auth', '/console/accountover/index', 'accountover:index', '94ffc0f6302f48018c5a89de4731afd3', null, '0', '0', '2018-05-20 21:07:06', '2018-05-20 21:07:06');
INSERT INTO `menu` VALUES ('0bdfa256e9b146d89b8ca4e80e0757f1', '部门添加', 'auth', '/console/dept/save', 'dept:save', 'cc406fc7a9744afcba25dda109ef869d', null, '0', '0', '2018-05-17 10:10:42', '2018-05-17 10:10:42');
INSERT INTO `menu` VALUES ('0f8b1b72fe594a759ce2329dcd8bb3e9', '总账', 'auth', '/console/generalLedger/index', 'generalLedger:index', '94ffc0f6302f48018c5a89de4731afd3', null, '0', '0', '2018-05-20 15:40:05', '2018-05-20 15:40:05');
INSERT INTO `menu` VALUES ('181487eb541f43a3ab30ad6e3967c744', '删除', 'auth', '/console/settMethod/delete', 'settMethod:delete', 'a3a2e12a446f4b599d0d06aac183c1dc', null, '0', '0', '2018-05-19 00:09:00', '2018-05-19 00:09:00');
INSERT INTO `menu` VALUES ('1cc3d9ad04e4424db1bb086d1678925e', '菜单删除', 'auth', '/console/menu/delete', 'menu:delete', '736bdf0b9aec4c59928a530e34bd9aad', null, '0', '0', '2017-05-10 16:45:30', '2017-05-10 16:45:30');
INSERT INTO `menu` VALUES ('2191c9efc2fa431bb427b81ad938e8aa', '角色保存', 'auth', '/console/role/save', 'role:save', '6cda978dc9404ba2bf5854b74735b0bc', null, '0', '0', '2017-05-10 16:41:21', '2017-05-10 16:41:21');
INSERT INTO `menu` VALUES ('270fc5b7c6ac482490837b566e45d0c2', '凭证管理', 'menu', '/console/certificateBase/index', 'certificateBase:index', '0', null, '6', '0', '2018-05-19 22:02:19', '2018-05-20 01:08:32');
INSERT INTO `menu` VALUES ('2ea7432849214cd0b8b7c5418ebf349d', '密码修改', 'button', '/console/admin/editpwd', 'admin:editpwd', '7de6e7383e5f434289eb47431c32f39c', null, '0', '0', '2018-05-25 14:31:53', '2018-05-25 14:31:53');
INSERT INTO `menu` VALUES ('35a6a364403d4994be8de854ce44bfc8', '凭证填置', 'auth', '/console/certifcateBase/from', 'certifcateBase:edit', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-19 22:03:15', '2018-05-20 01:06:29');
INSERT INTO `menu` VALUES ('362923d31e064f84adb8c23ba91e54d8', '管理员编辑', 'auth', '/console/admin/from', 'admin:edit', 'e0dde3b9227c471eb3bd2ba0a7fab131', null, '0', '0', '2017-05-08 14:57:39', '2017-05-10 16:40:47');
INSERT INTO `menu` VALUES ('384fed733d17458da6b9af2eb01d161e', '编辑', 'auth', '/console/evidenceType/from', 'evidenceType:edit', '9e57818b3d2e4a6faacf8eebbaec5a75', null, '0', '0', '2018-05-19 00:05:32', '2018-05-19 00:07:28');
INSERT INTO `menu` VALUES ('3ac96215e82f40b5bfe442e6828641df', '系统管理', 'menu', '/console/system/admin', 'system:admin', '0', null, '3', '1', '2016-12-07 16:00:00', '2017-05-10 16:46:27');
INSERT INTO `menu` VALUES ('3d3fa5e8dff44e9593a8e4a409d0bd46', '凭证保存', 'button', '/console/certifcateBase/save', 'certifcateBase:save', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-20 12:53:29', '2018-05-20 17:15:01');
INSERT INTO `menu` VALUES ('3e2894c4386f4239aa5fe4c3098ae608', '字典配置', 'menu', '/console/accountCertifcate/index', 'accountCertifcate:index', '0', null, '3', '0', '2018-05-18 23:58:06', '2018-05-18 23:58:06');
INSERT INTO `menu` VALUES ('47902ae36c534058aacfa04a318a33b4', '密码修改', 'auth', '/console/admin/viewpwd', 'admin:editpwd', '647bb41f1a984853be7ec020d5d1dc58', null, '0', '0', '2018-05-17 14:04:25', '2018-05-17 14:04:25');
INSERT INTO `menu` VALUES ('4c5e2a95e58e4c62a6a4f83f8027aa75', '编辑', 'auth', '/console/accountCertifcate/from', 'accountCertifcate:edit', 'c910ddb9061b48659f932994a352400e', null, '0', '0', '2018-05-19 00:01:40', '2018-05-19 00:02:21');
INSERT INTO `menu` VALUES ('5188320791524ae8bd512f893379ce27', '删除', 'auth', '/console/accountCertifcate/delete', 'accountCertifcate:delete', 'c910ddb9061b48659f932994a352400e', null, '0', '0', '2018-05-19 00:02:39', '2018-05-19 00:03:33');
INSERT INTO `menu` VALUES ('58c02996bb7f4ff798e8d8c182747eb0', '查看', 'auth', '/console/book/index', 'book:index', 'd5de82cb92d843648be18b2b8d039925', null, '0', '0', '2018-05-20 20:15:40', '2018-05-20 20:15:40');
INSERT INTO `menu` VALUES ('636b041b0a434aa4a7d902f34b7fe6c9', '添加', 'auth', '/console/settMethod/save', 'settMethod:save', 'a3a2e12a446f4b599d0d06aac183c1dc', null, '0', '0', '2018-05-19 00:07:57', '2018-05-19 22:07:02');
INSERT INTO `menu` VALUES ('6580896645d046a0acf3c1194d7bbf8e', '用户删除', 'menu', '/console/admin/delete', 'admin:delete', 'e0dde3b9227c471eb3bd2ba0a7fab131', null, '0', '0', '2017-05-10 16:39:44', '2018-05-17 20:24:34');
INSERT INTO `menu` VALUES ('6cda978dc9404ba2bf5854b74735b0bc', '角色管理', 'auth', '/console/role/index', 'role:index', '3ac96215e82f40b5bfe442e6828641df', null, '4', '2', '2016-12-07 16:47:40', '2016-12-07 16:47:40');
INSERT INTO `menu` VALUES ('6cf3887190a5422480e6094c58a8f69a', '凭证查看', 'button', '/console/certifcateBase/itemList', 'certifcateBase:index', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-20 01:10:24', '2018-05-20 13:20:10');
INSERT INTO `menu` VALUES ('702f7ea562ab45798410d60f6d18465f', '删除', 'auth', '/console/evidenceType/delete', 'evidenceType:delete', '9e57818b3d2e4a6faacf8eebbaec5a75', null, '0', '0', '2018-05-19 00:06:00', '2018-05-19 00:06:00');
INSERT INTO `menu` VALUES ('71f3766578bd4219a66720c6e4c4a0fa', '部门修改', 'auth', '/console/dept/from', 'dept:edit', 'cc406fc7a9744afcba25dda109ef869d', null, '0', '0', '2018-05-17 10:10:08', '2018-05-17 10:11:43');
INSERT INTO `menu` VALUES ('736bdf0b9aec4c59928a530e34bd9aad', '权限管理', 'auth', '/console/menu/index', 'menu:index', '3ac96215e82f40b5bfe442e6828641df', null, '3', '3', '2016-12-07 16:50:17', '2018-05-17 20:23:58');
INSERT INTO `menu` VALUES ('74f72b24dbf7406c9e91dfb42b36fd62', '删除', 'button', '/console/book/delete', 'book:delete', 'd5de82cb92d843648be18b2b8d039925', null, '0', '0', '2018-05-20 20:14:23', '2018-05-20 20:28:58');
INSERT INTO `menu` VALUES ('7de6e7383e5f434289eb47431c32f39c', '密码设置', 'button', '/console/admin/editpwd', 'admin:editpwd', '0', null, '1', '0', '2018-05-25 14:31:14', '2018-05-25 14:34:46');
INSERT INTO `menu` VALUES ('817e3af5f73443ecb6448b45d38ba6f6', '添加', 'button', '/console/book/save', 'book:save', 'd5de82cb92d843648be18b2b8d039925', null, '0', '0', '2018-05-20 20:12:08', '2018-05-20 20:21:32');
INSERT INTO `menu` VALUES ('858047ef28fc42b8b142b7d48e397d63', '部门查看', 'auth', '/console/dept/index', 'dept:index', 'cc406fc7a9744afcba25dda109ef869d', null, '0', '0', '2018-05-17 10:09:26', '2018-05-17 10:09:26');
INSERT INTO `menu` VALUES ('85dad2bd9023451fab632dcfc4357d3b', '用户保存', 'auth', '/console/admin/save', 'admin:save', 'e0dde3b9227c471eb3bd2ba0a7fab131', null, '0', '0', '2017-05-10 16:38:07', '2018-05-17 20:24:51');
INSERT INTO `menu` VALUES ('8682d1d509d94c1d9e8cb300b0e8b396', '添加', 'auth', '/console/accountCertifcate/save', 'accountCertifcate:save', 'c910ddb9061b48659f932994a352400e', null, '0', '0', '2018-05-19 00:00:01', '2018-05-19 00:03:44');
INSERT INTO `menu` VALUES ('8a653e3fb15642d9be6aad13b02009fb', '角色授权', 'auth', '/console/role/grant', 'role:grant', '6cda978dc9404ba2bf5854b74735b0bc', null, '0', '0', '2017-05-10 16:42:37', '2017-05-10 16:42:37');
INSERT INTO `menu` VALUES ('920b17c730b846e6bcc6f35abe7d1c1f', '明细', 'auth', '/console/accountDetails/index', 'accountDetails:index', '94ffc0f6302f48018c5a89de4731afd3', null, '0', '0', '2018-05-20 16:08:42', '2018-05-20 16:08:42');
INSERT INTO `menu` VALUES ('94ffc0f6302f48018c5a89de4731afd3', '报表', 'menu', '/console/stat/index', 'stat:index', '0', null, '3', '0', '2018-05-20 15:38:57', '2018-05-20 15:38:57');
INSERT INTO `menu` VALUES ('9e57818b3d2e4a6faacf8eebbaec5a75', '凭证类型管理', 'menu', '/console/evidenceType/index', 'evidenceType:index', '3e2894c4386f4239aa5fe4c3098ae608', null, '3', '0', '2018-05-19 00:04:01', '2018-05-19 00:04:01');
INSERT INTO `menu` VALUES ('9f41af1454d046b596023a2822c5078c', '角色编辑', 'auth', '/console/role/from', 'role:edit', '6cda978dc9404ba2bf5854b74735b0bc', null, '0', '0', '2017-05-08 14:59:25', '2017-05-08 14:59:25');
INSERT INTO `menu` VALUES ('9fc5f788d0064a2aa076192eb10a5072', '凭证列表', 'auth', '/console/certifcateBase/index', 'certifcateBase:index', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-20 13:20:26', '2018-05-20 13:20:26');
INSERT INTO `menu` VALUES ('a3a2e12a446f4b599d0d06aac183c1dc', '结算方式管理', 'menu', '/console/settMethod/index', 'settMethod:index', '3e2894c4386f4239aa5fe4c3098ae608', null, '3', '0', '2018-05-19 00:06:51', '2018-05-19 00:06:51');
INSERT INTO `menu` VALUES ('aab7966c97db4643a36cb5afa24be38b', '角色删除', 'menu', '/console/role/delete', 'role:delete', '6cda978dc9404ba2bf5854b74735b0bc', null, '0', '0', '2017-05-10 16:43:37', '2017-05-10 16:43:37');
INSERT INTO `menu` VALUES ('c27cb559c392499786e5438a1ea92f32', '添加', 'auth', '/console/evidenceType/save', 'evidenceType:save', '9e57818b3d2e4a6faacf8eebbaec5a75', null, '0', '0', '2018-05-19 00:04:49', '2018-05-19 00:04:49');
INSERT INTO `menu` VALUES ('c5cca135ee534bfeb482fb04b9311982', '菜单编辑', 'auth', '/console/menu/from', 'menu:from', '736bdf0b9aec4c59928a530e34bd9aad', null, '0', '0', '2016-12-07 16:51:31', '2017-05-08 15:00:02');
INSERT INTO `menu` VALUES ('c62a71b1bf8547e0b9521f76ac264c24', '凭证复核', 'button', '/console/certifcateBase/review', 'certifcateBase:review', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-20 21:12:35', '2018-05-20 21:13:30');
INSERT INTO `menu` VALUES ('c910ddb9061b48659f932994a352400e', '科目管理', 'auth', '/console/accountCertifcate/index', 'accountCertifcate:index', '3e2894c4386f4239aa5fe4c3098ae608', null, '3', '0', '2018-05-18 23:59:08', '2018-05-19 00:18:25');
INSERT INTO `menu` VALUES ('ca2e733335a24c139db6773da53c582a', '凭证修改', 'button', '/console/certifcateBase/edit', 'certifcateBase:edit', '270fc5b7c6ac482490837b566e45d0c2', null, '0', '0', '2018-05-19 22:05:56', '2018-05-20 16:54:57');
INSERT INTO `menu` VALUES ('cc406fc7a9744afcba25dda109ef869d', '部门管理', 'menu', '/console/dept/index', 'dept:index', '142e308996e642b69144e9904799cdff', null, '3', '0', '2018-05-17 10:06:26', '2018-05-17 10:06:26');
INSERT INTO `menu` VALUES ('d5de82cb92d843648be18b2b8d039925', '记账', 'menu', '/console/book/index', 'book:index', '0', null, '4', '0', '2018-05-20 20:11:03', '2018-05-20 20:11:03');
INSERT INTO `menu` VALUES ('df1e4e3c59b0433ba99b53987942a36d', '编辑', 'auth', '/console/settMethod/from', 'settMethod:edit', 'a3a2e12a446f4b599d0d06aac183c1dc', null, '0', '0', '2018-05-19 00:08:29', '2018-05-19 00:08:29');
INSERT INTO `menu` VALUES ('e0dde3b9227c471eb3bd2ba0a7fab131', '用户管理', 'auth', '/console/admin/index', 'admin:index', '3ac96215e82f40b5bfe442e6828641df', null, '3', '1', '2016-12-07 16:45:47', '2018-05-17 20:24:20');
INSERT INTO `menu` VALUES ('e5f52fe2115e46229c60803e478d2e9a', '扩展设置', 'menu', '/console/system/setting', 'system:setting', '0', null, '1', '3', '2016-12-07 16:36:42', '2017-05-10 16:50:00');
INSERT INTO `menu` VALUES ('ee4121d81a6a4300829616a020b6439b', '编辑', 'auth', '/console/book/from', 'book:edit', 'd5de82cb92d843648be18b2b8d039925', null, '0', '0', '2018-05-20 20:13:28', '2018-05-20 20:13:28');
INSERT INTO `menu` VALUES ('f4237d06c0c94906bdc04f5ed19cbaeb', '菜单保存', 'auth', '/console/menu/save', 'menu:save', '736bdf0b9aec4c59928a530e34bd9aad', null, '0', '0', '2017-05-10 16:44:51', '2017-05-10 16:44:51');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `role_desc` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_unique` (`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', '记账', '记账', '1', '2018-05-20 22:07:55', '2018-05-20 22:07:55');
INSERT INTO `role` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '凭证填制', '凭证填制', '1', '2018-05-20 22:07:26', '2018-05-20 22:07:26');
INSERT INTO `role` VALUES ('36f1dd1296674fc08484c5abf6a5806b', '系统管理员', '系统管理员', '1', '2016-12-07 08:53:57', '2017-05-11 13:59:03');
INSERT INTO `role` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '复核', '复核', '1', '2018-05-20 21:58:52', '2018-05-20 21:58:52');
INSERT INTO `role` VALUES ('cbe8356d64a8433cb5dad5c7fccf8dce', '普通管理员', '普通管理员', '1', '2016-12-07 13:21:21', '2017-05-05 12:58:38');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `menu_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `role_menu_foreign` (`menu_id`) USING BTREE,
  CONSTRAINT `fk_ref_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `fk_ref_role2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '270fc5b7c6ac482490837b566e45d0c2');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '270fc5b7c6ac482490837b566e45d0c2');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '2ea7432849214cd0b8b7c5418ebf349d');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '2ea7432849214cd0b8b7c5418ebf349d');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '35a6a364403d4994be8de854ce44bfc8');
INSERT INTO `role_menu` VALUES ('cbe8356d64a8433cb5dad5c7fccf8dce', '3ac96215e82f40b5bfe442e6828641df');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '3d3fa5e8dff44e9593a8e4a409d0bd46');
INSERT INTO `role_menu` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', '58c02996bb7f4ff798e8d8c182747eb0');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '6cf3887190a5422480e6094c58a8f69a');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '6cf3887190a5422480e6094c58a8f69a');
INSERT INTO `role_menu` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', '74f72b24dbf7406c9e91dfb42b36fd62');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '7de6e7383e5f434289eb47431c32f39c');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '7de6e7383e5f434289eb47431c32f39c');
INSERT INTO `role_menu` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', '817e3af5f73443ecb6448b45d38ba6f6');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', '9fc5f788d0064a2aa076192eb10a5072');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', '9fc5f788d0064a2aa076192eb10a5072');
INSERT INTO `role_menu` VALUES ('908ccf7a07e44f328093b78bef6a4faf', 'c62a71b1bf8547e0b9521f76ac264c24');
INSERT INTO `role_menu` VALUES ('3006a6d5cafe4a9c806445d389f1933f', 'ca2e733335a24c139db6773da53c582a');
INSERT INTO `role_menu` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', 'd5de82cb92d843648be18b2b8d039925');
INSERT INTO `role_menu` VALUES ('cbe8356d64a8433cb5dad5c7fccf8dce', 'e5f52fe2115e46229c60803e478d2e9a');
INSERT INTO `role_menu` VALUES ('2a19e3df7a5641d09db7bafeda7a3871', 'ee4121d81a6a4300829616a020b6439b');
