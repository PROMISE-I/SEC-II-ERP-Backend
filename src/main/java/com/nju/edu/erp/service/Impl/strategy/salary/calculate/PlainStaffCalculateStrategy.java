package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author WFS
 * @date 2022/7/6 12:05
 */
public class PlainStaffCalculateStrategy extends SalaryCalculateStrategy {

    public PlainStaffCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly, BigDecimal totalSaleAmount, BigDecimal commissionRate) {
        super(basicSalary, postSalary, checkInTimeMonthly, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal calculate() {
        return basicSalary.multiply(checkInTimeMonthly)
                .divide(BigDecimal.valueOf(30), RoundingMode.HALF_DOWN)
                .add(postSalary);
    }
}
