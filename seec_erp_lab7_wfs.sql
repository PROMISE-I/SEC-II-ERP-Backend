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

-- ----------------------------
-- Table structure for salary_sheet
-- ----------------------------
DROP TABLE IF EXISTS `salary_sheet`;
CREATE TABLE `salary_sheet`  (
    `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工资单单据编号（格式为：GZD-yyyyMM-{staffId}',
    `staff_id` int(11) NULL DEFAULT NULL COMMENT '员工编号',
    `staff_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
    `company_bank_account_id` int(11) NULL DEFAULT NULL COMMENT '公司银行账号编号',
    `raw_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '应发工资',
    `tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '扣除税款',
    `actual_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '实发金额',
    `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for salary_calculate_type
-- ----------------------------
DROP TABLE IF EXISTS `salary_calculate_type`;
CREATE TABLE `salary_calculate_type`  (
     `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '自增主键',
     `type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资计算方式类型',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_calculate_type
-- ----------------------------
INSERT INTO salary_calculate_type VALUES (0, '普通员工薪资计算');
INSERT INTO salary_calculate_type VALUES (1, '提成员工薪资计算');
INSERT INTO salary_calculate_type VALUES (2, '管理员工薪资计算');

-- ----------------------------
-- Table structure for salary_send_type
-- ----------------------------
DROP TABLE IF EXISTS `salary_send_type`;
CREATE TABLE `salary_send_type`  (
   `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '自增主键',
   `type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资发放方式类型',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_send_type
-- ----------------------------
INSERT INTO salary_send_type VALUES (0, '每月发放');
INSERT INTO salary_send_type VALUES (1, '每年发放');