package com.nju.edu.erp.dao.finance;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SalaryDao {
    /**
     * 存入工资单
     * @param salarySheet 工资单
     */
    void saveSheet(SalarySheetPO salarySheet);

    /**
     * 通过员工id和时间查找工资单
     * @param employeeId 员工id
     * @param date 日期
     * @return 工资单
     */
    SalarySheetPO findSheetByEmployeeIdAndDate(int employeeId, Date date);

    /**
     * 查询所有工资单
     * @return 所有工资单
     */
    List<SalarySheetPO> findAllSheet();

    /**
     * 按状态查询所有工资单
     * @return 工资单列表
     */
    List<SalarySheetPO> findAllSheetByState();

    /**
     * 根据员工id查找最近一个工资单
     * @param employeeId 员工id
     * @return 工资单
     */
    SalarySheetPO findLatestByEmployeeId(int employeeId);

    /**
     * 根据工资单id查找工资单
     * @param salarySheetId 工资单id
     * @return 工资单
     */
    SalarySheetPO findSheetById(String salarySheetId);

    /**
     * 修改工资单id的状态
     * @param salarySheetId 工资单id
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateSheetState(String salarySheetId, SalarySheetState state);

    /**
     * 根据状态和工资单id修改状态
     * @param salarySheetId 工资单id
     * @param prevState 修改前的状态
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateSheetStateOnPrev(String salarySheetId, SalarySheetState prevState, SalarySheetState state);
}
