/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : springboot_demo

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-14 09:20:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `css` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '样式',
  `uri` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '访问资源',
  `type` int(2) DEFAULT NULL COMMENT '类型(1页面/菜单 2按钮)',
  `permission` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'spring security标识',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '系统管理', null, '', '1', '', null);
INSERT INTO `sys_permission` VALUES ('2', '1', '管理员列表', null, '/route/getPage?location=sys-user/list', '1', null, null);
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('4', '2', '添加', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('5', '2', '编辑', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('6', '2', '删除', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('7', '1', '角色管理', null, '/route/getPage?location=sys-role/list', '1', null, null);
INSERT INTO `sys_permission` VALUES ('8', '7', '查询', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('9', '7', '添加', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('10', '7', '编辑', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('11', '7', '删除', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('12', '1', '权限管理', null, '/route/getPage?location=sys-permission/list', '1', null, null);
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('14', '12', '添加', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('15', '12', '编辑', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('16', '12', '删除', null, null, '2', null, null);
INSERT INTO `sys_permission` VALUES ('17', '0', '测试页面', null, '/route/getPage?location=sys-common/test', '1', '', null);
INSERT INTO `sys_permission` VALUES ('18', '0', '系统日志', null, '/route/getPage?location=sys-log/list', '1', '', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色名',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '根管理员', '即超级管理员', '2019-10-28 21:00:46', '2019-10-28 21:00:46');
INSERT INTO `sys_role` VALUES ('2', '测试', '供测试使用的角色', '2019-10-28 21:03:55', '2019-11-01 10:07:15');
INSERT INTO `sys_role` VALUES ('3', '开发', '随便写啦', '2019-10-28 21:04:22', '2019-10-31 10:42:26');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限资源id',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '0');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('2', '0');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '8');
INSERT INTO `sys_role_permission` VALUES ('2', '12');
INSERT INTO `sys_role_permission` VALUES ('2', '13');
INSERT INTO `sys_role_permission` VALUES ('2', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '0');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '3');
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '5');
INSERT INTO `sys_role_permission` VALUES ('3', '7');
INSERT INTO `sys_role_permission` VALUES ('3', '8');
INSERT INTO `sys_role_permission` VALUES ('3', '9');
INSERT INTO `sys_role_permission` VALUES ('3', '10');
INSERT INTO `sys_role_permission` VALUES ('3', '12');
INSERT INTO `sys_role_permission` VALUES ('3', '13');
INSERT INTO `sys_role_permission` VALUES ('3', '14');
INSERT INTO `sys_role_permission` VALUES ('3', '15');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '3');
INSERT INTO `sys_role_user` VALUES ('3', '2');
INSERT INTO `sys_role_user` VALUES ('4', '3');
INSERT INTO `sys_role_user` VALUES ('5', '3');
INSERT INTO `sys_role_user` VALUES ('6', '2');
INSERT INTO `sys_role_user` VALUES ('7', '1');
INSERT INTO `sys_role_user` VALUES ('8', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(80) NOT NULL COMMENT '密码(bcrypt加密)',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `gender` int(2) DEFAULT NULL COMMENT '性别：0女 1男',
  `state` int(2) DEFAULT '1' COMMENT '状态：0冻结 1正常 2删除',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', 'obito_mangekyo.jpg', 'admin@163.com', '1', '1', '2019-10-02 20:00:00', '2019-10-02 20:00:00');
INSERT INTO `sys_user` VALUES ('2', '123', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', '1570847812479.jpg', '123123123123@qq.com', '1', '1', '2019-10-04 20:39:16', '2019-10-31 10:51:26');
INSERT INTO `sys_user` VALUES ('3', 'test', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', '1570810496048.jpeg', 'test@qq.com', '0', '1', '2019-10-04 20:42:11', '2019-10-04 20:42:11');
INSERT INTO `sys_user` VALUES ('4', 'abcabc', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', 'default_avatar.png', 'abcabc@126.com', '1', '1', '2019-10-04 20:50:16', '2019-10-31 10:51:32');
INSERT INTO `sys_user` VALUES ('5', 'hello', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', null, 'hello@gmail.com', '1', '1', '2019-10-28 21:07:11', '2019-10-31 10:53:35');
INSERT INTO `sys_user` VALUES ('6', 'alin', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', null, 'alin@qq.com', '0', '1', '2019-10-29 13:07:31', '2019-10-29 17:03:41');
INSERT INTO `sys_user` VALUES ('7', 'obito', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', null, 'obito@163.com', '1', '1', '2019-10-29 17:06:26', '2019-10-29 17:06:26');
INSERT INTO `sys_user` VALUES ('8', 'rin', '$2a$10$Egof7TGlHXvzrX7R3tHyoOmA3mMd6shrdUP4FvvoVJBevbSIRbQPS', null, 'rin@126.com', '0', '1', '2019-10-29 17:09:44', '2019-10-31 10:37:09');
