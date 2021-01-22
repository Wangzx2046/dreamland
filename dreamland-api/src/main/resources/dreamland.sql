/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : dreamland

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/01/2021 10:28:04
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
INSERT INTO `sys_quartz_job` VALUES ('0', 'zkbXgyzTask', '0/20 * * * * ? ', b'0', '小鸽驿站-历史删除', 'delHistory', '', '定时删除历史数据', 'caoshuangna', NULL, NULL, b'0', 0, '无名之辈', NULL, '2020-07-30 18:56:35', NULL);
INSERT INTO `sys_quartz_job` VALUES ('1', 'zkbXgyzTask', '0 * * * * ? ', b'0', '小鸽驿站数据抓取', 'CrawlerXgyz', '', '定时抓取小鸽驿站', 'caoshuangna', NULL, NULL, b'0', 0, '无名之辈', NULL, '2020-07-30 18:56:35', NULL);
INSERT INTO `sys_quartz_job` VALUES ('2', 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, b'1', 0, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-05 17:26:19');
INSERT INTO `sys_quartz_job` VALUES ('3', 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '2,6', b'1', 0, NULL, '无名之辈', '2019-09-26 16:44:39', '2020-07-02 21:59:41');
INSERT INTO `sys_quartz_job` VALUES ('5', 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 0, 'admin', NULL, '2020-05-05 20:32:41', NULL);
INSERT INTO `sys_quartz_job` VALUES ('6', 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 0, 'admin', '无名之辈', '2020-05-05 20:35:41', NULL);
INSERT INTO `sys_quartz_job` VALUES ('9b1d07b8391a58b52af151ab05e356c1', 'emailTestTask', '0 59 11 * * ?', b'1', '支付宝消费券', 'SendTOts', '{\"to\":\"443082604@qq.com\",\"text\":\"吃饭-支付宝消费券\"}', '中午提醒', '阿阿斯顿', NULL, NULL, b'1', 0, '无名之辈', '无名之辈', '2020-08-05 14:51:43', NULL);
INSERT INTO `sys_quartz_job` VALUES ('c58dc020042117920ec96dcc6aef1f6b', 'emailTestTask', '7 59 11 * * ?', b'0', '曹双娜打车', 'SendTOts', '{\"to\":\"caoshuangna@e12349.com\",\"text\":\"待办事项：\\n1.往群里发打车券\\n2.提醒大家领支付宝消费券\\n3.接着睡吧\"}', '提醒她发打车助力', 'caoshuangna', NULL, NULL, b'1', 0, '无名之辈', '无名之辈', '2020-07-30 18:56:35', NULL);
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
INSERT INTO `sys_quartz_log` VALUES ('012ae4dc0df6b424112f3a5bce0fe4af', 'zkbXgyzTask', '2021-01-22 00:46:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 118);
INSERT INTO `sys_quartz_log` VALUES ('01559dd5777214018bcad2d97feb192c', 'zkbXgyzTask', '2021-01-21 06:54:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('01db56cb67da6b162da84c619a26bf7e', 'zkbXgyzTask', '2021-01-22 02:24:20', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 5);
INSERT INTO `sys_quartz_log` VALUES ('0270e3806594a4574f3f192ace1100be', 'zkbXgyzTask', '2021-01-21 08:30:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('029dcd708e15578a19afdec3d5c2bc54', 'zkbXgyzTask', '2021-01-21 07:52:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('02b2304ebdaabee12279b29921758a4b', 'zkbXgyzTask', '2021-01-21 09:14:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('030a7a04960e282aaca0791bf2f8db72', 'zkbXgyzTask', '2021-01-21 07:04:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('035cd3bba406d16ffb48abf3a617d99d', 'zkbXgyzTask', '2021-01-21 07:10:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('03b4790a8e68636347246f5b7498eb8f', 'zkbXgyzTask', '2021-01-21 10:08:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 132);
INSERT INTO `sys_quartz_log` VALUES ('0417469048f492c7632a1eab4bd911b1', 'zkbXgyzTask', '2021-01-21 09:48:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('05cab2f69af8cef98cb4299f2358b7f0', 'zkbXgyzTask', '2021-01-21 11:28:21', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('064761d0978a975c8ee6d87dba564e62', 'zkbXgyzTask', '2021-01-21 10:32:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('066f0d2114ee83170ffdbed5ab6950ca', 'zkbXgyzTask', '2021-01-21 07:28:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('06d69e79cbf93988e11e47fa4fab2454', 'zkbXgyzTask', '2021-01-21 08:06:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('06e0a887c05a3f77b0639d1034c92d1b', 'zkbXgyzTask', '2021-01-21 08:31:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 105);
INSERT INTO `sys_quartz_log` VALUES ('071e21cdd9f130b44b48d6665bd547af', 'zkbXgyzTask', '2021-01-21 08:25:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('086a5d57bbbd77fb1f9bb819003fe51c', 'zkbXgyzTask', '2021-01-22 01:04:59', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('08895936b294a83db592dd5040a5bdae', 'zkbXgyzTask', '2021-01-21 07:01:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 136);
INSERT INTO `sys_quartz_log` VALUES ('0937cc16fa8826589a4bce91e3c2722a', 'zkbXgyzTask', '2021-01-21 07:46:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('096863f7e23554de51083303089ed8ea', 'zkbXgyzTask', '2021-01-21 10:16:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('099a4c7fb10af95baeb70e02964aec20', 'zkbXgyzTask', '2021-01-21 07:30:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('09a4c0bbce0f2dce71b84ffecc98830a', 'zkbXgyzTask', '2021-01-21 08:30:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('0a089d14be0c73927c134893f95d3468', 'zkbXgyzTask', '2021-01-21 08:15:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('0b51c298d512e820aa0116f195912728', 'zkbXgyzTask', '2021-01-21 08:33:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('0bd7423a14572697b063922a2c7bc381', 'zkbXgyzTask', '2021-01-21 09:11:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 128);
INSERT INTO `sys_quartz_log` VALUES ('0be946d0f3a9f7ecc8ce23804c9de11c', 'zkbXgyzTask', '2021-01-21 08:23:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('0c243c045d461ebd3078dc57c1882c06', 'zkbXgyzTask', '2021-01-21 08:24:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('0c5c8fe16a8168eaaeedbe9854ec66cf', 'zkbXgyzTask', '2021-01-21 08:43:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('0c62e305e6352de7cc67fb017360eb29', 'zkbXgyzTask', '2021-01-21 07:16:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('0cd8408097c27d264b84374f272b0b3c', 'zkbXgyzTask', '2021-01-21 07:17:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('0cdfded43806f14e617b194873ecacc5', 'zkbXgyzTask', '2021-01-21 10:29:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('0cf186c1c226b3080e1aa54c9b9e21b4', 'zkbXgyzTask', '2021-01-21 09:33:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('0ddff8079930aadf875c055f11e0a253', 'zkbXgyzTask', '2021-01-21 08:03:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('0e1b1dc941d13c0e5dd14a3619659cdd', 'zkbXgyzTask', '2021-01-21 07:59:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('0e4e5448af307cfe5af332297937cb90', 'zkbXgyzTask', '2021-01-21 09:15:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('0e53a08dd1325b2e7456118256001a37', 'zkbXgyzTask', '2021-01-21 10:28:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 255);
INSERT INTO `sys_quartz_log` VALUES ('0e9fe9ca05a42b266fc8b1846dc0e192', 'zkbXgyzTask', '2021-01-21 07:23:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 535);
INSERT INTO `sys_quartz_log` VALUES ('0ea9a7cbc605a4abcd8e9978c158b4fd', 'zkbXgyzTask', '2021-01-21 10:30:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('0edf45821b75221343d164bf935b35d6', 'zkbXgyzTask', '2021-01-21 07:27:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('0ef87057bc2e419a43ae8a4bb36012db', 'zkbXgyzTask', '2021-01-21 06:59:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('0f395da92c6c9fdb91f8c9de4a8fc309', 'zkbXgyzTask', '2021-01-21 10:07:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('0f74f6bb79091dd68c7ebb178be0faa4', 'zkbXgyzTask', '2021-01-21 07:20:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('0fcc66b3b819222aabff4193580aba68', 'zkbXgyzTask', '2021-01-21 11:09:03', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('105239d056a2be5bb2e3255cb274684c', 'zkbXgyzTask', '2021-01-21 11:08:28', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 153);
INSERT INTO `sys_quartz_log` VALUES ('116534097959a30d84c0704dc4920a0a', 'zkbXgyzTask', '2021-01-21 07:18:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('116c1009c1657c8cb2480c0924e0eae8', 'zkbXgyzTask', '2021-01-21 09:46:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('117362935e5f6d700ca9c3caf49ccc71', 'zkbXgyzTask', '2021-01-21 09:30:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 105);
INSERT INTO `sys_quartz_log` VALUES ('1189390033fe386553eec3d381082017', 'zkbXgyzTask', '2021-01-21 08:32:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 121);
INSERT INTO `sys_quartz_log` VALUES ('12493584ca2d7d297a3b24b6c688cd29', 'zkbXgyzTask', '2021-01-21 10:38:38', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('1251fb18344cae3713b3d2bc6019e60f', 'zkbXgyzTask', '2021-01-21 09:03:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('12a05161867d221f7ff8ebc8d6a0f45b', 'zkbXgyzTask', '2021-01-21 07:27:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('12b89b31a8a5dbe73ec0086675e0d84c', 'zkbXgyzTask', '2021-01-21 07:17:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 113);
INSERT INTO `sys_quartz_log` VALUES ('132957fb3d1a7c5dc862cd92e8541ea1', 'zkbXgyzTask', '2021-01-22 02:21:40', '0/20 * * * * ? ', NULL, NULL, '小鸽驿站-历史删除', 'delHistory', '', 16);
INSERT INTO `sys_quartz_log` VALUES ('1345f209354fadd7253ccc04e6e59aba', 'zkbXgyzTask', '2021-01-21 10:23:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('13638f1ca7e56012e5df754f55c91537', 'zkbXgyzTask', '2021-01-21 09:38:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('13a0d3ca7bfdef26b9d41eb2c350a7d6', 'zkbXgyzTask', '2021-01-21 10:25:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('13c74cbe42e5dff26891d3ffca1a76e4', 'zkbXgyzTask', '2021-01-21 09:20:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('13edb17d45f53224d56422efb24e3a36', 'zkbXgyzTask', '2021-01-21 07:14:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('13f291843cba438825f37f508a64f442', 'zkbXgyzTask', '2021-01-22 02:22:40', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 4);
INSERT INTO `sys_quartz_log` VALUES ('14319448b11ea7c1dc7740f1c42ac24b', 'zkbXgyzTask', '2021-01-21 09:36:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('15de5cdcede3cf167f368f428b7d5979', 'zkbXgyzTask', '2021-01-21 07:18:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 120);
INSERT INTO `sys_quartz_log` VALUES ('15edf23977b6efe79ab5c41a9204f17a', 'zkbXgyzTask', '2021-01-21 10:18:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('16b17286b9099c2be35beac609978969', 'zkbXgyzTask', '2021-01-21 08:03:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('17219b7b7e6683768322f7ac779f8f2d', 'zkbXgyzTask', '2021-01-21 10:56:46', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('177c9e69c94204bcf0d9affbe296b0dc', 'zkbXgyzTask', '2021-01-21 06:56:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('17ae2bd3ca10e48ca687f4ec19f1870d', 'zkbXgyzTask', '2021-01-21 08:42:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('1813148a55486e6e3bcc46ee4ca97548', 'zkbXgyzTask', '2021-01-21 09:01:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('181cf4b822966492c18da0163a7a4c96', 'zkbXgyzTask', '2021-01-21 10:08:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('186bb13fd539ae9e8c04a248d52dcedf', 'zkbXgyzTask', '2021-01-21 08:35:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('18d947a33626ece73d1970a916baf292', 'zkbXgyzTask', '2021-01-21 07:06:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('1909325bc86f0660460ab69fa5dbc931', 'zkbXgyzTask', '2021-01-21 09:26:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 409);
INSERT INTO `sys_quartz_log` VALUES ('1a57011ebde11c70e231b96d5d571138', 'zkbXgyzTask', '2021-01-21 07:20:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('1a6e377c813f654674978ac59d6fe4f2', 'zkbXgyzTask', '2021-01-21 09:55:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('1abbd669c7437865d3393e700758d9ba', 'zkbXgyzTask', '2021-01-21 07:05:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('1aecbdb703d7714d7ceba087c6304e1d', 'zkbXgyzTask', '2021-01-22 01:23:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 212);
INSERT INTO `sys_quartz_log` VALUES ('1b50244a564b409cbaa428db1782afe2', 'zkbXgyzTask', '2021-01-21 07:32:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('1bf0a9ce2f1c0fabca8fefaa5fab0cc6', 'zkbXgyzTask', '2021-01-21 07:51:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('1ce4966d1336548ca28ebc411467ac32', 'zkbXgyzTask', '2021-01-21 09:57:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('1d46897f1c4a048c7ebf254d9fb33e81', 'zkbXgyzTask', '2021-01-21 07:55:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('1dcce8a41e0fdb0a146a394067443b83', 'zkbXgyzTask', '2021-01-21 07:02:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('1dfa32063e57db0435c5668525b6253b', 'zkbXgyzTask', '2021-01-21 10:13:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('1e11d6ad0a166f40c0f28568aad0c4a9', 'zkbXgyzTask', '2021-01-22 01:21:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 115);
INSERT INTO `sys_quartz_log` VALUES ('1e19b0f05a29a421ae0d70f568cd48b7', 'zkbXgyzTask', '2021-01-21 07:15:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('1e374686ec33042a4440d6d0c4b548ce', 'zkbXgyzTask', '2021-01-21 08:54:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 116);
INSERT INTO `sys_quartz_log` VALUES ('1e4e87cfc3f72d6dafbb56310d0b4129', 'zkbXgyzTask', '2021-01-21 07:41:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('1e5735ea2c7fcbccf81be7971d9486a8', 'zkbXgyzTask', '2021-01-21 09:58:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('1e654471624f28fd84128b1841dbf64d', 'zkbXgyzTask', '2021-01-22 01:09:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('1e69c6f96e35851e04eca22e9f514868', 'zkbXgyzTask', '2021-01-21 10:47:24', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('1ea9b056a9357d17a646c7ac79d8d5ec', 'zkbXgyzTask', '2021-01-21 07:51:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('1eda49f9afa6a87177fbf85e24bed02c', 'zkbXgyzTask', '2021-01-21 07:48:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('1f174f33899f60baf4ffa6046619d242', 'zkbXgyzTask', '2021-01-21 07:00:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 152);
INSERT INTO `sys_quartz_log` VALUES ('1f5ee0eec888fed13c46080dade99be2', 'zkbXgyzTask', '2021-01-21 07:08:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('201c148fcbc80336b6f0b59810806414', 'zkbXgyzTask', '2021-01-21 07:40:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('2068e6ba24557a968c02692edce03a0e', 'zkbXgyzTask', '2021-01-21 08:48:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('210b61ba3b17c16fc7cb4ea65a0bce56', 'zkbXgyzTask', '2021-01-21 09:44:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('216b4ca8f027aee28c1dda56f5dbfa56', 'zkbXgyzTask', '2021-01-21 09:59:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('218053142a7a9e631eb40609c5d07d6f', 'zkbXgyzTask', '2021-01-21 09:28:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('22bbf4c2a04c9a61bbda83891ec3f9be', 'zkbXgyzTask', '2021-01-21 10:24:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('22ca97f25dd133a7a02b0793c774bf13', 'zkbXgyzTask', '2021-01-21 09:41:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('22cef2d522fa6f85d1c1ee48c5350358', 'zkbXgyzTask', '2021-01-21 08:51:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('22e023379a693ad63eb06850a87bd46a', 'zkbXgyzTask', '2021-01-21 07:35:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('22e1a309c90188d489b3dfcacabcbe7b', 'zkbXgyzTask', '2021-01-22 01:20:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 167);
INSERT INTO `sys_quartz_log` VALUES ('22f59ecf6a435f0cc26e8cb1c23c91c4', 'zkbXgyzTask', '2021-01-21 09:02:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('23c05e6ab59efbe5010d5b34f17ac1d9', 'zkbXgyzTask', '2021-01-21 11:12:33', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('23c15cb8823c7d822b2801f59f804717', 'zkbXgyzTask', '2021-01-21 10:57:56', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('23e0efcc1127ef4532ba6054a1d40b50', 'zkbXgyzTask', '2021-01-21 08:57:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('23ee60a628f31d0d30b8eec5419dbf42', 'zkbXgyzTask', '2021-01-21 09:19:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 111);
INSERT INTO `sys_quartz_log` VALUES ('24111e91671fa974f8fae674096330cf', 'zkbXgyzTask', '2021-01-21 11:14:54', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('2453e1bcb3b962912262c0fcee76155c', 'zkbXgyzTask', '2021-01-21 07:52:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 105);
INSERT INTO `sys_quartz_log` VALUES ('2487912768f6405cbd05f431ae3b83a2', 'zkbXgyzTask', '2021-01-21 09:04:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('24e9010da02da46a29e63c029f334212', 'zkbXgyzTask', '2021-01-21 09:35:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('25121d060cf1e46a3b83dedcaa41d6c6', 'zkbXgyzTask', '2021-01-21 09:56:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 134);
INSERT INTO `sys_quartz_log` VALUES ('25d5e9eb0ef4f957db480ef254af15d9', 'zkbXgyzTask', '2021-01-21 10:45:04', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 121);
INSERT INTO `sys_quartz_log` VALUES ('25fb5d8e845bdfa9b85e1af7b437a452', 'zkbXgyzTask', '2021-01-21 07:07:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('260fc2a0b4cb440c39cbb93e8880a2e5', 'zkbXgyzTask', '2021-01-21 07:28:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('2658f8f0909161be82a25586b0b28781', 'zkbXgyzTask', '2021-01-21 07:22:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('267ff7b6b04f241ca840713e3b36ef76', 'zkbXgyzTask', '2021-01-21 09:45:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('270cfb7295cc91ff45b49858632ac66b', 'zkbXgyzTask', '2021-01-21 10:02:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('272f02fd2deaa1ea76bffc2d54117e72', 'zkbXgyzTask', '2021-01-21 07:06:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('273248fdef9fc2af619d6053dfcc8fd8', 'zkbXgyzTask', '2021-01-21 09:32:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('273cc514044d4a80f8afb80cf29c4de0', 'zkbXgyzTask', '2021-01-21 07:03:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('27a3887ffd9f6cdf5fb921a820cf9996', 'zkbXgyzTask', '2021-01-21 10:03:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('28ca855abbda1a7aea89394c03b39710', 'zkbXgyzTask', '2021-01-21 09:38:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('28e63621c417e1d881925063d8e47d53', 'zkbXgyzTask', '2021-01-21 09:59:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('291a8a36087bddbe739c05142f1559ff', 'zkbXgyzTask', '2021-01-21 09:41:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('293669fa68e2ebb21b9402fd13adf105', 'zkbXgyzTask', '2021-01-21 09:27:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 152);
INSERT INTO `sys_quartz_log` VALUES ('298504d2c339df3c213b9ad8f13d3257', 'zkbXgyzTask', '2021-01-21 09:26:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('29b1371bb82130a9e0f1af3e048bc40a', 'zkbXgyzTask', '2021-01-21 09:09:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('29ea41288a5da8d9e1ef89aaca1f4a63', 'zkbXgyzTask', '2021-01-21 08:10:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('2a2c8ce43ec199a698b95627d208e92b', 'zkbXgyzTask', '2021-01-22 00:47:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('2a2d14b730dc9b2ccc56c8236f5ed577', 'zkbXgyzTask', '2021-01-21 09:08:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('2ac6d203fa1c6609d3d7949f9771302f', 'zkbXgyzTask', '2021-01-22 00:47:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('2adf94f3d0c4889c31dca3736838c3af', 'zkbXgyzTask', '2021-01-21 09:25:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 1199);
INSERT INTO `sys_quartz_log` VALUES ('2b1d5c806cbe6c0f4f464c66f3557365', 'zkbXgyzTask', '2021-01-22 01:21:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 130);
INSERT INTO `sys_quartz_log` VALUES ('2ba9a709a38a5589834b650ecf23ae1d', 'zkbXgyzTask', '2021-01-21 07:41:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('2bd856fc740a48384083aa632b0777c3', 'zkbXgyzTask', '2021-01-21 07:56:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('2bee1f12b5999393206c38a3c617a728', 'zkbXgyzTask', '2021-01-21 08:56:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('2c096c258acb6c77f3e88fec0b17e04c', 'zkbXgyzTask', '2021-01-21 10:00:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('2c7861373a1fdee6eb935981e4521554', 'zkbXgyzTask', '2021-01-21 10:26:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 374);
INSERT INTO `sys_quartz_log` VALUES ('2d581f9b697a42c67b70906e67f1a95e', 'zkbXgyzTask', '2021-01-21 07:47:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('2d9203fff0c93f9e251b3274f6b7a4f8', 'zkbXgyzTask', '2021-01-21 09:05:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('2da5e0ea936e6e565bc711ca9f75a6af', 'zkbXgyzTask', '2021-01-21 08:39:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('2e0861491b4199d7460d295528dfe196', 'zkbXgyzTask', '2021-01-21 09:19:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('2e2327d85baada9c782ad7a6274a3347', 'zkbXgyzTask', '2021-01-21 09:04:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('2e7836e15313258d48a18a37d023b7bb', 'zkbXgyzTask', '2021-01-21 11:04:22', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('2f40896f7645aa205271105aa1587056', 'zkbXgyzTask', '2021-01-21 07:23:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('2f8e485203ad304f13439fdce94d0925', 'zkbXgyzTask', '2021-01-22 00:40:15', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('2fb82323169844e812e1c23eaf1e5d29', 'zkbXgyzTask', '2021-01-21 06:55:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('2fd66bda96b9db428f006143d37c0034', 'zkbXgyzTask', '2021-01-22 00:57:23', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('2fe5efaa8437997835eada4cda1bbdc9', 'zkbXgyzTask', '2021-01-22 01:23:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 213);
INSERT INTO `sys_quartz_log` VALUES ('2ff60cfb81dd93c6360b609a345b4d5e', 'zkbXgyzTask', '2021-01-21 10:16:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('3189d6cd8565211f6e91a23637d0419a', 'zkbXgyzTask', '2021-01-21 09:47:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('3198ff242e0191909288e6d95d1e0723', 'zkbXgyzTask', '2021-01-21 09:38:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('320435c5b0e1c49430fb085e907642b0', 'zkbXgyzTask', '2021-01-21 11:17:14', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('32525ab48e98a7e5b2ab179eb70b11e8', 'zkbXgyzTask', '2021-01-21 10:06:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('329fc51ac9eb7fbd7969fa7387034af2', 'zkbXgyzTask', '2021-01-21 07:43:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('32f03284823f4b5ad929a1c237ee0c96', 'zkbXgyzTask', '2021-01-21 09:29:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('3352e48afcc1fa498c3b5ee3773c8959', 'zkbXgyzTask', '2021-01-22 00:53:52', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 1122);
INSERT INTO `sys_quartz_log` VALUES ('33cd917e5cc4e90f30428350fdd20228', 'zkbXgyzTask', '2021-01-21 07:48:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('33e1aff1359fc91a033b88cc38ffc853', 'zkbXgyzTask', '2021-01-21 07:29:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('3461a0a469ec3fda9ae1f533b733b39e', 'zkbXgyzTask', '2021-01-21 10:17:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 203);
INSERT INTO `sys_quartz_log` VALUES ('34d4e063bc98f0048383046c9411f68e', 'zkbXgyzTask', '2021-01-21 08:45:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('350c37167e0f7e860f4538934f0f20ba', 'zkbXgyzTask', '2021-01-21 06:45:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 51);
INSERT INTO `sys_quartz_log` VALUES ('3512bca1b748fee1dc44cf45c45f6378', 'zkbXgyzTask', '2021-01-21 10:13:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('352a763830d975aa070075d731596284', 'zkbXgyzTask', '2021-01-21 08:01:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('354b5fff49b2458782749c9306299948', 'zkbXgyzTask', '2021-01-21 11:40:38', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('3649fa034107e8d170ff613528f46c74', 'zkbXgyzTask', '2021-01-21 11:30:41', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('371b6d1f25706084c1d40d5f0ce75ca6', 'zkbXgyzTask', '2021-01-21 08:54:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('37873e6f529d2f0d139380a1031dd0fb', 'zkbXgyzTask', '2021-01-21 08:59:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('3835b9621e78dfcad2c4d63752d0d6c7', 'zkbXgyzTask', '2021-01-21 08:19:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('3968760bd721e01a3d160b1482b65a92', 'zkbXgyzTask', '2021-01-21 08:50:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('39afa258bd33a95c3c02d44804cc8667', 'zkbXgyzTask', '2021-01-21 07:24:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('39b0956eecfe68490432dcd5a42129ca', 'zkbXgyzTask', '2021-01-22 01:23:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 196);
INSERT INTO `sys_quartz_log` VALUES ('3b018e59f5c850bcf3623cd2fa960552', 'zkbXgyzTask', '2021-01-21 10:08:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('3b25af94f2b0708f3274b1398a513c8b', 'zkbXgyzTask', '2021-01-21 07:36:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('3b33c82a43e78fbe52958f88ecd68b46', 'zkbXgyzTask', '2021-01-21 11:37:08', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('3b795dd2f1538f025fd5c6e2cdf5aae0', 'zkbXgyzTask', '2021-01-21 08:26:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('3b981498b2b3e888085dce62ace9feb1', 'zkbXgyzTask', '2021-01-21 07:08:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('3bb5d818127b598ca57330d9e0797694', 'zkbXgyzTask', '2021-01-21 10:04:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('3c18447fd9fdb63752c5284fb9890c1d', 'zkbXgyzTask', '2021-01-21 08:40:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('3d52edf7bbf393eccbb08dba615c4081', 'zkbXgyzTask', '2021-01-21 08:58:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('3d60bc17da3418b6397bb43dd868fc7c', 'zkbXgyzTask', '2021-01-21 09:49:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('3ec41ba59635673b1d8edad254584de2', 'zkbXgyzTask', '2021-01-21 11:09:38', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('3efb8ed2d5dec51c7eb3cda8d47842aa', 'zkbXgyzTask', '2021-01-21 07:24:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 115);
INSERT INTO `sys_quartz_log` VALUES ('3f2886b0b2ac9545c22d3c1441fe20aa', 'zkbXgyzTask', '2021-01-21 08:12:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('3fd12447d1aa65b25534c9f114da0bef', 'zkbXgyzTask', '2021-01-22 01:10:50', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 108);
INSERT INTO `sys_quartz_log` VALUES ('428dd9721edc81b6ae4f229ad8d1f6c8', 'zkbXgyzTask', '2021-01-22 00:51:32', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 127);
INSERT INTO `sys_quartz_log` VALUES ('42d288e36b07f0fbbfb59f843a6c7bc9', 'zkbXgyzTask', '2021-01-21 10:12:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('435af0c24b207392ba08df269b7f5685', 'zkbXgyzTask', '2021-01-21 07:31:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('44095c3d751a14a2d145df312de9e980', 'zkbXgyzTask', '2021-01-21 08:07:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('4416f82911f5c7aff17412cd72bcdc83', 'zkbXgyzTask', '2021-01-21 09:08:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('4461ec034ba2f8dd112988d3c774afa6', 'zkbXgyzTask', '2021-01-21 08:10:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('44961633ccda889ea82aee2211ec46ec', 'zkbXgyzTask', '2021-01-21 07:14:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 227);
INSERT INTO `sys_quartz_log` VALUES ('44abeb6350969fd9dd9b14e66a0520d4', 'zkbXgyzTask', '2021-01-21 07:09:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('45ba51ddfb00b618bdbcd345d0124fd1', 'zkbXgyzTask', '2021-01-21 08:47:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('4638b1c05d6c3de8488759847013da34', 'zkbXgyzTask', '2021-01-21 07:41:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('465c1216eb72bfe5facb31ad6cd95c4b', 'zkbXgyzTask', '2021-01-21 09:58:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('468d143e269ab880b3bc4d4d720f6b05', 'zkbXgyzTask', '2021-01-22 00:35:34', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('468fc1c330aa30cdc1a4d774273bc1ec', 'zkbXgyzTask', '2021-01-21 08:49:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('46924ea3e51624a30dfd4d8fc6355bd1', 'zkbXgyzTask', '2021-01-21 07:30:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('475479a00b43139a1b3be6ba3239052d', 'zkbXgyzTask', '2021-01-21 09:54:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 241);
INSERT INTO `sys_quartz_log` VALUES ('477a1a1f1ce99f0a1d9d0bfa3a982c4d', 'zkbXgyzTask', '2021-01-21 09:37:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('477d07a25436388f56887ce019f5e592', 'zkbXgyzTask', '2021-01-21 09:10:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('478a158a9b1a12afa51af7f623c011c7', 'zkbXgyzTask', '2021-01-21 09:00:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('478fc21f34559ab126ff9cf79dac68fa', 'zkbXgyzTask', '2021-01-21 07:54:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('47c2c3b2d38d1051451cb9823949e886', 'zkbXgyzTask', '2021-01-21 07:26:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('48952200dcbd67c89ebbf1f050f54838', 'zkbXgyzTask', '2021-01-21 09:24:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 40469);
INSERT INTO `sys_quartz_log` VALUES ('4897436c478d4a73ab4b20011762d3e9', 'zkbXgyzTask', '2021-01-21 10:10:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('48db561421fd3121310657ed813090ec', 'zkbXgyzTask', '2021-01-21 09:43:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('48f52b227773fab4f0c499fdfee41164', 'zkbXgyzTask', '2021-01-21 07:09:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('490784a8c1663fa30a52a74aa97ab9b0', 'zkbXgyzTask', '2021-01-21 08:02:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('49120fb5235fa5b9d363d915101f9d59', 'zkbXgyzTask', '2021-01-21 08:55:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('49bd2f89edb10dc4338f1ea174d090f3', 'zkbXgyzTask', '2021-01-21 07:00:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('49c0826fc563272004178ec5e09f8813', 'zkbXgyzTask', '2021-01-21 08:42:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('4a492982c6a448a88ed3cd2e6a685b4d', 'zkbXgyzTask', '2021-01-21 07:56:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('4a77deb1fb5c454cfcba3737db0eba85', 'zkbXgyzTask', '2021-01-21 08:41:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('4aa0b09d98c96c5eeb9485f4e87e9a27', 'zkbXgyzTask', '2021-01-21 09:01:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('4ad78f72905351686dfd2dc106c2b74a', 'zkbXgyzTask', '2021-01-21 09:46:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('4b60154e274d51cd1584e4f0b62cc485', 'zkbXgyzTask', '2021-01-21 09:37:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('4bc8679a7d85d489520987f1923ed79e', 'zkbXgyzTask', '2021-01-21 08:01:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('4bdf0e23c87f4c1224f754091f2ae003', 'zkbXgyzTask', '2021-01-21 06:58:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('4c54b5a495aa84a91fcba5d640f8024a', 'zkbXgyzTask', '2021-01-21 08:59:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('4ce85346a9f367de173ceba795fccb0f', 'zkbXgyzTask', '2021-01-21 07:53:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 121);
INSERT INTO `sys_quartz_log` VALUES ('4d1105505ac74de66375453eea7563ec', 'zkbXgyzTask', '2021-01-21 07:53:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('4d4ba5dd2a77ce0f0c71d26beac1f223', 'zkbXgyzTask', '2021-01-21 07:26:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('4d51349f1143859621ac83f4942ae128', 'zkbXgyzTask', '2021-01-21 08:29:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('4dc154d6d861b961d9a68a86f375be80', 'zkbXgyzTask', '2021-01-21 06:56:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('4dd14c540a1c8b1006bc7111c61510b6', 'zkbXgyzTask', '2021-01-21 08:56:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('4e247c00585f83f3125a94a7b58cb13d', 'zkbXgyzTask', '2021-01-21 08:46:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('4f2905c3c224ee1ca8ad68aff2857931', 'zkbXgyzTask', '2021-01-21 07:58:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('4f4377513ad9765164b741347f0a348e', 'zkbXgyzTask', '2021-01-21 08:36:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('4fb9a96856f42818b36756d9db434b3e', 'zkbXgyzTask', '2021-01-22 00:55:38', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('5043c17c5e7953095dc846041f7dde47', 'zkbXgyzTask', '2021-01-21 08:23:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('508cf4b857a5da3f19d9aa82b5b4fb3b', 'zkbXgyzTask', '2021-01-21 09:05:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('50aced394ed1cca4d3f4ba80b50ba1b3', 'zkbXgyzTask', '2021-01-21 08:36:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('50b84fa9763bf5468bd9ab6c91bc00a6', 'zkbXgyzTask', '2021-01-21 09:12:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('50ce85ab88b1bb83b79b266a0b3f013a', 'zkbXgyzTask', '2021-01-21 09:07:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('511b1a889e76794d5309d60313b03def', 'zkbXgyzTask', '2021-01-21 09:58:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('51a1b4f56e1e371b56e9c4fa01ca437b', 'zkbXgyzTask', '2021-01-21 08:21:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('51d95063429dd28a01771beb6da39ec5', 'zkbXgyzTask', '2021-01-21 08:35:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('52104551c7acd2de395c10650031712f', 'zkbXgyzTask', '2021-01-21 08:33:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 119);
INSERT INTO `sys_quartz_log` VALUES ('523090f7716e6c0971e04297bc51a8b6', 'zkbXgyzTask', '2021-01-21 07:45:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('534c9707828693ebff83d1d23d002c43', 'zkbXgyzTask', '2021-01-21 08:57:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('5367be8a4d3a1a90a73a6c0cd8bc10e6', 'zkbXgyzTask', '2021-01-21 07:20:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('5420915b67e842b0d4af126adec698f1', 'zkbXgyzTask', '2021-01-21 08:32:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('5468aff5017be03ce6e53d3af76c8ebc', 'zkbXgyzTask', '2021-01-21 08:00:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('5493ea33f46cd1498d018e8ab15216a4', 'zkbXgyzTask', '2021-01-21 08:30:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('5511c72acfec1f7dadc1b5040ef26002', 'zkbXgyzTask', '2021-01-21 07:33:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('564f28435d75fb97ad4e0e5fbad21c2c', 'zkbXgyzTask', '2021-01-21 08:20:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('5680982a4e4061f10e002855ba696836', 'zkbXgyzTask', '2021-01-21 08:08:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('568853402b652893872da3084cb41380', 'zkbXgyzTask', '2021-01-21 08:17:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('57189c515834dfc976fea115b0444a07', 'zkbXgyzTask', '2021-01-21 06:58:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('5735b0cb51c3a935227432f9356b6351', 'zkbXgyzTask', '2021-01-21 09:17:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('574916fb828ee79ab8af66a62e2bb32d', 'zkbXgyzTask', '2021-01-21 08:11:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('57c0d57062be548b09c4760e553ea695', 'zkbXgyzTask', '2021-01-21 10:24:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('580c2a764fae7f6bf1fb265717ffe1a4', 'zkbXgyzTask', '2021-01-21 09:40:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 213);
INSERT INTO `sys_quartz_log` VALUES ('5818d2eb8aac29a21c4acefd8922efcd', 'zkbXgyzTask', '2021-01-22 02:24:00', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 13);
INSERT INTO `sys_quartz_log` VALUES ('582c26c008471beb2bb677b3e837f2e1', 'zkbXgyzTask', '2021-01-21 07:46:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('58510252fde77bc144da9e92d2d6b318', 'zkbXgyzTask', '2021-01-21 06:53:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 258);
INSERT INTO `sys_quartz_log` VALUES ('58c48197a035e21ad978581c343ecee5', 'zkbXgyzTask', '2021-01-21 07:25:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 115);
INSERT INTO `sys_quartz_log` VALUES ('59731343468d5df638939083e394dc6f', 'zkbXgyzTask', '2021-01-21 09:50:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 108);
INSERT INTO `sys_quartz_log` VALUES ('59987b572c25035cba1661debc219bf3', 'zkbXgyzTask', '2021-01-21 08:59:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('59e364179f16e98f72326d3ba80bc317', 'zkbXgyzTask', '2021-01-21 10:17:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('5b98cc18713436e67092935e7b599828', 'zkbXgyzTask', '2021-01-21 10:20:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('5bb48c9a09e96cdd565b46a8b5ac9efe', 'zkbXgyzTask', '2021-01-21 09:00:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('5bddd23a524b07319c8776c9023fd2a9', 'zkbXgyzTask', '2021-01-21 09:28:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('5bde1a26c839eb7dc838383b45e3733f', 'zkbXgyzTask', '2021-01-21 07:15:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('5c621e4f8836e87737376712ed08560c', 'zkbXgyzTask', '2021-01-21 10:22:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 468);
INSERT INTO `sys_quartz_log` VALUES ('5d580ed6e7adc545c6e408657740ff66', 'zkbXgyzTask', '2021-01-21 09:17:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('5d71baf336bf19fec968a135a2a1f41a', 'zkbXgyzTask', '2021-01-21 09:55:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('5d78a32d66d021448f18aa6baa34f4f7', 'zkbXgyzTask', '2021-01-22 01:00:18', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 118);
INSERT INTO `sys_quartz_log` VALUES ('5d7dd114f926bb648d7bb7b2b3b62362', 'zkbXgyzTask', '2021-01-21 08:53:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('5d8217068b537c957e95da7c91d1b1c1', 'zkbXgyzTask', '2021-01-21 09:46:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('5d99d3be2e64fff7850859581c801705', 'zkbXgyzTask', '2021-01-21 08:37:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('5e0e9b3526662faaf16b9d237a2724e3', 'zkbXgyzTask', '2021-01-21 10:25:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('5ea2c67ce8d125c88fc33b32e9e0b769', 'zkbXgyzTask', '2021-01-21 10:06:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('5eb628a33e5c58d5db41f41e437247e3', 'zkbXgyzTask', '2021-01-21 10:43:53', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('5ec49842e0c8c1ddbad6356e444bc2c7', 'zkbXgyzTask', '2021-01-21 08:53:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 108);
INSERT INTO `sys_quartz_log` VALUES ('5ee21f86946f44821da00a72b65d48a3', 'zkbXgyzTask', '2021-01-21 07:27:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('5f742027f830386d2f2f59b25dfbae28', 'zkbXgyzTask', '2021-01-21 09:05:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('5f824f947537e0798db8ec7ae14fe639', 'zkbXgyzTask', '2021-01-21 09:29:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('5f877bb828d0bcc95c380eda00c5a6f9', 'zkbXgyzTask', '2021-01-21 09:50:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('5f9457ff916482631314d6781a3f3b9f', 'zkbXgyzTask', '2021-01-21 07:19:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 137);
INSERT INTO `sys_quartz_log` VALUES ('5feaca5856b42587efd69f950875ca89', 'zkbXgyzTask', '2021-01-21 10:05:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('601b0c6a5b9d95e0ae0eb6e3cdefcd65', 'zkbXgyzTask', '2021-01-21 07:22:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('602650532ff9ccc19a515829aa2f8e83', 'zkbXgyzTask', '2021-01-21 09:52:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 415);
INSERT INTO `sys_quartz_log` VALUES ('605b9718f27c11770ddfa7a1f0293df0', 'zkbXgyzTask', '2021-01-21 11:06:42', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('60804317c1f3d97e755727d94a19d398', 'zkbXgyzTask', '2021-01-21 08:14:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('60bc6af2f96fdbabfcca86e618404492', 'zkbXgyzTask', '2021-01-21 07:55:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('60d7fa36f3d02d1ec4837ca494ed68c8', 'zkbXgyzTask', '2021-01-21 07:34:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('6123a5b2814c8f193c48c81cbd5aaf65', 'zkbXgyzTask', '2021-01-21 07:32:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 112);
INSERT INTO `sys_quartz_log` VALUES ('615c03162e2b7f70526280fc59febdba', 'zkbXgyzTask', '2021-01-21 09:12:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('61cfbf176b660413e8b865ee6485dc02', 'zkbXgyzTask', '2021-01-21 06:59:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('61d15d8295b025e1f8a567ae9bd82fae', 'zkbXgyzTask', '2021-01-21 10:05:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('61e1e58cd17d1f50847c6e8d3f44401a', 'zkbXgyzTask', '2021-01-22 02:23:00', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 5);
INSERT INTO `sys_quartz_log` VALUES ('637ba16c748e5f22f34f311c01190094', 'zkbXgyzTask', '2021-01-21 10:15:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('63fd6e0959e45aaf67927b5f8ed1989a', 'zkbXgyzTask', '2021-01-21 09:06:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('63ffd8c08dcbdb0d0ff1561a9991c4f2', 'zkbXgyzTask', '2021-01-21 08:58:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('64b7e02dee48a9bc82a0a790c7188035', 'zkbXgyzTask', '2021-01-21 08:51:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('64d399316a265fab5f16a9dea5c2f178', 'zkbXgyzTask', '2021-01-21 10:14:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('65160e8e75c4feb2b7c3d0573d4a1c9a', 'zkbXgyzTask', '2021-01-21 10:19:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('651f71e2afc94ff428b5b6963350c2a0', 'zkbXgyzTask', '2021-01-21 08:28:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('65766b32e1bd9752a645b6ed4ae10624', 'zkbXgyzTask', '2021-01-21 08:22:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('6586467ef9df37f194ad4d1d47f11624', 'zkbXgyzTask', '2021-01-21 08:08:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('65f1999c4c766d1a003df18ed3b0ee4a', 'zkbXgyzTask', '2021-01-21 08:27:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('663e3626025eac2c6fabd8471c2ad362', 'zkbXgyzTask', '2021-01-21 08:02:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('667dfe48457a06d5a40696bea2ff8841', 'zkbXgyzTask', '2021-01-21 08:19:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('66d18f31dcf7d1caf5ef1ebf7a679c23', 'zkbXgyzTask', '2021-01-21 07:54:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('670178f18af07456009c528a333e03ce', 'zkbXgyzTask', '2021-01-21 08:18:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('679d31fa86cabdc8c8cc0747487f19f5', 'zkbXgyzTask', '2021-01-21 09:31:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('6864620eddc640f97cdb29f2fbc17b4d', 'zkbXgyzTask', '2021-01-21 10:31:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('688a20f4e6e0683db4b50fe27926d7d1', 'zkbXgyzTask', '2021-01-21 10:36:17', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 112);
INSERT INTO `sys_quartz_log` VALUES ('68e070e4127c0ea0a5fab5b2389baee1', 'zkbXgyzTask', '2021-01-21 09:13:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('68f8e1e74dfd242e87fbe26f65e37bd0', 'zkbXgyzTask', '2021-01-21 08:17:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('695af9aec754aad18bfb8efcc4be4a4e', 'zkbXgyzTask', '2021-01-21 08:46:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('6a0cfe76624c61326094212bb1079768', 'zkbXgyzTask', '2021-01-21 08:20:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('6a4f4c477370d833145456f489370d6a', 'zkbXgyzTask', '2021-01-21 09:18:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 69);
INSERT INTO `sys_quartz_log` VALUES ('6a5ce9f889d00561fbc00b5cc77c87e0', 'zkbXgyzTask', '2021-01-21 07:40:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('6a6c5d00644f92e0cafee5a94882bea6', 'zkbXgyzTask', '2021-01-21 09:39:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('6ae053b4f13ccb6b2deb33121b4d02ac', 'zkbXgyzTask', '2021-01-21 08:28:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('6b7d9dac996dfcf296dda90fc51b211a', 'zkbXgyzTask', '2021-01-21 08:43:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('6c4aa8c234816c2095c2a95f7ede4a1a', 'zkbXgyzTask', '2021-01-22 01:07:55', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('6c5a1dbe55af933f7462812a1a64bf09', 'zkbXgyzTask', '2021-01-21 09:45:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('6c6dad772cf56d6153282dd5d2ceed05', 'zkbXgyzTask', '2021-01-21 09:51:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('6d2852399d21c62395b4d170267e8216', 'zkbXgyzTask', '2021-01-21 08:04:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('6dbd0d1f81ee23d701e2b6d0afeeb731', 'zkbXgyzTask', '2021-01-22 02:22:20', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 4);
INSERT INTO `sys_quartz_log` VALUES ('6e1c44cefa1673066219a2f2d0c073ab', 'zkbXgyzTask', '2021-01-22 00:46:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('6ee52c2206f02fe88cfcf360d33e01c1', 'zkbXgyzTask', '2021-01-22 00:35:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('6f3f51a8a86bc2c3555c887eaa5805e8', 'zkbXgyzTask', '2021-01-21 10:23:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 293);
INSERT INTO `sys_quartz_log` VALUES ('6fccdbcf3619e7760f47cb506554ecf9', 'zkbXgyzTask', '2021-01-21 07:10:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('6fe3f7d019263d0a0631bf088cb5b4aa', 'zkbXgyzTask', '2021-01-21 08:09:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('6ffac35ea855231eb0a59d25df340426', 'zkbXgyzTask', '2021-01-21 07:36:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('703a566ead335b4b690a14e4ac6c9fa5', 'zkbXgyzTask', '2021-01-21 09:21:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('7042b5337f289798284b4af511041270', 'zkbXgyzTask', '2021-01-21 06:55:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('707d9bf781a6c0de72ea6d5aeb0dc8bd', 'zkbXgyzTask', '2021-01-21 07:58:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 108);
INSERT INTO `sys_quartz_log` VALUES ('70b388384d770f19acd17c51a0295ae3', 'zkbXgyzTask', '2021-01-21 11:25:26', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('711370181447a827f16413ff1f4e8c5f', 'zkbXgyzTask', '2021-01-21 09:24:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 1097);
INSERT INTO `sys_quartz_log` VALUES ('7126b189ee8167269127d8dba8c9c291', 'zkbXgyzTask', '2021-01-21 10:01:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('714e4a03b546371c37f2df57cdec32f5', 'zkbXgyzTask', '2021-01-21 07:16:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('7218dda9581d40502aaae071db837991', 'zkbXgyzTask', '2021-01-21 07:14:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('72a5980d453ae00276588b1370c74fe8', 'zkbXgyzTask', '2021-01-21 08:29:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('733541f86f5a9062b0f9a676f700defe', 'zkbXgyzTask', '2021-01-21 07:28:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('73678d94d94d956cf9557311f82cf538', 'zkbXgyzTask', '2021-01-21 09:41:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('7434c26adc2edd777f7bea658866f745', 'zkbXgyzTask', '2021-01-21 09:17:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('748471fa1413fceef438c498c4c5cb85', 'zkbXgyzTask', '2021-01-21 09:10:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('7561700fd1167ee901e9b863a7c89bad', 'zkbXgyzTask', '2021-01-21 08:52:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('75d12e2f0bbefd842cfd2ceb19643378', 'zkbXgyzTask', '2021-01-21 06:57:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('75db676eb4a90167059d592f96c4da63', 'zkbXgyzTask', '2021-01-22 01:06:44', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('75e4935021b1292b532784d28b1f2600', 'zkbXgyzTask', '2021-01-21 07:19:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('76052dd58071621e422d8189732602ea', 'zkbXgyzTask', '2021-01-21 10:15:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('7655b261535b5c2ff8f0035b5a83ad9b', 'zkbXgyzTask', '2021-01-21 09:37:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('768c3063fdc1dbece89d32958f89c285', 'zkbXgyzTask', '2021-01-21 07:26:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('768f74f27292b9466db902e8a74199f0', 'zkbXgyzTask', '2021-01-21 07:42:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('76b290d57f7af90e92d55f462c7f7560', 'zkbXgyzTask', '2021-01-21 08:38:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('773c363786d5a9d02e5df263dff5aaf6', 'zkbXgyzTask', '2021-01-21 07:37:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('7796227a29034f90a25e3107cf5410b9', 'zkbXgyzTask', '2021-01-21 11:23:05', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('77b6f541853c60f57db0ae603998207d', 'zkbXgyzTask', '2021-01-21 10:31:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('77d7ad97515fffefb4c5617e93c87a5d', 'zkbXgyzTask', '2021-01-21 07:56:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('7828b36ae4f2b30463d602a7765268f0', 'zkbXgyzTask', '2021-01-21 08:17:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 119);
INSERT INTO `sys_quartz_log` VALUES ('782918564f88086f17d8fc23da60e954', 'zkbXgyzTask', '2021-01-21 07:07:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('783d744b26bb3a43ed61cfeb59f975b9', 'zkbXgyzTask', '2021-01-21 08:44:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('794ac554be28c3f8079ceb7fd6bb0939', 'zkbXgyzTask', '2021-01-21 10:48:34', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('797c565d4df6a10a29cec2daf01dedec', 'zkbXgyzTask', '2021-01-21 08:41:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('79d5c053f1ff30d864a29391cd0cfdfd', 'zkbXgyzTask', '2021-01-21 11:24:15', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('7a19ec0b99a92171b07accdb0ba6d54f', 'zkbXgyzTask', '2021-01-21 09:49:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 267);
INSERT INTO `sys_quartz_log` VALUES ('7a52f2d58e90e7ef30f71e4ed93276d2', 'zkbXgyzTask', '2021-01-21 08:07:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('7acb326dbe1fcdd79573f183d29f33cb', 'zkbXgyzTask', '2021-01-21 09:28:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 1491);
INSERT INTO `sys_quartz_log` VALUES ('7ad03ca4011b018886374333cc0356ed', 'zkbXgyzTask', '2021-01-21 09:24:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 39760);
INSERT INTO `sys_quartz_log` VALUES ('7b43dbe9798b24233766d4644715012e', 'zkbXgyzTask', '2021-01-21 09:22:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('7ccf712240d314cea20a4d1fa2e53bd9', 'zkbXgyzTask', '2021-01-21 07:29:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('7d05bf0764b3e568b6e699040381d39b', 'zkbXgyzTask', '2021-01-21 08:47:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('7d097abe20584ff8f19fd8548b1d3a15', 'zkbXgyzTask', '2021-01-21 08:06:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('7d1176b144f6ede9b7b02d02d483f439', 'zkbXgyzTask', '2021-01-21 07:57:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('7d55a350564a99a686acb820223a3358', 'zkbXgyzTask', '2021-01-21 10:25:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('7d8bf1a563a135a1d558b5f2a5f99e70', 'zkbXgyzTask', '2021-01-21 08:57:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('7de38090fc7dfa1df26aba392f153073', 'zkbXgyzTask', '2021-01-21 09:25:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 1310);
INSERT INTO `sys_quartz_log` VALUES ('7de9f0322ef3da45959abf1a5f70e2a7', 'zkbXgyzTask', '2021-01-21 07:08:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('7e0b0fd22cd35a37cf9eb43dc75d924b', 'zkbXgyzTask', '2021-01-21 07:31:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('7eca81a0eaa8bcd9eb44945b8c48015c', 'zkbXgyzTask', '2021-01-22 00:49:46', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('7f6ab69e450b5b474b654454ac29e81a', 'zkbXgyzTask', '2021-01-21 08:13:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('7f74ab5769ff0bf65659ef49fe4eb2ed', 'zkbXgyzTask', '2021-01-21 09:47:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('7f922bed7841c89f6f726fe5102b9a26', 'zkbXgyzTask', '2021-01-21 08:25:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('804487b51d8387f64141d8bac9321fc1', 'zkbXgyzTask', '2021-01-21 08:21:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('812d7e88445fa103065342e49182a0e0', 'zkbXgyzTask', '2021-01-21 08:34:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('8147c88957e3a090caf1f71dd3dcb713', 'zkbXgyzTask', '2021-01-21 08:15:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 112);
INSERT INTO `sys_quartz_log` VALUES ('82cd29b41ef6aa04ea7406633f370e4a', 'zkbXgyzTask', '2021-01-21 08:00:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('82fa3c40074b3fd13deb57edbdcc9a2e', 'zkbXgyzTask', '2021-01-21 08:39:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('8357817b789f117334a8ef5e42b141fc', 'zkbXgyzTask', '2021-01-22 01:24:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 4705);
INSERT INTO `sys_quartz_log` VALUES ('83c47cbfc698b3a4d18fc289329fc34a', 'zkbXgyzTask', '2021-01-22 00:48:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 111);
INSERT INTO `sys_quartz_log` VALUES ('843e458a12a2ad1c1ac3e7694cfc41d4', 'zkbXgyzTask', '2021-01-21 06:53:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('844fead16bba731fca23fa925ad7dfea', 'zkbXgyzTask', '2021-01-21 09:16:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 127);
INSERT INTO `sys_quartz_log` VALUES ('84d6379c1c930ccca1067a052961f7af', 'zkbXgyzTask', '2021-01-21 07:13:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('85a633b519cbce5603255c8f48f932da', 'zkbXgyzTask', '2021-01-21 07:57:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 126);
INSERT INTO `sys_quartz_log` VALUES ('85c3ef0202f125de411afe3d8a498f65', 'zkbXgyzTask', '2021-01-21 10:14:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('8683d2a64589358447319d3acdf7ab26', 'zkbXgyzTask', '2021-01-21 07:31:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('86915c7ceb896505f0d792e88b95b58a', 'zkbXgyzTask', '2021-01-21 10:23:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('869654b264e416ad2e404c7d5993e54a', 'zkbXgyzTask', '2021-01-21 08:16:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 105);
INSERT INTO `sys_quartz_log` VALUES ('86a0b725ff4f03229cdf9372da5ea1c9', 'zkbXgyzTask', '2021-01-21 08:31:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('86cc724bff39cbe868ff83ea88ed4627', 'zkbXgyzTask', '2021-01-21 10:02:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('86cf03139ec804ee0cfead970c20acc7', 'zkbXgyzTask', '2021-01-21 11:31:17', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('875911c19450b55521c99d69a40bcdb7', 'zkbXgyzTask', '2021-01-21 11:18:59', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('875eb53b0163f3acc5aba6b530749b9a', 'zkbXgyzTask', '2021-01-21 08:03:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('877328a078650068269d07c53edb19c1', 'zkbXgyzTask', '2021-01-21 11:03:47', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('87f94d0ad8e6be1a19c9f2a2ef70fd0d', 'zkbXgyzTask', '2021-01-21 09:56:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('88c6b3008cf48c67a692f98d8d05383e', 'zkbXgyzTask', '2021-01-21 08:46:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('8922b89a03de69da4d69f01bd91d067d', 'zkbXgyzTask', '2021-01-21 10:22:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('892f45046235e226ffd6640c83a64bea', 'zkbXgyzTask', '2021-01-21 07:42:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('897de52dca2d6f8fe46019eabc119f1b', 'zkbXgyzTask', '2021-01-21 10:53:50', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('89e23d71f5399eeb7852336faf056448', 'zkbXgyzTask', '2021-01-21 08:21:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('89ea7b0e71a45a39f1482b32c101582c', 'zkbXgyzTask', '2021-01-21 11:36:32', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('8a1d89bd344fc8f1a44e35f3136a2b8c', 'zkbXgyzTask', '2021-01-21 10:51:30', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('8a55a7a91c866d50be8b402613a35861', 'zkbXgyzTask', '2021-01-21 09:42:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('8a6c5d9aa95c0c3b7c2d2f399b24a039', 'zkbXgyzTask', '2021-01-21 09:00:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('8b107fbed5b87dfc1fd1247b50662410', 'zkbXgyzTask', '2021-01-21 10:07:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('8b13f2937f8bac418aae27171c193827', 'zkbXgyzTask', '2021-01-21 07:24:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 115);
INSERT INTO `sys_quartz_log` VALUES ('8b3ccad2de34a27952e56bd93d301b7f', 'zkbXgyzTask', '2021-01-21 08:22:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('8b546da053183af4e72609f60b057236', 'zkbXgyzTask', '2021-01-21 09:54:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('8be0ebd8820ba3ab4107ef09c9720170', 'zkbXgyzTask', '2021-01-21 07:44:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('8c2cc6061ee4777d7fe9b40e52d2775b', 'zkbXgyzTask', '2021-01-21 10:39:13', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('8c2d9f7c3c1b8fe2e6b390a96926376a', 'zkbXgyzTask', '2021-01-21 08:24:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('8c47601d771f8dbffaf67bd613bca264', 'zkbXgyzTask', '2021-01-21 07:44:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('8c7ec0e96c1de62c354b760f83263b59', 'zkbXgyzTask', '2021-01-21 07:33:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('8cffe36ad95dceb19630310ef83d6665', 'zkbXgyzTask', '2021-01-21 07:25:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('8d2ac62c209b48bc359d7900bc0d5a41', 'zkbXgyzTask', '2021-01-21 09:51:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('8d674609deb397969e6b2af8e457b226', 'zkbXgyzTask', '2021-01-21 07:34:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('8d77518e855febd05d7d6836b5d08415', 'zkbXgyzTask', '2021-01-21 10:21:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 108);
INSERT INTO `sys_quartz_log` VALUES ('8e17c93d9e7e5079005ce86c319b7dad', 'zkbXgyzTask', '2021-01-21 07:11:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('8e33eb91eaf135bf092dbdf05ee6a9ab', 'zkbXgyzTask', '2021-01-21 08:39:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('8e3409c912c9720c4e9a91f77980a4c9', 'zkbXgyzTask', '2021-01-21 07:17:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('8e454dea019fc6e2fa8492bb51d7a79c', 'zkbXgyzTask', '2021-01-21 09:11:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('8e927c2a09e081fea3bd675ef7343ca3', 'zkbXgyzTask', '2021-01-21 09:22:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('8ed2199dac4d5c9c8227c4e03689cdeb', 'zkbXgyzTask', '2021-01-22 00:48:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('8ee58d26aa4af1253a9b2e04c4883129', 'zkbXgyzTask', '2021-01-21 07:10:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('8fe9dbaee2a91f8faf3194a13300d1e5', 'zkbXgyzTask', '2021-01-21 10:09:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('9023ec276e9f6d80c554bef36b5e193e', 'zkbXgyzTask', '2021-01-21 08:09:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('903503600a59965f897ff1a3c7408993', 'zkbXgyzTask', '2021-01-22 02:23:20', '0/20 * * * * ? ', NULL, b'1', '小鸽驿站-历史删除', 'delHistory', '', 4);
INSERT INTO `sys_quartz_log` VALUES ('90978d685fc1070003d700225249616b', 'zkbXgyzTask', '2021-01-21 08:56:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('90a411f8027b4caca2c242d5771c60c4', 'zkbXgyzTask', '2021-01-21 08:43:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('90b37211c6f4263fe532a9290a9d8f1f', 'zkbXgyzTask', '2021-01-21 08:42:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 168);
INSERT INTO `sys_quartz_log` VALUES ('90dc2df7ff6309f27ecba74369e4a220', 'zkbXgyzTask', '2021-01-22 01:22:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 166);
INSERT INTO `sys_quartz_log` VALUES ('913fb9d434805685f51f1509b081a856', 'zkbXgyzTask', '2021-01-21 08:05:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('919471c720deca2b6c8c0d06d1bdf7a4', 'zkbXgyzTask', '2021-01-21 08:11:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('91a05fd5ccecce164122753fa35da03d', 'zkbXgyzTask', '2021-01-21 08:44:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('91ba9cc1464aa3707c562484343ad3ff', 'zkbXgyzTask', '2021-01-22 01:06:09', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('91e84827c9857e52338bef8460dd55cc', 'zkbXgyzTask', '2021-01-21 07:21:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('9263cbcdb05c9a1721815e4e22e6e8c2', 'zkbXgyzTask', '2021-01-21 10:26:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('92c42df272fd77eb94aa3fb7697c03b6', 'zkbXgyzTask', '2021-01-21 10:01:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('93300354432a346a6496cb3a266a0a84', 'zkbXgyzTask', '2021-01-21 09:06:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('93a1b3ad7052e151d7460a76f4940b4c', 'zkbXgyzTask', '2021-01-21 09:23:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('94358567c8a0249d7f0ddf1804c3b7b1', 'zkbXgyzTask', '2021-01-22 01:04:24', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 115);
INSERT INTO `sys_quartz_log` VALUES ('943821da98f1c187a9e5ebcccc190b83', 'zkbXgyzTask', '2021-01-21 09:42:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('9466291913842fc1faf54897f855711b', 'zkbXgyzTask', '2021-01-22 00:39:05', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('947af5b0018a268d69ef41f493255b40', 'zkbXgyzTask', '2021-01-21 08:16:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('952f1d7304ac217a7a2f4c361ea95bcf', 'zkbXgyzTask', '2021-01-21 07:39:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('9532cdd73987ab9e59f3fc808a217b93', 'zkbXgyzTask', '2021-01-21 09:09:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('9607746d28e0ee55e36c4ed77ef4e2c6', 'zkbXgyzTask', '2021-01-21 07:57:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('96c65e7150611e4c23b9d5b8837a0bc3', 'zkbXgyzTask', '2021-01-21 09:42:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 159);
INSERT INTO `sys_quartz_log` VALUES ('9702306992d85542f4f549f80d33c6c1', 'zkbXgyzTask', '2021-01-21 09:04:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('97330b889448786882ef7194149a6679', 'zkbXgyzTask', '2021-01-21 08:00:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('976114d16fe1fc5c77f9a64afc2974a7', 'zkbXgyzTask', '2021-01-21 09:14:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('977155f228fb38839c67563aa5a646c9', 'zkbXgyzTask', '2021-01-21 07:18:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('9780131f4fdf4f367be1a2ba4b83be87', 'zkbXgyzTask', '2021-01-21 10:34:32', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('97e0385dd41ede7c9fb6da7a435b6e9f', 'zkbXgyzTask', '2021-01-21 09:06:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('97e8a3738545d0c800eceff007c329c0', 'zkbXgyzTask', '2021-01-21 10:18:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('983d8b194e90048fac0be8aa03ec2269', 'zkbXgyzTask', '2021-01-21 08:34:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('9863be7183380be1cf65f22c3230804c', 'zkbXgyzTask', '2021-01-21 09:34:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('9892895fe54ee616f91dfef8217016b8', 'zkbXgyzTask', '2021-01-22 00:45:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 254);
INSERT INTO `sys_quartz_log` VALUES ('98f80351b14dc3ebede287e2a1b5e3b9', 'zkbXgyzTask', '2021-01-21 07:13:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('99139083e133632a6f0de7649adc52f8', 'zkbXgyzTask', '2021-01-21 09:33:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('99db0d516274e08e088ce936b57a1adc', 'zkbXgyzTask', '2021-01-21 08:09:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('9a88f41c4118fc3dc2041d9f90e58510', 'zkbXgyzTask', '2021-01-21 07:43:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('9a8f34e47681a643f02c85e96ae8db4c', 'zkbXgyzTask', '2021-01-21 08:27:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('9a9fbcc377ece672ba803140088e74c6', 'zkbXgyzTask', '2021-01-22 00:40:51', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('9ae096d977c60d3e70939cefa7a399ab', 'zkbXgyzTask', '2021-01-21 08:06:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('9b37ef725628d32f2a5ba3d4053f0c05', 'zkbXgyzTask', '2021-01-21 08:10:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 141);
INSERT INTO `sys_quartz_log` VALUES ('9b89458072902c3265fd99f94a20ed66', 'zkbXgyzTask', '2021-01-21 08:01:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('9bb5cedc8aa9e6ed82fd3e12256db736', 'zkbXgyzTask', '2021-01-22 00:48:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('9bc69a0baaf9472b5ccfbbf89c8d6439', 'zkbXgyzTask', '2021-01-21 08:49:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('9c216a658f3635ec1741cf202d3ec4d8', 'zkbXgyzTask', '2021-01-21 07:01:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('9c7fa737a17bfca4748f39bc6dd45ddb', 'zkbXgyzTask', '2021-01-21 09:48:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('9cc52c3aef70dbe5fccd9635c40daa85', 'zkbXgyzTask', '2021-01-21 09:50:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('9d78471bcc6a87988de168df33969bfe', 'zkbXgyzTask', '2021-01-21 08:24:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('9dced1601153aeebe997b8fd8da7e62e', 'zkbXgyzTask', '2021-01-21 09:12:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('9dcf8f2c4a2b6321f60c734f2e40f5ff', 'zkbXgyzTask', '2021-01-21 10:03:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 116);
INSERT INTO `sys_quartz_log` VALUES ('9df8e4b47932c27ee167356a5e57e8d0', 'zkbXgyzTask', '2021-01-21 07:40:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('9e27c961f43954d053619cfc8cf8ed1e', 'zkbXgyzTask', '2021-01-21 10:21:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('9e47174856abe0dbaa347e4191e42fcd', 'zkbXgyzTask', '2021-01-21 09:57:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('9ed6c5ee0618ade835acb812c841d28b', 'zkbXgyzTask', '2021-01-21 06:45:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 58);
INSERT INTO `sys_quartz_log` VALUES ('9f01650ec506ee1ccb287ec38071a097', 'zkbXgyzTask', '2021-01-21 09:44:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 130);
INSERT INTO `sys_quartz_log` VALUES ('9f52fa9503b5d849590573884d46a261', 'zkbXgyzTask', '2021-01-21 10:18:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('9fbbb1c8d0346b838f28cc63474f44ce', 'zkbXgyzTask', '2021-01-21 10:19:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('9fde680e3662e67b6fa156389334d2b1', 'zkbXgyzTask', '2021-01-21 10:26:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 119);
INSERT INTO `sys_quartz_log` VALUES ('a04a8e6d00cdd3b6f82a9f2b58df9b9a', 'zkbXgyzTask', '2021-01-21 10:04:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 120);
INSERT INTO `sys_quartz_log` VALUES ('a0507192a62ee1880dc2e661a1596805', 'zkbXgyzTask', '2021-01-21 11:31:52', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('a05adf877de2fb5563601a5da8054c21', 'zkbXgyzTask', '2021-01-21 08:19:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('a1643b30f3e9dd39f9b992b959f947cb', 'zkbXgyzTask', '2021-01-21 09:20:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('a1827f4e535bf4f0f6879a3bc004d714', 'zkbXgyzTask', '2021-01-21 09:56:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('a2c585ea04636430f3f9b26b2e08d6c7', 'zkbXgyzTask', '2021-01-21 07:19:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('a308645338115b723ec88501ffd774b2', 'zkbXgyzTask', '2021-01-21 09:07:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('a31f308dff989841a0625b6414827813', 'zkbXgyzTask', '2021-01-21 09:40:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('a3d7b7953ce658650b76865f42984fed', 'zkbXgyzTask', '2021-01-21 09:03:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('a4361d4d1c0bb1d627964f6d124a206e', 'zkbXgyzTask', '2021-01-21 07:01:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('a4855a7a631e6cbde53b75d0d9a593a8', 'zkbXgyzTask', '2021-01-21 08:29:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 103);
INSERT INTO `sys_quartz_log` VALUES ('a4d0bc645382099f06e299f3464e60a9', 'zkbXgyzTask', '2021-01-21 07:12:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('a4f027cf8c2a3f36842ac4ecfd9c984b', 'zkbXgyzTask', '2021-01-21 08:45:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('a55075d379235868cc3dfcf7563b599c', 'zkbXgyzTask', '2021-01-21 09:21:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('a5834ae048af94041360e9f6d50d8d60', 'zkbXgyzTask', '2021-01-21 11:20:45', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('a588088fdfda32ed16b07f4b83654262', 'zkbXgyzTask', '2021-01-21 10:00:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('a60b9999b78087b73198be55643b9fb0', 'zkbXgyzTask', '2021-01-21 07:52:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('a6c9761473f1865a05c749d24204867d', 'zkbXgyzTask', '2021-01-21 07:37:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('a6fc4f08ecb4e608cf22d1b060621cd9', 'zkbXgyzTask', '2021-01-21 07:33:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('a70641642a3d03a12727bd1deba8e7d8', 'zkbXgyzTask', '2021-01-21 07:53:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('a71ba77140ac4d11036304267ffb8ac2', 'zkbXgyzTask', '2021-01-22 01:20:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 147);
INSERT INTO `sys_quartz_log` VALUES ('a7293425578658b10b06e7158e645b9f', 'zkbXgyzTask', '2021-01-21 07:02:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('a74c81aacb5ec4e108b81236957284de', 'zkbXgyzTask', '2021-01-21 09:08:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('a7b0ff12514e4ab1575a7123838c7d95', 'zkbXgyzTask', '2021-01-21 09:34:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('a7b1c8b70f77dd117283abbd8734e8ce', 'zkbXgyzTask', '2021-01-21 08:04:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('a7e27baf2885ab084edd107e78ec64f8', 'zkbXgyzTask', '2021-01-21 08:04:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 212);
INSERT INTO `sys_quartz_log` VALUES ('a8144d45c4b072abdefd24d1b493bc81', 'zkbXgyzTask', '2021-01-21 08:08:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('a8c0edddadd837021e1ade70e90edf89', 'zkbXgyzTask', '2021-01-21 11:41:13', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 124);
INSERT INTO `sys_quartz_log` VALUES ('a960f6c78124789f1ef89c46c494cd9c', 'zkbXgyzTask', '2021-01-21 10:00:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 69);
INSERT INTO `sys_quartz_log` VALUES ('a98498addbeddc58cf0bdf16d62a0220', 'zkbXgyzTask', '2021-01-21 09:29:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('a9a1e11fe923800e5dc61588e30e78d4', 'zkbXgyzTask', '2021-01-21 09:25:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('a9a98a5cb8bec53798da8ccddbe5cbf6', 'zkbXgyzTask', '2021-01-21 10:20:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('aa1268c5ddc7e2f778cfd4495c82f7a6', 'zkbXgyzTask', '2021-01-21 08:48:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 114);
INSERT INTO `sys_quartz_log` VALUES ('aa4594e77db24a87d75bb0a7bf5cb2ab', 'zkbXgyzTask', '2021-01-21 07:06:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('aaac724992470deafb9859f50fdca857', 'zkbXgyzTask', '2021-01-21 08:40:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('aac98784fb5defb7700aaa948836ef08', 'zkbXgyzTask', '2021-01-22 01:20:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 123);
INSERT INTO `sys_quartz_log` VALUES ('ab103cd672644f87c9724c766fb50592', 'zkbXgyzTask', '2021-01-21 07:45:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('ab36e950885042cb4ebc3dab9adcdf12', 'zkbXgyzTask', '2021-01-21 09:52:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('abd79dda30b0c408d40474d74b256edc', 'zkbXgyzTask', '2021-01-21 08:45:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('abe85abcfbc23fdaae35db450cd96bd6', 'zkbXgyzTask', '2021-01-21 07:46:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('ac4a1242c900ea388966382d1db4bc7b', 'zkbXgyzTask', '2021-01-21 10:31:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('ac73ea69c8c7ba4ff501425491273611', 'zkbXgyzTask', '2021-01-21 10:16:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('ac8f87ac82439aa17acd7f29beefd554', 'zkbXgyzTask', '2021-01-21 07:15:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('ad294a7ab252303868a1c961eb7362af', 'zkbXgyzTask', '2021-01-22 00:49:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('ad8c622e5aa5ccb03cd3c73f74f16917', 'zkbXgyzTask', '2021-01-21 09:35:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 116);
INSERT INTO `sys_quartz_log` VALUES ('addff3ff069b93036b38666b1e3e96d3', 'zkbXgyzTask', '2021-01-21 09:15:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('adf74741f5d5b5ccd02ae2dcf23418b8', 'zkbXgyzTask', '2021-01-21 09:13:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('ae162b21fb4caa03393965d3356ca33e', 'zkbXgyzTask', '2021-01-21 10:06:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('ae1bba3c3c546a92443d8d2339659836', 'zkbXgyzTask', '2021-01-21 10:30:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('ae749513618718e5b8b7740371849487', 'zkbXgyzTask', '2021-01-21 07:29:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 120);
INSERT INTO `sys_quartz_log` VALUES ('ae7736bc43a6ead2ccbc2d0031099762', 'zkbXgyzTask', '2021-01-21 07:43:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('aec327fe441a4b9df866f0fa729da68c', 'zkbXgyzTask', '2021-01-21 09:43:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('b0c0a3bb68181fde553d5274f7207e70', 'zkbXgyzTask', '2021-01-21 09:51:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('b0d3b40305916ee047b1bf84b30eb6c4', 'zkbXgyzTask', '2021-01-21 10:19:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('b11326f69db44014a59f7901276c71ba', 'zkbXgyzTask', '2021-01-21 09:14:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('b1a9c604ca2f697a3f6d572212d36708', 'zkbXgyzTask', '2021-01-21 09:48:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('b2ea9fac2059ae9ad89f1db35f9269fc', 'zkbXgyzTask', '2021-01-22 01:12:35', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 135);
INSERT INTO `sys_quartz_log` VALUES ('b30439121fdea967f964d3ebdfb90cec', 'zkbXgyzTask', '2021-01-21 07:05:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('b38fe1e8e161b5293507fa5ebeb86d83', 'zkbXgyzTask', '2021-01-21 09:39:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('b4e0974ca83004d8bd3176eaceadcdf8', 'zkbXgyzTask', '2021-01-21 08:52:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('b5239b6dd1836ae074410263d7f115e0', 'zkbXgyzTask', '2021-01-21 07:47:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('b57a86b9943a554459ac447dc32fc23a', 'zkbXgyzTask', '2021-01-21 09:13:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 175);
INSERT INTO `sys_quartz_log` VALUES ('b698161f28912c4c10ec90ef395435f1', 'zkbXgyzTask', '2021-01-21 09:27:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 1183);
INSERT INTO `sys_quartz_log` VALUES ('b795c6cc66b47557bc321bc6e50180e6', 'zkbXgyzTask', '2021-01-21 09:23:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('b7f2b8effede2c93d476d12dcdb95626', 'zkbXgyzTask', '2021-01-21 09:57:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('b879551a61eed74eee5450b04e68fce6', 'zkbXgyzTask', '2021-01-21 08:47:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('b8aa2769e7d99ff563b394e04857c27e', 'zkbXgyzTask', '2021-01-21 10:59:06', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 121);
INSERT INTO `sys_quartz_log` VALUES ('b93cccc3f7de9d4db4ee35279f36f68b', 'zkbXgyzTask', '2021-01-21 08:05:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('b9f2d2a40f529874aa11fb9ba5309446', 'zkbXgyzTask', '2021-01-21 07:58:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('ba4d46a3a6e24b8b0ea6ca6506d6892e', 'zkbXgyzTask', '2021-01-21 09:16:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('bb75d9aa0141a7ade9045d6ba1a0138c', 'zkbXgyzTask', '2021-01-21 06:57:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('bbfe26acd3d57c63076240ea3f669693', 'zkbXgyzTask', '2021-01-21 09:53:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 75);
INSERT INTO `sys_quartz_log` VALUES ('bc1ac07e9ee19f16796230707008b952', 'zkbXgyzTask', '2021-01-21 10:20:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('bcab6272231997de353b34c3464a4e72', 'zkbXgyzTask', '2021-01-21 11:30:06', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('bcb7b8347372807cc5852dc43b9eb0b7', 'zkbXgyzTask', '2021-01-21 07:04:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('bd32fe7ba71d4c9f5c5840f473032cf3', 'zkbXgyzTask', '2021-01-21 07:07:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('be24521b4d2e43c6fcacecbd61fcdd78', 'zkbXgyzTask', '2021-01-21 08:14:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('be5c4589a5c71190f761e00ebbbcd289', 'zkbXgyzTask', '2021-01-21 08:26:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('be6f9b2735e8b6bae3275d2bc1cc507c', 'zkbXgyzTask', '2021-01-21 10:01:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('be92c16912a593a0c04ac6528e913f3e', 'zkbXgyzTask', '2021-01-21 09:27:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 229);
INSERT INTO `sys_quartz_log` VALUES ('bf1e916b576d6602860236e196df1bde', 'zkbXgyzTask', '2021-01-21 07:05:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('bf6bb41a25c1d685218b03c10b3a8f24', 'zkbXgyzTask', '2021-01-21 08:07:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('bf7149e3ca36af4da70047bdcd10bc0d', 'zkbXgyzTask', '2021-01-21 09:26:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('bf7d954c602749d493b2e58af7e08826', 'zkbXgyzTask', '2021-01-21 08:16:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('bff6f50c99b0424360f517a513dcad97', 'zkbXgyzTask', '2021-01-21 08:12:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('c0fbd219d840febf139ae6466d1f6c3b', 'zkbXgyzTask', '2021-01-21 10:11:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('c15a0074065bf7f65ef1dee410f94c5d', 'zkbXgyzTask', '2021-01-21 07:50:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('c1e48b695bd7a68b857c0f2ac4bb8fa5', 'zkbXgyzTask', '2021-01-22 00:53:17', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('c1ee71ad50165dc2c5b60dd999950051', 'zkbXgyzTask', '2021-01-21 10:41:33', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 137);
INSERT INTO `sys_quartz_log` VALUES ('c1f12c451eb1bc3cfc7ddba16d6fd0d6', 'zkbXgyzTask', '2021-01-21 07:54:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('c202728d699996cd0311017ac1761d42', 'zkbXgyzTask', '2021-01-21 08:50:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('c2ea9ea70d4d7e7253cd7c5dc614db89', 'zkbXgyzTask', '2021-01-21 08:11:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('c35fb082bb3de55ca9faf4e04bf2b651', 'zkbXgyzTask', '2021-01-21 11:35:22', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('c368e729d324c8319b02c6abf0c2a817', 'zkbXgyzTask', '2021-01-21 08:26:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('c3a7155380c7d2097fee24c6ec6de02c', 'zkbXgyzTask', '2021-01-21 11:02:02', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('c3ad237dd2d08ad837d6618cb4b89536', 'zkbXgyzTask', '2021-01-21 09:32:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('c40f9e59a2ddb7a2a13e4994a34f664a', 'zkbXgyzTask', '2021-01-21 08:28:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('c4ba5af506ae735a97d8f943cd32f755', 'zkbXgyzTask', '2021-01-21 08:33:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('c51184e758000cc4901c7b422014c69b', 'zkbXgyzTask', '2021-01-21 08:53:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('c53f956cddd8676811286f50c71a0cbd', 'zkbXgyzTask', '2021-01-21 08:36:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('c56f6278097ec0810180e415fe947c56', 'zkbXgyzTask', '2021-01-21 11:14:19', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('c570d35dba1f2882dfb367944893f25e', 'zkbXgyzTask', '2021-01-22 00:46:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 271);
INSERT INTO `sys_quartz_log` VALUES ('c5b888de116eaa6a001e922b6a4066c2', 'zkbXgyzTask', '2021-01-21 09:10:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 76);
INSERT INTO `sys_quartz_log` VALUES ('c7b02542dc96b7d5c3ebcd749037a477', 'zkbXgyzTask', '2021-01-21 10:21:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('c835a073bbc44069b416c8fd4f104bb8', 'zkbXgyzTask', '2021-01-21 09:40:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('c8be9dd2460cc6e0558d7a7160ace378', 'zkbXgyzTask', '2021-01-21 09:02:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('c9370ca63e2c8f25be7e864ed8b29007', 'zkbXgyzTask', '2021-01-21 07:50:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 105);
INSERT INTO `sys_quartz_log` VALUES ('c93b69b8a80649f02f210981e6be9150', 'zkbXgyzTask', '2021-01-21 09:33:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('c9bf158232547f49e6f19b282b7b0f45', 'zkbXgyzTask', '2021-01-21 08:27:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 113);
INSERT INTO `sys_quartz_log` VALUES ('c9d283f67086f79d8a1214d6cf3b786f', 'zkbXgyzTask', '2021-01-21 10:56:11', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('ca1f9f339568934306782f64cce40043', 'zkbXgyzTask', '2021-01-21 08:02:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('ca570c911e64520c9cec5421ca02f007', 'zkbXgyzTask', '2021-01-21 10:12:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('ca6a7979bce9d35568ec9cca22433f38', 'zkbXgyzTask', '2021-01-22 02:22:00', '0 * * * * ? ', NULL, NULL, '小鸽驿站数据抓取', 'CrawlerXgyz', '', NULL);
INSERT INTO `sys_quartz_log` VALUES ('ca6e2373aff13b7deca5fbce1a556d0c', 'zkbXgyzTask', '2021-01-21 10:59:41', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('caaad26ffa93a6a7b7de1c53f685b696', 'zkbXgyzTask', '2021-01-21 10:07:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 102);
INSERT INTO `sys_quartz_log` VALUES ('cad38e1896781b42783831f5913dea64', 'zkbXgyzTask', '2021-01-21 07:25:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('cb23a6c704256b6318f9968611d21630', 'zkbXgyzTask', '2021-01-21 08:32:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('cb68ed9dd926a55cd87c5f9175c3d9b2', 'zkbXgyzTask', '2021-01-21 09:01:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('cb7f0113e4aa2930c37779d043d1b49c', 'zkbXgyzTask', '2021-01-21 09:07:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('cc07d5438f461463f43765be802e9cdd', 'zkbXgyzTask', '2021-01-21 07:50:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('cc39d6935173ed57418d109888216f4c', 'zkbXgyzTask', '2021-01-21 08:23:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('cc4e6d9ee96d87823c9480c4c80bcd8a', 'zkbXgyzTask', '2021-01-21 07:09:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('ccd881a16e6480cd7d529ddfb33064e1', 'zkbXgyzTask', '2021-01-21 09:18:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('cd487a9bd258a9dbe81d55394b85230c', 'zkbXgyzTask', '2021-01-21 07:48:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('ce2d01db40db6676e2ec31f7c396f5c1', 'zkbXgyzTask', '2021-01-21 09:21:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('ceabf02be08441acec9ffeb25c828bc9', 'zkbXgyzTask', '2021-01-21 07:49:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 114);
INSERT INTO `sys_quartz_log` VALUES ('cec34eba6c4a8ba67ded832df8bc2580', 'zkbXgyzTask', '2021-01-21 08:51:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('ced6e075971556f8275f16ad47402885', 'zkbXgyzTask', '2021-01-21 07:16:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('cf0e90f2d7cc8df3826cf52d45cbee39', 'zkbXgyzTask', '2021-01-21 08:37:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('cf1aa6e2410fc98f0bad2d7ced7fc354', 'zkbXgyzTask', '2021-01-21 09:34:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('cf61610d4a6b46766c1e7a34c811d119', 'zkbXgyzTask', '2021-01-21 09:36:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 130);
INSERT INTO `sys_quartz_log` VALUES ('cf895751b43f115738829f1939271661', 'zkbXgyzTask', '2021-01-21 08:15:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('cfe33c015561603413594dd992c6f00f', 'zkbXgyzTask', '2021-01-21 06:54:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('d00334d85257f5c9504c45a589be2b2d', 'zkbXgyzTask', '2021-01-21 10:32:47', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('d01b3a1791438d1a4b2cca16143fa1a2', 'zkbXgyzTask', '2021-01-21 10:14:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('d02fba05c7c6f910ab907b7dd0b6405a', 'zkbXgyzTask', '2021-01-22 01:22:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 121);
INSERT INTO `sys_quartz_log` VALUES ('d155b46cf25ae98353b32fe345277d44', 'zkbXgyzTask', '2021-01-21 06:53:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('d2197636181261a862b0bde1836229bf', 'zkbXgyzTask', '2021-01-22 01:02:04', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('d27449b27164873a6260a38d7ca8c80f', 'zkbXgyzTask', '2021-01-21 09:19:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('d2b5ed4c010b2b26187d823df667c09a', 'zkbXgyzTask', '2021-01-21 09:59:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('d3253ef34faa207b3af0a82302d2ef7e', 'zkbXgyzTask', '2021-01-21 08:34:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('d36df2471063e94eb37021f023346431', 'zkbXgyzTask', '2021-01-21 06:57:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('d41bdf504a54ec24d78cffb9723138f7', 'zkbXgyzTask', '2021-01-21 10:12:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('d4dbb32de8479c9d1f96ec8dfd888b83', 'zkbXgyzTask', '2021-01-21 07:12:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('d5b54dbbb03c7cbbc3001623e98110da', 'zkbXgyzTask', '2021-01-21 07:51:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('d79b9b9a40338b735099bee321271b48', 'zkbXgyzTask', '2021-01-21 10:29:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 113);
INSERT INTO `sys_quartz_log` VALUES ('d835a63e19b3db86285eefd96f8c6f11', 'zkbXgyzTask', '2021-01-22 00:56:13', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('d872f5e9c430ebb15fc0b0b219262497', 'zkbXgyzTask', '2021-01-21 10:36:52', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('d90e1f5af795844b8db53b8b8d96d672', 'zkbXgyzTask', '2021-01-21 08:14:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('d91825e610609764fcfb754d6e407a4b', 'zkbXgyzTask', '2021-01-21 10:09:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('d91863ec985fb54d01369ad94c257f36', 'zkbXgyzTask', '2021-01-21 10:15:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 114);
INSERT INTO `sys_quartz_log` VALUES ('d9916f5735466b1a2478315ade0d87bc', 'zkbXgyzTask', '2021-01-21 08:55:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('d9eb6befc789e4b82bdeb57345dc2d2c', 'zkbXgyzTask', '2021-01-21 09:43:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('d9eeee0f0e51a6bccdad657f2ca6df52', 'zkbXgyzTask', '2021-01-21 08:38:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('da1a16854728118a305af01a0c31126b', 'zkbXgyzTask', '2021-01-21 07:03:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('dae86e03b2a0837f54e2e9efffd83163', 'zkbXgyzTask', '2021-01-21 09:03:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('db3a70226d3283e76803ada5073e356f', 'zkbXgyzTask', '2021-01-21 10:17:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('db3c79de2792805a91766ab6bb0f952a', 'zkbXgyzTask', '2021-01-22 01:21:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 154);
INSERT INTO `sys_quartz_log` VALUES ('db4c6a853d4cb4cf402a0075b54b7d91', 'zkbXgyzTask', '2021-01-21 06:54:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('db6048366a59c019fc96514f5ff99371', 'zkbXgyzTask', '2021-01-21 07:22:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 111);
INSERT INTO `sys_quartz_log` VALUES ('db7115802cdbc35037c74776391432a7', 'zkbXgyzTask', '2021-01-21 09:36:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('db92a6825b65531cde1bde403bc7eb0f', 'zkbXgyzTask', '2021-01-21 09:18:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('dc9b31d14d2fce4199d41420b128aa7f', 'zkbXgyzTask', '2021-01-21 10:24:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('dcbb20f11feaf56d8d2ce3b9747ebc72', 'zkbXgyzTask', '2021-01-21 08:25:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 143);
INSERT INTO `sys_quartz_log` VALUES ('dcc63f12233166eef89ba1fbcbc7b66f', 'zkbXgyzTask', '2021-01-21 07:36:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('dd0aa43b7e163ec6819b8e16fc7d2815', 'zkbXgyzTask', '2021-01-22 02:23:00', '0 * * * * ? ', 'java.lang.NoSuchMethodException: com.zero.dreamland.quartz.task.ZkbXgyzTask.CrawlerXgyz()\r\n	at java.lang.Class.getDeclaredMethod(Class.java:2130)\r\n	at com.zero.dreamland.quartz.utils.QuartzRunnable.<init>(QuartzRunnable.java:45)\r\n	at com.zero.dreamland.quartz.utils.ExecutionJob.executeInternal(ExecutionJob.java:85)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '小鸽驿站数据抓取', 'CrawlerXgyz', '', 2);
INSERT INTO `sys_quartz_log` VALUES ('dd20368370c87048faeeb02dee54c64a', 'zkbXgyzTask', '2021-01-21 10:05:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('dd9cebd9aee3be8be62b33158fe93803', 'zkbXgyzTask', '2021-01-21 10:11:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('ddd6a040aaea16f67969d7a19dd5930b', 'zkbXgyzTask', '2021-01-21 10:02:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 142);
INSERT INTO `sys_quartz_log` VALUES ('de9b5d6614f240c4e0b64575966fbe12', 'zkbXgyzTask', '2021-01-21 07:23:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('de9bef33ea10268730f3e9abdff2c34b', 'zkbXgyzTask', '2021-01-21 07:59:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('debe3e1f4326425f07239df55b8916b2', 'zkbXgyzTask', '2021-01-21 10:22:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('dec2038558012ca0c43d2c7f91ad5536', 'zkbXgyzTask', '2021-01-21 08:44:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('dee6417fa44476076812101eeaf80cf8', 'zkbXgyzTask', '2021-01-21 07:38:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('df0c679239db186b350878a81ae29103', 'zkbXgyzTask', '2021-01-21 10:30:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('df9cf2011f7f039a4944ec169c850bcf', 'zkbXgyzTask', '2021-01-21 08:40:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('dfb36873dbdd4ffc302e6eac0b9c560a', 'zkbXgyzTask', '2021-01-21 06:58:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('dfccbd9bd29ebc9c4f3fa91d26a6202d', 'zkbXgyzTask', '2021-01-21 07:21:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('dfdb931f4c5fb6fec983369acc6a8cef', 'zkbXgyzTask', '2021-01-21 09:23:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('e009e9a1b96db73ecb5b25ca70cfa027', 'zkbXgyzTask', '2021-01-21 11:26:01', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('e0845a282952beb5f4ef5907cbc9075b', 'zkbXgyzTask', '2021-01-21 08:05:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('e096087f8819b1047acecb3538878267', 'zkbXgyzTask', '2021-01-21 07:32:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 77);
INSERT INTO `sys_quartz_log` VALUES ('e09800b634073fe1de67813ab534b90a', 'zkbXgyzTask', '2021-01-21 09:30:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 153);
INSERT INTO `sys_quartz_log` VALUES ('e0bc574335d182dfad5c2869c4d3e717', 'zkbXgyzTask', '2021-01-21 09:22:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('e0d4135a07bc6b3f953ef5b5d9f2d806', 'zkbXgyzTask', '2021-01-21 09:09:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('e101fb7c22cf0211dd9234c14c944d35', 'zkbXgyzTask', '2021-01-21 09:16:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('e1256bcd3ee009732bf226433a5222a3', 'zkbXgyzTask', '2021-01-22 01:24:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 308);
INSERT INTO `sys_quartz_log` VALUES ('e13adddea7cf8649bf9df59d8864a1d7', 'zkbXgyzTask', '2021-01-21 10:13:40', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('e1971b0882852f3440c4a29e5e6f6607', 'zkbXgyzTask', '2021-01-21 11:20:10', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('e1b7ef3f5307bd72775314492d1cbf4f', 'zkbXgyzTask', '2021-01-21 07:47:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('e1ef7e077316aa13faf46591568b8d13', 'zkbXgyzTask', '2021-01-21 09:52:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('e229dbad7bb9a315184eff0c35da4627', 'zkbXgyzTask', '2021-01-21 08:52:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('e2919361332857eede4c90ed9477c6c1', 'zkbXgyzTask', '2021-01-21 07:35:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('e2a0416ebcd331d5e1e0f231841d75a9', 'zkbXgyzTask', '2021-01-21 08:48:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 82);
INSERT INTO `sys_quartz_log` VALUES ('e2c0d0d44b331c421176858007529f46', 'zkbXgyzTask', '2021-01-22 01:22:20', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 170);
INSERT INTO `sys_quartz_log` VALUES ('e2e24369fe2cf94f713449412c00600e', 'zkbXgyzTask', '2021-01-21 10:04:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('e2fd04644c7d694dcabe99ba1e9008b6', 'zkbXgyzTask', '2021-01-21 08:55:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('e37701c3fafeca507225e7d09fa32445', 'zkbXgyzTask', '2021-01-21 08:54:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('e37af86632021105d4312755cb67052f', 'zkbXgyzTask', '2021-01-21 07:11:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('e3ef7b8dae84e3d48d41dc40e9b3c978', 'zkbXgyzTask', '2021-01-21 10:46:14', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('e4930f4a3f8d2292a3bbb56ab7180429', 'zkbXgyzTask', '2021-01-21 07:42:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('e510efd61efce9a18eebc299d37a5acc', 'zkbXgyzTask', '2021-01-21 07:39:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('e5266384a62b9430b5f080d564aebc34', 'zkbXgyzTask', '2021-01-21 10:53:15', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 198);
INSERT INTO `sys_quartz_log` VALUES ('e52d48c76f570aba23d3cecaeda3c2c7', 'zkbXgyzTask', '2021-01-21 08:49:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 112);
INSERT INTO `sys_quartz_log` VALUES ('e58061102cbccbf684167adcb98aa429', 'zkbXgyzTask', '2021-01-21 09:31:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 136);
INSERT INTO `sys_quartz_log` VALUES ('e5c89da4abbb38f2ab007bd98d3559c9', 'zkbXgyzTask', '2021-01-21 09:49:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('e620b94edc177fab55f029babeb98343', 'zkbXgyzTask', '2021-01-21 08:20:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 118);
INSERT INTO `sys_quartz_log` VALUES ('e7296c7ec7bed11affc037552c4a6713', 'zkbXgyzTask', '2021-01-21 07:35:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 140);
INSERT INTO `sys_quartz_log` VALUES ('e74f8aea0ec7ff8e8f051ed45ea8f68e', 'zkbXgyzTask', '2021-01-22 00:59:08', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('e7d7a400fa76e975c6effbaecc5e2b0d', 'zkbXgyzTask', '2021-01-21 07:03:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('e7f2c166f6e66603fda256293258307d', 'zkbXgyzTask', '2021-01-21 09:15:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('e845d801e868ea72598db33d118e6dca', 'zkbXgyzTask', '2021-01-21 06:56:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 91);
INSERT INTO `sys_quartz_log` VALUES ('e85ccd854f8269d9f80abb745a8e508d', 'zkbXgyzTask', '2021-01-21 10:10:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 104);
INSERT INTO `sys_quartz_log` VALUES ('e9c0f97d18017213d769f3cfe6e85845', 'zkbXgyzTask', '2021-01-21 07:49:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 87);
INSERT INTO `sys_quartz_log` VALUES ('e9de3b796b537e10b960f67fef84a688', 'zkbXgyzTask', '2021-01-21 07:39:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('ea84349ac945bb124f5cbd24da01a296', 'zkbXgyzTask', '2021-01-21 08:12:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('eaccb4144d802876f04316e25b153091', 'zkbXgyzTask', '2021-01-21 09:54:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 126);
INSERT INTO `sys_quartz_log` VALUES ('eaf72c1281ec331cd6ff8c48689e0526', 'zkbXgyzTask', '2021-01-21 07:21:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 99);
INSERT INTO `sys_quartz_log` VALUES ('ec215372ff6c7923dd733f2fc792de8b', 'zkbXgyzTask', '2021-01-21 09:45:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 135);
INSERT INTO `sys_quartz_log` VALUES ('ec560d2c98167ae98ef475267582f885', 'zkbXgyzTask', '2021-01-21 07:59:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('edc2fe19745cc8c1c11ffa900f636fbf', 'zkbXgyzTask', '2021-01-21 09:32:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('ee2ccf52caa5d2883e0bc929b5f29545', 'zkbXgyzTask', '2021-01-21 07:34:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 110);
INSERT INTO `sys_quartz_log` VALUES ('ee5cd481386397e3da72b7032195db0c', 'zkbXgyzTask', '2021-01-21 08:38:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('eea11a15b94c4e34cf360c72fe1ab7f8', 'zkbXgyzTask', '2021-01-21 07:13:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('eeb7292ca8cff22858e0d3e7488af062', 'zkbXgyzTask', '2021-01-21 09:20:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('eef217dd9951ff4a21217a6fb4a5bdc5', 'zkbXgyzTask', '2021-01-21 08:13:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 71);
INSERT INTO `sys_quartz_log` VALUES ('eefec933a005bd88168a8ea2623662c4', 'zkbXgyzTask', '2021-01-21 10:54:25', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 179);
INSERT INTO `sys_quartz_log` VALUES ('ef4ad70026d900810fcf3157ec80dad3', 'zkbXgyzTask', '2021-01-21 10:11:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('efa0333667be6f9a80d9072d4fca59f2', 'zkbXgyzTask', '2021-01-21 07:00:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 106);
INSERT INTO `sys_quartz_log` VALUES ('eff602edcf6f951ee024f59121e8de3d', 'zkbXgyzTask', '2021-01-22 02:22:00', '0/20 * * * * ? ', NULL, NULL, '小鸽驿站-历史删除', 'delHistory', '', 8);
INSERT INTO `sys_quartz_log` VALUES ('f0c9d8382a854a925f5b58f5c834dc2f', 'zkbXgyzTask', '2021-01-21 08:58:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('f0f611050ed8d12e69d33639974512b1', 'zkbXgyzTask', '2021-01-22 02:24:00', '0 * * * * ? ', 'java.lang.NoSuchMethodException: com.zero.dreamland.quartz.task.ZkbXgyzTask.CrawlerXgyz()\r\n	at java.lang.Class.getDeclaredMethod(Class.java:2130)\r\n	at com.zero.dreamland.quartz.utils.QuartzRunnable.<init>(QuartzRunnable.java:45)\r\n	at com.zero.dreamland.quartz.utils.ExecutionJob.executeInternal(ExecutionJob.java:85)\r\n	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', b'0', '小鸽驿站数据抓取', 'CrawlerXgyz', '', 610);
INSERT INTO `sys_quartz_log` VALUES ('f110a4ece072ee4efcf6783b4cd82be2', 'zkbXgyzTask', '2021-01-21 11:07:17', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('f1b46d3263dce421155137da9074a4a9', 'zkbXgyzTask', '2021-01-21 09:55:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('f1c7a1b453d67b18a178f686b5957a2c', 'zkbXgyzTask', '2021-01-21 09:53:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('f242a067c9ed55d4eaa928fc237c2851', 'zkbXgyzTask', '2021-01-21 07:38:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('f2f3743212f53b4a913b4d86f4891b90', 'zkbXgyzTask', '2021-01-21 09:44:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('f3d10b88ad3e947d8bdb618814d9d874', 'zkbXgyzTask', '2021-01-21 10:09:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 95);
INSERT INTO `sys_quartz_log` VALUES ('f46cdf623d4eb240afb78f903ff291b1', 'zkbXgyzTask', '2021-01-21 09:30:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 70);
INSERT INTO `sys_quartz_log` VALUES ('f4f768c97c2e007906528aaf3874a088', 'zkbXgyzTask', '2021-01-21 07:02:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 78);
INSERT INTO `sys_quartz_log` VALUES ('f5975be43f885adf374e59930df0297f', 'zkbXgyzTask', '2021-01-21 09:35:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 100);
INSERT INTO `sys_quartz_log` VALUES ('f5d7001dd4e74242bb2eeab7fc07660a', 'zkbXgyzTask', '2021-01-22 00:54:27', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('f64d96e7d76983110ea18801e7f1aad7', 'zkbXgyzTask', '2021-01-21 07:30:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 74);
INSERT INTO `sys_quartz_log` VALUES ('f64ed1dfda0d392f2bd73e3990deb0d9', 'zkbXgyzTask', '2021-01-21 08:31:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('f65a842ddd39572729cd1b02f4e1f6cb', 'zkbXgyzTask', '2021-01-21 10:29:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 94);
INSERT INTO `sys_quartz_log` VALUES ('f6791b51695c4d6d73a475fc93122af1', 'zkbXgyzTask', '2021-01-21 07:49:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 84);
INSERT INTO `sys_quartz_log` VALUES ('f69964eae333f4c9c3fcdc0e3ae0fd11', 'zkbXgyzTask', '2021-01-21 07:12:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 117);
INSERT INTO `sys_quartz_log` VALUES ('f7947f07b9d872be1efec0e1e9bbd6b6', 'zkbXgyzTask', '2021-01-21 09:11:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 73);
INSERT INTO `sys_quartz_log` VALUES ('f7ec614cd51aaa9f0514cebc2a371ab8', 'zkbXgyzTask', '2021-01-21 08:35:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 72);
INSERT INTO `sys_quartz_log` VALUES ('f811cb9b4a8e04b90444c93e91c716b8', 'zkbXgyzTask', '2021-01-21 08:37:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('f8bdba796c454721e1f8d9f6e2416ab3', 'zkbXgyzTask', '2021-01-21 09:02:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('f94ca5c35928003d5c48b49d91e0cc4f', 'zkbXgyzTask', '2021-01-21 06:55:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 86);
INSERT INTO `sys_quartz_log` VALUES ('f958304c22e02b60c73fd63967fb9b9d', 'zkbXgyzTask', '2021-01-21 08:50:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('f95d146f23f7fa6c35811063c783209c', 'zkbXgyzTask', '2021-01-21 10:37:27', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('fa5004974b00a390d6f99807b931aec0', 'zkbXgyzTask', '2021-01-21 08:18:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 79);
INSERT INTO `sys_quartz_log` VALUES ('fa697d9fc5f691c20a24778ab0539afe', 'zkbXgyzTask', '2021-01-21 09:31:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 101);
INSERT INTO `sys_quartz_log` VALUES ('fa702d7d4b38ec8f054e18fd265f543f', 'zkbXgyzTask', '2021-01-21 07:55:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('fa88d3af460e6d3ffd8f99e727939b34', 'zkbXgyzTask', '2021-01-22 00:47:00', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 92);
INSERT INTO `sys_quartz_log` VALUES ('fa9d524cabb6f467ad365b2117ed4da4', 'zkbXgyzTask', '2021-01-21 07:04:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 165);
INSERT INTO `sys_quartz_log` VALUES ('fab0473b4dd6f30ce75c268e87a7ac62', 'zkbXgyzTask', '2021-01-21 07:44:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 85);
INSERT INTO `sys_quartz_log` VALUES ('fada75ee5b503d310edb05e3d61031ed', 'zkbXgyzTask', '2021-01-21 11:39:28', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 114);
INSERT INTO `sys_quartz_log` VALUES ('fbac3e32ef240bebae80f6b649798eea', 'zkbXgyzTask', '2021-01-21 10:03:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('fbbcebc64048dd5dbbc3f35502b1b123', 'zkbXgyzTask', '2021-01-21 08:18:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('fc08a1ddd5b5dd5cc3091021bd88bf4c', 'zkbXgyzTask', '2021-01-21 09:39:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('fc6a1c5c75e80bfee329078cb2d5c2d0', 'zkbXgyzTask', '2021-01-21 08:41:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 97);
INSERT INTO `sys_quartz_log` VALUES ('fcc817e8fa389de4a0f0d5df794731c6', 'zkbXgyzTask', '2021-01-21 11:17:49', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 96);
INSERT INTO `sys_quartz_log` VALUES ('fcec2c232558cb4d8cc4e513ff4fef36', 'zkbXgyzTask', '2021-01-21 11:19:35', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 81);
INSERT INTO `sys_quartz_log` VALUES ('fcf51ef10effa8b01964338c685fddaa', 'zkbXgyzTask', '2021-01-21 07:37:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 98);
INSERT INTO `sys_quartz_log` VALUES ('fd7cdfb601c74f97bd9b8d9ccf1ab834', 'zkbXgyzTask', '2021-01-21 08:13:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 113);
INSERT INTO `sys_quartz_log` VALUES ('fda87f0806a394bbfc8e06dc9ef8e4c2', 'zkbXgyzTask', '2021-01-21 10:10:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 127);
INSERT INTO `sys_quartz_log` VALUES ('fde5c9e6691b2c84a8a8820225a48766', 'zkbXgyzTask', '2021-01-21 06:59:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 88);
INSERT INTO `sys_quartz_log` VALUES ('fe10a4ab96a9b2a2466f6c0787c0efaa', 'zkbXgyzTask', '2021-01-21 10:58:31', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 90);
INSERT INTO `sys_quartz_log` VALUES ('fed812b017752e83a98442e4f4111592', 'zkbXgyzTask', '2021-01-21 07:38:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 89);
INSERT INTO `sys_quartz_log` VALUES ('fee2eb714baa0359e197cfaf5e1fb834', 'zkbXgyzTask', '2021-01-21 11:15:29', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 80);
INSERT INTO `sys_quartz_log` VALUES ('fef79c725dc0d24b7a28764e4f4e6584', 'zkbXgyzTask', '2021-01-21 07:11:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 107);
INSERT INTO `sys_quartz_log` VALUES ('ff3890ecb08c968bca86cb4cdc2e50da', 'zkbXgyzTask', '2021-01-21 07:45:40', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 83);
INSERT INTO `sys_quartz_log` VALUES ('ffaff8dff400c70c15c2858d4fccf7b2', 'zkbXgyzTask', '2021-01-21 09:47:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 93);
INSERT INTO `sys_quartz_log` VALUES ('ffb8257c63df49b1fe6b2b8830ec2f36', 'zkbXgyzTask', '2021-01-21 09:53:00', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 109);
INSERT INTO `sys_quartz_log` VALUES ('ffc4ed04f9207f1acb410dd0e98d8583', 'zkbXgyzTask', '2021-01-21 08:22:20', '0/20 * * * * ? ', NULL, b'1', '曹双娜打车', 'CrawlerXgyzAll', '', 114);
INSERT INTO `sys_quartz_log` VALUES ('ffd41a63b27905971ff9f38964f80738', 'zkbXgyzTask', '2021-01-21 10:40:23', '0/20 * * * * ? ', NULL, NULL, '曹双娜打车', 'CrawlerXgyzAll', '', 111);

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
  `siteId` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '站点ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `mainText` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正文',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '链接',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `imgList` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片列表（逗号分隔）',
  `relTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发帖时间',
  `replyNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复数',
  `readNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '阅读数',
  `del_flag` int NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_date` datetime NULL DEFAULT NULL COMMENT '插入时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插入者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '赚客吧-小鸽驿站' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zkb_xgyz
-- ----------------------------
INSERT INTO `zkb_xgyz` VALUES ('276730', '7817053', '哪个老哥有cdr的破解版,最好是2020的感谢', '哪个老哥有cdr的破解版,最好是2020的感谢', 'http://www.zuanke8.com/thread-7817053-1-1.html', '天空很蓝', NULL, '2021-01-22 08:32:00', '0', '85', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276731', '7817055', '中信牛牛问题', '不是消费拿的金钥匙。就是积分兑换的颜值开出来的金钥匙。中了个扫地机。现在是必须要消费一笔2000的吗', 'http://www.zuanke8.com/thread-7817055-1-1.html', '她很好', NULL, '2021-01-22 08:33:00', '1', '176', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276732', '7817057', '融e联记账本洪水', '7-7   20通用', 'http://www.zuanke8.com/thread-7817057-1-1.html', 'zcpydj', NULL, '2021-01-22 08:34:00', '3', '363', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276733', '7817056', '大水，速度', '工银融e联app，首页搜索“记账本”，下拉功能那里点进去，弹抽奖，大概率通用券\n\n抽完以后右侧还有个签到，进入还能抽一次【图片】', 'http://www.zuanke8.com/thread-7817056-1-1.html', '17675678272', '083352jf7f6zx6myygfqqf.jpg', '2021-01-22 08:34:00', '8', '1360', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276734', '7817058', '融e联记账本洪水', '7-7  20通用', 'http://www.zuanke8.com/thread-7817058-1-1.html', 'zcpydj', NULL, '2021-01-22 08:35:00', '4', '452', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276735', '7817059', '工银融e联记账本不弹抽奖的可以这么操作，好用加果。', '工银融e联app，登录后，首页点记账本，等一会自动弹出抽奖，现在必中全场通用券，可以0撸。\n\n不弹抽奖的可以这么操作，搜索活动大厅，进去第一个活动，点抽奖。\n\n好用加果。', 'http://www.zuanke8.com/thread-7817059-1-1.html', 'blueparkman', NULL, '2021-01-22 08:35:00', '3', '337', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276736', '7817061', '网易现在开会员有什么方式能降低费用成本吗？', '现在不送豆，应该没车头了吧，大家来聊聊啊，现在都多少钱开的？', 'http://www.zuanke8.com/thread-7817061-1-1.html', '世之巅', NULL, '2021-01-22 08:38:00', '1', '98', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276737', '7817060', '逼乎年均过百万，抖音人均法拉利，我还是适合赚吧', '看着网络充斥着这些天朝富裕程度画面，感觉自己就不适合活着，过着赚吧撸毛生活，也没少被反撸（PX,FX,LB,DX等），加上疫情和公司变故，生活太难了。', 'http://www.zuanke8.com/thread-7817060-1-1.html', '284857914', NULL, '2021-01-22 08:38:00', '9', '286', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276738', '7817065', '027邮政生肖卡什么活动都没有吗？？', '我发现每个活动我都没资格参与。。。', 'http://www.zuanke8.com/thread-7817065-1-1.html', 'siscool', NULL, '2021-01-22 08:40:00', '0', '80', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276739', '7817063', '融易购10元券可以1分钱买卫生纸', '买过好几次了，还行，一分钱或者积分0【图片】', 'http://www.zuanke8.com/thread-7817063-1-1.html', 'wenmendnesday', '083928spqekq48mgbseur1.jpg', '2021-01-22 08:39:00', '5', '362', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276740', '7817066', '翼支付砍价的二维码他们是怎么转换的', '是翼支付可以直接扫出来的那种  \n不是微信扫出来的   ', 'http://www.zuanke8.com/thread-7817066-1-1.html', 'mike89', NULL, '2021-01-22 08:41:00', '2', '97', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276741', '7817068', '有找这个人刷e卡的吗', '同在装死【图片】', 'http://www.zuanke8.com/thread-7817068-1-1.html', 'ゞ_伴つ你一世ゞ', '084212jy9tyckccxlucqyw.jpg,084218gz46mb4b4d1omtl4.jpg', '2021-01-22 08:42:00', '5', '317', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276742', '7817070', '有果 求问 拼多多15火车票入口在哪里 搜了半天', '有果  求问 拼多多15火车票入口在哪里  搜了半天', 'http://www.zuanke8.com/thread-7817070-1-1.html', 'qq342035326', NULL, '2021-01-22 08:43:00', '1', '77', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276743', '7817071', '心里苦', '湖北的\n法规答题卡忘记填试卷类型了\n估了下分是70多的【图片】', 'http://www.zuanke8.com/thread-7817071-1-1.html', 'bandelu', '084324er7aaz54dig754rz.png', '2021-01-22 08:44:00', '2', '257', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276744', '7817072', '广发，国寿安保不算基金？', '刚刚买了3001，任务没完成，是不是买错了？【图片】', 'http://www.zuanke8.com/thread-7817072-1-1.html', 'hk1285', '084450lhs8h1hzde6hm666.jpg', '2021-01-22 08:44:00', '4', '154', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276745', '7817076', '广发每张卡被扣了60多的手续费。。', '广发每张卡被扣了60多的手续费。。', 'http://www.zuanke8.com/thread-7817076-1-1.html', 'stalker0214', NULL, '2021-01-22 08:47:00', '7', '460', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276746', '7817073', '天猫精灵cc10 联通版和公开版什么区别', '为什么差价这么大', 'http://www.zuanke8.com/thread-7817073-1-1.html', 'q276046897', NULL, '2021-01-22 08:45:00', '2', '155', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276747', '7817078', '移动和粉雪球飞行大挑战领200M~6GB流量', '移动和粉雪球飞行大挑战领200M~6GB流量\n\n活动期间，每位用户参与游戏次数不限制\n\n雪球飞行距离超过10米即可随机获得200M、500M、1GB、2GB流量券\n\n邀请好友首次关注公众号并参与活动可累计解锁相应档位流量券，最高得6GB\n\n活动地址：微信打开：<a>https://mp.weixin.qq.com/s/Pdq-270LYSl9yftPlNxeTw</a>【图片】', 'http://www.zuanke8.com/thread-7817078-1-1.html', '波gsgt110', '084733a6wo5h9ovoxze11s.png,084733vfb4bfis9baga2jf.png', '2021-01-22 08:47:00', '2', '220', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276748', '7817077', '有果，来个大哥解三个化学题！有果', '【图片】', 'http://www.zuanke8.com/thread-7817077-1-1.html', '13507627755', '084727n1wiz4m0wjw5ywyg.jpg', '2021-01-22 08:47:00', '4', '167', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276749', '7817080', '之前建材理财季活动买了100块钱理财在哪里看啊', '死活找不到，钱去哪了，在哪里能赎回啊', 'http://www.zuanke8.com/thread-7817080-1-1.html', 'hsyxsw', NULL, '2021-01-22 08:50:00', '0', '112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276750', '7817079', '万达影院抽奖试试', '万达影院app  我的  左上角小标  活动广播  第一个  免费抽取爆米花套餐    我中了个  你们可以试试', 'http://www.zuanke8.com/thread-7817079-1-1.html', '7s丶小闯', NULL, '2021-01-22 08:48:00', '1', '388', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276751', '7817085', '咨询下怎么推到交通、广发、浦发、华夏卡', '目前接近10行的卡，这几个行不好推了，有啥办法推倒？只有5块砖可用', 'http://www.zuanke8.com/thread-7817085-1-1.html', 'dyb1208', NULL, '2021-01-22 08:51:00', '3', '116', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276752', '7817084', '新号 登陆融e联20-20', '登录进去填完昵称保存就有了【图片】', 'http://www.zuanke8.com/thread-7817084-1-1.html', 'kookpua', '085134rzfbkexlbe3kfkzr.jpg', '2021-01-22 08:51:00', '3', '405', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276753', '7817081', '你们说的优衣库打折入口在哪里？', '没跟上节奏。', 'http://www.zuanke8.com/thread-7817081-1-1.html', '地球人丶', NULL, '2021-01-22 08:50:00', '3', '327', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276754', '7817086', '贺岁普通纪念币今晚预约！', '工行公众号\n\n今晚10点半和11点半两批次预约\n\n如图【图片】', 'http://www.zuanke8.com/thread-7817086-1-1.html', 'sailcom', '085531nn55rn7ae7a89857.jpg', '2021-01-22 08:55:00', '3', '211', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276755', '7817087', '招行金葵花有什么用么', 'rt 最近卡应该到了50w阈值  客户经理隔几天一个电话问我办不办理金葵花 要到网点办  吧里老哥有办这个的么  大概有什么实用的权益  然后还得找路子免管理费？？', 'http://www.zuanke8.com/thread-7817087-1-1.html', 'shinayf', NULL, '2021-01-22 08:56:00', '1', '82', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276756', '7817088', '微信关注公众号亲测0.33元微信红包(每天可参与)', '微信关注“卡士CLASSYKISS”->菜单栏非必中->中了会推包每天可参与->亲测0.33元微信红包！【图片】', 'http://www.zuanke8.com/thread-7817088-1-1.html', '波gsgt110', '085726khmm882nqndqzhlg.png', '2021-01-22 08:57:00', '0', '126', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276757', '7817092', '淘宝付款200元有什么支付优惠？', '好久没用淘宝，不熟悉', 'http://www.zuanke8.com/thread-7817092-1-1.html', '红米note', NULL, '2021-01-22 08:59:00', '0', '2', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276758', '7817091', '看了一眼plus 105降到了101', '擦', 'http://www.zuanke8.com/thread-7817091-1-1.html', '宋不败', NULL, '2021-01-22 08:58:00', '2', '115', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276759', '7817096', '关于生肖纪念币预约必知', '这一次一个省是好几个银行一起预约，但并不是每个市都可以在这些银行兑换，刚查了下，应该是一个银行负责几个市，大家预约之前先确定好自己所在地是哪个银行', 'http://www.zuanke8.com/thread-7817096-1-1.html', '小风哈哈', NULL, '2021-01-22 09:00:00', '0', '84', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276760', '7817095', '速度 白条有概率55-5 扫码直达', '【图片】', 'http://www.zuanke8.com/thread-7817095-1-1.html', '饮品辉煌', '085938l53534cxeoeeoic6.jpg', '2021-01-22 09:00:00', '0', '47', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276761', '7817094', '融e联不出是不是黑号？', '   三个号不出，一个出了', 'http://www.zuanke8.com/thread-7817094-1-1.html', 'snoopyzf1', NULL, '2021-01-22 09:00:00', '0', '44', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276762', '7817093', '招行读文章得的钱越来越低了', '看了昨天的到账记录只有3毛9，如果你是在这月月尾提现10块的话，下月万一每天跌到3毛8以下，就有可能每月提不到11块而造成有些天数过期的情况了', 'http://www.zuanke8.com/thread-7817093-1-1.html', 'ZEEROKAMIHF', NULL, '2021-01-22 09:00:00', '1', '127', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276763', '7817097', '河北农行xyk微信刷卡金50元其它地区自测', '京东买E卡微信农行xyk支付100返10元刷卡金，返五次。扫商家码也有刷卡金', 'http://www.zuanke8.com/thread-7817097-1-1.html', '联合资讯', NULL, '2021-01-22 09:00:00', '3', '134', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276764', '7817100', '融E购特仑苏满减搭配全品减20券后60两箱还可以', '如题【图片】', 'http://www.zuanke8.com/thread-7817100-1-1.html', 'huhaoran', '090139mtfolu7lyd56la8y.jpg', '2021-01-22 09:01:00', '3', '233', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276765', '7817099', '交行5积分好久抢不到了。。。', '太容易完成了。。。', 'http://www.zuanke8.com/thread-7817099-1-1.html', '善良的小川', NULL, '2021-01-22 09:01:00', '11', '306', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276766', '7817103', '电信话费抵用券，适合刚需', 'VX中国电信上海网厅，历史一【图片】', 'http://www.zuanke8.com/thread-7817103-1-1.html', 'youma123', '090217qz69dtj9n5n5qz5z.jpg', '2021-01-22 09:02:00', '3', '313', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276767', '7817102', '刚刚的aj太牛逼了，都用的什么软件呀？', '影子都没看到', 'http://www.zuanke8.com/thread-7817102-1-1.html', '火星蹦迪', NULL, '2021-01-22 09:02:00', '8', '515', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276768', '7817105', '现在还有什么银行理财可以买？', '收益高的，谢谢', 'http://www.zuanke8.com/thread-7817105-1-1.html', 'iamfly', NULL, '2021-01-22 09:03:00', '3', '108', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276769', '7817101', '中国银行山东分行领微信立减金', '关注公众号，中国银行山东分行，点我的，就看到了【图片】', 'http://www.zuanke8.com/thread-7817101-1-1.html', '卖炭翁', '090207laktw20zw13t14hz.jpg', '2021-01-22 09:02:00', '4', '193', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276770', '7817107', 'pdd真够不要脸', '第一次用多多买菜，提示邀请一个人必奖励30元，用我妈的号下了一单，到账30，结果满50才能提现。\n\n好吧，继续邀请了两个人，加了10块钱多，然后弹出来说邀请一个新人直接满50提现，我看结束时间还有30分钟，就又邀请了1个，那边刚跟我说下完单，好戏来了，多多买菜进不去了。不管是app的入口，还是小程序，进不去了，之前的订单在列表里可以看，点进去就是白屏。\n\n如果我违规，请送我几张优惠券惩罚我，不要用这种不要脸的方式', 'http://www.zuanke8.com/thread-7817107-1-1.html', '东少11007', NULL, '2021-01-22 09:03:00', '13', '292', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276771', '7817106', '现在非布司他这么便宜？', '这几年尿酸一直高，但是没有什么通风反应\n听说非布司他便宜了去开了二盒才32', 'http://www.zuanke8.com/thread-7817106-1-1.html', 'sorr_y', NULL, '2021-01-22 09:03:00', '2', '159', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276772', '7817108', '广发反撸了？昨晚取现，现在这样子了', '昨晚提现溢存款显示失败，然后试了好多次，今早一看额度傻眼了，结果发现自动买了理财，而且看数额不是整数，应该扣手续费了！！！反撸【图片】', 'http://www.zuanke8.com/thread-7817108-1-1.html', 'cilunhui', '090357gtd4747dqlp4t4ob.jpg,090408oa1woa2x2x8o0ax1.jpg', '2021-01-22 09:04:00', '0', '269', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276773', '7817110', '每天小毛 京喜红包和礼金券', '1.特别提醒，询价、询问、交换、合作求代下任何交易相关的帖，都必须发布到赚品交换区; 2.严禁发布时事类，影视版权类帖子; 3.严禁发布他人个人信息以及骚扰类 帖子【图片】', 'http://www.zuanke8.com/thread-7817110-1-1.html', '从心出发2020', '090513fsqse4e4dus6ud22.png,090529is4t3rzrisfx3e9x.png', '2021-01-22 09:05:00', '0', '73', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276774', '7817114', '浣洗恢复了余额还在', '恢复了', 'http://www.zuanke8.com/thread-7817114-1-1.html', '疯八', NULL, '2021-01-22 09:06:00', '2', '255', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276775', '7817115', '赶紧干，90%佣 3.8元38只套套', '【图片】', 'http://www.zuanke8.com/thread-7817115-1-1.html', 'mmmm362', '090610m7fjfj8m65eev6tf.png', '2021-01-22 09:07:00', '8', '356', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276776', '7817117', '每天小毛 京喜红包和礼金券', '前面发错了,再发【图片】', 'http://www.zuanke8.com/thread-7817117-1-1.html', '从心出发2020', '090755gw21l2nvv2xm2d5x.png', '2021-01-22 09:08:00', '3', '205', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276777', '7817116', '速度 京东1元白条券 扫码直达', '  扫码领。领到加果哦~【图片】', 'http://www.zuanke8.com/thread-7817116-1-1.html', 'sh32ppy', '090653mho4q0i7yqfb902q.png', '2021-01-22 09:07:00', '0', '306', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276778', '7817118', '真快乐除了米面还买什么？大家都上了多少单自报一下', '米面粮油还买啥', 'http://www.zuanke8.com/thread-7817118-1-1.html', 'yy8585', NULL, '2021-01-22 09:08:00', '5', '188', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276779', '7817120', '浣洗修复好了，余额又回来了', '看来这是不打算回收了？爽歪歪啊。余额应该没有有效期吧，以后慢慢用了', 'http://www.zuanke8.com/thread-7817120-1-1.html', '算什么男人', NULL, '2021-01-22 09:09:00', '5', '305', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276780', '7817119', '请问美团外卖中行xing/用卡20-10，是几点开始呀', '我的怎么没有显示。', 'http://www.zuanke8.com/thread-7817119-1-1.html', 'zkblcf', NULL, '2021-01-22 09:08:00', '3', '181', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276781', '7817122', '邮储打卡刚中10元e卡', '终于中一个', 'http://www.zuanke8.com/thread-7817122-1-1.html', '小荷才落尖尖角', NULL, '2021-01-22 09:10:00', '2', '225', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276782', '7817121', 'plus用拆单了，有单不要是是拒收还是收货无理由退货好，哪个不黑号，果果熟了', 'plus用拆单了，有单不要是是拒收还是收货无理由退货好，哪个不黑号，果果熟了', 'http://www.zuanke8.com/thread-7817121-1-1.html', '烤地瓜', NULL, '2021-01-22 09:10:00', '1', '71', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276783', '7817123', '重庆有收驾照分的吗', '老哥们。。', 'http://www.zuanke8.com/thread-7817123-1-1.html', 'wrtian', NULL, '2021-01-22 09:11:00', '1', '69', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276784', '7817128', '币圈大跌已经破3万刀大关了', '币圈大跌已经破3万刀大关了，应该还会下跌一段时间，现在千万不要入坑哦【图片】', 'http://www.zuanke8.com/thread-7817128-1-1.html', 'sflower2010', '091238k5ee77ka63je62z3.png,091301ogmkemmunpvgfgvc.jpeg', '2021-01-22 09:13:00', '0', '2', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276785', '7817130', '浣洗你们能登录吗？', '邀请的小程序，填完验证码后，点登录没反应', 'http://www.zuanke8.com/thread-7817130-1-1.html', '小白的天空', NULL, '2021-01-22 09:13:00', '0', '50', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276786', '7817129', '联通500券的1号原订单发货了', '下的红米X10 5G的8G+128版本\n电话过来的500+50方案没有接受，要求100代金券没有争取下来。\n昨天再次沟通客服，结果当晚就发货了，京东物流。', 'http://www.zuanke8.com/thread-7817129-1-1.html', 'xialier', NULL, '2021-01-22 09:13:00', '0', '58', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276787', '7817133', '今晚约牛逼了，这次够乱的 附查询数量链接', '整天瞎鸡儿改，我山东的，看了下这次除了工行可以约，浦发、交通 都可以就是数量少点\n\n\n<a>http://www.icbc.com.cn/icbc/%e7%ba%aa%e5%bf%b5%e5%b8%81%e4%b8%93%e5%8c%ba/%e5%90%84%e5%9c%b0%e5%8f%91%e5%94%ae%e4%bf%a1%e6%81%af/%e5%85%b3%e4%ba%8e2021%e5%b9%b4%e8%b4%ba%e5%b2%81%e6%99%ae%e9%80%9a%e7%ba%aa%e5%bf%b5%e5%b8%81%e9%a2%84%e7%ba%a6%e5%85%91%e6%8d%a2%e7%9a%84%e5%85%ac%e5%91%8a210121.htm</a>', 'http://www.zuanke8.com/thread-7817133-1-1.html', 'zhang661', NULL, '2021-01-22 09:15:00', '0', '387', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276788', '7817132', '邮储打卡水10e卡', '【图片】', 'http://www.zuanke8.com/thread-7817132-1-1.html', 'tt01688', '091411bz8mmk0863cb8epg.jpg', '2021-01-22 09:15:00', '3', '238', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276789', '7817131', '昨天提的溢缴款，今天没扣手续费就稳了吧？', '    不会账单日再扣吧？给你们说的翻来覆去看了好多遍，找不到扣款项', 'http://www.zuanke8.com/thread-7817131-1-1.html', 'snoopyzf1', NULL, '2021-01-22 09:14:00', '3', '131', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276790', '7817137', '邮储签到有水', '【图片】', 'http://www.zuanke8.com/thread-7817137-1-1.html', 'xinyu5253', '091657m7oo05e352vea576.jpg', '2021-01-22 09:17:00', '2', '220', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276791', '7817136', '我怀疑刷建行的都是一批人', '他们微信号都特别简单，像给我刷的up68800，我看了好几个都这样，我特么25刷的，太过分了', 'http://www.zuanke8.com/thread-7817136-1-1.html', '为人民服务。', NULL, '2021-01-22 09:16:00', '14', '533', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276792', '7817135', '小孩看牙齿抽牙神经是不是不能用医保啊？', '两边的牙齿都蛀在两个大牙之间，就是一下子就是4颗大牙，不能用医保4颗大牙估计得不少钱，一颗牙齿抽神经得跑3趟。一般大医院没有助手还不能看儿童牙齿，只能跑口腔医院或者私立口腔医院。', 'http://www.zuanke8.com/thread-7817135-1-1.html', 'bijiasuo', NULL, '2021-01-22 09:16:00', '2', '100', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276793', '7817144', '肯德基的新春皮卡丘玩具看起来有点low，能卖出去吗？', '还没看到实物，只看图片的话，看起来比圣诞节那个差远了', 'http://www.zuanke8.com/thread-7817144-1-1.html', '水灵清', NULL, '2021-01-22 09:18:00', '1', '167', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276794', '7817142', '优衣库这两件，有人买过吗？', '感觉质量怎么样？好不好看？【图片】', 'http://www.zuanke8.com/thread-7817142-1-1.html', '余罪', '091816unfr1nnrq1czn8f2.jpg', '2021-01-22 09:18:00', '14', '382', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276795', '7817141', '兄弟们，沃派巴卡限速了', '限速了，能解除吗？客服让我订购加速包或者5G升级包。要不然等下个月恢复。', 'http://www.zuanke8.com/thread-7817141-1-1.html', '就爱薅羊毛c', NULL, '2021-01-22 09:18:00', '3', '173', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276796', '7817145', '韦尔转债爽啊', '直接150', 'http://www.zuanke8.com/thread-7817145-1-1.html', 'sumax', NULL, '2021-01-22 09:19:00', '2', '343', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276797', '7817147', '韦尔转债大家开盘卖还是', '韦尔转债大家开盘卖还是', 'http://www.zuanke8.com/thread-7817147-1-1.html', 'zimi', NULL, '2021-01-22 09:20:00', '2', '149', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276798', '7817150', '69-20，买这个气泡水划算吗？？？准备过年喝', '雀巢 （Nestle）优活 蓝莓黑加仑风味无糖无热量无脂肪气泡水饮料汽水450ML*15瓶 整箱装\n\n算下来 1.5一瓶，划算不，各位老铁', 'http://www.zuanke8.com/thread-7817150-1-1.html', '奋の斗', NULL, '2021-01-22 09:22:00', '0', '59', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276799', '7817152', '融e购 特仑苏没货了 低脂的怎么样', '速度真快', 'http://www.zuanke8.com/thread-7817152-1-1.html', 'mmmm362', NULL, '2021-01-22 09:23:00', '0', '29', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276800', '7817151', 'PDD戴森V10国行入门版、2488可以上车吗？', 'RT\n\n百亿补贴V10 MO、4个头那款2488 还是国行、\n\n能上吗？有没有吧友在PDD买过戴森的稳不稳？', 'http://www.zuanke8.com/thread-7817151-1-1.html', 'ai0108xb', NULL, '2021-01-22 09:23:00', '0', '36', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz` VALUES ('276801', '7817153', '请问老哥们，山东的公众号茅台自提点在哪', '公众号预约的山东茅台有货了，有没有老哥知道自提点在哪啊。特殊时期去外地都要报备，有点麻烦啊。', 'http://www.zuanke8.com/thread-7817153-1-1.html', 'lixiaopeng0977', NULL, '2021-01-22 09:23:00', '0', '36', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for zkb_xgyz_log
-- ----------------------------
DROP TABLE IF EXISTS `zkb_xgyz_log`;
CREATE TABLE `zkb_xgyz_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `siteId` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '站点ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `mainText` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正文',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '链接',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `imgList` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片列表（逗号分隔）',
  `relTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发帖时间',
  `replyNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复数',
  `readNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '阅读数',
  `del_flag` int NULL DEFAULT 0 COMMENT '状态标志：0正常 1禁用 2删除',
  `create_date` datetime NULL DEFAULT NULL COMMENT '插入时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插入者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '赚客吧-小鸽驿站' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zkb_xgyz_log
-- ----------------------------
INSERT INTO `zkb_xgyz_log` VALUES ('276764', '7817100', '融E购特仑苏满减搭配全品减20券后60两箱还可以', '如题【图片】', 'http://www.zuanke8.com/thread-7817100-1-1.html', 'huhaoran', '090139mtfolu7lyd56la8y.jpg', '2021-01-22 09:01:00', '0', '111', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276765', '7817099', '交行5积分好久抢不到了。。。', '太容易完成了。。。', 'http://www.zuanke8.com/thread-7817099-1-1.html', '善良的小川', NULL, '2021-01-22 09:01:00', '7', '192', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276766', '7817103', '电信话费抵用券，适合刚需', 'VX中国电信上海网厅，历史一【图片】', 'http://www.zuanke8.com/thread-7817103-1-1.html', 'youma123', '090217qz69dtj9n5n5qz5z.jpg', '2021-01-22 09:02:00', '0', '2', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276767', '7817102', '刚刚的aj太牛逼了，都用的什么软件呀？', '影子都没看到', 'http://www.zuanke8.com/thread-7817102-1-1.html', '火星蹦迪', NULL, '2021-01-22 09:02:00', '1', '148', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276768', '7817105', '现在还有什么银行理财可以买？', '收益高的，谢谢', 'http://www.zuanke8.com/thread-7817105-1-1.html', 'iamfly', NULL, '2021-01-22 09:03:00', '0', '31', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276769', '7817101', '中国银行山东分行领微信立减金', '关注公众号，中国银行山东分行，点我的，就看到了【图片】', 'http://www.zuanke8.com/thread-7817101-1-1.html', '卖炭翁', '090207laktw20zw13t14hz.jpg', '2021-01-22 09:02:00', '0', '64', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276770', '7817107', 'pdd真够不要脸', '第一次用多多买菜，提示邀请一个人必奖励30元，用我妈的号下了一单，到账30，结果满50才能提现。\n\n好吧，继续邀请了两个人，加了10块钱多，然后弹出来说邀请一个新人直接满50提现，我看结束时间还有30分钟，就又邀请了1个，那边刚跟我说下完单，好戏来了，多多买菜进不去了。不管是app的入口，还是小程序，进不去了，之前的订单在列表里可以看，点进去就是白屏。\n\n如果我违规，请送我几张优惠券惩罚我，不要用这种不要脸的方式', 'http://www.zuanke8.com/thread-7817107-1-1.html', '东少11007', NULL, '2021-01-22 09:03:00', '0', '56', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276771', '7817106', '现在非布司他这么便宜？', NULL, 'http://www.zuanke8.com/thread-7817106-1-1.html', 'sorr_y', NULL, '2021-01-22 09:03:00', '0', '40', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276772', '7817108', '广发反撸了？昨晚取现，现在这样子了', '昨晚提现溢存款显示失败，然后试了好多次，今早一看额度傻眼了，结果发现自动买了理财，而且看数额不是整数，应该扣手续费了！！！反撸【图片】', 'http://www.zuanke8.com/thread-7817108-1-1.html', 'cilunhui', '090357gtd4747dqlp4t4ob.jpg,090408oa1woa2x2x8o0ax1.jpg', '2021-01-22 09:04:00', '0', '79', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276773', '7817110', '每天小毛 京喜红包和礼金券', '1.特别提醒，询价、询问、交换、合作求代下任何交易相关的帖，都必须发布到赚品交换区; 2.严禁发布时事类，影视版权类帖子; 3.严禁发布他人个人信息以及骚扰类 帖子【图片】', 'http://www.zuanke8.com/thread-7817110-1-1.html', '从心出发2020', 'http://img.zuanke8.com/forum/202101/22/090513fsqse4e4dus6ud22.png,http://img.zuanke8.com/forum/202101/22/090529is4t3rzrisfx3e9x.png', '2021-01-22 09:05:00', '0', '23', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276774', '7817114', '浣洗恢复了余额还在', '恢复了', 'http://www.zuanke8.com/thread-7817114-1-1.html', '疯八', NULL, '2021-01-22 09:06:00', '0', '33', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276775', '7817115', '赶紧干，90%佣 3.8元38只套套', '【图片】', 'http://www.zuanke8.com/thread-7817115-1-1.html', 'mmmm362', 'http://img.zuanke8.com/forum/202101/22/090610m7fjfj8m65eev6tf.png', '2021-01-22 09:07:00', '0', '14', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276776', '7817117', '每天小毛 京喜红包和礼金券', '前面发错了,再发【图片】', 'http://www.zuanke8.com/thread-7817117-1-1.html', '从心出发2020', '090755gw21l2nvv2xm2d5x.png', '2021-01-22 09:08:00', '1', '62', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276777', '7817116', '速度 京东1元白条券 扫码直达', '  扫码领。领到加果哦~【图片】', 'http://www.zuanke8.com/thread-7817116-1-1.html', 'sh32ppy', '090653mho4q0i7yqfb902q.png', '2021-01-22 09:07:00', '0', '127', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276778', '7817118', '真快乐除了米面还买什么？大家都上了多少单自报一下', '米面粮油还买啥', 'http://www.zuanke8.com/thread-7817118-1-1.html', 'yy8585', NULL, '2021-01-22 09:08:00', '0', '34', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276779', '7817120', '浣洗修复好了，余额又回来了', '看来这是不打算回收了？爽歪歪啊。余额应该没有有效期吧，以后慢慢用了', 'http://www.zuanke8.com/thread-7817120-1-1.html', '算什么男人', NULL, '2021-01-22 09:09:00', '0', '30', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276780', '7817119', '请问美团外卖中行xing/用卡20-10，是几点开始呀', '我的怎么没有显示。', 'http://www.zuanke8.com/thread-7817119-1-1.html', 'zkblcf', NULL, '2021-01-22 09:08:00', '2', '82', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276781', '7817122', '邮储打卡刚中10元e卡', '终于中一个', 'http://www.zuanke8.com/thread-7817122-1-1.html', '小荷才落尖尖角', NULL, '2021-01-22 09:10:00', '0', '5', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276782', '7817121', 'plus用拆单了，有单不要是是拒收还是收货无理由退货好，哪个不黑号，果果熟了', 'plus用拆单了，有单不要是是拒收还是收货无理由退货好，哪个不黑号，果果熟了', 'http://www.zuanke8.com/thread-7817121-1-1.html', '烤地瓜', NULL, '2021-01-22 09:10:00', '0', '36', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276783', '7817123', '重庆有收驾照分的吗', '老哥们。。', 'http://www.zuanke8.com/thread-7817123-1-1.html', 'wrtian', NULL, '2021-01-22 09:11:00', '0', '36', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276784', '7817128', '币圈大跌已经破3万刀大关了', '币圈大跌已经破3万刀大关了，应该还会下跌一段时间，现在千万不要入坑哦【图片】', 'http://www.zuanke8.com/thread-7817128-1-1.html', 'sflower2010', '091238k5ee77ka63je62z3.png,091301ogmkemmunpvgfgvc.jpeg', '2021-01-22 09:13:00', '0', '28', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276785', '7817130', '浣洗你们能登录吗？', '邀请的小程序，填完验证码后，点登录没反应', 'http://www.zuanke8.com/thread-7817130-1-1.html', '小白的天空', NULL, '2021-01-22 09:13:00', '0', '50', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276786', '7817129', '联通500券的1号原订单发货了', '下的红米X10 5G的8G+128版本\n电话过来的500+50方案没有接受，要求100代金券没有争取下来。\n昨天再次沟通客服，结果当晚就发货了，京东物流。', 'http://www.zuanke8.com/thread-7817129-1-1.html', 'xialier', NULL, '2021-01-22 09:13:00', '0', '58', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276787', '7817133', '今晚约牛逼了，这次够乱的 附查询数量链接', '整天瞎鸡儿改，我山东的，看了下这次除了工行可以约，浦发、交通 都可以就是数量少点\n\n\n<a>http://www.icbc.com.cn/icbc/%e7%ba%aa%e5%bf%b5%e5%b8%81%e4%b8%93%e5%8c%ba/%e5%90%84%e5%9c%b0%e5%8f%91%e5%94%ae%e4%bf%a1%e6%81%af/%e5%85%b3%e4%ba%8e2021%e5%b9%b4%e8%b4%ba%e5%b2%81%e6%99%ae%e9%80%9a%e7%ba%aa%e5%bf%b5%e5%b8%81%e9%a2%84%e7%ba%a6%e5%85%91%e6%8d%a2%e7%9a%84%e5%85%ac%e5%91%8a210121.htm</a>', 'http://www.zuanke8.com/thread-7817133-1-1.html', 'zhang661', NULL, '2021-01-22 09:15:00', '0', '306', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276788', '7817132', '邮储打卡水10e卡', '【图片】', 'http://www.zuanke8.com/thread-7817132-1-1.html', 'tt01688', '091411bz8mmk0863cb8epg.jpg', '2021-01-22 09:15:00', '3', '193', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276789', '7817131', '昨天提的溢缴款，今天没扣手续费就稳了吧？', '    不会账单日再扣吧？给你们说的翻来覆去看了好多遍，找不到扣款项', 'http://www.zuanke8.com/thread-7817131-1-1.html', 'snoopyzf1', NULL, '2021-01-22 09:14:00', '3', '112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276790', '7817137', '邮储签到有水', '【图片】', 'http://www.zuanke8.com/thread-7817137-1-1.html', 'xinyu5253', '091657m7oo05e352vea576.jpg', '2021-01-22 09:17:00', '0', '123', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276791', '7817136', '我怀疑刷建行的都是一批人', '他们微信号都特别简单，像给我刷的up68800，我看了好几个都这样，我特么25刷的，太过分了', 'http://www.zuanke8.com/thread-7817136-1-1.html', '为人民服务。', NULL, '2021-01-22 09:16:00', '9', '302', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276792', '7817135', '小孩看牙齿抽牙神经是不是不能用医保啊？', '两边的牙齿都蛀在两个大牙之间，就是一下子就是4颗大牙，不能用医保4颗大牙估计得不少钱，一颗牙齿抽神经得跑3趟。一般大医院没有助手还不能看儿童牙齿，只能跑口腔医院或者私立口腔医院。', 'http://www.zuanke8.com/thread-7817135-1-1.html', 'bijiasuo', NULL, '2021-01-22 09:16:00', '1', '58', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276793', '7817144', '肯德基的新春皮卡丘玩具看起来有点low，能卖出去吗？', '还没看到实物，只看图片的话，看起来比圣诞节那个差远了', 'http://www.zuanke8.com/thread-7817144-1-1.html', '水灵清', NULL, '2021-01-22 09:18:00', '0', '33', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276794', '7817142', '优衣库这两件，有人买过吗？', '感觉质量怎么样？好不好看？【图片】', 'http://www.zuanke8.com/thread-7817142-1-1.html', '余罪', '091816unfr1nnrq1czn8f2.jpg', '2021-01-22 09:18:00', '1', '65', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276795', '7817141', '兄弟们，沃派巴卡限速了', '限速了，能解除吗？客服让我订购加速包或者5G升级包。要不然等下个月恢复。', 'http://www.zuanke8.com/thread-7817141-1-1.html', '就爱薅羊毛c', NULL, '2021-01-22 09:18:00', '0', '52', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276796', '7817145', '韦尔转债爽啊', '直接150', 'http://www.zuanke8.com/thread-7817145-1-1.html', 'sumax', NULL, '2021-01-22 09:19:00', '0', '15', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276797', '7817147', '韦尔转债大家开盘卖还是', '韦尔转债大家开盘卖还是', 'http://www.zuanke8.com/thread-7817147-1-1.html', 'zimi', NULL, '2021-01-22 09:20:00', '0', '49', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276798', '7817150', '69-20，买这个气泡水划算吗？？？准备过年喝', NULL, 'http://www.zuanke8.com/thread-7817150-1-1.html', '奋の斗', NULL, '2021-01-22 09:22:00', '0', '26', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276799', '7817152', '融e购 特仑苏没货了 低脂的怎么样', NULL, 'http://www.zuanke8.com/thread-7817152-1-1.html', 'mmmm362', NULL, '2021-01-22 09:23:00', '0', '18', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276800', '7817151', 'PDD戴森V10国行入门版、2488可以上车吗？', NULL, 'http://www.zuanke8.com/thread-7817151-1-1.html', 'ai0108xb', NULL, '2021-01-22 09:23:00', '0', '20', 0, NULL, NULL, NULL, NULL);
INSERT INTO `zkb_xgyz_log` VALUES ('276801', '7817153', '请问老哥们，山东的公众号茅台自提点在哪', '公众号预约的山东茅台有货了，有没有老哥知道自提点在哪啊。特殊时期去外地都要报备，有点麻烦啊。', 'http://www.zuanke8.com/thread-7817153-1-1.html', 'lixiaopeng0977', NULL, '2021-01-22 09:23:00', '0', '36', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Triggers structure for table zkb_xgyz
-- ----------------------------
DROP TRIGGER IF EXISTS `xgyz_trigger`;
delimiter ;;
CREATE TRIGGER `xgyz_trigger` AFTER INSERT ON `zkb_xgyz` FOR EACH ROW BEGIN
		INSERT INTO zkb_xgyz_log ( id, siteId, label, mainText, href, author, imgList,relTime,replyNum,readNum,del_flag,create_date,create_by,update_by,update_date )
	VALUES
		(
				new.id, 
	new.siteId, 
	new.label, 
	new.mainText, 
	new.href, 
	new.author, 
	new.imgList, 
	new.relTime, 
	new.replyNum, 
	new.readNum, 
	new.del_flag, 
	new.create_date, 
	new.create_by, 
	new.update_by, 
	new.update_date
		);
	
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
