/*
 Navicat Premium Data Transfer1

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : dreamland

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 21/01/2021 10:34:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门',
  `sub_count` int NULL DEFAULT 0 COMMENT '子部门数目',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `dept_sort` int NULL DEFAULT 999 COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('15', '8', 1, 'UI部门', 7, b'1', 0, 'admin', '无名之辈', '2020-05-13 22:56:53', '2020-06-22 01:01:39');
INSERT INTO `sys_dept` VALUES ('2', '7', 0, '研发部', 3, b'1', 0, NULL, '无名之辈', '2019-03-25 09:15:32', '2020-06-21 21:22:36');
INSERT INTO `sys_dept` VALUES ('26cf0756c5fef8baafebdc12b47626bf', NULL, 0, '阿斯达斯的', 999, b'1', 1, '无名之辈', '无名之辈', '2020-06-16 00:15:29', '2020-06-21 21:41:03');
INSERT INTO `sys_dept` VALUES ('4ef571a7dc4efd18ebfacabb7cb232cc', '15', 0, 'JAVA组', 999, b'1', 0, '无名之辈', '无名之辈', '2020-06-20 19:22:35', '2020-06-22 01:01:39');
INSERT INTO `sys_dept` VALUES ('5', '7', 0, '运维部', 4, b'1', 0, NULL, '无名之辈', '2019-03-24 20:20:44', '2020-06-22 01:01:39');
INSERT INTO `sys_dept` VALUES ('6', '8', 0, '测试部', 6, b'1', 0, NULL, NULL, '2019-03-25 09:52:18', NULL);
INSERT INTO `sys_dept` VALUES ('7', NULL, 2, '华南分部', 0, b'1', 0, NULL, '无名之辈', '2019-03-24 22:04:50', '2020-06-21 22:05:56');
INSERT INTO `sys_dept` VALUES ('8', NULL, 2, '华北分部', 1, b'1', 0, NULL, 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', 'user_status', '用户状态', 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES ('4', 'dept_status', '部门状态', 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES ('5', 'job_status', '岗位状态', 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES ('526e53ee2b45ff8f7e16cb63785cc27a', 'ageType', '年龄类型', 0, '无名之辈', '无名之辈', '2020-06-17 09:18:56', '2020-06-17 22:50:34');
INSERT INTO `sys_dict` VALUES ('7cfe32b375aa0b1cc37f2146966c2355', 'asd', '阿斯达斯的', 1, '无名之辈', NULL, '2020-06-17 22:18:31', NULL);
INSERT INTO `sys_dict` VALUES ('a302ad396d5a989f88c3fc39da003776', '安爱', '阿萨大', 0, '无名之辈', NULL, '2020-06-18 02:38:53', NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `dict_sort` int NULL DEFAULT NULL COMMENT '排序',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('0ca86fd05236520813a0e8b8aebc90ac', 'a302ad396d5a989f88c3fc39da003776', 'asd', 'ffff', 999, 0, '无名之辈', NULL, '2020-06-18 03:00:23', NULL);
INSERT INTO `sys_dict_detail` VALUES ('0f2b979116488b75bc2221bdd36c5864', 'a302ad396d5a989f88c3fc39da003776', 'shibai', 'flase', 999, 0, '无名之辈', NULL, '2020-06-18 02:59:48', NULL);
INSERT INTO `sys_dict_detail` VALUES ('1', '1', '激活', 'true', 1, 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES ('105fd2fd936ad1a53e6d3383d70d574a', 'a302ad396d5a989f88c3fc39da003776', '成功', 'true', 999, 1, '无名之辈', NULL, '2020-06-18 02:59:00', NULL);
INSERT INTO `sys_dict_detail` VALUES ('2', '1', '禁用', 'false', 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES ('3', '4', '启用', 'true', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES ('4', '4', '停用', 'false', 2, 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES ('5', '5', '启用', 'true', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES ('6', '5', '停用', 'false', 2, 0, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES ('d9bca24d4c4568f9942d462c2bff0f6b', 'a302ad396d5a989f88c3fc39da003776', 'ad22', 'dddd22', 3, 1, '无名之辈', '无名之辈', '2020-06-17 01:04:01', '2020-06-18 03:04:20');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int NULL DEFAULT NULL COMMENT '排序',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
  INDEX `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('10', '产品经理', b'1', 4, 0, NULL, '无名之辈', NULL, '2020-06-21 21:20:35');
INSERT INTO `sys_job` VALUES ('11', '全栈开发', b'1', 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_job` VALUES ('12', '软件测试', b'1', 5, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_job` VALUES ('23fa6bbfef72c6575bd86499b044e8b6', '大客户经理', b'1', 1, 1, '无名之辈', '无名之辈', '2020-06-18 12:24:22', '2020-06-19 01:24:34');
INSERT INTO `sys_job` VALUES ('8', '人事专员', b'1', 3, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `log_create_time_index`(`create_time`) USING BTREE,
  INDEX `inx_log_type`(`log_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int NULL DEFAULT 0 COMMENT '子菜单数目',
  `type` int NULL DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `component_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `menu_level` int NULL DEFAULT NULL COMMENT '菜单级别',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单描述',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态标志（0正常 1删除 2停用）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_title`(`title`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`component_name`) USING BTREE,
  INDEX `inx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('10', NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('102', '97', 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('103', '92', 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('104', '92', 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('105', '92', 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('106', '93', 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('107', '93', 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('108', '93', 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('109', '94', 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11', '10', 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('110', '94', 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('111', '94', 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('112', '98', 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('113', '98', 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('114', '98', 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('116', '36', 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('14', '36', 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('15', '10', 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', 'tinymce', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('16', '36', 2, 1, '图床管理', 'Pictures', 'tools/picture/index', 33, 'image', 'pictures', b'0', b'0', b'0', 'pictures:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('18', '36', 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('19', '36', 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('2', '1', 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('21', NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', b'0', b'0', b'1', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('22', '21', 2, 1, '二级菜单1', NULL, 'nested/menu1/index', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('23', '21', 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('24', '22', 0, 1, '三级菜单1', NULL, 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('27', '22', 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('28', '1', 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('3', '1', 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('30', '36', 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('32', '6', 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('33', '10', 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('34', '10', 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('35', '1', 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('36', NULL, 8, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('37', '1', 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('38', '36', 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('39', '1', 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('39f1924aca91c7cda1aee2302db9bb44', '0', 0, 0, '山东荣登', NULL, NULL, 999, 'alipay', '/sdrd', b'0', b'0', b'0', NULL, NULL, NULL, '0', '无名之辈', '2020-06-22 03:13:01', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('41', '6', 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('44', '2', 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('45', '2', 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('46', '2', 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('48', '3', 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('49', '3', 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5', '1', 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('50', '3', 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('51fee3d7251c9f7daee780360daaafab', '92d2a68b7b96fd78bda75066e73000fd', 1, 1, '实施组', NULL, NULL, 999, 'date', '/ssz', b'0', b'0', b'0', 'asd', NULL, NULL, '0', '无名之辈', '2020-06-22 04:35:15', '无名之辈', '2020-06-22 21:30:41');
INSERT INTO `sys_menu` VALUES ('52', '5', 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('53', '5', 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('54', '5', 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('56', '35', 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('57', '35', 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('58', '35', 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6', NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('60', '37', 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('61', '37', 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('62', '37', 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('64', '39', 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('65', '39', 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('66', '39', 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6f37e548aa17dcdb6acc8632d7e8fd36', NULL, 0, 0, '蓝图嘉惠', NULL, NULL, 999, 'codeConsole', '/ltjh', b'0', b'0', b'0', NULL, NULL, NULL, '0', '无名之辈', '2020-06-22 03:14:41', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('7', '6', 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('70', '16', 0, 2, '图片上传', NULL, '', 2, '', '', b'0', b'0', b'0', 'pictures:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('71', '16', 0, 2, '图片删除', NULL, '', 3, '', '', b'0', b'0', b'0', 'pictures:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('73', '28', 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('74', '28', 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('75', '28', 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('77', '18', 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('78', '18', 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('79', '18', 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('80', '6', 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('82', '36', 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('83', '10', 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'0', '', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('9', '6', 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('90', NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('92', '90', 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('92d2a68b7b96fd78bda75066e73000fd', NULL, 1, 0, '青鸟软通', NULL, NULL, 999, 'Steve-Jobs', '/jbinfo', b'0', b'0', b'0', NULL, NULL, NULL, '0', '无名之辈', '2020-06-22 03:01:16', '无名之辈', '2020-06-22 04:35:15');
INSERT INTO `sys_menu` VALUES ('93', '90', 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('94', '90', 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('97', '90', 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('98', '90', 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '0', NULL, '2020-06-12 13:33:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b8d44b2580e34e67ff92b34618dd5bc3', NULL, 0, 1, '乡宁扶贫', NULL, '/a/c/s', 999, 'anq', '/xnfp', b'0', b'0', b'0', 'role:add', NULL, NULL, '1', '无名之辈', '2020-06-22 03:16:02', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('d4b84dc1906a6ba660956a6a8e1be76b', '51fee3d7251c9f7daee780360daaafab', 2, 2, '签到', NULL, NULL, 999, NULL, NULL, b'0', b'0', b'0', 'asddd', NULL, NULL, '0', '无名之辈', '2020-06-22 21:30:41', NULL, NULL);

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` bit(1) NULL DEFAULT NULL COMMENT '任务失败后是否暂停',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `inx_is_pause`(`is_pause`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('2', 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, 0, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-05 17:26:19');
INSERT INTO `sys_quartz_job` VALUES ('3', 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '2,6', b'1', 0, NULL, '无名之辈', '2019-09-26 16:44:39', '2020-07-02 21:59:41');
INSERT INTO `sys_quartz_job` VALUES ('5', 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 0, 'admin', NULL, '2020-05-05 20:32:41', NULL);
INSERT INTO `sys_quartz_job` VALUES ('6', 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 0, 'admin', '无名之辈', '2020-05-05 20:35:41', NULL);
INSERT INTO `sys_quartz_job` VALUES ('9b1d07b8391a58b52af151ab05e356c1', 'emailTestTask', '0 59 11 * * ?', b'1', '支付宝消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"吃饭-支付宝消费券\"}', '中午提醒', '阿阿斯顿', NULL, NULL, b'1', 0, '无名之辈', '无名之辈', '2020-08-05 14:51:43', NULL);
INSERT INTO `sys_quartz_job` VALUES ('c58dc020042117920ec96dcc6aef1f6b', 'emailTestTask', '0 58 7 * * ? ', b'0', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', '提醒她发打车助力', 'caoshuangna', NULL, NULL, b'1', 0, '无名之辈', '无名之辈', '2020-07-30 18:56:35', NULL);
INSERT INTO `sys_quartz_job` VALUES ('dc00db1d68b42ae631bcc7c2102030f0', 'emailRemindTask', '0/5 * * * * ?', b'1', '定时提醒', 'Remind2', '吃饭啦，快跑', '每日提醒备忘事项', 'wzx', '443082604@qq.com', NULL, b'1', 0, '无名之辈', '无名之辈', '2020-07-05 09:45:11', NULL);
INSERT INTO `sys_quartz_job` VALUES ('f8ca911576423944526b035fd76c036c', 'emailTestTask', '0 58 8-12 * * ?', b'1', '支付宝8月消费券', 'SendTOts', '{\"to\":\"wangzhengxiang-1@12349.com\",\"text\":\"年费plus\"}', '支付宝8月消费券', 'a', NULL, NULL, b'1', 0, '无名之辈', '无名之辈', '2020-08-05 01:34:57', NULL);

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_quartz_log
-- ----------------------------
INSERT INTO `sys_quartz_log` VALUES ('1bfc118ffc9319817d1d6c6f085efa72', 'emailTestTask', '2020-08-07 11:59:00', '0 59 11 * * ?', NULL, b'1', '支付宝消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"吃饭-支付宝消费券\"}', 334);
INSERT INTO `sys_quartz_log` VALUES ('206c4215b52ba663d0365502ca00db3c', 'emailTestTask', '2021-01-21 00:49:19', '0 58 7 * * ? ', NULL, NULL, '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', NULL);
INSERT INTO `sys_quartz_log` VALUES ('35820feecc065e4d0a90d937a9790c39', 'emailTestTask', '2020-08-11 07:58:00', '0 58 7 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', 472);
INSERT INTO `sys_quartz_log` VALUES ('461b5f4435306eaa023bde5b86172338', 'emailTestTask', '2020-08-06 11:59:00', '0 59 11 * * ?', NULL, b'1', '支付宝消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"吃饭-支付宝消费券\"}', 384);
INSERT INTO `sys_quartz_log` VALUES ('63243e00490824fafd29d2506df56f3e', 'emailTestTask', '2020-08-12 07:58:00', '0 58 7 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', 350);
INSERT INTO `sys_quartz_log` VALUES ('84149ac1207f60028505b6add4b252d6', 'Test', '2020-08-05 15:05:05', '0/5 * * * * ?', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'Test\' available\r\n	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:812)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1282)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:297)\r\n	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)\r\n	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1109)\r\n	at com.zero.dreamland.common.utils.SpringContextHolder.getBean(SpringContextHolder.java:60)\r\n	at com.zero.dreamland.quartz.utils.QuartzRunnable.<init>(QuartzRunnable.java:39)\r\n	at com.zero.dreamland.quartz.utils.ExecutionJob.executeInternal(ExecutionJob.java:85)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '任务告警测试', 'run', NULL, 10);
INSERT INTO `sys_quartz_log` VALUES ('9e423f9ce92a30e66a2736838a21eee1', 'emailTestTask', '2020-08-06 08:00:00', '0 0 8 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"快往群里发打车助力\"}', 587);
INSERT INTO `sys_quartz_log` VALUES ('a3afe5ff49508797d0ab5ffdb477917c', 'emailTestTask', '2020-08-10 11:08:37', '0 58 7 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', 859);
INSERT INTO `sys_quartz_log` VALUES ('a44982a1a55a0a99509651d2e7669e90', 'emailTestTask', '2020-08-06 08:35:11', '0 58 7 * * ?', NULL, b'1', '支付宝8月消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"支付宝消费券\"}', 539);
INSERT INTO `sys_quartz_log` VALUES ('c3da9e5632fcce04b72cd4850ff54970', 'emailTestTask', '2020-08-07 08:00:00', '0 0 8 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"快往群里发打车助力\"}', 273);
INSERT INTO `sys_quartz_log` VALUES ('eb2f5b1283ab570144b694d0194fd3ef', 'emailTestTask', '2020-08-13 07:58:00', '0 58 7 * * ? ', NULL, b'1', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', 362);
INSERT INTO `sys_quartz_log` VALUES ('fb81c829894ea7e2a4e8187e70b2095b', 'emailTestTask', '2020-08-07 07:58:00', '0 58 7 * * ?', NULL, b'1', '支付宝8月消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"支付宝消费券\"}', 445);
INSERT INTO `sys_quartz_log` VALUES ('fc8dfa0792465214ec81cd006021b1d1', 'emailTestTask', '2021-01-06 00:48:01', '0 58 7 * * ? ', NULL, NULL, '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `level` int NULL DEFAULT 3 COMMENT '角色级别',
  `role_sort` int NULL DEFAULT 0 COMMENT '排序',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `role_is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否系统默认 0 非默认 1默认',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE,
  UNIQUE INDEX `role_type`(`role_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('60dae2dadb123e5b18dd798c7490d888', 'TOURIST', '游客', 4, 10, '自定义', '0', '未获得系统权限的人员', '0', '无名之辈', '2019-09-09 20:37:21', NULL, NULL);
INSERT INTO `sys_role` VALUES ('635127e54a2c4dca87777fa0c488c3f3', 'ADMIN', '超级管理员', 1, 0, '全部', '0', '具有最高权限', '0', '1', '2018-07-19 14:28:32', NULL, NULL);
INSERT INTO `sys_role` VALUES ('697fc287c47e7a9075daa89d0fbb3222', NULL, 'PH1P研发组', 5, 0, '本级', '0', '随便啦爱爱爱', '1', '无名之辈', '2020-06-22 13:31:01', '无名之辈', '2020-06-23 02:31:32');
INSERT INTO `sys_role` VALUES ('70207334852248a2bd04b06687a0e782', 'TESTER', '测试人员', 3, 1, '本级', '0', '具有最高权限', '0', '1', '2018-07-19 14:28:32', NULL, NULL);
INSERT INTO `sys_role` VALUES ('83c583445cfb3fe53ed0b648a051f9fe', NULL, '测试权限', 4, 0, '本级', '0', '阿斯达斯', '0', '无名之辈', '2020-07-06 04:03:23', NULL, NULL);
INSERT INTO `sys_role` VALUES ('dfbc75e9bf07f708b3529ff33a691fa9', 'OPERATORS', '运营人员', 2, 3, '本级', '0', '甲方拥有最高权限的角色', '1', '无名之辈', '2019-09-08 20:48:28', '无名之辈', '2019-09-09 03:54:33');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  PRIMARY KEY (`role_id`, `dept_id`, `id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色部门关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------
INSERT INTO `sys_roles_depts` VALUES ('635127e54a2c4dca87777fa0c488c3f3', '2', '');

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  PRIMARY KEY (`menu_id`, `role_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
INSERT INTO `sys_roles_menus` VALUES ('1', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('1', '83c583445cfb3fe53ed0b648a051f9fe', '60db0e24d5bc998a5316229c50986f66');
INSERT INTO `sys_roles_menus` VALUES ('10', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('102', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('103', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('104', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('105', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('106', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('107', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('108', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('109', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('11', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('110', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('111', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('112', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('113', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('114', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('116', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('14', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('15', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('16', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('18', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('19', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('2', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('2', '83c583445cfb3fe53ed0b648a051f9fe', 'fd0b7375fc762d9d8cd01f604a3644ac');
INSERT INTO `sys_roles_menus` VALUES ('21', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('22', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('23', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('24', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('27', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('28', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('3', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('30', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('32', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('33', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('34', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('35', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('36', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('37', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('38', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('39', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('41', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('44', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('45', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('46', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('48', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('49', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('5', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('50', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('51fee3d7251c9f7daee780360daaafab', '60dae2dadb123e5b18dd798c7490d888', '');
INSERT INTO `sys_roles_menus` VALUES ('51fee3d7251c9f7daee780360daaafab', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('52', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('53', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('54', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('56', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('57', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('58', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('6', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('60', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('61', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('62', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('64', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('65', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('66', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('6f37e548aa17dcdb6acc8632d7e8fd36', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('6f37e548aa17dcdb6acc8632d7e8fd36', '697fc287c47e7a9075daa89d0fbb3222', '');
INSERT INTO `sys_roles_menus` VALUES ('7', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('70', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('71', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('73', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('74', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('75', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('77', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('78', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('79', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('80', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('82', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('83', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('9', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('90', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('92', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('92d2a68b7b96fd78bda75066e73000fd', '60dae2dadb123e5b18dd798c7490d888', '');
INSERT INTO `sys_roles_menus` VALUES ('92d2a68b7b96fd78bda75066e73000fd', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('93', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('94', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('97', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('98', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_roles_menus` VALUES ('d4b84dc1906a6ba660956a6a8e1be76b', '60dae2dadb123e5b18dd798c7490d888', '');
INSERT INTO `sys_roles_menus` VALUES ('d4b84dc1906a6ba660956a6a8e1be76b', '635127e54a2c4dca87777fa0c488c3f3', '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `user_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_enable_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码明文',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `face_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸照片',
  `avatar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像真实路径',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织ID',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `position_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位ID',
  `employee_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `last_login_date` datetime NULL DEFAULT NULL COMMENT '最后访问时间',
  `pwd_reset_time` datetime NULL DEFAULT NULL COMMENT '修改密码的时间',
  `is_admin` bit(1) NULL DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bigint NOT NULL COMMENT '状态：1启用、0禁用',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_date` datetime NOT NULL COMMENT '插入时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插入者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('a8aca4f0d0ca4fdd875d7f34f3634e57', 'wzx', '$2a$10$HMFDmQnbMSGfouf3kjMJNOicOD9I7L3cLBWrLUDkCkqSpADIgZos6', '123', '无名之辈', '2147483647', NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, '2019-04-26 13:52:32', NULL, b'1', 1, 0, '2019-07-18 17:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES ('c051c316714e00f781fc897533071bce', '阿斯达', '$2a$10$IfIK4VfQ4Zj3lSGa3jourOp6PqIZUxsoMuEsKNJfDiNGS/.ZhcFRC', NULL, '年底', '15621009921', '443082604@qq.com', NULL, NULL, NULL, '男', NULL, '4ef571a7dc4efd18ebfacabb7cb232cc', NULL, NULL, NULL, NULL, b'0', 1, 0, '2020-07-06 07:07:40', '无名之辈', '无名之辈', NULL);
INSERT INTO `sys_user` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', 'ggg', '123', '123', '无名之辈', '15621009921', '960074810@qq.com', NULL, NULL, NULL, '男', NULL, '2', NULL, NULL, '2019-04-25 11:52:32', NULL, b'0', 1, 0, '2019-07-17 15:12:03', '', '无名之辈', '2020-07-02 02:11:21');

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `job_id` varbinary(32) NOT NULL COMMENT '岗位ID',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  PRIMARY KEY (`user_id`, `job_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES ('0587c557e3e0806621f1b9e218d5cda3', 0x3130, '48bc82ca67be84efdbd5a918d182801e');
INSERT INTO `sys_users_jobs` VALUES ('0587c557e3e0806621f1b9e218d5cda3', 0x3131, '91bb5f867d93819c03b2217b7fa8e21c');
INSERT INTO `sys_users_jobs` VALUES ('0fd10297fd38b1f170a50e5708caa248', 0x3131, '590bacae19215560f6c1fa2c659e206b');
INSERT INTO `sys_users_jobs` VALUES ('1', 0x3132, '');
INSERT INTO `sys_users_jobs` VALUES ('2', 0x3132, '');
INSERT INTO `sys_users_jobs` VALUES ('4d785a3facd77809bb5c0e737ecd9bc4', 0x3132, '3ded3d6e248659895e82a4dc46908d79');
INSERT INTO `sys_users_jobs` VALUES ('6a3114c367b902ccc2fe25915e2e4947', 0x38, '0b950768b631ec53899a60584a5988c9');
INSERT INTO `sys_users_jobs` VALUES ('a8aca4f0d0ca4fdd875d7f34f3634e57', 0x3131, '');
INSERT INTO `sys_users_jobs` VALUES ('c051c316714e00f781fc897533071bce', 0x3131, '9f347ea27064e549cfd6b555e968cd96');
INSERT INTO `sys_users_jobs` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', 0x3131, '3d89ec8226754b197f9e1f546d890978');
INSERT INTO `sys_users_jobs` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', 0x3132, 'b3f2a246b88229310b7badda4f68612b');

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  PRIMARY KEY (`user_id`, `role_id`, `id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('2', '2', '');
INSERT INTO `sys_users_roles` VALUES ('0fd10297fd38b1f170a50e5708caa248', '60dae2dadb123e5b18dd798c7490d888', '05edfcbd0c8aad7b67361e4596446048');
INSERT INTO `sys_users_roles` VALUES ('c051c316714e00f781fc897533071bce', '60dae2dadb123e5b18dd798c7490d888', '75d1393b71ed701f099aab3d0fb4a636');
INSERT INTO `sys_users_roles` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', '60dae2dadb123e5b18dd798c7490d888', 'f5178571cb697aa0f1b174b6830f1637');
INSERT INTO `sys_users_roles` VALUES ('0587c557e3e0806621f1b9e218d5cda3', '635127e54a2c4dca87777fa0c488c3f3', 'e502844ccffc4c4b241905d3efab34fa');
INSERT INTO `sys_users_roles` VALUES ('a8aca4f0d0ca4fdd875d7f34f3634e57', '635127e54a2c4dca87777fa0c488c3f3', '');
INSERT INTO `sys_users_roles` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', '635127e54a2c4dca87777fa0c488c3f3', '4b45de017fff790d7df796fe87135cc4');
INSERT INTO `sys_users_roles` VALUES ('0587c557e3e0806621f1b9e218d5cda3', '70207334852248a2bd04b06687a0e782', '71f0af94ab25f9e186a5977bad90d2ad');
INSERT INTO `sys_users_roles` VALUES ('dddca4f0d0ca4fdd875d7f34f3634e52', '70207334852248a2bd04b06687a0e782', '3e248f430dd7f8481fc9130ae12d0f1d');
INSERT INTO `sys_users_roles` VALUES ('6a3114c367b902ccc2fe25915e2e4947', '83c583445cfb3fe53ed0b648a051f9fe', '656273249d267ce94fde3f8ad68a9211');

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `config_id` bigint NOT NULL COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮箱配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_email_config
-- ----------------------------
INSERT INTO `tool_email_config` VALUES ('asdasas12312312', 1, 'jbinfoecard@163.com', 'smtp.163.com', '2EC6D000F14C7E76BF9FF102897E0B52', '465', '王先生', 0, NULL, '无名之辈', NULL, NULL);

-- ----------------------------
-- Table structure for zkb_article
-- ----------------------------
DROP TABLE IF EXISTS `zkb_article`;
CREATE TABLE `zkb_article`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_date` datetime NOT NULL COMMENT '插入时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插入者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `tid` bigint NULL DEFAULT NULL COMMENT '文章ID',
  `readperm` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `authorid` int NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dateline` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `lastpost` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `lastposter` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `views` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `replies` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `digest` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `attachment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dbdateline` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dblastpost` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `details` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '赚客吧-文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zkb_article
-- ----------------------------

-- ----------------------------
-- Table structure for zkb_xgyz
-- ----------------------------
DROP TABLE IF EXISTS `zkb_xgyz`;
CREATE TABLE `zkb_xgyz`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_date` datetime NOT NULL COMMENT '插入时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插入者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `siteId` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '站点ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `mainText` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正文',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '链接',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `imgList` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片列表（逗号分隔）',
  `relTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发帖时间',
  `replyNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复数',
  `readNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '阅读数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '赚客吧-小鸽驿站' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zkb_xgyz
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
