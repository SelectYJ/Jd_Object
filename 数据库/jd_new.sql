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

 Date: 30/06/2023 12:43:17
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
  `information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `img` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '华为', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (2, '小米', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (3, 'vivo', '挺好的', 3900.5, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (4, 'oppo', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (5, '一加', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (6, '联想', '很nice', 999, '5.jpg', '2023-06-12 23:12:00', NULL);
INSERT INTO `goods` VALUES (7, '华为1', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (8, '小米1', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (9, 'vivo1', '挺好的', 4999.55, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (10, 'oppo1', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (11, '一加1', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (12, '华为1', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (13, '小米2', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (14, 'vivo3', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (15, 'oppo4', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (16, '一加4', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (17, '联想5', '很nice', 999, '5.jpg', '2023-06-12 23:12:00', NULL);
INSERT INTO `goods` VALUES (18, '华为6', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (19, '小米6', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (20, 'vivo6', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (21, 'oppo6', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (22, '一加6', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (23, '华为7', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (24, '小米7', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (25, 'vivo7', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (26, 'oppo7', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (27, '一加7', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (28, '联想7', '很nice', 999, '5.jpg', '2023-06-12 23:12:00', NULL);
INSERT INTO `goods` VALUES (29, '华为8', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (30, '小米8', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (31, 'vivo8', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (32, 'oppo8', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (33, '一加9', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (34, '华为11', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (35, '小米11', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (36, 'vivo11', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (37, 'oppo11', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (38, '一加11', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (39, '联想13', '很nice', 999, '5.jpg', '2023-06-12 23:12:00', NULL);
INSERT INTO `goods` VALUES (40, '华为13', '性能好', 99, '1.jpg', '2023-06-10 22:15:23', '2023-06-10 22:15:27');
INSERT INTO `goods` VALUES (41, '小米14', '嘎嘎香', 66, '2.jpg', '2023-06-10 22:17:56', '2023-06-10 22:18:00');
INSERT INTO `goods` VALUES (42, 'vivo13', '挺好的', 100, '3.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (43, 'oppo13', '拍照好看', 200, '4.jpg', '2023-06-12 23:09:58', NULL);
INSERT INTO `goods` VALUES (44, '一加12', '不错不错', 333, '5.jpg', '2023-06-12 23:11:05', NULL);
INSERT INTO `goods` VALUES (45, '小米9', '杠杠的', 2000, '1.jpg', '2023-06-22 10:30:12', '2023-06-22 10:30:15');

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
) ENGINE = InnoDB AUTO_INCREMENT = 147 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '结算的商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recycle
-- ----------------------------
INSERT INTO `recycle` VALUES (134, 25, 4, 'oppo', 200, 1, '2023-06-27 22:40:32');
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

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `goods_id` int(0) NULL DEFAULT NULL,
  `goods_count` int(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 342 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (1, 13, 3, 12, '2023-06-21 22:53:51', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (2, 13, 5, 1000, '2023-06-21 23:08:34', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (172, 16, 5, 1, '2023-06-18 10:46:02', '2023-06-25 15:06:46');
INSERT INTO `shopping` VALUES (340, 9, 4, 1, '2023-06-28 11:24:43', '2023-06-28 11:24:43');
INSERT INTO `shopping` VALUES (341, 9, 8, 1, '2023-06-28 11:26:42', '2023-06-28 11:26:42');
INSERT INTO `shopping` VALUES (342, 9, 7, 1, '2023-06-28 11:26:44', '2023-06-28 11:26:43');

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
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '李四', NULL, 1, 111, '我爱吃西瓜', 'Url:adadad', 'lisi', '123456', 0, '2023-06-11 09:50:11', '2023-06-11 16:46:47');
INSERT INTO `user` VALUES (9, 'xiao', NULL, 1, 123, NULL, '0', '999', '111', 0, '2023-06-11 15:40:59', '2023-06-28 11:29:26');
INSERT INTO `user` VALUES (17, 'xiaowu123', NULL, 1, 23, NULL, '0', 'xiaowu123', '123', 0, '2023-06-18 11:08:20', '2023-06-18 11:09:39');
INSERT INTO `user` VALUES (24, '小5', NULL, 1, 0, NULL, '0', '55', '123', 0, '2023-06-25 16:25:21', '2023-06-25 16:25:21');
INSERT INTO `user` VALUES (25, '111', '1301808700@qq.com', 1, 122, NULL, '0', '111', '123456', 0, '2023-06-27 22:39:53', '2023-06-30 00:54:26');
INSERT INTO `user` VALUES (26, '1111', NULL, 1, 0, NULL, '0', '1111', '1111', 0, '2023-06-29 20:13:27', '2023-06-29 20:13:27');
INSERT INTO `user` VALUES (31, '我我饿说', NULL, 1, 0, NULL, '0', '我我饿说五若对方', '111', 0, '2023-06-29 22:06:26', '2023-06-29 22:06:26');
INSERT INTO `user` VALUES (32, '11111', NULL, 1, 0, NULL, '0', '11111', '111', 0, '2023-06-30 00:41:50', '2023-06-30 00:41:50');

SET FOREIGN_KEY_CHECKS = 1;
