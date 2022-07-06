package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.SalaryCalculateType;
import com.nju.edu.erp.enums.SalarySendType;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.PositionInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionInfoPO {
    public PositionInfoPO(PositionInfoVO positionInfoVO){
        this.title = positionInfoVO.getTitle();
        this.baseSalary = positionInfoVO.getBaseSalary();
        this.specialSalary = positionInfoVO.getSpecialSalary();
        this.level = positionInfoVO.getLevel();
        this.salaryCalculateMethod = positionInfoVO.getSalaryCalculateMethod();
        this.salaryPaymentMethod = positionInfoVO.getSalaryPaymentMethod();
        this.tax = positionInfoVO.getTax();
    }
    /**
     * 岗位名称
     */
    Role title;

    /**
     * 基本工资
     */
    BigDecimal baseSalary;
    /**
     * 岗位工资
     */
    BigDecimal specialSalary;
    /**
     * 岗位级别
     */
    Integer level;
    /**
     * 薪资计算方式
     */
    SalaryCalculateType salaryCalculateMethod;
    /**
     * 薪资发放方式
     */
    SalarySendType salaryPaymentMethod;
    /**
     * 扣税
     */
    BigDecimal tax;
}
