package com.nju.edu.erp.service.Interface.finance.salary;

import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.salary.SalarySheetVO;

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

    /**
     * 获得所有工资单
     * @return 工资单列表
     */
    List<SalarySheetVO> findAllSalarySheets();

    /**
     * 红冲功能的工资单单据制定
     * @param salarySheetVO 工资单内容
     */
    void redInkOffsetSheetMake(SalarySheetVO salarySheetVO);

    /**
     * 返回去年前11个月份的原始工资总额
     * @param staffId 员工编号
     * @return 原始工资总额
     */
    BigDecimal getTotalAmountBeforeNovember(int staffId);

    /**
     * 查看单据是否存在
     * @param sheetId 单据编号
     * @return 是否存在, true 表示存在
     */
    boolean isSheetExists(String sheetId);
}
