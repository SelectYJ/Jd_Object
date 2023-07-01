/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : jd_new

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 01/07/2023 14:18:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `family` int(0) NULL DEFAULT NULL COMMENT '商品分类',
  `information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `img` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '华为', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (2, '小米', 1, '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (3, 'vivo', 1, '挺好的', 3900.5, '3.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:29');
INSERT INTO `goods` VALUES (4, 'oppo', 1, '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:34');
INSERT INTO `goods` VALUES (5, '一加', 1, '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', '2023-06-30 14:44:35');
INSERT INTO `goods` VALUES (6, '联想', 1, '很nice', 999, '5.jpg', '2023-06-12 23:12:00', '2023-06-30 14:44:36');
INSERT INTO `goods` VALUES (7, '华为1', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (8, '小米1', 1, '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (9, 'vivo1', 1, '挺好的', 4999.55, '3.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:37');
INSERT INTO `goods` VALUES (10, 'oppo1', 1, '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:38');
INSERT INTO `goods` VALUES (11, '一加1', 1, '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', '2023-06-30 14:44:38');
INSERT INTO `goods` VALUES (12, '华为1', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (13, '小米2', 1, '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (14, 'vivo3', 1, '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:40');
INSERT INTO `goods` VALUES (15, 'oppo4', 1, '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:41');
INSERT INTO `goods` VALUES (16, '一加4', 1, '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', '2023-06-30 14:44:40');
INSERT INTO `goods` VALUES (17, '联想5', 1, '很nice', 999, '5.jpg', '2023-06-12 23:12:00', '2023-06-30 14:44:42');
INSERT INTO `goods` VALUES (18, '华为6', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (19, '小米6', 1, '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (20, 'vivo6', 1, '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:43');
INSERT INTO `goods` VALUES (21, 'oppo6', 1, '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:44');
INSERT INTO `goods` VALUES (22, '一加6', 1, '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', '2023-06-30 14:44:45');
INSERT INTO `goods` VALUES (23, '华为7', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (24, '小米7', 1, '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (25, 'vivo7', 1, '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:46');
INSERT INTO `goods` VALUES (26, 'oppo7', 1, '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', '2023-06-30 14:44:46');
INSERT INTO `goods` VALUES (27, '一加7', 1, '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', '2023-06-30 14:44:47');
INSERT INTO `goods` VALUES (28, '联想7', 1, '很nice', 999, '5.jpg', '2023-06-12 23:12:00', '2023-06-30 14:44:48');
INSERT INTO `goods` VALUES (29, '华为8', 1, '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (30, '联想Y7000', 3, '好用', 6888, '1.jpg', '2023-06-30 14:42:04', '2023-06-30 14:44:49');
INSERT INTO `goods` VALUES (31, '联想Y9000', 3, '好', 8999, '1.jpg', '2023-06-30 14:47:11', '2023-06-30 14:47:11');
INSERT INTO `goods` VALUES (32, '衣服', 4, '好看', 123, '1.jpg', '2023-06-30 14:48:05', '2023-06-30 14:48:05');
INSERT INTO `goods` VALUES (33, '外星人', 3, '好贵', 19999, '1.jpg', '2023-06-30 14:49:04', '2023-06-30 14:49:04');
INSERT INTO `goods` VALUES (34, '苹果', 5, '好吃', 12, '1.jpg', '2023-06-30 14:50:09', '2023-06-30 14:50:09');
INSERT INTO `goods` VALUES (35, '香蕉', 5, '好大', 10, '1.jpg', '2023-06-30 14:50:47', '2023-06-30 14:50:47');

-- ----------------------------
-- Table structure for goods_family
-- ----------------------------
DROP TABLE IF EXISTS `goods_family`;
CREATE TABLE `goods_family`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `family` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品类型',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `goods_family_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_family
-- ----------------------------
INSERT INTO `goods_family` VALUES (1, '手机', '2023-06-30 14:32:37');
INSERT INTO `goods_family` VALUES (2, '家用电器', '2023-06-30 14:33:15');
INSERT INTO `goods_family` VALUES (3, '电脑', '2023-06-30 14:33:40');
INSERT INTO `goods_family` VALUES (4, '男装', '2023-06-30 14:39:08');
INSERT INTO `goods_family` VALUES (5, '食品生鲜', '2023-06-30 14:39:26');

-- ----------------------------
-- Table structure for recycle
-- ----------------------------
DROP TABLE IF EXISTS `recycle`;
CREATE TABLE `recycle`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名字',
  `goods_price` double NULL DEFAULT NULL COMMENT '商品价格',
  `goods_count` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '结算的商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recycle
