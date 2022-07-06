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
INSERT INTO staff VALUES (0, 'seecoder', 'male', '1990-01-01', '12345678901', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (0, 'uncln', 'female', '1990-01-02', '12345678902', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (0, 'kucun', 'male', '1990-01-01', '12345678903', 'INVENTORY_MANAGER', 0);
INSERT INTO staff VALUES (0, 'zxr', 'male', '1990-01-03', '12345678904', 'SALE_MANAGER', 0);
INSERT INTO staff VALUES (0, '67', 'male', '1990-01-04', '12345678905', 'GM', 0);
INSERT INTO staff VALUES (0, 'xiaoshou', 'female', '1990-01-05', '12345678906', 'SALE_STAFF', 0);
INSERT INTO staff VALUES (0, 'Leone', 'male', '1990-01-06', '12345678907', 'GM', 0);
INSERT INTO staff VALUES (0, 'xiaoshoujingli', 'female', '1990-01-07', '12345678908', 'SALE_MANAGER', 0);


drop table if exists staff_user;
CREATE TABLE staff_user (
    staff_id INT(11) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(id) on delete cascade
);

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


