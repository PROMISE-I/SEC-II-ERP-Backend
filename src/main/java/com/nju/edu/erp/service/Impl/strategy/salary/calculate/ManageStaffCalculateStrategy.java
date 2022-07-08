package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/6 12:06
 */
public class ManageStaffCalculateStrategy extends StaffSalaryCalculateStrategy {

    public ManageStaffCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary) {
        super(basicSalary, postSalary);
    }

    /**
     * 薪资计算方式
     * @return 原始薪资
     */
    @Override
    public BigDecimal calculate() {
        return super.calculate().multiply(BigDecimal.valueOf(12));
    }
}