-- ----------------------------
INSERT INTO `recycle` VALUES (135, 25, 8, '小米1', 66, 1, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (136, 25, 7, '华为1', 99, 1, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (137, 25, 12, '华为1', 99, 1, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (138, 25, 14, 'vivo3', 100, 1, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (139, 25, 15, 'oppo4', 200, 9, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (140, 25, 19, '小米6', 66, 9, '2023-06-27 22:40:32');
INSERT INTO `recycle` VALUES (141, 9, 3, 'vivo', 3900.5, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (142, 9, 4, 'oppo', 200, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (143, 9, 7, '华为1', 99, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (144, 9, 11, '一加1', 333, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (145, 9, 13, '小米2', 66, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (146, 9, 14, 'vivo3', 100, 1, '2023-06-27 22:41:32');
INSERT INTO `recycle` VALUES (147, 9, 15, 'oppo4', 200, 7, '2023-06-27 23:42:13');
INSERT INTO `recycle` VALUES (148, 33, 4, 'oppo', 200, 1, '2023-06-30 16:48:37');
INSERT INTO `recycle` VALUES (149, 33, 3, 'vivo', 3900.5, 1, '2023-06-30 16:48:37');
INSERT INTO `recycle` VALUES (150, 33, 33, '外星人', 19999, 2, '2023-06-30 20:48:23');
INSERT INTO `recycle` VALUES (151, 33, 31, '联想Y9000', 8999, 1, '2023-06-30 20:48:23');
INSERT INTO `recycle` VALUES (152, 33, 3, 'vivo', 3900.5, 3, '2023-06-30 20:48:35');
INSERT INTO `recycle` VALUES (153, 33, 34, '苹果', 12, 1, '2023-06-30 20:48:35');
INSERT INTO `recycle` VALUES (154, 25, 3, 'vivo', 3900.5, 1, '2023-07-01 12:14:05');

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `family` int(0) NULL DEFAULT NULL COMMENT '商品类别',
  `goods_count` int(0) NULL DEFAULT 1 COMMENT '商品数量',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 419 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (1, 13, 3, 1, 12, '2023-06-21 22:53:51', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (2, 13, 5, 1, 1000, '2023-06-21 23:08:34', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (172, 16, 5, 1, 1, '2023-06-18 10:46:02', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (340, 9, 4, 1, 1, '2023-06-28 11:24:43', '2023-06-28 11:24:43');
INSERT INTO `shopping` VALUES (341, 9, 8, 1, 1, '2023-06-28 11:26:42', '2023-06-28 11:26:42');
INSERT INTO `shopping` VALUES (342, 9, 7, 1, 1, '2023-06-28 11:26:44', '2023-06-28 11:26:43');
INSERT INTO `shopping` VALUES (357, 33, 2, 1, 2, '2023-06-30 17:01:07', NULL);
INSERT INTO `shopping` VALUES (359, 33, 5, 1, 1, '2023-06-30 17:01:09', '2023-06-30 17:01:09');
INSERT INTO `shopping` VALUES (366, 33, 32, 4, 1, '2023-06-30 20:15:10', '2023-06-30 20:15:09');
INSERT INTO `shopping` VALUES (367, 33, 29, 1, 2, '2023-06-30 20:15:19', NULL);
INSERT INTO `shopping` VALUES (371, 33, 35, 5, 1, '2023-06-30 20:44:28', '2023-06-30 20:44:27');
INSERT INTO `shopping` VALUES (411, 25, 3, 1, 1, '2023-07-01 14:12:55', '2023-07-01 14:12:54');
INSERT INTO `shopping` VALUES (412, 25, 2, 1, 1, '2023-07-01 14:12:56', '2023-07-01 14:12:55');
INSERT INTO `shopping` VALUES (414, 25, 35, 5, 1, '2023-07-01 14:13:03', '2023-07-01 14:13:03');
INSERT INTO `shopping` VALUES (415, 25, 34, 5, 1, '2023-07-01 14:13:04', '2023-07-01 14:13:03');
INSERT INTO `shopping` VALUES (416, 25, 33, 3, 1, '2023-07-01 14:13:05', '2023-07-01 14:13:04');
INSERT INTO `shopping` VALUES (417, 25, 32, 4, 1, '2023-07-01 14:13:06', '2023-07-01 14:13:06');
INSERT INTO `shopping` VALUES (418, 25, 31, 3, 1, '2023-07-01 14:13:08', '2023-07-01 14:13:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `email_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `gender` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '性别，1 男，0 女',
  `age` int(0) NULL DEFAULT 0 COMMENT '年龄',
  `information` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `img` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `money` double NULL DEFAULT 0 COMMENT '账户余额',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tb_user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '李四', NULL, 1, 111, '我爱吃西瓜', 'Url:adadad', 'lisi', '123456', 0, '2023-06-11 09:50:11', '2023-06-11 16:46:47');
INSERT INTO `user` VALUES (9, 'xiao', NULL, 1, 123, NULL, '0', '999', '111', 0, '2023-06-11 15:40:59', '2023-06-28 11:29:26');
INSERT INTO `user` VALUES (17, 'xiaowu123', NULL, 1, 23, NULL, '0', 'xiaowu123', '123', 0, '2023-06-18 11:08:20', '2023-06-18 11:09:39');
INSERT INTO `user` VALUES (24, '小5', NULL, 1, 0, NULL, '0', '55', '123', 0, '2023-06-25 16:25:21', '2023-06-25 16:25:21');
INSERT INTO `user` VALUES (25, '111', '2087888071@qq.com', 1, 122, NULL, '0', '111', '123456', 0, '2023-06-27 22:39:53', '2023-06-30 13:51:00');
INSERT INTO `user` VALUES (26, '1111', NULL, 1, 0, NULL, '0', '1111', '1111', 0, '2023-06-29 20:13:27', '2023-06-29 20:13:27');
INSERT INTO `user` VALUES (31, '我我饿说', NULL, 1, 0, NULL, '0', '我我饿说五若对方', '111', 0, '2023-06-29 22:06:26', '2023-06-29 22:06:26');
INSERT INTO `user` VALUES (32, '11111', NULL, 1, 0, NULL, '0', '11111', '111', 0, '2023-06-30 00:41:50', '2023-06-30 00:41:50');
INSERT INTO `user` VALUES (33, '1301808700', '1301808700@qq.com', 1, 122, NULL, '0', '1301808700@qq.com', '123456', 0, '2023-06-30 16:32:36', '2023-06-30 16:51:16');

SET FOREIGN_KEY_CHECKS = 1;
