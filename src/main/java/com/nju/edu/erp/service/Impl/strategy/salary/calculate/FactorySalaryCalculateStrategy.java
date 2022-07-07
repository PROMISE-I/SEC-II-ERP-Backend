package com.nju.edu.erp.service.Impl.strategy.salary.calculate;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/7 8:01
 */
public class FactorySalaryCalculateStrategy {

    public static SalaryCalculateStrategy productStrategy(StaffInfo staffInfo) {
        switch(staffInfo.getSalaryCalculateMethod()) {
            case PLAIN_STAFF_PAY:
                return new PlainStaffCalculateStrategy(staffInfo.getBasicSalary(), staffInfo.getSpecialSalary(),
                        staffInfo.getCheckInTimeMonthly(), BigDecimal.ZERO, BigDecimal.ZERO);
            case COMMISSION_STAFF_PAY:
                return new SaleStaffCalculateStrategy(staffInfo.getBasicSalary(), staffInfo.getSpecialSalary(),
                        staffInfo.getCheckInTimeMonthly(), staffInfo.getTotalSaleAmount(), BigDecimal.valueOf(0.3));
            case MANAGE_STAFF_PAY:
                return new SaleStaffCalculateStrategy(staffInfo.getBasicSalary(), staffInfo.getSpecialSalary(),
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
            default:
                throw new RuntimeException("没有对应的薪资计算策略，请联系管理员！");
        }
    }
}
