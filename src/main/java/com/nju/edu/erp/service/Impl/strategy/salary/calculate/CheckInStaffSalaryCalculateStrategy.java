package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/8 10:24
 */
public class CheckInStaffSalaryCalculateStrategy extends StaffSalaryCalculateStrategy{
    /**
     * 打卡次数
     */
    protected BigDecimal checkInTimeMonthly;

    public CheckInStaffSalaryCalculateStrategy(BigDecimal basicSalary, BigDecimal postSalary, BigDecimal checkInTimeMonthly) {
        super(basicSalary, postSalary);
        this.checkInTimeMonthly = checkInTimeMonthly;
    }

    /**
     * 薪资计算方式
     * @return 原始薪资
     */
    @Override
    public BigDecimal calculate() {
        return basicSalary.multiply(checkInTimeMonthly).multiply(BigDecimal.valueOf(1.0/30)).add(postSalary);
    }
}
