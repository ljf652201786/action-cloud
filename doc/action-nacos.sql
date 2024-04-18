/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : action-nacos

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 18/04/2024 12:22:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (25, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-system\n          uri: lb://springcloud-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-auth\n          uri: lb://springcloud-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1', '12940732cb2209f7b014ba6869635e10', '2024-04-02 01:52:05', '2024-04-05 09:26:51', 'nacos', '192.168.1.136', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (32, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 02:12:32', '2024-04-02 02:13:47', 'nacos', '192.168.1.136', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (39, 'springcloud-auth-dev.yaml', 'DEFAULT_GROUP', 'security-auth:\r\n  ignore-urls:\r\n    - /auth/**\r\n#    - /system/user/**\r\n  encoding: bcrypt\r\n  jwt:\r\n    token-header: Forum-Token', 'a15b05d017ad2ea9b69290fc6a2bc202', '2024-04-02 03:23:34', '2024-04-02 03:23:34', 'nacos', '192.168.1.136', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (40, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      password: Admin996LJF!.\n      database: 1\n      timeout: 30000\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', '333a4fb2547a1f85d4de3191e1756c9c', '2024-04-02 04:03:23', '2024-04-16 09:27:00', 'nacos', '127.0.0.1', '', '', '', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\r\n        - id: baidu # 当前路由的标识, 要求唯一\r\n          uri: http://www.baidu.com # 请求要转发到的地址\r\n          predicates: # 断言(就是路由转发要满足的条件)\r\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '9181a21d7106158067b92b7369695ec3', '2024-04-01 11:25:33', '2024-04-01 03:25:34', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\r\n        - id: baidu # 当前路由的标识, 要求唯一\r\n          uri: http://www.baidu.com # 请求要转发到的地址\r\n          predicates: # 断言(就是路由转发要满足的条件)\r\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '9181a21d7106158067b92b7369695ec3', '2024-04-01 11:31:19', '2024-04-01 03:31:19', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 3, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'gateway:\n  routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n    - id: baidu # 当前路由的标识, 要求唯一\n      uri: http://www.baidu.com # 请求要转发到的地址\n      predicates: # 断言(就是路由转发要满足的条件)\n        - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '0399b6bfd9e4129214e76649596b1b13', '2024-04-01 11:32:03', '2024-04-01 03:32:04', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'gateway:\n  routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n    - id: baidu # 当前路由的标识, 要求唯一\n      uri: http://www.baidu.com # 请求要转发到的地址\n      predicates: # 断言(就是路由转发要满足的条件)\n        - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '0399b6bfd9e4129214e76649596b1b13', '2024-04-01 11:32:43', '2024-04-01 03:32:43', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n        - id: baidu # 当前路由的标识, 要求唯一\n          uri: http://www.baidu.com # 请求要转发到的地址\n          predicates: # 断言(就是路由转发要满足的条件)\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', 'b61166a62bd44ee910be6a021f04e428', '2024-04-01 11:49:22', '2024-04-01 03:49:22', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 6, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n          locator:\n            enabled: true\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n        - id: baidu # 当前路由的标识, 要求唯一\n          uri: http://www.baidu.com # 请求要转发到的地址\n          predicates: # 断言(就是路由转发要满足的条件)\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', 'e7a35bbcf72994d4eacfeee64e9770ee', '2024-04-01 11:49:38', '2024-04-01 03:49:38', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 7, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n        - id: baidu # 当前路由的标识, 要求唯一\n          uri: http://www.baidu.com # 请求要转发到的地址\n          predicates: # 断言(就是路由转发要满足的条件)\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '42dd21cf2c7ebb13e466a36e34ea67ff', '2024-04-01 14:22:37', '2024-04-01 06:22:38', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 8, 'springcloud-business', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 15:07:24', '2024-04-01 07:07:24', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (1, 9, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes: # 路由数组[路由 就是指定当请求满足什么条件的时候转到哪个微服务]\n        - id: baidu # 当前路由的标识, 要求唯一\n          uri: http://www.baidu.com # 请求要转发到的地址\n          predicates: # 断言(就是路由转发要满足的条件)\n            - Path=/ # 当请求路径满足Path指定的规则时,才进行路由转发', '42dd21cf2c7ebb13e466a36e34ea67ff', '2024-04-01 15:10:28', '2024-04-01 07:10:28', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 10, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: springcloud-business \n          uri: lb://springcloud-business\n          predicates: \n            - Path=/business/**\n          ', 'a5fa9f787efa23422d1ec72c4593166a', '2024-04-01 15:11:45', '2024-04-01 07:11:45', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 11, 'springcloud-gateway', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: springcloud-business \n          uri: lb://springcloud-business\n          predicates: \n            - Path=/business/**\n          ', 'a5fa9f787efa23422d1ec72c4593166a', '2024-04-01 15:18:30', '2024-04-01 07:18:31', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 12, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true\r\n      routes:\r\n        - id: \r\n          uri: lb://\r\n          predicates:\r\n            - Path=/\r\n          filters:\r\n            - StripPrefix=1', 'b382e87fe9c3b194f552c1fbe3becf59', '2024-04-01 15:23:05', '2024-04-01 07:23:06', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (8, 13, 'springcloud-business', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 15:23:21', '2024-04-01 07:23:21', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 14, 'springcloud-business', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 15:23:39', '2024-04-01 07:23:39', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (12, 15, 'springcloud-business', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 15:23:45', '2024-04-01 07:23:45', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 16, 'springcloud-business-dev', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 15:24:02', '2024-04-01 07:24:03', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (11, 17, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true\r\n      routes:\r\n        - id: \r\n          uri: lb://\r\n          predicates:\r\n            - Path=/\r\n          filters:\r\n            - StripPrefix=1', 'b382e87fe9c3b194f552c1fbe3becf59', '2024-04-01 15:25:12', '2024-04-01 07:25:12', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 18, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1', 'bbd47f28dabd0ba8ed323c214c93e286', '2024-04-01 15:34:07', '2024-04-01 07:34:07', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 19, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1', '215170244f088a48bf6cbaefa24470b0', '2024-04-01 15:34:33', '2024-04-01 07:34:33', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 20, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: false\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1', 'c859b4bc448fcee826b9ca2b20e437d3', '2024-04-01 15:35:27', '2024-04-01 07:35:27', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 21, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1', '215170244f088a48bf6cbaefa24470b0', '2024-04-01 15:53:32', '2024-04-01 07:53:32', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 22, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', '540f9f66df85ff2f658236b032a0d1c3', '2024-04-01 15:55:04', '2024-04-01 07:55:04', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 23, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', 'd16211135a24098b1892792e34c19a24', '2024-04-01 16:03:19', '2024-04-01 08:03:20', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (11, 24, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n      globalcors: #跨域配置\n        cors-configurations:\n          \'[/**]\':\n            allowedOrigins:\n              - \"http://127.0.0.1:8887\" #允许跨域\n            allow-credentials: true\n            allowed-headers: \"*\"\n            allowedMethods:\n              - GET\n              - POST\n              - DELETE\n              - PUT\n              - PATCH\n              - OPTIONS\n              - HEAD\n              - CONNECT\n              - TRACE', '1d5fd29ee32a042a8930559540c7361e', '2024-04-01 16:05:13', '2024-04-01 08:05:13', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 25, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-01 17:38:15', '2024-04-01 09:38:16', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 26, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-01 17:38:45', '2024-04-01 09:38:46', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (11, 27, 'springcloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', 'd16211135a24098b1892792e34c19a24', '2024-04-01 17:38:48', '2024-04-01 09:38:48', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (13, 28, 'springcloud-business-dev', 'DEFAULT_GROUP', '', 'username: 张三', '0da1678caefe398261cd42b8ba84574c', '2024-04-01 17:38:51', '2024-04-01 09:38:52', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (23, 29, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 09:41:20', '2024-04-02 01:41:21', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (22, 30, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-02 09:43:18', '2024-04-02 01:43:19', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 31, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-02 09:44:42', '2024-04-02 01:44:42', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (24, 32, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-02 09:51:35', '2024-04-02 01:51:36', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 33, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-02 09:52:05', '2024-04-02 01:52:05', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 34, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 09:52:29', '2024-04-02 01:52:29', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (25, 35, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      routes:\r\n        - id: springcloud-business\r\n          uri: lb://springcloud-business\r\n          predicates:\r\n            - Path=/bu/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: baidu\r\n          uri: http://www.baidu.com\r\n          predicates:\r\n            - Path=/', '75a2eb87f1f8c55544ebdac0ac989e5f', '2024-04-02 10:06:35', '2024-04-02 02:06:36', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (26, 36, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 10:06:44', '2024-04-02 02:06:45', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 37, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', 'd16211135a24098b1892792e34c19a24', '2024-04-02 10:08:44', '2024-04-02 02:08:45', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 38, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', '2b38b3b4398b01e7caa00d31634967cc', '2024-04-02 10:10:15', '2024-04-02 02:10:15', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (26, 39, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 10:11:16', '2024-04-02 02:11:16', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (26, 40, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 10:12:04', '2024-04-02 02:12:04', 'nacos', '192.168.1.136', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 41, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 10:12:31', '2024-04-02 02:12:32', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (32, 42, 'springcloud-business-dev.yaml', 'DEFAULT_GROUP', '', 'username: zhangsan', '0b160a9f05eb9a5c695084d38f322c5f', '2024-04-02 10:13:46', '2024-04-02 02:13:47', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 43, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', '2b38b3b4398b01e7caa00d31634967cc', '2024-04-02 10:15:02', '2024-04-02 02:15:02', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 44, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', '2b38b3b4398b01e7caa00d31634967cc', '2024-04-02 10:19:01', '2024-04-02 02:19:01', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 45, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/', '4156f7089d34a1e447e500d4b54895f3', '2024-04-02 10:27:43', '2024-04-02 02:27:44', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 46, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu1/**\n          filters:\n            - StripPrefix=1', '0bc3b93ea13d617aa21528ea8a0b6197', '2024-04-02 10:51:44', '2024-04-02 02:51:45', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 47, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1', '10fa9887b7a72af19a573a342ab2ad3b', '2024-04-02 11:20:54', '2024-04-02 03:20:54', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 48, 'springcloud-auth-dev.yaml', 'DEFAULT_GROUP', '', 'security-auth:\r\n  ignore-urls:\r\n    - /auth/**\r\n#    - /system/user/**\r\n  encoding: bcrypt\r\n  jwt:\r\n    token-header: Forum-Token', 'a15b05d017ad2ea9b69290fc6a2bc202', '2024-04-02 11:23:33', '2024-04-02 03:23:34', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 49, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'username: lisi', '2de774f4800fdbd08cba62c8f1cad1e1', '2024-04-02 12:03:22', '2024-04-02 04:03:23', 'nacos', '192.168.1.136', 'I', '', '');
INSERT INTO `his_config_info` VALUES (40, 50, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'username: lisi', '2de774f4800fdbd08cba62c8f1cad1e1', '2024-04-02 14:48:40', '2024-04-02 06:48:40', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 51, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: ${DATASOURCE_URL:jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai}\n    username: ${DATASOURCE_USERNAME:root}\n    password: ${DATASOURCE_PWD:root}\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        #设置可以获取tables remarks信息\n        useInformationSchema: true\nmybatis-plus:\n  # xml扫描，多个目录用逗号或者分号分隔（告诉 Mapper 所对应的 XML 文件位置）\n  mapper-locations: classpath:mapper/*.xml\n  # 以下配置均有默认值,可以不设置\n  global-config:\n    #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n    id-type: 0\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\n    field-strategy: 2\n    #驼峰下划线转换\n    db-column-underline: true\n    #刷新mapper\n    refresh-mapper:\n      enable: true\n      mapper_base_path: E:\\自己开发\\开发工具\\testmapper\\\n    #数据库大写下划线转换\n    #capital-mode: true\n    #序列接口实现类配置\n    #key-generator: com.baomidou.springboot.xxx\n    #逻辑删除配置\n    #logic-delete-value: 0 # 逻辑已删除值(默认为 1)\n    #logic-not-delete-value: 1 # 逻辑未删除值(默认为 0)\n    #自定义填充策略接口实现\n    #meta-object-handler: MyMetaObjectHandler\n    #自定义SQL注入器\n    #sql-injector: com.baomidou.springboot.xxx\n  configuration:\n    # 开启打印sql配置\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    # 是否开启自动驼峰命名规则映射:从数据库列名到Java属性驼峰命名的类似映射\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    # 如果查询结果中包含空值的列，则 MyBatis 在映射的时候，不会映射这个字段\n    #    call-setters-on-nulls: true\n    # 解决oracle更新数据为null时无法转换报错，mysql不会出现此情况\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', '1b4c84dc32e4f2f43a8761fc30846586', '2024-04-02 14:56:47', '2024-04-02 06:56:47', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 52, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-auth\n          uri: lb://springcloud-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1', '7642ebc758acba1447228d76aa859d22', '2024-04-02 15:02:46', '2024-04-02 07:02:46', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 53, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/business/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-system\n          uri: lb://springcloud-system\n          predicates:\n            - Path=/user/**\n          filters:\n            - StripPrefix=1', 'b64df3c1704fa89a31b0779dd45dec46', '2024-04-02 15:03:07', '2024-04-02 07:03:08', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 54, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/business/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-system\n          uri: lb://springcloud-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', '4d4618d6381863884ac2069a5152cb14', '2024-04-02 15:07:58', '2024-04-02 07:07:58', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 55, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'username: lisi', '2de774f4800fdbd08cba62c8f1cad1e1', '2024-04-03 10:54:38', '2024-04-03 02:54:38', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (25, 56, 'springcloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: baidu\n          uri: http://www.baidu.com\n          predicates:\n            - Path=/\n        - id: springcloud-business\n          uri: lb://springcloud-business\n          predicates:\n            - Path=/bu/**\n          filters:\n            - StripPrefix=1\n        - id: springcloud-system\n          uri: lb://springcloud-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', '2df93bdebdc7c8f0fb5f95cbd9d514a6', '2024-04-05 17:26:51', '2024-04-05 09:26:51', 'nacos', '192.168.1.136', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 57, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', '8aae0b86aa1d19ba8c30603593560dfd', '2024-04-14 18:41:30', '2024-04-14 10:41:31', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 58, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', '0a44761ef4e13d773211a67a2759a10a', '2024-04-16 14:07:46', '2024-04-16 06:07:47', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 59, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  redis:\n    host: localhost\n    port: 6379\n    password: Admin996LJF!.\n    database: 1\n    timeout: 30000\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', 'c1e764ec379d749a2a7a5b77732cf560', '2024-04-16 14:13:40', '2024-04-16 06:13:40', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 60, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  redis:\n    host: localhost\n    port: 6379\n    password: Admin996LJF!.\n    database: 1\n    timeout: 30000\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', 'c1e764ec379d749a2a7a5b77732cf560', '2024-04-16 17:24:24', '2024-04-16 09:24:25', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (40, 61, 'springcloud-system-dev.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  \nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: true\n    data-permission:\n      enable: true', 'eacf0853c066f0250aa56349014555c3', '2024-04-16 17:26:59', '2024-04-16 09:27:00', 'nacos', '127.0.0.1', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
