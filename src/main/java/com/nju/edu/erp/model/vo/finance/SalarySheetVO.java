package com.nju.edu.erp.model.vo.finance;

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
public class SalarySheetVO {
    /**
     * 工资单编号，格式："GZD-yyyyMM-sid", 前端传入null
     */
    private String id;

    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 员工姓名, 前端传入null
     */
    private String staffName;

    /**
     * 公司银行账号编号
     */
    private Integer companyBankAccountId;

    /**
     * 应发工资，前端传入null
     */
    private BigDecimal rawSalary;

    /**
     * 扣除税款，前端传入null
     */
    private BigDecimal tax;

    /**
     * 实发金额，前端传入null
     */
    private BigDecimal actualSalary;

    /**
     * 单据状态，前端传入null
     */
    private SalarySheetState state;

    /**
     * 单据创建时间，前端传入null
     */
    private Date createTime;
}
