-- ----------------------------
-- Table structure for bank_account
-- ----------------------------
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account`(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户名称',
    `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank_account
-- ----------------------------
INSERT INTO `bank_account` VALUES (0, '南哪第一银行', 1000000.00);
INSERT INTO `bank_account` VALUES (1, '南哪第二银行', 5000000.00);
INSERT INTO `bank_account` VALUES (2, '南哪软院分行', 10000000.00);
INSERT INTO `bank_account` VALUES (3, '南哪商院分行', 50000000.00);

-- ----------------------------
-- Table structure for receive_money_transfer_list
-- ----------------------------
DROP TABLE IF EXISTS `receive_money_transfer_list`;
CREATE TABLE `receive_money_transfer_list`(
    `id`                     int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `receive_money_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款单id',
    `bank_account_id`        int(11) NULL DEFAULT NULL COMMENT '银行账户id',
    `amount`                 decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `remark`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive_money_transfer_list
-- ----------------------------
INSERT INTO `receive_money_transfer_list` VALUES (0, 'SKD-20220528-00000', 2, 1000000.00, '对应销售单：XSD-20220523-00000');
INSERT INTO `receive_money_transfer_list` VALUES (1, 'SKD-20220528-00000', 0, 39800.00, '对应销售单：XSD-20220523-00000');
INSERT INTO `receive_money_transfer_list` VALUES (2, 'SKD-20220528-00001', 2, 3000000.00, '对应销售单：XSD-20220524-00000');
INSERT INTO `receive_money_transfer_list` VALUES (3, 'SKD-20220528-00001', 0, 359800.00, '对应销售单：XSD-20220524-00000');
INSERT INTO `receive_money_transfer_list` VALUES (4, 'SKD-20220528-00002', 1, 495800.00, '对应销售单：XSD-20220524-00001');
INSERT INTO `receive_money_transfer_list` VALUES (5, 'SKD-20220528-00003', 1, 575800.00, '对应销售单：XSD-20220524-00002');

-- ----------------------------
-- Table structure for receive_money_sheet
-- ----------------------------
DROP TABLE IF EXISTS `receive_money_sheet`;
CREATE TABLE `receive_money_sheet`(
    `id`           varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx',
    `customer`     int(11) NULL DEFAULT NULL COMMENT '客户（包括供应商和销售商）',
    `operator`     varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
    `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `state`        varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive_money_sheet
-- ----------------------------
INSERT INTO `receive_money_sheet` VALUES ('SKD-20220528-00000', 2, 'sky', 1039800.00, '待一级审批', '2022-05-28 08:31:02');
INSERT INTO `receive_money_sheet` VALUES ('SKD-20220528-00001', 2, 'sky', 3359800.00, '待一级审批', '2022-05-28 08:32:11');
INSERT INTO `receive_money_sheet` VALUES ('SKD-20220528-00002', 2, 'sky', 495800.00, '待一级审批', '2022-05-28 08:32:37');
INSERT INTO `receive_money_sheet` VALUES ('SKD-20220528-00003', 2, 'sky', 575800.00, '待一级审批', '2022-05-28 08:33:06');

-- ----------------------------
-- Table structure for pay_money_transfer_list
-- ----------------------------
DROP TABLE IF EXISTS `pay_money_transfer_list`;
CREATE TABLE `pay_money_transfer_list`(
    `id`                 int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `pay_money_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款单id',
    `bank_account_id`    int(11) NULL DEFAULT NULL COMMENT '银行账户id',
    `amount`             decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `remark`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_money_transfer_list
-- ----------------------------
INSERT INTO `pay_money_transfer_list` VALUES (0, 'FKD-20220528-00000', 3, 1000000.00, '对应进货单：JHD-20220523-00000');
INSERT INTO `pay_money_transfer_list` VALUES (1, 'FKD-20220528-00001', 3, 2000000.00, '对应进货单：JHD-20220523-00001');
INSERT INTO `pay_money_transfer_list` VALUES (2, 'FKD-20220528-00001', 2, 200000.00, '对应进货单：JHD-20220523-00001');
INSERT INTO `pay_money_transfer_list` VALUES (3, 'FKD-20220528-00002', 2, 3000000.00, '对应进货单：JHD-20220523-00002');
INSERT INTO `pay_money_transfer_list` VALUES (4, 'FKD-20220528-00002', 0, 400000.00, '对应进货单：JHD-20220523-00002');
INSERT INTO `pay_money_transfer_list` VALUES (5, 'FKD-20220528-00002', 1, 50000.00, '对应进货单：JHD-20220523-00002');
INSERT INTO `pay_money_transfer_list` VALUES (6, 'FKD-20220528-00003', 3, 1650000.00, '对应进货单：JHD-20220524-00002');

-- ----------------------------
-- Table structure for pay_money_sheet
-- ----------------------------
DROP TABLE IF EXISTS `pay_money_sheet`;
CREATE TABLE `pay_money_sheet`(
    `id`           varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '付款单单据编号（格式为：FKD-yyyyMMdd-xxxxx',
    `customer`     int(11) NULL DEFAULT NULL COMMENT '客户（包括供应商和销售商）',
    `operator`     varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
    `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账总额',
    `state`        varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_money_sheet
-- ----------------------------
INSERT INTO `pay_money_sheet` VALUES ('FKD-20220528-00000', 1, 'sky', 1000000.00, '待一级审批', '2022-05-28 08:38:21');
INSERT INTO `pay_money_sheet` VALUES ('FKD-20220528-00001', 1, 'sky', 2200000.00, '待一级审批', '2022-05-28 08:40:25');
INSERT INTO `pay_money_sheet` VALUES ('FKD-20220528-00002', 1, 'sky', 3450000.00, '待一级审批', '2022-05-28 08:41:30');
INSERT INTO `pay_money_sheet` VALUES ('FKD-20220528-00003', 1, 'sky', 1650000.00, '待一级审批', '2022-05-28 08:41:59');

-- ----------------------------
-- Table structure for salary_sheet
-- ----------------------------
DROP TABLE IF EXISTS `salary_sheet`;
CREATE TABLE `salary_sheet`(
    `id`                      varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工资单单据编号（格式为：GZD-yyyyMM-{staffId}',
    `staff_id`                int(11) NULL DEFAULT NULL COMMENT '员工编号',
    `staff_name`              varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
    `company_bank_account_id` int(11) NULL DEFAULT NULL COMMENT '公司银行账号编号',
    `raw_salary`              decimal(10, 2) NULL DEFAULT NULL COMMENT '应发工资',
    `tax`                     decimal(10, 2) NULL DEFAULT NULL COMMENT '扣除税款',
    `actual_salary`           decimal(10, 2) NULL DEFAULT NULL COMMENT '实发金额',
    `state`                   varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
    `create_time`             datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for salary_calculate_type
-- ----------------------------
DROP TABLE IF EXISTS `salary_calculate_type`;
CREATE TABLE `salary_calculate_type`(
    `id`   int(11) NOT NULL COMMENT '自增主键',
    `type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资计算方式类型',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
CREATE TABLE `salary_send_type`(
    `id`   int(11) COMMENT '自增主键',
    `type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资发放方式类型',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_send_type
-- ----------------------------
INSERT INTO salary_send_type VALUES (0, '每月发放');
INSERT INTO salary_send_type VALUES (1, '每年发放');

-- ----------------------------
-- Table structure for year_end_awards
-- ----------------------------
DROP TABLE IF EXISTS `year_end_awards`;
CREATE TABLE `year_end_awards`(
    `staff_id` int(11) NOT NULL COMMENT '员工编号',
    `year`     int(11) NOT NULL COMMENT '年份',
    `amount`   decimal(10, 2) NULL DEFAULT NULL COMMENT '年终奖总额',
    PRIMARY KEY (`staff_id`, `year`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for total_price_promotion
-- ----------------------------
DROP TABLE IF EXISTS `total_price_promotion`;
CREATE TABLE `total_price_promotion` (
    `id`             varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号：ZJCXCL-yyyyMMdd-xxxxx',
    `threshold`      decimal(10, 2) NULL DEFAULT NULL COMMENT '总价阈值',
    `begin_time`     datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_time`       datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `operator`       varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
    `voucher_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '代金卷额度',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for total_price_promotion_content
-- ----------------------------
DROP TABLE IF EXISTS `total_price_promotion_content`;
CREATE TABLE `total_price_promotion_content`(
    `id`                       int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `total_price_promotion_id` varchar(31)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价促销策略的编号',
    `pid`                      varchar(31)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号',
    `quantity`                 int(11) NULL DEFAULT NULL COMMENT '数量',
    `unit_price`               decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
    `total_price`              decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
    `remark`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;