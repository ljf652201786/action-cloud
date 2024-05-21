/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : action-cloud

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/05/2024 15:56:58
*/
use action-cloud;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_data`;
CREATE TABLE `sys_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父id',
  `data_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 1  启用 0 禁用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1681237501446090754 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data
-- ----------------------------
INSERT INTO `sys_data` VALUES (1681237501446090753, 0, NULL, 'ces1', '', 0, '1', NULL, '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_data_column_limit
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_column_limit`;
CREATE TABLE `sys_data_column_limit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `data_id` bigint(11) NOT NULL COMMENT '数据限制id',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1、用户组 2、岗位 3、角色',
  `contact_id` bigint(20) NOT NULL COMMENT '关联id',
  `limit_field` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限制所属字段',
  `limit_field_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制所属字段描述',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1681218368704770052 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据限制字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_column_limit
-- ----------------------------
INSERT INTO `sys_data_column_limit` VALUES (1681218368704770050, 1669178594389364737, '', 0, '', NULL, '', NULL, '', NULL);
INSERT INTO `sys_data_column_limit` VALUES (1681218368704770051, 1669178594389364737, '', 0, '', NULL, '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_data_row_limit
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_row_limit`;
CREATE TABLE `sys_data_row_limit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `data_id` bigint(11) NOT NULL COMMENT '数据限制id',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1、用户组 2、岗位 3、角色',
  `contact_id` bigint(20) NOT NULL COMMENT '关联id',
  `relation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '衔接关系(默认就是and)',
  `limit_field` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限制所属字段',
  `limit_field_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制所属字段描述',
  `limit_condition` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限制条件（属于(等于)、不属于(不等于)、大于、小于、范围）',
  `val` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条件值',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 1  启用 0 禁用',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据行限制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `dept_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（1正常 0停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1663819787069018115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, 'ys', '禹穗科技', 0, '1', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-01 17:23:47');
INSERT INTO `sys_dept` VALUES (101, 100, 'ys-szzgs', '深圳总公司', 1, '1', 'admin', '2018-03-16 11:33:00', 'admin', '2021-02-25 17:06:08');
INSERT INTO `sys_dept` VALUES (102, 100, 'ys-csfgs', '长沙分公司', 2, '1', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-01 17:23:47');
INSERT INTO `sys_dept` VALUES (103, 101, 'ys-szzgs-yfbm', '研发部门', 1, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (104, 101, 'ys-szzgs-scbm', '市场部门', 2, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (105, 101, 'ys-szzgs-csbm', '测试部门', 3, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (106, 101, 'ys-szzgs-cwbm', '财务部门', 4, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (107, 101, 'ys-szzgs-ywbm', '运维部门', 5, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (108, 102, 'ys-csfgs-scbm', '市场部门', 1, '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (109, 102, 'ys-csfgs-cwbm', '财务部门', 2, '1', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-01 17:23:47');
INSERT INTO `sys_dept` VALUES (1663819787069018114, 0, 'ces', '测试部门', 1, '1', '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '字典排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '字典状态（1正常 0停用）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1664549910374170627 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1663094762737143810, 'sys_user_sex', '性别', 1, '0', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1663094990122946561, 'sys_normal_disable', '系统开关', 1, '0', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1664546371048488961, 'sys_show_hide', '菜单状态', 3, '0', '菜单状态列表', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1664548342585278465, 'whether', '是否', 1, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1664549910374170626, 'sys_enable_disable', '启用禁用', 3, '0', '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `dict_id` bigint(20) NULL DEFAULT NULL COMMENT '字典id',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典详情编码',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `sort` int(32) NULL DEFAULT NULL COMMENT '字典详情排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '字典状态（1正常 0停用）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1664549985406074882 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1663107069294854146, 1663094762737143810, 'sys_user_sex', '男', '0', 0, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1663107090962628609, 1663094762737143810, 'sys_user_sex', '女', '1', 1, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1663108753551179778, 1663094990122946561, 'sys_normal_disable', '正常', '0', 1, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1663108789911601153, 1663094990122946561, 'sys_normal_disable', '停用', '1', 0, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664547635496927233, 1664546371048488961, 'sys_show_hide', '显示', '0', 0, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664547668095057921, 1664546371048488961, 'sys_show_hide', '隐藏', '1', 1, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664548402618351617, 1664548342585278465, 'whether', '是', '1', 0, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664548424994963458, 1664548342585278465, 'whether', '否', '0', 1, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664549953634222081, 1664549910374170626, 'sys_enable_disable', '禁用', '0', 1, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (1664549985406074881, 1664549910374170626, 'sys_enable_disable', '启用', '1', 0, '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `file_original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原始文件名',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自动生成的文件名',
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件储存路径',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件访问地址',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小，单位：kb',
  `upload_user_id` bigint(20) NULL DEFAULT NULL COMMENT '上传人id',
  `upload_user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传人名字',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（1代表存在 0代表删除）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1788085059450974211 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '调研概要v2.1.docx', '3916a8f8-1ddf-4d0e-a24d-2972772fa72b', 'docx', 'D:/platform/uploadPath\\3916a8f8-1ddf-4d0e-a24d-2972772fa72b', 'http://localhost:9300/statics3916a8f8-1ddf-4d0e-a24d-2972772fa72b', 2841044, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (2, '应急管理平台建设方案(20190907133619).pdf', 'a6872741-a74b-4281-8ae6-c59a2d68430f', 'pdf', 'D:/platform/uploadPath\\a6872741-a74b-4281-8ae6-c59a2d68430f', 'http://localhost:9300/staticsa6872741-a74b-4281-8ae6-c59a2d68430f', 39320212, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (3, '广州市外网协同办公平台可复用可共用接入指引.pdf', '38af4210-11d5-45e0-a381-9cd61a0cbe76', 'pdf', 'D:/platform/uploadPath\\38af4210-11d5-45e0-a381-9cd61a0cbe76', 'http://localhost:9300/statics38af4210-11d5-45e0-a381-9cd61a0cbe76', 826904915, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (4, '应急管理平台建设方案(20190907133619).pdf', '80acd290-caa6-4ecb-98cf-551b6a71aa13', 'pdf', 'D:/platform/uploadPath\\80acd290-caa6-4ecb-98cf-551b6a71aa13', 'http://localhost:9300/statics\\80acd290-caa6-4ecb-98cf-551b6a71aa13', 39320212, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (5, '应急管理平台建设方案(20190907133619).pdf', '27aece4e-337a-4383-a67e-0d81202e664b', 'pdf', 'D:/platform/uploadPath\\27aece4e-337a-4383-a67e-0d81202e664b', 'http://localhost:9300/statics\\27aece4e-337a-4383-a67e-0d81202e664b', 39320212, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (6, 'img11.jpg', 'd2d53b8c-3e4a-4631-bfe6-fe6936591c12', 'jpg', 'D:/platform/uploadPath\\d2d53b8c-3e4a-4631-bfe6-fe6936591c12', 'http://localhost:9300D:/platform/uploadPath\\d2d53b8c-3e4a-4631-bfe6-fe6936591c12', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (7, 'img11.jpg', 'e578985c-3d29-4944-9807-ee85a28a7a7c', 'jpg', 'D:/platform/uploadPath\\e578985c-3d29-4944-9807-ee85a28a7a7c', 'http://localhost:9300D:/platform/uploadPath\\e578985c-3d29-4944-9807-ee85a28a7a7c', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (8, 'img11.jpg', '174cae4b-5368-483c-a054-0baef202077e', 'jpg', 'D:/platform/uploadPath\\174cae4b-5368-483c-a054-0baef202077e', 'http://localhost:9300D:/platform/uploadPath\\174cae4b-5368-483c-a054-0baef202077e', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (9, 'img11.jpg', '3c9ca272-59ca-4fa8-8b44-bc8589f13c9d', 'jpg', 'D:/platform/uploadPath\\3c9ca272-59ca-4fa8-8b44-bc8589f13c9d', 'http://localhost:9300D:/platform/uploadPath\\3c9ca272-59ca-4fa8-8b44-bc8589f13c9d', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (10, 'img11.jpg', 'a813ad95-8dc2-4659-9c62-422c84ce6192', 'jpg', 'D:/platform/uploadPath\\a813ad95-8dc2-4659-9c62-422c84ce6192', 'http://localhost:9300D:/platform/uploadPath\\a813ad95-8dc2-4659-9c62-422c84ce6192', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (11, 'im11g11.jpg', '90a8c869-0428-4088-a7ae-77869cc5b8d3', 'jpg', 'D:/platform/uploadPath\\90a8c869-0428-4088-a7ae-77869cc5b8d3', 'http://localhost:9300D:/platform/uploadPath\\90a8c869-0428-4088-a7ae-77869cc5b8d3', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (12, 'mycat', 'ae0d613e-8255-4374-aacf-2844f9dfd170', 'mycat', 'D:/platform/uploadPath\\ae0d613e-8255-4374-aacf-2844f9dfd170', 'http://localhost:9300D:/platform/uploadPath\\ae0d613e-8255-4374-aacf-2844f9dfd170', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (13, 'cat', 'b3eda1d7-d4b1-4db3-a885-618dfd736c70', 'cat', 'D:/platform/uploadPath\\b3eda1d7-d4b1-4db3-a885-618dfd736c70', 'http://localhost:9300D:/platform/uploadPath\\b3eda1d7-d4b1-4db3-a885-618dfd736c70', 1607095, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (14, 'mycat.jpg', '965437b3-d462-4805-a233-01f2d6867c17', 'jpg', 'D:/platform/uploadPath\\965437b3-d462-4805-a233-01f2d6867c17', 'http://localhost:9300D:/platform/uploadPath\\965437b3-d462-4805-a233-01f2d6867c17', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (15, '1工程.docx', 'cc0d4e39-f40b-43f3-80c7-29d35a531e66', 'docx', 'D:/platform/uploadPath\\cc0d4e39-f40b-43f3-80c7-29d35a531e66', 'http://localhost:9300D:/platform/uploadPath\\cc0d4e39-f40b-43f3-80c7-29d35a531e66', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (16, '1工程.docx', '0ecf65dd-5f71-4217-8cdd-a689b571edbb', 'docx', 'D:/platform/uploadPath\\0ecf65dd-5f71-4217-8cdd-a689b571edbb', 'http://localhost:9300D:/platform/uploadPath\\0ecf65dd-5f71-4217-8cdd-a689b571edbb', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (17, 'im11g11.jpg', 'f93cb23f-c8be-46c6-b5db-76da81de1f93', 'jpg', 'D:/platform/uploadPath\\f93cb23f-c8be-46c6-b5db-76da81de1f93', 'http://192.168.1.188:9300D:/platform/uploadPath\\f93cb23f-c8be-46c6-b5db-76da81de1f93', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (18, 'mycat.jpg', '759ec0d1-a7e5-451a-9c0d-a626ae0c97e0', 'jpg', 'D:/platform/uploadPath\\759ec0d1-a7e5-451a-9c0d-a626ae0c97e0', 'http://192.168.1.188:9300D:/platform/uploadPath\\759ec0d1-a7e5-451a-9c0d-a626ae0c97e0', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (19, 'mycat.jpg', 'e77fe5a9-1146-4478-a891-33db55469a91', 'jpg', 'D:/platform/uploadPath\\e77fe5a9-1146-4478-a891-33db55469a91', 'http://192.168.1.188:9300D:/platform/uploadPath\\e77fe5a9-1146-4478-a891-33db55469a91', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (20, 'im11g11.jpg', '5f845e2d-9e94-45cf-a286-368d943691e8', 'jpg', 'D:/platform/uploadPath\\5f845e2d-9e94-45cf-a286-368d943691e8', 'http://192.168.1.188:9300/statics5f845e2d-9e94-45cf-a286-368d943691e8', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (21, '1工程.docx', '0d3b13ba-d10d-4afb-a754-b7b772ebac20', 'docx', 'D:/platform/uploadPath\\0d3b13ba-d10d-4afb-a754-b7b772ebac20', 'http://192.168.1.188:9300\\/statics0d3b13ba-d10d-4afb-a754-b7b772ebac20', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (22, '05需求规格说明书.zip', 'a1e3b5c2-7335-4d2f-8898-98148945535d', 'zip', 'D:/platform/uploadPath\\a1e3b5c2-7335-4d2f-8898-98148945535d', 'http://192.168.1.188:9300\\/staticsa1e3b5c2-7335-4d2f-8898-98148945535d', 10161968, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (23, 'cat.jpg', '3b0d4d6b-a433-4717-88cb-a343b3d273bc', 'jpg', 'D:/platform/uploadPath\\3b0d4d6b-a433-4717-88cb-a343b3d273bc', 'http://192.168.1.188:9300\\/statics3b0d4d6b-a433-4717-88cb-a343b3d273bc', 1607095, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (24, 'cat.jpg', 'f06ed420-2754-4221-93cb-56360d97c733', 'jpg', 'D:/platform/uploadPath\\f06ed420-2754-4221-93cb-56360d97c733', 'http://192.168.1.188:9300\\/staticsf06ed420-2754-4221-93cb-56360d97c733', 1607095, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (25, 'mycat.jpg', '2abd4c6a-f594-4724-baf0-b89232cc5471', 'jpg', 'D:/platform/uploadPath\\2abd4c6a-f594-4724-baf0-b89232cc5471', 'http://192.168.1.188:9300\\/statics2abd4c6a-f594-4724-baf0-b89232cc5471', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (26, 'cat.jpg', '212b5b25-ff99-4fe7-8cd9-1b417e512253', 'jpg', 'D:/platform/uploadPath\\212b5b25-ff99-4fe7-8cd9-1b417e512253', 'http://192.168.1.188:9300/statics\\212b5b25-ff99-4fe7-8cd9-1b417e512253', 1607095, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (27, 'mycat.jpg', '0d56f91c-7886-44e0-be6a-27cc38e12c04', 'jpg', 'D:/platform/uploadPath\\0d56f91c-7886-44e0-be6a-27cc38e12c04', 'http://192.168.1.188:9300/statics\\0d56f91c-7886-44e0-be6a-27cc38e12c04', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (28, 'mycat.jpg', 'fd98ad8e-dd5a-4ee7-904d-88155c3cdeab', 'jpg', 'D:/platform/uploadPath\\fd98ad8e-dd5a-4ee7-904d-88155c3cdeab', 'http://192.168.1.188:9300/statics\\fd98ad8e-dd5a-4ee7-904d-88155c3cdeab', 68767, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (29, 'cat.jpg', 'eac9222c-5de0-4282-880c-a8b2502931f3', 'jpg', 'D:/platform/uploadPath\\eac9222c-5de0-4282-880c-a8b2502931f3', 'http://192.168.1.188:9300/statics\\eac9222c-5de0-4282-880c-a8b2502931f3', 1607095, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (30, 'avatar.jpg', '90404936-a512-4b91-a87d-c60d01a8e1b7', 'jpg', 'D:/platform/uploadPath\\90404936-a512-4b91-a87d-c60d01a8e1b7', 'http://192.168.1.188:9300/statics\\90404936-a512-4b91-a87d-c60d01a8e1b7', 8106, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (31, 'photo4.jpg', '39b638b0-025c-477d-b958-70b84283d866', 'jpg', 'D:/platform/uploadPath\\39b638b0-025c-477d-b958-70b84283d866', 'http://192.168.1.188:9300/statics\\39b638b0-025c-477d-b958-70b84283d866', 53923, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (32, '05需求规格说明书.zip', '9e1b66af-34b5-41a8-83cc-0875390f9ad3', 'zip', 'D:/platform/uploadPath\\9e1b66af-34b5-41a8-83cc-0875390f9ad3', 'http://192.168.1.188:9300/statics\\9e1b66af-34b5-41a8-83cc-0875390f9ad3', 10161968, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (33, '1工程.docx', 'ab58e9eb-9348-40d5-91bc-656410a439dd', 'docx', 'D:/platform/uploadPath\\ab58e9eb-9348-40d5-91bc-656410a439dd', 'http://192.168.1.188:9300/statics\\ab58e9eb-9348-40d5-91bc-656410a439dd', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (34, '1.png', 'bc30728b-b63e-4d35-9b85-e865a068960d', 'png', 'D:/platform/uploadPath\\bc30728b-b63e-4d35-9b85-e865a068960d', 'http://192.168.1.188:9300/statics\\bc30728b-b63e-4d35-9b85-e865a068960d', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (35, '1.png', '58962f4b-5416-425a-9016-684044ea9b05', 'png', 'D:/platform/uploadPath\\58962f4b-5416-425a-9016-684044ea9b05', 'http://192.168.1.188:9300/statics\\58962f4b-5416-425a-9016-684044ea9b05', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (36, '1工程.docx', '15363d62-9403-4443-ac90-0c3f743d4157', 'docx', 'D:/platform/uploadPath\\15363d62-9403-4443-ac90-0c3f743d4157', 'http://192.168.1.188:9300/statics\\15363d62-9403-4443-ac90-0c3f743d4157', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (37, '1.png', '5650e898-50f2-4b53-b678-bc7940c2b3a0', 'png', 'D:/platform/uploadPath\\5650e898-50f2-4b53-b678-bc7940c2b3a0', 'http://192.168.1.188:9300/statics\\5650e898-50f2-4b53-b678-bc7940c2b3a0', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (38, '1.png', 'a26e746a-a91e-4dea-a0e0-f1121938468b', 'png', 'D:/platform/uploadPath\\a26e746a-a91e-4dea-a0e0-f1121938468b', 'http://192.168.1.188:9300/statics\\a26e746a-a91e-4dea-a0e0-f1121938468b', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (39, '1工程.docx', '9a387d6a-7ac1-4897-ac62-86bd3e7084a9', 'docx', 'D:/platform/uploadPath\\9a387d6a-7ac1-4897-ac62-86bd3e7084a9', 'http://192.168.1.188:9300/statics\\9a387d6a-7ac1-4897-ac62-86bd3e7084a9', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (40, '1.png', 'cc9493ef-31ff-47b7-8415-2f9d36e9d2a3', 'png', 'D:/platform/uploadPath\\cc9493ef-31ff-47b7-8415-2f9d36e9d2a3', 'http://192.168.1.188:9300/statics\\cc9493ef-31ff-47b7-8415-2f9d36e9d2a3', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (41, '1工程.docx', '93bb64ac-c4d0-4201-9e31-7d802ae04638', 'docx', 'D:/platform/uploadPath\\93bb64ac-c4d0-4201-9e31-7d802ae04638', 'http://192.168.1.188:9300/statics\\93bb64ac-c4d0-4201-9e31-7d802ae04638', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (42, '1.png', '0ca56fc7-8f70-48ef-aee5-e38b0d164797', 'png', 'D:/platform/uploadPath\\0ca56fc7-8f70-48ef-aee5-e38b0d164797', 'http://192.168.1.188:9300/statics\\0ca56fc7-8f70-48ef-aee5-e38b0d164797', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (43, '1工程.docx', 'ec65a5a4-2faf-4eaa-a206-e164869a302a', 'docx', 'D:/platform/uploadPath\\ec65a5a4-2faf-4eaa-a206-e164869a302a', 'http://192.168.1.188:9300/statics\\ec65a5a4-2faf-4eaa-a206-e164869a302a', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (44, '1.png', '287038f6-d26c-40b3-bedc-dc9b8c2a0b5f', 'png', 'D:/platform/uploadPath\\287038f6-d26c-40b3-bedc-dc9b8c2a0b5f', '/statics\\287038f6-d26c-40b3-bedc-dc9b8c2a0b5f', 59284, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (45, '1工程.docx', 'b11455dc-1bea-443b-8cc8-5411cae5e6a6', 'docx', 'D:/platform/uploadPath\\b11455dc-1bea-443b-8cc8-5411cae5e6a6', '/statics\\b11455dc-1bea-443b-8cc8-5411cae5e6a6', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (46, '05需求规格说明书.zip', '653ded74-fcf4-4f92-bac6-eee5eebc787e', 'zip', 'D:/platform/uploadPath\\653ded74-fcf4-4f92-bac6-eee5eebc787e', '/statics\\653ded74-fcf4-4f92-bac6-eee5eebc787e', 10161968, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (47, '05需求规格说明书.zip', '0b825e03-d075-4186-bbd8-2ac68be5277f', 'zip', 'D:/platform/uploadPath\\0b825e03-d075-4186-bbd8-2ac68be5277f', '/statics\\0b825e03-d075-4186-bbd8-2ac68be5277f', 10161968, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (48, '2养护.docx', '85c92bd6-d77f-4dc7-b334-ea78d19cde3f', 'docx', 'D:/platform/uploadPath\\85c92bd6-d77f-4dc7-b334-ea78d19cde3f', '/statics\\85c92bd6-d77f-4dc7-b334-ea78d19cde3f', 70006, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (49, '1工程.docx', '4a90db08-f8b6-4918-a2d9-0e1eeffef2b2', 'docx', 'D:/platform/uploadPath\\4a90db08-f8b6-4918-a2d9-0e1eeffef2b2', '/statics\\4a90db08-f8b6-4918-a2d9-0e1eeffef2b2', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (50, '05需求规格说明书.zip', 'b03999c2-d4a1-4a99-b9e0-7550aab0579e', 'zip', 'D:/platform/uploadPath\\b03999c2-d4a1-4a99-b9e0-7550aab0579e', '/statics\\b03999c2-d4a1-4a99-b9e0-7550aab0579e', 10161968, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (51, '集客重点项目标前评估汇报（广州市第二老人院项目二期工程10KV供配电工程施工专业承包项目(第二次)）(1)(1).pptx', 'be60ac47-07cb-401f-81e5-a0b02bc2b9a6', 'pptx', 'D:/platform/uploadPath\\be60ac47-07cb-401f-81e5-a0b02bc2b9a6', '/statics\\be60ac47-07cb-401f-81e5-a0b02bc2b9a6', 3750603, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (52, '项目2.docx', '6c410635-def6-45fd-8fb2-77e0041b2e0e', 'docx', 'D:/platform/uploadPath\\6c410635-def6-45fd-8fb2-77e0041b2e0e', '/statics\\6c410635-def6-45fd-8fb2-77e0041b2e0e', 5430454, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (53, 'demo.zip', '9cea0676-a345-4ee7-b1ff-7bc1190f3888', 'zip', 'D:/platform/uploadPath\\9cea0676-a345-4ee7-b1ff-7bc1190f3888', '/statics\\9cea0676-a345-4ee7-b1ff-7bc1190f3888', 8900441, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (54, 'demo.zip', '1c05e1ca-6562-45e7-ac2a-7129991a9c08', 'zip', 'D:/platform/uploadPath\\1c05e1ca-6562-45e7-ac2a-7129991a9c08', '/statics\\1c05e1ca-6562-45e7-ac2a-7129991a9c08', 8900441, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (55, '1工程.docx', '1488c3ca-0867-4570-b9e3-715b6ba1ab7e', 'docx', 'D:/platform/uploadPath\\1488c3ca-0867-4570-b9e3-715b6ba1ab7e', '/statics\\1488c3ca-0867-4570-b9e3-715b6ba1ab7e', 45080, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (56, '项目2.docx', 'f53edb60-d1f6-4c5e-89cf-05bfab2413cd', 'docx', 'D:/platform/uploadPath\\f53edb60-d1f6-4c5e-89cf-05bfab2413cd', '/statics\\f53edb60-d1f6-4c5e-89cf-05bfab2413cd', 5430454, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (57, 'yyzz.png', 'd130dbaf-f562-4b03-9d82-b0a929110448', 'png', 'D:/platform/uploadPath\\d130dbaf-f562-4b03-9d82-b0a929110448', '/statics\\d130dbaf-f562-4b03-9d82-b0a929110448', 2278550, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (58, 'nginx.exe', '7ee1df8a-52c5-448e-99df-2a97c7e0d69a', 'exe', 'D:/platform/uploadPath\\7ee1df8a-52c5-448e-99df-2a97c7e0d69a', '/statics\\7ee1df8a-52c5-448e-99df-2a97c7e0d69a', 3691520, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (59, 'dist2.zip', '5260acec-e113-4fe8-b02c-6e9afcb1410d', 'zip', 'D:/platform/uploadPath\\5260acec-e113-4fe8-b02c-6e9afcb1410d', '/statics\\5260acec-e113-4fe8-b02c-6e9afcb1410d', 1386696, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (60, '集客重点项目标前评估汇报（广州市第二老人院项目二期工程10KV供配电工程施工专业承包项目(第二次)）(1)(1).pptx', '3889adae-9c10-437f-8ea6-f92deced8f56', 'pptx', 'D:/platform/uploadPath\\3889adae-9c10-437f-8ea6-f92deced8f56', '/statics\\3889adae-9c10-437f-8ea6-f92deced8f56', 3750603, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (61, '项目2.docx', '26d4f6ca-3677-4dda-bfc7-bcae4eeb8eaa', 'docx', 'D:/platform/uploadPath\\26d4f6ca-3677-4dda-bfc7-bcae4eeb8eaa', '/statics\\26d4f6ca-3677-4dda-bfc7-bcae4eeb8eaa', 5107126, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (62, '项目3.docx', '9610f0f3-0e30-4995-90a4-ac20914448f5', 'docx', 'D:/platform/uploadPath\\9610f0f3-0e30-4995-90a4-ac20914448f5', '/statics\\9610f0f3-0e30-4995-90a4-ac20914448f5', 5738917, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (63, '__UNI__2AF36A0_20210506095516.apk', '5b0ba877-79d2-4cae-a791-698698749d75', 'apk', 'D:/platform/uploadPath\\5b0ba877-79d2-4cae-a791-698698749d75', '/statics\\5b0ba877-79d2-4cae-a791-698698749d75', 19505209, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (64, '集客重点项目标前评估汇报（广州市第二老人院项目二期工程10KV供配电工程施工专业承包项目(第二次)）(1)(1).pptx', 'a249390c-604e-4532-af73-8e16425fc21e', 'pptx', 'D:/platform/uploadPath\\a249390c-604e-4532-af73-8e16425fc21e', '/statics\\a249390c-604e-4532-af73-8e16425fc21e', 3906699, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (65, '__UNI__2AF36A0_20210506095516.apk', 'f6ed2877-ab24-48a7-971d-444ff7d737a5', 'apk', 'D:/platform/uploadPath\\f6ed2877-ab24-48a7-971d-444ff7d737a5', '/statics\\f6ed2877-ab24-48a7-971d-444ff7d737a5', 19505209, 1, 'admin', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (66, 'im11g11.jpg', '838d965d-c654-430d-9b68-d3cf63f31621', 'jpg', 'D:/platform/uploadPath\\838d965d-c654-430d-9b68-d3cf63f31621', '/statics\\838d965d-c654-430d-9b68-d3cf63f31621', 68767, NULL, NULL, '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (67, '111.xlsx', '111.xlsx', 'xlsx', 'C:\\Users\\ljf\\Desktop\\kkkkk\\999\\', '/api/file/111.xlsx', 12, NULL, 'admin', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (1788085059450974210, 'avatar.png', '9f7e28ad-1c75-4897-b47a-8d3db135fa27.png', 'image/png', 'C:\\Users\\ljf\\Desktop\\kkkkk\\999\\', '/api/file/9f7e28ad-1c75-4897-b47a-8d3db135fa27.png', 11406, NULL, 'admin', '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `group_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户组编码',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户组名称',
  `sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（1正常 0停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_limit_obj
-- ----------------------------
DROP TABLE IF EXISTS `sys_limit_obj`;
CREATE TABLE `sys_limit_obj`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `limit_obj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限制对象（时间、人员、岗位、部门、角色等等自定义）',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 1  启用 0 禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '限制对象表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_limit_obj
-- ----------------------------
INSERT INTO `sys_limit_obj` VALUES (1, 'now.getYear', '1', '当前年份', '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录信息',
  `request_time` datetime(0) NULL DEFAULT NULL COMMENT '请求时间',
  `browser_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器名',
  `browser_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `browser_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器家族',
  `browser_manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器生产厂商',
  `browser_renderingengine` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器使用的渲染引擎',
  `browser_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `os_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统名',
  `os_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问设备类型',
  `os_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统家族',
  `os_manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统生产厂商',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_request
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_request`;
CREATE TABLE `sys_log_request`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `model_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `operator_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `method_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `request_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求ip',
  `request_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地点',
  `request_param` blob NULL COMMENT '请求参数',
  `request_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `response_param` blob NULL COMMENT '响应参数',
  `login_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态',
  `exception_msg` blob NULL COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_request
-- ----------------------------
INSERT INTO `sys_log_request` VALUES (1, '操作日志', 9, 1, 'admin', 'com.platform.system.controller.SysOperlogController.clean()', 'DELETE', '/operlog/clean', '127.0.0.1', NULL, NULL, '2021-02-26 09:58:25', 0x7B226D7367223A22E6938DE4BD9CE68890E58A9F222C22636F6465223A3230307D, '0', NULL);
INSERT INTO `sys_log_request` VALUES (2, '角色管理', 2, 1, 'admin', 'com.platform.system.controller.SysRoleController.edit()', 'PUT', '/role', '127.0.0.1', NULL, 0x7B22666C6167223A66616C73652C22726F6C654964223A312C2261646D696E223A747275652C2272656D61726B223A22E8B685E7BAA7E7AEA1E79086E59198222C226461746153636F7065223A2231222C2264656C466C6167223A2230222C22706172616D73223A7B7D2C22726F6C65536F7274223A2231222C2264657074436865636B5374726963746C79223A747275652C2263726561746554696D65223A313532313137313138303030302C226D656E75436865636B5374726963746C79223A747275652C22726F6C654B6579223A2261646D696E222C22726F6C654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C226D656E75496473223A5B312C3130302C313030312C313030322C313030332C313030342C313030352C313030362C313030372C3130312C313030382C313030392C313031302C313031312C313031322C3130322C313031332C313031342C313031352C313031362C3130332C313031372C313031382C313031392C313032302C3130342C313032312C313032322C313032332C313032342C313032352C3130352C313032362C313032372C313032382C313032392C313033302C3130362C313033312C313033322C313033332C313033342C313033352C3130372C313034312C313034322C313034332C313034342C3130382C3530302C313034352C313034362C313034372C3530312C313034382C313034392C313035302C322C3130392C313035312C313035322C313035332C3131302C313035342C313035352C313035362C313035372C313035382C313035392C3131312C3131322C3131332C332C3131342C3131352C313036302C313036312C313036332C313036322C313036342C313036352C3131362C323030302C323030312C323030322C323030332C323030342C323030355D2C22737461747573223A2230227D, '2021-02-26 10:03:37', 0x6E756C6C, '0', 0xE4B88DE58581E8AEB8E6938DE4BD9CE8B685E7BAA7E7AEA1E79086E59198E8A792E889B2);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `menu_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `menu_perm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `route_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（1是 0否）',
  `iframe_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'iframe地址',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（1缓存 0不缓存）',
  `sort` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（1显示 0隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（1正常 0停用）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1683762378296184834 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'M', 'icon-park-outline:system', 'sys', 'sys', '/sys', '', 1, NULL, 0, 1, '0', '1', '系统管理目录', 'admin', '2018-03-16 11:33:00', 'admin', '2021-10-05 13:42:52');
INSERT INTO `sys_menu` VALUES (2, 0, '系统监控', 'M', 'v-icon-monitor', '', 'monitor', '/monitor', 'Layout', 1, NULL, 0, 2, '0', '1', '系统监控目录', 'admin', '2018-03-16 11:33:00', 'admin', '2021-10-05 13:41:00');
INSERT INTO `sys_menu` VALUES (3, 0, '系统工具', 'M', 'el-icon-s-tools', '', 'tool', '/tool', 'Layout', 1, NULL, 0, 3, '0', '1', '系统工具目录', 'admin', '2018-03-16 11:33:00', 'admin', '2021-10-05 13:41:16');
INSERT INTO `sys_menu` VALUES (100, 1, '用户管理', 'C', 'v-icon-user', 'system:user:list', 'sys-user', '/sys/user', '/sys/user', 1, NULL, 0, 1, '0', '1', '用户管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:49:29');
INSERT INTO `sys_menu` VALUES (101, 1, '角色管理', 'C', 'v-icon-adduser', 'system:role:list', 'sys-role', '/sys/role', '/sys/role', 1, NULL, 0, 2, '0', '1', '角色管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:49:56');
INSERT INTO `sys_menu` VALUES (102, 1, '菜单管理', 'C', 'v-icon-menu', 'system:menu:list', 'sys-menu', '/sys/menu', '/sys/menu', 1, NULL, 0, 3, '0', '1', '菜单管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:50:21');
INSERT INTO `sys_menu` VALUES (103, 1, '部门管理', 'C', 'v-icon-branches', 'system:dept:list', 'sys-dept', '/sys/dept', '/sys/dept', 1, NULL, 0, 4, '0', '1', '部门管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:51:37');
INSERT INTO `sys_menu` VALUES (104, 1, '岗位管理', 'C', 'v-icon-switchuser', 'system:post:list', 'sys-post', '/sys/post', '/sys/post', 1, NULL, 0, 5, '0', '1', '岗位管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:52:08');
INSERT INTO `sys_menu` VALUES (105, 1, '字典管理', 'C', 'v-icon-book', 'system:dict:list', 'sys-dict', '/sys/dict', '/sys/dict', 1, NULL, 0, 6, '0', '1', '字典管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:52:32');
INSERT INTO `sys_menu` VALUES (106, 1, '参数设置', 'C', 'v-icon-setting', 'system:config:list', 'sys-param', '/param', '/sys/param', 1, NULL, 0, 7, '0', '1', '参数设置菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:53:17');
INSERT INTO `sys_menu` VALUES (107, 1, '通知公告', 'C', 'v-icon-message', 'system:notice:list', 'sys-notice', '/sys/notice', '/sys/notice', 1, NULL, 0, 99, '0', '1', '通知公告菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:53:40');
INSERT INTO `sys_menu` VALUES (108, 2, '日志管理', 'M', 'v-icon-file-text', '', 'log', '/log', 'Main', 1, NULL, 0, 1, '0', '1', '日志管理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:56:48');
INSERT INTO `sys_menu` VALUES (109, 2, '在线用户', 'C', 'v-icon-deleteuser', 'monitor:online:list', 'sys-online', '/online', 'system/online/index', 1, NULL, 0, 2, '0', '1', '在线用户菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:56:45');
INSERT INTO `sys_menu` VALUES (110, 2, '定时任务', 'C', 'v-icon-database', 'monitor:job:list', 'sys-task', '/task', 'system/task/index', 1, NULL, 0, 3, '0', '1', '定时任务菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:56:44');
INSERT INTO `sys_menu` VALUES (111, 2, 'Sentinel控制台', 'C', 'sentinel', 'monitor:sentinel:list', 'sentinel', '/sentinel', 'Iframe', 1, 'http://192.168.1.90:8718', 0, 4, '0', '1', '流量控制菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-19 14:40:41');
INSERT INTO `sys_menu` VALUES (112, 2, 'Nacos控制台', 'C', 'nacos', 'monitor:nacos:list', 'nacos', '/nacos', 'Iframe', 1, 'http://192.168.1.90:8848/nacos', 0, 5, '0', '1', '服务治理菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-19 14:40:41');
INSERT INTO `sys_menu` VALUES (113, 2, 'Admin控制台', 'C', 'server', 'monitor:server:list', 'admin', '/admin', 'Iframe', 1, 'http://192.168.1.90:9100/login', 0, 6, '0', '1', '服务监控菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-19 14:40:43');
INSERT INTO `sys_menu` VALUES (114, 3, '表单构建', 'C', 'build', 'tool:build:list', 'build', '/build', 'tool/build/index', 1, NULL, 0, 3, '0', '1', '表单构建菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-19 14:40:24');
INSERT INTO `sys_menu` VALUES (115, 3, '代码生成', 'C', 'v-icon-codelibrary', 'tool:gen:list', 'sys-gen', '/gen', 'system/gen', 1, NULL, 0, 2, '0', '1', '代码生成菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-19 14:40:58');
INSERT INTO `sys_menu` VALUES (116, 3, '接口文档', 'C', 'v-icon-codelibrary', 'tool:swagger:list', 'swagger', '/swagger', 'Iframe', 1, 'http://localhost:8080/swagger-ui/index.html', 0, 1, '0', '1', '系统接口菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-06-24 10:12:45');
INSERT INTO `sys_menu` VALUES (500, 108, '操作日志', 'C', 'form', 'system:operlog:list', 'sys-log-operator', '/log-operator', 'system/log/operator', 1, NULL, 0, 1, '0', '1', '操作日志菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:17:35');
INSERT INTO `sys_menu` VALUES (501, 108, '登录日志', 'C', 'logininfor', 'system:logininfor:list', 'sys-log-login', '/log-login', 'system/log/login', 1, NULL, 0, 2, '0', '1', '登录日志菜单', 'admin', '2018-03-16 11:33:00', 'admin', '2021-03-16 14:16:36');
INSERT INTO `sys_menu` VALUES (1001, 100, '用户查询', 'F', '#', 'system:user:query', NULL, '', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1002, 100, '用户新增', 'F', '#', 'system:user:add', NULL, '', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1003, 100, '用户修改', 'F', '#', 'system:user:edit', NULL, '', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1004, 100, '用户删除', 'F', '#', 'system:user:remove', NULL, '', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1005, 100, '用户导出', 'F', '#', 'system:user:export', NULL, '', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1006, 100, '用户导入', 'F', '#', 'system:user:import', NULL, '', '', 1, NULL, 0, 6, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1007, 100, '重置密码', 'F', '#', 'system:user:resetPwd', NULL, '', '', 1, NULL, 0, 7, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1008, 101, '角色查询', 'F', '#', 'system:role:query', NULL, '', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1009, 101, '角色新增', 'F', '#', 'system:role:add', NULL, '', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1010, 101, '角色修改', 'F', '#', 'system:role:edit', NULL, '', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1011, 101, '角色删除', 'F', '#', 'system:role:remove', NULL, '', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1012, 101, '角色导出', 'F', '#', 'system:role:export', NULL, '', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1013, 102, '菜单查询', 'F', '#', 'system:menu:query', NULL, '', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1014, 102, '菜单新增', 'F', '#', 'system:menu:add', NULL, '', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1015, 102, '菜单修改', 'F', '#', 'system:menu:edit', NULL, '', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1016, 102, '菜单删除', 'F', '#', 'system:menu:remove', NULL, '', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1017, 103, '部门查询', 'F', '#', 'system:dept:query', NULL, '', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1018, 103, '部门新增', 'F', '#', 'system:dept:add', NULL, '', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1019, 103, '部门修改', 'F', '#', 'system:dept:edit', NULL, '', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1020, 103, '部门删除', 'F', '#', 'system:dept:remove', NULL, '', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1021, 104, '岗位查询', 'F', '#', 'system:post:query', NULL, '', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1022, 104, '岗位新增', 'F', '#', 'system:post:add', NULL, '', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1023, 104, '岗位修改', 'F', '#', 'system:post:edit', NULL, '', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1024, 104, '岗位删除', 'F', '#', 'system:post:remove', NULL, '', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1025, 104, '岗位导出', 'F', '#', 'system:post:export', NULL, '', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1026, 105, '字典查询', 'F', '#', 'system:dict:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1027, 105, '字典新增', 'F', '#', 'system:dict:add', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1028, 105, '字典修改', 'F', '#', 'system:dict:edit', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1029, 105, '字典删除', 'F', '#', 'system:dict:remove', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1030, 105, '字典导出', 'F', '#', 'system:dict:export', NULL, '#', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1031, 106, '参数查询', 'F', '#', 'system:config:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1032, 106, '参数新增', 'F', '#', 'system:config:add', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1033, 106, '参数修改', 'F', '#', 'system:config:edit', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1034, 106, '参数删除', 'F', '#', 'system:config:remove', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1035, 106, '参数导出', 'F', '#', 'system:config:export', NULL, '#', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1041, 107, '公告查询', 'F', '#', 'system:notice:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1042, 107, '公告新增', 'F', '#', 'system:notice:add', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1043, 107, '公告修改', 'F', '#', 'system:notice:edit', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1044, 107, '公告删除', 'F', '#', 'system:notice:remove', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1045, 500, '操作查询', 'F', '#', 'system:operlog:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1046, 500, '操作删除', 'F', '#', 'system:operlog:remove', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1047, 500, '日志导出', 'F', '#', 'system:operlog:export', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1048, 501, '登录查询', 'F', '#', 'system:logininfor:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1049, 501, '登录删除', 'F', '#', 'system:logininfor:remove', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1050, 501, '日志导出', 'F', '#', 'system:logininfor:export', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1051, 109, '在线查询', 'F', '#', 'monitor:online:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1052, 109, '批量强退', 'F', '#', 'monitor:online:batchLogout', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1053, 109, '单条强退', 'F', '#', 'monitor:online:forceLogout', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1054, 110, '任务查询', 'F', '#', 'monitor:job:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1055, 110, '任务新增', 'F', '#', 'monitor:job:add', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1056, 110, '任务修改', 'F', '#', 'monitor:job:edit', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1057, 110, '任务删除', 'F', '#', 'monitor:job:remove', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1058, 110, '状态修改', 'F', '#', 'monitor:job:changeStatus', NULL, '#', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1059, 110, '任务导出', 'F', '#', 'monitor:job:export', NULL, '#', '', 1, NULL, 0, 7, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1060, 115, '生成查询', 'F', '#', 'tool:gen:query', NULL, '#', '', 1, NULL, 0, 1, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1061, 115, '生成修改', 'F', '#', 'tool:gen:edit', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1062, 115, '生成删除', 'F', '#', 'tool:gen:remove', NULL, '#', '', 1, NULL, 0, 3, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1063, 115, '导入代码', 'F', '#', 'tool:gen:import', NULL, '#', '', 1, NULL, 0, 2, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1064, 115, '预览代码', 'F', '#', 'tool:gen:preview', NULL, '#', '', 1, NULL, 0, 4, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1065, 115, '生成代码', 'F', '#', 'tool:gen:code', NULL, '#', '', 1, NULL, 0, 5, '0', '1', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_menu` VALUES (1066, 3, '文件上传', 'M', 'v-icon-upload', NULL, 'sys-file', '/sys-file', 'system/file', 1, NULL, 0, 0, '0', '1', '', 'admin', '2021-06-24 10:05:52', '', NULL);
INSERT INTO `sys_menu` VALUES (1068, 0, '项目管理', 'M', 'v-icon-trophy', '', 'manager', '/manager', 'Layout', 1, NULL, 0, 4, '0', '1', '', 'admin', '2021-10-05 13:36:57', 'admin', '2021-10-05 13:37:44');
INSERT INTO `sys_menu` VALUES (1069, 1068, '项目台账', 'C', 'v-icon-trophy-fill', '', 'project', '/project', 'manager/project/index', 1, NULL, 0, 1, '0', '1', '', 'admin', '2021-10-05 13:39:48', 'admin', '2021-10-28 14:11:21');
INSERT INTO `sys_menu` VALUES (1070, 1068, '周报填写', 'C', 'el-icon-notebook-1', 'weekly:weekly:querymonth', 'weekly', '/weekly', 'manager/weekly/index', 1, NULL, 0, 2, '0', '1', '', 'admin', '2021-10-19 11:12:42', 'admin', '2021-10-28 11:01:36');
INSERT INTO `sys_menu` VALUES (1071, 1068, '周报管理', 'M', 'el-icon-notebook-2', 'weekly:weekly:list', 'weeklymanage', '/api/system/menu/getAllList', 'manager/weeklymanage/index', 1, NULL, 0, 3, '0', '1', '', 'admin', '2021-10-26 17:10:29', 'admin', '2021-10-28 10:42:37');
INSERT INTO `sys_menu` VALUES (1072, 1071, '审批周报', 'F', '#', 'weekly:weekly:handle', NULL, '', NULL, 1, NULL, 0, 1, '0', '1', '', 'admin', '2021-10-28 10:32:47', 'admin', '2021-10-28 10:40:46');
INSERT INTO `sys_menu` VALUES (1073, 1070, '重新提交周报', 'F', '#', 'weekly:weekly:submit', NULL, '', NULL, 1, NULL, 0, 1, '0', '1', '', 'admin', '2021-10-28 10:33:38', 'admin', '2021-10-28 10:40:37');
INSERT INTO `sys_menu` VALUES (1074, 1070, '周报提交', 'F', '#', 'weekly:weekly:add', NULL, '', NULL, 1, NULL, 0, 2, '0', '1', '', 'admin', '2021-10-28 10:38:01', 'admin', '2021-10-28 10:40:39');
INSERT INTO `sys_menu` VALUES (1075, 1070, '周报修改', 'F', '#', 'weekly:weekly:edit', NULL, '', NULL, 1, NULL, 0, 3, '0', '1', '', 'admin', '2021-10-28 10:40:02', 'admin', '2021-10-28 10:40:40');
INSERT INTO `sys_menu` VALUES (1076, 1070, '周报详情', 'F', '#', 'weekly:weekly:query', NULL, '', NULL, 1, NULL, 0, 4, '0', '1', '', 'admin', '2021-10-28 11:53:32', '', NULL);
INSERT INTO `sys_menu` VALUES (1077, 1068, '项目信息登记', 'M', 'v-icon-project', NULL, 'projectadd', '/projectadd', 'manager/projectadd/index', 1, NULL, 0, 4, '0', '1', '', 'admin', '2021-11-13 16:15:37', '', NULL);
INSERT INTO `sys_menu` VALUES (1078, 1071, '一键审批周报', 'F', '#', 'weekly:weekly:fastHandle', NULL, '', NULL, 1, NULL, 0, 2, '0', '1', '', 'admin', '2021-11-14 15:22:56', '', NULL);
INSERT INTO `sys_menu` VALUES (1079, 1068, '知识库', 'M', 'v-icon-book', '', '/librarymanager', '/librarymanager', 'manager/librarymanager/index', 1, NULL, 0, 5, '0', '1', '', 'admin', '2021-11-15 16:48:13', 'admin', '2021-11-15 16:52:55');
INSERT INTO `sys_menu` VALUES (1080, 1068, '视频监控', 'M', 'v-icon-codelibrary-fill', NULL, '/video', '/video', 'manager/video/index', 1, NULL, 0, 6, '0', '1', '', 'admin', '2021-12-02 17:25:25', '', NULL);
INSERT INTO `sys_menu` VALUES (1664469643647172609, 1, '权限管理', 'C', 'arcticons:permissionmanagerx', 'system:permission:list', 'sys-permission', '/sys/permission', '/sys/permission', 1, '', 1, 8, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1664470057528508417, 1664469643647172609, '权限查询', 'F', '', 'system:permission:query', '', '', '', 1, '', 1, 0, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1664470277716885506, 1664469643647172609, '权限新增', 'F', '', 'system:permission:add', '', '', '', 1, '', 1, 1, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1664470339545120770, 1664469643647172609, '权限修改', 'F', '', 'system:permission:edit', '', '', '', 1, '', 1, 2, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1664470409824878594, 1664469643647172609, '权限删除', 'F', '', 'system:permission:remove', '', '', '', 1, '', 1, 3, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1664470485582397441, 1664469643647172609, '权限导出', 'F', '', 'system:permission:export', '', '', '', 1, '', 1, 4, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681513995066032129, 1, '驱动管理', 'C', 'ri:drive-line', 'system:drive:list', 'sys-drive', '/sys/drive', '/sys/drive', 1, '', 1, 9, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681514181238603778, 1, 'xml管理', 'C', 'bi:filetype-xml', 'system:mapper:list', 'sys-mapper', '/sys/mapper', '/sys/mapper', 1, '', 1, 10, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548504775933953, 1681513995066032129, '驱动查询', 'F', '', 'system:drive:query', '', '', '', 1, '', 1, 0, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548604025749506, 1681513995066032129, '驱动新增', 'F', '', 'system:drive:add', '', '', '', 1, '', 1, 1, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548662259466242, 1681513995066032129, '驱动修改', 'F', '', 'system:drive:edit', '', '', '', 1, '', 1, 2, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548727678025729, 1681513995066032129, '驱动删除', 'F', '', 'system:drive:remove', '', '', '', 1, '', 1, 3, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548795441201154, 1681513995066032129, '驱动导出', 'F', '', 'system:drive:export', '', '', '', 1, '', 1, 4, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548923518468098, 1681514181238603778, 'xml查询', 'F', '', 'system:mapper:query', '', '', '', 1, '', 1, 0, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681548977478189058, 1681514181238603778, 'xml新增', 'F', '', 'system:mapper:add', '', '', '', 1, '', 1, 1, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681549037645479937, 1681514181238603778, 'xml修改', 'F', '', 'system:mapper:edit', '', '', '', 1, '', 1, 2, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681549108466302978, 1681514181238603778, 'xml删除', 'F', '', 'system:mapper:remove', '', '', '', 1, '', 1, 3, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1681549180574777346, 1681514181238603778, 'xml导出', 'F', '', 'system:mapper:export', '', '', '', 1, '', 1, 4, '0', '0', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1683761702220517377, 0, '代码流程设计', 'M', 'ph:code-fill', 'code', 'code-flow', '/code-flow', '/code-flow', 1, '', 1, 4, '0', '0', '低代码流程设计', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (1683762378296184833, 1683761702220517377, '项目模板', 'C', 'tabler:template', 'code:template:list', 'code-flow-project-template', '/code-flow/project-template', '/code-flow/project-template', 1, '', 1, 0, '0', '0', '', '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_menu_limit
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_limit`;
CREATE TABLE `sys_menu_limit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1、用户组 2、岗位 3、角色',
  `contact_id` bigint(20) NOT NULL COMMENT '关联ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态 1  启用 0 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '功能权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_rule`;
CREATE TABLE `sys_menu_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `rule_id` bigint(20) NOT NULL COMMENT '规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '所属部门',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（1正常 0停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 102, 'ceo', '董事长', 1, '0', '', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_post` VALUES (2, 108, 'se', '项目经理', 3, '1', '', 'admin', '2024-04-24 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_post` VALUES (3, 109, 'hr', '人力资源', 2, '0', '', 'admin', '2024-04-09 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_post` VALUES (4, 108, 'user', '普通员工', 4, '1', '', 'admin', '2024-03-30 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `default_role` bit(1) NULL DEFAULT NULL COMMENT '是否为注册默认角色',
  `sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（1正常 0停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '超级管理员', b'0', 1, '1', '超级管理员', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_role` VALUES (2, 'common', '普通角色', b'1', 2, '1', '普通角色', 'admin', '2018-03-16 11:33:00', 'admin', '2021-06-24 16:44:46');
INSERT INTO `sys_role` VALUES (106, 'teammembers', '组员', b'0', 5, '1', '研发部组员角色', 'admin', '2021-10-28 10:55:02', 'admin', '2021-10-28 11:53:59');

-- ----------------------------
-- Table structure for sys_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_rule`;
CREATE TABLE `sys_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rule_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则编码',
  `limit_obj_id` bigint(20) NOT NULL COMMENT '限制对象id',
  `limit_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限制方式（属于(等于)、不属于(不等于)、大于、小于、范围）',
  `val` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条件值',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 1  启用 0 禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_scope`;
CREATE TABLE `sys_scope`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dept_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门id 父级表示：[1,2,3]',
  `post_id` bigint(20) NOT NULL COMMENT '岗位id',
  `dept_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门状态（1正常 0停用）',
  `post_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位状态（1正常 0停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '范围表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `user_name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770275787111649282 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '/api/file/2023/05/31/3989b38e-c10f-415f-9dce-9b12ebf47cf4.png', 'admin', '张三', '2889b4ce36d64937c1489ed9ba3ca80112aa62c0', 'ry@163.com', '15888888888', '1', '1', '12', '2018-03-16 11:33:00', 'ry', '2021-06-25 10:53:14', '管理员');
INSERT INTO `sys_user` VALUES (1769929093765304322, '', 'user3', '张三3', '$2a$10$b02AzHxH3KLzk0NMwTFWv.qBIo3.g8.kyXY9uJjXhdE9OND1IgbRe', '', '', '0', '0', '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1770275787111649281, '', 'wangwu', '王五', '{bcrypt}$2a$10$vXmtX1s5VFFPHf2ZqcHM7euv5P3NUfG/qh9d.tbBVXx3tXFG3U1am', '', '', '1', '0', '', NULL, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `group_id` bigint(20) NOT NULL COMMENT '用户组id',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态 1  启用 0 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户用户组中间件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态 1  启用 0 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
