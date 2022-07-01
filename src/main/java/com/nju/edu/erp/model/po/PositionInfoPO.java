package com.nju.edu.erp.model.po;

import com.nju.edu.erp.model.vo.PositionInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
