package com.nju.edu.erp.model.po.finance;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetPO {

    /**
     * 工资单编号，格式："GZD-yyyyMM-sid"
     */
    private String id;

    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 公司银行账号编号
     */
    private Integer companyBankAccountId;

    /**
     * 应发工资
     */
    private BigDecimal rawSalary;

    /**
     * 扣除税款
     */
    private BigDecimal tax;

    /**
     * 实发金额
     */
    private BigDecimal actualSalary;

    /**
     * 单据状态
     */
    private SalarySheetState state;

    /**
     * 单据创建时间
     */
    private Date createTime;
}
