# 打卡信息: 员工号，日期
CREATE TABLE attendance(
                           staff_id INT NOT NULL,
                           `date` VARCHAR(20) NOT NULL,
                           PRIMARY KEY (staff_id, `date`)
);


# 岗位信息：名称，基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息。
CREATE TABLE position (
                          title VARCHAR(20) PRIMARY KEY COMMENT '名称',
                          base_salary INT NOT NULL COMMENT '基本工资',
                          level INT NOT NULL COMMENT '岗位级别',
                          special_salary INT NOT NULL COMMENT '岗位工资',
                          salary_calculate_method VARCHAR(15) NOT NULL COMMENT '薪资计算方式',
                          salary_payment_method VARCHAR(15) NOT NULL COMMENT '薪资发放方式',
                          tax INT NOT NULL DEFAULT 0 COMMENT '扣税'
);
