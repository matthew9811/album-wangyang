/*
 Navicat Premium Data Transfer

 Source Server         : wang
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 106.52.247.35:3306
 Source Schema         : wang

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 21/02/2020 01:20:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customer_id` int(11) NOT NULL COMMENT 'æ‹¥æœ‰è€…id',
  `create_time` datetime(0) NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `update_time` datetime(0) NOT NULL COMMENT 'æ›´æ–°æ—¶é—´(é»˜è®¤ä¸ºåˆ›å»ºæ—¶é—´)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for album_photo
-- ----------------------------
DROP TABLE IF EXISTS `album_photo`;
CREATE TABLE `album_photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `photo_id` int(11) NOT NULL COMMENT 'ç…§ç‰‡id',
  `album_id` int(11) NOT NULL COMMENT 'ç›¸å†Œid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'å¾®ä¿¡æŽˆæƒæ ‡è¯†',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `link` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'å›¾ç‰‡é“¾æŽ¥',
  `customer_id` int(11) NOT NULL COMMENT 'æ‹¥æœ‰è€…id',
  `upload_time` datetime(0) NOT NULL COMMENT 'ä¸Šä¼ æ—¶é—´',
  `area` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'æ‹æ‘„åœ°å€',
  `filming_time` date NULL DEFAULT NULL COMMENT 'æ‹æ‘„æ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
