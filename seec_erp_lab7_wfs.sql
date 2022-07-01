-- ----------------------------
-- Table structure for bank_account
-- ----------------------------
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account`  (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户名称',
     `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for receive_money_transfer_list
-- ----------------------------
DROP TABLE IF EXISTS `receive_money_transfer_list`;
CREATE TABLE `receive_money_transfer_list`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `receive_money_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款单id',
    `bank_account_id` int(11) NULL DEFAULT NULL COMMENT '银行账户id',
    `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for receive_money_sheet
-- ----------------------------
DROP TABLE IF EXISTS `receive_money_sheet`;
CREATE TABLE `receive_money_sheet`  (
     `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx',
     `customer` int(11) NULL DEFAULT NULL COMMENT '客户（包括供应商和销售商）',
     `operator` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
     `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
     `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_money_transfer_list
-- ----------------------------
DROP TABLE IF EXISTS `pay_money_transfer_list`;
CREATE TABLE `pay_money_transfer_list`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `pay_money_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款单id',
    `bank_account_id` int(11) NULL DEFAULT NULL COMMENT '银行账户id',
    `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_money_sheet
-- ----------------------------
DROP TABLE IF EXISTS `pay_money_sheet`;
CREATE TABLE `pay_money_sheet`  (
    `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '付款单单据编号（格式为：FKD-yyyyMMdd-xxxxx',
    `customer` int(11) NULL DEFAULT NULL COMMENT '客户（包括供应商和销售商）',
    `operator` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
    `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
