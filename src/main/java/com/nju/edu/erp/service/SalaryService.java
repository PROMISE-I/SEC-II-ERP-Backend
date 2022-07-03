package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.SalarySheetVO;

import java.util.List;

public interface SalaryService {
    /**
     * 制定工资单
     * @param employeeId 员工id
     * @param bankAccountId 员工银行账户id
     */
    void makeSalarySheet(int employeeId, int bankAccountId);

    /**
     * 根据员工id返回当月的薪资
     * @param employeeId 员工id
     */
    int getSalaryByEmployeeId(int employeeId);

    /**
     * 根据状态返回相应的工资单
     * @param state 工资单状态
     */
    List<SalarySheetVO> getSalarySheetByState(SalarySheetState state);
}
