# 打卡信息: 员工号，日期
drop table if exists attendance;
CREATE TABLE attendance(
    staff_id INT NOT NULL,
    `date` VARCHAR(20) NOT NULL,
    PRIMARY KEY (staff_id, `date`)
);

# 岗位信息：名称，基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息。
drop table if exists position;
CREATE TABLE position (
    title VARCHAR(20) PRIMARY KEY COMMENT '名称',
    base_salary DECIMAL(10, 2) NOT NULL COMMENT '基本工资',
    level INT NOT NULL COMMENT '岗位级别',
    special_salary DECIMAL(10, 2) NOT NULL COMMENT '岗位工资',
    salary_calculate_method VARCHAR(15) NOT NULL COMMENT '薪资计算方式',
    salary_payment_method VARCHAR(15) NOT NULL COMMENT '薪资发放方式',
    tax DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '扣税'
);

-- ----------------------------
-- Records of position
-- ----------------------------

INSERT INTO position VALUES ('SALE_STAFF', 5000, 1, 10000, '提成员工薪资计算', '每月发放', 0.1);
INSERT INTO position VALUES ('SALE_MANAGER', 10000, 3, 40000, '提成员工薪资计算', '每月发放', 0.3);
INSERT INTO position VALUES ('INVENTORY_MANAGER', 5000, 1, 15000, '普通员工薪资计算', '每月发放', 0.15);
INSERT INTO position VALUES ('FINANCIAL_STAFF', 5000, 1, 15000, '普通员工薪资计算', '每月发放', 0.15);
INSERT INTO position VALUES ('HR', 6000, 2, 15000, '普通员工薪资计算', '每月发放', 0.15);
INSERT INTO position VALUES ('GM', 20000, 5, 100000, '管理员工薪资计算', '每年发放', 0.3);


drop table if exists staff;
CREATE TABLE staff(
    `id` int(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    gender VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    birthday VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    phone VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    position VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    balance decimal(10,2 ) DEFAULT 0
);
-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO staff VALUES (1, 'seecoder', '男', '1990-01-01', '12345678901', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (2, 'uncln', '女', '1990-01-02', '12345678902', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (3, 'kucun', '男', '1990-01-01', '12345678903', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (4, 'sky', '男', '1990-09-02', '12787979', 'ADMIN', 0);
INSERT INTO staff VALUES (5, 'zxr', '男', '1990-01-03', '12345678904', 'SALE_MANAGER', 0);
INSERT INTO staff VALUES (6, '67', '男', '1990-01-04', '12345678905', 'GM', 0);
INSERT INTO staff VALUES (7, 'xiaoshou', '女', '1990-01-05', '12345678906', 'SALE_STAFF', 0);
INSERT INTO staff VALUES (8, 'Leone', '男', '1990-01-06', '12345678907', 'GM', 0);
INSERT INTO staff VALUES (9, 'xiaoshoujingli', '女', '1990-01-07', '12345678908', 'SALE_MANAGER', 0);
INSERT INTO staff VALUES (10, 'slf', '男', '1990-01-08', '12345678909', 'HR', 0);
INSERT INTO staff VALUES (11, 'wfs', '男', '1990-01-09', '12345678910', 'FINANCIAL_STAFF', 0);



drop table if exists staff_user;
CREATE TABLE staff_user (
    staff_id INT(11) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(id) on delete cascade
);

INSERT INTO staff_user VALUES (1, 1);
INSERT INTO staff_user VALUES (2, 2);
INSERT INTO staff_user VALUES (3, 3);
INSERT INTO staff_user VALUES (4, 4);
INSERT INTO staff_user VALUES (5, 5);
INSERT INTO staff_user VALUES (6, 6);
INSERT INTO staff_user VALUES (7, 7);
INSERT INTO staff_user VALUES (8, 8);
INSERT INTO staff_user VALUES (9, 9);
INSERT INTO staff_user VALUES (10, 10);
INSERT INTO staff_user VALUES (11, 11);

drop table if exists present_info;
CREATE TABLE present_info (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    level INT NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

drop table if exists promotion;
CREATE TABLE promotion (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    level INT NOT NULL,
    discount DECIMAL(10, 2) DEFAULT 0,
    coupon DECIMAL(10, 2) DEFAULT 0,
    begin VARCHAR(255) NOT NULL,
    end VARCHAR(255) NOT NULL
);

drop table if exists combinatorial_discount;
CREATE TABLE combinatorial_discount (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_one_id VARCHAR(255) NOT NULL,
    product_two_id VARCHAR(255) NOT NULL,
    discount_amount DECIMAL(10, 2) NOT NULL DEFAULT 0,
    begin VARCHAR(255) NOT NULL ,
    end VARCHAR(255) NOT NULL
);
INSERT INTO combinatorial_discount(product_one_id, product_two_id, discount_amount, begin, end)
VALUES ('0000000000500000', '0000000000400001', 1000, '2002-09-09', '2022-09-09');
INSERT INTO combinatorial_discount(product_one_id, product_two_id, discount_amount, begin, end)
VALUES ('0000000000500002', '0000000000400001', 2000, '2002-08-09', '2022-09-09');
INSERT INTO combinatorial_discount(product_one_id, product_two_id, discount_amount, begin, end)
VALUES ('0000000000400000', '0000000000500002', 1000, '2002-10-09', '2022-11-09');

# 根据用户级别的促销策略默认数据
INSERT INTO promotion (level, begin, end)
VALUES (1, '1970-01-01', '1970-01-01');
INSERT INTO promotion (level, begin, end)
VALUES (2, '1970-01-01', '1970-01-01');
INSERT INTO promotion (level, begin, end)
VALUES (3, '1970-01-01', '1970-01-01');
INSERT INTO promotion (level, begin, end)
VALUES (4, '1970-01-01', '1970-01-01');
INSERT INTO promotion (level, begin, end)
VALUES (5, '1970-01-01', '1970-01-01');


