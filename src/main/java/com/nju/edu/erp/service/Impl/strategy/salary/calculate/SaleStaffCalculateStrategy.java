package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/6 12:06
 */
public class SaleStaffCalculateStrategy extends SalaryCalculateStrategy {

    public SaleStaffCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly, BigDecimal totalSaleAmount, BigDecimal commissionRate) {
        super(basicSalary, postSalary, checkInTimeMonthly, totalSaleAmount, commissionRate);
    }

    @Override
    public BigDecimal calculate() {
        return basicSalary.multiply(checkInTimeMonthly)
                .multiply(BigDecimal.valueOf(1/30))
                .add(postSalary)
                .add(totalSaleAmount.multiply(commissionRate));
    }
}
