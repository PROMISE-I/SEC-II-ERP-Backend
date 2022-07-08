package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/6 12:06
 */
public class SaleStaffCalculateStrategy extends CheckInStaffSalaryCalculateStrategy {
    /**
     * 销售总额
     */
    protected BigDecimal totalSaleAmount;

    /**
     * 提成比率
     */
    protected BigDecimal commissionRate;

    public SaleStaffCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly, BigDecimal totalSaleAmount, BigDecimal commissionRate) {
        super(basicSalary, postSalary, checkInTimeMonthly);
        this.totalSaleAmount = totalSaleAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public BigDecimal calculate() {
        return super.calculate().add(totalSaleAmount.multiply(commissionRate));
    }
}
