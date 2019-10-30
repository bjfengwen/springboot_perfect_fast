
SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(45) NOT NULL COMMENT '用户主键',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '盐',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '头像地址',
  `phone_num` varchar(11) DEFAULT NULL COMMENT '十一位手机号码',
  `country_code` varchar(45) DEFAULT NULL COMMENT '手机国际区号代码，如+86',
  `user_mail` varchar(45) DEFAULT NULL COMMENT '用户邮箱',
  `user_status` tinyint(4) DEFAULT '0' COMMENT '用户状态：0正常',
  `find_me_by_phone` tinyint(4) DEFAULT '1' COMMENT '手机号状态：0禁止被搜索，1允许被搜索',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `max_amount` int(11) DEFAULT '10' COMMENT '最大授权用户数',
  `use_amount` int(11) DEFAULT '0' COMMENT '已经使用授权用户数',
  `max_device_amount` int(11) DEFAULT '1' COMMENT '最大授权设备数量',
  `use_device_amount` int(11) DEFAULT '0' COMMENT '已经有授权设备',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('USER0754071052341104', null, null, null, null, 'http://pic1.dingweidashi.net/27c0f981-518a-4293-bc63-55cb42c43972.webp', '18744444444', '+86', null, '0', '1', '2019-01-26 15:09:41', '2019-02-13 16:07:19', '10', '1', '1', '0');
INSERT INTO `t_user` VALUES ('USER1023394803241619', '二狗护糖', null, null, null, 'http://pic1.dingweidashi.net/472c9332-e42e-46ec-8b7c-0c002c31c921.webp', '18700000000', '+86', null, '0', '1', '2019-01-26 10:25:44', '2019-02-13 15:50:04', '10', '2', '1', '0');
INSERT INTO `t_user` VALUES ('USER1180745462760597', null, null, null, null, null, '18766666666', '+86', null, '0', '1', '2019-01-26 14:42:48', '2019-01-26 16:27:20', '10', '0', '1', '0');
INSERT INTO `t_user` VALUES ('USER2124514037589644', null, null, null, null, null, '18777777777', '+86', null, '0', '1', '2019-01-26 10:29:59', '2019-01-26 10:40:14', '10', '0', '1', '0');
INSERT INTO `t_user` VALUES ('USER4485945831647132', null, null, null, null, null, '18711111111', '+86', null, '0', '1', '2019-01-26 10:53:19', '2019-01-30 10:43:25', '10', '2', '1', '0');
INSERT INTO `t_user` VALUES ('USER5097674933521245', null, null, null, null, null, '18710029010', '+86', null, '0', '1', '2019-01-28 12:07:53', '2019-02-13 13:42:29', '10', '1', '1', '0');
INSERT INTO `t_user` VALUES ('USER5451065481799200', null, null, null, null, null, '15110263477', '+86', null, '0', '1', '2019-01-30 10:25:06', '2019-01-30 11:34:06', '10', '0', '1', '0');
INSERT INTO `t_user` VALUES ('USER5882627246810329', '我是谁', null, null, null, null, '18733333333', '+86', null, '0', '1', '2019-01-26 14:41:29', '2019-02-15 15:10:26', '10', '1', '1', '0');
INSERT INTO `t_user` VALUES ('USER7184710112112516', null, null, null, null, null, '18755555555', '+86', null, '0', '1', '2019-01-28 04:03:41', '2019-01-28 04:03:41', '10', '0', '1', '0');
INSERT INTO `t_user` VALUES ('USER7634217405362021', '大海', null, null, null, null, '18614235678', '+86', null, '0', '1', '2019-01-28 12:02:44', '2019-01-31 14:52:16', '10', '2', '1', '1');
INSERT INTO `t_user` VALUES ('USER9216344000396330', '鹅狗抢糖', null, null, null, 'http://pic1.dingweidashi.net/2fcbe7ad-d9fa-4cc4-8f32-4409bd5a3bc2.webp', '18722222222', '+86', null, '0', '1', '2019-01-26 14:51:08', '2019-01-28 12:05:09', '10', '1', '1', '0');

