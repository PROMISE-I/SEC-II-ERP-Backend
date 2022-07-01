package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.model.po.PositionInfoPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionInfoVO {
    public PositionInfoVO(PositionInfoPO positionInfoPO){
        this.title = positionInfoPO.getTitle();
        this.baseSalary = positionInfoPO.getBaseSalary();
        this.specialSalary = positionInfoPO.getSpecialSalary();
        this.level = positionInfoPO.getLevel();
        this.salaryCalculateMethod = positionInfoPO.getSalaryCalculateMethod();
        this.salaryPaymentMethod = positionInfoPO.getSalaryPaymentMethod();
        this.tax = positionInfoPO.getTax();
    }
    /**
     * 岗位名称
     */
    String title;

    /**
     * 基本工资
     */
    Integer baseSalary;
    /**
     * 岗位工资
     */
    Integer specialSalary;
    /**
     * 岗位级别
     */
    Integer level;
    /**
     * 薪资计算方式
     */
    String salaryCalculateMethod;
    /**
     * 薪资发放方式
     */
    String salaryPaymentMethod;
    /**
     * 扣税
     */
    Integer tax;
}
