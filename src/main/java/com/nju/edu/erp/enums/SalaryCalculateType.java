package com.nju.edu.erp.enums;

/**
 * @author WFS
 * @date 2022/7/5 20:31
 */
public enum SalaryCalculateType implements BaseEnum<SalaryCalculateType, String>{

    PLAIN_STAFF_PAY("普通员工薪资计算"),
    COMMISSION_STAFF_PAY("提成员工薪资计算"),
    MANAGE_STAFF_PAY("管理员工薪资计算");


    private final String value;

    SalaryCalculateType(String value) {this.value = value;}

    @Override
    public String getValue() {
        return value;
    }
}
