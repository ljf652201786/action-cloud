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

 Date: 28/06/2024 17:21:07
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
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (45, 'action-business-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://${mysql.host}:${mysql.port}/action-biz?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: ${mysql.username}\n    password: ${mysql.password}\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  data:\n    redis:\n      database: ${redis.database}\n      host: ${redis.host}\n      port: ${redis.port}\n      password: ${redis.password}\n      lettuce:\n        pool:\n          min-idle: 1\n  mail:\n    host: smtp.qq.com\n    protocol: smtp\n    username: 2521313275\n    password: axqyyhbwvtezdhjf\n    from_addr: 2521313275@qq.com\n    nick_name: 张三\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: false\n    data-permission:\n      enable: true\n# 安全配置\nsecurity-manage:\n  # 允许无需认证的路径列表\n  whitelist-paths:\n    - /**\n', '9ca552798372047c59b61d6fb475d482', '2024-04-20 03:37:04', '2024-06-28 08:48:06', 'nacos', '127.0.0.1', '', '', '业务服务配置文件', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (46, 'action-gateway-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      routes:\n        - id: action-system\n          uri: lb://action-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        - id: action-auth\n          uri: lb://action-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        - id: action-file\n          uri: lb://action-file\n          predicates:\n            - Path=/file/**\n          filters:\n            - StripPrefix=1\n        - id: action-business\n          uri: lb://action-business\n          predicates:\n            - Path=/business/**\n          filters:\n            - StripPrefix=1\n  data:\n    redis:\n      database: ${redis.database}\n      host: ${redis.host}\n      port: ${redis.port}\n      password: ${redis.password}\n      lettuce:\n        pool:\n          min-idle: 1\n# Filter only for all service requests through the gateway\nsecurity-manage:\n  blacklist-paths:\n    # - /auth/**', '827dfa8d6863b1eb8d954436739c23d3', '2024-04-20 03:37:44', '2024-06-25 09:36:04', 'nacos', '127.0.0.1', '', '', '网关服务配置文件', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (71, 'action-system-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://${mysql.host}:${mysql.port}/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: ${mysql.username}\n    password: ${mysql.password}\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  data:\n    redis:\n      database: ${redis.database}\n      host: ${redis.host}\n      port: ${redis.port}\n      password: ${redis.password}\n      lettuce:\n        pool:\n          min-idle: 1\n  mail:\n    host: smtp.qq.com\n    protocol: smtp\n    username: 2521313275\n    password: axqyyhbwvtezdhjf\n    from_addr: 2521313275@qq.com\n    nick_name: 张三\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: false\n    data-permission:\n      enable: false\n# 安全配置\nsecurity-manage:\n  # 允许无需认证的路径列表\n  whitelist-paths:\n    # 获取系统用户的认证信息用于账号密码判读\n    - /user/getUserByUserName\n    - /**\n', 'f7c6badfe1298ad3d5f3f8b05affb3ac', '2024-05-15 08:29:13', '2024-06-26 09:02:37', 'nacos', '127.0.0.1', '', '', '系统配置文件', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (72, 'action-file-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://${mysql.host}:${mysql.port}/action-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: ${mysql.username}\n    password: ${mysql.password}\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: false\n    data-permission:\n      enable: false\nfile:\n  resources:\n    - path-patterns: /api/file/**,/api/file1/**\n      locations: C:\\Users\\ljf\\Desktop\\kkkkk\\999\\,C:\\Users\\ljf\\Desktop\\kkkkk\\test\\\n      \n\n    ', '9f507245463ecf3f804285695fc9f8c9', '2024-05-15 08:32:42', '2024-05-16 12:05:43', 'nacos', '127.0.0.1', '', '', '文件服务配置', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (90, 'action-common.yaml', 'DEFAULT_GROUP', 'redis:\n  database: 5\n  host: 127.0.0.1\n  port: 6379\n  password: Admin996LJF!.\n  \nmysql:\n  host: 127.0.0.1\n  port: 3306\n  username: root\n  password: root\n\nrabbitmq:\n  host: 127.0.0.1\n  port: 5672\n  username: guest\n  password: guest\n\n# 验签公钥地址\nspring:\n  security:\n    oauth2:\n      authorizationserver:\n        token-uri: ${gateway.endpoint}/auth/oauth2/token\n      resourceserver:\n        jwt: \n          jwk-set-uri: ${gateway.endpoint}/auth/oauth2/jwks\n# 网关地址\ngateway:\n  endpoint: http://localhost:8889\n\n# Nacos 配置中心地址(Seata RM、TM 注册)\nnacos:\n  server-addr: http://localhost:8848\n\nsecurity-manage:\n  # 权限验证模式 true:系统动态权限管理  false:基于注解方式权限管理 ；默认为true\n  mode: true\n  \n# 短信配置\nsms:\n  # 阿里云短信\n  aliyun:\n    accessKeyId: LTAI5tSMgfxxxxxxdiBJLyR\n    accessKeySecret: SoOWRqpjtS7xxxxxxZ2PZiMTJOVC\n    domain: dysmsapi.aliyuncs.com \n    regionId: cn-shanghai\n    signName: 有来技术\n    templateCodes: \n      register: SMS_22xxx771\n      login: SMS_22xxx770\n# 微信小程序\nwx:\n  miniapp:\n    appId: wx99a151dc43d2637b\n    appSecret: a09605af8ad29ca5d18ff31c19828f37\n\n\n', '179fed698f6a44f2603694627b431ac4', '2024-05-16 11:40:49', '2024-05-24 00:49:59', 'nacos', '127.0.0.1', '', '', '公共配置类', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (109, 'action-auth-dev.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driverClassName: com.mysql.jdbc.Driver\n    url: jdbc:mysql://${mysql.host}:${mysql.port}/action-oauth2?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai\n    username: ${mysql.username}\n    password: ${mysql.password}\n    druid:\n      initialSize: 5\n      minIdle: 10\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      maxEvictableIdleTimeMillis: 900000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        allow:\n        url-pattern: /druid/*\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n    xa:\n      properties:\n        useInformationSchema: true\n  data:\n    redis:\n      database: ${redis.database}\n      host: ${redis.host}\n      port: ${redis.port}\n      password: ${redis.password}\n      lettuce:\n        pool:\n          min-idle: 1\nmybatis-plus:\n  mapper-locations: classpath:mapper/*.xml\n  global-config:\n    id-type: 0\n    field-strategy: 2\n    db-column-underline: true\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    jdbc-type-for-null: \'null\'\n  extend:\n    dytable-name:\n      enable: false\n    data-permission:\n      enable: false\n# 安全配置\nsecurity-manage:\n  # 允许无需认证的路径列表\n  whitelist-paths:', '0ee7ed8d54e3f13e38393296bbfc90e4', '2024-05-22 03:02:21', '2024-06-26 07:04:06', 'nacos', '127.0.0.1', '', '', '认证服务配置', '', '', 'yaml', '', '');

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
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

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
