package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.SalarySheetVO;

import java.util.List;

public interface SalaryService {
    void makeSalarySheet(int employeeId, int bankAccountId);

    int getSalaryByEmployeeId(int employeeId);

    List<SalarySheetVO> getSalarySheetByState(SalarySheetState state);
}
