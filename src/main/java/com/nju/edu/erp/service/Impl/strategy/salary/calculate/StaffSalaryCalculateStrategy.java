package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/6 12:03
 */
public class StaffSalaryCalculateStrategy {
    /**
     * 基本工资
     */
    protected BigDecimal basicSalary;

    /**
     * 岗位工资
     */
    protected BigDecimal postSalary;

    public StaffSalaryCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary) {
        this.basicSalary = basicSalary;
        this.postSalary = postSalary;
    }

    /**
     * 薪资计算方式
     * @return 原始薪资
     */
    public BigDecimal calculate() {
        return basicSalary.add(postSalary);
    };
}
