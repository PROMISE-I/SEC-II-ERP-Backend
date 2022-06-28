# 打卡信息: 员工号，日期
drop table if exists attendance;
CREATE TABLE attendance(
    staff_id INT NOT NULL,
    `date` VARCHAR(20) NOT NULL,
    PRIMARY KEY (staff_id, `date`)
);