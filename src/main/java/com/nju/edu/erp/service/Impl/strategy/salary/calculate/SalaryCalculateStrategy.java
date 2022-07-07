package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/6 12:03
 */
public class SalaryCalculateStrategy {
    /**
     * 基本工资
     */
    protected BigDecimal basicSalary;

    /**
     * 岗位工资
     */
    protected BigDecimal postSalary;

    /**
     * 打卡次数
     */
    protected BigDecimal checkInTimeMonthly;

    /**
     * 销售总额
     */
    protected BigDecimal totalSaleAmount;

    /**
     * 提成比率
     */
    protected BigDecimal commissionRate;

    public SalaryCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly, BigDecimal totalSaleAmount, BigDecimal commissionRate) {
        this.basicSalary = basicSalary;
        this.postSalary = postSalary;
        this.checkInTimeMonthly = checkInTimeMonthly;
        this.totalSaleAmount = totalSaleAmount;
        this.commissionRate = commissionRate;
    }

    public BigDecimal calculate() {
        return BigDecimal.ZERO;
    };
}
