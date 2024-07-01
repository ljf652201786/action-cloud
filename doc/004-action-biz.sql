/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : action-biz

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 28/06/2024 17:16:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_test
-- ----------------------------
DROP TABLE IF EXISTS `biz_test`;
CREATE TABLE `biz_test`  (
  `id` bigint(20) NOT NULL COMMENT '注解id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_test
-- ----------------------------
INSERT INTO `biz_test` VALUES (1, '张三');
INSERT INTO `biz_test` VALUES (2, '王五');

SET FOREIGN_KEY_CHECKS = 1;
