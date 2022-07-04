package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.SalarySheetVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SalaryService {
    /**
     * 制定工资单
     * @param employeeId 员工id
     */
    void makeSalarySheet(int employeeId, int companyBankAccountId);

    /**
     * 根据员工id返回当月的薪资
     * @param employeeId 员工id
     */
    BigDecimal getSalaryByEmployeeId(int employeeId);

    /**
     * 根据状态返回相应的工资单
     * @param state 工资单状态
     */
    List<SalarySheetVO> getSalarySheetByState(SalarySheetState state);

    /**
     * 根据员工id获得最近一次工资单的日期
     * @param employeeId 员工id
     */
    Date getLatestDateByEmployeeId(int employeeId);

    /**
     * 审批工资单
     * @param salarySheetId 工资单单据编号
     * @param state 修改后的状态
     */
    void approval(String salarySheetId, SalarySheetState state);
}
