/*
 Navicat MySQL Data Transfer

 Source Server         : linzhao
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : education

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 17/07/2018 23:22:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `branch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, '实训中心', '沈阳市', '13478654765', 123, 41.660006, 0);
INSERT INTO `address` VALUES (2, 1, '艺术中心', '扬州市', '13478654765', 123.2333, 32.2333, 0);
INSERT INTO `address` VALUES (3, 1, '恒大名都', '天津市', '13478654765', 123.2333, 32.2333, 0);
INSERT INTO `address` VALUES (4, 6, '扬州分舵', '扬州市', '13478654765', 123.2333, 32.2333, 1);
INSERT INTO `address` VALUES (5, 6, '扬州分舵', '扬州市', '13478654765', 123.2333, 32.2333, 1);
INSERT INTO `address` VALUES (6, 1, '东北大学', '东北大学', '1008611', 123.42677201589, 41.65699265291, 0);
INSERT INTO `address` VALUES (7, 1, '大连211', '大连333', '213131', 121, 38, 1);
INSERT INTO `address` VALUES (8, 1, 'dadsa', 'sadsad', '213225', 123.309759, 41.812946, 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `adminname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (0, NULL, '1234567890', 'adminadmin', '超级管理员', 0);
INSERT INTO `admin` VALUES (1, 1, '1111111111', '1111111111', '管理员', 0);
INSERT INTO `admin` VALUES (2, 2, '1111111112', '1111111112', '管理员', 0);
INSERT INTO `admin` VALUES (3, 3, '1111111113', '1111111113', '管理员', 0);
INSERT INTO `admin` VALUES (6, 6, '6666666666', '6666666666', '管理员', 0);
INSERT INTO `admin` VALUES (10, 10, '1805767437', '1805767437', '管理员', 0);
INSERT INTO `admin` VALUES (11, 11, '1805964386', '1805964386', '管理员', 0);
INSERT INTO `admin` VALUES (12, 12, '1806046818', '1806046818', '管理员', 0);
INSERT INTO `admin` VALUES (13, 13, '1806049330', '1806049330', '管理员', 0);
INSERT INTO `admin` VALUES (14, 14, '1806053800', '1806053800', '管理员', 0);
INSERT INTO `admin` VALUES (15, 15, '1806056507', '1806056507', '管理员', 1);
INSERT INTO `admin` VALUES (16, 16, '1806060886', '1806060886', '管理员', 1);
INSERT INTO `admin` VALUES (17, 17, '1806069545', '1806069545', '管理员', 1);
INSERT INTO `admin` VALUES (18, 18, '1806120948', '1806120948', '管理员', 0);
INSERT INTO `admin` VALUES (19, 19, '1806180900', '1806180900', '管理员', 0);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `get` int(11) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (2, 1, 'Python', 888, 200, 60, '2018-07-31');
INSERT INTO `coupon` VALUES (3, 1, 'C/C++', 111111, 390, 160, '2018-06-29');

-- ----------------------------
-- Table structure for couponrecord
-- ----------------------------
DROP TABLE IF EXISTS `couponrecord`;
CREATE TABLE `couponrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gettime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `videopath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jczs` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`qid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1, '东软睿道', '/video/life.mp4', '沈阳东软睿道教育服务有限公司（简称东软睿道）由东软创办，基于东软的产业发展与人才培养实践，提供更加符合IT行业发展需求的人才培养体系和方法学，精准提升大学生和大学后的就业、创业技能，为新经济时代的转型发展提供高质量规模化的人才供给。', '<p><img src=\"http://www.zyrc.tech/uploads/1/shenyangpark1.jpg\" style=\"max-width: 90%\"/><img src=\"http://www.zyrc.tech/uploads/1/shenyangpark2.jpg\" style=\"max-width: 90%\"/><img src=\"http://www.zyrc.tech/uploads/1/shenyangpark3.jpg\" style=\"max-width: 90%\"/><img src=\"http://www.zyrc.tech/uploads/1/shenyangpark4.jpg\" style=\"max-width: 90%\"/>\r\n <img src=\"http://www.zyrc.tech/images/shenyangpark5.jpg\" style=\"max-width: 90%\"/></p>', 0);
INSERT INTO `enterprise` VALUES (2, '古汉文渊', '', '古汉文渊，带您领略古文之美', NULL, 0);
INSERT INTO `enterprise` VALUES (3, '东北大学', NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (6, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (10, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (11, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (12, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (13, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (14, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (15, NULL, NULL, NULL, NULL, 1);
INSERT INTO `enterprise` VALUES (16, NULL, NULL, NULL, NULL, 1);
INSERT INTO `enterprise` VALUES (17, NULL, NULL, NULL, NULL, 1);
INSERT INTO `enterprise` VALUES (18, NULL, NULL, NULL, NULL, 0);
INSERT INTO `enterprise` VALUES (19, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for freelisten
-- ----------------------------
DROP TABLE IF EXISTS `freelisten`;
CREATE TABLE `freelisten`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `pubtime` datetime(0) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of freelisten
-- ----------------------------
INSERT INTO `freelisten` VALUES (1, 'JAVA免费试听课大咔讲师', 'java.jpg', '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', '进行中', 'java', 1, 1, '2018-06-11 10:53:43', 0);
INSERT INTO `freelisten` VALUES (2, '大数据免费试听课大咖讲师', 'c.jpg', '本课程的学习方向为C++应用开发方向，将在恒大名都开课，目前正火热招生中……', '进行中', 'c', 1, 2, '2018-06-12 10:53:46', 0);
INSERT INTO `freelisten` VALUES (3, 'PHP免费试听课', 'python.jpg', '本课程的学习方向为Python与深度学习方向，将在恒大名都开课，目前正火热进行中……', '进行中', 'python', 1, 1, '2018-06-13 10:53:50', 0);
INSERT INTO `freelisten` VALUES (4, 'PHP项目实战', 'php.jpg', '本课程的学习方向为PHP项目开发方向，将在实训中心免费开讲，目前正火热招生中……', '进行中', 'php', 1, 1, '2018-07-11 00:52:24', 0);
INSERT INTO `freelisten` VALUES (5, '知识图谱与语义Web', 'semantics.jpg', '本课程的学习方向为知识图谱与语义Web方向，将在恒大名都免费开讲，目前正火热招生中……', '进行中', '知识图谱', 1, 2, '2018-07-04 00:52:27', 0);
INSERT INTO `freelisten` VALUES (6, '机器学习', 'learning.jpg', '本课程的学习方向为机器学习方向，将在实训中心免费开讲，目前正火热招生中……', '进行中', '人工智能', 1, 1, '2018-07-06 00:52:30', 0);

-- ----------------------------
-- Table structure for freelistenbook
-- ----------------------------
DROP TABLE IF EXISTS `freelistenbook`;
CREATE TABLE `freelistenbook`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `booktime` datetime(0) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `comment` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  `userdeletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of freelistenbook
-- ----------------------------
INSERT INTO `freelistenbook` VALUES (7, 2, '费园园', '15940471397', '2018-05-26 17:54:50', '待处理', '嘻嘻嘻', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (8, 1, '费园园', '15940471397', '2018-05-26 17:55:33', '待处理', '弟弟', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (9, 3, '费园园', '15940471397', '2018-05-26 17:55:35', '待处理', '弟弟', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (10, 1, '费园园', '15940471397', '2018-05-26 17:58:17', '待处理', '玩儿玩儿', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (11, 4, '费园园', '15940471397', '2018-06-15 09:53:39', '待处理', '', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (12, 6, '费园园', '15940471397', '2018-06-15 11:39:18', '待处理', '上午课', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (13, 6, '费园园', '15940471397', '2018-06-15 12:00:02', '待处理', '谁是', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (14, 3, '谢谢', '15940471397', '2018-06-15 12:00:28', '待处理', '谁是', 8, 0, 0);
INSERT INTO `freelistenbook` VALUES (15, 3, '谢谢', '123', '2018-06-15 12:00:30', '待处理', '谁是', 9, 0, 0);
INSERT INTO `freelistenbook` VALUES (16, 2, '费园园', '15940471397', '2018-06-15 14:05:03', '已处理', '', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (17, 3, '费园园', '15940471397', '2018-06-15 14:12:20', '待处理', '', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (18, 3, '', '', '2018-06-15 14:31:52', '待处理', '', 12, 0, 0);
INSERT INTO `freelistenbook` VALUES (19, 3, '', '', '2018-06-15 14:31:53', '待处理', '', 13, 0, 0);
INSERT INTO `freelistenbook` VALUES (20, 3, '', '', '2018-06-15 15:14:09', '已处理', '', 14, 0, 0);
INSERT INTO `freelistenbook` VALUES (21, 3, 'xx', '123', '2018-06-19 12:59:15', '已处理', 'xx', 15, 0, 0);
INSERT INTO `freelistenbook` VALUES (22, 3, '费园园', '15940471397', '2018-06-21 18:54:26', '已处理', '明天上午', 16, 0, 0);
INSERT INTO `freelistenbook` VALUES (23, 3, '', '', '2018-06-22 13:29:45', '待处理', '', 17, 0, 0);
INSERT INTO `freelistenbook` VALUES (24, 3, '费园园', '15940471397', '2018-05-26 17:54:50', '待处理', '嘻嘻嘻', 1, NULL, NULL);
INSERT INTO `freelistenbook` VALUES (32, 6, NULL, '1231', '2018-07-16 09:53:25', '待处理', '1213', 1, 0, 0);
INSERT INTO `freelistenbook` VALUES (33, 1, NULL, '1231431513', '2018-07-17 08:11:46', '待处理', 'wefwefwfwg', 17, 0, 0);

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `lname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lprice` double DEFAULT NULL,
  `ldesc` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `pubtime` datetime(0) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, '体验课:万圣节抽符', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'HTML5', 1, '2018-06-05 10:39:24', 0);
INSERT INTO `lesson` VALUES (2, '体验课:手机天气通', 'lesson.jpg', 0.02, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'HTML5', 1, '2018-06-04 10:39:29', 0);
INSERT INTO `lesson` VALUES (3, '体验课:微信摇一摇', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'JAVA', 1, '2018-06-11 10:39:33', 0);
INSERT INTO `lesson` VALUES (4, '体验课:音乐盒子', 'lesson.jpg', 0.03, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'HTML5', 1, '2018-06-07 10:39:37', 0);
INSERT INTO `lesson` VALUES (5, '大数据课程', 'lesson.jpg', 0.01, ' 本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'C++', 1, '2018-06-08 10:39:40', 0);
INSERT INTO `lesson` VALUES (6, '体验课:万圣节抽符6', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'C++', 1, '2018-06-13 10:39:44', 0);
INSERT INTO `lesson` VALUES (7, '体验课:万圣节抽符7', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'HTML5', 1, '2018-06-11 10:39:47', 0);
INSERT INTO `lesson` VALUES (8, '体验课:万圣节抽符8', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'Python', 1, '2018-06-02 10:39:52', 0);
INSERT INTO `lesson` VALUES (9, '体验课:万圣节抽符9', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'HTML5', 1, '2018-06-11 10:39:56', 1);
INSERT INTO `lesson` VALUES (10, '体验课:万圣节抽符10', 'lesson.jpg', 0.01, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'Python', 1, '2018-06-15 10:39:59', 1);
INSERT INTO `lesson` VALUES (15, 'Java与大数据', 'java.jpg', 20, '本课程的学习方向为Java与大数据方向，将在恒大名都、实训中心开课，目前正火热招生中……', NULL, 'JAVA', 1, '2018-07-11 01:15:53', 0);
INSERT INTO `lesson` VALUES (16, 'C++入门与实战', 'c.jpg', 50, '本课程的学习方向为C++应用开发方向，将在恒大名都开课，目前正火热招生中……', NULL, 'C++', 1, '2018-07-10 01:15:58', 0);
INSERT INTO `lesson` VALUES (17, 'Python与深度学习', 'python.jpg', 100, '本课程的学习方向为Python与深度学习方向，将在恒大名都开课，目前正火热进行中……', NULL, 'Python', 1, '2018-07-08 01:16:01', 0);

-- ----------------------------
-- Table structure for lessonbranch
-- ----------------------------
DROP TABLE IF EXISTS `lessonbranch`;
CREATE TABLE `lessonbranch`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `branchid` int(255) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lessonbranch
-- ----------------------------
INSERT INTO `lessonbranch` VALUES (1, 1, 1, 0);
INSERT INTO `lessonbranch` VALUES (2, 2, 1, 0);
INSERT INTO `lessonbranch` VALUES (4, 4, 2, 0);
INSERT INTO `lessonbranch` VALUES (5, 5, 2, 0);
INSERT INTO `lessonbranch` VALUES (6, 6, 2, 0);
INSERT INTO `lessonbranch` VALUES (7, 7, 3, 0);
INSERT INTO `lessonbranch` VALUES (8, 8, 3, 0);
INSERT INTO `lessonbranch` VALUES (9, 9, 3, 0);
INSERT INTO `lessonbranch` VALUES (10, 10, 3, 0);
INSERT INTO `lessonbranch` VALUES (11, 3, 1, 0);
INSERT INTO `lessonbranch` VALUES (15, 15, 1, 0);
INSERT INTO `lessonbranch` VALUES (16, 16, 1, 0);
INSERT INTO `lessonbranch` VALUES (17, 17, 1, 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mtime` datetime(0) DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (2, '2', '2018-06-19 15:49:56', 1, 0);
INSERT INTO `message` VALUES (3, '3', '2018-07-12 11:15:23', 1, 0);
INSERT INTO `message` VALUES (4, '4', '2018-07-12 11:15:25', 1, 0);
INSERT INTO `message` VALUES (5, '5', '2018-07-27 11:15:27', 1, 0);
INSERT INTO `message` VALUES (6, '6', '2018-07-04 11:15:30', 1, 0);
INSERT INTO `message` VALUES (7, '7', '2018-07-09 11:15:33', 1, 0);

-- ----------------------------
-- Table structure for messageimg
-- ----------------------------
DROP TABLE IF EXISTS `messageimg`;
CREATE TABLE `messageimg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messageimg
-- ----------------------------
INSERT INTO `messageimg` VALUES (5, 2, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (6, 2, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (7, 3, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (8, 3, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (9, 3, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (10, 4, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (11, 4, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (12, 5, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (13, 4, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (14, 5, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (15, 5, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (16, 6, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (17, 6, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (18, 6, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (19, 6, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (20, 6, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (21, 6, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (22, 6, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (23, 6, 'moment1.jpg', 0);
INSERT INTO `messageimg` VALUES (24, 7, 'moment2.jpg', 0);
INSERT INTO `messageimg` VALUES (25, NULL, 'moment1.jpg', 0);

-- ----------------------------
-- Table structure for messagelike
-- ----------------------------
DROP TABLE IF EXISTS `messagelike`;
CREATE TABLE `messagelike`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `stime` datetime(0) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messagelike
-- ----------------------------
INSERT INTO `messagelike` VALUES (7, 2, 1, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (8, 3, 2, '2018-07-12 11:24:29', 0);
INSERT INTO `messagelike` VALUES (9, 4, 3, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (10, 5, 4, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (11, 6, 5, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (12, 6, 6, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (13, 7, 3, '2018-06-21 19:00:12', 0);
INSERT INTO `messagelike` VALUES (27, 7, 1, '2018-07-16 00:23:02', 1);
INSERT INTO `messagelike` VALUES (28, 7, 1, '2018-07-16 00:31:02', 1);
INSERT INTO `messagelike` VALUES (29, 7, 1, '2018-07-16 00:32:25', 1);
INSERT INTO `messagelike` VALUES (30, 7, 1, '2018-07-16 00:37:10', 1);
INSERT INTO `messagelike` VALUES (31, 7, 1, '2018-07-16 00:37:30', 1);
INSERT INTO `messagelike` VALUES (32, 7, 1, '2018-07-16 00:38:00', 1);
INSERT INTO `messagelike` VALUES (33, 7, 1, '2018-07-16 00:38:31', 1);
INSERT INTO `messagelike` VALUES (34, 7, 1, '2018-07-16 00:38:52', 1);
INSERT INTO `messagelike` VALUES (35, 7, 1, '2018-07-16 00:39:46', 1);
INSERT INTO `messagelike` VALUES (36, 7, 1, '2018-07-16 00:43:12', 1);
INSERT INTO `messagelike` VALUES (37, 7, 1, '2018-07-16 00:43:39', 1);
INSERT INTO `messagelike` VALUES (38, 7, 1, '2018-07-16 00:43:53', 1);
INSERT INTO `messagelike` VALUES (39, 6, 1, '2018-07-16 01:00:51', 1);
INSERT INTO `messagelike` VALUES (40, 4, 1, '2018-07-16 01:01:45', 1);
INSERT INTO `messagelike` VALUES (41, 4, 1, '2018-07-16 01:01:48', 1);
INSERT INTO `messagelike` VALUES (42, 4, 1, '2018-07-16 01:01:55', 1);
INSERT INTO `messagelike` VALUES (43, 6, 1, '2018-07-16 01:01:59', 1);
INSERT INTO `messagelike` VALUES (44, 7, 1, '2018-07-16 01:02:02', 1);
INSERT INTO `messagelike` VALUES (45, 6, 1, '2018-07-16 10:32:39', 1);
INSERT INTO `messagelike` VALUES (46, 6, 1, '2018-07-16 10:33:14', 1);
INSERT INTO `messagelike` VALUES (47, 6, 1, '2018-07-16 10:33:17', 1);
INSERT INTO `messagelike` VALUES (48, 6, 1, '2018-07-16 10:49:57', 1);
INSERT INTO `messagelike` VALUES (49, 7, 1, '2018-07-16 11:01:09', 1);
INSERT INTO `messagelike` VALUES (50, 7, 1, '2018-07-16 11:01:11', 1);
INSERT INTO `messagelike` VALUES (51, 7, 1, '2018-07-16 11:01:16', 1);
INSERT INTO `messagelike` VALUES (52, 7, 1, '2018-07-16 11:01:18', 1);
INSERT INTO `messagelike` VALUES (53, 6, 1, '2018-07-16 11:01:21', 1);
INSERT INTO `messagelike` VALUES (54, 6, 1, '2018-07-16 11:01:22', 1);
INSERT INTO `messagelike` VALUES (55, 6, 1, '2018-07-16 11:01:23', 1);
INSERT INTO `messagelike` VALUES (56, 4, 1, '2018-07-16 11:01:37', 1);
INSERT INTO `messagelike` VALUES (57, 7, 1, '2018-07-16 11:03:26', 1);
INSERT INTO `messagelike` VALUES (58, 7, 1, '2018-07-16 11:04:18', 1);
INSERT INTO `messagelike` VALUES (59, 7, 1, '2018-07-16 11:04:29', 1);
INSERT INTO `messagelike` VALUES (60, 7, 1, '2018-07-16 11:04:33', 1);
INSERT INTO `messagelike` VALUES (61, 7, 1, '2018-07-16 11:04:35', 1);
INSERT INTO `messagelike` VALUES (62, 6, 1, '2018-07-16 11:04:38', 1);
INSERT INTO `messagelike` VALUES (63, 5, 1, '2018-07-16 11:11:29', 1);
INSERT INTO `messagelike` VALUES (64, 7, 1, '2018-07-16 11:12:19', 1);
INSERT INTO `messagelike` VALUES (65, 7, 1, '2018-07-16 11:12:43', 1);
INSERT INTO `messagelike` VALUES (66, 7, 1, '2018-07-16 11:13:21', 1);
INSERT INTO `messagelike` VALUES (67, 7, 1, '2018-07-16 11:15:43', 1);
INSERT INTO `messagelike` VALUES (68, 7, 1, '2018-07-16 11:15:46', 1);
INSERT INTO `messagelike` VALUES (69, 7, 1, '2018-07-16 11:15:47', 1);
INSERT INTO `messagelike` VALUES (70, 7, 1, '2018-07-16 11:15:49', 1);
INSERT INTO `messagelike` VALUES (71, 7, 1, '2018-07-16 13:04:26', 1);
INSERT INTO `messagelike` VALUES (72, 7, 1, '2018-07-16 13:04:27', 1);
INSERT INTO `messagelike` VALUES (73, 7, 1, '2018-07-16 13:05:12', 1);
INSERT INTO `messagelike` VALUES (74, 4, 1, '2018-07-16 13:05:24', 0);
INSERT INTO `messagelike` VALUES (75, 7, 1, '2018-07-17 09:08:46', 1);
INSERT INTO `messagelike` VALUES (76, 7, 1, '2018-07-17 09:08:49', 1);
INSERT INTO `messagelike` VALUES (77, 7, 1, '2018-07-17 09:13:33', 1);
INSERT INTO `messagelike` VALUES (78, 7, 1, '2018-07-17 09:36:25', 1);
INSERT INTO `messagelike` VALUES (79, 6, 1, '2018-07-17 09:36:34', 0);
INSERT INTO `messagelike` VALUES (80, 7, 1, '2018-07-17 09:54:35', 1);
INSERT INTO `messagelike` VALUES (81, 7, 1, '2018-07-17 10:48:21', 0);
INSERT INTO `messagelike` VALUES (82, 5, 1, '2018-07-17 22:07:14', 1);

-- ----------------------------
-- Table structure for messagereply
-- ----------------------------
DROP TABLE IF EXISTS `messagereply`;
CREATE TABLE `messagereply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `stime` datetime(0) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messagereply
-- ----------------------------
INSERT INTO `messagereply` VALUES (1, 2, '哈哈', 1, '2018-06-21 18:27:26', 0);
INSERT INTO `messagereply` VALUES (2, 2, '嘻嘻', 1, '2018-07-12 11:26:41', 0);
INSERT INTO `messagereply` VALUES (3, 3, '乐乐乐', 2, '2018-07-12 11:26:43', 0);
INSERT INTO `messagereply` VALUES (4, 3, '天', 3, '2018-07-12 11:26:47', 0);
INSERT INTO `messagereply` VALUES (5, 4, '开心呢', 4, '2018-07-12 11:26:50', 0);
INSERT INTO `messagereply` VALUES (6, 5, '开心呀', 5, '2018-07-12 11:26:52', 0);
INSERT INTO `messagereply` VALUES (7, 6, 'GG了', 6, '2018-07-12 11:26:56', 0);
INSERT INTO `messagereply` VALUES (8, 3, '写不完了要GG ', 3, '2018-07-12 11:26:57', 0);
INSERT INTO `messagereply` VALUES (9, 6, '啊实打实的', 1, '2018-07-16 00:59:26', 0);
INSERT INTO `messagereply` VALUES (11, 7, '啊实打实', 1, '2018-07-16 01:51:27', 0);
INSERT INTO `messagereply` VALUES (12, 7, 'asdas', 1, '2018-07-17 09:13:22', 0);
INSERT INTO `messagereply` VALUES (13, 7, 'hhhh', 1, '2018-07-17 09:13:37', 0);
INSERT INTO `messagereply` VALUES (14, 7, '', 1, '2018-07-17 09:48:28', 0);
INSERT INTO `messagereply` VALUES (15, 7, 'asdasd', 1, '2018-07-17 10:48:34', 0);

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL,
  `refundreason` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `refundtime` datetime(0) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  `userdeletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of refund
-- ----------------------------
INSERT INTO `refund` VALUES (1, 0, NULL, '2018-07-12 22:18:43', '待处理', NULL, NULL);
INSERT INTO `refund` VALUES (2, 43, '', '2018-05-26 12:28:10', '待处理', 0, 0);
INSERT INTO `refund` VALUES (3, 44, '', '2018-05-26 12:29:26', '待处理', 0, 0);
INSERT INTO `refund` VALUES (4, 45, '', '2018-05-26 12:32:20', '待处理', 0, 0);
INSERT INTO `refund` VALUES (5, 46, '做', '2018-05-26 12:40:15', '待处理', 0, 0);
INSERT INTO `refund` VALUES (6, 50, '111', '2018-05-26 14:22:38', '待处理', 0, 0);
INSERT INTO `refund` VALUES (7, 54, '', '2018-05-26 14:21:51', '待处理', 0, 0);
INSERT INTO `refund` VALUES (8, 55, '', '2018-05-26 14:20:43', '待处理', 0, 0);
INSERT INTO `refund` VALUES (9, 63, '', '2018-06-15 11:33:18', '待处理', 0, 0);
INSERT INTO `refund` VALUES (10, 69, '', '2018-06-19 14:38:27', '待处理', 1, 1);
INSERT INTO `refund` VALUES (11, 70, '', '2018-06-19 14:37:24', '已处理', 0, 0);
INSERT INTO `refund` VALUES (12, 71, '', '2018-06-19 16:53:55', '已处理', 0, 0);
INSERT INTO `refund` VALUES (16, 82, NULL, '2018-07-15 00:17:24', '待处理', 1, 1);
INSERT INTO `refund` VALUES (17, 93, NULL, '2018-07-17 09:26:16', '待处理', 1, 1);
INSERT INTO `refund` VALUES (18, 94, NULL, '2018-07-17 10:43:02', '待处理', 1, 1);
INSERT INTO `refund` VALUES (19, 94, NULL, '2018-07-17 10:44:17', '待处理', 0, 0);

-- ----------------------------
-- Table structure for sorder
-- ----------------------------
DROP TABLE IF EXISTS `sorder`;
CREATE TABLE `sorder`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `cid` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `actual` double DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ordertime` datetime(0) DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `transactionid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  `userdeletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sorder
-- ----------------------------
INSERT INTO `sorder` VALUES (43, 1, NULL, 1, 5, NULL, 5, '已退款', '2018-05-26 12:27:24', 1, '4200000116201805263247934519', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (44, 1, NULL, 1, 5, NULL, 5, '已退款', '2018-05-26 12:27:43', 1, '4200000110201805261060715976', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (45, 1, NULL, 1, 5, NULL, 5, '已退款', '2018-05-26 12:31:02', 1, '4200000130201805260683103431', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (46, 1, NULL, 1, 5, NULL, 5, '已退款', '2018-05-26 12:39:42', 1, '4200000109201805266997780435', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (47, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-05-26 13:57:41', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (48, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-05-26 14:03:30', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (49, 1, NULL, 1, 0.01, NULL, 0.01, '已付款', '2018-05-26 14:04:54', 1, '4200000110201805264729572488', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (50, 2, NULL, 1, 0.02, NULL, 0.02, '退款中', '2018-05-26 14:05:22', 1, '4200000130201805266329195070', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (51, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-05-26 14:10:43', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (52, 2, NULL, 1, 0.02, NULL, 0.02, '待付款', '2018-05-26 14:10:50', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (53, 4, NULL, 1, 0.03, NULL, 0.03, '待付款', '2018-05-26 14:11:35', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (54, 2, NULL, 1, 0.02, NULL, 0.02, '退款中', '2018-05-26 14:19:35', 1, '4200000110201805263169394125', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (55, 4, NULL, 1, 0.03, NULL, 0.03, '退款中', '2018-05-26 14:20:15', 1, '4200000138201805262717162733', NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (56, 1, NULL, 1, 0.01, NULL, 0.01, '已付款', '2018-06-07 16:46:55', 1, '4200000124201806070328955434', '费园园', '15940471397', 0, 0);
INSERT INTO `sorder` VALUES (57, 1, NULL, 1, 0.02, NULL, 0.02, '已付款', '2018-06-07 17:36:42', 1, '4200000126201806078388299831', '哈哈', '123', 0, 0);
INSERT INTO `sorder` VALUES (58, 1, NULL, 1, 100, NULL, 100, '待付款', '2018-06-07 18:38:05', 1, NULL, '', '', 0, 0);
INSERT INTO `sorder` VALUES (59, 2, NULL, 1, 0.02, NULL, 0.02, '待付款', '2018-06-07 18:39:49', 1, NULL, '', '', 0, 0);
INSERT INTO `sorder` VALUES (63, 1, NULL, 1, 0.01, NULL, 0.01, '退款中', '2018-06-15 11:31:05', 1, '4200000117201806150052450136', '费园园', '15940471397', 0, 0);
INSERT INTO `sorder` VALUES (64, 1, NULL, 1, 0.01, NULL, 0.01, '已付款', '2018-06-15 11:31:18', 1, '4200000137201806155057560390', '费园园', '', 0, 0);
INSERT INTO `sorder` VALUES (65, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-06-15 14:03:47', 1, NULL, '', '', 0, 0);
INSERT INTO `sorder` VALUES (66, 1, NULL, 1, -99.98, NULL, -99.98, '待付款', '2018-06-15 14:51:50', 1, NULL, '', '', 0, 0);
INSERT INTO `sorder` VALUES (67, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-06-19 13:02:37', 1, NULL, 'aa', '123', 0, 0);
INSERT INTO `sorder` VALUES (68, 1, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-06-19 13:03:49', 1, NULL, 'www', '123', 0, 0);
INSERT INTO `sorder` VALUES (69, 10, NULL, 1, 0.01, NULL, 0.01, '退款中', '2018-06-19 14:27:56', 1, '4200000112201806197932997460', '费元', '', 0, 0);
INSERT INTO `sorder` VALUES (70, 6, NULL, 1, 0.01, NULL, 0.01, '已退款', '2018-06-19 14:36:52', 1, '4200000136201806193206770784', '给', '', 0, 0);
INSERT INTO `sorder` VALUES (71, 6, NULL, 1, 0.01, NULL, 0.01, '已退款', '2018-06-19 16:53:29', 1, '4200000118201806199959334942', '费园', '', 0, 0);
INSERT INTO `sorder` VALUES (72, 10, NULL, 1, 0.01, NULL, 0.01, '已退款', '2018-06-21 18:55:55', 1, '4200000133201806213004102392', '费园园', '', 0, 0);
INSERT INTO `sorder` VALUES (73, 10, NULL, 1, 0.01, NULL, 0.01, '待付款', '2018-06-22 09:10:34', 1, NULL, '费园园', '15940471397', 0, 1);
INSERT INTO `sorder` VALUES (76, 17, 0, 1, 100, NULL, 0, '已付款', '2018-07-13 14:35:40', 0, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (82, 16, 1, 1, 50, NULL, 0, '已付款', '2018-07-14 23:32:22', 0, NULL, '11', '11', 0, 0);
INSERT INTO `sorder` VALUES (83, 16, 1, 1, 50, NULL, 0, '已付款', '2018-07-15 00:18:16', 0, NULL, '11', 'qq', 0, 0);
INSERT INTO `sorder` VALUES (84, 16, 1, 1, 50, NULL, 0, '待付款', '2018-07-16 11:22:03', 0, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (85, 8, 3, 1, 0.01, NULL, 0, '待付款', '2018-07-17 08:01:13', 0, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (86, 8, 3, 1, 0.01, NULL, 0, '待付款', '2018-07-17 08:01:20', 0, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (87, 17, 1, 17, 100, NULL, 0, '待付款', '2018-07-17 08:10:29', 1, NULL, NULL, NULL, 1, 1);
INSERT INTO `sorder` VALUES (88, 17, 1, 1, 100, NULL, 100, '已付款', '2018-07-17 08:11:04', 0, NULL, 'Andrew', '15804032497', 0, 0);
INSERT INTO `sorder` VALUES (89, 17, 1, 1, 100, NULL, 100, '已付款', '2018-07-17 08:11:17', 0, NULL, 'Andrew', '15804032497', 0, 0);
INSERT INTO `sorder` VALUES (90, 16, 1, 1, 50, NULL, 50, '已付款', '2018-07-17 08:12:23', 0, NULL, 'wew2', '3411511135125135', 0, 0);
INSERT INTO `sorder` VALUES (91, 17, 1, 17, 100, NULL, 0, '待付款', '2018-07-17 08:24:13', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (92, 16, 1, 1, 50, NULL, 0, '待付款', '2018-07-17 09:14:25', 1, NULL, NULL, NULL, 0, 0);
INSERT INTO `sorder` VALUES (93, 17, 1, 1, 100, NULL, 0, '已付款', '2018-07-17 09:25:25', 1, NULL, '123', '123', 0, 0);
INSERT INTO `sorder` VALUES (94, 16, 1, 1, 50, NULL, 0, '退款中', '2018-07-17 10:42:03', 1, NULL, 'asdas', 'asdasd', 0, 0);

-- ----------------------------
-- Table structure for swiper
-- ----------------------------
DROP TABLE IF EXISTS `swiper`;
CREATE TABLE `swiper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of swiper
-- ----------------------------
INSERT INTO `swiper` VALUES (1, 1, 'office.jpg', 'A', 0);
INSERT INTO `swiper` VALUES (2, 1, 'logo.jpg', 'A', 0);
INSERT INTO `swiper` VALUES (3, 1, 'garden.jpg', 'A', 0);
INSERT INTO `swiper` VALUES (4, 1, 'building.jpg', 'A', 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tphoto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '费园园', 'teacher4.jpg', '主要从事HTML5、Java开源领域及Android移动开发，在东软集团担任过6年的软件工程师，2年半的HTML5、JAVA培训讲师，承担过东北大学，北交大等重点高校培训项目。', 1, 1);
INSERT INTO `teacher` VALUES (2, '陈伟', 'teacher2.jpg', '有30多年的软件项目开发经验，有10多年的教学经验，熟悉.NET、C#、F#、C语言、Java等编程语言等，擅长C#、Java语言课程。为Microsoft、Intel、AutoDisk、南京大学、东北大学等学校和企业授过课。', 1, 1);
INSERT INTO `teacher` VALUES (3, '宋波', 'teacher3.jpg', ' 12年开发经验，2年教学经验,精通JAVA、C、C++、C#、PHP等多种编程语言，熟悉ios，android移动平台APP开发，语言功底扎实浑厚，精通数据结构和算法，积累多套软件开发框架。', 1, 1);
INSERT INTO `teacher` VALUES (4, '任涛', 'rentao.jpg', '任涛，教授，博士生导师，系主任，辽宁省百千万人才，沈阳市优秀研究生导师。 \r\n											于2003年毕业于东北大学自动化专业，获工学学士学位；2005年毕业于东北大学控制理论与控制工程专业，获工学硕士学位.', 1, 0);
INSERT INTO `teacher` VALUES (5, '郭贵冰', 'guoguibing.jpg', '2015年11月，以引进人才的方式加入东北大学软件学院，任职副教授。\r\n											师从新加坡南洋理工大学(NTU)的Assoc. Prof. Jie Zhang和Prof. Daniel Thalmann，2015年7月获得博士学位；\r\n											2015年1~10月，在新加坡管理大学(SMU)朱飞达教授的实验室担任研究员。', 1, 0);
INSERT INTO `teacher` VALUES (6, '宋杰', 'songjie.jpg', '主研方向：大数据存储与管理，高能效计算，机器学习应用；\r\n											Big data management, Energy Efficient computing, Machine learning  and its application；<br />\r\n											辅研方向：云计算框架及其应用；Cloud computing framework and its applications.', 1, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL,
  `qid` int(11) DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `openid` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, '18622351756', '费园园', 'aa', 'portrait.jpg', 'oC9yV5HNntbgqDyPEg2J0YSY8dC8', 0);
INSERT INTO `user` VALUES (2, 1, NULL, '特朗普', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (3, 1, NULL, '梅西', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (4, 1, NULL, 'C罗', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (5, 1, NULL, '伊瓜因', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (6, 1, NULL, '内马尔', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (10, NULL, NULL, ' ', ' ', NULL, NULL, NULL);
INSERT INTO `user` VALUES (17, 1, '15940471397', '186****1756', 'leslie', 'portrait.jpg', '', 0);

-- ----------------------------
-- Table structure for weixin
-- ----------------------------
DROP TABLE IF EXISTS `weixin`;
CREATE TABLE `weixin`  (
  `qid` int(11) NOT NULL,
  `appid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appsecret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `partner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `partnerkey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `weixinpaycallback` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletekey` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`qid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weixin
-- ----------------------------
INSERT INTO `weixin` VALUES (1, NULL, NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
