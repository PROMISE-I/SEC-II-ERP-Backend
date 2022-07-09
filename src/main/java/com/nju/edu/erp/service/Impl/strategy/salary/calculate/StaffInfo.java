package com.nju.edu.erp.service.Impl.strategy.salary.calculate;


import com.nju.edu.erp.enums.salary_strategy.SalaryCalculateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffInfo {

    private BigDecimal basicSalary;

    private BigDecimal specialSalary;

    private SalaryCalculateType salaryCalculateMethod;

    private BigDecimal totalSaleAmount;

    private BigDecimal checkInTimeMonthly;
}
