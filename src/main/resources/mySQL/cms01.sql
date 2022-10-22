/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : cms01

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/10/2022 16:24:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'answer表主键，自动递增\r\n',
  `bug_id` int(11) NULL DEFAULT NULL COMMENT '关联bug表主键，用于判断是bug表内容或者brush表内容。如果为空则是brush表',
  `brush_id` int(11) NULL DEFAULT NULL COMMENT '关联brush表主键，用于判断是bug表内容或者brush表内容。如果为空则是bug表',
  `crete_time` datetime(0) NULL DEFAULT NULL COMMENT '提出解决方案时间',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决方案(bug答案或问题答案)',
  `man` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提出解决问题方案的人',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '针对快乐刷题，存储提交问题的人的Id，从cookie中获取',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bug_id`(`bug_id`) USING BTREE,
  INDEX `brush_id`(`brush_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES (1, 1, NULL, '2022-10-09 21:10:41', '检查数据库发现表名不一致，少写一个字母c，表名实际为:tb_account', 'ooo', NULL);
INSERT INTO `t_answer` VALUES (2, 2, NULL, '2022-10-10 09:56:02', '吃饭吃饭', 'aaa', NULL);
INSERT INTO `t_answer` VALUES (3, 2, NULL, '2022-10-10 10:01:36', '睡觉睡觉', 'bbb', NULL);
INSERT INTO `t_answer` VALUES (4, 2, NULL, '2022-10-10 10:26:47', '充分的武器', 'ccc', NULL);
INSERT INTO `t_answer` VALUES (11, NULL, 14, '2022-10-11 14:56:40', 'a1x', NULL, 0);
INSERT INTO `t_answer` VALUES (13, NULL, 4, '2022-10-11 17:12:08', 'a', NULL, 0);
INSERT INTO `t_answer` VALUES (14, NULL, 16, '2022-10-11 17:15:27', 'a', NULL, 0);
INSERT INTO `t_answer` VALUES (15, NULL, 17, '2022-10-11 20:39:08', '吃饭吃饭', NULL, 0);
INSERT INTO `t_answer` VALUES (17, NULL, 37, '2022-10-12 11:07:29', 'asfdsdaf', NULL, 0);
INSERT INTO `t_answer` VALUES (18, NULL, 39, '2022-10-12 11:11:54', 'zheshidaan', NULL, 0);
INSERT INTO `t_answer` VALUES (19, NULL, 40, '2022-10-12 11:14:01', 'answer222', NULL, 0);
INSERT INTO `t_answer` VALUES (20, NULL, 42, '2022-10-12 11:21:56', 'uyfutiy', NULL, 0);
INSERT INTO `t_answer` VALUES (21, NULL, 43, '2022-10-12 11:24:48', 'ascfewq', NULL, 0);
INSERT INTO `t_answer` VALUES (22, NULL, 60, '2022-10-12 11:47:49', '<p style=\"text-align: left;\">vdwsa</p>', NULL, 0);
INSERT INTO `t_answer` VALUES (23, NULL, 24, '2022-10-12 11:53:33', '<img src=\"http://localhost:9999/cms/lib/layui-v2.5.5/images/face/6.gif\" alt=\"[吃惊]\">', NULL, 0);
INSERT INTO `t_answer` VALUES (24, NULL, 34, '2022-10-12 11:55:41', '<img src=\"http://localhost:9999/cms/lib/layui-v2.5.5/images/face/1.gif\" alt=\"[嘻嘻]\">', NULL, 0);
INSERT INTO `t_answer` VALUES (25, NULL, 35, '2022-10-12 11:57:26', '<p style=\"text-align: center;\">gufvyut</p>', NULL, 0);
INSERT INTO `t_answer` VALUES (26, NULL, 33, '2022-10-12 14:05:05', 'a', NULL, 0);
INSERT INTO `t_answer` VALUES (27, NULL, 31, '2022-10-12 16:47:31', '<p style=\"text-align: center;\">vrfewgb</p>', NULL, 0);
INSERT INTO `t_answer` VALUES (28, NULL, 5, '2022-10-12 16:48:39', '<p style=\"text-align: left;\">关于这个问题我只能说出以下六点......</p>', NULL, 0);
INSERT INTO `t_answer` VALUES (29, NULL, 61, '2022-10-12 17:28:50', '发的瓦房v', NULL, 0);
INSERT INTO `t_answer` VALUES (30, 22, NULL, '2022-10-12 19:39:34', 'a', 'aaa', NULL);
INSERT INTO `t_answer` VALUES (31, 22, NULL, '2022-10-12 20:49:44', 'aaa', 'aaa', NULL);
INSERT INTO `t_answer` VALUES (32, 22, NULL, '2022-10-12 20:49:56', '<p style=\"text-align: center;\">fqwfewa</p>', 'aaa', NULL);
INSERT INTO `t_answer` VALUES (33, 22, NULL, '2022-10-12 21:01:04', '测试测试', 'aaa', NULL);
INSERT INTO `t_answer` VALUES (34, NULL, 63, '2022-10-14 10:25:23', '1212', NULL, NULL);
INSERT INTO `t_answer` VALUES (35, NULL, 3, '2022-10-14 16:59:06', '11', NULL, NULL);

-- ----------------------------
-- Table structure for t_brush
-- ----------------------------
DROP TABLE IF EXISTS `t_brush`;
CREATE TABLE `t_brush`  (
  `brush_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'brush表主键，自动递增',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建问题时间',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题详情',
  `parent_id` int(11) NOT NULL COMMENT '父ID',
  PRIMARY KEY (`brush_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_brush
-- ----------------------------
INSERT INTO `t_brush` VALUES (1, '2022-10-09 21:13:29', 'spring基础扩展', -1);
INSERT INTO `t_brush` VALUES (2, '2022-10-10 19:43:32', 'SSM 框架作业练习', -1);
INSERT INTO `t_brush` VALUES (3, '2022-10-10 19:44:27', 'spring jdbc 实现云日记云记类别修改功能', 1);
INSERT INTO `t_brush` VALUES (4, '2022-10-10 19:44:47', '基于spring aop环境实现云记项目访问日志收集功能，日志数据存储到mysql数据库', 1);
INSERT INTO `t_brush` VALUES (5, '2022-10-10 19:45:07', '使用SSM 环境实现云记项目用户登录', 2);
INSERT INTO `t_brush` VALUES (6, '2022-10-10 19:45:30', '使用SSM 环境实现用户非法请求访问过滤', 2);
INSERT INTO `t_brush` VALUES (7, '2022-10-10 19:45:45', 'SSM 环境下实现用户头像上传(可研究第三方云存储  技术，如七牛云、阿里云OSS等)', 2);
INSERT INTO `t_brush` VALUES (14, '2022-10-11 14:43:56', 'test', 1);
INSERT INTO `t_brush` VALUES (15, '2022-10-11 15:51:51', 'test', -1);
INSERT INTO `t_brush` VALUES (16, '2022-10-11 15:51:58', 'test2', 15);
INSERT INTO `t_brush` VALUES (17, '2022-10-11 20:38:54', 'test2', 15);
INSERT INTO `t_brush` VALUES (21, '2022-10-12 09:14:26', 'test3', 15);
INSERT INTO `t_brush` VALUES (23, '2022-10-12 09:22:32', '111', -1);
INSERT INTO `t_brush` VALUES (24, '2022-10-12 09:23:01', '<strike>csqacs</strike>', 23);
INSERT INTO `t_brush` VALUES (28, '2022-10-12 10:10:17', '<strike>csdac</strike>', 15);
INSERT INTO `t_brush` VALUES (31, '2022-10-12 10:13:37', 'cdsafv<strike>cdwvced</strike>', 15);
INSERT INTO `t_brush` VALUES (33, '2022-10-12 10:26:08', '<p style=\"text-align: center; \"><b>fwdqfv<i>fvdewq<u>vqw<strike>vgerwq</strike>gre</u></i></b></p>', 15);
INSERT INTO `t_brush` VALUES (34, '2022-10-12 10:31:01', 'cdsa<strike>fdaw</strike>', 15);
INSERT INTO `t_brush` VALUES (35, '2022-10-12 10:35:03', 'test', 15);
INSERT INTO `t_brush` VALUES (60, '2022-10-12 11:47:40', '<p style=\"text-align: center;\">vbfbew</p>', 23);
INSERT INTO `t_brush` VALUES (61, '2022-10-12 17:28:34', '<p style=\"text-align: center;\">v但是我v</p>', 15);
INSERT INTO `t_brush` VALUES (62, '2022-10-14 10:25:03', '222', -1);
INSERT INTO `t_brush` VALUES (63, '2022-10-14 10:25:15', '2221', 62);

-- ----------------------------
-- Table structure for t_bug
-- ----------------------------
DROP TABLE IF EXISTS `t_bug`;
CREATE TABLE `t_bug`  (
  `bug_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'bug序号',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '发布bug的用户的ID',
  `bug_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'bug标题',
  `bug_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'bug具体内容',
  `bug_picture` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `solve` int(11) NOT NULL DEFAULT 0 COMMENT 'bug解决与否,0未解决,1解决',
  PRIMARY KEY (`bug_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_bug
-- ----------------------------
INSERT INTO `t_bug` VALUES (41, '0', 't', '', '20221013d260b1f3a78b4dce8dbb274f16748bfa41422b1ca0fc8a1b.jpg', '2022-10-13 17:21:59', 0);
INSERT INTO `t_bug` VALUES (42, '0', 't', '', '20221013ee80ca96f8e6498ab786362a1ee913e11261113614.jpeg', '2022-10-13 17:24:45', 0);
INSERT INTO `t_bug` VALUES (43, '0', 'Spring-SpringJDBC-SpringJDBC的环境测试1', '', '2022101341f85dce666d450f800463b51653541c41422b1ca0fc8a1b.jpg', '2022-10-13 17:26:52', 0);
INSERT INTO `t_bug` VALUES (44, '0', 'Spring-SpringJDBC-SpringJDBC的环境测试1', '', '2022101319c86ae274084d2897f374dc1a09748941422b1ca0fc8a1b.jpg', '2022-10-13 17:32:45', 0);
INSERT INTO `t_bug` VALUES (55, '0', 'test', '<p>产生的瓦房店我</p><p><img src=\"data:image/png;base64,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\" alt=\"\"></p>', NULL, '2022-10-13 20:17:12', 0);

-- ----------------------------
-- Table structure for t_conference
-- ----------------------------
DROP TABLE IF EXISTS `t_conference`;
CREATE TABLE `t_conference`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `theme` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议主题',
  `rank_id` int(10) NULL DEFAULT NULL,
  `room_id` int(10) NULL DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起人,真姓名',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_conference
-- ----------------------------
INSERT INTO `t_conference` VALUES (40, '班级会议', 1, 12, 'admin', '2022-10-22 16:03:58', '2022-10-22 17:03:41', 'qqq');

-- ----------------------------
-- Table structure for t_conference_questions
-- ----------------------------
DROP TABLE IF EXISTS `t_conference_questions`;
CREATE TABLE `t_conference_questions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conference_id` int(10) NULL DEFAULT NULL COMMENT '会议id',
  `emit_id` int(10) NULL DEFAULT NULL COMMENT '指出人id',
  `Recipient_id` int(10) NULL DEFAULT NULL COMMENT '接收人id',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `question_state` int(10) NULL DEFAULT NULL COMMENT '0未知，1已解决，2为解决',
  `is_valid` int(10) NULL DEFAULT NULL,
  `group_id` int(10) NULL DEFAULT NULL COMMENT '组id',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_conference_questions
-- ----------------------------
INSERT INTO `t_conference_questions` VALUES (40, 40, 1, 9, '121', 2, 1, 1, NULL);

-- ----------------------------
-- Table structure for t_conference_room
-- ----------------------------
DROP TABLE IF EXISTS `t_conference_room`;
CREATE TABLE `t_conference_room`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `room_number` int(30) NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  `room_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_conference_room
-- ----------------------------
INSERT INTO `t_conference_room` VALUES (12, 101, 1, '自习室', 'admin');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `is_valid` int(35) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1001, '组员', '2022-10-08 16:18:27', '2022-10-08 16:18:34', 1);
INSERT INTO `t_role` VALUES (1002, '组长', '2022-10-08 16:20:16', '2022-10-08 16:20:19', 1);
INSERT INTO `t_role` VALUES (1003, '班长', '2022-10-08 16:20:59', '2022-10-08 16:21:02', 1);

-- ----------------------------
-- Table structure for t_sp
-- ----------------------------
DROP TABLE IF EXISTS `t_sp`;
CREATE TABLE `t_sp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '进度表id主键',
  `user_id` int(11) NOT NULL COMMENT '用户表外键',
  `video_sp_start` int(255) NULL DEFAULT NULL COMMENT '视频进度开始',
  `video_sp_end` int(255) NULL DEFAULT NULL COMMENT '视频进度开始',
  `code_sp_start` int(255) NULL DEFAULT NULL COMMENT '代码进度起始位置',
  `code_sp_end` int(255) NULL DEFAULT NULL COMMENT '代码进度结束位置',
  `note` int(10) NOT NULL COMMENT '笔记未完成/笔记已完成',
  `study_question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学习问题描述',
  `class_question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程问题描述',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `update_time` datetime(0) NOT NULL COMMENT '用于记录进度更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sp
-- ----------------------------
INSERT INTO `t_sp` VALUES (1, 1, 1, 9, 1, 7, 0, NULL, NULL, NULL, '2022-10-10 17:46:35');
INSERT INTO `t_sp` VALUES (2, 2, 1, 8, 2, 7, 1, NULL, NULL, NULL, '2022-10-10 17:46:43');
INSERT INTO `t_sp` VALUES (3, 3, 0, 0, 2, 0, 1, NULL, NULL, NULL, '2022-10-10 17:46:45');
INSERT INTO `t_sp` VALUES (4, 4, 1, 9, 2, 7, 0, NULL, NULL, NULL, '2022-10-10 17:46:46');
INSERT INTO `t_sp` VALUES (5, 5, 1, 9, 2, 7, 1, NULL, NULL, NULL, '2022-10-10 17:46:48');
INSERT INTO `t_sp` VALUES (6, 6, 5, 5, 3, 6, 1, NULL, NULL, NULL, '2022-10-10 17:46:49');
INSERT INTO `t_sp` VALUES (7, 7, 1, 10, 5, 8, 0, NULL, NULL, NULL, '2022-10-10 17:46:51');
INSERT INTO `t_sp` VALUES (8, 8, 1, 11, 5, 9, 0, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (9, 10, 1, 7, 5, 9, 1, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (10, 10, 1, 5, 5, 5, 1, NULL, NULL, NULL, '2022-10-11 17:46:52');
INSERT INTO `t_sp` VALUES (11, 11, 1, 11, 5, 9, 1, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (12, 12, 1, 8, 5, 6, 1, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (13, 13, 1, 10, 5, 9, 0, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (14, 14, 1, 9, 5, 10, 0, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (15, 15, 1, 7, 5, 9, 1, NULL, NULL, NULL, '2022-10-10 17:46:52');
INSERT INTO `t_sp` VALUES (16, 2, 1, 8, 2, 7, 1, NULL, NULL, NULL, '2022-10-11 17:46:43');
INSERT INTO `t_sp` VALUES (18, 2, 1, 2, 1, 2, 1, '', '', '', '2022-10-13 20:15:50');
INSERT INTO `t_sp` VALUES (19, 32, 1, 10, 1, 10, 1, '无', '无', '无', '2022-10-14 16:58:26');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `user_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '4QrcOUm6Wau+VuBX8g+IPg==' COMMENT '密码',
  `true_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生手机号',
  `QQ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生QQ或微信',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `sign_time` datetime(0) NULL DEFAULT NULL COMMENT '用户签到时间',
  `group_id` int(11) NOT NULL COMMENT '学生所在组',
  `is_valid` int(11) NOT NULL DEFAULT 1 COMMENT '是否可用，0为不可以，1为可用',
  `sign` int(11) NOT NULL DEFAULT 0 COMMENT '签到状态，0为未签到，1为签到',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 178 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '600000', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', NULL, NULL, '2022-10-09 09:01:44', '2022-10-09 09:01:48', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (2, '600101', '4QrcOUm6Wau+VuBX8g+IPg==', 'a11', NULL, NULL, '2022-10-09 09:03:14', '2022-10-09 20:32:47', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (3, '600102', '4QrcOUm6Wau+VuBX8g+IPg==', 'a12', NULL, NULL, '2022-10-08 17:03:22', '2022-10-08 17:03:25', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (4, '600103', '4QrcOUm6Wau+VuBX8g+IPg==', 'a13', NULL, NULL, '2022-10-10 09:24:36', '2022-10-10 09:24:36', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (5, '600104', '4QrcOUm6Wau+VuBX8g+IPg==', 'a14', NULL, NULL, '2022-10-10 09:24:49', '2022-10-10 11:56:24', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (6, '600105', '4QrcOUm6Wau+VuBX8g+IPg==', 'a15', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (7, '600106', '4QrcOUm6Wau+VuBX8g+IPg==', 'a16', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (8, '600107', '4QrcOUm6Wau+VuBX8g+IPg==', 'a17', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 1, 1, 0);
INSERT INTO `t_user` VALUES (9, '600201', '4QrcOUm6Wau+VuBX8g+IPg==', 'a21', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (10, '600202', '4QrcOUm6Wau+VuBX8g+IPg==', 'a22', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (11, '600203', '4QrcOUm6Wau+VuBX8g+IPg==', 'a23', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (12, '600204', '4QrcOUm6Wau+VuBX8g+IPg==', 'a24', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (13, '600205', '4QrcOUm6Wau+VuBX8g+IPg==', 'a25', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (14, '600206', '4QrcOUm6Wau+VuBX8g+IPg==', 'a26', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);
INSERT INTO `t_user` VALUES (15, '600207', '4QrcOUm6Wau+VuBX8g+IPg==', 'a27', NULL, NULL, '2022-10-10 15:06:54', '2022-10-10 15:06:57', '2022-10-11 15:02:29', 2, 1, 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 2, '2022-10-08 16:21:01', '2022-10-08 16:21:03');
INSERT INTO `t_user_role` VALUES (2, 2, 3, '2022-10-08 16:22:06', '2022-10-08 16:22:08');
INSERT INTO `t_user_role` VALUES (3, 3, 4, '2022-10-08 16:22:22', '2022-10-08 16:22:25');
INSERT INTO `t_user_role` VALUES (4, 4, 1, '2022-10-08 16:23:13', '2022-10-08 16:23:15');

SET FOREIGN_KEY_CHECKS = 1;
