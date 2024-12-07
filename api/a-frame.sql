/*
Navicat MySQL Data Transfer

Source Server         : LocalHost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : a-frame

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2024-06-26 11:41:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `app_user_username_uindex` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'MarkerHub', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', '18825000000', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '公众号：MarkerHub', '2024-06-25 14:39:23', '2024-06-20 23:38:27');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL,
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', 'admin10086@qq.com', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg', '17288890984', '2024-06-26 10:46:25', '2024-06-18 11:01:01');
