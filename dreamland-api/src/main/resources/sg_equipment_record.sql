/*
 Navicat Premium Data Transfer

 Source Server         : him-device-dev
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 47.105.144.190:23306
 Source Schema         : wuhan2020

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 08/02/2021 17:28:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sg_equipment_record
-- ----------------------------
DROP TABLE IF EXISTS `sg_equipment_record`;
CREATE TABLE `sg_equipment_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `equipment_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备编码',
  `pass_state` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通行状态：1-通过；2-失败；3-无效；4-证件失效',
  `health_code` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康码状态：0；绿码；1：黄码；2：红码',
  `temperature_sen` decimal(3, 1) NULL DEFAULT NULL COMMENT '体温(表面)',
  `temperature_body` decimal(3, 1) NULL DEFAULT NULL COMMENT '体温',
  `is_mask` tinyint(1) NULL DEFAULT 1 COMMENT '是否戴口罩',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '照片地址',
  `last_travel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上一次行程（来源地）',
  `mq_time` datetime NULL DEFAULT NULL COMMENT 'MQ时间',
  `source` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '01' COMMENT '来源：01-设备采集；02-手动登记',
  `phone` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_date` datetime NULL DEFAULT NULL COMMENT '接收时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记人',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `equipment_code`(`equipment_code`, `mq_time`) USING BTREE,
  INDEX `idx_equ_code`(`equipment_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1358708688422768643 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '体温采集数据记录表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sg_equipment_record
-- ----------------------------
INSERT INTO `sg_equipment_record` VALUES (1357169740605505537, '靳继杭', '372926198808082219', 'ec101', '1', '1', 36.6, 36.6, 1, NULL, '山东省巨野县龙堌镇靳庄行政村靳庄村226号', '2021-02-04 11:31:55', '01', '17669671235', '2021-02-04 11:30:56', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169740607746050, '靳继杭', '372926198808082219', 'ec101', '1', '1', 36.6, 36.6, 1, NULL, '山东省巨野县龙堌镇靳庄行政村靳庄村226号', '2021-02-04 11:31:53', '01', '17669671235', '2021-02-04 11:30:56', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169740614770689, '靳继杭', '372926198808082219', 'ec101', '1', '1', 36.6, 36.6, 1, NULL, '山东省巨野县龙堌镇靳庄行政村靳庄村226号', '2021-02-04 11:31:54', '01', '17669671235', '2021-02-04 11:30:56', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169740725673986, '靳继杭', '372926198808082219', 'ec101', '1', '1', 36.6, 36.6, 1, NULL, '山东省巨野县龙堌镇靳庄行政村靳庄村226号', '2021-02-04 11:31:52', '01', '17669671235', '2021-02-04 11:30:56', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169843313668097, '王鹏', '371522198910170014', 'ec101', '1', '1', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区海尔路1号', '2021-02-04 11:31:29', '01', '17669671235', '2021-02-04 11:31:20', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169843324010497, '王鹏', '371522198910170014', 'ec101', '2', '1', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区海尔路1号', '2021-02-04 11:31:35', '01', '17669671235', '2021-02-05 11:31:20', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169843345858561, '王鹏', '371522198910170014', 'ec101', '1', '1', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区海尔路1号', '2021-02-04 11:31:26', '01', '17669671235', '2021-02-04 11:31:20', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357169843431596034, '王鹏', '371522198910170014', 'ec101', '1', '1', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区海尔路1号', '2021-02-04 11:31:25', '01', '17669671235', '2021-02-04 11:31:20', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357187934440169473, '王飞', '370602199209230652', 'ec101', '1', '2', 0.0, 36.6, 1, NULL, '山东省青岛市崂山区中韩街道', '2021-02-04 12:45:55', '01', '17669671235', '2021-02-04 12:43:14', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357187934504935425, '王飞', '370602199209230652', 'ec101', '2', '2', 0.0, 38.0, 1, NULL, '山东省青岛市崂山区中韩街道', '2021-02-04 12:45:54', '01', '17669671235', '2021-02-05 12:43:14', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357213068808269826, '田发胜', '370602199209230652', 'ec101', '1', '2', 0.0, 36.6, 1, NULL, '山东省青岛市崂山区中韩街道', '2021-02-04 14:45:58', '01', '17669671235', '2021-02-04 14:23:06', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1357213070655647746, '田发胜', '370602199209230652', 'ec101', '1', '2', 0.0, 36.6, 1, NULL, '山东省青岛市崂山区中韩街道', '2021-02-04 14:45:59', '01', '17669671232', '2021-02-04 14:23:07', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358349661002858498, '黄海平', '370682198705262439', 'ec101', '1', '1', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-07 14:40:27', '01', NULL, '2021-02-07 17:39:31', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358349662353424386, '黄海平', '370682198705262439', 'ec101', '1', '1', 36.6, 36.6, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-07 14:40:42', '01', NULL, '2021-02-07 17:39:31', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358349663532023810, '黄海平', '370682198705262439', 'ec101', '1', '2', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-07 14:52:39', '01', NULL, '2021-02-07 17:39:31', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358350001584537602, '黄海平', '370682198705262439', 'ec101', '1', '2', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-07 17:40:49', '01', NULL, '2021-02-07 17:40:52', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358350003165790209, '黄海平', '370682198705262439', 'ec101', '1', '2', 36.6, 36.6, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-07 17:41:00', '01', NULL, '2021-02-07 17:40:52', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358589938472673282, '黄海平', '370682198705262439', '689322761', '1', '0', 36.7, 36.7, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-08 09:34:33', '01', NULL, '2021-02-08 09:34:17', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358589941715361793, '黄海平', '370682198705262439', '689322763', '1', '0', 36.7, 36.7, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-08 09:34:34', '01', NULL, '2021-02-08 09:34:18', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358702686549905410, '田发胜', '371323199107077910', '689322763', '1', '0', 36.6, 36.6, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:02:36', '01', NULL, '2021-02-08 17:02:19', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358703204701638657, '赵磊', '370205198512123016', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省青岛市市北区瑞昌路59号二单元102户附1户', '2021-02-08 17:04:39', '01', NULL, '2021-02-08 17:04:22', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358703727215448065, '赵磊', '370205198512123016', '689322763', '1', '0', 36.6, 36.6, 1, NULL, '山东省青岛市市北区瑞昌路59号二单元102户附1户', '2021-02-08 17:06:44', '01', NULL, '2021-02-08 17:06:27', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358703885877579777, '赵磊', '370205198512123016', '689322763', '1', '0', 36.4, 36.4, 1, NULL, '山东省青岛市市北区瑞昌路59号二单元102户附1户', '2021-02-08 17:07:22', '01', NULL, '2021-02-08 17:07:05', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358703941187866625, '赵磊', '370205198512123016', '689322763', '1', '0', 36.4, 36.4, 1, NULL, '山东省青岛市市北区瑞昌路59号二单元102户附1户', '2021-02-08 17:07:33', '01', NULL, '2021-02-08 17:07:18', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358705674844704770, '田发胜', '371323199107077910', '689322763', '1', '-1', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:14:24', '01', NULL, '2021-02-08 17:14:11', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706170137481217, '田发胜', '371323199107077910', '689322763', '1', '-1', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:15:19', '01', NULL, '2021-02-08 17:16:09', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706296482500610, '田发胜', '371323199107077910', '689322763', '1', '-1', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:16:30', '01', NULL, '2021-02-08 17:16:39', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706297770151937, '田发胜', '371323199107077910', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:16:50', '01', NULL, '2021-02-08 17:16:40', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706634996387841, '田发胜', '371323199107077910', '689322763', '1', '-1', 36.6, 36.6, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:18:12', '01', NULL, '2021-02-08 17:18:00', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706681519607810, '田发胜', '371323199107077910', '689322763', '1', '0', 36.6, 36.6, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:18:28', '01', NULL, '2021-02-08 17:18:11', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706760389300226, '田发胜', '371323199107077910', '689322763', '1', '0', 36.6, 36.6, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:18:47', '01', NULL, '2021-02-08 17:18:30', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358706832531329025, '赵磊', '370205198512123016', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省青岛市市北区瑞昌路59号二单元102户附1户', '2021-02-08 17:19:03', '01', NULL, '2021-02-08 17:18:47', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707086609682433, '刘旭', '372926198310081139', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省巨野县凤凰街道办事处西三里庙行政村前三村95号', '2021-02-08 17:20:05', '01', NULL, '2021-02-08 17:19:48', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707174572625922, '王鹏', '371522198910170014', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区海尔路1号', '2021-02-08 17:20:26', '01', NULL, '2021-02-08 17:20:09', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707429766664194, '田发胜', '371323199107077910', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:21:27', '01', NULL, '2021-02-08 17:21:09', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707496409960449, '田发胜', '371323199107077910', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:21:43', '01', NULL, '2021-02-08 17:21:25', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707636990447618, '田发胜', '371323199107077910', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:22:16', '01', NULL, '2021-02-08 17:21:59', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358707998203908098, '于东洋', '230521199303171732', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '黑龙江省集贤县福利镇站前社区一组29号', '2021-02-08 17:23:42', '01', NULL, '2021-02-08 17:23:25', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358708206082002946, '田发胜', '371323199107077910', '689322763', '2', '-1', 36.3, 36.3, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:24:33', '01', NULL, '2021-02-08 17:24:15', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358708283097812994, '田发胜', '371323199107077910', '689322763', '2', '-1', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:24:52', '01', NULL, '2021-02-08 17:24:33', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358708335631470593, '黄海平', '370682198705262439', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省青岛市崂山区秦岭路12号32户', '2021-02-08 17:25:02', '01', NULL, '2021-02-08 17:24:45', NULL, '男');
INSERT INTO `sg_equipment_record` VALUES (1358708688422768642, '田发胜', '371323199107077910', '689322763', '1', '0', 36.5, 36.5, 1, NULL, '山东省沂水县龙家圈街道盆山村450号', '2021-02-08 17:26:26', '01', NULL, '2021-02-08 17:26:10', NULL, '男');

SET FOREIGN_KEY_CHECKS = 1;
