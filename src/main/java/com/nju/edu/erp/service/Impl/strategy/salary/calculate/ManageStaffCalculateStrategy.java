package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;
import java.util.BitSet;

/**
 * @author WFS
 * @date 2022/7/6 12:06
 */
public class ManageStaffCalculateStrategy extends SalaryCalculateStrategy {

    public ManageStaffCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly, BigDecimal totalSaleAmount, BigDecimal commissionRate) {
        super(basicSalary, postSalary, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal calculate() {
        return basicSalary.add(postSalary).multiply(BigDecimal.valueOf(12));
    }
}
